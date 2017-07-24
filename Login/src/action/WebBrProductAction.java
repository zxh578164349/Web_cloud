/**
 * 
 */
package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import net.sf.json.JSONArray;

import services.IWebBrProductServices;
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
		
	private IWebBrProductServices webbrproSer;
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
	public void setWebbrproSer(IWebBrProductServices webbrproSer){
		this.webbrproSer=webbrproSer;
	}			
	
	public List<WebBrProduct> getListbrpro(){
		return listbrpro;
	}
	public void setListbrpro(List<WebBrProduct> listbrpro){
		this.listbrpro=listbrpro;
	}
	public void setServletResponse(HttpServletResponse response){
		// TODO Auto-generated method stub
		this.response=response;
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
		XSSFWorkbook wb=new XSSFWorkbook();
		XSSFSheet sheet=wb.createSheet();
		listitemAndest=webbrproSer.findByfactNoAndYymmdd_print2(factNo,yymmdd,yymmdd2);
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
