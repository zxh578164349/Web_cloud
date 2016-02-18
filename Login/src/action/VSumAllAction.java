package action;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

import com.opensymphony.xwork2.ActionSupport;

import entity.*;

import services.*;

public class VSumAllAction extends ActionSupport implements ServletResponseAware{
	private IVSuminvServices suminvSer;
	private IVSumlossServices sumlossSer;
	private IVSumstoreServices sumstoreSer;
	private IVSumwloServices sumwlodSer;
	private IWebFactServices webFactSer;
	private String factNo;
	private String year;
	private String yymm;
	private String yymm_begin;
	private String yymm_end;
	private List<String>list_factno;//�ҿ諸factNo
	private javax.servlet.http.HttpServletResponse response;
	
	public String getFactNo() {
		return factNo;
	}
	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}
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
	
	public List<String> getList_factno() {
		return list_factno;
	}
	public void setList_factno(List<String> list_factno) {
		this.list_factno = list_factno;
	}
	public void setSuminvSer(IVSuminvServices suminvSer) {
		this.suminvSer = suminvSer;
	}
	public void setSumlossSer(IVSumlossServices sumlossSer) {
		this.sumlossSer = sumlossSer;
	}
	public void setSumstoreSer(IVSumstoreServices sumstoreSer) {
		this.sumstoreSer = sumstoreSer;
	}
	public void setSumwlodSer(IVSumwloServices sumwlodSer) {
		this.sumwlodSer = sumwlodSer;
	}
	
	
	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}
	public void print_fact() throws IOException, ParseException{
		
		/**
		 * 建立模板
		 */
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet();
		/**
		 * 各種樣式
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
		HSSFCellStyle cs_title=wb.createCellStyle();
		HSSFFont font_title=wb.createFont();
		font_title.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_title.setFontHeightInPoints((short)20);
		cs_title.setFont(font_title);
		cs_title.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_title.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
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
		//無邊框新式
		HSSFCellStyle cs_noborder=wb.createCellStyle();
		cs_noborder.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_noborder.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		//紅色
		HSSFCellStyle cs_red=wb.createCellStyle();
		HSSFFont font_red=wb.createFont();
		font_red.setColor(IndexedColors.RED.getIndex());
		cs_red.setFont(font_red);
		cs_red.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_red.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_red.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_red.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_red.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_red.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		//數字格式
		HSSFDataFormat fm=wb.createDataFormat();
		HSSFCellStyle cs_percent=wb.createCellStyle();
		cs_percent.setDataFormat(fm.getFormat("0.00%"));
		cs_percent.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_percent.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_percent.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle cs_poi2=wb.createCellStyle();
		cs_poi2.setDataFormat(fm.getFormat("#,###,0.00"));
		cs_poi2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle cs_poi4=wb.createCellStyle();
		cs_poi4.setDataFormat(fm.getFormat("#,###,0.0000"));
		cs_poi4.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi4.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderLeft(HSSFCellStyle.BORDER_THIN);
						
		
		/**
		 * 數據源
		 */			
		//big用於填充null
		BigDecimal big=new BigDecimal(0.0);
		//損益匯總
		List<String>list_contentName_loss=new ArrayList<String>();
		List<String>list_unit_loss=new ArrayList<String>();
		list_contentName_loss.add("總銷貨收入");list_unit_loss.add("USD");
		list_contentName_loss.add("－) 成本合計");list_unit_loss.add("USD");
		list_contentName_loss.add("－) 工資合計");list_unit_loss.add("USD");
		list_contentName_loss.add("－) 費用合計");list_unit_loss.add("USD");
		list_contentName_loss.add("＋) 其他");list_unit_loss.add("USD");
		list_contentName_loss.add("本期損益");list_unit_loss.add("USD");
		//全廠原料庫存
		List<String>list_contentName_store=new ArrayList<String>();
		List<String>list_unit_store=new ArrayList<String>();
		list_contentName_store.add("總原料庫存量");list_unit_store.add("KG");
		list_contentName_store.add("總原料庫存金額");list_unit_store.add("USD");
		list_contentName_store.add("平均單價");list_unit_store.add("USD");
		list_contentName_store.add("漲幅");list_unit_store.add("%");//3
		list_contentName_store.add("膠類庫存量");list_unit_store.add("KG");
		list_contentName_store.add("膠類庫存量");list_unit_store.add("噸");
		list_contentName_store.add("膠類金額");list_unit_store.add("USD");
		list_contentName_store.add("平均單價");list_unit_store.add("USD");
		list_contentName_store.add("漲幅");list_unit_store.add("%");//8
		
		//水電油
		List<String>list_contentName_wlo=new ArrayList<String>();
		List<String>list_unit_wlo=new ArrayList<String>();
		list_contentName_wlo.add("生產模數");list_unit_wlo.add("模");
		list_contentName_wlo.add("水用量");list_unit_wlo.add("噸");
		list_contentName_wlo.add("用水單秏");list_unit_wlo.add("噸/模");//2
		list_contentName_wlo.add("用水金額");list_unit_wlo.add("USD");
		list_contentName_wlo.add("用水金額單秏(模)");list_unit_wlo.add("USD/模");//4
		list_contentName_wlo.add("用電量");list_unit_wlo.add("度");
		list_contentName_wlo.add("用電單秏");list_unit_wlo.add("度/模");//6
		list_contentName_wlo.add("用電金額");list_unit_wlo.add("USD");
		list_contentName_wlo.add("用電金額單秏");list_unit_wlo.add("USD/模");//8
		list_contentName_wlo.add("蒸氣用量");list_unit_wlo.add("噸");
		list_contentName_wlo.add("用蒸氣單秏");list_unit_wlo.add("噸/模");//10
		list_contentName_wlo.add("用蒸氣金額(USD)");list_unit_wlo.add("USD");
		list_contentName_wlo.add("用蒸氣金額單秏");list_unit_wlo.add("USD/模");//12
		list_contentName_wlo.add("柴油用量");list_unit_wlo.add("公升");
		list_contentName_wlo.add("用油單秏");list_unit_wlo.add("公升/模");//14
		list_contentName_wlo.add("柴油金額(USD)");list_unit_wlo.add("USD");
		list_contentName_wlo.add("用油金額單秏(模)");list_unit_wlo.add("USD/模");//16
		//下月請款狀況,下月生產狀況
		List<String>list_contentName_inv=new ArrayList<String>();
		List<String>list_unit_inv=new ArrayList<String>();
		list_contentName_inv.add("預計請款");list_unit_inv.add("雙");
		list_contentName_inv.add("請款金額");list_unit_inv.add("USD");
		list_contentName_inv.add("平均單價");list_unit_inv.add("USD/雙");
		list_contentName_inv.add("預計生產");list_unit_inv.add("模");
		list_contentName_inv.add("機臺標准戰力");list_unit_inv.add("模");
		list_contentName_inv.add("機孔達成率");list_unit_inv.add("%");//5
		
		
		//表頭內容
		List<String>list_column=new ArrayList<String>();
		list_column.add("項目");
		list_column.add("細項");
		list_column.add("單位");
		list_column.add("1月");
		list_column.add("2月");
		list_column.add("3月");
		list_column.add("Q1");//6
		list_column.add("4月");
		list_column.add("5月");
		list_column.add("6月");
		list_column.add("Q2");//10
		list_column.add("上半年");//11
		list_column.add("7月");
		list_column.add("8月");
		list_column.add("9月");
		list_column.add("Q3");//15
		list_column.add("10月");
		list_column.add("11月");
		list_column.add("12月");
		list_column.add("Q4");//19
		list_column.add("下半年");//20
		list_column.add("全年");//21
		//主要數據
		//最外層集合
		List<List<Double>>list_loss_all=new ArrayList<List<Double>>();
		List<List<Double>>list_store_all=new ArrayList<List<Double>>();
		List<List<Double>>list_wlo_all=new ArrayList<List<Double>>();
		List<List<Double>>list_inv_all=new ArrayList<List<Double>>();
		//注意,因為有"項目""細項""單位",所以要添加為空
		list_loss_all.add(null);
		list_loss_all.add(null);
		list_loss_all.add(null);
		
		list_store_all.add(null);
		list_store_all.add(null);
		list_store_all.add(null);
		
		list_wlo_all.add(null);
		list_wlo_all.add(null);
		list_wlo_all.add(null);
		
		list_inv_all.add(null);
		list_inv_all.add(null);
		list_inv_all.add(null);
		
	    ////損益彙總(v_sumloss)
		//第一季度
		Double q1_a001=0.0;Double q1_a002=0.0;
		Double q1_a003=0.0;Double q1_a004=0.0;
		Double q1_a005=0.0;
		//第二季度
		Double q2_a001=0.0;Double q2_a002=0.0;
		Double q2_a003=0.0;Double q2_a004=0.0;
		Double q2_a005=0.0;
		//第三季度
		Double q3_a001=0.0;Double q3_a002=0.0;
		Double q3_a003=0.0;Double q3_a004=0.0;
		Double q3_a005=0.0;
		//第四季度
		Double q4_a001=0.0;Double q4_a002=0.0;
		Double q4_a003=0.0;Double q4_a004=0.0;
		Double q4_a005=0.0;
		//上半年
		Double half1_a001=0.0;Double half1_a002=0.0;
		Double half1_a003=0.0;Double half1_a004=0.0;
		Double half1_a005=0.0;
		//下半年
		Double half2_a001=0.0;Double half2_a002=0.0;
		Double half2_a003=0.0;Double half2_a004=0.0;
		Double half2_a005=0.0;
		//全年
		Double year_a001=0.0;Double year_a002=0.0;
		Double year_a003=0.0;Double year_a004=0.0;
		Double year_a005=0.0;
		
		//全廠原料庫存(v_sumstore)
		Double q1_b001=0.0;Double q1_b002=0.0;
		Double q1_b005=0.0;Double q1_b006=0.0;
		Double q1_b007=0.0;
		//第二季度
		Double q2_b001=0.0;Double q2_b002=0.0;
		Double q2_b005=0.0;Double q2_b006=0.0;
		Double q2_b007=0.0;
		//第三季度
		Double q3_b001=0.0;Double q3_b002=0.0;
		Double q3_b005=0.0;Double q3_b006=0.0;
		Double q3_b007=0.0;
		//第四季度
		Double q4_b001=0.0;Double q4_b002=0.0;
		Double q4_b005=0.0;Double q4_b006=0.0;
		Double q4_b007=0.0;
		//上半年
		Double half1_b001=0.0;Double half1_b002=0.0;
		Double half1_b005=0.0;Double half1_b006=0.0;
		Double half1_b007=0.0;
		//下半年
		Double half2_b001=0.0;Double half2_b002=0.0;
		Double half2_b005=0.0;Double half2_b006=0.0;
		Double half2_b007=0.0;
		//全年
		Double year_b001=0.0;Double year_b002=0.0;
		Double year_b005=0.0;Double year_b006=0.0;
		Double year_b007=0.0;
		
		//上月平均單價
		Double lag_b003=0.0;
		//上月平均單價(膠)
		Double lag_b008=0.0;
		//當月平均單價
		Double b003=0.0;
		//當月平均單價(膠)
		Double b008=0.0;
		//漲福1
		Double store_b004=0.0;		
		//漲福2
		Double store_b009=0.0;
		
		//水電油(v_sumwlo)
		//第一季度
		Double q1_c001=0.0;Double q1_c002=0.0;
		Double q1_c004=0.0;Double q1_c006=0.0;
		Double q1_c008=0.0;Double q1_c010=0.0;
		Double q1_c012=0.0;Double q1_c014=0.0;
		Double q1_c016=0.0;
		//第二季度
		Double q2_c001=0.0;Double q2_c002=0.0;
		Double q2_c004=0.0;Double q2_c006=0.0;
		Double q2_c008=0.0;Double q2_c010=0.0;
		Double q2_c012=0.0;Double q2_c014=0.0;
		Double q2_c016=0.0;
		//第三季度
		Double q3_c001=0.0;Double q3_c002=0.0;
		Double q3_c004=0.0;Double q3_c006=0.0;
		Double q3_c008=0.0;Double q3_c010=0.0;
		Double q3_c012=0.0;Double q3_c014=0.0;
		Double q3_c016=0.0;
		
		//第四季度
		Double q4_c001=0.0;Double q4_c002=0.0;
		Double q4_c004=0.0;Double q4_c006=0.0;
		Double q4_c008=0.0;Double q4_c010=0.0;
		Double q4_c012=0.0;Double q4_c014=0.0;
		Double q4_c016=0.0;
		//上半年
		Double half1_c001=0.0;Double half1_c002=0.0;
		Double half1_c004=0.0;Double half1_c006=0.0;
		Double half1_c008=0.0;Double half1_c010=0.0;
		Double half1_c012=0.0;Double half1_c014=0.0;
		Double half1_c016=0.0;
		//下半年
		Double half2_c001=0.0;Double half2_c002=0.0;
		Double half2_c004=0.0;Double half2_c006=0.0;
		Double half2_c008=0.0;Double half2_c010=0.0;
		Double half2_c012=0.0;Double half2_c014=0.0;
		Double half2_c016=0.0;
		//全年
		Double year_c001=0.0;Double year_c002=0.0;
		Double year_c004=0.0;Double year_c006=0.0;
		Double year_c008=0.0;Double year_c010=0.0;
		Double year_c012=0.0;Double year_c014=0.0;
		Double year_c016=0.0;
		//下月請款狀況,下月生產狀況
		//第一季度
		Double q1_d001=0.0;Double q1_d002=0.0;
		Double q1_d004=0.0;Double q1_d005=0.0;
		//第二季度
		Double q2_d001=0.0;Double q2_d002=0.0;
		Double q2_d004=0.0;Double q2_d005=0.0;
		//第三季度
		Double q3_d001=0.0;Double q3_d002=0.0;
		Double q3_d004=0.0;Double q3_d005=0.0;
		//第四季度
		Double q4_d001=0.0;Double q4_d002=0.0;
		Double q4_d004=0.0;Double q4_d005=0.0;
		//上半年
		Double half1_d001=0.0;Double half1_d002=0.0;
		Double half1_d004=0.0;Double half1_d005=0.0;
		//下半年
		Double half2_d001=0.0;Double half2_d002=0.0;
		Double half2_d004=0.0;Double half2_d005=0.0;
		//全年
		Double year_d001=0.0;Double year_d002=0.0;
		Double year_d004=0.0;Double year_d005=0.0;
		
		
		for(int a=1;a<13;a++){//start for1
			String month="";
			if(a<10){
				month="0"+a;
			}else{
				month=""+a;
			}
			
			/**
			 * 獲取上個月日期
			 */
			SimpleDateFormat format=new SimpleDateFormat("yyyyMM");
			Date date=format.parse(year+month);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, -1);
			String lastdate=format.format(calendar.getTime());
			//System.out.print(lastdate);
			//月
			List<Double>list_loss=new ArrayList<Double>();
			List<Double>list_store=new ArrayList<Double>();
			List<Double>list_wlo=new ArrayList<Double>();
			List<Double>list_inv=new ArrayList<Double>();
			//季度
			List<Double>list_loss_q=new ArrayList<Double>();
			List<Double>list_store_q=new ArrayList<Double>();
			List<Double>list_wlo_q=new ArrayList<Double>();
			List<Double>list_inv_q=new ArrayList<Double>();
			//半年
			List<Double>list_loss_h=new ArrayList<Double>();
			List<Double>list_store_h=new ArrayList<Double>();
			List<Double>list_wlo_h=new ArrayList<Double>();
			List<Double>list_inv_h=new ArrayList<Double>();
			//全年
			List<Double>list_loss_y=new ArrayList<Double>();
			List<Double>list_store_y=new ArrayList<Double>();
			List<Double>list_wlo_y=new ArrayList<Double>();
			List<Double>list_inv_y=new ArrayList<Double>();
			VSumloss loss=sumlossSer.findById(factNo,year+month);
			VSumstore store=sumstoreSer.findById(factNo, year+month);
			VSumwlo wlo=sumwlodSer.findById(factNo, year+month);
			VSuminv inv=suminvSer.findById(factNo, year+month);
			
			//
			//上個月store
			VSumstore store_last=sumstoreSer.findById(factNo, lastdate);
			//損益彙總(v_sumloss)
			if(loss!=null){
				list_loss.add(loss.getLossA001().doubleValue());
				list_loss.add(loss.getLossA002().doubleValue());
				list_loss.add(loss.getLossA003().doubleValue());
				list_loss.add(loss.getLossA004().doubleValue());
				list_loss.add(loss.getLossA005().doubleValue());
				list_loss.add(loss.getLossA006().doubleValue());
				list_loss_all.add(list_loss);
				//第一季度
				if(a<4){
					q1_a001=q1_a001+loss.getLossA001().doubleValue();
					q1_a002=q1_a002+loss.getLossA002().doubleValue();
					q1_a003=q1_a003+loss.getLossA003().doubleValue();
					q1_a004=q1_a004+loss.getLossA004().doubleValue();
					q1_a005=q1_a005+loss.getLossA005().doubleValue();
				}
				//第二季度,上半年
				if(a>3&&a<7){
					q2_a001=q2_a001+loss.getLossA001().doubleValue();
					q2_a002=q2_a002+loss.getLossA002().doubleValue();
					q2_a003=q2_a003+loss.getLossA003().doubleValue();
					q2_a004=q2_a004+loss.getLossA004().doubleValue();
					q2_a005=q2_a005+loss.getLossA005().doubleValue();					
				}
				//第三季度
				if(a>6&&a<10){
					q3_a001=q3_a001+loss.getLossA001().doubleValue();
					q3_a002=q3_a002+loss.getLossA002().doubleValue();
					q3_a003=q3_a003+loss.getLossA003().doubleValue();
					q3_a004=q3_a004+loss.getLossA004().doubleValue();
					q3_a005=q3_a005+loss.getLossA005().doubleValue();
				}
				//第四季度,下半年,全年
				if(a>9){
					q4_a001=q4_a001+loss.getLossA001().doubleValue();
					q4_a002=q4_a002+loss.getLossA002().doubleValue();
					q4_a003=q4_a003+loss.getLossA003().doubleValue();
					q4_a004=q4_a004+loss.getLossA004().doubleValue();
					q4_a005=q4_a005+loss.getLossA005().doubleValue();					
				}								
			}else{
				list_loss_all.add(null);
			}
			//全廠原料庫存(v_sumstore)
			//找到上月平均單價
			if(store_last!=null){
				lag_b003=store_last.getStoreB003().doubleValue();
				lag_b008=store_last.getStoreB008().doubleValue();
			}
			//找到當月平均單價
			if(store!=null){
				b003=store.getStoreB003().doubleValue();
				b008=store.getStoreB008().doubleValue();
			}
			//漲福1
			store_b004=this.division((b003-lag_b003), lag_b003);			
			//漲福2
			store_b009=this.division((b008-lag_b008), lag_b008);
			if(store!=null){								
				list_store.add(store.getStoreB001().doubleValue());
				list_store.add(store.getStoreB002().doubleValue());
				list_store.add(store.getStoreB003().doubleValue());
				list_store.add(store_b004);
				list_store.add(store.getStoreB005().doubleValue());
				list_store.add(store.getStoreB006().doubleValue());
				list_store.add(store.getStoreB007().doubleValue());
				list_store.add(store.getStoreB008().doubleValue());
				list_store.add(store_b009);				
				list_store_all.add(list_store);
				//第一季度
				if(a<4){
					q1_b001=q1_b001+store.getStoreB001().doubleValue();
					q1_b002=q1_b002+store.getStoreB002().doubleValue();
					q1_b005=q1_b005+store.getStoreB005().doubleValue();
					q1_b006=q1_b006+store.getStoreB006().doubleValue();
					q1_b007=q1_b007+store.getStoreB007().doubleValue();
				}
				//第二季度,上半年
				if(a>3&&a<7){
					q2_b001=q2_b001+store.getStoreB001().doubleValue();
					q2_b002=q2_b002+store.getStoreB002().doubleValue();
					q2_b005=q2_b005+store.getStoreB005().doubleValue();
					q2_b006=q2_b006+store.getStoreB006().doubleValue();
					q2_b007=q2_b007+store.getStoreB007().doubleValue();					
				}
				//第三季度
				if(a>6&&a<10){
					q3_b001=q3_b001+store.getStoreB001().doubleValue();
					q3_b002=q3_b002+store.getStoreB002().doubleValue();
					q3_b005=q3_b005+store.getStoreB005().doubleValue();
					q3_b006=q3_b006+store.getStoreB006().doubleValue();
					q3_b007=q3_b007+store.getStoreB007().doubleValue();
				}
				//第四季度,下半年,全年
				if(a>9){
					q4_b001=q4_b001+store.getStoreB001().doubleValue();
					q4_b002=q4_b002+store.getStoreB002().doubleValue();
					q4_b005=q4_b005+store.getStoreB005().doubleValue();
					q4_b006=q4_b006+store.getStoreB006().doubleValue();
					q4_b007=q4_b007+store.getStoreB007().doubleValue();										
				}
			}else{
				list_store_all.add(null);
			}
			
			//水電油(v_sumwlo)
			if(wlo!=null){
				if(wlo.getWloC002()==null){
					wlo.setWloC002(big);
				}
				if(wlo.getWloC003()==null){
					wlo.setWloC003(big);
				}
				if(wlo.getWloC004()==null){
					wlo.setWloC004(big);
				}
				if(wlo.getWloC005()==null){
					wlo.setWloC005(big);
				}
				if(wlo.getWloC006()==null){
					wlo.setWloC006(big);
				}
				if(wlo.getWloC007()==null){
					wlo.setWloC007(big);
				}
				if(wlo.getWloC008()==null){
					wlo.setWloC008(big);
				}
				if(wlo.getWloC009()==null){
					wlo.setWloC009(big);
				}
				if(wlo.getWloC010()==null){
					wlo.setWloC010(big);
				}
				if(wlo.getWloC011()==null){
					wlo.setWloC011(big);
				}
				if(wlo.getWloC012()==null){
					wlo.setWloC012(big);
				}
				if(wlo.getWloC013()==null){
					wlo.setWloC013(big);
				}
				if(wlo.getWloC014()==null){
					wlo.setWloC014(big);
				}
				if(wlo.getWloC015()==null){
					wlo.setWloC015(big);
				}
				if(wlo.getWloC016()==null){
					wlo.setWloC016(big);
				}
				if(wlo.getWloC017()==null){
					wlo.setWloC017(big);
				}
				list_wlo.add(wlo.getWloC001().doubleValue());
				list_wlo.add(wlo.getWloC002().doubleValue());
				list_wlo.add(wlo.getWloC003().doubleValue());
				list_wlo.add(wlo.getWloC004().doubleValue());
				list_wlo.add(wlo.getWloC005().doubleValue());
				list_wlo.add(wlo.getWloC006().doubleValue());
				list_wlo.add(wlo.getWloC007().doubleValue());
				list_wlo.add(wlo.getWloC008().doubleValue());
				list_wlo.add(wlo.getWloC009().doubleValue());
				list_wlo.add(wlo.getWloC010().doubleValue());
				list_wlo.add(wlo.getWloC011().doubleValue());
				list_wlo.add(wlo.getWloC012().doubleValue());
				list_wlo.add(wlo.getWloC013().doubleValue());
				list_wlo.add(wlo.getWloC014().doubleValue());
				list_wlo.add(wlo.getWloC015().doubleValue());
				list_wlo.add(wlo.getWloC016().doubleValue());
				list_wlo.add(wlo.getWloC017().doubleValue());
				list_wlo_all.add(list_wlo);
				//第一季度
				if(a<4){
					q1_c001=q1_c001+wlo.getWloC001().doubleValue();
					q1_c002=q1_c002+wlo.getWloC002().doubleValue();
					q1_c004=q1_c004+wlo.getWloC004().doubleValue();
					q1_c006=q1_c006+wlo.getWloC006().doubleValue();
					q1_c008=q1_c008+wlo.getWloC008().doubleValue();
					q1_c010=q1_c010+wlo.getWloC010().doubleValue();
					q1_c012=q1_c012+wlo.getWloC012().doubleValue();
					q1_c014=q1_c014+wlo.getWloC014().doubleValue();
					q1_c016=q1_c016+wlo.getWloC016().doubleValue();
				}
				//第二季度,上半年
				if(a>3&&a<7){
					q2_c001=q2_c001+wlo.getWloC001().doubleValue();
					q2_c002=q2_c002+wlo.getWloC002().doubleValue();
					q2_c004=q2_c004+wlo.getWloC004().doubleValue();
					q2_c006=q2_c006+wlo.getWloC006().doubleValue();
					q2_c008=q2_c008+wlo.getWloC008().doubleValue();
					q2_c010=q2_c010+wlo.getWloC010().doubleValue();
					q2_c012=q2_c012+wlo.getWloC012().doubleValue();
					q2_c014=q2_c014+wlo.getWloC014().doubleValue();
					q2_c016=q2_c016+wlo.getWloC016().doubleValue();										
				}
				//第三季度
				if(a>6&&a<10){
					q3_c001=q3_c001+wlo.getWloC001().doubleValue();
					q3_c002=q3_c002+wlo.getWloC002().doubleValue();
					q3_c004=q3_c004+wlo.getWloC004().doubleValue();
					q3_c006=q3_c006+wlo.getWloC006().doubleValue();
					q3_c008=q3_c008+wlo.getWloC008().doubleValue();
					q3_c010=q3_c010+wlo.getWloC010().doubleValue();
					q3_c012=q3_c012+wlo.getWloC012().doubleValue();
					q3_c014=q3_c014+wlo.getWloC014().doubleValue();
					q3_c016=q3_c016+wlo.getWloC016().doubleValue();
				}
				//第四季度,下半年,全年
				if(a>9){
					q4_c001=q4_c001+wlo.getWloC001().doubleValue();
					q4_c002=q4_c002+wlo.getWloC002().doubleValue();
					q4_c004=q4_c004+wlo.getWloC004().doubleValue();
					q4_c006=q4_c006+wlo.getWloC006().doubleValue();
					q4_c008=q4_c008+wlo.getWloC008().doubleValue();
					q4_c010=q4_c010+wlo.getWloC010().doubleValue();
					q4_c012=q4_c012+wlo.getWloC012().doubleValue();
					q4_c014=q4_c014+wlo.getWloC014().doubleValue();
					q4_c016=q4_c016+wlo.getWloC016().doubleValue();					
				}
			}else{
				list_wlo_all.add(null);
			}
			//下月請款狀況,下月生產狀況
			if(inv!=null){
				if(inv.getInvD001()==null){
					inv.setInvD001(big);
				}
				if(inv.getInvD002()==null){
					inv.setInvD002(big);
				}
				if(inv.getInvD003()==null){
					inv.setInvD003(big);
				}
				if(inv.getInvD004()==null){
					inv.setInvD004(big);
				}
				if(inv.getInvD005()==null){
					inv.setInvD005(big);
				}
				if(inv.getInvD006()==null){
					inv.setInvD006(big);
				}
				list_inv.add(inv.getInvD001().doubleValue());
				list_inv.add(inv.getInvD002().doubleValue());
				list_inv.add(inv.getInvD003().doubleValue());
				list_inv.add(inv.getInvD004().doubleValue());
				list_inv.add(inv.getInvD005().doubleValue());
				list_inv.add(inv.getInvD006().doubleValue());
				list_inv_all.add(list_inv);
				//第一季度
				if(a<4){
					q1_d001=q1_d001+inv.getInvD001().doubleValue();
					q1_d002=q1_d002+inv.getInvD002().doubleValue();
					q1_d004=q1_d004+inv.getInvD004().doubleValue();
					q1_d005=q1_d005+inv.getInvD005().doubleValue();
				}
				//第二季度,上半年
				if(a>3&&a<7){
					q2_d001=q2_d001+inv.getInvD001().doubleValue();
					q2_d002=q2_d002+inv.getInvD002().doubleValue();
					q2_d004=q2_d004+inv.getInvD004().doubleValue();
					q2_d005=q2_d005+inv.getInvD005().doubleValue();					
				}
				//第三季度
				if(a>6&&a<10){
					q3_d001=q3_d001+inv.getInvD001().doubleValue();
					q3_d002=q3_d002+inv.getInvD002().doubleValue();
					q3_d004=q3_d004+inv.getInvD004().doubleValue();
					q3_d005=q3_d005+inv.getInvD005().doubleValue();
				}
				//第四季度,下半年,全年
				if(a>9){
					q4_d001=q4_d001+inv.getInvD001().doubleValue();
					q4_d002=q4_d002+inv.getInvD002().doubleValue();
					q4_d004=q4_d004+inv.getInvD004().doubleValue();
					q4_d005=q4_d005+inv.getInvD005().doubleValue();										
				}
			}else{
				list_inv_all.add(null);				
			}			
			//第一季度					
			if(a==3){
				//損益彙總(v_sumloss)				
				list_loss_q=this.findResult_loss(3, q1_a001, q1_a002, q1_a003, q1_a004, q1_a005);
				//全廠原料庫存(v_sumstore)
				list_store_q=this.findResult_store(3, q1_b001, q1_b002, q1_b005,q1_b006, q1_b007);
				//水電油(v_sumwlo)
				list_wlo_q=this.findResult_wlo(3, q1_c001, q1_c002, q1_c004, q1_c006, q1_c008, q1_c010, q1_c012, q1_c014, q1_c016);
				//下月請款狀況,下月生產狀況
				list_inv_q=this.findResult_inv(3, q1_d001, q1_d002, q3_d004, q3_d005);
				
				list_loss_all.add(list_loss_q);
				list_store_all.add(list_store_q);
				list_wlo_all.add(list_wlo_q);
				list_inv_all.add(list_inv_q);
			}
			//第二季度,上半年
			if(a==6){
				//損益彙總(v_sumloss)
				half1_a001=q1_a001+q2_a001;
				half1_a002=q1_a002+q2_a002;
				half1_a003=q1_a003+q2_a003;
				half1_a004=q1_a004+q2_a004;
				half1_a005=q1_a005+q2_a005;
				list_loss_q=this.findResult_loss(3, q2_a001, q2_a002, q2_a003, q2_a004, q2_a005);
				list_loss_h=this.findResult_loss(6, half1_a001, half1_a002, half1_a003, half1_a004, half1_a005);
				//全廠原料庫存(v_sumstore)
				half1_b001=q1_b001+q2_b001;
				half1_b002=q1_b002+q2_b002;
				half1_b005=q1_b005+q2_b005;
				half1_b006=q1_b006+q2_b006;
				half1_b007=q1_b007+q2_b007;
				list_store_q=this.findResult_store(3, q2_b001, q2_b002, q2_b005,q2_b006, q2_b007);
				list_store_h=this.findResult_store(6, half1_b001, half1_b002, half1_b005,half1_b006, half1_b007);
				//水電油(v_sumwlo)
				half1_c001=q1_c001+q2_c001;
				half1_c002=q1_c002+q2_c002;
				half1_c004=q1_c004+q2_c004;
				half1_c006=q1_c006+q2_c006;
				half1_c008=q1_c008+q2_c008;
				half1_c010=q1_c010+q2_c010;
				half1_c012=q1_c012+q2_c012;
				half1_c014=q1_c014+q2_c014;
				half1_c016=q1_c016+q2_c016;
				list_wlo_q=this.findResult_wlo(3, q2_c001, q2_c002, q2_c004, q2_c006, q2_c008, q2_c010, q2_c012, q2_c014, q2_c016);
				list_wlo_h=this.findResult_wlo(6, half1_c001, half1_c002, half1_c004, half1_c006, half1_c008, half1_c010, half1_c012, half1_c014, half1_c016);
				//下月請款狀況,下月生產狀況
				half1_d001=q1_d001+q2_d001;
				half1_d002=q1_d002+q2_d002;
				half1_d004=q1_d004+q2_d004;
				half1_d005=q1_d005+q2_d005;
				list_inv_q=this.findResult_inv(3, q2_d001, q2_d002, q3_d004, q3_d005);
				list_inv_h=this.findResult_inv(6, half1_d001, half1_d002, half1_d004, half1_d005);
				
				list_loss_all.add(list_loss_q);
				list_loss_all.add(list_loss_h);
				
				list_store_all.add(list_store_q);
				list_store_all.add(list_store_h);
				
				list_wlo_all.add(list_wlo_q);
				list_wlo_all.add(list_wlo_h);
				
				list_inv_all.add(list_inv_q);
				list_inv_all.add(list_inv_h);
			}
			//第三季度
			if(a==9){
				//損益彙總(v_sumloss)				
				list_loss_q=this.findResult_loss(3, q3_a001, q3_a002, q3_a003, q3_a004, q3_a005);
				//全廠原料庫存(v_sumstore)
				list_store_q=this.findResult_store(3, q3_b001, q3_b002, q3_b005,q3_b006, q3_b007);
				//水電油(v_sumwlo)
				list_wlo_q=this.findResult_wlo(3, q3_c001, q3_c002, q3_c004, q3_c006, q3_c008, q3_c010, q3_c012, q3_c014, q3_c016);
				//下月請款狀況,下月生產狀況
				list_inv_q=this.findResult_inv(3, q3_d001, q3_d002, q3_d004, q3_d005);
				list_loss_all.add(list_loss_q);
				list_store_all.add(list_store_q);
				list_wlo_all.add(list_wlo_q);
				list_inv_all.add(list_inv_q);
				
			}
			//第四季度,下半年,全年
			if(a==12){
				//損益彙總(v_sumloss)
				half2_a001=q3_a001+q4_a001;
				half2_a002=q3_a002+q4_a002;
				half2_a003=q3_a003+q4_a003;
				half2_a004=q3_a004+q4_a004;
				half2_a005=q3_a005+q4_a005;
				
				year_a001=half1_a001+half2_a001;
				year_a002=half1_a002+half2_a002;
				year_a003=half1_a003+half2_a003;
				year_a004=half1_a004+half2_a004;
				year_a005=half1_a005+half2_a005;
				list_loss_q=this.findResult_loss(3, q4_a001, q4_a002, q4_a003, q4_a004, q4_a005);
				list_loss_h=this.findResult_loss(6, half2_a001, half2_a002, half2_a003, half2_a004, half2_a005);
				list_loss_y=this.findResult_loss(12, year_a001, year_a002, year_a003, year_a004, year_a005);
				//全廠原料庫存(v_sumstore)
				half2_b001=q3_b001+q4_b001;
				half2_b002=q3_b002+q4_b002;
				half2_b005=q3_b005+q4_b005;
				half2_b006=q3_b006+q4_b006;
				half2_b007=q3_b007+q4_b007;
				
				year_b001=half1_b001+half2_b001;
				year_b002=half1_b002+half2_b002;
				year_b005=half1_b005+half2_b005;
				year_b006=half1_b006+half2_b006;
				year_b007=half1_b007+half2_b007;
				list_store_q=this.findResult_store(3, q4_b001, q4_b002, q4_b005,q4_b006, q4_b007);
				list_store_h=this.findResult_store(6, half2_b001, half2_b002, half2_b005,half2_b006, half2_b007);
				list_store_y=this.findResult_store(12, year_b001, year_b002, year_b005,year_b006, year_b007);
				//水電油(v_sumwlo)
				half2_c001=q3_c001+q4_c001;
				half2_c002=q3_c002+q4_c002;
				half2_c004=q3_c004+q4_c004;
				half2_c006=q3_c006+q4_c006;
				half2_c008=q3_c008+q4_c008;
				half2_c010=q3_c010+q4_c010;
				half2_c012=q3_c012+q4_c012;
				half2_c014=q3_c014+q4_c014;
				half2_c016=q3_c016+q4_c016;
				
				year_c001=half1_c001+half2_c001;
				year_c002=half1_c002+half2_c002;
				year_c004=half1_c004+half2_c004;
				year_c006=half1_c006+half2_c006;
				year_c008=half1_c008+half2_c008;
				year_c010=half1_c010+half2_c010;
				year_c012=half1_c012+half2_c012;
				year_c014=half1_c014+half2_c014;
				year_c016=half1_c016+half2_c016;
				list_wlo_q=this.findResult_wlo(3, q4_c001, q4_c002, q4_c004, q4_c006, q4_c008, q4_c010, q4_c012, q4_c014, q4_c016);
				list_wlo_h=this.findResult_wlo(6, half2_c001, half2_c002, half2_c004, half2_c006, half2_c008, half2_c010, half2_c012, half2_c014, half2_c016);
				list_wlo_y=this.findResult_wlo(12, year_c001, year_c002, year_c004, year_c006, year_c008, year_c010, year_c012, year_c014, year_c016);
				//下月請款狀況,下月生產狀況
				half2_d001=q3_d001+q4_d001;
				half2_d002=q3_d002+q4_d002;
				half2_d004=q3_d004+q4_d004;
				half2_d005=q3_d005+q4_d005;
				
				year_d001=half1_d001+half2_d001;
				year_d002=half1_d002+half2_d002;
				year_d004=half1_d004+half2_d004;
				year_d005=half1_d005+half2_d005;
				list_inv_q=this.findResult_inv(3, q4_d001, q4_d002, q3_d004, q3_d005);
				list_inv_h=this.findResult_inv(6, half2_d001, half2_d002, half2_d004, half2_d005);
				list_inv_y=this.findResult_inv(12, year_d001, year_d002, year_d004, year_d005);
				
				list_loss_all.add(list_loss_q);
				list_loss_all.add(list_loss_h);
				list_loss_all.add(list_loss_y);
				
				list_store_all.add(list_store_q);
				list_store_all.add(list_store_h);
				list_store_all.add(list_store_y);
				
				list_wlo_all.add(list_wlo_q);
				list_wlo_all.add(list_wlo_h);
				list_wlo_all.add(list_wlo_y);
				
				list_inv_all.add(list_inv_q);
				list_inv_all.add(list_inv_h);
				list_inv_all.add(list_inv_y);
				
			}
			//全廠原料庫存(v_sumstore)
			
			
		}//end for1
		
		//標題
		String factName=webFactSer.selByid(factNo);
		String title=factName+"("+factNo+")"+year+"每月檢討項目統計表---全廠(各型態之合計)";
		
		
		/**
		 * 內容填充
		 */
		//填充標題
		HSSFRow row_title=sheet.createRow(0);
		HSSFCell cell_title=row_title.createCell(0);
		cell_title.setCellValue(title);
		cell_title.setCellStyle(cs_title);
		CellRangeAddress cra_title=new CellRangeAddress(0,(short)0,0,(short)list_column.size()-1);
		sheet.addMergedRegion(cra_title);
		for(int a=1;a<list_column.size();a++){
			HSSFCell cell=row_title.createCell(a);
			cell.setCellStyle(cs_title);
		}
		//填充表頭
		HSSFRow row_column=sheet.createRow(2);
		for(int b=0;b<list_column.size();b++){
			String column=list_column.get(b);
			HSSFCell cell=row_column.createCell(b);
			cell.setCellValue(column);
			cell.setCellStyle(cs_column);
		}
		//填充項目,細項,單位
		int length1=list_contentName_loss.size();
		int length2=list_contentName_store.size();
		int length3=list_contentName_wlo.size();
		int length4=list_contentName_inv.size();
		for(int b=0;b<list_column.size();b++){//start for1
			sheet.setColumnWidth(b, 3500);
			switch(b){
			case 0:
				//損益彙總(v_sumloss)				
				HSSFRow row_loss=sheet.createRow(3);
				HSSFCell cell_loss=row_loss.createCell(b);
				cell_loss.setCellValue("損益彙總");
				cell_loss.setCellStyle(cs);
				CellRangeAddress cra_loss=new CellRangeAddress(3,(short)(3+length1-1),0,(short)0);
				sheet.addMergedRegion(cra_loss);
				for(int c=1;c<length1;c++){
					HSSFRow row=sheet.createRow(3+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellStyle(cs);					
				}
				//全廠原料庫存(v_sumstore)
				HSSFRow row_store=sheet.createRow(3+length1);
				HSSFCell cell_store=row_store.createCell(b);
				cell_store.setCellValue("全廠原料庫存");
				cell_store.setCellStyle(cs);
				CellRangeAddress cra_store=new CellRangeAddress((3+length1),(short)(3+length1+length2-1),0,(short)0);
				sheet.addMergedRegion(cra_store);
				for(int c=1;c<length2;c++){
					HSSFRow row=sheet.createRow(3+length1+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellStyle(cs);					
				}
				//水電油(v_sumwlo)
				HSSFRow row_wlo=sheet.createRow(3+length1+length2);
				HSSFCell cell_wlo=row_wlo.createCell(b);
				cell_wlo.setCellValue("水電油");
				cell_wlo.setCellStyle(cs);
				CellRangeAddress cra_wlo=new CellRangeAddress((3+length1+length2),(short)(3+length1+length2+length3-1),0,(short)0);
				sheet.addMergedRegion(cra_wlo);
				for(int c=1;c<length3;c++){
					HSSFRow row=sheet.createRow(3+length1+length2+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellStyle(cs);					
				}
				//下月請款狀況
				HSSFRow row_inv1=sheet.createRow(3+length1+length2+length3);
				HSSFCell cell_inv1=row_inv1.createCell(b);
				cell_inv1.setCellValue("下月請款狀況");
				cell_inv1.setCellStyle(cs);
				CellRangeAddress cra_inv1=new CellRangeAddress((3+length1+length2+length3),(short)(3+length1+length2+length3+length4/2-1),0,(short)0);
				sheet.addMergedRegion(cra_inv1);
				for(int c=1;c<length4/2;c++){
					HSSFRow row=sheet.createRow(3+length1+length2+length3+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellStyle(cs);					
				}
				//下月生產狀況
				HSSFRow row_inv2=sheet.createRow(3+length1+length2+length3+length4/2);
				HSSFCell cell_inv2=row_inv2.createCell(b);
				cell_inv2.setCellValue("下月生產狀況");
				cell_inv2.setCellStyle(cs);
				CellRangeAddress cra_inv2=new CellRangeAddress((3+length1+length2+length3+length4/2),(short)(3+length1+length2+length3+length4-1),0,(short)0);
				sheet.addMergedRegion(cra_inv2);
				for(int c=1;c<length4/2;c++){
					HSSFRow row=sheet.createRow(3+length1+length2+length3+length4/2+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellStyle(cs);					
				}
				break;
			case 1:
				//損益彙總(v_sumloss)					
				for(int c=0;c<length1;c++){
					String content=list_contentName_loss.get(c);
					HSSFRow row=sheet.getRow(3+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellValue(content);
					cell.setCellStyle(cs);
				}
				//全廠原料庫存(v_sumstore)
				for(int c=0;c<length2;c++){
					String content=list_contentName_store.get(c);
					HSSFRow row=sheet.getRow(3+length1+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellValue(content);
					cell.setCellStyle(cs);
				}
				//水電油(v_sumwlo)
				for(int c=0;c<length3;c++){
					String content=list_contentName_wlo.get(c);
					HSSFRow row=sheet.getRow(3+length1+length2+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellValue(content);
					cell.setCellStyle(cs);
				}
				//下月請款狀況
				for(int c=0;c<length4/2;c++){
					String content=list_contentName_inv.get(c);
					HSSFRow row=sheet.getRow(3+length1+length2+length3+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellValue(content);
					cell.setCellStyle(cs);
				}
				//下月生產狀況
				for(int c=0;c<length4/2;c++){
					String content=list_contentName_inv.get(c+3);
					HSSFRow row=sheet.getRow(3+length1+length2+length3+length4/2+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellValue(content);
					cell.setCellStyle(cs);
				}
				break;
			case 2:
				//損益彙總(v_sumloss)			
				for(int c=0;c<length1;c++){
					String content=list_unit_loss.get(c);
					HSSFRow row=sheet.getRow(3+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellValue(content);
					cell.setCellStyle(cs);
				}
				//全廠原料庫存(v_sumstore)
				for(int c=0;c<length2;c++){
					String content=list_unit_store.get(c);
					HSSFRow row=sheet.getRow(3+length1+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellValue(content);
					cell.setCellStyle(cs);
				}
				//水電油(v_sumwlo)
				for(int c=0;c<length3;c++){
					String content=list_unit_wlo.get(c);
					HSSFRow row=sheet.getRow(3+length1+length2+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellValue(content);
					cell.setCellStyle(cs);
				}
				//下月請款狀況
				for(int c=0;c<length4/2;c++){
					String content=list_unit_inv.get(c);
					HSSFRow row=sheet.getRow(3+length1+length2+length3+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellValue(content);
					cell.setCellStyle(cs);
				}
				//下月生產狀況
				for(int c=0;c<length4/2;c++){
					String content=list_unit_inv.get(c+3);
					HSSFRow row=sheet.getRow(3+length1+length2+length3+length4/2+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellValue(content);
					cell.setCellStyle(cs);
				}
				break;
				default:
					//臨時樣式
					HSSFCellStyle cs_temp=wb.createCellStyle();
					//損益彙總(v_sumloss)
					List<Double>list_loss=list_loss_all.get(b);
					if(list_loss!=null){						
						for(int c=0;c<list_loss.size();c++){
							Double db=list_loss.get(c);
							HSSFRow row=sheet.getRow(3+c);
							HSSFCell cell=row.createCell(b);
							cell.setCellValue(db);
							cell.setCellStyle(cs_poi2);
						}
					}else{
						for(int c=0;c<length1;c++){
							HSSFRow row=sheet.getRow(3+c);
							HSSFCell cell=row.createCell(b);
							cell.setCellValue("無數據");
							cell.setCellStyle(cs);
						}
					}
					//全廠原料庫存(v_sumstore)
					List<Double>list_store=list_store_all.get(b);
					if(list_store!=null){						
						for(int c=0;c<list_store.size();c++){
							if(c==3||c==8){
								cs_temp=cs_percent;
							}else{
								cs_temp=cs_poi2;
							}
							Double db=list_store.get(c);
							HSSFRow row=sheet.getRow(3+length1+c);
							HSSFCell cell=row.createCell(b);
							//季度,半年和全年漲福沒有算出來
							if((b==6||b==10||b==11||b==15||b==19||b==20||b==21)&&(c==3||c==8)){
								cell.setCellValue("----");
								cell.setCellStyle(cs_red);
							}else{
								cell.setCellValue(db);
								cell.setCellStyle(cs_temp);
							}							
						}
					}else{
						for(int c=0;c<length2;c++){
							HSSFRow row=sheet.getRow(3+length1+c);
							HSSFCell cell=row.createCell(b);
							cell.setCellValue("無數據");
							cell.setCellStyle(cs);
						}
					}
					//水電油(v_sumwlo)
					List<Double>list_wlo=list_wlo_all.get(b);
					if(list_wlo!=null){						
						for(int c=0;c<list_wlo.size();c++){							
							Double db=list_wlo.get(c);
							HSSFRow row=sheet.getRow(3+length1+length2+c);
							HSSFCell cell=row.createCell(b);
							if(c==2||c==4||c==6||c==8||c==10||c==12||c==14||c==16){
								cs_temp=cs_poi4;
							}else{
								cs_temp=cs_poi2;
							}
							cell.setCellValue(db);
							cell.setCellStyle(cs_temp);
						}
					}else{
						for(int c=0;c<length3;c++){
							HSSFRow row=sheet.getRow(3+length1+length2+c);
							HSSFCell cell=row.createCell(b);
							cell.setCellValue("無數據");
							cell.setCellStyle(cs);
						}
					}
					//下月請款狀況,生產狀況
					List<Double>list_inv=list_inv_all.get(b);
					if(list_inv!=null){						
						for(int c=0;c<list_inv.size();c++){
							if(c==5){
								cs_temp=cs_percent;
							}else{
								cs_temp=cs_poi2;
							}
							Double db=list_inv.get(c);
							HSSFRow row=sheet.getRow(3+length1+length2+length3+c);
							HSSFCell cell=row.createCell(b);
							cell.setCellValue(db);
							cell.setCellStyle(cs_temp);
						}
					}else{
						for(int c=0;c<length4;c++){
							HSSFRow row=sheet.getRow(3+length1+length2+length3+c);
							HSSFCell cell=row.createCell(b);
							cell.setCellValue("無數據");
							cell.setCellStyle(cs);
						}
					}									
			}
		}//end for1
		//OutputStream os=new FileOutputStream("d:/vsumall.xls");
		ServletOutputStream os=response.getOutputStream();
		response.setContentType("application/vnd.ms-excel");
		title=title+".xls";
		String fileName="";
		int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");
		if(msie>0){
			fileName=java.net.URLEncoder.encode(title,"utf-8");
		}else{
			fileName=new String(title.getBytes("utf-8"),"iso8859-1");
		}
		response.setHeader("Content-disposition","attachment;filename="+fileName);
		wb.write(os);
		os.close();
	}
	
	public void print_tw() throws IOException, ParseException{
		//建立模板
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet();
		//基本樣式
		HSSFCellStyle cs=wb.createCellStyle();
		cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		//標題樣式
		HSSFCellStyle cs_title=wb.createCellStyle();
		HSSFFont font_title=wb.createFont();
		font_title.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_title.setFontHeightInPoints((short)20);
		cs_title.setFont(font_title);
		cs_title.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_title.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		//表頭新式
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
		cs_column.setWrapText(true);
		//數字格式
		HSSFDataFormat format=wb.createDataFormat();
		HSSFCellStyle cs_percent=wb.createCellStyle();
		cs_percent.setDataFormat(format.getFormat("0.00%"));
		cs_percent.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_percent.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_percent.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
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
		
		/**
		 * 數據源
		 */		
		//big用於填充null
		BigDecimal big=new BigDecimal(0.0);
		//損益匯總
		List<String>list_contentName_loss=new ArrayList<String>();
		List<String>list_unit_loss=new ArrayList<String>();
		list_contentName_loss.add("總銷貨收入");list_unit_loss.add("USD");
		list_contentName_loss.add("－) 成本合計 ");list_unit_loss.add("USD");
		list_contentName_loss.add("－) 工資合計 ");list_unit_loss.add("USD");
		list_contentName_loss.add("－) 費用合計 ");list_unit_loss.add("USD");
		list_contentName_loss.add("＋) 其他  ");list_unit_loss.add("USD");
		list_contentName_loss.add("本期損益");list_unit_loss.add("USD");
		//全廠原料庫存
		List<String>list_contentName_store=new ArrayList<String>();
		List<String>list_unit_store=new ArrayList<String>();
		list_contentName_store.add("總原料庫存量");list_unit_store.add("KG");
		list_contentName_store.add("總原料庫存金額");list_unit_store.add("USD");
		list_contentName_store.add("平均單價");list_unit_store.add("USD");
		list_contentName_store.add("漲幅");list_unit_store.add("%");//3
		list_contentName_store.add("膠類庫存量");list_unit_store.add("KG");
		list_contentName_store.add("膠類庫存量");list_unit_store.add("噸");
		list_contentName_store.add("膠類金額");list_unit_store.add("USD");
		list_contentName_store.add("平均單價");list_unit_store.add("USD");
		list_contentName_store.add("漲幅");list_unit_store.add("%");//8
		
		//水電油
		List<String>list_contentName_wlo=new ArrayList<String>();
		List<String>list_unit_wlo=new ArrayList<String>();
		list_contentName_wlo.add("生產模數");list_unit_wlo.add("模");
		list_contentName_wlo.add("水用量");list_unit_wlo.add("噸");
		list_contentName_wlo.add("用水單秏");list_unit_wlo.add("噸/模");
		list_contentName_wlo.add("用水金額");list_unit_wlo.add("USD");
		list_contentName_wlo.add("用水金額單秏(模)");list_unit_wlo.add("USD/模");
		list_contentName_wlo.add("用電量");list_unit_wlo.add("度");
		list_contentName_wlo.add("用電單秏");list_unit_wlo.add("度/模");
		list_contentName_wlo.add("用電金額");list_unit_wlo.add("USD");
		list_contentName_wlo.add("用電金額單秏");list_unit_wlo.add("USD/模");
		list_contentName_wlo.add("蒸氣用量");list_unit_wlo.add("噸");
		list_contentName_wlo.add("用蒸氣單秏");list_unit_wlo.add("噸/模");
		list_contentName_wlo.add("用蒸氣金額(USD)");list_unit_wlo.add("USD");
		list_contentName_wlo.add("用蒸氣金額單秏");list_unit_wlo.add("USD/模");
		list_contentName_wlo.add("柴油用量");list_unit_wlo.add("公升");
		list_contentName_wlo.add("用油單秏");list_unit_wlo.add("公升/模");
		list_contentName_wlo.add("柴油金額(USD)");list_unit_wlo.add("USD");
		list_contentName_wlo.add("用油金額單秏(模)");list_unit_wlo.add("USD/模");
		//下月請款狀況,下月生產狀況
		List<String>list_contentName_inv=new ArrayList<String>();
		List<String>list_unit_inv=new ArrayList<String>();
		list_contentName_inv.add("預計請款");list_unit_inv.add("雙");
		list_contentName_inv.add("請款金額");list_unit_inv.add("USD");
		list_contentName_inv.add("平均單價");list_unit_inv.add("USD/雙");
		list_contentName_inv.add("預計生產");list_unit_inv.add("模");
		list_contentName_inv.add("機臺標准戰力");list_unit_inv.add("模");
		list_contentName_inv.add("機孔達成率");list_unit_inv.add("%");//5
		//標題
		String title=yymm+"台灣損益會議各廠檢討項目各廠對比表----全廠總計";			
		/**
		 * 表頭內容
		 */
		List<String>list_column=new ArrayList<String>();//用於打印表頭內容
		List<String>list_column_factno=new ArrayList<String>();//用於循環數據
		list_column.add("項目");
		list_column.add("細項");
		list_column.add("單位");
		
		list_column_factno.add(null);
		list_column_factno.add(null);
		list_column_factno.add(null);
		//所有工廠(0:廠號  1:廠名)
		//如果頁面上有選擇廠別
		if(list_factno!=null&&list_factno.size()>0){
			for(int a=0;a<list_factno.size();a++){
				String factNo=list_factno.get(a);
				String factName=webFactSer.selByid(factNo);
				String webfact=factName+"("+factNo+")";
				list_column.add(webfact);
				list_column_factno.add(factNo);
				if(a==list_factno.size()-1){
					list_column.add("合計");
				}
			}
		//如果沒有選擇,則默認全部廠別
		}else{
			List<Object[]>list_webfact=webFactSer.findAllWebFact_showA();
			for(int a=0;a<list_webfact.size();a++){
				Object[]objs=list_webfact.get(a);
				String factNo=objs[0].toString();
				String factName=objs[1].toString();
				String webfact=factName+"("+factNo+")";
				list_column.add(webfact);
				list_column_factno.add(factNo);
				//最後一列添加一欄"合計"
				if(a==list_webfact.size()-1){
					list_column.add("合計");					
				}
			}
		}
		
		//主要數據內容		
		//最外層集合
		List<List<Double>>list_loss_all=new ArrayList<List<Double>>();
		List<List<Double>>list_store_all=new ArrayList<List<Double>>();
		List<List<Double>>list_wlo_all=new ArrayList<List<Double>>();
		List<List<Double>>list_inv_all=new ArrayList<List<Double>>();
		
		//"合計"欄集合
		List<Double>list_loss_total=new ArrayList<Double>();
		List<Double>list_store_total=new ArrayList<Double>();
		List<Double>list_wlo_total=new ArrayList<Double>();
		List<Double>list_inv_total=new ArrayList<Double>();
		//"合計"欄各元素
		//損益彙總(v_sumloss)
		Double total_a001=0.0;
		Double total_a002=0.0;
		Double total_a003=0.0;
		Double total_a004=0.0;
		Double total_a005=0.0;
		//全廠原料庫存(v_sumstore)
		//本月
		Double total_b001=0.0;
		Double total_b002=0.0;
		Double total_b005=0.0;
		Double total_b006=0.0;
		Double total_b007=0.0;
		//上月
		Double lag_total_b001=0.0;
		Double lag_total_b002=0.0;
		Double lag_total_b005=0.0;
		Double lag_total_b007=0.0;
		//水電油(v_sumwlo)
		Double total_c001=0.0;
		Double total_c002=0.0;
		Double total_c004=0.0;
		Double total_c006=0.0;
		Double total_c008=0.0;
		Double total_c010=0.0;
		Double total_c012=0.0;
		Double total_c014=0.0;
		Double total_c016=0.0;
		//下月請款與生產狀況
		Double total_d001=0.0;
		Double total_d002=0.0;
		Double total_d004=0.0;
		Double total_d005=0.0;
		
		//各個最外層集合先添加三個空null
		for(int a=0;a<3;a++){
			list_loss_all.add(null);
			list_store_all.add(null);
			list_wlo_all.add(null);
			list_inv_all.add(null);
		}
		for(int b=3;b<list_column_factno.size();b++){//start for
			//注意,b從3開始
			//各單元集合
			List<Double>list_loss=new ArrayList<Double>();
			List<Double>list_store=new ArrayList<Double>();
			List<Double>list_wlo=new ArrayList<Double>();
			List<Double>list_inv=new ArrayList<Double>();
			//上月平均單價
			Double lag_b003=0.0;
			//上月平均單價(膠)
			Double lag_b008=0.0;
			//當月平均單價
			Double b003=0.0;
			//當月平均單價(膠)
			Double b008=0.0;
			//漲福1
			Double store_b004=0.0;
			//膠類庫存量(噸)
			Double store_b006=0.0;
			//漲福2
			Double store_b009=0.0;
												
			String factNo=list_column_factno.get(b);
			VSumloss loss=sumlossSer.findById(factNo,yymm);
			VSumstore store=sumstoreSer.findById(factNo, yymm);
			VSumwlo wlo=sumwlodSer.findById(factNo, yymm);
			VSuminv inv=suminvSer.findById(factNo, yymm);
			
			//損益彙總(v_sumloss)
			if(loss!=null){				
				list_loss.add(loss.getLossA001().doubleValue());
				list_loss.add(loss.getLossA002().doubleValue());
				list_loss.add(loss.getLossA003().doubleValue());
				list_loss.add(loss.getLossA004().doubleValue());
				list_loss.add(loss.getLossA005().doubleValue());
				list_loss.add(loss.getLossA006().doubleValue());
				list_loss_all.add(list_loss);
				//合計
				total_a001=total_a001+loss.getLossA001().doubleValue();
				total_a002=total_a002+loss.getLossA002().doubleValue();
				total_a003=total_a003+loss.getLossA003().doubleValue();
				total_a004=total_a004+loss.getLossA004().doubleValue();
				total_a005=total_a005+loss.getLossA005().doubleValue();
			}else{
				list_loss_all.add(null);
			}
			//上一個月全廠原料庫存平均單價
			String lastdate=this.findLastMonth(yymm);
			VSumstore store_last=sumstoreSer.findById(factNo,lastdate);
			if(store_last!=null){
				lag_b003=store_last.getStoreB003().doubleValue();
				lag_b008=store_last.getStoreB008().doubleValue();
				//上月合計
				lag_total_b001=store_last.getStoreB001().doubleValue();
				lag_total_b002=store_last.getStoreB002().doubleValue();
				lag_total_b005=store_last.getStoreB005().doubleValue();
				lag_total_b007=store_last.getStoreB007().doubleValue();
			}
			//當月全廠原料庫存平均單價
			if(store!=null){
				b003=store.getStoreB003().doubleValue();
				b008=store.getStoreB008().doubleValue();
				//當月合計
				total_b001=store.getStoreB001().doubleValue();
				total_b002=store.getStoreB002().doubleValue();
				total_b005=store.getStoreB005().doubleValue();
				total_b007=store.getStoreB007().doubleValue();
			}
			//根據上月與本月的平均單價,算出漲福1,膠類庫存量(噸),漲福2
			//漲福1
			store_b004=this.division((b003-lag_b003), lag_b003);			
			//漲福2
			store_b009=this.division((b008-lag_b008), lag_b008);
			//全廠原料庫存(v_sumstore)
			if(store!=null){
				list_store.add(store.getStoreB001().doubleValue());
				list_store.add(store.getStoreB002().doubleValue());
				list_store.add(store.getStoreB003().doubleValue());
				list_store.add(store_b004);
				list_store.add(store.getStoreB005().doubleValue());
				list_store.add(store.getStoreB006().doubleValue());
				list_store.add(store.getStoreB007().doubleValue());
				list_store.add(store.getStoreB008().doubleValue());
				list_store.add(store_b009);
				list_store_all.add(list_store);
			}else{
				list_store_all.add(null);
			}
			//水電油(v_sumwlo)
			if(wlo!=null){
				if(wlo.getWloC002()==null){
					wlo.setWloC002(big);
				}
				if(wlo.getWloC003()==null){
					wlo.setWloC003(big);
				}
				if(wlo.getWloC004()==null){
					wlo.setWloC004(big);
				}
				if(wlo.getWloC005()==null){
					wlo.setWloC005(big);
				}
				if(wlo.getWloC006()==null){
					wlo.setWloC006(big);
				}
				if(wlo.getWloC007()==null){
					wlo.setWloC007(big);
				}
				if(wlo.getWloC008()==null){
					wlo.setWloC008(big);
				}
				if(wlo.getWloC009()==null){
					wlo.setWloC009(big);
				}
				if(wlo.getWloC010()==null){
					wlo.setWloC010(big);
				}
				if(wlo.getWloC011()==null){
					wlo.setWloC011(big);
				}
				if(wlo.getWloC012()==null){
					wlo.setWloC012(big);
				}
				if(wlo.getWloC013()==null){
					wlo.setWloC013(big);
				}
				if(wlo.getWloC014()==null){
					wlo.setWloC014(big);
				}
				if(wlo.getWloC015()==null){
					wlo.setWloC015(big);
				}
				if(wlo.getWloC016()==null){
					wlo.setWloC016(big);
				}
				if(wlo.getWloC017()==null){
					wlo.setWloC017(big);
				}
				list_wlo.add(wlo.getWloC001().doubleValue());
				list_wlo.add(wlo.getWloC002().doubleValue());
				list_wlo.add(wlo.getWloC003().doubleValue());
				list_wlo.add(wlo.getWloC004().doubleValue());
				list_wlo.add(wlo.getWloC005().doubleValue());
				list_wlo.add(wlo.getWloC006().doubleValue());
				list_wlo.add(wlo.getWloC007().doubleValue());
				list_wlo.add(wlo.getWloC008().doubleValue());
				list_wlo.add(wlo.getWloC009().doubleValue());
				list_wlo.add(wlo.getWloC010().doubleValue());
				list_wlo.add(wlo.getWloC011().doubleValue());
				list_wlo.add(wlo.getWloC012().doubleValue());
				list_wlo.add(wlo.getWloC013().doubleValue());
				list_wlo.add(wlo.getWloC014().doubleValue());
				list_wlo.add(wlo.getWloC015().doubleValue());
				list_wlo.add(wlo.getWloC016().doubleValue());
				list_wlo.add(wlo.getWloC017().doubleValue());
				list_wlo_all.add(list_wlo);
				//合計
				total_c001=total_c001+wlo.getWloC001().doubleValue();
				total_c002=total_c002+wlo.getWloC002().doubleValue();
				total_c004=total_c004+wlo.getWloC004().doubleValue();
				total_c006=total_c006+wlo.getWloC006().doubleValue();
				total_c008=total_c008+wlo.getWloC008().doubleValue();
				total_c010=total_c010+wlo.getWloC010().doubleValue();
				total_c012=total_c012+wlo.getWloC012().doubleValue();
				total_c014=total_c014+wlo.getWloC014().doubleValue();
				total_c016=total_c016+wlo.getWloC016().doubleValue();
				
			}else{
				list_wlo_all.add(null);
			}
			//下月請款狀況,下月生產狀況
			if(inv!=null){
				if(inv.getInvD001()==null){
					inv.setInvD001(big);
				}
				if(inv.getInvD002()==null){
					inv.setInvD002(big);
				}
				if(inv.getInvD003()==null){
					inv.setInvD003(big);
				}
				if(inv.getInvD004()==null){
					inv.setInvD004(big);
				}
				if(inv.getInvD005()==null){
					inv.setInvD005(big);
				}
				if(inv.getInvD006()==null){
					inv.setInvD006(big);
				}
				list_inv.add(inv.getInvD001().doubleValue());
				list_inv.add(inv.getInvD002().doubleValue());
				list_inv.add(inv.getInvD003().doubleValue());
				list_inv.add(inv.getInvD004().doubleValue());
				list_inv.add(inv.getInvD005().doubleValue());
				list_inv.add(inv.getInvD006().doubleValue());
				list_inv_all.add(list_inv);
				//合計
				total_d001=total_d001+inv.getInvD001().doubleValue();
				total_d002=total_d002+inv.getInvD002().doubleValue();
				total_d004=total_d004+inv.getInvD004().doubleValue();
				total_d005=total_d005+inv.getInvD005().doubleValue();
			}else{
				list_inv_all.add(null);
			}									
		}//end for
		//最外層集合添加"合計"數據
		list_loss_total=this.findResult_loss(1, total_a001, total_a002, total_a003, total_a004, total_a005);
		list_store_total=this.findResult_store_tw(1, total_b001, total_b002, total_b005,total_b006, total_b007, lag_total_b001, lag_total_b002, lag_total_b005, lag_total_b007);
		list_wlo_total=this.findResult_wlo(1, total_c001, total_c002, total_c004, total_c006, total_c008, total_c010, total_c012, total_c014, total_c016);
		list_inv_total=this.findResult_inv(1, total_d001, total_d002, total_d004, total_d005);
		
		list_loss_all.add(list_loss_total);
		list_store_all.add(list_store_total);
		list_wlo_all.add(list_wlo_total);
		list_inv_all.add(list_inv_total);
		/**
		 * 填充內容
		 */
		//填充標題
		HSSFRow row_title=sheet.createRow(0);
		HSSFCell cell_title=row_title.createCell(0);
		cell_title.setCellValue(title);
		cell_title.setCellStyle(cs_title);
		CellRangeAddress cra_title=new CellRangeAddress(0,(short)0,0,(short)list_column.size()-1);
		sheet.addMergedRegion(cra_title);
		for(int b=1;b<list_column.size();b++){
			HSSFCell cell=row_title.createCell(b);
			cell.setCellStyle(cs_title);
		}
		//填充表頭
		HSSFRow row_column=sheet.createRow(2);
		for(int b=0;b<list_column.size();b++){
			sheet.setColumnWidth(b, 4000);
			String columnName=list_column.get(b);
			HSSFCell cell=row_column.createCell(b);
			cell.setCellValue(columnName);
			cell.setCellStyle(cs_column);
		}
		//填充"項目","細項","單位"和數據內容
		int length1=list_contentName_loss.size();
		int length2=list_contentName_store.size();
		int length3=list_contentName_wlo.size();
		int length4=list_contentName_inv.size();
		for(int b=0;b<list_column.size();b++){//start for1
			switch(b){
			case 0:
				//損益彙總(v_sumloss)			
				HSSFRow row_loss=sheet.createRow(3);
				HSSFCell cell_loss=row_loss.createCell(b);
				cell_loss.setCellValue("損益彙總");
				cell_loss.setCellStyle(cs);
				CellRangeAddress cra_loss=new CellRangeAddress(3,(short)(3+length1-1),0,(short)0);
				sheet.addMergedRegion(cra_loss);
				for(int c=1;c<length1;c++){
					HSSFRow row=sheet.createRow(3+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellStyle(cs);					
				}
				//全廠原料庫存(v_sumstore)
				HSSFRow row_store=sheet.createRow(3+length1);
				HSSFCell cell_store=row_store.createCell(b);
				cell_store.setCellValue("全廠原料庫存");
				cell_store.setCellStyle(cs);
				CellRangeAddress cra_store=new CellRangeAddress((3+length1),(short)(3+length1+length2-1),0,(short)0);
				sheet.addMergedRegion(cra_store);
				for(int c=1;c<length2;c++){
					HSSFRow row=sheet.createRow(3+length1+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellStyle(cs);					
				}
				//水電油(v_sumwlo)
				HSSFRow row_wlo=sheet.createRow(3+length1+length2);
				HSSFCell cell_wlo=row_wlo.createCell(b);
				cell_wlo.setCellValue("水電油");
				cell_wlo.setCellStyle(cs);
				CellRangeAddress cra_wlo=new CellRangeAddress((3+length1+length2),(short)(3+length1+length2+length3-1),0,(short)0);
				sheet.addMergedRegion(cra_wlo);
				for(int c=1;c<length3;c++){
					HSSFRow row=sheet.createRow(3+length1+length2+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellStyle(cs);					
				}
				//下月請款狀況
				HSSFRow row_inv1=sheet.createRow(3+length1+length2+length3);
				HSSFCell cell_inv1=row_inv1.createCell(b);
				cell_inv1.setCellValue("下月請款狀況");
				cell_inv1.setCellStyle(cs);
				CellRangeAddress cra_inv1=new CellRangeAddress((3+length1+length2+length3),(short)(3+length1+length2+length3+length4/2-1),0,(short)0);
				sheet.addMergedRegion(cra_inv1);
				for(int c=1;c<length4/2;c++){
					HSSFRow row=sheet.createRow(3+length1+length2+length3+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellStyle(cs);					
				}
				//下月生產狀況
				HSSFRow row_inv2=sheet.createRow(3+length1+length2+length3+length4/2);
				HSSFCell cell_inv2=row_inv2.createCell(b);
				cell_inv2.setCellValue("下月生產狀況");
				cell_inv2.setCellStyle(cs);
				CellRangeAddress cra_inv2=new CellRangeAddress((3+length1+length2+length3+length4/2),(short)(3+length1+length2+length3+length4-1),0,(short)0);
				sheet.addMergedRegion(cra_inv2);
				for(int c=1;c<length4/2;c++){
					HSSFRow row=sheet.createRow(3+length1+length2+length3+length4/2+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellStyle(cs);					
				}
				break;
			case 1:
				//損益彙總(v_sumloss)				
				for(int c=0;c<length1;c++){
					String content=list_contentName_loss.get(c);
					HSSFRow row=sheet.getRow(3+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellValue(content);
					cell.setCellStyle(cs);
				}
				//全廠原料庫存(v_sumstore)
				for(int c=0;c<length2;c++){
					String content=list_contentName_store.get(c);
					HSSFRow row=sheet.getRow(3+length1+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellValue(content);
					cell.setCellStyle(cs);
				}
				//水電油(v_sumwlo)
				for(int c=0;c<length3;c++){
					String content=list_contentName_wlo.get(c);
					HSSFRow row=sheet.getRow(3+length1+length2+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellValue(content);
					cell.setCellStyle(cs);
				}
				//下月請款狀況與生產狀況
				for(int c=0;c<length4;c++){
					String content=list_contentName_inv.get(c);
					HSSFRow row=sheet.getRow(3+length1+length2+length3+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellValue(content);
					cell.setCellStyle(cs);
				}				
				
				break;
			case 2:
				//損益彙總(v_sumloss)				
				for(int c=0;c<length1;c++){
					String content=list_unit_loss.get(c);
					HSSFRow row=sheet.getRow(3+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellValue(content);
					cell.setCellStyle(cs);
				}
				//全廠原料庫存(v_sumstore)
				for(int c=0;c<length2;c++){
					String content=list_unit_store.get(c);
					HSSFRow row=sheet.getRow(3+length1+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellValue(content);
					cell.setCellStyle(cs);
				}
				//水電油(v_sumwlo)
				for(int c=0;c<length3;c++){
					String content=list_unit_wlo.get(c);
					HSSFRow row=sheet.getRow(3+length1+length2+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellValue(content);
					cell.setCellStyle(cs);
				}
				//下月請款狀況與生產狀況
				for(int c=0;c<length4;c++){
					String content=list_unit_inv.get(c);
					HSSFRow row=sheet.getRow(3+length1+length2+length3+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellValue(content);
					cell.setCellStyle(cs);
				}				
				break;
				default:
					//獲取各個單元集合
					List<Double>list_loss=list_loss_all.get(b);
					List<Double>list_store=list_store_all.get(b);
					List<Double>list_wlo=list_wlo_all.get(b);
					List<Double>list_inv=list_inv_all.get(b);
					HSSFCellStyle cs_temp=wb.createCellStyle();//臨時樣式
					//損益彙總(v_sumloss)
					if(list_loss!=null){
						for(int c=0;c<list_loss.size();c++){							
							Double db=list_loss.get(c);
							HSSFRow row=sheet.getRow(3+c);
							HSSFCell cell=row.createCell(b);
							cell.setCellValue(db);
							cell.setCellStyle(cs_poi2);
						}
					}else{
						for(int c=0;c<length1;c++){
							HSSFRow row=sheet.getRow(3+c);
							HSSFCell cell=row.createCell(b);
							cell.setCellValue("無數據");
							cell.setCellStyle(cs);
						}
					}					
					//全廠原料庫存(v_sumstore)
					if(list_store!=null){
						for(int c=0;c<list_store.size();c++){
							if(c==3||c==8){
								cs_temp=cs_percent;
							}else{
								cs_temp=cs_poi2;
							}
							Double db=list_store.get(c);
							HSSFRow row=sheet.getRow(3+length1+c);
							HSSFCell cell=row.createCell(b);
							cell.setCellValue(db);
							cell.setCellStyle(cs_temp);
						}
					}else{
						for(int c=0;c<length2;c++){
							HSSFRow row=sheet.getRow(3+length1+c);
							HSSFCell cell=row.createCell(b);
							cell.setCellValue("無數據");
							cell.setCellStyle(cs);
						}
					}					
					//水電油(v_sumwlo)
					if(list_wlo!=null){
						for(int c=0;c<list_wlo.size();c++){
							Double db=list_wlo.get(c);
							HSSFRow row=sheet.getRow(3+length1+length2+c);
							HSSFCell cell=row.createCell(b);
							if(c==2||c==4||c==6||c==8||c==10||c==12||c==14||c==16){
								cs_temp=cs_poi4;
							}else{
								cs_temp=cs_poi2;
							}
							cell.setCellValue(db);
							cell.setCellStyle(cs_temp);
						}
					}else{
						for(int c=0;c<length3;c++){
							HSSFRow row=sheet.getRow(3+length1+length2+c);
							HSSFCell cell=row.createCell(b);
							cell.setCellValue("無數據");
							cell.setCellStyle(cs);
						}
					}					
					//下朋請款與生產狀況
					if(list_inv!=null){
						for(int c=0;c<list_inv.size();c++){
							if(c==5){
								cs_temp=cs_percent;
							}else{
								cs_temp=cs_poi2;
							}
							Double db=list_inv.get(c);
							HSSFRow row=sheet.getRow(3+length1+length2+length3+c);
							HSSFCell cell=row.createCell(b);
							cell.setCellValue(db);
							cell.setCellStyle(cs_temp);
						}
					}else{
						for(int c=0;c<length4;c++){
							HSSFRow row=sheet.getRow(3+length1+length2+length3+c);
							HSSFCell cell=row.createCell(b);
							cell.setCellValue("無數據");
							cell.setCellStyle(cs);
						}
					}					
					break;										
			}			
		}//end for1
				
		//OutputStream os=new FileOutputStream("d:/vsumall_tw.xls");
		ServletOutputStream os=response.getOutputStream();
		response.setContentType("application/vnd.ms-excel");
		title=title+".xls";
		String fileName="";
		int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");
		if(msie>0){
			fileName=java.net.URLEncoder.encode(title,"utf-8");
		}else{
			fileName=new String(title.getBytes("utf-8"),"iso8859-1");
		}
		response.setHeader("Content-disposition","attachment;filename="+fileName);
		wb.write(os);
		os.close();
		
		
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
	 * 損益彙總(v_sumloss)季度,半年,全年統計
	 */
	public List<Double>findResult_loss(int index,
			Double a001,Double a002,Double a003,Double a004,Double a005
			){
		List<Double>list=new ArrayList<Double>();
		Double a01=a001/index;
		Double a02=a002/index;
		Double a03=a003/index;
		Double a04=a004/index;
		Double a05=a005/index;
		Double a06=a01-a02-a03-a04+a05;
		list.add(a01);
		list.add(a02);
		list.add(a03);
		list.add(a04);
		list.add(a05);
		list.add(a06);
		return list;
	}
	
	/**
	 * 全廠原料庫存(v_sumstore)季度,半年,全年統計(工廠)
	 */
	public List<Double>findResult_store(int index,
			Double b001,Double b002,Double b005,Double b006,Double b007
			){
		List<Double>list=new ArrayList<Double>();
		Double b01=b001/index;
		Double b02=b002/index;
		Double b03=this.division(b02, b01);
		Double b04=0.0;
		Double b05=b005/index;
		Double b06=b006/index;
		Double b07=b007/index;
		Double b08=this.division(b07, b05);
		Double b09=0.0;
		list.add(b01);
		list.add(b02);
		list.add(b03);
		list.add(b04);
		list.add(b05);
		list.add(b06);
		list.add(b07);
		list.add(b08);
		list.add(b09);
		return list;
	}
	/**
	 * 全廠原料庫存(v_sumstore)季度,半年,全年統計(台灣)
	 */
	public List<Double>findResult_store_tw(int index,
			Double b001,Double b002,Double b005,Double b006,Double b007,
			Double lag_b001,Double lag_b002,Double lag_b005,Double lag_b007
			){
		List<Double>list=new ArrayList<Double>();
		Double b01=b001/index;
		Double b02=b002/index;
		Double b03=this.division(b02, b01);//本月平均單價1
		Double lag_b03=this.division(lag_b002, lag_b001);//上月平均單價1
		Double b04=this.division((b03-lag_b03), lag_b03);
		Double b05=b005/index;
		Double b06=b006/index;
		Double b07=b007;
		Double b08=this.division(b07, b05);//本月平均單價2
		Double lag_b08=this.division(lag_b007, lag_b005);//上月平均單價2
		Double b09=this.division((b08-lag_b08), lag_b08);
		list.add(b01);
		list.add(b02);
		list.add(b03);
		list.add(b04);
		list.add(b05);
		list.add(b06);
		list.add(b07);
		list.add(b08);
		list.add(b09);
		return list;
	}
	
	
	/**
	 * 水電油(v_sumwlo)季度,半年,全年統計
	 */
	public List<Double>findResult_wlo(int index,
			Double c001,Double c002,Double c004,Double c006,
			Double c008,Double c010,Double c012,Double c014,
			Double c016
			){
		List<Double>list=new ArrayList<Double>();
		Double c01=c001/index;
		Double c02=c002/index;
		Double c03=this.division(c02, c01);
		Double c04=c004/index;
		Double c05=this.division(c04, c01);
		Double c06=c006/index;
		Double c07=this.division(c06, c01);
		Double c08=c008/index;
		Double c09=this.division(c08, c01);
		Double c10=c010/index;
		Double c11=this.division(c10, c01);
		Double c12=c012/index;
		Double c13=this.division(c12, c01);
		Double c14=c014/index;
		Double c15=this.division(c14, c01);
		Double c16=c016/index;
		Double c17=this.division(c16, c01);
		list.add(c01);
		list.add(c02);
		list.add(c03);
		list.add(c04);
		list.add(c05);
		list.add(c06);
		list.add(c07);
		list.add(c08);
		list.add(c09);
		list.add(c10);
		list.add(c11);
		list.add(c12);
		list.add(c13);
		list.add(c14);
		list.add(c15);
		list.add(c16);
		list.add(c17);
		return list;
	}
	/**
	 * 下月請款狀況(v_suminv)季度,半年,全年統計
	 */
	public List<Double>findResult_inv(int index,
			Double d001,Double d002,Double d004,Double d005
			){
		List<Double>list=new ArrayList<Double>();
		Double d01=d001/index;
		Double d02=d002/index;
		Double d03=this.division(d02, d01);
		Double d04=d004/index;
		Double d05=d005/index;
		Double d06=this.division(d04, d05);
		list.add(d01);
		list.add(d02);
		list.add(d03);
		list.add(d04);
		list.add(d05);
		list.add(d06);
		return list;
	}
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	/**
	 * 獲取上個月日期
	 * @throws ParseException 
	 */
	public String findLastMonth(String yymm) throws ParseException{
		SimpleDateFormat format=new SimpleDateFormat("yyyyMM");
		Date date=format.parse(yymm);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		String lastdate=format.format(calendar.getTime());
		return lastdate;
	}
	
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

	public void print_month() throws ParseException, IOException{
		/**
		 * 建立模板
		 */
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet();
		/**
		 * 各種樣式
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
		HSSFCellStyle cs_title=wb.createCellStyle();
		HSSFFont font_title=wb.createFont();
		font_title.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_title.setFontHeightInPoints((short)20);
		cs_title.setFont(font_title);
		cs_title.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_title.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
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
		//無邊框新式
		HSSFCellStyle cs_noborder=wb.createCellStyle();
		cs_noborder.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_noborder.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		//數字格式
		HSSFDataFormat fm=wb.createDataFormat();
		HSSFCellStyle cs_percent=wb.createCellStyle();
		cs_percent.setDataFormat(fm.getFormat("0.00%"));
		cs_percent.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_percent.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_percent.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle cs_poi2=wb.createCellStyle();
		cs_poi2.setDataFormat(fm.getFormat("#,###,0.00"));
		cs_poi2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle cs_poi4=wb.createCellStyle();
		cs_poi4.setDataFormat(fm.getFormat("#,###,0.0000"));
		cs_poi4.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi4.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		
		HSSFCellStyle cs_red=wb.createCellStyle();
		HSSFFont font_red=wb.createFont();
		font_red.setColor(IndexedColors.RED.getIndex());
		cs_red.setFont(font_red);
		cs_red.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_red.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_red.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_red.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_red.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_red.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		/**
		 * 數據源
		 */	
		
		//big用於填充null
		BigDecimal big=new BigDecimal(0.0);
		//損益匯總
		List<String>list_contentName_loss=new ArrayList<String>();
		List<String>list_unit_loss=new ArrayList<String>();
		list_contentName_loss.add("總銷貨收入");list_unit_loss.add("USD");
		list_contentName_loss.add("－) 成本合計 ");list_unit_loss.add("USD");
		list_contentName_loss.add("－) 工資合計 ");list_unit_loss.add("USD");
		list_contentName_loss.add("－) 費用合計 ");list_unit_loss.add("USD");
		list_contentName_loss.add("＋) 其他  ");list_unit_loss.add("USD");
		list_contentName_loss.add("本期損益");list_unit_loss.add("USD");
		//全廠原料庫存
		List<String>list_contentName_store=new ArrayList<String>();
		List<String>list_unit_store=new ArrayList<String>();
		list_contentName_store.add("總原料庫存量");list_unit_store.add("KG");
		list_contentName_store.add("總原料庫存金額");list_unit_store.add("USD");
		list_contentName_store.add("平均單價");list_unit_store.add("USD");
		list_contentName_store.add("漲幅");list_unit_store.add("%");//3
		list_contentName_store.add("膠類庫存量");list_unit_store.add("KG");
		list_contentName_store.add("膠類庫存量");list_unit_store.add("噸");
		list_contentName_store.add("膠類金額");list_unit_store.add("USD");
		list_contentName_store.add("平均單價");list_unit_store.add("USD");
		list_contentName_store.add("漲幅");list_unit_store.add("%");//8
		
		//水電油
		List<String>list_contentName_wlo=new ArrayList<String>();
		List<String>list_unit_wlo=new ArrayList<String>();
		list_contentName_wlo.add("生產模數");list_unit_wlo.add("模");
		list_contentName_wlo.add("水用量");list_unit_wlo.add("噸");
		list_contentName_wlo.add("用水單秏");list_unit_wlo.add("噸/模");
		list_contentName_wlo.add("用水金額");list_unit_wlo.add("USD");
		list_contentName_wlo.add("用水金額單秏(模)");list_unit_wlo.add("USD/模");
		list_contentName_wlo.add("用電量");list_unit_wlo.add("度");
		list_contentName_wlo.add("用電單秏");list_unit_wlo.add("度/模");
		list_contentName_wlo.add("用電金額");list_unit_wlo.add("USD");
		list_contentName_wlo.add("用電金額單秏");list_unit_wlo.add("USD/模");
		list_contentName_wlo.add("蒸氣用量");list_unit_wlo.add("噸");
		list_contentName_wlo.add("用蒸氣單秏");list_unit_wlo.add("噸/模");
		list_contentName_wlo.add("用蒸氣金額(USD)");list_unit_wlo.add("USD");
		list_contentName_wlo.add("用蒸氣金額單秏");list_unit_wlo.add("USD/模");
		list_contentName_wlo.add("柴油用量");list_unit_wlo.add("公升");
		list_contentName_wlo.add("用油單秏");list_unit_wlo.add("公升/模");
		list_contentName_wlo.add("柴油金額(USD)");list_unit_wlo.add("USD");
		list_contentName_wlo.add("用油金額單秏(模)");list_unit_wlo.add("USD/模");
		//下月請款狀況,下月生產狀況
		List<String>list_contentName_inv=new ArrayList<String>();
		List<String>list_unit_inv=new ArrayList<String>();
		list_contentName_inv.add("預計請款");list_unit_inv.add("雙");
		list_contentName_inv.add("請款金額");list_unit_inv.add("USD");
		list_contentName_inv.add("平均單價");list_unit_inv.add("USD/雙");
		list_contentName_inv.add("預計生產");list_unit_inv.add("模");
		list_contentName_inv.add("機臺標准戰力");list_unit_inv.add("模");
		list_contentName_inv.add("機孔達成率");list_unit_inv.add("%");//5
		
		
		//表頭內容
		//獲取所有月數
		List<String>list_months=this.getMonths(yymm_begin, yymm_end);
		List<String>list_column=new ArrayList<String>();
		list_column.add("項目");
		list_column.add("細項");
		list_column.add("單位");	
		for(int a=0;a<list_months.size();a++){
			String ym=list_months.get(a);
			list_column.add(ym);
		}
		list_column.add("合計");//注意,不帶序號
		
		//主要數據
		//最外層集合
		List<List<Double>>list_loss_all=new ArrayList<List<Double>>();
		List<List<Double>>list_store_all=new ArrayList<List<Double>>();
		List<List<Double>>list_wlo_all=new ArrayList<List<Double>>();
		List<List<Double>>list_inv_all=new ArrayList<List<Double>>();
		//注意,因為有"項目""細項""單位",所以要添加為空
		list_loss_all.add(null);
		list_loss_all.add(null);
		list_loss_all.add(null);
		
		list_store_all.add(null);
		list_store_all.add(null);
		list_store_all.add(null);
		
		list_wlo_all.add(null);
		list_wlo_all.add(null);
		list_wlo_all.add(null);
		
		list_inv_all.add(null);
		list_inv_all.add(null);
		list_inv_all.add(null);
		
		////損益彙總(v_sumloss)
		
		//合計
		Double total_a001=0.0;Double total_a002=0.0;
		Double total_a003=0.0;Double total_a004=0.0;
		Double total_a005=0.0;
		
		//全廠原料庫存(v_sumstore)
		
		//合計
		Double total_b001=0.0;Double total_b002=0.0;
		Double total_b005=0.0;Double total_b006=0.0;
		Double total_b007=0.0;
		
		//上月平均單價
		Double lag_b003=0.0;
		//上月平均單價(膠)
		Double lag_b008=0.0;
		//當月平均單價
		Double b003=0.0;
		//當月平均單價(膠)
		Double b008=0.0;
		//漲福1
		Double store_b004=0.0;		
		//漲福2
		Double store_b009=0.0;
		
		//水電油(v_sumwlo)
		
		//合計
		Double total_c001=0.0;Double total_c002=0.0;
		Double total_c004=0.0;Double total_c006=0.0;
		Double total_c008=0.0;Double total_c010=0.0;
		Double total_c012=0.0;Double total_c014=0.0;
		Double total_c016=0.0;
		//下月請款狀況,下月生產狀況
		
		//合計
		Double total_d001=0.0;Double total_d002=0.0;
		Double total_d004=0.0;Double total_d005=0.0;
		
		
		for(int a=0;a<list_months.size();a++){//start for1
			String yymm=list_months.get(a);			
			/**
			 * 獲取上個月日期
			 */
			SimpleDateFormat format=new SimpleDateFormat("yyyyMM");
			Date date=format.parse(yymm);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, -1);
			String lastdate=format.format(calendar.getTime());
			//月
			List<Double>list_loss=new ArrayList<Double>();
			List<Double>list_store=new ArrayList<Double>();
			List<Double>list_wlo=new ArrayList<Double>();
			List<Double>list_inv=new ArrayList<Double>();
			
			
			VSumloss loss=sumlossSer.findById(factNo,yymm);
			VSumstore store=sumstoreSer.findById(factNo, yymm);
			VSumwlo wlo=sumwlodSer.findById(factNo, yymm);
			VSuminv inv=suminvSer.findById(factNo, yymm);
			
			//
			//上個月store
			VSumstore store_last=sumstoreSer.findById(factNo, lastdate);
			//損益彙總(v_sumloss)
			if(loss!=null){
				list_loss.add(loss.getLossA001().doubleValue());
				list_loss.add(loss.getLossA002().doubleValue());
				list_loss.add(loss.getLossA003().doubleValue());
				list_loss.add(loss.getLossA004().doubleValue());
				list_loss.add(loss.getLossA005().doubleValue());
				list_loss.add(loss.getLossA006().doubleValue());
				list_loss_all.add(list_loss);
				
				//合計
				total_a001=total_a001+loss.getLossA001().doubleValue();
				total_a002=total_a002+loss.getLossA002().doubleValue();
				total_a003=total_a003+loss.getLossA003().doubleValue();
				total_a004=total_a004+loss.getLossA004().doubleValue();
				total_a005=total_a005+loss.getLossA005().doubleValue();
																				
			}else{
				list_loss_all.add(null);
			}
			//全廠原料庫存(v_sumstore)
			//找到上月平均單價
			if(store_last!=null){
				lag_b003=store_last.getStoreB003().doubleValue();
				lag_b008=store_last.getStoreB008().doubleValue();
			}
			//找到當月平均單價
			if(store!=null){
				b003=store.getStoreB003().doubleValue();
				b008=store.getStoreB008().doubleValue();
			}
			//漲福1
			store_b004=this.division((b003-lag_b003), lag_b003);			
			//漲福2
			store_b009=this.division((b008-lag_b008), lag_b008);
			if(store!=null){								
				list_store.add(store.getStoreB001().doubleValue());
				list_store.add(store.getStoreB002().doubleValue());
				list_store.add(store.getStoreB003().doubleValue());
				list_store.add(store_b004);
				list_store.add(store.getStoreB005().doubleValue());
				list_store.add(store.getStoreB006().doubleValue());
				list_store.add(store.getStoreB007().doubleValue());
				list_store.add(store.getStoreB008().doubleValue());
				list_store.add(store_b009);				
				list_store_all.add(list_store);
				
				//合計
				total_b001=total_b001+store.getStoreB001().doubleValue();
				total_b002=total_b002+store.getStoreB002().doubleValue();
				total_b005=total_b005+store.getStoreB005().doubleValue();
				total_b006=total_b006+store.getStoreB006().doubleValue();
				total_b007=total_b007+store.getStoreB007().doubleValue();
				
			}else{
				list_store_all.add(null);
			}
			
			//水電油(v_sumwlo)
			if(wlo!=null){
				if(wlo.getWloC002()==null){
					wlo.setWloC002(big);
				}
				if(wlo.getWloC003()==null){
					wlo.setWloC003(big);
				}
				if(wlo.getWloC004()==null){
					wlo.setWloC004(big);
				}
				if(wlo.getWloC005()==null){
					wlo.setWloC005(big);
				}
				if(wlo.getWloC006()==null){
					wlo.setWloC006(big);
				}
				if(wlo.getWloC007()==null){
					wlo.setWloC007(big);
				}
				if(wlo.getWloC008()==null){
					wlo.setWloC008(big);
				}
				if(wlo.getWloC009()==null){
					wlo.setWloC009(big);
				}
				if(wlo.getWloC010()==null){
					wlo.setWloC010(big);
				}
				if(wlo.getWloC011()==null){
					wlo.setWloC011(big);
				}
				if(wlo.getWloC012()==null){
					wlo.setWloC012(big);
				}
				if(wlo.getWloC013()==null){
					wlo.setWloC013(big);
				}
				if(wlo.getWloC014()==null){
					wlo.setWloC014(big);
				}
				if(wlo.getWloC015()==null){
					wlo.setWloC015(big);
				}
				if(wlo.getWloC016()==null){
					wlo.setWloC016(big);
				}
				if(wlo.getWloC017()==null){
					wlo.setWloC017(big);
				}
				list_wlo.add(wlo.getWloC001().doubleValue());
				list_wlo.add(wlo.getWloC002().doubleValue());
				list_wlo.add(wlo.getWloC003().doubleValue());
				list_wlo.add(wlo.getWloC004().doubleValue());
				list_wlo.add(wlo.getWloC005().doubleValue());
				list_wlo.add(wlo.getWloC006().doubleValue());
				list_wlo.add(wlo.getWloC007().doubleValue());
				list_wlo.add(wlo.getWloC008().doubleValue());
				list_wlo.add(wlo.getWloC009().doubleValue());
				list_wlo.add(wlo.getWloC010().doubleValue());
				list_wlo.add(wlo.getWloC011().doubleValue());
				list_wlo.add(wlo.getWloC012().doubleValue());
				list_wlo.add(wlo.getWloC013().doubleValue());
				list_wlo.add(wlo.getWloC014().doubleValue());
				list_wlo.add(wlo.getWloC015().doubleValue());
				list_wlo.add(wlo.getWloC016().doubleValue());
				list_wlo.add(wlo.getWloC017().doubleValue());
				list_wlo_all.add(list_wlo);
				
				//合計
				total_c001=total_c001+wlo.getWloC002().doubleValue();
				total_c002=total_c002+wlo.getWloC002().doubleValue();
				total_c004=total_c004+wlo.getWloC004().doubleValue();
				total_c006=total_c006+wlo.getWloC006().doubleValue();
				total_c008=total_c008+wlo.getWloC008().doubleValue();
				total_c010=total_c010+wlo.getWloC010().doubleValue();
				total_c012=total_c012+wlo.getWloC012().doubleValue();
				total_c014=total_c014+wlo.getWloC014().doubleValue();
				total_c016=total_c016+wlo.getWloC016().doubleValue();
				
			}else{
				list_wlo_all.add(null);
			}
			//下月請款狀況,下月生產狀況
			if(inv!=null){
				if(inv.getInvD001()==null){
					inv.setInvD001(big);
				}
				if(inv.getInvD002()==null){
					inv.setInvD002(big);
				}
				if(inv.getInvD003()==null){
					inv.setInvD003(big);
				}
				if(inv.getInvD004()==null){
					inv.setInvD004(big);
				}
				if(inv.getInvD005()==null){
					inv.setInvD005(big);
				}
				if(inv.getInvD006()==null){
					inv.setInvD006(big);
				}
				list_inv.add(inv.getInvD001().doubleValue());
				list_inv.add(inv.getInvD002().doubleValue());
				list_inv.add(inv.getInvD003().doubleValue());
				list_inv.add(inv.getInvD004().doubleValue());
				list_inv.add(inv.getInvD005().doubleValue());
				list_inv.add(inv.getInvD006().doubleValue());
				list_inv_all.add(list_inv);
				
				//合計
				total_d001=total_d001+inv.getInvD001().doubleValue();
				total_d002=total_d002+inv.getInvD002().doubleValue();
				total_d004=total_d004+inv.getInvD004().doubleValue();
				total_d005=total_d005+inv.getInvD005().doubleValue();
				
			}else{
				list_inv_all.add(null);				
			}
			//合計
			if(a==list_months.size()-1){				
				List<Double>list_loss_total=new ArrayList<Double>();
				List<Double>list_store_total=new ArrayList<Double>();
				List<Double>list_wlo_total=new ArrayList<Double>();
				List<Double>list_inv_total=new ArrayList<Double>();	
				int index=this.getMonths(yymm_begin, yymm_end).size();
				list_loss_total=this.findResult_loss(index, total_a001, total_a002, total_a003, total_a004, total_a005);							
				list_store_total=this.findResult_store(index, total_b001, total_b002, total_b005, total_b006, total_b007);							
				list_wlo_total=this.findResult_wlo(index, total_c001, total_c002, total_c004, total_c006, total_c008, total_c010, total_c012, total_c014, total_c016);				
				list_inv_total=this.findResult_inv(index, total_d001, total_d002, total_d004, total_d005);
				
				list_loss_all.add(list_loss_total);
				list_store_all.add(list_store_total);
				list_wlo_all.add(list_wlo_total);
				list_inv_all.add(list_inv_total);
			}
																					
		}//end for1
		
		//標題
		String factName=webFactSer.selByid(factNo);
		String title=factName+"("+factNo+")"+yymm_begin+"--"+yymm_end+"每月檢討項目統計表---全廠(各型態之合計)";
		
		
		/**
		 * 內容填充
		 */
		//填充標題
		HSSFRow row_title=sheet.createRow(0);
		HSSFCell cell_title=row_title.createCell(0);
		cell_title.setCellValue(title);
		cell_title.setCellStyle(cs_title);
		CellRangeAddress cra_title=new CellRangeAddress(0,(short)0,0,(short)list_column.size()-1);
		sheet.addMergedRegion(cra_title);
		for(int a=1;a<list_column.size();a++){
			HSSFCell cell=row_title.createCell(a);
			cell.setCellStyle(cs_title);
		}
		//填充表頭
		HSSFRow row_column=sheet.createRow(2);
		for(int b=0;b<list_column.size();b++){
			String column=list_column.get(b);
			HSSFCell cell=row_column.createCell(b);
			cell.setCellValue(column);
			cell.setCellStyle(cs_column);
		}
		//填充項目,細項,單位
		int length1=list_contentName_loss.size();
		int length2=list_contentName_store.size();
		int length3=list_contentName_wlo.size();
		int length4=list_contentName_inv.size();
		for(int b=0;b<list_column.size();b++){//start for1
			sheet.setColumnWidth(b, 3500);
			switch(b){
			case 0:
				//損益彙總(v_sumloss)			
				HSSFRow row_loss=sheet.createRow(3);
				HSSFCell cell_loss=row_loss.createCell(b);
				cell_loss.setCellValue("損益彙總");
				cell_loss.setCellStyle(cs);
				CellRangeAddress cra_loss=new CellRangeAddress(3,(short)(3+length1-1),0,(short)0);
				sheet.addMergedRegion(cra_loss);
				for(int c=1;c<length1;c++){
					HSSFRow row=sheet.createRow(3+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellStyle(cs);					
				}
				//全廠原料庫存(v_sumstore)
				HSSFRow row_store=sheet.createRow(3+length1);
				HSSFCell cell_store=row_store.createCell(b);
				cell_store.setCellValue("全廠原料庫存");
				cell_store.setCellStyle(cs);
				CellRangeAddress cra_store=new CellRangeAddress((3+length1),(short)(3+length1+length2-1),0,(short)0);
				sheet.addMergedRegion(cra_store);
				for(int c=1;c<length2;c++){
					HSSFRow row=sheet.createRow(3+length1+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellStyle(cs);					
				}
				//水電油(v_sumwlo)
				HSSFRow row_wlo=sheet.createRow(3+length1+length2);
				HSSFCell cell_wlo=row_wlo.createCell(b);
				cell_wlo.setCellValue("水電油");
				cell_wlo.setCellStyle(cs);
				CellRangeAddress cra_wlo=new CellRangeAddress((3+length1+length2),(short)(3+length1+length2+length3-1),0,(short)0);
				sheet.addMergedRegion(cra_wlo);
				for(int c=1;c<length3;c++){
					HSSFRow row=sheet.createRow(3+length1+length2+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellStyle(cs);					
				}
				//下月請款狀況
				HSSFRow row_inv1=sheet.createRow(3+length1+length2+length3);
				HSSFCell cell_inv1=row_inv1.createCell(b);
				cell_inv1.setCellValue("下月請款狀況");
				cell_inv1.setCellStyle(cs);
				CellRangeAddress cra_inv1=new CellRangeAddress((3+length1+length2+length3),(short)(3+length1+length2+length3+length4/2-1),0,(short)0);
				sheet.addMergedRegion(cra_inv1);
				for(int c=1;c<length4/2;c++){
					HSSFRow row=sheet.createRow(3+length1+length2+length3+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellStyle(cs);					
				}
				//下月生產狀況
				HSSFRow row_inv2=sheet.createRow(3+length1+length2+length3+length4/2);
				HSSFCell cell_inv2=row_inv2.createCell(b);
				cell_inv2.setCellValue("下月生產狀況");
				cell_inv2.setCellStyle(cs);
				CellRangeAddress cra_inv2=new CellRangeAddress((3+length1+length2+length3+length4/2),(short)(3+length1+length2+length3+length4-1),0,(short)0);
				sheet.addMergedRegion(cra_inv2);
				for(int c=1;c<length4/2;c++){
					HSSFRow row=sheet.createRow(3+length1+length2+length3+length4/2+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellStyle(cs);					
				}
				break;
			case 1:
				//損益彙總(v_sumloss)			
				for(int c=0;c<length1;c++){
					String content=list_contentName_loss.get(c);
					HSSFRow row=sheet.getRow(3+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellValue(content);
					cell.setCellStyle(cs);
				}
				//全廠原料庫存(v_sumstore)
				for(int c=0;c<length2;c++){
					String content=list_contentName_store.get(c);
					HSSFRow row=sheet.getRow(3+length1+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellValue(content);
					cell.setCellStyle(cs);
				}
				//水電油(v_sumwlo)
				for(int c=0;c<length3;c++){
					String content=list_contentName_wlo.get(c);
					HSSFRow row=sheet.getRow(3+length1+length2+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellValue(content);
					cell.setCellStyle(cs);
				}
				//下月請款狀況
				for(int c=0;c<length4/2;c++){
					String content=list_contentName_inv.get(c);
					HSSFRow row=sheet.getRow(3+length1+length2+length3+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellValue(content);
					cell.setCellStyle(cs);
				}
				//下月生產狀況
				for(int c=0;c<length4/2;c++){
					String content=list_contentName_inv.get(c+3);
					HSSFRow row=sheet.getRow(3+length1+length2+length3+length4/2+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellValue(content);
					cell.setCellStyle(cs);
				}
				break;
			case 2:
				//損益彙總(v_sumloss)				
				for(int c=0;c<length1;c++){
					String content=list_unit_loss.get(c);
					HSSFRow row=sheet.getRow(3+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellValue(content);
					cell.setCellStyle(cs);
				}
				//全廠原料庫存(v_sumstore)
				for(int c=0;c<length2;c++){
					String content=list_unit_store.get(c);
					HSSFRow row=sheet.getRow(3+length1+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellValue(content);
					cell.setCellStyle(cs);
				}
				//水電油(v_sumwlo)
				for(int c=0;c<length3;c++){
					String content=list_unit_wlo.get(c);
					HSSFRow row=sheet.getRow(3+length1+length2+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellValue(content);
					cell.setCellStyle(cs);
				}
				//下月請款狀況
				for(int c=0;c<length4/2;c++){
					String content=list_unit_inv.get(c);
					HSSFRow row=sheet.getRow(3+length1+length2+length3+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellValue(content);
					cell.setCellStyle(cs);
				}
				//下月生產狀況
				for(int c=0;c<length4/2;c++){
					String content=list_unit_inv.get(c+3);
					HSSFRow row=sheet.getRow(3+length1+length2+length3+length4/2+c);
					HSSFCell cell=row.createCell(b);
					cell.setCellValue(content);
					cell.setCellStyle(cs);
				}
				break;
				default:
					//臨時樣式
					HSSFCellStyle cs_temp=wb.createCellStyle();
					//損益彙總(v_sumloss)
					List<Double>list_loss=list_loss_all.get(b);
					if(list_loss!=null){						
						for(int c=0;c<list_loss.size();c++){
							Double db=list_loss.get(c);
							HSSFRow row=sheet.getRow(3+c);
							HSSFCell cell=row.createCell(b);
							cell.setCellValue(db);
							cell.setCellStyle(cs_poi2);
						}
					}else{
						for(int c=0;c<length1;c++){
							HSSFRow row=sheet.getRow(3+c);
							HSSFCell cell=row.createCell(b);
							cell.setCellValue("無數據");
							cell.setCellStyle(cs);
						}
					}
					//全廠原料庫存(v_sumstore)
					List<Double>list_store=list_store_all.get(b);
					if(list_store!=null){						
						for(int c=0;c<list_store.size();c++){
							if(c==3||c==8){
								cs_temp=cs_percent;
							}else{
								cs_temp=cs_poi2;
							}
							Double db=list_store.get(c);
							HSSFRow row=sheet.getRow(3+length1+c);
							HSSFCell cell=row.createCell(b);
							//合計的漲福沒有算出來
							if(b==list_column.size()-1&&(c==3||c==8)){
								cell.setCellValue("----");
								cell.setCellStyle(cs_red);
							}else{
								cell.setCellValue(db);
								cell.setCellStyle(cs_temp);
							}							
							
						}
					}else{
						for(int c=0;c<length2;c++){
							HSSFRow row=sheet.getRow(3+length1+c);
							HSSFCell cell=row.createCell(b);
							cell.setCellValue("無數據");
							cell.setCellStyle(cs);
						}
					}
					//水電油(v_sumwlo)
					List<Double>list_wlo=list_wlo_all.get(b);
					if(list_wlo!=null){						
						for(int c=0;c<list_wlo.size();c++){							
							Double db=list_wlo.get(c);
							HSSFRow row=sheet.getRow(3+length1+length2+c);
							HSSFCell cell=row.createCell(b);
							if(c==2||c==4||c==6||c==8||c==10||c==12||c==14||c==16){
								cs_temp=cs_poi4;
							}else{
								cs_temp=cs_poi2;
							}
							cell.setCellValue(db);
							cell.setCellStyle(cs_temp);
						}
					}else{
						for(int c=0;c<length3;c++){
							HSSFRow row=sheet.getRow(3+length1+length2+c);
							HSSFCell cell=row.createCell(b);
							cell.setCellValue("無數據");
							cell.setCellStyle(cs);
						}
					}
					//下月請款狀況,生產狀況
					List<Double>list_inv=list_inv_all.get(b);
					if(list_inv!=null){						
						for(int c=0;c<list_inv.size();c++){
							if(c==5){
								cs_temp=cs_percent;
							}else{
								cs_temp=cs_poi2;
							}
							Double db=list_inv.get(c);
							HSSFRow row=sheet.getRow(3+length1+length2+length3+c);
							HSSFCell cell=row.createCell(b);
							cell.setCellValue(db);
							cell.setCellStyle(cs_temp);
						}
					}else{
						for(int c=0;c<length4;c++){
							HSSFRow row=sheet.getRow(3+length1+length2+length3+c);
							HSSFCell cell=row.createCell(b);
							cell.setCellValue("無數據");
							cell.setCellStyle(cs);
						}
					}									
			}
		}//end for1
		//OutputStream os=new FileOutputStream("d:/vsumall.xls");
		ServletOutputStream os=response.getOutputStream();
		response.setContentType("application/vnd.ms-excel");
		title=title+".xls";
		String fileName="";
		int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");
		if(msie>0){
			fileName=java.net.URLEncoder.encode(title,"utf-8");
		}else{
			fileName=new String(title.getBytes("utf-8"),"iso8859-1");
		}
		response.setHeader("Content-disposition","attachment;filename="+fileName);
		wb.write(os);
		os.close();
	}
}
