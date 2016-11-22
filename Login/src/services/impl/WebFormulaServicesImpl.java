/**
 * 
 */
package services.impl;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.context.ContextLoader;

import com.spreada.utils.chinese.ZHConverter;

import dao.IKyVisaBillsDao;
import dao.IKyzExpectmatmFileDao;
import dao.IKyzVisaFlowDao;
import dao.IWebFormulaDao;
import entity.KyVisabillm;
import entity.KyVisabills;
import entity.KyzExpectmatm;
import entity.KyzExpectmatmFile;
import entity.KyzExpectmats;
import entity.KyzVisaflow;
import entity.WebFormula;
import entity.WebFormulaItems;
import entity.WebTabpom;
import entity_temp.VisabillsTemp;
import services.IWebFormulaServices;
import util.GlobalMethod;
import util.PageBean;

/**   
 *    
 * 项目名称：Login   
 * 类名称：WebFormulaServicesImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/11/3 上午11:16:49   
 * 修改人：Administrator   
 * 修改时间：2016/11/3 上午11:16:49   
 * 修改备注：   
 * @version    
 *    
 **/
public class WebFormulaServicesImpl implements IWebFormulaServices{
	private IWebFormulaDao webformuladao;
	private IKyzVisaFlowDao visaDao;
	private IKyVisaBillsDao visabillDao;
	private IKyzExpectmatmFileDao kyzexpfileDao;

	public void setWebformuladao(IWebFormulaDao webformuladao){
		this.webformuladao=webformuladao;
	}	
	public void setVisaDao(IKyzVisaFlowDao visaDao){
		this.visaDao=visaDao;
	}
	public void setVisabillDao(IKyVisaBillsDao visabillDao){
		this.visabillDao=visabillDao;
	}

	public void setKyzexpfileDao(IKyzExpectmatmFileDao kyzexpfileDao){
		this.kyzexpfileDao=kyzexpfileDao;
	}
	/**
	 * 日期:2016/11/3
	 * 描述:
	 */
	
	
	public PageBean findPageBean(int page,int pageSize,WebFormula formula,String issuedDate_a,String issuedDate_b){
		// TODO Auto-generated method stub
		return webformuladao.findPageBean(page,pageSize, formula,issuedDate_a,issuedDate_b);
	}


	/**
	 * 日期:2016/11/4
	 * 描述:
	 */
	
	
	public void add(WebFormula formula){
		// TODO Auto-generated method stub
		webformuladao.add(formula);
	}

	/**
	 * 日期:2016/11/4
	 * 描述:
	 */
	
	
	public WebFormula findById(String formulaIndex){
		// TODO Auto-generated method stub
		return webformuladao.findById(formulaIndex);
	}

	/**
	 * 日期:2016/11/4
	 * 描述:
	 */
	
	
	public void delete(String formulaIndex){
		// TODO Auto-generated method stub
		webformuladao.delete(formulaIndex);
	}

	/**
	 * 日期:2016/11/14
	 * 描述:
	 */
	
	
	public List<String> findFormulaIndex(String factNo,String factCode,String createDate){
		// TODO Auto-generated method stub
		return webformuladao.findFormulaIndex(factNo,factCode,createDate);
	}

