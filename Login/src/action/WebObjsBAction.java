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

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import entity.VWebobjBAll;
import entity.VWebobjBAllFactno;
import entity.VWebobjBAllFactnoId;
import entity.VWebobjBAllFactnoYear;
import entity.VWebobjBAllId;
import entity.VWebobjBAllYear;
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
import entity.WeballobjB;


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
	private WebObjsB obj;
	private String isnull;
	private String year;
    
    
     
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getIsnull() {
		return isnull;
	}

	public void setIsnull(String isnull) {
		this.isnull = isnull;
	}

	public WebObjsB getObj() {
		return obj;
	}

	public void setObj(WebObjsB obj) {
		this.obj = obj;
	}

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

	
	
	public String add(){
		try{
			if("0".equals(obj.getWorkorholiday())&&obj.getDaycount()==0){
				obj.setDaycount(1.0);
			}
			if(isnull!=null&&!"".equals(isnull)){
				WebObjsB o=webobjbservices.findById(obj.getId().getWebFact().getId().getFactNo(), obj.getId().getWebFact().getId().getFactArea(), obj.getId().getYymmdd());
				if(o==null){					
					webobjbservices.add(obj);
					ajaxResult="0";//保存成功
				}else{
					ajaxResult="2";//數據已存在
				}
			}else{				
				webobjbservices.add(obj);
				ajaxResult="0";
			}
		}catch(Exception e){
			ajaxResult="1";//保存失敗
			e.printStackTrace();
		}		
		return "add";
	}
	
	public void addMore() throws IOException{
		response.setContentType("text/html;charset=utf-8");
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		try{
			if("0".equals(workorholiday)){
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
						WebObjsB obj=new WebObjsB(new WebObjsBId(fact,yymmdd));								
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
			
			}else{
				List<WebObjsB>list=new ArrayList<WebObjsB>();
				List<String>factcodes=webFactSer.findFactCodeByFactNo_show(factNo);
				for(String factcode:factcodes){
					WebObjsB obj=new WebObjsB(new WebObjsBId(new WebFact(new WebFactId(factNo,factcode)),yymmdd));
					obj.setWorkorholiday(workorholiday);
					obj.setUsername(user.getUsername());
					obj.setDatecreate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
					list.add(obj);
				}
				webobjbservices.addMore(list);
				response.getWriter().print("<script>window.parent.layer.msg('添加成功',3,1)</script>");
			}					
		}catch(Exception e){
			System.out.println(e);
			response.getWriter().print("<script>window.parent.layer.msg('導入錯誤',3,3);</script>");
		}
	}
	
	
	
	public String findPageBean(){
		ActionContext.getContext().getSession().remove("allrow");//dao層
		ActionContext.getContext().getSession().remove("public_yymm");
		ActionContext.getContext().getSession().remove("workorholiday");
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		ActionContext.getContext().getSession().put("public_factno", factNo);
		bean=webobjbservices.findPageBean(20,page, factNo, yymm,workorholiday);
		return "beanList";
		
	}
	public String findPageBean2(){
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		ActionContext.getContext().getSession().remove("allrow");//dao層
		ActionContext.getContext().getSession().put("public_factno", factNo.split("__")[0]);
		ActionContext.getContext().getSession().put("public_yymm", yymm);	
		ActionContext.getContext().getSession().put("workorholiday", workorholiday);
		bean=webobjbservices.findPageBean(20,page, factNo.split("__")[0], yymm,workorholiday);
		return "beanList1";
	}
	public String findPageBean3(){
		factNo=(String)ActionContext.getContext().getSession().get("public_factno");
		yymm=(String)ActionContext.getContext().getSession().get("public_yymm");
		workorholiday=(String)ActionContext.getContext().getSession().get("workorholiday");
		bean=webobjbservices.findPageBean(20,page, factNo, yymm,workorholiday);
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
	
	public String findById(){
		obj=webobjbservices.findById(factNo, factCode, yymmdd);
		return "findById";
	}
	
	
	
	/*************************************************工廠報表(一年情況)***********************************************************/
	public void print3() throws ParseException, IOException{
		XSSFWorkbook wb=new XSSFWorkbook();
		Map<String,Object>map_cs=findStyles2007(wb);				
		//List<VWebobjBObj>list_objs=webobjbservices.findByYymm2(factNo.split("__")[0], yymm,workorholiday);
		List<VWebobjBObj4>list_objs=webobjbservices.findVWebobjBObj4(factNo.split("__")[0], year);
			
		switch(list_objs.size()){//switch		
		case 0:
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>window.parent.alert('無數據');</script>");			
			break;
		default:
			
			
			int months=12;//一年的月份數
			List<String>list_factcodes=new ArrayList<String>();
			for(VWebobjBObj4 obj:list_objs){
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
			Map<String,Object>map3=new LinkedHashMap<String,Object>();
			List<VWebobjBObj4>list_lg=new ArrayList<VWebobjBObj4>();
			VWebobjBObj4 obj_allfact=new VWebobjBObj4();
			for(int a=0;a<months;a++){
				list_lg.add(new VWebobjBObj4());
			}
			for(String factcode:list_factcodes){
				String temp="";
				List<VWebobjBObj4>list=new ArrayList<VWebobjBObj4>();
				for(int a=1;a<=months;a++){				
					if(a>9){
						temp=year+a;
					}else{
						temp=year+"0"+a;
					}
					list.add(new VWebobjBObj4(new VWebobjBObj4Id(new WebFact(new WebFactId(factNo.split("__")[0],factcode)),temp)));
					temp="";
				}
				
				int work_months=0;
				for(int a=0;a<list.size();a++){
					for(VWebobjBObj4 obj:list_objs){
						if(list.get(a).getId().getWebFact().getId().getFactArea().equals(obj.getId().getWebFact().getId().getFactArea())&&
								list.get(a).getId().getWebFact().getId().getFactNo().equals(obj.getId().getWebFact().getId().getFactNo())&&
								list.get(a).getId().getYymm().equals(obj.getId().getYymm())){
							list.set(a, obj);
							work_months++;
							break;
							
						}
					}
				}				
				map.put(factcode, list);
				map3.put(factcode, work_months);
				
				for(int a=0;a<list.size();a++){	
					    list_lg.get(a).setObja5(isMyNull_ll(list_lg.get(a).getObja5())+isMyNull_ll(list.get(a).getObja5()));
						list_lg.get(a).setObja6(isMyNull_ll(list_lg.get(a).getObja6())+isMyNull_ll(list.get(a).getObja6()));
						list_lg.get(a).setObjaE(isMyNull_ll(list_lg.get(a).getObjaE())+isMyNull_ll(list.get(a).getObjaE()));
						list_lg.get(a).setObja7(isMyNull_ll(list_lg.get(a).getObja7())+isMyNull_ll(list.get(a).getObja7()));
						list_lg.get(a).setObja8(isMyNull_ll(list_lg.get(a).getObja8())+isMyNull_ll(list.get(a).getObja8()));
						list_lg.get(a).setObja9(isMyNull_ll(list_lg.get(a).getObja9())+isMyNull_ll(list.get(a).getObja9()));																
				}
				
				VWebobjBObj4 sum_obj=new VWebobjBObj4();
				for(VWebobjBObj4 obj:list){								
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
			for(VWebobjBObj4 obj:list_lg){
				obj_allfact.setObja5(isMyNull_ll(obj_allfact.getObja5())+isMyNull_ll(obj.getObja5()));
				obj_allfact.setObja6(isMyNull_ll(obj_allfact.getObja6())+isMyNull_ll(obj.getObja6()));
				obj_allfact.setObjaE(isMyNull_ll(obj_allfact.getObjaE())+isMyNull_ll(obj.getObjaE()));
				obj_allfact.setObja7(isMyNull_ll(obj_allfact.getObja7())+isMyNull_ll(obj.getObja7()));
				obj_allfact.setObja8(isMyNull_ll(obj_allfact.getObja8())+isMyNull_ll(obj.getObja8()));
				obj_allfact.setObja9(isMyNull_ll(obj_allfact.getObja9())+isMyNull_ll(obj.getObja9()));
			}
		
			XSSFSheet sheet=wb.createSheet(year+"工廠訊息");
			XSSFSheet sheet2=wb.createSheet(year+"提報事項");
			init3(sheet,map_cs,map,list_lg,months,map2,map3,obj_allfact);
			//init2(sheet2,map_cs,map,days);
			
			try {				
				ServletOutputStream os=response.getOutputStream();
				//response.setContentType("application/vnd.ms-excel");
				response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
				int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");//判斷是否為IE瀏覽器,大於0則為IE瀏覽器
				String fileName=factNo.split("__")[0]+"_"+year+"工廠訊息報表"+".xlsx";
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
	
								
	public void init3(XSSFSheet sheet,Map<String,Object>map_cs,Map<String,Object>map,List<VWebobjBObj4>list_lg,int months,Map<String,Object>map2,Map<String,Object>map3,VWebobjBObj4 obj_allfact) throws ParseException{				
		XSSFCellStyle cs=(XSSFCellStyle)map_cs.get("cs");
		XSSFCellStyle cs_head=(XSSFCellStyle)map_cs.get("cs_head");
		XSSFCellStyle cs_title=(XSSFCellStyle)map_cs.get("cs_title");		
		List<String>list_col=findItems();
		List<String>list_col2=findItems2();
			sheet.setColumnWidth(1, 5000);
			for(int a=0;a<list_col.size()*map.size()+20;a++){
				sheet.createRow(a);				
				for(int b=0;b<months+5;b++){
					sheet.getRow(a).createCell(b);					
				}
			}
			
			CellRangeAddress cra_title=new CellRangeAddress(0,0,0,4);
			sheet.addMergedRegion(cra_title);
			sheet.getRow(0).getCell(0).setCellValue(factNo.split("__")[1]+"-"+year+"工廠訊息");
			for(int i=0;i<4;i++){
				sheet.getRow(0).getCell(i).setCellStyle(cs_title);
			}
						
			int temp=2;
			for(String factcode:map.keySet()){
				
				CellRangeAddress cra_date=new CellRangeAddress(temp,temp,0,1);
				sheet.addMergedRegion(cra_date);
				sheet.getRow(temp).getCell(0).setCellValue(year);
				for(int i=0;i<2;i++){
					sheet.getRow(temp).getCell(i).setCellStyle(cs_head);
				}
				for(int a=0;a<months;a++){
					sheet.getRow(temp).getCell(a+2).setCellValue(a+1+"月");
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
				List<VWebobjBObj4>list=(List<VWebobjBObj4>)map.get(factcode);					
				for(int a=0;a<list.size();a++){						
					List<Double>list_db=objToDouble(list.get(a));
					for(int b=0;b<list_db.size();b++){
						sheet.getRow(b+temp+1).getCell(a+2).setCellValue(list_db.get(b));
						//this.selectValue_db(sheet.getRow(b+temp+1).getCell(a+2), list.get(a).getWorkorholiday(), list_db.get(b));
						sheet.getRow(b+temp+1).getCell(a+2).setCellStyle(this.selectStyle2007(b, map_cs,1,null,list_db.get(b),list_col));
					}																
				}
				
				sheet.getRow(temp).getCell(2+months).setCellValue("總計");
				sheet.getRow(temp).getCell(3+months).setCellValue("平均");
				for(int a=0;a<2;a++){
					sheet.getRow(temp).getCell(2+months+a).setCellStyle(cs_head);
				}
				List<Double>list_sum=objToDouble((VWebobjBObj4)map2.get(factcode));
				int work_months=(Integer)map3.get(factcode);
				for(int b=0;b<list_sum.size();b++){
					sheet.getRow(b+temp+1).getCell(2+months).setCellValue(list_sum.get(b));
					sheet.getRow(b+temp+1).getCell(3+months).setCellValue(list_sum.get(b)/work_months);
					sheet.getRow(b+temp+1).getCell(2+months).setCellStyle(this.selectStyle2007(b, map_cs,1,null,null,list_col));
					sheet.getRow(b+temp+1).getCell(3+months).setCellStyle(this.selectStyle2007(b, map_cs,1,null,null,list_col));
				}
				
				temp=temp+list_col.size()+1;
			}	
			
			
			
			
			
			CellRangeAddress cra_date=new CellRangeAddress(temp,temp,0,1);
			sheet.addMergedRegion(cra_date);
			sheet.getRow(temp).getCell(0).setCellValue(year);
			for(int i=0;i<2;i++){
				sheet.getRow(temp).getCell(i).setCellStyle(cs_head);
			}
			for(int a=0;a<months;a++){
				sheet.getRow(temp).getCell(a+2).setCellValue(a+1+"月");
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
			
			sheet.getRow(temp).getCell(2+months).setCellValue("總計");
			sheet.getRow(temp).getCell(3+months).setCellValue("平均");
			for(int a=0;a<2;a++){
				sheet.getRow(temp).getCell(2+months+a).setCellStyle(cs_head);
			}
			List<Long>ll=this.objToLong(obj_allfact);
			for(int b=0;b<ll.size();b++){
				sheet.getRow(temp+1+b).getCell(2+months).setCellValue(ll.get(b));
				sheet.getRow(temp+1+b).getCell(3+months).setCellValue(ll.get(b)/months);
				sheet.getRow(temp+1+b).getCell(2+months).setCellStyle(this.selectStyle2007(b, map_cs,1,null,null,list_col2));
				sheet.getRow(temp+1+b).getCell(3+months).setCellStyle(this.selectStyle2007(b, map_cs,1,null,null,list_col2));
			}
	}
	/*************************************************工廠報表(一年情況)***********************************************************/
	
	
	/*************************************************工廠報表(一年情況)__改版***********************************************************/
	public void print3_1() throws ParseException, IOException{
		XSSFWorkbook wb=new XSSFWorkbook();
		Map<String,Object>map_cs=findStyles2007(wb);				
		//List<VWebobjBObj>list_objs=webobjbservices.findByYymm2(factNo.split("__")[0], yymm,workorholiday);
		List<VWebobjBAll>list_objs=webobjbservices.findVWebobjBAll(factNo.split("__")[0], year);
		List<VWebobjBAllYear>list_objs2=webobjbservices.findVWebobjBAllYear(factNo.split("__")[0], year);
		List<VWebobjBAllFactno>list_objs3=webobjbservices.findVWebobjBAllFactno(factNo.split("__")[0], year);
		VWebobjBAllFactnoYear objs4=webobjbservices.findVWebobjBAllFactnoYear(factNo.split("__")[0], year);
		List<VWebobjBAllYear>list_objs2_lyear=webobjbservices.findVWebobjBAllYear(factNo.split("__")[0], Integer.parseInt(year)-1+"");
		VWebobjBAllFactnoYear objs4_lyear=webobjbservices.findVWebobjBAllFactnoYear(factNo.split("__")[0], Integer.parseInt(year)-1+"");
		
			
		switch(list_objs.size()){//switch		
		case 0:
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>window.parent.alert('無數據');</script>");			
			break;
		default:
			
			
			int months=12;//一年的月份數
			List<String>list_factcodes=new ArrayList<String>();
			for(VWebobjBAll obj:list_objs){
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
			Map<String,Object>map2_lyear=new LinkedHashMap<String,Object>();
			List<VWebobjBAllFactno>list_lg=new ArrayList<VWebobjBAllFactno>();						
									
			for(String factcode:list_factcodes){
				String temp="";
				List<VWebobjBAll>list=new ArrayList<VWebobjBAll>();
				for(int a=1;a<=months;a++){				
					if(a>9){
						temp=year+a;
					}else{
						temp=year+"0"+a;
					}
					list.add(new VWebobjBAll(new VWebobjBAllId(new WebFact(new WebFactId(factNo.split("__")[0],factcode)),temp)));
					temp="";
				}
				
				for(int a=0;a<list.size();a++){
					for(VWebobjBAll obj:list_objs){
						if(list.get(a).getId().getWebFact().getId().getFactArea().equals(obj.getId().getWebFact().getId().getFactArea())&&
								list.get(a).getId().getWebFact().getId().getFactNo().equals(obj.getId().getWebFact().getId().getFactNo())&&
								list.get(a).getId().getYymm().equals(obj.getId().getYymm())){
							list.set(a, obj);
							break;
							
						}
					}
				}				
				map.put(factcode, list);
				
				for(int a=0;a<list_objs2.size();a++){
					if(factcode.equals(list_objs2.get(a).getId().getWebFact().getId().getFactArea())){
						map2.put(factcode, list_objs2.get(a));
						break;
					}else if(a==list_objs2.size()-1){
						map2.put(factcode, new VWebobjBAllYear());
					}
				}
				
				if(list_objs2_lyear==null||list_objs2_lyear.size()==0){
					map2_lyear.put(factcode, new VWebobjBAllYear());
				}else{
					for(int a=0;a<list_objs2_lyear.size();a++){
						if(factcode.equals(list_objs2_lyear.get(a).getId().getWebFact().getId().getFactArea())){
							map2_lyear.put(factcode, list_objs2_lyear.get(a));
							break;
						}else if(a==list_objs2_lyear.size()-1){
							map2_lyear.put(factcode, new VWebobjBAllYear());
						}
					}
				}
				
				
				
				
			}
			
			for(int a=1;a<=months;a++){
				String temp=null;
				if(a<10){
					temp=year+"0"+a;
				}else{
					temp=year+a;
				}
				list_lg.add(new VWebobjBAllFactno(new VWebobjBAllFactnoId(new VWebFact(factNo),temp)));
				temp=null;
			}
			for(int a=0;a<list_lg.size();a++){
				for(VWebobjBAllFactno obj:list_objs3){
					if(list_lg.get(a).getId().getYymm().equals(obj.getId().getYymm())){
						list_lg.set(a, obj);
						break;
					}
				}
			}
			
			if(objs4==null){
				objs4=new VWebobjBAllFactnoYear();				
			}
			if(objs4_lyear==null){
				objs4_lyear=new VWebobjBAllFactnoYear();
			}
			
			
											
			XSSFSheet sheet=wb.createSheet(year+"工廠訊息");
			XSSFSheet sheet2=wb.createSheet(year+"提報事項");
			init3_1(sheet,map_cs,map,list_lg,months,map2,map2_lyear,objs4,objs4_lyear);
			//init2(sheet2,map_cs,map,days);
			
			try {				
				ServletOutputStream os=response.getOutputStream();
				//response.setContentType("application/vnd.ms-excel");
				response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
				int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");//判斷是否為IE瀏覽器,大於0則為IE瀏覽器
				String fileName=factNo.split("__")[0]+"_"+year+"工廠訊息報表"+".xlsx";
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
	
								
	public void init3_1(XSSFSheet sheet,Map<String,Object>map_cs,Map<String,Object>map,List<VWebobjBAllFactno>list_lg,int months,Map<String,Object>map2,Map<String,Object>map2_lyear,VWebobjBAllFactnoYear obj4,VWebobjBAllFactnoYear obj4_lyear) throws ParseException{				
		XSSFCellStyle cs=(XSSFCellStyle)map_cs.get("cs");
		XSSFCellStyle cs_head=(XSSFCellStyle)map_cs.get("cs_head");
		XSSFCellStyle cs_title=(XSSFCellStyle)map_cs.get("cs_title");
		XSSFCellStyle cs_percent1=(XSSFCellStyle)map_cs.get("cs_percent1");
		List<String>list_col=findItems_new();
		List<String>list_col2=findItems2_new();
			sheet.setColumnWidth(1, 5000);
			for(int a=14;a<22;a++){
				sheet.setColumnWidth(a, 3500);
			}
			for(int a=0;a<list_col.size()*map.size()+20;a++){
				sheet.createRow(a);				
				for(int b=0;b<months+10;b++){
					sheet.getRow(a).createCell(b);					
				}
			}
			
			CellRangeAddress cra_title=new CellRangeAddress(0,0,0,4);
			sheet.addMergedRegion(cra_title);
			sheet.getRow(0).getCell(0).setCellValue(factNo.split("__")[1]+"-"+year+"工廠訊息");
			for(int i=0;i<4;i++){
				sheet.getRow(0).getCell(i).setCellStyle(cs_title);
			}
						
			int temp=2;
			for(String factcode:map.keySet()){
				
				CellRangeAddress cra_date=new CellRangeAddress(temp,temp,0,1);
				sheet.addMergedRegion(cra_date);
				sheet.getRow(temp).getCell(0).setCellValue(year);
				for(int i=0;i<2;i++){
					sheet.getRow(temp).getCell(i).setCellStyle(cs_head);
				}
				for(int a=0;a<months;a++){
					sheet.getRow(temp).getCell(a+2).setCellValue(a+1+"月");
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
				List<VWebobjBAllYear>list=(List<VWebobjBAllYear>)map.get(factcode);					
				for(int a=0;a<list.size();a++){						
					List<Double>list_db=objToDouble(list.get(a));
					for(int b=0;b<list_db.size();b++){
						sheet.getRow(b+temp+1).getCell(a+2).setCellValue(list_db.get(b));						
						sheet.getRow(b+temp+1).getCell(a+2).setCellStyle(this.selectStyle2007(b, map_cs,1,null,list_db.get(b),list_col));
					}																
				}
				
				sheet.getRow(temp).getCell(2+months).setCellValue("今年平均");
				sheet.getRow(temp).getCell(3+months).setCellValue("去年平均");
				sheet.getRow(temp).getCell(4+months).setCellValue("平均差異");
				sheet.getRow(temp).getCell(5+months).setCellValue("平均差異率");
				
				sheet.getRow(temp).getCell(6+months).setCellValue("今年總計");
                sheet.getRow(temp).getCell(7+months).setCellValue("去年總計");
				sheet.getRow(temp).getCell(8+months).setCellValue("合計差異");				
				sheet.getRow(temp).getCell(9+months).setCellValue("合計差異率");
								
				for(int a=0;a<8;a++){
					sheet.getRow(temp).getCell(2+months+a).setCellStyle(cs_head);
				}
				List<Double>list_sum=objToDouble((VWebobjBAllYear)map2.get(factcode));
				List<Double>list_sum_lyear=objToDouble((VWebobjBAllYear)map2_lyear.get(factcode));
				for(int b=0;b<list_sum.size();b++){
					for(int c=0;c<8;c++){
						sheet.getRow(b+temp+1).getCell(2+months+c).setCellStyle(this.selectStyle2007(b, map_cs,1,null,null,list_col));
					}
					
					sheet.getRow(b+temp+1).getCell(2+months).setCellValue(GlobalMethod.division(list_sum.get(b), ((VWebobjBAllYear)map2.get(factcode)).getMonths()));
					sheet.getRow(b+temp+1).getCell(3+months).setCellValue(GlobalMethod.division(list_sum_lyear.get(b),((VWebobjBAllYear)map2_lyear.get(factcode)).getMonths()));					
					sheet.getRow(b+temp+1).getCell(4+months).setCellValue(
							sheet.getRow(b+temp+1).getCell(2+months).getNumericCellValue()-	
							sheet.getRow(b+temp+1).getCell(3+months).getNumericCellValue()
                    );
					sheet.getRow(b+temp+1).getCell(5+months).setCellValue(
							GlobalMethod.division(sheet.getRow(b+temp+1).getCell(4+months).getNumericCellValue(),
									sheet.getRow(b+temp+1).getCell(3+months).getNumericCellValue())
                    );
					sheet.getRow(b+temp+1).getCell(5+months).setCellStyle(cs_percent1);
					
					
					sheet.getRow(b+temp+1).getCell(6+months).setCellValue(list_sum.get(b));
					sheet.getRow(b+temp+1).getCell(7+months).setCellValue(list_sum_lyear.get(b));
					sheet.getRow(b+temp+1).getCell(8+months).setCellValue(
							sheet.getRow(b+temp+1).getCell(6+months).getNumericCellValue()-	
							sheet.getRow(b+temp+1).getCell(7+months).getNumericCellValue()
                    );										
					sheet.getRow(b+temp+1).getCell(9+months).setCellValue(
							GlobalMethod.division(sheet.getRow(b+temp+1).getCell(8+months).getNumericCellValue(),
							sheet.getRow(b+temp+1).getCell(7+months).getNumericCellValue())
                    );
					sheet.getRow(b+temp+1).getCell(9+months).setCellStyle(cs_percent1);											
				}				
				temp=temp+list_col.size()+1;
			}	
			
			
			
			
			
			CellRangeAddress cra_date=new CellRangeAddress(temp,temp,0,1);
			sheet.addMergedRegion(cra_date);
			sheet.getRow(temp).getCell(0).setCellValue(year);
			for(int i=0;i<2;i++){
				sheet.getRow(temp).getCell(i).setCellStyle(cs_head);
			}
			for(int a=0;a<months;a++){
				sheet.getRow(temp).getCell(a+2).setCellValue(a+1+"月");
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
									
			sheet.getRow(temp).getCell(2+months).setCellValue("今年平均");
			sheet.getRow(temp).getCell(3+months).setCellValue("去年平均");
			sheet.getRow(temp).getCell(4+months).setCellValue("平均差異");
			sheet.getRow(temp).getCell(5+months).setCellValue("平均差異率");
			
			sheet.getRow(temp).getCell(6+months).setCellValue("今年總計");
            sheet.getRow(temp).getCell(7+months).setCellValue("去年總計");
			sheet.getRow(temp).getCell(8+months).setCellValue("合計差異");				
			sheet.getRow(temp).getCell(9+months).setCellValue("合計差異率");
			
			for(int a=0;a<8;a++){
				sheet.getRow(temp).getCell(2+months+a).setCellStyle(cs_head);
			}
			List<Long>ll=this.objToLong(obj4);
			List<Long>ll_lyear=this.objToLong(obj4_lyear);
			for(int b=0;b<ll.size();b++){
				
				for(int c=0;c<8;c++){
					sheet.getRow(temp+1+b).getCell(2+months+c).setCellStyle(this.selectStyle2007(b, map_cs,1,null,null,list_col));
				}
				sheet.getRow(temp+1+b).getCell(2+months).setCellValue(GlobalMethod.division_lg(ll.get(b),obj4.getMonths()));
				sheet.getRow(temp+1+b).getCell(3+months).setCellValue(GlobalMethod.division_lg(ll_lyear.get(b),obj4_lyear.getMonths()));
				sheet.getRow(temp+1+b).getCell(4+months).setCellValue(
						sheet.getRow(temp+1+b).getCell(2+months).getNumericCellValue()-	
						sheet.getRow(temp+1+b).getCell(3+months).getNumericCellValue()
                );
				sheet.getRow(temp+1+b).getCell(5+months).setCellValue(
						GlobalMethod.division(sheet.getRow(temp+1+b).getCell(4+months).getNumericCellValue(),
								sheet.getRow(temp+1+b).getCell(3+months).getNumericCellValue())
                );
				sheet.getRow(temp+1+b).getCell(5+months).setCellStyle(cs_percent1);
			
				
				sheet.getRow(temp+1+b).getCell(6+months).setCellValue(ll.get(b));
				sheet.getRow(temp+1+b).getCell(7+months).setCellValue(ll_lyear.get(b));	
				sheet.getRow(temp+1+b).getCell(8+months).setCellValue(
						sheet.getRow(temp+1+b).getCell(6+months).getNumericCellValue()-	
						sheet.getRow(temp+1+b).getCell(7+months).getNumericCellValue()
                );				
				sheet.getRow(temp+1+b).getCell(9+months).setCellValue(
						GlobalMethod.division(sheet.getRow(temp+1+b).getCell(8+months).getNumericCellValue(),
						sheet.getRow(temp+1+b).getCell(7+months).getNumericCellValue())
                );
				sheet.getRow(temp+1+b).getCell(9+months).setCellStyle(cs_percent1);
				
				
				
				
			}
	}
	/*************************************************工廠報表(一年情況)__改版***********************************************************/
	
	
	/*************************************************工廠報表(一個月每天情況)***********************************************************/
	public void print() throws ParseException, IOException{
		XSSFWorkbook wb=new XSSFWorkbook();
		Map<String,Object>map_cs=findStyles2007(wb);				
		List<VWebobjBObj>list_objs=webobjbservices.findByYymm2(factNo.split("__")[0], yymm,workorholiday);
			
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
			Map<String,Object>map3=new LinkedHashMap<String,Object>();
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
				Double work_days=0.0;
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
					if("0".equals(obj.getWorkorholiday())){
						work_days++;
					}					
				}
				map2.put(factcode, sum_obj);
				map3.put(factcode, work_days);
				
								
			}
			
		
			XSSFSheet sheet=wb.createSheet(yymm+"工廠訊息");
			XSSFSheet sheet2=wb.createSheet(yymm+"提報事項");
			init(sheet,map_cs,map,list_lg,days,map2,map3);
			init2(sheet2,map_cs,map,days);
			
			try {				
				ServletOutputStream os=response.getOutputStream();
				//response.setContentType("application/vnd.ms-excel");
				response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
				int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");//判斷是否為IE瀏覽器,大於0則為IE瀏覽器
				String fileName=factNo.split("__")[0]+"_"+yymm+"工廠訊息報表"+".xlsx";
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
	
								
	public void init(XSSFSheet sheet,Map<String,Object>map_cs,Map<String,Object>map,List<VWebobjBObj>list_lg,int days,Map<String,Object>map2,Map<String,Object>map3) throws ParseException{				
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
						sheet.getRow(b+temp+1).getCell(a+2).setCellStyle(this.selectStyle2007(b, map_cs,0,list.get(a).getWorkorholiday(),list_db.get(b),list_col));
					}																
				}
				
				sheet.getRow(temp).getCell(2+days).setCellValue("總計");
				sheet.getRow(temp).getCell(3+days).setCellValue("平均");
				for(int a=0;a<2;a++){
					sheet.getRow(temp).getCell(2+days+a).setCellStyle(cs_head);
				}
				List<Double>list_sum=objToDouble((VWebobjBObj)map2.get(factcode));
				Double work_days=(Double)map3.get(factcode);
				for(int b=0;b<list_sum.size();b++){
					sheet.getRow(b+temp+1).getCell(2+days).setCellValue(list_sum.get(b));
					sheet.getRow(b+temp+1).getCell(3+days).setCellValue(list_sum.get(b)/work_days);
					sheet.getRow(b+temp+1).getCell(2+days).setCellStyle(this.selectStyle2007(b, map_cs,1,null,null,list_col));
					sheet.getRow(b+temp+1).getCell(3+days).setCellStyle(this.selectStyle2007(b, map_cs,1,null,null,list_col));
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
					sheet.getRow(b+temp+1).getCell(a+2).setCellValue(list_db.get(b));//全廠的數據為  各個factCode相加之和，如果要求平均數  就要除facdtCode數量
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
						sheet.getRow(b+temp+1).getCell(a+2).setCellStyle(this.selectStyle2007(b, map_cs, 0, list.get(a).getWorkorholiday(), list_str.get(b),list_col));
					}																
				}										
				temp=temp+list_col.size()+1;
			}			
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
					sheet.getRow(3+b+temp1_1).getCell(3+a+temp).setCellStyle(this.selectStyle2007(b, map_style,1,null,null,list_items));
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
				sheet.getRow(1+temp2+temp1_1+b).getCell(3+a).setCellStyle(this.selectStyle2007(b, map_style,1,null,null,list_items2));
			}
						
		}
		
	}
			
	/***************************************各廠月報表彙總************************************************************/
	
	
	
	/***************************************各廠日報表彙總************************************************************/
	public void print_tw2() throws IOException{
		XSSFWorkbook wb=new XSSFWorkbook();		
		Map<String,Object>map_style=GlobalMethod.findStyles2007(wb);				
		List<VWebobjBObj>list_source=webobjbservices.findObjByDay(yymmdd,workorholiday);
		List<VWebobjBObj3>list_source2=webobjbservices.findByVwebobjb3(yymmdd,workorholiday);
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
					sheet.getRow(3+b+temp1_1).getCell(3+a+temp).setCellStyle(this.selectStyle2007(b, map_style,0,list_obj.get(a).getWorkorholiday(),list_db.get(b),list_items));
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
				sheet.getRow(3+temp1_1+b).getCell(2+a+temp).setCellStyle(this.selectStyle2007(b, map_style, 0, list_obj.get(a).getWorkorholiday(), list_str.get(b),list_items));
				
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
		list.add("最大孔位數__孔__cs_poi");
		list.add("最大生產數(模/月)__模__cs_poi");
		list.add("有效孔位數__孔__cs_poi");
		list.add("工程樣品孔位__孔__cs_poi");
		list.add("補料孔位__孔__cs_poi");
		list.add("其它孔位__孔__cs_poi");
		list.add("預計生產模數(模/月)__模__cs_poi");
		list.add("預計生產雙數(模/月)__模__cs_poi");
		list.add("預計請款雙數(雙/月)__雙__cs_poi");
		list.add("上模數__模__cs_poi");
		list.add("人數(拉模手)__人__cs_poi");
		list.add("標準產量(模/日)__模__cs_poi");
		list.add("實際產量(模/日)__模__cs_poi");
		list.add("實際產量(雙)__雙__cs_poi");
		list.add("達成率(%)__%__cs_percent1");
		list.add("實際回轉數__回__cs_poi");
		list.add("成型不良雙數__雙__cs_poi");
		list.add("成型不良率(%)__%__cs_percent1");
		list.add("慢單狀況(張)__張__cs_poi");
		list.add("慢單狀況(雙)__雙__cs_poi");
		list.add("訂單欠數__雙__cs_poi");		
		return list;		
	}
	
	public List<String>findItems2(){
		List<String>list=new ArrayList<String>();														
		list.add("直工人數__人__cs_poi");
		list.add("間工人數__人__cs_poi");
		list.add("全廠人數__人__cs_poi");
		list.add("招工數__人__cs_poi");
		list.add("離職數__人__cs_poi");
		list.add("請假數__人__cs_poi");
		return list;		
	}
	
	public List<String> findItems3(){
		List<String>list=new ArrayList<String>();			
		list.add("品質問題與客訴__文字__cs");
		list.add("扣款訊息__文字__cs");
		list.add("機台狀況/異常__文字__cs");
		list.add("客人來訪訊息__文字__cs");
		list.add("其他提報事項__文字__cs");					
		return list;
	}
	
	public List<String>findItems_new(){
		List<String>list=new ArrayList<String>();																
		list.add("最大孔位數__孔__cs_poi");
		list.add("最大生產數(模/月)__模__cs_poi");
		list.add("有效孔位數__孔__cs_poi");
		list.add("工程樣品孔位__孔__cs_poi");
		list.add("補料孔位__孔__cs_poi");
		list.add("其它孔位__孔__cs_poi");
		list.add("實際請款雙數(雙/月)__雙__cs_poi");
		list.add("實際請款金額(USD)__USD__cs_poi");
		list.add("實際生產模數(模/月)__模__cs_poi");
		list.add("實際生產雙數(雙/月)__雙__cs_poi");
		list.add("正批生產雙數(雙)/月__雙__cs_poi");
		list.add("客補生產雙數(雙)/月__雙__cs_poi");
		list.add("廠補生產雙數(雙)/月__雙__cs_poi");
		list.add("樣品生產雙數(雙)/月__雙__cs_poi");
		list.add("月平均_上模數__模__cs_poi");
		list.add("月平均_人數(拉模手)__人__cs_poi");
		list.add("月平均_標準產量(模/日)__模__cs_poi");
		list.add("月平均_實際產量(模/日)__模__cs_poi");
		list.add("月平均_實際產量(雙)__雙__cs_poi");
		list.add("月達成率(%)__%__cs_percent1");
		list.add("月實際回轉數__回__cs_poi");
		list.add("月平均_慢單狀況(張)__張__cs_poi");
		list.add("月平均_慢單狀況(雙)__雙__cs_poi");
		list.add("當月月底之訂單欠數__雙__cs_poi");
		list.add("機台利用率__%__cs_percent1");
		list.add("直工人均產能__模__cs_poi");
		list.add("全廠人均產能__模__cs_poi");
		list.add("加班費__USD__cs");
		list.add("成本率__%__cs_percent1");
		list.add("回頭率__%__cs_percent1");
		list.add("總損耗__%__cs_percent1");
		list.add("平均邊料重__G/雙__cs");
		list.add("邊料率__%__cs_percent1");
		list.add("不良率__%__cs_percent1");
		list.add("退貨率__%__cs_percent1");
		list.add("用水單耗__USD/模__cs");
		list.add("用電單耗__USD/模__cs");
		list.add("蒸汽單耗__USD/模__cs");
		list.add("蒸汽單耗__KG/模__cs");
		list.add("色料藥品單耗__G/雙__cs");
		list.add("色料藥品單耗__USD/雙__cs");

		return list;		
	}
	
	public List<String>findItems2_new(){
		List<String>list=new ArrayList<String>();	
		list.add("招工數__人");
		list.add("離職數__人");
		list.add("直工人數__人");
		list.add("間工人數__人");
		list.add("全廠人數__人");
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
		if(obj instanceof VWebobjBAll){
			list.add(isMyNull_db(((VWebobjBAll)obj).getTotalhole()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getMachinepower()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getHole()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getSample()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getAccessories()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getOther()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getSumRealcashoutpairs()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getSumRealcashoutmoney()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getSumActualyield()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getFormulaA()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getSumZpobja()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getSumHostpairs()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getSumFactpairs()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getSumSamplepairs()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getAvgOnmodulus()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getAvgPersonnum()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getAvgStandardoutput()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getAvgActualyield()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getFormulaB()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getFormulaC()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getFormulaD()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getAvgObjA2()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getAvgObjA3()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getObjA4()));
			/*list.add(isMyNull_db(((VWebobjBAll)obj).getSumObjA7()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getSumObjA8()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getCObjA6()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getCObjA7()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getCObjA8()));*/
			list.add(isMyNull_db(((VWebobjBAll)obj).getCObjA2()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getCObjA10()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getCObjA11()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getCObjA14()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getCObjA15()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getCObjA16()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getCObjA17()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getCObjA18()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getCObjA19()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getCObjA21()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getCObjA22()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getCObjA24()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getCObjA25()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getCObjA26()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getCObjA27()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getCObjA28()));
			list.add(isMyNull_db(((VWebobjBAll)obj).getCObjA29()));
		}
		if(obj instanceof VWebobjBAllYear){
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getTotalhole()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getMachinepower()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getHole()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getSample()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getAccessories()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getOther()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getSumRealcashoutpairs()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getSumRealcashoutmoney()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getSumActualyield()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getFormulaA()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getSumZpobja()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getSumHostpairs()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getSumFactpairs()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getSumSamplepairs()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getAvgOnmodulus()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getAvgPersonnum()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getAvgStandardoutput()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getAvgActualyield()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getFormulaB()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getFormulaC()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getFormulaD()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getAvgObjA2()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getAvgObjA3()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getObjA4()));
			/*list.add(isMyNull_db(((VWebobjBAllYear)obj).getSumObjA7()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getSumObjA8()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getCObjA6()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getCObjA7()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getCObjA8()));*/
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getCObjA2()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getCObjA10()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getCObjA11()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getCObjA14()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getCObjA15()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getCObjA16()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getCObjA17()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getCObjA18()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getCObjA19()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getCObjA21()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getCObjA22()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getCObjA24()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getCObjA25()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getCObjA26()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getCObjA27()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getCObjA28()));
			list.add(isMyNull_db(((VWebobjBAllYear)obj).getCObjA29()));
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
		if(obj instanceof VWebobjBObj4){
			list.add(isMyNull_ll(((VWebobjBObj4) obj).getObja5()));
			list.add(isMyNull_ll(((VWebobjBObj4) obj).getObja6()));
			list.add(isMyNull_ll(((VWebobjBObj4) obj).getObjaE()));
			list.add(isMyNull_ll(((VWebobjBObj4) obj).getObja7()));
			list.add(isMyNull_ll(((VWebobjBObj4) obj).getObja8()));
			list.add(isMyNull_ll(((VWebobjBObj4) obj).getObja9()));
		}
		
		if(obj instanceof VWebobjBObj5){
			list.add(isMyNull_ll(((VWebobjBObj5) obj).getObja5()));
			list.add(isMyNull_ll(((VWebobjBObj5) obj).getObja6()));
			list.add(isMyNull_ll(((VWebobjBObj5) obj).getObjaE()));
			list.add(isMyNull_ll(((VWebobjBObj5) obj).getObja7()));
			list.add(isMyNull_ll(((VWebobjBObj5) obj).getObja8()));
			list.add(isMyNull_ll(((VWebobjBObj5) obj).getObja9()));
		}
		if(obj instanceof VWebobjBAll){
			list.add(isMyNull_ll(((VWebobjBAll)obj).getSumObjA7()));
			list.add(isMyNull_ll(((VWebobjBAll)obj).getSumObjA8()));
			list.add(isMyNull_ll(((VWebobjBAll)obj).getCObjA6()));
			list.add(isMyNull_ll(((VWebobjBAll)obj).getCObjA7()));
			list.add(isMyNull_ll(((VWebobjBAll)obj).getCObjA8()));
		}
		if(obj instanceof VWebobjBAllFactno){
			list.add(isMyNull_ll(((VWebobjBAllFactno)obj).getSumObjA7()));
			list.add(isMyNull_ll(((VWebobjBAllFactno)obj).getSumObjA8()));
			list.add(isMyNull_ll(((VWebobjBAllFactno)obj).getCObjA6()));
			list.add(isMyNull_ll(((VWebobjBAllFactno)obj).getCObjA7()));
			list.add(isMyNull_ll(((VWebobjBAllFactno)obj).getCObjA8()));
		}
		if(obj instanceof VWebobjBAllFactnoYear){
			list.add(isMyNull_ll(((VWebobjBAllFactnoYear)obj).getSumObjA7()));
			list.add(isMyNull_ll(((VWebobjBAllFactnoYear)obj).getSumObjA8()));
			list.add(isMyNull_ll(((VWebobjBAllFactnoYear)obj).getCObjA6()));
			list.add(isMyNull_ll(((VWebobjBAllFactnoYear)obj).getCObjA7()));
			list.add(isMyNull_ll(((VWebobjBAllFactnoYear)obj).getCObjA8()));
		}
		return list;		
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
	public XSSFCellStyle selectStyle2007(int a,Map<String,Object>map_style,int type,String workorholiday,Object ele_data,List<String> list_col){				
		XSSFCellStyle cs=(XSSFCellStyle)map_style.get("cs");
		switch(type){
		case 0:
			if("0".equals(workorholiday)){//工作日
				if(ele_data==null||ele_data.equals(0.0)||"null".equals(ele_data)||"無".equals(ele_data)){//待補
					cs=(XSSFCellStyle)map_style.get("cs_font_red");
				}else{
					/*if(a<14){
						cs=(XSSFCellStyle)map_style.get("cs_poi");
					}else if(a==14||a==17){
						cs=(XSSFCellStyle)map_style.get("cs_percent1");
					}else{
						cs=(XSSFCellStyle)map_style.get("cs_poi1");
					}*/
					cs=(XSSFCellStyle)map_style.get(list_col.get(a).split("__")[2]);
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
			/*if(a<14){
				cs=(XSSFCellStyle)map_style.get("cs_poi");
			}else if(a==14||a==17){
				cs=(XSSFCellStyle)map_style.get("cs_percent1");
			}else{
				cs=(XSSFCellStyle)map_style.get("cs_poi1");
			}*/
			cs=(XSSFCellStyle)map_style.get(list_col.get(a).split("__")[2]);
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
	
	public Double minus_db(Double d1,Double d2){
		Double d=0.0;
		if(d1!=null&&d2!=null){
			d=d1-d2;
		}
		return d;
	}
	
	public Long minus_lg(Long l1,Long l2){
		Long l=0l;
		if(l1!=null&&l2!=null){
			l=l1-l2;
		}
		return l;
	}
	
	/*********************************************導出數據格式******************************************************/
	public void exp_file() throws ParseException, IOException{
		XSSFWorkbook wb=new XSSFWorkbook();
		Map<String,Object>map_cs=findStyles2007(wb);
		List<String>list_col=findLeftCol();
		List<WebObjsB>list_objs=webobjbservices.findWebObjsBByFactNo(factNo, yymmdd);	
		
		XSSFCellStyle cs=(XSSFCellStyle)map_cs.get("cs");
		XSSFCellStyle cs_head=(XSSFCellStyle)map_cs.get("cs_head");
		XSSFCellStyle cs_title=(XSSFCellStyle)map_cs.get("cs_title");
		
		switch(list_objs.size()){//switch		
		case 0:
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>window.parent.alert('無數據');</script>");			
			break;
		default:
			XSSFSheet sheet=wb.createSheet(yymmdd);
			for(int a=0;a<list_col.size()+5;a++){
				sheet.createRow(a);
				if(a==0){
					sheet.setColumnWidth(1, 5500);
				}
				for(int b=0;b<list_objs.size()+5;b++){
					sheet.getRow(a).createCell(b);
				}
			}
			
			CellRangeAddress cra_title=new CellRangeAddress(0,0,0,4);
			sheet.addMergedRegion(cra_title);
			sheet.getRow(0).getCell(0).setCellValue("工廠訊息每日更新導入數據-"+yymmdd);
			for(int a=0;a<5;a++){
				sheet.getRow(0).getCell(a).setCellStyle(cs_title);
			}
			
			for(int a=0;a<list_col.size();a++){
				sheet.getRow(2+a).getCell(0).setCellValue(list_col.get(a).split("__")[0]);
				sheet.getRow(2+a).getCell(1).setCellValue(list_col.get(a).split("__")[1]);
				sheet.getRow(2+a).getCell(2).setCellValue(list_col.get(a).split("__")[2]);
				
				for(int b=0;b<3;b++){
					if(a==0){
						sheet.getRow(2+a).getCell(b).setCellStyle(cs_head);
					}else{
						sheet.getRow(2+a).getCell(b).setCellStyle(cs);
					}					
				}												
			}
			for(int a=0;a<list_objs.size();a++){
				sheet.getRow(2).getCell(3+a).setCellValue(list_objs.get(a).getId().getWebFact().getId().getFactArea());
				sheet.getRow(3).getCell(3+a).setCellValue(isMyNull_db(list_objs.get(a).getOnModulus()));
				sheet.getRow(4).getCell(3+a).setCellValue(isMyNull_db(list_objs.get(a).getPersonnum()));
				sheet.getRow(5).getCell(3+a).setCellValue(isMyNull_db(list_objs.get(a).getStandardOutput()));
				sheet.getRow(6).getCell(3+a).setCellValue(isMyNull_db(list_objs.get(a).getActualYield()));
				sheet.getRow(7).getCell(3+a).setCellValue(isMyNull_db(list_objs.get(a).getZpObja()));
				sheet.getRow(8).getCell(3+a).setCellValue(isMyNull_db(list_objs.get(a).getHostpairs()));
				sheet.getRow(9).getCell(3+a).setCellValue(isMyNull_db(list_objs.get(a).getFactpairs()));
				sheet.getRow(10).getCell(3+a).setCellValue(isMyNull_db(list_objs.get(a).getSamplepairs()));
				sheet.getRow(11).getCell(3+a).setCellValue(isMyNull_db(list_objs.get(a).getOutnum()));
				sheet.getRow(12).getCell(3+a).setCellValue(isMyNull_db(list_objs.get(a).getBacknum()));
				sheet.getRow(13).getCell(3+a).setCellValue(isMyNull_db(list_objs.get(a).getWorkhours()));
				sheet.getRow(14).getCell(3+a).setCellValue(isMyNull_db(list_objs.get(a).getDaycount()));
				sheet.getRow(15).getCell(3+a).setCellValue(isMyNull_db(list_objs.get(a).getObjA1()));
				sheet.getRow(16).getCell(3+a).setCellValue(isMyNull_db(list_objs.get(a).getObjA2()));
				sheet.getRow(17).getCell(3+a).setCellValue(isMyNull_db(list_objs.get(a).getObjA3()));
				sheet.getRow(18).getCell(3+a).setCellValue(isMyNull_db(list_objs.get(a).getObjA4()));
				sheet.getRow(19).getCell(3+a).setCellValue(isMyNull_ll(list_objs.get(a).getObjA5()));
				sheet.getRow(20).getCell(3+a).setCellValue(isMyNull_ll(list_objs.get(a).getObjA6()));
				sheet.getRow(21).getCell(3+a).setCellValue(isMyNull_ll(list_objs.get(a).getObjA7()));
				sheet.getRow(22).getCell(3+a).setCellValue(isMyNull_ll(list_objs.get(a).getObjA8()));
				sheet.getRow(23).getCell(3+a).setCellValue(isMyNull_ll(list_objs.get(a).getObjA9()));
				sheet.getRow(24).getCell(3+a).setCellValue(isMyNull_str(list_objs.get(a).getObjA10()));
				sheet.getRow(25).getCell(3+a).setCellValue(isMyNull_str(list_objs.get(a).getObjA11()));
				sheet.getRow(26).getCell(3+a).setCellValue(isMyNull_str(list_objs.get(a).getObjA12()));
				sheet.getRow(27).getCell(3+a).setCellValue(isMyNull_str(list_objs.get(a).getObjA13()));
				sheet.getRow(28).getCell(3+a).setCellValue(isMyNull_str(list_objs.get(a).getObjA14()));
				
				for(int b=0;b<list_col.size();b++){
					if(b==0){
						sheet.getRow(2+b).getCell(3+a).setCellStyle(cs_head);
					}else{
						sheet.getRow(2+b).getCell(3+a).setCellStyle(cs);
					}
					
				}
				
			}													
			try {
				/*OutputStream os = new FileOutputStream("E:/" + "websort.xls");
				wb.write(os);
				os.close();	*/
				ServletOutputStream os=response.getOutputStream();
				//response.setContentType("application/vnd.ms-excel");
				response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
				int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");//判斷是否為IE瀏覽器,大於0則為IE瀏覽器
				String fileName=factNo+"_工廠訊息每日更新導入數據-"+".xlsx";
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
	

	public List<String>findLeftCol(){
		List<String>list=new ArrayList<String>();
		list.add("序號__項目__單位");
		list.add("1__上模數__模");
		list.add("2__人數(拉模手)__人");
		list.add("3__標準產量__模");
		list.add("4__實際產量__模");
		list.add("5__正批生產雙數__雙");
		list.add("6__客補生產雙數__雙");
		list.add("7__廠補生產雙數__雙");
		list.add("8__樣品生產雙數__雙");
		list.add("9__出貨數__雙");
		list.add("10__退貨數__雙");
		list.add("11__上模總工時__小時");
		list.add("12__天數__天");
		list.add("13__成型不良雙數__雙");
		list.add("14__慢單狀況(張)__張");
		list.add("15__慢單狀況(雙)__雙");
		list.add("16__訂單欠數__雙");
		list.add("17__直工人數__人");
		list.add("18__間工人數__人");
		list.add("19__招工數__人");
		list.add("20__離職數__人");
		list.add("21__請假數__人");
		list.add("22__品質問題與客訴 __文字");
		list.add("23__扣款訊息 __文字");
		list.add("24__機台狀況/異常 __文字");
		list.add("25__客人來訪訊息__文字");
		list.add("26__其他提報事項__文字");
		return list;		
	}
}
