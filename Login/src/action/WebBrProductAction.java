/**
 * 
 */
package action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import net.sf.json.JSONArray;

import services.IWebBrProductServices;
import services.IWebErpBrankProcessServices;
import services.IWebFactServices;
import util.GlobalMethod;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyzExpectmatmLog;
import entity.VWebBrProandest;
import entity.VWebFact;
import entity.WebBrEstimatingitem;
import entity.WebBrProduct;
import entity.WebBrProductitem;

/**   
 *    
 * 项目名称：WebLogin   
 * 类名称：WebBrProductAction   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2017/7/13 下午2:06:46   
 * 修改人：Administrator   
 * 修改时间：2017/7/13 下午2:06:46   
 * 修改备注：   
 * @version    
 *    
 **/
public class WebBrProductAction extends ActionSupport implements ServletResponseAware{
	
	private final static String OP1="OP-1";//所有的廠別狀態（製程代號）
	private IWebBrProductServices webbrproSer;
	//private IWebErpBrankProcessServices weberpbpser;
	private IWebFactServices webFactSer;
	private String factNo;
	private List<WebBrProduct> listbrpro;
	private int page;
	private PageBean bean;
	private WebBrProduct wbpro;
	private String ajaxResult;
	private String createDate;
	private Integer createUser; 
	private Integer wid;
	private JSONArray jsons;
	private String yymmdd;
	private List<WebBrProductitem>listitem;
	private List<WebBrEstimatingitem>listest;
	private List<VWebBrProandest>listitemAndest;
	private javax.servlet.http.HttpServletResponse response;
	private String yymmdd2;
	private String factCode;
	private int months;
	
	
	
	
	public int getMonths(){
		return months;
	}
	public void setMonths(int months){
		this.months=months;
	}
	public String getFactCode(){
		return factCode;
	}
	public void setFactCode(String factCode){
		this.factCode=factCode;
	}
	public List<VWebBrProandest> getListitemAndest(){
		return listitemAndest;
	}
	public void setListitemAndest(List<VWebBrProandest> listitemAndest){
		this.listitemAndest=listitemAndest;
	}
	public String getYymmdd2(){
		return yymmdd2;
	}
	public void setYymmdd2(String yymmdd2){
		this.yymmdd2=yymmdd2;
	}
	public List<WebBrEstimatingitem> getListest(){
		return listest;
	}
	public void setListest(List<WebBrEstimatingitem> listest){
		this.listest=listest;
	}
	public List<WebBrProductitem> getListitem(){
		return listitem;
	}
	public void setListitem(List<WebBrProductitem> listitem){
		this.listitem=listitem;
	}
	public String getYymmdd(){
		return yymmdd;
	}
	public void setYymmdd(String yymmdd){
		this.yymmdd=yymmdd;
	}
	public JSONArray getJsons(){
		return jsons;
	}
	public void setJsons(JSONArray jsons){
		this.jsons=jsons;
	}
	public Integer getWid(){
		return wid;
	}
	public void setWid(Integer wid){
		this.wid=wid;
	}
	public String getCreateDate(){
		return createDate;
	}
	public void setCreateDate(String createDate){
		this.createDate=createDate;
	}
	public Integer getCreateUser(){
		return createUser;
	}
	public void setCreateUser(Integer createUser){
		this.createUser=createUser;
	}
	public String getAjaxResult(){
		return ajaxResult;
	}
	public void setAjaxResult(String ajaxResult){
		this.ajaxResult=ajaxResult;
	}
	public String getItemcategory(){
		return itemcategory;
	}
	public void setItemcategory(String itemcategory){
		this.itemcategory=itemcategory;
	}

	private String itemcategory;
	
	public WebBrProduct getWbpro(){
		return wbpro;
	}
	public void setWbpro(WebBrProduct wbpro){
		this.wbpro=wbpro;
	}
	public int getPage(){
		return page;
	}
	public void setPage(int page){
		this.page=page;
	}
	public PageBean getBean(){
		return bean;
	}
	public void setBean(PageBean bean){
		this.bean=bean;
	}
	public String getFactNo(){
		return factNo;
	}
	public void setFactNo(String factNo){
		this.factNo=factNo;
	}
			
	
	public List<WebBrProduct> getListbrpro(){
		return listbrpro;
	}
	public void setListbrpro(List<WebBrProduct> listbrpro){
		this.listbrpro=listbrpro;
	}
	
