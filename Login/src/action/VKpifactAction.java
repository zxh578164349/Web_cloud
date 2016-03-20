package action;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import services.IKpifactServices;
import services.IVKpifactEveServices;
import services.IVKpifactServices;
import services.IWebFactServices;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.Kpifact;
import entity.VKpifact;
import entity.VKpifactEve;
import entity.WebFact;

public class VKpifactAction extends ActionSupport implements ServletResponseAware{
	private IVKpifactServices vkpiSer;
	private IWebFactServices webFactSer;
	private IVKpifactEveServices vkpieveSer;
	private IKpifactServices kpiSer;
	private String yymm;
	private String factNo;
	private String year;
	private String yymm_begin;
	private String yymm_end;
	private List<String>list_factcode;
	private List<String>list_factno;
	private javax.servlet.http.HttpServletResponse response;
	private final static int cursor=45;//循環廠別狀態時的迭代量

	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
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
	
	public String getYymm_begin() {
		return yymm_begin;
	}
	public void setYymm_begin(String yymm_begin) {
		this.yymm_begin = yymm_begin;
	}
	public String getYymm_end() {
		return yymm_end;
	}
	public void setYymm_end(String yymm_end) {
		this.yymm_end = yymm_end;
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
	public void setVkpiSer(IVKpifactServices vkpiSer) {
		this.vkpiSer = vkpiSer;
	}
	
	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}
	
