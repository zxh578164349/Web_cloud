package action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import services.IKyzExpectmatmLogServices;
import services.IKyzVisaFlowServices;
import services.IWebFactServices;
import services.IWebTypeServices;
import services.IWebUserService;
import services.IWebuserEmailServices;
import util.GlobalMethod;
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
import entity.KyzExpectmatmLog;
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
	private String ajaxResult;//申請函文時返回的ajax結果,   0:提交成功       1:提交失敗
	private int backIndex;//返回標識      0或null:不走返回路徑         1:走返回路徑
	private String addorupdate;//添加或更新標識    update表示進入更新狀態
	private List<File> files;
    private List<String> filesFileName;
    private List<String> filesContentType;
    private InputStream fileInput;
	private String fileName;
	private String title;
	private IKyzContactLetterServices kyzletterSer;
	private IKyVisabillmServices visabillmSer;
	private IKyzExpectmatmFileServices kyzexpfileSer;
	private javax.servlet.http.HttpServletResponse response;
	private IWebuserEmailServices webuseremailSer;
	private IKyzExpectmatmLogServices kyzExpLogSer;
	
	
	
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public InputStream getFileInput(){
		return fileInput;
	}
	public void setFileInput(InputStream fileInput){
		this.fileInput=fileInput;
	}
	public String getFileName(){
		return fileName;
	}
	public void setFileName(String fileName){
		this.fileName=fileName;
	}
	public String getAddorupdate() {
		return addorupdate;
	}
	public void setAddorupdate(String addorupdate) {
		this.addorupdate = addorupdate;
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
		
	public void setKyzexpfileSer(IKyzExpectmatmFileServices kyzexpfileSer) {
		this.kyzexpfileSer = kyzexpfileSer;
	}
		
	public void setWebuseremailSer(IWebuserEmailServices webuseremailSer) {
		this.webuseremailSer = webuseremailSer;
	}
	
	public void setKyzExpLogSer(IKyzExpectmatmLogServices kyzExpLogSer) {
		this.kyzExpLogSer = kyzExpLogSer;
	}
	public String add() throws IOException{
		/*文件上傳驗證*/
		if(files!=null&&files.get(0)!=null){
			for(int i=0;i<files.size();i++){
				if(files.get(i)!=null){
					long filesize=files.get(i).length();
					String filetype=filesFileName.get(i).substring(filesFileName.get(i).lastIndexOf(".")).toLowerCase();
					if(filesize>5120000){
						response.setContentType("text/html;charset=utf-8");
						response.getWriter().print("<script>window.parent.alert('文件不可超過5M!');window.parent.layer.closeAll()</script>");
						return null;
					}
					if(!filetype.equals(".bmp")&&!filetype.equals(".jpg")&&!filetype.equals(".jpeg")&&!filetype.equals(".gif")&&!filetype.equals(".tif")){
						response.setContentType("text/html;charset=utf-8");
						response.getWriter().print("<script>window.parent.alert('只允許jpg,bmp,jpeg,gif,tif圖片');window.parent.layer.closeAll()</script>");
						return null;
					}										
				}
			}
		}
				
		try{
			kyzletter.setVisaTypeM(kyzletter.getVisaType().substring(0,2));
			if(files!=null&&files.get(0)!=null){
				kyzletter.setFilesYn("1");//標示是否帶有附檔
			}
			if(isnull.equals("isNull")){//start if
				String factno=kyzletter.getId().getFactNo();
				String billno=kyzletter.getId().getBillNo();
				KyzContactletter letter=kyzletterSer.findById(factno, billno);
				if(letter==null){
					//kyzletter.setVisaTypeM(kyzletter.getVisaType().substring(0,2));
					kyzletterSer.add(kyzletter);
					
					KyVisabillm vbm=visabillmSer.findById(kyzletter.getId().getFactNo(), kyzletter.getVisaType(), kyzletter.getId().getBillNo());				      
				    List<String>list_emailPwd=webuseremailSer.findByFactNoAEmailPwd2(vbm.getId().getFactNo(),vbm.getSignerNext());											      
					GlobalMethod.sendNewEmail(vbm,list_emailPwd);//發送郵件									     
					}else{
						response.setContentType("text/html;charset=utf-8");
						response.getWriter()
						.print("<script>window.parent.alert('數據庫已存在("
								+ kyzletter.getId().getFactNo()					
								+ " "
								+ kyzletter.getId().getBillNo()
								+ ")!');window.parent.layer.closeAll()</script>");
					}								
			}//end if
			else{
				//kyzletter.setVisaTypeM(kyzletter.getVisaType().substring(0,2));
				kyzletterSer.add(kyzletter);
			}
			/*文件上傳*/
			if(files!=null&&files.get(0)!=null){//不為空代表有上傳附檔,不能寫成files.size()>0,否則報空指針
				//kyzletter.setFilesYn("1");//標示是否帶有附檔
				//File uploadFile=new File(ServletActionContext.getServletContext().getRealPath("KyzexpFile\\"+kyz.getId().getBillNo()));//附檔上傳到項目
				File uploadFile_backup=new File("d:\\KyzletterexpFile_backup\\"+kyzletter.getId().getBillNo());//附檔上傳到D盤(為了避免更新項目時丟失附檔,所在上傳到D盤)			
				if(!uploadFile_backup.exists()){
					uploadFile_backup.mkdirs();
				}
				for(int i=0;i<files.size();i++){							
					if(files.get(i)!=null){					    																																		
						KyzExpectmatmFile kyzexpFile=new KyzExpectmatmFile();//函文附檔
						kyzexpFile.setBillno(kyzletter.getId().getBillNo());
						kyzexpFile.setFilename(filesFileName.get(i));
						WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
						String username=user.getName();
						kyzexpFile.setUsername(username);
						kyzexpFile.setFactNo(kyzletter.getId().getFactNo());
						kyzexpFile.setVisaTypeM(kyzletter.getVisaType().substring(0,2));
						kyzexpFile.setFileurl("upload_letter");
						kyzexpfileSer.add(kyzexpFile);
						GlobalMethod.uploadFile(files.get(i),uploadFile_backup+"\\"+filesFileName.get(i));//文件上傳		
					}
				}
			}						
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>window.parent.gook();</script>");
		}catch(Exception e){
			// TODO Auto-generated catch block
			response.setContentType("text/html;charset=urf-8");
			response.getWriter().print("<script>window.parent.layer.msg('操作失敗',3,3);window.parent.layer.closeAll()</script>");
			e.printStackTrace();			
		}
		return null;
}
	public void print(String factNo,String billNo,String sort) throws IOException{		
		Map<String,Object>map_result=kyzletterSer.print(factNo, billNo, sort,null);
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
	
	public String findPageBean() {
		//ActionContext.getContext().getApplication().clear();
		ActionContext.getContext().getSession().remove("public_factNo");
		ActionContext.getContext().getSession().remove("public_visaSort");
		ActionContext.getContext().getSession().remove("public_billNo");
		ActionContext.getContext().getSession().remove("public_timeCreate");
		ActionContext.getContext().getSession().remove("public_timeCreate2");
		ActionContext.getContext().getSession().remove("public_title");
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		factNo = (String) ActionContext.getContext().getSession().get("factNo");
		bean = kyzletterSer.findPageBean(20,page, factNo, visaSort,billNo,user,yymmdd,yymmdd2,title);
		this.getTypeName(bean);
		return "beanList";
	}

	public String findPageBean2() {
		//ActionContext.getContext().getApplication().clear();		
		
			ActionContext.getContext().getSession().put("public_factno", factNo);					
			ActionContext.getContext().getSession().put("public_visaSort", visaSort);
			ActionContext.getContext().getSession().put("public_billNo", billNo);
			ActionContext.getContext().getSession().put("public_timeCreate", yymmdd);
			ActionContext.getContext().getSession().put("public_timeCreate2", yymmdd2);
			ActionContext.getContext().getSession().put("public_title",title);
		
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		bean = kyzletterSer.findPageBean(20,page, factNo, visaSort,billNo,user,yymmdd,yymmdd2,title);
		this.getTypeName(bean);
		return "beanList1";
	}

	public String findPageBean3() {
		String result="beanList1";
		if(backIndex==1){
			result="beanList";
		}
		factNo = (String) ActionContext.getContext().getSession().get("public_factNo");				
		visaSort = (String) ActionContext.getContext().getSession().get("public_visaSort");				
		billNo=(String)ActionContext.getContext().getSession().get("public_billNo");
		yymmdd=(String)ActionContext.getContext().getSession().get("public_timeCreate");
		yymmdd2=(String)ActionContext.getContext().getSession().get("public_timeCreate2");
		title=(String)ActionContext.getContext().getSession().get("public_title");		
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		bean = kyzletterSer.findPageBean(20,page, factNo, visaSort,billNo,user,yymmdd,yymmdd2,title);
		this.getTypeName(bean);
		return result;
	}
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	public String findById() throws UnsupportedEncodingException{
		addorupdate="update";
		kyzletter=kyzletterSer.findById(factNo, billNo);
		String file_yn=kyzletter.getFilesYn();		
		if("1".equals(file_yn)){
			List<KyzExpectmatmFile> list=kyzexpfileSer.findByBillNo(billNo);			
		    /*for(int i=0;i<list.size();i++){
				String tempname=list.get(i).getFilename();			
				String utfname=URLEncoder.encode(tempname,"utf-8");				
				list.get(i).setFilename(utfname);
			}*/
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
		ActionContext.getContext().getSession().remove("list_filesexp");
		kyzletter=kyzletterSer.findById(factNo, billNo);
		if(kyzletter!=null){
			String file_yn=kyzletter.getFilesYn();			
			if("1".equals(file_yn)){
				List<KyzExpectmatmFile> list=kyzexpfileSer.findByBillNo(billNo);				
			   /*for(int i=0;i<list.size();i++){
					String tempname=list.get(i).getFilename();			
					String utfname=URLEncoder.encode(tempname,"utf-8");				
					list.get(i).setFilename(utfname);
				}*/
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
		try{
			/*********************刪除記錄**************************/
			KyzExpectmatmLog log=new KyzExpectmatmLog();
			log.setObj("KyzContactletter");
			log.setBillNo(billNo);
			log.setFactNo(factNo);
			kyzletterSer.delete(factNo, billNo,log);			
			File file=new File("d:\\KyzletterexpFile_backup\\"+billNo);
			if(file.exists()){
				GlobalMethod.deletefile(file);//引用下面刪除文件夾方法
			}			
		}catch(Exception e){
			e.printStackTrace();
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
			if(visaSort==null){
				visaSort="";
			}
			String typename=visaSort;			
			//typename=webtypeSer.findTypeNameById(factno, visaSort.substring(0, 2));			
			if(visaSort.length()>0){
				if(list_type!=null&&list_type.size()>0){
					for(int j=0;j<list_type.size();j++){//for2
						WebType type=list_type.get(j);
						if(factno.equals(type.getId().getFactNo())&&visaSort.substring(0,2).equals(type.getId().getTypeNo())){
							typename=type.getTypeName();					
							break;
						}
					}//for2
				}				
			}			
			letter.setColTemp(typename);
		}//for1				
	}
	
	/**
	 * 解決url中空格轉換成 +號的問題
	 */
	public String toUrl2(String filename){
		return filename.replace("+", "%20");
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
		fileInput=GlobalMethod.getFileInput("D:\\KyzletterexpFile_backup\\"+billNo+"\\"+fileName);
		return "lookFile";
	}
	

}
