package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import mail.MailSenderInfo;
import mail.SimpleMailSender;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.spreada.utils.chinese.ZHConverter;
import com.sun.jndi.toolkit.url.Uri;

import services.IKyVisaBillsServices;
import services.IKyVisabillmServices;
import services.IKyzExpectmatmFileServices;
import services.IKyzExpectmatmLogServices;
import services.IKyzExpectmatmServices;
import services.IKyzExpectmatmsServices;
import services.IKyzVisaFlowServices;
import services.IWebFactServices;
import services.IWebTypeServices;
import services.IWebUserService;
import services.IWebuserEmailServices;
import util.JasperHelper;
import util.PageBean;
import entity.KyVisabillm;
import entity.KyVisabills;
import entity.KyzContactletter;
import entity.KyzExpectmatm;
import entity.KyzExpectmatmFile;
import entity.KyzExpectmatmId;
import entity.KyzExpectmatmLog;
import entity.KyzExpectmats;
import entity.KyzVisaflow;
import entity.WebType;
import entity.WebUser;
import entity_temp.VisabillsTemp;

public class KyzExpcetmatmAction extends ActionSupport implements ServletResponseAware{
	private KyzExpectmatm kyz;
	private IWebFactServices webFactSer;
	private IKyzVisaFlowServices visaSer;
	private IWebUserService webUserService;
	private IWebuserEmailServices webuseremailSer;
	private IWebTypeServices webtypeSer;
	private KyzExpectmatmId id;
    private PageBean bean;
    private String factNo;
    private String yymm;
    private int page;
    private int maxNum;
    private String yymmdd;
    private String factCode;
    private javax.servlet.http.HttpServletResponse response;
    private List<File> files;
    private List<String> filesFileName;
    private List<String> filesContentType;
    private IKyzExpectmatmFileServices kyzexpfileSer;
    private String lookordown;
    private String fileName;
    private InputStream fileInput;
    private String userNm;
    private String yymmdd2;
    private String itemNo;
    private String readMk;//標識返回函文查看頁面(Y)，還是返回函文提交頁面(N)
    private String visa_mk;
    private IKyzExpectmatmLogServices kyzExpLogSer;
    
    
    public String getVisa_mk() {
		return visa_mk;
	}

	public void setVisa_mk(String visa_mk) {
		this.visa_mk = visa_mk;
	}

	public String getReadMk() {
		return readMk;
	}

