package action;

import java.util.Date;
import java.util.List;

import services.IWebuserEmailServices;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyzExpectmatmLog;
import entity.WebUser;
import entity.WebuserEmail;

/**
 * 
* 項目名稱：WebLogin   
* 類名稱：WebuseremailAction   
* 類描述：函文郵件同步人管理
* 創建人：KY2
 */
public class WebuseremailAction extends ActionSupport{
	private IWebuserEmailServices webuseremailSer;
	private WebuserEmail useremail;
	private String factNo;
	private String email;
	private String emailpwd;
	private int page;
	private PageBean bean;
	private int backIndex;//返回標識      0或null:不走返回路徑         1:走返回路徑
	private String ajaxResult;
	private String typeMk;
	
	
	public String getTypeMk(){
		return typeMk;
	}
	public void setTypeMk(String typeMk){
		this.typeMk=typeMk;
	}
	public String getAjaxResult(){
		return ajaxResult;
	}
	public void setAjaxResult(String ajaxResult){
		this.ajaxResult=ajaxResult;
	}
	public int getBackIndex() {
		return backIndex;
	}
	public void setBackIndex(int backIndex) {
		this.backIndex = backIndex;
	}
	public WebuserEmail getUseremail() {
		return useremail;
	}
	public void setUseremail(WebuserEmail useremail) {
		this.useremail = useremail;
	}
	public String getFactNo() {
		return factNo;
	}
	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailpwd() {
		return emailpwd;
	}
	public void setEmailpwd(String emailpwd) {
		this.emailpwd = emailpwd;
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
	public void setWebuseremailSer(IWebuserEmailServices webuseremailSer) {
		this.webuseremailSer = webuseremailSer;
	}
	
	/**
	 * 添加修改新數據
	 * @return
	 */
	public String add(){
		try{
			webuseremailSer.add(useremail);
			ajaxResult="0";
		}catch(Exception e){
			ajaxResult="1";
			e.printStackTrace();
		}
		return "add";
	}
	public String findById(){
		useremail=webuseremailSer.findById(factNo, email, emailpwd,typeMk);
		return "findById";
	}
	
	/**
	 * 刪除數據
	 * @return
	 */
	public String delete(){
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setObj("WebuserEmail");
		log.setFactNo(factNo);
		log.setContent(email+emailpwd+"_("+typeMk+")");
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		log.setUsername(user.getUsername());
		webuseremailSer.delete(factNo, email, emailpwd,typeMk,log);                               
		return "delete";
	}
	
	/**
	 * 分頁查詢
	 * @return
	 */
	public String findPageBean(){		
		ActionContext.getContext().getSession().remove("public_factno");
		ActionContext.getContext().getSession().remove("public_email");
		ActionContext.getContext().getSession().remove("public_typeMk");
		bean=webuseremailSer.findPageBean(20,page, factNo, email,typeMk);
		return "beanList";
	}
	
	/**
	 * 分頁查詢2
	 * @return
	 */
	public String findPageBean2(){
		bean=webuseremailSer.findPageBean(20,page, factNo, email,typeMk);
		ActionContext.getContext().getSession().put("public_factno", factNo);
		ActionContext.getContext().getSession().put("public_email", email);
		ActionContext.getContext().getSession().put("public_typeMk",typeMk);
		return "beanList1";
	}
	
	/**
	 * 分頁查詢3
	 * @return
	 */
	public String findPageBean3(){
		String result="beanList1";
		if(backIndex==1){
			result="beanList";
		}
		factNo=(String)ActionContext.getContext().getSession().get("public_factno");
		email=(String)ActionContext.getContext().getSession().get("public_email");
		typeMk=(String)ActionContext.getContext().getSession().get("public_typeMk");
		bean=webuseremailSer.findPageBean(20,page, factNo, email,typeMk);
		return result;
	}

}
