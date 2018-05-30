package action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import mail.AutoSendWebWeeklyreportItems;
import net.sf.json.JSONArray;

import services.IWebWeeklyreportServices;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyzExpectmatmLog;
import entity.WebErpBrankProcess;
import entity.WebUser;
import entity.WebWeeklyreport;

public class WebWeeklyreportAction extends ActionSupport implements ServletResponseAware{
	private IWebWeeklyreportServices webweeklyreportservices;
	private WebWeeklyreport obj;
	private String ajaxResult;
	private int page;
	private PageBean bean;
	private int backIndex;//返回標識      0或null:不走返回路徑         1:走返回路徑
	private int uid;
	private String sdate;//本周星期一的日期
	private String edate;//本周星期天的日期
	
	private int bid;//品牌id
	private int rid;//周報告id
	private JSONArray jsons;
	private String uname;//用戶中文名
	private HttpServletResponse response;

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}
	public JSONArray getJsons() {
		return jsons;
	}


	public void setJsons(JSONArray jsons) {
		this.jsons = jsons;
	}

	public int getRid() {
		return rid;
	}


	public void setRid(int rid) {
		this.rid = rid;
	}


	public int getBid() {
		return bid;
	}


	public void setBid(int bid) {
		this.bid = bid;
	}


	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public String getSdate() {
		return sdate;
	}


	public void setSdate(String sdate) {
		this.sdate = sdate;
	}


	public int getBackIndex() {
		return backIndex;
	}


	public void setBackIndex(int backIndex) {
		this.backIndex = backIndex;
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


	public String getAjaxResult() {
		return ajaxResult;
	}


	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}


	public WebWeeklyreport getObj() {
		return obj;
	}


	public void setObj(WebWeeklyreport obj) {
		this.obj = obj;
	}


	public void setWebweeklyreportservices(
			IWebWeeklyreportServices webweeklyreportservices) {
		this.webweeklyreportservices = webweeklyreportservices;
	}
	
	public String add(){
		try{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
			Calendar cal=Calendar.getInstance();		
			cal.setTime(sdf.parse(obj.getSDate()));
			cal.setFirstDayOfWeek(Calendar.MONDAY);
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			obj.setEDate(sdf.format(cal.getTime()));									
			webweeklyreportservices.add(obj);
			ajaxResult="0";
		}catch(Exception e){
			ajaxResult="1";
			e.printStackTrace();			
			
		}
		return "add";
	}
	
		
	public String findPageBean(){
		ActionContext.getContext().getSession().remove("allrow");	
		bean=webweeklyreportservices.findPageBean(page, 0, uid, sdate,bid);
		ActionContext.getContext().getSession().put("sdate", sdate);
		ActionContext.getContext().getSession().put("bid", bid);
		return "findPageBean";		
	}
	
	public String findPageBean2(){		
		this.findPageBean();
		return "findPageBean1";
	}
	
	public String findPageBean3(){
		sdate=(String)ActionContext.getContext().getSession().get("sdate");
		bid=(Integer)ActionContext.getContext().getSession().get("bid");
		bean=webweeklyreportservices.findPageBean(page, 0, uid, sdate,bid);
		String result="findPageBean1";
		if(backIndex==1){
			result="findPageBean";
		}
		return result;
		
	}
	
	public String findByUidASdate(){
		obj=webweeklyreportservices.findByUidASdateABid(uid, sdate,bid);
		if(obj==null){
			ajaxResult="0";
		}else{
			ajaxResult="1";
		}
		return "findByUidASdate";
	}
	
	public String findByUidASdate_page(){
		this.findByUidASdate();
		return "findByUidASdate_page";
	}
		
	
	public String findById(){
		obj=webweeklyreportservices.findById(rid);
		return "findById";
	}
	
	public String delete(){
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setContent("ID:"+rid);
		log.setObj("WebWeeklyreport");
		log.setUsername(null);//如果為null,自動設定為登錄人帳號
		webweeklyreportservices.delete(rid, log);
		return "delete";
		
	}
	
	public String email_url(){
		System.out.println("********************uid:"+uid+"\n sdate:"+sdate+"\n edate:"+edate+"**********************");
		return "email_url";
	}
	
	public void print(){				
		try {
			List<WebWeeklyreport>list_obj=webweeklyreportservices.findByEdate(sdate);
			if(list_obj.size()==0){
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print("<script>alert('暫無數據');history.back()</script>");
				response.getWriter().close();
			}else{
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
				Calendar cal=Calendar.getInstance();
				cal.setTime(sdf.parse(sdate));
				cal.add(Calendar.DAY_OF_MONTH, 6);
				edate=sdf.format(cal.getTime());
				Workbook wb=AutoSendWebWeeklyreportItems.excel2007(sdate, edate, list_obj);
				
				//response.setContentType("application/vnd.ms-excel");
				response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
				String fileName="report.xlsx";
				int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");
				if(msie>0){
					fileName=java.net.URLEncoder.encode(fileName,"utf-8");
				}else{
					fileName=new String(fileName.getBytes("utf-8"),"iso8859-1");
				}
				response.setHeader("Content-disposition","attachment;filename="+fileName);
				ServletOutputStream os=response.getOutputStream();
				wb.write(os);
				os.flush();
				os.close();
				
			}						
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	
	
	
	


}
