package action;

import java.io.IOException;
import java.util.Date;
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

import entity.KyzExpectmatmLog;
import entity.KyzVisaflow;
import entity.WebType;
import entity.WebUser;
import entity.WebuserEmailA;

public class WebuserEmailaAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{
	private String factNo;
	private String email;
	private String visaSort;
	private String emailPwd;
	private  int page;
	private PageBean bean;
	private WebuserEmailA emailobj;
	private String ajaxResult;//申請函文時返回的ajax結果,   0:提交成功       1:提交失敗
	private int backIndex;//返回標識      0或null:不走返回路徑         1:走返回路徑
	private IWebuserEmailAServices webuseremailaSer;
	private IWebTypeServices webtypeSer;
	private javax.servlet.http.HttpServletResponse response;
	private javax.servlet.http.HttpServletRequest request;
	private String typeMk;
    
	
	public String getTypeMk(){
		return typeMk;
	}

	public void setTypeMk(String typeMk){
		this.typeMk=typeMk;
	}

	public int getBackIndex() {
		return backIndex;
	}

	public void setBackIndex(int backIndex) {
		this.backIndex = backIndex;
	}

	public String getAjaxResult() {
		return ajaxResult;
	}

	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}

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
		try{
			webuseremailaSer.add(emailobj);
			ajaxResult="0";
		}catch(Exception e){
			e.printStackTrace();
			ajaxResult="1";
		}		
		return "add";
	}
	public String delete() throws IOException{
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setObj("WebuserEmailA");
		log.setFactNo(factNo);
		log.setContent(email+emailPwd+visaSort);
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		log.setUsername(user.getUsername());
		boolean flag=webuseremailaSer.deleteObj(factNo, email, emailPwd, visaSort,typeMk,log);
		if(flag==false){
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>alert('刪除失敗!');history.back()</script>");
			return null;
		}else{			
			return "delete";
		}
		
	}
	public String findPageBean(){
		ActionContext.getContext().getSession().remove("public_factno");
		ActionContext.getContext().getSession().remove("public_email");
		ActionContext.getContext().getSession().remove("public_visaSort");
		ActionContext.getContext().getSession().remove("public_typeMk");
		bean=webuseremailaSer.findPageBean(20,page, factNo, email, visaSort,typeMk);
		this.getTypeName(bean);
		return "beanList";
	}
	public String findPageBean2(){
		bean=webuseremailaSer.findPageBean(20,page, factNo, email, visaSort,typeMk);		
		ActionContext.getContext().getSession().put("public_factno", factNo);
		ActionContext.getContext().getSession().put("public_email", email);
		ActionContext.getContext().getSession().put("public_visaSort", visaSort);
		ActionContext.getContext().getSession().put("public_typeMk",typeMk);
		this.getTypeName(bean);
		return "beanList1";
	}
	
	public String findPageBean3(){
		String result="beanList1";
		if(backIndex==1){
			result="beanList";
		}
		factNo=(String)ActionContext.getContext().getSession().get("public_factno");
		email=(String)ActionContext.getContext().getSession().get("public_email");
		visaSort=(String)ActionContext.getContext().getSession().get("public_visaSort");
		typeMk=(String)ActionContext.getContext().getSession().get("public_typeMk");
		bean=webuseremailaSer.findPageBean(20,page, factNo, email, visaSort,typeMk);
		this.getTypeName(bean);
		return result;
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
		emailobj=webuseremailaSer.findById(factNo, email, emailPwd, visaSort,typeMk);
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
