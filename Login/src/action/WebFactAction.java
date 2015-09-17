package action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import services.IKyTypeServices;
import services.IWebFactServices;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.*;

public class WebFactAction extends ActionSupport {
	private IWebFactServices webFactSer;
	private IKyTypeServices kytypeSer;

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
	 * 在login頁面上顯示所有廠別
	 * 
	 * @return
	 */
	public String findAllfact2() {
		//退出時，清除session的所有內容（廠別，用戶名），因為退出時指向了jedge.jsp，而這個頁面又指向此方法
		ActionContext.getContext().getSession().clear();
		List list = webFactSer.findAllFact();
		//List factCodes = webFactSer.findAllFactCode();// tw登錄時所有廠別狀態
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
	
	

}
