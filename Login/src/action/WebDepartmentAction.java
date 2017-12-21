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

}
