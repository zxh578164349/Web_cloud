package action;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import services.ISumWebYieldDataLogServices;
import services.ISumWebYieldDataServices;
import services.IWebFactServices;
import services.IWebYieldDataServices;
import util.JasperHelper;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.SumWebYieldData;
import entity.SumWebYieldDataId;
import entity.SumWebYieldDataLog;
import entity.SumWebYieldDataLogId;
import entity.WebUser;
import entity.WebYieldData;

public class SumWebYieldDataAction extends ActionSupport implements ServletResponseAware{
	private String factNo;
	private String factCode;
	private String yymm;
	private String startDate;
	private String endDate;
	private PageBean bean;
	private int page;
	private String begin_yymm;
	private String end_yymm;
	private String username;
	private SumWebYieldData sumydata;
	private IWebYieldDataServices dataSer;
	private ISumWebYieldDataServices sumydateSer;
	private IWebFactServices webFactSer;
	private ISumWebYieldDataLogServices sumydatelogSer;
	private javax.servlet.http.HttpServletResponse response;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBegin_yymm() {
		return begin_yymm;
	}
	public void setBegin_yymm(String begin_yymm) {
		this.begin_yymm = begin_yymm;
	}
	public String getEnd_yymm() {
		return end_yymm;
	}
	public void setEnd_yymm(String end_yymm) {
		this.end_yymm = end_yymm;
	}
	public PageBean getBean() {
		return bean;
	}
	public void setBean(PageBean bean) {
		this.bean = bean;
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public SumWebYieldData getSumydata() {
		return sumydata;
	}
	public void setSumydata(SumWebYieldData sumydata) {
		this.sumydata = sumydata;
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
	
	public void setSumydatelogSer(ISumWebYieldDataLogServices sumydatelogSer) {
		this.sumydatelogSer = sumydatelogSer;
	}
	/**
	 * 睰
	 * 礚 把计
	 * (0)onModulus:(everyDemo)讽ら家计;(1)personnum:(everyPeople)讽ら家计;(2)standardOutput:(standardDemo)夹ネ玻家计;
	 * (3)actualYield:(actualDemo)龟悔ネ玻家计;(4)daycount:(workDay)痁ぱ计
	 * (5)actualpairs:龟悔ネ玻蛮计;(6)hostpairs:干ネ玻蛮计;(7)factpairs:紅干ネ玻蛮计;(8)samplepairs:妓珇ネ玻蛮计
	 * (9)outnum:砯计;(10)backnum:癶砯计
	 * @return
	 */
	public String add(){
		List list=webFactSer.findFactCodeByFactNo(factNo);
		for(int i=0;i<list.size();i++){
			String factcode=(String)list.get(i);
			Object[]objs=dataSer.getSumWebYieldDate(factNo, factcode, startDate, endDate);
			List<WebYieldData>list_ydata=dataSer.findYdateSdateToEnddate(factNo, factcode, startDate, endDate);
			SumWebYieldData ydate=new SumWebYieldData();
			SumWebYieldDataId id=new SumWebYieldDataId();
			id.setFactNo(factNo);
			id.setFactCode(factcode);
			id.setYymm(yymm);
			ydate.setId(id);
			//objsぃ耞┮耞list_ydata.size()(ㄢ琩高兵ン妓挡狦琌璓)
			if(list_ydata.size()!=0){
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
			sumydateSer.add(ydate);
		}	
		return "add";
	}
	/**
	 * Τ把计
	 * @param factNo
	 * @param yymm
	 * @param startDate
	 * @param endDate
	 */
	public void add2(String factNo,String yymm,String startDate,String endDate){
		List list=webFactSer.findFactCodeByFactNo(factNo);
		for(int i=0;i<list.size();i++){
			String factcode=(String)list.get(i);
			Object[]objs=dataSer.getSumWebYieldDate(factNo, factcode, startDate, endDate);
			SumWebYieldData ydate=new SumWebYieldData();
			SumWebYieldDataId id=new SumWebYieldDataId();
			id.setFactNo(factNo);
			id.setFactCode(factcode);
			id.setYymm(yymm);
			ydate.setId(id);
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
			ydate.setStartDate(startDate);
			ydate.setEndDate(endDate);
			String username=sumydateSer.findUsername(factNo, factcode, yymm);
			if(username!=null){
				ydate.setUsername(username);
			}
			sumydateSer.add(ydate);
		}
	}
	public String findPageBean(){
		ActionContext.getContext().getApplication().clear();
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		bean=sumydateSer.findPageBean(25, page, factNo, begin_yymm,end_yymm);
		return "findPageBean";
	}
	public String findPageBean2(){
		ActionContext.getContext().getApplication().clear();
		if(factNo!=null&&!factNo.equals("")){
			ActionContext.getContext().getApplication().put("sumydate_factno", factNo);
		}
		if(begin_yymm!=null&&!begin_yymm.equals("")){
			ActionContext.getContext().getApplication().put("sumydate_begin_yymm", begin_yymm);
		}
		if(end_yymm!=null&&!end_yymm.equals("")){
			ActionContext.getContext().getApplication().put("sumydate_end_yymm", end_yymm);
		}
		bean=sumydateSer.findPageBean(25, page, factNo, begin_yymm,end_yymm);
		return "findPageBean1";
	}
	public String findPageBean3(){
		factNo=(String)ActionContext.getContext().getApplication().get("sumydate_factno");
		begin_yymm=(String)ActionContext.getContext().getApplication().get("sumydate_begin_yymm");
		end_yymm=(String)ActionContext.getContext().getApplication().get("sumydate_end_yymm");
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		bean=sumydateSer.findPageBean(25, page, factNo, begin_yymm,end_yymm);
		return "findPageBean1";
	}
	public String formatDouble(double s) {
		DecimalFormat format = new DecimalFormat(",###.#");
		String temp = format.format(s);
		return temp;
		// return temp.replace(",", "");
	}
	
	/**
	 * 礚把计
	 * @return
	 */
	public String delete(){
		List<SumWebYieldData>list=sumydateSer.findByFactNo2(factNo, yymm);
		SumWebYieldDataLog log=new SumWebYieldDataLog();
		SumWebYieldDataLogId id=new SumWebYieldDataLogId();
		for(int i=0;i<list.size();i++){
			SumWebYieldData ydata=list.get(i);
			sumydateSer.delete(ydata);
		}
		//埃癘魁
		if(list.size()>0){
			id.setFactNo(factNo);
			id.setYymm(yymm);
			log.setId(id);
			log.setStartDate(startDate);
			log.setEndDate(endDate);
			String username = ((WebUser) ActionContext.getContext().getSession().get("loginUser")).getUsername();
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyyMMdd_hh:mm");
			String createdate=format.format(date);
			log.setUsername(username);
			log.setCreatedate(createdate);
			sumydatelogSer.add(log);
		}
		return "delete";
	}
	/**
     * Τ把计
     * @param factNo
     * @param yymm
     */
	public void delete2(String factNo,String yymm){
		List<SumWebYieldData>list=sumydateSer.findByFactNo2(factNo, yymm);
		for(int i=0;i<list.size();i++){
			SumWebYieldData ydata=list.get(i);
			sumydateSer.delete(ydata);
		}
	}
	
	public String update(){
		//this.delete2(factNo, yymm);
		this.add2(factNo, yymm, startDate, endDate);
		return "update";
	}
	
	public String updateAll(){
		List<SumWebYieldData>list=sumydateSer.findAll();
		for(int i=0;i<list.size();i++){
			SumWebYieldData sumYdata=list.get(i);
			String factno=sumYdata.getId().getFactNo();
			String factcode=sumYdata.getId().getFactCode();
			String yymm=sumYdata.getId().getYymm();
			String startdate=sumYdata.getStartDate();
			String enddate=sumYdata.getEndDate();
			
			Object[]objs=dataSer.getSumWebYieldDate(factno, factcode, startdate, enddate);
			SumWebYieldData ydate=new SumWebYieldData();
			SumWebYieldDataId id=new SumWebYieldDataId();
			id.setFactNo(factno);
			id.setFactCode(factcode);
			id.setYymm(yymm);
			ydate.setId(id);		
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
			ydate.setStartDate(startdate);
			ydate.setEndDate(enddate);
			String username=sumydateSer.findUsername(factno, factcode, yymm);
			if(username!=null){
				ydate.setUsername(username);
			}
			//sumydateSer.delete(sumYdata);                                              20150421
			sumydateSer.add(ydate);						
		}
		return "updateAll";
	}
	
	public String findById(){
		sumydata=sumydateSer.findById(factNo, factCode, yymm);
		return "findById";
	}
	public void print() throws IOException{				
		if(factNo==null||(begin_yymm==null&&end_yymm==null)||(begin_yymm.equals("")&&end_yymm.equals(""))||factNo.equals("nothing")){
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>alert('紅㎝ら戳ぃ!');history.back()</script>");
		}else{
			String yymm=begin_yymm+"-"+end_yymm;			
			Map<String,Object>map=new HashMap<String,Object>();
			List<SumWebYieldData>list_sumYdata=sumydateSer.findByFactNoAndYymm(factNo, begin_yymm, end_yymm);		
			map.put("factNo", factNo);
			map.put("yymm", yymm);
			JasperHelper.exportmain("excel", map,"sum_ydata.jasper",list_sumYdata, factNo+"_"+yymm, "jasper/audit/");
		}
		
				
	}
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}

}
