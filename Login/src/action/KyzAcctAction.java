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
	private String ajaxResult;//申請函文時返回的ajax結果,   0:提交成功       1:提交失敗
	private int backIndex;//返回標識      0或null:不走返回路徑         1:走返回路徑
	private javax.servlet.http.HttpServletResponse response;
	private IKyzAcctServices kyzacctSer;
	
	
	
	public String getAjaxResult() {
		return ajaxResult;
	}
	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}
	public int getBackIndex() {
		return backIndex;
	}
	public void setBackIndex(int backIndex) {
		this.backIndex = backIndex;
	}
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
		String result=kyzacctSer.findAcctNo(kyzacct.getAcctNo());
		if(result!=null){
				ajaxResult="2";//表示數據庫已經存在數據
			
		}else{
			try{
				kyzacctSer.add(kyzacct);
				ajaxResult="0";
			}catch(Exception e){
				ajaxResult="1";
				System.out.println(e);
				
			}
			
		}		
		return "add";
	}
	
	public String findPageBean(){			
		ActionContext.getContext().getSession().remove("kyzacct_acctNo");
		ActionContext.getContext().getSession().remove("kyzacct_acctName");
		bean=kyzacctSer.findPageBean(20,page, acctNo,acctName);
		return "beanList";		
	}
	public String findPageBean2(){
		bean=kyzacctSer.findPageBean(20,page, acctNo.trim(), acctName);
		ActionContext.getContext().getSession().put("kyzacct_acctNo", acctNo);		
		ActionContext.getContext().getSession().put("kyzacct_acctName", acctName);		
		return "beanList1";
	}
	public String findPageBean3(){
		acctNo=(String)ActionContext.getContext().getSession().get("kyzacct_acctNo");
		acctName=(String)ActionContext.getContext().getSession().get("kyzacct_acctName");
		bean=kyzacctSer.findPageBean(20,page, acctNo, acctName);
		return "beanList1";
	}
	public String findById(){
		kyzacct=kyzacctSer.findById(acctNo);
		return "findById";
	}
	public String delete(){
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setObj("KyzAcct");
		log.setContent(acctNo);
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		log.setUsername(user.getUsername());
		kyzacctSer.delete(acctNo,log);
		return "delete";
	}



}
