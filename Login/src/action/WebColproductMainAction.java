package action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import services.IKyVisabillmServices;
import services.IWebColproductMainServices;
import services.IWebuserEmailServices;
import util.GlobalMethod;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyVisabillm;
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
	 private String dateA;
	 private String dateB;
	 private String createDate;
	 private String ajaxResult;
	 private IKyVisabillmServices visabillmSer;
	 private IWebuserEmailServices webuseremailSer;
	 private String isnull;	 
	 private int backIndex;
	 
	 
	 
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
	
	public String add(){
		try{
			obj.setVisaTypeM(obj.getVisaType().substring(0,2));
			if(isnull.equals("isNull")){//start if
				WebColproductMain col=webcolproServer.findByBillNo(obj.getBillNo());
				if(col==null){					
					webcolproServer.add(obj);				
					KyVisabillm vbm=visabillmSer.findById(obj.getFactNo(), obj.getVisaType(), obj.getBillNo());				      
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
		ActionContext.getContext().getSession().put("c_dateA", dateA);
		ActionContext.getContext().getSession().put("c_dateB", dateB);		
		bean=webcolproServer.findPageBean(page, 0, factNo, billNo, dateA, dateB);		
		return "findPageBean";
	}
	
	public String findPageBean2(){		
		this.findPageBean();
		return "findPageBean1";
	}
	
	public String findPageBean3(){
		factNo=(String)ActionContext.getContext().getSession().get("c_factNo");
		billNo=(String)ActionContext.getContext().getSession().get("c_billNo");
		dateA=(String)ActionContext.getContext().getSession().get("c_dateA");
		dateB=(String)ActionContext.getContext().getSession().get("c_dateB");		
		bean=webcolproServer.findPageBean(page, 0, factNo, billNo, dateA, dateB);
		if(backIndex==1){
			return "findPageBean";
		}else{
			return "findPageBean1";
		}		
	}
	
	public String addItem(){
		try{
			webcolproServer.addItem(item);
			ajaxResult="0";
		}catch(Exception e){
			ajaxResult="1";
		}
		return "addItem";
	}
	
	public String findById(){
		item=webcolproServer.findById(iid);
		return "findById";
	}
	
	
	
	
	 

}
