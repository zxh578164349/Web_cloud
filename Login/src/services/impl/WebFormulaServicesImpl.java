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
import entity.KyzExpectmatmLog;
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
	
	
	public void delete(String formulaIndex,KyzExpectmatmLog log){
		// TODO Auto-generated method stub
		webformuladao.delete(formulaIndex,log);
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
			return null;
		}else{
			
		}		
		//map.put("SUBREPORT_DIR",ServletActionContext.getRequest().getRealPath("/jasper/audit/")+ "/");
		//map.put("pic", ServletActionContext.getRequest().getRealPath("/jasper/audit/images/")+ "/");//圖片路徑
		map.put("SUBREPORT_DIR",ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/jasper/audit/")+ "/");
		map.put("pic", ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/jasper/audit/images/")+ "/");//圖片路徑
		map.put("pfactno", factNo);
		map.put("pbillno",billNo);
		
		List<WebFormulaItems> sub_list =obj.getWebFormulaItemses();	
		/*for(int i=0;i<sub_list.size()-1;i++){
			for(int j=0;j<sub_list.size()-1-i;j++){
				if(sub_list.get(j).getSectionNo()==sub_list.get(j+1).getSectionNo()){//相同配方階段的才排序
					if(sub_list.get(j).getFk_weberp_pf().getSelfchar1().compareTo(sub_list.get(j+1).getFk_weberp_pf().getSelfchar1())>0){
						sub_list.add(j,sub_list.get(j+1));
						sub_list.add(j+2,sub_list.get(j+1));
						sub_list.remove(j+1);
						sub_list.remove(j+2);
					}
				}
				
			}
			
		}*/								
		Map<String,Object> sub_map=new HashMap<String,Object>();
		sub_map.put("sub_list", sub_list);								
		if(vbm==null){
			vbm=list.get(0).getVbm();
		}
						
		if(vbm!=null){
			String sort=vbm.getId().getVisaSort();
			List<KyVisabills>list_visa=vbm.getKyVisabillses();
			List<KyzVisaflow>list_visaflow=visaDao.findByType(factNo,sort);		
			
			
			List<VisabillsTemp>list_visabillstemp=GlobalMethod.initVisabillstemp(factNo, list_visa, list_visaflow);//20201013			
									
			Map<String,Object> visa_map=new HashMap<String,Object>();
			visa_map.put("list_visa", list_visabillstemp);
			map.put("visa_map", visa_map);	
			String sub_file=GlobalMethod.getSubfile(list_visabillstemp.size(),null);
			map.put("sub_file",sub_file);
		}				
		map.put("sub_map", sub_map);
		
		
		Map<String,Object> main_map=new HashMap<String,Object>();
		main_map.put("list_main", list);
		map.put("main_map", main_map);
		
		if(obj.getPom()!=null){
			Map<String,Object>map_pom=new HashMap<String,Object>();
			List<WebTabpom>list_poms=new ArrayList<WebTabpom>();
			list_poms.add(obj.getPom());
			map_pom.put("list_poms",list_poms);
			map.put("map_pom",map_pom);
		}		
		map_result.put("map", map);
		map_result.put("list", list);				
		return map_result;
		
	}
	
	
	public Map<String,Object> print2(WebFormula formula,String issuedDate_a,String issuedDate_b){
		// TODO Auto-generated method stub
		Map<String,Object>map_result=new HashMap<String,Object>();
		List<WebFormula>list=this.findList(formula, issuedDate_a, issuedDate_b);		
		Map<String,Object>map=new HashMap<String,Object>();		
		map.put("SUBREPORT_DIR",ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/jasper/audit/")+ "/");		
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
	/**
	 * 日期:2016/11/25
	 * 描述:
	 */
	
	
	public void addItems(List<WebFormulaItems> webFormulaItemses){
		// TODO Auto-generated method stub
		webformuladao.addItems(webFormulaItemses);
	}
	public WebFormulaItems findById(int itemid) {
		// TODO Auto-generated method stub
		return webformuladao.findById(itemid);
	}
	public void deleteItems(int itemid,KyzExpectmatmLog log) {
		// TODO Auto-generated method stub
		webformuladao.deleteItems(itemid,log);
	}
	/**
	 * 日期:2016/12/9
	 * 描述:
	 */
	
	
	public List<WebFormula> findList(WebFormula formula,String issuedDate_a,String issuedDate_b){
		// TODO Auto-generated method stub
		return webformuladao.findList(formula,issuedDate_a,issuedDate_b);
	}

	
}