	public void setVkpieveSer(IVKpifactEveServices vkpieveSer) {
		this.vkpieveSer = vkpieveSer;
	}
	public String findAll(){
		List<VKpifact>list=vkpiSer.findAll(factNo,yymm);
		ActionContext.getContext().getSession().put("vkpilist", list);
		return "findAll";
	}
	
	
	public void setKpiSer(IKpifactServices kpiSer) {
		this.kpiSer = kpiSer;
	}
	public void print_fact() throws IOException{
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet("KPI-工廠");
		
		/**
		 * 報表相關樣式
		 */
		Map<String,Object>map=findStyles(wb);
		//標題樣式
		HSSFCellStyle cs_head=(HSSFCellStyle)map.get("cs_head");
		
		
		//標準單元格樣式
		HSSFCellStyle cs=(HSSFCellStyle)map.get("cs");		
		//表頭樣式
		HSSFCellStyle cs_column=(HSSFCellStyle)map.get("cs_column");
				
		/**
		 * 數字格式（有背景顏色與無背景顏色）
		 */
		
		/*//無背景
		HSSFCellStyle cs_percent=(HSSFCellStyle)map.get("cs_percent");				
		HSSFCellStyle cs_poi=(HSSFCellStyle)map.get("cs_poi");	
		HSSFCellStyle cs_poi1=(HSSFCellStyle)map.get("cs_poi1");		
		HSSFCellStyle cs_poi2=(HSSFCellStyle)map.get("cs_poi2");
		HSSFCellStyle cs_poi4=(HSSFCellStyle)map.get("cs_poi4");		
		//有背景
		HSSFCellStyle cs_percent_bg=(HSSFCellStyle)map.get("cs_percent_bg");				
		HSSFCellStyle cs_poi_bg=(HSSFCellStyle)map.get("cs_poi_bg");		
		HSSFCellStyle cs_poi1_bg=(HSSFCellStyle)map.get("cs_poi1_bg");	
		HSSFCellStyle cs_poi2_bg=(HSSFCellStyle)map.get("cs_poi2_bg");		
		HSSFCellStyle cs_poi4_bg=(HSSFCellStyle)map.get("cs_poi4_bg");*/
										
		/**
		 * 獲取要循環的數據
		 */
		List<WebFact>list_factcode=webFactSer.findFactById_showA(factNo);				
		List<String>list_str=findListstrAndUnit().get(0);		
		List<String>list_unit=findListstrAndUnit().get(1);
		          	
		          
		//表頭內容
		List<String>list_column=new ArrayList<String>();
		list_column.add("項次");
		list_column.add("管制指標");
		list_column.add("單位");
		list_column.add(yymm+"目標值");//20150330
		list_column.add("1月");
		list_column.add("2月");
		list_column.add("3月");
		list_column.add("Q1");
		list_column.add("4月");
		list_column.add("5月");
		list_column.add("6月");
		list_column.add("Q2");
		list_column.add("上半年");
		list_column.add("7月");
		list_column.add("8月");
		list_column.add("9月");
		list_column.add("Q3");
		list_column.add("10月");
		list_column.add("11月");
		list_column.add("12月");
		list_column.add("Q4");
		list_column.add("下半年");
		list_column.add("全年");
		
		String factName=webFactSer.selByid(factNo);
		String title=factName+"("+factNo+")"+year+"年"+"KPI匯總表";
		
		/**
		 * 獲取季度,上下半年,全年數據
		 */
		//最外層集合
		List<List<List<Double>>>list1_all=new ArrayList<List<List<Double>>>();
		for(int a=0;a<list_factcode.size();a++){//start for1
			List<VKpifactEve>list_kpieve=new ArrayList<VKpifactEve>();
			for(int i=0;i<8;i++){
				list_kpieve.add(new VKpifactEve());
			}
			
			/**
			 * 中間集合
			 * 用於裝季度與上下半年與全年的數據
			 */
			
			List<List<Double>>list2_all=new ArrayList<List<Double>>();
			/**
			 * 單元集合
			 */
			//第一季度
			List<Double>list3_q1=new ArrayList<Double>();
			//第二季度
			List<Double>list3_q2=new ArrayList<Double>();
			//第三季度
			List<Double>list3_q3=new ArrayList<Double>();
			//第四季度
			List<Double>list3_q4=new ArrayList<Double>();
			//上半年
			List<Double>list3_half1=new ArrayList<Double>();
			//下半年
			List<Double>list3_half2=new ArrayList<Double>();
			//全年
			List<Double>list3_year=new ArrayList<Double>();
			WebFact fact=list_factcode.get(a);
			String factCode=fact.getId().getFactArea();
			for(int b=1;b<13;b++){//start for2						
				String month="";
				if(b<10){
					month="0"+b;
				}else{
					month=""+b;
				}
				VKpifactEve eve=vkpieveSer.findById(factNo, factCode, year+month)==null?new VKpifactEve():vkpieveSer.findById(factNo, factCode, year+month);
				if(eve!=null){//start if
					/**
					 * sumActualpairs和sumFacpairs,otherweight有可能為空
					 */
					BigDecimal big=new BigDecimal(0.00);
					if(eve.getSumActualpairs()==null){
						eve.setSumActualpairs(big);
					}
					if(eve.getSumFacpairs()==null){
						eve.setSumFacpairs(big);
					}
					
					if(eve.getProductednum()==null){
						eve.setProductednum(0.0);
					}
					if(eve.getNoglueweight()==null){
						eve.setNoglueweight(0.0);
					}
					if(eve.getRepairmoney()==null){
						eve.setRepairmoney(0.0);
					}
					if(eve.getWorkhours()==null){
						eve.setWorkhours(0.0);
					}
					  int b_index=(b-1)/3+1;
					  list_kpieve.get(b_index).setSumEverydemo(list_kpieve.get(b_index).getSumEverydemo().add(eve.getSumEverydemo()));
				      list_kpieve.get(b_index).setSumStandarddemo(list_kpieve.get(b_index).getSumStandarddemo().add(eve.getSumStandarddemo()));
				      list_kpieve.get(b_index).setSumActualdemo(list_kpieve.get(b_index).getSumActualdemo().add(eve.getSumActualdemo()));
				      list_kpieve.get(b_index).setSumActualpairs(list_kpieve.get(b_index).getSumActualpairs().add(eve.getSumActualpairs()));
				      list_kpieve.get(b_index).setSumFacpairs(list_kpieve.get(b_index).getSumFacpairs().add(eve.getSumFacpairs()));
				      list_kpieve.get(b_index).setWorkhours(list_kpieve.get(b_index).getWorkhours()+eve.getWorkhours());
				      list_kpieve.get(b_index).setPersonzg(list_kpieve.get(b_index).getPersonzg()+eve.getPersonzg());
				      list_kpieve.get(b_index).setPersonjg(list_kpieve.get(b_index).getPersonjg()+eve.getPersonjg());
				      list_kpieve.get(b_index).setTimezg(list_kpieve.get(b_index).getTimezg()+eve.getTimezg());
				      list_kpieve.get(b_index).setTimejg(list_kpieve.get(b_index).getTimejg()+eve.getTimejg());
				      list_kpieve.get(b_index).setAddtimezg(list_kpieve.get(b_index).getAddtimezg()+eve.getAddtimezg());
				      list_kpieve.get(b_index).setAddtimejg(list_kpieve.get(b_index).getAddtimejg()+eve.getAddtimejg());
				      list_kpieve.get(b_index).setLeavenumzg(list_kpieve.get(b_index).getLeavenumzg()+eve.getLeavenumzg());
				      list_kpieve.get(b_index).setLeavenumjg(list_kpieve.get(b_index).getLeavenumjg()+eve.getLeavenumjg());
				      list_kpieve.get(b_index).setHurtnum(list_kpieve.get(b_index).getHurtnum()+eve.getHurtnum());
				      list_kpieve.get(b_index).setInvcount(list_kpieve.get(b_index).getInvcount()+eve.getInvcount());
				      list_kpieve.get(b_index).setSellcount(list_kpieve.get(b_index).getSellcount()+eve.getSellcount());
				      list_kpieve.get(b_index).setCostcount(list_kpieve.get(b_index).getCostcount()+eve.getCostcount());
				      list_kpieve.get(b_index).setWagezgUsd(list_kpieve.get(b_index).getWagezgUsd()+eve.getWagezgUsd());
				      list_kpieve.get(b_index).setWagejgUsd(list_kpieve.get(b_index).getWagejgUsd()+eve.getWagejgUsd());
				      list_kpieve.get(b_index).setCashcount(list_kpieve.get(b_index).getCashcount()+eve.getCashcount());
				      list_kpieve.get(b_index).setSideweit(list_kpieve.get(b_index).getSideweit()+eve.getSideweit());
				      list_kpieve.get(b_index).setBadweit(list_kpieve.get(b_index).getBadweit()+eve.getBadweit());
				      list_kpieve.get(b_index).setOtherbadweight(list_kpieve.get(b_index).getOtherbadweight()+eve.getOtherbadweight());
				      list_kpieve.get(b_index).setOtherweight(list_kpieve.get(b_index).getOtherweight().add(eve.getOtherweight()));
				      list_kpieve.get(b_index).setWaterton(list_kpieve.get(b_index).getWaterton()+eve.getWaterton());
				      list_kpieve.get(b_index).setElectricdu(list_kpieve.get(b_index).getElectricdu()+eve.getElectricdu());
				      list_kpieve.get(b_index).setGaston(list_kpieve.get(b_index).getGaston()+eve.getGaston());
				      list_kpieve.get(b_index).setStorenum(list_kpieve.get(b_index).getStorenum()+eve.getStorenum());
				      list_kpieve.get(b_index).setOutnum(list_kpieve.get(b_index).getOutnum()+eve.getOutnum());
				      list_kpieve.get(b_index).setMinusnum(list_kpieve.get(b_index).getMinusnum()+eve.getMinusnum());
				      list_kpieve.get(b_index).setActlost(list_kpieve.get(b_index).getActlost()+eve.getActlost());
				      list_kpieve.get(b_index).setAvgbuttomweight2(list_kpieve.get(b_index).getAvgbuttomweight2()+eve.getAvgbuttomweight2());
				      list_kpieve.get(b_index).setProductednum(list_kpieve.get(b_index).getProductednum()+eve.getProductednum());
				      list_kpieve.get(b_index).setNoglueweight(list_kpieve.get(b_index).getNoglueweight()+eve.getNoglueweight());
				      list_kpieve.get(b_index).setRepairmoney(list_kpieve.get(b_index).getRepairmoney()+eve.getRepairmoney());
				      list_kpieve.get(b_index).setInstorenum(list_kpieve.get(b_index).getInstorenum()+eve.getInstorenum());			     
				      list_kpieve.get(b_index).setHole(list_kpieve.get(b_index).getHole()+eve.getHole());
				      list_kpieve.get(b_index).setSumWorkday(list_kpieve.get(b_index).getSumWorkday().add(eve.getSumWorkday()));
				      list_kpieve.get(b_index).setWaterusd(list_kpieve.get(b_index).getWaterusd()+eve.getWaterusd());
				      list_kpieve.get(b_index).setElectricusd(list_kpieve.get(b_index).getElectricusd()+eve.getElectricusd());
				      list_kpieve.get(b_index).setGasusd(list_kpieve.get(b_index).getGasusd()+eve.getGasusd());				      
				      list_kpieve.get(b_index).setThickused(list_kpieve.get(b_index).getThickused()+eve.getThickused());
				      list_kpieve.get(b_index).setBackfeed(list_kpieve.get(b_index).getBackfeed()+eve.getBackfeed());			      
				      list_kpieve.get(b_index).setOilback(list_kpieve.get(b_index).getOilback()+eve.getOilback());
				      list_kpieve.get(b_index).setDrugsused(list_kpieve.get(b_index).getDrugsused()+eve.getDrugsused());
				      list_kpieve.get(b_index).setColorused(list_kpieve.get(b_index).getColorused()+eve.getColorused());
				      list_kpieve.get(b_index).setLeavemoney(list_kpieve.get(b_index).getLeavemoney()+eve.getLeavemoney());
				      list_kpieve.get(b_index).setPaypairs(list_kpieve.get(b_index).getPaypairs()+eve.getPaypairs());
				      list_kpieve.get(b_index).setBadcount(list_kpieve.get(b_index).getBadcount()+eve.getBadcount());
				      
				      if(b==12){
				    	  for(int i=0;i<3;i++){
				    		  list_kpieve.get(i+5).setSumEverydemo(list_kpieve.get(2*i+1).getSumEverydemo().add(list_kpieve.get(2*i+2).getSumEverydemo()));
						      list_kpieve.get(i+5).setSumStandarddemo(list_kpieve.get(2*i+1).getSumStandarddemo().add(list_kpieve.get(2*i+2).getSumStandarddemo()));
						      list_kpieve.get(i+5).setSumActualdemo(list_kpieve.get(2*i+1).getSumActualdemo().add(list_kpieve.get(2*i+2).getSumActualdemo()));
						      list_kpieve.get(i+5).setSumActualpairs(list_kpieve.get(2*i+1).getSumActualpairs().add(list_kpieve.get(2*i+2).getSumActualpairs()));
						      list_kpieve.get(i+5).setSumFacpairs(list_kpieve.get(2*i+1).getSumFacpairs().add(list_kpieve.get(2*i+2).getSumFacpairs()));
						      list_kpieve.get(i+5).setWorkhours(list_kpieve.get(2*i+1).getWorkhours()+list_kpieve.get(2*i+2).getWorkhours());
						      list_kpieve.get(i+5).setPersonzg(list_kpieve.get(2*i+1).getPersonzg()+list_kpieve.get(2*i+2).getPersonzg());
						      list_kpieve.get(i+5).setPersonjg(list_kpieve.get(2*i+1).getPersonjg()+list_kpieve.get(2*i+2).getPersonjg());
						      list_kpieve.get(i+5).setTimezg(list_kpieve.get(2*i+1).getTimezg()+list_kpieve.get(2*i+2).getTimezg());
						      list_kpieve.get(i+5).setTimejg(list_kpieve.get(2*i+1).getTimejg()+list_kpieve.get(2*i+2).getTimejg());
						      list_kpieve.get(i+5).setAddtimezg(list_kpieve.get(2*i+1).getAddtimezg()+list_kpieve.get(2*i+2).getAddtimezg());
						      list_kpieve.get(i+5).setAddtimejg(list_kpieve.get(2*i+1).getAddtimejg()+list_kpieve.get(2*i+2).getAddtimejg());
						      list_kpieve.get(i+5).setLeavenumzg(list_kpieve.get(2*i+1).getLeavenumzg()+list_kpieve.get(2*i+2).getLeavenumzg());
						      list_kpieve.get(i+5).setLeavenumjg(list_kpieve.get(2*i+1).getLeavenumjg()+list_kpieve.get(2*i+2).getLeavenumjg());
						      list_kpieve.get(i+5).setHurtnum(list_kpieve.get(2*i+1).getHurtnum()+list_kpieve.get(2*i+2).getHurtnum());
						      list_kpieve.get(i+5).setInvcount(list_kpieve.get(2*i+1).getInvcount()+list_kpieve.get(2*i+2).getInvcount());
						      list_kpieve.get(i+5).setSellcount(list_kpieve.get(2*i+1).getSellcount()+list_kpieve.get(2*i+2).getSellcount());
						      list_kpieve.get(i+5).setCostcount(list_kpieve.get(2*i+1).getCostcount()+list_kpieve.get(2*i+2).getCostcount());
						      list_kpieve.get(i+5).setWagezgUsd(list_kpieve.get(2*i+1).getWagezgUsd()+list_kpieve.get(2*i+2).getWagezgUsd());
						      list_kpieve.get(i+5).setWagejgUsd(list_kpieve.get(2*i+1).getWagejgUsd()+list_kpieve.get(2*i+2).getWagejgUsd());
						      list_kpieve.get(i+5).setCashcount(list_kpieve.get(2*i+1).getCashcount()+list_kpieve.get(2*i+2).getCashcount());
						      list_kpieve.get(i+5).setSideweit(list_kpieve.get(2*i+1).getSideweit()+list_kpieve.get(2*i+2).getSideweit());
						      list_kpieve.get(i+5).setBadweit(list_kpieve.get(2*i+1).getBadweit()+list_kpieve.get(2*i+2).getBadweit());
						      list_kpieve.get(i+5).setOtherbadweight(list_kpieve.get(2*i+1).getOtherbadweight()+list_kpieve.get(2*i+2).getOtherbadweight());
						      list_kpieve.get(i+5).setOtherweight(list_kpieve.get(2*i+1).getOtherweight().add(eve.getOtherweight()));
						      list_kpieve.get(i+5).setWaterton(list_kpieve.get(2*i+1).getWaterton()+list_kpieve.get(2*i+2).getWaterton());
						      list_kpieve.get(i+5).setElectricdu(list_kpieve.get(2*i+1).getElectricdu()+list_kpieve.get(2*i+2).getElectricdu());
						      list_kpieve.get(i+5).setGaston(list_kpieve.get(2*i+1).getGaston()+list_kpieve.get(2*i+2).getGaston());
						      list_kpieve.get(i+5).setStorenum(list_kpieve.get(2*i+1).getStorenum()+list_kpieve.get(2*i+2).getStorenum());
						      list_kpieve.get(i+5).setOutnum(list_kpieve.get(2*i+1).getOutnum()+list_kpieve.get(2*i+2).getOutnum());
						      list_kpieve.get(i+5).setMinusnum(list_kpieve.get(2*i+1).getMinusnum()+list_kpieve.get(2*i+2).getMinusnum());
						      list_kpieve.get(i+5).setActlost(list_kpieve.get(2*i+1).getActlost()+list_kpieve.get(2*i+2).getActlost());
						      list_kpieve.get(i+5).setAvgbuttomweight2(list_kpieve.get(2*i+1).getAvgbuttomweight2()+list_kpieve.get(2*i+2).getAvgbuttomweight2());
						      list_kpieve.get(i+5).setProductednum(list_kpieve.get(2*i+1).getProductednum()+list_kpieve.get(2*i+2).getProductednum());
						      list_kpieve.get(i+5).setNoglueweight(list_kpieve.get(2*i+1).getNoglueweight()+list_kpieve.get(2*i+2).getNoglueweight());
						      list_kpieve.get(i+5).setRepairmoney(list_kpieve.get(2*i+1).getRepairmoney()+list_kpieve.get(2*i+2).getRepairmoney());
						      list_kpieve.get(i+5).setInstorenum(list_kpieve.get(2*i+1).getInstorenum()+list_kpieve.get(2*i+2).getInstorenum());							      
						      list_kpieve.get(i+5).setHole(list_kpieve.get(2*i+1).getHole()+list_kpieve.get(2*i+2).getHole());
						      list_kpieve.get(i+5).setSumWorkday(list_kpieve.get(2*i+1).getSumWorkday().add(list_kpieve.get(2*i+2).getSumWorkday()));
						      list_kpieve.get(i+5).setWaterusd(list_kpieve.get(2*i+1).getWaterusd()+list_kpieve.get(2*i+2).getWaterusd());
						      list_kpieve.get(i+5).setElectricusd(list_kpieve.get(2*i+1).getElectricusd()+list_kpieve.get(2*i+2).getElectricusd());
						      list_kpieve.get(i+5).setGasusd(list_kpieve.get(2*i+1).getGasusd()+list_kpieve.get(2*i+2).getGasusd());						      					     
						      list_kpieve.get(i+5).setThickused(list_kpieve.get(2*i+1).getThickused()+list_kpieve.get(2*i+2).getThickused());
						      list_kpieve.get(i+5).setBackfeed(list_kpieve.get(2*i+1).getBackfeed()+list_kpieve.get(2*i+2).getBackfeed());						      
						      list_kpieve.get(i+5).setOilback(list_kpieve.get(2*i+1).getOilback()+list_kpieve.get(2*i+2).getOilback());						      
						      list_kpieve.get(i+5).setDrugsused(list_kpieve.get(2*i+1).getDrugsused()+list_kpieve.get(2*i+2).getDrugsused());
						      list_kpieve.get(i+5).setColorused(list_kpieve.get(2*i+1).getColorused()+list_kpieve.get(2*i+2).getColorused());
						      list_kpieve.get(i+5).setLeavemoney(list_kpieve.get(2*i+1).getLeavemoney()+list_kpieve.get(2*i+2).getLeavemoney());
						      list_kpieve.get(i+5).setPaypairs(list_kpieve.get(2*i+1).getPaypairs()+list_kpieve.get(2*i+2).getPaypairs());
						      list_kpieve.get(i+5).setBadcount(list_kpieve.get(2*i+1).getBadcount()+list_kpieve.get(2*i+2).getBadcount());					      
				    	  }
				    	  list3_q1=this.findResult(b/4,list_kpieve.get(1));//list_kpieve.get(1)第一季度							
						  list2_all.add(list3_q1);
						  list3_q2=this.findResult(b/4,list_kpieve.get(2));	//	list_kpieve.get(2):第二季度					
						  list3_half1=this.findResult(b/2,list_kpieve.get(5));//list_kpieve.get(5):上半年										
						  list2_all.add(list3_q2);
						  list2_all.add(list3_half1);
						  list3_q3=this.findResult(b/4,list_kpieve.get(3));//list_kpieve.get(3):第三季度							
						  list2_all.add(list3_q3);
						  list3_q4=this.findResult(b/4,list_kpieve.get(4));	//list_kpieve.get(3):第四季度						
						  list3_half2=this.findResult(b/2,list_kpieve.get(6));	//list_kpieve.get(5):下半年						
						  list3_year=this.findResult(b,list_kpieve.get(7));	//list_kpieve.get(6):全年						
						  list2_all.add(list3_q4);
						  list2_all.add(list3_half2);
						  list2_all.add(list3_year);
				      }																		
				}// end if						
			}//end for2
			list1_all.add(list2_all);
		}// end for1
		
		
		for(int k=0;k<list_factcode.size();k++){//start for1			
			WebFact fact=list_factcode.get(k);
			String factCode=fact.getId().getFactArea();	
			Kpifact kpi_pur=kpiSer.findById(factNo, factCode, yymm);//每年的預計目標(20150327)
			List<Double> list_content_pur=new ArrayList<Double>();//每年的預計目標封裝在一個list，以便於for循環(20150327)
			if(kpi_pur!=null){//start if2(20150327)
				list_content_pur=kpiFactToDouble(kpi_pur);	//KpiFact目標各項封裝到List<Double>									
			}//end if2
			
			//填充標題
			CellRangeAddress addTitle=new CellRangeAddress(0,(short)0,0,(short)list_column.size()-1);
			sheet.addMergedRegion(addTitle);
			sheet.createRow(0).createCell(0).setCellValue(title);
			sheet.getRow(0).getCell(0).setCellStyle(cs_head);
			for(int a=1;a<list_column.size();a++){
				sheet.getRow(0).createCell(a).setCellStyle(cs_head);
			}
			//填充表頭(包括廠別狀態)
			sheet.createRow(2+45*k).createCell(0).setCellValue("形態:"+factCode);
			HSSFRow row_columnHead=sheet.createRow(3+45*k);			
			for(int h=0;h<list_column.size();h++){
				String column=list_column.get(h);
				HSSFCell cell_columnHead=row_columnHead.createCell(h);
				cell_columnHead.setCellValue(column);
				cell_columnHead.setCellStyle(cs_column);
				if(h==1||h==3){
					sheet.setColumnWidth(h, 4000);//列寬
				}else{
					sheet.setColumnWidth(h, 3000);//列寬
				}
				
			}
			//填充內容
			for(int i=0;i<12;i++){//start for2
				
				int temp_num=0;
				if(i>2&&i<6){
					temp_num=1;
				}
				if(i>5&&i<9){
					temp_num=3;
				}
				if(i>8&&i<12){
					temp_num=4;
				}
				
				String month="";
				if(i+1<10){
					month="0"+(i+1);
				}else{
					month=""+(i+1);
				}
				switch(i){				
				case 0:					
					for(int j=0;j<list_str.size();j++){//start for3
						
						String index="";
						if(j+1<10){
							index="0"+(j+1);
						}else{
							index=""+(j+1);
						}
						HSSFRow row=sheet.createRow(4+j+45*k);
						HSSFCell cell0=row.createCell(i);
						HSSFCell cell1=row.createCell(i+1);
						HSSFCell cell2=row.createCell(i+2);
						HSSFCell cell3=row.createCell(i+3);//20150330
						cell0.setCellValue(index);
						cell0.setCellStyle(cs);
						cell1.setCellValue(list_str.get(j));
						cell1.setCellStyle(cs);
						cell2.setCellValue(list_unit.get(j));
						cell2.setCellStyle(cs);
						HSSFCellStyle cs_temp=findStyle(wb, j);
						if(kpi_pur!=null){							
							//數字格式的選擇
							/*if(j==0||j==2){
								cs_temp=cs_poi1;
							}else if(j==1||j==5||j==6||j==9||j==10){
								cs_temp=cs_poi;
							}else if(j>12&&j<17){
								cs_temp=cs_poi4;
							}else if(j>7&&j<13||j==22||j==23){
								cs_temp=cs_poi2;
							}else{
								cs_temp=cs_percent;
							}*/
							cell3.setCellValue(list_content_pur.get(j));
							cell3.setCellStyle(cs_temp);
						}else{
							cell3.setCellValue("無數據");
							cell3.setCellStyle(cs);
						}
						
					}//end for3
					break;								
				}
				VKpifact kpi=vkpiSer.findById(factNo, factCode,year+month);								
				List<Double> list_content=new ArrayList<Double>();
				if(kpi!=null){// start if1
					BigDecimal big=new BigDecimal(0.00);
					if(kpi.getFactaddRate()==null){
						kpi.setFactaddRate(big);
					}
					if(kpi.getOutrequestRate()==null){
						kpi.setOutrequestRate(big);
					}
					if(kpi.getAvgPermoney()==null){
						kpi.setAvgPermoney(big);
					}
					if(kpi.getPermoney()==null){
						kpi.setPermoney(big);
					}
					if(kpi.getWasteFact()==null){
						kpi.setWasteFact(big);
					}
					if(kpi.getWasteNo()==null){
						kpi.setWasteNo(big);
					}
					if(kpi.getWasteUsd()==null){
						kpi.setWasteUsd(big);
					}
					if(kpi.getAvgCirclehour()==null){
						kpi.setAvgCirclehour(big);
					}
					list_content=VkpiFactToDouble(kpi);//VKpiFact對象各項封裝List<Double>集合
					
											
				}//end if1
																									
				/**
				 * 補全季度與年度統計
				 */
				List<List<Double>> list2=list1_all.get(k);//中間集合,每個中間集合包括1-4季度,上下半年,全年數庫
				List<Double>list3_q1=list2.get(0);//第一季度
				List<Double>list3_q2=list2.get(1);//第二季度
				List<Double>list3_half1=list2.get(2);//上半年
				List<Double>list3_q3=list2.get(3);//第三季度
				List<Double>list3_q4=list2.get(4);//第四季度
				List<Double>list3_half2=list2.get(5);//下半年
				List<Double>list3_year=list2.get(6);//全年
				
								
				
				/**
				 * 20150327
				 * 以下代碼代替以上注釋代碼
				 * 開始2
				 */
				for(int j=0;j<list_str.size();j++){
					HSSFRow row=sheet.getRow(4+j+45*k);
					HSSFCell cell=row.createCell(i+4+temp_num);
					HSSFCellStyle cs_temp=findStyle(wb,j);
					
					//數字格式的選擇
					/*if(j==0||j==2){
						cs_temp=cs_poi1;
					}else if(j==1||j==5||j==6||j==9||j==10){
						cs_temp=cs_poi;
					}else if(j>12&&j<17){
						cs_temp=cs_poi4;
					}else if(j>7&&j<13||j==22||j==23){
						cs_temp=cs_poi2;
					}else{
						cs_temp=cs_percent;
					}*/
					cell.setCellStyle(cs_temp);
					/**
					 * 20150327
					 * 開始3
					 * 月度的樣式判斷
					 */
					if(kpi!=null&&kpi_pur!=null){						
						Double num1=list_content.get(j);//實際值
						Double num2=list_content_pur.get(j);//預計值	
						HSSFCellStyle cs_temp2=findStyle(wb,num1,num2,j);
						/*boolean flag=true;
						if(j==0||j==1||j==2||j==4||j==8||j==9||j==10||j==11||j==12||j==22){
							flag=num1<num2;
						}else{
							flag=num1>num2;
						}
						if(flag){//start if
							//數字格式的選擇
							if(j==0||j==2){								
								cs_temp2=cs_poi1_bg;
							}else if(j==1||j==5||j==6||j==9||j==10){
								cs_temp2=cs_poi_bg;
							}else if(j>12&&j<17){
								cs_temp2=cs_poi4_bg;
							}else if(j>7&&j<13||j==22||j==23){
								cs_temp2=cs_poi2_bg;
							}else{
								cs_temp2=cs_percent_bg;
							}
							cell.setCellStyle(cs_temp2);
						}//end if						
*/					
						cell.setCellStyle(cs_temp2);
					}					
					/**
					 * 結束3
					 * 
					 */					
					if(kpi!=null){
						cell.setCellValue(list_content.get(j));
					}else{
						cell.setCellValue("無數據");
					}
					//cell.setCellStyle(cs_temp);
					
					/**
					 * 開始4	
					 * 季度，半年，全年的樣式分開判斷											
					 */
					if(i==2){
						HSSFCell cell2=row.createCell(i+5+temp_num);//第一季度
						cell2.setCellValue(list3_q1.get(j));
						cell2.setCellStyle(cs_temp);
						if(kpi_pur!=null){//start if
							Double num1=list3_q1.get(j);
							Double num2=list_content_pur.get(j);
							/*cs_temp=wb.createCellStyle();
							
							boolean flag=true;
							if(j==0||j==1||j==2||j==4||j==8||j==9||j==10||j==11||j==12||j==22){
								flag=num1<num2;
							}else{
								flag=num1>num2;
							}
							if(flag){
								//HSSFCellStyle cs_temp_q1=wb.createCellStyle();
								//數字格式的選擇
								if(j==0||j==2){								
									cs_temp=cs_poi1_bg;
								}else if(j==1||j==5||j==6||j==9||j==10){
									cs_temp=cs_poi_bg;
								}else if(j>12&&j<17){
									cs_temp=cs_poi4_bg;
								}else if(j>7&&j<13||j==22||j==23){
									cs_temp=cs_poi2_bg;
								}else{
									cs_temp=cs_percent_bg;
								}
								cell2.setCellStyle(cs_temp);
							}*/
							cell2.setCellStyle(findStyle(wb,num1,num2,j));
						}//end if
						
					}
					if(i==5){
						HSSFCell cell3_1=row.createCell(i+5+temp_num);//第二季度
						HSSFCell cell3_2=row.createCell(i+6+temp_num);//上半年
						cell3_1.setCellValue(list3_q2.get(j));
						cell3_2.setCellValue(list3_half1.get(j));
						cell3_1.setCellStyle(cs_temp);
						cell3_2.setCellStyle(cs_temp);
						if(kpi_pur!=null){//start if
							Double num1=list3_q2.get(j);
							Double num2=list3_half1.get(j);
							Double num3=list_content_pur.get(j);
							/*boolean flag1=true;
							boolean flag2=true;
							if(j==0||j==1||j==2||j==4||j==8||j==9||j==10||j==11||j==12||j==22){								
								flag1=num1<num3;
								flag2=num2<num3;
							}else{
								flag1=num1>num3;
								flag2=num2>num3;
							}
							if(flag1){
								//HSSFCellStyle cs_temp_q2=wb.createCellStyle();
								//數字格式的選擇
								if(j==0||j==2){								
									cs_temp=cs_poi1_bg;
								}else if(j==1||j==5||j==6||j==9||j==10){
									cs_temp=cs_poi_bg;
								}else if(j>12&&j<17){
									cs_temp=cs_poi4_bg;
								}else if(j>7&&j<13||j==22||j==23){
									cs_temp=cs_poi2_bg;
								}else{
									cs_temp=cs_percent_bg;
								}
								cell3_1.setCellStyle(cs_temp);
							}
							if(flag2){
								//HSSFCellStyle cs_temp_half1=wb.createCellStyle();
								//數字格式的選擇
								if(j==0||j==2){								
									cs_temp=cs_poi1_bg;
								}else if(j==1||j==5||j==6||j==9||j==10){
									cs_temp=cs_poi_bg;
								}else if(j>12&&j<17){
									cs_temp=cs_poi4_bg;
								}else if(j>7&&j<13||j==22||j==23){
									cs_temp=cs_poi2_bg;
								}else{
									cs_temp=cs_percent_bg;
								}	
								cell3_2.setCellStyle(cs_temp);
							}*/
							cell3_1.setCellStyle(findStyle(wb,num1,num3,j));
							cell3_2.setCellStyle(findStyle(wb,num2,num3,j));
						}//end if
						
					}
					if(i==8){
						HSSFCell cell4=row.createCell(i+5+temp_num);//第三季度
						cell4.setCellValue(list3_q3.get(j));
						cell4.setCellStyle(cs_temp);
						if(kpi_pur!=null){//start if
							Double num1=list3_q3.get(j);
							Double num2=list_content_pur.get(j);
							/*boolean flag=true;
							if(j==0||j==1||j==2||j==4||j==8||j==9||j==10||j==11||j==12||j==22){
								flag=num1<num2;
							}else{
								flag=num1>num2;
							}
							if(flag){
								//HSSFCellStyle cs_temp_q3=wb.createCellStyle();
								//數字格式的選擇
								if(j==0||j==2){								
									cs_temp=cs_poi1_bg;
								}else if(j==1||j==5||j==6||j==9||j==10){
									cs_temp=cs_poi_bg;
								}else if(j>12&&j<17){
									cs_temp=cs_poi4_bg;
								}else if(j>7&&j<13||j==22||j==23){
									cs_temp=cs_poi2_bg;
								}else{
									cs_temp=cs_percent_bg;
								}
								cell4.setCellStyle(cs_temp);
							}*/
							cell4.setCellStyle(findStyle(wb,num1,num2,j));
						}//end if
						
					}
					if(i==11){
						HSSFCell cell5=row.createCell(i+5+temp_num);//第四季度
						HSSFCell cell5_2=row.createCell(i+6+temp_num);//下半年
						HSSFCell cell5_3=row.createCell(i+7+temp_num);//全年
						cell5.setCellValue(list3_q4.get(j));
						cell5_2.setCellValue(list3_half2.get(j));
						cell5_3.setCellValue(list3_year.get(j));
						cell5.setCellStyle(cs_temp);
						cell5_2.setCellStyle(cs_temp);
						cell5_3.setCellStyle(cs_temp);
						if(kpi_pur!=null){//start if
							Double num_q4=list3_q4.get(j);
							Double num_half2=list3_half2.get(j);
							Double num_year=list3_year.get(j);
							Double num=list_content_pur.get(j);
							/*boolean flag1=true;
							boolean flag2=true;
							boolean flag3=true;
							if(j==0||j==1||j==2||j==4||j==8||j==9||j==10||j==11||j==12||j==22){
								flag1=num_q4<num;
								flag2=num_half2<num;
								flag3=num_year<num;
							}else{
								flag1=num_q4>num;
								flag2=num_half2>num;
								flag3=num_year>num;
							}
							if(flag1){
								//數字格式的選擇
								if(j==0||j==2){								
									cs_temp=cs_poi1_bg;
								}else if(j==1||j==5||j==6||j==9||j==10){
									cs_temp=cs_poi_bg;
								}else if(j>12&&j<17){
									cs_temp=cs_poi4_bg;
								}else if(j>7&&j<13||j==22||j==23){
									cs_temp=cs_poi2_bg;
								}else{
									cs_temp=cs_percent_bg;
								}
								cell5.setCellStyle(cs_temp);
							}
							if(flag2){
								//數字格式的選擇
								if(j==0||j==2){								
									cs_temp=cs_poi1_bg;
								}else if(j==1||j==5||j==6||j==9||j==10){
									cs_temp=cs_poi_bg;
								}else if(j>12&&j<17){
									cs_temp=cs_poi4_bg;
								}else if(j>7&&j<13||j==22||j==23){
									cs_temp=cs_poi2_bg;
								}else{
									cs_temp=cs_percent_bg;
								}
								cell5_2.setCellStyle(cs_temp);
								
							}
							if(flag3){
								//數字格式的選擇
								if(j==0||j==2){								
									cs_temp=cs_poi1_bg;
								}else if(j==1||j==5||j==6||j==9||j==10){
									cs_temp=cs_poi_bg;
								}else if(j>12&&j<17){
									cs_temp=cs_poi4_bg;
								}else if(j>7&&j<13||j==22||j==23){
									cs_temp=cs_poi2_bg;
								}else{
									cs_temp=cs_percent_bg;
								}
								cell5_3.setCellStyle(cs_temp);
							}*/
							cell5.setCellStyle(findStyle(wb,num_q4,num,j));
							cell5_2.setCellStyle(findStyle(wb,num_half2,num,j));
							cell5_3.setCellStyle(findStyle(wb,num_year,num,j));
						}//end if
					}
			    }
				/**
				 * 結束4
				 */
				
				/**
				 * 結束2
				 */
				
				
			}//end for2
		}//end for1
		ServletOutputStream os=response.getOutputStream();
		response.setContentType("application/vnd.ms-excel");
		title=title+".xls";
		int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");//判斷是否為IE瀏覽器,大於0則為IE瀏覽器
		String fileName="";
		if(msie>0){
			fileName=java.net.URLEncoder.encode(title,"utf-8");//解決IE中文文件不能下載的問題
		}else{
			fileName=new String(title.getBytes("utf-8"),"iso-8859-1");//解決非IE中文名亂碼問題
		}		
		response.setHeader("Content-disposition", "attachment;filename="+fileName);
		wb.write(os);
		os.close();
	}
	public void print_month() throws IOException, ParseException{
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet("KPI-工廠");		
		/**
		 * 報表相關樣式
		 */
		Map<String,Object>map=findStyles(wb);
		//標題樣式
		HSSFCellStyle cs_head=(HSSFCellStyle)map.get("cs_head");		
		//標準單元格樣式
		HSSFCellStyle cs=(HSSFCellStyle)map.get("cs");		
		//表頭樣式
		HSSFCellStyle cs_column=(HSSFCellStyle)map.get("cs_column");
		/**
		 * 數字格式（有背景顏色與無背景顏色）
		 */
		
		//無背景
		HSSFCellStyle cs_percent=(HSSFCellStyle)map.get("cs_percent");				
		HSSFCellStyle cs_poi=(HSSFCellStyle)map.get("cs_poi");	
		HSSFCellStyle cs_poi1=(HSSFCellStyle)map.get("cs_poi1");		
		HSSFCellStyle cs_poi2=(HSSFCellStyle)map.get("cs_poi2");
		HSSFCellStyle cs_poi4=(HSSFCellStyle)map.get("cs_poi4");		
		//有背景
		HSSFCellStyle cs_percent_bg=(HSSFCellStyle)map.get("cs_percent_bg");				
		HSSFCellStyle cs_poi_bg=(HSSFCellStyle)map.get("cs_poi_bg");		
		HSSFCellStyle cs_poi1_bg=(HSSFCellStyle)map.get("cs_poi1_bg");	
		HSSFCellStyle cs_poi2_bg=(HSSFCellStyle)map.get("cs_poi2_bg");		
		HSSFCellStyle cs_poi4_bg=(HSSFCellStyle)map.get("cs_poi4_bg");
						
		/**
		 * 獲取要循環的數據
		 */
		List<WebFact>list_factcode=webFactSer.findFactById_showA(factNo);				
		List<String>list_str=findListstrAndUnit().get(0);		
		List<String>list_unit=findListstrAndUnit().get(1);
		
		
		
		
		//表頭內容
		List<String>list_column=new ArrayList<String>();
		//獲取所有月份
		List<String>list_months=this.getMonths(yymm_begin, yymm_end);		
		list_column.add("項次");
		list_column.add("管制指標");
		list_column.add("單位");
		for(int a=0;a<list_months.size();a++){
			String ym=list_months.get(a);
			list_column.add(ym);
		}
		list_column.add("合計");
		
		String factName=webFactSer.selByid(factNo);
		String title=factName+"("+factNo+")"+yymm_begin+"--"+yymm_end+"KPI匯總表";
		
		/**
		 * 獲取季度,上下半年,全年數據
		 */
		//最外層集合
		List<List<List<Double>>>list1_all=new ArrayList<List<List<Double>>>();
		for(int a=0;a<list_factcode.size();a++){//start for1
			List<VKpifactEve>list_kpieve=new ArrayList<VKpifactEve>();
			for(int i=0;i<1;i++){
				list_kpieve.add(new VKpifactEve());
			}
			//合計
			/*Double total_sumEverydemo=0.00;Double total_sumStandarddemo=0.00;
			Double total_sumActualdemo=0.00;Double total_sumActualpairs=0.00;
			Double total_sumFactpairs=0.00;Double total_sumWorkhours=0.00;
			
			Double total_personzg=0.00;Double total_personjg=0.00;
			Double total_timezg=0.00;Double total_timejg=0.00;
			Double total_addtimezg=0.00;Double total_addtimejg=0.00;
			Double total_leavenumzg=0.00;Double total_leavenumjg=0.00;
			Double total_hurtnum=0.00;
			
			Double total_invcount=0.00;Double total_sellcount=0.00;
			Double total_costcount=0.00;Double total_wagezgUsd=0.00;
			Double total_wagejgUsd=0.00;Double total_cashcount=0.00;
			
			Double total_sideweit=0.00;Double total_badweit=0.00;
			Double total_otherbadweight=0.00;
			Double total_otherweight=0.00;
			Double total_productnum=0.00;
			Double total_noglueweight=0.00;
			Double total_repairmoney=0.00;
			
			Double total_waterton=0.00;Double total_electricdu=0.00;
			Double total_gaston=0.00;
			
			Double total_storenum=0.00;Double total_outnum=0.00;
			Double total_minusnum=0.00;
			
			Double total_actlost=0.00;Double total_avgbuttomweight2=0.00;
			Double total_instorenum=0.00;*/
						
			//中間集合,用於裝"合計"數據
			List<List<Double>>list2_total=new ArrayList<List<Double>>();
			
			//單元集合,合計
			List<Double>list3_total=new ArrayList<Double>();
			
			WebFact fact=list_factcode.get(a);
			String factCode=fact.getId().getFactArea();						
			for(int b=0;b<list_months.size();b++){//start for2
				String ym=list_months.get(b);
				VKpifactEve eve=vkpieveSer.findById(factNo, factCode,ym)==null?new VKpifactEve():vkpieveSer.findById(factNo, factCode,ym);
				if(eve!=null){//start if
					/**
					 * sumActualpairs和sumFacpairs,otherweight有可能為空
					 */
					BigDecimal big=new BigDecimal(0.00);
					if(eve.getSumActualpairs()==null){
						eve.setSumActualpairs(big);
					}
					if(eve.getSumFacpairs()==null){
						eve.setSumFacpairs(big);
					}
					if(eve.getOtherweight()==null){
						eve.setOtherweight(big);
					}
					if(eve.getProductednum()==null){
						eve.setProductednum(0.0);
					}
					if(eve.getNoglueweight()==null){
						eve.setNoglueweight(0.0);
					}
					if(eve.getRepairmoney()==null){
						eve.setRepairmoney(0.0);
					}
					if(eve.getWorkhours()==null){
						eve.setWorkhours(0.0);
					}
					//合計
					 list_kpieve.get(0).setSumEverydemo(list_kpieve.get(0).getSumEverydemo().add(eve.getSumEverydemo()));
				      list_kpieve.get(0).setSumStandarddemo(list_kpieve.get(0).getSumStandarddemo().add(eve.getSumStandarddemo()));
				      list_kpieve.get(0).setSumActualdemo(list_kpieve.get(0).getSumActualdemo().add(eve.getSumActualdemo()));
				      list_kpieve.get(0).setSumActualpairs(list_kpieve.get(0).getSumActualpairs().add(eve.getSumActualpairs()));
				      list_kpieve.get(0).setSumFacpairs(list_kpieve.get(0).getSumFacpairs().add(eve.getSumFacpairs()));
				      list_kpieve.get(0).setWorkhours(list_kpieve.get(0).getWorkhours()+eve.getWorkhours());
				      list_kpieve.get(0).setPersonzg(list_kpieve.get(0).getPersonzg()+eve.getPersonzg());
				      list_kpieve.get(0).setPersonjg(list_kpieve.get(0).getPersonjg()+eve.getPersonjg());
				      list_kpieve.get(0).setTimezg(list_kpieve.get(0).getTimezg()+eve.getTimezg());
				      list_kpieve.get(0).setTimejg(list_kpieve.get(0).getTimejg()+eve.getTimejg());
				      list_kpieve.get(0).setAddtimezg(list_kpieve.get(0).getAddtimezg()+eve.getAddtimezg());
				      list_kpieve.get(0).setAddtimejg(list_kpieve.get(0).getAddtimejg()+eve.getAddtimejg());
				      list_kpieve.get(0).setLeavenumzg(list_kpieve.get(0).getLeavenumzg()+eve.getLeavenumzg());
				      list_kpieve.get(0).setLeavenumjg(list_kpieve.get(0).getLeavenumjg()+eve.getLeavenumjg());
				      list_kpieve.get(0).setHurtnum(list_kpieve.get(0).getHurtnum()+eve.getHurtnum());
				      list_kpieve.get(0).setInvcount(list_kpieve.get(0).getInvcount()+eve.getInvcount());
				      list_kpieve.get(0).setSellcount(list_kpieve.get(0).getSellcount()+eve.getSellcount());
				      list_kpieve.get(0).setCostcount(list_kpieve.get(0).getCostcount()+eve.getCostcount());
				      list_kpieve.get(0).setWagezgUsd(list_kpieve.get(0).getWagezgUsd()+eve.getWagezgUsd());
				      list_kpieve.get(0).setWagejgUsd(list_kpieve.get(0).getWagejgUsd()+eve.getWagejgUsd());
				      list_kpieve.get(0).setCashcount(list_kpieve.get(0).getCashcount()+eve.getCashcount());
				      list_kpieve.get(0).setSideweit(list_kpieve.get(0).getSideweit()+eve.getSideweit());
				      list_kpieve.get(0).setBadweit(list_kpieve.get(0).getBadweit()+eve.getBadweit());
				      list_kpieve.get(0).setOtherbadweight(list_kpieve.get(0).getOtherbadweight()+eve.getOtherbadweight());
				      list_kpieve.get(0).setOtherweight(list_kpieve.get(0).getOtherweight().add(eve.getOtherweight()));
				      list_kpieve.get(0).setWaterton(list_kpieve.get(0).getWaterton()+eve.getWaterton());
				      list_kpieve.get(0).setElectricdu(list_kpieve.get(0).getElectricdu()+eve.getElectricdu());
				      list_kpieve.get(0).setGaston(list_kpieve.get(0).getGaston()+eve.getGaston());
				      list_kpieve.get(0).setStorenum(list_kpieve.get(0).getStorenum()+eve.getStorenum());
				      list_kpieve.get(0).setOutnum(list_kpieve.get(0).getOutnum()+eve.getOutnum());
				      list_kpieve.get(0).setMinusnum(list_kpieve.get(0).getMinusnum()+eve.getMinusnum());
				      list_kpieve.get(0).setActlost(list_kpieve.get(0).getActlost()+eve.getActlost());
				      list_kpieve.get(0).setAvgbuttomweight2(list_kpieve.get(0).getAvgbuttomweight2()+eve.getAvgbuttomweight2());
				      list_kpieve.get(0).setProductednum(list_kpieve.get(0).getProductednum()+eve.getProductednum());
				      list_kpieve.get(0).setNoglueweight(list_kpieve.get(0).getNoglueweight()+eve.getNoglueweight());
				      list_kpieve.get(0).setRepairmoney(list_kpieve.get(0).getRepairmoney()+eve.getRepairmoney());
				      list_kpieve.get(0).setInstorenum(list_kpieve.get(0).getInstorenum()+eve.getInstorenum());
					
						/*total_sumEverydemo=total_sumEverydemo+eve.getSumEverydemo().doubleValue();total_sumStandarddemo=total_sumStandarddemo+eve.getSumStandarddemo().doubleValue();
						total_sumActualdemo=total_sumActualdemo+eve.getSumActualdemo().doubleValue();total_sumActualpairs=total_sumActualpairs+eve.getSumActualpairs().doubleValue();
						total_sumFactpairs=total_sumFactpairs+eve.getSumFacpairs().doubleValue();total_sumWorkhours=total_sumWorkhours+eve.getWorkhours();
						
						total_personzg=total_personzg+eve.getPersonzg();total_personjg=total_personjg+eve.getPersonjg();
						total_timezg=total_timezg+eve.getTimezg();total_timejg=total_timejg+eve.getTimejg();
						total_addtimezg=total_addtimezg+eve.getAddtimezg();total_addtimejg=total_addtimejg+eve.getAddtimejg();
						total_leavenumzg=total_leavenumzg+eve.getLeavenumzg();total_leavenumjg=total_leavenumjg+eve.getLeavenumjg();
						total_hurtnum=total_hurtnum+eve.getHurtnum();
						
						total_invcount=total_invcount+eve.getInvcount();total_sellcount=total_sellcount+eve.getSellcount();
						total_costcount=total_costcount+eve.getCostcount();total_wagezgUsd=total_wagezgUsd+eve.getWagezgUsd();
						total_wagejgUsd=total_wagejgUsd+eve.getWagejgUsd();total_cashcount=total_cashcount+eve.getCashcount();
						
						total_sideweit=total_sideweit+eve.getSideweit();total_badweit=total_badweit+eve.getBadweit();
						total_otherbadweight=total_otherbadweight+eve.getOtherbadweight();
						
						total_waterton=total_waterton+eve.getWaterton();total_electricdu=total_electricdu+eve.getElectricdu();
						total_gaston=total_gaston+eve.getGaston();
						
						total_storenum=total_storenum+eve.getStorenum();total_outnum=total_outnum+eve.getOutnum();
						total_minusnum=total_minusnum+eve.getMinusnum();
						
						total_actlost=total_actlost+eve.getActlost();total_avgbuttomweight2=total_avgbuttomweight2+eve.getAvgbuttomweight2();
						total_otherweight=total_otherweight+eve.getOtherweight().doubleValue();
						total_productnum=total_productnum+eve.getProductednum();
						total_noglueweight=total_noglueweight+eve.getNoglueweight();
						total_repairmoney=total_repairmoney+eve.getRepairmoney();
						total_instorenum=total_instorenum+eve.getInstorenum();*/
																																			
				}// end if				
				//合計				
					if(b==list_months.size()-1){
						list3_total=this.findResult(b,list_kpieve.get(0));
						list2_total.add(list3_total);
					}																																			
			}//end for2
			list1_all.add(list2_total);
		}// end for1
		
		for(int k=0;k<list_factcode.size();k++){//start for1												
			WebFact fact=list_factcode.get(k);
			String factCode=fact.getId().getFactArea();
			//填充標題
			CellRangeAddress addTitle=new CellRangeAddress(0,(short)0,0,(short)list_column.size()-1);
			sheet.addMergedRegion(addTitle);
			sheet.createRow(0).createCell(0).setCellValue(title);
			sheet.getRow(0).getCell(0).setCellStyle(cs_head);
			for(int a=1;a<list_column.size();a++){
				sheet.getRow(0).createCell(a).setCellStyle(cs_head);
			}
			//填充表頭(包括廠別狀態)
			sheet.createRow(2+cursor*k).createCell(0).setCellValue("形態:"+factCode);
			HSSFRow row_columnHead=sheet.createRow(3+cursor*k);			
			for(int h=0;h<list_column.size();h++){
				String column=list_column.get(h);
				HSSFCell cell_columnHead=row_columnHead.createCell(h);
				cell_columnHead.setCellValue(column);
				cell_columnHead.setCellStyle(cs_column);
				sheet.setColumnWidth(h, 3000);//列寬
			}
			//填充內容
			for(int i=0;i<list_months.size();i++){//start for2
				//獲取每個日期
				String ym=list_months.get(i);
				int temp_num=0;
				
				/*if(i==list_months.size()-1){
					temp_num=1;
				}*/
				switch(i){				
				case 0:					
					for(int j=0;j<list_str.size();j++){//start for3
						
						String index="";
						if(j+1<10){
							index="0"+(j+1);
						}else{
							index=""+(j+1);
						}
						HSSFRow row=sheet.createRow(4+j+cursor*k);
						HSSFCell cell0=row.createCell(i);
						HSSFCell cell1=row.createCell(i+1);
						HSSFCell cell2=row.createCell(i+2);
						cell0.setCellValue(index);
						cell0.setCellStyle(cs);
						cell1.setCellValue(list_str.get(j));
						cell1.setCellStyle(cs);
						cell2.setCellValue(list_unit.get(j));
						cell2.setCellStyle(cs);																	
					}//end for3
					break;								
				}
				VKpifact kpi=vkpiSer.findById(factNo, factCode,ym);
				List<Double> list_content=new ArrayList<Double>();
				if(kpi!=null){// start if1
					BigDecimal big=new BigDecimal(0.00);
					if(kpi.getFactaddRate()==null){
						kpi.setFactaddRate(big);
					}
					if(kpi.getOutrequestRate()==null){
						kpi.setOutrequestRate(big);
					}
					if(kpi.getAvgPermoney()==null){
						kpi.setAvgPermoney(big);
					}
					if(kpi.getPermoney()==null){
						kpi.setPermoney(big);
					}
					if(kpi.getWasteFact()==null){
						kpi.setWasteFact(big);
					}
					if(kpi.getWasteNo()==null){
						kpi.setWasteNo(big);
					}
					if(kpi.getWasteUsd()==null){
						kpi.setWasteUsd(big);
					}
					if(kpi.getAvgCirclehour()==null){
						kpi.setAvgCirclehour(big);
					}
					list_content=this.VkpiFactToDouble(kpi);//VKpiFact對象各項封裝List<Double>集合
					
				}//end if1
													
				/**
				 * 補全"合計"統計
				 */
				List<List<Double>> list2=list1_all.get(k);//中間集合,每個中間集合"合計"數據
				List<Double>list3_total=list2.get(0);//合計							
				if(kpi!=null){	
					for(int j=0;j<list_str.size();j++){
						HSSFRow row=sheet.getRow(4+j+cursor*k);
						HSSFCell cell=row.createCell(i+3+temp_num);
						HSSFCellStyle cs_temp=wb.createCellStyle();
						//數字格式的選擇
						/*if(j==0||j==2){
							cs_temp=cs_poi1;
						}else if(j==1||j==5||j==6||j==9||j==10){
							cs_temp=cs_poi;
						}else if(j>12&&j<17){
							cs_temp=cs_poi4;
						}else if(j>7&&j<13||j==22||j==23){
							cs_temp=cs_poi2;
						}else{
							cs_temp=cs_percent;
						}*/
						cs_temp=this.findStyle(wb, j);
						cell.setCellValue(list_content.get(j));
						cell.setCellStyle(cs_temp);
																		
						if(i==list_months.size()-1){
							HSSFCell cell2=row.createCell(i+4+temp_num);//合計
							cell2.setCellValue(list3_total.get(j));
							cell2.setCellStyle(cs_temp);
						}
						
				    }				
				}else{
					for(int j=0;j<list_str.size();j++){
						HSSFRow row=sheet.getRow(4+j+cursor*k);
						HSSFCell cell=row.createCell(i+3+temp_num);
						//數字格式的選擇
						HSSFCellStyle cs_temp=wb.createCellStyle();
						/*if(j==0||j==2){
							cs_temp=cs_poi1;
						}else if(j==1||j==5||j==6||j==9||j==10){
							cs_temp=cs_poi;
						}else if(j>12&&j<17){
							cs_temp=cs_poi4;
						}else if(j>7&&j<13||j==22||j==23){
							cs_temp=cs_poi2;
						}else{
							cs_temp=cs_percent;
						}*/
						cs_temp=this.findStyle(wb, j);
						cell.setCellValue("無數據");						
						cell.setCellStyle(cs_temp);												
						if(i==list_months.size()-1){							
							HSSFCell cell2=row.createCell(i+4+temp_num);//第一季度
							cell2.setCellValue(list3_total.get(j));
							cell2.setCellStyle(cs_temp);
						}						
					}
					
				}
				
			}//end for2
		}//end for1
		ServletOutputStream os=response.getOutputStream();
		response.setContentType("application/vnd.ms-excel");
		title=title+".xls";
		int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");//判斷是否為IE瀏覽器,大於0則為IE瀏覽器
		String fileName="";
		if(msie>0){
			fileName=java.net.URLEncoder.encode(title,"utf-8");//解決IE中文文件不能下載的問題
		}else{
			fileName=new String(title.getBytes("utf-8"),"iso-8859-1");//解決非IE中文名亂碼問題
		}		
		response.setHeader("Content-disposition", "attachment;filename="+fileName);
		wb.write(os);
		os.close();
	}
	

