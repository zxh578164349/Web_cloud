package action;

import java.util.List;

import services.IWebuserEmailServices;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.WebuserEmail;

public class WebuseremailAction extends ActionSupport{
	private IWebuserEmailServices webuseremailSer;
	private WebuserEmail useremail;
	private String factNo;
	private String email;
	private String emailpwd;
	private int page;
	private PageBean bean;
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
	
	public String add(){
		webuseremailSer.add(useremail);
		return "add";
	}
	public String findById(){
		useremail=webuseremailSer.findById(factNo, email, emailpwd);
		return "findById";
	}
	public String delete(){
		webuseremailSer.delete(factNo, email, emailpwd);
		return "delete";
	}
	public String findPageBean(){
		ActionContext.getContext().getApplication().clear();
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		bean=webuseremailSer.findPageBean(25, page, factNo, email);
		return "beanList";
	}
	public String findPageBean2(){
		ActionContext.getContext().getApplication().clear();
		bean=webuseremailSer.findPageBean(25, page, factNo, email);
		ActionContext.getContext().getApplication().put("webuseremail_factno", factNo);
		ActionContext.getContext().getApplication().put("webuseremail_email", email);
		return "beanList1";
	}
	public String findPageBean3(){
		factNo=(String)ActionContext.getContext().getApplication().get("webuseremail_factno");
		email=(String)ActionContext.getContext().getApplication().get("webuseremail_email");
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getApplication().get("factNo");
		}
		bean=webuseremailSer.findPageBean(25, page, factNo, email);
		return "beanList1";
	}
	
	public void testEamil(){
		factNo="631";
		email="liujung@mail.gj.com.tw";
		List<String>list=webuseremailSer.findByFactNoAEmailPwd2(factNo, email);
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
	}

}
