package action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import services.IKyzAcctServices;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyzAcct;
import entity.KyzExpectmatmLog;
import entity.WebUser;

public class KyzAcctAction extends ActionSupport implements ServletResponseAware{
	private KyzAcct kyzacct;
	private PageBean bean;
	private String acctNo;
	private int page;
	private String isnull;
	private String acctName;
	private javax.servlet.http.HttpServletResponse response;
	private IKyzAcctServices kyzacctSer;
	public KyzAcct getKyzacct() {
		return kyzacct;
	}
	public void setKyzacct(KyzAcct kyzacct) {
		this.kyzacct = kyzacct;
	}
	public PageBean getBean() {
		return bean;
	}
	public void setBean(PageBean bean) {
		this.bean = bean;
	}
	public String getAcctNo() {
		return acctNo;
	}
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;		
	}
	
	public String getIsnull() {
		return isnull;
	}
	public void setIsnull(String isnull) {
		this.isnull = isnull;		
	}
	
	public String getAcctName() {
		return acctName;
	}
	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}
	public void setKyzacctSer(IKyzAcctServices kyzacctSer) {
		this.kyzacctSer = kyzacctSer;
	}
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	
	public String add() throws Exception{
		String result_str="add";
		String acctno=kyzacct.getAcctNo();
		Boolean result=kyzacctSer.checkAcctNo(acctno);
		if(result==false&&isnull.equals("isNull")){
			result_str=null;
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>alert('��إN��("+acctno+"),�Э��s��J!');history.back()</script>");	
			
		}else{
			kyzacctSer.add(kyzacct);
		}		
		return result_str;
	}
	
	public String findPageBean(){
		ActionContext.getContext().getApplication().clear();
		bean=kyzacctSer.findPageBean(25, page, acctNo,acctName);
		return "beanList";		
	}
	public String findPageBean2(){
		bean=kyzacctSer.findPageBean(25, page, acctNo.trim(), acctName.trim());
		ActionContext.getContext().getApplication().clear();
		if(acctNo!=null&&!acctNo.equals("")){
			ActionContext.getContext().getApplication().put("kyzacct_acctNo", acctNo.trim());
		}
		if(acctName!=null&&!acctName.equals("")){
			ActionContext.getContext().getApplication().put("kyzacct_acctName", acctName.trim());
		}
		return "beanList1";
	}
	public String findPageBean3(){
		acctNo=(String)ActionContext.getContext().getApplication().get("kyzacct_acctNo");
		acctName=(String)ActionContext.getContext().getApplication().get("kyzacct_acctName");
		bean=kyzacctSer.findPageBean(25, page, acctNo, acctName);
		return "beanList1";
	}
	public String findById(){
		kyzacct=kyzacctSer.findById(acctNo);
		return "findById";
	}
	public String delete(){
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setContent(acctNo);
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		log.setUsername(user.getUsername());
		kyzacctSer.delete(acctNo,log);
		return "delete";
	}



}
