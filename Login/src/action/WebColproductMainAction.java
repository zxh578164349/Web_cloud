package action;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import services.IKyVisabillmServices;
import services.IWebColproductMainServices;
import services.IWebuserEmailServices;
import util.GlobalMethod;
import util.JasperHelper;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyVisabillm;
import entity.KyzExpectmatmLog;
import entity.WebColproductItems;
import entity.WebColproductMain;
import entity.WebNewproduct;

public class WebColproductMainAction extends ActionSupport implements ServletResponseAware{
	 private HttpServletResponse response;
	 private IWebColproductMainServices webcolproServer;
	 private WebColproductItems item;
	 private WebColproductMain obj;
	 private int page;
	 private PageBean bean;
	 private String factNo;
	 private String billNo;
	 private int iid;
	 private String yymmdd;
	 private String yymmdd2;
	 private String createDate;
	 private String ajaxResult;
	 private IKyVisabillmServices visabillmSer;
	 private IWebuserEmailServices webuseremailSer;
	 private String isnull;	 
	 private int backIndex;
	 private String title;
	 private String addorupdate;
	 private String itemNo;
	 private String visaSort;
	 private String readMk;
	 private String lookordown;
	 
	 	 	
	 
	public String getLookordown() {
		return lookordown;
	}

	public void setLookordown(String lookordown) {
		this.lookordown = lookordown;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getVisaSort() {
		return visaSort;
	}

	public void setVisaSort(String visaSort) {
		this.visaSort = visaSort;
	}

	public String getReadMk() {
		return readMk;
	}

	public void setReadMk(String readMk) {
		this.readMk = readMk;
	}

	public String getAddorupdate() {
		return addorupdate;
	}

	public void setAddorupdate(String addorupdate) {
		this.addorupdate = addorupdate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getBackIndex() {
		return backIndex;
	}

	public void setBackIndex(int backIndex) {
		this.backIndex = backIndex;
	}

	public String getIsnull() {
		return isnull;
	}

	public void setIsnull(String isnull) {
		this.isnull = isnull;
	}

	public void setVisabillmSer(IKyVisabillmServices visabillmSer) {
		this.visabillmSer = visabillmSer;
	}

	public void setWebuseremailSer(IWebuserEmailServices webuseremailSer) {
		this.webuseremailSer = webuseremailSer;
	}

	public String getAjaxResult() {
		return ajaxResult;
	}

	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public WebColproductMain getObj() {
		return obj;
	}

	public void setObj(WebColproductMain obj) {
		this.obj = obj;
	}

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

	

	public String getYymmdd() {
		return yymmdd;
	}

	public void setYymmdd(String yymmdd) {
		this.yymmdd = yymmdd;
	}

	public String getYymmdd2() {
		return yymmdd2;
	}

	public void setYymmdd2(String yymmdd2) {
		this.yymmdd2 = yymmdd2;
	}

	public void setWebcolproServer(IWebColproductMainServices webcolproServer) {
		this.webcolproServer = webcolproServer;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
		
	}
	
	public String add(){
		try{
			obj.setVisaTypeM(obj.getVisaType().substring(0,2));
			if(isnull.equals("isNull")){//start if
				WebColproductMain col=webcolproServer.findByBillNo(obj.getBillNo());
				if(col==null){					
					webcolproServer.add(obj);				
					KyVisabillm vbm=visabillmSer.findById(obj.getFactNo().getFactNo(), obj.getVisaType(), obj.getBillNo());				      
				    List<String>list_emailPwd=webuseremailSer.findByFactNoAEmailPwd2(vbm.getId().getFactNo(),vbm.getSignerNext());											      
					GlobalMethod.sendNewEmail(vbm,list_emailPwd);//發送郵件	
					ajaxResult="0";	//添加成功
					}else{
						ajaxResult="2";	//數據已存在
					}								
			}//end if
			else{				
				webcolproServer.add(obj);
				ajaxResult="0";	
			}
		}catch(Exception e){
			e.printStackTrace();
			ajaxResult="1";	//添加失敗
		}
		
		return "add";
	}
	
	
	
	public String makeBillNo(){
		String maxbillno=webcolproServer.findByfactNoACreatedate(factNo, createDate);
		StringBuffer bn=new StringBuffer();
		bn.append("CP");
		bn.append(factNo);
		bn.append(createDate.substring(2,8));
		if(maxbillno==null||"".equals(maxbillno)){
			bn.append("01");
		}else{
			int num=Integer.parseInt(maxbillno.substring(maxbillno.length()-2, maxbillno.length()));
			if(num+1<10){
				bn.append("0"+(num+1));
			}else{
				bn.append(num+1);
			}
		}
		ajaxResult=bn.toString();
		return "makeBillNo";	
	}
	
	
	public String findPageBean(){
		ActionContext.getContext().getSession().remove("allRow");
		ActionContext.getContext().getSession().put("c_factNo", factNo);
		ActionContext.getContext().getSession().put("c_billNo", billNo);
		ActionContext.getContext().getSession().put("c_dateA", yymmdd);
		ActionContext.getContext().getSession().put("c_dateB", yymmdd2);		
		ActionContext.getContext().getSession().put("c_title", title);	
		bean=webcolproServer.findPageBeanMain(page, 0, factNo, billNo, yymmdd, yymmdd2,title);		
		return "findPageBean";
	}
	
	public String findPageBean2(){		
		this.findPageBean();
		return "findPageBean1";
	}
	
	public String findPageBean3(){
		factNo=(String)ActionContext.getContext().getSession().get("c_factNo");
		billNo=(String)ActionContext.getContext().getSession().get("c_billNo");
		yymmdd=(String)ActionContext.getContext().getSession().get("c_dateA");
		yymmdd2=(String)ActionContext.getContext().getSession().get("c_dateB");	
		title=(String)ActionContext.getContext().getSession().get("c_title");	
		bean=webcolproServer.findPageBeanMain(page, 0, factNo, billNo, yymmdd, yymmdd2,title);
		if(backIndex==1){
			return "findPageBean";
		}else{
			return "findPageBean1";
		}		
	}
	
	public String findByBillNo(){
		addorupdate="update";
		obj=webcolproServer.findByBillNo(billNo);
		return "findByBillNo";
	}
	
	public String findByBillNo_layer(){
		obj=webcolproServer.findByBillNo(billNo);
		return "findByBillNo_layer";
	}
	
	public String delete(){
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setObj("WebColproduct");
		log.setBillNo(billNo);
		webcolproServer.delete(billNo, log);		
		return "delete";
	}
	
	
	public void print(String factNo,String billNo,String sort) throws IOException{		
		Map<String,Object>map_result=webcolproServer.print(factNo, billNo, sort,null);
		if(map_result!=null&&map_result.size()>0){
			Map<String,Object>map=(Map<String,Object>)map_result.get("map");
			List<WebNewproduct>list=(List<WebNewproduct>)map_result.get("list");
			if(lookordown!=null){
				if(lookordown.equals("look")){
					JasperHelper.exportmain("line", map,"webcolproduct_main.jasper", list,billNo, "jasper/audit/");
				}else{
					JasperHelper.exportmain("pdf", map,"webcolproduct_main.jasper", list,billNo, "jasper/audit/");
				}
			}else{
				JasperHelper.exportmain("pdf", map,"webcolproduct_main.jasper", list,billNo, "jasper/audit/");
			}
		}
								
	}
	
	public void print2() throws IOException{
		this.print(factNo, billNo, visaSort);
	}
	
	
	
	
	 

}
