package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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

import mail.MailSenderInfo;
import mail.SimpleMailSender;
import services.IKyVisaBillsServices;
import services.IKyVisabillmServices;
import services.IKyzContactLetterServices;
import services.IKyzExpectmatmFileServices;
import services.IKyzVisaFlowServices;
import services.IWebFactServices;
import services.IWebTypeServices;
import services.IWebUserService;
import services.IWebuserEmailServices;
import util.JasperHelper;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.spreada.utils.chinese.ZHConverter;

import entity.KyVisabillm;
import entity.KyVisabills;
import entity.KyzContactletter;
import entity.KyzExpectmatm;
import entity.KyzExpectmatmFile;
import entity.KyzExpectmatmId;
import entity.KyzExpectmats;
import entity.KyzVisaflow;
import entity.WebType;
import entity.WebUser;
import entity_temp.VisabillsTemp;

public class KyzContactLetterAction extends ActionSupport implements ServletResponseAware{
	private KyzContactletter kyzletter;
	private PageBean bean;
	private int page;
	private String factNo;
	private String visaSort;
	private String billNo;
	private String userNm;
	private String yymmdd;
	private String yymmdd2;
	private String isnull;
	private String lookordown;
	private String factCode;
	private String itemNo;
	private String readMk;//標識返回函文查看頁面(Y)，還是返回函文提交頁面(N)
	private String visa_mk;
	private List<File> files;
    private List<String> filesFileName;
    private List<String> filesContentType;
	private IKyzContactLetterServices kyzletterSer;
	private IKyVisabillmServices visabillmSer;
	private IKyzVisaFlowServices visaSer;
	private IWebFactServices webFactSer;
	private IKyzExpectmatmFileServices kyzexpfileSer;
	private javax.servlet.http.HttpServletResponse response;
	private IKyVisaBillsServices visabillSer;
	private IWebUserService webUserService;
	private IWebuserEmailServices webuseremailSer;
	private IWebTypeServices webtypeSer;
	
	
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
	public String getFactCode() {
		return factCode;
	}
	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}
	public String getLookordown() {
		return lookordown;
	}
	public void setLookordown(String lookordown) {
		this.lookordown = lookordown;
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
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
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
	
	public KyzContactletter getKyzletter() {
		return kyzletter;
	}
	public void setKyzletter(KyzContactletter kyzletter) {
		this.kyzletter = kyzletter;
	}
	
	public String getIsnull() {
		return isnull;
	}
	public void setIsnull(String isnull) {
		this.isnull = isnull;
	}
	
	public void setKyzletterSer(IKyzContactLetterServices kyzletterSer) {
		this.kyzletterSer = kyzletterSer;
	}
	
	
	public void setVisabillmSer(IKyVisabillmServices visabillmSer) {
		this.visabillmSer = visabillmSer;
	}
	
	public void setVisaSer(IKyzVisaFlowServices visaSer) {
		this.visaSer = visaSer;
	}
	
	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}
	
	public void setKyzexpfileSer(IKyzExpectmatmFileServices kyzexpfileSer) {
		this.kyzexpfileSer = kyzexpfileSer;
	}
	
	public void setVisabillSer(IKyVisaBillsServices visabillSer) {
		this.visabillSer = visabillSer;
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
	public String add() throws IOException{
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
			kyzletter.setFilesYn("1");//標示是否帶有附檔
			//File uploadFile=new File(ServletActionContext.getServletContext().getRealPath("KyzexpFile\\"+kyz.getId().getBillNo()));//附檔上傳到項目
			File uploadFile_backup=new File("d:\\KyzletterexpFile_backup\\"+kyzletter.getId().getBillNo());//附檔上傳到D盤(為了避免更新項目時丟失附檔,所在上傳到D盤)
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
					kyzexpFile.setBillno(kyzletter.getId().getBillNo());
					kyzexpFile.setFilename(filesFileName.get(i));
					WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
					String username=user.getName();
					kyzexpFile.setUsername(username);
					kyzexpfileSer.add(kyzexpFile);
				}
			}
		}
		
		String result=null;								
			if(isnull.equals("isNull")){//start if
				String factno=kyzletter.getId().getFactNo();
				String billno=kyzletter.getId().getBillNo();
				KyzContactletter letter=kyzletterSer.findById(factno, billno);
				if(letter==null){
					kyzletterSer.add(kyzletter);
					KyVisabillm vbm=visabillmSer.findById(kyzletter.getId().getFactNo(), kyzletter.getVisaType(), kyzletter.getId().getBillNo());
					result="add";
					/**
					 * 發送郵件
					 */
					String emailUrl_in="http://203.85.73.161/Login/vbm_findById_email?visaSort="+kyzletter.getVisaType()+"&billNo="+kyzletter.getId().getBillNo()
					         +"&factNo="+kyzletter.getId().getFactNo()+"&email="+vbm.getSignerNext();	
					String emailUrl_in2="http://203.85.73.161/Login/vbm_findById_email2?visaSort="+kyzletter.getVisaType()+"&billNo="+kyzletter.getId().getBillNo()
					         +"&factNo="+kyzletter.getId().getFactNo()+"&email="+vbm.getSignerNext();
					String singernext=vbm.getSignerNext();
					String vbm_billno=vbm.getId().getBillNo();
					String vbm_factno=vbm.getId().getFactNo();
					MailSenderInfo mailinfo=new MailSenderInfo();
					mailinfo.setValidate(true);
					/*mailinfo.setUserName("kyuen@yydg.com.cn");
					mailinfo.setPassword("yydgmail");
					mailinfo.setFromAddress("<kyuen@yydg.com.cn>");*/
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
				      
				      /**
				       * 測試主站kyuen@yydg.com.cn有沒有收到郵件
				       */
				      MailSenderInfo mailinfo2=new MailSenderInfo();
				      mailinfo2.setValidate(true);
				      /*mailinfo2.setUserName("kyuen@yydg.com.cn");
				      mailinfo2.setPassword("yydgmail");
				      mailinfo2.setFromAddress("<kyuen@yydg.com.cn>");*/
				      mailinfo2.setToAddress("kyuen@yydg.com.cn");
				      mailinfo2.setSubject("新函文初次審核(總站已收到)");
				      mailinfo2.setContent("請登錄加久網站:(云端)<a href='http://203.85.73.161/Login'>http://203.85.73.161/Login</a>" +								
								"<br/>進入[KPI數據]--[函文審核]查找對應單號進行審核" +
								"&nbsp;&nbsp;單號:<span style='color:red'>"+vbm_billno+"<span>"+"&nbsp;&nbsp;廠別:"+vbm_factno);
				      SimpleMailSender sms2=new SimpleMailSender();
				      sms2.sendHtmlMail(mailinfo2);
				      
				      /**
				       * 给备签人发送邮件
				       */
				      String emailPwd=webuseremailSer.findEmailPWD(kyzletter.getId().getFactNo(),singernext);
				      if(emailPwd!=null){
				    	  MailSenderInfo mailinfo3=new MailSenderInfo();
							mailinfo3.setValidate(true);
							/*mailinfo3.setUserName("kyuen@yydg.com.cn");
							mailinfo3.setPassword("yydgmail");
							mailinfo3.setFromAddress("<kyuen@yydg.com.cn>");*/
							mailinfo3.setToAddress(emailPwd);
							mailinfo3.setSubject("新函文初次審核"+vbm_billno+"("+vbm_factno+")");
							mailinfo3.setContent("單號:<span style='color:red'>"+vbm_billno+"</span>"+"&nbsp;&nbsp;廠別:"+vbm_factno+								
									"<br/>點擊單號直接審核:<a href='"+emailUrl_in2+"'>"+vbm_billno+"</a>(電腦適用)"+
									"<br/>點擊單號直接審核:<a href='"+emailUrl_in+"'>"+vbm_billno+"</a>(手機平板適用)"+
									"<hr/>"+
									"如需查詢以往單據請登陸:(云端)<a href='http://203.85.73.161/Login'>http://203.85.73.161/Login</a>" +									
									"<br/>進入[KPI數據]--[函文審核]查找對應單號審核" +									
									"<hr/>"+
									"<br/>本郵件自動發送,請勿回復!如需回復或者問題，請回复到kyinfo.lp@yydg.com.cn劉平!<br/>"+
									"<hr/>");
						    //这个类主要来发送邮件   
						      SimpleMailSender sms3 = new SimpleMailSender();   
						         // sms.sendTextMail(mailInfo);//发送文体格式    
						      sms3.sendHtmlMail(mailinfo3);//发送html格式  
				      }
				      
					  print(kyzletter.getId().getFactNo(),kyzletter.getId().getBillNo(),kyzletter.getVisaType());
					  return null;
					}
				
				
			}//end if
			else{
				kyzletterSer.add(kyzletter);
				result="update";
			}
			if(result==null){
				response.setContentType("text/html;charset=utf-8");
				response.getWriter()
				.print("<script>alert('數據庫已存在("
						+ kyzletter.getId().getFactNo()					
						+ " "
						+ kyzletter.getId().getBillNo()
						+ ")!');histore.back()</script>");
			}
											
			
			
			return result;
}
	public String print(String factNo,String billNo,String sort) throws IOException{
		List<KyzContactletter>list=new ArrayList<KyzContactletter>();
		Map<String,Object>map=new HashMap<String,Object>();
		String factname=webFactSer.selByid(factNo);
		String factCode="";
		KyzContactletter letter=kyzletterSer.findById(factNo,billNo);
		if(letter==null){
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>alert('單號為"+billNo+"的函文不存在!');window.close()</script>");
			return null;
		}else{
			/*************************/						
			letter.setUserNm(ZHConverter.convert(letter.getUserNm(), ZHConverter.TRADITIONAL));
			letter.setToUser(ZHConverter.convert(letter.getToUser(), ZHConverter.TRADITIONAL));
			letter.setChargeList(ZHConverter.convert(letter.getChargeList(), ZHConverter.TRADITIONAL));
			letter.setTitle(ZHConverter.convert(letter.getTitle(), ZHConverter.TRADITIONAL));
			letter.setMemoMk(ZHConverter.convert(letter.getMemoMk(), ZHConverter.TRADITIONAL));
			list.add(letter);
		}		
		factCode=letter.getFactCode();
		String result=factname+"("+factCode+")"+"內部聯絡函";
		//map = new HashMap<String, Object>();
		map.put("SUBREPORT_DIR",ServletActionContext.getRequest().getRealPath("/jasper/audit/")+ "/");
		map.put("pic", ServletActionContext.getRequest().getRealPath("/jasper/audit/images/")+ "/");//圖片路徑		
		map.put("pfactno", factNo);
		map.put("pbillno",billNo);
		map.put("title",result);
		List<KyzExpectmats> sub_list = new ArrayList<KyzExpectmats>();		
		KyzExpectmats temp=new KyzExpectmats();		
				
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		KyVisabillm vbm=visabillmSer.findById(factNo, sort, billNo);
		List<KyVisabills>list_visa=vbm.getKyVisabillses();
		List<KyzVisaflow>list_visaflow=visaSer.findByType(factNo,sort);
		int num1=list_visa.size();
		int num2=list_visaflow.size();
		/**
		 * 最後個不用審核的,就去掉
		 */
		int nos=visabillSer.findBillsWithNo(sort, billNo);
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
		}else if(list_visa.get(list_visa.size()-1).getFlowMk().equals("N")){
			list_visa.remove(list_visa.size()-1);
		}
		if(list_visaflow.get(list_visaflow.size()-3).getFlowMk().equals("N")){//(>=1000的，後面三個都不要簽核   20150803)
			list_visaflow.remove(list_visaflow.size()-1);
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
		/*函文附檔*/
		//String pic_file=ServletActionContext.getRequest().getRealPath("/KyzexpFile/"+id.getBillNo()+"/")+"/";//函文附檔圖片路徑(附檔在項目的路徑)
		String pic_file=new File("d:\\KyzletterexpFile_backup\\"+billNo).toString();//函文附檔圖片路徑(附檔在D盤的路徑)
		List<KyzExpectmatmFile>list_kyzexpfile=kyzexpfileSer.findByBillNo(billNo);
		if(pic_file!=null&&list_kyzexpfile.size()>0){
			map.put("pic_file", pic_file+"\\");
			Map file_map=new HashMap<String,Object>();
			file_map.put("list_kyzexpfile", list_kyzexpfile);
			map.put("file_map", file_map);
		}	
				
		if(lookordown!=null){
			if(lookordown.equals("look")){
				JasperHelper.exportmain("line", map,"kyz_contactletter.jasper", list,billNo, "jasper/audit/");
			}else{
				JasperHelper.exportmain("pdf", map,"kyz_contactletter.jasper", list,billNo, "jasper/audit/");
			}
		}else{
			JasperHelper.exportmain("pdf", map,"kyz_contactletter.jasper", list,billNo, "jasper/audit/");
		}
		
		
		return null;
				
	}
	
	public String findPageBean() {
		ActionContext.getContext().getApplication().clear();
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		userNm=user.getName();
		factNo = (String) ActionContext.getContext().getSession().get("factNo");
		bean = kyzletterSer.findPageBean(25, page, factNo, visaSort,billNo,userNm,yymmdd,yymmdd2);
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
		bean = kyzletterSer.findPageBean(25, page, factNo, visaSort,billNo.trim(),userNm,yymmdd,yymmdd2);
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
		bean = kyzletterSer.findPageBean(25, page, factNo, visaSort,billNo,userNm,yymmdd,yymmdd2);
		this.getTypeName(bean);
		return "beanList1";
	}
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	public String findById() throws UnsupportedEncodingException{
		kyzletter=kyzletterSer.findById(factNo, billNo);
		String file_yn=kyzletter.getFilesYn();
		if(file_yn==null){
			return "findById";
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
		return "findById";
	}
	
	/**
	 * 電腦的函文內容審核頁面
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String findById_layer() throws UnsupportedEncodingException{
		kyzletter=kyzletterSer.findById(factNo, billNo);
		if(kyzletter!=null){
			String file_yn=kyzletter.getFilesYn();
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
	
	public String delete(){
		kyzletterSer.delete(factNo, billNo);
		visabillmSer.delete(factNo, visaSort, billNo);
		List<KyzExpectmatmFile>list=kyzexpfileSer.findByBillNo(billNo);
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				kyzexpfileSer.delete(list.get(i));				
			}			
		}
		File file=new File("d:\\KyzletterexpFile_backup\\"+billNo);
		if(file.exists()){
			this.deletefile(file);//引用下面刪除文件夾方法
		}
		return "delete";
	}
	public void print2() throws IOException{
		this.print(factNo, billNo, visaSort);
	}
	
	public String toUrl(String filename) throws UnsupportedEncodingException{
		String urlname2=URLDecoder.decode(filename,"utf-8");
		return urlname2;
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
	
	/*public void getTypeName(PageBean bean){
		List<KyzContactletter>list=bean.getList();
		for(int i=0;i<list.size();i++){
			KyzContactletter letter=list.get(i);
			String factno=letter.getId().getFactNo();
			String visaSort=letter.getVisaType();
			char visaSort_char=visaSort.charAt(0);
			String typename="";
			if(visaSort_char=='C'){
				typename=webtypeSer.findTypeNameById(factno, visaSort.substring(0, 2));
			}else{
				//typename=webtypeSer.findTypeNameById(factno, visaSort);
				typename=webtypeSer.findTypeNameById(factno, visaSort.substring(0, 2));
			}
			if(typename!=null&&!typename.equals("")){
				letter.setColTemp(typename);	
			}else{
				letter.setColTemp(visaSort);
			}
					
		}
	}*/
	
	public void getTypeName(PageBean bean){
		List<KyzContactletter>list=bean.getList();
		List<WebType>list_type=(List<WebType>)ActionContext.getContext().getSession().get("list_webtype");/********20151029登錄時已經記錄**************/
		for(int i=0;i<list.size();i++){//for1
			KyzContactletter letter=list.get(i);
			String factno=letter.getId().getFactNo();
			String visaSort=letter.getVisaType();			
			String typename=visaSort;			
			//typename=webtypeSer.findTypeNameById(factno, visaSort.substring(0, 2));									
			for(int j=0;j<list_type.size();j++){//for2
				WebType type=list_type.get(j);
				if(factno.equals(type.getId().getFactNo())&&visaSort.substring(0,2).equals(type.getId().getTypeNo())){
					typename=type.getTypeName();					
					break;
				}
			}//for2
			letter.setColTemp(typename);
		}//for1				
	}
	

}
