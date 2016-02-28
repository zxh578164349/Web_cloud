package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import services.IWebTabpomServices;
import services.IWebTabpomfileServices;
import util.GlobalMethod;
import util.JasperHelper;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyzExpectmatmFile;
import entity.KyzExpectmatmLog;
import entity.VWebFact;
import entity.WebFact;
import entity.WebFactId;
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
	private IWebTabpomServices tabpomSer;
	private IWebTabpomfileServices tabpomfileSer;
	private javax.servlet.http.HttpServletResponse response;
	private int backIndex;//返回標識      0或null:不走返回路徑         1:走返回路徑
	
	
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
		ActionContext.getContext().getSession().remove("tabpom_name");
		ActionContext.getContext().getSession().remove("tabpom_brank");
		ActionContext.getContext().getSession().remove("tabpom_yymm");
		ActionContext.getContext().getSession().remove("tabpom_yymm2");
		bean=tabpomSer.findPageBean(25, page, pomName, brank,yymm,yymm2);
		return "beanList";
	}
	
	public String findPageBean2(){
		ActionContext.getContext().getSession().put("tabpom_name", pomName);			
		ActionContext.getContext().getSession().put("tabpom_brank", brank);
		ActionContext.getContext().getSession().put("tabpom_yymm", yymm);			
		ActionContext.getContext().getSession().put("tabpom_yymm2", yymm2);	
		bean=tabpomSer.findPageBean(25, page, pomName, brank,yymm,yymm2);
		return "beanList1";
	}
	public String findPageBean3(){
		String result="beanList1";
		if(backIndex==1){
			result="beanList";
		}
		pomName=(String)ActionContext.getContext().getSession().get("tabpom_name");
		brank=(String)ActionContext.getContext().getSession().get("tabpom_brank");
		pomName=(String)ActionContext.getContext().getSession().get("tabpom_yymm");
		brank=(String)ActionContext.getContext().getSession().get("tabpom_yymm2");
		bean=tabpomSer.findPageBean(25, page, pomName, brank,yymm,yymm2);
		return result;
	}
	public String add() throws IOException{
		/*文件上傳驗證*/
		if(files!=null){
			for(int i=0;i<files.size();i++){
				if(files.get(i)!=null){
					long filesize=files.get(i).length();
					String filetype=filesFileName.get(i).substring(filesFileName.get(i).lastIndexOf(".")).toLowerCase();
					if(filesize>5120000){
						//response.setContentType("text/html;charset=utf-8");
						//response.getWriter().print("<script>alert('文件不可超過5M!');window.opener=null;window.open('','_self');window.close()</script>");
						ajaxResult="3";
						return "add";
					}
					if(!filetype.equals(".bmp")&&!filetype.equals(".jpg")&&!filetype.equals(".jpeg")&&!filetype.equals(".gif")&&!filetype.equals(".tif")){
						//response.setContentType("text/html;charset=utf-8");
						//response.getWriter().print("<script>alert('只允許jpg,bmp,jpeg,gif,tif圖片!');window.opener=null;window.open('','_self');window.close()</script>");
						ajaxResult="4";
						return "add";
					}
					
				}
			}
		}
		
		/*文件上傳*/
		if(files!=null){//不為空代表有上傳附檔,不能寫成files.size()>0,否則報空指針
			tabpom.setFileMk("1");//標示是否帶有附檔
			//File uploadFile=new File(ServletActionContext.getServletContext().getRealPath("KyzexpFile\\"+tabpom.getPomNo()));//附檔上傳到項目
			File uploadFile_backup=new File("d:\\WebtabpomFile_backup\\"+tabpom.getPomNo());//附檔上傳到D盤(為了避免更新項目時丟失附檔,所在上傳到D盤)
			/*if(!uploadFile.exists()){
				uploadFile.mkdirs();
			}*/
			if(!uploadFile_backup.exists()){
				uploadFile_backup.mkdirs();
			}
			List<WebTabpomfile>list_file=new ArrayList<WebTabpomfile>();
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
					WebTabpomfile webtabFile=new WebTabpomfile();//函文附檔
					WebTabpomfileId fileId=new WebTabpomfileId();					
					fileId.setFilename(filesFileName.get(i));
					fileId.setWebTabpom(tabpom);
					webtabFile.setId(fileId);
					list_file.add(webtabFile);
				}
			}
			tabpom.setWebtabfiles(list_file);
		}
		
		switch(nullmk){
		case 0:
			pomNo=tabpomSer.findPomNoById(tabpom.getPomNo());
			if(pomNo!=null&&!pomNo.equals("")){
				ajaxResult="2";
			}else{
				try{
					if(list_fact.size()>0){					
						List<VWebFact>list_vfact=new ArrayList<VWebFact>();
						for(int i=0;i<list_fact.size();i++){
							VWebFact vfact=new VWebFact();
							vfact.setFactNo(list_fact.get(i));
							list_vfact.add(vfact);
						}
						tabpom.setWebfacts(list_vfact);
					}
					tabpomSer.add(tabpom);
					ajaxResult="0";
				}catch(Exception e){
					ajaxResult="1";
				}
			}
			break;
		case 1:
			try{
				if(list_fact.size()>0){					
					List<VWebFact>list_vfact=new ArrayList<VWebFact>();
					for(int i=0;i<list_fact.size();i++){
						VWebFact vfact=new VWebFact();
						vfact.setFactNo(list_fact.get(i));
						list_vfact.add(vfact);
					}
					tabpom.setWebfacts(list_vfact);
				}
				tabpomSer.add(tabpom);
				ajaxResult="0";
			}catch(Exception e){
				ajaxResult="1";
			}
		}
		
		return "add";
	}
	
	public String findById(){
		tabpom=tabpomSer.findById(pomNo);
		return "findById";
	}
	
	public String delete(){
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setObj("WebTabpom");
		log.setContent(pomNo);
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		log.setUsername(user.getUsername());
		tabpomSer.delete(pomNo,log);
		
		//同时删除文档20160227
		/*File file=new File("d:\\WebtabpomFile_backup\\"+pomNo);
		if(file.exists()){
			GlobalMethod.deletefile(file);//引用下面刪除文件夾方法
		}*/
		return "delete";
	}
	
	/*
	 * 文件上傳
	 */
	public void swfuploadfile() throws IOException{
		//File uploadFile_backup=new File("d:\\WebtabpomFile_backup\\"+tabpom.getPomNo());//附檔上傳到D盤(為了避免更新項目時丟失附檔,所在上傳到D盤)
		File uploadFile_backup=new File("d:\\WebtabpomFile_backup\\"+"test");
		/*if(!uploadFile.exists()){
			uploadFile.mkdirs();
		}*/
		if(!uploadFile_backup.exists()){
			uploadFile_backup.mkdirs();
		}
		/*for(int i=0;i<files.size();i++){							
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
				WebTabpomfile webtabFile=new WebTabpomfile();//函文附檔
				webtabFile.getId().setFilename(filesFileName.get(i));
				webtabFile.getId().setWebTabpom(tabpom);																			
			}
		}*/
		fileFileName = URLDecoder.decode(fileFileName, "UTF-8");  
		
		InputStream is = new FileInputStream(file);
	
		File deskFile = new File(uploadFile_backup, this.getFileFileName());
		OutputStream os = new FileOutputStream(deskFile);
		byte[] bytefer = new byte[1024];
		int length = 0;
		while ((length = is.read(bytefer)) != -1) {
			os.write(bytefer, 0, length);
		}
		os.close();
		is.close();
	}
	
	/**
	 * 搜索打印
	 * @throws IOException 
	 */
	public void printSerch() throws IOException{
		List<WebTabpom>list=tabpomSer.findByAny(pomName, brank, yymm, yymm2);
		for(WebTabpom tabpom:list){
			StringBuffer factSnames=new StringBuffer();
			for(VWebFact vwebfact:tabpom.getWebfacts()){
				factSnames.append(vwebfact.getFactSname()+" ");
			}
			tabpom.setVwebfacts(factSnames.toString());
		}
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
	    map.put("list_file", tabpom.getWebtabfiles());
	    map.put("list_fact", tabpom.getWebfacts());
	    if(lookordown.equals("look")){
	    	JasperHelper.exportmain("line", map, "webtabpom_one.jasper", list, pomNo, "jasper/webtabpom/");
	    }else{
	    	JasperHelper.exportmain("pdf", map, "webtabpom_one.jasper", list, pomNo, "jasper/webtabpom/");
	    }		
	}
	
	


}
