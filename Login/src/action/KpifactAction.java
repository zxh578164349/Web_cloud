package action;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import services.IKpifactServices;
import util.PageBean;
import entity.Kpifact;

public class KpifactAction extends ActionSupport implements ServletResponseAware{
	private Kpifact kpi;
	private String factNo;
	private String factCode;
	private String yyyy;
	private PageBean bean;
	private int page;
	private String isnull;
	private IKpifactServices kpiSer;
	private javax.servlet.http.HttpServletResponse response;
	private String ajaxResult;//申請函文時返回的ajax結果,   0:提交成功       1:提交失敗
	
	public String getAjaxResult() {
		return ajaxResult;
	}
	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}
	public Kpifact getKpi() {
		return kpi;
	}
	public void setKpi(Kpifact kpi) {
		this.kpi = kpi;
	}
	public String getFactNo() {
		return factNo;
	}
	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}
	public String getYyyy() {
		return yyyy;
	}
	public void setYyyy(String yyyy) {
		this.yyyy = yyyy;
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
	
	public String getFactCode() {
		return factCode;
	}
	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}
	
	public String getIsnull() {
		return isnull;
	}
	public void setIsnull(String isnull) {
		this.isnull = isnull;
	}
	public void setKpiSer(IKpifactServices kpiSer) {
		this.kpiSer = kpiSer;
	}
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	public String add() throws IOException{
		String result=null;
		try{
			if(isnull!=null){
				kpiSer.add(kpi);
				result="add";
				ajaxResult="0";
			}else{
				factNo=kpi.getId().getFactNo();
				factCode=kpi.getId().getFactCode();
				yyyy=kpi.getId().getYyyy();
				Kpifact kpi_temp=kpiSer.findById(factNo, factCode, yyyy);
				if(kpi_temp!=null){
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().print("<script>alert('數據庫已存在("+factNo+factCode+yyyy+")');history.back()</script>");
				}else{
					kpiSer.add(kpi);
					result="add";
					ajaxResult="0";
				}
			}
		}catch(Exception e){
			result="add";
			e.printStackTrace();
			ajaxResult="1";
		}				
		return result;
	}
	public String findById(){
		kpi=kpiSer.findById(factNo, factCode, yyyy);
		return "findById";
	}
	public String findById_copy(){
		kpi=kpiSer.findById(factNo, factCode, yyyy);
		return "findById_copy";
	}
	public String findPageBean(){
		//ActionContext.getContext().getApplication().clear();
		ActionContext.getContext().getSession().remove("public_factno");
		ActionContext.getContext().getSession().remove("public_yyyy");
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		bean=kpiSer.findPageBean(25, page, factNo, yyyy);
		return "findPageBean";		
	}
	public String findPageBean2(){
		//ActionContext.getContext().getApplication().clear();
		ActionContext.getContext().getSession().remove("public_factno");
		ActionContext.getContext().getSession().remove("public_yyyy");
		bean=kpiSer.findPageBean(25, page, factNo, yyyy);
		ActionContext.getContext().getSession().put("public_factno", factNo);
		ActionContext.getContext().getSession().put("public_yyyy", yyyy);
		return "findPageBean1";
	}
	public String findPageBean3(){
		factNo=(String)ActionContext.getContext().getSession().get("public_factno");
		yyyy=(String)ActionContext.getContext().getSession().get("public_yyyy");
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		bean=kpiSer.findPageBean(25, page, factNo, yyyy);
		return "findPageBean1";
	}
	public String delete(){
		kpiSer.delete(factNo, factCode, yyyy);
		return "delete";
	}
	public String formatDouble(double s) {
		DecimalFormat format = new DecimalFormat(",###.##");
		String temp = format.format(s);
		return temp;
		// return temp.replace(",", "");
	}
	public String formatPercent(double s){
		DecimalFormat format=new DecimalFormat("0.00%");
		String temp=format.format(s);
		return temp;
	}
	public String formatInteger(double s){
		DecimalFormat format=new DecimalFormat("#");
		String temp=format.format(s);
		return temp;
	}

	

}
