package action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import services.IWebFactServices;
import services.IWebObjsCServices;
import util.GlobalMethod;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyzExpectmatmLog;
import entity.VWebFact;
import entity.VWebobjA;
import entity.VWebobjA3;
import entity.VWebobjA3Id;
import entity.WebFact;
import entity.WebObjsC;
import entity.WebObjsCId;

public class WebObjsCAction extends ActionSupport implements ServletResponseAware{
	private javax.servlet.http.HttpServletResponse response;
	private final static int NUM=31;//print_tw  多少箇項目（29+1）
	private IWebObjsCServices webobjcservices;
	private File file;
    private String fileFileName;
    private String fileContentType;
    private String ajaxResult;
    private String factNo;
    private String factCode;
    private String yymm;
    private String yymm2;
    private String yymmdd;
    private int page;
    private PageBean bean;
    private String isnull;
    private WebObjsC obj;
    private IWebFactServices webFactSer;
    private int emailMk;
    
    
    
	public int getEmailMk() {
		return emailMk;
	}

	public void setEmailMk(int emailMk) {
		this.emailMk = emailMk;
	}

	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
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

	public String getAjaxResult() {
		return ajaxResult;
	}

	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}

	public String getFactNo() {
		return factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public String getFactCode() {
		return factCode;
	}

	public void setFactCode(String factCode) {
		this.factCode = factCode;
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

	public String getYymmdd() {
		return yymmdd;
	}

	public void setYymmdd(String yymmdd) {
		this.yymmdd = yymmdd;
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

	public void setWebobjcservices(IWebObjsCServices webobjcservices) {
		this.webobjcservices = webobjcservices;
	}
		
	public String getIsnull() {
		return isnull;
	}

	public void setIsnull(String isnull) {
		this.isnull = isnull;
	}	

	public WebObjsC getObj() {
		return obj;
	}

	public void setObj(WebObjsC obj) {
		this.obj = obj;
	}

	public String findById(){
		obj=webobjcservices.findById(factNo, factCode, yymmdd);
		return "findById";		
	}
	
	public String add(){
		try {			
			if (isnull.equals("isnull")) {												
				WebObjsC temp=webobjcservices.findById(obj.getId().getWebFact().getId().getFactNo(), obj.getId().getWebFact().getId().getFactArea(), obj.getId().getYymmdd());
				if (temp==null) {
					webobjcservices.add(obj);
					ajaxResult="0";
				}else{
					ajaxResult="2";//數據庫存在數據4
				} 
			} else {
				webobjcservices.add(obj);
				ajaxResult="0";
			}			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ajaxResult="1";
		}
		return "add";
	} 
	
	public String delete(){
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setFactNo(factNo);
		log.setFactCode(factCode);
		log.setObj("WebObjsC");
		log.setYymm(yymmdd);
		webobjcservices.delete(factNo, factCode, yymmdd,log);
		return "delete";
	}
	
	public String findPageBean(){
		ActionContext.getContext().getSession().remove("allrow");//dao層
		ActionContext.getContext().getSession().remove("public_yymm");
		ActionContext.getContext().getSession().remove("public_yymmdd");
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		ActionContext.getContext().getSession().put("public_factno", factNo);
		bean=webobjcservices.findPageBean(20,page, factNo, yymm,yymmdd);
		return "beanList";
		
	}
	public String findPageBean2(){
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		ActionContext.getContext().getSession().remove("allrow");//dao層
		ActionContext.getContext().getSession().put("public_factno", factNo);
		ActionContext.getContext().getSession().put("public_yymm", yymm);	
		ActionContext.getContext().getSession().put("public_yymmdd", yymmdd);
		bean=webobjcservices.findPageBean(20,page, factNo, yymm,yymmdd);
		return "beanList1";
	}
	public String findPageBean3(){
		factNo=(String)ActionContext.getContext().getSession().get("public_factno");
		yymm=(String)ActionContext.getContext().getSession().get("public_yymm");
		yymmdd=(String)ActionContext.getContext().getSession().get("public_yymmdd");
		bean=webobjcservices.findPageBean(20,page, factNo, yymm,yymmdd);
		return "beanList1";
	}
	
	
	
	/***************************************各廠工廠日報************************************************************/
	public void print_tw2_c() throws IOException{
		XSSFWorkbook wb=new XSSFWorkbook();		
		Map<String,Object>map_style=GlobalMethod.findStyles2007(wb);				
		List<WebObjsC>list_source=webobjcservices.findObjByDay(yymmdd);
		Map<String,Object>map=new LinkedHashMap<String,Object>();
						
		//List<WebFact>facts=webFactSer.findFactAble();//所有有效廠別
		//List<WebFact>facts=webFactSer.findAllFact_showA();//與重點指標報表相同
		List<WebFact>facts=webFactSer.findAllFact_2();//與產量報表相同
		List<String>factcodes=new ArrayList<String>();
		List<String>factnos=new ArrayList<String>();
		List<WebObjsC>list_obj=new LinkedList<WebObjsC>();
		for(WebFact fact:facts){
			factcodes.add(fact.getId().getFactArea());
			list_obj.add(new WebObjsC(new WebObjsCId(fact,yymmdd)));
			factnos.add(fact.getId().getFactNo());
			
		}
		for(int a=0;a<factcodes.size();a++){
			for(int b=factcodes.size()-1;b>a;b--){
				if(factcodes.get(a).equals(factcodes.get(b))){
					factcodes.remove(b);
				}
			}
		}
		
		
		for(int a=0;a<list_obj.size();a++){
			for(WebObjsC obj:list_source){
				if(list_obj.get(a).getId().getWebFact().getId().getFactArea().equals(obj.getId().getWebFact().getId().getFactArea())&&
						list_obj.get(a).getId().getWebFact().getId().getFactNo().equals(obj.getId().getWebFact().getId().getFactNo())){
					list_obj.set(a, obj);
				}
			}
		}
		
		for(String factcode:factcodes){
			List<WebObjsC>list=new ArrayList<WebObjsC>();
			for(WebObjsC obj:list_obj){
				if(factcode.equals(obj.getId().getWebFact().getId().getFactArea())){
					list.add(obj);
				}
			}
			map.put(factcode, list);
		}
		
		
		
		for(int a=0;a<factnos.size();a++){
			for(int b=factnos.size()-1;b>a;b--){
				if(factnos.get(a).equals(factnos.get(b))){
					factnos.remove(b);
				}
			}
		}		
						
		XSSFSheet sheet=wb.createSheet("工廠日報_"+yymmdd);
		this.init_more2_c(sheet,map,map_style);	
		if(emailMk==1){//發送郵件報表			
			//OutputStream os=new FileOutputStream("d:\\"+sdate+".xls");
			OutputStream os=new FileOutputStream(ServletActionContext.getServletContext().getRealPath("TEMPFILES\\"+yymmdd+".xlsx"));
			wb.write(os);
			os.close();
		}else{
			ServletOutputStream os=response.getOutputStream();
			//response.setContentType("application/vnd.ms-excel");
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			String fileName=yymmdd+"工廠日報.xlsx";
			int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");
			if(msie>0){
				fileName=java.net.URLEncoder.encode(fileName,"utf-8");
			}else{
				fileName=new String(fileName.getBytes("utf-8"),"iso8859-1");
			}
			response.setHeader("Content-disposition","attachment;filename="+fileName);
			wb.write(os);
			os.close();
		}
		
		
	}
	
	
	
public void init_more2_c(XSSFSheet sheet,Map<String,Object>map,Map<String,Object>map_style) throws IOException{				   
	    this.init(sheet,map);
		List<String>list_items=this.findItems_c();
		//樣式
		XSSFCellStyle cs_title=(XSSFCellStyle)map_style.get("cs_title");
		XSSFCellStyle cs_head=(XSSFCellStyle)map_style.get("cs_head");
		XSSFCellStyle cs_head2=(XSSFCellStyle)map_style.get("cs_head2");
		XSSFCellStyle cs=(XSSFCellStyle)map_style.get("cs");//		
		//標題
		CellRangeAddress cra_title=new CellRangeAddress(0,(short)0,0,(short)5);
		sheet.addMergedRegion(cra_title);				
		sheet.getRow(0).getCell(0).setCellValue("工廠日報_"+yymmdd);
		
		for(int i=0;i<4;i++){
			sheet.getRow(0).getCell(i).setCellStyle(cs_title);
		}
		
		CellRangeAddress cra=new CellRangeAddress(1,(short)1,0,(short)2);
		sheet.addMergedRegion(cra);
		sheet.getRow(1).getCell(0).setCellValue("廠別狀態");
		for(int i=0;i<3;i++){
			sheet.getRow(1).getCell(i).setCellStyle(cs_head2);
		}
		
		List<String>list_head=new ArrayList<String>();
		list_head.add("項次");
		list_head.add("項目");
		list_head.add("單位");						
		for(int i=0;i<list_head.size();i++){
			sheet.getRow(2).getCell(i).setCellValue(list_head.get(i));
			sheet.getRow(2).getCell(i).setCellStyle(cs_head);
		}
				
		for(int i=0;i<list_items.size();i++){
			sheet.getRow(3+i).getCell(0).setCellValue(i+1);
			sheet.getRow(3+i).getCell(1).setCellValue(list_items.get(i).split("__")[0]);//項目
			sheet.getRow(3+i).getCell(2).setCellValue(list_items.get(i).split("__")[1]);//單位
			for(int j=0;j<3;j++){
				sheet.getRow(3+i).getCell(j).setCellStyle(cs);
			}
		}
		
		int temp=0;
		int temp1_1=0;
		for(String factcode:map.keySet()){                                             
			List<WebObjsC>list_obj=(List<WebObjsC>)map.get(factcode);	
			
			/***********************************MD  EVA  DJ  換行**********************************/
			if("MD".equals(factcode)||"EVA".equals(factcode)||"DJ".equals(factcode)){
				temp=0;
				temp1_1=temp1_1+list_items.size()+2;
				CellRangeAddress c=new CellRangeAddress(1+temp1_1,1+temp1_1,0,(short)2);
				sheet.addMergedRegion(c);
				sheet.getRow(1+temp1_1).getCell(0).setCellValue("廠別狀態");
				for(int i=0;i<3;i++){
					sheet.getRow(1+temp1_1).getCell(i).setCellStyle(cs_head2);
				}
				
				for(int i=0;i<list_head.size();i++){
					sheet.getRow(2+temp1_1).getCell(i).setCellValue(list_head.get(i));
					sheet.getRow(2+temp1_1).getCell(i).setCellStyle(cs_head);
				}
				for(int i=0;i<list_items.size();i++){
					sheet.getRow(3+i+temp1_1).getCell(0).setCellValue(i+1);
					sheet.getRow(3+i+temp1_1).getCell(1).setCellValue(list_items.get(i).split("__")[0]);//項目
					sheet.getRow(3+i+temp1_1).getCell(2).setCellValue(list_items.get(i).split("__")[1]);//單位
					for(int j=0;j<3;j++){
						sheet.getRow(3+i+temp1_1).getCell(j).setCellStyle(cs);
					}
				}
				
				
			}
			/***********************************MD  EVA  DJ  換行**********************************/
			
			int length=list_obj.size();			
			sheet.getRow(1+temp1_1).getCell(3+temp).setCellValue(factcode);
			CellRangeAddress cra1=new CellRangeAddress(1+temp1_1,1+temp1_1,3+temp,(short)2+temp+length);
			sheet.addMergedRegion(cra1);
			for(int i=0;i<length;i++){
				sheet.getRow(1+temp1_1).getCell(3+temp+i).setCellStyle(cs_head2);
			}
			
			List<List<String>>list_pack=this.packageTostring2_c(list_obj,list_items);			
			for(int a=0;a<list_pack.size();a++){
				List<String>list=list_pack.get(a);
				for(int b=0;b<list.size();b++){
					sheet.getRow(2+b+temp1_1).getCell(3+a+temp).setCellValue(this.isMyNull_str(list.get(b)));										
					if(b==0){
						sheet.getRow(2+b+temp1_1).getCell(3+a+temp).setCellStyle(cs_head);
					}else{
						sheet.getRow(2+b+temp1_1).getCell(3+a+temp).setCellStyle(cs);
					}
				}
			}
			temp=temp+length;
		}							
								
	}
		
	/**
	 * 
	 * @Title: packageTostring
	 * @Description: TODO
	 * @param @param list_obj
	 * @param @param mk  1:工廠報表    0:臺灣報表
	 * @param @return
	 * @return List<List<String>>
	 * @throws
	 * @author web
	 * @date 2016/9/8
	 */
	public List<List<String>> packageTostring2_c(List<WebObjsC>list_obj,List<String>list_items){
		List<List<String>>result=new ArrayList<List<String>>();
		for(int i=0;i<list_obj.size();i++){
			WebObjsC obj=list_obj.get(i);
			List<String>list=new ArrayList<String>();			
			if(obj.getId().getWebFact().getFactSname()!=null){
				list.add(obj.getId().getWebFact().getFactSname());
			}else{
				list.add(obj.getId().getWebFact().getId().getFactNo().split("_")[2]);
			}								
			List<String>list_str=this.isPercents2_c(obj,list_items);
			for(String str:list_str){
				list.add(str);
			}			
			result.add(list);								
		}
		return result;
		
	}
	
	
	
	/**
	 * 
	 * @Title: isPercents
	 * @Description: 判斷哪些數據用 % 號     (控制所有數據的格式)
	 * @param @param obj
	 * @param @return
	 * @return List<String>
	 * @throws
	 * @author web
	 * @date 2016/9/20
	 */
	public List<String> isPercents2_c(WebObjsC obj,List<String> list_items){
		List<Object>list=new ArrayList<Object>();
		List<String>list_result=new ArrayList<String>();
		DecimalFormat frm=new DecimalFormat("0.00%");//%號
		DecimalFormat frm1=new DecimalFormat("#,##0");//不保留小數
		DecimalFormat frm2=new DecimalFormat("#,##0.0");//保留1位小數
		list.add(obj.getObjA1());
		list.add(obj.getObjA2());
		list.add(obj.getObjA3());
		list.add(obj.getObjA4());
		list.add(obj.getObjA5());
		list.add(obj.getObjA6());
		list.add(obj.getObjA7());
		list.add(obj.getObjA8());			

		for(int a=0;a<list.size();a++){
			if(list.get(a)!=null){
				/*if(list_items.get(i).split("__")[1].equals("%")){
					list_result.add(frm.format(list.get(i)));
				}else{
					list_result.add(frm1.format(list.get(i)));							
				}*/	
				if(a<4||a==7||a==8){
					list_result.add(frm1.format(list.get(a)));
				}else if(a==10){
					list_result.add(frm.format(list.get(a)));
				}else{
					list_result.add(frm2.format(list.get(a)));
				}
			}else{
				list_result.add("0");
			}						
		}			
		return list_result;
	}
	
	public List<String>findItems_c(){
		List<String>list=new ArrayList<String>();										
		list.add("生產欠數__雙");
		list.add("孔位數__孔");
		list.add("上模數__模");
		list.add("日產能__雙");
		list.add("回轉數__回");
		list.add("出勤人員數__人");
		list.add("離職、資遺人數__人");
		list.add("每日發生費用__USD");										
		return list;		
	}
	/***************************************各廠工廠日報************************************************************/
	
	
	public void init(XSSFSheet sheet,Map<String,Object>map){
		sheet.setColumnWidth(1,4500);		
		for(int i=0;i<NUM*3+10;i++){
			XSSFRow row=sheet.createRow(i);
			int index=3;
			for(String factcode:map.keySet()){
				if(index==3){
					for(int j=0;j<index;j++){
						row.createCell(j);
					}
				}
				List<VWebobjA>list_obj=(List<VWebobjA>)map.get(factcode);
				for(int j=0;j<list_obj.size();j++){					
					row.createCell(index);
					if(i==0){
						sheet.setColumnWidth(index,3000);
					}
					index++;
					
				}
			}			
		}
	}
	
	
	
	public String isMyNull_str(String str){		
		if(str==null){					
			str="無";
		}
		return str;
	}	
		
	

}
