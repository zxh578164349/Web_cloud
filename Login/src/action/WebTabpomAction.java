package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import services.IWebTabpomServices;
import util.GlobalMethod;
import util.JasperHelper;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyzExpectmatmFile;
import entity.VWebFact;
import entity.WebFact;
import entity.WebFactId;
import entity.WebTabpom;
import entity.WebTabpomfile;
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
    
    private File file;
	private String fileFileName;
	private String fileContentType;
	private IWebTabpomServices tabpomSer;
	private javax.servlet.http.HttpServletResponse response;
	
	
	
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
		pomName=(String)ActionContext.getContext().getSession().get("tabpom_name");
		brank=(String)ActionContext.getContext().getSession().get("tabpom_brank");
		pomName=(String)ActionContext.getContext().getSession().get("tabpom_yymm");
		brank=(String)ActionContext.getContext().getSession().get("tabpom_yymm2");
		bean=tabpomSer.findPageBean(25, page, pomName, brank,yymm,yymm2);
		return "beanList1";
	}
	public String add() throws IOException{
		/*文件上傳驗證*/
		/*if(files!=null){
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
		}*/
		
		/*文件上傳*/
		/*if(files!=null){//不為空代表有上傳附檔,不能寫成files.size()>0,否則報空指針
			tabpom.setFileMk("1");//標示是否帶有附檔
			//File uploadFile=new File(ServletActionContext.getServletContext().getRealPath("KyzexpFile\\"+kyz.getId().getBillNo()));//附檔上傳到項目
			File uploadFile_backup=new File("d:\\WebtabpomFile_backup\\"+tabpom.getPomNo());//附檔上傳到D盤(為了避免更新項目時丟失附檔,所在上傳到D盤)
			if(!uploadFile.exists()){
				uploadFile.mkdirs();
			}
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
					WebTabpomfile webtabFile=new WebTabpomfile();//函文附檔
					webtabFile.getId().setFilename(filesFileName.get(i));
					webtabFile.getId().setWebTabpom(tabpom);																			
				}
			}
		}*/
		
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
		tabpomSer.delete(pomNo);
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
	
	


}