	public void print_tw() throws IOException{
		//創建模板
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet();
		/**
		 * 模板相關樣式
		 */
		Map<String,Object>map=this.findStyles(wb);
		//標題樣式
		HSSFCellStyle cs_head=(HSSFCellStyle)map.get("cs_head");		
		//標準單元格樣式
		HSSFCellStyle cs=(HSSFCellStyle)map.get("cs");		
		//表頭樣式
		HSSFCellStyle cs_column=(HSSFCellStyle)map.get("cs_column");
		/**
		 * 數字格式（有背景顏色與無背景顏色）
		 */
		
		//無背景
		HSSFCellStyle cs_percent=(HSSFCellStyle)map.get("cs_percent");				
		HSSFCellStyle cs_poi=(HSSFCellStyle)map.get("cs_poi");	
		HSSFCellStyle cs_poi1=(HSSFCellStyle)map.get("cs_poi1");		
		HSSFCellStyle cs_poi2=(HSSFCellStyle)map.get("cs_poi2");
		HSSFCellStyle cs_poi4=(HSSFCellStyle)map.get("cs_poi4");		
		//有背景
		HSSFCellStyle cs_percent_bg=(HSSFCellStyle)map.get("cs_percent_bg");				
		HSSFCellStyle cs_poi_bg=(HSSFCellStyle)map.get("cs_poi_bg");		
		HSSFCellStyle cs_poi1_bg=(HSSFCellStyle)map.get("cs_poi1_bg");	
		HSSFCellStyle cs_poi2_bg=(HSSFCellStyle)map.get("cs_poi2_bg");		
		HSSFCellStyle cs_poi4_bg=(HSSFCellStyle)map.get("cs_poi4_bg");
		
		
		
		
		//粗藍色字體		
		HSSFFont font_blue=wb.createFont();
		font_blue.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_blue.setFontHeightInPoints((short)12);
		font_blue.setColor(IndexedColors.BLUE.getIndex());
		
		
							
		/**
		 * 數據獲取
		 */
		//各項數據名稱和各項單位
		List<String>list_contentName=findListstrAndUnit().get(0);
		List<String>list_unit=findListstrAndUnit().get(1);
		
	
		//所有廠別狀態
		List<String>list_factCode=new ArrayList<String>();
		List<List<String>>list_factNo_all=new ArrayList<List<String>>();
		//如果頁面上有選factcode
        if(list_factcode!=null&&list_factcode.size()>0){
        	list_factCode=list_factcode;
			for(int i=0;i<list_factcode.size();i++){
				String factCode=list_factcode.get(i);
				List<String>list_factNo=new ArrayList();
				for(int y=0;y<list_factno.size();y++){
					String temp=list_factno.get(y);
					String[]objs=temp.split("_");
					if(objs[0].equals(factCode)){
						list_factNo.add(objs[1]);
					}
				}
				list_factNo_all.add(list_factNo);
			}
		//如果沒有沒有,則默認全部	
		}else{
			List<Object[]>list_objs=webFactSer.findAllFactCode2_showA();
			for(int i=0;i<list_objs.size();i++){
				Object[]obj=list_objs.get(i);
				String fact_code=obj[0].toString();
				list_factCode.add(fact_code);
			}
		}
		
		for(int a=0;a<list_factCode.size();a++){//start for1
			//每個狀態所包含的廠別
			String factCode=list_factCode.get(a);
			List<String>list_factName=new ArrayList<String>();
			List<String>list_factNo=new ArrayList<String>();
			//如果頁面上有選擇廠別
			if(list_factno!=null&&list_factno.size()>0){
				list_factNo=list_factNo_all.get(a);
				for(int i=0;i<list_factNo.size();i++){
					String factno=list_factNo.get(i);
					String factname=webFactSer.selByid(factno);
					list_factName.add(factname);
				}
			//如果頁面上沒有選擇廠別,則默認全部
			}else{
				List<WebFact>list_webfact=webFactSer.findFactByFactCode_showA(factCode);
				for(int b=0;b<list_webfact.size();b++){
					WebFact fact=list_webfact.get(b);
					String factno=fact.getId().getFactNo();
					String factSname=fact.getFactSname();
					list_factName.add(factSname);
					list_factNo.add(factno);
				}
			}			
			list_factName.add(0,"項次");
			list_factName.add(1,"管制指標");
			list_factName.add(2,"單位");
			list_factNo.add(0,"項次");
			list_factNo.add(1,"管制指標");
			list_factNo.add(2,"單位");
			//填充各個表的廠別狀態和表頭
			HSSFRow row_factCode=sheet.createRow(2+cursor*a);
			HSSFRow row_column=sheet.createRow(3+cursor*a);
			HSSFCell cell_factCode=row_factCode.createCell(0);
			cell_factCode.setCellValue(factCode);
			cell_factCode.getCellStyle().setFont(font_blue);
			for(int a_1=0;a_1<list_factName.size();a_1++){				
				String column=list_factName.get(a_1);
				HSSFCell cell=row_column.createCell(a_1);
				cell.setCellValue(column);
				cell.setCellStyle(cs_column);
				sheet.setColumnWidth(a_1, 3000);//列寬
			}
			
			//填充內容
			for(int b=0;b<list_factNo.size();b++){//start for2			
				String factNo=list_factNo.get(b);
				VKpifact kpi=vkpiSer.findById(factNo, factCode, yymm);
				List<Double>list_kpi=new ArrayList<Double>();
				if(kpi!=null){
					BigDecimal big=new BigDecimal(0.00);
					if(kpi.getFactaddRate()==null){
						kpi.setFactaddRate(big);
					}
					if(kpi.getOutrequestRate()==null){
						kpi.setOutrequestRate(big);
					}
					if(kpi.getAvgPermoney()==null){
						kpi.setAvgPermoney(big);
					}
					if(kpi.getPermoney()==null){
						kpi.setPermoney(big);
					}
					if(kpi.getWasteUsd()==null){
						kpi.setWasteUsd(big);
					}
					if(kpi.getWasteFact()==null){
						kpi.setWasteFact(big);
					}
					if(kpi.getWasteNo()==null){
						kpi.setWasteNo(big);
					}
					if(kpi.getAvgCirclehour()==null){
						kpi.setAvgCirclehour(big);
					}
					list_kpi=this.VkpiFactToDouble(kpi);
					/*list_kpi.add(kpi.getThisYield().doubleValue());
					list_kpi.add(kpi.getAvgCircle().doubleValue());
					list_kpi.add(kpi.getAvgCirclehour().doubleValue());
					list_kpi.add(kpi.getFactaddRate().doubleValue());//--null
					list_kpi.add(kpi.getProductRate().doubleValue());
					list_kpi.add(kpi.getStoreNum());list_kpi.add(kpi.getOutRequest());
					list_kpi.add(kpi.getOutrequestRate().doubleValue());//--null
					list_kpi.add(kpi.getAvgFactpro().doubleValue());
					list_kpi.add(kpi.getAvgZgpro().doubleValue());list_kpi.add(kpi.getAvgPerpro().doubleValue());
					list_kpi.add(kpi.getAvgPermoney().doubleValue());//--null
					list_kpi.add(kpi.getPermoney().doubleValue());//--null
					list_kpi.add(kpi.getWaterTon().doubleValue());list_kpi.add(kpi.getLightDu().doubleValue());
					list_kpi.add(kpi.getGasUsd().doubleValue());list_kpi.add(kpi.getWasteUsd().doubleValue());
					list_kpi.add(kpi.getMainRate().doubleValue());list_kpi.add(kpi.getSideRate().doubleValue());
					list_kpi.add(kpi.getWasteRate().doubleValue());list_kpi.add(kpi.getWasteFact().doubleValue());
					list_kpi.add(kpi.getWasteNo().doubleValue());list_kpi.add(kpi.getZjRate().doubleValue());
					list_kpi.add(kpi.getHurtNum());list_kpi.add(kpi.getZgleaveRate().doubleValue());
					list_kpi.add(kpi.getFactleaveRate().doubleValue());*/
				}				
				for(int c=0;c<list_contentName.size();c++){//start for3
					HSSFRow row=null;
					if(b==0){
						row=sheet.createRow(4+c+cursor*a);
					}else{
						row=sheet.getRow(4+c+cursor*a);
					}					
					switch(b){
					case 0:	                   
						String index="";
						if(c+1<10){
							index="0"+(c+1);
						}else{
							index=""+(c+1);
						}
						HSSFCell cell_0=row.createCell(b);
						cell_0.setCellValue(index);
						cell_0.setCellStyle(cs);						
	                   break;
					case 1:
						String columnName=list_contentName.get(c);
						HSSFCell cell_1=row.createCell(b);
						cell_1.setCellValue(columnName);
						cell_1.setCellStyle(cs);
						break;
					case 2:
						String unit=list_unit.get(c);
						HSSFCell cell_2=row.createCell(b);
						cell_2.setCellValue(unit);
						cell_2.setCellStyle(cs);
						break;
					default:																		
						HSSFCell cell_3=row.createCell(b);
						//數字格式的選擇
						HSSFCellStyle cs_temp=wb.createCellStyle();
						
						/*if(c==0||c==2){
							cs_temp=cs_poi1;
						}else if(c==1||c==5||c==6||c==9||c==10){
							cs_temp=cs_poi;
						}else if(c>12&&c<17){
							cs_temp=cs_poi4;
						}else if(c>7&&c<13||c==22||c==23){
							cs_temp=cs_poi2;
						}else{
							cs_temp=cs_percent;
						}*/
						if(kpi==null){
							cell_3.setCellValue("無數據");
						}else{
							Double kpi_content=list_kpi.get(c);
							cell_3.setCellValue(kpi_content);
						}
						cs_temp=this.findStyle(wb, c);
						cell_3.setCellStyle(cs_temp);
						break;
					}
					
				}//end for3				
				
			}//end for2
		}//end for1
		
		/**
		 * 填充標題
		 */
		HSSFRow row_title=sheet.createRow(0);
		HSSFCell cell_title=row_title.createCell(0);
		cell_title.setCellValue(yymm+"各廠KPI比較資料");
		cell_title.setCellStyle(cs_head);
		CellRangeAddress addTitle=new CellRangeAddress(0,(short)0,0,(short)20);
		sheet.addMergedRegion(addTitle);
		for(int z=1;z<20;z++){
			HSSFCell cell=row_title.createCell(z);
			cell.setCellStyle(cs_head);
		}
		ServletOutputStream os=response.getOutputStream();		
		response.setContentType("application/vnd.ms-excel");
		String title=yymm+"各廠KPI比較資料"+".xls";
		String fileName="";
		int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");//判斷瀏覽器的類型,大於0則為IE瀏覽器
		if(msie>0){
			fileName=java.net.URLEncoder.encode(title,"utf-8");//解決IE中文文件不能下載的問題
		}else{
			fileName=new String(title.getBytes("utf-8"),"iso8859-1");//解決非IE中文名亂碼問題
		}
		response.setHeader("Content-disposition","attachment;filename="+fileName);
		wb.write(os);
		os.close();
	}
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	
	/**
	 * 季度,上下半年,全年數據計算
	 * @param index
	 * @param sumEverydemo
	 * @param sumStandarddemo
	 * @param sumActualdemo
	 * @param sumActualpairs
	 * @param sumFactpairs
	 * @param personzg
	 * @param personjg
	 * @param timezg
	 * @param timejg
	 * @param addtimezg
	 * @param addtimejg
	 * @param leavenumzg
	 * @param leavenumjg
	 * @param hurtnum
	 * @param invcount
	 * @param sellcount
	 * @param costcount
	 * @param wagezgUsd
	 * @param wagejgUsd
	 * @param cashcount
	 * @param sideweit
	 * @param badweit
	 * @param otherbadweight
	 * @param waterton
	 * @param electricdu
	 * @param gasusd
	 * @param storenum
	 * @param outnum
	 * @param minusnum
	 * @param actlost
	 * @param avgbuttomweight2
	 * @return
	 */
	public List<Double> findResult_old(int index,Double sumEverydemo, Double sumStandarddemo,
			Double sumActualdemo, Double sumActualpairs, Double sumFactpairs,

			Double personzg, Double personjg, Double timezg, Double timejg,
			Double addtimezg, Double addtimejg, Double leavenumzg,
			Double leavenumjg, Double hurtnum,

			Double invcount, Double sellcount, Double costcount,
			Double wagezgUsd, Double wagejgUsd, Double cashcount,

			Double sideweit, Double badweit, Double otherbadweight,

			Double waterton, Double electricdu, Double gaston,

			Double storenum, Double outnum, Double minusnum,

			Double actlost, Double avgbuttomweight2,Double otherweight,
			Double productnum,Double noglueweight,Double repairmoney,Double sumWorkhours,
			Double instorenum,Double hole,Double sum_workday,Double waterusd,Double electricued,Double gasusd,Double thickUsed,
			Double backFeed,Double oilBack,Double drugsUsed ,Double colorUsed ,Double leaveMoney,
			Double payPairs,Double badcount
			) {
		if(index==0){
			index=1;
		}
		DecimalFormat format=new DecimalFormat("####0");
		DecimalFormat format1=new DecimalFormat("####0.0");
		DecimalFormat format2=new DecimalFormat("####0.00");
		DecimalFormat format4=new DecimalFormat("####0.0000");
		List<Double>list=new ArrayList<Double>();
		Double thisYield=Double.valueOf(format1.format(sumActualdemo/index));
		//Double avgCircle=this.division(sumActualdemo, sumEverydemo);
		Double avgCircle=Double.valueOf(format.format(this.division(sumActualdemo, sumEverydemo)));
		//Double avgCirclehour=this.division(sumActualdemo, sumWorkhours);
		Double avgCirclehour=Double.valueOf(format1.format(this.division(sumActualdemo, sumWorkhours)));
		//Double factaddRate=this.division(sumFactpairs, sumActualpairs);
		Double factaddRate=Double.valueOf(format4.format(this.division(sumFactpairs, sumActualpairs)));
		//Double productRate=this.division(sumActualdemo, sumStandarddemo);
		Double productRate=Double.valueOf(format4.format(this.division(sumActualdemo, sumStandarddemo)));
		//Double storeNum=storenum/index;
		Double storeNum=Double.valueOf(format.format(storenum/index));
		//Double outRequest=outnum/index;
		Double outRequest=Double.valueOf(format.format(outnum/index));
		//Double outrequestRate=this.division(minusnum, instorenum);
		Double outrequestRate=Double.valueOf(format4.format(this.division(minusnum, instorenum)));
		//Double avgFactpro=this.division(sumActualdemo, (timezg+timejg+addtimezg+addtimejg));
		Double avgFactpro=Double.valueOf(format2.format(this.division(sumActualdemo, (timezg+timejg+addtimezg+addtimejg))));
		//Double avgZgpro=this.division(sumActualdemo, personzg);
		Double avgZgpro=Double.valueOf(format.format(this.division(sumActualdemo, personzg)));
		//Double avgPerpro=this.division(sumActualdemo, (personzg+personjg));
		Double avgPerpro=Double.valueOf(format.format(this.division(sumActualdemo, (personzg+personjg))));
		//Double avgPermoney=this.division(productnum,(personzg+personjg));
		Double avgPermoney=Double.valueOf(format2.format(this.division(productnum,(personzg+personjg))));
		//Double permoney=this.division(productnum, (wagezgUsd+wagejgUsd));
		Double permoney=Double.valueOf(format2.format(this.division(productnum, (wagezgUsd+wagejgUsd))));
		//Double waterTon=this.division(waterton, sumActualdemo);
		Double waterTon=Double.valueOf(format4.format(this.division(waterton, sumActualdemo)));
		//Double lightDu=this.division(electricdu, sumActualdemo);
		Double lightDu=Double.valueOf(format4.format(this.division(electricdu, sumActualdemo)));
		//Double gasTon=this.division(gaston, sumActualdemo);
		Double gasTon=Double.valueOf(format4.format(this.division(gaston, sumActualdemo)));
		//Double wasteUsd=this.division(repairmoney, sumActualdemo);
		Double wasteUsd=Double.valueOf(format4.format(this.division(repairmoney, sumActualdemo)));
		//Double mainRate=this.division(costcount, sellcount);
		Double mainRate=Double.valueOf(format4.format(this.division(costcount, sellcount)));
		//Double sideRate=this.division(sideweit, (avgbuttomweight2+sideweit+badweit));
		Double sideRate=Double.valueOf(format4.format(this.division(sideweit, (avgbuttomweight2+sideweit+badweit))));
		//Double wasteRate=this.division(badweit, (avgbuttomweight2+sideweit+badweit));
		Double wasteRate=Double.valueOf(format4.format(this.division(badweit, (avgbuttomweight2+sideweit+badweit))));
		//Double wasteFact=this.division((actlost-avgbuttomweight2-noglueweight), (avgbuttomweight2+noglueweight));
		Double wasteFact=Double.valueOf(format4.format(this.division((actlost-avgbuttomweight2-noglueweight), (avgbuttomweight2+noglueweight))));
		//Double wasteNo=this.division((actlost-avgbuttomweight2-noglueweight-sideweit-badweit-otherbadweight), (avgbuttomweight2+noglueweight));
		Double wasteNo=Double.valueOf(format4.format(this.division((actlost-avgbuttomweight2-noglueweight-sideweit-badweit-otherbadweight), (avgbuttomweight2+noglueweight))));
		//Double zjRate=this.division(personzg, personjg);
		Double zjRate=Double.valueOf(format2.format(this.division(personzg, personjg)));
		//Double hurtNum=hurtnum/index;
		Double hurtNum=Double.valueOf(format2.format(hurtnum/index));
		//Double zgleaveRate=this.division(leavenumzg, personzg);
		Double zgleaveRate=Double.valueOf(format4.format(this.division(leavenumzg, personzg)));
		//Double factleaveRate=this.division((leavenumzg+leavenumjg), (personzg+personjg));
		Double factleaveRate=Double.valueOf(format4.format(this.division((leavenumzg+leavenumjg), (personzg+personjg))));
		
		
		
		list.add(thisYield);
		list.add(avgCircle);
		list.add(avgCirclehour);
		list.add(factaddRate);
		list.add(productRate);
		list.add(storeNum);
		list.add(outRequest);
		list.add(outrequestRate);
		list.add(avgFactpro);
		list.add(avgZgpro);
		list.add(avgPerpro);
		list.add(avgPermoney);
		list.add(permoney);
		list.add(waterTon);
		list.add(lightDu);
		list.add(gasTon);
		list.add(wasteUsd);
		list.add(mainRate);
		list.add(sideRate);
		list.add(wasteRate);
		list.add(wasteFact);
		list.add(wasteNo);
		list.add(zjRate);
		list.add(hurtNum);
		list.add(zgleaveRate);
		list.add(factleaveRate);		
		return list;
	}
	
	
	public List<Double> findResult(int index,VKpifactEve eve
			) {
		if(index==0){
			index=1;
		}
		DecimalFormat format=new DecimalFormat("####0");
		DecimalFormat format1=new DecimalFormat("####0.0");
		DecimalFormat format2=new DecimalFormat("####0.00");
		DecimalFormat format4=new DecimalFormat("####0.0000");
		List<Double>list=new ArrayList<Double>();
		//當月產量
		Double thisYield=Double.valueOf(format1.format(eve.getSumActualdemo().doubleValue()/index));//
		//月均回轉Double avgCircle=this.division(sumActualdemo, sumEverydemo);
		Double avgCircle=Double.valueOf(format.format(this.division(eve.getSumActualdemo().doubleValue(), eve.getSumEverydemo().doubleValue())));
		//時回轉Double avgCirclehour=this.division(sumActualdemo, sumWorkhours);
		Double avgCirclehour=Double.valueOf(format1.format(this.division(eve.getSumActualdemo().doubleValue(), eve.getWorkhours())));
		//機臺利用率Double muti_rate=sumEverydemo/sum_workday/hole;
		Double muti_rate=Double.valueOf(format4.format(this.division(eve.getSumEverydemo().doubleValue(),eve.getHole()*eve.getSumWorkday().doubleValue())));
		//產能達成率Double productRate=this.division(sumActualdemo, sumStandarddemo);
		Double productRate=Double.valueOf(format4.format(this.division( eve.getSumActualdemo().doubleValue(),eve.getSumStandarddemo().doubleValue())));
		//直工人均产能Double avgZgpro=this.division(sumActualdemo, personzg);
		Double avgZgpro=Double.valueOf(format.format(this.division( eve.getSumActualdemo().doubleValue(),eve.getPersonzg() )));
		//全厂人均产能Double avgPerpro=this.division(sumActualdemo, (personzg+personjg));
		Double avgPerpro=Double.valueOf(format.format(this.division( eve.getSumActualdemo().doubleValue(), (eve.getPersonzg()+eve.getPersonjg()))));
		//全廠人均時產能Double avgFactpro=this.division(sumActualdemo, (timezg+timejg+addtimezg+addtimejg));
		Double avgFactpro=Double.valueOf(format2.format(this.division( eve.getSumActualdemo().doubleValue(), ( eve.getTimezg()+eve.getTimejg() +eve.getAddtimezg()+eve.getAddtimejg()))));
		//成倉庫存Double storeNum=storenum/index;
		Double storeNum=Double.valueOf(format.format(eve.getStorenum()/index));
		//已出未請Double outRequest=outnum/index;
		Double outRequest=Double.valueOf(format.format( eve.getOutnum()/index));
		//生產與請款差異率Double outrequestRate=this.division(minusnum, instorenum);
		Double outrequestRate=Double.valueOf(format4.format(this.division( eve.getMinusnum(),eve.getInstorenum() )));
		//銷貨收入Double sl_income=sellcount/index;
		Double sl_income=Double.valueOf(format4.format(eve.getSellcount()/index));	
		//主材料成本比率Double mainRate=this.division(costcount, sellcount);
		Double mainRate=Double.valueOf(format4.format(this.division(eve.getCostcount(),eve.getSellcount())));
		//人工成本率Double pcost_rate=(wagezgUsd+wagejgUsd)/sellcount;
		Double pcost_rate=Double.valueOf(format4.format(this.division(eve.getWagezgUsd()+eve.getWagejgUsd(), eve.getSellcount())));
		//費用成本率Double ccost_rate=cashcount/sellcount;
		Double ccost_rate=Double.valueOf(format4.format(this.division(eve.getCashcount(), eve.getSellcount())));
		//修繕單耗Double wasteUsd=this.division(repairmoney, sumActualdemo);
		Double wasteUsd=Double.valueOf(format4.format(this.division(eve.getRepairmoney(),eve.getSumActualdemo().doubleValue())));
		//平均單價Double per_price=payPairs/invcount;
		Double per_price=Double.valueOf(format4.format(this.division(eve.getPaypairs(), eve.getInvcount())));
		//全廠人均薪資Double per_salar=(wagezgUsd+wagejgUsd)/(personzg+personjg);
		Double per_salar=Double.valueOf(format4.format(this.division(eve.getWagezgUsd()+eve.getWagejgUsd(), eve.getPersonzg()+eve.getPersonjg())));
		//人均產值Double avgPermoney=this.division(productnum,(personzg+personjg));
		Double avgPermoney=Double.valueOf(format2.format(this.division(eve.getProductednum(),(eve.getPersonzg()+eve.getPersonjg()))));
		//人薪產值Double permoney=this.division(productnum, (wagezgUsd+wagejgUsd));
		Double permoney=Double.valueOf(format2.format(this.division(eve.getProductednum(), (eve.getWagezgUsd()+eve.getWagejgUsd()))));
		//全廠總損耗Double wasteFact=this.division((actlost-avgbuttomweight2-noglueweight), (avgbuttomweight2+noglueweight));
		Double wasteFact=Double.valueOf(format4.format(this.division((eve.getActlost()-eve.getAvgbuttomweight2()-eve.getNoglueweight()), (eve.getAvgbuttomweight2()+eve.getNoglueweight()))));
		//無形損耗Double wasteNo=this.division((actlost-avgbuttomweight2-noglueweight-sideweit-badweit-otherbadweight), (avgbuttomweight2+noglueweight));
		Double wasteNo=Double.valueOf(format4.format(this.division((eve.getActlost()-eve.getAvgbuttomweight2()-eve.getNoglueweight()-eve.getSideweit()-eve.getBadweit()-eve.getOtherweight().doubleValue()), (eve.getAvgbuttomweight2()+eve.getNoglueweight()))));
		//邊料率Double sideRate=this.division(sideweit, (avgbuttomweight2+sideweit+badweit));
		Double sideRate=Double.valueOf(format4.format(this.division(eve.getSideweit(), ( eve.getAvgbuttomweight2()+eve.getSideweit()+eve.getBadweit()))));
		//不良率Double uheal_rate=badcount/(sumActualpairs+badcount);
		Double uheal_rate=Double.valueOf(format4.format(this.division(eve.getBadcount(), eve.getSumActualpairs().doubleValue()+eve.getBadcount())));
		//報廢率Double wasteRate=this.division(badweit, (avgbuttomweight2+sideweit+badweit));
		Double wasteRate=Double.valueOf(format4.format(this.division(eve.getBadweit(), (eve.getAvgbuttomweight2()+eve.getSideweit()+eve.getBadweit()))));		
		//廠補率Double factaddRate=this.division(sumFactpairs, sumActualpairs);
		Double factaddRate=Double.valueOf(format4.format(this.division(eve.getSumFacpairs().doubleValue(),eve.getSumActualpairs().doubleValue())));		
		//水用量单耗Double waterTon=this.division(waterton, sumActualdemo);
		Double waterTon=Double.valueOf(format4.format(this.division(eve.getWaterton(),eve.getSumActualdemo().doubleValue())));
		//用水金額單耗Double water_usd=waterusd/sumActualdemo;
		Double water_usd=Double.valueOf(format4.format(this.division(eve.getWaterusd(), eve.getSumActualdemo().doubleValue())));
		//电度数单耗Double lightDu=this.division(electricdu, sumActualdemo);
		Double lightDu=Double.valueOf(format4.format(this.division(eve.getElectricdu(),eve.getSumActualdemo().doubleValue())));
		//用電金額單耗Double light_usd=electricued/sumActualdemo;
		Double light_usd=Double.valueOf(format4.format(this.division(eve.getElectricusd(), eve.getSumActualdemo().doubleValue())));
		//蒸汽用量單耗Double gasTon=this.division(gaston, sumActualdemo);
		Double gasTon=Double.valueOf(format4.format(this.division( eve.getGaston(),eve.getSumActualdemo().doubleValue())));
		//用汽金額單耗Double gas_usd=gasusd/sumActualdemo;
		Double gas_usd=Double.valueOf(format4.format(this.division(eve.getGasusd(), eve.getSumActualdemo().doubleValue())));				
		//回頭料%Double bhead_rate=backFeed/thickUsed;
		Double bhead_rate=Double.valueOf(format4.format(this.division(eve.getBackfeed(), eve.getThickused())));
		//油壓退料%Double bpre_rate=oilBack/thickUsed;
		Double bpre_rate=Double.valueOf(format4.format(this.division(eve.getOilback(), eve.getThickused())));
		//回流率%Double bflow_rate=(backFeed+oilBack)/thickUsed;
		Double bflow_rate=Double.valueOf(format4.format(this.division(eve.getBackfeed()+eve.getOilback(), eve.getThickused())));
		//藥品用量單耗Double drug_wast=drugsUsed/sumActualdemo;
		Double drug_wast=Double.valueOf(format4.format(this.division(eve.getDrugsused(), eve.getSumActualdemo().doubleValue())));
		//色料用量單耗Double clr_wast=colorUsed/sumActualdemo;
		Double clr_wast=Double.valueOf(format4.format(this.division(eve.getColorused(), eve.getSumActualdemo().doubleValue())));
		//離型劑金額單耗Double leave_usd=leaveMoney/sumActualdemo;
		Double leave_usd=Double.valueOf(format4.format(this.division(eve.getLeavemoney(), eve.getSumActualdemo().doubleValue())));
		//直間比Double zjRate=this.division(personzg, personjg);
		Double zjRate=Double.valueOf(format2.format(this.division( eve.getPersonzg(),eve.getPersonjg())));	
		//直工離職率Double zgleaveRate=this.division(leavenumzg, personzg);
		Double zgleaveRate=Double.valueOf(format4.format(this.division(eve.getLeavenumzg(),eve.getPersonzg())));
		//全廠離職率Double factleaveRate=this.division((leavenumzg+leavenumjg), (personzg+personjg));
		Double factleaveRate=Double.valueOf(format4.format(this.division((eve.getLeavenumzg()+eve.getLeavenumjg()), (eve.getPersonzg()+eve.getPersonjg()))));
		//工傷件數Double hurtNum=hurtnum/index;
		Double hurtNum=Double.valueOf(format2.format(eve.getHurtnum()/index));
		
		
		
		list.add(thisYield);//當月產量
		list.add(avgCircle);//月均回轉
		list.add(avgCirclehour);//時迴轉
		list.add(muti_rate);//機臺利用率
		list.add(productRate);//產能達成率
		list.add(avgZgpro);//直工人均产能
		list.add(avgPerpro);//全厂人均产能
		list.add(avgFactpro);//全廠人均時產能
		list.add(storeNum);//成倉庫存
		list.add(outRequest);//已出未請
		list.add(outrequestRate);//生產與請款差異率
		list.add(sl_income);//銷貨收入
		list.add(mainRate);//主材料成本比率
		list.add(pcost_rate);//人工成本率
		list.add(ccost_rate);//費用成本率
		list.add(wasteUsd);//修繕單耗
		list.add(per_price);//平均單價
		list.add(per_salar);//全廠人均薪資
		list.add(avgPermoney);//人均產值
		list.add(permoney);//人薪產值
		list.add(wasteFact);//全廠總損耗
		list.add(wasteNo);//無形損耗
		list.add(sideRate);//邊料率
		list.add(uheal_rate);//不良率
		list.add(wasteRate);//報廢率					
		list.add(factaddRate);//廠補率		
		list.add(waterTon);//水用量单耗
		list.add(water_usd);//用水金額單耗
		list.add(lightDu);//电度数单耗
		list.add(light_usd);//用電金額單耗
		list.add(gasTon);//蒸汽用量單耗
		list.add(gas_usd);//用汽金額單耗
		list.add(bhead_rate);//回頭料%
		list.add(bpre_rate);//油壓退料%
		list.add(bflow_rate);//回流率%
		list.add(drug_wast);//藥品用量單耗
		list.add(clr_wast);//色料用量單耗
		list.add(leave_usd);//離型劑金額單耗						
		list.add(zjRate);//直間比		
		list.add(zgleaveRate);//直工離職率
		list.add(factleaveRate);//全廠離職率
		list.add(hurtNum);//工傷件數			
		return list;
	}
	
	
	/**
	 * 避免除數為0的方法
	 * @param d1
	 * @param d2
	 * @return
	 */
	public Double division(Double d1,Double d2){
		Double db=0.00;
		if(d2!=0.00){
			db=d1/d2;
		}
		return db;
	}
	/**
	 * 獲取一個時間段的所有日期
	 * @param yymm_begin
	 * @param yymm_end
	 * @return
	 * @throws ParseException
	 */
	public List<String> getMonths(String yymm_begin,String yymm_end) throws ParseException{
		List<String>list=new ArrayList<String>();
		SimpleDateFormat format=new SimpleDateFormat("yyyyMM");
		Date date1=format.parse(yymm_begin);
		Date date2=format.parse(yymm_end);
		Calendar calendar1=Calendar.getInstance();
		Calendar calendar2=Calendar.getInstance();
		calendar1.setTime(date1);
		calendar2.setTime(date2);		
		while(calendar1.getTime().getTime()<calendar2.getTime().getTime()||calendar1.getTime().getTime()==calendar2.getTime().getTime()){	
			String result=format.format(calendar1.getTime());
			list.add(result);
			calendar1.add(Calendar.MONTH, 1);					
		}
		System.out.print(list);
		return list;					
	}
	
