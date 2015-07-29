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
		if(isnull!=null){
			kpiSer.add(kpi);
			result="add";
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
			}
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
		ActionContext.getContext().getApplication().clear();
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		bean=kpiSer.findPageBean(25, page, factNo, yyyy);
		return "findPageBean";		
	}
	public String findPageBean2(){
		ActionContext.getContext().getApplication().clear();
		bean=kpiSer.findPageBean(25, page, factNo, yyyy);
		ActionContext.getContext().getApplication().put("kpifact_factno", factNo);
		ActionContext.getContext().getApplication().put("kpifact_yyyy", yyyy);
		return "findPageBean1";
	}
	public String findPageBean3(){
		factNo=(String)ActionContext.getContext().getApplication().get("kpifact_factno");
		yyyy=(String)ActionContext.getContext().getApplication().get("kpifact_yyyy");
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
