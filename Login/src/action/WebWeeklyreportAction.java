package action;

import services.IWebWeeklyreportServices;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.WebUser;
import entity.WebWeeklyreport;

public class WebWeeklyreportAction extends ActionSupport{
	private IWebWeeklyreportServices webweeklyreportservices;
	private WebWeeklyreport obj;
	private String ajaxResult;
	private int page;
	private PageBean bean;
	private int backIndex;//返回標識      0或null:不走返回路徑         1:走返回路徑
	private int uid;
	private String createDate;
	
	
	

	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public String getCreateDate() {
		return createDate;
	}


	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}


	public int getBackIndex() {
		return backIndex;
	}


	public void setBackIndex(int backIndex) {
		this.backIndex = backIndex;
	}


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	public PageBean getBean() {
		return bean;
	}


	public void setBean(PageBean bean) {
		this.bean = bean;
	}


	public String getAjaxResult() {
		return ajaxResult;
	}


	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}


	public WebWeeklyreport getObj() {
		return obj;
	}


	public void setObj(WebWeeklyreport obj) {
		this.obj = obj;
	}


	public void setWebweeklyreportservices(
			IWebWeeklyreportServices webweeklyreportservices) {
		this.webweeklyreportservices = webweeklyreportservices;
	}
	
	public String add(){
		try{
			webweeklyreportservices.add(obj);
			ajaxResult="0";
		}catch(Exception e){
			ajaxResult="1";
			e.printStackTrace();
		}
		return "add";
	}
	
	public String findPageBean(){
		ActionContext.getContext().getSession().remove("allrow");	
		bean=webweeklyreportservices.findPageBean(page, 0, uid, createDate);
		ActionContext.getContext().getSession().put("createDate", createDate);
		return "findPageBean";		
	}
	
	public String findPageBean2(){		
		this.findPageBean();
		return "findPageBean1";
	}
	
	public String findPageBean3(){
		createDate=(String)ActionContext.getContext().getSession().get("CreateDate");
		bean=webweeklyreportservices.findPageBean(page, 0, uid, createDate);
		String result="findPageBean1";
		if(backIndex==1){
			result="findPageBean";
		}
		return result;
		
	}
	
	

	
	

}
