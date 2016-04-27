package services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.ContextLoader;

import com.spreada.utils.chinese.ZHConverter;

import dao.IKyVisaBillmDao;
import dao.IKyVisaBillsDao;
import dao.IKyzExpectmatmFileDao;
import dao.IKyzVisaFlowDao;
import dao.IWebBussinessletterDao;
import dao.IWebFactDao;
import entity.KyVisabillm;
import entity.KyVisabills;
import entity.KyzContactletter;
import entity.KyzExpectmatmLog;
import entity.KyzVisaflow;
import entity.WebBussinessletter;
import entity.WebUser;
import entity_temp.VisabillsTemp;
import services.IWebBussinessletterServices;
import util.GlobalMethod;
import util.PageBean;

public class WebBussinessletterServicesImpl implements IWebBussinessletterServices{
	private IWebBussinessletterDao webbussletterdao;
	private IWebFactDao webFactDao;
	private IKyVisaBillmDao visabillmDao;
	private IKyzVisaFlowDao visaDao;
	private IKyVisaBillsDao visabillDao;
	private IKyzExpectmatmFileDao kyzexpfileDao;

	public void setWebbussletterdao(IWebBussinessletterDao webbussletterdao) {
		this.webbussletterdao = webbussletterdao;
	}
	
	

	public void setWebFactDao(IWebFactDao webFactDao) {
		this.webFactDao = webFactDao;
	}



	public void setVisabillmDao(IKyVisaBillmDao visabillmDao) {
		this.visabillmDao = visabillmDao;
	}



	public void setVisaDao(IKyzVisaFlowDao visaDao) {
		this.visaDao = visaDao;
	}



	public void setVisabillDao(IKyVisaBillsDao visabillDao) {
		this.visabillDao = visabillDao;
	}



	public void setKyzexpfileDao(IKyzExpectmatmFileDao kyzexpfileDao) {
		this.kyzexpfileDao = kyzexpfileDao;
	}



	public void add(WebBussinessletter buss) {
		// TODO Auto-generated method stub
		webbussletterdao.add(buss);
	}

	public PageBean findPageBean(int pageSize, int page, String billNo,
			String factNo,WebUser user) {
		// TODO Auto-generated method stub
		return webbussletterdao.findPageBean(pageSize, page, billNo, factNo,user);
	}

	/**
	 * 出差函文billNO:BM+廠別代號+日期前4位+2位序號
	 */
	public String makeBillNo(String factNo, String createDate) {
		// TODO Auto-generated method stub
		StringBuffer maxbillno=new StringBuffer();
		maxbillno.append("BM"+factNo+createDate.substring(2));
		String billno=webbussletterdao.findMaxBillNo(factNo, createDate);
		if(billno==null||billno.equals("")){
			maxbillno.append("01");
		}else{
			int temp=Integer.parseInt(billno.substring(billno.length()-2))+1;
			if(temp<10){
				maxbillno.append("0"+temp);
			}else{
				maxbillno.append(temp);
			}
		}
		return maxbillno.toString();
	}

	public WebBussinessletter findById(String billNo) {
		// TODO Auto-generated method stub
		return webbussletterdao.findById(billNo);
	}

	public void delete(WebBussinessletter letter,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		webbussletterdao.delete(letter,delLog);
	}

	public void delete(String billNo,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		webbussletterdao.delete(billNo,delLog);
	}

	public List<WebBussinessletter> findBefor2Month() {
		// TODO Auto-generated method stub
		return webbussletterdao.findBefor2Month();
	}

	public void addLarge(List<WebBussinessletter> list) {
		// TODO Auto-generated method stub
		webbussletterdao.addLarge(list);
	}



