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
		
		//標題樣式
		HSSFCellStyle cs_head=wb.createCellStyle();
		HSSFFont font_head=wb.createFont();
		font_head.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_head.setFontHeightInPoints((short)20);
		cs_head.setFont(font_head);
		cs_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_head.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		//標準單元格樣式
		HSSFCellStyle cs=wb.createCellStyle();
		cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
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
		
		HSSFCellStyle cs_poi=wb.createCellStyle();
		cs_poi.setDataFormat(format.getFormat("#,###,0"));
		cs_poi.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle cs_poi1=wb.createCellStyle();
		cs_poi1.setDataFormat(format.getFormat("#,###,0.0"));
		cs_poi1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle cs_poi2=wb.createCellStyle();
		cs_poi2.setDataFormat(format.getFormat("#,###,0.00"));
		cs_poi2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle cs_poi4=wb.createCellStyle();
		cs_poi4.setDataFormat(format.getFormat("#,###,0.0000"));
		cs_poi4.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi4.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderLeft(HSSFCellStyle.BORDER_THIN);
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
								
		/**
		 * 獲取要循環的數據
		 */
		List<WebFact>list_factcode=webFactSer.findFactById_showA(factNo);				
		List<String>list_str=new ArrayList<String>();		
		List<String>list_unit=new ArrayList<String>();
		list_str.add("當月產量");list_unit.add("模");//0 (一位小數)     (0<目標)  
		list_str.add("月均回轉 ");list_unit.add("回");//1(整數)          (1<目標)
		list_str.add("時迴轉");list_unit.add("模/H");//2(一位小數)       (2<目標)
		list_str.add("機臺利用率");list_unit.add("%");
		list_str.add("產能達成率");list_unit.add("%");//                 (4<目標)
		list_str.add("直工人均产能");list_unit.add("模/人");//9(整數)      (9<目標)
		list_str.add("全厂人均产能");list_unit.add("模/人");//10(整數)      (10<目標)
		list_str.add("全廠人均時產能");list_unit.add("模/H");//           (8<目標)
		list_str.add("成倉庫存");list_unit.add("雙");//5(整數)
		list_str.add("已出未請");list_unit.add("雙");//6(整數) 
		list_str.add("生產與請款差異率");list_unit.add("%"); 
		list_str.add("銷貨收入");list_unit.add("USD");
		list_str.add("主材料成本比率");list_unit.add("%");
		list_str.add("人工成本率");list_unit.add("%");
		list_str.add("費用成本率");list_unit.add("%");
		list_str.add("修繕單耗");list_unit.add("USD/模");//16
		list_str.add("平均單價");list_unit.add("USD/雙");
		list_str.add("全廠人均薪資");list_unit.add("USD/人");
		list_str.add("人均產值");list_unit.add("USD/人");//                (11<目標)
		list_str.add("人薪產值");list_unit.add("--");//                    (12<目標)
		list_str.add("全廠總損耗");list_unit.add("%");
		list_str.add("無形損耗");list_unit.add("%"); 
		list_str.add("邊料率");list_unit.add("%");
		list_str.add("不良率");list_unit.add("%");
		list_str.add("報廢率");list_unit.add("%");
		list_str.add("廠補率");  list_unit.add("%");// 
		list_str.add("水用量单耗");list_unit.add("噸/模");//13(12至15取四位小數)  
		list_str.add("用水金額單耗");list_unit.add("USD/模");
		list_str.add("电度数单耗");list_unit.add("度/模");
		list_str.add("用電金額單耗");list_unit.add("USD/模");
		list_str.add("蒸汽用量單耗");list_unit.add("噸/模");
		list_str.add("用汽金額單耗");list_unit.add("USD/模");
		list_str.add("回頭料%");list_unit.add("%");
		list_str.add("油壓退料%");list_unit.add("%");
		list_str.add("回流率%");list_unit.add("%");
		list_str.add("藥品用量單耗");list_unit.add("g/模");
		list_str.add("色料用量單耗");list_unit.add("g/模");
		list_str.add("離型劑金額單耗");list_unit.add("USD/模");
		list_str.add("直間比");list_unit.add("--");//                        (22<目標)
		list_str.add("直工離職率");list_unit.add("%");
		list_str.add("全廠離職率");list_unit.add("%");
		list_str.add("工傷件數");list_unit.add("件");	          	
		          
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
			//第一季度
			Double q1_sumEverydemo=0.00;Double q1_sumStandarddemo=0.00;
			Double q1_sumActualdemo=0.00;Double q1_sumActualpairs=0.00;
			Double q1_sumFactpairs=0.00;Double q1_sumWorkhours=0.00;
						
			Double q1_personzg=0.00;Double q1_personjg=0.00;
			Double q1_timezg=0.00;Double q1_timejg=0.00;
			Double q1_addtimezg=0.00;Double q1_addtimejg=0.00;
			Double q1_leavenumzg=0.00;Double q1_leavenumjg=0.00;
			Double q1_hurtnum=0.00;
			
			Double q1_invcount=0.00;Double q1_sellcount=0.00;
			Double q1_costcount=0.00;Double q1_wagezgUsd=0.00;
			Double q1_wagejgUsd=0.00;Double q1_cashcount=0.00;
			
			Double q1_sideweit=0.00;Double q1_badweit=0.00;
			Double q1_otherbadweight=0.00;
			Double q1_otherweight=0.00;
			
			Double q1_waterton=0.00;Double q1_electricdu=0.00;
			Double q1_gaston=0.00;
			
			Double q1_storenum=0.00;Double q1_outnum=0.00;
			Double q1_minusnum=0.00;
			
			Double q1_actlost=0.00;Double q1_avgbuttomweight2=0.00;
			Double q1_productednum=0.00;
			Double q1_noglueweight=0.00;
			Double q1_repairmoney=0.00;
			Double q1_instorenum=0.00;
			//第二季度
			Double q2_sumEverydemo=0.00;Double q2_sumStandarddemo=0.00;
			Double q2_sumActualdemo=0.00;Double q2_sumActualpairs=0.00;
			Double q2_sumFactpairs=0.00;Double q2_sumWorkhours=0.00;
			
			Double q2_personzg=0.00;Double q2_personjg=0.00;
			Double q2_timezg=0.00;Double q2_timejg=0.00;
			Double q2_addtimezg=0.00;Double q2_addtimejg=0.00;
			Double q2_leavenumzg=0.00;Double q2_leavenumjg=0.00;
			Double q2_hurtnum=0.00;
			
			Double q2_invcount=0.00;Double q2_sellcount=0.00;
			Double q2_costcount=0.00;Double q2_wagezgUsd=0.00;
			Double q2_wagejgUsd=0.00;Double q2_cashcount=0.00;
			
			Double q2_sideweit=0.00;Double q2_badweit=0.00;
			Double q2_otherbadweight=0.00;
			Double q2_otherweight=0.00;
			
			Double q2_waterton=0.00;Double q2_electricdu=0.00;
			Double q2_gaston=0.00;
			
			Double q2_storenum=0.00;Double q2_outnum=0.00;
			Double q2_minusnum=0.00;
			
			Double q2_actlost=0.00;Double q2_avgbuttomweight2=0.00;
			Double q2_productednum=0.00;
			Double q2_noglueweight=0.00;
			Double q2_repairmoney=0.00;
			Double q2_instorenum=0.00;
			//第三季度
			Double q3_sumEverydemo=0.00;Double q3_sumStandarddemo=0.00;
			Double q3_sumActualdemo=0.00;Double q3_sumActualpairs=0.00;
			Double q3_sumFactpairs=0.00;Double q3_sumWorkhours=0.00;
			
			Double q3_personzg=0.00;Double q3_personjg=0.00;
			Double q3_timezg=0.00;Double q3_timejg=0.00;
			Double q3_addtimezg=0.00;Double q3_addtimejg=0.00;
			Double q3_leavenumzg=0.00;Double q3_leavenumjg=0.00;
			Double q3_hurtnum=0.00;
			
			Double q3_invcount=0.00;Double q3_sellcount=0.00;
			Double q3_costcount=0.00;Double q3_wagezgUsd=0.00;
			Double q3_wagejgUsd=0.00;Double q3_cashcount=0.00;
			
			Double q3_sideweit=0.00;Double q3_badweit=0.00;
			Double q3_otherbadweight=0.00;
			Double q3_otherweight=0.00;
			
			Double q3_waterton=0.00;Double q3_electricdu=0.00;
			Double q3_gaston=0.00;
			
			Double q3_storenum=0.00;Double q3_outnum=0.00;
			Double q3_minusnum=0.00;
			
			Double q3_actlost=0.00;Double q3_avgbuttomweight2=0.00;
			Double q3_productednum=0.00;
			Double q3_noglueweight=0.00;
			Double q3_repairmoney=0.00;
			Double q3_instorenum=0.00;
			//第四季度
			Double q4_sumEverydemo=0.00;Double q4_sumStandarddemo=0.00;
			Double q4_sumActualdemo=0.00;Double q4_sumActualpairs=0.00;
			Double q4_sumFactpairs=0.00;Double q4_sumWorkhours=0.00;
			
			Double q4_personzg=0.00;Double q4_personjg=0.00;
			Double q4_timezg=0.00;Double q4_timejg=0.00;
			Double q4_addtimezg=0.00;Double q4_addtimejg=0.00;
			Double q4_leavenumzg=0.00;Double q4_leavenumjg=0.00;
			Double q4_hurtnum=0.00;
			
			Double q4_invcount=0.00;Double q4_sellcount=0.00;
			Double q4_costcount=0.00;Double q4_wagezgUsd=0.00;
			Double q4_wagejgUsd=0.00;Double q4_cashcount=0.00;
			
			Double q4_sideweit=0.00;Double q4_badweit=0.00;
			Double q4_otherbadweight=0.00;
			Double q4_otherweight=0.00;
			
			Double q4_waterton=0.00;Double q4_electricdu=0.00;
			Double q4_gaston=0.00;
			
			Double q4_storenum=0.00;Double q4_outnum=0.00;
			Double q4_minusnum=0.00;
			
			Double q4_actlost=0.00;Double q4_avgbuttomweight2=0.00;
			Double q4_productednum=0.00;
			Double q4_noglueweight=0.00;
			Double q4_repairmoney=0.00;
			Double q4_instorenum=0.00;
			//上半年
			Double half1_sumEverydemo=0.00;Double half1_sumStandarddemo=0.00;
			Double half1_sumActualdemo=0.00;Double half1_sumActualpairs=0.00;
			Double half1_sumFactpairs=0.00;Double half1_sumWorkhours=0.00;
			
			Double half1_personzg=0.00;Double half1_personjg=0.00;
			Double half1_timezg=0.00;Double half1_timejg=0.00;
			Double half1_addtimezg=0.00;Double half1_addtimejg=0.00;
			Double half1_leavenumzg=0.00;Double half1_leavenumjg=0.00;
			Double half1_hurtnum=0.00;
			
			Double half1_invcount=0.00;Double half1_sellcount=0.00;
			Double half1_costcount=0.00;Double half1_wagezgUsd=0.00;
			Double half1_wagejgUsd=0.00;Double half1_cashcount=0.00;
			
			Double half1_sideweit=0.00;Double half1_badweit=0.00;
			Double half1_otherbadweight=0.00;
			Double half1_otherweight=0.00;
			
			Double half1_waterton=0.00;Double half1_electricdu=0.00;
			Double half1_gaston=0.00;
			
			Double half1_storenum=0.00;Double half1_outnum=0.00;
			Double half1_minusnum=0.00;
			
			Double half1_actlost=0.00;Double half1_avgbuttomweight2=0.00;
			Double half1_productednum=0.00;
			Double half1_noglueweight=0.00;
			Double half1_repairmoney=0.00;
			Double half1_instorenum=0.00;
			//下半年
			Double half2_sumEverydemo=0.00;Double half2_sumStandarddemo=0.00;
			Double half2_sumActualdemo=0.00;Double half2_sumActualpairs=0.00;
			Double half2_sumFactpairs=0.00;Double half2_sumWorkhours=0.00;
			
			Double half2_personzg=0.00;Double half2_personjg=0.00;
			Double half2_timezg=0.00;Double half2_timejg=0.00;
			Double half2_addtimezg=0.00;Double half2_addtimejg=0.00;
			Double half2_leavenumzg=0.00;Double half2_leavenumjg=0.00;
			Double half2_hurtnum=0.00;
			
			Double half2_invcount=0.00;Double half2_sellcount=0.00;
			Double half2_costcount=0.00;Double half2_wagezgUsd=0.00;
			Double half2_wagejgUsd=0.00;Double half2_cashcount=0.00;
			
			Double half2_sideweit=0.00;Double half2_badweit=0.00;
			Double half2_otherbadweight=0.00;
			Double half2_otherweight=0.00;
			
			Double half2_waterton=0.00;Double half2_electricdu=0.00;
			Double half2_gaston=0.00;
			
			Double half2_storenum=0.00;Double half2_outnum=0.00;
			Double half2_minusnum=0.00;
			
			Double half2_actlost=0.00;Double half2_avgbuttomweight2=0.00;
			Double half2_productednum=0.00;
			Double half2_noglueweight=0.00;
			Double half2_repairmoney=0.00;
			Double half2_instorenum=0.00;
			//全年
			Double year_sumEverydemo=0.00;Double year_sumStandarddemo=0.00;
			Double year_sumActualdemo=0.00;Double year_sumActualpairs=0.00;
			Double year_sumFactpairs=0.00;Double year_sumWorkhours=0.00;
			
			Double year_personzg=0.00;Double year_personjg=0.00;
			Double year_timezg=0.00;Double year_timejg=0.00;
			Double year_addtimezg=0.00;Double year_addtimejg=0.00;
			Double year_leavenumzg=0.00;Double year_leavenumjg=0.00;
			Double year_hurtnum=0.00;
			
			Double year_invcount=0.00;Double year_sellcount=0.00;
			Double year_costcount=0.00;Double year_wagezgUsd=0.00;
			Double year_wagejgUsd=0.00;Double year_cashcount=0.00;
			
			Double year_sideweit=0.00;Double year_badweit=0.00;
			Double year_otherbadweight=0.00;
			Double year_otherweight=0.00;
			
			Double year_waterton=0.00;Double year_electricdu=0.00;
			Double year_gaston=0.00;
			
			Double year_storenum=0.00;Double year_outnum=0.00;
			Double year_minusnum=0.00;
			
			Double year_actlost=0.00;Double year_avgbuttomweight2=0.00;
			Double year_productednum=0.00;
			Double year_noglueweight=0.00;
			Double year_repairmoney=0.00;
			Double year_instorenum=0.00;
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
				VKpifactEve eve=vkpieveSer.findById(factNo, factCode, year+month);
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
					/*if(eve.getOtherweight()==null){
						eve.setOtherweight(0.0);
					}*/
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
					if(b<4){
						q1_sumEverydemo=q1_sumEverydemo+eve.getSumEverydemo().doubleValue();q1_sumStandarddemo=q1_sumStandarddemo+eve.getSumStandarddemo().doubleValue();
						q1_sumActualdemo=q1_sumActualdemo+eve.getSumActualdemo().doubleValue();q1_sumActualpairs=q1_sumActualpairs+eve.getSumActualpairs().doubleValue();
						q1_sumFactpairs=q1_sumFactpairs+eve.getSumFacpairs().doubleValue();q1_sumWorkhours=q1_sumWorkhours+eve.getWorkhours();
						
						q1_personzg=q1_personzg+eve.getPersonzg();q1_personjg=q1_personjg+eve.getPersonjg();
						q1_timezg=q1_timezg+eve.getTimezg();q1_timejg=q1_timejg+eve.getTimejg();
						q1_addtimezg=q1_addtimezg+eve.getAddtimezg();q1_addtimejg=q1_addtimejg+eve.getAddtimejg();
						q1_leavenumzg=q1_leavenumzg+eve.getLeavenumzg();q1_leavenumjg=q1_leavenumjg+eve.getLeavenumjg();
						q1_hurtnum=q1_hurtnum+eve.getHurtnum();
						
						q1_invcount=q1_invcount+eve.getInvcount();q1_sellcount=q1_sellcount+eve.getSellcount();
						q1_costcount=q1_costcount+eve.getCostcount();q1_wagezgUsd=q1_wagezgUsd+eve.getWagezgUsd();
						q1_wagejgUsd=q1_wagejgUsd+eve.getWagejgUsd();q1_cashcount=q1_cashcount+eve.getCashcount();
						
						q1_sideweit=q1_sideweit+eve.getSideweit();q1_badweit=q1_badweit+eve.getBadweit();
						q1_otherbadweight=q1_otherbadweight+eve.getOtherbadweight();
						q1_otherweight=q1_otherweight+eve.getOtherweight().doubleValue();
						
						q1_waterton=q1_waterton+eve.getWaterton();q1_electricdu=q1_electricdu+eve.getElectricdu();
						q1_gaston=q1_gaston+eve.getGaston();
						
						q1_storenum=q1_storenum+eve.getStorenum();q1_outnum=q1_outnum+eve.getOutnum();
						q1_minusnum=q1_minusnum+eve.getMinusnum();
						
						q1_actlost=q1_actlost+eve.getActlost();q1_avgbuttomweight2=q1_avgbuttomweight2+eve.getAvgbuttomweight2();
						q1_productednum=q1_productednum+eve.getProductednum();
						q1_noglueweight=q1_noglueweight+eve.getNoglueweight();
						q1_repairmoney=q1_repairmoney+eve.getRepairmoney();
						q1_instorenum=q1_instorenum+eve.getInstorenum();
					}
					if(b>3&&b<7){
						q2_sumEverydemo=q2_sumEverydemo+eve.getSumEverydemo().doubleValue();q2_sumStandarddemo=q2_sumStandarddemo+eve.getSumStandarddemo().doubleValue();
						q2_sumActualdemo=q2_sumActualdemo+eve.getSumActualdemo().doubleValue();q2_sumActualpairs=q2_sumActualpairs+eve.getSumActualpairs().doubleValue();
						q2_sumFactpairs=q2_sumFactpairs+eve.getSumFacpairs().doubleValue();q2_sumWorkhours=q2_sumWorkhours+eve.getWorkhours();
						
						q2_personzg=q2_personzg+eve.getPersonzg();q2_personjg=q2_personjg+eve.getPersonjg();
						q2_timezg=q2_timezg+eve.getTimezg();q2_timejg=q2_timejg+eve.getTimejg();
						q2_addtimezg=q2_addtimezg+eve.getAddtimezg();q2_addtimejg=q2_addtimejg+eve.getAddtimejg();
						q2_leavenumzg=q2_leavenumzg+eve.getLeavenumzg();q2_leavenumjg=q2_leavenumjg+eve.getLeavenumjg();
						q2_hurtnum=q2_hurtnum+eve.getHurtnum();
						
						q2_invcount=q2_invcount+eve.getInvcount();q2_sellcount=q2_sellcount+eve.getSellcount();
						q2_costcount=q2_costcount+eve.getCostcount();q2_wagezgUsd=q2_wagezgUsd+eve.getWagezgUsd();
						q2_wagejgUsd=q2_wagejgUsd+eve.getWagejgUsd();q2_cashcount=q2_cashcount+eve.getCashcount();
						
						q2_sideweit=q2_sideweit+eve.getSideweit();q2_badweit=q2_badweit+eve.getBadweit();
						q2_otherbadweight=q2_otherbadweight+eve.getOtherbadweight();
						q2_otherweight=q2_otherweight+eve.getOtherweight().doubleValue();
						
						q2_waterton=q2_waterton+eve.getWaterton();q2_electricdu=q2_electricdu+eve.getElectricdu();
						q2_gaston=q2_gaston+eve.getGaston();
						
						q2_storenum=q2_storenum+eve.getStorenum();q2_outnum=q2_outnum+eve.getOutnum();
						q2_minusnum=q2_minusnum+eve.getMinusnum();
						
						q2_actlost=q2_actlost+eve.getActlost();q2_avgbuttomweight2=q2_avgbuttomweight2+eve.getAvgbuttomweight2();
						q2_productednum=q2_productednum+eve.getProductednum();
						q2_noglueweight=q2_noglueweight+eve.getNoglueweight();
						q2_repairmoney=q2_repairmoney+eve.getRepairmoney();
						q2_instorenum=q2_instorenum+eve.getInstorenum();
												
					}
					if(b>6&&b<10){
						q3_sumEverydemo=q3_sumEverydemo+eve.getSumEverydemo().doubleValue();q3_sumStandarddemo=q3_sumStandarddemo+eve.getSumStandarddemo().doubleValue();
						q3_sumActualdemo=q3_sumActualdemo+eve.getSumActualdemo().doubleValue();q3_sumActualpairs=q3_sumActualpairs+eve.getSumActualpairs().doubleValue();
						q3_sumFactpairs=q3_sumFactpairs+eve.getSumFacpairs().doubleValue();q3_sumWorkhours=q3_sumWorkhours+eve.getWorkhours();
						
						q3_personzg=q3_personzg+eve.getPersonzg();q3_personjg=q3_personjg+eve.getPersonjg();
						q3_timezg=q3_timezg+eve.getTimezg();q3_timejg=q3_timejg+eve.getTimejg();
						q3_addtimezg=q3_addtimezg+eve.getAddtimezg();q3_addtimejg=q3_addtimejg+eve.getAddtimejg();
						q3_leavenumzg=q3_leavenumzg+eve.getLeavenumzg();q3_leavenumjg=q3_leavenumjg+eve.getLeavenumjg();
						q3_hurtnum=q3_hurtnum+eve.getHurtnum();
						
						q3_invcount=q3_invcount+eve.getInvcount();q3_sellcount=q3_sellcount+eve.getSellcount();
						q3_costcount=q3_costcount+eve.getCostcount();q3_wagezgUsd=q3_wagezgUsd+eve.getWagezgUsd();
						q3_wagejgUsd=q3_wagejgUsd+eve.getWagejgUsd();q3_cashcount=q3_cashcount+eve.getCashcount();
						
						q3_sideweit=q3_sideweit+eve.getSideweit();q3_badweit=q3_badweit+eve.getBadweit();
						q3_otherbadweight=q3_otherbadweight+eve.getOtherbadweight();
						q3_otherweight=q3_otherweight+eve.getOtherweight().doubleValue();
						
						q3_waterton=q3_waterton+eve.getWaterton();q3_electricdu=q3_electricdu+eve.getElectricdu();
						q3_gaston=q3_gaston+eve.getGaston();
						
						q3_storenum=q3_storenum+eve.getStorenum();q3_outnum=q3_outnum+eve.getOutnum();
						q3_minusnum=q3_minusnum+eve.getMinusnum();
						
						q3_actlost=q3_actlost+eve.getActlost();q3_avgbuttomweight2=q3_avgbuttomweight2+eve.getAvgbuttomweight2();
						q3_productednum=q3_productednum+eve.getProductednum();
						q3_noglueweight=q3_noglueweight+eve.getNoglueweight();
						q3_repairmoney=q3_repairmoney+eve.getRepairmoney();
						q3_instorenum=q3_instorenum+eve.getInstorenum();
					}
					if(b>9){
						q4_sumEverydemo=q4_sumEverydemo+eve.getSumEverydemo().doubleValue();q4_sumStandarddemo=q4_sumStandarddemo+eve.getSumStandarddemo().doubleValue();
						q4_sumActualdemo=q4_sumActualdemo+eve.getSumActualdemo().doubleValue();q4_sumActualpairs=q4_sumActualpairs+eve.getSumActualpairs().doubleValue();
						q4_sumFactpairs=q4_sumFactpairs+eve.getSumFacpairs().doubleValue();q4_sumWorkhours=q4_sumWorkhours+eve.getWorkhours();
						
						q4_personzg=q4_personzg+eve.getPersonzg();q4_personjg=q4_personjg+eve.getPersonjg();
						q4_timezg=q4_timezg+eve.getTimezg();q4_timejg=q4_timejg+eve.getTimejg();
						q4_addtimezg=q4_addtimezg+eve.getAddtimezg();q4_addtimejg=q4_addtimejg+eve.getAddtimejg();
						q4_leavenumzg=q4_leavenumzg+eve.getLeavenumzg();q4_leavenumjg=q4_leavenumjg+eve.getLeavenumjg();
						q4_hurtnum=q4_hurtnum+eve.getHurtnum();
						
						q4_invcount=q4_invcount+eve.getInvcount();q4_sellcount=q4_sellcount+eve.getSellcount();
						q4_costcount=q4_costcount+eve.getCostcount();q4_wagezgUsd=q4_wagezgUsd+eve.getWagezgUsd();
						q4_wagejgUsd=q4_wagejgUsd+eve.getWagejgUsd();q4_cashcount=q4_cashcount+eve.getCashcount();
						
						q4_sideweit=q4_sideweit+eve.getSideweit();q4_badweit=q4_badweit+eve.getBadweit();
						q4_otherbadweight=q4_otherbadweight+eve.getOtherbadweight();
						q4_otherweight=q4_otherweight+eve.getOtherweight().doubleValue();
						
						q4_waterton=q4_waterton+eve.getWaterton();q4_electricdu=q4_electricdu+eve.getElectricdu();
						q4_gaston=q4_gaston+eve.getGaston();
						
						q4_storenum=q4_storenum+eve.getStorenum();q4_outnum=q4_outnum+eve.getOutnum();
						q4_minusnum=q4_minusnum+eve.getMinusnum();
						
						q4_actlost=q4_actlost+eve.getActlost();q4_avgbuttomweight2=q4_avgbuttomweight2+eve.getAvgbuttomweight2();
						q4_productednum=q4_productednum+eve.getProductednum();
						q4_noglueweight=q4_noglueweight+eve.getNoglueweight();
						q4_repairmoney=q4_repairmoney+eve.getRepairmoney();
						q4_instorenum=q4_instorenum+eve.getInstorenum();
					}															
				}// end if
				switch(b){
				//第一季度
				case 3:
					list3_q1=this.findResult(b,
							q1_sumEverydemo, q1_sumStandarddemo, q1_sumActualdemo, q1_sumActualpairs, q1_sumFactpairs,
							q1_personzg, q1_personjg, q1_timezg, q1_timejg, q1_addtimezg, q1_addtimejg, q1_leavenumzg, q1_leavenumjg, q1_hurtnum,
							q1_invcount, q1_sellcount, q1_costcount, q1_wagezgUsd, q1_wagejgUsd, q1_cashcount,
							q1_sideweit, q1_badweit, q1_otherbadweight,
							q1_waterton, q1_electricdu, q1_gaston,
							q1_storenum, q1_outnum, q1_minusnum,
							q1_actlost, q1_avgbuttomweight2,q1_otherweight,
							q1_productednum,q1_noglueweight,q1_repairmoney,
							q1_sumWorkhours,q1_instorenum);
					list2_all.add(list3_q1);
					break;
				//第二季度和上半年
				case 6:
					//上半年
					half1_sumEverydemo=q1_sumEverydemo+q2_sumEverydemo;half1_sumStandarddemo=q1_sumStandarddemo+q2_sumStandarddemo;
					half1_sumActualdemo=q1_sumActualdemo+q2_sumActualdemo;half1_sumActualpairs=q1_sumActualpairs+q2_sumActualpairs;
					half1_sumFactpairs=q1_sumFactpairs+q2_sumFactpairs;half1_sumWorkhours=q1_sumWorkhours+q2_sumWorkhours;
					
					half1_personzg=q1_personzg+q2_personzg;half1_personjg=q1_personjg+q2_personjg;
					half1_timezg=q1_timezg+q2_timezg;half1_timejg=q1_timejg+q2_timejg;
					half1_addtimezg=q1_addtimezg+q2_addtimezg;half1_addtimejg=q1_addtimejg+q2_addtimejg;
					half1_leavenumzg=q1_leavenumzg+q2_leavenumzg;half1_leavenumjg=q1_leavenumjg+q2_leavenumjg;
					half1_hurtnum=q1_hurtnum+q2_hurtnum;
					
					half1_invcount=q1_invcount+q2_invcount;half1_sellcount=q1_sellcount+q2_sellcount;
					half1_costcount=q1_costcount+q2_costcount;half1_wagezgUsd=q1_wagezgUsd+q2_wagezgUsd;
					half1_wagejgUsd=q1_wagejgUsd+q2_wagejgUsd;half1_cashcount=q1_cashcount+q2_cashcount;
					
					half1_sideweit=q1_sideweit+q2_sideweit;half1_badweit=q1_badweit+q2_badweit;
					half1_otherbadweight=q1_otherbadweight+q2_otherbadweight;
					half1_otherweight=q1_otherweight+q2_otherweight;
					
					half1_waterton=q1_waterton+q2_waterton;half1_electricdu=q1_electricdu+q2_electricdu;
					half1_gaston=q1_gaston+q2_gaston;
					
					half1_storenum=q1_storenum+q2_storenum;half1_outnum=q1_outnum+q2_outnum;
					half1_minusnum=q1_minusnum+q2_minusnum;
					
					half1_actlost=q1_actlost+q2_actlost;half1_avgbuttomweight2=q1_avgbuttomweight2+q2_avgbuttomweight2;
					half1_productednum=q1_productednum+q2_productednum;
					half1_noglueweight=q1_noglueweight+q2_noglueweight;
					half1_repairmoney=q1_repairmoney+q2_repairmoney;
					half1_instorenum=q1_instorenum+q2_instorenum;
					list3_q2=this.findResult(b/2,
							q2_sumEverydemo, q2_sumStandarddemo, q2_sumActualdemo, q2_sumActualpairs, q2_sumFactpairs,
							q2_personzg, q2_personjg, q2_timezg, q2_timejg, q2_addtimezg, q2_addtimejg, q2_leavenumzg, q2_leavenumjg, q2_hurtnum,
							q2_invcount, q2_sellcount, q2_costcount, q2_wagezgUsd, q2_wagejgUsd, q2_cashcount,
							q2_sideweit, q2_badweit, q2_otherbadweight,
							q2_waterton, q2_electricdu, q2_gaston,
							q2_storenum, q2_outnum, q2_minusnum,
							q2_actlost, q2_avgbuttomweight2,q2_otherweight,
							q2_productednum,q2_noglueweight,q2_repairmoney,
							q2_sumWorkhours,q2_instorenum);
					list3_half1=this.findResult(b,
							half1_sumEverydemo, half1_sumStandarddemo, half1_sumActualdemo, half1_sumActualpairs, half1_sumFactpairs,
							half1_personzg, half1_personjg, half1_timezg, half1_timejg, half1_addtimezg, half1_addtimejg, half1_leavenumzg, half1_leavenumjg, half1_hurtnum,
							half1_invcount, half1_sellcount, half1_costcount, half1_wagezgUsd, half1_wagejgUsd, half1_cashcount,
							half1_sideweit, half1_badweit, half1_otherbadweight,
							half1_waterton, half1_electricdu, half1_gaston,
							half1_storenum, half1_outnum, half1_minusnum,
							half1_actlost, half1_avgbuttomweight2,half1_otherweight,
							half1_productednum,half1_noglueweight,half1_repairmoney,
							half1_sumWorkhours,half1_instorenum);
					list2_all.add(list3_q2);
					list2_all.add(list3_half1);
					break;
				//第三季度	
				case 9:
					list3_q3=this.findResult(b/3,
							q3_sumEverydemo, q3_sumStandarddemo, q3_sumActualdemo, q3_sumActualpairs, q3_sumFactpairs,
							q3_personzg, q3_personjg, q3_timezg, q3_timejg, q3_addtimezg, q3_addtimejg, q3_leavenumzg, q3_leavenumjg, q3_hurtnum,
							q3_invcount, q3_sellcount, q3_costcount, q3_wagezgUsd, q3_wagejgUsd, q3_cashcount,
							q3_sideweit, q3_badweit, q3_otherbadweight,
							q3_waterton, q3_electricdu, q3_gaston,
							q3_storenum, q3_outnum, q3_minusnum,
							q3_actlost, q3_avgbuttomweight2,q3_otherweight,
							q3_productednum,q3_noglueweight,q3_repairmoney,
							q3_sumWorkhours,q3_instorenum);
					list2_all.add(list3_q3);
					break;
				//第四季度,下半年,全年	
				case 12:
					//下半年
					half2_sumEverydemo=q3_sumEverydemo+q4_sumEverydemo;half2_sumStandarddemo=q3_sumStandarddemo+q4_sumStandarddemo;
					half2_sumActualdemo=q3_sumActualdemo+q4_sumActualdemo;half2_sumActualpairs=q3_sumActualpairs+q4_sumActualpairs;
					half2_sumFactpairs=q3_sumFactpairs+q4_sumFactpairs;half2_sumWorkhours=q3_sumWorkhours+q4_sumWorkhours;
					
					half2_personzg=q3_personzg+q4_personzg;half2_personjg=q3_personjg+q4_personjg;
					half2_timezg=q3_timezg+q4_timezg;half2_timejg=q3_timejg+q4_timejg;
					half2_addtimezg=q3_addtimezg+q4_addtimezg;half2_addtimejg=q3_addtimejg+q4_addtimejg;
					half2_leavenumzg=q3_leavenumzg+q4_leavenumzg;half2_leavenumjg=q3_leavenumjg+q4_leavenumjg;
					half2_hurtnum=q3_hurtnum+q4_hurtnum;
					
					half2_invcount=q3_invcount+q4_invcount;half2_sellcount=q3_sellcount+q4_sellcount;
					half2_costcount=q3_costcount+q4_costcount;half2_wagezgUsd=q3_wagezgUsd+q4_wagezgUsd;
					half2_wagejgUsd=q3_wagejgUsd+q4_wagejgUsd;half2_cashcount=q3_cashcount+q4_cashcount;
					
					half2_sideweit=q3_sideweit+q4_sideweit;half2_badweit=q3_badweit+q4_badweit;
					half2_otherbadweight=q3_otherbadweight+q4_otherbadweight;
					half2_otherweight=q3_otherweight+q4_otherweight;
					
					half2_waterton=q3_waterton+q4_waterton;half2_electricdu=q3_electricdu+q4_electricdu;
					half2_gaston=q3_gaston+q4_gaston;
					
					half2_storenum=q3_storenum+q4_storenum;half2_outnum=q3_outnum+q4_outnum;
					half2_minusnum=q3_minusnum+q4_minusnum;
					
					half2_actlost=q3_actlost+q4_actlost;half2_avgbuttomweight2=q3_avgbuttomweight2+q4_avgbuttomweight2;
					half2_productednum=q3_productednum+q4_productednum;
					half2_noglueweight=q3_noglueweight+q4_noglueweight;
					half2_repairmoney=q3_repairmoney+q4_repairmoney;
					half2_instorenum=q3_instorenum+q4_instorenum;
					//全年
					year_sumEverydemo=half1_sumEverydemo+half2_sumEverydemo;year_sumStandarddemo=half1_sumStandarddemo+half2_sumStandarddemo;
					year_sumActualdemo=half1_sumActualdemo+half2_sumActualdemo;year_sumActualpairs=half1_sumActualpairs+half2_sumActualpairs;
					year_sumFactpairs=half1_sumFactpairs+half2_sumFactpairs;year_sumWorkhours=half1_sumWorkhours+half2_sumWorkhours;
					
					year_personzg=half1_personzg+half2_personzg;year_personjg=half1_personjg+half2_personjg;
					year_timezg=half1_timezg+half2_timezg;year_timejg=half1_timejg+half2_timejg;
					year_addtimezg=half1_addtimezg+half2_addtimezg;year_addtimejg=half1_addtimejg+half2_addtimejg;
					year_leavenumzg=half1_leavenumzg+half2_leavenumzg;year_leavenumjg=half1_leavenumjg+half2_leavenumjg;
					year_hurtnum=half1_hurtnum+half2_hurtnum;
					
					year_invcount=half1_invcount+half2_invcount;year_sellcount=half1_sellcount+half2_sellcount;
					year_costcount=half1_costcount+half2_costcount;year_wagezgUsd=half1_wagezgUsd+half2_wagezgUsd;
					year_wagejgUsd=half1_wagejgUsd+half2_wagejgUsd;year_cashcount=half1_cashcount+half2_cashcount;
					
					year_sideweit=half1_sideweit+half2_sideweit;year_badweit=half1_badweit+half2_badweit;
					year_otherbadweight=half1_otherbadweight+half2_otherbadweight;
					year_otherweight=half1_otherweight+half2_otherweight;
					
					year_waterton=half1_waterton+half2_waterton;year_electricdu=half1_electricdu+half2_electricdu;
					year_gaston=half1_gaston+half2_gaston;
					
					year_storenum=half1_storenum+half2_storenum;year_outnum=half1_outnum+half2_outnum;
					year_minusnum=half1_minusnum+half2_minusnum;
					
					year_actlost=half1_actlost+half2_actlost;year_avgbuttomweight2=half1_avgbuttomweight2+half2_avgbuttomweight2;
					year_productednum=half1_productednum+half2_productednum;
					year_noglueweight=half1_noglueweight+half2_noglueweight;
					year_repairmoney=half1_repairmoney+half2_repairmoney;
					year_instorenum=half1_instorenum+half2_instorenum;
					list3_q4=this.findResult(b/4,
							q4_sumEverydemo, q4_sumStandarddemo, q4_sumActualdemo, q4_sumActualpairs, q4_sumFactpairs,
							q4_personzg, q4_personjg, q4_timezg, q4_timejg, q4_addtimezg, q4_addtimejg, q4_leavenumzg, q4_leavenumjg, q4_hurtnum,
							q4_invcount, q4_sellcount, q4_costcount, q4_wagezgUsd, q4_wagejgUsd, q4_cashcount,
							q4_sideweit, q4_badweit, q4_otherbadweight,
							q4_waterton, q4_electricdu, q4_gaston,
							q4_storenum, q4_outnum, q4_minusnum,
							q4_actlost, q4_avgbuttomweight2,q4_otherweight,
							q4_productednum,q4_noglueweight,q4_repairmoney,
							q4_sumWorkhours,q4_instorenum);
					list3_half2=this.findResult(b/2,
							half2_sumEverydemo, half2_sumStandarddemo, half2_sumActualdemo, half2_sumActualpairs, half2_sumFactpairs,
							half2_personzg, half2_personjg, half2_timezg, half2_timejg, half2_addtimezg, half2_addtimejg, half2_leavenumzg, half2_leavenumjg, half2_hurtnum,
							half2_invcount, half2_sellcount, half2_costcount, half2_wagezgUsd, half2_wagejgUsd, half2_cashcount,
							half2_sideweit, half2_badweit, half2_otherbadweight,
							half2_waterton, half2_electricdu, half2_gaston,
							half2_storenum, half2_outnum, half2_minusnum,
							half2_actlost, half2_avgbuttomweight2,half2_otherweight,
							half2_productednum,half2_noglueweight,q4_repairmoney,
							half2_sumWorkhours,half2_instorenum);
					list3_year=this.findResult(b,
							year_sumEverydemo, year_sumStandarddemo, year_sumActualdemo, year_sumActualpairs, year_sumFactpairs,
							year_personzg, year_personjg, year_timezg, year_timejg, year_addtimezg, year_addtimejg, year_leavenumzg, year_leavenumjg, year_hurtnum,
							year_invcount, year_sellcount, year_costcount, year_wagezgUsd, year_wagejgUsd, year_cashcount,
							year_sideweit, year_badweit, year_otherbadweight,
							year_waterton, year_electricdu, year_gaston,
							year_storenum, year_outnum, year_minusnum,
							year_actlost, year_avgbuttomweight2,year_otherweight,
							year_productednum,year_noglueweight,year_repairmoney,
							year_sumWorkhours,year_instorenum);
					list2_all.add(list3_q4);
					list2_all.add(list3_half2);
					list2_all.add(list3_year);
					break;
				}
				
			}//end for2
			list1_all.add(list2_all);
		}// end for1
		
		
		for(int k=0;k<list_factcode.size();k++){//start for1			
			WebFact fact=list_factcode.get(k);
			String factCode=fact.getId().getFactArea();	
			Kpifact kpi_pur=kpiSer.findById(factNo, factCode, yymm);//每年的預計目標(20150327)
			List<Double> list_content_pur=new ArrayList<Double>();//每年的預計目標封裝在一個list，以便於for循環(20150327)
			if(kpi_pur!=null){//start if2(20150327)
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
			sheet.createRow(2+28*k).createCell(0).setCellValue("形態:"+factCode);
			HSSFRow row_columnHead=sheet.createRow(3+28*k);			
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
						HSSFRow row=sheet.createRow(4+j+28*k);
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
						HSSFCellStyle cs_temp=wb.createCellStyle();
						if(kpi_pur!=null){							
							//數字格式的選擇
							if(j==0||j==2){
								cs_temp=cs_poi1;
							}else if(j==1||j==5||j==6||j==9||j==10){
								cs_temp=cs_poi;
							}else if(j>12&&j<17){
								cs_temp=cs_poi4;
							}else if(j>7&&j<13||j==22||j==23){
								cs_temp=cs_poi2;
							}else{
								cs_temp=cs_percent;
							}
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
				 * 開始
				 */
				/*if(kpi!=null){	
					for(int j=0;j<list_str.size();j++){
						HSSFRow row=sheet.getRow(4+j+28*k);
						HSSFCell cell=row.createCell(i+3+temp_num);
						HSSFCellStyle cs_temp=wb.createCellStyle();
						//數字格式的選擇
						if(j==0||j==2){
							cs_temp=cs_poi1;
						}else if(j==1||j==5||j==6||j==9||j==10){
							cs_temp=cs_poi;
						}else if(j>12&&j<17){
							cs_temp=cs_poi4;
						}else if(j>7&&j<13||j==22||j==23){
							cs_temp=cs_poi2;
						}else{
							cs_temp=cs_percent;
						}
						cell.setCellValue(list_content.get(j));
						cell.setCellStyle(cs_temp);
																		
						if(i==2){
							HSSFCell cell2=row.createCell(i+4+temp_num);//第一季度
							cell2.setCellValue(list3_q1.get(j));
							cell2.setCellStyle(cs_temp);
						}
						if(i==5){
							HSSFCell cell3_1=row.createCell(i+4+temp_num);//第二季度
							HSSFCell cell3_2=row.createCell(i+5+temp_num);//上半年
							cell3_1.setCellValue(list3_q2.get(j));
							cell3_2.setCellValue(list3_half1.get(j));
							cell3_1.setCellStyle(cs_temp);
							cell3_2.setCellStyle(cs_temp);
						}
						if(i==8){
							HSSFCell cell4=row.createCell(i+4+temp_num);//第三季度
							cell4.setCellValue(list3_q3.get(j));
							cell4.setCellStyle(cs_temp);
						}
						if(i==11){
							HSSFCell cell5=row.createCell(i+4+temp_num);//第四季度
							HSSFCell cell5_2=row.createCell(i+5+temp_num);//下半年
							HSSFCell cell5_3=row.createCell(i+6+temp_num);//全年
							cell5.setCellValue(list3_q4.get(j));
							cell5_2.setCellValue(list3_half2.get(j));
							cell5_3.setCellValue(list3_year.get(j));
							cell5.setCellStyle(cs_temp);
							cell5_2.setCellStyle(cs_temp);
							cell5_3.setCellStyle(cs_temp);
						}
				    }				
				}else{
					for(int j=0;j<list_str.size();j++){
						HSSFRow row=sheet.getRow(4+j+28*k);
						HSSFCell cell=row.createCell(i+3+temp_num);
						//數字格式的選擇
						HSSFCellStyle cs_temp=wb.createCellStyle();                      
						if(j==0||j==2){
							cs_temp=cs_poi1;
						}else if(j==1||j==5||j==6||j==9||j==10){
							cs_temp=cs_poi;
						}else if(j>12&&j<17){
							cs_temp=cs_poi4;
						}else if(j>7&&j<13||j==22||j==23){
							cs_temp=cs_poi2;
						}else{
							cs_temp=cs_percent;
						}
						cell.setCellValue("無數據");						
						cell.setCellStyle(cs_temp);
						
						
						if(i==2){							
							HSSFCell cell2=row.createCell(i+4+temp_num);//第一季度
							cell2.setCellValue(list3_q1.get(j));
							cell2.setCellStyle(cs_temp);
						}
						if(i==5){
							HSSFCell cell3_1=row.createCell(i+4+temp_num);//第二季度
							HSSFCell cell3_2=row.createCell(i+5+temp_num);//上半年							
							cell3_1.setCellValue(list3_q2.get(j));
							cell3_2.setCellValue(list3_half1.get(j));
							cell3_1.setCellStyle(cs_temp);
							cell3_2.setCellStyle(cs_temp);
						}
						if(i==8){
							HSSFCell cell4=row.createCell(i+4+temp_num);//第三季度
							cell4.setCellValue(list3_q3.get(j));
							cell4.setCellStyle(cs_temp);
						}
						if(i==11){
							HSSFCell cell5=row.createCell(i+4+temp_num);//第四季度
							HSSFCell cell5_2=row.createCell(i+5+temp_num);//下半年
							HSSFCell cell5_3=row.createCell(i+6+temp_num);//全年
							cell5.setCellValue(list3_q4.get(j));
							cell5_2.setCellValue(list3_half2.get(j));
							cell5_3.setCellValue(list3_year.get(j));
							cell5.setCellStyle(cs_temp);
							cell5_2.setCellStyle(cs_temp);
							cell5_3.setCellStyle(cs_temp);
						}
					}
					
				}*/
				/**
				 * 結束
				 */
				
				/**
				 * 20150327
				 * 以下代碼代替以上注釋代碼
				 * 開始2
				 */
				for(int j=0;j<list_str.size();j++){
					HSSFRow row=sheet.getRow(4+j+28*k);
					HSSFCell cell=row.createCell(i+4+temp_num);
					HSSFCellStyle cs_temp=wb.createCellStyle();
					
					//數字格式的選擇
					if(j==0||j==2){
						cs_temp=cs_poi1;
					}else if(j==1||j==5||j==6||j==9||j==10){
						cs_temp=cs_poi;
					}else if(j>12&&j<17){
						cs_temp=cs_poi4;
					}else if(j>7&&j<13||j==22||j==23){
						cs_temp=cs_poi2;
					}else{
						cs_temp=cs_percent;
					}
					cell.setCellStyle(cs_temp);
					/**
					 * 20150327
					 * 開始3
					 * 月度的樣式判斷
					 */
					if(kpi!=null&&kpi_pur!=null){
						HSSFCellStyle cs_temp2=wb.createCellStyle();
						Double num1=list_content.get(j);//實際值
						Double num2=list_content_pur.get(j);//預計值	
						boolean flag=true;
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
							cs_temp=wb.createCellStyle();
							Double num1=list3_q1.get(j);
							Double num2=list_content_pur.get(j);
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
							}
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
							boolean flag1=true;
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
							}
						}//end if
						
					}
					if(i==8){
						HSSFCell cell4=row.createCell(i+5+temp_num);//第三季度
						cell4.setCellValue(list3_q3.get(j));
						cell4.setCellStyle(cs_temp);
						if(kpi_pur!=null){//start if
							Double num1=list3_q3.get(j);
							Double num2=list_content_pur.get(j);
							boolean flag=true;
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
							}
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
							boolean flag1=true;
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
							}
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
		
		//標題樣式
		HSSFCellStyle cs_head=wb.createCellStyle();
		HSSFFont font_head=wb.createFont();
		font_head.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_head.setFontHeightInPoints((short)20);
		cs_head.setFont(font_head);
		cs_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_head.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		//標準單元格樣式
		HSSFCellStyle cs=wb.createCellStyle();
		cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
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
		
		/**
		 * 數字格式
		 * 有背景與無背景
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
		
		HSSFCellStyle cs_poi=wb.createCellStyle();
		cs_poi.setDataFormat(format.getFormat("#,###,0"));
		cs_poi.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle cs_poi1=wb.createCellStyle();
		cs_poi1.setDataFormat(format.getFormat("#,###,0.0"));
		cs_poi1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle cs_poi2=wb.createCellStyle();
		cs_poi2.setDataFormat(format.getFormat("#,###,0.00"));
		cs_poi2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle cs_poi4=wb.createCellStyle();
		cs_poi4.setDataFormat(format.getFormat("#,###,0.0000"));
		cs_poi4.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi4.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		//有背景
		HSSFCellStyle cs_percent_bg=wb.createCellStyle();
		cs_percent_bg.setDataFormat(format.getFormat("0.00%"));
		cs_percent_bg.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_percent_bg.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_percent_bg.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_percent_bg.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_percent_bg.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_percent_bg.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_percent_bg.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		cs_percent_bg.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		HSSFCellStyle cs_poi_bg=wb.createCellStyle();
		cs_poi_bg.setDataFormat(format.getFormat("#,###,0"));
		cs_poi_bg.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi_bg.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi_bg.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi_bg.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi_bg.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi_bg.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_poi_bg.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		cs_poi_bg.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		HSSFCellStyle cs_poi1_bg=wb.createCellStyle();
		cs_poi1_bg.setDataFormat(format.getFormat("#,###,0.0"));
		cs_poi1_bg.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi1_bg.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi1_bg.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi1_bg.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi1_bg.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi1_bg.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_poi1_bg.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		cs_poi1_bg.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		HSSFCellStyle cs_poi2_bg=wb.createCellStyle();
		cs_poi2_bg.setDataFormat(format.getFormat("#,###,0.00"));
		cs_poi2_bg.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi2_bg.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi2_bg.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi2_bg.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi2_bg.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi2_bg.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_poi2_bg.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		cs_poi2_bg.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		HSSFCellStyle cs_poi4_bg=wb.createCellStyle();
		cs_poi4_bg.setDataFormat(format.getFormat("#,###,0.0000"));
		cs_poi4_bg.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi4_bg.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi4_bg.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi4_bg.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi4_bg.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi4_bg.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_poi4_bg.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		cs_poi4_bg.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
							
		/**
		 * 獲取要循環的數據
		 */
		List<WebFact>list_factcode=webFactSer.findFactById_showA(factNo);				
		List<String>list_str=new ArrayList<String>();		
		List<String>list_unit=new ArrayList<String>();
		list_str.add("當月產量");list_unit.add("模");//0 (一位小數)
		list_str.add("月均回轉 ");list_unit.add("回");//1(整數)
		list_str.add("時回轉 ");list_unit.add("模/H");//2(整數)
		list_str.add("廠補率");  list_unit.add("%");
		list_str.add("產能達成率");list_unit.add("%");		
		list_str.add("成倉庫存");list_unit.add("雙");//5(整數)
		
		list_str.add("已出未請");list_unit.add("雙");//6(整數)
		list_str.add("生產與請款差異率");list_unit.add("%");
		list_str.add("全廠人均時產能");list_unit.add("模/H");
		list_str.add("直工人均产能");list_unit.add("模/人");//9(整數)
		list_str.add("全厂人均产能");list_unit.add("模/人");//10(整數)
		
		list_str.add("人均產值");list_unit.add("USD/人");
		list_str.add("人薪產值");list_unit.add("--");
		list_str.add("水用量单耗");list_unit.add("噸/模");//13(13至16取四位小數)
		list_str.add("电度数单耗");list_unit.add("度/模");
		list_str.add("蒸汽单耗");list_unit.add("噸/模");
		
		list_str.add("修繕單耗");list_unit.add("USD/模");//16
		list_str.add("主材料成本比率");list_unit.add("%");
		list_str.add("邊料率");list_unit.add("%");
		list_str.add("報廢率");list_unit.add("%");
		list_str.add("全廠總損耗");list_unit.add("%");
		
		list_str.add("無形損耗");list_unit.add("%");
		list_str.add("直間比");list_unit.add("--");
		list_str.add("工傷件數");list_unit.add("件");
		list_str.add("直工離職率");list_unit.add("%");
		list_str.add("全廠離職率");list_unit.add("%");
		
		
		
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
			//合計
			Double total_sumEverydemo=0.00;Double total_sumStandarddemo=0.00;
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
			Double total_instorenum=0.00;
						
			//中間集合,用於裝"合計"數據
			List<List<Double>>list2_total=new ArrayList<List<Double>>();
			
			//單元集合,合計
			List<Double>list3_total=new ArrayList<Double>();
			
			WebFact fact=list_factcode.get(a);
			String factCode=fact.getId().getFactArea();						
			for(int b=0;b<list_months.size();b++){//start for2
				String ym=list_months.get(b);
				VKpifactEve eve=vkpieveSer.findById(factNo, factCode,ym);
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
					/*if(eve.getOtherweight()==null){
						eve.setOtherweight(0.0);
					}*/
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
						total_sumEverydemo=total_sumEverydemo+eve.getSumEverydemo().doubleValue();total_sumStandarddemo=total_sumStandarddemo+eve.getSumStandarddemo().doubleValue();
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
						total_instorenum=total_instorenum+eve.getInstorenum();
																																			
				}// end if				
				//合計				
					if(b==list_months.size()-1){
						list3_total=this.findResult(b,
								total_sumEverydemo, total_sumStandarddemo, total_sumActualdemo, total_sumActualpairs, total_sumFactpairs,
								total_personzg, total_personjg, total_timezg, total_timejg, total_addtimezg, total_addtimejg, total_leavenumzg, total_leavenumjg, total_hurtnum,
								total_invcount, total_sellcount, total_costcount, total_wagezgUsd, total_wagejgUsd, total_cashcount,
								total_sideweit, total_badweit, total_otherbadweight,
								total_waterton, total_electricdu, total_gaston,
								total_storenum, total_outnum, total_minusnum,
								total_actlost, total_avgbuttomweight2,total_otherweight,
								total_productnum,total_noglueweight,total_repairmoney,
								total_sumWorkhours,total_instorenum);
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
			sheet.createRow(2+28*k).createCell(0).setCellValue("形態:"+factCode);
			HSSFRow row_columnHead=sheet.createRow(3+28*k);			
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
						HSSFRow row=sheet.createRow(4+j+28*k);
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
					list_content.add(kpi.getThisYield().doubleValue());//1
					list_content.add(kpi.getAvgCircle().doubleValue());//2
					list_content.add(kpi.getAvgCirclehour().doubleValue());
					list_content.add(kpi.getFactaddRate().doubleValue());//--null
					list_content.add(kpi.getProductRate().doubleValue());
					list_content.add(kpi.getStoreNum().doubleValue());//6
					list_content.add(kpi.getOutRequest().doubleValue());//7
					list_content.add(kpi.getOutrequestRate().doubleValue());//--null
					list_content.add(kpi.getAvgFactpro().doubleValue());
					list_content.add(kpi.getAvgZgpro().doubleValue());
					list_content.add(kpi.getAvgPerpro().doubleValue());
					list_content.add(kpi.getAvgPermoney().doubleValue());//--null
					list_content.add(kpi.getPermoney().doubleValue());//--null
					list_content.add(kpi.getWaterTon().doubleValue());
					list_content.add(kpi.getLightDu().doubleValue());
					list_content.add(kpi.getGasUsd().doubleValue());
					list_content.add(kpi.getWasteUsd().doubleValue());
					list_content.add(kpi.getMainRate().doubleValue());
					list_content.add(kpi.getSideRate().doubleValue());
					list_content.add(kpi.getWasteRate().doubleValue());
					list_content.add(kpi.getWasteFact().doubleValue());
					list_content.add(kpi.getWasteNo().doubleValue());
					list_content.add(kpi.getZjRate().doubleValue());
					list_content.add(kpi.getHurtNum().doubleValue());//24
					list_content.add(kpi.getZgleaveRate().doubleValue());
					list_content.add(kpi.getFactleaveRate().doubleValue());
				}//end if1
													
				/**
				 * 補全"合計"統計
				 */
				List<List<Double>> list2=list1_all.get(k);//中間集合,每個中間集合"合計"數據
				List<Double>list3_total=list2.get(0);//合計							
				if(kpi!=null){	
					for(int j=0;j<list_str.size();j++){
						HSSFRow row=sheet.getRow(4+j+28*k);
						HSSFCell cell=row.createCell(i+3+temp_num);
						HSSFCellStyle cs_temp=wb.createCellStyle();
						//數字格式的選擇
						if(j==0||j==2){
							cs_temp=cs_poi1;
						}else if(j==1||j==5||j==6||j==9||j==10){
							cs_temp=cs_poi;
						}else if(j>12&&j<17){
							cs_temp=cs_poi4;
						}else if(j>7&&j<13||j==22||j==23){
							cs_temp=cs_poi2;
						}else{
							cs_temp=cs_percent;
						}                     
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
						HSSFRow row=sheet.getRow(4+j+28*k);
						HSSFCell cell=row.createCell(i+3+temp_num);
						//數字格式的選擇
						HSSFCellStyle cs_temp=wb.createCellStyle();
						if(j==0||j==2){
							cs_temp=cs_poi1;
						}else if(j==1||j==5||j==6||j==9||j==10){
							cs_temp=cs_poi;
						}else if(j>12&&j<17){
							cs_temp=cs_poi4;
						}else if(j>7&&j<13||j==22||j==23){
							cs_temp=cs_poi2;
						}else{
							cs_temp=cs_percent;
						}
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
		//基本樣式
		HSSFCellStyle cs=wb.createCellStyle();
		cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);		
		//標題樣式
		HSSFCellStyle cs_head=wb.createCellStyle();
		HSSFFont font_head=wb.createFont();
		font_head.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_head.setFontHeightInPoints((short)20);
		cs_head.setFont(font_head);
		cs_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_head.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		
		//表頭樣式
		HSSFCellStyle cs_column=wb.createCellStyle();
		HSSFFont font_column=wb.createFont();
		font_column.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_column.setFontHeightInPoints((short)10);
		cs_column.setFont(font_column);
		cs_column.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_column.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_column.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_column.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_column.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_column.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		cs_column.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cs_column.setWrapText(true);
		
		//粗藍色字體		
		HSSFFont font_blue=wb.createFont();
		font_blue.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_blue.setFontHeightInPoints((short)12);
		font_blue.setColor(IndexedColors.BLUE.getIndex());
		
		/**
		 * 數字格式有背景與無背景
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
		
		HSSFCellStyle cs_poi=wb.createCellStyle();
		cs_poi.setDataFormat(format.getFormat("#,###,0"));
		cs_poi.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle cs_poi1=wb.createCellStyle();
		cs_poi1.setDataFormat(format.getFormat("#,###,0.0"));
		cs_poi1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle cs_poi2=wb.createCellStyle();
		cs_poi2.setDataFormat(format.getFormat("#,###,0.00"));
		cs_poi2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle cs_poi4=wb.createCellStyle();
		cs_poi4.setDataFormat(format.getFormat("#,###,0.0000"));
		cs_poi4.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi4.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		//有背景
		HSSFCellStyle cs_percent_bg=wb.createCellStyle();
		cs_percent_bg.setDataFormat(format.getFormat("0.00%"));
		cs_percent_bg.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_percent_bg.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_percent_bg.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_percent_bg.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_percent_bg.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_percent_bg.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_percent_bg.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		cs_percent_bg.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		HSSFCellStyle cs_poi_bg=wb.createCellStyle();
		cs_poi_bg.setDataFormat(format.getFormat("#,###,0"));
		cs_poi_bg.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi_bg.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi_bg.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi_bg.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi_bg.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi_bg.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_poi_bg.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		cs_poi_bg.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		HSSFCellStyle cs_poi1_bg=wb.createCellStyle();
		cs_poi1_bg.setDataFormat(format.getFormat("#,###,0.0"));
		cs_poi1_bg.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi1_bg.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi1_bg.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi1_bg.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi1_bg.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi1_bg.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_poi1_bg.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		cs_poi1_bg.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		HSSFCellStyle cs_poi2_bg=wb.createCellStyle();
		cs_poi2_bg.setDataFormat(format.getFormat("#,###,0.00"));
		cs_poi2_bg.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi2_bg.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi2_bg.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi2_bg.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi2_bg.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi2_bg.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_poi2_bg.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		cs_poi2_bg.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		HSSFCellStyle cs_poi4_bg=wb.createCellStyle();
		cs_poi4_bg.setDataFormat(format.getFormat("#,###,0.0000"));
		cs_poi4_bg.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi4_bg.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi4_bg.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi4_bg.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi4_bg.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi4_bg.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_poi4_bg.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		cs_poi4_bg.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
							
		/**
		 * 數據獲取
		 */
		//各項數據名稱和各項單位
		List<String>list_contentName=new ArrayList<String>();
		List<String>list_unit=new ArrayList<String>();
		list_contentName.add("當月產量");list_unit.add("模");
		list_contentName.add("月均回轉");list_unit.add("回");
		list_contentName.add("時迴轉");list_unit.add("模/H");
		list_contentName.add("廠補率");list_unit.add("%");
		list_contentName.add("產能達成率");list_unit.add("%");
		list_contentName.add("成倉庫存");list_unit.add("雙");

		list_contentName.add("已出未請");list_unit.add("雙");
		list_contentName.add("生產與請款差異率");list_unit.add("%");
		list_contentName.add("全廠人均時產能");list_unit.add("模/H");
		list_contentName.add("直工人均产能");list_unit.add("模/人");
		list_contentName.add("全厂人均产能");list_unit.add("模/人");
		
		list_contentName.add("人均產值");list_unit.add("USD/人");
		list_contentName.add("人薪產值");list_unit.add("--");
		list_contentName.add("水用量单耗");list_unit.add("噸/模");
		list_contentName.add("电度数单耗");list_unit.add("度/模");
		list_contentName.add("蒸汽单耗");list_unit.add("噸/模");
		
		list_contentName.add("修繕單耗");list_unit.add("USD/模");
		list_contentName.add("主材料成本比率");list_unit.add("%");
		list_contentName.add("邊料率");list_unit.add("%");
		list_contentName.add("報廢率");list_unit.add("%");
		list_contentName.add("全廠總損耗");list_unit.add("%");
		
		list_contentName.add("無形損耗");list_unit.add("%");
		list_contentName.add("直間比");list_unit.add("--");
		list_contentName.add("工傷件數");list_unit.add("件");
		list_contentName.add("直工離職率");list_unit.add("%");
		list_contentName.add("全廠離職率");list_unit.add("%");
	
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
			HSSFRow row_factCode=sheet.createRow(2+28*a);
			HSSFRow row_column=sheet.createRow(3+28*a);
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
					list_kpi.add(kpi.getThisYield().doubleValue());
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
					list_kpi.add(kpi.getFactleaveRate().doubleValue());
				}				
				for(int c=0;c<list_contentName.size();c++){//start for3
					HSSFRow row=null;
					if(b==0){
						row=sheet.createRow(4+c+28*a);
					}else{
						row=sheet.getRow(4+c+28*a);
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
						/*if(c==0||c==1||c==4||c==5||c==21||c==22||(c>6&&c<16)){
							cs_temp=cs_poi1;
						}else{
							cs_temp=cs_percent;
						}*/
						if(c==0||c==2){
							cs_temp=cs_poi1;
						}else if(c==1||c==5||c==6||c==9||c==10){
							cs_temp=cs_poi;
						}else if(c>12&&c<17){
							cs_temp=cs_poi4;
						}else if(c>7&&c<13||c==22||c==23){
							cs_temp=cs_poi2;
						}else{
							cs_temp=cs_percent;
						}
						if(kpi==null){
							cell_3.setCellValue("無數據");
						}else{
							Double kpi_content=list_kpi.get(c);
							cell_3.setCellValue(kpi_content);
						}
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
	public List<Double> findResult(int index,Double sumEverydemo, Double sumStandarddemo,
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
			Double instorenum) {
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
	
}