	/**
	 * 各項的項目與單位
	 * @Title: findListstrAndUnit
	 * @Description: TODO
	 * @param @return
	 * @return List<List<String>>
	 * @throws
	 * @author web
	 * @date 2016/3/18
	 */
	public List<List<String>>findListstrAndUnit(){
		List<List<String>>list_all=new ArrayList<List<String>>();
		List<String>list_str=new ArrayList<String>();
		List<String>list_unit=new ArrayList<String>();
		
		list_str.add("當月產量");
		list_unit.add("模");// 0 (0位小數) (0<目標)
		list_str.add("月均回轉 ");
		list_unit.add("回");// 1(1位小數) (1<目標)
		list_str.add("時迴轉");
		list_unit.add("模/H");// 2(1位小數) (2<目標)
		list_str.add("機臺利用率");
		list_unit.add("%");// (2位小數) (3>目標)
		list_str.add("產能達成率");
		list_unit.add("%");// (2位小數) (4<=目標)
		list_str.add("直工人均产能");
		list_unit.add("模/人");// (0位小數) (5<目標)
		list_str.add("全厂人均产能");
		list_unit.add("模/人");// (0位小數) (6<目標)
		list_str.add("全廠人均時產能");
		list_unit.add("模/H");// (0位小數) (7<目標)
		list_str.add("成倉庫存");
		list_unit.add("雙");// 5(整數)(0位小數) (8>目標)
		list_str.add("已出未請");
		list_unit.add("雙");// 6(整數)(0位小數) (9>目標)
		list_str.add("生產與請款差異率");
		list_unit.add("%"); // (2位小數) (10>目標)
		list_str.add("銷貨收入");
		list_unit.add("USD");// (0位小數) (11>目標)
		list_str.add("主材料成本比率");
		list_unit.add("%");// (2位小數) (12>目標)
		list_str.add("人工成本率");
		list_unit.add("%");// (2位) (13<目標)
		list_str.add("費用成本率");
		list_unit.add("%");// (2位) (14<目標)
		list_str.add("修繕單耗");
		list_unit.add("USD/模");// (2位小數) (15>目標)
		list_str.add("平均單價");
		list_unit.add("USD/雙");// (2位小數) (16>目標)
		list_str.add("全廠人均薪資");
		list_unit.add("USD/人");// (0位小數) (17<目標)
		list_str.add("人均產值");
		list_unit.add("USD/人");// (2位小數) (18<目標)
		list_str.add("人薪產值");
		list_unit.add("--");// (2位小數) (19<目標)
		list_str.add("全廠總損耗");
		list_unit.add("%");// (2位小數) (20>目標)
		list_str.add("無形損耗");
		list_unit.add("%"); // (2位小數) (21>目標)
		list_str.add("邊料率");
		list_unit.add("%");// (2位小數) (22>目標)
		list_str.add("不良率");
		list_unit.add("%");// (2位小數) (23<目標)
		list_str.add("報廢率");
		list_unit.add("%");// (2位小數) (24>目標)
		list_str.add("廠補率");
		list_unit.add("%");// (2位小數) (25>目標)
		list_str.add("水用量单耗");
		list_unit.add("噸/模");// (4位小數) (26>目標)
		list_str.add("用水金額單耗");
		list_unit.add("USD/模");// (4位小數) (27<目標)
		list_str.add("电度数单耗");
		list_unit.add("度/模");// (4位小數) (28>目標)
		list_str.add("用電金額單耗");
		list_unit.add("USD/模");// (4位小數) (29<目標)
		list_str.add("蒸汽用量單耗");
		list_unit.add("噸/模");// (4位小數) (30>目標)
		list_str.add("用汽金額單耗");
		list_unit.add("USD/模");// (4位小數) (31<目標)
		list_str.add("回頭料%");
		list_unit.add("%");// (2位小數) (32<目標)
		list_str.add("油壓退料%");
		list_unit.add("%");// (2位小數) (33<目標)
		list_str.add("回流率%");
		list_unit.add("%");// (2位小數) (34<目標)
		list_str.add("藥品用量單耗");
		list_unit.add("g/模");// (4位) (35<目標)
		list_str.add("色料用量單耗");
		list_unit.add("g/模");// （4位） (36<目標)
		list_str.add("離型劑金額單耗");
		list_unit.add("USD/模");// （4位） (37<目標)
		list_str.add("直間比");
		list_unit.add("--");// (4位小數) (38<目標)
		list_str.add("直工離職率");
		list_unit.add("%");// (2位小數) (39>目標)
		list_str.add("全廠離職率");
		list_unit.add("%");// (2位小數) (40>目標)
		list_str.add("工傷件數");
		list_unit.add("件");// (0位小數) (41>目標)
		
		list_all.add(list_str);
		list_all.add(list_unit);
		return list_all;
		
	}
	
