package action;

import java.util.List;

import services.IKyzSecServices;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyzSec;

public class KyzSecAction extends ActionSupport{
	private KyzSec kyzsec;
	private PageBean bean;
	private String factNo;
	private String secNo;
	private int page;
	private IKyzSecServices kyzsecSer;
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
		kyzsecSer.add(kyzsec);
		return "add";
	}
	public String findPageBean(){
		ActionContext.getContext().getApplication().clear();
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		bean=kyzsecSer.findPageBean(25, page, factNo, secNo);
		return "beanList";
	}
	public String findPageBean2(){
		bean=kyzsecSer.findPageBean(25, page, factNo, secNo.trim());
		ActionContext.getContext().getApplication().clear();
		if(factNo!=null&&!factNo.equals("")){
			ActionContext.getContext().getApplication().put("kyzsec_factNo", factNo);
		}
		if(secNo!=null&&!secNo.equals("")){
			ActionContext.getContext().getApplication().put("kyzsec_secNo", secNo.trim());
		}
		return "beanList1";
	}
    public String findPageBean3(){
    	factNo=(String)ActionContext.getContext().getApplication().get("kyzsec_factNo");
    	secNo=(String)ActionContext.getContext().getApplication().get("kyzsec_secNo");
    	if(factNo==null||factNo.equals("")){
    		factNo=(String)ActionContext.getContext().getSession().get("factNo");
    	}
    	bean=kyzsecSer.findPageBean(25, page, factNo, secNo);
    	return "beanList1";
    }
    public String findById(){
    	kyzsec=kyzsecSer.findById(factNo, secNo);
    	return "findById";
    }
    public String delete(){
    	kyzsecSer.delete(factNo, secNo);
    	return "delete";
    }
    
    public void getSecNoByFactNo(){
    	List<KyzSec>list=kyzsecSer.findByFactno(factNo);
    	ActionContext.getContext().getSession().put("get_kyzsecs_list", list);
    }
	

}
