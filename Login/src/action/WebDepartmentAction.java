/**
 * 
 */
package action;

import java.util.List;

import net.sf.json.JSONArray;

import services.IWebDepartmentServices;

import com.opensymphony.xwork2.ActionSupport;

import entity.WebDepartment;

/**   
 *    
 * 项目名称：WebLogin   
 * 类名称：WebDepartmentAction   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2017/12/15 下午2:57:15   
 * 修改人：Administrator   
 * 修改时间：2017/12/15 下午2:57:15   
 * 修改备注：   
 * @version    
 *    
 **/
public class WebDepartmentAction extends ActionSupport{
	private String factNo;
	private JSONArray jsons;
	private IWebDepartmentServices webdepServices;
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
	public void setWebdepServices(IWebDepartmentServices webdepServices){
		this.webdepServices=webdepServices;
	}
	
	public String findWebDepartmentByFactNo(){
		List<WebDepartment>list=webdepServices.findWebDepartmentByFactNo(factNo);
		jsons=JSONArray.fromObject(list);
		return "findWebDepartmentByFactNo";
	}

}
