package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import services.IWebTypeServices;
import services.IWebuserEmailAServices;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyzVisaflow;
import entity.WebType;
import entity.WebuserEmailA;

public class WebuserEmailaAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{
	private String factNo;
	private String email;
	private String visaSort;
	private String emailPwd;
	private  int page;
	private PageBean bean;
	private WebuserEmailA emailobj;
	
	private IWebuserEmailAServices webuseremailaSer;
	private IWebTypeServices webtypeSer;
	private javax.servlet.http.HttpServletResponse response;
	private javax.servlet.http.HttpServletRequest request;
    
	public String getEmailPwd() {
		return emailPwd;
	}

	public void setEmailPwd(String emailPwd) {
		this.emailPwd = emailPwd;
	}

	public WebuserEmailA getEmailobj() {
		return emailobj;
	}

	public void setEmailobj(WebuserEmailA emailobj) {
		this.emailobj = emailobj;
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

	public String getVisaSort() {
		return visaSort;
	}

	public void setVisaSort(String visaSort) {
		this.visaSort = visaSort;
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

	public void setWebuseremailaSer(IWebuserEmailAServices webuseremailaSer) {
		this.webuseremailaSer = webuseremailaSer;
	}
	
	
	public void setWebtypeSer(IWebTypeServices webtypeSer) {
		this.webtypeSer = webtypeSer;
	}

	public String add(){
		webuseremailaSer.add(emailobj);
		return "add";
	}
	public String delete() throws IOException{
		boolean flag=webuseremailaSer.deleteObj(factNo, email, emailPwd, visaSort);
		if(flag==false){
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>alert('刪除失敗!');history.back()</script>");
			return null;
		}else{			
			return "delete";
		}
		
	}
	public String findPageBean(){
		ActionContext.getContext().getApplication().clear();
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		bean=webuseremailaSer.findPageBean(25, page, factNo, email, visaSort);
		this.getTypeName(bean);
		return "beanList";
	}
	public String findPageBean2(){
		ActionContext.getContext().getApplication().clear();
		bean=webuseremailaSer.findPageBean(25, page, factNo, email, visaSort);
		if(factNo!=null&&!factNo.equals("")){
			ActionContext.getContext().getApplication().put("webemaila_factno", factNo);
		}
		if(email!=null&&!email.equals("")){
			ActionContext.getContext().getApplication().put("webemaila_email", email);
		}
		if(visaSort!=null&&!visaSort.equals("")){
			ActionContext.getContext().getApplication().put("webemaila_visaSort", visaSort);
		}
		this.getTypeName(bean);
		return "beanList1";
	}
	
	public String findPageBean3(){
		factNo=(String)ActionContext.getContext().getApplication().get("webemaila_factno");
		email=(String)ActionContext.getContext().getApplication().get("webemaila_email");
		visaSort=(String)ActionContext.getContext().getApplication().get("webemaila_visaSort");
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		bean=webuseremailaSer.findPageBean(25, page, factNo, email, visaSort);
		this.getTypeName(bean);
		return "beanList1";
	}
	public void getTypeName(PageBean bean){
		List<WebuserEmailA>list=bean.getList();
		List<WebType>list_type=(List<WebType>)ActionContext.getContext().getSession().get("list_webtype");/********20151029登錄時已經記錄**************/
		for(int i=0;i<list.size();i++){//for1
			WebuserEmailA eml=list.get(i);
			String factno=eml.getId().getFactNo();
			String visaSort=eml.getId().getVisaSort();			
			String typename=visaSort;
			//typename=webtypeSer.findTypeNameById(factno, visaSort.substring(0, 2));									
			for(int j=0;j<list_type.size();j++){//for2
				WebType type=list_type.get(j);
				if(factno.equals(type.getId().getFactNo())&&visaSort.substring(0,2).equals(type.getId().getTypeNo())){
					typename=type.getTypeName();					
					break;
				}
			}//for2
			eml.setColTemp(typename);
		}//for1	
	}
	public String findById(){
		emailobj=webuseremailaSer.findById(factNo, email, emailPwd, visaSort);
		return "findById";
	}

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	

}