	/**
	 * 日期:2016/11/22
	 * 描述:
	 */
	
	
	public Map<String,Object> print(String factNo,String billNo,KyVisabillm vbm){
		// TODO Auto-generated method stub
		Map<String,Object>map_result=new HashMap<String,Object>();
		Map<String,Object>map=new HashMap<String,Object>();
		//String secNo="";//申請單位
		List<WebFormula>list=new ArrayList<WebFormula>();
		WebFormula obj=webformuladao.findById(billNo);
		if(obj!=null){
			list.add(obj);
		}		
		if(list.size()==0){
			/*response.setContentType("text/html;charset=utf-8");
			try {
				response.getWriter().print("<script>alert('單號為"+id.getBillNo()+"的函文不存在!');window.close()</script>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			return null;
		}else{
			/*list.get(0).setSecNo(ZHConverter.convert(list.get(0).getSecNo(), ZHConverter.TRADITIONAL));
			list.get(0).setUserNm(ZHConverter.convert(list.get(0).getUserNm(), ZHConverter.TRADITIONAL));			
			list.get(0).setMemoMk(ZHConverter.convert(list.get(0).getMemoMk(), ZHConverter.TRADITIONAL));
			list.get(0).setMemoSmk(ZHConverter.convert(list.get(0).getMemoSmk(), ZHConverter.TRADITIONAL));*/
		}
		/*if(list.get(0).getSecNo()!=null&&!list.get(0).getSecNo().equals("")){
			secNo="("+list.get(0).getSecNo()+")";
		}*/
		//String result=factname+secNo+"費用申請單";
		map = new HashMap<String, Object>();
		//map.put("SUBREPORT_DIR",ServletActionContext.getRequest().getRealPath("/jasper/audit/")+ "/");
		//map.put("pic", ServletActionContext.getRequest().getRealPath("/jasper/audit/images/")+ "/");//圖片路徑
		map.put("SUBREPORT_DIR",ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/jasper/audit/")+ "/");
		map.put("pic", ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/jasper/audit/images/")+ "/");//圖片路徑
		map.put("pfactno", factNo);
		map.put("pbillno",billNo);
		//map.put("title",result);		
		
		List<WebFormulaItems> sub_list =obj.getWebFormulaItemses();		
		
		/*if(list.get(0).getKyzExpectmatses().size()==1){
			KyzExpectmats kyzss=list.get(0).getKyzExpectmatses().get(0);			
			if(kyzss.getMatNo()==null&&kyzss.getItemNm()==null||(kyzss.getMatNo().trim().equals("")&&kyzss.getItemNm().trim().equals(""))){
				list.get(0).setKyzsMk("1");
			}else{
				for(int i=0;i<list.get(0).getKyzExpectmatses().size();i++){
					KyzExpectmats kyzs=list.get(0).getKyzExpectmatses().get(i);
					kyzs.setMatNo(ZHConverter.convert(kyzs.getMatNo(), ZHConverter.TRADITIONAL));
					kyzs.setQtyPair(ZHConverter.convert(kyzs.getQtyPair(), ZHConverter.TRADITIONAL));
					kyzs.setItemNm(ZHConverter.convert(kyzs.getItemNm(), ZHConverter.TRADITIONAL));
					sub_list.add(kyzs);
				}
				while(sub_list.size()<10){
					sub_list.add(temp);
				}
			}
		}else{
			for(int i=0;i<list.get(0).getKyzExpectmatses().size();i++){
				KyzExpectmats kyzs=list.get(0).getKyzExpectmatses().get(i);
				kyzs.setMatNo(ZHConverter.convert(kyzs.getMatNo(), ZHConverter.TRADITIONAL));
				kyzs.setQtyPair(ZHConverter.convert(kyzs.getQtyPair(), ZHConverter.TRADITIONAL));
				kyzs.setItemNm(ZHConverter.convert(kyzs.getItemNm(), ZHConverter.TRADITIONAL));
				sub_list.add(kyzs);
			}
			while(sub_list.size()<10){
				sub_list.add(temp);
			}
		}*/
		
		
		Map<String,Object> sub_map=new HashMap<String,Object>();
		sub_map.put("sub_list", sub_list);
		
		
		/*String type=list.get(0).getVisaType();
		List<KyzVisaflow> list_visa=visaSer.findByType(type);*/
		
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		if(vbm==null){
			vbm=list.get(0).getVbm();
		}	
		String sort=vbm.getId().getVisaSort();
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
			//String visadate=list_visa.get(i).getDateVisa();
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
		
		map.put("sub_map", sub_map);
		map.put("visa_map", visa_map);
		
		Map<String,Object> main_map=new HashMap<String,Object>();    /*把list（List<KyzExpectmatm> list=kyzSer.findById_Print(id)）放在一个子表,便于打印  20150804*/
		main_map.put("list_main", list);
		map.put("main_map", main_map);
		
		/*函文附檔*/
		//String pic_file=ServletActionContext.getRequest().getRealPath("/KyzexpFile/"+id.getBillNo()+"/")+"/";//函文附檔圖片路徑(附檔在項目的路徑)
		String pic_file=new File("d:\\KyzexpFile_backup\\"+billNo).toString();//函文附檔圖片路徑(附檔在D盤的路徑)
		List<KyzExpectmatmFile>list_kyzexpfile=kyzexpfileDao.findByBillNo(billNo);
		if(pic_file!=null&&list_kyzexpfile.size()>0){
			map.put("pic_file", pic_file+"\\");
			Map<String,Object> file_map=new HashMap<String,Object>();
			file_map.put("list_kyzexpfile", list_kyzexpfile);
			map.put("file_map", file_map);
		}
		
		String sub_file=GlobalMethod.getSubfile(list_visa.size()-nos);
		map.put("sub_file",sub_file);
		map_result.put("map", map);
		map_result.put("list", list);
		return map_result;
		
	}
	/**
	 * 日期:2016/11/22
	 * 描述:
	 */
	
	
	public WebFormula findById_nosession(String formulaIndex){
		// TODO Auto-generated method stub
		return webformuladao.findById_nosession(formulaIndex);
	}

	
}