	public void setReadMk(String readMk) {
		this.readMk = readMk;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getYymmdd2() {
		return yymmdd2;
	}

	public void setYymmdd2(String yymmdd2) {
		this.yymmdd2 = yymmdd2;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) throws Exception {
		this.fileName=new String(fileName.getBytes("ISO-8859-1"), "utf-8");
	}

	public InputStream getFileInput() {
		 String pic_file=new File("d:\\KyzexpFile_backup\\"+billNo).toString();
		 return ServletActionContext.getServletContext().getResourceAsStream(pic_file+"\\"+fileName);
	}

	public void setFileInput(InputStream fileInput) {
		this.fileInput = fileInput;
	}

	public String getLookordown() {
		return lookordown;
	}

	public void setLookordown(String lookordown) {
		this.lookordown = lookordown;
	}

	public void setKyzexpfileSer(IKyzExpectmatmFileServices kyzexpfileSer) {
		this.kyzexpfileSer = kyzexpfileSer;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public List<String> getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(List<String> filesFileName) {
		this.filesFileName = filesFileName;
	}

	public List<String> getFilesContentType() {
		return filesContentType;
	}

	public void setFilesContentType(List<String> filesContentType) {
		this.filesContentType = filesContentType;
	}


	public final String getIsnull() {
		return isnull;
	}

	public final void setIsnull(String isnull) {
		this.isnull = isnull;
	}

	private String title;
    private String billNo;
    private String visaSort;
    private List<String> cbox;   
    private IKyzExpectmatmsServices kyzsSer;
    private String isnull; 
    private IKyVisaBillsServices visabillSer;
    private IKyVisabillmServices visabillmSer;
    private List<KyVisabills>visabills;
    private KyVisabillm vbm;
    

	public String getVisaSort() {
		return visaSort;
	}
	public void setVisaSort(String visaSort) {
		this.visaSort = visaSort;
	}

	public KyVisabillm getVbm() {
		return vbm;
	}

	public void setVbm(KyVisabillm vbm) {
		this.vbm = vbm;
	}

	

	public List<KyVisabills> getVisabills() {
		return visabills;
	}

	public void setVisabills(List<KyVisabills> visabills) {
		this.visabills = visabills;
	}

	public void setVisabillSer(IKyVisaBillsServices visabillSer) {
		this.visabillSer = visabillSer;
	}
	
	

	public void setVisabillmSer(IKyVisabillmServices visabillmSer) {
		this.visabillmSer = visabillmSer;
	}

	public final void setKyzsSer(IKyzExpectmatmsServices kyzsSer) {
		this.kyzsSer = kyzsSer;
	}
	 public void setVisaSer(IKyzVisaFlowServices visaSer) {
			this.visaSer = visaSer;
		}

	public final List<String> getCbox() {
		return cbox;
	}

	public final void setCbox(List<String> cbox) {
		this.cbox = cbox;
	}

	public final String getBillNo() {
		return billNo;
	}

	public final void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public final String getFactCode() {
		return factCode;
	}

	public final void setFactCode(String factCode) {
		this.factCode = factCode;
	}

	public final String getTitle() {
		return title;
	}

	public final void setTitle(String title) {
		this.title = title;
	}

	public IWebFactServices getWebFactSer() {
		return webFactSer;
	}

	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}

	public String getYymmdd() {
		return yymmdd;
	}

	public void setYymmdd(String yymmdd) {
		this.yymmdd = yymmdd;
	}

	public int getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
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

	public String getYymm() {
		return yymm;
	}

	public void setYymm(String yymm) {
		this.yymm = yymm;
	}

	public KyzExpectmatmId getId() {
		return id;
	}

	public void setId(KyzExpectmatmId id) {
		this.id = id;
	}

	public KyzExpectmatm getKyz() {
		return kyz;
	}

	public void setKyz(KyzExpectmatm kyz) {
		this.kyz = kyz;
	}

	private IKyzExpectmatmServices kyzSer;

	private Map<String, Object> map;

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public void setKyzSer(IKyzExpectmatmServices kyzSer) {
		this.kyzSer = kyzSer;
	}
	
	

	public void setWebUserService(IWebUserService webUserService) {
		this.webUserService = webUserService;
	}
	
	

	public void setWebuseremailSer(IWebuserEmailServices webuseremailSer) {
		this.webuseremailSer = webuseremailSer;
	}
	
	

	public void setWebtypeSer(IWebTypeServices webtypeSer) {
		this.webtypeSer = webtypeSer;
	}
	
	

	public void setKyzExpLogSer(IKyzExpectmatmLogServices kyzExpLogSer) {
		this.kyzExpLogSer = kyzExpLogSer;
	}

	public String add() throws Exception  {
		/*文件上傳驗證*/
		if(files!=null){
			for(int i=0;i<files.size();i++){
				if(files.get(i)!=null){
					long filesize=files.get(i).length();
					String filetype=filesFileName.get(i).substring(filesFileName.get(i).lastIndexOf(".")).toLowerCase();
					if(filesize>5120000){
						response.setContentType("text/html;charset=utf-8");
						response.getWriter().print("<script>alert('文件不可超過5M!');window.opener=null;window.open('','_self');window.close()</script>");
						return null;
					}
					if(!filetype.equals(".bmp")&&!filetype.equals(".jpg")){
						response.setContentType("text/html;charset=utf-8");
						response.getWriter().print("<script>alert('只允許jpg和bmp圖片!');window.opener=null;window.open('','_self');window.close()</script>");
						return null;
					}
					
				}
			}
		}
		
		/*文件上傳*/
		if(files!=null){//不為空代表有上傳附檔,不能寫成files.size()>0,否則報空指針
			kyz.setFilesYn("1");//標示是否帶有附檔
			//File uploadFile=new File(ServletActionContext.getServletContext().getRealPath("KyzexpFile\\"+kyz.getId().getBillNo()));//附檔上傳到項目
			File uploadFile_backup=new File("d:\\KyzexpFile_backup\\"+kyz.getId().getBillNo());//附檔上傳到D盤(為了避免更新項目時丟失附檔,所在上傳到D盤)
			/*if(!uploadFile.exists()){
				uploadFile.mkdirs();
			}*/
			if(!uploadFile_backup.exists()){
				uploadFile_backup.mkdirs();
			}
			for(int i=0;i<files.size();i++){							
				if(files.get(i)!=null){									
					FileInputStream in=new FileInputStream(files.get(i));
					//FileOutputStream out=new FileOutputStream(uploadFile+"\\"+filesFileName.get(i));
					FileOutputStream out_backup=new FileOutputStream(uploadFile_backup+"\\"+filesFileName.get(i));//備份
					byte[]b=new byte[1024];
					int length=0;
					while((length=in.read(b))>0){
						//out.write(b,0,length);
						out_backup.write(b,0,length);//備份
					}
																									
					KyzExpectmatmFile kyzexpFile=new KyzExpectmatmFile();//函文附檔
					kyzexpFile.setBillno(kyz.getId().getBillNo());
					kyzexpFile.setFilename(filesFileName.get(i));
					WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
					String username=user.getName();
					kyzexpFile.setUsername(username);
					kyzexpfileSer.add(kyzexpFile);
				}
			}
		}
		
		
		
		String result=null;
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		Date date;
		try {
			date = format.parse(yymmdd);
			kyz.setTimeCreate(date);
				        
			if(isnull.equals("isNull")){//start if
				//String fact=(String)ActionContext.getContext().getSession().get("factNo");
				KyzExpectmatm temp=kyzSer.findById(kyz.getId().getFactNo(), kyz.getId().getBillNo());
				if(temp==null){//if
					kyzSer.add(kyz);							
					result="add";
					
					KyVisabillm vbm=visabillmSer.findById(kyz.getId().getFactNo(), kyz.getVisaType(), kyz.getId().getBillNo());							
					String emailUrl_in="http://203.85.73.161/Login/vbm_findById_email?visaSort="+kyz.getVisaType()+"&billNo="+kyz.getId().getBillNo()
					         +"&factNo="+kyz.getId().getFactNo()+"&email="+vbm.getSignerNext();
					String emailUrl_in2="http://203.85.73.161/Login/vbm_findById_email2?visaSort="+kyz.getVisaType()+"&billNo="+kyz.getId().getBillNo()
					         +"&factNo="+kyz.getId().getFactNo()+"&email="+vbm.getSignerNext();							
					/**
					 * 發送郵件
					 */														
					String singernext=vbm.getSignerNext();
					String vbm_billno=vbm.getId().getBillNo();
					String vbm_factno=vbm.getId().getFactNo();
					MailSenderInfo mailinfo=new MailSenderInfo();
					mailinfo.setValidate(true);					
					mailinfo.setToAddress(singernext);
					mailinfo.setSubject("新函文初次審核-"+vbm_billno+"("+vbm_factno+")");
					mailinfo.setContent("單號:<span style='color:red'>"+vbm_billno+"</span>"+"&nbsp;&nbsp;廠別:"+vbm_factno+																	
							"<br/>點擊單號直接審核:<a href='"+emailUrl_in2+"'>"+vbm_billno+"</a>(電腦適用)"+
							"<br/>點擊單號直接審核:<a href='"+emailUrl_in+"'>"+vbm_billno+"</a>(手機平板適用)"+
							"<hr/>"+
							"如需查詢以往單據請登陸:(云端)<a href='http://203.85.73.161/Login'>http://203.85.73.161/Login</a>" +									
							"<br/>進入[KPI數據]--[函文審核]查找對應單號審核" +									
							"<hr/>"+
							"<br/>本郵件自動發送,請勿回復!如需回復或者問題，請回复到kyinfo.lp@yydg.com.cn劉平!<br/>"+
							"<hr/>");
				    //这个类主要来发送邮件   
				      SimpleMailSender sms = new SimpleMailSender();   
				         // sms.sendTextMail(mailInfo);//发送文体格式    
				      sms.sendHtmlMail(mailinfo);//发送html格式  
				}//if
				
				
				/*List list=kyzSer.findByFactNo(fact);				
				if(list.size()>0){
					for(int i=0;i<list.size();i++){
						KyzExpectmatm temp=(KyzExpectmatm)list.get(i);
						String t_factno=temp.getId().getFactNo();
						String t_billno=temp.getId().getBillNo();
						if(kyz.getId().getFactNo().equals(t_factno)&&kyz.getId().getBillNo().equals(t_billno)){
							break;
						}else if(i==list.size()-1){
							kyzSer.add(kyz);							
							result="add";
							
							KyVisabillm vbm=visabillmSer.findById(kyz.getId().getFactNo(), kyz.getVisaType(), kyz.getId().getBillNo());							
							String emailUrl_in="http://203.85.73.161/Login/vbm_findById_email?visaSort="+kyz.getVisaType()+"&billNo="+kyz.getId().getBillNo()
							         +"&factNo="+kyz.getId().getFactNo()+"&email="+vbm.getSignerNext();
							String emailUrl_in2="http://203.85.73.161/Login/vbm_findById_email2?visaSort="+kyz.getVisaType()+"&billNo="+kyz.getId().getBillNo()
							         +"&factNo="+kyz.getId().getFactNo()+"&email="+vbm.getSignerNext();							
							*//*														
							String singernext=vbm.getSignerNext();
							String vbm_billno=vbm.getId().getBillNo();
							String vbm_factno=vbm.getId().getFactNo();
							MailSenderInfo mailinfo=new MailSenderInfo();
							mailinfo.setValidate(true);
							mailinfo.setUserName("kyuen@yydg.com.cn");
							mailinfo.setPassword("yydgmail");
							mailinfo.setFromAddress("<kyuen@yydg.com.cn>");
							mailinfo.setToAddress(singernext);
							mailinfo.setSubject("新函文初次審核-"+vbm_billno+"("+vbm_factno+")");
							mailinfo.setContent("單號:<span style='color:red'>"+vbm_billno+"</span>"+"&nbsp;&nbsp;廠別:"+vbm_factno+																	
									"<br/>點擊單號直接審核:<a href='"+emailUrl_in2+"'>"+vbm_billno+"</a>(電腦適用)"+
									"<br/>點擊單號直接審核:<a href='"+emailUrl_in+"'>"+vbm_billno+"</a>(手機平板適用)"+
									"<hr/>"+
									"如需查詢以往單據請登陸:(云端)<a href='http://203.85.73.161/Login'>http://203.85.73.161/Login</a>" +									
									"<br/>進入[KPI數據]--[函文審核]查找對應單號審核" +									
									"<hr/>"+
									"<br/>本郵件自動發送,請勿回復!如需回復或者問題，請回复到kyinfo.lp@yydg.com.cn劉平!<br/>"+
									"<hr/>");
						    //这个类主要来发送邮件   
						      SimpleMailSender sms = new SimpleMailSender();   
						         // sms.sendTextMail(mailInfo);//发送文体格式    
						      sms.sendHtmlMail(mailinfo);//发送html格式  	          
						      						     
						}
					}
				}else{
					kyzSer.add(kyz);
					result="add";
					*//*	
					KyVisabillm vbm=visabillmSer.findById(kyz.getId().getFactNo(), kyz.getVisaType(), kyz.getId().getBillNo());	
					String emailUrl_in="http://203.85.73.161/Login/vbm_findById_email?visaSort="+kyz.getVisaType()+"& billNo="+kyz.getId().getBillNo()
					         +"& factNo="+kyz.getId().getFactNo()+"& email="+vbm.getSignerNext();
					String emailUrl_in2="http://203.85.73.161/Login/vbm_findById_email2?visaSort="+kyz.getVisaType()+"& billNo="+kyz.getId().getBillNo()
					         +"& factNo="+kyz.getId().getFactNo()+"& email="+vbm.getSignerNext();
					String singernext=vbm.getSignerNext();
					String vbm_billno=vbm.getId().getBillNo();
					String vbm_factno=vbm.getId().getFactNo();
					MailSenderInfo mailinfo=new MailSenderInfo();
					mailinfo.setValidate(true);
					mailinfo.setUserName("kyuen@yydg.com.cn");
					mailinfo.setPassword("yydgmail");
					mailinfo.setFromAddress("<kyuen@yydg.com.cn>");
					mailinfo.setToAddress(singernext);
					mailinfo.setSubject("新函文初次審核"+vbm_billno+"("+vbm_factno+")");
					mailinfo.setContent("單號:<span style='color:red'>"+vbm_billno+"</span>"+"&nbsp;&nbsp;廠別:"+vbm_factno+
							"<br/>點擊單號直接審核:<a href='"+emailUrl_in2+"'>"+vbm_billno+"</a>(電腦適用)"+
							"<br/>點擊單號直接審核:<a href='"+emailUrl_in+"'>"+vbm_billno+"</a>(手機平板適用)"+							
							"<hr/>"+
							"如需查詢以往單據請登陸:(云端)<a href='http://203.85.73.161/Login'>http://203.85.73.161/Login</a>" +							
							"<br/>進入[KPI數據]--[函文審核]查找對應單號審核" +							
							"<hr/>"+
							"<br/>本郵件自動發送,請勿回復!如需回復或者問題，請回复到kyinfo.lp@yydg.com.cn劉平!<br/>"+
							"<hr/>");
				    //这个类主要来发送邮件   
				      SimpleMailSender sms = new SimpleMailSender();   
				         // sms.sendTextMail(mailInfo);//发送文体格式    
				      sms.sendHtmlMail(mailinfo);//发送html格式  	          
				      				      				      				      
				}*/
				
				
				 /**
			       * 发送给备签人  20150817
			       */
				KyVisabillm vbm=visabillmSer.findById(kyz.getId().getFactNo(), kyz.getVisaType(), kyz.getId().getBillNo());	
				String emailUrl_in="http://203.85.73.161/Login/vbm_findById_email?visaSort="+kyz.getVisaType()+"& billNo="+kyz.getId().getBillNo()
				         +"& factNo="+kyz.getId().getFactNo()+"& email="+vbm.getSignerNext();
				String emailUrl_in2="http://203.85.73.161/Login/vbm_findById_email2?visaSort="+kyz.getVisaType()+"& billNo="+kyz.getId().getBillNo()
				         +"& factNo="+kyz.getId().getFactNo()+"& email="+vbm.getSignerNext();
				 /******************20151113备签人请使用方法findByFactNoAEmailPwd2(String factNo,String email)**********************/
			      /*String emailPwd=webuseremailSer.findEmailPWD(kyz.getId().getFactNo(),vbm.getSignerNext());
			      if(emailPwd!=null){
			    	  MailSenderInfo mailInfo2 = new MailSenderInfo();    				         
				      mailInfo2.setValidate(true);    
				      mailInfo2.setUserName("kyuen@yydg.com.cn"); 
				      mailInfo2.setPassword("yydgmail");//您的邮箱密码    
				      mailInfo2.setFromAddress("<kyuen@yydg.com.cn>");    
				      mailInfo2.setToAddress(emailPwd);    
				      mailInfo2.setSubject("新函文初次審核"+vbm.getId().getBillNo()+"("+vbm.getId().getFactNo()+")");    
				      mailInfo2.setContent("函文單號:"+"<span style='color:red'>"+vbm.getId().getBillNo()+"</span>"+"&nbsp;&nbsp;廠別:"+vbm.getId().getFactNo()+
				    		  "<br/>點擊單號直接審核:<a href='"+emailUrl_in2+"'>"+vbm.getId().getBillNo()+"</a>(電腦適用)"+
				    		  "<br/>點擊單號直接審核:<a href='"+emailUrl_in+"'>"+vbm.getId().getBillNo()+"</a>(手機平板適用)"+			    		  	
						      "<hr/>"+
				    		 result+"如需查詢以往單據請登錄加久網站:(云端)<a href='http://203.85.73.161/Login'>http://203.85.73.161/Login</a>" +			      		
				      		"<br/>進入[KPI數據]--[函文審核]中查找對應單號審核,"+	      		    		
				    		"<hr/>"+
				      		"<br/>本郵件自動發送,請勿回復!如需回復或者問題，請回复到kyinfo.lp@yydg.com.cn劉平!<br/>"+
				    		"<hr/>"
				    		);      
				         //这个类主要来发送邮件   
				      SimpleMailSender sms2 = new SimpleMailSender();   
				      sms2.sendHtmlMail(mailInfo2);//发送html格式  	   
			      }*/
			      
			      List<String>list_emailPwd=webuseremailSer.findByFactNoAEmailPwd2(kyz.getId().getFactNo(),vbm.getSignerNext());
			      if(list_emailPwd.size()>0){//if
			    	  for(int i=0;i<list_emailPwd.size();i++){
			    		  MailSenderInfo mailInfo2 = new MailSenderInfo();    				         
					      mailInfo2.setValidate(true);    					        
					      mailInfo2.setToAddress(list_emailPwd.get(i));    
					      mailInfo2.setSubject("新函文初次審核"+vbm.getId().getBillNo()+"("+vbm.getId().getFactNo()+")");    
					      mailInfo2.setContent("函文單號:"+"<span style='color:red'>"+vbm.getId().getBillNo()+"</span>"+"&nbsp;&nbsp;廠別:"+vbm.getId().getFactNo()+
					    		  "<br/>點擊單號直接審核:<a href='"+emailUrl_in2+"'>"+vbm.getId().getBillNo()+"</a>(電腦適用)"+
					    		  "<br/>點擊單號直接審核:<a href='"+emailUrl_in+"'>"+vbm.getId().getBillNo()+"</a>(手機平板適用)"+			    		  	
							      "<hr/>"+
					    		 result+"如需查詢以往單據請登錄加久網站:(云端)<a href='http://203.85.73.161/Login'>http://203.85.73.161/Login</a>" +			      		
					      		"<br/>進入[KPI數據]--[函文審核]中查找對應單號審核,"+	      		    		
					    		"<hr/>"+
					      		"<br/>本郵件自動發送,請勿回復!如需回復或者問題，請回复到kyinfo.lp@yydg.com.cn劉平!<br/>"+
					    		"<hr/>"
					    		);      
					         //这个类主要来发送邮件   
					      SimpleMailSender sms2 = new SimpleMailSender();   
					      sms2.sendHtmlMail(mailInfo2);//发送html格式  
				      } 
			      }//if
			      
			      
			      /**
			       * 測試主站kyuen@yydg.com.cn有沒有收到郵件
			       */
			      MailSenderInfo mailinfo3=new MailSenderInfo();
			      mailinfo3.setValidate(true);
			      /*mailinfo3.setUserName("kyuen@yydg.com.cn");
			      mailinfo3.setPassword("yydgmail");
			      mailinfo3.setFromAddress("<kyuen@yydg.com.cn>");*/
			      mailinfo3.setToAddress("kyuen@yydg.com.cn");
			      mailinfo3.setSubject("新函文初次審核(總站已收到)");
			      mailinfo3.setContent("請登錄加久網站:(云端)<a href='http://203.85.73.161/Login'>http://203.85.73.161/Login</a>" +							
							"<br/>進入[KPI數據]--[函文審核]查找對應單號進行審核" +
							"<br/>單號:<span style='color:red'>"+vbm.getId().getBillNo()+"<span>"+"&nbsp;&nbsp;廠別:"+vbm.getId().getFactNo());
			      SimpleMailSender sms3=new SimpleMailSender();
			      sms3.sendHtmlMail(mailinfo3);
			      
			      
				  print(kyz.getId(),kyz.getVisaType());					
				  return null;
				
			}//end if
			else{
				kyzSer.add(kyz);
				result="update";
			}
														
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(result==null){
			response.setContentType("text/html;charset=utf-8");
			response.getWriter()
			.print("<script>alert('數據庫已存在("
					+ kyz.getId().getFactNo()					
					+ " "
					+ kyz.getId().getBillNo()
					+ ")!');window.location.href='saveAndUpdate/matterApplicationSaveOrUpdate.jsp';window.close()</script>");
		}
						
		return result;		
	}
	
	public void print2() throws IOException{
		print(id,visaSort);
	}



	
	public String print(KyzExpectmatmId id,String sort) throws IOException{
		String factname=webFactSer.selByid(id.getFactNo());
		String secNo="";//申請單位
		List<KyzExpectmatm> list=kyzSer.findById_Print(id);
		if(list.size()==0){
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>alert('單號為"+id.getBillNo()+"的函文不存在!');window.close()</script>");
			return null;
		}else{
			list.get(0).setSecNo(ZHConverter.convert(list.get(0).getSecNo(), ZHConverter.TRADITIONAL));
			list.get(0).setUserNm(ZHConverter.convert(list.get(0).getUserNm(), ZHConverter.TRADITIONAL));			
			list.get(0).setMemoMk(ZHConverter.convert(list.get(0).getMemoMk(), ZHConverter.TRADITIONAL));
			list.get(0).setMemoSmk(ZHConverter.convert(list.get(0).getMemoSmk(), ZHConverter.TRADITIONAL));
		}
		if(list.get(0).getSecNo()!=null&&!list.get(0).getSecNo().equals("")){
			secNo="("+list.get(0).getSecNo()+")";
		}
		String result=factname+secNo+"費用申請單";
		map = new HashMap<String, Object>();
		map.put("SUBREPORT_DIR",ServletActionContext.getRequest().getRealPath("/jasper/audit/")+ "/");
		map.put("pic", ServletActionContext.getRequest().getRealPath("/jasper/audit/images/")+ "/");//圖片路徑		
		map.put("pfactno", id.getFactNo());
		map.put("pbillno",id.getBillNo());
		map.put("title",result);		
		
		List<KyzExpectmats> sub_list = new ArrayList<KyzExpectmats>();		
		KyzExpectmats temp=new KyzExpectmats();
		
		if(list.get(0).getKyzExpectmatses().size()==1){
			KyzExpectmats kyzss=list.get(0).getKyzExpectmatses().get(0);
			String aa=kyzss.getMatNo();
			String bb=kyzss.getItemNm();
			if(kyzss.getMatNo()==null&&kyzss.getItemNm()==null||(kyzss.getMatNo().trim().equals("")&&kyzss.getItemNm().trim().equals(""))){
				list.get(0).setKyzsMk("1");
			}else{
				for(int i=0;i<list.get(0).getKyzExpectmatses().size();i++){
					KyzExpectmats kyzs=list.get(0).getKyzExpectmatses().get(i);
					kyzs.setMatNo(ZHConverter.convert(kyzs.getMatNo(), ZHConverter.TRADITIONAL));
					kyzs.setQtyPair(ZHConverter.convert(kyzs.getQtyPair(), ZHConverter.TRADITIONAL));
					kyzs.setItemNm(ZHConverter.convert(kyzs.getItemNm(), ZHConverter.TRADITIONAL));
					sub_list.add(kyzs);
				}
				while(sub_list.size()<10){
					sub_list.add(temp);
				}
			}
		}else{
			for(int i=0;i<list.get(0).getKyzExpectmatses().size();i++){
				KyzExpectmats kyzs=list.get(0).getKyzExpectmatses().get(i);
				kyzs.setMatNo(ZHConverter.convert(kyzs.getMatNo(), ZHConverter.TRADITIONAL));
				kyzs.setQtyPair(ZHConverter.convert(kyzs.getQtyPair(), ZHConverter.TRADITIONAL));
				kyzs.setItemNm(ZHConverter.convert(kyzs.getItemNm(), ZHConverter.TRADITIONAL));
				sub_list.add(kyzs);
			}
			while(sub_list.size()<10){
				sub_list.add(temp);
			}
		}
		
		
		Map sub_map=new HashMap<String,Object>();
		sub_map.put("sub_list", sub_list);
		
		
		/*String type=list.get(0).getVisaType();
		List<KyzVisaflow> list_visa=visaSer.findByType(type);*/
		
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		KyVisabillm vbm=visabillmSer.findById(id.getFactNo(), sort, id.getBillNo());
		List<KyVisabills>list_visa=vbm.getKyVisabillses();
		List<KyzVisaflow>list_visaflow=visaSer.findByType(id.getFactNo(),sort);
		int num1=list_visa.size();
		int num2=list_visaflow.size();
		/**
		 * 最後個不用審核的,就去掉
		 */
		int nos=visabillSer.findBillsWithNo(sort, id.getBillNo());
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
		for(int i=0;i<list_visa.size();i++){//for
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
			//String visadate=list_visa.get(i).getDateVisa();
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
		}//for
		
		/*********************簡體轉繁體******************/
		for(int i=0;i<list_visabillstemp.size();i++){
			list_visabillstemp.get(i).setMemo(ZHConverter.convert(list_visabillstemp.get(i).getMemo(), ZHConverter.TRADITIONAL));
			list_visabillstemp.get(i).setVisaName(ZHConverter.convert(list_visabillstemp.get(i).getVisaName(), ZHConverter.TRADITIONAL));
			list_visabillstemp.get(i).setVisaNameAndMk(ZHConverter.convert(list_visabillstemp.get(i).getVisaNameAndMk(), ZHConverter.TRADITIONAL));
			list_visabillstemp.get(i).setVisaRank(ZHConverter.convert(list_visabillstemp.get(i).getVisaRank(), ZHConverter.TRADITIONAL));			
		}
		/*********************簡體轉繁體******************/
		
		
		
		Map visa_map=new HashMap<String,Object>();
		visa_map.put("list_visa", list_visabillstemp);
		
		map.put("sub_map", sub_map);
		map.put("visa_map", visa_map);
		
		Map main_map=new HashMap<String,Object>();    /*把list（List<KyzExpectmatm> list=kyzSer.findById_Print(id)）放在一个子表,便于打印  20150804*/
		main_map.put("list_main", list);
		map.put("main_map", main_map);
		
		/*函文附檔*/
		//String pic_file=ServletActionContext.getRequest().getRealPath("/KyzexpFile/"+id.getBillNo()+"/")+"/";//函文附檔圖片路徑(附檔在項目的路徑)
		String pic_file=new File("d:\\KyzexpFile_backup\\"+id.getBillNo()).toString();//函文附檔圖片路徑(附檔在D盤的路徑)
		List<KyzExpectmatmFile>list_kyzexpfile=kyzexpfileSer.findByBillNo(id.getBillNo());
		if(pic_file!=null&&list_kyzexpfile.size()>0){
			map.put("pic_file", pic_file+"\\");
			Map file_map=new HashMap<String,Object>();
			file_map.put("list_kyzexpfile", list_kyzexpfile);
			map.put("file_map", file_map);
		}	
		
		if(lookordown!=null){
			if(lookordown.equals("look")){
				JasperHelper.exportmain("line", map,"matterApplication.jasper", list,id.getBillNo(), "jasper/audit/");
			}else{
				JasperHelper.exportmain("pdf", map,"matterApplication.jasper", list,id.getBillNo(), "jasper/audit/");
			}
		}else{
			JasperHelper.exportmain("pdf", map,"matterApplication.jasper", list,id.getBillNo(), "jasper/audit/");
		}
		
		
		return null;
				
	}
	
	public String findById() throws UnsupportedEncodingException{
		kyz=kyzSer.findById(id);
		List<KyzExpectmats> list=kyz.getKyzExpectmatses();
		for(int i=0;i<list.size();i++){
			String str=list.get(i).getId().getItemNo();
			int num=Integer.parseInt(str);
			if(num>maxNum){
				maxNum=num;
			}
		}
		String file_yn=kyz.getFilesYn();
		if(file_yn==null){
			//return "findById_layer";
			return "findById";
		}
		if(file_yn.equals("1")){
			List<KyzExpectmatmFile> listfiles=kyzexpfileSer.findByBillNo(id.getBillNo());
			//退回而生成新函文，不显示旧函文的附档
			/*if((list==null||list.size()==0)&&billNo.contains("-")){
				String[]objs=billNo.split("-");
				list=kyzexpfileSer.findByBillNo(objs[0]);
			}*/
		   for(int i=0;i<listfiles.size();i++){
				String tempname=listfiles.get(i).getFilename();			
				String utfname=URLEncoder.encode(tempname,"utf-8");				
				listfiles.get(i).setFilename(utfname);
			}
			ActionContext.getContext().getSession().put("list_filesexp", listfiles);									
		}
		return "findById";
	}
	
	
	/**
	 * 電腦的函文內容審核頁面
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String findById_layer() throws UnsupportedEncodingException{
		KyzExpectmatmId kyzId=new KyzExpectmatmId();
		kyzId.setBillNo(billNo);
		kyzId.setFactNo(factNo);
		kyz=kyzSer.findById(kyzId);
		if(kyz!=null){
			String file_yn=kyz.getFilesYn();
			if(file_yn==null){
				return "findById_layer";
			}
			if(file_yn.equals("1")){
				List<KyzExpectmatmFile> list=kyzexpfileSer.findByBillNo(billNo);
				//退回而生成新函文，不显示旧函文的附档
				/*if((list==null||list.size()==0)&&billNo.contains("-")){
					String[]objs=billNo.split("-");
					list=kyzexpfileSer.findByBillNo(objs[0]);
				}*/
			   for(int i=0;i<list.size();i++){
					String tempname=list.get(i).getFilename();			
					String utfname=URLEncoder.encode(tempname,"utf-8");				
					list.get(i).setFilename(utfname);
				}
				ActionContext.getContext().getSession().put("list_filesexp", list);			
			}
		}
					
