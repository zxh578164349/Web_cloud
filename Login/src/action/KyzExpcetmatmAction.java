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
import util.GlobalMethod;
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
	private IWebuserEmailServices webuseremailSer;
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
    private String title;
    private String billNo;
    private String visaSort;
    private List<String> cbox;   
    private IKyzExpectmatmsServices kyzsSer;
    private IKyzExpectmatmServices kyzSer;
    private String isnull; 
    private IKyVisabillmServices visabillmSer;
    private List<KyVisabills>visabills;
    private KyVisabillm vbm;
    private String ajaxResult;//申請函文時返回的ajax結果,   0:提交成功       1:提交失敗
    private int backIndex;//返回標識      0或null:不走返回路徑         1:走返回路徑
    private String addorupdate;//添加或更新標識    update表示進入更新狀態
    
    
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

	
	
	

	public void setVisabillmSer(IKyVisabillmServices visabillmSer) {
		this.visabillmSer = visabillmSer;
	}

	public final void setKyzsSer(IKyzExpectmatmsServices kyzsSer) {
		this.kyzsSer = kyzsSer;
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
	
	public void setWebuseremailSer(IWebuserEmailServices webuseremailSer) {
		this.webuseremailSer = webuseremailSer;
	}
	
	public void setKyzExpLogSer(IKyzExpectmatmLogServices kyzExpLogSer) {
		this.kyzExpLogSer = kyzExpLogSer;
	}

	public String add() throws Exception  {
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
						response.getWriter().print("<script>window.parent.alert('只允許jpg,bmp,jpeg,gif,tif圖片!');window.parent.layer.closeAll()</script>");
						return null;
					}
					
				}
			}
		}
		
		/*文件上傳*/
		if(files!=null&&files.get(0)!=null){//不為空代表有上傳附檔,不能寫成files.size()>0,否則報空指針
			kyz.setFilesYn("1");//標示是否帶有附檔
			//File uploadFile=new File(ServletActionContext.getServletContext().getRealPath("KyzexpFile\\"+kyz.getId().getBillNo()));//附檔上傳到項目
			File uploadFile_backup=new File("d:\\KyzexpFile_backup\\"+kyz.getId().getBillNo());//附檔上傳到D盤(為了避免更新項目時丟失附檔,所在上傳到D盤)			
			if(!uploadFile_backup.exists()){
				uploadFile_backup.mkdirs();
			}
			for(int i=0;i<files.size();i++){							
				if(files.get(i)!=null){																									
					GlobalMethod.uploadFile(files.get(i),uploadFile_backup+"\\"+filesFileName.get(i));//文件上傳																							
					KyzExpectmatmFile kyzexpFile=new KyzExpectmatmFile();//函文附檔
					kyzexpFile.setBillno(kyz.getId().getBillNo());
					kyzexpFile.setFilename(filesFileName.get(i));
					WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
					String username=user.getName();
					kyzexpFile.setUsername(username);
					kyzexpFile.setFactNo(kyz.getId().getFactNo());
					kyzexpFile.setVisaTypeM(kyz.getVisaType().substring(0,2));
					kyzexpfileSer.add(kyzexpFile);
				}
			}
		}
		

		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		Date date;
		try {
			response.setContentType("text/html;charset=utf-8");
			date = format.parse(yymmdd);
			kyz.setTimeCreate(date);
			kyz.setVisaTypeM(kyz.getVisaType().substring(0,2));
			if(isnull.equals("isNull")){			
				KyzExpectmatm temp=kyzSer.findById(kyz.getId().getFactNo(), kyz.getId().getBillNo());										
				if(temp==null){
					//kyz.setVisaTypeM(kyz.getVisaType().substring(0,2));
					kyzSer.add(kyz);											
					KyVisabillm vbm=visabillmSer.findById(kyz.getId().getFactNo(), kyz.getVisaType(), kyz.getId().getBillNo());
					List<String>list_emailPwd=webuseremailSer.findByFactNoAEmailPwd2(vbm.getId().getFactNo(),vbm.getSignerNext());																					
																								
					GlobalMethod.sendNewEmail(vbm,list_emailPwd);//發送郵件
				      
				      /****************************函文打印************************************/
					 //print(kyz.getId(),kyz.getVisaType());
					  /****************************函文打印************************************/
				}else{
					response.getWriter().print(
							"<script>window.parent.alert('數據庫已存在(" + kyz.getId().getBillNo() + ")!');window.parent.layer.closeAll()</script>");
				}																							 				
			}else{
				//kyz.setVisaTypeM(kyz.getVisaType().substring(0,2));
				kyzSer.add(kyz);			
			}
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>window.parent.gook();</script>");										
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.getWriter().print("<script>window.parent.layer.msg('操作失敗',3,3);window.parent.layer.closeAll()</script>");
			e.printStackTrace();
		} 	
		return null;
	}
	
	public void print2() throws IOException{
		print(factNo,billNo,visaSort,null);
	}



	
	public void print(String factNo,String billNo,String sort,KyVisabillm vbm) throws IOException{
		
		Map<String,Object>map_result=kyzSer.print(factNo,billNo, sort,null);		
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
	
	public String findById() throws UnsupportedEncodingException{
		addorupdate="update";
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
	
	/**
	 * 各細項刪除頁面
	 * @Title: findById_layer3
	 * @Description: TODO
	 * @param @return
	 * @return String
	 * @throws
	 * @author web
	 * @date 2016/5/3
	 */
	public String findById_layer3(){
		kyz=kyzSer.findById(new KyzExpectmatmId(factNo,billNo));
		return "findById_layer3";
	}
	
	/**
	 * 各細項刪除
	 * @Title: delete_lists
	 * @Description: TODO
	 * @param @return
	 * @return String
	 * @throws
	 * @author web
	 * @date 2016/5/3
	 */
	public String delete_lists(){
		kyzsSer.delete(factNo, billNo, itemNo);
		return "delete_lists";
	}
	/**
	 * 各細項刪除後返回的頁面
	 * @Title: findById_layer4
	 * @Description: TODO
	 * @param @return
	 * @return String
	 * @throws
	 * @author web
	 * @date 2016/5/3
	 */
	public String findById_layer4(){
		kyz=kyzSer.findById(new KyzExpectmatmId(factNo,billNo));
		return "findById_layer4";
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
		//ActionContext.getContext().getApplication().clear();
		ActionContext.getContext().getSession().remove("public_factno");
		ActionContext.getContext().getSession().remove("public_visaSort");
		ActionContext.getContext().getSession().remove("public_billNo");
		ActionContext.getContext().getSession().remove("public_timeCreate");
		ActionContext.getContext().getSession().remove("public_timeCreate2");
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		factNo = (String) ActionContext.getContext().getSession().get("factNo");
		bean = kyzSer.findPageBean(25, page, factNo, visaSort,billNo,user,yymmdd,yymmdd2);
		this.getTypeName(bean);
		return "beanList";
	}

	public String findPageBean2() {
		//ActionContext.getContext().getApplication().clear();
		ActionContext.getContext().getSession().remove("public_factno");
		ActionContext.getContext().getSession().remove("public_visaSort");
		ActionContext.getContext().getSession().remove("public_billNo");
		ActionContext.getContext().getSession().remove("public_timeCreate");
		ActionContext.getContext().getSession().remove("public_timeCreate2");
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			ActionContext.getContext().getSession().put("public_factno", factNo);					
		}
		if (visaSort != null && !visaSort.equals("")) {
			ActionContext.getContext().getSession().put("public_visaSort", visaSort);
		}
		if(billNo!=null&&!billNo.equals("")){
			ActionContext.getContext().getSession().put("public_billNo", billNo.trim());
		}
		if(yymmdd!=null&&!yymmdd.equals("")){
			ActionContext.getContext().getSession().put("public_timeCreate", yymmdd);
		}
		if(yymmdd2!=null&&!yymmdd2.equals("")){
			ActionContext.getContext().getSession().put("public_timeCreate2", yymmdd2);
		}
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		bean = kyzSer.findPageBean(25, page, factNo, visaSort,billNo.trim(),user,yymmdd,yymmdd2);
		this.getTypeName(bean);
		return "beanList1";
	}

	public String findPageBean3() {
		String result="beanList1";
		if(backIndex==1){
			result="beanList";
		}
		factNo = (String) ActionContext.getContext().getSession().get("public_factno");				
		visaSort = (String) ActionContext.getContext().getSession().get("public_visaSort");				
		billNo=(String)ActionContext.getContext().getSession().get("public_billNo");
		yymmdd=(String)ActionContext.getContext().getSession().get("public_timeCreate");
		yymmdd2=(String)ActionContext.getContext().getSession().get("public_timeCreate2");
		if (factNo == null || factNo.equals("") || factNo.equals("tw")){
			factNo = (String) ActionContext.getContext().getSession().get("factNo");					
		}
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		bean = kyzSer.findPageBean(25, page, factNo, visaSort,billNo,user,yymmdd,yymmdd2);
		this.getTypeName(bean);
		return result;
	}
	
	/**
	 * 徹底刪除函文
	 * @return
	 */
	public String delete(){
		try{
			KyzExpectmatmLog log=new KyzExpectmatmLog();
			log.setObj("KyzExpectmatm");
			log.setBillNo(id.getBillNo());
			WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
			log.setUsername(user.getUsername());
			kyzSer.delete(id,log);
			/*visabillmSer.delete(id.getFactNo(), visaSort, id.getBillNo());
			List<KyzExpectmatmFile>list=kyzexpfileSer.findByBillNo(id.getBillNo());
			if(list.size()>0){
				for(int i=0;i<list.size();i++){
					kyzexpfileSer.delete(list.get(i));				
				}			
			}*/
			File file=new File("d:\\KyzexpFile_backup\\"+id.getBillNo());
			if(file.exists()){
				GlobalMethod.deletefile(file);//引用下面刪除文件夾方法
			}
	
		}catch(Exception e){
			System.out.println(e);
		}		
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
			if(visaSort==null){
				visaSort="";
			}
			String typename=visaSort;			
			//typename=webtypeSer.findTypeNameById(factno, visaSort.substring(0, 2));
			if(list_type!=null&&list_type.size()>0){
				for(int j=0;j<list_type.size();j++){//for2
					WebType type=list_type.get(j);
					if(visaSort.length()>0){
						if(factno.equals(type.getId().getFactNo())&&visaSort.substring(0,2).equals(type.getId().getTypeNo())){
							typename=type.getTypeName();					
							break;
						}
					}
					
				}//for2
			}			
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
	
	/**
	 * 解決url中空格轉換成 +號的問題
	 */
	public String toUrl2(String filename){
		return filename.replace("+", "%20");
	}
	
	
	
}
