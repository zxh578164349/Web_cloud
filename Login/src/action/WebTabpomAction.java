package action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import services.IWebTabpomServices;
import services.IWebTabpomfileServices;
import util.GlobalMethod;
import util.JasperHelper;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyzExpectmatmLog;
import entity.WebTabpom;
import entity.WebTabpomfile;
import entity.WebTabpomfileId;
import entity.WebUser;

public class WebTabpomAction extends ActionSupport implements ServletResponseAware{
	private PageBean bean;
	private WebTabpom tabpom;
	private int page;
	private String pomName;
	private String brank;
	private String ajaxResult;//申請函文時返回的ajax結果,   0:提交成功       1:提交失敗      2:提示數據庫已經存在  3:圖片大小限制    4:圖片格式限制
	private String pomNo;
	private int nullmk;//判斷是親增或修改（0：新增      1：修改）
	private List<String>list_fact;
	private List<File> files;
    private List<String> filesFileName;
    private List<String> filesContentType;
    private String yymm;
    private String yymm2;
    private String lookordown;
    private File file;
	private String fileFileName;
	private String fileContentType;
	private String tabpomDate; 
	private int backIndex;//返回標識      0或null:不走返回路徑         1:走返回路徑
	private String fileusername;
	private String filecreatedate;
	private WebTabpomfile webtabFile;
	private String factNo;
	private IWebTabpomServices tabpomSer;
	private IWebTabpomfileServices tabpomfileSer;
	private javax.servlet.http.HttpServletResponse response;	
	
	
	public String getFactNo(){
		return factNo;
	}
	public void setFactNo(String factNo){
		this.factNo=factNo;
	}
	public String getFileusername(){
		return fileusername;
	}
	public void setFileusername(String fileusername){
		this.fileusername=fileusername;
	}
	public String getFilecreatedate(){
		return filecreatedate;
	}
	public void setFilecreatedate(String filecreatedate){
		this.filecreatedate=filecreatedate;
	}
	public WebTabpomfile getWebtabFile(){
		return webtabFile;
	}
	public void setWebtabFile(WebTabpomfile webtabFile){
		this.webtabFile=webtabFile;
	}
	public String getTabpomDate(){
		return tabpomDate;
	}
	public void setTabpomDate(String tabpomDate){
		this.tabpomDate=tabpomDate;
	}	
	public int getBackIndex() {
		return backIndex;
	}
	public void setBackIndex(int backIndex) {
		this.backIndex = backIndex;
	}
	public String getLookordown() {
		return lookordown;
	}
	public void setLookordown(String lookordown) {
		this.lookordown = lookordown;
	}
	public String getYymm() {
		return yymm;
	}
	public void setYymm(String yymm) {
		this.yymm = yymm;
	}
	public String getYymm2() {
		return yymm2;
	}
	public void setYymm2(String yymm2) {
		this.yymm2 = yymm2;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
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
	public int getNullmk() {
		return nullmk;
	}
	public void setNullmk(int nullmk) {
		this.nullmk = nullmk;
	}
	public List<String> getList_fact() {
		return list_fact;
	}
	public void setList_fact(List<String> list_fact) {
		this.list_fact = list_fact;
	}
	
	public String getPomNo() {
		return pomNo;
	}
	public void setPomNo(String pomNo) {
		this.pomNo = pomNo;
	}
	public String getAjaxResult() {
		return ajaxResult;
	}
	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}
	public String getPomName() {
		return pomName;
	}
	public void setPomName(String pomName) {
		this.pomName = pomName;
	}
	public String getBrank() {
		return brank;
	}
	public void setBrank(String brank) {
		this.brank = brank;
	}
	public PageBean getBean() {
		return bean;
	}
	public void setBean(PageBean bean) {
		this.bean = bean;
	}
	
	public WebTabpom getTabpom() {
		return tabpom;
	}
	public void setTabpom(WebTabpom tabpom) {
		this.tabpom = tabpom;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setTabpomSer(IWebTabpomServices tabpomSer) {
		this.tabpomSer = tabpomSer;
	}
	
	public void setTabpomfileSer(IWebTabpomfileServices tabpomfileSer) {
		this.tabpomfileSer = tabpomfileSer;
	}
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}	
	
	
	public String findPageBean(){
		this.clearSession();
		ActionContext.getContext().getSession().remove("tabpom_no");
		ActionContext.getContext().getSession().remove("tabpom_brank");
		ActionContext.getContext().getSession().remove("tabpom_yymm");
		ActionContext.getContext().getSession().remove("tabpom_yymm2");		
		bean=tabpomSer.findPageBean(20,page, pomNo, brank,yymm,yymm2,factNo);
		return "beanList";
	}
	