	public Map<String,Object> findStyles(HSSFWorkbook wb){
		/**
		 * 報表相關樣式
		 */
		Map<String,Object>map=new HashMap<String,Object>();
		//標題樣式
		HSSFCellStyle cs_head=wb.createCellStyle();
		HSSFFont font_head=wb.createFont();
		font_head.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_head.setFontHeightInPoints((short)20);
		cs_head.setFont(font_head);
		cs_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_head.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		map.put("cs_head", cs_head);
		
		//標準單元格樣式
		HSSFCellStyle cs=wb.createCellStyle();
		cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		map.put("cs", cs);
		//表頭樣式
		HSSFCellStyle cs_column=wb.createCellStyle();
		HSSFFont font_column=wb.createFont();
		font_column.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_column.setFontHeightInPoints((short)12);
		cs_column.setFont(font_column);
		cs_column.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_column.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_column.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_column.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_column.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_column.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_column.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		cs_column.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		map.put("cs_column", cs_column);
		
		//紅色加粗字體
		HSSFFont font_red=wb.createFont();
		font_red.setColor(IndexedColors.RED.getIndex());
		font_red.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		/**
		 * 數字格式（有背景顏色與無背景顏色）
		 */
		
		HSSFDataFormat format=wb.createDataFormat();
		//無背景
		HSSFCellStyle cs_percent=wb.createCellStyle();
		cs_percent.setDataFormat(format.getFormat("0.00%"));
		cs_percent.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_percent.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_percent.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		map.put("cs_percent", cs_percent);
		
		HSSFCellStyle cs_poi=wb.createCellStyle();
		cs_poi.setDataFormat(format.getFormat("#,###,0"));
		cs_poi.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		map.put("cs_poi", cs_poi);
		
		HSSFCellStyle cs_poi1=wb.createCellStyle();
		cs_poi1.setDataFormat(format.getFormat("#,###,0.0"));
		cs_poi1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		map.put("cs_poi1", cs_poi1);
		
		HSSFCellStyle cs_poi2=wb.createCellStyle();
		cs_poi2.setDataFormat(format.getFormat("#,###,0.00"));
		cs_poi2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		map.put("cs_poi2", cs_poi2);
		
		HSSFCellStyle cs_poi4=wb.createCellStyle();
		cs_poi4.setDataFormat(format.getFormat("#,###,0.0000"));
		cs_poi4.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi4.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		map.put("cs_poi4", cs_poi4);
		//有背景
		HSSFCellStyle cs_percent_bg=wb.createCellStyle();
		cs_percent_bg.setDataFormat(format.getFormat("0.00%"));
		cs_percent_bg.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_percent_bg.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_percent_bg.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_percent_bg.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_percent_bg.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_percent_bg.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		//cs_percent_bg.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		//cs_percent_bg.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cs_percent_bg.setFont(font_red);
		map.put("cs_percent_bg", cs_percent_bg);
		
		
		HSSFCellStyle cs_poi_bg=wb.createCellStyle();
		cs_poi_bg.setDataFormat(format.getFormat("#,###,0"));
		cs_poi_bg.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi_bg.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi_bg.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi_bg.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi_bg.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi_bg.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		//cs_poi_bg.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		//cs_poi_bg.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cs_poi_bg.setFont(font_red);
		map.put("cs_poi_bg", cs_poi_bg);
		
		HSSFCellStyle cs_poi1_bg=wb.createCellStyle();
		cs_poi1_bg.setDataFormat(format.getFormat("#,###,0.0"));
		cs_poi1_bg.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi1_bg.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi1_bg.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi1_bg.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi1_bg.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi1_bg.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		//cs_poi1_bg.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		//cs_poi1_bg.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cs_poi1_bg.setFont(font_red);
		map.put("cs_poi1_bg", cs_poi1_bg);
		
		HSSFCellStyle cs_poi2_bg=wb.createCellStyle();
		cs_poi2_bg.setDataFormat(format.getFormat("#,###,0.00"));
		cs_poi2_bg.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi2_bg.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi2_bg.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi2_bg.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi2_bg.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi2_bg.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		//cs_poi2_bg.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		//cs_poi2_bg.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cs_poi2_bg.setFont(font_red);
		map.put("cs_poi2_bg", cs_poi2_bg);
		
		HSSFCellStyle cs_poi4_bg=wb.createCellStyle();
		cs_poi4_bg.setDataFormat(format.getFormat("#,###,0.0000"));
		cs_poi4_bg.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi4_bg.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi4_bg.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi4_bg.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi4_bg.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi4_bg.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		//cs_poi4_bg.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		//cs_poi4_bg.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cs_poi4_bg.setFont(font_red);
		map.put("cs_poi4_bg", cs_poi4_bg);
		return map;
	}
	
