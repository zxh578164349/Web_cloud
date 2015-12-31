package action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import services.IWebFactorderServices;
import util.ImportExcel;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.WebFactorder;

public class WebFactOrderAction extends ActionSupport{
	private IWebFactorderServices webfactorderSer;
	private WebFactorder factorder;
	private PageBean bean;
	private int page;
	private String factNo;
	private List<String>factNos=new ArrayList<String>();
	private String brank;
	private String customer;
	private String model;
	private String component;
	
	
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
	
	
	public List<String> getFactNos() {
		return factNos;
	}
	public void setFactNos(List<String> factNos) {
		this.factNos = factNos;
	}
	public String getBrank() {
		return brank;
	}
	public void setBrank(String brank) {
		this.brank = brank;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public WebFactorder getFactorder() {
		return factorder;
	}
	public void setFactorder(WebFactorder factorder) {
		this.factorder = factorder;
	}
	public void setWebfactorderSer(IWebFactorderServices webfactorderSer) {
		this.webfactorderSer = webfactorderSer;
	}
	public String add() throws IOException{
		String path="d:\\北越&鞋塑2015接單匯總-1201.xls";
		List<String>list_all=ImportExcel.exportListFromExcel(new File(path), 1);
		webfactorderSer.addLarge(list_all);
		return null;
	}
	public String findPageBean(){
		
		//System.out.println(factNos.getClass().getName());
		ActionContext.getContext().getSession().remove("allrow");//首次進入，清除分頁的總條數（dao層中的allrow）
		ActionContext.getContext().getSession().remove("public_factnos");
		ActionContext.getContext().getSession().remove("public_factno");
		ActionContext.getContext().getSession().remove("public_brank");
		ActionContext.getContext().getSession().remove("public_customer");
		ActionContext.getContext().getSession().remove("public_model");
		ActionContext.getContext().getSession().remove("public_component");
		bean=webfactorderSer.findPageBean(25, page, factNos, brank, customer, model, component);
		return "beanList";
		
	}
	public String findPageBean2(){
		//System.out.println(factNos.getClass().getName());//com.opensymphony.xwork2.util.XWorkList
		ActionContext.getContext().getSession().remove("allrow");//條件查詢，清除分頁的總條數（dao層中的allrow）
		ActionContext.getContext().getSession().remove("public_factnos");
		ActionContext.getContext().getSession().remove("public_factno");
		ActionContext.getContext().getSession().remove("public_brank");
		ActionContext.getContext().getSession().remove("public_customer");
		ActionContext.getContext().getSession().remove("public_model");
		ActionContext.getContext().getSession().remove("public_component");
		
		ActionContext.getContext().getSession().put("public_factnos",factNos);
		ActionContext.getContext().getSession().put("public_factno",factNo);
		ActionContext.getContext().getSession().put("public_brank",brank);
		ActionContext.getContext().getSession().put("public_customer",customer);
		ActionContext.getContext().getSession().put("public_model",model);
		ActionContext.getContext().getSession().put("public_component",component);
		bean=webfactorderSer.findPageBean(25, page, factNos, brank, customer, model, component);
		return "beanList1";
	}
	public String findPageBean3(){
		factNos=(List<String>)ActionContext.getContext().getSession().get("public_factnos");
		factNo=(String)ActionContext.getContext().getSession().get("public_factno");
		brank=(String)ActionContext.getContext().getSession().get("public_brank");
		customer=(String)ActionContext.getContext().getSession().get("public_customer");
		model=(String)ActionContext.getContext().getSession().get("public_model");
		component=(String)ActionContext.getContext().getSession().get("public_component");
		bean=webfactorderSer.findPageBean(25, page, factNos, brank, customer, model, component);
		return "beanList1";
	}

}
