package action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.interceptor.ServletResponseAware;

import net.sf.json.JSONArray;

import services.IWebFactorderServices;
import util.GlobalMethod;
import util.ImportExcel;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.WebFactorder;

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
	public String add() throws IOException{
		String path="d:\\北越&鞋塑2015接單匯總-1201.xls";
		List<String>list_all=ImportExcel.exportListFromExcel(new File(path), 1);
		webfactorderSer.addLarge(list_all);
		return null;
	}
	public String findPageBean(){
		
		//System.out.println(factNos.getClass().getName());
		ActionContext.getContext().getSession().remove("allrow");//首次進入，清除分頁的總條數（dao層中的allrow）
		ActionContext.getContext().getSession().remove("public_factnos");
		ActionContext.getContext().getSession().remove("public_factno");
		ActionContext.getContext().getSession().remove("public_brank");
		ActionContext.getContext().getSession().remove("public_customer");
		ActionContext.getContext().getSession().remove("public_model");
		ActionContext.getContext().getSession().remove("public_component");
		bean=webfactorderSer.findPageBean(25, page, factNos, branks, customers, models, components);
		return "beanList";
		
	}
	public String findPageBean2(){
		//System.out.println(factNos.getClass().getName());//com.opensymphony.xwork2.util.XWorkList
		ActionContext.getContext().getSession().remove("allrow");//條件查詢，清除分頁的總條數（dao層中的allrow）
		ActionContext.getContext().getSession().remove("public_factnos");
		ActionContext.getContext().getSession().remove("public_brank");
		ActionContext.getContext().getSession().remove("public_customer");
		ActionContext.getContext().getSession().remove("public_model");
		ActionContext.getContext().getSession().remove("public_component");
		
		ActionContext.getContext().getSession().put("public_factnos",factNos);
		ActionContext.getContext().getSession().put("public_brank",branks);
		ActionContext.getContext().getSession().put("public_customer",customers);
		ActionContext.getContext().getSession().put("public_model",models);
		ActionContext.getContext().getSession().put("public_component",components);
		bean=webfactorderSer.findPageBean(25, page, factNos, branks, customers, models, components);
		return "beanList1";
	}
	public String findPageBean3(){
		factNos=(List<String>)ActionContext.getContext().getSession().get("public_factnos");
		branks=(List<String>)ActionContext.getContext().getSession().get("public_brank");
		customers=(List<String>)ActionContext.getContext().getSession().get("public_customer");
		models=(List<String>)ActionContext.getContext().getSession().get("public_model");
		components=(List<String>)ActionContext.getContext().getSession().get("public_component");
		bean=webfactorderSer.findPageBean(25, page, factNos, branks, customers, models, components);
		return "beanList1";
	}
	
	/**
	 * 从WebFactorder中获取所有的部件
	 * @return
	 */
	public String findComponent(){
		List<String>list=webfactorderSer.findComponent();
		jsons=JSONArray.fromObject(list);
		return "findComponent";
	}
	
	/**
	 * 从WebFactorder中获取所有的品牌
	 * @return
	 */
	public String findBrank(){
		List<String>list=webfactorderSer.findBrank();
		jsons=JSONArray.fromObject(list);
		return "findBrank";
	}
	
	/**
	 * 从WebFactorder中获取所有的客户
	 */
	public String findCustomer(){
		List<String>list=webfactorderSer.findCustomer();
		jsons=JSONArray.fromObject(list);
		return "findCustomer";
	}
	
	/**
	 *从WebFactorder中获取所有的model
	 * @return
	 */
	public String findModel(){
		List<String>list=webfactorderSer.findModel();
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
	 * @throws IOException 
	 */
	public void print() throws IOException{
		/*List<WebFactorder>list=webfactorderSer.findWithNoPage(factNos, branks, customers, models, components,yymm);
		GlobalMethod.print_webfactorder(list, "webfactorder.jasper",year, response);*/
		
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
	
	
	public void print2() throws IOException{
		List<Object[]>list=webfactorderSer.findWebFactorder(factSnames, branks, customers, models, components, "2015");
		List<WebFactorder>list2=webfactorderSer.findWithNoPage(factNos, branks, customers, models, components,"2015");
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
		
		for(int i=1;i<list.size()+2;i++){
			sheet.createRow(i);
			for(int j=0;j<30;j++){
				sheet.getRow(i).createCell(j);
				sheet.getRow(i).getCell(j).setCellStyle(cs);
			}
			if(i==1){
				sheet.getRow(i).getCell(0).setCellValue("厂名");
				sheet.getRow(i).getCell(1).setCellValue("品牌");
				sheet.getRow(i).getCell(2).setCellValue("客户");
				sheet.getRow(i).getCell(3).setCellValue("模具");
				sheet.getRow(i).getCell(4).setCellValue("部件");
				for(int k=0;k<12;k++){
					sheet.getRow(i).getCell(5+k).setCellValue(year+(k+1));
				}
			}										
		}
		for(int i=0;i<list.size();i++){
			sheet.getRow(i+2).getCell(0).setCellValue(list.get(i)[1].toString());
			sheet.getRow(i+2).getCell(1).setCellValue(list.get(i)[2].toString());
			sheet.getRow(i+2).getCell(2).setCellValue(list.get(i)[3].toString());
			sheet.getRow(i+2).getCell(3).setCellValue(list.get(i)[4].toString());
			sheet.getRow(i+2).getCell(4).setCellValue(list.get(i)[5].toString());
			for(int j=0;j<list_all.get(i).size();j++){
				sheet.getRow(i+2).getCell(5+j).setCellValue(list_all.get(i).get(j).getOrderData());
			}
		}
		OutputStream os=new FileOutputStream("d:\\tttttt.xls");
		wb.write(os);
		os.close();
		
		
	}
	
	

}
