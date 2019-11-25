package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import services.IWebFactServices;
import services.IWebObjsAServices;
import util.GlobalMethod;
import util.ImportExcel;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyzExpectmatmLog;
import entity.VKpiWebprofitlossItems;
import entity.VWebFact;
import entity.VWebobjA;
import entity.VWebobjA2;
import entity.VWebobjA2Id;
import entity.VWebobjA3;
import entity.VWebobjA3Id;
import entity.VWebobjAId;
import entity.WebFact;
import entity.WebFactId;
import entity.WebObjsA;
import entity.WebObjsAId;
import entity.WebUser;
import entity.WeballobjB;
import entity.WeballobjBId;

public class WebObjsAAction extends ActionSupport implements ServletResponseAware{
	private final static int NUM=31;//print_tw  多少箇項目（29+1）
	private IWebObjsAServices webobjaservices;
	private File file;
    private String fileFileName;
    private String fileContentType;
    private String ajaxResult;
    private String factNo;
    private String factCode;
    private String yymm;
    private String yymm2;
    private int page;
    private PageBean bean;
    private final static String SEPARATOR = "__";
    private javax.servlet.http.HttpServletResponse response;
    private IWebFactServices webFactSer;
    private String asicsMk;//是否ASICS工廠    0是      1否
    private List<String>list_factcode;
	private List<String>list_factno;
	private String factname;
	private String yymmdd;
	private int emailMk;
	
    
    
        
	public int getEmailMk() {
		return emailMk;
	}

	public void setEmailMk(int emailMk) {
		this.emailMk = emailMk;
	}

	public String getYymmdd() {
		return yymmdd;
	}

	public void setYymmdd(String yymmdd) {
		this.yymmdd = yymmdd;
	}

	public String getFactname() {
		return factname;
	}

	public void setFactname(String factname) {
		this.factname = factname;
	}

	public List<String> getList_factcode() {
		return list_factcode;
	}

	public void setList_factcode(List<String> list_factcode) {
		this.list_factcode = list_factcode;
	}

	public List<String> getList_factno() {
		return list_factno;
	}

	public void setList_factno(List<String> list_factno) {
		this.list_factno = list_factno;
	}

	public String getAsicsMk() {
		return asicsMk;
	}