	public String findPageBean2(){
		ActionContext.getContext().getSession().put("tabpom_no", pomNo);			
		ActionContext.getContext().getSession().put("tabpom_brank", brank);
		ActionContext.getContext().getSession().put("tabpom_yymm", yymm);			
		ActionContext.getContext().getSession().put("tabpom_yymm2", yymm2);	
		bean=tabpomSer.findPageBean(20,page, pomNo, brank,yymm,yymm2,factNo);
		return "beanList1";
	}
	public String findPageBean3(){
		String result="beanList1";
		if(backIndex==1){
			result="beanList";
		}
		pomName=(String)ActionContext.getContext().getSession().get("tabpom_no");
		brank=(String)ActionContext.getContext().getSession().get("tabpom_brank");
		pomName=(String)ActionContext.getContext().getSession().get("tabpom_yymm");
		brank=(String)ActionContext.getContext().getSession().get("tabpom_yymm2");
		bean=tabpomSer.findPageBean(20,page, pomNo, brank,yymm,yymm2,factNo);
		return result;
	}
	
	
	public String add() throws IOException{						
		try{
			GlobalMethod.uploadfile(tabpom);				
		}catch(Exception e){
			e.printStackTrace();
			ajaxResult="1";
			return "add";			
		}		
		switch(nullmk){
		case 0:
			pomNo=tabpomSer.findPomNoById(tabpom.getPomNo());
			if(pomNo!=null&&!pomNo.equals("")){
				ajaxResult="2";
			}else{
				try{
				   tabpomSer.add(tabpom);				   
					ajaxResult="0";
				}catch(Exception e){
					ajaxResult="1";
					return "add";
				}
			}
			break;
		case 1:
			try{				
				tabpomSer.add(tabpom);
				ajaxResult="0";
			}catch(Exception e){
				ajaxResult="1";
				return "add";
			}
		}
		try{
			List<BufferedInputStream>ins=(List<BufferedInputStream>)ActionContext.getContext().getSession().get("ins");
			List<BufferedOutputStream>outs=(List<BufferedOutputStream>)ActionContext.getContext().getSession().get("outs");
			   if(outs!=null&&outs.size()>0){
				   GlobalMethod.uploadFiles(ins, outs);
			   }
		}catch(Exception e){
			e.printStackTrace();			
			ajaxResult="3";
			return "add";
		}
		this.clearSession();//添加成功后,清除session
		return "add";
	}
	
	public  void add_session(){
		ActionContext.getContext().getSession().put("tabpom",tabpom);						
	}
	
	/**
	 * 上傳的附檔保存到session
	 * @Title: uploadfile_session
	 * @Description: TODO
	 * @param 
	 * @return void
	 * @throws
	 * @author web
	 * @date 2016/12/1
	 */
	public void uploadfile_session(){		
		List<WebTabpomfile>list_tabfile=(List<WebTabpomfile>)ActionContext.getContext().getSession().get("list_tabfile");
		List<BufferedInputStream>ins=(List<BufferedInputStream>)ActionContext.getContext().getSession().get("ins");
		List<String>filenames=(List<String>)ActionContext.getContext().getSession().get("filenames");
		List<BufferedOutputStream>outs=(List<BufferedOutputStream>)ActionContext.getContext().getSession().get("outs");
		if(list_tabfile==null){
			list_tabfile=new ArrayList<WebTabpomfile>();
		}
		if(ins==null){
			ins=new ArrayList<BufferedInputStream>();	
		}
		if(filenames==null){
			filenames=new ArrayList<String>();
		}
		if(outs==null){
			outs=new ArrayList<BufferedOutputStream>();
		}
		
		try{			
			WebTabpomfile webtabFile=new WebTabpomfile(new WebTabpomfileId());				
			webtabFile.setCreatedate(filecreatedate);
			webtabFile.setUsername(fileusername);
			webtabFile.getId().setFilename(fileFileName);
			list_tabfile.add(webtabFile);						
			ins.add(new BufferedInputStream(new FileInputStream(file)));				
			filenames.add(fileFileName);
			File file=new File("d:\\WebtabpomFile_backup\\"+pomNo);//在添加之前，就要創建好路徑，否則可能會報找不到路徑錯誤
			if(!file.exists()){
				file.mkdirs();
			}
			BufferedOutputStream out=new BufferedOutputStream(new FileOutputStream(file+"\\"+fileFileName));
			outs.add(out);
			ActionContext.getContext().getSession().put("outs",outs);
			ActionContext.getContext().getSession().put("list_tabfile",list_tabfile);
			ActionContext.getContext().getSession().put("ins",ins);
			ActionContext.getContext().getSession().put("filenames",filenames);
		}catch(Exception e){
			this.clearSession();
			e.printStackTrace();			
		}		
	}
	