	/**
	 * 數字樣式，以及是否達標時的樣式
	 * @Title: findStyle
	 * @Description: TODO
	 * @param @return
	 * @return Map<String,Object>
	 * @throws
	 * @author web
	 * @date 2016/3/18
	 */
	public HSSFCellStyle findStyle(HSSFWorkbook wb,Double num1,Double num2,int j){
		HSSFCellStyle cs_temp2=findStyle(wb,j);		
		boolean flag=true;
		if((j>=0&&j<=7)||j==11||j==16||j==18||j==19||j==38){
			flag=num1<num2;
		}else{
			flag=num1>num2;
		}
		if(flag){//start if
			cs_temp2=findStyle_red(wb, j);			
		}//end if
		return cs_temp2;
	}
	
	/**
	 * 數字樣式選擇(紅色字體)
	 * @Title: findStyle
	 * @Description: TODO
	 * @param @return
	 * @return Map<String,Object>
	 * @throws
	 * @author web
	 * @date 2016/3/18
	 */
	public HSSFCellStyle findStyle_red(HSSFWorkbook wb,int j){
		Map<String,Object>map=findStyles(wb);
		HSSFCellStyle cs_temp2=wb.createCellStyle();		
			//數字格式的選擇
			if(j==0||(j>=5&&j<=9)||j==11||j==17||j==41){								
				cs_temp2= (HSSFCellStyle)map.get("cs_poi_bg");
			}else if(j==1||j==2){
				cs_temp2=(HSSFCellStyle)map.get("cs_poi1_bg");
			}else if((j>=26&&j<=31)||(j>=35&&j<=38)){
				cs_temp2=(HSSFCellStyle)map.get("cs_poi4_bg");
			}else if(j==18||j==19){
				cs_temp2=(HSSFCellStyle)map.get("cs_poi2_bg");				
			}else{
				cs_temp2=(HSSFCellStyle)map.get("cs_percent_bg");
			}			
		return cs_temp2;
	}
	/**
	 * 數字樣式選擇(黑色字體)
	 * @Title: findStyle
	 * @Description: TODO
	 * @param @return
	 * @return Map<String,Object>
	 * @throws
	 * @author web
	 * @date 2016/3/18
	 */
	public HSSFCellStyle findStyle(HSSFWorkbook wb,int j){
		Map<String,Object>map=findStyles(wb);
		HSSFCellStyle cs_temp2=wb.createCellStyle();		
			//數字格式的選擇
			if(j==0||(j>=5&&j<=9)||j==11||j==17||j==41){								
				cs_temp2= (HSSFCellStyle)map.get("cs_poi");
			}else if(j==1||j==2){
				cs_temp2=(HSSFCellStyle)map.get("cs_poi1");
			}else if((j>=26&&j<=31)||(j>=35&&j<=38)){
				cs_temp2=(HSSFCellStyle)map.get("cs_poi4");
			}else if(j==18||j==19){
				cs_temp2=(HSSFCellStyle)map.get("cs_poi2");				
			}else{
				cs_temp2=(HSSFCellStyle)map.get("cs_percent");
			}			
		return cs_temp2;
	}
	
