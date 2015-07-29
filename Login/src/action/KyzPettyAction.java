package action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import services.IKyzAcctServices;
import services.IKyzPettyServices;
import services.IKyzSecServices;
import services.IWebFactServices;
import util.JasperHelper;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyzAcct;
import entity.KyzPetty;
import entity.KyzSec;

public class KyzPettyAction extends ActionSupport{
	private KyzPetty kyzpetty;
	private String factNo;
	private String billNo;
	private String createTime;
	private int page;
	private PageBean bean;
	private String yymm;
	private String dateTime;//支付日期(開始)
	private String dateTime2;//支付日期(結束)
	private String expenseMk;//費用性質(現金或銀行)
	private String taxmMk;//管稅注記(稅帳與非稅帳)
	private String lookordown;//標記查看或下載
	private IKyzPettyServices kyzpettySer;
	private IKyzAcctServices kyzacctSer;
	private IKyzSecServices kyzsecSer;
	private IWebFactServices webFactSer;
	
	
	
	public String getDateTime2() {
		return dateTime2;
	}
	public void setDateTime2(String dateTime2) {
		this.dateTime2 = dateTime2;
	}
	public String getLookordown() {
		return lookordown;
	}
	public void setLookordown(String lookordown) {
		this.lookordown = lookordown;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getExpenseMk() {
		return expenseMk;
	}
	public void setExpenseMk(String expenseMk) {
		this.expenseMk = expenseMk;
	}
	public String getTaxmMk() {
		return taxmMk;
	}
	public void setTaxmMk(String taxmMk) {
		this.taxmMk = taxmMk;
	}
	public KyzPetty getKyzpetty() {
		return kyzpetty;
	}
	public void setKyzpetty(KyzPetty kyzpetty) {
		this.kyzpetty = kyzpetty;
	}
	
	public String getYymm() {
		return yymm;
	}
	public void setYymm(String yymm) {
		this.yymm = yymm;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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
	public void setKyzpettySer(IKyzPettyServices kyzpettySer) {
		this.kyzpettySer = kyzpettySer;
	}
	
	
	public void setKyzacctSer(IKyzAcctServices kyzacctSer) {
		this.kyzacctSer = kyzacctSer;
	}
	public void setKyzsecSer(IKyzSecServices kyzsecSer) {
		this.kyzsecSer = kyzsecSer;
	}
	
	
	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}
	public String findPageBean(){
		ActionContext.getContext().getApplication().clear();
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		bean=kyzpettySer.findPageBean(25, page, factNo, billNo, yymm);
		return "beanList";
	}
	
	public String findPageBean2(){
		ActionContext.getContext().getApplication().clear();
		bean=kyzpettySer.findPageBean(25, page, factNo, billNo, yymm);
		if(factNo!=null&&!factNo.equals("")){
			ActionContext.getContext().getApplication().put("kyzpetty_factno", factNo);
		}
		if(billNo!=null&&!billNo.equals("")){
			ActionContext.getContext().getApplication().put("kyzpetty_billno", billNo.trim());
		}
		if(yymm!=null&&!yymm.equals("")){
			ActionContext.getContext().getApplication().put("kyzpetty_yymm", yymm);
		}
		return "beanList1";
	}
	
	public String findPageBean3(){
		factNo=(String)ActionContext.getContext().getApplication().get("kyzpetty_factno");
		billNo=(String)ActionContext.getContext().getApplication().get("kyzpetty_billno");
		yymm=(String)ActionContext.getContext().getApplication().get("kyzpetty_yymm");
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		bean=kyzpettySer.findPageBean(25, page, factNo, billNo, yymm);
		return "beanList1";
	}
	
	public String add(){
		kyzpettySer.add(kyzpetty);
		return "add";
	}
	
	public String findById(){
		kyzpetty=kyzpettySer.findById(factNo, billNo);
		return "findById";
	}
	
	public String delete(){
		kyzpettySer.delete(factNo, billNo);
		return "delete";
	}
	
	public String findAllSecNoAndAcctNo(){
		String factno=(String)ActionContext.getContext().getSession().get("factNo");
		List<KyzSec>list_kyzsec=kyzsecSer.findByFactno(factno);
		List<KyzAcct>list_kyzacct=kyzacctSer.findAll();
		ActionContext.getContext().getSession().put("list_kyzsec",list_kyzsec);
		ActionContext.getContext().getSession().put("list_kyzacct", list_kyzacct);
		return "findAllSecNoAndAcctNo";
	}
	
	public void print() throws UnsupportedEncodingException{
		List<KyzPetty>list=kyzpettySer.findByAnyThing(factNo, dateTime,dateTime2, expenseMk, taxmMk);
		Map<String,Object>map=new HashMap<String,Object>();
		String factName="";
		String title="";
		if(factNo!=null&&!factNo.equals("")){
			factName=webFactSer.selByid(factNo);
			title=factName+"零用金支出明細表";
		}else{
			title="加久有限公司各廠零用金支出明細表";
		}
		map.put("title", title);
		map.put("factname", factName);
		map.put("factno", factNo);
		map.put("datetime", dateTime);
		map.put("expenseMk", expenseMk);
		map.put("taxmmk", taxmMk);
		if(lookordown.equals("look")){
			JasperHelper.exportmain("html", map,"kyzpetty.jasper",list, title, "jasper/audit/");
		}else{			
			JasperHelper.exportmain("excel", map,"kyzpetty.jasper",list,title , "jasper/audit/");
		}
		
		
	}
	


}
