package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import net.sf.json.JSONArray;

import services.IWebFactorderServices;
import util.GlobalMethod;
import util.ImportExcel;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyzExpectmatmFile;
import entity.WebFactorder;
import entity.WebUser;

public class WebFactOrderAction extends ActionSupport implements ServletResponseAware{
	private IWebFactorderServices webfactorderSer;
	private WebFactorder factorder;
	private PageBean bean;
	private int page;
	private String factNo;
	private List<String>factNos=new ArrayList<String>();
	private String brank;
	private String customer;
	private String model;
	private String component;
	private JSONArray jsons;
	private List<String>components=new ArrayList<String>();
	private List<String>branks=new ArrayList<String>();
	private List<String>customers=new ArrayList<String>();
	private List<String>models=new ArrayList<String>();
	private javax.servlet.http.HttpServletResponse response;
	private String yymm;
	private String year;
	private List<String>factSnames;
	private File file;
    private String fileFileName;
    private String fileContentType;
    private String ajaxResult;//申請函文時返回的ajax結果,   0:提交成功       1：上传的文档格式不符合要求   2：excel导入失败     3：excel数据格式不符合要求
     
    
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
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	public String importExcel() throws IOException{
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
		
		
		/*******************數據導入到數據庫********************/
		try{
			List<String>list_all=ImportExcel.exportListFromExcel(new File(path+"\\"+fileFileName), 0);
			if(list_all.size()>0){
				try{
					webfactorderSer.addLarge(list_all);
				}catch(Exception e){
					ajaxResult="2";//导入Excel失败
				}
			}else{
				ajaxResult="3";//Excel数据结构不符合要求,不允许导入
			}
		}catch(Exception e){
			ajaxResult="2";//导入Excel失败
		}
		/*******************數據導入到數據庫********************/
		return result;
	}
	public String findPageBean(){
		
		//System.out.println(factNos.getClass().getName());
		ActionContext.getContext().getSession().remove("allrow");//首次進入，清除分頁的總條數（dao層中的allrow）
		ActionContext.getContext().getSession().remove("public_factnames");
		ActionContext.getContext().getSession().remove("public_factno");
		ActionContext.getContext().getSession().remove("public_brank");
		ActionContext.getContext().getSession().remove("public_customer");
		ActionContext.getContext().getSession().remove("public_model");
		ActionContext.getContext().getSession().remove("public_component");
		bean=webfactorderSer.findPageBean(25, page, factSnames, branks, customers, models, components);
		return "beanList";
		
	}
	public String findPageBean2(){
		//System.out.println(factNos.getClass().getName());//com.opensymphony.xwork2.util.XWorkList
		ActionContext.getContext().getSession().remove("allrow");//條件查詢，清除分頁的總條數（dao層中的allrow）
		/*ActionContext.getContext().getSession().remove("public_factnames");
		ActionContext.getContext().getSession().remove("public_brank");
		ActionContext.getContext().getSession().remove("public_customer");
		ActionContext.getContext().getSession().remove("public_model");
		ActionContext.getContext().getSession().remove("public_component");*/
		
		ActionContext.getContext().getSession().put("public_factnames",factSnames);
		ActionContext.getContext().getSession().put("public_brank",branks);
		ActionContext.getContext().getSession().put("public_customer",customers);
		ActionContext.getContext().getSession().put("public_model",models);
		ActionContext.getContext().getSession().put("public_component",components);
		bean=webfactorderSer.findPageBean(25, page, factSnames, branks, customers, models, components);
		return "beanList1";
	}
	public String findPageBean3(){
		factNos=(List<String>)ActionContext.getContext().getSession().get("public_factnames");
		branks=(List<String>)ActionContext.getContext().getSession().get("public_brank");
		customers=(List<String>)ActionContext.getContext().getSession().get("public_customer");
		models=(List<String>)ActionContext.getContext().getSession().get("public_model");
		components=(List<String>)ActionContext.getContext().getSession().get("public_component");
		bean=webfactorderSer.findPageBean(25, page, factSnames, branks, customers, models, components);
		return "beanList1";
	}
	
	/**
	 * 从WebFactorder中获取所有的部件
	 * @return
	 */
	public String findComponent(){
		List<String>list=webfactorderSer.findComponent(factSnames);
		jsons=JSONArray.fromObject(list);
		return "findComponent";
	}
	
	/**
	 * 从WebFactorder中获取所有的品牌
	 * @return
	 */
	public String findBrank(){
		List<String>list=webfactorderSer.findBrank(factSnames);
		jsons=JSONArray.fromObject(list);
		return "findBrank";
	}
	
