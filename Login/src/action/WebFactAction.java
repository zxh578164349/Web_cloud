package action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import services.IKyTypeServices;
import services.IWebFactServices;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.*;

public class WebFactAction extends ActionSupport {
	private IWebFactServices webFactSer;
	private IKyTypeServices kytypeSer;
	private JSONArray jsons;
	private String factNo;
	

	public String getFactNo() {
		return factNo;
	}


	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}


	public JSONArray getJsons() {
		return jsons;
	}


	public void setJsons(JSONArray jsons) {
		this.jsons = jsons;
	}


	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}
	

	public void setKytypeSer(IKyTypeServices kytypeSer) {
		this.kytypeSer = kytypeSer;
	}


	/**
	 * 在index頁面上顯示所有廠別
	 * 
	 * @return
	 */
	public String findAllfact() {
		//退出時，清除session的所有內容（廠別，用戶名），因為退出時指向了jedge.jsp，而這個頁面又指向此方法
		ActionContext.getContext().getSession().clear();
		List list = webFactSer.findAllFact();
		//List factCodes = webFactSer.findAllFactCode();// tw登錄時所有的廠別狀態
		List factCodes=webFactSer.findAllFactCode_show();
		//List<KyType>listkytype=kytypeSer.findByTypeNo_action("VV");//函文類別
		ActionContext.getContext().getSession().put("factcodes", factCodes);
		ActionContext.getContext().getSession().put("facts", list);
		//ActionContext.getContext().getSession().put("listkytype", listkytype);
		this.findAllFact_code_no();		
		return "findAllfact";
	}

	/**
	 * 在index頁面上顯示所有廠別
	 * 
	 * @return
	 */
	public String findAllfact2() {
		//退出時，清除session的所有內容（廠別，用戶名），因為退出時指向了jedge.jsp，而這個頁面又指向此方法
		ActionContext.getContext().getSession().clear();
		List list = webFactSer.findAllFact();
		//List factCodes = webFactSer.findAllFactCode();// tw登錄時所有的廠別狀態
		List factCodes=webFactSer.findAllFactCode_show();
		//List<KyType>listkytype=kytypeSer.findByTypeNo_action("VV");//函文類別
		ActionContext.getContext().getSession().put("factcodes", factCodes);
		ActionContext.getContext().getSession().put("facts", list);
		//ActionContext.getContext().getSession().put("listkytype", listkytype);
		this.findAllFact_code_no();
		return "findAllfact2";
	}
	/**
	 * 用于"分形態損益表"的廠別與廠別狀態的選擇
	 */
	public void findAllFact_code_no(){
		Map<String,List<WebFact>>map=new LinkedHashMap<String,List<WebFact>>();
		List<Object[]>list_objs=webFactSer.findAllFactCode2_showA();
		for(int i=0;i<list_objs.size();i++){			
			Object[]obj=list_objs.get(i);
			String factCode=obj[0].toString();
			List<WebFact>list_fact=webFactSer.findFactByFactCode(factCode);						
			map.put(factCode, list_fact);
			
		}
		ActionContext.getContext().getSession().put("map", map);
		//System.out.println(map);
	}
	
	/**
	 * 返回所有的廠別，幷封裝成json
	 */
	public String findAllFact_obj(){
		List<Object[]>list=webFactSer.findAllFact_obj();
		jsons=JSONArray.fromObject(list);
		return "findAllFact_obj";		
	}
	
	/**
	 * 返回所有的廠別狀態，幷封裝成json
	 */
	public String findAllFactarea_obj(){
		List<Object[]>list=webFactSer.findAllFactarea_obj();
		jsons=JSONArray.fromObject(list);
		return "findAllFactarea_obj";
	}
	
	/**
	 * 根據用戶所屬的廠別，來加載廠別列錶
	 * 如果是臺灣（tw）用戶，加載所有的的廠別
	 * 如果是工廠用戶，加載所屬工廠
	 * 20160115
	 * @param factNo
	 * @return
	 */
	public String findFactByFactNo(){		
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		List<Object[]>list=webFactSer.findFactByFactNo(factNo);
		jsons=JSONArray.fromObject(list);
		return "findFactByFactNo";
	}
	
	
	
	

}