	/**
	 * VKpiFact對象各項封裝List<Double>集合
	 * @return
	 */
	public List<Double> VkpiFactToDouble(VKpifact kpi){
		List<Double>list_content=new ArrayList<Double>();
		list_content.add(kpi.getThisYield().doubleValue());//1當月產量
		list_content.add(kpi.getAvgCircle().doubleValue());//2月均回轉
		list_content.add(kpi.getAvgCirclehour().doubleValue());//時迴轉
		list_content.add(kpi.getMainRate().doubleValue());//機臺利用率
		list_content.add(kpi.getProductRate().doubleValue());//產能達成率
		list_content.add(kpi.getAvgZgpro().doubleValue());//直工人均产能
		list_content.add(kpi.getAvgPerpro().doubleValue());//全厂人均产能
		list_content.add(kpi.getAvgFactpro().doubleValue());//全廠人均時產能
		list_content.add(kpi.getStoreNum().doubleValue());//6  成倉庫存
		list_content.add(kpi.getOutRequest().doubleValue());//7 已出未請
		list_content.add(kpi.getOutrequestRate().doubleValue());//--null 生產與請款差異率
		list_content.add(kpi.getSlIncome());//銷貨收入
		list_content.add(kpi.getMainRate().doubleValue());//主材料成本比率
		list_content.add(kpi.getPcostRate().doubleValue());//人工成本率
		list_content.add(kpi.getCcostRate().doubleValue());//費用成本率
		list_content.add(kpi.getWasteUsd().doubleValue());//修繕單耗
		list_content.add(kpi.getPerPrice().doubleValue());//平均單價
		list_content.add(kpi.getPerSalar().doubleValue());//全廠人均薪資
		list_content.add(kpi.getAvgPermoney().doubleValue());//--null 人均產值
		list_content.add(kpi.getPermoney().doubleValue());//--null  人薪產值
		list_content.add(kpi.getWasteFact().doubleValue());//全廠總損耗
		list_content.add(kpi.getWasteNo().doubleValue());//無形損耗
		list_content.add(kpi.getSideRate().doubleValue());//邊料率
		list_content.add(kpi.getUhealRate().doubleValue());//不良率
		list_content.add(kpi.getWasteRate().doubleValue());//報廢率									
		list_content.add(kpi.getFactaddRate().doubleValue());//--null 廠補率
		list_content.add(kpi.getWaterTon().doubleValue());//水用量单耗
		list_content.add(kpi.getWaterUsd().doubleValue());//用水金額單耗
		list_content.add(kpi.getLightDu().doubleValue());//电度数单耗
		list_content.add(kpi.getLightUsd().doubleValue());//用電金額單耗
		list_content.add(kpi.getGasTon().doubleValue());//蒸汽用量單耗
		list_content.add(kpi.getGasUsd().doubleValue());//用汽金額單耗
		list_content.add(kpi.getBheadRate().doubleValue());//回頭料%
		list_content.add(kpi.getBpreRate().doubleValue());//油壓退料%
		list_content.add(kpi.getBflowRate().doubleValue());//回流率%
		list_content.add(kpi.getDrugWast().doubleValue());//藥品用量單耗
		list_content.add(kpi.getClrWast().doubleValue());//色料用量單耗
		list_content.add(kpi.getLeaveUsd().doubleValue());//離型劑金額單耗
		list_content.add(kpi.getZjRate().doubleValue());//直間比		
		list_content.add(kpi.getZgleaveRate().doubleValue());//直工離職率
		list_content.add(kpi.getFactleaveRate().doubleValue());//全廠離職率
		list_content.add(kpi.getHurtNum().doubleValue());//24  //工傷件數
		return list_content;
	}
	
