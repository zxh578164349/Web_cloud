package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import services.ISumWebYieldDataServices;
import services.IWebFactServices;
import services.IWebYieldDataServices;
import util.GlobalMethod;
import util.ImportExcel;
import util.JasperHelper;
import util.Page;
import util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.SumWebYieldData;
import entity.SumWebYieldDataId;
import entity.VWebFact;
import entity.WebUser;
import entity.WebYieldData;
import entity.WebYieldDataId;
import entity.WebYieldDataLog;

/**
 * 
* 項目名稱：WebLogin   
* 類名稱：WebYdataAction   
* 類描述：產量資料
* 創建人：KY2
 */
public class WebYdataAction extends ActionSupport implements
		ServletResponseAware, ServletRequestAware {
	private WebYieldData ydata;
	private IWebYieldDataServices dataSer;
	private ISumWebYieldDataServices sumydateSer;
	private IWebFactServices webFactSer;
	private String year;
	private String month;
	private String yymmdd;
	private Page pages;
	private int page;
	private int yz;
	private WebYieldDataId id;
	private String factNo;
	private String yymm;
	private PageBean bean;
	private String isnull;
	private List nulllist_data;
	private String transit;
	private String goadd;
	private String sdate;
	private String edate;
	private String ajaxResult;//申請函文時返回的ajax結果,   0:提交成功       1:提交失敗
	private int backIndex;//返回標識      0或null:不走返回路徑         1:走返回路徑
	
	private File file;
    private String fileFileName;
    private String fileContentType;
    private final static String SEPARATOR = "__";
    private String workorholiday;
	

    
	
	public String getWorkorholiday() {
		return workorholiday;
	}

	public void setWorkorholiday(String workorholiday) {
		this.workorholiday = workorholiday;
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

	public int getBackIndex() {
		return backIndex;
	}

	public void setBackIndex(int backIndex) {
		this.backIndex = backIndex;
	}

	public String getSdate() {
		return sdate;
	}

	public String getAjaxResult() {
		return ajaxResult;
	}

	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public String getGoadd() {
		return goadd;
	}

	public void setGoadd(String goadd) {
		this.goadd = goadd;
	}

	public String getTransit() {
		return transit;
	}

	public void setTransit(String transit) {
		this.transit = transit;
	}

	public List getNulllist_data() {
		return nulllist_data;
	}

	public void setNulllist_data(List nulllist_data) {
		this.nulllist_data = nulllist_data;
	}

	private javax.servlet.http.HttpServletResponse response;
	private javax.servlet.http.HttpServletRequest request;

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	public String getIsnull() {
		return isnull;
	}

	public void setIsnull(String isnull) {
		this.isnull = isnull;
	}

	public PageBean getBean() {
		return bean;
	}

	public void setBean(PageBean bean) {
		this.bean = bean;
	}

	public String getYymm() {
		return yymm;
	}

	public void setYymm(String yymm) {
		this.yymm = yymm;
	}

	public String getFactNo() {
		return factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public Page getPages() {
		return pages;
	}

	public void setPages(Page pages) {
		this.pages = pages;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public WebYieldDataId getId() {
		return id;
	}

	public void setId(WebYieldDataId id) {
		this.id = id;
	}

	public void setYz(int yz) {
		this.yz = yz;
	}

	public WebYieldData getYdata() {
		return ydata;
	}

	public void setYdata(WebYieldData ydata) {
		this.ydata = ydata;
	}

	public String getYymmdd() {
		return yymmdd;
	}

	public void setYymmdd(String yymmdd) {
		this.yymmdd = yymmdd;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setDataSer(IWebYieldDataServices dataSer) {
		this.dataSer = dataSer;
	}
	
	

	public void setSumydateSer(ISumWebYieldDataServices sumydateSer) {
		this.sumydateSer = sumydateSer;
	}
	

	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}

	/**
	 * 添加修改
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public String addData() throws ParseException, IOException {				
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		String lastday="";
		Double achievingRate = null;		
		Date today=new Date();
		
		long to=today.getTime();//今天時間
		long from=format.parse(yymmdd).getTime();//選擇的時間
		long betweenDay=(to-from)/(1000*3600*24);//相差的時間
		
		
		try{
			//主鍵的日期和達成率，添加與修改都作同樣處理
			date = format.parse(yymmdd);
			ydata.getId().setYymmdd(date);		
			if (ydata.getActualYield() != null&& ydata.getStandardOutput() != null) {
				if(ydata.getStandardOutput()!=0){
					achievingRate = ydata.getActualYield()/ ydata.getStandardOutput();	
				}else{
					achievingRate=0.0;
				}								
				ydata.setAchievingRate(achievingRate);
			}
			/**
			 * 添加
			 */
			if (isnull.equals("isnull")) {// start "if 1"				
				
				/****************************隻限制已輸入數據的廠別，沒有數據就不限制***********************************/
				double nums=dataSer.findNums(ydata.getId().getFactNo(), ydata.getId().getFactCode());
				if(nums>0){
					WebYieldDataId ID=ydata.getId();
					WebYieldData ydata_find=dataSer.findById(ID);
					
					//判斷選定的日期前一天數據是否輸入
					Calendar cal=Calendar.getInstance();
					cal.setTime(date);
					cal.add(Calendar.DATE, -1);
					lastday=format.format(cal.getTime());
					Date lastday2=format.parse(lastday);
					WebYieldDataId y_id=new WebYieldDataId(ydata.getId().getFactNo(),ydata.getId().getFactCode(),lastday2);																															
						WebYieldData ydata_last=dataSer.findById(y_id);
						if(ydata_last==null){						
							ajaxResult="3";//表示要輸入前天數據												
							if(betweenDay>1){//如果大於前1天的就不提示輸入		20200903				
								if(ydata_find!=null){//有可能出現當天數據不為空的，所以也要判斷
									ajaxResult="2";//表示數據已經存在
								}else{
									dataSer.addYdata(ydata);
									ajaxResult="0";
								}												
							}
						}else{
							if(ydata_find==null){
								dataSer.addYdata(ydata);
								ajaxResult="0";
							}else{
								ajaxResult="2";//表示數據已經存在
							}
						}										
				}else{
					dataSer.addYdata(ydata);
					ajaxResult="0";
				}
				/****************************隻限制已輸入數據的廠別，沒有數據就不限制***********************************/									
				
			}// end "if 1"
			/**
			 * 修改
			 */
			else {// start "else 1"												
				WebYieldDataLog log = new WebYieldDataLog();
				String username = ((WebUser) ActionContext.getContext().getSession().get("loginUser")).getUsername();						
				log.setUsername(username);
				log.setFactNo(ydata.getId().getFactNo());
				log.setFactCode(ydata.getId().getFactCode());
				log.setYymmdd(ydata.getId().getYymmdd());
				log.setLogTime(new Date());

				
				String ipAddress=GlobalMethod.findIp();//獲取本機IP
				log.setIp(ipAddress);
				dataSer.addYdate_log(log);
				dataSer.addYdata(ydata);
												
				//更新盤點數據
				this.updatesumYdate(ydata.getId().getFactNo(),yymmdd);
				ajaxResult="0";
			}// end "else 1"
		}catch(Exception e){
			// TODO Auto-generated catch block
			ajaxResult="1";
			e.printStackTrace();
		}							 
		return "addData";

	}

	public String findById() {
		ydata = dataSer.findById(id);		
		return "findById";
	}

	/**
	 * 分頁查詢
	 * @return
	 */
	public String findPageBean() {		
		System.out.println(request.getRequestURI());
		ActionContext.getContext().getSession().remove("public_factno");
		ActionContext.getContext().getSession().remove("public_yymm");
		ActionContext.getContext().getSession().remove("public_yymm2");
		factNo = (String) ActionContext.getContext().getSession().get("factNo");
		bean = dataSer.findPageBean(20,page, factNo, sdate,edate);

		return "beanList";

	}

	/**
	 * 分頁查詢2
	 * @return
	 */
	public String findPageBean2() {		
		ActionContext.getContext().getSession().remove("public_factno");
		ActionContext.getContext().getSession().remove("public_yymm");
		ActionContext.getContext().getSession().remove("public_yymm2");
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			ActionContext.getContext().getSession().put("public_factno", factNo);
					
		}
		if (sdate != null && !sdate.equals("")) {
			ActionContext.getContext().getSession().put("public_yymm", sdate);
		}
		if (edate != null && !edate.equals("")) {
			ActionContext.getContext().getSession().put("public_yymm2", edate);
		}

		bean = dataSer.findPageBean(20,page, factNo, sdate,edate);

		return "beanList1";
	}

	/**
	 * 分頁查詢3
	 * @return
	 */
	public String findPageBean3() {
		String result="beanList1";
		if(backIndex==1){
			result="beanList";
		}
		factNo = (String) ActionContext.getContext().getSession().get("public_factno");				
		sdate = (String) ActionContext.getContext().getSession().get("public_yymm");
		edate = (String) ActionContext.getContext().getSession().get("public_yymm2");
		if (factNo == null || factNo.equals("") || factNo.equals("tw")) {
			factNo = (String) ActionContext.getContext().getSession().get("factNo");					
		}
		bean = dataSer.findPageBean(20,page, factNo, sdate,edate);

		return result;

	}	

	/**
	 * 刪除
	 * @return
	 * @throws ParseException
	 */
	public String delete() throws ParseException {
		//ydata = dataSer.findById(id);
		WebYieldDataLog log = new WebYieldDataLog();
		String username = ((WebUser) ActionContext.getContext().getSession().get("loginUser")).getUsername();				
		log.setUsername(username);
		log.setFactNo(id.getFactNo());
		log.setFactCode(id.getFactCode());
		log.setYymmdd(id.getYymmdd());
		log.setIsdel("0");
		log.setLogTime(new Date());
		
		String ipAddress=GlobalMethod.findIp();//獲取本機IP
		log.setIp(ipAddress);
		dataSer.addYdate_log(log);
		dataSer.delete(id);							
		
		//更新盤點數據
		this.updatesumYdate(id.getFactNo(),new SimpleDateFormat("yyyyMMdd").format(id.getYymmdd()));
		return "delete";
	}
	
	/**
	 * 更新盤點數據
	 * @Title: updatesumYdate
	 * @Description: TODO
	 * @param @param yymmdd
	 * @return void
	 * @throws ParseException 
	 * @throws
	 * @author web
	 * @date 2016/12/15
	 */
	public void updatesumYdate(String factNo,String yymmdd) throws ParseException{
		DateFormat format=new SimpleDateFormat("yyyyMM");
		yymm=format.format(new SimpleDateFormat("yyyyMMdd").parse(yymmdd));
		String yymm_last="";
		String yymm_next="";
		Calendar cal=Calendar.getInstance();
		cal.setTime(format.parse(yymm));
		cal.add(Calendar.MONTH, -1);
		yymm_last=format.format(cal.getTime());
		cal.add(Calendar.MONTH, 2);
		yymm_next=format.format(cal.getTime());
		List<String>list_yymm=new ArrayList<String>();
		list_yymm.add(yymm_last);
		list_yymm.add(yymm);
		list_yymm.add(yymm_next);
		for(int i=0;i<list_yymm.size();i++){
			List<SumWebYieldData>list_sumYdata=sumydateSer.findByFactNo2(factNo, list_yymm.get(i));
			if(list_sumYdata.size()>0){
				for(int k=0;k<list_sumYdata.size();k++){
					SumWebYieldData sumYdata=list_sumYdata.get(k);
					if(sumYdata!=null){
						//sumydateSer.delete(sumYdata);
						String sumydata_username=sumYdata.getUsername()==null?"none":sumYdata.getUsername();
						String sumydata_usernameUd=sumYdata.getUsernameUd()==null?"none":sumYdata.getUsernameUd();
						this.add_sumYdata(sumYdata.getId().getFactNo().getFactNo(),sumYdata.getId().getFactCode(), list_yymm.get(i), sumYdata.getStartDate(), sumYdata.getEndDate(),sumydata_username,sumydata_usernameUd);
					}							
				}
			}							
		}
	}		

	public String formatDouble(double s) {
		DecimalFormat format = new DecimalFormat(",###.#");
		String temp = format.format(s);
		return temp;
		// return temp.replace(",", "");
	}

	public String formatDouble2(double s) {
		DecimalFormat format = new DecimalFormat("#.#");
		String temp = format.format(s);
		return temp;
	}

	public String formatDouble_percent(double s) {
		DecimalFormat format = new DecimalFormat("0.0%");
		String temp = format.format(s);
		return temp;
	}

	public String findPageBeanForMonth() {
		ActionContext.getContext().getSession().remove("ydata_factNo_formonth");
		ActionContext.getContext().getSession().remove("ydata_yymm_formonth");
		bean = dataSer.findAllYDataForMonth(20,page, factNo, yymm);
		return "beanListForMonth";
	}

	public String findPageBean2ForMonth() {
		ActionContext.getContext().getSession().put("ydata_factNo_formonth",factNo);
		ActionContext.getContext().getSession().put("ydata_yymm_formonth",yymm);
		bean=dataSer.findAllYDataForMonth(20,page,factNo,yymm);
		return "beanListForMonth1";
	}

	public String findPageBean3ForMonth() {
		factNo = (String) ActionContext.getContext().getSession().get("ydata_factNo_formonth");
		yymm = (String) ActionContext.getContext().getSession().get("ydata_yymm_formonth");		
		bean = dataSer.findAllYDataForMonth(20,page, factNo, yymm);
		return "beanListForMonth1";

	}
	
	public String findNulFact() {
		nulllist_data = dataSer.findNullYdata(yymm);
		return "findNulFact";
	}

	public String go_temp() {
		return "go_temp";
	}
	/**
	 * 添加盤點數據
	 * @param factNo
	 * @param yymm
	 * @param startDate
	 * @param endDate
	 */
	public void add_sumYdata(String factNo,String factCode,String yymm,String startDate,String endDate,String username,String usernameUd){
		List list=webFactSer.findFactCodeByFactNo(factNo);
		
			//String factcode=(String)list.get(i);
			Object[]objs=dataSer.getSumWebYieldDate(factNo, factCode, startDate, endDate);
			long list_ydata=dataSer.findYdateSdateToEnddate(factNo, factCode, startDate, endDate);
			SumWebYieldData ydate=new SumWebYieldData();
			SumWebYieldDataId id=new SumWebYieldDataId();
			VWebFact fact=new VWebFact();
			fact.setFactNo(factNo);
			id.setFactNo(fact);
			id.setFactCode(factCode);
			id.setYymm(yymm);
			ydate.setId(id);
			if(list_ydata!=0){				
				GlobalMethod.add_sumYdata(objs,list_ydata,ydate);
			}		
			ydate.setStartDate(startDate);
			ydate.setEndDate(endDate);
			ydate.setUsername(username);
			ydate.setUsernameUd(usernameUd);
			sumydateSer.add(ydate);
			
			//內存回收20160219
			ydate=null;
			id=null;
			fact=null;
		
	}
	
	/**
	 * 導出數據
	 */
	public void print(){
		Map<String,Object>map=new HashMap<String,Object>();		
		List<WebYieldData>list=dataSer.findYdate(factNo, sdate, edate);
		StringBuffer fileName=new StringBuffer();
		StringBuffer title=new StringBuffer();
		fileName.append("report");		
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		fileName.append(factNo);
		title.append(factNo);
		if(sdate!=null&&!sdate.equals("")){
			fileName.append("-"+sdate);
			title.append("-"+sdate);
		}
		if(edate!=null&&!edate.equals("")){
			fileName.append("-"+edate);
			title.append("-"+edate);
		}
		title.append("產量資料");
		map.put("title", title.toString());
		JasperHelper.exportmain("excel", map,"webydate.jasper", list,fileName.toString(), "jasper/input/");
		
	}
	
	/**
	 * 導入數據
	 * @throws IOException
	 */
	public void importFile() throws IOException{
		response.setContentType("text/html;charset=utf-8");
		try{
			DateFormat dfm=new SimpleDateFormat("yyyyMMdd");
			DateFormat dfm2=new SimpleDateFormat("yyMMddhhmm");
			String path="d:\\Webestpro_backup\\"+dfm.format(new Date());//Excel文檔存放目錄
			ajaxResult="0";				
			//文件上傳
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
			//file=new File("i:\\test.xlsx");
			//Map<String,Object>map=ImportExcel.exportListFromFile(file);
			Map<String,Object>map=ImportExcel.exportListFromFile(new File(path+"\\"+fileFileName));
			List<String>list_factArea=webFactSer.findfactAreaByFactNo(factNo);
			if(map.keySet().size()>1){
				response.getWriter().print("<script>window.parent.layer.msg('文檔中只允許一張表')</script>");				
			}else{
				for(String key:map.keySet()){								
					List<String>list=(List<String>)map.get(key);
					if(!list.get(0).contains("__序號__項目__單位")){				
						response.getWriter().print("<script>window.parent.showDiv();window.parent.layer.msg('表格式不符合要求')</script>");
						//continue;
						response.getWriter().close();
						break;
						
					}
					List<String>list_factcode=new ArrayList<String>();
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
					
					List<WebYieldData>list_obj=new ArrayList<WebYieldData>();
					for(int i=4;i<array_head.length;i++){
						WebYieldData obj=new WebYieldData(new WebYieldDataId(factNo,array_head[i],dfm.parse(yymmdd)));
						if(list.get(3).split(SEPARATOR)[i]!=null&&list.get(4).split(SEPARATOR)[i]!=null){
							if(Double.valueOf(list.get(3).split(SEPARATOR)[i])!=0){
								obj.setAchievingRate(Double.valueOf(list.get(4).split(SEPARATOR)[i])/Double.valueOf(list.get(3).split(SEPARATOR)[i]));
							}else{
								obj.setAchievingRate(0.0);
							}
						}						
						obj.setPersonnum(Double.valueOf(list.get(1).split(SEPARATOR)[i]));
						obj.setOnModulus(Double.valueOf(list.get(2).split(SEPARATOR)[i]));
						obj.setStandardOutput(Double.valueOf(list.get(3).split(SEPARATOR)[i]));
						obj.setActualYield(Double.valueOf(list.get(4).split(SEPARATOR)[i]));
						obj.setActualpairs(Double.valueOf(list.get(5).split(SEPARATOR)[i]));
						obj.setHostpairs(Double.valueOf(list.get(6).split(SEPARATOR)[i]));
						obj.setFactpairs(Double.valueOf(list.get(7).split(SEPARATOR)[i]));
						obj.setSamplepairs(Double.valueOf(list.get(8).split(SEPARATOR)[i]));
						obj.setOutnum(Double.valueOf(list.get(9).split(SEPARATOR)[i]));
						obj.setBacknum(Double.valueOf(list.get(10).split(SEPARATOR)[i]));
						obj.setDaycount(Double.valueOf(list.get(11).split(SEPARATOR)[i]));
						obj.setWorkhours(Double.valueOf(list.get(12).split(SEPARATOR)[i]));																	
						obj.setUsername(user.getUsername());
						obj.setUsernameUd(user.getUsername());
						obj.setDateCreate(dfm2.format(new Date()));
						list_obj.add(obj);
						dataSer.addMore(list_obj);										
					}												
				}
				response.getWriter().print("<script>window.parent.layer.msg('導入成功',3,1)</script>");
				//response.getWriter().print("<script>alert('導入成功')</script>");
			}			
			
		}catch(Exception e){
			System.out.println(e);
			response.getWriter().print("<script>window.parent.layer.msg('導入錯誤',3,3);</script>");
		}
	}

}
