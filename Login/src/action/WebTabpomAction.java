package action;

import java.util.ArrayList;
import java.util.List;

import services.IWebTabpomServices;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.WebFact;
import entity.WebFactId;
import entity.WebTabpom;

public class WebTabpomAction extends ActionSupport{
	private PageBean bean;
	private WebTabpom tabpom;
	private int page;
	private String pomName;
	private String brank;
	private String ajaxResult;//申請函文時返回的ajax結果,   0:提交成功       1:提交失敗      2:提示數據庫已經存在
	private String pomNo;
	private List<String>list_fact;
	private IWebTabpomServices tabpomSer;
	
	
	public List<String> getList_fact() {
		return list_fact;
	}
	public void setList_fact(List<String> list_fact) {
		this.list_fact = list_fact;
	}
	public String getPomNo() {
		return pomNo;
	}
	public void setPomNo(String pomNo) {
		this.pomNo = pomNo;
	}
	public String getAjaxResult() {
		return ajaxResult;
	}
	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}
	public String getPomName() {
		return pomName;
	}
	public void setPomName(String pomName) {
		this.pomName = pomName;
	}
	public String getBrank() {
		return brank;
	}
	public void setBrank(String brank) {
		this.brank = brank;
	}
	public PageBean getBean() {
		return bean;
	}
	public void setBean(PageBean bean) {
		this.bean = bean;
	}
	
	public WebTabpom getTabpom() {
		return tabpom;
	}
	public void setTabpom(WebTabpom tabpom) {
		this.tabpom = tabpom;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setTabpomSer(IWebTabpomServices tabpomSer) {
		this.tabpomSer = tabpomSer;
	}
	
	public String findPageBean(){
		ActionContext.getContext().getSession().remove("tabpom_name");
		ActionContext.getContext().getSession().remove("tabpom_brank");
		bean=tabpomSer.findPageBean(25, page, pomName, brank);
		return "beanList";
	}
	
	public String findPageBean2(){
		ActionContext.getContext().getSession().put("tabpom_name", pomName);			
		ActionContext.getContext().getSession().put("tabpom_brank", brank);		
		bean=tabpomSer.findPageBean(25, page, pomName, brank);
		return "beanList1";
	}
	public String findPageBean3(){
		pomName=(String)ActionContext.getContext().getSession().get("tabpom_name");
		brank=(String)ActionContext.getContext().getSession().get("tabpom_brank");
		bean=tabpomSer.findPageBean(25, page, pomName, brank);
		return "beanList1";
	}
	public String add(){
		pomNo=tabpomSer.findPomNoById(tabpom.getPomNo());
		if(pomNo!=null&&!pomNo.equals("")){
			ajaxResult="2";
		}else{
			try{
				if(list_fact.size()>0){
					List<WebFact>list_factobj=new ArrayList<WebFact>();
					WebFact fact=new WebFact();
					WebFactId factid=new WebFactId();
					for(int i=0;i<list_fact.size();i++){
						factid.setFactNo(list_fact.get(i));
						factid.setFactArea(tabpom.getComponent());
						fact.setId(factid);
						list_factobj.add(fact);
					}
					tabpom.setList_fact(list_factobj);
				}
				tabpomSer.add(tabpom);
				ajaxResult="0";
			}catch(Exception e){
				ajaxResult="1";
			}
		}
		return "add";
	}
	
	public String findById(){
		tabpom=tabpomSer.findById(pomNo);
		return "findById";
	}

}
