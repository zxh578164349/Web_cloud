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
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;
import services.IWebFactServices;
import services.IWebObjsBServices;
import util.GlobalMethod;
import util.ImportExcel;
import util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.KyzExpectmatmLog;
import entity.VWebFact;
import entity.VWebobjBObj3;
import entity.VWebobjBObj3Id;
import entity.VWebobjBObj;
import entity.VWebobjBObj4;
import entity.VWebobjBObj4Id;
import entity.VWebobjBObj5;
import entity.VWebobjBObj5Id;
import entity.VWebobjBObjId;
import entity.WebFact;
import entity.WebFactId;
import entity.WebObjsB;
import entity.WebObjsBId;
import entity.WebUser;


public class WebObjsBAction extends ActionSupport implements ServletResponseAware{
	private final static int NUM=31;//print_tw  多少箇項目（29+1）
	private IWebObjsBServices webobjbservices;
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
	private String workorholiday;
    
    
     
	public String getWorkorholiday() {
		return workorholiday;
	}

	public void setWorkorholiday(String workorholiday) {
		this.workorholiday = workorholiday;
	}

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

	public void setWebobjbservices(IWebObjsBServices webobjbservices) {
		this.webobjbservices = webobjbservices;
	}

	public void addMore() throws IOException{
		response.setContentType("text/html;charset=utf-8");
		try{
			String path="d:\\Webobjs_b_backup\\"+new SimpleDateFormat("yyyyMMdd").format(new Date());//Excel文檔存放目錄
			ajaxResult="0";				
			if(file!=null){		
				File uploadFile_backup=new File(path);	
				if(!uploadFile_backup.exists()){
					uploadFile_backup.mkdirs();
				}																						
						FileInputStream in=new FileInputStream(file);
						FileOutputStream out_backup=new FileOutputStream(uploadFile_backup+"\\"+fileFileName);//備份
						byte[]b=new byte[1024];
						int length=0;
						while((length=in.read(b))>0){
							out_backup.write(b,0,length);
						}																																				
			}
			WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");			
			Map<String,Object>map=ImportExcel.exportListFromFile(new File(path+"\\"+fileFileName));
			List<String>list_factArea=webFactSer.findfactAreaByFactNo(factNo);
			a:for(String key:map.keySet()){
				List<WebObjsB>list_b=new ArrayList<WebObjsB>();
				List<String>list_factcode=new ArrayList<String>();
				List<String>list=(List<String>)map.get(key);
				if(!list.get(0).contains("__序號__項目__單位")){				
					response.getWriter().print("<script>window.parent.showDiv();window.parent.layer.msg('表格式不符合要求')</script>");
					break;
				}
								
				if(list.size()<23||list.size()>35){
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
				for(int i=4;i<array_head.length;i++){
					WebFact fact=new WebFact(new WebFactId(factNo,array_head[i].trim()));
					WebObjsB obj=new WebObjsB(new WebObjsBId(fact,yymm));								
					obj.setOnModulus(Double.valueOf(list.get(1).split(SEPARATOR)[i]));
					obj.setPersonnum(Double.valueOf(list.get(2).split(SEPARATOR)[i]));
					obj.setStandardOutput(Double.valueOf(list.get(3).split(SEPARATOR)[i]));
					obj.setActualYield(Double.valueOf(list.get(4).split(SEPARATOR)[i]));
					obj.setZpObja(Double.valueOf(list.get(5).split(SEPARATOR)[i]));
					obj.setHostpairs(Double.valueOf(list.get(6).split(SEPARATOR)[i]));
					obj.setFactpairs(Double.valueOf(list.get(7).split(SEPARATOR)[i]));
					obj.setSamplepairs(Double.valueOf(list.get(8).split(SEPARATOR)[i]));
					obj.setOutnum(Double.valueOf(list.get(9).split(SEPARATOR)[i]));
					obj.setBacknum(Double.valueOf(list.get(10).split(SEPARATOR)[i]));
					obj.setWorkhours(Double.valueOf(list.get(11).split(SEPARATOR)[i]));
					obj.setDaycount(Double.valueOf(list.get(12).split(SEPARATOR)[i]));
					if("0".equals(workorholiday)&&"0.0".equals(list.get(12).split(SEPARATOR)[i])){
						obj.setDaycount(1.0);
					}else{
						obj.setDaycount(Double.valueOf(list.get(12).split(SEPARATOR)[i]));
					}
					obj.setObjA1(Double.valueOf(list.get(13).split(SEPARATOR)[i]));
					obj.setObjA2(Double.valueOf(list.get(14).split(SEPARATOR)[i]));
					obj.setObjA3(Double.valueOf(list.get(15).split(SEPARATOR)[i]));
					obj.setObjA4(Double.valueOf(list.get(16).split(SEPARATOR)[i]));	
					obj.setObjA5(Double.valueOf(list.get(17).split(SEPARATOR)[i]).longValue());
					obj.setObjA6(Double.valueOf(list.get(18).split(SEPARATOR)[i]).longValue());
					obj.setObjA7(Double.valueOf(list.get(19).split(SEPARATOR)[i]).longValue());
					obj.setObjA8(Double.valueOf(list.get(20).split(SEPARATOR)[i]).longValue());
					obj.setObjA9(Double.valueOf(list.get(21).split(SEPARATOR)[i]).longValue());
					obj.setObjA10("0.0".equals(list.get(22).split(SEPARATOR)[i])?"null":list.get(22).split(SEPARATOR)[i]);
					obj.setObjA11("0.0".equals(list.get(23).split(SEPARATOR)[i])?"null":list.get(23).split(SEPARATOR)[i]);
					obj.setObjA12("0.0".equals(list.get(24).split(SEPARATOR)[i])?"null":list.get(24).split(SEPARATOR)[i]);
					obj.setObjA13("0.0".equals(list.get(25).split(SEPARATOR)[i])?"null":list.get(25).split(SEPARATOR)[i]);
					obj.setObjA14("0.0".equals(list.get(26).split(SEPARATOR)[i])?"null":list.get(26).split(SEPARATOR)[i]);	
					obj.setWorkorholiday(workorholiday);
					obj.setUsername(user.getUsername());
					obj.setDatecreate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
					list_b.add(obj);
				}
				webobjbservices.addMore(list_b);
				response.getWriter().print("<script>window.parent.layer.msg('導入成功',3,1)</script>");
			}
						
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
		bean=webobjbservices.findPageBean(20,page, factNo, yymm);
		return "beanList";
		
	}
	public String findPageBean2(){
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		ActionContext.getContext().getSession().remove("allrow");//dao層
		ActionContext.getContext().getSession().put("public_factno", factNo.split("__")[0]);
		ActionContext.getContext().getSession().put("public_yymm", yymm);	
		bean=webobjbservices.findPageBean(20,page, factNo.split("__")[0], yymm);
		return "beanList1";
	}
	public String findPageBean3(){
		factNo=(String)ActionContext.getContext().getSession().get("public_factno");
		yymm=(String)ActionContext.getContext().getSession().get("public_yymm");
		bean=webobjbservices.findPageBean(20,page, factNo, yymm);
		return "beanList1";
	}
	
	
	public String delete(){
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setFactNo(factNo);
		log.setFactCode(factCode);
		log.setObj("WebObjsB");
		log.setYymm(yymmdd);
		webobjbservices.delete(factNo, factCode, yymmdd,log);
		return "delete";
	}
	
	
	/*************************************************工廠報表(一個月每天情況)***********************************************************/
	public void print() throws ParseException, IOException{
		XSSFWorkbook wb=new XSSFWorkbook();
		Map<String,Object>map_cs=findStyles2007(wb);				
		List<VWebobjBObj>list_objs=webobjbservices.findByYymm2(factNo.split("__")[0], yymm);
		
		Double work_days=0.0;//工作日天數
		for(VWebobjBObj obj:list_objs){
			if("0".equals(obj.getWorkorholiday())){
				work_days++;
			}
		}
		
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
			for(VWebobjBObj obj:list_objs){
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
			List<VWebobjBObj>list_lg=new ArrayList<VWebobjBObj>();
			for(int a=0;a<days;a++){
				list_lg.add(new VWebobjBObj());
			}
			for(String factcode:list_factcodes){
				String temp="";
				List<VWebobjBObj>list=new ArrayList<VWebobjBObj>();
				for(int a=1;a<=days;a++){				
					if(a>9){
						temp=yymm+a;
					}else{
						temp=yymm+"0"+a;
					}
					list.add(new VWebobjBObj(new VWebobjBObjId(new WebFact(new WebFactId(factNo.split("__")[0],factcode)),temp)));
					temp="";
				}
				
				for(int a=0;a<list.size();a++){
					for(VWebobjBObj obj:list_objs){
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
					    list_lg.get(a).setObja5(isMyNull_ll(list_lg.get(a).getObja5())+isMyNull_ll(list.get(a).getObja5()));
						list_lg.get(a).setObja6(isMyNull_ll(list_lg.get(a).getObja6())+isMyNull_ll(list.get(a).getObja6()));
						list_lg.get(a).setObjaE(isMyNull_ll(list_lg.get(a).getObjaE())+isMyNull_ll(list.get(a).getObjaE()));
						list_lg.get(a).setObja7(isMyNull_ll(list_lg.get(a).getObja7())+isMyNull_ll(list.get(a).getObja7()));
						list_lg.get(a).setObja8(isMyNull_ll(list_lg.get(a).getObja8())+isMyNull_ll(list.get(a).getObja8()));
						list_lg.get(a).setObja9(isMyNull_ll(list_lg.get(a).getObja9())+isMyNull_ll(list.get(a).getObja9()));																
				}
				
				VWebobjBObj sum_obj=new VWebobjBObj();
				for(VWebobjBObj obj:list){								
					sum_obj.setTotalhole(isMyNull_db(sum_obj.getTotalhole())+isMyNull_db(obj.getTotalhole()));
					sum_obj.setMachinepower(isMyNull_db(sum_obj.getMachinepower())+isMyNull_db(obj.getMachinepower()));
					sum_obj.setHole(isMyNull_db(sum_obj.getHole())+isMyNull_db(obj.getHole()));
					sum_obj.setSample(isMyNull_db(sum_obj.getSample())+isMyNull_db(obj.getSample()));
					sum_obj.setAccessories(isMyNull_db(sum_obj.getAccessories())+isMyNull_db(obj.getAccessories()));
					sum_obj.setOther(isMyNull_db(sum_obj.getOther())+isMyNull_db(obj.getOther()));
					sum_obj.setEstmodel(isMyNull_db(sum_obj.getEstmodel())+isMyNull_db(obj.getEstmodel()));
					sum_obj.setEstnum(isMyNull_db(sum_obj.getEstnum())+isMyNull_db(obj.getEstnum()));
					sum_obj.setEstpay(isMyNull_db(sum_obj.getEstpay())+isMyNull_db(obj.getEstpay()));
					sum_obj.setOnmodulus(isMyNull_db(sum_obj.getOnmodulus())+isMyNull_db(obj.getOnmodulus()));
					sum_obj.setPersonnum(isMyNull_db(sum_obj.getPersonnum())+isMyNull_db(obj.getPersonnum()));	
					
					sum_obj.setStandardoutput(isMyNull_db(sum_obj.getStandardoutput())+isMyNull_db(obj.getStandardoutput()));
					sum_obj.setActualyield(isMyNull_db(sum_obj.getActualyield())+isMyNull_db(obj.getActualyield()));
					sum_obj.setObja1(isMyNull_db(sum_obj.getObja1())+isMyNull_db(obj.getObja1()));
					sum_obj.setObja2(isMyNull_db(sum_obj.getObja2())+isMyNull_db(obj.getObja2()));
					sum_obj.setObja3(isMyNull_db(sum_obj.getObja3())+isMyNull_db(obj.getObja3()));
					sum_obj.setObja4(isMyNull_db(sum_obj.getObja4())+isMyNull_db(obj.getObja4()));
					sum_obj.setObjaA(isMyNull_db(sum_obj.getObjaA())+isMyNull_db(obj.getObjaA()));
					sum_obj.setObjaB(isMyNull_db(sum_obj.getObjaB())+isMyNull_db(obj.getObjaB()));
					sum_obj.setObjaC(isMyNull_db(sum_obj.getObjaC())+isMyNull_db(obj.getObjaC()));
					sum_obj.setObjaD(isMyNull_db(sum_obj.getObjaD())+isMyNull_db(obj.getObjaD()));
				}
				map2.put(factcode, sum_obj);
				
								
			}
			
		
			XSSFSheet sheet=wb.createSheet(yymm+"工廠訊息");
			XSSFSheet sheet2=wb.createSheet(yymm+"提報事項");
			init(sheet,map_cs,map,list_lg,days,map2,work_days);
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
	
	
	
	
	
							
	public void init(XSSFSheet sheet,Map<String,Object>map_cs,Map<String,Object>map,List<VWebobjBObj>list_lg,int days,Map<String,Object>map2,Double work_days) throws ParseException{				
		XSSFCellStyle cs=(XSSFCellStyle)map_cs.get("cs");
		XSSFCellStyle cs_head=(XSSFCellStyle)map_cs.get("cs_head");
		XSSFCellStyle cs_title=(XSSFCellStyle)map_cs.get("cs_title");		
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
				List<VWebobjBObj>list=(List<VWebobjBObj>)map.get(factcode);					
				for(int a=0;a<list.size();a++){						
					List<Double>list_db=objToDouble(list.get(a));
					for(int b=0;b<list_db.size();b++){
						//sheet.getRow(b+temp+1).getCell(a+2).setCellValue(list_db.get(b));
						this.selectValue_db(sheet.getRow(b+temp+1).getCell(a+2), list.get(a).getWorkorholiday(), list_db.get(b));
						sheet.getRow(b+temp+1).getCell(a+2).setCellStyle(this.selectStyle2007(b, map_cs,0,list.get(a).getWorkorholiday(),list_db.get(b)));
					}																
				}
				
				sheet.getRow(temp).getCell(2+days).setCellValue("總計");
				sheet.getRow(temp).getCell(3+days).setCellValue("平均");
				for(int a=0;a<2;a++){
					sheet.getRow(temp).getCell(2+days+a).setCellStyle(cs_head);
				}
				List<Double>list_sum=objToDouble((VWebobjBObj)map2.get(factcode));
				for(int b=0;b<list_sum.size();b++){
					sheet.getRow(b+temp+1).getCell(2+days).setCellValue(list_sum.get(b));
					sheet.getRow(b+temp+1).getCell(2+days).setCellStyle(this.selectStyle2007(b, map_cs,1,null,null));
					sheet.getRow(b+temp+1).getCell(3+days).setCellStyle(this.selectStyle2007(b, map_cs,1,null,null));
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
	
	
	public void init2(XSSFSheet sheet,Map<String,Object>map_cs,Map<String,Object>map,int days) throws ParseException{				
		XSSFCellStyle cs=(XSSFCellStyle)map_cs.get("cs");
		XSSFCellStyle cs_head=(XSSFCellStyle)map_cs.get("cs_head");
		XSSFCellStyle cs_title=(XSSFCellStyle)map_cs.get("cs_title");	
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
				List<VWebobjBObj>list=(List<VWebobjBObj>)map.get(factcode);					
				for(int a=0;a<list.size();a++){						
					List<String>list_str=objToString(list.get(a));
					for(int b=0;b<list_str.size();b++){
						/*sheet.getRow(b+temp+1).getCell(a+2).setCellValue(list_str.get(b));
						sheet.getRow(b+temp+1).getCell(a+2).setCellStyle(cs);*/
						this.selectValue_str(sheet.getRow(b+temp+1).getCell(a+2), list.get(a).getWorkorholiday(), list_str.get(b));
						sheet.getRow(b+temp+1).getCell(a+2).setCellStyle(this.selectStyle2007(b, map_cs, 0, list.get(a).getWorkorholiday(), list_str.get(b)));
					}																
				}										
				temp=temp+list_col.size()+1;
			}			
	}
	
	public List<Double> objToDouble(Object obj){
		List<Double>list=new ArrayList<Double>();
		if(obj instanceof VWebobjBObj){
			list.add(isMyNull_db(((VWebobjBObj)obj).getTotalhole()));
			list.add(isMyNull_db(((VWebobjBObj)obj).getMachinepower()));
			list.add(isMyNull_db(((VWebobjBObj)obj).getHole()));
			list.add(isMyNull_db(((VWebobjBObj)obj).getSample()));
			list.add(isMyNull_db(((VWebobjBObj)obj).getAccessories()));
			list.add(isMyNull_db(((VWebobjBObj)obj).getOther()));
			list.add(isMyNull_db(((VWebobjBObj)obj).getEstmodel()));
			list.add(isMyNull_db(((VWebobjBObj)obj).getEstnum()));
			list.add(isMyNull_db(((VWebobjBObj)obj).getEstpay()));
			list.add(isMyNull_db(((VWebobjBObj)obj).getOnmodulus()));
			list.add(isMyNull_db(((VWebobjBObj)obj).getPersonnum()));		
			list.add(isMyNull_db(((VWebobjBObj)obj).getStandardoutput()));
			list.add(isMyNull_db(((VWebobjBObj)obj).getActualyield()));
			list.add(isMyNull_db(((VWebobjBObj)obj).getObjaA()));
			list.add(isMyNull_db(((VWebobjBObj)obj).getObjaB()));
			list.add(isMyNull_db(((VWebobjBObj)obj).getObjaC()));
			list.add(isMyNull_db(((VWebobjBObj)obj).getObja1()));
			list.add(isMyNull_db(((VWebobjBObj)obj).getObjaD()));
			list.add(isMyNull_db(((VWebobjBObj)obj).getObja2()));
			list.add(isMyNull_db(((VWebobjBObj)obj).getObja3()));
			list.add(isMyNull_db(((VWebobjBObj)obj).getObja4()));
		}
		if(obj instanceof VWebobjBObj4){
			list.add(isMyNull_db(((VWebobjBObj4)obj).getTotalhole()));
			list.add(isMyNull_db(((VWebobjBObj4)obj).getMachinepower()));
			list.add(isMyNull_db(((VWebobjBObj4)obj).getHole()));
			list.add(isMyNull_db(((VWebobjBObj4)obj).getSample()));
			list.add(isMyNull_db(((VWebobjBObj4)obj).getAccessories()));
			list.add(isMyNull_db(((VWebobjBObj4)obj).getOther()));
			list.add(isMyNull_db(((VWebobjBObj4)obj).getEstmodel()));
			list.add(isMyNull_db(((VWebobjBObj4)obj).getEstnum()));
			list.add(isMyNull_db(((VWebobjBObj4)obj).getEstpay()));
			list.add(isMyNull_db(((VWebobjBObj4)obj).getOnmodulus()));
			list.add(isMyNull_db(((VWebobjBObj4)obj).getPersonnum()));		
			list.add(isMyNull_db(((VWebobjBObj4)obj).getStandardoutput()));
			list.add(isMyNull_db(((VWebobjBObj4)obj).getActualyield()));
			list.add(isMyNull_db(((VWebobjBObj4)obj).getObjaA()));
			list.add(isMyNull_db(((VWebobjBObj4)obj).getObjaB()));
			list.add(isMyNull_db(((VWebobjBObj4)obj).getObjaC()));
			list.add(isMyNull_db(((VWebobjBObj4)obj).getObja1()));
			list.add(isMyNull_db(((VWebobjBObj4)obj).getObjaD()));
			list.add(isMyNull_db(((VWebobjBObj4)obj).getObja2()));
			list.add(isMyNull_db(((VWebobjBObj4)obj).getObja3()));
			list.add(isMyNull_db(((VWebobjBObj4)obj).getObja4()));
		}
					
		return list;		
	}
	
	public List<String> objToString(VWebobjBObj obj){
		List<String>list=new ArrayList<String>();		
		list.add(isMyNull_str(obj.getObja10()));
		list.add(isMyNull_str(obj.getObja11()));
		list.add(isMyNull_str(obj.getObja12()));
		list.add(isMyNull_str(obj.getObja13()));
		list.add(isMyNull_str(obj.getObja14()));
		return list;		
	}
	
	public List<Long> objToLong(Object obj){
		List<Long>list=new ArrayList<Long>();
		if(obj instanceof VWebobjBObj){
			list.add(isMyNull_ll(((VWebobjBObj) obj).getObja5()));
			list.add(isMyNull_ll(((VWebobjBObj) obj).getObja6()));
			list.add(isMyNull_ll(((VWebobjBObj) obj).getObjaE()));
			list.add(isMyNull_ll(((VWebobjBObj) obj).getObja7()));
			list.add(isMyNull_ll(((VWebobjBObj) obj).getObja8()));
			list.add(isMyNull_ll(((VWebobjBObj) obj).getObja9()));
		}
		if(obj instanceof VWebobjBObj3){
			list.add(isMyNull_ll(((VWebobjBObj3) obj).getObjA5()));
			list.add(isMyNull_ll(((VWebobjBObj3) obj).getObjA6()));
			list.add(isMyNull_ll(((VWebobjBObj3) obj).getObjAe()));
			list.add(isMyNull_ll(((VWebobjBObj3) obj).getObjA7()));
			list.add(isMyNull_ll(((VWebobjBObj3) obj).getObjA8()));
			list.add(isMyNull_ll(((VWebobjBObj3) obj).getObjA9()));
		}
		if(obj instanceof VWebobjBObj5){
			list.add(isMyNull_ll(((VWebobjBObj5) obj).getObja5()));
			list.add(isMyNull_ll(((VWebobjBObj5) obj).getObja6()));
			list.add(isMyNull_ll(((VWebobjBObj5) obj).getObjaE()));
			list.add(isMyNull_ll(((VWebobjBObj5) obj).getObja7()));
			list.add(isMyNull_ll(((VWebobjBObj5) obj).getObja8()));
			list.add(isMyNull_ll(((VWebobjBObj5) obj).getObja9()));
		}
					
		
		return list;		
	}
		
		
	/*************************************************工廠報表(一個月每天情況)***********************************************************/
	
	
	
	
	
	
	/***************************************各廠月報表彙總************************************************************/
	public void print_tw() throws IOException, ParseException{
		XSSFWorkbook wb=new XSSFWorkbook();
		XSSFSheet sheet=wb.createSheet("工廠訊息匯總_"+yymm);
		Map<String,Object>map_style=GlobalMethod.findStyles2007(wb);				
		List<VWebobjBObj4>list_source=webobjbservices.findVWebobjBObj4(yymm);
		List<VWebobjBObj5>list_source2=webobjbservices.findVWebobjBObj5(yymm);
		List<VWebobjBObj>list_source3=webobjbservices.findObjByMonth(yymm);
		Map<String,Object>map=new LinkedHashMap<String,Object>();
						
		//List<WebFact>facts=webFactSer.findFactAble();//所有有效廠別
		//List<WebFact>facts=webFactSer.findAllFact_showA();//與重點指標報表相同
		List<WebFact>facts=webFactSer.findAllFact_2();//與產量報表相同
		Calendar cal=Calendar.getInstance();
		cal.setTime(new SimpleDateFormat("yyyyMM").parse(yymm));
		int days=cal.getActualMaximum(Calendar.DAY_OF_MONTH);//選擇月份的天數
		List<String>factcodes=new ArrayList<String>();
		List<String>factnos=new ArrayList<String>();
		List<VWebobjBObj4>list_obj=new LinkedList<VWebobjBObj4>();
		List<VWebobjBObj5>list_obj2=new LinkedList<VWebobjBObj5>();
		List<List<VWebobjBObj>>list_obj3=new LinkedList<List<VWebobjBObj>>();
		for(WebFact fact:facts){
			factcodes.add(fact.getId().getFactArea());
			list_obj.add(new VWebobjBObj4(new VWebobjBObj4Id(fact,yymm)));
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
			for(VWebobjBObj4 obj:list_source){
				if(list_obj.get(a).getId().getWebFact().getId().getFactArea().equals(obj.getId().getWebFact().getId().getFactArea())&&
						list_obj.get(a).getId().getWebFact().getId().getFactNo().equals(obj.getId().getWebFact().getId().getFactNo())){
					list_obj.set(a, obj);
					break;
				}
			}
		}
		
		for(String factcode:factcodes){
			List<VWebobjBObj4>list=new ArrayList<VWebobjBObj4>();
			for(VWebobjBObj4 obj:list_obj){
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
			list_obj2.add(new VWebobjBObj5(new VWebobjBObj5Id(new VWebFact(factno),yymm)));
		}
		
		for(VWebobjBObj5 obj:list_obj2){
			for(WebFact fact:facts){
				if(obj.getId().getFact().getFactNo().equals(fact.getId().getFactNo())){
					obj.getId().getFact().setFactSname(fact.getFactSname());
				}
			}
		}
		
		for(int a=0;a<list_obj2.size();a++){
			for(VWebobjBObj5 obj:list_source2){
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
			List<VWebobjBObj>list=new LinkedList<VWebobjBObj>();		
			for(WebFact fact:facts){
				list.add(new VWebobjBObj(new VWebobjBObjId(fact,yymm+dd)));
			}
			list_obj3.add(list);
			dd="";
		}
		
		List<Map<String,Object>>lm=new LinkedList<Map<String,Object>>();
		for(List<VWebobjBObj>list:list_obj3){			
			Map<String,Object>m=new LinkedHashMap<String,Object>();
			for(int a=0;a<list.size();a++){
				for(VWebobjBObj obj:list_source3){
					if(list.get(a).getId().getWebFact().getId().getFactArea().equals(obj.getId().getWebFact().getId().getFactArea())&&
							list.get(a).getId().getWebFact().getId().getFactNo().equals(obj.getId().getWebFact().getId().getFactNo())&&
							list.get(a).getId().getYymmdd().equals(obj.getId().getYymmdd())){
						list.set(a, obj);
						break;
					}
				}
			}
			
			for(String factcode:factcodes){
				List<VWebobjBObj>ll=new ArrayList<VWebobjBObj>();
				for(VWebobjBObj obj:list){
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
	
public void init_more(XSSFSheet sheet,Map<String,Object>map,Map<String,Object>map_style,List<VWebobjBObj5>list_obj2) throws IOException{			
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
			List<VWebobjBObj4>list_obj=(List<VWebobjBObj4>)map.get(factcode);	
			
			/***********************************MD    DJ  換行**********************************/
			if("MD".equals(factcode)||"DJ".equals(factcode)){
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
			/***********************************MD    DJ  換行**********************************/
			
			int length=list_obj.size();			
			sheet.getRow(1+temp1_1).getCell(3+temp).setCellValue(factcode);
			CellRangeAddress cra1=new CellRangeAddress(1+temp1_1,(short)1+temp1_1,3+temp,(short)2+temp+length);
			sheet.addMergedRegion(cra1);
			for(int i=0;i<length;i++){
				sheet.getRow(1+temp1_1).getCell(3+temp+i).setCellStyle(cs_head2);
			}									
			for(int a=0;a<list_obj.size();a++){
				sheet.getRow(2+temp1_1).getCell(3+a+temp).setCellValue(list_obj.get(a).getId().getWebFact().getFactSname());
				sheet.getRow(2+temp1_1).getCell(3+a+temp).setCellStyle(cs_head);
				List<Double>list_db=this.objToDouble(list_obj.get(a));
				for(int b=0;b<list_db.size();b++){
					sheet.getRow(3+b+temp1_1).getCell(3+a+temp).setCellValue(list_db.get(b));
					sheet.getRow(3+b+temp1_1).getCell(3+a+temp).setCellStyle(this.selectStyle2007(b, map_style,1,null,null));
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
			sheet.getRow(temp2+temp1_1).getCell(3+a).setCellStyle(cs_head2);
			List<Long>ll=this.objToLong(list_obj2.get(a));
			for(int b=0;b<ll.size();b++){
				sheet.getRow(1+temp2+temp1_1+b).getCell(3+a).setCellValue(ll.get(b));
				sheet.getRow(1+temp2+temp1_1+b).getCell(3+a).setCellStyle(this.selectStyle2007(b, map_style,1,null,null));
			}
						
		}
		
	}
			
	/***************************************各廠月報表彙總************************************************************/
	
	
	
	/***************************************各廠日報表彙總************************************************************/
	public void print_tw2() throws IOException{
		XSSFWorkbook wb=new XSSFWorkbook();		
		Map<String,Object>map_style=GlobalMethod.findStyles2007(wb);				
		List<VWebobjBObj>list_source=webobjbservices.findObjByDay(yymmdd);
		List<VWebobjBObj3>list_source2=webobjbservices.findByVwebobjb3(yymmdd);
		Map<String,Object>map=new LinkedHashMap<String,Object>();
						
		//List<WebFact>facts=webFactSer.findFactAble();//所有有效廠別
		//List<WebFact>facts=webFactSer.findAllFact_showA();//與重點指標報表相同
		List<WebFact>facts=webFactSer.findAllFact_2();//與產量報表相同
		List<String>factcodes=new ArrayList<String>();
		List<String>factnos=new ArrayList<String>();
		List<VWebobjBObj>list_obj=new LinkedList<VWebobjBObj>();
		List<VWebobjBObj3>list_obj2=new LinkedList<VWebobjBObj3>();
		for(WebFact fact:facts){
			factcodes.add(fact.getId().getFactArea());
			list_obj.add(new VWebobjBObj(new VWebobjBObjId(fact,yymmdd)));
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
			for(VWebobjBObj obj:list_source){
				if(list_obj.get(a).getId().getWebFact().getId().getFactArea().equals(obj.getId().getWebFact().getId().getFactArea())&&
						list_obj.get(a).getId().getWebFact().getId().getFactNo().equals(obj.getId().getWebFact().getId().getFactNo())){
					list_obj.set(a, obj);
				}
			}
		}
		
		for(String factcode:factcodes){
			List<VWebobjBObj>list=new ArrayList<VWebobjBObj>();
			for(VWebobjBObj obj:list_obj){
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
			list_obj2.add(new VWebobjBObj3(new VWebobjBObj3Id(new VWebFact(factno),yymm)));
		}
		
		for(VWebobjBObj3 obj:list_obj2){
			for(WebFact fact:facts){
				if(obj.getId().getFact().getFactNo().equals(fact.getId().getFactNo())){
					obj.getId().getFact().setFactSname(fact.getFactSname());
				}
			}
		}
		
		for(int a=0;a<list_obj2.size();a++){
			for(VWebobjBObj3 obj:list_source2){
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
	
	
	
public void init_more2(XSSFSheet sheet,Map<String,Object>map,Map<String,Object>map_style,List<VWebobjBObj3>list_obj2) throws IOException{				   
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
			List<VWebobjBObj>list_obj=(List<VWebobjBObj>)map.get(factcode);	
			
			/***********************************MD    DJ  換行**********************************/
			if("MD".equals(factcode)||"DJ".equals(factcode)){
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
			/***********************************MD    DJ  換行**********************************/
			
			int length=list_obj.size();			
			sheet.getRow(1+temp1_1).getCell(3+temp).setCellValue(factcode);
			CellRangeAddress cra1=new CellRangeAddress(1+temp1_1,1+temp1_1,3+temp,(short)2+temp+length);
			sheet.addMergedRegion(cra1);
			for(int i=0;i<length;i++){
				sheet.getRow(1+temp1_1).getCell(3+temp+i).setCellStyle(cs_head2);
			}						
			
			for(int a=0;a<list_obj.size();a++){
				sheet.getRow(2+temp1_1).getCell(3+a+temp).setCellValue(list_obj.get(a).getId().getWebFact().getFactSname());
				sheet.getRow(2+temp1_1).getCell(3+a+temp).setCellStyle(cs_head);
				List<Double>list_db=this.objToDouble(list_obj.get(a));
				for(int b=0;b<list_db.size();b++){									
					this.selectValue_db(sheet.getRow(3+b+temp1_1).getCell(3+a+temp), list_obj.get(a).getWorkorholiday(), list_db.get(b));
					sheet.getRow(3+b+temp1_1).getCell(3+a+temp).setCellStyle(this.selectStyle2007(b, map_style,0,list_obj.get(a).getWorkorholiday(),list_db.get(b)));
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
			sheet.getRow(temp2+temp1_1).getCell(3+a).setCellStyle(cs_head2);
			List<Long>list_lg=this.objToLong(list_obj2.get(a));
			for(int b=0;b<list_lg.size();b++){
				sheet.getRow(temp2+temp1_1+b+1).getCell(3+a).setCellValue(list_lg.get(b));
				sheet.getRow(temp2+temp1_1+b+1).getCell(3+a).setCellStyle(cs);
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
		List<VWebobjBObj>list_obj=(List<VWebobjBObj>)map.get(factcode);
		
		/***********************************MD    DJ  換行**********************************/
		if("MD".equals(factcode)||"DJ".equals(factcode)){
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
		/***********************************MD    DJ  換行**********************************/
		
		int length=list_obj.size();			
		sheet.getRow(1+temp1_1).getCell(2+temp).setCellValue(factcode);
		CellRangeAddress cra1=new CellRangeAddress(1+temp1_1,(short)1+temp1_1,2+temp,(short)1+temp+length);
		sheet.addMergedRegion(cra1);
		for(int i=0;i<length;i++){
			sheet.getRow(1+temp1_1).getCell(2+temp+i).setCellStyle(cs_head2);
		}				
		
		for(int a=0;a<list_obj.size();a++){
			sheet.getRow(2+temp1_1).getCell(2+a+temp).setCellValue(list_obj.get(a).getId().getWebFact().getFactSname());
			sheet.getRow(2+temp1_1).getCell(2+a+temp).setCellStyle(cs_head);
			List<String>list_str=this.objToString(list_obj.get(a));
			for(int b=0;b<list_str.size();b++){				
				//sheet.getRow(3+temp1_1+b).getCell(2+a+temp).setCellStyle(cs);
				this.selectValue_str(sheet.getRow(3+temp1_1+b).getCell(2+a+temp), list_obj.get(a).getWorkorholiday(), list_str.get(b));
				sheet.getRow(3+temp1_1+b).getCell(2+a+temp).setCellStyle(this.selectStyle2007(b, map_style, 0, list_obj.get(a).getWorkorholiday(), list_str.get(b)));
				
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
				List<VWebobjBObj4>list_obj=(List<VWebobjBObj4>)map.get(factcode);
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
				List<VWebobjBObj4>list_obj=(List<VWebobjBObj4>)map.get(factcode);
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
		list.add("最大孔位數__孔");
		list.add("最大生產數(模/月)__模");
		list.add("有效孔位數__孔");
		list.add("工程樣品孔位__孔");
		list.add("補料孔位__孔");
		list.add("其它孔位__孔");
		list.add("預計生產模數(模/月)__模");
		list.add("預計生產雙數(模/月)__模");
		list.add("預計請款雙數(雙/月)__雙");
		list.add("上模數__模");
		list.add("人數(拉模手)__人");
		list.add("標準產量(模/日)__模");
		list.add("實際產量(模/日)__模");
		list.add("實際產量(雙)__雙");
		list.add("達成率(%)__%");
		list.add("實際回轉數__回");
		list.add("成型不良雙數__雙");
		list.add("成型不良率(%)__%");
		list.add("慢單狀況(張)__張");
		list.add("慢單狀況(雙)__雙");
		list.add("訂單欠數__雙");
		return list;		
	}
	
	public List<String>findItems2(){
		List<String>list=new ArrayList<String>();														
		list.add("直工人數__人");
		list.add("間工人數__人");
		list.add("全廠人數__人");
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
				
	/**
	 * 
	 * @param a
	 * @param map_style
	 * @param type  0 非统计單元格       1統計單元格          (PS：月報表的都採用統計單元格)
	 * @param workorholiday   "0"工作日        "1"假日         "2"未排產
	 * @param ele_data
	 * @return
	 */
	public XSSFCellStyle selectStyle2007(int a,Map<String,Object>map_style,int type,String workorholiday,Object ele_data){				
		XSSFCellStyle cs=(XSSFCellStyle)map_style.get("cs");
		switch(type){
		case 0:
			if("0".equals(workorholiday)){//工作日
				if(ele_data==null||ele_data.equals(0.0)||"null".equals(ele_data)||"無".equals(ele_data)){//待補
					cs=(XSSFCellStyle)map_style.get("cs_font_red");
				}else{
					if(a<4||a==7||a==8){
						cs=(XSSFCellStyle)map_style.get("cs_poi");
					}else if(a==14||a==17){
						cs=(XSSFCellStyle)map_style.get("cs_percent");
					}else{
						cs=(XSSFCellStyle)map_style.get("cs_poi1");
					}
				}
			
			}
			if("1".equals(workorholiday)){//假日
				cs=(XSSFCellStyle)map_style.get("cs_font_blue");
			}
			if("2".equals(workorholiday)){//未排产
				cs=(XSSFCellStyle)map_style.get("cs_font_green");
			}
			break;
			
		case 1:
			if(a<4||a==7||a==8){
				cs=(XSSFCellStyle)map_style.get("cs_poi");
			}else if(a==14||a==17){
				cs=(XSSFCellStyle)map_style.get("cs_percent");
			}else{
				cs=(XSSFCellStyle)map_style.get("cs_poi1");
			}
			break;
		}		
		return cs;
	}
	
	public void selectValue_db(XSSFCell cell,String workorholiday,Double ele_data){
		if("0".equals(workorholiday)){
			if(ele_data==null||(Double)ele_data==0){
				cell.setCellValue("待補");
			}
			else{
				cell.setCellValue(ele_data);
			}
		}
		if("1".equals(workorholiday)){
			cell.setCellValue("假日");
		}
		if("2".equals(workorholiday)){
			cell.setCellValue("未排產");
		}
	}
	
	public void selectValue_str(XSSFCell cell,String workorholiday,String ele_data){
		if("0".equals(workorholiday)){
			if(ele_data==null||"null".equals(ele_data)||"無".equals(ele_data)){
				cell.setCellValue("待補");
			}else{
				cell.setCellValue(ele_data);
			}
		}
		if("1".equals(workorholiday)){
			cell.setCellValue("假日");
		}
		if("2".equals(workorholiday)){
			cell.setCellValue("未排產");
		}
	}		
	
	public Map<String,Object> findStyles2007(XSSFWorkbook wb){		
		Map<String,Object>map=GlobalMethod.findStyles2007(wb);				
		return map;
	}
	

}
