package action;

import java.io.File;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import services.IKyVisabillmServices;
import services.IKyzContactLetterServices;
import services.IKyzExpectmatmFileServices;
import services.IKyzExpectmatmLogServices;
import services.IKyzExpectmatmServices;
import services.IKyzVisaFlowServices;
import services.IWebBussinessletterServices;
import services.IWebTypeServices;
import util.GlobalMethod;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyzExpectmatmLog;
import entity.WebType;
import entity.WebUser;

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
	private IKyzExpectmatmFileServices kyzexpfileSer;
	private IKyzExpectmatmLogServices kyzExpLogSer;
	private IKyzExpectmatmServices kyzSer;
	private IKyzContactLetterServices kyzletterSer;
	private IKyzVisaFlowServices visaSer;
	private IWebBussinessletterServices webbussletterSer;
	private IKyVisabillmServices visabillmSer;
	private JSONArray jsons;
	
	
	public JSONArray getJsons() {
		return jsons;
	}
	public void setJsons(JSONArray jsons) {
		this.jsons = jsons;
	}
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
	
	
	public void setKyzexpfileSer(IKyzExpectmatmFileServices kyzexpfileSer) {
		this.kyzexpfileSer = kyzexpfileSer;
	}
	
	public void setKyzExpLogSer(IKyzExpectmatmLogServices kyzExpLogSer) {
		this.kyzExpLogSer = kyzExpLogSer;
	}
	
	
	public void setKyzSer(IKyzExpectmatmServices kyzSer) {
		this.kyzSer = kyzSer;
	}
	public void setKyzletterSer(IKyzContactLetterServices kyzletterSer) {
		this.kyzletterSer = kyzletterSer;
	}
	public void setVisaSer(IKyzVisaFlowServices visaSer) {
		this.visaSer = visaSer;
	}
	public void setWebbussletterSer(IWebBussinessletterServices webbussletterSer) {
		this.webbussletterSer = webbussletterSer;
	}
	public void setVisabillmSer(IKyVisabillmServices visabillmSer) {
		this.visabillmSer = visabillmSer;
	}
	public String add(){
		try{
			//如果頁面上選擇了 "其它類" 以外選項時，則在後臺賦值typeNo     20161116
			if(typeNo!=null&&!typeNo.equals("0")){
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
		try{
			/*********************刪除記錄**************************/
			KyzExpectmatmLog log=new KyzExpectmatmLog();
			log.setObj("WebType");
			log.setFactNo(factNo);
			log.setContent(typeNo);
			log.setObj("WebType");
			WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
			log.setUsername(user.getUsername());
			webtypeSer.delete(factNo, typeNo,log);
			List<String>list=kyzexpfileSer.findBillNo(factNo, typeNo);
			for(String billNo:list){
				File file=new File("d:\\KyzexpFile_backup\\"+billNo);
				File file2=new File("d:\\KyzletterexpFile_backup\\"+billNo);
				if(file.exists()){
					GlobalMethod.deletefile(file);//引用下面刪除文件夾方法					
				}
				if(file2.exists()){
					GlobalMethod.deletefile(file2);
				}				
			}
			
		}catch(Exception e){
			System.out.println(e);
		}
						
		return "delete";
	}
	public String findById(){
		webtype=webtypeSer.findById(factNo, typeNo);
		return "findById";
	}
	
	public String recovery(){
		try{
			webtype=webtypeSer.findById(factNo, typeNo);
			webtype.setDelMk("1");//標記刪除
			webtypeSer.add(webtype);
			ajaxResult="0";
		}catch(Exception e){
			System.out.println(e);
			ajaxResult="1";
		}
		return "recovery";
	}
	
	
	public String findPF(){
		try{
			ajaxResult=webtypeSer.findPF(factNo);
		}catch(Exception e){
			ajaxResult="1";
			System.out.println(e);
		}
		return "findPF";
	}
	
	public String findTypes(){
		List<Object[]>list=webtypeSer.findTypes(factNo);
		jsons=JSONArray.fromObject(list);
		return "findTypes";
	}
	
	
	
	
	

}
