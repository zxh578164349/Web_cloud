/**
 * 
 */
package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyzExpectmatmLog;
import entity.VWebFact;
import entity.VWeballobjasumwebyield;
import entity.VWeballobjasumwebyield2019;
import entity.VWeballobjasumwebyield2019Id;
import entity.VWeballobjasumwebyieldId;
import entity.VWeballobjbStorage;
import entity.VWeballobjbStorageId;
import entity.WebFact;
import entity.WebFactId;
import entity.WebUser;
import entity.WeballobjB;
import entity.WeballobjBId;

import services.IWebFactServices;
import services.IWeballobjBServices;
import util.GlobalMethod;
import util.ImportExcel;
import util.JasperHelper;
import util.PageBean;

/**
 * 
* 項目名稱：WebLogin   
* 類名稱：WeballobjBAction   
* 類描述：生產與請款差異匯總表    各廠廢品重量匯總    重點指標導入
* 創建人：KY2
 */
public class WeballobjBAction  extends ActionSupport implements ServletResponseAware{
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
	private IWeballobjBServices weballobjbser;
	private javax.servlet.http.HttpServletResponse response;
	private IWebFactServices webFactSer;
	private String a_type;
	private final static String FC = "MD+IP+EVA";

	
	
	public String getA_type() {
		return a_type;
	}

	public void setA_type(String a_type) {
		this.a_type = a_type;
	}

