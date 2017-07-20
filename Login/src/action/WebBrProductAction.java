/**
 * 
 */
package action;

import java.util.List;

import net.sf.json.JSONArray;

import services.IWebBrProductServices;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyzExpectmatmLog;
import entity.VWebFact;
import entity.WebBrProduct;
import entity.WebBrProductitem;

/**   
 *    
 * 项目名称：WebLogin   
 * 类名称：WebBrProductAction   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2017/7/13 下午2:06:46   
 * 修改人：Administrator   
 * 修改时间：2017/7/13 下午2:06:46   
 * 修改备注：   
 * @version    
 *    
 **/
public class WebBrProductAction extends ActionSupport{
		
	private IWebBrProductServices webbrproSer;
	private String factNo;
	private List<WebBrProduct> listbrpro;
	private int page;
	private PageBean bean;
	private WebBrProduct wbpro;
	private String ajaxResult;
	private String createDate;
	private Integer createUser; 
	private Integer wid;
	private JSONArray jsons;
	private String yymmdd;
	private List<WebBrProductitem>listitem;
	
	
	
	public List<WebBrProductitem> getListitem(){
		return listitem;
	}
	public void setListitem(List<WebBrProductitem> listitem){
		this.listitem=listitem;
	}
	public String getYymmdd(){
		return yymmdd;
	}
	public void setYymmdd(String yymmdd){
		this.yymmdd=yymmdd;
	}
	public JSONArray getJsons(){
		return jsons;
	}
	public void setJsons(JSONArray jsons){
		this.jsons=jsons;
	}
	public Integer getWid(){
		return wid;
	}
	public void setWid(Integer wid){
		this.wid=wid;
	}
	public String getCreateDate(){
		return createDate;
	}
	public void setCreateDate(String createDate){
		this.createDate=createDate;
	}
	public Integer getCreateUser(){
		return createUser;
	}
	public void setCreateUser(Integer createUser){
		this.createUser=createUser;
	}
	public String getAjaxResult(){
		return ajaxResult;
	}
	public void setAjaxResult(String ajaxResult){
		this.ajaxResult=ajaxResult;
	}
	public String getItemcategory(){
		return itemcategory;
	}
	public void setItemcategory(String itemcategory){
		this.itemcategory=itemcategory;
	}

	private String itemcategory;
	
	public WebBrProduct getWbpro(){
		return wbpro;
	}
	public void setWbpro(WebBrProduct wbpro){
		this.wbpro=wbpro;
	}
	public int getPage(){
		return page;
	}
	public void setPage(int page){
		this.page=page;
	}
	public PageBean getBean(){
		return bean;
	}
	public void setBean(PageBean bean){
		this.bean=bean;
	}
	public String getFactNo(){
		return factNo;
	}
	public void setFactNo(String factNo){
		this.factNo=factNo;
	}
	public void setWebbrproSer(IWebBrProductServices webbrproSer){
		this.webbrproSer=webbrproSer;
	}
	
	
		
	
	public List<WebBrProduct> getListbrpro(){
		return listbrpro;
	}
	public void setListbrpro(List<WebBrProduct> listbrpro){
		this.listbrpro=listbrpro;
	}
	public String findPageBean(){
		ActionContext.getContext().getSession().remove("webbrproFactNo");
		bean=webbrproSer.findPageBean(20,page,factNo);
		return "findPageBean";
	}
	
	public String findPageBean2(){
		ActionContext.getContext().getSession().put("webbrproFactNo",factNo);
		bean=webbrproSer.findPageBean(20,page,factNo);
		return "findPageBean1";
	}
	
	public String findPageBean3(){
		factNo=(String)ActionContext.getContext().getSession().get("webbrproFactNo");
		bean=webbrproSer.findPageBean(20,page,factNo);
		return "findPageBean1";
	}
	
	public String add(){
		ajaxResult="0";
		try{
			for(WebBrProduct obj:listbrpro){
				obj.setCreateDate(createDate);
				obj.setCreateUser(createUser);
				obj.setItemcategory(itemcategory);
			}
			webbrproSer.add(listbrpro);
		}catch(Exception e){
			ajaxResult="1";
			e.printStackTrace();
		}
		return "add";
	}
	
	public String delete(){
		try{
			KyzExpectmatmLog log=new KyzExpectmatmLog();
			wbpro=webbrproSer.findById(factNo,wid);
			log.setFactNo(wbpro.getId().getFactNo());
			log.setContent(wbpro.getNamec1()+"__"+wbpro.getNamec2());
			log.setObj("WebBrProduct");
			log.setBillNo(wbpro.getItemcategory());
			webbrproSer.delete(wbpro,log);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "delete";
	}
	
	public String findByFactno(){
		try{
			List<Object[]>list=webbrproSer.findByFactno(factNo);
			jsons=JSONArray.fromObject(list);
		}catch(Exception e){
			e.printStackTrace();
		}		
		return "findByFactno";
	}
	
	public String add2(){
		ajaxResult="0";
		try{
			webbrproSer.add2(listitem);
		}catch(Exception e){
			ajaxResult="1";
			e.printStackTrace();
		}
		return "add2";
	}
	
	
	

}
