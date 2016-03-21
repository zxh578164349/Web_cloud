package action;

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

	public String addData() throws ParseException, IOException {				
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		DateFormat format2=new SimpleDateFormat("yyyyMM");
		String result = null;
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
			if (ydata.getActualYield() != null&& ydata.getStandardOutput() != null&&ydata.getStandardOutput()!=0) {				
				achievingRate = ydata.getActualYield()/ ydata.getStandardOutput();					
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
					WebYieldDataId y_id=new WebYieldDataId();
					y_id.setFactNo(ydata.getId().getFactNo());
					y_id.setFactCode(ydata.getId().getFactCode());
					y_id.setYymmdd(lastday2);							
					WebYieldData ydata_last=dataSer.findById(y_id);
					if(ydata_last==null){
						result="noData";
						//如果大於前21天的就不提示輸入
						if(betweenDay>21){
							//如果跳過提示，有可能出現當天數據不為空的，所以也要判斷
							if(ydata_find!=null){
								result=null;
							}else{
								dataSer.addYdata(ydata);
								result="addData";
								ajaxResult="0";
							}												
						}
					}else{
						if(ydata_find==null){
							dataSer.addYdata(ydata);
							result = "addData";
							ajaxResult="0";
						}
					}
				}else{
					dataSer.addYdata(ydata);
					result = "addData";
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

				String ipAddress = null;
				ipAddress = request.getHeader("x-forwarded-for");
				if (ipAddress == null || ipAddress.length() == 0
						|| "unknown".equalsIgnoreCase(ipAddress)) {
					ipAddress = request.getHeader("Proxy-Client-IP");
				}
				if (ipAddress == null || ipAddress.length() == 0
						|| "unknown".equalsIgnoreCase(ipAddress)) {
					ipAddress = this.request.getHeader("WL-Proxy-Client-IP");
				}
				if (ipAddress == null || ipAddress.length() == 0
						|| "unknown".equalsIgnoreCase(ipAddress)) {
					ipAddress = this.request.getRemoteAddr();
					if (ipAddress.equals("127.0.0.1")) {
						InetAddress inet = null;
						try {
							inet = InetAddress.getLocalHost();
						} catch (Exception e) {
							e.printStackTrace();
						}
						ipAddress = inet.getHostAddress();
					}
				}
				if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
																	// = 15
					if (ipAddress.indexOf(",") > 0) {
						ipAddress = ipAddress.substring(0,
								ipAddress.indexOf(","));
					}
				}
				log.setIp(ipAddress);
				dataSer.addYdate_log(log);
				dataSer.addYdata(ydata);
				
				//更新盤點數據
				yymm=yymmdd.substring(0,6);
				String yymm_last="";
				String yymm_next="";
				Calendar cal2=Calendar.getInstance();
				cal2.setTime(format2.parse(yymm));
				cal2.add(Calendar.MONTH, -1);
				yymm_last=format2.format(cal2.getTime());
				cal2.add(Calendar.MONTH, 2);
				yymm_next=format2.format(cal2.getTime());
				List<String>list_yymm=new ArrayList<String>();
				list_yymm.add(yymm_last);
				list_yymm.add(yymm);
				list_yymm.add(yymm_next);
				for(int i=0;i<list_yymm.size();i++){
					List<SumWebYieldData>list_sumYdata=sumydateSer.findByFactNo2(ydata.getId().getFactNo(), list_yymm.get(i));
					if(list_sumYdata.size()>0){
						for(int k=0;k<list_sumYdata.size();k++){
							SumWebYieldData sumYdata=list_sumYdata.get(k);
							if(sumYdata!=null){
								//sumydateSer.delete(sumYdata);
								String sumydata_username=sumYdata.getUsername()==null?"none":sumYdata.getUsername();
								String sumydata_usernameUd=sumYdata.getUsernameUd()==null?"none":sumYdata.getUsernameUd();
								this.add_sumYdata(ydata.getId().getFactNo(), list_yymm.get(i), sumYdata.getStartDate(), sumYdata.getEndDate(),sumydata_username,sumydata_usernameUd);
							}							
						}
					}							
				}							
				result = "upData";
				ajaxResult="0";
			}// end "else 1"
		}catch(Exception e){
			// TODO Auto-generated catch block
			ajaxResult="1";
			e.printStackTrace();
		}
			
			if (result == null) {
				response.setContentType("text/html;charset=utf-8");
				String temp1 = ydata.getId().getFactNo();
				String temp2 = ydata.getId().getFactCode();
				String temp3 = format.format(ydata.getId().getYymmdd());
				response.getWriter()
						.print("<script>window.parent.alert('數據已經存在("
								+ temp1
								+ " "
								+ temp2
								+ " "
								+ temp3
								+ ")!');</script>");				
			}
			if(result.equals("noData")){
				response.setContentType("text/html;charset=utf-8");
				response.getWriter()
				.print("<script>window.parent.alert('選定日期的前天數據("
						+ lastday					
						+ ")還沒有輸入!');</script>");							
				result=null;
			}
		 
		return result;

	}

	/**
	 * ���豢�
	 * 
	 * @return
	 */
	public void list() {

		pages.setPage(page);
		if (pages.getPage() > pages.getPageSize()) {
			pages.setPage(pages.getPageSize());
		}
		if (pages.getPage() == 1 || pages.getPage() < 1) {
			pages.setPage(1);
		}
		if (factNo != null && !factNo.equals("")) {
			ActionContext.getContext().getSession().put("factNo", factNo);
		}
		String factNos = (String) ActionContext.getContext().getSession()
				.get("factNo");
		List<WebYieldData> bList = dataSer.selectYDate(factNos, yymm,
				pages.getPage(), pages.getRows());
		ActionContext.getContext().getSession().put("ydataList", bList);
		pages.setCount(dataSer.totlePage(factNo, yymm));
		if (pages.getCount() % pages.getRows() == 0) {
			pages.setPageSize(pages.getCount() / pages.getRows());
		} else {
			pages.setPageSize(pages.getCount() / pages.getRows() + 1);
		}
	}

	/**
	 * �憿舐內�豢�
	 * 
	 * @return
	 */
	public String getList() {
		list();
		if (factNo != null && !factNo.equals("") || yz == 1) {
			return "showList1";
		} else {
			return "showList";
		}
	}

	/**
	 * 擐�憿舐內�豢�
	 */
	public String getList1() {
		list();
		return "list";
	}

	public String findById() {
		ydata = dataSer.findById(id);
		if (ydata.getWorkorholiday() == null
				|| ydata.getWorkorholiday().equals("0")) {
			return "findById";
		} else {
			return "findById2";
		}

	}

	public String findPageBean() {
		//ActionContext.getContext().getApplication().clear();
		ActionContext.getContext().getSession().remove("public_factno");
		ActionContext.getContext().getSession().remove("public_yymm");
		ActionContext.getContext().getSession().remove("public_yymm2");
		factNo = (String) ActionContext.getContext().getSession().get("factNo");
		bean = dataSer.findPageBean(25, page, factNo, sdate,edate);

		return "beanList";

	}

	public String findPageBean2() {
		//ActionContext.getContext().getApplication().clear();
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
			ActionContext.getContext().getSession().put("public_yymm", edate);
		}

		bean = dataSer.findPageBean(25, page, factNo, sdate,edate);

		return "beanList1";
	}

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
		bean = dataSer.findPageBean(25, page, factNo, sdate,edate);

		return result;

	}

	public String findPageBean4() {
		String result = this.findPageBean3();
		result = "beanList";
		return result;
	}

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

		String ipAddress = null;
		ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = this.request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = this.request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1")) {
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (Exception e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
															// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		log.setIp(ipAddress);
		dataSer.addYdate_log(log);
		dataSer.delete(id);
		
		//更新盤點數據
		DateFormat format=new SimpleDateFormat("yyyyMM");
		yymm=format.format(id.getYymmdd());
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
			List<SumWebYieldData>list_sumYdata=sumydateSer.findByFactNo2(id.getFactNo(), list_yymm.get(i));
			if(list_sumYdata.size()>0){
				for(int k=0;k<list_sumYdata.size();k++){
					SumWebYieldData sumYdata=list_sumYdata.get(k);
					//sumydateSer.delete(sumYdata);
					String sumydata_username=sumYdata.getUsername()==null?"none":sumYdata.getUsername();
					String sumydata_username_ud=sumYdata.getUsernameUd()==null?"none":sumYdata.getUsernameUd();
					this.add_sumYdata(id.getFactNo(), list_yymm.get(i), sumYdata.getStartDate(), sumYdata.getEndDate(),sumydata_username,sumydata_username_ud);
				}
			}							
		}		
		return "delete";
	}

	public String transit() {// 瘛餃�銝剛�action
		transit = "transit";
		return "transit";
	}

	public String findPageBean2_print() {
		ActionContext.getContext().getApplication().clear();
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			ActionContext.getContext().getApplication()
					.put("print_ydata_factNo", factNo);

		} else {
			factNo = (String) ActionContext.getContext().getSession()
					.get("factNo");
		}
		if (yymm != null && !yymm.equals("")) {
			ActionContext.getContext().getApplication()
					.put("print_ydata_yymm", yymm);
		}
		bean = dataSer.findPageBean(25, page, factNo, sdate,edate);
		/*
		 * ActionContext.getContext().getSession() .put("zwcashList",
		 * dataSer.getAllWithNoPage(factNo, yymm));
		 */
		return "list";
	}

	public String findPageBean3_print() {
		factNo = (String) ActionContext.getContext().getApplication()
				.get("print_ydata_factNo");
		yymm = (String) ActionContext.getContext().getApplication()
				.get("print_ydata_yymm");
		if (factNo == null || factNo.equals("") || factNo.equals("tw")) {
			factNo = (String) ActionContext.getContext().getSession()
					.get("factNo");
		}
		bean = dataSer.findPageBean(25, page, factNo, sdate,edate);
		return "list";
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
		DecimalFormat format = new DecimalFormat("#.0%");
		String temp = format.format(s);
		return temp;
	}

	public String findPageBeanForMonth() {
		ActionContext.getContext().getApplication().clear();
		factNo = (String) ActionContext.getContext().getSession().get("factNo");
		bean = dataSer.findAllYDataForMonth(25, page, factNo, yymm);
		return "beanListForMonth";
	}

	public String findPageBean2ForMonth() {
		ActionContext.getContext().getApplication().clear();
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			ActionContext.getContext().getApplication()
					.put("ydata_factNo_formonth", factNo);
		}
		if (yymm != null && !yymm.equals("")) {
			ActionContext.getContext().getApplication()
					.put("ydata_yymm_formonth", yymm);
		}

		bean = dataSer.findAllYDataForMonth(25, page, factNo, yymm);

		return "beanListForMonth1";
	}

	public String findPageBean3ForMonth() {
		factNo = (String) ActionContext.getContext().getApplication()
				.get("ydata_factNo_formonth");
		yymm = (String) ActionContext.getContext().getApplication()
				.get("ydata_yymm_formonth");
		if (factNo == null || factNo.equals("") || factNo.equals("tw")) {
			factNo = (String) ActionContext.getContext().getSession()
					.get("factNo");
		}
		bean = dataSer.findAllYDataForMonth(25, page, factNo, yymm);

		return "beanListForMonth1";

	}

	public String findPageBean2_print_formonth() {
		ActionContext.getContext().getApplication().clear();
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			ActionContext.getContext().getApplication()
					.put("print_ydata_factNo_formonth", factNo);

		} else {
			factNo = (String) ActionContext.getContext().getSession()
					.get("factNo");
		}
		if (yymm != null && !yymm.equals("")) {
			ActionContext.getContext().getApplication()
					.put("print_ydata_yymm_formonth", yymm);
		}
		bean = dataSer.findAllYDataForMonth(15, page, factNo, yymm);
		/*
		 * ActionContext.getContext().getSession() .put("zwcashList",
		 * dataSer.getAllWithNoPage(factNo, yymm));
		 */
		return "list_formonth";
	}

	public String findPageBean3_print_formonth() {
		factNo = (String) ActionContext.getContext().getApplication()
				.get("print_ydata_factNo_formonth");
		yymm = (String) ActionContext.getContext().getApplication()
				.get("print_ydata_yymm_formonth");
		if (factNo == null || factNo.equals("") || factNo.equals("tw")) {
			factNo = (String) ActionContext.getContext().getSession()
					.get("factNo");
		}
		bean = dataSer.findAllYDataForMonth(15, page, factNo, yymm);
		return "list_formonth";
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
	public void add_sumYdata(String factNo,String yymm,String startDate,String endDate,String username,String usernameUd){
		List list=webFactSer.findFactCodeByFactNo(factNo);
		for(int i=0;i<list.size();i++){
			String factcode=(String)list.get(i);
			Object[]objs=dataSer.getSumWebYieldDate(factNo, factcode, startDate, endDate);
			long list_ydata=dataSer.findYdateSdateToEnddate(factNo, factcode, startDate, endDate);
			SumWebYieldData ydate=new SumWebYieldData();
			SumWebYieldDataId id=new SumWebYieldDataId();
			VWebFact fact=new VWebFact();
			fact.setFactNo(factNo);
			id.setFactNo(fact);
			id.setFactCode(factcode);
			id.setYymm(yymm);
			ydate.setId(id);
			if(list_ydata!=0){
				BigDecimal onModulus=new BigDecimal(objs[0].toString());
				BigDecimal personnum=new BigDecimal(objs[1].toString());
				BigDecimal standardOutput=new BigDecimal(objs[2].toString());
				BigDecimal actualYield=new BigDecimal(objs[3].toString());
				BigDecimal daycount=new BigDecimal(objs[4].toString());
				BigDecimal actualpairs=new BigDecimal(objs[5].toString());
				BigDecimal hostpairs=new BigDecimal(objs[6].toString());
				BigDecimal factpairs=new BigDecimal(objs[7].toString());
				BigDecimal samplepairs=new BigDecimal(objs[8].toString());
				BigDecimal outnum=new BigDecimal(objs[9].toString());
				BigDecimal backnum=new BigDecimal(objs[10].toString());
				Double workhours=(Double)objs[11];
				ydate.setSumEverydemo(onModulus);
				ydate.setSumEverypeople(personnum);
				ydate.setSumStandarddemo(standardOutput);
				ydate.setSumActualdemo(actualYield);
				ydate.setSumWorkdays(daycount);
				ydate.setSumActualpairs(actualpairs);
				ydate.setSumHostpairs(hostpairs);
				ydate.setSumFactpairs(factpairs);
				ydate.setSumSamplepairs(samplepairs);
				ydate.setSumOutnum(outnum);
				ydate.setSumBacknum(backnum);
				ydate.setSumWorkhours(workhours);
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
	}
	
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

}