	public String getFactCode() {
		return factCode;
	}

	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}

	public PageBean getBean() {
		return bean;
	}

	public void setBean(PageBean bean) {
		this.bean = bean;
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

	public String getFactNo() {
		return factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public String getYymm() {
		return yymm;
	}

	public void setYymm(String yymm) {
		this.yymm = yymm;
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
	
	public void setWeballobjbser(IWeballobjBServices weballobjbser) {
		this.weballobjbser = weballobjbser;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
			
	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}

	/**
	 * 數據導入
	 * @throws IOException
	 */
	public void addMore() throws IOException{
		response.setContentType("text/html;charset=utf-8");
		try{
			String path="d:\\Weballobj_b_backup\\"+new SimpleDateFormat("yyyyMMdd").format(new Date());//Excel文檔存放目錄
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
				List<WeballobjB>list_b=new ArrayList<WeballobjB>();
				List<String>list_factcode=new ArrayList<String>();//導入數據所有的factcode
				List<String>list=(List<String>)map.get(key);
				if(!list.get(0).contains("__序號__項目__單位")){				
					response.getWriter().print("<script>window.parent.showDiv();window.parent.layer.msg('表格式不符合要求')</script>");
					//continue;
					break;
				}
				
				for(int a=0;a<list.size();a++){
					if(list.size()>43||list.get(a).contains("平均日產能")||list.get(a).contains("庫存天數")){
						/*list.remove(a);
						a=0;*/
						response.getWriter().print("<script>window.parent.layer.msg('項目不正確')</script>");
						break a;
					}
					
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
					WeballobjB obj=new WeballobjB(new WeballobjBId(fact,yymm));								
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
					obj.setObjA12(Double.valueOf(list.get(12).split(SEPARATOR)[i]));
					obj.setObjA13(Double.valueOf(list.get(13).split(SEPARATOR)[i]));
					obj.setObjA14(Double.valueOf(list.get(14).split(SEPARATOR)[i]));
					obj.setObjA15(Double.valueOf(list.get(15).split(SEPARATOR)[i]));
					obj.setObjA16(Double.valueOf(list.get(16).split(SEPARATOR)[i]));
					obj.setObjA17(Double.valueOf(list.get(17).split(SEPARATOR)[i]));
					obj.setObjA18(Double.valueOf(list.get(18).split(SEPARATOR)[i]));
					obj.setObjA19(Double.valueOf(list.get(19).split(SEPARATOR)[i]));
					obj.setObjA20(Double.valueOf(list.get(20).split(SEPARATOR)[i]));
					obj.setObjA21(Double.valueOf(list.get(21).split(SEPARATOR)[i]));
					obj.setObjA22(Double.valueOf(list.get(22).split(SEPARATOR)[i]));
					obj.setObjA23(Double.valueOf(list.get(23).split(SEPARATOR)[i]));
					obj.setObjA24(Double.valueOf(list.get(24).split(SEPARATOR)[i]));
					obj.setObjA25(Double.valueOf(list.get(25).split(SEPARATOR)[i]));
					obj.setObjA26(Double.valueOf(list.get(26).split(SEPARATOR)[i]));
					obj.setObjA27(Double.valueOf(list.get(27).split(SEPARATOR)[i]));
					obj.setObjA28(Double.valueOf(list.get(28).split(SEPARATOR)[i]));
					obj.setObjA29(Double.valueOf(list.get(29).split(SEPARATOR)[i]));
					obj.setObjA30(Double.valueOf(list.get(30).split(SEPARATOR)[i]));
					obj.setObjA31(Double.valueOf(list.get(31).split(SEPARATOR)[i]));
					obj.setObjA32(Double.valueOf(list.get(32).split(SEPARATOR)[i]));
					obj.setObjA33(Double.valueOf(list.get(33).split(SEPARATOR)[i]));
					obj.setObjA34(Double.valueOf(list.get(34).split(SEPARATOR)[i]));
					obj.setObjA35(Double.valueOf(list.get(35).split(SEPARATOR)[i]));
					obj.setObjA36(Double.valueOf(list.get(36).split(SEPARATOR)[i]));
					obj.setObjA37(Double.valueOf(list.get(37).split(SEPARATOR)[i]));
					//obj.setObjA38(Double.valueOf(list.get(38).split(SEPARATOR)[i]));38項與4項重覆了
					obj.setObjA39(Double.valueOf(list.get(38).split(SEPARATOR)[i]));
					obj.setObjA40(Double.valueOf(list.get(39).split(SEPARATOR)[i]));
					obj.setObjA41(Double.valueOf(list.get(40).split(SEPARATOR)[i]));
					obj.setObjA42(Double.valueOf(list.get(41).split(SEPARATOR)[i]));
					obj.setObjA43(Double.valueOf(list.get(42).split(SEPARATOR)[i]));																											
					obj.setUsername(user.getUsername());
					obj.setCreateDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
					list_b.add(obj);
				}//for b
				weballobjbser.addMore(list_b);
				response.getWriter().print("<script>window.parent.layer.msg('導入成功',3,1)</script>");
			}//for a
						
		}catch(Exception e){
			System.out.println(e);
			response.getWriter().print("<script>window.parent.layer.msg('導入錯誤',3,3);</script>");
		}
		
	}
	
	/**
	 * 分頁查詢
	 * @return
	 */
	public String findPageBean(){
		ActionContext.getContext().getSession().remove("allrow");//dao層
		ActionContext.getContext().getSession().remove("public_yymm");
		ActionContext.getContext().getSession().remove("public_yymm2");
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		ActionContext.getContext().getSession().put("public_factno", factNo);
		bean=weballobjbser.findPageBean(20,page, factNo, yymm, yymm2);
		return "beanList";
		
	}
	
	/**
	 * 分頁查詢2
	 * @return
	 */
	public String findPageBean2(){
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		ActionContext.getContext().getSession().remove("allrow");//dao層
		ActionContext.getContext().getSession().put("public_factno", factNo);
		ActionContext.getContext().getSession().put("public_yymm", yymm);
		ActionContext.getContext().getSession().put("public_yymm2", yymm2);		
		bean=weballobjbser.findPageBean(20,page, factNo, yymm, yymm2);
		return "beanList1";
	}
	
	/**
	 * 分頁查詢3
	 * @return
	 */
	public String findPageBean3(){
		factNo=(String)ActionContext.getContext().getSession().get("public_factno");
		yymm=(String)ActionContext.getContext().getSession().get("public_yymm");
		yymm2=(String)ActionContext.getContext().getSession().get("public_yymm2");
		bean=weballobjbser.findPageBean(20,page, factNo, yymm, yymm2);
		return "beanList1";
	}
	
	/**
	 * 刪除
	 * @return
	 */
	public String delete(){
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setFactNo(factNo);
		log.setFactCode(factCode);
		log.setObj("WeballobjB");
		log.setYymm(yymm);
		weballobjbser.delete(factNo, factCode, yymm,log);
		return "delete";
	}
	

	/**
	 * 導出單個工廠數據
	 * @throws ParseException
	 * @throws IOException
	 */
	public void print() throws ParseException, IOException{
		HSSFWorkbook wb=new HSSFWorkbook();
		Map<String,Object>map_cs=findStyles(wb);
		//HSSFCellStyle cs=(HSSFCellStyle)map_cs.get("cs");				
		List<String>list_months=GlobalMethod.findMonths(yymm, yymm2);
		List<String>list_col=findLeftCol();
		List<WeballobjB>list_objs=weballobjbser.findAllobj(factNo, yymm, yymm2);		
		switch(list_objs.size()){//switch		
		case 0:
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>window.parent.alert('無數據');</script>");			
			break;
		default:
			Map<String,Object>map=new LinkedHashMap<String,Object>();		
			for(String month:list_months){//for
				List<WeballobjB>list=new ArrayList<WeballobjB>();
				for(WeballobjB obj:list_objs){
					if(month.equals(obj.getId().getYymm())){
						list.add(obj);
					}				
				}			
				map.put(month, list);					
			}//for
			
			
			Map<String,Object>map_fcode=new LinkedHashMap<String,Object>();
			for(String month:map.keySet()){
				List<WeballobjB>list=(List<WeballobjB>)map.get(month);
				List<String>list_fcode=new ArrayList<String>();				
					for(WeballobjB obj:list){
						list_fcode.add(obj.getId().getFact().getId().getFactArea());
					}
					map_fcode.put(month,list_fcode);
			}
			
			Map<String,Object>map_all=new LinkedHashMap<String,Object>();
			for(String month:map.keySet()){
				List<List<Double>>list_a=new ArrayList<List<Double>>();		
				List<WeballobjB>list=(List<WeballobjB>)map.get(month);							
					for (WeballobjB obj : list) {
						List<Double> list_b =this.packageObj(obj);						
						list_a.add(list_b);
					}								
				map_all.put(month, list_a);
			}
			init(wb,map_cs,list_col,factNo,yymm,yymm2,list_months,map_fcode);	
			for(String month:map_all.keySet()){
				HSSFSheet sheet=wb.getSheet(month);
				List<List<Double>>list_a=(List<List<Double>>)map_all.get(month);
				List<String>list_fcode=(List<String>)map_fcode.get(month);
				if(list_a.size()>0){
					for(int a=0;a<list_a.size();a++){
						List<Double>list_b=list_a.get(a);
						for(int b=0;b<list_b.size();b++){
							if(list_b.get(b)==null){
								sheet.getRow(3+b).getCell(3+a).setCellValue("0.0");	
							}else{
								sheet.getRow(3+b).getCell(3+a).setCellValue(list_b.get(b));
								sheet.setColumnWidth(3+a,4000);
							}							
							sheet.getRow(3+b).getCell(3+a).setCellStyle(this.findStyleByIndex(map_cs, b,list_col));
						}
					}
				}						
			}
			try {
				/*OutputStream os = new FileOutputStream("E:/" + "websort.xls");
				wb.write(os);
				os.close();	*/
				ServletOutputStream os=response.getOutputStream();
				response.setContentType("application/vnd.ms-excel");
				int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");//判斷是否為IE瀏覽器,大於0則為IE瀏覽器
				String fileName="report"+yymm+"-"+yymm2+".xls";
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
	
	/**
	 * 導出所有工廠數據
	 * @throws ParseException
	 * @throws IOException
	 */
	public void print_all() throws ParseException, IOException{
		factNo="all";
		HSSFWorkbook wb=new HSSFWorkbook();
		Map<String,Object>map_cs=findStyles(wb);
		List<Object[]>list_facts=(List<Object[]>)ActionContext.getContext().getSession().get("facts");//所有廠別	
		List<String>list_months=GlobalMethod.findMonths(yymm, yymm2);
		List<String>list_col=findLeftCol();
		List<WeballobjB>list_objs=weballobjbser.findAllobj(factNo, yymm, yymm2);		
		switch(list_objs.size()){//switch		
		case 0:
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>window.parent.alert('無數據');</script>");			
			break;
		default:
			Map<String,Object>map=new LinkedHashMap<String,Object>();				
			for(String month:list_months){	
				List<WeballobjB>list=new ArrayList<WeballobjB>();
				for(WeballobjB obj:list_objs){
					if(month.equals(obj.getId().getYymm())){
						list.add(obj);
					}				
				}
				Map<String,Object>map2=new LinkedHashMap<String,Object>();
				for(Object[] fact:list_facts){//for
					List<WeballobjB>list_obj=new ArrayList<WeballobjB>();
					for(WeballobjB obj:list){
						if(fact[0].equals(obj.getId().getFact().getId().getFactNo())){
							list_obj.add(obj);
						}
					}
					map2.put(fact[1].toString(),list_obj);
				}
				map.put(month, map2);	
			}//for
			
			
			Map<String,Object>map_fcode=new LinkedHashMap<String,Object>();			
			for(String month:map.keySet()){
				Map<String,Object>map_fcode2=new LinkedHashMap<String,Object>();
				Map<String,Object>map2=(Map<String,Object>)map.get(month);
				for(String factno:map2.keySet()){
					List<WeballobjB>list=(List<WeballobjB>)map2.get(factno);
					List<String>list_fcode=new ArrayList<String>();				
						for(WeballobjB obj:list){
							list_fcode.add(obj.getId().getFact().getId().getFactArea());
						}
						map_fcode2.put(factno,list_fcode);
				}
				map_fcode.put(month,map_fcode2);
			}
			
			
			Map<String,Object>map_all=new LinkedHashMap<String,Object>();
			
			for(String month:map.keySet()){
				Map<String,Object>map_all2=new LinkedHashMap<String,Object>();
				Map<String,Object>map2=(Map<String,Object>)map.get(month);
				for(String factno:map2.keySet()){
					List<List<Double>>list_a=new ArrayList<List<Double>>();		
					List<WeballobjB>list=(List<WeballobjB>)map2.get(factno);
					List<Double> list_b=null;
					if(list!=null&&list.size()!=0){
						for (WeballobjB obj : list) {
							list_b =this.packageObj(obj);						
							list_a.add(list_b);
						}
					}														
					map_all2.put(factno, list_a);
				}
				map_all.put(month,map_all2);
			}
			
			init_all(wb,map_cs,list_col,factNo,list_months,map_fcode,map_all);
												
			try {
				/*OutputStream os = new FileOutputStream("E:/" + "websort.xls");
				wb.write(os);
				os.close();	*/
				ServletOutputStream os=response.getOutputStream();
				response.setContentType("application/vnd.ms-excel");
				int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");//判斷是否為IE瀏覽器,大於0則為IE瀏覽器
				String fileName="report"+yymm+"-"+yymm2+".xls";
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
	
	
	/**
	 * 各廠別狀態的月份統計
	 * @Title: print2
	 * @Description: TODO
	 * @param @throws ParseException
	 * @param @throws IOException
	 * @return void
	 * @throws
	 * @author web
	 * @date 2017/1/17
	 */
	public void print2() throws ParseException, IOException{
		HSSFWorkbook wb=new HSSFWorkbook();
		Map<String,Object>map_cs=findStyles(wb);						
		List<String>list_months=GlobalMethod.findMonths(yymm, yymm2);
		List<String>list_col=findLeftCol();
		List<WeballobjB>list_objs=weballobjbser.findAllobj(factNo, yymm, yymm2);
		List<String>list_fcodes=weballobjbser.findFactCodes(factNo,yymm,yymm2);
		
		Map<String,Object>map=new LinkedHashMap<String,Object>();
		for(String fcode:list_fcodes){
			List<WeballobjB>list_obj=new ArrayList<WeballobjB>();
			for(String month:list_months){
				WeballobjB obj=new WeballobjB(new WeballobjBId(new WebFact(new WebFactId(factNo,fcode)),month));
				list_obj.add(obj);				
			}
			for(int i=0;i<list_obj.size();i++){
				WeballobjB obj=list_obj.get(i);
				for(WeballobjB obj2:list_objs){
					if(obj.getId().getFact().getId().getFactNo().equals(obj2.getId().getFact().getId().getFactNo())&&
						obj.getId().getFact().getId().getFactArea().equals(obj2.getId().getFact().getId().getFactArea())&&
						obj.getId().getYymm().equals(obj2.getId().getYymm())){
						list_obj.remove(i);
						list_obj.add(i,obj2);
					}
				}
			}
			map.put(fcode,list_obj);				
		}
		
		
		
		switch(list_objs.size()){//switch		
		case 0:
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>window.parent.alert('無數據');</script>");			
			break;
		default:																	
			Map<String,Object>map_all=new LinkedHashMap<String,Object>();
			for(String fcode:map.keySet()){
				List<List<Double>>list_a=new ArrayList<List<Double>>();		
				List<WeballobjB>list=(List<WeballobjB>)map.get(fcode);							
					for (WeballobjB obj : list) {
						List<Double> list_b =this.packageObj(obj);						
						list_a.add(list_b);
					}								
				map_all.put(fcode, list_a);
			}
			init2(wb,map_cs,list_col,factNo,yymm,yymm2,list_months,list_fcodes);
			
			for(String fcode:map_all.keySet()){
				HSSFSheet sheet=wb.getSheet(fcode);
				List<List<Double>>list_a=(List<List<Double>>)map_all.get(fcode);
				if(list_a.size()>0){
					for(int a=0;a<list_a.size();a++){
						List<Double>list_b=list_a.get(a);
						for(int b=0;b<list_b.size();b++){
							if(list_b.get(b)==null){
								sheet.getRow(3+b).getCell(3+a).setCellValue("0.0");	//利潤率有可能爲null,所以要判斷  obj.getObjA203()      20160916
							}else{
								sheet.getRow(3+b).getCell(3+a).setCellValue(list_b.get(b));
								sheet.setColumnWidth(3+a,4000);
							}														
							sheet.getRow(3+b).getCell(3+a).setCellStyle(this.findStyleByIndex(map_cs, b,list_col));
						}
						/*****************統計******************/
						if(a==list_a.size()-1){
							List<Double>list_total=this.package_total(list_a,list_col);
							for(int i=0;i<list_total.size();i++){
								sheet.getRow(3+i).getCell(4+a).setCellValue(list_total.get(i));
								sheet.getRow(3+i).getCell(4+a).setCellStyle(this.findStyleByIndex(map_cs, i,list_col));
								sheet.setColumnWidth(4+a,4000);
							}
						}
						/*****************統計******************/
					}
					
					
				}
				
				
			}
			try {				
				ServletOutputStream os=response.getOutputStream();
				response.setContentType("application/vnd.ms-excel");
				int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");//判斷是否為IE瀏覽器,大於0則為IE瀏覽器
				String fileName="report_total"+yymm+"-"+yymm2+".xls";
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
	
	
	
	
	public void init(HSSFWorkbook wb,Map<String,Object>map_cs,List<String>list_col,String factNo,String yymm,String yymm2,List<String>list_months,Map<String,Object>map_fcode) throws ParseException{
		HSSFCellStyle cs=(HSSFCellStyle)map_cs.get("cs");
		HSSFCellStyle cs_head=(HSSFCellStyle)map_cs.get("cs_head");
		HSSFCellStyle cs_title=(HSSFCellStyle)map_cs.get("cs_title");			
		for(String month:list_months){//for
			HSSFSheet sheet=wb.createSheet(month);
			List<String>list_fcode=(List<String>)map_fcode.get(month);			
			if(list_fcode.size()>0){				
				sheet.setColumnWidth(1, 5000);
				for(int a=0;a<2+list_col.size();a++){
					sheet.createRow(a);				
					for(int b=0;b<10;b++){
						sheet.getRow(a).createCell(b);					
					}
				}
				
				CellRangeAddress cra_title=new CellRangeAddress(0,0,0,4);
				sheet.addMergedRegion(cra_title);
				sheet.getRow(0).getCell(0).setCellValue(factNo+"-"+month+"重點指標數據");
				for(int i=0;i<6;i++){
					sheet.getRow(0).getCell(i).setCellStyle(cs_title);
				}
				for(int a=0;a<2+list_col.size();a++){
					if(a<list_col.size()){
					for(int b=0;b<list_col.get(0).split("__").length;b++){					
							sheet.getRow(a+2).getCell(b).setCellValue(list_col.get(a).split("__")[b]);
							if(a==0){
								sheet.getRow(a+2).getCell(b).setCellStyle(cs_head);
								if(b==list_col.get(0).split("__").length-1){
									for(int c=0;c<list_fcode.size();c++){
										sheet.getRow(a+2).getCell(b+1+c).setCellValue(list_fcode.get(c));
										sheet.getRow(a+2).getCell(b+1+c).setCellStyle(cs_head);
									}
								}
							}else{
								sheet.getRow(a+2).getCell(b).setCellStyle(cs);
							}						
						}
					}
				}
			}else{
				sheet.setColumnWidth(0,10000);
				sheet.createRow(0).createCell(0);
				sheet.getRow(0).getCell(0).setCellValue("當前月份無數據");
				sheet.getRow(0).getCell(0).setCellStyle(cs_head);
			}
			
		}//for
	}
	
	public void init_all(HSSFWorkbook wb,Map<String,Object>map_cs,List<String>list_col,String factNo,List<String>list_months,Map<String,Object>map_fcode,Map<String,Object>map_all) throws ParseException{
		HSSFCellStyle cs=(HSSFCellStyle)map_cs.get("cs");
		HSSFCellStyle cs_head=(HSSFCellStyle)map_cs.get("cs_head");
		HSSFCellStyle cs_title=(HSSFCellStyle)map_cs.get("cs_title");
		
					
		for(String month:map_fcode.keySet()){//for
			HSSFSheet sheet=wb.createSheet(month);
			Map<String,Object>map_fcode2=(Map<String,Object>)map_fcode.get(month);
			Map<String,Object>map_all2=(Map<String,Object>)map_all.get(month);
			int index_x=0;
			
			for(int a=0;a<=list_col.size()+4;a++){
				sheet.createRow(a);					
			}
			
			/*********************************標題***************************************/
			sheet.setColumnWidth(1, 5000);
			CellRangeAddress cra_title=new CellRangeAddress(0,0,0,4);
			sheet.addMergedRegion(cra_title);
			for(int i=0;i<6;i++){
				sheet.getRow(0).createCell(i).setCellStyle(cs_title);
			}
			sheet.getRow(0).getCell(0).setCellValue(month+"-"+"各工廠重點指標數據");
			/*********************************標題***************************************/
			
			int leg_col=list_col.get(0).split("__").length;
			for(int a=0;a<list_col.size();a++){
				for(int b=0;b<leg_col;b++){
					sheet.getRow(a+4).createCell(b).setCellValue(list_col.get(a).split("__")[b]);
					if(a==0){
						sheet.getRow(a+4).getCell(b).setCellStyle(cs_head);
					}else{
						sheet.getRow(a+4).getCell(b).setCellStyle(cs);
					}
				}
			}
			index_x=index_x+leg_col;//...................001
									
			for(String factno:map_fcode2.keySet()){//for2				
				List<String>list_fcode=(List<String>)map_fcode2.get(factno);
				
				if(list_fcode.size()>0){
					for(int a=0;a<list_col.size();a++){										
						for(int b=0;b<list_fcode.size();b++){
							sheet.getRow(a+4).createCell(b+index_x);
							if(a==0){
								sheet.getRow(a+4).getCell(b+index_x).setCellValue(factno+"_"+list_fcode.get(b));
								sheet.getRow(a+4).getCell(b+index_x).setCellStyle(cs_head);
								sheet.setColumnWidth(b+index_x,6000);
							}else{
								sheet.getRow(a+4).getCell(b+index_x).setCellStyle(cs);
							}							
						}
					}																		
					List<List<Double>>list_a=(List<List<Double>>)map_all2.get(factno);
					if(list_a.size()>0){
						for(int a=0;a<list_a.size();a++){
							List<Double>list_b=list_a.get(a);
							for(int b=0;b<list_b.size();b++){
								if(list_b.get(b)==null){
									sheet.getRow(b+5).getCell(a+index_x).setCellValue("0.0");
								}else{
									sheet.getRow(b+5).getCell(a+index_x).setCellValue(list_b.get(b));
								}
								sheet.getRow(b+5).getCell(a+index_x).setCellStyle(this.findStyleByIndex(map_cs, b,list_col));
							}
						}												
					}																										
					index_x=index_x+list_fcode.size();//...............002
				}else{											
					for(int a=0;a<list_col.size();a++){										
						for(int b=0;b<1;b++){
							sheet.getRow(a+4).createCell(b+index_x);
							if(a==0){
								sheet.getRow(a+4).getCell(b+index_x).setCellValue(factno+"(無數據)");
								sheet.getRow(a+4).getCell(b+index_x).setCellStyle(cs_head);
								sheet.setColumnWidth(b+index_x,6000);
							}else{
								sheet.getRow(a+4).getCell(b+index_x).setCellStyle(cs);
							}							
						}						
					}
					index_x=index_x+1;//.......................003
				}								
			}//for2	
			
			/*********************************合計***************************************/
			List<Double>list_total=this.package_total2(map_all2,list_col);
			sheet.getRow(4).createCell(index_x).setCellValue("合計");
			sheet.getRow(4).getCell(index_x).setCellStyle(cs_head);	
			sheet.setColumnWidth(index_x,6000);
			for(int a=0;a<list_total.size();a++){
				sheet.getRow(a+5).createCell(index_x).setCellValue(list_total.get(a));
				sheet.getRow(a+5).getCell(index_x).setCellStyle(this.findStyleByIndex(map_cs,a,list_col));
			}
			/*********************************合計***************************************/
		}//for
	}
	
				
	public void init2(HSSFWorkbook wb,Map<String,Object>map_cs,List<String>list_col,String factNo,String yymm,String yymm2,List<String>list_months,List<String>list_fcode) throws ParseException{
		HSSFCellStyle cs=(HSSFCellStyle)map_cs.get("cs");
		HSSFCellStyle cs_head=(HSSFCellStyle)map_cs.get("cs_head");
		HSSFCellStyle cs_title=(HSSFCellStyle)map_cs.get("cs_title");			
		for(String fcode:list_fcode){//for
			HSSFSheet sheet=wb.createSheet(fcode);						
				sheet.setColumnWidth(1, 5000);
				for(int a=0;a<2+list_col.size();a++){
					sheet.createRow(a);				
					for(int b=0;b<list_months.size()+6;b++){
						sheet.getRow(a).createCell(b);					
					}
				}
				
				CellRangeAddress cra_title=new CellRangeAddress(0,0,0,4);
				sheet.addMergedRegion(cra_title);
				sheet.getRow(0).getCell(0).setCellValue(factNo+"--"+fcode+"重點指標數據(統計)");
				for(int i=0;i<6;i++){
					sheet.getRow(0).getCell(i).setCellStyle(cs_title);
				}
				for(int a=0;a<2+list_col.size();a++){
					if(a<list_col.size()){
					for(int b=0;b<list_col.get(0).split("__").length;b++){					
							sheet.getRow(a+2).getCell(b).setCellValue(list_col.get(a).split("__")[b]);
							if(a==0){
								sheet.getRow(a+2).getCell(b).setCellStyle(cs_head);
								if(b==list_col.get(0).split("__").length-1){
									for(int c=0;c<list_months.size();c++){
										sheet.getRow(a+2).getCell(b+1+c).setCellValue(list_months.get(c));
										sheet.getRow(a+2).getCell(b+1+c).setCellStyle(cs_head);
										
										/*****************統計******************/
										if(c==list_months.size()-1){
											sheet.getRow(a+2).getCell(b+2+c).setCellValue("統計");
											sheet.getRow(a+2).getCell(b+2+c).setCellStyle(cs_head);
										}
										/*****************統計******************/
									}
								}
							}else{
								sheet.getRow(a+2).getCell(b).setCellStyle(cs);
							}						
						}
					}
				}						
		}//for
	}
	
	
	/**
	 * 獲取表格新式2003
	 * @param wb
	 * @return
	 */
	public Map<String,Object> findStyles(HSSFWorkbook wb){		
		Map<String,Object>map=GlobalMethod.findStyles(wb);				
		return map;
	}
	
	/**
	 * 獲取表格新式2007
	 * @param wb
	 * @return
	 */
	public Map<String,Object>findStyles2007(XSSFWorkbook wb){
		Map<String,Object>map=GlobalMethod.findStyles2007(wb);
		return map;
	}

	
	public HSSFCellStyle findStyleByIndex(Map<String,Object>map,int index,List<String>list_col){
		HSSFCellStyle cs=(HSSFCellStyle)map.get("cs_poi2");
		HSSFCellStyle cs_percent=(HSSFCellStyle)map.get("cs_percent");				

		if("%".equals(list_col.get(index+1).split("__")[2])){
			cs=cs_percent;
		}								
		return cs;
		
	}
	
	public List<String> findLeftCol(){
		List<String>list=new ArrayList<String>();
		list.add("序號__項目__單位");		
		list.add("1__機臺孔位數__孔");
		list.add("2__機台利用率__%");
		list.add("3__生產數__模");
		list.add("4__請款雙數__雙");
		list.add("5__實際回轉數__回");
		list.add("6__直工__人");
		list.add("7__間工__人");
		list.add("8__全廠總人數__人");
		list.add("9__直間比__:");
		list.add("10__直工人均產能__模");
		list.add("11__全廠人均產能__模");
		list.add("12__時產能__雙");
		list.add("13__時迴轉__模/H");
		list.add("14__加班費__USD");
		list.add("15__成本率__%");
		list.add("16__回頭率__%");
		list.add("17__總損耗__%");
		list.add("18__平均邊料重__G/雙");
		list.add("19__邊料率__%");
		list.add("20__不良重量__KG");
		list.add("21__不良率__%");
		list.add("22__退貨率__%");
		list.add("23__成倉庫存__雙");
		list.add("24__用水單耗__USD/模");
		list.add("25__用電單耗__USD/模");
		list.add("26__蒸汽單耗__USD/模");
		list.add("27__蒸汽單耗__KG/模");
		list.add("28__色料藥品單耗__G/雙");
		list.add("29__色料藥品單耗__USD/雙");
		list.add("30__生產與請款差異__雙");
		list.add("31__工作天數__天");
		list.add("32__正批生產雙數__雙");
		list.add("33__廠補__雙");
		list.add("34__客補__雙");
		list.add("35__樣品__雙");
		list.add("36__前倉入庫折算后可請款數__雙");
		list.add("37__平均日產能__雙");
		list.add("38__庫存(成品倉、整理課)__雙");
		list.add("39__庫存天數__雙");
		//list.add("40__請款雙數(含正批、客補、樣品、托外)__雙");
		list.add("40__無形差异__雙");
		list.add("41__無形差異率__%");
		list.add("42__廢品重量__KG");
		list.add("43__當月耗用金額__USD");
		list.add("44__平均庫存金額__USD");				
		return list;
	}
	
	public List<Double>packageObj(WeballobjB obj){
		List<Double>list=new ArrayList<Double>();
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
		list.add(obj.getObjA12());
		list.add(obj.getObjA13());
		list.add(obj.getObjA14());
		list.add(obj.getObjA15());
		list.add(obj.getObjA16());
		list.add(obj.getObjA17());
		list.add(obj.getObjA18());
		list.add(obj.getObjA19());
		list.add(obj.getObjA20());
		list.add(obj.getObjA21());
		list.add(obj.getObjA22());
		list.add(obj.getObjA23());
		list.add(obj.getObjA24());
		list.add(obj.getObjA25());
		list.add(obj.getObjA26());
		list.add(obj.getObjA27());
		list.add(obj.getObjA28());
		list.add(obj.getObjA29());
		list.add(obj.getObjA30());
		list.add(obj.getObjA31());
		list.add(obj.getObjA32());
		list.add(obj.getObjA33());
		list.add(obj.getObjA34());
		list.add(obj.getObjA35());
		list.add(obj.getObjA36());
		double d=GlobalMethod.division(obj.getObjA36(),obj.getObjA31());//平均日產能(雙)
		list.add(d);
		list.add(obj.getObjA37());
		list.add(GlobalMethod.division(obj.getObjA37(),d));//庫存天數
		//list.add(obj.getObjA38());
		list.add(obj.getObjA39());
		list.add(obj.getObjA40());
		list.add(obj.getObjA41());
		list.add(obj.getObjA42());
		list.add(obj.getObjA43());
		return list;
		
	}
	
	

	public List<Double> package_total(List<List<Double>>list,List<String>list_cols){
		List<Double>list_result=new ArrayList<Double>();			
				for(int j=0;j<list_cols.size()-1;j++){
					double temp=0.0;
					for(int k=0;k<list.size();k++){
							temp=temp+(list.get(k).get(j)==null?0.0:list.get(k).get(j));																		
					}
					list_result.add(temp);
				}										
		return list_result;
	}
	
	public List<Double> package_total2(Map<String,Object>map,List<String>list_cols){
		List<Double>list_result=new ArrayList<Double>();					
		for (int j=0; j < list_cols.size() - 1; j++) {
			double temp=0.0;
			for (String factno : map.keySet()) {
				List<List<Double>> list=(List<List<Double>>)map.get(factno);				
				if (list != null && list.size() > 0) {
					for (int k=0; k < list.size(); k++) {
						temp=temp + (list.get(k).get(j) == null ? 0.0 : list.get(k).get(j));
					}
				}				
			}
			list_result.add(temp);
		}																
		return list_result;
	}	
	
	
	public String formatDouble(double s) {
		DecimalFormat format = new DecimalFormat(",###.0");
		String temp = format.format(s);
		return temp;
	}
	
	
	public void init_a(List<WeballobjB>list_objs) throws ParseException, IOException{
		
		/***************************************數據處理******************************************************/						
		List<String>months=GlobalMethod.findMonths(yymm+"01", yymm+"12");
		List<WebFact>list_facts=webFactSer.findFactAble();		
		List<WeballobjB>list_objs2=new ArrayList<WeballobjB>();
		for(WebFact obj:list_facts){
			for(String month:months){
				list_objs2.add(new WeballobjB(new WeballobjBId(obj,month)));
			}
		}		
		for(int a=0;a<list_objs2.size();a++){
			for(WeballobjB obj:list_objs){
				if(list_objs2.get(a).getId().getFact().getId().getFactNo().equals(obj.getId().getFact().getId().getFactNo())&&
						list_objs2.get(a).getId().getFact().getId().getFactArea().equals(obj.getId().getFact().getId().getFactArea())&&
						list_objs2.get(a).getId().getYymm().equals(obj.getId().getYymm())){
					list_objs2.set(a, obj);
					break;
				}
			}
		}		
		List<String>factnos=new ArrayList<String>();
		for(WeballobjB obj:list_objs2){
			factnos.add(obj.getId().getFact().getId().getFactNo()+"__"+obj.getId().getFact().getFactSname());
		}		
		for(int a=0;a<factnos.size();a++){
			for(int b=factnos.size()-1;b>a;b--){
				if(factnos.get(a).split("__")[0].equals(factnos.get(b).split("__")[0])){
					factnos.remove(b);
				}
			}
		}		
		Map<String,Object>map_temp=new LinkedHashMap<String,Object>();
		for(String str:factnos){
			List<String>l1=new ArrayList<String>();
			for(WebFact obj:list_facts){
				if(str.split("__")[0].equals(obj.getId().getFactNo())){
					l1.add(obj.getId().getFactArea());
				}
			}
			map_temp.put(str, l1);
		}		
		Map<String,Object>m1=new LinkedHashMap<String,Object>();//最終要循環的數據
		for(String key:map_temp.keySet()){
			List<String>l1=(List<String>)map_temp.get(key);
			Map<String,Object>m2=new LinkedHashMap<String,Object>();
			for(String str:l1){
				List<WeballobjB>list_obj=new ArrayList<WeballobjB>();
				for(WeballobjB obj:list_objs2){
					if(key.split("__")[0].equals(obj.getId().getFact().getId().getFactNo())&&str.equals(obj.getId().getFact().getId().getFactArea())){
						list_obj.add(obj);
					}
				}
				m2.put(str, list_obj);				
			}						
			m1.put(key, m2);
		}
		
		/***************************各廠別狀態數據統計***********************************/
		List<String>factcodes=new ArrayList<String>();
		for(WeballobjB obj:list_objs2){
			factcodes.add(obj.getId().getFact().getId().getFactArea());
		}
		//去除重覆factcode
		for(int a=0;a<factcodes.size();a++){
			for(int b=factcodes.size()-1;b>a;b--){
				if(factcodes.get(a).equals(factcodes.get(b))){
					factcodes.remove(b);
				}
			}
		}
		
		Map<String,Object>m3=new LinkedHashMap<String,Object>();
		for(String factcode:factcodes){
			List<Double>list=new ArrayList<Double>();
			for(String month:months){
				double d=0.0;
				for(WeballobjB obj:list_objs2){
					if(factcode.equals(obj.getId().getFact().getId().getFactArea())&&month.equals(obj.getId().getYymm())){
						if(obj.getObjA41()!=null){
							d=d+obj.getObjA41();
						}					   
					}					
				}
				list.add(d);
			}
			m3.put(factcode, list);
		}				
		/***************************各廠別狀態數據統計***********************************/
		
		
		List<String>list_cols=new ArrayList<String>();
		list_cols.add("廠別");
		list_cols.add("廠別狀態");
		list_cols.add("1月");
		list_cols.add("2月");
		list_cols.add("3月");
		list_cols.add("4月");
		list_cols.add("5月");
		list_cols.add("6月");
		list_cols.add("7月");
		list_cols.add("8月");
		list_cols.add("9月");
		list_cols.add("10月");
		list_cols.add("11月");
		list_cols.add("12月");
		list_cols.add("合計");
		list_cols.add("噸");
		list_cols.add("全廠合計");
		list_cols.add("平均報廢重量/月");
		list_cols.add("全廠平均合計/月");
		/***************************************數據處理******************************************************/
		
		
		XSSFWorkbook wb=new XSSFWorkbook();
		XSSFSheet sheet=wb.createSheet(yymm);
		Map<String,Object>map_style=GlobalMethod.findStyles2007(wb);
		XSSFCellStyle cs_title=(XSSFCellStyle)map_style.get("cs_title");
		XSSFCellStyle cs_head=(XSSFCellStyle)map_style.get("cs_head");
		XSSFCellStyle cs_poi1=(XSSFCellStyle)map_style.get("cs_poi1");
		XSSFCellStyle cs_poi1_bg=(XSSFCellStyle)map_style.get("cs_poi1_bg");
		XSSFCellStyle cs=(XSSFCellStyle)map_style.get("cs");
		
		for(int a=0;a<list_facts.size()+factcodes.size()+5;a++){
			sheet.createRow(a);
			for(int b=0;b<list_cols.size()+5;b++){
				if(b==0){
					sheet.setColumnWidth(b, 5000);
				}else{
					sheet.setColumnWidth(b, 4000);
				}
				sheet.getRow(a).createCell(b);
			}
		}
		
		//標題
		CellRangeAddress cra_title=new CellRangeAddress(0,0,0,5);
		sheet.addMergedRegion(cra_title);
		sheet.getRow(0).getCell(0).setCellValue(yymm+"各廠廢品重量匯總");		
		for(int a=0;a<4;a++){
			sheet.getRow(0).getCell(a).setCellStyle(cs_title);
		}
		
		//表頭
		for(int a=0;a<list_cols.size();a++){
			sheet.getRow(1).getCell(a).setCellValue(list_cols.get(a));
			sheet.getRow(1).getCell(a).setCellStyle(cs_head);
		}
		
		//數據填充
		int y_index=0;
		for(String key:m1.keySet()){
			Map<String,Object>m_a=(Map<String,Object>)m1.get(key);
			CellRangeAddress cra=new CellRangeAddress(2+y_index,2+m_a.keySet().size()-1+y_index,0,0);//廠別	
			CellRangeAddress cra2=new CellRangeAddress(2+y_index,2+m_a.keySet().size()-1+y_index,4+months.size(),4+months.size());//全廠合計
			CellRangeAddress cra3=new CellRangeAddress(2+y_index,2+m_a.keySet().size()-1+y_index,6+months.size(),6+months.size());//全廠平均合計
			sheet.addMergedRegion(cra);
			sheet.addMergedRegion(cra2);
			sheet.addMergedRegion(cra3);
			
			
			sheet.getRow(2+y_index).getCell(0).setCellValue(key.split("__")[1]);
			
			for(int a=0;a<m_a.keySet().size();a++){
				sheet.getRow(2+y_index+a).getCell(0).setCellStyle(cs);
				sheet.getRow(2+y_index+a).getCell(4+months.size()).setCellStyle(cs_poi1);
				sheet.getRow(2+y_index+a).getCell(6+months.size()).setCellStyle(cs_poi1);
			}

			double tt1=0.0;
			double dd1=0.0;
			int i=0;
			for(String factcode:m_a.keySet()){				
				sheet.getRow(2+y_index+i).getCell(1).setCellValue(factcode);
				sheet.getRow(2+y_index+i).getCell(1).setCellStyle(cs);
				List<WeballobjB>l_a=(List<WeballobjB>)m_a.get(factcode);
				double tt2=0.0;
				double j=0;//標記數據的個數
				for(int a=0;a<l_a.size();a++){
					sheet.getRow(2+y_index+i).getCell(2+a).setCellStyle(cs_poi1);	
					if(l_a.get(a).getObjA41()!=null){						
						sheet.getRow(2+y_index+i).getCell(2+a).setCellValue(l_a.get(a).getObjA41());//41項廢品重量
						tt2=tt2+l_a.get(a).getObjA41();
						j++;//當不為null時，才算入平均數
					}					
				}
				sheet.getRow(2+y_index+i).getCell(2+l_a.size()).setCellValue(tt2);//廠別狀態合計
				sheet.getRow(2+y_index+i).getCell(2+l_a.size()).setCellStyle(cs_poi1);
				
				sheet.getRow(2+y_index+i).getCell(3+l_a.size()).setCellValue(tt2/1000);//廠別狀態合計(噸)
				sheet.getRow(2+y_index+i).getCell(3+l_a.size()).setCellStyle(cs_poi1);
				
				sheet.getRow(2+y_index+i).getCell(5+l_a.size()).setCellValue(GlobalMethod.division(tt2/1000,j));//平均報廢重量/月
				sheet.getRow(2+y_index+i).getCell(5+l_a.size()).setCellStyle(cs_poi1);
				
				tt1=tt1+tt2;
				dd1=dd1+GlobalMethod.division(tt2/1000,j);
				i++;
			}									
			sheet.getRow(2+y_index).getCell(4+months.size()).setCellValue(tt1/1000);//全廠合計
			sheet.getRow(2+y_index).getCell(6+months.size()).setCellValue(dd1);//全廠平均合計/月
			y_index=y_index+m_a.keySet().size();																											
		}
		
		//表格最下面一行合計
		sheet.getRow(2+list_facts.size()).getCell(0).setCellValue("合計");
		for(int a=0;a<2;a++){
			sheet.getRow(2+list_facts.size()).getCell(a).setCellStyle(cs_poi1_bg);
		}
		for(int a=0;a<months.size()+5;a++){
			double result=0.0;
			for(int b=0;b<list_facts.size();b++){
				double d=sheet.getRow(2+b).getCell(2+a).getNumericCellValue();
				result=result+d;
			}
			sheet.getRow(2+list_facts.size()).getCell(2+a).setCellValue(result);
			sheet.getRow(2+list_facts.size()).getCell(2+a).setCellStyle(cs_poi1_bg);
		}
		
		/**********************************各廠別狀態合計****************************************/
		//表頭
		for(int a=0;a<list_cols.size();a++){
			if(a>1){
				sheet.getRow(3+list_facts.size()).getCell(a).setCellValue(list_cols.get(a));
			}			
			sheet.getRow(3+list_facts.size()).getCell(a).setCellStyle(cs_head);
		}
		
		int i=0;		
		for(String key:m3.keySet()){
			double j=0;
			sheet.getRow(4+list_facts.size()+i).getCell(0).setCellValue(key);
			for(int a=0;a<2;a++){
				sheet.getRow(4+list_facts.size()+i).getCell(a).setCellStyle(cs);
			}
			List<Double>list=(List<Double>)m3.get(key);
			double d=0.0;
			for(int a=0;a<list.size();a++){
				sheet.getRow(4+list_facts.size()+i).getCell(2+a).setCellValue(list.get(a));
				sheet.getRow(4+list_facts.size()+i).getCell(2+a).setCellStyle(cs_poi1);
				d=d+list.get(a);
				
				if(list.get(a)>0.0){
					j++;//當>0，才算入平均數
				}
			}
			sheet.getRow(4+list_facts.size()+i).getCell(2+list.size()).setCellValue(d);    //廠別狀態合計
			sheet.getRow(4+list_facts.size()+i).getCell(2+list.size()).setCellStyle(cs_poi1);
			
			sheet.getRow(4+list_facts.size()+i).getCell(3+list.size()).setCellValue(d/1000);//廠別狀態合計(噸)
			sheet.getRow(4+list_facts.size()+i).getCell(3+list.size()).setCellStyle(cs_poi1);
			
			sheet.getRow(4+list_facts.size()+i).getCell(4+list.size()).setCellStyle(cs_poi1);//全廠合計  （不用填數據）
			
			sheet.getRow(4+list_facts.size()+i).getCell(5+list.size()).setCellValue(GlobalMethod.division(d/1000,j));//平均報廢重量/月
			sheet.getRow(4+list_facts.size()+i).getCell(5+list.size()).setCellStyle(cs_poi1);
			
			sheet.getRow(4+list_facts.size()+i).getCell(6+list.size()).setCellStyle(cs_poi1);//全廠平均合計/月  （不用填數據）
			i++;
		}
		//表格最下面一行合計
		sheet.getRow(4+list_facts.size()+m3.size()).getCell(0).setCellValue("合計");
		for(int a=0;a<2;a++){
			sheet.getRow(4+list_facts.size()+m3.size()).getCell(a).setCellStyle(cs_poi1_bg);
		}
		for(int a=0;a<months.size()+5;a++){
			double result=0.0;
			for(int b=0;b<m3.size();b++){
				double d=sheet.getRow(4+list_facts.size()+b).getCell(2+a).getNumericCellValue();
				result=result+d;
			}
			sheet.getRow(4+list_facts.size()+m3.size()).getCell(2+a).setCellValue(result);
			sheet.getRow(4+list_facts.size()+m3.size()).getCell(2+a).setCellStyle(cs_poi1_bg);
		}
		/**********************************各廠別狀態合計****************************************/
				
		ServletOutputStream os=response.getOutputStream();
		//response.setContentType("application/vnd.ms-excel");
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");				
		String fileName="waste_report.xlsx";
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
	
	/**
	 * 各廠廢品重量匯總表
	 * @throws ParseException
	 * @throws IOException 
	 */
	public void printwastereport() throws ParseException, IOException{
		List<WeballobjB>list_objs=weballobjbser.findobjA41(yymm);
		if(list_objs==null||list_objs.size()==0){
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>window.parent.alert('無數據');</script>");
		}else{
			init_a(list_objs);
		}
	}
	
	
	/**
	 * 生產與請款差異匯總表
	 * @throws ParseException
	 * @throws IOException 
	 */
	public void printProDiff() throws ParseException, IOException{
		List<WeballobjB>list=weballobjbser.findProDiff(factNo, yymm, yymm2);		
		if(list==null||list.size()==0){
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>window.parent.alert('無數據');</script>");
		}else{
			Map<String,Object>map=new HashMap<String,Object>();				
			List<String>months=GlobalMethod.findMonths(yymm, yymm2);
			List<WeballobjB>list3=new ArrayList<WeballobjB>();		
			StringBuffer fileName=new StringBuffer();
			StringBuffer title=new StringBuffer();		
			List<WebFact>list2=webFactSer.findFactAble();			
			for(String month:months){
				for(WebFact fact:list2){
					list3.add(new WeballobjB(new WeballobjBId(fact,month)));
				}
			}
			fileName.append("prodiffreport");
					
			for(int a=0;a<list3.size();a++){
				WeballobjB obj=list3.get(a);
				for(WeballobjB obj2:list){
					if(obj.getId().getFact().getId().getFactArea().equals(obj2.getId().getFact().getId().getFactArea())&&
							obj.getId().getFact().getId().getFactNo().equals(obj2.getId().getFact().getId().getFactNo())&&
							obj.getId().getYymm().equals(obj2.getId().getYymm())){
						list3.set(a, obj2);
						
					}
				}
			}										
			if(yymm!=null&&!yymm.equals("")){
				fileName.append("-"+yymm);
				title.append(yymm);
			}
			if(yymm2!=null&&!yymm2.equals("")){
				fileName.append("-"+yymm2);
				title.append("-"+yymm2);
			}
			title.append("生產与請款差异匯總表");
			map.put("title", title.toString());
			JasperHelper.exportmain("excel", map,"vweballobj2019_tw.jasper", list3,fileName.toString(), "jasper/input/");	
		}
					
	}
	
	
    public void init_b(List<VWeballobjbStorage>list_objs) throws ParseException, IOException{
		
		/***************************************數據處理******************************************************/						
		List<String>months=GlobalMethod.findMonths(yymm+"01", yymm+"12");
		List<WebFact>list_facts=webFactSer.findAllFact_showA();
		//List<WebFact>list_facts=webFactSer.findFactAble();
		
		
		for(WebFact obj:list_facts){
			if(obj.getId().getFactArea().equals("MD")||obj.getId().getFactArea().equals("IP")||obj.getId().getFactArea().equals("EVA")){
				obj.getId().setFactArea(FC);
			}
		}
		
		for(int a=0;a<list_facts.size();a++){
			for(int b=list_facts.size()-1;b>a;b--){
				if(list_facts.get(a).getId().getFactNo().equals(list_facts.get(b).getId().getFactNo())&&
						list_facts.get(a).getId().getFactArea().equals(list_facts.get(b).getId().getFactArea())){
					list_facts.remove(b);
				}
			}
		}
		
		List<VWeballobjbStorage>list_objs2=new ArrayList<VWeballobjbStorage>();
		for(WebFact obj:list_facts){			
			for(String month:months){
				list_objs2.add(new VWeballobjbStorage(new VWeballobjbStorageId(obj.getId().getFactNo(),obj.getId().getFactArea(),month),
						new VWebFact(obj.getId().getFactNo(),obj.getFactSname())));
			}
		}
		for(int a=0;a<list_objs2.size();a++){
			VWeballobjbStorage obj=list_objs2.get(a);
			for(VWeballobjbStorage obj2:list_objs){
				if(obj.getId().getFactNo().equals(obj2.getId().getFactNo())&&
						obj.getId().getFactCode().equals(obj2.getId().getFactCode())&&
						obj.getId().getYymm().equals(obj2.getId().getYymm())){
					list_objs2.set(a, obj2);
					break;
				}
			}
		}
		
		
		List<String>factnos=new ArrayList<String>();
		for(VWeballobjbStorage obj:list_objs2){
			factnos.add(obj.getId().getFactNo()+"__"+obj.getFactNo2().getFactSname());
		}		
		for(int a=0;a<factnos.size();a++){
			for(int b=factnos.size()-1;b>a;b--){
				if(factnos.get(a).split("__")[0].equals(factnos.get(b).split("__")[0])){
					factnos.remove(b);
				}
			}
		}		
		Map<String,Object>map_temp=new LinkedHashMap<String,Object>();
		for(String str:factnos){
			List<String>l1=new ArrayList<String>();
			for(WebFact obj:list_facts){
				if(str.split("__")[0].equals(obj.getId().getFactNo())){
					l1.add(obj.getId().getFactArea());
				}
			}
			map_temp.put(str, l1);
		}		
		Map<String,Object>m1=new LinkedHashMap<String,Object>();//最終要循環的數據
		for(String key:map_temp.keySet()){
			List<String>l1=(List<String>)map_temp.get(key);
			Map<String,Object>m2=new LinkedHashMap<String,Object>();
			for(String str:l1){
				List<VWeballobjbStorage>list_obj=new ArrayList<VWeballobjbStorage>();
				for(VWeballobjbStorage obj:list_objs2){
					if(key.split("__")[0].equals(obj.getId().getFactNo())&&str.equals(obj.getId().getFactCode())){
						list_obj.add(obj);
					}
				}
				m2.put(str, list_obj);				
			}						
			m1.put(key, m2);
		}
		
		List<String>list_cols=new ArrayList<String>();
		list_cols.add("廠別");
		list_cols.add("廠別狀態");
		list_cols.add("項目");
		list_cols.add("1月");
		list_cols.add("2月");
		list_cols.add("3月");
		list_cols.add("4月");
		list_cols.add("5月");
		list_cols.add("6月");
		list_cols.add("7月");
		list_cols.add("8月");
		list_cols.add("9月");
		list_cols.add("10月");
		list_cols.add("11月");
		list_cols.add("12月");	
		
		List<String>list_str=new ArrayList<String>();
		list_str.add("當月耗用金額");
		list_str.add("原材料平均庫存金額");
		/***************************************數據處理******************************************************/	
		
		
		XSSFWorkbook wb=new XSSFWorkbook();
				
		for(int x=0;x<4;x++){
			XSSFSheet sheet;
			if(x==1){//表0
				list_str.remove(0);//移除一項
				list_cols.remove(2);//移除第二項  “項目”
			}
			if(x==3){
				sheet=wb.createSheet(yymm+"各廠平均庫存金額");
			}else{
				sheet=wb.createSheet("分表"+x);
			}
			
			Map<String,Object>map_style=GlobalMethod.findStyles2007(wb);
			XSSFCellStyle cs_title=(XSSFCellStyle)map_style.get("cs_title");
			XSSFCellStyle cs_head=(XSSFCellStyle)map_style.get("cs_head");
			XSSFCellStyle cs_poi1=(XSSFCellStyle)map_style.get("cs_poi1");
			XSSFCellStyle cs_poi2=(XSSFCellStyle)map_style.get("cs_poi2");
			XSSFCellStyle cs_poi1_bg=(XSSFCellStyle)map_style.get("cs_poi1_bg");
			XSSFCellStyle cs=(XSSFCellStyle)map_style.get("cs");
			
			for(int a=0;a<list_facts.size()*list_str.size()+5;a++){
				sheet.createRow(a);
				for(int b=0;b<list_cols.size()+5;b++){
					if(b==0||b==2){
						sheet.setColumnWidth(b, 5000);
					}else{
						sheet.setColumnWidth(b, 4000);
					}
					sheet.getRow(a).createCell(b);
				}
			}
			
			//標題
			CellRangeAddress cra_title=new CellRangeAddress(0,0,0,5);
			sheet.addMergedRegion(cra_title);
			sheet.getRow(0).getCell(0).setCellValue(yymm+"各廠庫存周轉率");		
			for(int a=0;a<4;a++){
				sheet.getRow(0).getCell(a).setCellStyle(cs_title);
			}
			
			//表頭
			for(int a=0;a<list_cols.size();a++){
				sheet.getRow(1).getCell(a).setCellValue(list_cols.get(a));
				sheet.getRow(1).getCell(a).setCellStyle(cs_head);
			}
			
			
			//數據填充
			int y_index2=0;
			for(String key:m1.keySet()){
				Map<String,Object>m_a=(Map<String,Object>)m1.get(key);
				CellRangeAddress cra=new CellRangeAddress(2+y_index2,1+(m_a.keySet().size()*list_str.size())+y_index2,0,0);//廠別			
				sheet.addMergedRegion(cra);									
				sheet.getRow(2+y_index2).getCell(0).setCellValue(key.split("__")[1]+"-"+key.split("__")[0]);			
				for(int a=0;a<m_a.keySet().size()*list_str.size();a++){
					sheet.getRow(2+y_index2+a).getCell(0).setCellStyle(cs);				
				}
				
				for(String factcode:m_a.keySet()){
					CellRangeAddress cra2=new CellRangeAddress(2+y_index2,1+list_str.size()+y_index2,1,1);//廠別狀態
					sheet.addMergedRegion(cra2);	
					sheet.getRow(2+y_index2).getCell(1).setCellValue(factcode);					
						for(int a=0;a<list_str.size();a++){					
							sheet.getRow(2+y_index2+a).getCell(1).setCellStyle(cs);
							if(x==0){//表0
								sheet.getRow(2+y_index2+a).getCell(2).setCellValue(list_str.get(a));
								sheet.getRow(2+y_index2+a).getCell(2).setCellStyle(cs);
							}
							
						}
															
					List<VWeballobjbStorage>l1=(List<VWeballobjbStorage>)m_a.get(factcode);	
					for(int a=0;a<l1.size();a++){
						double obj42=l1.get(a).getObjA42()==null?0.0:l1.get(a).getObjA42();
						double obj43=l1.get(a).getObjA43()==null?0.0:l1.get(a).getObjA43();	
						switch (x) {
						case 0://表0
							sheet.getRow(2+y_index2).getCell(3+a).setCellValue(obj42);
							sheet.getRow(3+y_index2).getCell(3+a).setCellValue(obj43);
							for(int b=0;b<list_str.size();b++){
								sheet.getRow(2+y_index2+b).getCell(3+a).setCellStyle(cs_poi1);
							}
							break;
						case 1://表1
							sheet.getRow(2+y_index2).getCell(2+a).setCellValue(GlobalMethod.division(obj42, obj43));
							for(int b=0;b<list_str.size();b++){
								sheet.getRow(2+y_index2+b).getCell(2+a).setCellStyle(cs_poi2);
							}
							break;
						case 2://表2
							sheet.getRow(2+y_index2).getCell(2+a).setCellValue(GlobalMethod.division(obj43, obj42));
							for(int b=0;b<list_str.size();b++){
								sheet.getRow(2+y_index2+b).getCell(2+a).setCellStyle(cs_poi1);
							}
							break;
						case 3://表3
							sheet.getRow(2+y_index2).getCell(2+a).setCellValue(obj43);
							for(int b=0;b<list_str.size();b++){
								sheet.getRow(2+y_index2+b).getCell(2+a).setCellStyle(cs_poi1);
							}
							
							//表格最下面一行合計
							sheet.getRow(2+list_facts.size()).getCell(0).setCellValue("合計");
							for(int b=0;b<2;b++){
								sheet.getRow(2+list_facts.size()).getCell(b).setCellStyle(cs_poi1_bg);
							}							
								double result=0.0;
								for(int b=0;b<list_facts.size();b++){
									double d=sheet.getRow(2+b).getCell(2+a).getNumericCellValue();
									result=result+d;
								}
								sheet.getRow(2+list_facts.size()).getCell(2+a).setCellValue(result);
								sheet.getRow(2+list_facts.size()).getCell(2+a).setCellStyle(cs_poi1_bg);																					
							break;						
						}																								
					}
					if(x==0){//表0
						y_index2=y_index2+2;
					}else{
						y_index2++;
					}
					
				}			
				
			}
			
		}
		
				
		ServletOutputStream os=response.getOutputStream();
		//response.setContentType("application/vnd.ms-excel");
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");				
		String fileName=yymm+"各廠庫存周轉率匯總.xlsx";
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
	/**
	 * 各廠庫存周轉率
	 */
	public void printstorage() throws ParseException, IOException{
		List<VWeballobjbStorage>list_objs=weballobjbser.findStorage(yymm);
		if(list_objs==null||list_objs.size()==0){
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>window.parent.alert('無數據');</script>");
		}else{
			init_b(list_objs);
		}
	}
	
	/**
	 * 盤點報表
	 * @throws IOException 
	 */
	public void printTotalReport() throws IOException{
		List<WeballobjB>list_source=weballobjbser.findWeballobjB(yymm);
		List<WebFact>list_facts=webFactSer.findFactAble();//所有廠別    
		List<String>list_head=new ArrayList<String>();
		list_head.add("廠別");
		list_head.add("區域");
		list_head.add("帳上(KG)");
		list_head.add("實盤(KG)");
		list_head.add("差異");
		list_head.add("%");
		list_head.add("排名%");
		
		List<WeballobjB>list_objs=new LinkedList<WeballobjB>();
		for(WebFact fact:list_facts){
			list_objs.add(new WeballobjB(new WeballobjBId(fact,yymm)));
		}
		for(int a=0;a<list_objs.size();a++){
			for(WeballobjB obj:list_source){
				if(list_objs.get(a).getId().getFact().getId().getFactNo().equals(obj.getId().getFact().getId().getFactNo())&&
						list_objs.get(a).getId().getFact().getId().getFactArea().equals(obj.getId().getFact().getId().getFactArea())){
					list_objs.remove(a);
					list_objs.add(a,obj);
					break;
				}
			}
		}
				
		XSSFWorkbook wb=new XSSFWorkbook();
		XSSFSheet sheet=wb.createSheet("排名");
		Map<String,Object>map_style=GlobalMethod.findStyles2007(wb);
		//XSSFCellStyle cs_title=(XSSFCellStyle)map_style.get("cs_title");
		XSSFCellStyle cs_head=(XSSFCellStyle)map_style.get("cs_head");
		XSSFCellStyle cs_poi1=(XSSFCellStyle)map_style.get("cs_poi1");
		XSSFCellStyle cs_poi2=(XSSFCellStyle)map_style.get("cs_poi2");
		XSSFCellStyle cs_poi1_bg=(XSSFCellStyle)map_style.get("cs_poi1_bg");
		XSSFCellStyle cs=(XSSFCellStyle)map_style.get("cs");
		XSSFCellStyle cs_font_green=(XSSFCellStyle)map_style.get("cs_font_green");
		XSSFCellStyle cs_font_red=(XSSFCellStyle)map_style.get("cs_font_red");
		for(int a=0;a<list_facts.size()+2;a++){
			sheet.createRow(a);
			if(a==0){
				sheet.setColumnWidth(0, 5000);
			}
			for(int b=0;b<list_head.size()+2;b++){
				sheet.getRow(a).createCell(b).setCellStyle(cs);
			}
		}
						
		for(int a=0;a<list_head.size();a++){
			sheet.getRow(0).getCell(a).setCellValue(list_head.get(a));
			sheet.getRow(0).getCell(a).setCellStyle(cs_head);
		}
		
		/*for(int a=0;a<list_facts.size();a++){
			sheet.getRow(a+1).getCell(0).setCellValue(list_facts.get(a).getFactSname());
			sheet.getRow(a+1).getCell(0).setCellStyle(cs);
		}*/
		
		DecimalFormat frm=new DecimalFormat("0.00%");
		List<Double>list_db=new ArrayList<Double>();
		for(int a=0;a<list_objs.size();a++){
			sheet.getRow(a+1).getCell(0).setCellValue(list_objs.get(a).getId().getFact().getFactSname());
			sheet.getRow(a+1).getCell(1).setCellValue(list_objs.get(a).getId().getFact().getId().getFactArea());
			if(list_objs.get(a).getObjA3()==null){
				sheet.getRow(a+1).getCell(2).setCellValue("無數據");
			}else{
				sheet.getRow(a+1).getCell(2).setCellValue(list_objs.get(a).getObjA3());
			}
			if(list_objs.get(a).getObjA4()==null){
				sheet.getRow(a+1).getCell(3).setCellValue("無數據");
			}else{
				sheet.getRow(a+1).getCell(3).setCellValue(list_objs.get(a).getObjA4());
			}
			
			if(list_objs.get(a).getObjA3()==null||list_objs.get(a).getObjA4()==null){
				sheet.getRow(a+1).getCell(4).setCellValue("無數據");
				sheet.getRow(a+1).getCell(5).setCellValue("無數據");
				list_db.add(0.0);
			}else{
				sheet.getRow(a+1).getCell(4).setCellValue(list_objs.get(a).getObjA4()-list_objs.get(a).getObjA3());
				sheet.getRow(a+1).getCell(5).setCellValue(GlobalMethod.division(list_objs.get(a).getObjA4()-list_objs.get(a).getObjA3(), list_objs.get(a).getObjA4()));
				list_db.add(GlobalMethod.division(list_objs.get(a).getObjA4()-list_objs.get(a).getObjA3(), list_objs.get(a).getObjA4()));
			}
		}
		
		
		List<Double>list_db3=new ArrayList<Double>();
		
		list_db3.add(1.1);
		list_db3.add(1.1);
		list_db3.add(22.7);
		list_db3.add(-3.3);
		list_db3.add(-5.5);
		//排名
		
		List<Double>list_db3_1=new ArrayList<Double>();
		List<Double>list_db3_2=new ArrayList<Double>();
		for(Double d:list_db){
			if(d<0){
				list_db3_2.add(d);				
			}else{
				list_db3_1.add(d);
			}
		}
		
		List<Double>list_db4_1=new ArrayList<Double>();
		List<Double>list_db4_2=new ArrayList<Double>();
		for(Double d:list_db3_1){
			list_db4_1.add(d);
		}
		for(Double d:list_db3_2){
			list_db4_2.add(d);
		}
		List<Double>list_db5_1=GlobalMethod.removeSameDouble2(list_db4_1);
		List<Double>list_db5_2=GlobalMethod.removeSameDouble(list_db4_2);
		
		List<Double[]>list_db2_1=new ArrayList<Double[]>();
		List<Double[]>list_db2_2=new ArrayList<Double[]>();
		for(Double d:list_db){
			
			for(int a=0;a<list_db5_1.size();a++){
				if(d.equals(list_db5_1.get(a))){
					Double[]dd={(double) (a+1),d};
					list_db2_1.add(dd);
					break;
				}
			}
			
			for(int a=0;a<list_db5_2.size();a++){
				if(d.equals(list_db5_2.get(a))){
					Double[]dd={(double) (a+1),d};
					list_db2_2.add(dd);
					break;
				}
			}
		}
		
		for(int a=0;a<3;a++){
			for(int b=0;b<list_db.size();b++){
				if(list_db5_1.get(a).equals(list_db.get(b))){
					sheet.getRow(b+1).getCell(6).setCellValue(a+1);
					sheet.getRow(b+1).getCell(6).setCellStyle(cs_font_green);
					
				}
				if(list_db5_2.get(a).equals(list_db.get(b))){
					sheet.getRow(b+1).getCell(6).setCellValue(a+1);
					sheet.getRow(b+1).getCell(6).setCellStyle(cs_font_red);
				}
				
			}
		}
				
		System.out.println("------------------------------------------------------");
		System.out.println(list_db);
		for(int a=0;a<list_db2_1.size();a++){
			System.out.println(list_db2_1.get(a)[0]);
		}
		System.out.println("------------------------------------------------------");
		for(int a=0;a<list_db2_2.size();a++){
			System.out.println(list_db2_2.get(a)[0]);
		}
		System.out.println("------------------------------------------------------");
		
		
		

		ServletOutputStream os=response.getOutputStream();
		//response.setContentType("application/vnd.ms-excel");
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");				
		//String fileName=yymm+"盤點彙總報表.xlsx";
		String fileName="盤點彙總報表.xlsx";
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
	
	/**
	 * 盤點總表(百分比)
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public void printTotalReport_a() throws IOException, ParseException{
		List<WebFact>list_facts=webFactSer.findFactAble();//所有廠別    
		List<String>list_months=GlobalMethod.findMonths(yymm, yymm2);		
						
		XSSFWorkbook wb=new XSSFWorkbook();
		XSSFSheet sheet=wb.createSheet("盤點總表(百分比)");
		Map<String,Object>map_style=GlobalMethod.findStyles2007(wb);
		//XSSFCellStyle cs_title=(XSSFCellStyle)map_style.get("cs_title");
		XSSFCellStyle cs_head=(XSSFCellStyle)map_style.get("cs_head");
		XSSFCellStyle cs_poi1=(XSSFCellStyle)map_style.get("cs_poi1");
		XSSFCellStyle cs_poi2=(XSSFCellStyle)map_style.get("cs_poi2");
		XSSFCellStyle cs_poi1_bg=(XSSFCellStyle)map_style.get("cs_poi1_bg");
		XSSFCellStyle cs=(XSSFCellStyle)map_style.get("cs");
		
		for(int a=0;a<list_facts.size()+2;a++){
			sheet.createRow(a);
			if(a==0){
				sheet.setColumnWidth(0, 5000);
			}
			for(int b=0;b<list_months.size()+2;b++){
				sheet.getRow(a).createCell(b).setCellStyle(cs);
			}
		}
						
		for(int a=0;a<list_months.size();a++){
			sheet.getRow(0).getCell(a).setCellValue(list_months.get(a));
			sheet.getRow(0).getCell(a).setCellStyle(cs_head);
		}
		
		for(int a=0;a<list_facts.size();a++){
			sheet.getRow(a+1).getCell(0).setCellValue(list_facts.get(a).getFactSname());
			sheet.getRow(a+1).getCell(0).setCellStyle(cs);
		}
		
		
		

		ServletOutputStream os=response.getOutputStream();
		//response.setContentType("application/vnd.ms-excel");
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");				
		//String fileName=yymm+"盤點彙總報表.xlsx";
		String fileName="盤點彙總報表.xlsx";
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
	
	/**
	 * 盤點差異表(KG)
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public void printTotalReport_b() throws IOException, ParseException{
		List<WebFact>list_facts=webFactSer.findFactAble();//所有廠別    
		List<String>list_months=GlobalMethod.findMonths(yymm, yymm2);
								
		XSSFWorkbook wb=new XSSFWorkbook();
		XSSFSheet sheet=wb.createSheet("盤點差異表(KG)");
		Map<String,Object>map_style=GlobalMethod.findStyles2007(wb);
		//XSSFCellStyle cs_title=(XSSFCellStyle)map_style.get("cs_title");
		XSSFCellStyle cs_head=(XSSFCellStyle)map_style.get("cs_head");
		XSSFCellStyle cs_poi1=(XSSFCellStyle)map_style.get("cs_poi1");
		XSSFCellStyle cs_poi2=(XSSFCellStyle)map_style.get("cs_poi2");
		XSSFCellStyle cs_poi1_bg=(XSSFCellStyle)map_style.get("cs_poi1_bg");
		XSSFCellStyle cs=(XSSFCellStyle)map_style.get("cs");
		
		for(int a=0;a<list_facts.size()+2;a++){
			sheet.createRow(a);
			if(a==0){
				sheet.setColumnWidth(0, 5000);
			}
			for(int b=0;b<list_months.size()+2;b++){
				sheet.getRow(a).createCell(b).setCellStyle(cs);
			}
		}
						
		for(int a=0;a<list_months.size();a++){
			sheet.getRow(0).getCell(a).setCellValue(list_months.get(a));
			sheet.getRow(0).getCell(a).setCellStyle(cs_head);
		}
		
		for(int a=0;a<list_facts.size();a++){
			sheet.getRow(a+1).getCell(0).setCellValue(list_facts.get(a).getFactSname());
			sheet.getRow(a+1).getCell(0).setCellStyle(cs);
		}
		
		
		

		ServletOutputStream os=response.getOutputStream();
		//response.setContentType("application/vnd.ms-excel");
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");				
		//String fileName=yymm+"盤點彙總報表.xlsx";
		String fileName="盤點彙總報表.xlsx";
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
