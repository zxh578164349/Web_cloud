package action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import mail.MailSenderInfo;
import mail.SimpleMailSender;

import services.IKyVisaBillsServices;
import services.IKyVisabillmServices;
import services.IKyzContactLetterServices;
import services.IKyzExpectmatmFileServices;
import services.IKyzExpectmatmServices;
import services.IKyzExpectmatmsServices;
import services.IKyzVisaFlowServices;
import services.IWebBussinessletterServices;
import services.IWebFactServices;
import services.IWebTypeServices;
import services.IWebUserService;
import services.IWebuserEmailAServices;
import services.IWebuserEmailServices;
import util.JasperHelper;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyVisabillm;
import entity.KyVisabillmId;
import entity.KyVisabills;
import entity.KyVisabillsId;
import entity.KyzContactletter;
import entity.KyzExpectmatm;
import entity.KyzExpectmatmFile;
import entity.KyzExpectmatmId;
import entity.KyzExpectmats;
import entity.KyzVisaflow;
import entity.WebBussinessletter;
import entity.WebType;
import entity.WebUser;
import entity_temp.VisabillsTemp;

public class KyVisaBillmAction extends ActionSupport implements ServletResponseAware{
	private IKyVisabillmServices visabillmSer;
	private IKyVisaBillsServices visabillSer;
	private IKyzExpectmatmServices kyzSer;
	private IWebFactServices webFactSer;
	private IKyzVisaFlowServices visaSer;
	private IKyzContactLetterServices kyzletterSer;
	private IWebBussinessletterServices webbussletterSer;
	private IWebuserEmailServices webuseremailSer;
	private IWebuserEmailAServices webuseremailaSer;
	private PageBean bean;
	private String factNo;
	private String visaSort;
	private int page;
	private KyVisabillm vbm;
	private String billNo;
	private String visa_mk;
	private String itemNo;
	private String memo;//備註修改
	private String visaRank;
	private String visaSigner;
	private Map<String, Object> map;
	private IKyzExpectmatmFileServices kyzexpfileSer;
	private String visaMk;//審核的最終狀態(Y已審核,N未審核,T退回)
	private String userName;
	private javax.servlet.http.HttpServletResponse response;
	private String yymmdd;
	private String yymmdd2;
	private String email;
	private String ajaxResult;//申請函文時返回的ajax結果,   0:提交成功       1:提交失敗
	
	
	public String getAjaxResult() {
		return ajaxResult;
	}
	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getVisaMk() {
		return visaMk;
	}
	public void setVisaMk(String visaMk) {
		this.visaMk = visaMk;
	}
	public void setKyzexpfileSer(IKyzExpectmatmFileServices kyzexpfileSer) {
		this.kyzexpfileSer = kyzexpfileSer;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public String getVisaRank() {
		return visaRank;
	}
	public void setVisaRank(String visaRank) {
		this.visaRank = visaRank;
	}
	public String getVisaSigner() {
		return visaSigner;
	}
	public void setVisaSigner(String visaSigner) {
		this.visaSigner = visaSigner;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getVisa_mk() {
		return visa_mk;
	}
	public void setVisa_mk(String visa_mk) {
		this.visa_mk = visa_mk;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public KyVisabillm getVbm() {
		return vbm;
	}
	public void setVbm(KyVisabillm vbm) {
		this.vbm = vbm;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getFactNo() {
		return factNo;
	}
	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}
	public String getVisaSort() {
		return visaSort;
	}
	public void setVisaSort(String visaSort) {
		this.visaSort = visaSort;
	}
	public PageBean getBean() {
		return bean;
	}
	public void setBean(PageBean bean) {
		this.bean = bean;
	}
	public void setVisabillmSer(IKyVisabillmServices visabillmSer) {
		this.visabillmSer = visabillmSer;
	}
	
	public void setVisabillSer(IKyVisaBillsServices visabillSer) {
		this.visabillSer = visabillSer;
	}
			
	public void setKyzSer(IKyzExpectmatmServices kyzSer) {
		this.kyzSer = kyzSer;
	}
	
	
	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}
	public void setVisaSer(IKyzVisaFlowServices visaSer) {
		this.visaSer = visaSer;
	}
	
	public void setWebuseremailaSer(IWebuserEmailAServices webuseremailaSer) {
		this.webuseremailaSer = webuseremailaSer;
	}	
	public void setWebuseremailSer(IWebuserEmailServices webuseremailSer) {
		this.webuseremailSer = webuseremailSer;
	}
	
	public void setKyzletterSer(IKyzContactLetterServices kyzletterSer) {
		this.kyzletterSer = kyzletterSer;
	}
	
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	public void setWebbussletterSer(IWebBussinessletterServices webbussletterSer) {
		this.webbussletterSer = webbussletterSer;
	}
		
	
	
	
	
	
	/********************************函文审核******************************************/
	public String findPageBean(){
		//ActionContext.getContext().getApplication().clear();
		ActionContext.getContext().getSession().remove("public_factNo");
		ActionContext.getContext().getSession().remove("public_billNo");
		ActionContext.getContext().getSession().remove("public_visaMk");
		ActionContext.getContext().getSession().remove("public_visaSort");
		ActionContext.getContext().getSession().remove("public_timeCreate");
		ActionContext.getContext().getSession().remove("public_timeCreate2");
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		userName=user.getUsername();
		if(user.getEmail()==null){
			email="no";
		}else{
			email=user.getEmail();
		}
		bean=visabillSer.findPageBean_tw(25, page, userName, visaMk, factNo, billNo,visaSort,yymmdd,yymmdd2,email);
		
		this.getKyzTitle(bean);
		this.getTypeName(bean);
		return "beanList";
	}
	public String findPageBean2(){
		//ActionContext.getContext().getApplication().clear();
		ActionContext.getContext().getSession().remove("public_factNo");
		ActionContext.getContext().getSession().remove("public_billNo");
		ActionContext.getContext().getSession().remove("public_visaMk");
		ActionContext.getContext().getSession().remove("public_visaSort");
		ActionContext.getContext().getSession().remove("public_timeCreate");
		ActionContext.getContext().getSession().remove("public_timeCreate2");
		if(factNo!=null&&!factNo.equals("")){
			ActionContext.getContext().getSession().put("public_factNo", factNo);
		}
		if(billNo!=null&&!billNo.equals("")){
			billNo=billNo.trim();
			ActionContext.getContext().getSession().put("public_billNo", billNo);
		}
		if(visaMk!=null&&!visaMk.equals("")){
			ActionContext.getContext().getSession().put("public_visaMk", visaMk);
		}
		if(visaSort!=null&&!visaSort.equals("")){
			ActionContext.getContext().getSession().put("public_visaSort", visaSort);
		}
		if(yymmdd!=null&&!yymmdd.equals("")){
			ActionContext.getContext().getSession().put("public_timeCreate", yymmdd);
		}
		if(yymmdd2!=null&&!yymmdd2.equals("")){
			ActionContext.getContext().getSession().put("public_timeCreate2", yymmdd2);
		}
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		userName=user.getUsername();
		if(user.getEmail()==null){
			email="no";
		}else{
			email=user.getEmail();
		}
		bean=visabillSer.findPageBean_tw(25, page, userName, visaMk, factNo, billNo,visaSort,yymmdd,yymmdd2,email);
		/*List<KyVisabills>list=bean.getList();
		for(int i=0;i<list.size();i++){
			String billNo=list.get(i).getId().getKyVisabillm().getId().getBillNo();
			String title="";
			//判斷費用函文還是內部聯絡函
			if(billNo.substring(0, 2).equals("EM")){
				title=kyzSer.findTitleByBillno(billNo);
			}else{
				title=kyzletterSer.findTitleByBillno(billNo);
			}
			
			if(title!=null){
				list.get(i).setMemo(title);
			}else{
				list.get(i).setMemo("");
			}						
		}*/
		this.getKyzTitle(bean);
		this.getTypeName(bean);
		return "beanList1";		
	}
	public String findPageBean3(){
		factNo=(String)ActionContext.getContext().getSession().get("public_factNo");
		billNo=(String)ActionContext.getContext().getSession().get("public_billNo");
		visaMk=(String)ActionContext.getContext().getSession().get("public_visaMk");
		visaSort=(String)ActionContext.getContext().getSession().get("public_visaSort");
		yymmdd=(String)ActionContext.getContext().getSession().get("public_timeCreate");
		yymmdd2=(String)ActionContext.getContext().getSession().get("public_timeCreate2");
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		userName=user.getUsername();
		if(user.getEmail()==null){
			email="no";
		}else{
			email=user.getEmail();
		}
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		bean=visabillSer.findPageBean_tw(25, page, userName, visaMk, factNo, billNo,visaSort,yymmdd,yymmdd2,email);
		/*List<KyVisabills>list=bean.getList();
		for(int i=0;i<list.size();i++){
			String billNo=list.get(i).getId().getKyVisabillm().getId().getBillNo();
			String title="";
			//判斷費用函文還是內部聯絡函
			if(billNo.substring(0, 2).equals("EM")){
				title=kyzSer.findTitleByBillno(billNo);
			}else{
				title=kyzletterSer.findTitleByBillno(billNo);
			}
			if(title!=null){
				list.get(i).setMemo(title);
			}else{
				list.get(i).setMemo("");
			}						
		}*/
		this.getKyzTitle(bean);
		this.getTypeName(bean);
		return "beanList1";
	}
	/********************************函文审核******************************************/
	
	
	/**
	 * 以下是tw胡艷敏使用
	 * 開始
	 * @return
	 */
	public String findPageBean_tw(){
		ActionContext.getContext().getApplication().clear();
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		userName=user.getUsername();
		if(user.getEmail()==null){
			email="no";
		}else{
			email=user.getEmail();
		}
		bean=visabillSer.findPageBean_tw(25, page, userName, visaMk, factNo, billNo,visaSort,yymmdd,yymmdd2,email);
		/*List<KyVisabills>list=bean.getList();
		for(int i=0;i<list.size();i++){
			String billNo=list.get(i).getId().getKyVisabillm().getId().getBillNo();
			String title="";
			//判斷費用函文還是內部聯絡函
			if(billNo.substring(0, 2).equals("EM")){
				title=kyzSer.findTitleByBillno(billNo);
			}else{
				title=kyzletterSer.findTitleByBillno(billNo);
			}
			if(title!=null){
				list.get(i).setMemo(title);
			}else{
				list.get(i).setMemo("");
			}						
		}*/		
		this.getKyzTitle(bean);
		this.getTypeName(bean);
		return "beanList_tw";
	}
	public String findPageBean2_tw(){
		ActionContext.getContext().getApplication().clear();
		if(factNo!=null&&!factNo.equals("")){
			ActionContext.getContext().getApplication().put("visabills-factno", factNo);
		}
		if(billNo!=null&&!billNo.equals("")){
			billNo=billNo.trim();
			ActionContext.getContext().getApplication().put("visabills-billno", billNo);
		}
		if(visaMk!=null&&!visaMk.equals("")){
			ActionContext.getContext().getApplication().put("visabills-visamk", visaMk);
		}
		if(visaSort!=null&&!visaSort.equals("")){
			ActionContext.getContext().getApplication().put("visabills-visasort", visaSort);
		}
		if(yymmdd!=null&&!yymmdd.equals("")){
			ActionContext.getContext().getApplication().put("visabills-yymmdd", yymmdd);
		}
		if(yymmdd2!=null&&!yymmdd2.equals("")){
			ActionContext.getContext().getApplication().put("visabills-yymmdd2", yymmdd2);
		}
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		userName=user.getUsername();
		if(user.getEmail()==null){
			email="no";
		}else{
			email=user.getEmail();
		}
		bean=visabillSer.findPageBean_tw(25, page, userName, visaMk, factNo, billNo,visaSort,yymmdd,yymmdd2,email);
		/*List<KyVisabills>list=bean.getList();
		for(int i=0;i<list.size();i++){
			String billNo=list.get(i).getId().getKyVisabillm().getId().getBillNo();
			String title="";
			//判斷費用函文還是內部聯絡函
			if(billNo.substring(0, 2).equals("EM")){
				title=kyzSer.findTitleByBillno(billNo);
			}else{
				title=kyzletterSer.findTitleByBillno(billNo);
			}
			if(title!=null){
				list.get(i).setMemo(title);
			}else{
				list.get(i).setMemo("");
			}						
		}*/	
		this.getKyzTitle(bean);
		this.getTypeName(bean);
		return "beanList1_tw";		
	}
	public String findPageBean3_tw(){
		factNo=(String)ActionContext.getContext().getApplication().get("visabills-factno");
		billNo=(String)ActionContext.getContext().getApplication().get("visabills-billno");
		visaMk=(String)ActionContext.getContext().getApplication().get("visabills-visamk");
		visaSort=(String)ActionContext.getContext().getApplication().get("visabills-visasort");
		yymmdd=(String)ActionContext.getContext().getApplication().get("visabills-yymmdd");
		yymmdd2=(String)ActionContext.getContext().getApplication().get("visabills-yymmdd2");
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		userName=user.getUsername();
		if(user.getEmail()==null){
			email="no";
		}else{
			email=user.getEmail();
		}
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		bean=visabillSer.findPageBean_tw(25, page, userName, visaMk, factNo, billNo,visaSort,yymmdd,yymmdd2,email);
		/*List<KyVisabills>list=bean.getList();
		for(int i=0;i<list.size();i++){
			String billNo=list.get(i).getId().getKyVisabillm().getId().getBillNo();
			String title="";
			//判斷費用函文還是內部聯絡函
			if(billNo.substring(0, 2).equals("EM")){
				title=kyzSer.findTitleByBillno(billNo);
			}else{
				title=kyzletterSer.findTitleByBillno(billNo);
			}
			if(title!=null){
				list.get(i).setMemo(title);
			}else{
				list.get(i).setMemo("");
			}						
		}	*/
		this.getKyzTitle(bean);
		this.getTypeName(bean);
		return "beanList1_tw";
	}
	/**
	 * 結束
	 */
	
	/********************************函文审核状况******************************************/
	public String findPageBean_1(){
		//ActionContext.getContext().getApplication().clear();
		ActionContext.getContext().getSession().remove("public_factNo");
		ActionContext.getContext().getSession().remove("public_billNo");
		ActionContext.getContext().getSession().remove("public_visaMk");
		ActionContext.getContext().getSession().remove("public_visaSort");
		ActionContext.getContext().getSession().remove("public_timeCreate");
		ActionContext.getContext().getSession().remove("public_timeCreate2");
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		userName=user.getUsername();
		if(user.getEmail()==null){
			email="no";
		}else{
			email=user.getEmail();
		}
		bean=visabillSer.findPageBean(25, page, userName, visaMk, factNo, billNo,visaSort,yymmdd,yymmdd2,email);
		this.getTypeName(bean);
		this.getKyzTitle(bean);
		return "beanList_1";
	}
	public String findPageBean2_1(){
		//ActionContext.getContext().getApplication().clear();
		ActionContext.getContext().getSession().remove("public_factNo");
		ActionContext.getContext().getSession().remove("public_billNo");
		ActionContext.getContext().getSession().remove("public_visaMk");
		ActionContext.getContext().getSession().remove("public_visaSort");
		ActionContext.getContext().getSession().remove("public_timeCreate");
		ActionContext.getContext().getSession().remove("public_timeCreate2");
		if(factNo!=null&&!factNo.equals("")){
			ActionContext.getContext().getSession().put("public_factNo", factNo);
		}
		if(billNo!=null&&!billNo.equals("")){
			billNo=billNo.trim();
			ActionContext.getContext().getSession().put("public_billNo", billNo);
		}
		if(visaMk!=null&&!visaMk.equals("")){
			ActionContext.getContext().getSession().put("public_visaMk", visaMk);
		}
		if(visaSort!=null&&!visaSort.equals("")){
			ActionContext.getContext().getSession().put("public_visaSort", visaSort);
		}
		if(yymmdd!=null&&!yymmdd.equals("")){
			ActionContext.getContext().getSession().put("public_timeCreate", yymmdd);
		}
		if(yymmdd2!=null&&!yymmdd2.equals("")){
			ActionContext.getContext().getSession().put("public_timeCreate2", yymmdd2);
		}
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		userName=user.getUsername();
		if(user.getEmail()==null){
			email="no";
		}else{
			email=user.getEmail();
		}
		bean=visabillSer.findPageBean(25, page, userName, visaMk, factNo, billNo,visaSort,yymmdd,yymmdd2,email);
		this.getTypeName(bean);
		this.getKyzTitle(bean);
		return "beanList1_1";
	}
	public String findPageBean3_1(){
		factNo=(String)ActionContext.getContext().getSession().get("public_factNo");
		billNo=(String)ActionContext.getContext().getSession().get("public_billNo");
		visaMk=(String)ActionContext.getContext().getSession().get("public_visaMk");
		visaSort=(String)ActionContext.getContext().getSession().get("public_visaSort");
		yymmdd=(String)ActionContext.getContext().getSession().get("public_timeCreate");
		yymmdd2=(String)ActionContext.getContext().getSession().get("public_timeCreate2");
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		userName=user.getUsername();
		if(user.getEmail()==null){
			email="no";
		}else{
			email=user.getEmail();
		}
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		bean=visabillSer.findPageBean(25, page, userName, visaMk, factNo, billNo,visaSort,yymmdd,yymmdd2,email);
		this.getTypeName(bean);
		this.getKyzTitle(bean);
		return "beanList1_1";
	}
	/********************************函文审核状况******************************************/
	
	
	
	public String findById(){//審核
		vbm=visabillmSer.findById(factNo, visaSort, billNo);
		ActionContext.getContext().getSession().put("vbm", vbm);//爲了在函文簽核的彈出窗口顯示已簽人的備註信息
		return "findById";
	}
	public String findById2(){//加簽
		vbm=visabillmSer.findById(factNo, visaSort, billNo);
		return "findById2";
	}
	public String findById3(){
		vbm=visabillmSer.findById(factNo, visaSort, billNo);
		return "findById3";
	}
	public String findById4(){//減簽
		vbm=visabillmSer.findById(factNo, visaSort, billNo);
		return "findById4";
	}
	public String findById5(){//減簽(帶刪除)
		vbm=visabillmSer.findById(factNo, visaSort, billNo);
		return "findById5";
	}
	public String findById_tw(){//審核(臺灣)
		vbm=visabillmSer.findById(factNo, visaSort, billNo);
		return "findById_tw";
	}
	public String findById2_tw(){//加簽(臺灣)
		vbm=visabillmSer.findById(factNo, visaSort, billNo);
		return "findById2_tw";
	}
	public String findById4_tw(){//減簽(臺灣)
		vbm=visabillmSer.findById(factNo, visaSort, billNo);
		return "findById4_tw";
	}
	public String findById_email(){//email直接審核，不需要登錄帳號（手機平板）
		vbm=visabillmSer.findById(factNo, visaSort, billNo);
		//ActionContext.getContext().getSession().put("Email", email);//用於判斷審核完後頁面的跳轉（在this.remark()方法最後字符串返回）
		ActionContext.getContext().getSession().put("vbm", vbm);//爲了在函文簽核的彈出窗口顯示已簽人的備註信息
		return "findById_email";
	}
	public String findById_email2(){//email直接審核，不需要登錄帳號（普通電腦）findById_email2
		vbm=visabillmSer.findById(factNo, visaSort, billNo);
		//ActionContext.getContext().getSession().put("Email", email);//用於判斷審核完後頁面的跳轉（在this.remark()方法最後字符串返回）
		ActionContext.getContext().getSession().put("vbm", vbm);//爲了在函文簽核的彈出窗口顯示已簽人的備註信息
		return "findById_email2";
	}
	
	/**
	 * 審核
	 * 審核之後就是備註修改
	 * @return
	 */
		
	public void add() throws IOException{
		KyVisabillm vbm=visabillmSer.findById(factNo, visaSort, billNo);
		if(itemNo==null||itemNo.equals("")){
			itemNo=vbm.getItemNext();
		}
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd_hh");
		int num_temp=Integer.parseInt(itemNo);//把項次轉化為數字
		int num=Integer.parseInt(itemNo)-1;//用於識別KyVisabills集合裡的每幾個對象									
		String next_singer="";
		int num_next=0;		
		KyVisabills vbs=vbm.getKyVisabillses().get(num);
		/**
		 * 如果最後一個不要審核,則要去掉(也就是總長度-1)（現在，>=1000的最後三位都不要審核，則總長度-3      20150803）
		 */
		int vbs_size=vbm.getKyVisabillses().size();	
		int nos=visabillSer.findBillsWithNo(visaSort, billNo);
		if(nos>0){
			vbs_size=vbs_size-nos;
		}				
		String last_singer=vbm.getKyVisabillses().get(num).getVisaSigner();								
		if(visa_mk.equals("Y")){//start if
			//當最後一個審核人時
			
			if((num+1)==vbs_size){
				vbm.setVisaMk(visa_mk);
				next_singer=vbm.getKyVisabillses().get(num).getVisaSigner();
				num_next=num_temp;
			}else{
				next_singer=vbm.getKyVisabillses().get(num+1).getVisaSigner();
				num_next=num_temp+1;
			}
		}//end if
		if(visa_mk.equals("T")){//start if2
			vbm.setVisaMk(visa_mk);
			num_next=1; //退回第一項次
			next_singer=vbm.getKyVisabillses().get(0).getVisaSigner();//退回第一個Email											
		}// end if2
		vbs.setDateVisa(format.format(new Date()));
		vbs.setVisaMk(visa_mk);//子表的當前狀態
		vbm.setSignerLast(last_singer);//當前人Email
		vbm.setSignerNext(next_singer);//下一個人Email
		vbm.setLastMk(visa_mk);        //當前狀態  
		String item_next=""; 
		if(num_next<10){
			item_next="0"+num_next;
		}else{
			item_next=num_next+"";
		}
		vbm.setItemLast(itemNo);       //當前項次
		vbm.setItemNext(item_next);	   //下一項次	
				
		//標記當前審核人的信息,並保存在session中,用於備註修改				
		/******************************remark***********************************/										
				String dateVisa=format.format(new Date());
				vbs.setDateVisa(dateVisa);
				vbs.setMemo(memo);
				String lastmk=vbm.getLastMk();
				
				if(lastmk.equals("Y")){										
					if(num_temp==vbm.getKyVisabillses().size()){
						vbm.setMemoMk(memo);							
					}
				}
				if(lastmk.equals("T")){
					vbm.setMemoMk(memo);																			
				}		
				visabillmSer.add(vbm);								
		/******************************remark***********************************/
	}
	
	/**
	 * 手機平板返回(bootstrap)
	 * @return
	 * @throws IOException
	 */
	public String add2() throws IOException{
		this.add();
		return "success";
	}
	
	/**
	 * 電腦返回
	 * (好像暫無使用20160112)
	 * @throws IOException 
	 */
	public String add3() throws IOException{
		this.add();
		return "beanList";
	}
		
	
	/**
	 * 加簽
	 */
	
	public String addvisabills(){
		try{
			KyVisabillm vbm=new KyVisabillm();
			vbm=visabillmSer.findById(factNo, visaSort, billNo);
			List<KyVisabills>list=vbm.getKyVisabillses();
			//List<KyVisabills>list_temp=vbm.getKyVisabillses();
			int startnum=Integer.parseInt(itemNo);
			String nextnum="";
			if((startnum+1)>9){
				nextnum=(startnum+1)+"";
			}else{
				nextnum="0"+(startnum+1);
			}
			KyVisabills vbs=new KyVisabills();
			KyVisabillsId id=new KyVisabillsId();
			
			KyVisabillm kyvbm=new KyVisabillm();
			KyVisabillmId vbmid=new KyVisabillmId();
			
			vbmid.setBillNo(billNo);
			vbmid.setFactNo(factNo);
			vbmid.setVisaSort(visaSort);
			kyvbm.setId(vbmid);
			id.setKyVisabillm(kyvbm);
			id.setItemNo(nextnum);
			vbs.setId(id);
			vbs.setVisaMk("N");
			vbs.setFlowMk("Y");
			if(visaRank!=null&&!visaRank.equals("")){
				vbs.setVisaRank(visaRank.trim());
			}
			if(visaSigner!=null&&!visaSigner.equals("")){
				vbs.setVisaSigner(visaSigner.trim());
			}	
			if(startnum<list.size()){
				for(int i=startnum;i<list.size();i++){
					String itemno=list.get(i).getId().getItemNo();
					String next_itemno="";
					int itemno_num=Integer.parseInt(itemno);
					if((itemno_num+1)>9){
						next_itemno=(itemno_num+1)+"";
					}else{
						next_itemno="0"+(itemno_num+1);
					}
					list.get(i).getId().setItemNo(next_itemno);
				}
			}	
			list.add(startnum,vbs);
			vbm.setKyVisabillses(list);
			visabillmSer.add(vbm);
			ajaxResult="0";
		}catch(Exception e){
			ajaxResult="1";
			System.out.println(e);
		}											
		return "addvisabills";
	}
	
	/**
	 * 減簽（不刪除）（湘威JW專用）
	 * @Title: minusvisabills
	 * @Description: TODO
	 * @param @return
	 * @param @throws IOException
	 * @return String
	 * @throws
	 * @author web
	 * @date 2016/3/11
	 */
	
	public String minusvisabills() throws IOException{
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		KyVisabillm vbm=new KyVisabillm();
		vbm=visabillmSer.findById(factNo, visaSort, billNo);
		List<KyVisabills>list=vbm.getKyVisabillses();
		int startnum=Integer.parseInt(itemNo);
		/**
		 * 如果最後一個不要審核,則要去掉(也就是總長度-1)
		 */
		int vbs_list=list.size();
		int nos=visabillSer.findBillsWithNo(visaSort, billNo);
		if(nos>0){
			vbs_list=vbs_list-nos;
		}
						
		for(int i=startnum;i>0;i--){
			KyVisabills bills=list.get(i-1);
			bills.setVisaMk("Y");
			String datevisa=bills.getDateVisa();
			if(datevisa==null||datevisa.equals("")){
				bills.setDateVisa(format.format(new Date()));
			}
		}
		int nextnum=0;
		if(startnum==vbs_list){
			nextnum=startnum;
		}else{
			nextnum=startnum+1;
		}
		String nextnumstr="";
		if(nextnum<10){
			nextnumstr="0"+nextnum;
		}else{
			nextnumstr=""+nextnum;
		}
		KyVisabills vbs_first=visabillSer.findById(factNo, visaSort, billNo, itemNo);
		KyVisabills vbs_next=visabillSer.findById(factNo, visaSort, billNo, nextnumstr);
		String singernext=vbs_next.getVisaSigner();
		String singerlast=vbs_first.getVisaSigner();
		String itemnext=vbs_next.getId().getItemNo();
		String itemlast=vbs_first.getId().getItemNo();
		
		String flowMknext=vbs_next.getFlowMk();//標識下一位是否為"知會"的
		
		vbm.setSignerNext(singernext);
		vbm.setSignerLast(singerlast);
		vbm.setItemNext(itemnext);
		vbm.setItemLast(itemlast);
		vbm.setLastMk("Y");
		if(startnum==vbs_list){
			vbm.setVisaMk("Y");
		}
		//visabillmSer.add(vbm);
						
		//这个类主要来发送邮件  
		SimpleMailSender sms = new SimpleMailSender();
		//这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo(); 
		
		/**
		 * 發郵件到當前減簽的下一位審核人
		 * 如果當前減簽人不是最後一個審核人,則要發送郵件審核通知
		 */  
		if(startnum!=vbs_list&&flowMknext.equals("Y")){//start if	
			String emailUrl_in="http://203.85.73.161/Login/vbm_findById_email?visaSort="+vbm.getId().getVisaSort()+"& billNo="+billNo
			         +"& factNo="+vbm.getId().getFactNo()+"& email="+singernext;			
	      mailInfo.setValidate(true);    	       
	      mailInfo.setToAddress(singernext);    
	      mailInfo.setSubject("函文減簽(下一位審核)_"+billNo+"("+factNo+")");    
	      mailInfo.setContent(
	    		"函文單號:"+"<span style='color:red'>"+billNo+"</span>"+"&nbsp;&nbsp;廠別:"+factNo+
	    		"<br/>點擊單號直接審核:<a href='"+emailUrl_in+"'>"+billNo+"</a>"+				
	    		"如需查詢以往單據請登錄加久網站:((云端))<a href='http://203.85.73.161/Login'>http://203.85.73.161/Login</a>" +	            
	      		"<br/>進入[KPI數據]--[函文審核]中查找對應單號審核,"+	      	    		
	    		"<hr/>"+	      		
	    		"<br/>本郵件自動發送,請勿回復!如需回復或者問題，請回复到kyinfo.lp@yydg.com.cn劉平!<br/>" +
	    		"<hr/>");    	       	      
	      sms.sendHtmlMail(mailInfo);
	      /**
	       * 給當前減簽人前面沒有審核的人發郵件(包括當前減簽人)
	       */
	      for(int i=startnum;i>0;i--){
	    	  //注意:要重新從數據庫中查找vbm,因為前面已經把vbm中KyVisabills的visaMk Set為Y了,所以不能再使用前面的vbm,但還沒有保存到數據庫
	    	  KyVisabillm vbm2=visabillmSer.findById(factNo, visaSort, billNo);
	    	  List<KyVisabills>list2=vbm2.getKyVisabillses();
	    	  KyVisabills bills=list2.get(i-1);
	    	  String visamk=bills.getVisaMk();	    	 
	    	  String visaSigner=bills.getVisaSigner();
	    	  //當前減簽的人,是當前登錄者
	    	  WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
	    	  String userName=user.getUsername();
	    	  if(visamk.equals("N")){
	    		  SimpleMailSender sms_n=new SimpleMailSender();
	    		  MailSenderInfo mailInfo_n=new MailSenderInfo();
	    		  mailInfo_n.setValidate(true);	    		
	    		  mailInfo_n.setToAddress(visaSigner);
	    		  mailInfo_n.setSubject("函文減簽通知_"+billNo+"("+factNo+")");
	    		  mailInfo_n.setContent("你好,單號為:"+"<span style='color:red'>"+billNo+"</span>"+"的函文已由用戶:"
	    		                         +"<span style='color:blue'>"+userName+"</span>"+"減簽,收到該郵件的人,不需要審核此單號的函文.");
	    		  sms_n.sendHtmlMail(mailInfo_n);
	    	  }
	      }
	      }//end if1
			     	          	          	         
	          /**
	           * 注意,函文要放到最後保存
	           */
	          visabillmSer.add(vbm);
	          
	          /**
	           * 注意,這裡要放在visabillmSer.add(vbm)之後
	  		 * 如果是最後一個,則要給所有人發送郵件(相當於知會)
	  		 */
	  		if(startnum==vbs_list){
	  			//判斷是費用函文還是內部聯絡函
	  			if(billNo.substring(0, 2).equals("EM")){
	  				this.addVisabillsAndEmail(factNo, billNo, visaSort,vbm);
	  			}else{
	  				this.addVisabillsAndEmail2(factNo, billNo, visaSort,vbm);
	  			}
	  			
	  		}
	         	  		
		return "minusvisabills";
	}
	
	
	
	
	
	
	/**
	 * 費用函文
	 * @param local_factNo
	 * @param local_billNo
	 * @param local_visaSort
	 * @throws IOException
	 */
	public void addVisabillsAndEmail(String local_factNo,String local_billNo,String local_visaSort,KyVisabillm vbm) throws IOException{
		
		/**
		 * 打印
		 */				
		/*KyzExpectmatmId kyzid=new KyzExpectmatmId();
		kyzid.setBillNo(local_billNo);
		kyzid.setFactNo(local_factNo);
		String factname=webFactSer.selByid(kyzid.getFactNo());
		String factCode="";
		List<KyzExpectmatm> listkyz=kyzSer.findById_Print(kyzid);
		if(listkyz.size()==0){
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>alert('單號為"+billNo+"的函文不存在!');window.close()</script>");
			return null;
		}
		factCode=listkyz.get(0).getFactCode();
		String result=factname+"("+factCode+")"+"費用申請單";
		map = new HashMap<String, Object>();
		map.put("SUBREPORT_DIR",ServletActionContext.getRequest().getRealPath("/jasper/audit/")+ "/");
		map.put("pic", ServletActionContext.getRequest().getRealPath("/jasper/audit/images/")+ "/");//圖片路徑
		map.put("pfactno", kyzid.getFactNo());
		map.put("pbillno",kyzid.getBillNo());
		map.put("title",result);
		List<KyzExpectmats> sub_list = new ArrayList<KyzExpectmats>();
		
		KyzExpectmats temp=new KyzExpectmats();
		for(int i=0;i<listkyz.get(0).getKyzExpectmatses().size();i++){
			KyzExpectmats kyzs=listkyz.get(0).getKyzExpectmatses().get(i);
			sub_list.add(kyzs);
		}
		while(sub_list.size()<10){
			sub_list.add(temp);
		}
		Map sub_map=new HashMap<String,Object>();
		sub_map.put("sub_list", sub_list);
		
		String type=list.get(0).getVisaType();
		List<KyzVisaflow> list_visa=visaSer.findByType(type);
		
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		KyVisabillm vbm=visabillmSer.findById(local_factNo, local_visaSort, local_billNo);
		List<KyVisabills>list_visa=vbm.getKyVisabillses();		
		List<KyzVisaflow>list_visaflow=visaSer.findByType(local_factNo,local_visaSort);
		
		*//**
		 * 如果最後不用審核,則去掉
		 *//*
		int nos=visabillSer.findBillsWithNo(local_visaSort, local_billNo);
		if(nos>0){
			for(int i=0;i<nos;i++){
				list_visa.remove(list_visa.size()-1);
				list_visaflow.remove(list_visaflow.size()-1);
			}
		}
		
		List<VisabillsTemp>list_visabillstemp=new ArrayList();		
		for(int i=0;i<list_visa.size();i++){
			VisabillsTemp visabillstemp=new VisabillsTemp();
			String visa_result="";
			String visamk_temp="";
			Date date=null;			
			String datestr=list_visa.get(i).getDateVisa();
			try {
				if(datestr!=null){
					date=format.parse(datestr);
					visabillstemp.setCreateDate(date);
				}
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String name=list_visa.get(i).getVisaRank();
			String visamk=list_visa.get(i).getVisaMk();
			String visadate=list_visa.get(i).getDateVisa();
			String memo=list_visa.get(i).getMemo();
			if(visamk.equals("Y")){
				visamk_temp="(已審核)";
			}
			if(visamk.equals("N")){
				visamk_temp="(未審核)";
			}
			if(visamk.equals("T")){
				visamk_temp="(未通過)";
			}			
			visa_result=name+visamk_temp;
			visabillstemp.setVisaNameAndMk(visa_result);
			if(list_visa.size()==list_visaflow.size()){
				String visaRank=list_visaflow.get(i).getVisaRank();
				visabillstemp.setVisaRank(visaRank+":");
			}
			
			if(memo!=null){
				visabillstemp.setMemo("(備註:"+memo+")");
			}
			visabillstemp.setVisaSigner(list_visa.get(i).getVisaSigner());
			visabillstemp.setVisaMk(list_visa.get(i).getVisaMk());
			visabillstemp.setVisaName(name);
			list_visabillstemp.add(visabillstemp);
		}
						
		Map visa_map=new HashMap<String,Object>();
		visa_map.put("list_visa", list_visabillstemp);
		
		map.put("sub_map", sub_map);
		map.put("visa_map", visa_map);
		Map main_map=new HashMap<String,Object>();    把list（List<KyzExpectmatm> list=kyzSer.findById_Print(id)）放在一个子表,便于打印  20150804
		main_map.put("list_main", listkyz);
		map.put("main_map", main_map);
		
		函文附檔
		//String pic_file=ServletActionContext.getRequest().getRealPath("/KyzexpFile/"+kyzid.getBillNo()+"/")+"/";//函文附檔圖片路徑
		String pic_file=new File("d:\\KyzexpFile_backup\\"+kyzid.getBillNo()).toString();//函文附檔圖片路徑(附檔在D盤的路徑)
		List<KyzExpectmatmFile>list_kyzexpfile=kyzexpfileSer.findByBillNo(kyzid.getBillNo());
		if(pic_file!=null&&list_kyzexpfile.size()>0){
			map.put("pic_file", pic_file+"\\");
			Map file_map=new HashMap<String,Object>();
			file_map.put("list_kyzexpfile", list_kyzexpfile);
			map.put("file_map", file_map);
		}*/
		
		Map<String,Object>map_result=kyzSer.print(local_factNo, local_billNo,local_visaSort,vbm);
		if(map_result!=null&&map_result.size()>0){
			map=(Map<String,Object>)map_result.get("map");
			List<KyzExpectmatm>listkyz=(List<KyzExpectmatm>)map_result.get("list");
			JasperHelper.exportmain("auto", map,"matterApplication.jasper", listkyz,local_billNo, "jasper/audit/");
		}
		this.sendEmail(local_factNo, local_billNo, local_visaSort, vbm);
		
		/**
		 * 發郵件
		 * 由於要給所有人(包括不要審核的)發送郵件,所以要重新從數據庫中獲取,而不能使用上面已有的list_visa
		 */
		      
		/*KyVisabillm vbm2=visabillmSer.findById(local_factNo, local_visaSort, local_billNo);
		List<KyVisabills>list_visa2=vbm2.getKyVisabillses();
		//这个类主要是设置邮件   
		
		String[] attachFileNames = { "d:/" + local_billNo + ".pdf" };// 附件
		for (int i = 0; i < list_visa2.size(); i++) {
			MailSenderInfo mailInfo = new MailSenderInfo();
			// 这个类主要来发送邮件
			SimpleMailSender sms = new SimpleMailSender();
			mailInfo.setValidate(true);
			mailInfo.setUserName("kyuen@yydg.com.cn");
			mailInfo.setPassword("yydgmail");// 您的邮箱密码
			mailInfo.setFromAddress("<kyuen@yydg.com.cn>");
			mailInfo.setSubject("函文知會(審核完畢)_" + local_billNo + "("
					+ local_factNo + ")");

			//String[] attachFileNames = { "d:/" + local_billNo + ".pdf" };
			mailInfo.setAttachFileNames(attachFileNames);
			mailInfo.setContent("單號為:" + "<span style='color:red'>"
					+ local_billNo + "</span>" + "的函文已審核完畢,請查看附件.");

			String toAddress = list_visa2.get(i).getVisaSigner();
			mailInfo.setToAddress(toAddress);
			sms.sendHtmlMail(mailInfo);// 发送html格式

			// 给备签人发送邮件
			 *//******************20151113备签人请使用方法findByFactNoAEmailPwd2(String factNo,String email)**********************//*
			//String emailPwd = webuseremailSer.findEmailPWD(local_factNo,list_visa2.get(i).getVisaSigner());
			String emailPwd="";
			if (emailPwd != null&&!emailPwd.equals("")) {
				MailSenderInfo mailInfo2 = new MailSenderInfo();
				// 这个类主要来发送邮件
				SimpleMailSender sms2 = new SimpleMailSender();
				mailInfo2.setValidate(true);
				mailInfo2.setUserName("kyuen@yydg.com.cn");
				mailInfo2.setPassword("yydgmail");// 您的邮箱密码
				mailInfo2.setFromAddress("<kyuen@yydg.com.cn>");
				mailInfo2.setSubject("函文知會(審核完畢)_" + local_billNo + "("
						+ local_factNo + ")");
				mailInfo2.setAttachFileNames(attachFileNames);
				mailInfo2.setContent("單號為:" + "<span style='color:red'>"
						+ local_billNo + "</span>" + "的函文已審核完畢,請查看附件.");

				mailInfo2.setToAddress(emailPwd);
				sms2.sendHtmlMail(mailInfo2);// 发送html格式

			}

		}
	       
	          File file = new File("d:/" + local_billNo + ".pdf");
				if (file.exists()) {
					if (file.isFile()) {
						file.delete();
					}
				}*/
		
	}
	
	/**
	 * 內部聯絡函
	 * @param local_factNo
	 * @param local_billNo
	 * @param local_visaSort
	 * @throws IOException
	 */
	public void addVisabillsAndEmail2(String local_factNo,String local_billNo,String local_visaSort,KyVisabillm vbm) throws IOException{
		/*List<KyzContactletter>list=new ArrayList<KyzContactletter>();
		Map<String,Object>map=new HashMap<String,Object>();
		String factname=webFactSer.selByid(local_factNo);
		String factCode="";
		KyzContactletter letter=kyzletterSer.findById(local_factNo,local_billNo);
		if(letter==null){
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>alert('單號為"+local_billNo+"的函文不存在!');window.close()</script>");
			return null;
		}else{
			list.add(letter);
		}	
		list.add(letter);
		factCode=letter.getFactCode();
		String result=factname+"("+factCode+")"+"內部聯絡函";
		//map = new HashMap<String, Object>();
		map.put("SUBREPORT_DIR",ServletActionContext.getRequest().getRealPath("/jasper/audit/")+ "/");
		map.put("pic", ServletActionContext.getRequest().getRealPath("/jasper/audit/images/")+ "/");//圖片路徑		
		map.put("pfactno", local_factNo);
		map.put("pbillno",local_billNo);
		map.put("title",result);
		List<KyzExpectmats> sub_list = new ArrayList<KyzExpectmats>();		
		KyzExpectmats temp=new KyzExpectmats();		
				
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		KyVisabillm vbm=visabillmSer.findById(local_factNo, local_visaSort, local_billNo);
		List<KyVisabills>list_visa=vbm.getKyVisabillses();
		List<KyzVisaflow>list_visaflow=visaSer.findByType(local_factNo,local_visaSort);
		int num1=list_visa.size();
		int num2=list_visaflow.size();
		*//**
		 * 最後個不用審核的,就去掉
		 *//*
		int nos=visabillSer.findBillsWithNo(local_visaSort, local_billNo);
		if(nos>0){
			for(int i=0;i<nos;i++){
				list_visa.remove(list_visa.size()-1);
				list_visaflow.remove(list_visaflow.size()-1);
			}
		}		
		
		List<VisabillsTemp>list_visabillstemp=new ArrayList();		
		for(int i=0;i<list_visa.size();i++){
			VisabillsTemp visabillstemp=new VisabillsTemp();
			String visa_result="";
			String visamk_temp="";
			Date date=null;
			
			String datestr=list_visa.get(i).getDateVisa();
			try {
				if(datestr!=null){
					date=format.parse(datestr);
					visabillstemp.setCreateDate(date);
				}
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String name=list_visa.get(i).getVisaRank();
			String visamk=list_visa.get(i).getVisaMk();
			String visadate=list_visa.get(i).getDateVisa();
			String memo=list_visa.get(i).getMemo();
			if(visamk.equals("Y")){
				visamk_temp="(已審核)";
			}
			if(visamk.equals("N")){
				visamk_temp="(未審核)";
			}
			if(visamk.equals("T")){
				visamk_temp="(未通過)";
			}			
			visa_result=name+visamk_temp;
			visabillstemp.setVisaNameAndMk(visa_result);			
			if(list_visa.size()==list_visaflow.size()){
				String visaRank=list_visaflow.get(i).getVisaRank();
				visabillstemp.setVisaRank(visaRank+":");
			}
			if(memo!=null){
				visabillstemp.setMemo("(備註:"+memo+")");
			}
			visabillstemp.setVisaSigner(list_visa.get(i).getVisaSigner());
			visabillstemp.setVisaMk(list_visa.get(i).getVisaMk());
			visabillstemp.setVisaName(name);
			list_visabillstemp.add(visabillstemp);
		}
		
		
		
		Map visa_map=new HashMap<String,Object>();
		visa_map.put("list_visa", list_visabillstemp);
		
		map.put("visa_map", visa_map);
		Map main_map=new HashMap<String,Object>();    把list（List<KyzExpectmatm> list=kyzSer.findById_Print(id)）放在一个子表,便于打印  20150804
		main_map.put("list_main", list);
		map.put("main_map", main_map);*/
		
		Map<String,Object>map_result=kyzletterSer.print(local_factNo, local_billNo, local_visaSort,vbm);
		if(map_result!=null&&map_result.size()>0){
			map=(Map<String,Object>)map_result.get("map");
			List<KyzContactletter>list=(List<KyzContactletter>)map_result.get("list");
			JasperHelper.exportmain("auto", map,"kyz_contactletter.jasper", list,local_billNo, "jasper/audit/");
		}
		this.sendEmail(local_factNo, local_billNo, local_visaSort, vbm);
		
		/**
		 * 發郵件
		 * 由於要給所有人(包括不要審核的)發送郵件,所以要重新從數據庫中獲取,而不能使用上面已有的list_visa
		 */
		      
		/*KyVisabillm vbm2=visabillmSer.findById(local_factNo, local_visaSort, local_billNo);
		List<KyVisabills>list_visa2=vbm2.getKyVisabillses();
		//这个类主要是设置邮件   
		String[] attachFileNames = { "d:/" + local_billNo + ".pdf" };// 附件
		for (int i = 0; i < list_visa2.size(); i++) {
			MailSenderInfo mailInfo = new MailSenderInfo();
			// 这个类主要来发送邮件
			SimpleMailSender sms = new SimpleMailSender();
			mailInfo.setValidate(true);
			mailInfo.setUserName("kyuen@yydg.com.cn");
			mailInfo.setPassword("yydgmail");// 您的邮箱密码
			mailInfo.setFromAddress("<kyuen@yydg.com.cn>");
			mailInfo.setSubject("函文知會(審核完畢)_" + local_billNo + "("
					+ local_factNo + ")");

			// String[] attachFileNames={"d:/"+local_billNo+".pdf"};
			mailInfo.setAttachFileNames(attachFileNames);
			mailInfo.setContent("單號為:" + "<span style='color:red'>"
					+ local_billNo + "</span>" + "的函文已審核完畢,請查看附件.");

			String toAddress = list_visa2.get(i).getVisaSigner();
			mailInfo.setToAddress(toAddress);
			sms.sendHtmlMail(mailInfo);// 发送html格式
			
			// 给备签人发送邮件
			 *//******************20151113备签人请使用方法findByFactNoAEmailPwd2(String factNo,String email)**********************//*
			//String emailPwd = webuseremailSer.findEmailPWD(local_factNo,list_visa2.get(i).getVisaSigner());
			String emailPwd="";
			if(emailPwd!=null&&!emailPwd.equals("")){
				MailSenderInfo mailInfo2 = new MailSenderInfo();
				// 这个类主要来发送邮件
				SimpleMailSender sms2 = new SimpleMailSender();
				mailInfo2.setValidate(true);				
				mailInfo2.setSubject("函文知會(審核完畢)_" + local_billNo + "("
						+ local_factNo + ")");
				mailInfo2.setAttachFileNames(attachFileNames);
				mailInfo2.setContent("單號為:" + "<span style='color:red'>"
						+ local_billNo + "</span>" + "的函文已審核完畢,請查看附件.");

				mailInfo2.setToAddress(emailPwd);
				sms2.sendHtmlMail(mailInfo2);// 发送html格式
			}
		}
	       
	          File file = new File("d:/" + local_billNo + ".pdf");
				if (file.exists()) {
					if (file.isFile()) {
						file.delete();
					}
				}*/
								
}
	
	/**
	 * 人員出差函文
	 * @Title: addVisabillsAndEmail3
	 * @Description: TODO
	 * @param @param local_factNo
	 * @param @param local_billNo
	 * @param @param local_visaSort
	 * @param @param vbm
	 * @param @throws IOException
	 * @return void
	 * @throws
	 * @author web
	 * @date 2016/3/11
	 */
	public void addVisabillsAndEmail3(String local_factNo,String local_billNo,String local_visaSort,KyVisabillm vbm) throws IOException{
		Map<String,Object>map_result=webbussletterSer.print(factNo, billNo, visaSort,vbm);
		if(map_result!=null&&map_result.size()>0){
			map=(Map<String,Object>)map_result.get("map");
			List<WebBussinessletter>list=(List<WebBussinessletter>)map_result.get("list");
			JasperHelper.exportmain("auto", map,"webbussletter.jasper", list,billNo, "jasper/audit/");
		}
		this.sendEmail(local_factNo, local_billNo, local_visaSort, vbm);
	}
	
	
	
	/**
	 * 複製文件
	 * @param sourceFile
	 * @param targetFile
	 * @throws IOException
	 */
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }
    
    /**
     * 複製文件夾
     * @param sourceDir
     * @param targetDir
     * @throws IOException
     */
    public static void copyDirectiory(String sourceDir, String targetDir) throws IOException {
        // 新建目标目录
        (new File(targetDir)).mkdirs();
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(sourceDir)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                // 源文件
                File sourceFile = file[i];
                // 目标文件
                File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + file[i].getName());
                copyFile(sourceFile, targetFile);
            }
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹
                String dir1 = sourceDir + "/" + file[i].getName();
                // 准备复制的目标文件夹
                String dir2 = targetDir + "/" + file[i].getName();
                copyDirectiory(dir1, dir2);
            }
        }
    }
    
    public String formatDate(String date) throws ParseException{
    	SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd");
    	SimpleDateFormat format2=new SimpleDateFormat("yyyyMMdd");
    	String result=format.format(format2.parse(date));
    	return result;
    }
    public void test() throws IOException{
    	ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "spring-action.xml", "spring-dao.xml",
						"spring.xml", "spring-services.xml" });
    	KyzExpcetmatmAction kyzexp=new KyzExpcetmatmAction();
    	KyzExpectmatmId id=new KyzExpectmatmId(); 
    	id.setBillNo("EMXW15050505");
    	id.setFactNo("XW");
    	//kyzexp.print(id, "C21");

    }
    
	/*public void getKyzTitle(PageBean bean){
		List<KyVisabills>list=bean.getList();
		for(int i=0;i<list.size();i++){
			String billNo=list.get(i).getId().getKyVisabillm().getId().getBillNo();
			String title="";
			//判斷費用函文還是內部聯絡函
			if(billNo.substring(0, 2).equals("EM")){
				title=kyzSer.findTitleByBillno(billNo);
			}else{
				title=kyzletterSer.findTitleByBillno(billNo);
			}
			if(title!=null){
				list.get(i).setMemo(title);
			}else{
				list.get(i).setMemo("");
			}						
		}
	}*/
	public void getKyzTitle(PageBean bean){
		List<KyVisabills>list=bean.getList();
		List<Object[]>list_kyz=null;
		List<Object[]>list_letter=null;
		//list_kyz=(List<Object[]>)ActionContext.getContext().getSession().get("list_kyz");
		//list_letter=(List<Object[]>)ActionContext.getContext().getSession().get("list_letter");
		String title="";
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				String billNo=list.get(i).getId().getKyVisabillm().getId().getBillNo();
				if(billNo.substring(0,2).equals("EM")){
					list_kyz=kyzSer.findTitle(factNo);
					//ActionContext.getContext().getSession().put("list_kyz", list_kyz);
					break;
				}
			}
			for(int i=0;i<list.size();i++){
				String billNo=list.get(i).getId().getKyVisabillm().getId().getBillNo();
				if(billNo.substring(0,2).equals("CM")){
					list_letter=kyzletterSer.findTitle(factNo);
					//ActionContext.getContext().getSession().put("list_letter", list_letter);
					break;
				}
			}
			for(int i=0;i<list.size();i++){//for
				
				String billNo=list.get(i).getId().getKyVisabillm().getId().getBillNo();
				if(billNo.substring(0, 2).equals("EM")&&list_kyz!=null){
					for(int j=0;j<list_kyz.size();j++){
						if(billNo.equals((String)list_kyz.get(j)[1])){
							title=(String)list_kyz.get(j)[2];
							list.get(i).setMemo(title);
							break;
						}
					}
				}
				if(billNo.substring(0,2).equals("CM")&&list_letter!=null){
					for(int j=0;j<list_letter.size();j++){
						if(billNo.equals((String)list_letter.get(j)[1])){
							title=(String)list_letter.get(j)[2];
							list.get(i).setMemo(title);
							break;
						}
					}
				}
				//list.get(i).setMemo(title);
			}//for
		}
	}
	
	public void getTypeName(PageBean bean){
		List<KyVisabills>list=bean.getList();
		List<WebType>list_type=(List<WebType>)ActionContext.getContext().getSession().get("list_webtype");/********20151029登錄時已經記錄**************/
		for(int i=0;i<list.size();i++){//for1
			KyVisabills vbs=list.get(i);
			String factno=vbs.getId().getKyVisabillm().getId().getFactNo();
			String visaSort=vbs.getId().getKyVisabillm().getId().getVisaSort();			
			String typename=visaSort;			
			//typename=webtypeSer.findTypeNameById(factno, visaSort.substring(0, 2));
			if(list_type!=null&&list_type.size()>0){
				for(int j=0;j<list_type.size();j++){//for2
					WebType type=list_type.get(j);
					if(factno.equals(type.getId().getFactNo())&&visaSort.substring(0,2).equals(type.getId().getTypeNo())){
						typename=type.getTypeName();					
						break;
					}
				}//for2
			}			
			vbs.getId().getKyVisabillm().setColTemp(typename);
		}//for1				
	}
	
	/**
	 * 減簽（直接刪除）
	 * @Title: minusvisabills2
	 * @Description: TODO
	 * @param @return
	 * @param @throws IOException
	 * @return String
	 * @throws
	 * @author web
	 * @date 2016/3/11
	 */
	public String minusvisabills2() throws IOException{
		try{
			KyVisabillm vbm=visabillmSer.findById(factNo, visaSort, billNo);		
			KyVisabills vbs=visabillSer.findById(factNo, visaSort, billNo, itemNo);
			
			String visaNext=vbm.getSignerNext();
			String singer=vbs.getVisaSigner();
			String flowMk=vbs.getFlowMk();
			int index=Integer.parseInt(vbs.getId().getItemNo())-1;//当前减签人的位置（集合从0开始，所以减1）
			
			//當前減簽的人,是當前登錄者
	  	    WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
	  	    String userName=user.getUsername();
			
	  	    /**
	  	     * 如果减签的刚好函文的下一位签核人，
	  	     * （1）则要email通知他不要签
	  	     * （2）并且更新下一位签核人,并且把新sigerNext赋给的sigerNext,主表的itemNext不需要变；      
	  	     * （3）email通知新的下一位签核
	  	     */
	  	    
	  	    //*********************************************如果当前减签人刚好是函文的下一位签核人**********************************************//
			if(visaNext.equals(singer)&&flowMk.equals("Y")){
				//*********发送email通知已经减签***********//
			  SimpleMailSender sms_n=new SimpleMailSender();
	  		  MailSenderInfo mailInfo_n=new MailSenderInfo();
	  		  mailInfo_n.setValidate(true);
	  		  /*mailInfo_n.setUserName("kyuen@yydg.com.cn");
	  		  mailInfo_n.setPassword("yydgmail");
	  		  mailInfo_n.setFromAddress("<kyuen@yydg.com.cn>");*/
	  		  mailInfo_n.setToAddress(visaNext);
	  		  mailInfo_n.setSubject("函文減簽通知_"+billNo+"("+factNo+")");
	  		  mailInfo_n.setContent("你好,單號為:"+"<span style='color:red'>"+billNo+"</span>"+"的函文已由用戶:"
	  		                         +"<span style='color:blue'>"+userName+"</span>"+"減簽,收到該郵件的人,不需要審核此單號的函文.");
	  		  sms_n.sendHtmlMail(mailInfo_n);
	  		  
	  		  /****************發送到kyuen@yydg.com.cn***********************/
	  		  mailInfo_n.setToAddress("kyuen@yydg.com.cn");
	  		  sms_n.sendHtmlMail(mailInfo_n);
	  		  
	  		//*********更新下一位签核人***********//
	  		  //如果不是最后一个签核人，并且不是最后一个知会人,则不需要更新
	  		  int num001=vbm.getKyVisabillses().size()-visabillSer.findBillsWithNo(visaSort, billNo);
	  		  if(Integer.parseInt(itemNo)!=num001&&Integer.parseInt(itemNo)!=vbm.getKyVisabillses().size()){//if 			
	  			  String new_nextItemno="";
	      		  if(Integer.parseInt(itemNo)+1<10){
	      			  new_nextItemno="0"+(Integer.parseInt(itemNo)+1);
	      		  }else{
	      			  new_nextItemno=""+(Integer.parseInt(itemNo)+1);
	      		  }
	      		  KyVisabills bills=visabillSer.findById(factNo, visaSort, billNo, new_nextItemno);
	      		  vbm.setSignerNext(bills.getVisaSigner());
	      		 // visabillmSer.add(vbm);
	      		  
	      		  //****************通知下一位签核人***************/
	      		 String emailUrl_in="http://203.85.73.161/Login/vbm_findById_email?visaSort="+visaSort+"&billNo="+billNo
				         +"&factNo="+factNo+"&email="+bills.getVisaSigner();
	      		String emailUrl_in2="http://203.85.73.161/Login/vbm_findById_email2?visaSort="+visaSort+"&billNo="+billNo
				         +"&factNo="+factNo+"&email="+bills.getVisaSigner();
	      		 mailInfo_n.setToAddress(bills.getVisaSigner());
	      		 mailInfo_n.setSubject("函文減簽(下一位審核)_"+billNo+"("+factNo+")");
	      		 mailInfo_n.setContent(
	     	    		"函文單號:"+"<span style='color:red'>"+billNo+"</span>"+"&nbsp;&nbsp;廠別:"+factNo+
	     	    		"<br/>點擊單號直接審核:<a href='"+emailUrl_in2+"'>"+billNo+"</a>(電腦適用)"+
	     	    		"<br/>點擊單號直接審核:<a href='"+emailUrl_in+"'>"+billNo+"</a>(手機平板適用)"+
	     	    		"<br/>如需查詢以往單據請登錄加久網站:((云端))<a href='http://203.85.73.161/Login'>http://203.85.73.161/Login</a>" +	            
	     	      		"<br/>進入[KPI數據]--[函文審核]中查找對應單號審核,"+	      	    		
	     	    		"<hr/>"+	      		
	     	    		"<br/>本郵件自動發送,請勿回復!如需回復或者問題，請回复到kyinfo.lp@yydg.com.cn劉平!<br/>" +
	     	    		"<hr/>");
	      		 sms_n.sendHtmlMail(mailInfo_n);
	      		//****************通知下一位签核人***************/
	  		  }//if 
	  		  
	  		  
	  		  if(Integer.parseInt(itemNo)==num001){
	  			  vbm.setVisaMk("Y");
	  		  }
	  		  visabillmSer.add(vbm);
	  		//*********更新下一位签核人***********//
			}		
			//*********************************************如果当前减签人刚好是函文的下一位签核人**********************************************//
			
			visabillSer.delete(factNo, visaSort, billNo, itemNo);//直接删除
			vbm.getKyVisabillses().remove(index);
							
			/*for(int i=0;i<vbm.getKyVisabillses().size();i++){
				KyVisabills bls=vbm.getKyVisabillses().get(i);
				String itemno=bls.getId().getItemNo();
				System.out.println(itemno+"---"+bls.getVisaRank()+"----"+bls.getFlowMk());
			}
			System.out.println("--------------------------------------------------");*/		
			for(int i=index;i<vbm.getKyVisabillses().size();i++){//从index开始（如果index是最后一个，则跳出循环）
				KyVisabills bls=vbm.getKyVisabillses().get(i);
				visabillSer.delete(bls);//为了避免最后一个出现重复，所以要先删除（这点很重要）
				String itemno=bls.getId().getItemNo();			
				String tempnum="";
				if(i+1<10){
					tempnum="0"+(i+1);
				}else{
					tempnum=""+(i+1);
				}
				itemno=tempnum;	
				bls.getId().setItemNo(tempnum);
				visabillSer.add(bls);
				System.out.println(itemno+"---"+bls.getVisaRank()+"----"+bls.getFlowMk());
			}
			/*System.out.println("--------------------------------------------------");
			for(int i=index;i<vbm.getKyVisabillses().size();i++){
				KyVisabills bls=vbm.getKyVisabillses().get(i);
				String itemno=bls.getId().getItemNo();
				System.out.println(itemno+"---"+bls.getVisaRank()+"----"+bls.getFlowMk());
			}*/
			
			//如果未签核的人数为0，也就签完，则进行知会email通知
	        //int numYes=visabillSer.findBillsWithYes(visaSort, billNo);
			
			/*********************最後知會（每天定時有發送，所以註釋）20160311*********************************/
	        /*int numNo=visabillSer.findBillsWithNo2(visaSort, billNo);//未签核的人数
			if(numNo==0){
				//判斷是費用函文還是內部聯絡函
	  			if(billNo.substring(0, 2).equals("EM")){
	  				this.addVisabillsAndEmail(factNo, billNo, visaSort,vbm);
	  			}
	  			if((billNo.substring(0, 2).equals("CM"))){
	  				this.addVisabillsAndEmail2(factNo, billNo, visaSort,vbm);
	  			}
	  			if((billNo.substring(0, 2).equals("BM"))){
	  				this.addVisabillsAndEmail3(factNo, billNo, visaSort, vbm);
	  			}
			}*/
			/*********************最後知會（每天定時有發送，所以註釋）*********************************/
			ajaxResult="0";
		}catch(Exception e){
			ajaxResult="1";
			System.out.println(e);
		}
		
		return "minusvisabills2";
	}
	
	

	
	/**
	 * 暫無用
	 * @Title: minusvisabills3
	 * @Description: TODO
	 * @param @return
	 * @param @throws IOException
	 * @return String
	 * @throws
	 * @author web
	 * @date 2016/3/11
	 */
	public String minusvisabills3() throws IOException{
		KyVisabillm vbm=visabillmSer.findById(factNo, visaSort, billNo);		
		KyVisabills vbs=visabillSer.findById(factNo, visaSort, billNo, itemNo);
		
		String visaNext=vbm.getSignerNext();
		String singer=vbs.getVisaSigner();
		String flowMk=vbs.getFlowMk();
		int index=Integer.parseInt(vbs.getId().getItemNo())-1;//当前减签人的位置（集合从0开始，所以减1）
		
		//當前減簽的人,是當前登錄者
  	    WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
  	    String userName=user.getUsername();
		
  	    /**
  	     * 如果减签的刚好函文的下一位签核人，
  	     * （1）则要email通知他不要签
  	     * （2）并且更新下一位签核人,并且把新sigerNext赋给的sigerNext,主表的itemNext不需要变；      
  	     * （3）email通知新的下一位签核
  	     */
  	    
  	    //*********************************************如果当前减签人刚好是函文的下一位签核人**********************************************//
		if(visaNext.equals(singer)&&flowMk.equals("Y")){
			//*********发送email通知已经减签***********//
		  SimpleMailSender sms_n=new SimpleMailSender();
  		  MailSenderInfo mailInfo_n=new MailSenderInfo();
  		  mailInfo_n.setValidate(true);
  		  /*mailInfo_n.setUserName("kyuen@yydg.com.cn");
  		  mailInfo_n.setPassword("yydgmail");
  		  mailInfo_n.setFromAddress("<kyuen@yydg.com.cn>");*/
  		  mailInfo_n.setToAddress(visaNext);
  		  mailInfo_n.setSubject("函文減簽通知_"+billNo+"("+factNo+")");
  		  mailInfo_n.setContent("你好,單號為:"+"<span style='color:red'>"+billNo+"</span>"+"的函文已由用戶:"
  		                         +"<span style='color:blue'>"+userName+"</span>"+"減簽,收到該郵件的人,不需要審核此單號的函文.");
  		  sms_n.sendHtmlMail(mailInfo_n);
  		  
  		//*********更新下一位签核人***********//
  		  //如果是最后一个签核人，或者最后一个知会人（不是最后一个签核人，并且不是最后一个知会人）则不需要更新
  		  int num001=vbm.getKyVisabillses().size()-visabillSer.findBillsWithNo(visaSort, billNo);
  		  if(Integer.parseInt(itemNo)!=num001&&Integer.parseInt(itemNo)!=vbm.getKyVisabillses().size()){ 			
  			  String new_nextItemno="";
      		  if(Integer.parseInt(itemNo)+1<10){
      			  new_nextItemno="0"+(Integer.parseInt(itemNo)+1);
      		  }else{
      			  new_nextItemno=""+(Integer.parseInt(itemNo)+1);
      		  }
      		  KyVisabills bills=visabillSer.findById(factNo, visaSort, billNo, new_nextItemno);
      		  vbm.setSignerNext(bills.getVisaSigner());
      		  visabillmSer.add(vbm);
      		  
      		  //****************通知下一位签核人***************/
      		 String emailUrl_in="http://203.85.73.161/Login/vbm_findById_email?visaSort="+visaSort+"&billNo="+billNo
			         +"&factNo="+factNo+"&email="+bills.getVisaSigner();
      		String emailUrl_in2="http://203.85.73.161/Login/vbm_findById_email?visaSort="+visaSort+"&billNo="+billNo
			         +"&factNo="+factNo+"&email="+bills.getVisaSigner();
      		 mailInfo_n.setToAddress(bills.getVisaSigner());
      		 mailInfo_n.setSubject("函文減簽(下一位審核)_"+billNo+"("+factNo+")");
      		 mailInfo_n.setContent(
     	    		"函文單號:"+"<span style='color:red'>"+billNo+"</span>"+"&nbsp;&nbsp;廠別:"+factNo+
     	    		"<br/>點擊單號直接審核:<a href='"+emailUrl_in2+"'>"+billNo+"</a>(電腦適用)"+
     	    		"<br/>點擊單號直接審核:<a href='"+emailUrl_in+"'>"+billNo+"</a>(手機平板適用)"+
     	    		"<br/>如需查詢以往單據請登錄加久網站:((云端))<a href='http://203.85.73.161/Login'>http://203.85.73.161/Login</a>" +	            
     	      		"<br/>進入[KPI數據]--[函文審核]中查找對應單號審核,"+	      	    		
     	    		"<hr/>"+	      		
     	    		"<br/>本郵件自動發送,請勿回復!如需回復或者問題，請回复到kyinfo.lp@yydg.com.cn劉平!<br/>" +
     	    		"<hr/>");
      		 sms_n.sendHtmlMail(mailInfo_n);
      		//****************通知下一位签核人***************/
  		  } 
  		//*********更新下一位签核人***********//
		}		
		//*********************************************如果当前减签人刚好是函文的下一位签核人**********************************************//
		
		visabillSer.delete(factNo, visaSort, billNo, itemNo);//直接删除
		vbm.getKyVisabillses().remove(index);
						
		/*for(int i=0;i<vbm.getKyVisabillses().size();i++){
			KyVisabills bls=vbm.getKyVisabillses().get(i);
			String itemno=bls.getId().getItemNo();
			System.out.println(itemno+"---"+bls.getVisaRank()+"----"+bls.getFlowMk());
		}
		System.out.println("--------------------------------------------------");*/		
		for(int i=index;i<vbm.getKyVisabillses().size();i++){//从index开始（如果index是最后一个，则跳出循环）
			KyVisabills bls=vbm.getKyVisabillses().get(i);
			visabillSer.delete(bls);//为了避免最后一个出现重复，所以要先删除（这点很重要）
			String itemno=bls.getId().getItemNo();			
			String tempnum="";
			if(i+1<10){
				tempnum="0"+(i+1);
			}else{
				tempnum=""+(i+1);
			}
			itemno=tempnum;	
			bls.getId().setItemNo(tempnum);
			visabillSer.add(bls);
			System.out.println(itemno+"---"+bls.getVisaRank()+"----"+bls.getFlowMk());
		}
		/*********************保留減簽的人員，幷放到最後**************************/
		int lastItemNo=vbm.getKyVisabillses().size();
		String str_lastItemNo="";
		if(lastItemNo+1<10){
			str_lastItemNo="0"+lastItemNo;
		}else{
			str_lastItemNo=""+lastItemNo;
		}
		vbs.getId().setItemNo(str_lastItemNo);
		vbs.setFlowMk("N");
		
		/*System.out.println("--------------------------------------------------");
		for(int i=index;i<vbm.getKyVisabillses().size();i++){
			KyVisabills bls=vbm.getKyVisabillses().get(i);
			String itemno=bls.getId().getItemNo();
			System.out.println(itemno+"---"+bls.getVisaRank()+"----"+bls.getFlowMk());
		}*/
		
		//如果未签核的人数为0，也就签完，则进行知会email通知
        //int numYes=visabillSer.findBillsWithYes(visaSort, billNo);		
        int numNo=visabillSer.findBillsWithNo2(visaSort, billNo);//未签核的人数（不包含知会的）
		if(numNo==0){
			//判斷是費用函文還是內部聯絡函
  			if(billNo.substring(0, 2).equals("EM")){
  				this.addVisabillsAndEmail(factNo, billNo, visaSort,vbm);
  			}else{
  				this.addVisabillsAndEmail2(factNo, billNo, visaSort,vbm);
  			}
		}
		return "minusvisabills3";
	}
	
	
	public String strToLow(String str){
		return str.toLowerCase();
	}
	
	
	/**
	 * 手動發送email
	 */
	public void sendEmail(){
		String subject="";
		String content="";
		MailSenderInfo mailInfo = new MailSenderInfo();
		SimpleMailSender sms = new SimpleMailSender();
		mailInfo.setValidate(true);
		vbm=visabillmSer.findByBillNo(billNo);
		List<String>list_email=new ArrayList<String>();
		String signerNext=vbm.getSignerNext();
		String factNo=vbm.getId().getFactNo();
		String billNo=vbm.getId().getBillNo();
		String visaSort=vbm.getId().getVisaSort();
		String visaMk=vbm.getVisaMk();
		 /******************20151113备签人请使用方法findByFactNoAEmailPwd2(String factNo,String email)**********************/
		//String emailPwd = webuseremailSer.findEmailPWD(factNo,signerNext);//備簽人Email
		list_email.add(signerNext);
		if(factNo.equals("GJ")){
			String visaSinger=visaSer.findVisaSigner(factNo, visaSort);
			list_email.add(visaSinger);
		}
		List<String>list_emailPwd=webuseremailSer.findByFactNoAEmailPwd2(factNo, signerNext);
			if(list_emailPwd.size()>0){
				for(int j=0;j<list_emailPwd.size();j++){
					list_email.add(list_emailPwd.get(j));
				}
			}
		/******************20151113备签人请使用方法findByFactNoAEmailPwd2(String factNo,String email)**********************/
			
		/***************************************中途知會人的email20160217********************************************/
			List<String>list_emailPwd_a=webuseremailaSer.findByEmail(factNo, signerNext, visaSort);
			for(int k=0;k<list_emailPwd_a.size();k++){
				list_email.add(list_emailPwd_a.get(k));
			}
		/***************************************中途知會人的email20160217********************************************/
			
		list_email.add("kyuen@yydg.com.cn");
		String emailUrl="http://203.85.73.161/Login/vbm_findById_email?visaSort="+visaSort+"&billNo="+billNo
		         +"&factNo="+factNo+"&email="+signerNext;
		String emailUrl2="http://203.85.73.161/Login/vbm_findById_email2?visaSort="+visaSort+"&billNo="+billNo
		         +"&factNo="+factNo+"&email="+signerNext;
		if(visaMk.equals("N")){
			subject="函文審核定時通知_"+billNo+"("+factNo+")";
			content="函文單號:"+"<span style='color:red'>"+billNo+"</span>"+"&nbsp;&nbsp;廠別:"+factNo+
		    		  "<br/>點擊單號直接審核:<a href='"+emailUrl2+"'>"+billNo+"</a>(電腦適用)"+
		    		  "<br/>點擊單號直接審核:<a href='"+emailUrl+"'>"+billNo+"</a>(手機平板適用)"+				    		 
				      "<hr/>"+
		    		 "如需查詢以往單據請登錄加久網站:(云端)<a href='http://203.85.73.161/Login'>http://203.85.73.161/Login</a>" +		            
		      		"<br/>進入[KPI數據]--[函文審核]中查找對應單號審核"+			    		
		    		"<hr/>"+
		      		"<br/>本郵件定時自動發送,請勿回復!如需回復或者問題，請回复到kyinfo.lp@yydg.com.cn劉平!<br/>"+
		    		"<hr/>";
		}
		if(visaMk.equals("T")){				
				subject="函文退回定時通知_"+billNo+"("+factNo+")";//退回函文隻發送一次，所以也要鎖定狀態emailMk	
				vbm.setEmailMk("Y");
				visabillmSer.add(vbm);
				content="函文單號:"+"<span style='color:red'>"+billNo+"</span>"+"&nbsp;&nbsp;"+"不通過，備註如下:"+
			    		  "<br/>"+
			    		  "<span style='color:red'>"+(vbm.getMemoMk()==null?"無備註":vbm.getMemoMk())+"</span>"+				    		 
					      "<hr/>"+
			    		 "詳情請登錄加久網站:(云端)<a href='http://203.85.73.161/Login'>http://203.85.73.161/Login</a>" +		            
			      		"<br/>進入[KPI數據]--[函文審核]中查找對應單號審核"+			    		
			    		"<hr/>"+
			      		"<br/>本郵件定時自動發送,請勿回復!如需回復或者問題，請回复到kyinfo.lp@yydg.com.cn劉平!<br/>"+
			    		"<hr/>";
		}
		for(int j=0;j<list_email.size();j++){//start for2
			  mailInfo.setToAddress(list_email.get(j));    
		      mailInfo.setSubject(subject);    			      
		      mailInfo.setContent(content); 				    		  			           
		      sms.sendHtmlMail(mailInfo);//发送html格式
		}//end for2	
	}
    

	public void sendEmail(String local_factNo,String local_billNo,String local_visaSort,KyVisabillm vbm){
		//ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-dao.xml","spring-services.xml"});
		//IKyVisabillmServices visabillmSer=(IKyVisabillmServices)ac.getBean("visabillmSer");
		//IWebuserEmailServices webuseremailSer=(IWebuserEmailServices)ac.getBean("webuseremailSer");/****備簽人****/
		//IWebuserEmailAServices webuseremailaSer=(IWebuserEmailAServices)ac.getBean("webuseremailaSer");/******知會人********/
		//KyVisabillm vbm2=visabillmSer.findById(local_factNo, local_visaSort, local_billNo);//用參數傳遞vbm,減少連接數據庫
		List<KyVisabills>list_visa2=vbm.getKyVisabillses();
		//这个类主要是设置邮件   
		List<String>list_emails=new ArrayList<String>();//所有發送人
		list_emails.add("kyuen@yydg.com.cn");
		for(KyVisabills bills:list_visa2){
			list_emails.add(bills.getVisaSigner());
			if(bills.getFlowMk().equals("Y")){//要簽核的人才需要通知知會人
				List<String>list_emailPwd=webuseremailaSer.findByEmail(local_factNo,bills.getVisaSigner(),local_visaSort);
				for(String str:list_emailPwd){
					list_emails.add(str);
				}
			}
		}
		
		String[] attachFileNames = { "d:/" + local_billNo + ".pdf" };// 附件
		SimpleMailSender sms = new SimpleMailSender();
		MailSenderInfo mailInfo = new MailSenderInfo();
		
		for(String email:list_emails){//for
			mailInfo.setValidate(true);			
			mailInfo.setSubject("函文知會定時通知(審核完畢)_" + local_billNo + "("
					+ local_factNo + ")");

			mailInfo.setAttachFileNames(attachFileNames);
			mailInfo.setContent("單號為:" + "<span style='color:red'>"
					+ local_billNo + "</span>" + "的函文已審核完畢,請查看附件"
					+ "<br/>本郵件自動定時發送，請勿回覆");

			String toAddress = email;
			mailInfo.setToAddress(toAddress);
			sms.sendHtmlMail(mailInfo);// 发送html格式
		}//for
		File file = new File("d:/" + local_billNo + ".pdf");
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			}
		}						             
}
}
