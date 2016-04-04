package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.Transaction;

import net.sf.json.JSONArray;

import services.IKyzExpectmatmFileServices;
import services.IKyzExpectmatmLogServices;
import services.IWebFactServices;
import services.IWebFactorderServices;
import util.GlobalMethod;
import util.ImportExcel;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.IKyzExpectmatmFileDao;

import entity.KyzExpectmatmFile;
import entity.KyzExpectmatmLog;
import entity.WebFactorder;
import entity.WebUser;

public class WebFactOrderAction extends ActionSupport implements ServletResponseAware{
	private IWebFactorderServices webfactorderSer;
	private IWebFactServices webFactSer;
	private IKyzExpectmatmLogServices kyzExpLogSer;
	private WebFactorder factorder;
	private PageBean bean;
	private int page;
	private String factNo;
	private String factArea;
	private String brank;
	private String customer;
	private String model;
	private String component;
	private JSONArray jsons;
	private List<String>factNos=new ArrayList<String>();
	private List<String>components=new ArrayList<String>();
	private List<String>branks=new ArrayList<String>();
	private List<String>customers=new ArrayList<String>();
	private List<String>models=new ArrayList<String>();
	private javax.servlet.http.HttpServletResponse response;
	private String yymm;
	private String yymm2;
	private String year;
	private List<String>factSnames=new ArrayList<String>();
	private List<String>factAreas=new ArrayList<String>();
	private File file;
    private String fileFileName;
    private String fileContentType;
    private String ajaxResult;//申請函文時返回的ajax結果,   0:提交成功       1：上传的文档格式不符合要求   2：excel导入失败     3：excel数据格式不符合要求
    private long orderid; 
    
    
    
	
	
	
	public String getFactArea() {
		return factArea;
	}
	public void setFactArea(String factArea) {
		this.factArea = factArea;
	}
	public List<String> getFactAreas() {
		return factAreas;
	}
	public void setFactAreas(List<String> factAreas) {
		this.factAreas = factAreas;
	}
	public long getOrderid() {
		return orderid;
	}
	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}
	public String getYymm2() {
		return yymm2;
	}
	public void setYymm2(String yymm2) {
		this.yymm2 = yymm2;
	}
	public String getAjaxResult() {
		return ajaxResult;
	}
	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
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
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	public List<String> getFactSnames() {
		return factSnames;
	}
	public void setFactSnames(List<String> factSnames) {
		this.factSnames = factSnames;
	}
	public String getYymm() {
		return yymm;
	}
	public void setYymm(String yymm) {
		this.yymm = yymm;
	}
	public List<String> getComponents() {
		return components;
	}
	public void setComponents(List<String> components) {
		this.components = components;
	}
	public List<String> getBranks() {
		return branks;
	}
	public void setBranks(List<String> branks) {
		this.branks = branks;
	}
	public List<String> getCustomers() {
		return customers;
	}
	public void setCustomers(List<String> customers) {
		this.customers = customers;
	}
	public List<String> getModels() {
		return models;
	}
	public void setModels(List<String> models) {
		this.models = models;
	}
	public JSONArray getJsons() {
		return jsons;
	}
	public void setJsons(JSONArray jsons) {
		this.jsons = jsons;
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
	
	
	public List<String> getFactNos() {
		return factNos;
	}
	public void setFactNos(List<String> factNos) {
		this.factNos = factNos;
	}
	public String getBrank() {
		return brank;
	}
	public void setBrank(String brank) {
		this.brank = brank;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public WebFactorder getFactorder() {
		return factorder;
	}
	public void setFactorder(WebFactorder factorder) {
		this.factorder = factorder;
	}
	public void setWebfactorderSer(IWebFactorderServices webfactorderSer) {
		this.webfactorderSer = webfactorderSer;
	}
	
	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}
	
	
	
	public void setKyzExpLogSer(IKyzExpectmatmLogServices kyzExpLogSer) {
		this.kyzExpLogSer = kyzExpLogSer;
	}
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	public String importExcel() throws IOException{
		String username=((WebUser)ActionContext.getContext().getSession().get("loginUser")).getUsername();
		String path="d:\\Webfactorder_backup\\"+new SimpleDateFormat("yyyyMMdd").format(new Date());//Excel文檔存放目錄
		String result="importExcel";
		ajaxResult="0";
		/*String path="d:\\北越&鞋塑2015接單匯總-1201.xls";
		List<String>list_all=ImportExcel.exportListFromExcel(new File(path), 1);
		webfactorderSer.addLarge(list_all);*/
		
		/*文件上傳驗證*/
		/*if(file!=null){							
					
					String filetype=fileFileName.substring(fileFileName.lastIndexOf(".")).toLowerCase();
					long filesize=file.length();
					if(filesize>5120000){
						response.setContentType("text/html;charset=utf-8");
						response.getWriter().print("<script>alert('文件不可超過5M!');window.opener=null;window.open('','_self');window.close()</script>");
						return null;
					}
					if(!filetype.equals(".xls")&&!filetype.equals(".xlsx")){
						response.setContentType("text/html;charset=utf-8");
						//response.getWriter().print("<script>alert('只允許上傳Excel文檔!');window.opener=null;window.open('','_self');window.close()</script>");
						//return null;
						ajaxResult="1";//只允許上傳Excel文檔
						return result;
						
					}												
		}*/
		
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
		
						
		/*******************數據導入到數據庫20160117********************/
		try{
			List<String>list_imp=ImportExcel.exportListFromExcel(new File(path+"\\"+fileFileName), 0);
			List<List<String>>list_all=new ArrayList<List<String>>();
			if(list_imp.size()>0){
				List<Object[]>list_fact=webFactSer.findAllFact_obj();
				//List<Object[]>list_fact=(List<Object[]>)ActionContext.getContext().getSession().get("login_facts");//用戶登錄時緩存的廠別信息
				for(int i=0;i<list_imp.size();i++){//for
					if(i==0){
						list_all.add(Arrays.asList(list_imp.get(i).split("__")));
					}else{
						List<String>list_data=new ArrayList<String>(Arrays.asList(list_imp.get(i).split("__")));
						for(int x=0;x<list_fact.size();x++){
							if(list_data.get(1).replace("(", "").replace(")", "").contains(list_fact.get(x)[1].toString())||
									list_data.get(1).replace("(", "").replace(")", "").contains(list_fact.get(x)[0].toString())){
								list_data.add(list_fact.get(x)[0].toString());
								break;
							}else if(x==list_fact.size()-1){
								list_data.add("null");
							}
						}
						list_all.add(list_data);
					}
				}//for
				
				try{
					//webfactorderSer.large2(list_all,username);
					if(list_all!=null&&list_all.size()>0){
						webfactorderSer.addLarge2(list_all, username);
					}				
				}catch(Exception e){
					if(e.toString().contains("org.springframework")){
						ajaxResult="2";//數據重複，导入Excel失败
					}else{
						ajaxResult="4";//Excel文檔格式不兼容，导入Excel失败
					}
					System.out.println("*********action*********"+e.toString()+"*********action*********");
				}
				
			}else{
				ajaxResult="3";//Excel数据结构不符合要求,不允许导入
			}
		}catch(Exception e){
			if(e.toString().contains("org.springframework")){
				ajaxResult="2";//數據重複，导入Excel失败
			}else{
				ajaxResult="4";//Excel文檔格式不兼容，导入Excel失败
			}
			System.out.println("*********action*********"+e.toString()+"*********action*********");
		}
		/*******************數據導入到數據庫20160117********************/
		
		return result;
	}
	public String findPageBean(){
		
		//System.out.println(factNos.getClass().getName());
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		ActionContext.getContext().getSession().remove("allrow");//首次進入，清除分頁的總條數（dao層中的allrow）
		ActionContext.getContext().getSession().remove("public_factareas");
		ActionContext.getContext().getSession().remove("public_factno");
		ActionContext.getContext().getSession().remove("public_brank");
		ActionContext.getContext().getSession().remove("public_customer");
		ActionContext.getContext().getSession().remove("public_model");
		ActionContext.getContext().getSession().remove("public_component");
		ActionContext.getContext().getSession().remove("public_factnos");
		ActionContext.getContext().getSession().remove("public_yymm");
		ActionContext.getContext().getSession().remove("public_yymm2");
		bean=webfactorderSer.findPageBean(25, page, factAreas, branks, customers, models, components,factNo,factNos,yymm,yymm2);
		for(WebFactorder order:(List<WebFactorder>)bean.getList()){
			this.factNoToFactSname(order);
		}
		
		return "beanList";
		
	}
	public String findPageBean2(){
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		//System.out.println(factNos.getClass().getName());//com.opensymphony.xwork2.util.XWorkList
		ActionContext.getContext().getSession().remove("allrow");//條件查詢，清除分頁的總條數（dao層中的allrow）
		/*ActionContext.getContext().getSession().remove("public_factnames");
		ActionContext.getContext().getSession().remove("public_brank");
		ActionContext.getContext().getSession().remove("public_customer");
		ActionContext.getContext().getSession().remove("public_model");
		ActionContext.getContext().getSession().remove("public_component");*/
		ActionContext.getContext().getSession().put("public_factno", factNo);
		ActionContext.getContext().getSession().put("public_factareas",factAreas);
		ActionContext.getContext().getSession().put("public_brank",branks);
		ActionContext.getContext().getSession().put("public_customer",customers);
		ActionContext.getContext().getSession().put("public_model",models);
		ActionContext.getContext().getSession().put("public_component",components);
		ActionContext.getContext().getSession().put("public_factnos", factNos);
		ActionContext.getContext().getSession().put("public_yymm", yymm);
		ActionContext.getContext().getSession().put("public_yymm2", yymm2);
		bean=webfactorderSer.findPageBean(25, page, factAreas, branks, customers, models, components,factNo,factNos,yymm,yymm2);
		for(WebFactorder order:(List<WebFactorder>)bean.getList()){
			this.factNoToFactSname(order);
		}
		return "beanList1";
	}
	public String findPageBean3(){
		factNo=(String)ActionContext.getContext().getSession().get("public_factno");
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		factAreas=(List<String>)ActionContext.getContext().getSession().get("public_factareas");
		branks=(List<String>)ActionContext.getContext().getSession().get("public_brank");
		customers=(List<String>)ActionContext.getContext().getSession().get("public_customer");
		models=(List<String>)ActionContext.getContext().getSession().get("public_model");
		components=(List<String>)ActionContext.getContext().getSession().get("public_component");
		year=(String)ActionContext.getContext().getSession().get("public_year");
		factNos=(List<String>)ActionContext.getContext().getSession().get("public_factnos");
		yymm=(String)ActionContext.getContext().getSession().get("public_yymm");
		yymm2=(String)ActionContext.getContext().getSession().get("public_yymm2");
		bean=webfactorderSer.findPageBean(25, page, factAreas, branks, customers, models, components,factNo,factNos,yymm,yymm2);
		for(WebFactorder order:(List<WebFactorder>)bean.getList()){
			this.factNoToFactSname(order);
		}
		return "beanList1";
	}
	
	public void init(){
		ActionContext.getContext().getSession().put("factNos", factNos);
	}
	public void init2(){
		ActionContext.getContext().getSession().put("factAreas", factAreas);
	}
	/**
	 * 从WebFactorder中获取所有的部件
	 * @return
	 */
	public String findComponent(){
		factNos=(List<String>)ActionContext.getContext().getSession().get("factNos");
		factAreas=(List<String>)ActionContext.getContext().getSession().get("factAreas");
		if(factAreas.size()>0){
			List<String>list=webfactorderSer.findComponent(factNos,factAreas);
			jsons=JSONArray.fromObject(list);
		}
		
		return "findComponent";
	}
	
	/**
	 * 从WebFactorder中获取所有的品牌
	 * @return
	 */
	public String findBrank(){
		factNos=(List<String>)ActionContext.getContext().getSession().get("factNos");
		factAreas=(List<String>)ActionContext.getContext().getSession().get("factAreas");
		if(factAreas.size()>0){
			List<String>list=webfactorderSer.findBrank(factNos,factAreas);
			jsons=JSONArray.fromObject(list);
		}
		
		return "findBrank";
	}
	
	/**
	 * 从WebFactorder中获取所有的客户
	 */
	public String findCustomer(){
		factNos=(List<String>)ActionContext.getContext().getSession().get("factNos");
		factAreas=(List<String>)ActionContext.getContext().getSession().get("factAreas");
		if(factAreas.size()>0){
			List<String>list=webfactorderSer.findCustomer(factNos,factAreas);
			jsons=JSONArray.fromObject(list);
		}		
		return "findCustomer";
	}
	
	/**
	 *从WebFactorder中获取所有的model
	 * @return
	 */
	public String findModel(){
		factNos=(List<String>)ActionContext.getContext().getSession().get("factNos");
		factAreas=(List<String>)ActionContext.getContext().getSession().get("factAreas");
		if(factAreas.size()>0){
			List<String>list=webfactorderSer.findModel(factNos,factAreas);
			jsons=JSONArray.fromObject(list);
		}
		
		return "findModel";
	}
	/**
	 *从WebFactorder中获取所有的工廠名稱
	 * @return
	 */
	/*public String findFactSname(){
		factNos=(List<String>)ActionContext.getContext().getSession().get("factNos");
		if(factNos.size()>0){
			List<String>list=webfactorderSer.findFactSname(factNos);
			jsons=JSONArray.fromObject(list);
		}		
		return "findFactSname";
	}*/
	
	/**
	 *从WebFactorder中获取所有的廠別狀態
	 * @return
	 */
	public String findFactArea(){
		factNos=(List<String>)ActionContext.getContext().getSession().get("factNos");
		if(factNos.size()>0){
			List<String>list=webfactorderSer.findFactArea(factNos);
			jsons=JSONArray.fromObject(list);
		}		
		return "findFactArea";
	}
	
	
	
	
	
	/**
	 * 打印搜索分組統計數據
	 * 快速準確修改版
	 * @throws IOException 
	 */
	public void print4() throws IOException{
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		List<Object[]>list=webfactorderSer.findByGroup2(factNos,factAreas, branks, customers, models, components,factNo,yymm,yymm2);
		List<Object[]>list2=webfactorderSer.findByGroup(factNos,factAreas, branks, customers, models, components,factNo,yymm,yymm2);
		List<String>list_date=GlobalMethod.getDateNum(yymm, yymm2);
		//List<List<Double>>list_all=new ArrayList<List<Double>>();
		List<Map<String,Double>>list_all=new ArrayList<Map<String,Double>>();
		
		for(int i=0;i<list.size();i++){//for1
			for(int x=0;x<6;x++){
				if(list.get(i)[x]==null){
					list.get(i)[x]="";
				}
			}
			//List<Double>list_one=new ArrayList<Double>();
			Map<String,Double>map=new LinkedHashMap<String,Double>();
			for(int j=0;j<list2.size();j++){//for2
				for(int y=0;y<6;y++){
					if(list2.get(j)[y]==null){
						list2.get(j)[y]="";
					}
				}
				if(list.get(i)[0].toString().equals(list2.get(j)[0].toString())&&
						list.get(i)[1].toString().equals(list2.get(j)[1].toString())&&
						list.get(i)[2].toString().equals(list2.get(j)[2].toString())&&
						list.get(i)[3].toString().equals(list2.get(j)[3].toString())&&
						list.get(i)[4].toString().equals(list2.get(j)[4].toString())&&
						list.get(i)[5].toString().equals(list2.get(j)[5].toString())){
					//list_one.add(Double.parseDouble(list2.get(j)[6].toString()));
					map.put((String)list2.get(j)[6], Double.parseDouble(list2.get(j)[7].toString()));
				}
			}//for2
			list_all.add(map);
		}//for1
		/*********************廠別代號轉換成廠名*****************************/
		List<Object[]>list_facts=(List<Object[]>)ActionContext.getContext().getSession().get("login_facts");//用戶登錄時已經緩存
		for(int i=0;i<list.size();i++){
			for(int j=0;j<list_facts.size();j++){
				if(list.get(i)[0].toString().equals(list_facts.get(j)[0].toString())){
					list.get(i)[0]=list_facts.get(j)[1];
					break;
				}
			}
		}
		
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet("sheet1");
		
		HSSFCellStyle cs=wb.createCellStyle();
		cs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs.setBorderTop(HSSFCellStyle.BORDER_THIN);
		
		HSSFDataFormat frm=wb.createDataFormat();
		
		//數字格式
		HSSFCellStyle cs_data=wb.createCellStyle();
		cs_data.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_data.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_data.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_data.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_data.setDataFormat(frm.getFormat("#,##0.0"));
		
		
		//標題樣式
		HSSFCellStyle cs_title=wb.createCellStyle();
		HSSFFont font_title=wb.createFont();
		font_title.setFontHeightInPoints((short)14);
		font_title.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cs_title.setFont(font_title);
		
		// 紅字體
		HSSFCellStyle cs_font_red = wb.createCellStyle();
		HSSFFont font_red = wb.createFont();
		font_red.setFontHeightInPoints((short) 10);
		font_red.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		font_red.setColor(HSSFFont.COLOR_RED);
		cs_font_red.setFont(font_red);
		cs_font_red.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_font_red.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_font_red.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_font_red.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_font_red.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_font_red.setBorderTop(HSSFCellStyle.BORDER_THIN);
		
		// 藍字體
		HSSFCellStyle cs_font_blue = wb.createCellStyle();
		HSSFFont font_blue = wb.createFont();
		font_blue.setFontHeightInPoints((short) 10);
		font_blue.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		font_blue.setColor(IndexedColors.BLUE.getIndex());
		cs_font_blue.setFont(font_blue);
		cs_font_blue.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_font_blue.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_font_blue.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_font_blue.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_font_blue.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_font_blue.setBorderTop(HSSFCellStyle.BORDER_THIN);
		
		//表頭樣式
		HSSFCellStyle cs_head = wb.createCellStyle();
		HSSFFont font_head=wb.createFont();
		font_head.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cs_head.setFont(font_head);
		cs_head.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_head.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_head.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_head.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_head.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_head.setFillForegroundColor(IndexedColors.YELLOW.getIndex());				
		cs_head.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		
		
		
		
		/***************************初始化表格************************************/
		int index=0;//不顯示列名標識，沒有選擇的條件就不顯示，則index-1     20160129
		for(int i=0;i<20;i++){			
				sheet.setColumnWidth(i, 5000);														
		}
		
		sheet.createRow(0).createCell(0);
		sheet.getRow(0).getCell(0).setCellValue("訂單月份匯總表");
		sheet.getRow(0).getCell(0).setCellStyle(cs_title);
		for(int i=1;i<list.size()+2+1;i++){//數據行+標題行+表頭行+匯總行20160129
			sheet.createRow(i);
			for(int j=0;j<30;j++){
				sheet.getRow(i).createCell(j);
				//sheet.getRow(i).getCell(j).setCellStyle(cs);
				sheet.getRow(i).getCell(j).setCellStyle(cs_data);//20160121
			}
			if(i==1){
				sheet.getRow(i).getCell(0).setCellValue("廠別");
				if(factAreas.size()>0){
					sheet.getRow(i).getCell(1).setCellValue("廠別狀態");
				}else{
					index--;
				}
				
				if(branks.size()>0){
					sheet.getRow(i).getCell(2+index).setCellValue("品牌");
				}else{
					index--;
				}
				if(customers.size()>0){
					sheet.getRow(i).getCell(3+index).setCellValue("客戶");
				}else{
					index--;
				}
				if(models.size()>0){
					sheet.getRow(i).getCell(4+index).setCellValue("模具");
				}else{
					index--;
				}
				if(components.size()>0){
					sheet.getRow(i).getCell(5+index).setCellValue("部件");	
				}else{
					index--;
				}
				
				
							
				for(int k=0;k<list_date.size();k++){					
					sheet.getRow(i).getCell(6+k+index).setCellValue(list_date.get(k));										
				}
				sheet.getRow(i).getCell(6+list_date.size()+index).setCellValue("匯總");
				for(int l=0;l<=6+list_date.size()+index;l++){
					sheet.getRow(i).getCell(l).setCellStyle(cs_head);
				}
			}										
		}
		/***************************初始化表格************************************/
		
		
		/***************************填充數據************************************/
		int index2=0;//不顯示列名標識，沒有選擇的條件就不顯示，則index2-1     20160129
		for(int i=0;i<list.size();i++){
			sheet.getRow(i+2).getCell(0).setCellValue(list.get(i)[0].toString());
			if(factAreas.size()>0){
				sheet.getRow(i+2).getCell(1).setCellValue(list.get(i)[1].toString());
			}else{
				index2--;
			}
			
			if(branks.size()>0){
				sheet.getRow(i+2).getCell(2+index2).setCellValue(list.get(i)[2].toString());
			}else{
				index2--;
			}
			if(customers.size()>0){
				sheet.getRow(i+2).getCell(3+index2).setCellValue(list.get(i)[3].toString());
			}else{
				index2--;
			}
			if(models.size()>0){
				sheet.getRow(i+2).getCell(4+index2).setCellValue(list.get(i)[4].toString());
			}else{
				index2--;
			}
			if(components.size()>0){
				sheet.getRow(i+2).getCell(5+index2).setCellValue(list.get(i)[5].toString());
			}else{
				index2--;
			}
			
			
			double row_total=0.0;						
			for(int j=0;j<list_date.size();j++){//for
				if(list_all.get(i).size()==0){
					sheet.getRow(i+2).getCell(6+j+index2).setCellValue(0);
					sheet.getRow(i+2).getCell(6+j+index2).setCellStyle(cs_font_red);
				}
				for(String key:list_all.get(i).keySet()){
					if(list_date.get(j).equals(key)){
						sheet.getRow(i+2).getCell(6+j+index2).setCellValue(list_all.get(i).get(key));
						row_total=row_total+list_all.get(i).get(key);
						list_all.get(i).remove(key);
						
					}else{
						sheet.getRow(i+2).getCell(6+j+index2).setCellValue(0);
						sheet.getRow(i+2).getCell(6+j+index2).setCellStyle(cs_font_red);
					}
					break;
				}
			}//for
			sheet.getRow(i+2).getCell(6+list_date.size()+index2).setCellValue(row_total);
			/*************************最後一行是各箇月份的匯總****************************/
            if(i==list.size()-1){
				for(int x=0;x<=list_date.size();x++){
					double col_total=0;
					for(int y=0;y<list.size();y++){
						col_total=col_total+sheet.getRow(y+2).getCell(6+x+index2).getNumericCellValue();
					}
					if(x==0){
						sheet.getRow(i+3).getCell(5+x+index2).setCellValue("合計");
						sheet.getRow(i+3).getCell(5+x+index2).setCellStyle(cs_head);
					}
					sheet.getRow(i+3).getCell(6+x+index2).setCellValue(col_total);
				}
			}
            /*************************最後一行是各箇月份的匯總****************************/
			index2=0;//一次循環，就回滾到0    20160129
			
			
		}
		/***************************填充數據************************************/
		//OutputStream os=new FileOutputStream("d:\\tttttt.xls");
		ServletOutputStream os=response.getOutputStream();
		response.setContentType("application/vnd.ms-excel");
		String fileName="fact_reportTotal_"+".xls";
		int msi=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");
		if(msi>0){
			fileName=java.net.URLEncoder.encode(fileName,"utf-8");
		}else{
			fileName=new String(fileName.getBytes("utf-8"),"iso-8859-1");
		}
		response.setHeader("Content-disposition", "attachment;filename="+fileName);
		wb.write(os);
		os.close();
		
		
	}
	
	public String findById(){
		factorder=webfactorderSer.findByOrderId(factNo,factArea,yymm,model,customer,brank,component);
		return "findById";
	}
	public String delete(){
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setObj("WebFactorder");
		log.setFactCode(factArea);
		log.setFactNo(factNo);
		log.setYymm(yymm);
		log.setContent(model+customer+brank+component);
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		log.setUsername(user.getUsername());
		webfactorderSer.delete(factNo,factArea,yymm,model,customer,brank,component,log);
		return "delete";
	}
	
	/**
	 * 數字轉換千分位格式
	 * @param dbl
	 * @return
	 */
	public String toThou(Double dbl){
		DecimalFormat cml=new DecimalFormat();
		cml.applyPattern(",###.#");		
		return cml.format(dbl);
	}
	
	/**
	 *根據廠別代號找到相對應的廠名
	 * @param order
	 * 
	 */
	public void factNoToFactSname(WebFactorder order){
		List<Object[]>list_facts=(List<Object[]>)ActionContext.getContext().getSession().get("login_facts");//用戶登錄時已經緩存
		
			for(int j=0;j<list_facts.size();j++){
				if(order.getId().getFactNo().equals(list_facts.get(j)[0].toString())){
					order.setFactSname(list_facts.get(j)[1].toString());
					break;
				}
			}
		
	}
	
	public void deleteMore() throws IOException{
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		response.setContentType("text/html;charset=utf-8");
		try{
			webfactorderSer.deleteMore(factAreas, branks, customers, models, components,factNo,factNos,yymm,yymm2);
			KyzExpectmatmLog log=new KyzExpectmatmLog();
			log.setFactNo(factNo);
			log.setObj("WebFactorder");
			StringBuffer contents=new StringBuffer();
			if(factNos.size()>0){
				contents.append("["+factNos.get(0)+"-"+factNos.get(factNos.size()-1)+"]");
			}
			if(factAreas.size()>0){
				contents.append("["+factAreas.get(0)+"-"+factAreas.get(factAreas.size()-1)+"]");
			}
			if(branks.size()>0){
				contents.append("["+branks.get(0)+"-"+branks.get(branks.size()-1)+"]");
			}
			if(customers.size()>0){
			    contents.append("["+customers.get(0)+"-"+customers.get(customers.size()-1)+"]");
			}
			if(models.size()>0){
				contents.append("["+models.get(0)+"-"+models.get(models.size()-1)+"]");
			}
			if(components.size()>0){
				contents.append("["+components.get(0)+"-"+components.get(components.size()-1)+"]");
			}
			log.setContent(contents.toString());						
			ajaxResult="0";
			kyzExpLogSer.add(log);
			response.getWriter().print("<script>window.parent.layer.msg('刪除成功',3,1)</script>");
		}catch(Exception e){
			System.out.println(e);
			ajaxResult="1";
			response.getWriter().print("<script>window.parent.layer.msg('刪除失敗',3,3)</script>");
		}		
	}
	
}
