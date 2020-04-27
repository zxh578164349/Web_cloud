/**
 * 
 */
package action;

import java.util.List;

import net.sf.json.JSONArray;

import services.IWebDepartmentServices;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.WebDepartment;

/**
 * 
* 項目名稱：WebLogin   
* 類名稱：WebDepartmentAction   
* 類描述：部門設置
* 創建人：KY2
 */
public class WebDepartmentAction extends ActionSupport{
	private String factNo;
	private JSONArray jsons;
	private String depId;
	private PageBean bean;
	private int page;
	private int backIndex;//返回標識      0或null:不走返回路徑         1:走返回路徑
	private WebDepartment dep;
	private String ajaxResult;
	private IWebDepartmentServices webdepServices;
	
	
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public String getFactNo(){
		return factNo;
	}
	public void setFactNo(String factNo){
		this.factNo=factNo;
	}
	
	public JSONArray getJsons(){
		return jsons;
	}
	public void setJsons(JSONArray jsons){
		this.jsons=jsons;
	}
	
	
	public int getBackIndex() {
		return backIndex;
	}
	public void setBackIndex(int backIndex) {
		this.backIndex = backIndex;
	}
	
	
	public PageBean getBean() {
		return bean;
	}
	public void setBean(PageBean bean) {
		this.bean = bean;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	
	public WebDepartment getDep() {
		return dep;
	}
	public void setDep(WebDepartment dep) {
		this.dep = dep;
	}
	
	public String getAjaxResult() {
		return ajaxResult;
	}
	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}
	public void setWebdepServices(IWebDepartmentServices webdepServices){
		this.webdepServices=webdepServices;
	}
	
	/**
	 * 目前只有臺灣加久（GJ）簽核流程有部分可供選擇簽核流程
	 * 其他工廠只能根據申請人email自動選擇流程
	 * 另外，福建鞋塑（XS）目前部門不全，也禁止部門選擇
	 * 禁止部門選擇的原因是：各廠部門數據沒有完善，一個申請人email只能對應一個部門的流程，否則系統無法識別流程
	 * @Title: findWebDepartmentByFactNo
	 * @Description: TODO
	 * @param @return
	 * @return String
	 * @throws
	 * @author web
	 * @date 2017/12/18
	 */
	public String findWebDepartmentByFactNo(){
		List<WebDepartment>list=null;		
		list=webdepServices.findWebDepartmentByFactNo(factNo);
			if(list==null||list.size()==0){
				jsons=new JSONArray();				
			}else{
				jsons=JSONArray.fromObject(list);
			}						
		return "findWebDepartmentByFactNo";
	}
	
	/**
	 * 分頁查詢
	 * @return
	 */
	public String findPageBean(){	
		ActionContext.getContext().getSession().remove("allrow");
		bean=webdepServices.findPageBean(page, 0, factNo);
		ActionContext.getContext().getSession().put("public_factNo", factNo);
		return "beanList";
	}
	
	/**
	 * 分頁查詢2
	 * @return
	 */
	public String findPageBean2(){				
		this.findPageBean();
		return "beanList1";
	}
	
	/**
	 * 分頁查詢3
	 * @return
	 */
	public String findPageBean3(){		
		factNo=(String)ActionContext.getContext().getSession().get("public_factNo");		
		bean=webdepServices.findPageBean(page, 0, factNo);
		String result="beanList1";
		if(backIndex==1){
			result="beanList";
		}
		return result;
	}
	
	public String findById(){
		dep=webdepServices.findById(depId);
		return "findById";
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	public String add(){
		try{
			webdepServices.add(dep);
			ajaxResult="0";
		}catch(Exception e){
			e.printStackTrace();
			ajaxResult="1";
		}
		return "add";
	}

}
