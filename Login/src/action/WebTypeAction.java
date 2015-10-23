package action;

import services.IWebTypeServices;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.WebType;

public class WebTypeAction extends ActionSupport{
	private int page;
	private int pageSize;
	private String factNo;
	private PageBean bean;
	private WebType webtype;
	private String typeNo;
	private IWebTypeServices webtypeSer;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getFactNo() {
		return factNo;
	}
	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}
	public PageBean getBean() {
		return bean;
	}
	public void setBean(PageBean bean) {
		this.bean = bean;
	}
	
	public WebType getWebtype() {
		return webtype;
	}
	public void setWebtype(WebType webtype) {
		this.webtype = webtype;
	}
	
	public String getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}
	public void setWebtypeSer(IWebTypeServices webtypeSer) {
		this.webtypeSer = webtypeSer;
	}
	
	public String add(){
		webtypeSer.add(webtype);
		return "add";
	}
	public String findPageBean(){
		ActionContext.getContext().getApplication().clear();
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		bean=webtypeSer.findPageBean(page, 25, factNo);
		return "beanList";
	}
	public String findPageBean2(){
		ActionContext.getContext().getApplication().clear();
		bean=webtypeSer.findPageBean(page, 25, factNo);
		ActionContext.getContext().getApplication().put("webtypeFactno", factNo);
		return "beanList1";
	}
	public String findPageBean3(){
		factNo=(String)ActionContext.getContext().getApplication().get("webtypeFactno");
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		bean=webtypeSer.findPageBean(page, 25, factNo);
		return "beanList1";
	}
	public String delete(){
		webtypeSer.delete(factNo, typeNo);
		return "delete";
	}
	public String findById(){
		webtype=webtypeSer.findById(factNo, typeNo);
		return "findById";
	}
	
	
	

}