	public void uploadfile_nosession(){
		try{
			WebTabpomfile webtabFile=new WebTabpomfile(new WebTabpomfileId(new WebTabpom(pomNo),fileFileName));				
			webtabFile.setCreatedate(filecreatedate);
			webtabFile.setUsername(fileusername);
			tabpomfileSer.add(webtabFile);
			GlobalMethod.uploadFile(file, "d:\\WebtabpomFile_backup\\"+pomNo+"\\"+fileFileName);			
		}catch(Exception e){
			e.printStackTrace();
		}			
	}
	
	public String findById(){
		this.clearSession();
		tabpom=tabpomSer.findById(pomNo);
		return "findById";
	}
	
	public String findByIdfiles(){
		this.findById();
		return "findByIdfiles";
	}
	public String findByIdfiles_ajax(){
		this.findById();
		return "findByIdfiles_ajax";
	}
	
	public String delete(){
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setObj("WebTabpom");
		log.setContent(pomNo);
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		log.setUsername(user.getUsername());
		tabpomSer.delete(pomNo,log);
		
		//同时删除文档20160227
		File file=new File("d:\\WebtabpomFile_backup\\"+pomNo);
		if(file.exists()){
			GlobalMethod.deletefile(file);//引用下面刪除文件夾方法
		}
		return "delete";
	}
	
	
	/**
	 * 搜索打印
	 * @throws IOException 
	 */
	public void printSerch() throws IOException{
		List<WebTabpom>list=tabpomSer.findByAny(pomName, brank, yymm, yymm2);
		/*for(WebTabpom tabpom:list){
			StringBuffer factSnames=new StringBuffer();
			for(VWebFact vwebfact:tabpom.getWebfacts()){
				factSnames.append(vwebfact.getFactSname()+" ");
			}
			tabpom.setVwebfacts(factSnames.toString());
		}*/
		GlobalMethod.print_webtabpom(list, pomName, brank, yymm, yymm2, "webtabpom.jasper", response);
	}
	
	public String toUrl(String filename) throws UnsupportedEncodingException{
		//String filename2=filename.replace("/u", "%");
		String urlname2=URLDecoder.decode(filename,"utf-8");
		return urlname2;
	}
	/**
	 * 解決url中空格轉換成 +號的問題
	 */
	public String toUrl2(String filename){
		return filename.replace("+", "%20");
	}
	
	/**
	 * 單個資料打印
	 */
	public void printOne(){
		List<WebTabpom>list=new ArrayList<WebTabpom>();
		Map<String,Object>map=new HashMap<String,Object>();
		tabpom=tabpomSer.findById(pomNo);
		list.add(tabpom);
		map.put("SUBREPORT_DIR",ServletActionContext.getRequest().getRealPath("/jasper/webtabpom/")+ "/");
	    String pic_file="d:\\WebtabpomFile_backup\\"+pomNo;
	    map.put("pic_file", pic_file+"\\");
	    map.put("list_file", tabpom.getWebTabpomfiles());
	    //map.put("list_fact", tabpom.getWebfacts());
	    if(lookordown.equals("look")){
	    	JasperHelper.exportmain("line", map, "webtabpom_one.jasper", list, pomNo, "jasper/webtabpom/");
	    }else{
	    	JasperHelper.exportmain("pdf", map, "webtabpom_one.jasper", list, pomNo, "jasper/webtabpom/");
	    }		
	}
	
	
	public String makePomNo() {
		// TODO Auto-generated method stub
		StringBuffer pomNo=new StringBuffer();
		pomNo.append("WX"+brank+"-"+tabpomDate.split("-")[0]);
		List<String>list=tabpomSer.findPomNos(brank, tabpomDate);
		if(list.size()>0){
			String indexStr=list.get(0).substring(list.get(0).length()-3);
			Integer temp=Integer.parseInt(indexStr)+1;
			String temp2=temp.toString();
			if(temp2.length()==1){
				temp2="00"+temp2;
			}
			if(temp2.length()==2){
				temp2="0"+temp2;
			}
			pomNo.append(temp2);
		}else{
			pomNo.append("001");
		}
		ajaxResult=pomNo.toString();
		return "makePomNo";
	}
	
	/**
	 * 每次點擊上傳附檔時都要清除session
	 * @Title: clearSession
	 * @Description: TODO
	 * @param 
	 * @return void
	 * @throws
	 * @author web
	 * @date 2016/11/30
	 */
	public void clearSession(){
		ActionContext.getContext().getSession().remove("list_tabfile");		
		ActionContext.getContext().getSession().remove("filenames");
		ActionContext.getContext().getSession().remove("ins");
		ActionContext.getContext().getSession().remove("outs");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