		return "findById_layer";
	}
	
	
	/**
	 * 手機平板的函文內容審核頁面
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String findById_layer2() throws UnsupportedEncodingException{
		this.findById_layer();					
		return "findById_layer2";
	}
	
	  public  String codeUtf(String str) throws UnsupportedEncodingException {
		    Pattern p = Pattern.compile("[/u4e00-/u9fa5]+");
		    Matcher m = p.matcher(str);
		    StringBuffer b = new StringBuffer();
		    while (m.find()) {
		      m.appendReplacement(b, URLEncoder.encode(m.group(0), "utf-8"));
		    }
		    m.appendTail(b);
		    return b.toString();
		  }

	
	public String toUrl(String filename) throws UnsupportedEncodingException{
		//String filename2=filename.replace("/u", "%");
		String urlname2=URLDecoder.decode(filename,"utf-8");
		return urlname2;
	}
	
	public String findPageBean() {
		ActionContext.getContext().getApplication().clear();
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		userNm=user.getName();
		factNo = (String) ActionContext.getContext().getSession().get("factNo");
		bean = kyzSer.findPageBean(25, page, factNo, visaSort,billNo,userNm,yymmdd,yymmdd2);
		this.getTypeName(bean);
		return "beanList";
	}

	public String findPageBean2() {
		ActionContext.getContext().getApplication().clear();
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			ActionContext.getContext().getApplication()
					.put("kyz_factNo", factNo);
		}
		if (visaSort != null && !visaSort.equals("")) {
			ActionContext.getContext().getApplication().put("kyz_visaSort", visaSort);
		}
		if(billNo!=null&&!billNo.equals("")){
			ActionContext.getContext().getApplication().put("kyz_billNo", billNo.trim());
		}
		if(yymmdd!=null&&!yymmdd.equals("")){
			ActionContext.getContext().getApplication().put("kyz_timeCreate", yymmdd);
		}
		if(yymmdd2!=null&&!yymmdd2.equals("")){
			ActionContext.getContext().getApplication().put("kyz_timeCreate2", yymmdd2);
		}
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		userNm=user.getName();
		bean = kyzSer.findPageBean(25, page, factNo, visaSort,billNo.trim(),userNm,yymmdd,yymmdd2);
		this.getTypeName(bean);
		return "beanList1";
	}

	public String findPageBean3() {
		factNo = (String) ActionContext.getContext().getApplication()
				.get("kyz_factNo");
		visaSort = (String) ActionContext.getContext().getApplication()
				.get("kyz_visaSort");
		billNo=(String)ActionContext.getContext().getApplication().get("kyz_billNo");
		yymmdd=(String)ActionContext.getContext().getApplication().get("kyz_timeCreate");
		yymmdd2=(String)ActionContext.getContext().getApplication().get("kyz_timeCreate2");
		if (factNo == null || factNo.equals("") || factNo.equals("tw")){
			factNo = (String) ActionContext.getContext().getSession()
					.get("factNo");
		}
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		userNm=user.getName();
		bean = kyzSer.findPageBean(25, page, factNo, visaSort,billNo,userNm,yymmdd,yymmdd2);
		this.getTypeName(bean);
		return "beanList1";
	}
	
	/**
	 * 徹底刪除函文
	 * @return
	 */
	public String delete(){
		kyzSer.delete(id);
		visabillmSer.delete(id.getFactNo(), visaSort, id.getBillNo());
		List<KyzExpectmatmFile>list=kyzexpfileSer.findByBillNo(id.getBillNo());
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				kyzexpfileSer.delete(list.get(i));				
			}			
		}
		File file=new File("d:\\KyzexpFile_backup\\"+id.getBillNo());
		if(file.exists()){
			this.deletefile(file);//引用下面刪除文件夾方法
		}
		
		/*********************刪除記錄**************************/
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setBillNo(id.getBillNo());
		log.setDeldate(new Date());
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		log.setUsername(user.getUsername());
		kyzExpLogSer.add(log);
		return "delete";
	}
	
	/**
	 * 不刪除，隻標記爲"刪除"狀態
	 * @return
	 */
	public String delete2(){
		kyz=kyzSer.findById(id);
		kyz.setDelMk("0");
		kyzSer.add(kyz);
		return "delete";
	}
	
	/**
	 * 刪除文件夾
	 * @param file
	 */
	public void deletefile(File file){
		if(file.isFile()){
			file.delete();
		}
		if(file.isDirectory()){
			File[]files=file.listFiles();
			for(int i=0;i<files.length;i++){
				this.deletefile(files[i]);
			}
			file.delete();
		}
		
	}
	
	@SuppressWarnings("finally")
	public String deleteMore(){
		try {			
			for(int i=0;i<cbox.size();i++){				
					kyzsSer.delete(factNo, billNo, (String)cbox.get(i));						
				}						
		} 
		finally{
			KyzExpectmatmId kyzid=new KyzExpectmatmId();
			kyzid.setBillNo(billNo);
			kyzid.setFactNo(factNo);
			kyz=kyzSer.findById(kyzid);
			List<KyzExpectmats> list=kyz.getKyzExpectmatses();
			for(int i=0;i<list.size();i++){
				String str=list.get(i).getId().getItemNo();
				int num=Integer.parseInt(str);
				if(num>maxNum){
					maxNum=num;
				}
			}
			return "findById";
		}					
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	public String findVisaBills(){
		vbm=visabillmSer.findById(factNo, visaSort, billNo);
		//visabills=visabillSer.findVisaBills(visaSort,billNo);
		return "findVisaBills";
		
	}
	
	public String formatDouble(double s){
		DecimalFormat format=new DecimalFormat("#");
		String temp=format.format(s);
		return temp;				
	}
	public String formatDouble2(double s){
		DecimalFormat format=new DecimalFormat(",###.#");
		String temp=format.format(s);
		return temp;				
	}
	
	
	public String download() throws UnsupportedEncodingException{
		String result="";
		fileInput=this.getFileInput();
		if(fileInput==null){
			result="input";
		}else{
			result="download";
		}		
		return result;
	}
	
	/*public void getTypeName(PageBean bean){
		List<KyzExpectmatm>list=bean.getList();
		for(int i=0;i<list.size();i++){
			KyzExpectmatm kyz=list.get(i);
			String factno=kyz.getId().getFactNo();
			String visaSort=kyz.getVisaType();		
				char visaSort_char=visaSort.charAt(0);
				String typename="";
				if(visaSort_char=='C'){
					typename=webtypeSer.findTypeNameById(factno, visaSort.substring(0, 2));
				}else{					
					//typename=webtypeSer.findTypeNameById(factno, visaSort);
					typename=webtypeSer.findTypeNameById(factno, visaSort.substring(0, 2));
				}
				if(typename!=null&&!typename.equals("")){
					kyz.setColTemp(typename);	
				}else{
					kyz.setColTemp(visaSort);
				}					
		}
	}*/
	
	public void getTypeName(PageBean bean){
		List<KyzExpectmatm>list=bean.getList();
		List<WebType>list_type=(List<WebType>)ActionContext.getContext().getSession().get("list_webtype");/********20151029登錄時已經記錄**************/
		for(int i=0;i<list.size();i++){//for1
			KyzExpectmatm kyz=list.get(i);
			String factno=kyz.getId().getFactNo();
			String visaSort=kyz.getVisaType();			
			String typename=visaSort;			
			//typename=webtypeSer.findTypeNameById(factno, visaSort.substring(0, 2));									
			for(int j=0;j<list_type.size();j++){//for2
				WebType type=list_type.get(j);
				if(factno.equals(type.getId().getFactNo())&&visaSort.substring(0,2).equals(type.getId().getTypeNo())){
					typename=type.getTypeName();					
					break;
				}
			}//for2
			kyz.setColTemp(typename);
		}//for1				
	}
	
/*	public JSONArray fileJson(){
		List<KyzExpectmatmFile> listfiles=kyzexpfileSer.findByBillNo(billNo);
		*//******************list轉json*************************//*
		JSONArray jsonList=JSONArray.fromObject(listfiles);
		*//******************list轉json*************************//*
		return jsonList;
	}*/
	
	
}
