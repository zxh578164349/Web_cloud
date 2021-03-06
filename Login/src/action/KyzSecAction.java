package action;

import java.util.List;

import services.IKyzSecServices;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyzExpectmatmLog;
import entity.KyzSec;
import entity.WebUser;

public class KyzSecAction extends ActionSupport{
	private KyzSec kyzsec;
	private PageBean bean;
	private String factNo;
	private String secNo;
	private int page;
	private String ajaxResult;//申請函文時返回的ajax結果,   0:提交成功       1:提交失敗
	private IKyzSecServices kyzsecSer;
	
	
	public String getAjaxResult() {
		return ajaxResult;
	}
	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}
	public KyzSec getKyzsec() {
		return kyzsec;
	}
	public void setKyzsec(KyzSec kyzsec) {
		this.kyzsec = kyzsec;
	}
	public PageBean getBean() {
		return bean;
	}
	public void setBean(PageBean bean) {
		this.bean = bean;
	}
	public String getFactNo() {
		return factNo;
	}
	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}
	public String getSecNo() {
		return secNo;
	}
	public void setSecNo(String secNo) {
		this.secNo = secNo;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setKyzsecSer(IKyzSecServices kyzsecSer) {
		this.kyzsecSer = kyzsecSer;
	}
	
	public String add(){
		try{
			kyzsecSer.add(kyzsec);
			ajaxResult="0";
		}catch(Exception e){
			ajaxResult="1";
		}		
		return "add";
	}
	public String findPageBean(){
		ActionContext.getContext().getSession().remove("kyzsec_factNo");
		ActionContext.getContext().getSession().remove("kyzsec_secNo");
		bean=kyzsecSer.findPageBean(20,page, factNo, secNo);
		return "beanList";
	}
	public String findPageBean2(){
		bean=kyzsecSer.findPageBean(20,page, factNo, secNo.trim());			
		ActionContext.getContext().getSession().put("kyzsec_factNo", factNo);		
		ActionContext.getContext().getSession().put("kyzsec_secNo",secNo);		
		return "beanList1";
	}
    public String findPageBean3(){
    	factNo=(String)ActionContext.getContext().getSession().get("kyzsec_factNo");
    	secNo=(String)ActionContext.getContext().getSession().get("kyzsec_secNo");   	
    	bean=kyzsecSer.findPageBean(20,page, factNo, secNo);
    	return "beanList1";
    }
    public String findById(){
    	kyzsec=kyzsecSer.findById(factNo, secNo);
    	return "findById";
    }
    public String delete(){
    	KyzExpectmatmLog log=new KyzExpectmatmLog();
    	log.setObj("KyzSec");
    	log.setFactNo(factNo);
		log.setContent(secNo);
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		log.setUsername(user.getUsername());
    	kyzsecSer.delete(factNo, secNo,log);
    	return "delete";
    }
    
    public void getSecNoByFactNo(){
    	List<KyzSec>list=kyzsecSer.findByFactno(factNo);
    	ActionContext.getContext().getSession().put("get_kyzsecs_list", list);
    }
	

}
