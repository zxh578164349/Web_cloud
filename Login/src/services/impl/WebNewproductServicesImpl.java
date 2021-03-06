package services.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.context.ContextLoader;

import com.spreada.utils.chinese.ZHConverter;

import dao.IKyVisaBillsDao;
import dao.IKyzVisaFlowDao;
import dao.IWebNewproductDao;
import entity.KyVisabillm;
import entity.KyVisabills;
import entity.KyzContactletter;
import entity.KyzExpectmatmFile;
import entity.KyzExpectmatmLog;
import entity.KyzVisaflow;
import entity.WebNewproduct;
import entity_temp.VisabillsTemp;
import services.IWebNewproductServices;
import util.GlobalMethod;
import util.PageBean;

public class WebNewproductServicesImpl implements IWebNewproductServices{
	private IWebNewproductDao webnewprodao;
	private IKyVisaBillsDao visabillDao;
	private IKyzVisaFlowDao visaDao;
	

	public void setVisaDao(IKyzVisaFlowDao visaDao) {
		this.visaDao = visaDao;
	}

	public void setVisabillDao(IKyVisaBillsDao visabillDao) {
		this.visabillDao = visabillDao;
	}

	public void setWebnewprodao(IWebNewproductDao webnewprodao) {
		this.webnewprodao = webnewprodao;
	}

	public void add(WebNewproduct obj) {
		// TODO Auto-generated method stub		
		webnewprodao.add(obj);
	}

	public PageBean findPageBean(int page, int pageSize, String factNo,
			String billNo, String createDateA, String createDateB,String PName) {
		// TODO Auto-generated method stub
		return webnewprodao.findPageBean(page, pageSize, factNo, billNo, createDateA, createDateB,PName);
	}
	
	public void delete(String billNo, KyzExpectmatmLog log) {
		// TODO Auto-generated method stub
		webnewprodao.delete(billNo, log);
	}

	public WebNewproduct findByBillNo(String billNo) {
		// TODO Auto-generated method stub
		return webnewprodao.findByBillNo(billNo);
	}

