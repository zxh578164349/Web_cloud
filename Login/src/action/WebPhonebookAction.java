package action;

import services.IWebPhonebookServices;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.WebPhonebook;

public class WebPhonebookAction extends ActionSupport{
	private IWebPhonebookServices webphonebookSer;
	private WebPhonebook webphone;
	private String factNo;
	private String department;
	private String post;
	private String userName;
	private PageBean bean;
	private int page;
	private long pbId;
	private String ajaxResult;//0:提交成功      1:提交失敗
	private int backIndex;//返回標識      0或null:不走返回路徑         1:走返回路徑
	
	
	public int getBackIndex() {
		return backIndex;
	}
	public void setBackIndex(int backIndex) {
		this.backIndex = backIndex;
	}
	public long getPbId() {
		return pbId;
	}
	public void setPbId(long pbId) {
		this.pbId = pbId;
	}
	public String getAjaxResult() {
		return ajaxResult;
	}
	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
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
	public String getFactNo() {
		return factNo;
	}
	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public WebPhonebook getWebphone() {
		return webphone;
	}
	public void setWebphone(WebPhonebook webphone) {
		this.webphone = webphone;
	}
	public void setWebphonebookSer(IWebPhonebookServices webphonebookSer) {
		this.webphonebookSer = webphonebookSer;
	}
	public String add(){
		try{
			webphonebookSer.add(webphone);
			ajaxResult="0";
		}catch(Exception e){
			ajaxResult="1";
		}		
		return "add";
	}
	public String findPageBean(){
		ActionContext.getContext().getSession().remove("allrow");
		ActionContext.getContext().getSession().remove("public_factNo");
		ActionContext.getContext().getSession().remove("public_department");
		ActionContext.getContext().getSession().remove("public_post");
		ActionContext.getContext().getSession().remove("public_username");
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		bean=webphonebookSer.findPageBean(25, page, factNo, department, post, userName);
		return "beanList";
	}
	public String findPageBean2(){
		ActionContext.getContext().getSession().remove("allrow");
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		ActionContext.getContext().getSession().put("public_factNo", factNo);
		ActionContext.getContext().getSession().put("public_department", department);
		ActionContext.getContext().getSession().put("public_post", post);
		ActionContext.getContext().getSession().put("public_username", userName);
		bean=webphonebookSer.findPageBean(25, page, factNo, department, post, userName);
		return "beanList1";
	}
	public String findPageBean3(){
		String result="beanList1";
		if(backIndex==1){
			result="beanList";
		}
		factNo=(String)ActionContext.getContext().getSession().get("public_factNo");
		department=(String)ActionContext.getContext().getSession().get("public_department");
		post=(String)ActionContext.getContext().getSession().get("public_post");
		userName=(String)ActionContext.getContext().getSession().get("public_username");
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		bean=webphonebookSer.findPageBean(25, page, factNo, department, post, userName);
		return result;
	}
	public String findById(){
		webphone=webphonebookSer.findById(pbId);
		return "findById";
	}
	public String delete(){
		webphonebookSer.delete(pbId);
		return "delete";
	}

}
