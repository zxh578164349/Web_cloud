package action;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import services.IWebColproductMainServices;
import util.PageBean;

import com.opensymphony.xwork2.ActionSupport;

import entity.WebColproductItems;

public class WebColproductMainAction extends ActionSupport implements ServletResponseAware{
	 private HttpServletResponse response;
	 private IWebColproductMainServices webcolproServer;
	 private WebColproductItems item;
	 private int page;
	 private PageBean bean;
	 private String factNo;
	 private String billNo;
	 private int iid;
	 private String dateA;
	 private String dateB;
	 
	 	  
	public WebColproductItems getItem() {
		return item;
	}

	public void setItem(WebColproductItems item) {
		this.item = item;
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

	public String getFactNo() {
		return factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public int getIid() {
		return iid;
	}

	public void setIid(int iid) {
		this.iid = iid;
	}

	public String getDateA() {
		return dateA;
	}

	public void setDateA(String dateA) {
		this.dateA = dateA;
	}

	public String getDateB() {
		return dateB;
	}

	public void setDateB(String dateB) {
		this.dateB = dateB;
	}

	public void setWebcolproServer(IWebColproductMainServices webcolproServer) {
		this.webcolproServer = webcolproServer;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
		
	}
	
	
	 

}
