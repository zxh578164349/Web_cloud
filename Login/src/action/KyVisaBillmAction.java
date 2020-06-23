package action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import services.IWebFormulaServices;
import services.IWebTypeServices;
import services.IWebUserService;
import services.IWebremittancelistServices;
import services.IWebuserEmailAServices;
import services.IWebuserEmailServices;
import util.GlobalMethod;
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
import entity.WebFormula;
import entity.WebType;
import entity.WebUser;
import entity.Webremittancelist;
import entity_temp.VisabillsTemp;

/**
 * 
* 項目名稱：WebLogin   
* 類名稱：KyVisaBillmAction   
* 類描述：函文審核    函文审核状况
* 創建人：KY2
 */
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
	private IWebremittancelistServices webremiSer;
	private IKyzExpectmatmFileServices kyzexpfileSer;
	private IWebFormulaServices webformulaser;
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
	private String visaMk;//審核的最終狀態(Y已審核,N未審核,T退回)
	private String userName;
	private javax.servlet.http.HttpServletResponse response;
	private String yymmdd;
	private String yymmdd2;
	private String email;
	private String ajaxResult;//申請函文時返回的ajax結果,   0:提交成功       1:提交失敗
	private int psMk;//加簽時，放在前面，還是放在後面（0:前面     1:後面）
	private InputStream fileInput;
	private String fileName;
	private String lookordown;
	private String title;//標題
	private String bigType;//大分類 EM:普通函文   CM:內部函文
	
	
	
	public String getBigType(){
		return bigType;
	}
	public void setBigType(String bigType){
		this.bigType=bigType;
	}
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title=title;
	}	
	public void setWebformulaser(IWebFormulaServices webformulaser) {
		this.webformulaser = webformulaser;
	}
	public String getLookordown() {
		return lookordown;
	}
	public void setLookordown(String lookordown) {
		this.lookordown = lookordown;
	}
	public String getFileName(){
		return fileName;
	}
	public void setFileName(String fileName){
		this.fileName=fileName;
	}
	public InputStream getFileInput(){
		return fileInput;
	}
	public void setFileInput(InputStream fileInput){
		this.fileInput=fileInput;
	}
	public int getPsMk() {
		return psMk;
	}
	public void setPsMk(int psMk) {
		this.psMk = psMk;
	}
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

	public void setWebremiSer(IWebremittancelistServices webremiSer) {
		this.webremiSer = webremiSer;
	}
	
	
	
	
	/********************************函文审核******************************************/
	/**
	 * 函文审核-分頁查詢
	 * @return
	 */	
	public String findPageBean(){
		//ActionContext.getContext().getApplication().clear();
		ActionContext.getContext().getSession().remove("public_factNo");
		ActionContext.getContext().getSession().remove("public_billNo");
		ActionContext.getContext().getSession().remove("public_visaMk");
		ActionContext.getContext().getSession().remove("public_visaSort");
		ActionContext.getContext().getSession().remove("public_timeCreate");
		ActionContext.getContext().getSession().remove("public_timeCreate2");
		ActionContext.getContext().getSession().remove("public_title");
		ActionContext.getContext().getSession().remove("public_bigType");
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");		
		bean=visabillSer.findPageBean_tw(20,page, visaMk, factNo, billNo,visaSort,yymmdd,yymmdd2,user,title,bigType);
		
		//this.getKyzTitle(bean);
		//this.getTypeName(bean);
		return "beanList";
	}
	
	/**
	 * 函文审核-分頁查詢2
	 * @return
	 */	
	public String findPageBean2(){
		//ActionContext.getContext().getApplication().clear();		
		
			ActionContext.getContext().getSession().put("public_factNo", factNo);
			billNo=billNo.trim();
			ActionContext.getContext().getSession().put("public_billNo", billNo);
			ActionContext.getContext().getSession().put("public_visaMk", visaMk);
			ActionContext.getContext().getSession().put("public_visaSort", visaSort);	
			ActionContext.getContext().getSession().put("public_timeCreate", yymmdd);			
			ActionContext.getContext().getSession().put("public_timeCreate2", yymmdd2);
			ActionContext.getContext().getSession().put("public_title",title);
			ActionContext.getContext().getSession().put("public_bigType",bigType);
		
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");		
		bean=visabillSer.findPageBean_tw(20,page, visaMk, factNo, billNo,visaSort,yymmdd,yymmdd2,user,title,bigType);		
		//this.getKyzTitle(bean);
		//this.getTypeName(bean);
		return "beanList1";		
	}
	
	/**
	 * 函文审核-分頁查詢3
	 * @return
	 */	
	public String findPageBean3(){
		factNo=(String)ActionContext.getContext().getSession().get("public_factNo");
		billNo=(String)ActionContext.getContext().getSession().get("public_billNo");
		visaMk=(String)ActionContext.getContext().getSession().get("public_visaMk");
		visaSort=(String)ActionContext.getContext().getSession().get("public_visaSort");
		yymmdd=(String)ActionContext.getContext().getSession().get("public_timeCreate");
		yymmdd2=(String)ActionContext.getContext().getSession().get("public_timeCreate2");
		title=(String)ActionContext.getContext().getSession().get("public_title");
		bigType=(String)ActionContext.getContext().getSession().get("public_bigType");
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");				
		bean=visabillSer.findPageBean_tw(20,page, visaMk, factNo, billNo,visaSort,yymmdd,yymmdd2,user,title,bigType);		
		//this.getKyzTitle(bean);
		//this.getTypeName(bean);
		return "beanList1";
	}
	/********************************函文审核******************************************/
	
	/********************************函文审核状况******************************************/
	/**
	 * 函文审核状况-分頁查詢
	 * @return
	 */	
	public String findPageBean_1(){
		//ActionContext.getContext().getApplication().clear();
		ActionContext.getContext().getSession().remove("public_factNo");
		ActionContext.getContext().getSession().remove("public_billNo");
		ActionContext.getContext().getSession().remove("public_visaMk");
		ActionContext.getContext().getSession().remove("public_visaSort");
		ActionContext.getContext().getSession().remove("public_timeCreate");
		ActionContext.getContext().getSession().remove("public_timeCreate2");
		ActionContext.getContext().getSession().remove("public_title");
		ActionContext.getContext().getSession().remove("public_bigType");
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");		
		bean=visabillSer.findPageBean(20,page, visaMk, factNo, billNo,visaSort,yymmdd,yymmdd2,user,title,bigType);
		//this.getTypeName(bean);
		//this.getKyzTitle(bean);
		return "beanList_1";
	}
	
	/**
	 * 函文审核状况-分頁查詢2
	 * @return
	 */
	public String findPageBean2_1(){
		//ActionContext.getContext().getApplication().clear();
			ActionContext.getContext().getSession().put("public_factNo", factNo);
			billNo=billNo.trim();
			ActionContext.getContext().getSession().put("public_billNo", billNo);
			ActionContext.getContext().getSession().put("public_visaMk", visaMk);
			ActionContext.getContext().getSession().put("public_visaSort", visaSort);
			ActionContext.getContext().getSession().put("public_timeCreate", yymmdd);
			ActionContext.getContext().getSession().put("public_timeCreate2", yymmdd2);
			ActionContext.getContext().getSession().put("public_title",title);
			ActionContext.getContext().getSession().put("public_bigType",bigType);
		
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");		
		bean=visabillSer.findPageBean(20,page, visaMk, factNo, billNo,visaSort,yymmdd,yymmdd2,user,title,bigType);
		//this.getTypeName(bean);
		//this.getKyzTitle(bean);
		return "beanList1_1";
	}
	
	/**
	 * 函文审核状况-分頁查詢3
	 * @return
	 */
	public String findPageBean3_1(){
		factNo=(String)ActionContext.getContext().getSession().get("public_factNo");
		billNo=(String)ActionContext.getContext().getSession().get("public_billNo");
		visaMk=(String)ActionContext.getContext().getSession().get("public_visaMk");
		visaSort=(String)ActionContext.getContext().getSession().get("public_visaSort");
		yymmdd=(String)ActionContext.getContext().getSession().get("public_timeCreate");
		yymmdd2=(String)ActionContext.getContext().getSession().get("public_timeCreate2");
		title=(String)ActionContext.getContext().getSession().get("public_title");
		bigType=(String)ActionContext.getContext().getSession().get("public_bigType");
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");				
		bean=visabillSer.findPageBean(20,page, visaMk, factNo, billNo,visaSort,yymmdd,yymmdd2,user,title,bigType);
		//this.getTypeName(bean);
		//this.getKyzTitle(bean);
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
		if(vbm==null){
			response.setContentType("text/html;charset=utf-8");
			try {
				response.getWriter().print("<script>alert('函文不存在或已刪除');</script>");
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ActionContext.getContext().getSession().put("vbm", vbm);//爲了在函文簽核的彈出窗口顯示已簽人的備註信息
		return "findById_email";
	}
	public String findById_email2(){//email直接審核，不需要登錄帳號（普通電腦）findById_email2
		vbm=visabillmSer.findById(factNo, visaSort, billNo);
		if(vbm==null){
			response.setContentType("text/html;charset=utf-8");
			try {
				response.getWriter().print("<script>alert('函文不存在或已刪除');</script>");
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ActionContext.getContext().getSession().put("vbm", vbm);//爲了在函文簽核的彈出窗口顯示已簽人的備註信息
		return "findById_email2";
	}
	
	/****************************************20170517不需要审核，不需要登录***********************************************/
	public String findById_email_gj(){//email不需要審核，不需要登錄帳號（手機平板）
		vbm=visabillmSer.findById(factNo, visaSort, billNo);
		if(vbm==null){
			response.setContentType("text/html;charset=utf-8");
			try {
				response.getWriter().print("<script>alert('函文不存在或已刪除');</script>");
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ActionContext.getContext().getSession().put("vbm", vbm);//爲了在函文簽核的彈出窗口顯示已簽人的備註信息
		return "findById_email_gj";
	}
	public String findById_email2_gj(){//email不需要審核，不需要登錄帳號（普通電腦）findById_email2
		vbm=visabillmSer.findById(factNo, visaSort, billNo);
		if(vbm==null){
			response.setContentType("text/html;charset=utf-8");
			try {
				response.getWriter().print("<script>alert('函文不存在或已刪除');</script>");
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ActionContext.getContext().getSession().put("vbm", vbm);//爲了在函文簽核的彈出窗口顯示已簽人的備註信息
		return "findById_email2_gj";
	}
	/****************************************20170517不需要审核，不需要登录***********************************************/
		
	/**
	 * 登錄web審核和郵件審核
	 * 20160322
	 * @throws IOException
	 */
	public String add() throws IOException{
		try{
			String isLastMan="N";//標識是否最後一箇簽核人，如果是則不需要發送郵件(Y:是     N:否)
			KyVisabillm vbm=visabillmSer.findById2(factNo, visaSort, billNo);
			if(itemNo==null||itemNo.equals("")){
				itemNo=vbm.getItemNext();
			}
						
			SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd_HH:mm");//大写H表示24小时制      小写h表示12小时制      20170224
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
					isLastMan="Y";//標識最後一箇簽核人20161229
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
					
					if(lastmk.equals("N")){										
						if(num_temp==vbm.getKyVisabillses().size()){
							vbm.setMemoMk(memo);							
						}
					}
					if(lastmk.equals("T")){
						vbm.setMemoMk(memo);																			
					}
					visabillmSer.add(vbm);
					
					/****************如果是緊急函文，就立即發送郵件 (目前只有普通函文和內部函文有緊急屬性)20161227****************/
					List<KyVisabillm>list=new ArrayList<KyVisabillm>();
					list.add(vbm);
					if("N".equals(isLastMan)){
						if(billNo.substring(0,2).equals("EM")){
							if("0".equals(vbm.getKyzexp().getEmerMk())){
								ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-dao.xml","spring-services.xml","spring-projectconfig.xml"});
								GlobalMethod.sendEmailA2(ac,list);
							}
						}
						if(billNo.substring(0,2).equals("CM")){
							if("0".equals(vbm.getKyzletter().getEmerMk())){
								ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-dao.xml","spring-services.xml","spring-projectconfig.xml"});
								GlobalMethod.sendEmailA2(ac,list);
							}
						}	
					}
														
					
					/****************如果是緊急函文，就立即發送郵件 20161227****************/
					
					ajaxResult="0";											
		}catch(Exception e){
			ajaxResult="1";
			System.out.println(e);						
		}
			return "add";								
	}
	
	
	/**
	 * 加簽
	 * @return
	 */
	public String addvisabills(){		
		try{			
			KyVisabillm vbm=new KyVisabillm();
			vbm=visabillmSer.findById(factNo, visaSort, billNo);
			List<KyVisabills>list=vbm.getKyVisabillses();
			int startnum=Integer.parseInt(itemNo);
			String nextnum="";
			KyVisabills vbs=new KyVisabills();
			KyVisabillsId id=new KyVisabillsId();
			
			KyVisabillm kyvbm=new KyVisabillm();
			KyVisabillmId vbmid=new KyVisabillmId();
			switch(psMk){//switch
			case 0:	//加在前面															
				vbmid.setBillNo(billNo);
				vbmid.setFactNo(factNo);
				vbmid.setVisaSort(visaSort);
				kyvbm.setId(vbmid);
				id.setKyVisabillm(kyvbm);
				id.setItemNo(itemNo);
				vbs.setId(id);
				vbs.setVisaMk("N");
				vbs.setFlowMk("Y");
				vbs.setVisible("Y");
				if(visaRank!=null&&!visaRank.equals("")){
					vbs.setVisaRank(visaRank.trim());
				}
				if(visaSigner!=null&&!visaSigner.equals("")){
					vbs.setVisaSigner(visaSigner.trim());
				}	
					for(int i=startnum-1;i<list.size();i++){
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
					
					if(itemNo.equals(vbm.getItemNext())){//如果在當前簽核人的前面加簽，則要修改當前簽核人為要加簽的人20170927
						vbm.setSignerNext(visaSigner);
					}
				break;
			case 1://加在後面
				if((startnum+1)>9){
					nextnum=(startnum+1)+"";
				}else{
					nextnum="0"+(startnum+1);
				}								
				vbmid.setBillNo(billNo);
				vbmid.setFactNo(factNo);
				vbmid.setVisaSort(visaSort);
				kyvbm.setId(vbmid);
				id.setKyVisabillm(kyvbm);
				id.setItemNo(nextnum);
				vbs.setId(id);
				vbs.setVisaMk("N");
				vbs.setFlowMk("Y");
				vbs.setVisible("Y");
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
				break;
			}//switcth							
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
    
    	
	public void getKyzTitle(PageBean bean){
		List<KyVisabills>list=bean.getList();
		List<String>list_billnos=new ArrayList<String>();
		for(int i=0;i<list.size();i++){
			list_billnos.add(list.get(i).getId().getKyVisabillm().getId().getBillNo());
		}
		List<Object[]>list_kyz=kyzSer.findTitle(list_billnos);
		List<Object[]>list_letter=kyzletterSer.findTitle(list_billnos);		
		String title="";
		if(list.size()>0){			
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
			
	  	    /**
	  	     * 如果减签的刚好函文的下一位签核人，
	  	     * （1）则要email通知他不要签
	  	     * （2）并且更新下一位签核人,并且把新sigerNext赋给的sigerNext,主表的itemNext不需要变；      
	  	     * （3）email通知新的下一位签核
	  	     */
	  	    
	  	    //*********************************************如果当前减签人刚好是函文的下一位签核人**********************************************//
			//if(visaNext.equals(singer)&&flowMk.equals("Y")){
			if(itemNo.equals(vbm.getItemNext())&&flowMk.equals("Y")){
				//*********发送email通知已经减签***********//				  		  
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
	      		 GlobalMethod.sendEmail_minus(factNo,billNo,visaSort,bills.getVisaSigner());
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
				//System.out.println(itemno+"---"+bls.getVisaRank()+"----"+bls.getFlowMk());
			}			
			ajaxResult="0";
		}catch(Exception e){
			ajaxResult="1";
			//System.out.println(e);
			e.printStackTrace();
		}
		
		return "minusvisabills2";
	}
			
	public String strToLow(String str){
		return str.toLowerCase();
	}
	
	
	/**
	 * 手動發送email
	 * @throws IOException 
	 */
	public void sendEmail() throws IOException{
		try{
			response.setContentType("text/html;charset=utf-8");
			ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-dao.xml","spring-services.xml","spring-projectconfig.xml"});
			IKyVisabillmServices visabillmSer=(IKyVisabillmServices)ac.getBean("visabillmSer");	
			List<KyVisabillm>list_vbm=visabillmSer.findByBillNo2(billNo);		
			GlobalMethod.sendEmailA(ac,list_vbm);			
			response.getWriter().print("<script>window.parent.layer.alert('發送成功',1)</script>");
		}catch(Exception e){
			response.getWriter().print("<script>window.parent.layer.alert('發送失敗',3)</script>");
		}		
	}
    	
	/**
	 * 導出函文
	 * @throws Exception
	 */
	public void printList() throws Exception{
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		List<KyVisabills>list=visabillSer.findtoprint(visaMk,factNo,billNo,visaSort,yymmdd,yymmdd2,user,title,bigType);
		response.setContentType("text/html;charset=utf-8");
		if(list.size()>21){			
			response.getWriter().print("<script>window.parent.alert('函文數目超出上限,禁止導出')</script>");
		}else if(list.size()==0){
			response.getWriter().print("<script>window.parent.alert('該時間段,沒有相關函文')</script>");
		}else{
			printList_init(list);
		}	
	}
	
	public void printList_init(List<KyVisabills>list) throws Exception{
		List<File>files=new ArrayList<File>();
		for(KyVisabills obj:list){
			Map<String,Object>map_result=null;
			String local_factNo=obj.getId().getKyVisabillm().getId().getFactNo();
			String local_billNo=obj.getId().getKyVisabillm().getId().getBillNo();
			String local_visaSort=obj.getId().getKyVisabillm().getId().getVisaSort();
			if(local_billNo.substring(0,2).equals("EM")){
				map_result=kyzSer.print(local_factNo, local_billNo,local_visaSort,obj.getId().getKyVisabillm());
				if(map_result!=null&&map_result.size()>0){
					map=(Map<String,Object>)map_result.get("map");
					List<KyzExpectmatm>listkyz=(List<KyzExpectmatm>)map_result.get("list");
					JasperHelper.exportmain("auto", map,"matterApplication.jasper", listkyz,local_billNo, "jasper/audit/");
				}
			}
			if(local_billNo.substring(0,2).equals("CM")){
				map_result=kyzletterSer.print(local_factNo, local_billNo, local_visaSort,obj.getId().getKyVisabillm());
				if(map_result!=null&&map_result.size()>0){
					map=(Map<String,Object>)map_result.get("map");
					List<KyzContactletter>listkyzc=(List<KyzContactletter>)map_result.get("list");
					JasperHelper.exportmain("auto", map,"kyz_contactletter.jasper", listkyzc,local_billNo, "jasper/audit/");
				}
			}
			if(local_billNo.substring(0,2).equals("BM")){
				map_result=webbussletterSer.print(local_factNo, local_billNo, local_visaSort,obj.getId().getKyVisabillm());
				if(map_result!=null&&map_result.size()>0){
					map=(Map<String,Object>)map_result.get("map");
					List<WebBussinessletter>listbusss=(List<WebBussinessletter>)map_result.get("list");
					JasperHelper.exportmain("auto", map,"webbussletter.jasper", listbusss,local_billNo, "jasper/audit/");
				}
			}
			if(local_billNo.substring(0,2).equals("RM")){
				map_result=webremiSer.print(local_factNo, local_billNo, local_visaSort, obj.getId().getKyVisabillm());
				map=(Map<String,Object>)map_result.get("map");
				List<Webremittancelist>listremit=(List<Webremittancelist>)map_result.get("list");
				JasperHelper.exportmain("auto", map, "webremittancelist.jasper", listremit, local_billNo, "jasper/audit/");
			}
			if(local_billNo.substring(0,2).equals("GJ")){
				map_result=webformulaser.print(local_factNo,local_billNo,obj.getId().getKyVisabillm());
				map=(Map<String,Object>)map_result.get("map");
				List<WebFormula>list_webformula=(List<WebFormula>)map_result.get("list");
				JasperHelper.exportmain("auto", map, "web_formula.jasper", list_webformula, local_billNo, "jasper/audit/");
			}			
			files.add(new File("D:/"+local_billNo+".pdf"));									
		}		
			GlobalMethod.downLoadFiles(files, null, response);
			for(File file:files){
				if(file.exists()){
					if(file.isFile()){
						file.delete();
					}
				}
			}
	}
	
	/**
	 * 查看函文附檔
	 * @Title: lookFile
	 * @Description: TODO
	 * @param @return
	 * @return String
	 * @throws FileNotFoundException 
	 * @throws
	 * @author web
	 * @date 2016/11/17
	 */
	public String lookFile() throws FileNotFoundException{
		fileInput=GlobalMethod.getFileInput("D:\\KyzexpFile_backup\\"+billNo+"\\"+fileName);
		return "lookFile";
	}
	
	
	/**
	 * 導出所類型函文
	 * @throws IOException
	 */
	public void print_all() throws IOException{
		if(billNo.substring(0, 2).equals("EM")){
			this.print_kyz();
		}
		if(billNo.substring(0, 2).equals("CM")){
			this.print_letter();
		}
		if(billNo.substring(0, 2).equals("BM")){
			this.print_buss();
		}
		if(billNo.substring(0, 2).equals("RM")){
			this.print_rm();
		}
		if(billNo.substring(0, 2).equals("GJ")){
			this.print_formula();
		}
	}
	
	/**
	 * 函文申請打印
	 * @param factNo
	 * @param billNo
	 * @param sort
	 * @param vbm
	 * @throws IOException
	 */
    public void print_kyz() throws IOException{		
		Map<String,Object>map_result=kyzSer.print(factNo,billNo, visaSort,null);		
		if(map_result!=null&&map_result.size()>0){
			map=(Map<String,Object>)map_result.get("map");
			List<KyzExpectmatm>list=(List<KyzExpectmatm>)map_result.get("list");
			if(lookordown!=null){
				if(lookordown.equals("look")){
					JasperHelper.exportmain("line", map,"matterApplication.jasper", list,billNo, "jasper/audit/");
				}else{
					JasperHelper.exportmain("pdf", map,"matterApplication.jasper", list,billNo, "jasper/audit/");
				}
			}else{
				JasperHelper.exportmain("pdf", map,"matterApplication.jasper", list,billNo, "jasper/audit/");
			}
		}else{
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>alert('單號為"+billNo+"的函文不存在!');window.close()</script>");
		}						
				
	}
    
    /**
	 * 內部聯絡函打印
	 * @param factNo
	 * @param billNo
	 * @param sort
	 * @throws IOException
	 */
	public void print_letter() throws IOException{		
		Map<String,Object>map_result=kyzletterSer.print(factNo, billNo, visaSort,null);
		if(map_result!=null&&map_result.size()>0){
			Map<String,Object>map=(Map<String,Object>)map_result.get("map");
			List<KyzContactletter>list=(List<KyzContactletter>)map_result.get("list");
			if(lookordown!=null){
				if(lookordown.equals("look")){
					JasperHelper.exportmain("line", map,"kyz_contactletter.jasper", list,billNo, "jasper/audit/");
				}else{
					JasperHelper.exportmain("pdf", map,"kyz_contactletter.jasper", list,billNo, "jasper/audit/");
				}
			}else{
				JasperHelper.exportmain("pdf", map,"kyz_contactletter.jasper", list,billNo, "jasper/audit/");
			}
		}								
	}
	
	/**
	 * 出差函文打印
	 * @param factNo
	 * @param billNo
	 * @param visaSort
	 * @throws IOException
	 */
   public void print_buss() throws IOException{		
		Map<String,Object>map_result=webbussletterSer.print(factNo, billNo, visaSort,null);
		if(map_result!=null&&map_result.size()>0){
			Map<String,Object>map=(Map<String,Object>)map_result.get("map");
			List<WebBussinessletter>list=(List<WebBussinessletter>)map_result.get("list");
			if(lookordown!=null){
				if(lookordown.equals("look")){
					JasperHelper.exportmain("line", map,"webbussletter.jasper", list,billNo, "jasper/audit/");
				}else{
					JasperHelper.exportmain("pdf", map,"webbussletter.jasper", list,billNo, "jasper/audit/");
				}
			}else{
				JasperHelper.exportmain("pdf", map,"webbussletter.jasper", list,billNo, "jasper/audit/");
			}
		}										
	}
   
   /**
    * 湘威費用清單函文打印
    * @throws IOException
    */
   public void print_rm() throws IOException{				
		Map<String,Object>map_result=webremiSer.print(factNo, billNo, visaSort,null);
		if(map_result!=null&&map_result.size()>0){
			Map<String,Object>map=(Map<String,Object>)map_result.get("map");
			List<Webremittancelist>list=(List<Webremittancelist>)map_result.get("list");
			if(lookordown!=null){
				if(lookordown.equals("look")){
					JasperHelper.exportmain("line", map,"webremittancelist.jasper", list,billNo, "jasper/audit/");
				}else{
					JasperHelper.exportmain("pdf", map,"webremittancelist.jasper", list,billNo, "jasper/audit/");
				}
			}else{
				JasperHelper.exportmain("pdf", map,"webremittancelist.jasper", list,billNo, "jasper/audit/");
			}
		}										
	}
   
   /**
    * 配方系統函文
    * @throws IOException
    */
   public void print_formula() throws IOException{		
		Map<String,Object>map_result=webformulaser.print(factNo,billNo,null);		
		if(map_result!=null&&map_result.size()>0){
			map=(Map<String,Object>)map_result.get("map");
			List<KyzExpectmatm>list=(List<KyzExpectmatm>)map_result.get("list");
			if(lookordown!=null){
				if(lookordown.equals("look")){
					JasperHelper.exportmain("line", map,"web_formula.jasper", list,billNo, "jasper/audit/");
				}else{
					JasperHelper.exportmain("pdf", map,"web_formula.jasper", list,billNo, "jasper/audit/");
				}
			}else{
				JasperHelper.exportmain("pdf", map,"web_formula.jasper", list,billNo, "jasper/audit/");
			}
		}else{
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>alert('單號為"+billNo+"的函文不存在!');window.close()</script>");
		}						
				
	}
   
   public String findKyVisaBills_Int(){
	   int result=visabillSer.findKyVisaBills_Int();	
	   ajaxResult=result+"";
	   return "findKyVisaBills_Int";
   }
	
	
}