	/**
	 * 从WebFactorder中获取所有的客户
	 */
	public String findCustomer(){
		List<String>list=webfactorderSer.findCustomer(factSnames);
		jsons=JSONArray.fromObject(list);
		return "findCustomer";
	}
	
	/**
	 *从WebFactorder中获取所有的model
	 * @return
	 */
	public String findModel(){
		List<String>list=webfactorderSer.findModel(factSnames);
		jsons=JSONArray.fromObject(list);
		return "findModel";
	}
	/**
	 *从WebFactorder中获取所有的model
	 * @return
	 */
	public String findFactSname(){
		List<String>list=webfactorderSer.findFactSname();
		jsons=JSONArray.fromObject(list);
		return "findFactSname";
	}
	
	/**
	 * 打印搜索
	 * 快速ireport版
	 * @throws IOException 
	 */
	public void print() throws IOException{
		List<WebFactorder>list=webfactorderSer.findWithNoPage(factNos, branks, customers, models, components,yymm);
		GlobalMethod.print_webfactorder(list, "webfactorder.jasper",year, response);
	}
	/**
	 * 打印搜索
	 * 慢速準確版
	 * @throws IOException 
	 */
	public void print2() throws IOException{		
		/****************1數據處理*****************/
		List<String>list_column=new ArrayList<String>();
		List<String>list_month=new ArrayList<String>();
		list_column.add("廠名");
		list_column.add("品牌");
		list_column.add("客戶");
		list_column.add("模件");
		list_column.add("部件");
		String yymm="";
		for(int i=1;i<13;i++){
			if(i<10){
				yymm=year+"0"+i;
			}else{
				yymm=year+i;
			}
			list_month.add(yymm);
			list_column.add(yymm);
		}
		int index=0;
		List<Object[]>list=webfactorderSer.findWebFactorder(factSnames, branks, customers, models, components, year);
		Map<String,List<Double>>map=new HashMap<String,List<Double>>();
		for(int i=0;i<list.size();i++){//for1
			List<Double>list_dbl=new ArrayList<Double>();
			for(int j=0;j<12;j++){				
				/**
				 * 0:orderid
				 * 1:factSname
				 * 2:brank
				 * 3:customer
				 * 4:modelNo
				 * 5:component
				 */
				List<Double>factorders=webfactorderSer.findOrderdata(list.get(i)[1].toString(), list.get(i)[2].toString(), 
						list.get(i)[3].toString(), list.get(i)[4].toString(), list.get(i)[5].toString(), list_month.get(j));
				if(factorders.size()>1){
					list_dbl.add(-1.0);
					index++;//數據出現重複，自加1，作標記用
				}
				if(factorders.size()==1){
					list_dbl.add(factorders.get(0));
				}
				if(factorders.size()==0){
					list_dbl.add(0.0);
				}
						
			}
			map.put(list.get(i)[0].toString(), list_dbl);
		}//for1
		
		
		
		/****************1數據處理*****************/
		
		/****************2報表打印*****************/
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet("sheet1");
		
		HSSFCellStyle cs=wb.createCellStyle();
		cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		for(int i=1;i<list.size()+2;i++){
			sheet.createRow(i);
			for(int j=0;j<25;j++){
				sheet.getRow(i).createCell(j).setCellStyle(cs);
			}
		}
		
		for(int i=0;i<list_column.size();i++){
			sheet.getRow(1).getCell(i).setCellValue(list_column.get(i));
		}
		for(int i=0;i<list.size();i++){//for
			sheet.getRow(2+i).getCell(0).setCellValue(list.get(i)[1].toString());
			sheet.getRow(2+i).getCell(1).setCellValue(list.get(i)[2].toString());
			sheet.getRow(2+i).getCell(2).setCellValue(list.get(i)[3].toString());
			sheet.getRow(2+i).getCell(3).setCellValue(list.get(i)[4].toString());
			sheet.getRow(2+i).getCell(4).setCellValue(list.get(i)[5].toString());
			List<Double>list_1=map.get(list.get(i)[0].toString());
			for(int j=0;j<list_1.size();j++){
				sheet.getRow(2+i).getCell(5+j).setCellValue(list_1.get(j));
			}
		}//for
		OutputStream os=new FileOutputStream("d:\\tttttt.xls");
		wb.write(os);
		os.close();
	}
	