	public void setAsicsMk(String asicsMk) {
		this.asicsMk = asicsMk;
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

	public void setWebobjaservices(IWebObjsAServices webobjaservices) {
		this.webobjaservices = webobjaservices;
	}
	
	
	public void addMore() throws IOException{
		response.setContentType("text/html;charset=utf-8");
		try{
			String path="d:\\Webobjs_a_backup\\"+new SimpleDateFormat("yyyyMMdd").format(new Date());//Excel文檔存放目錄
			ajaxResult="0";				
			/*文件上傳*/
			if(file!=null){//不為空代表有上傳附檔,不能寫成files.size()>0,否則報空指針
				//File uploadFile=new File(ServletActionContext.getServletContext().getRealPath("KyzexpFile\\"+kyz.getId().getBillNo()));//附檔上傳到項目
				File uploadFile_backup=new File(path);//附檔上傳到D盤(為了避免更新項目時丟失附檔,所在上傳到D盤)			
				if(!uploadFile_backup.exists()){
					uploadFile_backup.mkdirs();
				}																						
						FileInputStream in=new FileInputStream(file);
						FileOutputStream out_backup=new FileOutputStream(uploadFile_backup+"\\"+fileFileName);//備份
						byte[]b=new byte[1024];
						int length=0;
						while((length=in.read(b))>0){
							out_backup.write(b,0,length);//備份
						}																																				
			}
			WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");			
			//file=new File("e:\\導入格式2.xls");
			Map<String,Object>map=ImportExcel.exportListFromFile(new File(path+"\\"+fileFileName));
			List<String>list_factArea=webFactSer.findfactAreaByFactNo(factNo);
			a:for(String key:map.keySet()){//for a
				List<WebObjsA>list_b=new ArrayList<WebObjsA>();
				List<String>list_factcode=new ArrayList<String>();//導入數據所有的factcode
				List<String>list=(List<String>)map.get(key);
				if(!list.get(0).contains("__序號__項目__單位")){				
					response.getWriter().print("<script>window.parent.showDiv();window.parent.layer.msg('表格式不符合要求')</script>");
					//continue;
					break;
				}
				
				/*for(int a=0;a<list.size();a++){
					if(list.size()>43||list.get(a).contains("平均日產能")||list.get(a).contains("庫存天數")){						
						response.getWriter().print("<script>window.parent.layer.msg('項目不正確')</script>");
						break a;
					}
					
				 }*/
				if(list.size()>25){
					response.getWriter().print("<script>window.parent.layer.msg('項目不正確')</script>");
					break a;
				}
				
				
				String[] array_head =list.get(0).split("__");
				for(int i=4;i<array_head.length;i++){
					list_factcode.add(array_head[i].trim());
				}
				if(!list_factArea.containsAll(list_factcode)){
					StringBuilder sb=new StringBuilder();
					sb.append("(");					
					for(String factArea:list_factArea){
						sb.append(factArea+" ");
					}
					sb.append(")");
					response.getWriter().print("<script>window.parent.layer.alert('請核對正確的廠別狀態:"+sb.toString()+"',8)</script>");
					break;
				}
				for(int i=4;i<array_head.length;i++){//for b
					WebFact fact=new WebFact(new WebFactId(factNo,array_head[i].trim()));
					WebObjsA obj=new WebObjsA(new WebObjsAId(fact,yymm));								
					obj.setObjA1(Double.valueOf(list.get(1).split(SEPARATOR)[i]));
					obj.setObjA2(Double.valueOf(list.get(2).split(SEPARATOR)[i]));
					obj.setObjA3(Double.valueOf(list.get(3).split(SEPARATOR)[i]));
					obj.setObjA4(Double.valueOf(list.get(4).split(SEPARATOR)[i]));
					obj.setObjA5(Double.valueOf(list.get(5).split(SEPARATOR)[i]));
					obj.setObjA6(Double.valueOf(list.get(6).split(SEPARATOR)[i]));
					obj.setObjA7(Double.valueOf(list.get(7).split(SEPARATOR)[i]));
					obj.setObjA8(Double.valueOf(list.get(8).split(SEPARATOR)[i]));
					obj.setObjA9(Double.valueOf(list.get(9).split(SEPARATOR)[i]));
					obj.setObjA10(Double.valueOf(list.get(10).split(SEPARATOR)[i]));
					obj.setObjA11(Double.valueOf(list.get(11).split(SEPARATOR)[i]));
					obj.setObjA12(Double.valueOf(list.get(12).split(SEPARATOR)[i]).longValue());
					obj.setObjA13(Double.valueOf(list.get(13).split(SEPARATOR)[i]).longValue());
					obj.setObjA14(Double.valueOf(list.get(14).split(SEPARATOR)[i]).longValue());
					obj.setObjA15(Double.valueOf(list.get(15).split(SEPARATOR)[i]).longValue());
					obj.setObjA16(Double.valueOf(list.get(16).split(SEPARATOR)[i]).longValue());					
					obj.setObjA17("0.0".equals(list.get(17).split(SEPARATOR)[i])?"null":list.get(17).split(SEPARATOR)[i]);
					obj.setObjA18("0.0".equals(list.get(18).split(SEPARATOR)[i])?"null":list.get(18).split(SEPARATOR)[i]);
					obj.setObjA19("0.0".equals(list.get(19).split(SEPARATOR)[i])?"null":list.get(19).split(SEPARATOR)[i]);
					obj.setObjA20("0.0".equals(list.get(20).split(SEPARATOR)[i])?"null":list.get(20).split(SEPARATOR)[i]);
					obj.setObjA21("0.0".equals(list.get(21).split(SEPARATOR)[i])?"null":list.get(21).split(SEPARATOR)[i]);
					
					obj.setUsername(user.getUsername());
					obj.setCreatedate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
					list_b.add(obj);
				}//for b
				webobjaservices.addMore(list_b);
				response.getWriter().print("<script>window.parent.layer.msg('導入成功',3,1)</script>");
			}//for a
						
		}catch(Exception e){
			System.out.println(e);
			response.getWriter().print("<script>window.parent.layer.msg('導入錯誤',3,3);</script>");
		}
	}
	
	
	
	public String findPageBean(){
		ActionContext.getContext().getSession().remove("allrow");//dao層
		ActionContext.getContext().getSession().remove("public_yymm");
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		ActionContext.getContext().getSession().put("public_factno", factNo);
		bean=webobjaservices.findPageBean(20,page, factNo, yymm);
		return "beanList";
		
	}
	public String findPageBean2(){
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		ActionContext.getContext().getSession().remove("allrow");//dao層
		ActionContext.getContext().getSession().put("public_factno", factNo.split("__")[0]);
		ActionContext.getContext().getSession().put("public_yymm", yymm);	
		bean=webobjaservices.findPageBean(20,page, factNo.split("__")[0], yymm);
		return "beanList1";
	}
	public String findPageBean3(){
		factNo=(String)ActionContext.getContext().getSession().get("public_factno");
		yymm=(String)ActionContext.getContext().getSession().get("public_yymm");
		bean=webobjaservices.findPageBean(20,page, factNo, yymm);
		return "beanList1";
	}
	
	
	public String delete(){
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setFactNo(factNo);
		log.setFactCode(factCode);
		log.setObj("WebObjsA");
		log.setYymm(yymmdd);
		webobjaservices.delete(factNo, factCode, yymmdd,log);
		return "delete";
	}
	
	
	/*************************************************工廠報表(一個月每天情況)***********************************************************/
	public void print() throws ParseException, IOException{
		HSSFWorkbook wb=new HSSFWorkbook();
		Map<String,Object>map_cs=findStyles(wb);				
		List<WebObjsA>list_objs=webobjaservices.findByYymm(factNo.split("__")[0], yymm);		
		switch(list_objs.size()){//switch		
		case 0:
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>window.parent.alert('無數據');</script>");			
			break;
		default:
			
			Calendar cal=Calendar.getInstance();
			cal.setTime(new SimpleDateFormat("yyyyMM").parse(yymm));
			int days=cal.getActualMaximum(Calendar.DAY_OF_MONTH);//選擇月份的天數
			List<String>list_factcodes=new ArrayList<String>();
			for(WebObjsA obj:list_objs){
				list_factcodes.add(obj.getId().getWebFact().getId().getFactArea());
			}
			
			for(int a=0;a<list_factcodes.size();a++){
				for(int b=list_factcodes.size()-1;b>a;b--){
					if(list_factcodes.get(a).equals(list_factcodes.get(b))){
						list_factcodes.remove(b);
					}
				}
			}
			
			Map<String,Object>map=new LinkedHashMap<String,Object>();
			Map<String,Object>map2=new LinkedHashMap<String,Object>();
			List<WebObjsA>list_lg=new ArrayList<WebObjsA>();
			for(int a=0;a<days;a++){
				list_lg.add(new WebObjsA());
			}
			for(String factcode:list_factcodes){
				String temp="";
				List<WebObjsA>list=new ArrayList<WebObjsA>();
				for(int a=1;a<=days;a++){				
					if(a>9){
						temp=yymm+a;
					}else{
						temp=yymm+"0"+a;
					}
					list.add(new WebObjsA(new WebObjsAId(new WebFact(new WebFactId(factNo.split("__")[0],factcode)),temp)));
					temp="";
				}
				
				for(int a=0;a<list.size();a++){
					for(WebObjsA obj:list_objs){
						if(list.get(a).getId().getWebFact().getId().getFactArea().equals(obj.getId().getWebFact().getId().getFactArea())&&
								list.get(a).getId().getWebFact().getId().getFactNo().equals(obj.getId().getWebFact().getId().getFactNo())&&
								list.get(a).getId().getYymmdd().equals(obj.getId().getYymmdd())){
							list.set(a, obj);
							break;
							
						}
					}
				}				
				map.put(factcode, list);
				for(int a=0;a<list.size();a++){					
						list_lg.get(a).setObjA12((list_lg.get(a).getObjA12()==null?0:list_lg.get(a).getObjA12())+(list.get(a).getObjA12()==null?0:list.get(a).getObjA12()));
						list_lg.get(a).setObjA13((list_lg.get(a).getObjA13()==null?0:list_lg.get(a).getObjA13())+(list.get(a).getObjA13()==null?0:list.get(a).getObjA13()));
						list_lg.get(a).setObjA14((list_lg.get(a).getObjA14()==null?0:list_lg.get(a).getObjA14())+(list.get(a).getObjA14()==null?0:list.get(a).getObjA14()));
						list_lg.get(a).setObjA15((list_lg.get(a).getObjA15()==null?0:list_lg.get(a).getObjA15())+(list.get(a).getObjA15()==null?0:list.get(a).getObjA15()));
						list_lg.get(a).setObjA16((list_lg.get(a).getObjA16()==null?0:list_lg.get(a).getObjA16())+(list.get(a).getObjA16()==null?0:list.get(a).getObjA16()));										
				}
				
				WebObjsA sum_obj=new WebObjsA();
				for(WebObjsA obj:list){								
					sum_obj.setObjA1(isMyNull_db(sum_obj.getObjA1())+isMyNull_db(obj.getObjA1()));
					sum_obj.setObjA2(isMyNull_db(sum_obj.getObjA2())+isMyNull_db(obj.getObjA2()));
					sum_obj.setObjA3(isMyNull_db(sum_obj.getObjA3())+isMyNull_db(obj.getObjA3()));
					sum_obj.setObjA4(isMyNull_db(sum_obj.getObjA4())+isMyNull_db(obj.getObjA4()));
					sum_obj.setObjA5(isMyNull_db(sum_obj.getObjA5())+isMyNull_db(obj.getObjA5()));
					sum_obj.setObjA6(isMyNull_db(sum_obj.getObjA6())+isMyNull_db(obj.getObjA6()));
					sum_obj.setObjA7(isMyNull_db(sum_obj.getObjA7())+isMyNull_db(obj.getObjA7()));
					sum_obj.setObjA8(isMyNull_db(sum_obj.getObjA8())+isMyNull_db(obj.getObjA8()));
					sum_obj.setObjA9(isMyNull_db(sum_obj.getObjA9())+isMyNull_db(obj.getObjA9()));
					sum_obj.setObjA10(isMyNull_db(sum_obj.getObjA10())+isMyNull_db(obj.getObjA10()));
					sum_obj.setObjA11(isMyNull_db(sum_obj.getObjA11())+isMyNull_db(obj.getObjA11()));				
				}
				map2.put(factcode, sum_obj);
								
			}
			
		
			HSSFSheet sheet=wb.createSheet(yymm+"工廠訊息");
			HSSFSheet sheet2=wb.createSheet(yymm+"提報事項");
			init(sheet,map_cs,map,list_lg,days,map2);
			init2(sheet2,map_cs,map,days);
			
			try {				
				ServletOutputStream os=response.getOutputStream();
				response.setContentType("application/vnd.ms-excel");
				int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");//判斷是否為IE瀏覽器,大於0則為IE瀏覽器
				String fileName=factNo.split("__")[0]+"_"+yymm+"工廠訊息報表"+".xls";
				if(msie>0){
					fileName=java.net.URLEncoder.encode(fileName,"utf-8");//解決IE中文文件不能下載的問題
				}else{
					fileName=new String(fileName.getBytes("utf-8"),"iso-8859-1");//解決非IE中文名亂碼問題
				}		
				response.setHeader("Content-disposition", "attachment;filename="+fileName);					
				wb.write(os);
				os.close();						
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}//switch							
	}
	
	
	public Map<String,Object> findStyles(HSSFWorkbook wb){		
		Map<String,Object>map=GlobalMethod.findStyles(wb);				
		return map;
	}
							
	public void init(HSSFSheet sheet,Map<String,Object>map_cs,Map<String,Object>map,List<WebObjsA>list_lg,int days,Map<String,Object>map2) throws ParseException{				
		HSSFCellStyle cs=(HSSFCellStyle)map_cs.get("cs");
		HSSFCellStyle cs_head=(HSSFCellStyle)map_cs.get("cs_head");
		HSSFCellStyle cs_title=(HSSFCellStyle)map_cs.get("cs_title");		
		List<String>list_col=findItems();
		List<String>list_col2=findItems2();
			sheet.setColumnWidth(1, 5000);
			for(int a=0;a<list_col.size()*map.size()+20;a++){
				sheet.createRow(a);				
				for(int b=0;b<days+5;b++){
					sheet.getRow(a).createCell(b);					
				}
			}
			
			CellRangeAddress cra_title=new CellRangeAddress(0,0,0,4);
			sheet.addMergedRegion(cra_title);
			sheet.getRow(0).getCell(0).setCellValue(factNo.split("__")[1]+"-"+yymm+"工廠訊息");
			for(int i=0;i<4;i++){
				sheet.getRow(0).getCell(i).setCellStyle(cs_title);
			}
						
			int temp=2;
			for(String factcode:map.keySet()){
				
				CellRangeAddress cra_date=new CellRangeAddress(temp,temp,0,1);
				sheet.addMergedRegion(cra_date);
				sheet.getRow(temp).getCell(0).setCellValue(yymm);
				for(int i=0;i<2;i++){
					sheet.getRow(temp).getCell(i).setCellStyle(cs_head);
				}
				for(int a=0;a<days;a++){
					sheet.getRow(temp).getCell(a+2).setCellValue(a+1+"號");
					sheet.getRow(temp).getCell(a+2).setCellStyle(cs_head);	
				}
				
				CellRangeAddress cra_factcode=new CellRangeAddress(temp+1,temp+list_col.size(),0,0);
				sheet.addMergedRegion(cra_factcode);
				sheet.getRow(temp+1).getCell(0).setCellValue(factcode);
				for(int b=0;b<list_col.size();b++){
					sheet.getRow(b+temp+1).getCell(0).setCellStyle(cs);
				}
				for(int b=0;b<list_col.size();b++){
					sheet.getRow(b+temp+1).getCell(1).setCellValue(list_col.get(b).split("__")[0]);
					sheet.getRow(b+temp+1).getCell(1).setCellStyle(cs);										
				}
				List<WebObjsA>list=(List<WebObjsA>)map.get(factcode);					
				for(int a=0;a<list.size();a++){						
					List<Double>list_db=objToDouble(list.get(a));
					for(int b=0;b<list_db.size();b++){
						sheet.getRow(b+temp+1).getCell(a+2).setCellValue(list_db.get(b));
						sheet.getRow(b+temp+1).getCell(a+2).setCellStyle(this.selectStyle2003(b, map_cs));
					}																
				}
				
				sheet.getRow(temp).getCell(2+days).setCellValue("總計");
				sheet.getRow(temp).getCell(3+days).setCellValue("平均");
				for(int a=0;a<2;a++){
					sheet.getRow(temp).getCell(2+days+a).setCellStyle(cs_head);
				}
				List<Double>list_sum=objToDouble((WebObjsA)map2.get(factcode));
				for(int b=0;b<list_sum.size();b++){
					sheet.getRow(b+temp+1).getCell(2+days).setCellValue(list_sum.get(b));
					sheet.getRow(b+temp+1).getCell(3+days).setCellValue(list_sum.get(b)/30);
					sheet.getRow(b+temp+1).getCell(2+days).setCellStyle(this.selectStyle2003(b, map_cs));
					sheet.getRow(b+temp+1).getCell(3+days).setCellStyle(this.selectStyle2003(b, map_cs));
				}
				
				temp=temp+list_col.size()+1;
			}	
			
			
			
			
			
			CellRangeAddress cra_date=new CellRangeAddress(temp,temp,0,1);
			sheet.addMergedRegion(cra_date);
			sheet.getRow(temp).getCell(0).setCellValue(yymm);
			for(int i=0;i<2;i++){
				sheet.getRow(temp).getCell(i).setCellStyle(cs_head);
			}
			for(int a=0;a<days;a++){
				sheet.getRow(temp).getCell(a+2).setCellValue(a+1+"號");
				sheet.getRow(temp).getCell(a+2).setCellStyle(cs_head);	
			}
			
			CellRangeAddress cra_all=new CellRangeAddress(temp+1,temp+list_col2.size(),0,0);
			sheet.addMergedRegion(cra_all);
			sheet.getRow(temp+1).getCell(0).setCellValue("全廠");
			for(int b=0;b<list_col2.size();b++){
				sheet.getRow(b+temp+1).getCell(0).setCellStyle(cs);
			}
			for(int b=0;b<list_col2.size();b++){
				sheet.getRow(b+temp+1).getCell(1).setCellValue(list_col2.get(b).split("__")[0]);
				sheet.getRow(b+temp+1).getCell(1).setCellStyle(cs);										
			}
			for(int a=0;a<list_lg.size();a++){						
				List<Long>list_db=objToLong(list_lg.get(a));
				for(int b=0;b<list_db.size();b++){
					sheet.getRow(b+temp+1).getCell(a+2).setCellValue(list_db.get(b));
					sheet.getRow(b+temp+1).getCell(a+2).setCellStyle(cs);
				}																
			}
	}
	
	
	public void init2(HSSFSheet sheet,Map<String,Object>map_cs,Map<String,Object>map,int days) throws ParseException{				
		HSSFCellStyle cs=(HSSFCellStyle)map_cs.get("cs");
		HSSFCellStyle cs_head=(HSSFCellStyle)map_cs.get("cs_head");
		HSSFCellStyle cs_title=(HSSFCellStyle)map_cs.get("cs_title");	
		List<String>list_col=findItems3();
			sheet.setColumnWidth(1, 5000);
			for(int a=0;a<list_col.size()*map.size()+10;a++){
				sheet.createRow(a);				
				for(int b=0;b<days+5;b++){
					sheet.getRow(a).createCell(b);					
				}
			}
			
			CellRangeAddress cra_title=new CellRangeAddress(0,0,0,4);
			sheet.addMergedRegion(cra_title);
			sheet.getRow(0).getCell(0).setCellValue(factNo.split("__")[1]+"-"+yymm+"工廠訊息");
			for(int i=0;i<4;i++){
				sheet.getRow(0).getCell(i).setCellStyle(cs_title);
			}						
			int temp=2;
			for(String factcode:map.keySet()){
				
				CellRangeAddress cra_date=new CellRangeAddress(temp,temp,0,1);
				sheet.addMergedRegion(cra_date);
				sheet.getRow(temp).getCell(0).setCellValue(yymm);
				for(int i=0;i<2;i++){
					sheet.getRow(temp).getCell(i).setCellStyle(cs_head);
				}
				for(int a=0;a<days;a++){
					sheet.getRow(temp).getCell(a+2).setCellValue(a+1+"號");
					sheet.getRow(temp).getCell(a+2).setCellStyle(cs_head);	
				}
				
				CellRangeAddress cra_factcode=new CellRangeAddress(temp+1,temp+list_col.size(),0,0);
				sheet.addMergedRegion(cra_factcode);
				sheet.getRow(temp+1).getCell(0).setCellValue(factcode);
				for(int b=0;b<list_col.size();b++){
					sheet.getRow(b+temp+1).getCell(0).setCellStyle(cs);
				}
				for(int b=0;b<list_col.size();b++){
					sheet.getRow(b+temp+1).getCell(1).setCellValue(list_col.get(b).split("__")[0]);
					sheet.getRow(b+temp+1).getCell(1).setCellStyle(cs);										
				}
				List<WebObjsA>list=(List<WebObjsA>)map.get(factcode);					
				for(int a=0;a<list.size();a++){						
					List<String>list_db=objToString(list.get(a));
					for(int b=0;b<list_db.size();b++){
						sheet.getRow(b+temp+1).getCell(a+2).setCellValue(list_db.get(b));
						sheet.getRow(b+temp+1).getCell(a+2).setCellStyle(cs);
					}																
				}										
				temp=temp+list_col.size()+1;
			}			
	}
	
	public List<Double> objToDouble(WebObjsA obj){
		List<Double>list=new ArrayList<Double>();		
		list.add(isMyNull_db(obj.getObjA1()));
		list.add(isMyNull_db(obj.getObjA2()));
		list.add(isMyNull_db(obj.getObjA3()));
		list.add(isMyNull_db(obj.getObjA4()));
		list.add(isMyNull_db(obj.getObjA5()));
		list.add(isMyNull_db(obj.getObjA6()));
		list.add(isMyNull_db(obj.getObjA7()));
		list.add(isMyNull_db(obj.getObjA8()));
		list.add(isMyNull_db(obj.getObjA9()));
		list.add(isMyNull_db(obj.getObjA10()));
		list.add(isMyNull_db(obj.getObjA11()));
		return list;		
	}
	
	public List<String> objToString(WebObjsA obj){
		List<String>list=new ArrayList<String>();		
		list.add(isMyNull_str(obj.getObjA17()));
		list.add(isMyNull_str(obj.getObjA18()));
		list.add(isMyNull_str(obj.getObjA19()));
		list.add(isMyNull_str(obj.getObjA20()));
		list.add(isMyNull_str(obj.getObjA21()));
		return list;		
	}
	
	public List<Long> objToLong(WebObjsA obj){
		List<Long>list=new ArrayList<Long>();			
		list.add(isMyNull_ll(obj.getObjA12()));
		list.add(isMyNull_ll(obj.getObjA13()));
		list.add(isMyNull_ll(obj.getObjA14()));
		list.add(isMyNull_ll(obj.getObjA15()));
		list.add(isMyNull_ll(obj.getObjA16()));
		return list;		
	}
		
	/*************************************************工廠報表(一個月每天情況)***********************************************************/
	
	
	
	
	
	
	/***************************************各廠月報表彙總************************************************************/
	public void print_tw() throws IOException, ParseException{
		XSSFWorkbook wb=new XSSFWorkbook();
		XSSFSheet sheet=wb.createSheet("工廠訊息匯總_"+yymm);
		Map<String,Object>map_style=GlobalMethod.findStyles2007(wb);				
		List<VWebobjA>list_source=webobjaservices.findByVwebobja(yymm);
		List<VWebobjA2>list_source2=webobjaservices.findByVwebobja2(yymm);
		List<WebObjsA>list_source3=webobjaservices.findObjByMonth(yymm);
		Map<String,Object>map=new LinkedHashMap<String,Object>();
						
		//List<WebFact>facts=webFactSer.findFactAble();//所有有效廠別
		List<WebFact>facts=webFactSer.findAllFact_showA();
		Calendar cal=Calendar.getInstance();
		cal.setTime(new SimpleDateFormat("yyyyMM").parse(yymm));
		int days=cal.getActualMaximum(Calendar.DAY_OF_MONTH);//選擇月份的天數
		List<String>factcodes=new ArrayList<String>();
		List<String>factnos=new ArrayList<String>();
		List<VWebobjA>list_obj=new LinkedList<VWebobjA>();
		List<VWebobjA2>list_obj2=new LinkedList<VWebobjA2>();
		List<List<WebObjsA>>list_obj3=new LinkedList<List<WebObjsA>>();
		for(WebFact fact:facts){
			factcodes.add(fact.getId().getFactArea());
			list_obj.add(new VWebobjA(new VWebobjAId(fact,yymm)));
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
			for(VWebobjA obj:list_source){
				if(list_obj.get(a).getId().getWebFact().getId().getFactArea().equals(obj.getId().getWebFact().getId().getFactArea())&&
						list_obj.get(a).getId().getWebFact().getId().getFactNo().equals(obj.getId().getWebFact().getId().getFactNo())){
					list_obj.set(a, obj);
					break;
				}
			}
		}
		
		for(String factcode:factcodes){
			List<VWebobjA>list=new ArrayList<VWebobjA>();
			for(VWebobjA obj:list_obj){
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
		for(String factno:factnos){
			list_obj2.add(new VWebobjA2(new VWebobjA2Id(new VWebFact(factno),yymm)));
		}
		
		for(VWebobjA2 obj:list_obj2){
			for(WebFact fact:facts){
				if(obj.getId().getFact().getFactNo().equals(fact.getId().getFactNo())){
					obj.getId().getFact().setFactSname(fact.getFactSname());
				}
			}
		}
		
		for(int a=0;a<list_obj2.size();a++){
			for(VWebobjA2 obj:list_source2){
				if(list_obj2.get(a).getId().getFact().getFactNo().equals(obj.getId().getFact().getFactNo())){
					list_obj2.set(a, obj);
				}
			}
		}
		
		String dd="";
		for(int a=1;a<=days;a++){
			if(a<10){
				dd="0"+a;
			}else{
				dd=""+a;
			}
			List<WebObjsA>list=new LinkedList<WebObjsA>();		
			for(WebFact fact:facts){
				list.add(new WebObjsA(new WebObjsAId(fact,yymm+dd)));
			}
			list_obj3.add(list);
			dd="";
		}
		
		List<Map<String,Object>>lm=new LinkedList<Map<String,Object>>();
		for(List<WebObjsA>list:list_obj3){			
			Map<String,Object>m=new LinkedHashMap<String,Object>();
			for(int a=0;a<list.size();a++){
				for(WebObjsA obj:list_source3){
					if(list.get(a).getId().getWebFact().getId().getFactArea().equals(obj.getId().getWebFact().getId().getFactArea())&&
							list.get(a).getId().getWebFact().getId().getFactNo().equals(obj.getId().getWebFact().getId().getFactNo())&&
							list.get(a).getId().getYymmdd().equals(obj.getId().getYymmdd())){
						list.set(a, obj);
						break;
					}
				}
			}
			
			for(String factcode:factcodes){
				List<WebObjsA>ll=new ArrayList<WebObjsA>();
				for(WebObjsA obj:list){
					if(factcode.equals(obj.getId().getWebFact().getId().getFactArea())){
						ll.add(obj);
					}
				}
				m.put(factcode, ll);				
			}
			lm.add(m);
		}
		
																				
		this.init_more(sheet,map,map_style,list_obj2);	
		for(int a=1;a<=lm.size();a++){
			XSSFSheet sheet2=wb.createSheet("狀況回報_"+a+"號");
			Map<String,Object>m=lm.get(a-1);
			this.init_more2_b(sheet2, m, map_style,yymm);
		}
		if(emailMk==1){//發送郵件報表			
			//OutputStream os=new FileOutputStream("d:\\"+sdate+".xls");
			OutputStream os=new FileOutputStream(ServletActionContext.getServletContext().getRealPath("TEMPFILES\\"+yymm+".xlsx"));
			wb.write(os);
			os.close();		
		}else{
			ServletOutputStream os=response.getOutputStream();
			//response.setContentType("application/vnd.ms-excel");
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			String fileName=yymm+"月報表彙總.xlsx";
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
	
public void init_more(XSSFSheet sheet,Map<String,Object>map,Map<String,Object>map_style,List<VWebobjA2>list_obj2) throws IOException{			
		this.init(sheet,map);
		List<String>list_items=this.findItems();
		List<String>list_items2=this.findItems2();
		//樣式
		XSSFCellStyle cs_title=(XSSFCellStyle)map_style.get("cs_title");
		XSSFCellStyle cs_head=(XSSFCellStyle)map_style.get("cs_head");
		XSSFCellStyle cs_head2=(XSSFCellStyle)map_style.get("cs_head2");
		XSSFCellStyle cs=(XSSFCellStyle)map_style.get("cs");//		
		//標題
		CellRangeAddress cra_title=new CellRangeAddress(0,(short)0,0,(short)5);
		sheet.addMergedRegion(cra_title);				
		sheet.getRow(0).getCell(0).setCellValue("各廠訊息匯總_"+yymm);
		
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
			List<VWebobjA>list_obj=(List<VWebobjA>)map.get(factcode);	
			
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
			CellRangeAddress cra1=new CellRangeAddress(1+temp1_1,(short)1+temp1_1,3+temp,(short)2+temp+length);
			sheet.addMergedRegion(cra1);
			for(int i=0;i<length;i++){
				sheet.getRow(1+temp1_1).getCell(3+temp+i).setCellStyle(cs_head2);
			}
			
			List<List<String>>list_pack=this.packageTostring(list_obj,list_items);			
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
		
		int temp2=list_items.size()+5;
		CellRangeAddress cra2=new CellRangeAddress(temp2+temp1_1,(short)temp2+temp1_1,0,(short)2);
		sheet.addMergedRegion(cra2);
		sheet.getRow(temp2+temp1_1).getCell(0).setCellValue("廠別");
		for(int i=0;i<3;i++){
			sheet.getRow(temp2+temp1_1).getCell(i).setCellStyle(cs_head2);
		}
		for(int a=0;a<list_items2.size();a++){						
			sheet.getRow(temp2+a+1+temp1_1).getCell(0).setCellValue(a+1);
			sheet.getRow(temp2+a+1+temp1_1).getCell(1).setCellValue(list_items2.get(a).split("__")[0]);//項目
			sheet.getRow(temp2+a+1+temp1_1).getCell(2).setCellValue(list_items2.get(a).split("__")[1]);//單位
			for(int j=0;j<3;j++){
				sheet.getRow(temp2+a+1+temp1_1).getCell(j).setCellStyle(cs);
			}
		}
		
		for(int a=0;a<list_obj2.size();a++){
			sheet.getRow(temp2+temp1_1).getCell(3+a).setCellValue(list_obj2.get(a).getId().getFact().getFactSname());
			sheet.getRow(temp2+1+temp1_1).getCell(3+a).setCellValue(list_obj2.get(a).getObjA12()==null?0:list_obj2.get(a).getObjA12());
			sheet.getRow(temp2+2+temp1_1).getCell(3+a).setCellValue(list_obj2.get(a).getObjA13()==null?0:list_obj2.get(a).getObjA13());
			sheet.getRow(temp2+3+temp1_1).getCell(3+a).setCellValue(list_obj2.get(a).getObjA14()==null?0:list_obj2.get(a).getObjA14());
			sheet.getRow(temp2+4+temp1_1).getCell(3+a).setCellValue(list_obj2.get(a).getObjA15()==null?0:list_obj2.get(a).getObjA15());
			sheet.getRow(temp2+5+temp1_1).getCell(3+a).setCellValue(list_obj2.get(a).getObjA16()==null?0:list_obj2.get(a).getObjA16());
			for(int b=0;b<list_items2.size()+1;b++){
				if(b==0){
					sheet.getRow(temp2+b+temp1_1).getCell(3+a).setCellStyle(cs_head2);
				}else{
					sheet.getRow(temp2+b+temp1_1).getCell(3+a).setCellStyle(cs);
				}
				
			}
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
	public List<List<String>> packageTostring(List<VWebobjA>list_obj,List<String>list_items){
		List<List<String>>result=new ArrayList<List<String>>();
		for(int i=0;i<list_obj.size();i++){
			VWebobjA obj=list_obj.get(i);
			List<String>list=new ArrayList<String>();			
			if(obj.getId().getWebFact().getFactSname()!=null){
				list.add(obj.getId().getWebFact().getFactSname());
			}else{
				list.add(obj.getId().getWebFact().getId().getFactNo().split("_")[2]);
			}								
			List<String>list_str=this.isPercents(obj,list_items);
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
	public List<String> isPercents(VWebobjA obj,List<String> list_items){
		List<Object>list=new ArrayList<Object>();
		List<String>list_result=new ArrayList<String>();
		DecimalFormat frm=new DecimalFormat("0.00%");//%號
		DecimalFormat frm1=new DecimalFormat("#,##0");//不保留小數		
		list.add(obj.getObjA1());
		list.add(obj.getObjA2());
		list.add(obj.getObjA3());
		list.add(obj.getObjA4());
		list.add(obj.getObjA5());
		list.add(obj.getObjA6());
		list.add(obj.getObjA7());
		list.add(obj.getObjA8());
		list.add(obj.getObjA9());
		list.add(obj.getObjA10());
		list.add(obj.getObjA11());		

		for(int i=0;i<list.size();i++){
			if(list.get(i)!=null){
				if(list_items.get(i).split("__")[1].equals("%")){
					list_result.add(frm.format(list.get(i)));
				}else{
					list_result.add(frm1.format(list.get(i)));							
				}				
			}else{
				list_result.add("0");
			}						
		}			
		return list_result;
	}
	/***************************************各廠月報表彙總************************************************************/
	
	
	
	/***************************************各廠日報表彙總************************************************************/
	public void print_tw2() throws IOException{
		XSSFWorkbook wb=new XSSFWorkbook();		
		Map<String,Object>map_style=GlobalMethod.findStyles2007(wb);				
		List<WebObjsA>list_source=webobjaservices.findObjByDay(yymmdd);
		List<VWebobjA3>list_source2=webobjaservices.findByVwebobja3(yymmdd);
		Map<String,Object>map=new LinkedHashMap<String,Object>();
						
		//List<WebFact>facts=webFactSer.findFactAble();//所有有效廠別
		//List<WebFact>facts=webFactSer.findAllFact_showA();//與重點指標報表相同
		List<WebFact>facts=webFactSer.findAllFact_2();//與產量報表相同
		List<String>factcodes=new ArrayList<String>();
		List<String>factnos=new ArrayList<String>();
		List<WebObjsA>list_obj=new LinkedList<WebObjsA>();
		List<VWebobjA3>list_obj2=new LinkedList<VWebobjA3>();
		for(WebFact fact:facts){
			factcodes.add(fact.getId().getFactArea());
			list_obj.add(new WebObjsA(new WebObjsAId(fact,yymmdd)));
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
			for(WebObjsA obj:list_source){
				if(list_obj.get(a).getId().getWebFact().getId().getFactArea().equals(obj.getId().getWebFact().getId().getFactArea())&&
						list_obj.get(a).getId().getWebFact().getId().getFactNo().equals(obj.getId().getWebFact().getId().getFactNo())){
					list_obj.set(a, obj);
				}
			}
		}
		
		for(String factcode:factcodes){
			List<WebObjsA>list=new ArrayList<WebObjsA>();
			for(WebObjsA obj:list_obj){
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
		for(String factno:factnos){
			list_obj2.add(new VWebobjA3(new VWebobjA3Id(new VWebFact(factno),yymm)));
		}
		
		for(VWebobjA3 obj:list_obj2){
			for(WebFact fact:facts){
				if(obj.getId().getFact().getFactNo().equals(fact.getId().getFactNo())){
					obj.getId().getFact().setFactSname(fact.getFactSname());
				}
			}
		}
		
		for(int a=0;a<list_obj2.size();a++){
			for(VWebobjA3 obj:list_source2){
				if(list_obj2.get(a).getId().getFact().getFactNo().equals(obj.getId().getFact().getFactNo())){
					list_obj2.set(a, obj);
				}
			}
		}
		
		XSSFSheet sheet=wb.createSheet("工廠訊息匯總_"+yymmdd);
		XSSFSheet sheet2=wb.createSheet("狀況回報_"+yymmdd);
		this.init_more2(sheet,map,map_style,list_obj2);
		init_more2_b(sheet2,map,map_style,yymmdd);
									
		if(emailMk==1){//發送郵件報表			
			//OutputStream os=new FileOutputStream("d:\\"+sdate+".xls");
			OutputStream os=new FileOutputStream(ServletActionContext.getServletContext().getRealPath("TEMPFILES\\"+yymmdd+".xlsx"));
			wb.write(os);
			os.close();
		}else{
			ServletOutputStream os=response.getOutputStream();
			//response.setContentType("application/vnd.ms-excel");
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			String fileName=yymmdd+"日報表彙總.xlsx";
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
	
	
	
public void init_more2(XSSFSheet sheet,Map<String,Object>map,Map<String,Object>map_style,List<VWebobjA3>list_obj2) throws IOException{				   
	    this.init(sheet,map);
		List<String>list_items=this.findItems();
		List<String>list_items2=this.findItems2();
		//樣式
		XSSFCellStyle cs_title=(XSSFCellStyle)map_style.get("cs_title");
		XSSFCellStyle cs_head=(XSSFCellStyle)map_style.get("cs_head");
		XSSFCellStyle cs_head2=(XSSFCellStyle)map_style.get("cs_head2");
		XSSFCellStyle cs=(XSSFCellStyle)map_style.get("cs");//		
		//標題
		CellRangeAddress cra_title=new CellRangeAddress(0,(short)0,0,(short)5);
		sheet.addMergedRegion(cra_title);				
		sheet.getRow(0).getCell(0).setCellValue("各廠訊息匯總_"+yymmdd);
		
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
			List<WebObjsA>list_obj=(List<WebObjsA>)map.get(factcode);	
			
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
			
			List<List<String>>list_pack=this.packageTostring2(list_obj,list_items);			
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
		
		
		int temp2=list_items.size()+5;
		CellRangeAddress cra2=new CellRangeAddress(temp2+temp1_1,(short)temp2+temp1_1,0,(short)2);
		sheet.addMergedRegion(cra2);
		sheet.getRow(temp2+temp1_1).getCell(0).setCellValue("廠別");
		for(int i=0;i<3;i++){
			sheet.getRow(temp2+temp1_1).getCell(i).setCellStyle(cs_head2);
		}
		for(int a=0;a<list_items2.size();a++){						
			sheet.getRow(temp2+temp1_1+a+1).getCell(0).setCellValue(a+1);
			sheet.getRow(temp2+temp1_1+a+1).getCell(1).setCellValue(list_items2.get(a).split("__")[0]);//項目
			sheet.getRow(temp2+temp1_1+a+1).getCell(2).setCellValue(list_items2.get(a).split("__")[1]);//單位
			for(int j=0;j<3;j++){
				sheet.getRow(temp2+temp1_1+a+1).getCell(j).setCellStyle(cs);
			}
		}
		
		for(int a=0;a<list_obj2.size();a++){
			sheet.getRow(temp2+temp1_1).getCell(3+a).setCellValue(list_obj2.get(a).getId().getFact().getFactSname());
			sheet.getRow(temp2+temp1_1+1).getCell(3+a).setCellValue(list_obj2.get(a).getObjA12()==null?0:list_obj2.get(a).getObjA12());
			sheet.getRow(temp2+temp1_1+2).getCell(3+a).setCellValue(list_obj2.get(a).getObjA13()==null?0:list_obj2.get(a).getObjA13());
			sheet.getRow(temp2+temp1_1+3).getCell(3+a).setCellValue(list_obj2.get(a).getObjA14()==null?0:list_obj2.get(a).getObjA14());
			sheet.getRow(temp2+temp1_1+4).getCell(3+a).setCellValue(list_obj2.get(a).getObjA15()==null?0:list_obj2.get(a).getObjA15());
			sheet.getRow(temp2+temp1_1+5).getCell(3+a).setCellValue(list_obj2.get(a).getObjA16()==null?0:list_obj2.get(a).getObjA16());
			for(int b=0;b<list_items2.size()+1;b++){
				if(b==0){
					sheet.getRow(temp2+temp1_1+b).getCell(3+a).setCellStyle(cs_head2);
				}else{
					sheet.getRow(temp2+temp1_1+b).getCell(3+a).setCellStyle(cs);
				}
				
			}
		}
		
	}

public void init_more2_b(XSSFSheet sheet,Map<String,Object>map,Map<String,Object>map_style,String data) throws IOException{				
	this.init(sheet,map);
	List<String>list_items=this.findItems3();
	//樣式
	XSSFCellStyle cs_title=(XSSFCellStyle)map_style.get("cs_title");
	XSSFCellStyle cs_head=(XSSFCellStyle)map_style.get("cs_head");
	XSSFCellStyle cs_head2=(XSSFCellStyle)map_style.get("cs_head2");
	XSSFCellStyle cs=(XSSFCellStyle)map_style.get("cs");//		
	//標題
	CellRangeAddress cra_title=new CellRangeAddress(0,(short)0,0,(short)5);
	sheet.addMergedRegion(cra_title);				
	sheet.getRow(0).getCell(0).setCellValue("各廠訊息匯總_"+data);
	
	for(int i=0;i<4;i++){
		sheet.getRow(0).getCell(i).setCellStyle(cs_title);
	}
	
	CellRangeAddress cra=new CellRangeAddress(1,(short)1,0,(short)1);
	sheet.addMergedRegion(cra);
	sheet.getRow(1).getCell(0).setCellValue("廠別狀態");
	for(int i=0;i<2;i++){
		sheet.getRow(1).getCell(i).setCellStyle(cs_head2);
	}
	
	List<String>list_head=new ArrayList<String>();
	list_head.add("項次");
	list_head.add("項目");						
	for(int i=0;i<list_head.size();i++){
		sheet.getRow(2).getCell(i).setCellValue(list_head.get(i));
		sheet.getRow(2).getCell(i).setCellStyle(cs_head);
	}
			
	for(int i=0;i<list_items.size();i++){
		sheet.getRow(3+i).getCell(0).setCellValue(i+1);
		sheet.getRow(3+i).getCell(1).setCellValue(list_items.get(i).split("__")[0]);//項目
		for(int j=0;j<2;j++){
			sheet.getRow(3+i).getCell(j).setCellStyle(cs);
		}
	}
	
	int temp=0;
	int temp1_1=0;
	for(String factcode:map.keySet()){
		List<WebObjsA>list_obj=(List<WebObjsA>)map.get(factcode);
		
		/***********************************MD  EVA  DJ  換行**********************************/
		if("MD".equals(factcode)||"EVA".equals(factcode)||"DJ".equals(factcode)){
			temp=0;
			temp1_1=temp1_1+list_items.size()+2;
			CellRangeAddress c=new CellRangeAddress(1+temp1_1,1+temp1_1,0,(short)1);
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
				for(int j=0;j<2;j++){
					sheet.getRow(3+i+temp1_1).getCell(j).setCellStyle(cs);
				}
			}
			
			
		}
		/***********************************MD  EVA  DJ  換行**********************************/
		
		int length=list_obj.size();			
		sheet.getRow(1+temp1_1).getCell(2+temp).setCellValue(factcode);
		CellRangeAddress cra1=new CellRangeAddress(1+temp1_1,(short)1+temp1_1,2+temp,(short)1+temp+length);
		sheet.addMergedRegion(cra1);
		for(int i=0;i<length;i++){
			sheet.getRow(1+temp1_1).getCell(2+temp+i).setCellStyle(cs_head2);
		}
		
		List<List<String>>list_pack=this.packageTostring3(list_obj);			
		for(int a=0;a<list_pack.size();a++){
			List<String>list=list_pack.get(a);
			for(int b=0;b<list.size();b++){
				sheet.getRow(2+b+temp1_1).getCell(2+a+temp).setCellValue(this.isMyNull_str(list.get(b)));										
				if(b==0){
					sheet.getRow(2+b+temp1_1).getCell(2+a+temp).setCellStyle(cs_head);
				}else{
					sheet.getRow(2+b+temp1_1).getCell(2+a+temp).setCellStyle(cs);
				}
			}
		}
		temp=temp+length;
	}					
}
	
	public void init2(XSSFSheet sheet,Map<String,Object>map){
		sheet.setColumnWidth(1,4500);		
		for(int i=0;i<NUM+10;i++){
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
	public List<List<String>> packageTostring2(List<WebObjsA>list_obj,List<String>list_items){
		List<List<String>>result=new ArrayList<List<String>>();
		for(int i=0;i<list_obj.size();i++){
			WebObjsA obj=list_obj.get(i);
			List<String>list=new ArrayList<String>();			
			if(obj.getId().getWebFact().getFactSname()!=null){
				list.add(obj.getId().getWebFact().getFactSname());
			}else{
				list.add(obj.getId().getWebFact().getId().getFactNo().split("_")[2]);
			}								
			List<String>list_str=this.isPercents2(obj,list_items);
			for(String str:list_str){
				list.add(str);
			}			
			result.add(list);								
		}
		return result;
		
	}
	
	public List<List<String>> packageTostring3(List<WebObjsA>list_obj){
		List<List<String>>result=new ArrayList<List<String>>();
		for(int i=0;i<list_obj.size();i++){
			WebObjsA obj=list_obj.get(i);
			List<String>list=new ArrayList<String>();			
			list.add(obj.getId().getWebFact().getFactSname());
			list.add(obj.getObjA17());
			list.add(obj.getObjA18());
			list.add(obj.getObjA19());
			list.add(obj.getObjA20());
			list.add(obj.getObjA21());
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
	public List<String> isPercents2(WebObjsA obj,List<String> list_items){
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
		list.add(obj.getObjA9());
		list.add(obj.getObjA10());
		list.add(obj.getObjA11());		

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
	
	/***************************************各廠日報表彙總************************************************************/
	
	
	
	
	
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
	
	/**
	 * 判斷是否無數據
	 * @return
	 */
	public String isMyNull_str(String str){		
		if(str==null){					
			str="無";
		}
		return str;
	}
	public Double isMyNull_db(Double d){
		if(d==null){
			d=0.0;
		}
		return d;
	}
	public Long isMyNull_ll(Long ll){
		if(ll==null){
			ll=0l;
		}
		return ll;
	}
	
	public List<String>findItems(){
		List<String>list=new ArrayList<String>();										
		list.add("廠內孔位數__孔");
		list.add("最大戰力員工數__人");
		list.add("最大生產數__雙");
		list.add("有效孔位數__孔");
		list.add("上模數__模");
		list.add("回轉數__回");
		list.add("生產模數__模");
		list.add("目前欠數__雙");
		list.add("慢單狀況__張");
		list.add("不良雙數__雙");
		list.add("不良率__%");								
		return list;		
	}
	
	public List<String>findItems2(){
		List<String>list=new ArrayList<String>();										
		list.add("目前直工人數__人");
		list.add("全廠員工數__人");
		list.add("招工數__人");
		list.add("離職數__人");
		list.add("請假數__人");										
		return list;		
	}
	
	public List<String> findItems3(){
		List<String>list=new ArrayList<String>();			
		list.add("品質問題與客訴__文字");
		list.add("扣款訊息__文字");
		list.add("機台狀況/異常__文字");
		list.add("客人來訪訊息__文字");
		list.add("其他提報事項__文字");	
		return list;
	}
	
	public String formatDouble(double s) {
		DecimalFormat format = new DecimalFormat(",###");
		String temp = format.format(s);
		return temp;
	}
	
	public String formatDouble2(double s) {
		DecimalFormat format = new DecimalFormat(",##0.00");
		String temp = format.format(s);
		return temp;
	}
	
	public HSSFCellStyle selectStyle2003(int a,Map<String,Object>map_style){
		HSSFCellStyle cs;//  cs_poi
		
		if(a<4||a==7||a==8){
			cs=(HSSFCellStyle)map_style.get("cs_poi");
		}else if(a==10){
			cs=(HSSFCellStyle)map_style.get("cs_percent");
		}else{
			cs=(HSSFCellStyle)map_style.get("cs_poi1");
		}		
		return cs;
	}
	

}
