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
	private String ajaxResult;//申請函文時返回的ajax結果,   0:提交成功       1:提交失敗
	private int backIndex;//返回標識      0或null:不走返回路徑         1:走返回路徑
	private IWebTypeServices webtypeSer;
	
	
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
		try{
			//如果頁面上選擇了"出差類"，則要給值爲TR，標明爲"出差類"20160203
			if(typeNo.equals("TR")){
				webtype.getId().setTypeNo(typeNo);
			}
			webtypeSer.add(webtype);
			ajaxResult="0";
		}catch(Exception e){
			e.printStackTrace();
			ajaxResult="1";
		}		
		return "add";
	}
	public String findPageBean(){
		//ActionContext.getContext().getApplication().clear();
		ActionContext.getContext().getSession().remove("public_factNo");
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		bean=webtypeSer.findPageBean(page, 25, factNo);
		return "beanList";
	}
	public String findPageBean2(){
		//ActionContext.getContext().getApplication().clear();
		ActionContext.getContext().getSession().remove("public_factNo");
		bean=webtypeSer.findPageBean(page, 25, factNo);
		ActionContext.getContext().getSession().put("public_factNo", factNo);
		return "beanList1";
	}
	public String findPageBean3(){
		String result="beanList1";
		if(backIndex==1){
			result="beanList";
		}
		factNo=(String)ActionContext.getContext().getSession().get("public_factNo");
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		bean=webtypeSer.findPageBean(page, 25, factNo);
		return result;
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
