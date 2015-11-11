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
import services.IWebFactServices;
import services.IWebTypeServices;
import services.IWebUserService;
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
	private IWebUserService webUserService;
	private IWebuserEmailServices webuseremailSer;
	private IWebTypeServices webtypeSer;
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
	
	
	
	
		
	/*public String findPageBean(){
		ActionContext.getContext().getApplication().clear();
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		userName=user.getName();
		bean=visabillSer.findPageBean(25, page, userName, visaMk, factNo, billNo,visaSort,yymmdd,yymmdd2);
		return "beanList";
	}
	public String findPageBean2(){
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
		userName=user.getName();
		bean=visabillSer.findPageBean(25, page, userName, visaMk, factNo, billNo,visaSort,yymmdd,yymmdd2);
		return "beanList1";		
	}
	public String findPageBean3(){
		factNo=(String)ActionContext.getContext().getApplication().get("visabills-factno");
		billNo=(String)ActionContext.getContext().getApplication().get("visabills-billno");
		visaMk=(String)ActionContext.getContext().getApplication().get("visabills-visamk");
		visaSort=(String)ActionContext.getContext().getApplication().get("visabills-visasort");
		yymmdd=(String)ActionContext.getContext().getApplication().get("visabills-yymmdd");
		yymmdd2=(String)ActionContext.getContext().getApplication().get("visabills-yymmdd2");
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		userName=user.getName();
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		bean=visabillSer.findPageBean(25, page, userName, visaMk, factNo, billNo,visaSort,yymmdd,yymmdd2);
		return "beanList1";
	}*/
	
	public void setWebtypeSer(IWebTypeServices webtypeSer) {
		this.webtypeSer = webtypeSer;
	}
	public void setWebuseremailSer(IWebuserEmailServices webuseremailSer) {
		this.webuseremailSer = webuseremailSer;
	}
	public void setWebUserService(IWebUserService webUserService) {
		this.webUserService = webUserService;
	}
	public void setKyzletterSer(IKyzContactLetterServices kyzletterSer) {
		this.kyzletterSer = kyzletterSer;
	}
	
	/********************************函文审核******************************************/
	public String findPageBean(){
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
		
		this.getKyzTitle(bean);
		this.getTypeName(bean);
		return "beanList";
	}
	public String findPageBean2(){
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
		return "beanList1";		
	}
	public String findPageBean3(){
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
		ActionContext.getContext().getApplication().clear();
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
		return "beanList_1";
	}
	public String findPageBean2_1(){
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
		bean=visabillSer.findPageBean(25, page, userName, visaMk, factNo, billNo,visaSort,yymmdd,yymmdd2,email);
		this.getTypeName(bean);
		return "beanList1_1";
	}
	public String findPageBean3_1(){
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
		bean=visabillSer.findPageBean(25, page, userName, visaMk, factNo, billNo,visaSort,yymmdd,yymmdd2,email);
		this.getTypeName(bean);
		return "beanList1_1";
	}
	/********************************函文审核状况******************************************/
	
	
	
	public String findById(){//審核
		vbm=visabillmSer.findById(factNo, visaSort, billNo);
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
		ActionContext.getContext().getSession().put("Email", email);//用於判斷審核完後頁面的跳轉（在this.remark()方法最後字符串返回）
		return "findById_email";
	}
	public String findById_email2(){//email直接審核，不需要登錄帳號（普通電腦）findById_email2
		vbm=visabillmSer.findById(factNo, visaSort, billNo);
		ActionContext.getContext().getSession().put("Email", email);//用於判斷審核完後頁面的跳轉（在this.remark()方法最後字符串返回）
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
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
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
			
			//退回之後,還要生成新的函文表,默認內容是退回的函文,單號以:舊單號+序號(默認01開始)中間以"-"分開,每退一次,序號加1
			//(1)費用函文
			/*if(billNo.substring(0, 2).equals("EM")){
				KyzExpectmatmId id=new KyzExpectmatmId();
				id.setBillNo(billNo);
				id.setFactNo(factNo);
				KyzExpectmatm kyzm=kyzSer.findById(id);
				String billNo_temp="";
				if(billNo.contains("-")){
					String str_b=billNo.substring(0,billNo.indexOf("-"));
					String str_n=billNo.substring(billNo.indexOf("-")+1);
					int int_temp=Integer.parseInt(str_n);
					String str_result="";
					if((int_temp+1)>9){
						str_result="-"+(int_temp+1);
					}else{
						str_result="-0"+(int_temp+1);
					}
					billNo_temp=str_b+str_result;
				}else{
					billNo_temp=billNo+"-01";				
				}
				kyzm.getId().setBillNo(billNo_temp);
				for(int i=0;i<kyzm.getKyzExpectmatses().size();i++){
					kyzm.getKyzExpectmatses().get(i).getId().getKyzExpectmatm().getId().setBillNo(billNo_temp);
				}
				ActionContext.getContext().getSession().put("billNo_temp", billNo_temp);//保存新函文單號,便於退回時,每一位審核者查找單號(只保存新單號)
				
				ActionContext.getContext().getSession().put("kyzm", kyzm);//保存新函文,便於下一步remark時,新函文kyzm與最終的函文審核完畢時,同時進行add保存到數據庫
			}else{
				//(2)內部聯絡函
				KyzContactletter letter=kyzletterSer.findById(factNo, billNo);
				String billNo_temp="";
				if(billNo.contains("-")){
					String str_b=billNo.substring(0,billNo.indexOf("-"));
					String str_n=billNo.substring(billNo.indexOf("-")+1);
					int int_temp=Integer.parseInt(str_n);
					String str_result="";
					if((int_temp+1)>9){
						str_result="-"+(int_temp+1);
					}else{
						str_result="-0"+(int_temp+1);
					}
					billNo_temp=str_b+str_result;
				}else{
					billNo_temp=billNo+"-01";				
				}
				letter.getId().setBillNo(billNo_temp);
				ActionContext.getContext().getSession().put("billNo_temp", billNo_temp);//保存新函文單號,便於退回時,每一位審核者查找單號(只保存新單號)
				
				ActionContext.getContext().getSession().put("letter", letter);//保存新函文,便於下一步remark時,新函文kyzm與最終的函文審核完畢時,同時進行add保存到數據庫
			}
			*/						
			
			
			//kyzSer.add(kyzm);	先不保存,在下一步remark時同時add到數據庫					
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
		
		/*ActionContext.getContext().getSession().put("vbs", vbs);		
		ActionContext.getContext().getSession().put("vbm_num", num_temp);//項次保存到session,用於備註時,知道是第幾個審核人
		ActionContext.getContext().getSession().put("vbm", vbm);*/
		
		
		
		
		/******************************remark***********************************/
		
				/*String billno_temp="";//根據是否退回,返回相應單號								
				//KyVisabillm vbm=(KyVisabillm)ActionContext.getContext().getSession().get("vbm");
				//int num=(Integer)ActionContext.getContext().getSession().get("vbm_num");											
				//KyVisabills vbs=vbm.getKyVisabillses().get(num_temp-1);
				KyzExpectmatm kyzm=(KyzExpectmatm)ActionContext.getContext().getSession().get("kyzm");
				KyzContactletter letter=(KyzContactletter)ActionContext.getContext().getSession().get("letter");*/				
				String dateVisa=format.format(new Date());
				vbs.setDateVisa(dateVisa);
				vbs.setMemo(memo);
				String lastmk=vbm.getLastMk();
				
				if(lastmk.equals("Y")){										
					if(num_temp==vbm.getKyVisabillses().size()){
						vbm.setMemoMk(memo);							
					}
					//billno_temp=vbm.getId().getBillNo();
				}
				if(lastmk.equals("T")){
					vbm.setMemoMk(memo);
					/*billno_temp=(String)ActionContext.getContext().getSession().get("billNo_temp");					
					//判斷是費用函文還內部聯絡函
					if(vbm.getId().getBillNo().substring(0, 2).equals("EM")){
						kyzSer.add(kyzm);//只有被退回時,才生成新函文
						
						//根據函文是否帶有附檔文件 ,判斷時否要複製附檔文件
						String filesYn=kyzm.getFilesYn();
						if(filesYn!=null&&filesYn.equals("1")){
							String billNo_old=vbm.getId().getBillNo();
							String billNo_new=billno_temp;
							
							List<KyzExpectmatmFile>kyzfile_list=kyzexpfileSer.findByBillNo(billNo_old);
							for(int i=0;i<kyzfile_list.size();i++){
								KyzExpectmatmFile kyzfile=new KyzExpectmatmFile();
								KyzExpectmatmFile kyzfile_temp=kyzfile_list.get(i);
								String username=kyzfile_temp.getUsername();
								String filename=kyzfile_temp.getFilename();
								kyzfile.setBillno(billno_temp);
								kyzfile.setFilename(filename);
								kyzfile.setUsername(username);
								kyzexpfileSer.add(kyzfile);
							}
							//複製文件夾
							String sourceDir="d:\\KyzexpFile_backup\\"+billNo_old;
							String targetDir="d:\\KyzexpFile_backup\\"+billNo_new;
							this.copyDirectiory(sourceDir, targetDir);
						}
					}else{
						kyzletterSer.add(letter);//只有被退回時,才生成新函文
					}*/
														
				}		
				visabillmSer.add(vbm);
				
				/*String temp="";
				String Email=(String)ActionContext.getContext().getSession().get("Email");
				if(Email==null||Email.equals("")){
					temp="remark";
				}else{
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().print("<script>alert('審核成功!將返回空白頁或自動關閉');window.opener=null;window.open('','_self');window.close();</script>");
					temp="success";
				}
				return temp;*/
		/******************************remark***********************************/
	}
	
	/**
	 * 手機平板返回
	 * @return
	 * @throws IOException
	 */
	public String add2() throws IOException{
		this.add();
		return "success";
	}
	
	/**
	 * 電腦返回
	 * @throws IOException 
	 */
	public String add3() throws IOException{
		this.add();
		return "beanList";
	}
	public String remark() throws IOException{
		//String result="";//根據是否退回,返回相應結果
		String billno_temp="";//根據是否退回,返回相應單號
		String factno_temp="";
		KyVisabillm vbm=(KyVisabillm)ActionContext.getContext().getSession().get("vbm");
		KyzExpectmatm kyzm=(KyzExpectmatm)ActionContext.getContext().getSession().get("kyzm");
		KyzContactletter letter=(KyzContactletter)ActionContext.getContext().getSession().get("letter");
		factno_temp=vbm.getId().getFactNo();
		int num=(Integer)ActionContext.getContext().getSession().get("vbm_num");
		KyVisabills vbs=vbm.getKyVisabillses().get(num-1);
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		String dateVisa=format.format(new Date());
		vbs.setDateVisa(dateVisa);
		vbs.setMemo(memo);
		String lastmk=vbm.getLastMk();
		//String subject="";
		if(lastmk.equals("Y")){
			billno_temp=vbm.getId().getBillNo();
			//subject="函文審核(下一位)_"+billno_temp+"("+factno_temp+")";
			if(num==vbm.getKyVisabillses().size()){
				vbm.setMemoMk(memo);							
			}
		}
		if(lastmk.equals("T")){
			vbm.setMemoMk(memo);
			billno_temp=(String)ActionContext.getContext().getSession().get("billNo_temp");
			//result="舊函文("+vbm.getId().getBillNo()+")已退回,新函文已生成.";	
			//subject="函文退回通知_"+billno_temp+"("+factno_temp+")";
			//判斷是費用函文還內部聯絡函
			if(vbm.getId().getBillNo().substring(0, 2).equals("EM")){
				kyzSer.add(kyzm);//只有被退回時,才生成新函文
				
				//根據函文是否帶有附檔文件 ,判斷時否要複製附檔文件
				String filesYn=kyzm.getFilesYn();
				if(filesYn!=null&&filesYn.equals("1")){
					String billNo_old=vbm.getId().getBillNo();
					String billNo_new=billno_temp;
					
					List<KyzExpectmatmFile>kyzfile_list=kyzexpfileSer.findByBillNo(billNo_old);
					for(int i=0;i<kyzfile_list.size();i++){
						KyzExpectmatmFile kyzfile=new KyzExpectmatmFile();
						KyzExpectmatmFile kyzfile_temp=kyzfile_list.get(i);
						String username=kyzfile_temp.getUsername();
						String filename=kyzfile_temp.getFilename();
						kyzfile.setBillno(billno_temp);
						kyzfile.setFilename(filename);
						kyzfile.setUsername(username);
						kyzexpfileSer.add(kyzfile);
					}
					//複製文件夾
					String sourceDir="d:\\KyzexpFile_backup\\"+billNo_old;
					String targetDir="d:\\KyzexpFile_backup\\"+billNo_new;
					this.copyDirectiory(sourceDir, targetDir);
				}
			}else{
				kyzletterSer.add(letter);//只有被退回時,才生成新函文
			}
												
		}		
		visabillmSer.add(vbm);
		String temp="";
		String Email=(String)ActionContext.getContext().getSession().get("Email");
		if(Email==null||Email.equals("")){
			temp="remark";
		}else{
			/*response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>alert('審核成功!將返回空白頁或自動關閉');window.opener=null;window.open('','_self');window.close();</script>");*/
			temp="success";
		}
		return temp;
	}
	
	
	/**
	 * 備註修改
	 * @return
	 * @throws IOException 
	 */
	public String remark_old() throws IOException{
		String result="";//根據是否退回,返回相應結果
		String billno_temp="";//根據是否退回,返回相應單號
		String factno_temp="";
		KyVisabillm vbm=(KyVisabillm)ActionContext.getContext().getSession().get("vbm");
		KyzExpectmatm kyzm=(KyzExpectmatm)ActionContext.getContext().getSession().get("kyzm");
		KyzContactletter letter=(KyzContactletter)ActionContext.getContext().getSession().get("letter");
		factno_temp=vbm.getId().getFactNo();
		int num=(Integer)ActionContext.getContext().getSession().get("vbm_num");
		KyVisabills vbs=vbm.getKyVisabillses().get(num-1);
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		String dateVisa=format.format(new Date());
		vbs.setDateVisa(dateVisa);
		vbs.setMemo(memo);
		String lastmk=vbm.getLastMk();
		String subject="";
		if(lastmk.equals("Y")){
			billno_temp=vbm.getId().getBillNo();
			subject="函文審核(下一位)_"+billno_temp+"("+factno_temp+")";
			if(num==vbm.getKyVisabillses().size()){
				vbm.setMemoMk(memo);							
			}
		}
		if(lastmk.equals("T")){
			vbm.setMemoMk(memo);
			billno_temp=(String)ActionContext.getContext().getSession().get("billNo_temp");
			result="舊函文("+vbm.getId().getBillNo()+")已退回,新函文已生成.";	
			subject="函文退回通知_"+billno_temp+"("+factno_temp+")";
			//判斷是費用函文還內部聯絡函
			if(vbm.getId().getBillNo().substring(0, 2).equals("EM")){
				kyzSer.add(kyzm);//只有被退回時,才生成新函文
				
				//根據函文是否帶有附檔文件 ,判斷時否要複製附檔文件
				String filesYn=kyzm.getFilesYn();
				if(filesYn!=null&&filesYn.equals("1")){
					String billNo_old=vbm.getId().getBillNo();
					String billNo_new=billno_temp;
					
					List<KyzExpectmatmFile>kyzfile_list=kyzexpfileSer.findByBillNo(billNo_old);
					for(int i=0;i<kyzfile_list.size();i++){
						KyzExpectmatmFile kyzfile=new KyzExpectmatmFile();
						KyzExpectmatmFile kyzfile_temp=kyzfile_list.get(i);
						String username=kyzfile_temp.getUsername();
						String filename=kyzfile_temp.getFilename();
						kyzfile.setBillno(billno_temp);
						kyzfile.setFilename(filename);
						kyzfile.setUsername(username);
						kyzexpfileSer.add(kyzfile);
					}
					//複製文件夾
					String sourceDir="d:\\KyzexpFile_backup\\"+billNo_old;
					String targetDir="d:\\KyzexpFile_backup\\"+billNo_new;
					this.copyDirectiory(sourceDir, targetDir);
				}
			}else{
				kyzletterSer.add(letter);//只有被退回時,才生成新函文
			}
						
			
			
		}		
		visabillmSer.add(vbm);
		
		
		/***************************************************發送Email**************************************************/
		/**
		 * 發郵件到下一位審核人
		 */
		String signernext=vbm.getSignerNext();
		int vbs_size=vbm.getKyVisabillses().size();
		int nos=visabillSer.findBillsWithNo(vbm.getId().getVisaSort(), vbm.getId().getBillNo());
		if(nos>0){
			vbs_size=vbs_size-nos;
		}
		/*if(vbm.getKyVisabillses().get(vbm.getKyVisabillses().size()-3).getFlowMk().equals("N")){
			vbs_size=vbm.getKyVisabillses().size()-3;//(>=1000的最後三位都不要審核，則總長度-3  20150803)
		}else if(vbm.getKyVisabillses().get(vbm.getKyVisabillses().size()-2).getFlowMk().equals("N")){
			vbs_size=vbm.getKyVisabillses().size()-2;
		}else if(vbm.getKyVisabillses().get(vbm.getKyVisabillses().size()-1).getFlowMk().equals("N")){
			vbs_size=vbm.getKyVisabillses().size()-1;
		}else{
			vbs_size=vbm.getKyVisabillses().size();
		}*/
		
		//String subject="函文審核(下一位)";		
		if(num!=vbs_size||(num==vbs_size&&lastmk.equals("T"))){
			String emailUrl="http://203.85.73.161/Login/vbm_findById_email?visaSort="+vbm.getId().getVisaSort()+"& billNo="+billno_temp
			         +"& factNo="+vbm.getId().getFactNo()+"& email="+signernext;
			//这个类主要是设置邮件   
		      MailSenderInfo mailInfo = new MailSenderInfo();    
		      //mailInfo.setMailServerHost("smtp.qq.com");    
		      //mailInfo.setMailServerPort("25");    
		      mailInfo.setValidate(true);    
		      /*mailInfo.setUserName("kyuen@yydg.com.cn"); 
		      mailInfo.setPassword("yydgmail");//您的邮箱密码    
		      mailInfo.setFromAddress("<kyuen@yydg.com.cn>");*/    
		      mailInfo.setToAddress(signernext);    
		      mailInfo.setSubject(subject);    
		      mailInfo.setContent("函文單號:"+"<span style='color:red'>"+billno_temp+"</span>"+"&nbsp;&nbsp;廠別:"+factno_temp+
		    		  "<br/>點擊單號直接審核:<a href='"+emailUrl+"'>"+billno_temp+"(云端)</a>"+				      
				      "<hr/>"+
		    		 result+"如需查詢以往單據請登錄加久網站:(云端)<a href='http://203.85.73.161/Login'>http://203.85.73.161/Login</a>" +		            
		      		"<br/>進入[KPI數據]--[函文審核]中查找對應單號審核,"+	      		    		
		    		"<hr/>"+
		      		"<br/>本郵件自動發送,請勿回復!如需回復或者問題，請回复到kyinfo.lp@yydg.com.cn劉平!<br/>"+
		    		"<hr/>"
		    		);   
		         //这个类主要来发送邮件   
		      SimpleMailSender sms = new SimpleMailSender();   
		         // sms.sendTextMail(mailInfo);//发送文体格式    
		      sms.sendHtmlMail(mailInfo);//发送html格式  	          
		          //visabillmSer.add(vbm);
		      
		      /**
		       * 发送给备签人  20150817
		       */
			  String emailPwd=webuseremailSer.findEmailPWD(factno_temp,signernext);//备签人员
		      if(emailPwd!=null){
		    	  MailSenderInfo mailInfo2 = new MailSenderInfo();    
			      //mailInfo.setMailServerHost("smtp.qq.com");    
			      //mailInfo.setMailServerPort("25");    
			      mailInfo2.setValidate(true);    
			      /*mailInfo2.setUserName("kyuen@yydg.com.cn"); 
			      mailInfo2.setPassword("yydgmail");//您的邮箱密码    
			      mailInfo2.setFromAddress("<kyuen@yydg.com.cn>"); */   
			      mailInfo2.setToAddress(emailPwd);    
			      mailInfo2.setSubject(subject);    
			      mailInfo2.setContent("函文單號:"+"<span style='color:red'>"+billno_temp+"</span>"+"&nbsp;&nbsp;廠別:"+factno_temp+
			    		  "<br/>點擊單號直接審核:<a href='"+emailUrl+"'>"+billno_temp+"(云端)</a>"+					      
					      "<hr/>"+
			    		 result+"如需查詢以往單據請登錄加久網站:(云端)<a href='http://203.85.73.161/Login'>http://203.85.73.161/Login</a>" +
			            "<br/>外網請登錄"+			      		
			      		"<br/>進入[KPI數據]--[函文審核]中查找對應單號審核,"+	      		    		
			    		"<hr/>"+
			      		"<br/>本郵件自動發送,請勿回復!如需回復或者問題，請回复到kyinfo.lp@yydg.com.cn劉平!<br/>"+
			    		"<hr/>"
			    		);    
			         //这个类主要来发送邮件   
			      SimpleMailSender sms2 = new SimpleMailSender();   
			         // sms.sendTextMail(mailInfo);//发送文体格式    
			      sms2.sendHtmlMail(mailInfo2);//发送html格式  	   
		      }
		      
		      /**
		       * 這裡主要是測試kyuen@yydg.com.cn是否收到郵件
		       */
		      MailSenderInfo mailInfo3 = new MailSenderInfo();    
		      //mailInfo.setMailServerHost("smtp.qq.com");    
		      //mailInfo.setMailServerPort("25");    
		      mailInfo3.setValidate(true);    
		      /*mailInfo3.setUserName("kyuen@yydg.com.cn"); 
		      mailInfo3.setPassword("yydgmail");//您的邮箱密码    
		      mailInfo3.setFromAddress("<kyuen@yydg.com.cn>"); */   
		      mailInfo3.setToAddress("kyuen@yydg.com.cn");    
		      mailInfo3.setSubject(subject+"總站已收到("+num+")");    
		      mailInfo3.setContent(result+"請登錄加久網站:內部網站(云端)<a href='http://203.85.73.161/Login'>http://203.85.73.161/Login</a>" +		            
		      		"<br/>進入[KPI數據]--[函文審核]中查找對應單號審核,"+	      
		    		"<br/>"+
		    		"函文單號:"+"<span style='color:red'>"+billno_temp+"</span>"+"&nbsp;&nbsp;廠別:"+factno_temp);   
		         //这个类主要来发送邮件   
		      SimpleMailSender sms3 = new SimpleMailSender();     
		      sms3.sendHtmlMail(mailInfo3);//发送html格式  	          		      
		}
		if(num==vbs_size&&lastmk.equals("Y")){
			//subject="函文知會(審核完畢)";
			String local_factNo=vbm.getId().getFactNo();
			String local_billNo=vbm.getId().getBillNo();
			String local_visaSort=vbm.getId().getVisaSort();
			//判斷是費用函文還是內部聯絡函
			if(local_billNo.substring(0, 2).equals("EM")){
				this.addVisabillsAndEmail(local_factNo,local_billNo,local_visaSort);
			}else{
				this.addVisabillsAndEmail2(local_factNo,local_billNo,local_visaSort);
			}
			
			/**
		       * 发送给备签人  20150817
		       */
			/*for(int i=0;i<vbs_size;i++){
				String toEmail=vbm.getKyVisabillses().get(i).getVisaSigner();
				String emailPwd=webuseremailSer.findEmailPWD(vbm.getId().getFactNo(), toEmail);//备签人员
				if(emailPwd!=null){
			    	  MailSenderInfo mailInfo2 = new MailSenderInfo();    
				      //mailInfo.setMailServerHost("smtp.qq.com");    
				      //mailInfo.setMailServerPort("25");    
				      mailInfo2.setValidate(true);    
				      mailInfo2.setUserName("kyuen@yydg.com.cn"); 
				      mailInfo2.setPassword("yydgmail");//您的邮箱密码    
				      mailInfo2.setFromAddress("<kyuen@yydg.com.cn>");    
				      mailInfo2.setToAddress(emailPwd);    
				      mailInfo2.setSubject(subject);    
				      mailInfo2.setContent("函文:"+"<span style='color:red'>"+billno_temp+"</span>"+"&nbsp;&nbsp;廠別:"+factno_temp+"已審核完畢!");    
				         //这个类主要来发送邮件   
				      SimpleMailSender sms2 = new SimpleMailSender();   
				         // sms.sendTextMail(mailInfo);//发送文体格式    
				      sms2.sendHtmlMail(mailInfo2);//发送html格式  	   
			      }
			}*/		      			
		    /**
		       * 這裡主要是測試kyuen@yydg.com.cn是否收到郵件
		       */
		      MailSenderInfo mailInfo2 = new MailSenderInfo();    
		      //mailInfo.setMailServerHost("smtp.qq.com");    
		      //mailInfo.setMailServerPort("25");    
		      mailInfo2.setValidate(true);    
		      /*mailInfo2.setUserName("kyuen@yydg.com.cn"); 
		      mailInfo2.setPassword("yydgmail");//您的邮箱密码    
		      mailInfo2.setFromAddress("<kyuen@yydg.com.cn>"); */   
		      mailInfo2.setToAddress("kyuen@yydg.com.cn");    
		      mailInfo2.setSubject("總站已收到(審核完畢)");    		      		      
		      mailInfo2.setContent("函文:"+"<span style='color:red'>"+billno_temp+"</span>"+"&nbsp;&nbsp;廠別:"+factno_temp+"已審核完畢!");
		         //这个类主要来发送邮件   
		      SimpleMailSender sms2 = new SimpleMailSender();     
		      sms2.sendHtmlMail(mailInfo2);//发送html格式 		      		      			
		}
		/***************************************************發送Email**************************************************/
		
		
		String temp="";
		String Email=(String)ActionContext.getContext().getSession().get("Email");
		if(Email==null||Email.equals("")){
			temp="remark";
		}else{
			/*response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>alert('審核成功!將返回空白頁或自動關閉');window.opener=null;window.open('','_self');window.close();</script>");*/
			temp="success";
		}
		return temp;
	}
	
	
	
	/**
	 * 加簽
	 */
	
	public String addvisabills(){
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
									
		return "addvisabills";
	}
	
	/**
	 * 減簽
	 * @throws IOException 
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
		/*if(list.get(list.size()-3).getFlowMk().equals("N")){
			vbs_list=list.size()-3;//(>=1000的最後三位都不要審核，則總長度-3  20150803)
		}else if(list.get(list.size()-2).getFlowMk().equals("N")){
			vbs_list=list.size()-2;
		}else if(list.get(list.size()-1).getFlowMk().equals("N")){
			vbs_list=list.size()-1;
		}else{
			vbs_list=list.size();
		}*/
				
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
	      /*mailInfo.setUserName("kyuen@yydg.com.cn"); 
	      mailInfo.setPassword("yydgmail");//您的邮箱密码    
	      mailInfo.setFromAddress("<kyuen@yydg.com.cn>"); */   
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
	    		 /* mailInfo_n.setUserName("kyuen@yydg.com.cn");
	    		  mailInfo_n.setPassword("yydgmail");
	    		  mailInfo_n.setFromAddress("<kyuen@yydg.com.cn>");*/
	    		  mailInfo_n.setToAddress(visaSigner);
	    		  mailInfo_n.setSubject("函文減簽通知_"+billNo+"("+factNo+")");
	    		  mailInfo_n.setContent("你好,單號為:"+"<span style='color:red'>"+billNo+"</span>"+"的函文已由用戶:"
	    		                         +"<span style='color:blue'>"+userName+"</span>"+"減簽,收到該郵件的人,不需要審核此單號的函文.");
	    		  sms_n.sendHtmlMail(mailInfo_n);
	    	  }
	      }
	      }//end if1
			     	          	          
	          /**
	           * 如果當前減簽人的上一位已審核過,則要發送郵件告知當前減簽人,由於被減簽,不用再去審核了
	           */
	       /*   int lastnum=startnum-1;
	          String lastnumstr="";
	          if(lastnum<10){
	        	  lastnumstr="0"+lastnum;
	          }else{
	        	  lastnumstr=""+lastnum;
	          }
	          KyVisabills vbslast=visabillSer.findById(factNo, visaSort, billNo, lastnumstr);
	          String lastvsmk=vbslast.getVisaMk();
	          if(lastvsmk.equals("Y")){	        		      			      			      		    	      	         
	      	      mailInfo.setValidate(true);    
	      	      mailInfo.setUserName("kyuen@yydg.com.cn"); 
	      	      mailInfo.setPassword("yydgmail");  
	      	      mailInfo.setFromAddress("<kyuen@yydg.com.cn>");    
	      	      mailInfo.setToAddress(singerlast);    
	      	      mailInfo.setSubject("函文減簽通知");    
	      	      mailInfo.setContent("你好,單號為:"+billNo+"的函文已被減簽,不用再審核.");    	      	         	      	          
	      	      sms.sendHtmlMail(mailInfo);  	
	          }*/
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
	  				this.addVisabillsAndEmail(factNo, billNo, visaSort);
	  			}else{
	  				this.addVisabillsAndEmail2(factNo, billNo, visaSort);
	  			}
	  			
	  		}
	         	  		
		return "minusvisabills";
	}
	
	
	
	
	/**
	 * 知會
	 * @throws IOException 
	 */
	public String addVisabillsAndEmail() throws IOException{
				
		/**
		 * 打印
		 */
		
		
		KyzExpectmatmId kyzid=new KyzExpectmatmId();
		kyzid.setBillNo(billNo);
		kyzid.setFactNo(factNo);
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
		
		/*String type=list.get(0).getVisaType();
		List<KyzVisaflow> list_visa=visaSer.findByType(type);*/
		
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		KyVisabillm vbm2=visabillmSer.findById(kyzid.getFactNo(), visaSort, kyzid.getBillNo());
		List<KyVisabills>list_visa=vbm2.getKyVisabillses();
		for(int k=0;k<list_visa.size();k++){
			if(list_visa.get(k).getFlowMk().equals("N")){
				list_visa.remove(k);
			}
		}
		List<KyzVisaflow>list_visaflow=visaSer.findByType(factNo,visaSort);
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
			list_visabillstemp.add(visabillstemp);
		}
						
		Map visa_map=new HashMap<String,Object>();
		visa_map.put("list_visa", list_visabillstemp);
		
		map.put("sub_map", sub_map);
		map.put("visa_map", visa_map);
		
		/*函文附檔*/
		//String pic_file=ServletActionContext.getRequest().getRealPath("/KyzexpFile/"+kyzid.getBillNo()+"/")+"/";//函文附檔圖片路徑
		String pic_file=new File("d:\\KyzexpFile_backup\\"+kyzid.getBillNo()).toString();//函文附檔圖片路徑(附檔在D盤的路徑)
		List<KyzExpectmatmFile>list_kyzexpfile=kyzexpfileSer.findByBillNo(kyzid.getBillNo());
		if(pic_file!=null&&list_kyzexpfile.size()>0){
			map.put("pic_file", pic_file+"\\");
			Map file_map=new HashMap<String,Object>();
			file_map.put("list_kyzexpfile", list_kyzexpfile);
			map.put("file_map", file_map);
		}
		
	
		
		
		JasperHelper.exportmain("auto", map,"matterApplication.jasper", listkyz,billNo, "jasper/audit/");
		
		/**
		 * 發郵件
		 */
		
         //String signernext=visaSigner.trim();
		
		//这个类主要是设置邮件   
		 for(int i=0;i<list_visa.size();i++){
	      MailSenderInfo mailInfo = new MailSenderInfo();
	      //这个类主要来发送邮件   
	        SimpleMailSender sms = new SimpleMailSender();
	      mailInfo.setValidate(true);    
	      /*mailInfo.setUserName("kyuen@yydg.com.cn"); 
	      mailInfo.setPassword("yydgmail");//您的邮箱密码    
	      mailInfo.setFromAddress("<kyuen@yydg.com.cn>");*/    	         
	      mailInfo.setSubject("函文知會"); 	      	      
	    //附件  
	      String[] attachFileNames={"d:/"+billNo+".pdf"};  
	      mailInfo.setAttachFileNames(attachFileNames); 	      
	      /*mailInfo.setContent("加久網絡內部網站(裕元體系)<a href='http://172.17.18.14:8888/Login'>http://172.17.18.14:8888/Login</a>" +
	        		"外部網站:<a href='http://120.86.190.51/Login'>http://120.86.190.51/Login</a>" +
	        		"或<a href='http://121.12.166.101/Login'>http://121.12.166.101/Login</a>");*/
	      mailInfo.setContent("單號為:"+billNo+"的函文已審核完畢,請查看附件.");
           
            	String toAddress=list_visa.get(i).getVisaSigner();
            	mailInfo.setToAddress(toAddress); 
     	        sms.sendHtmlMail(mailInfo);//发送html格式  	
            }
	       
	          File file = new File("d:/" + billNo + ".pdf");
				if (file.exists()) {
					if (file.isFile()) {
						file.delete();
					}
				}
		
		return "addVisabillsAndEmail";
	}
	
	/**
	 * 費用函文
	 * @param local_factNo
	 * @param local_billNo
	 * @param local_visaSort
	 * @throws IOException
	 */
	public void addVisabillsAndEmail(String local_factNo,String local_billNo,String local_visaSort) throws IOException{
		
		/**
		 * 打印
		 */				
		KyzExpectmatmId kyzid=new KyzExpectmatmId();
		kyzid.setBillNo(local_billNo);
		kyzid.setFactNo(local_factNo);
		String factname=webFactSer.selByid(kyzid.getFactNo());
		String factCode="";
		List<KyzExpectmatm> listkyz=kyzSer.findById_Print(kyzid);
		/*if(listkyz.size()==0){
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>alert('單號為"+billNo+"的函文不存在!');window.close()</script>");
			return null;
		}*/
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
		
		/*String type=list.get(0).getVisaType();
		List<KyzVisaflow> list_visa=visaSer.findByType(type);*/
		
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		KyVisabillm vbm=visabillmSer.findById(local_factNo, local_visaSort, local_billNo);
		List<KyVisabills>list_visa=vbm.getKyVisabillses();		
		List<KyzVisaflow>list_visaflow=visaSer.findByType(local_factNo,local_visaSort);
		
		/**
		 * 如果最後不用審核,則去掉
		 */
		int nos=visabillSer.findBillsWithNo(local_visaSort, local_billNo);
		if(nos>0){
			for(int i=0;i<nos;i++){
				list_visa.remove(list_visa.size()-1);
				list_visaflow.remove(list_visaflow.size()-1);
			}
		}
		/*if(list_visa.get(list_visa.size()-3).getFlowMk().equals("N")){
			list_visa.remove(list_visa.size()-1);//(>=1000的最後三位都不要審核，則總長度-3  20150803)
			list_visa.remove(list_visa.size()-1);
			list_visa.remove(list_visa.size()-1);
		}else if(list_visa.get(list_visa.size()-2).getFlowMk().equals("N")){
			list_visa.remove(list_visa.size()-1);
			list_visa.remove(list_visa.size()-1);
		}else if(list_visa.get(list_visa.size()-1).getFlowMk().equals("N")){
			list_visa.remove(list_visa.size()-1);
		}
		
		if(list_visaflow.get(list_visaflow.size()-3).getFlowMk().equals("N")){
			list_visaflow.remove(list_visaflow.size()-1);//(>=1000的最後三位都不要審核，則總長度-3    20150803)
			list_visaflow.remove(list_visaflow.size()-1);
			list_visaflow.remove(list_visaflow.size()-1);
		}else if(list_visaflow.get(list_visaflow.size()-2).getFlowMk().equals("N")){
			list_visaflow.remove(list_visaflow.size()-1);
			list_visaflow.remove(list_visaflow.size()-1);
		}else if(list_visaflow.get(list_visaflow.size()-1).getFlowMk().equals("N")){
			list_visaflow.remove(list_visaflow.size()-1);
		}*/
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
		Map main_map=new HashMap<String,Object>();    /*把list（List<KyzExpectmatm> list=kyzSer.findById_Print(id)）放在一个子表,便于打印  20150804*/
		main_map.put("list_main", listkyz);
		map.put("main_map", main_map);
		
		/*函文附檔*/
		//String pic_file=ServletActionContext.getRequest().getRealPath("/KyzexpFile/"+kyzid.getBillNo()+"/")+"/";//函文附檔圖片路徑
		String pic_file=new File("d:\\KyzexpFile_backup\\"+kyzid.getBillNo()).toString();//函文附檔圖片路徑(附檔在D盤的路徑)
		List<KyzExpectmatmFile>list_kyzexpfile=kyzexpfileSer.findByBillNo(kyzid.getBillNo());
		if(pic_file!=null&&list_kyzexpfile.size()>0){
			map.put("pic_file", pic_file+"\\");
			Map file_map=new HashMap<String,Object>();
			file_map.put("list_kyzexpfile", list_kyzexpfile);
			map.put("file_map", file_map);
		}
		
	
		
		
		JasperHelper.exportmain("auto", map,"matterApplication.jasper", listkyz,local_billNo, "jasper/audit/");
		
		/**
		 * 發郵件
		 * 由於要給所有人(包括不要審核的)發送郵件,所以要重新從數據庫中獲取,而不能使用上面已有的list_visa
		 */
		      
		KyVisabillm vbm2=visabillmSer.findById(local_factNo, local_visaSort, local_billNo);
		List<KyVisabills>list_visa2=vbm2.getKyVisabillses();
		//这个类主要是设置邮件   
		
		String[] attachFileNames = { "d:/" + local_billNo + ".pdf" };// 附件
		for (int i = 0; i < list_visa2.size(); i++) {
			MailSenderInfo mailInfo = new MailSenderInfo();
			// 这个类主要来发送邮件
			SimpleMailSender sms = new SimpleMailSender();
			mailInfo.setValidate(true);
			/*mailInfo.setUserName("kyuen@yydg.com.cn");
			mailInfo.setPassword("yydgmail");// 您的邮箱密码
			mailInfo.setFromAddress("<kyuen@yydg.com.cn>");*/
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
			String emailPwd = webuseremailSer.findEmailPWD(local_factNo,list_visa2.get(i).getVisaSigner());					
			if (emailPwd != null) {
				MailSenderInfo mailInfo2 = new MailSenderInfo();
				// 这个类主要来发送邮件
				SimpleMailSender sms2 = new SimpleMailSender();
				mailInfo2.setValidate(true);
				/*mailInfo2.setUserName("kyuen@yydg.com.cn");
				mailInfo2.setPassword("yydgmail");// 您的邮箱密码
				mailInfo2.setFromAddress("<kyuen@yydg.com.cn>");*/
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
				}
		
	}
	
	/**
	 * 內部聯絡函
	 * @param local_factNo
	 * @param local_billNo
	 * @param local_visaSort
	 * @throws IOException
	 */
	public void addVisabillsAndEmail2(String local_factNo,String local_billNo,String local_visaSort) throws IOException{
		List<KyzContactletter>list=new ArrayList<KyzContactletter>();
		Map<String,Object>map=new HashMap<String,Object>();
		String factname=webFactSer.selByid(local_factNo);
		String factCode="";
		KyzContactletter letter=kyzletterSer.findById(local_factNo,local_billNo);
		/*if(letter==null){
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>alert('單號為"+local_billNo+"的函文不存在!');window.close()</script>");
			return null;
		}else{
			list.add(letter);
		}*/	
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
		/**
		 * 最後個不用審核的,就去掉
		 */
		int nos=visabillSer.findBillsWithNo(local_visaSort, local_billNo);
		if(nos>0){
			for(int i=0;i<nos;i++){
				list_visa.remove(list_visa.size()-1);
				list_visaflow.remove(list_visaflow.size()-1);
			}
		}		
		/*if(list_visa.get(list_visa.size()-3).getFlowMk().equals("N")){//(>=1000的，後面三個都不要簽核   20150803)
			list_visa.remove(list_visa.size()-1);
			list_visa.remove(list_visa.size()-1);
			list_visa.remove(list_visa.size()-1);
		}else if(list_visa.get(list_visa.size()-2).getFlowMk().equals("N")){
			list_visa.remove(list_visa.size()-1);
			list_visa.remove(list_visa.size()-1);
		}else if(list_visa.get(list_visa.size()-1).getFlowMk().equals("N")){
			list_visa.remove(list_visa.size()-1);
		}
		if(list_visaflow.get(list_visaflow.size()-3).getFlowMk().equals("N")){//(>=1000的，後面三個都不要簽核   20150803)
			list_visaflow.remove(list_visaflow.size()-1);
			list_visaflow.remove(list_visaflow.size()-1);
			list_visaflow.remove(list_visaflow.size()-1);
		}else if(list_visaflow.get(list_visaflow.size()-2).getFlowMk().equals("N")){
			list_visaflow.remove(list_visaflow.size()-1);
			list_visaflow.remove(list_visaflow.size()-1);
		}else if(list_visaflow.get(list_visaflow.size()-1).getFlowMk().equals("N")){
			list_visaflow.remove(list_visaflow.size()-1);
		}*/
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
		Map main_map=new HashMap<String,Object>();    /*把list（List<KyzExpectmatm> list=kyzSer.findById_Print(id)）放在一个子表,便于打印  20150804*/
		main_map.put("list_main", list);
		map.put("main_map", main_map);
				
		JasperHelper.exportmain("auto", map,"kyz_contactletter.jasper", list,local_billNo, "jasper/audit/");
		
		/**
		 * 發郵件
		 * 由於要給所有人(包括不要審核的)發送郵件,所以要重新從數據庫中獲取,而不能使用上面已有的list_visa
		 */
		      
		KyVisabillm vbm2=visabillmSer.findById(local_factNo, local_visaSort, local_billNo);
		List<KyVisabills>list_visa2=vbm2.getKyVisabillses();
		//这个类主要是设置邮件   
		String[] attachFileNames = { "d:/" + local_billNo + ".pdf" };// 附件
		for (int i = 0; i < list_visa2.size(); i++) {
			MailSenderInfo mailInfo = new MailSenderInfo();
			// 这个类主要来发送邮件
			SimpleMailSender sms = new SimpleMailSender();
			mailInfo.setValidate(true);
			/*mailInfo.setUserName("kyuen@yydg.com.cn");
			mailInfo.setPassword("yydgmail");// 您的邮箱密码
			mailInfo.setFromAddress("<kyuen@yydg.com.cn>");*/
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
			String emailPwd = webuseremailSer.findEmailPWD(local_factNo,list_visa2.get(i).getVisaSigner());
			if(emailPwd!=null){
				MailSenderInfo mailInfo2 = new MailSenderInfo();
				// 这个类主要来发送邮件
				SimpleMailSender sms2 = new SimpleMailSender();
				mailInfo2.setValidate(true);
				/*mailInfo2.setUserName("kyuen@yydg.com.cn");
				mailInfo2.setPassword("yydgmail");// 您的邮箱密码
				mailInfo2.setFromAddress("<kyuen@yydg.com.cn>");*/
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
				}
								
	}
	
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
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
    	kyzexp.print(id, "C21");

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
							break;
						}
					}
				}
				if(billNo.substring(0,2).equals("CM")&&list_letter!=null){
					for(int j=0;j<list_letter.size();j++){
						if(billNo.equals((String)list_letter.get(j)[1])){
							title=(String)list_letter.get(j)[2];
							break;
						}
					}
				}
				list.get(i).setMemo(title);
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
			for(int j=0;j<list_type.size();j++){//for2
				WebType type=list_type.get(j);
				if(factno.equals(type.getId().getFactNo())&&visaSort.substring(0,2).equals(type.getId().getTypeNo())){
					typename=type.getTypeName();					
					break;
				}
			}//for2
			vbs.getId().getKyVisabillm().setColTemp(typename);
		}//for1				
	}
	
	public String minusvisabills2() throws IOException{
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
  				this.addVisabillsAndEmail(factNo, billNo, visaSort);
  			}else{
  				this.addVisabillsAndEmail2(factNo, billNo, visaSort);
  			}
		}
		return "minusvisabills2";
	}
	
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
  				this.addVisabillsAndEmail(factNo, billNo, visaSort);
  			}else{
  				this.addVisabillsAndEmail2(factNo, billNo, visaSort);
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
		String emailPwd = webuseremailSer.findEmailPWD(factNo,signerNext);//備簽人Email
		list_email.add(signerNext);
		if(emailPwd!=null){
			list_email.add(emailPwd);
		}
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
    


}
