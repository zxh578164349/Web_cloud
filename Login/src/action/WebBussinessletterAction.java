package action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import services.IWebBussinessletterServices;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.WebBussinessletter;

public class WebBussinessletterAction extends ActionSupport{
	private String billNo;
	private String factNo;
	private WebBussinessletter bussletter;
	private PageBean bean;
	private int page;
	private String isnull;
	private String dateFrom;
	private String dateEnd;
	private String timeFrom;
	private String timeEnd;
	private IWebBussinessletterServices webbussletterSer;
	
	
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	public String getTimeFrom() {
		return timeFrom;
	}
	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}
	public String getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	public String getIsnull() {
		return isnull;
	}
	public void setIsnull(String isnull) {
		this.isnull = isnull;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getFactNo() {
		return factNo;
	}
	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}
	public WebBussinessletter getBussletter() {
		return bussletter;
	}
	public void setBussletter(WebBussinessletter bussletter) {
		this.bussletter = bussletter;
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
	public void setWebbussletterSer(IWebBussinessletterServices webbussletterSer) {
		this.webbussletterSer = webbussletterSer;
	}
	
	public String add() throws ParseException{				
		String result="";
		DateFormat fmt1=new SimpleDateFormat("yyyyMMdd");
		DateFormat fmt2=new SimpleDateFormat("yyyyMMddhhmm");
		bussletter.setDateFrom(fmt1.parse(dateFrom));
		bussletter.setDateEnd(fmt1.parse(dateEnd));
		bussletter.setTimeFrom(fmt2.parse(timeFrom));
		bussletter.setTimeEnd(fmt2.parse(timeEnd));
		if(isnull.equals("isNull")){
			
			webbussletterSer.add(bussletter);
		}else{
			webbussletterSer.add(bussletter);
		}
		return null;
	}
	public String findPageBean(){
		ActionContext.getContext().getSession().remove("allrow");//首次進入，清除分頁的總條數（dao層中的allrow）
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		ActionContext.getContext().getSession().remove("public_billNo");
		ActionContext.getContext().getSession().remove("public_factNo");
		bean=webbussletterSer.findPageBean(25, page, billNo, factNo);
		return "beanList";	
	}
	public String findPageBean2(){
		ActionContext.getContext().getSession().remove("allrow");//首次進入，清除分頁的總條數（dao層中的allrow）
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		ActionContext.getContext().getSession().put("public_billNo", billNo);
		ActionContext.getContext().getSession().put("public_factNo", factNo);
		bean=webbussletterSer.findPageBean(25, page, billNo, factNo);
		return "beanList1";
	}
	public String findPageBean3(){
		billNo=(String)ActionContext.getContext().getSession().get("public_billNo");
		factNo=(String)ActionContext.getContext().getSession().get("public_factNo");
		bean=webbussletterSer.findPageBean(25, page, billNo, factNo);
		return "beanList1";
	}
	
	
	

}