	/**
	 * 打印搜索
	 * 快速準確修改版
	 * @throws IOException 
	 */
	public void print3() throws IOException{
		List<Object[]>list=webfactorderSer.findWebFactorder(factSnames, branks, customers, models, components, year);
		List<WebFactorder>list2=webfactorderSer.findWithNoPage(factSnames, branks, customers, models, components,year);
		List<List<WebFactorder>>list_all=new ArrayList<List<WebFactorder>>();
		for(int i=0;i<list.size();i++){//for1
			List<WebFactorder>list_one=new ArrayList<WebFactorder>();
			for(int j=0;j<list2.size();j++){//for2
				if(list.get(i)[1].toString().equals(list2.get(j).getFactSname())&&
						list.get(i)[2].toString().equals(list2.get(j).getBrank())&&
						list.get(i)[3].toString().equals(list2.get(j).getCustomer())&&
						list.get(i)[4].toString().equals(list2.get(j).getModelNo())&&
						list.get(i)[5].toString().equals(list2.get(j).getComponent())){
					list_one.add(list2.get(j));
					//list2.remove(0);
				}
			}//for2
			list_all.add(list_one);
		}//for1
		
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet("sheet1");
		
		HSSFCellStyle cs=wb.createCellStyle();
		cs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs.setBorderTop(HSSFCellStyle.BORDER_THIN);
		
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
		for(int i=0;i<5;i++){
			if(i==0){
				sheet.setColumnWidth(i, 6000);	
			}else{
				sheet.setColumnWidth(i, 4500);
			}			
		}
		
		sheet.createRow(0).createCell(0);
		sheet.getRow(0).getCell(0).setCellValue(year+"訂單月份匯總表");
		sheet.getRow(0).getCell(0).setCellStyle(cs_title);
		for(int i=1;i<list.size()+2;i++){
			sheet.createRow(i);
			for(int j=0;j<30;j++){
				sheet.getRow(i).createCell(j);
				sheet.getRow(i).getCell(j).setCellStyle(cs);
			}
			if(i==1){
				sheet.getRow(i).getCell(0).setCellValue("廠別");
				sheet.getRow(i).getCell(1).setCellValue("品牌");
				sheet.getRow(i).getCell(2).setCellValue("客戶");
				sheet.getRow(i).getCell(3).setCellValue("模具");
				sheet.getRow(i).getCell(4).setCellValue("部件");				
				for(int k=0;k<12;k++){
					if(k+1<10){
						sheet.getRow(i).getCell(5+k).setCellValue(year+"0"+(k+1));
					}else{
						sheet.getRow(i).getCell(5+k).setCellValue(year+(k+1));
					}					
				}
				sheet.getRow(i).getCell(17).setCellValue("匯總");
				for(int l=0;l<18;l++){
					sheet.getRow(i).getCell(l).setCellStyle(cs_head);
				}
			}										
		}
		/***************************初始化表格************************************/
		
		for(int i=0;i<list.size();i++){
			sheet.getRow(i+2).getCell(0).setCellValue(list.get(i)[1].toString());
			sheet.getRow(i+2).getCell(1).setCellValue(list.get(i)[2].toString());
			sheet.getRow(i+2).getCell(2).setCellValue(list.get(i)[3].toString());
			sheet.getRow(i+2).getCell(3).setCellValue(list.get(i)[4].toString());
			sheet.getRow(i+2).getCell(4).setCellValue(list.get(i)[5].toString());
			double row_total=0.0;
			if(list_all.get(i).size()==12){
				for(int j=0;j<list_all.get(i).size();j++){
					sheet.getRow(i+2).getCell(5+j).setCellValue(list_all.get(i).get(j).getOrderData());
					row_total=row_total+list_all.get(i).get(j).getOrderData();															
				}
			}
			if(list_all.get(i).size()<12){
				for(int j=0;j<12;j++){
					try{
						sheet.getRow(i+2).getCell(5+j).setCellValue(list_all.get(i).get(j).getOrderData());
					}catch(Exception e){
						sheet.getRow(i+2).getCell(5+j).setCellValue("無數據");
						sheet.getRow(i+2).getCell(5+j).setCellStyle(cs_font_red);
					}					
					row_total=row_total+list_all.get(i).get(j).getOrderData();					
				}
			}
			if(list_all.get(i).size()>12){
				for(int j=0;j<12;j++){
					sheet.getRow(i+2).getCell(5+j).setCellValue(list_all.get(i).get(j).getOrderData());	
					sheet.getRow(i+2).getCell(5+j).setCellStyle(cs_font_blue);
					row_total=row_total+list_all.get(i).get(j).getOrderData();
				}
				sheet.getRow(i+2).getCell(18).setCellValue("(數據存在重複)");
				sheet.getRow(i+2).getCell(18).setCellStyle(cs_font_blue);
			}
			sheet.getRow(i+2).getCell(17).setCellValue(row_total);			
			
		}
		//OutputStream os=new FileOutputStream("d:\\tttttt.xls");
		ServletOutputStream os=response.getOutputStream();
		response.setContentType("application/vnd.ms-excel");
		String fileName="fact_report_2015.xls";
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
	
	

}
