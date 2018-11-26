package action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import services.IKyVisabillmServices;
import services.IKyzExpectmatmFileServices;
import services.IWebNewproductServices;
import services.IWebuserEmailServices;
import util.GlobalMethod;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyVisabillm;
import entity.KyzExpectmatmLog;
import entity.WebNewproduct;
import entity.KyzExpectmatmFile;
import entity.WebNewproduct;
import entity.WebUser;


public class WebNewproductAction extends ActionSupport implements ServletResponseAware{
	private IWebNewproductServices webnewproSer;
	private int page;
	private PageBean bean;
	private WebNewproduct obj;
	private String factNo;
	private String billNo;
	private String createDateA;
	private String createDateB;
	private int mid;
	private List<File> files;
    private List<String> filesFileName;
    private List<String> filesContentType;
    private HttpServletResponse response;
    private String isnull;
    private IKyVisabillmServices visabillmSer;
	private IWebuserEmailServices webuseremailSer;
	private IKyzExpectmatmFileServices kyzexpfileSer;
	private int backIndex;//返回標識      0或null:不走返回路徑         1:走返回路徑
	private String ajaxResult;
	private String createDate;
	private String addorupdate;//添加或更新標識    update表示進入更新狀態
	
	
	
	public String getAddorupdate() {
		return addorupdate;
	}
	public void setAddorupdate(String addorupdate) {
		this.addorupdate = addorupdate;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getAjaxResult() {
		return ajaxResult;
	}
	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
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
	public WebNewproduct getObj() {
		return obj;
	}
	public void setObj(WebNewproduct obj) {
		this.obj = obj;
	}
	public String getFactNo() {
		return factNo;
	}
	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getCreateDateA() {
		return createDateA;
	}
	public void setCreateDateA(String createDateA) {
		this.createDateA = createDateA;
	}
	public String getCreateDateB() {
		return createDateB;
	}
	public void setCreateDateB(String createDateB) {
		this.createDateB = createDateB;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public void setWebnewproSer(IWebNewproductServices webnewproSer) {
		this.webnewproSer = webnewproSer;
	}
	
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
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
	
	public String getIsnull() {
		return isnull;
	}
	public void setIsnull(String isnull) {
		this.isnull = isnull;
	}
			
	public void setVisabillmSer(IKyVisabillmServices visabillmSer) {
		this.visabillmSer = visabillmSer;
	}
	public void setWebuseremailSer(IWebuserEmailServices webuseremailSer) {
		this.webuseremailSer = webuseremailSer;
	}
	
	public void setKyzexpfileSer(IKyzExpectmatmFileServices kyzexpfileSer) {
		this.kyzexpfileSer = kyzexpfileSer;
	}
	
	public int getBackIndex() {
		return backIndex;
	}
	public void setBackIndex(int backIndex) {
		this.backIndex = backIndex;
	}
	
	
	
	
	public String add() throws IOException{
		/*文件上傳驗證*/
		if(files!=null&&files.get(0)!=null){
			for(int i=0;i<files.size();i++){
				if(files.get(i)!=null){
					long filesize=files.get(i).length();
					String filetype=filesFileName.get(i).substring(filesFileName.get(i).lastIndexOf(".")).toLowerCase();
					if(filesize>2048000){
						response.setContentType("text/html;charset=utf-8");
						response.getWriter().print("<script>window.parent.alert('文件不可超過2M!');window.parent.layer.closeAll()</script>");
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
			obj.setVisaTypeM(obj.getVisaType().substring(0,2));
			if(files!=null&&files.get(0)!=null){
				obj.setFilesYn("1");//標示是否帶有附檔
			}
			if(isnull.equals("isNull")){//start if
				String billno=obj.getBillNo();
				WebNewproduct pro=webnewproSer.findByBillNo(billno);
				if(pro==null){
					//obj.setVisaTypeM(obj.getVisaType().substring(0,2));
					webnewproSer.add(obj);				
					KyVisabillm vbm=visabillmSer.findById(obj.getFactNo(), obj.getVisaType(), obj.getBillNo());				      
				    List<String>list_emailPwd=webuseremailSer.findByFactNoAEmailPwd2(vbm.getId().getFactNo(),vbm.getSignerNext());											      
					GlobalMethod.sendNewEmail(vbm,list_emailPwd);//發送郵件									     
					}else{
						response.setContentType("text/html;charset=utf-8");
						response.getWriter()
						.print("<script>window.parent.alert('數據庫已存在("
								+ obj.getFactNo()					
								+ " "
								+ obj.getBillNo()
								+ ")!');window.parent.layer.closeAll()</script>");
						return null;
					}								
			}//end if
			else{
				//obj.setVisaTypeM(obj.getVisaType().substring(0,2));
				webnewproSer.add(obj);
			}
			/*文件上傳*/
			if(files!=null&&files.get(0)!=null){//不為空代表有上傳附檔,不能寫成files.size()>0,否則報空指針
				//obj.setFilesYn("1");//標示是否帶有附檔
				//File uploadFile=new File(ServletActionContext.getServletContext().getRealPath("KyzexpFile\\"+kyz.getId().getBillNo()));//附檔上傳到項目
				File uploadFile_backup=new File("d:\\webnewpro_backup\\"+obj.getBillNo());//附檔上傳到D盤(為了避免更新項目時丟失附檔,所在上傳到D盤)			
				if(!uploadFile_backup.exists()){
					uploadFile_backup.mkdirs();
				}
				for(int i=0;i<files.size();i++){							
					if(files.get(i)!=null){					    																																		
						KyzExpectmatmFile kyzexpFile=new KyzExpectmatmFile();//函文附檔
						kyzexpFile.setBillno(obj.getBillNo());
						kyzexpFile.setFilename(filesFileName.get(i));
						WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
						String username=user.getName();
						kyzexpFile.setUsername(username);
						kyzexpFile.setFactNo(obj.getFactNo());
						kyzexpFile.setVisaTypeM(obj.getVisaType().substring(0,2));
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
	
	public String findPageBean(){
		ActionContext.getContext().getSession().remove("allRow");
		ActionContext.getContext().getSession().put("n_factNo", factNo);
		ActionContext.getContext().getSession().put("n_billNo", billNo);
		ActionContext.getContext().getSession().put("n_createDateA", createDateA);
		ActionContext.getContext().getSession().put("n_createDateB", createDateB);
		bean=webnewproSer.findPageBean(page, 0, factNo, billNo, createDateA, createDateB);		
		return "findPageBean";
	}
	
	public String findPageBean2(){		
		this.findPageBean();
		return "findPageBean1";
	}
	
	public String findPageBean3(){
		factNo=(String)ActionContext.getContext().getSession().get("n_factNo");
		billNo=(String)ActionContext.getContext().getSession().get("n_billNo");
		createDateA=(String)ActionContext.getContext().getSession().get("n_createDateA");
		createDateB=(String)ActionContext.getContext().getSession().get("n_createDateB");
		bean=webnewproSer.findPageBean(page, 0, factNo, billNo, createDateA, createDateB);
		if(backIndex==1){
			return "findPageBean";
		}else{
			return "findPageBean1";
		}		
	}
	
	public String makeBillNo(){
		String maxbillno=webnewproSer.findByfactNoACreatedate(factNo, createDate);
		StringBuffer bn=new StringBuffer();
		bn.append("NP");
		bn.append(factNo);
		bn.append(createDate.substring(2,8));
		if(maxbillno==null||"".equals(maxbillno)){
			bn.append("01");
		}else{
			int num=Integer.parseInt(maxbillno.substring(maxbillno.length()-2, maxbillno.length()));
			if(num+1<10){
				bn.append("0"+(num+1));
			}else{
				bn.append(num+1);
			}
		}
		ajaxResult=bn.toString();
		return "makeBillNo";	
	}
	
	public String findByBillNo(){
		addorupdate="update";
		obj=webnewproSer.findByBillNo(billNo);
		if("1".equals(obj.getFilesYn())){
			List<KyzExpectmatmFile> list=kyzexpfileSer.findByBillNo(billNo);					    
			ActionContext.getContext().getSession().put("list_filesexp", list);
		}		
		return "findByBillNo";	
	}
	
	public String delete(){
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setObj("WebNewproduct");
		log.setBillNo(billNo);
		webnewproSer.delete(billNo, log);
		File file=new File("d:\\webnewpro_backup\\"+billNo);
		if(file.exists()){
			GlobalMethod.deletefile(file);//引用下面刪除文件夾方法
		}
		return "delete";
		
	}
		
}
