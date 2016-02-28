package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletResponseAware;

import services.IWebFactServices;
import services.IWebPhonebookServices;
import util.GlobalMethod;
import util.ImportExcel;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyzExpectmatmLog;
import entity.WebPhonebook;
import entity.WebUser;

public class WebPhonebookAction extends ActionSupport implements ServletResponseAware{
	private IWebPhonebookServices webphonebookSer;
	private IWebFactServices webFactSer;
	private WebPhonebook webphone;
	private String factNo;
	private String department;
	private String post;
	private String userName;
	private String phoneA;
	private String phoneB;
	private String phoneC;
	private String email;
	private PageBean bean;
	private int page;
	private long pbId;
	private String ajaxResult;//0:提交成功      1:提交失敗
	private int backIndex;//返回標識      0或null:不走返回路徑         1:走返回路徑
	private File file;
    private String fileFileName;
    private String fileContentType;
    private javax.servlet.http.HttpServletResponse response;
    private JSONArray jsons;
    private String isnull;
    
    
    
	public String getIsnull() {
		return isnull;
	}
	public void setIsnull(String isnull) {
		this.isnull = isnull;
	}
	public String getPhoneA() {
		return phoneA;
	}
	public void setPhoneA(String phoneA) {
		this.phoneA = phoneA;
	}
	public String getPhoneB() {
		return phoneB;
	}
	public void setPhoneB(String phoneB) {
		this.phoneB = phoneB;
	}
	public String getPhoneC() {
		return phoneC;
	}
	public void setPhoneC(String phoneC) {
		this.phoneC = phoneC;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public JSONArray getJsons() {
		return jsons;
	}
	public void setJsons(JSONArray jsons) {
		this.jsons = jsons;
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
	public int getBackIndex() {
		return backIndex;
	}
	public void setBackIndex(int backIndex) {
		this.backIndex = backIndex;
	}
	public long getPbId() {
		return pbId;
	}
	public void setPbId(long pbId) {
		this.pbId = pbId;
	}
	public String getAjaxResult() {
		return ajaxResult;
	}
	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public WebPhonebook getWebphone() {
		return webphone;
	}
	public void setWebphone(WebPhonebook webphone) {
		this.webphone = webphone;
	}
	public void setWebphonebookSer(IWebPhonebookServices webphonebookSer) {
		this.webphonebookSer = webphonebookSer;
	}
	
	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	public String add(){
		WebPhonebook oldbook=(WebPhonebook)ActionContext.getContext().getSession().get("findById_webphone");//进入修改页面时缓存的
		try{			
			webphonebookSer.add(oldbook,webphone,isnull);
			ajaxResult="0";
		}catch(Exception e){
			System.out.println("action**********************"+e+"**********************action");
			ajaxResult="1";
		}		
		return "add";
	}
	public String findPageBean(){
		ActionContext.getContext().getSession().remove("allrow");
		ActionContext.getContext().getSession().remove("public_factNo");
		ActionContext.getContext().getSession().remove("public_department");
		ActionContext.getContext().getSession().remove("public_post");
		ActionContext.getContext().getSession().remove("public_username");
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		bean=webphonebookSer.findPageBean(25, page, factNo, department, post, userName);
		return "beanList";
	}
	public String findPageBean2(){
		ActionContext.getContext().getSession().remove("allrow");
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		ActionContext.getContext().getSession().put("public_factNo", factNo);
		ActionContext.getContext().getSession().put("public_department", department);
		ActionContext.getContext().getSession().put("public_post", post);
		ActionContext.getContext().getSession().put("public_username", userName);
		bean=webphonebookSer.findPageBean(25, page, factNo, department, post, userName);
		return "beanList1";
	}
	public String findPageBean3(){
		String result="beanList1";
		if(backIndex==1){
			result="beanList";
		}
		factNo=(String)ActionContext.getContext().getSession().get("public_factNo");
		department=(String)ActionContext.getContext().getSession().get("public_department");
		post=(String)ActionContext.getContext().getSession().get("public_post");
		userName=(String)ActionContext.getContext().getSession().get("public_username");
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		bean=webphonebookSer.findPageBean(25, page, factNo, department, post, userName);
		return result;
	}
	public String findById(){
		ActionContext.getContext().getSession().remove("findById_webphone");
		webphone=webphonebookSer.findById(factNo, department, post, userName, phoneA, phoneB, phoneC, email);
		ActionContext.getContext().getSession().put("findById_webphone", webphone);
		return "findById";
	}
	public String delete(){
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setObj("WebPhonebook");
		log.setFactNo(factNo);
		log.setContent(department+post+userName);
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		log.setUsername(user.getUsername());
		webphonebookSer.delete(factNo, department, post, userName, phoneA, phoneB, phoneC, email,log);
		return "delete";
	}
	
	public String importExcel() throws Exception{
		String username=((WebUser)ActionContext.getContext().getSession().get("loginUser")).getUsername();
		String path="d:\\Webphonebook_backup\\"+new SimpleDateFormat("yyyyMMdd").format(new Date());//Excel文檔存放目錄
		ajaxResult="0";				
		/*文件上傳*/
		if(file!=null){//不為空代表有上傳附檔,不能寫成files.size()>0,否則報空指針
			//File uploadFile=new File(ServletActionContext.getServletContext().getRealPath("KyzexpFile\\"+kyz.getId().getBillNo()));//附檔上傳到項目
			File uploadFile_backup=new File(path);//附檔上傳到D盤(為了避免更新項目時丟失附檔,所在上傳到D盤)
			/*if(!uploadFile.exists()){
				uploadFile.mkdirs();
			}*/
			if(!uploadFile_backup.exists()){
				uploadFile_backup.mkdirs();
			}																						
					FileInputStream in=new FileInputStream(file);
					//FileOutputStream out=new FileOutputStream(uploadFile+"\\"+filesFileName.get(i));
					FileOutputStream out_backup=new FileOutputStream(uploadFile_backup+"\\"+fileFileName);//備份
					byte[]b=new byte[1024];
					int length=0;
					while((length=in.read(b))>0){
						out_backup.write(b,0,length);//備份
					}																																				
		}
		List<Object[]>list_fact=webFactSer.findAllFact_obj();
		//List<Object[]>list_fact=(List<Object[]>)ActionContext.getContext().getSession().get("login_facts");//用戶登錄時緩存的廠別信息
		try{
			Map<String,Object>map=new ConcurrentHashMap(ImportExcel.exportListFromExcel(new File(path+"\\"+fileFileName)));
			Map<String,Object>map_new=new HashMap<String,Object>();
			for(String key:map.keySet()){//for
				List<Object[]>list_oneFact=new ArrayList<Object[]>();
				List<String>list=(List<String>)map.get(key);
				for(int i=1;i<list.size();i++){//表頭不需要,所以從1開始					
						list_oneFact.add(list.get(i).split("__"));					
				}
				for(int j=0;j<list_fact.size();j++){
					if(key.replace("(", "").replace(")", "").contains(list_fact.get(j)[0].toString())){
						map_new.put(list_fact.get(j)[0].toString(), list_oneFact);
						break;
					}else if(j==list_fact.size()-1){
						//map_new.put(key, list_oneFact);
						map.remove(key);//把不存在的廠別清除
					}
				}								
			}//for	
			if(map_new!=null&&map_new.size()!=0){
				webphonebookSer.addLarge(map_new, username);
				ajaxResult="0";
			}else{
				ajaxResult="1";
			}			
		}catch(Exception e){
			ajaxResult="1";
			System.out.println("action**************************************"+e+"*******************************************action");
		}
		
		return "importExcel";
	}
	
	public void print() throws IOException{
		List<WebPhonebook>list=webphonebookSer.findToPrint(factNo, department, post, userName);
		GlobalMethod.print_webphonebook(list, factNo, department, "webphonebook.jasper", response);
	}
	
	public String findDepartments(){
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}		
		List<String>list=webphonebookSer.findDepartments(factNo);
		if(list.size()>0){
			jsons=JSONArray.fromObject(list);
		}
		return "findDepartments";
	}
	public String findPosts(){
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}		
		List<String>list=webphonebookSer.findPosts(factNo);
		if(list.size()>0){
			jsons=JSONArray.fromObject(list);
		}
		return "findPosts";
	}
	

}