	public void setWebbrproSer(IWebBrProductServices webbrproSer){
		this.webbrproSer=webbrproSer;
	}	
	
	public void setServletResponse(HttpServletResponse response){
		// TODO Auto-generated method stub
		this.response=response;
	}
	
	
	public void setWebFactSer(IWebFactServices webFactSer){
		this.webFactSer=webFactSer;
	}
	/*************************************BR產品**************************************************/
	public String findPageBean(){
		ActionContext.getContext().getSession().remove("webbrproFactNo");
		bean=webbrproSer.findPageBean(20,page,factNo);
		return "findPageBean";
	}
	
	public String findPageBean2(){
		ActionContext.getContext().getSession().put("webbrproFactNo",factNo);
		bean=webbrproSer.findPageBean(20,page,factNo);
		return "findPageBean1";
	}
	
	public String findPageBean3(){
		factNo=(String)ActionContext.getContext().getSession().get("webbrproFactNo");
		bean=webbrproSer.findPageBean(20,page,factNo);
		return "findPageBean1";
	}
	/*************************************BR產品**************************************************/
	
	
	/*************************************BR產品庫存**************************************************/
	public String findPageBean_item(){
		ActionContext.getContext().getSession().remove("webitemFactNo");
		ActionContext.getContext().getSession().remove("webitemYymmdd");
		ActionContext.getContext().getSession().remove("webitemYymmdd2");
		bean=webbrproSer.fincPageBean(20,page,factNo,yymmdd,yymmdd2);
		return "findPageBean_item";
	}
	public String findPageBean_item2(){
		ActionContext.getContext().getSession().put("webitemFactNo",factNo);
		ActionContext.getContext().getSession().put("webitemYymmdd",yymmdd);
		ActionContext.getContext().getSession().put("webitemYymmdd2",yymmdd2);
		bean=webbrproSer.fincPageBean(20,page,factNo,yymmdd,yymmdd2);
		return "findPageBean_item1";
	}
	public String findPageBean_item3(){
		factNo=(String)ActionContext.getContext().getSession().get("webitemFactNo");
		yymmdd=(String)ActionContext.getContext().getSession().get("webitemYymmdd");
		yymmdd2=(String)ActionContext.getContext().getSession().get("webitemYymmdd2");
		bean=webbrproSer.fincPageBean(20,page,factNo,yymmdd,yymmdd2);
		return "findPageBean_item1";
	}
	/*************************************BR產品庫存**************************************************/
	
	public String add(){
		ajaxResult="0";
		try{
			for(WebBrProduct obj:listbrpro){
				obj.setCreateDate(createDate);
				obj.setCreateUser(createUser);
				obj.setItemcategory(itemcategory);
			}
			webbrproSer.add(listbrpro);
		}catch(Exception e){
			ajaxResult="1";
			e.printStackTrace();
		}
		return "add";
	}
	