	/**
	 * 日期:2016/2/23
	 * 描述:
	 */
	public Map<String, Object> print(String factNo, String billNo, String sort,KyVisabillm vbm) {
		// TODO Auto-generated method stub
		Map<String,Object>map_result=new HashMap<String,Object>();
		List<WebBussinessletter>list=new ArrayList<WebBussinessletter>();
		Map<String,Object>map=new HashMap<String,Object>();
		String factname=webFactDao.selByid(factNo);
		String unit="";//承辦單位
		WebBussinessletter letter=webbussletterdao.findById(billNo);
		if(letter==null){
			return null;
		}else{
			/*******************簡轉繁體********************/						
			letter.setAddress(ZHConverter.convert(letter.getAddress(), ZHConverter.TRADITIONAL));
			letter.setGAgent(ZHConverter.convert(letter.getGAgent(), ZHConverter.TRADITIONAL));
			letter.setPlanList(ZHConverter.convert(letter.getPlanList(), ZHConverter.TRADITIONAL));
			letter.setPosition(ZHConverter.convert(letter.getPosition(), ZHConverter.TRADITIONAL));
			letter.setUnit(ZHConverter.convert(letter.getUnit(), ZHConverter.TRADITIONAL));
			letter.setUsername(ZHConverter.convert(letter.getUsername(), ZHConverter.TRADITIONAL));
			/*******************簡轉繁體********************/
			letter.setSumDate((int)GlobalMethod.sumDate(letter.getDateFrom(), letter.getDateEnd())+1);//出差天數
			list.add(letter);
		}
		if(letter.getUnit()!=null&&!letter.getUnit().equals("")){
			unit="("+letter.getUnit()+")";
		}
		//String result=factname+unit+"人員出差申請書";
		//map.put("SUBREPORT_DIR",ServletActionContext.getRequest().getRealPath("/jasper/audit/")+ "/");
		//map.put("pic", ServletActionContext.getRequest().getRealPath("/jasper/audit/images/")+ "/");//圖片路徑
		map.put("SUBREPORT_DIR",ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/jasper/audit/")+"/");
		map.put("pic", ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/jasper/audit/images/")+ "/");//圖片路徑	
		map.put("pfactno", factNo);
		map.put("pbillno",billNo);
		//map.put("title",result);						
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		if(vbm==null){
			vbm=visabillmDao.findById(factNo, sort, billNo);
		}		
		List<KyVisabills>list_visa=vbm.getKyVisabillses();
		List<KyzVisaflow>list_visaflow=visaDao.findByType(factNo,sort);
		
		/**
		 * 最後個不用審核的,就去掉
		 */
		int nos=visabillDao.findBillsWithNo(sort, billNo);
		/*if(nos>0){
			for(int i=0;i<nos;i++){
				list_visa.remove(list_visa.size()-1);
				list_visaflow.remove(list_visaflow.size()-1);
			}
		}*/
		
		List<VisabillsTemp>list_visabillstemp=new ArrayList();		
		for(int i=0;i<list_visa.size()-nos;i++){//for
			VisabillsTemp visabillstemp=new VisabillsTemp();
			String visa_result="";
			String visamk_temp="";
			Date date=null;
			
			String datestr=list_visa.get(i).getDateVisa();
			try {
				if(datestr!=null){
					date=format.parse(datestr);
					visabillstemp.setCreateDate(date);
				}
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			if(list_visa.size()==list_visaflow.size()-nos){
				String visaRank=list_visaflow.get(i).getVisaRank();
				visabillstemp.setVisaRank(visaRank+":");
			}
			if(memo!=null){
				visabillstemp.setMemo("(備註:"+memo+")");
			}
			visabillstemp.setVisaSigner(list_visa.get(i).getVisaSigner());
			visabillstemp.setVisaMk(list_visa.get(i).getVisaMk());
			visabillstemp.setVisaName(name);
			list_visabillstemp.add(visabillstemp);
		}//for
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
		/*String pic_file=new File("d:\\KyzletterexpFile_backup\\"+billNo).toString();//函文附檔圖片路徑(附檔在D盤的路徑)		
		List<KyzExpectmatmFile>list_kyzexpfile=kyzexpfileSer.findByBillNo(billNo);
		if(pic_file!=null&&list_kyzexpfile.size()>0){
			map.put("pic_file", pic_file+"\\");
			Map file_map=new HashMap<String,Object>();
			file_map.put("list_kyzexpfile", list_kyzexpfile);
			map.put("file_map", file_map);
		}*/
		map_result.put("map", map);
		map_result.put("list", list);
		return map_result;
	}

}