	/**
	 * KpiFact目標各項封裝到List<Double>
	 * @param kpi_pur
	 * @return
	 */
	public List<Double> kpiFactToDouble(Kpifact kpi_pur){
		List<Double>list_content_pur=new ArrayList<Double>();
		list_content_pur.add(kpi_pur.getThisYield()==null?0:kpi_pur.getThisYield());//當月產量
		list_content_pur.add(kpi_pur.getAvgCircle()==null?0:kpi_pur.getAvgCircle());//月均回轉
		list_content_pur.add(kpi_pur.getAvgCirclehour()==null?0:kpi_pur.getAvgCirclehour());//時回轉
		list_content_pur.add(kpi_pur.getMutiRate()==null?0:kpi_pur.getMutiRate());//機臺利用率
		list_content_pur.add(kpi_pur.getProductRate()==null?0:kpi_pur.getProductRate());//產能達成率
		list_content_pur.add(kpi_pur.getAvgZgpro()==null?0:kpi_pur.getAvgZgpro());//直工人均产能
		list_content_pur.add(kpi_pur.getAvgPerpro()==null?0:kpi_pur.getAvgPerpro());//全厂人均产能
		list_content_pur.add(kpi_pur.getAvgFactpro()==null?0:kpi_pur.getAvgFactpro());//全廠人均時產能
		list_content_pur.add(kpi_pur.getStoreNum()==null?0:kpi_pur.getStoreNum());//成倉庫存
		list_content_pur.add(kpi_pur.getOutRequest()==null?0:kpi_pur.getOutRequest());//已出未請
		list_content_pur.add(kpi_pur.getOutrequestRate()==null?0:kpi_pur.getOutrequestRate());//生產與請款差異率
		list_content_pur.add(kpi_pur.getSlIncome()==null?0:kpi_pur.getSlIncome());//銷貨收入
		list_content_pur.add(kpi_pur.getMainRate()==null?0:kpi_pur.getMainRate());//主材料成本比率
		list_content_pur.add(kpi_pur.getPcostRate()==null?0:kpi_pur.getPcostRate());//人工成本率
		list_content_pur.add(kpi_pur.getCcostRate()==null?0:kpi_pur.getCcostRate());//費用成本率
		list_content_pur.add(kpi_pur.getWasteUsd()==null?0:kpi_pur.getWasteUsd());//修繕單耗
		list_content_pur.add(kpi_pur.getPerPrice()==null?0:kpi_pur.getPerPrice());//平均單價
		list_content_pur.add(kpi_pur.getPerSalar()==null?0:kpi_pur.getPerSalar());//全廠人均薪資
		list_content_pur.add(kpi_pur.getAvgPermoney()==null?0:kpi_pur.getAvgPermoney());//人均產值
		list_content_pur.add(kpi_pur.getPermoney()==null?0:kpi_pur.getPermoney());//人薪產值
		list_content_pur.add(kpi_pur.getWasteFact()==null?0:kpi_pur.getWasteFact());//全廠總損耗
		list_content_pur.add(kpi_pur.getWasteNo()==null?0:kpi_pur.getWasteNo());//無形損耗
		list_content_pur.add(kpi_pur.getSideRate()==null?0:kpi_pur.getSideRate());//邊料率
		list_content_pur.add(kpi_pur.getUhealRate()==null?0:kpi_pur.getUhealRate());//不良率
		list_content_pur.add(kpi_pur.getWasteRate()==null?0:kpi_pur.getWasteRate());//報廢率
		list_content_pur.add(kpi_pur.getFactaddRate()==null?0:kpi_pur.getFactaddRate());//廠補率
		list_content_pur.add(kpi_pur.getWaterTon()==null?0:kpi_pur.getWaterTon());//水用量单耗
		list_content_pur.add(kpi_pur.getWasteUsd()==null?0:kpi_pur.getWasteUsd());//用水金額單耗
		list_content_pur.add(kpi_pur.getLightDu()==null?0:kpi_pur.getLightDu());//电度数单耗
		list_content_pur.add(kpi_pur.getLightUsd()==null?0:kpi_pur.getLightUsd());//用電金額單耗
		list_content_pur.add(kpi_pur.getGasTon()==null?0:kpi_pur.getGasTon());//蒸汽用量單耗
		list_content_pur.add(kpi_pur.getGasUsd()==null?0:kpi_pur.getGasUsd());//用汽金額單耗
		list_content_pur.add(kpi_pur.getBheadRate()==null?0:kpi_pur.getBheadRate());//回頭料%
		list_content_pur.add(kpi_pur.getBpreRate()==null?0:kpi_pur.getBpreRate());//油壓退料%
		list_content_pur.add(kpi_pur.getBflowRate()==null?0:kpi_pur.getBflowRate());//回流率%
		list_content_pur.add(kpi_pur.getDrugWast()==null?0:kpi_pur.getDrugWast());//藥品用量單耗
		list_content_pur.add(kpi_pur.getClrWast()==null?0:kpi_pur.getClrWast());//色料用量單耗
		list_content_pur.add(kpi_pur.getLeaveUsd()==null?0:kpi_pur.getLeaveUsd());//離型劑金額單耗
		list_content_pur.add(kpi_pur.getZjRate()==null?0:kpi_pur.getZjRate());//直間比		
		list_content_pur.add(kpi_pur.getZgleaveRate()==null?0:kpi_pur.getZgleaveRate());//直工離職率
		list_content_pur.add(kpi_pur.getFactleaveRate()==null?0:kpi_pur.getFactleaveRate());//全廠離職率
		list_content_pur.add(kpi_pur.getHurtNum()==null?0:kpi_pur.getHurtNum());//工傷件數	
		return list_content_pur;
	}
	
}