	public String delete(){
		try{
			KyzExpectmatmLog log=new KyzExpectmatmLog();
			wbpro=webbrproSer.findById(factNo,wid);
			log.setFactNo(wbpro.getId().getFactNo());
			log.setContent(wbpro.getNamec1()+"__"+wbpro.getNamec2());
			log.setObj("WebBrProduct");
			log.setBillNo(wbpro.getItemcategory());
			webbrproSer.delete(wbpro,log);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "delete";
	}
	
	public String findByFactno(){
		try{
			List<Object[]>list=webbrproSer.findByFactno(factNo);
			jsons=JSONArray.fromObject(list);
		}catch(Exception e){
			e.printStackTrace();
		}		
		return "findByFactno";
	}
	
	public String add2(){
		ajaxResult="0";
		try{
			webbrproSer.add2(listitem);
		}catch(Exception e){
			ajaxResult="1";
			e.printStackTrace();
		}
		return "add2";
	}
	
	public String  findByfactNoAndyymmdd(){
		Integer result=webbrproSer.findByfactNoAndyymmdd(factNo,yymmdd);
		if(result!=null&&result!=0){
			ajaxResult="1";
		}else{
			ajaxResult="0";
		}
		return "findByfactNoAndyymmdd";
	}
	
	public String findByfactNoAndyymmdd2(){
		Integer result=webbrproSer.findByfactNoAndyymmdd2(factNo,yymmdd);
		if(result!=null&&result!=0){
			ajaxResult="1";
		}else{
			ajaxResult="0";
		}
		return "findByfactNoAndyymmdd2";
	}
	
	public String add3(){
		ajaxResult="0";
		try{
			webbrproSer.add3(listest);
		}catch(Exception e){
			ajaxResult="1";
			e.printStackTrace();
		}
		return "add3";
	}
	
	public String add2_3(){
		ajaxResult="0";
		try{
			/*webbrproSer.add2(listitem);
			webbrproSer.add3(listest);*/
			List list=new ArrayList();
			for(int i=0;i<listitem.size();i++){
				list.add(listitem.get(i));
			}
			for(int i=0;i<listest.size();i++){
				list.add(listest.get(i));
			}			
			webbrproSer.add2_3(list);
		}catch(Exception e){
			ajaxResult="1";
			e.printStackTrace();
		}
		return "add2_3";
		
	}
	
	public String findByFactNo2(){
		Integer result=webbrproSer.findByFactNo2(factNo);
		if(result!=null&&result!=0){
			ajaxResult="1";
		}else{
			ajaxResult="0";
		}
		return "findByFactNo2";
	}
	
	public void print() throws IOException{
		XSSFWorkbook wb=new XSSFWorkbook();
		XSSFSheet sheet=wb.createSheet();
		listitem=webbrproSer.findByfactNoAndYymmdd_print(factNo,yymmdd,yymmdd2);
		Map<String,Object>map_style=GlobalMethod.findStyles2007(wb);
		XSSFCellStyle cs_title=(XSSFCellStyle)map_style.get("cs_title");
		XSSFCellStyle cs_head=(XSSFCellStyle)map_style.get("cs_head");
		XSSFCellStyle cs=(XSSFCellStyle)map_style.get("cs");
		XSSFCellStyle cs_poi2=(XSSFCellStyle)map_style.get("cs_poi2");
		List<String>list_head=new ArrayList<String>();
		list_head.add("廠別");
		list_head.add("截止日期");
		list_head.add("產品明稱");
		list_head.add("庫存數(KG)");
		list_head.add("已訂購未入廠(KG)");
		list_head.add("當月耗用(KG)");
		for(int a=0;a<=listitem.size()+1;a++){
			sheet.createRow(a);			
			for(int b=0;b<list_head.size();b++){
				if(b>2){
					sheet.getRow(a).createCell(b).setCellStyle(cs_poi2);
				}else{
					sheet.getRow(a).createCell(b).setCellStyle(cs);
				}				
				if(a==0){
					sheet.setColumnWidth(b,5000);
				}
			}
		}		
				
		CellRangeAddress cra_head=new CellRangeAddress(0,0,0,5);
		sheet.addMergedRegion(cra_head);
		sheet.getRow(0).getCell(0).setCellValue("各廠BR產品庫存耗用明細表");
		for(int a=0;a<5;a++){
			sheet.getRow(0).getCell(a).setCellStyle(cs_title);
		}
		
		for(int a=0;a<list_head.size();a++){
			sheet.getRow(1).getCell(a).setCellValue(list_head.get(a));
			sheet.getRow(1).getCell(a).setCellStyle(cs_head);
		}
		
		for(int a=0;a<listitem.size();a++){
			//sheet.getRow(2+a).getCell(0).setCellValue(listitem.get(a).getId().getWebBrProduct().getId().getFactNo());
			sheet.getRow(2+a).getCell(0).setCellValue(listitem.get(a).getId().getWebBrProduct().getFactNo2().getFactSname());
			sheet.getRow(2+a).getCell(1).setCellValue(listitem.get(a).getId().getYymmdd());
			sheet.getRow(2+a).getCell(2).setCellValue(listitem.get(a).getId().getWebBrProduct().getNamec1()+"  "+listitem.get(a).getId().getWebBrProduct().getNamec2());
			sheet.getRow(2+a).getCell(3).setCellValue(listitem.get(a).getInventory());
			sheet.getRow(2+a).getCell(4).setCellValue(listitem.get(a).getOrderNotin());
			sheet.getRow(2+a).getCell(5).setCellValue(listitem.get(a).getActualUsed());	
		}		
		
		ServletOutputStream os=response.getOutputStream();
		//response.setContentType("application/vnd.ms-excel");
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		String fileName="report_webbrproduct.xlsx";
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
	
	public void print2() throws IOException{
		
		listitemAndest=webbrproSer.findByfactNoAndYymmdd_print2(factNo,yymmdd,yymmdd2);
		if(listitemAndest!=null&&listitemAndest.size()==0){
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>alert('無數據');window.close()</script>");
		}else{
			XSSFWorkbook wb=new XSSFWorkbook();
			XSSFSheet sheet=wb.createSheet();
			Map<String,Object>map_style=GlobalMethod.findStyles2007(wb);
			XSSFCellStyle cs_title=(XSSFCellStyle)map_style.get("cs_title");
			XSSFCellStyle cs_head=(XSSFCellStyle)map_style.get("cs_head");
			XSSFCellStyle cs=(XSSFCellStyle)map_style.get("cs");
			XSSFCellStyle cs_poi2=(XSSFCellStyle)map_style.get("cs_poi2");
			List<String>list_head=new ArrayList<String>();
			list_head.add("廠別");
			list_head.add("製程");
			list_head.add("截止日期");
			list_head.add("庫存數(KG)");
			list_head.add("已訂購未入廠(KG)");
			list_head.add("當月耗用(KG)");
			list_head.add("當月實際生產雙數(含不良)");
			list_head.add("次一月預估生產雙數");
			list_head.add("次二月預估生產雙數");
			list_head.add("次三月預估生產雙數");
			
			for(int a=0;a<=listitemAndest.size()+1;a++){
				sheet.createRow(a);
				for(int b=0;b<list_head.size();b++){
					sheet.getRow(a).createCell(b);
					if(b<3){
						sheet.getRow(a).getCell(b).setCellStyle(cs);
					}else{
						sheet.getRow(a).getCell(b).setCellStyle(cs_poi2);
					}
					if(a==0){
						sheet.setColumnWidth(b,5000);
					}
				}			
			}
			CellRangeAddress cra_head=new CellRangeAddress(0,0,0,5);
			sheet.addMergedRegion(cra_head);
			sheet.getRow(0).getCell(0).setCellValue("各廠BR產品預估明細表");
			for(int a=0;a<5;a++){
				sheet.getRow(0).getCell(a).setCellStyle(cs_title);
			}
			for(int a=0;a<list_head.size();a++){
				sheet.getRow(1).getCell(a).setCellValue(list_head.get(a));
				sheet.getRow(1).getCell(a).setCellStyle(cs_head);
			}
			
			for(int a=0;a<listitemAndest.size();a++){
				sheet.getRow(2+a).getCell(0).setCellValue(listitemAndest.get(a).getId().getFactNo());
				sheet.getRow(2+a).getCell(1).setCellValue(listitemAndest.get(a).getId().getFactCode());
				sheet.getRow(2+a).getCell(2).setCellValue(listitemAndest.get(a).getId().getYymmdd());
				sheet.getRow(2+a).getCell(3).setCellValue(listitemAndest.get(a).getInventory().doubleValue());
				sheet.getRow(2+a).getCell(4).setCellValue(listitemAndest.get(a).getOrdernotin().doubleValue());
				sheet.getRow(2+a).getCell(5).setCellValue(listitemAndest.get(a).getActualused().doubleValue());
				sheet.getRow(2+a).getCell(6).setCellValue(listitemAndest.get(a).getActualpairs());
				sheet.getRow(2+a).getCell(7).setCellValue(listitemAndest.get(a).getEstimatingpairs1());
				sheet.getRow(2+a).getCell(8).setCellValue(listitemAndest.get(a).getEstimatingpairs2());
				sheet.getRow(2+a).getCell(9).setCellValue(listitemAndest.get(a).getEstimatingpairs3());								
			}
			ServletOutputStream os=response.getOutputStream();
			//response.setContentType("application/vnd.ms-excel");
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			String fileName="report_webbrproduct.xlsx";
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
	
	public void findByfactCodeAndfactNoAndYymmdd_print2() throws IOException, ParseException{
		listitemAndest=webbrproSer.findByfactCodeAndfactNoAndYymmdd_print2(factNo,factCode,yymmdd);
		if(listitemAndest==null||listitemAndest.size()==0){
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>alert('無數據');window.close()</script>");
		}else{
			findByfactCodeAndfactNoAndYymmdd_print2_init2();
		}
	}
	
	public void findByfactCodeAndfactNoAndYymmdd_print2_init2() throws ParseException, IOException{
		Map<String,Object>map=new HashMap<String,Object>();
		XSSFWorkbook wb=new XSSFWorkbook();
		Map<String,Object>map_style=GlobalMethod.findStyles2007(wb);
		if("all".equals(factCode)){
			List<String>list_factcode=new ArrayList<String>();
			List<String>list_obj=webFactSer.findfactarea();
			for(String obj:list_obj){
				list_factcode.add(obj);
			}
			
			for(String factcode:list_factcode){
				List<VWebBrProandest>list_est=new ArrayList<VWebBrProandest>();
				for(VWebBrProandest est:listitemAndest){
					if(factcode.equals(est.getId().getFactCode())){
						list_est.add(est);
					}
				}
				map.put(factcode,list_est);
			}	
		}else{
			map.put(factCode,listitemAndest);
		}
		
		Calendar cal=Calendar.getInstance();
		cal.setTime(new SimpleDateFormat("yyyyMMdd").parse(yymmdd));
		cal.add(Calendar.MONTH,1);
		String date1=new SimpleDateFormat("yyyyMM").format(cal.getTime());
		cal.add(Calendar.MONTH,2);
		String date2=new SimpleDateFormat("yyyyMM").format(cal.getTime());
		List<String>list_head=new ArrayList<String>();
		list_head.add("廠別");
		list_head.add("BR庫存數(KG)");
		list_head.add("已訂購未入廠(KG)");
		list_head.add("BR當月耗用(KG)");
		list_head.add("RB生產雙數(含不良)");
		list_head.add("BR單雙耗用(KG)");
		list_head.add(date1+"-"+date2+"預估月平均生產雙數");
		list_head.add(date1+"-"+date2+"預估BR月平均耗用(KG)");
		list_head.add(date1+"-"+date2+"預估BR日耗用(KG)");
		list_head.add("BR庫存可用天數");
		list_head.add("BR庫存可用日期");
		
		cal.setTime(new SimpleDateFormat("yyyyMMdd").parse(yymmdd));
		for(int a=1;a<=months;a++){
			if(a==1){
				cal.add(Calendar.MONTH,2);
			}else{
				cal.add(Calendar.MONTH,1);
			}
			list_head.add(new SimpleDateFormat("yyyyMMdd").format(cal.getTime())+"預估到港");
		}
		
		for(String factcode:map.keySet()){
			XSSFSheet sheet=wb.createSheet(factcode);
			List<VWebBrProandest>list_parameter=(List<VWebBrProandest>)map.get(factcode);
			findByfactCodeAndfactNoAndYymmdd_print2_init(sheet,map_style,list_parameter,list_head);
		}
		
		
		ServletOutputStream os=response.getOutputStream();
		//response.setContentType("application/vnd.ms-excel");
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		String fileName="report_webbrproduct.xlsx";
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
	
	public void findByfactCodeAndfactNoAndYymmdd_print2_init(XSSFSheet sheet,Map<String,Object>map_style, List<VWebBrProandest>list_parameter,List<String>list_head) throws ParseException, IOException{		
					
		List<List>list_all=new ArrayList<List>();		
		Double d1;
		Double d2;
		Double d3;
		Double d4;
		Double d5;
		String date;
		Calendar cal2=Calendar.getInstance();
		Calendar cal3=Calendar.getInstance();		
		
		for(VWebBrProandest obj:list_parameter){
			List list=new ArrayList();
			d1=GlobalMethod.division(obj.getActualused().doubleValue(),obj.getActualpairs().doubleValue());//BR單雙耗用(KG)
			d2=(obj.getEstimatingpairs1()+obj.getEstimatingpairs2()+obj.getEstimatingpairs3())/3;//預估月平均生產雙數
			d3=d1*d2;//預估BR月平均耗用(KG)
			d4=d3/25;//預估BR日耗用(KG)
			d5=GlobalMethod.division((obj.getInventory().doubleValue()+obj.getOrdernotin().doubleValue()),d4);//庫存可用天數		
			cal2.setTime(new SimpleDateFormat("yyyyMMdd").parse(yymmdd));
			cal2.add(Calendar.DAY_OF_MONTH,d5.intValue());
			date=new SimpleDateFormat("yyyyMMdd").format(cal2.getTime());//庫存可用日期
			list.add(obj.getId().getFactNo());
			list.add(obj.getInventory());
			list.add(obj.getOrdernotin());
			list.add(obj.getActualused());
			list.add(obj.getActualpairs());
			list.add(d1);
			list.add(d2);
			list.add(d3);
			list.add(d4);
			list.add(d5);
			list.add(date);	
			cal2.setTime(new SimpleDateFormat("yyyyMMdd").parse(yymmdd));
			cal3.setTime(new SimpleDateFormat("yyyyMMdd").parse(date));
			for(int a=1;a<=months;a++){				
				if(a==1){					
					cal2.add(Calendar.MONTH,3);
				}else{
					cal2.add(Calendar.MONTH,1);
				}
				list.add((GlobalMethod.sumDate(cal2.getTime(),cal3.getTime()))*d4);//預估到港
			}
			list_all.add(list);
		}
		
		
		
		
		XSSFCellStyle cs_title=(XSSFCellStyle)map_style.get("cs_title");
		XSSFCellStyle cs_head=(XSSFCellStyle)map_style.get("cs_head");
		XSSFCellStyle cs=(XSSFCellStyle)map_style.get("cs");
		XSSFCellStyle cs_poi2=(XSSFCellStyle)map_style.get("cs_poi2");
		
		for(int a=0;a<10;a++){
			sheet.createRow(a);
			for(int b=0;b<list_head.size();b++){
				sheet.getRow(a).createCell(b);
			}
		}
		CellRangeAddress cra_title=new CellRangeAddress(0,0,0,5);
		sheet.addMergedRegion(cra_title);
		sheet.getRow(0).getCell(0).setCellValue("各廠BR消耗進度表");
		for(int a=0;a<5;a++){
			sheet.getRow(0).getCell(a).setCellStyle(cs_title);
		}
		for(int a=0;a<list_head.size();a++){
			sheet.getRow(1).getCell(a).setCellValue(new XSSFRichTextString(list_head.get(a)));
			cs_head.setWrapText(true);
			sheet.getRow(1).getCell(a).setCellStyle(cs_head);
			sheet.setColumnWidth(a,5000);
		}
		
		List list_total=new ArrayList();
		for(String str:list_head){
			if("廠別".equals(str)){
				list_total.add("合計");
			}else if("BR庫存可用日期".equals(str)){
				list_total.add("");
			}else{
				list_total.add(0.00);
			}
		}
		for(int a=0;a<list_all.size();a++){
			for(int b=0;b<list_head.size();b++){								
				if(list_head.get(b).indexOf("廠別")==-1&&
				   list_head.get(b).indexOf("BR庫存可用日期")==-1&&
				   list_head.get(b).indexOf("BR庫存可用天數")==-1){
					if(list_head.get(b).indexOf("預估到港")==-1){
						list_total.set(b,Double.parseDouble(list_total.get(b).toString())+Double.parseDouble(list_all.get(a).get(b).toString()));
					}else{
						if(Double.parseDouble(list_all.get(a).get(b).toString())<0.01){
							list_total.set(b,Double.parseDouble(list_total.get(b).toString())+Double.parseDouble(list_all.get(a).get(b).toString()));//預估到港只加總負數
						}
					}										
				}				
			}
		}
		list_all.add(list_total);
		
		for(int a=0;a<list_all.size();a++){
			for(int b=0;b<list_head.size();b++){				
				if(list_head.get(b).indexOf("廠別")==0||
				   list_head.get(b).indexOf("BR庫存可用日期")==0){
					sheet.getRow(2+a).getCell(b).setCellValue(list_all.get(a).get(b).toString());
					sheet.getRow(2+a).getCell(b).setCellStyle(cs);
				}else{
					if(a==list_all.size()-1){
						if(list_head.get(b).indexOf("BR庫存可用天數")==0){
							sheet.getRow(2+a).getCell(b).setCellValue("");
							sheet.getRow(2+a).getCell(b).setCellStyle(cs);
						}else{
							sheet.getRow(2+a).getCell(b).setCellValue(Double.parseDouble(list_all.get(a).get(b).toString()));
							sheet.getRow(2+a).getCell(b).setCellStyle(cs_poi2);
						}
					}else{
						sheet.getRow(2+a).getCell(b).setCellValue(Double.parseDouble(list_all.get(a).get(b).toString()));
						sheet.getRow(2+a).getCell(b).setCellStyle(cs_poi2);
					}					
				}				
			}
		}	
	}
	
	
	
}