	public String findByfactNoACreatedate(String factNo, String createDate) {
		// TODO Auto-generated method stub
		return webnewprodao.findByfactNoACreatedate(factNo, createDate);
	}
	
	
	public Map<String, Object> print(String factNo, String billNo, String sort,KyVisabillm vbm) {
		// TODO Auto-generated method stub
		Map<String,Object>map_result=new HashMap<String,Object>();
		List<WebNewproduct>list=new ArrayList<WebNewproduct>();
		Map<String,Object>map=new HashMap<String,Object>();				
		WebNewproduct obj=webnewprodao.findByBillNo(billNo);
		if(obj==null){
			return null;
		}else{
			/*******************簡轉繁體********************/			
			obj.setPExp(ZHConverter.convert(obj.getPExp(), ZHConverter.TRADITIONAL));		
			obj.setPName(ZHConverter.convert(obj.getPName(), ZHConverter.TRADITIONAL));
			obj.setPResult(ZHConverter.convert(obj.getPResult(), ZHConverter.TRADITIONAL));
			obj.setPResultGuest(ZHConverter.convert(obj.getPResultGuest(), ZHConverter.TRADITIONAL));			
			/*******************簡轉繁體********************/
			list.add(obj);
		}						
		map.put("SUBREPORT_DIR",ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/jasper/audit/")+ "/");
		map.put("pic", ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/jasper/audit/images/")+ "/");//圖片路徑
		map.put("pfactno", factNo);
		map.put("pbillno",billNo);
		//map.put("title",result);
		//map.put("factname",factname);
								
		if(vbm==null){
			//vbm=visabillmDao.findById(factNo, sort, billNo);
			vbm=obj.getVbm();
		}		
		List<KyVisabills>list_visa=vbm.getKyVisabillses();
		List<KyzVisaflow>list_visaflow=visaDao.findByType(factNo,sort);
		
		/**
		 * 最後個不用審核的,就去掉
		 */
		int nos=visabillDao.findBillsWithNo(sort, billNo);			
		List<VisabillsTemp>list_visabillstemp=new ArrayList<VisabillsTemp>();		
		for(int i=0;i<list_visa.size()-nos;i++){//for
			VisabillsTemp visabillstemp=new VisabillsTemp();
			String visa_result="";
			String visamk_temp="";			
			
			String datestr=list_visa.get(i).getDateVisa();
			visabillstemp.setCreateDate(datestr);
			
			String name=list_visa.get(i).getVisaRank();
			String visamk=list_visa.get(i).getVisaMk();
			String memo=list_visa.get(i).getMemo();
			if(visamk.equals("Y")){
				visamk_temp="(已審核)";
			}
			if(visamk.equals("N")){
				visamk_temp="(未審核)";
			}
			if(visamk.equals("T")){
				visamk_temp="(未通過)";
			}			
			visa_result=name+visamk_temp;
			visabillstemp.setVisaNameAndMk(visa_result);
						
			//************************解決加簽後而破壞流程順序，使得打印函文時，職位與名字不對應的問題  20161030******************************
			for(int j=0;j<list_visaflow.size()-nos;j++){
				if(list_visa.get(i).getVisaSigner().equals(list_visaflow.get(j).getVisaSigner())){
					visabillstemp.setVisaRank(list_visaflow.get(j).getVisaRank()+":");
					break;
				}else if(j==list_visaflow.size()-nos-1){
					visabillstemp.setVisaRank("(加簽)");
				}
			}
			//************************解決加簽後而破壞流程順序，使得打印函文時，職位與名字不對應的問題  20161030******************************
			
			if(memo!=null){
				visabillstemp.setMemo("(備註:"+memo+")");
			}
			visabillstemp.setVisaSigner(list_visa.get(i).getVisaSigner());
			visabillstemp.setVisaMk(list_visa.get(i).getVisaMk());
			visabillstemp.setVisaName(name);
			visabillstemp.setVisible(list_visa.get(i).getVisible());
			list_visabillstemp.add(visabillstemp);
		}//for
		/**********************去掉不顯示出來（visible='N'）20171023****************************/
		for(int a=0;a<list_visabillstemp.size();a++){
			if("N".equals(list_visabillstemp.get(a).getVisible())){
				list_visabillstemp.remove(a);
			}
		}
		/**********************去掉不顯示出來（visible='N'）20171023****************************/
		/*********************簡體轉繁體******************/
		for(int i=0;i<list_visabillstemp.size();i++){
			list_visabillstemp.get(i).setMemo(ZHConverter.convert(list_visabillstemp.get(i).getMemo(), ZHConverter.TRADITIONAL));
			list_visabillstemp.get(i).setVisaName(ZHConverter.convert(list_visabillstemp.get(i).getVisaName(), ZHConverter.TRADITIONAL));
			list_visabillstemp.get(i).setVisaNameAndMk(ZHConverter.convert(list_visabillstemp.get(i).getVisaNameAndMk(), ZHConverter.TRADITIONAL));
			list_visabillstemp.get(i).setVisaRank(ZHConverter.convert(list_visabillstemp.get(i).getVisaRank(), ZHConverter.TRADITIONAL));			
		}
		/*********************簡體轉繁體******************/
		
		
		Map<String,Object> visa_map=new HashMap<String,Object>();
		visa_map.put("list_visa", list_visabillstemp);
		
		map.put("visa_map", visa_map);
		/*函文附檔*/
		//String pic_file=ServletActionContext.getRequest().getRealPath("/KyzexpFile/"+id.getBillNo()+"/")+"/";//函文附檔圖片路徑(附檔在項目的路徑)
		String pic_file=new File("d:\\webnewpro_backup\\"+billNo).toString();//函文附檔圖片路徑(附檔在D盤的路徑)
		//List<KyzExpectmatmFile>list_kyzexpfile=kyzexpfileDao.findByBillNo(billNo);
		List<KyzExpectmatmFile>list_kyzexpfile=obj.getList_file();
		if(pic_file!=null&&list_kyzexpfile.size()>0){
			map.put("pic_file", pic_file+"\\");
			Map<String,Object> file_map=new HashMap<String,Object>();
			file_map.put("list_kyzexpfile", list_kyzexpfile);
			map.put("file_map", file_map);
		}
		
		String sub_file=GlobalMethod.getSubfile(list_visa.size()-nos,null);
		map.put("sub_file",sub_file);
		map_result.put("map", map);
		map_result.put("list", list);
		return map_result;
	}

}
