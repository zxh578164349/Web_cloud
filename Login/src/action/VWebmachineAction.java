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
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import services.*;


import com.opensymphony.xwork2.ActionSupport;

import entity.*;


public class VWebmachineAction extends ActionSupport implements ServletResponseAware{
	private IVWebmachineServices vwebmachineser;//機台回轉數
	private IVWebOutoutinvServices vinvser;//生產與請款
	private IVSumWebmix1Services vmix1ser;//出貨與退貨
	private ISumWebYieldDataServices sumydateSer;//出貨與退貨(修改)
	private IVWebpersonServices vpersonser;//人員效能
	private IVWebGwServices vgwser;//關務損耗
	private IVWebSideServices vsideser;//邊料不良重量分析
	private IVWebStoreServices vstoreser;//庫存
	private IVWebwloServices vwloser;//水電油
	private IWebBackFeedServices feedSer;//回頭料
	private IVWebcostServices vwebcostSer;//色料回收粉
	private IWebFactServices webFactSer;
	private String year;
	private String factNo;
	private String yymm;
	private String yymm_begin;
	private String yymm_end;
	private javax.servlet.http.HttpServletResponse response;
	private IVWebmachineEveServices machineeveSer;
	private IVWeboutputinvEveServices inveveSer;
	private IVWebpersonEveServices personeveSer;
	private IVWebgwEveServices gweveSer;
	private IVWebsideEveServices sideeveSer;
	private IVWebstoreEveServices storeeveSer;
	private IVWebwloEveServices wloeveSer;
	private IVWebcostEveServices vwebcosteveSer;
	private List<String>list_factcode;//所選的廠別狀態
	private List<String>list_factno;//所選的所有的廠別（包含各個廠別狀態）
	
	
	public List<String> getList_factno() {
		return list_factno;
	}


	public void setList_factno(List<String> list_factno) {
		this.list_factno = list_factno;
	}


	public String getYymm() {
		return yymm;
	}


	public void setYymm(String yymm) {
		this.yymm = yymm;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
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


	public void setVwebmachineser(IVWebmachineServices vwebmachineser) {
		this.vwebmachineser = vwebmachineser;
	}
	
	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}
	


	public void setVinvser(IVWebOutoutinvServices vinvser) {
		this.vinvser = vinvser;
	}


	public void setVmix1ser(IVSumWebmix1Services vmix1ser) {
		this.vmix1ser = vmix1ser;
	}


	public void setVpersonser(IVWebpersonServices vpersonser) {
		this.vpersonser = vpersonser;
	}


	public void setVgwser(IVWebGwServices vgwser) {
		this.vgwser = vgwser;
	}


	public void setVsideser(IVWebSideServices vsideser) {
		this.vsideser = vsideser;
	}


	public void setVstoreser(IVWebStoreServices vstoreser) {
		this.vstoreser = vstoreser;
	}


	public void setVwloser(IVWebwloServices vwloser) {
		this.vwloser = vwloser;
	}


	public void setFeedSer(IWebBackFeedServices feedSer) {
		this.feedSer = feedSer;
	}
	


	public void setMachineeveSer(IVWebmachineEveServices machineeveSer) {
		this.machineeveSer = machineeveSer;
	}


	public void setInveveSer(IVWeboutputinvEveServices inveveSer) {
		this.inveveSer = inveveSer;
	}


	public void setPersoneveSer(IVWebpersonEveServices personeveSer) {
		this.personeveSer = personeveSer;
	}


	public void setGweveSer(IVWebgwEveServices gweveSer) {
		this.gweveSer = gweveSer;
	}


	public void setSideeveSer(IVWebsideEveServices sideeveSer) {
		this.sideeveSer = sideeveSer;
	}


	public void setStoreeveSer(IVWebstoreEveServices storeeveSer) {
		this.storeeveSer = storeeveSer;
	}


	public void setWloeveSer(IVWebwloEveServices wloeveSer) {
		this.wloeveSer = wloeveSer;
	}
	
	public void setSumydateSer(ISumWebYieldDataServices sumydateSer) {
		this.sumydateSer = sumydateSer;
	}
	


	public void setVwebcostSer(IVWebcostServices vwebcostSer) {
		this.vwebcostSer = vwebcostSer;
	}


	public void setVwebcosteveSer(IVWebcostEveServices vwebcosteveSer) {
		this.vwebcosteveSer = vwebcosteveSer;
	}


	public void print_fact() throws IOException, ParseException{
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
		//無邊框樣式
		HSSFCellStyle cs_noborder=wb.createCellStyle();
		cs_noborder.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_noborder.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		//數字格式
		HSSFDataFormat format_hf=wb.createDataFormat();
		HSSFCellStyle cs_percent2=wb.createCellStyle();
		cs_percent2.setDataFormat(format_hf.getFormat("0.00%"));
		cs_percent2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_percent2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_percent2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_percent2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_percent2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_percent2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle cs_percent1=wb.createCellStyle();
		cs_percent1.setDataFormat(format_hf.getFormat("0.0%"));
		cs_percent1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_percent1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_percent1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_percent1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_percent1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_percent1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle cs_percent=wb.createCellStyle();
		cs_percent.setDataFormat(format_hf.getFormat("0%"));
		cs_percent.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_percent.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_percent.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle cs_poi2=wb.createCellStyle();
		cs_poi2.setDataFormat(format_hf.getFormat("#,###,0.00"));
		cs_poi2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle cs_poi4=wb.createCellStyle();
		cs_poi4.setDataFormat(format_hf.getFormat("#,###,0.0000"));
		cs_poi4.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi4.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle cs_poi=wb.createCellStyle();
		cs_poi.setDataFormat(format_hf.getFormat("#,###,0"));
		cs_poi.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle cs_poi1=wb.createCellStyle();
		cs_poi1.setDataFormat(format_hf.getFormat("#,###,0.0"));
		cs_poi1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		/**		
		 * 數據源
		 * (1)機台回轉數
		 */
		//數據內容名稱和單位
		List<String>list_contentName_machine=new ArrayList<String>();
		List<String>list_unit_machine=new ArrayList<String>();
		list_contentName_machine.add("全廠孔位數");list_unit_machine.add("孔");
		list_contentName_machine.add("上班天數");list_unit_machine.add("天");
		list_contentName_machine.add("總上模數");list_unit_machine.add("模");
		list_contentName_machine.add("平均一天上模數");list_unit_machine.add("模/天");
		list_contentName_machine.add("機台利用率");list_unit_machine.add("%");
		list_contentName_machine.add("平均料重");list_unit_machine.add("g/雙");
		list_contentName_machine.add("平均淨重");list_unit_machine.add("g/雙");
		list_contentName_machine.add("標準回轉數");list_unit_machine.add("回");
		list_contentName_machine.add("實際回轉數");list_unit_machine.add("回");
		list_contentName_machine.add("回轉數差異");list_unit_machine.add("回");
		list_contentName_machine.add("達成率(%)");list_unit_machine.add("%");
		
		/**
		 * 數據源
		 * (2)生產與請款狀況
		 */
		        //數據內容名稱和單位
				List<String>list_contentName_inv=new ArrayList<String>();
				List<String>list_unit_inv=new ArrayList<String>();
				list_contentName_inv.add("生產雙數");list_unit_inv.add("雙");
				list_contentName_inv.add("請款雙數");list_unit_inv.add("雙");
				list_contentName_inv.add("銷貨收入");list_unit_inv.add("USD");
				list_contentName_inv.add("平均單價");list_unit_inv.add("USD/雙");
																	
			/**
			* 數據源
			* (3)出貨與退貨
			*/
			//數據內容名稱和單位
			List<String>list_contentName_mix1=new ArrayList<String>();
			List<String>list_unit_mix1=new ArrayList<String>();
			list_contentName_mix1.add("出貨數");list_unit_mix1.add("雙");
			list_contentName_mix1.add("退貨數");list_unit_mix1.add("雙");
			list_contentName_mix1.add("退貨率");list_unit_mix1.add("%");
			
			/**
			* 數據源
			* (3)人員效能
			*/
			//數據內容名稱和單位
			List<String>list_contentName_person=new ArrayList<String>();
			List<String>list_unit_person=new ArrayList<String>();
			list_contentName_person.add("標準產量");list_unit_person.add("模");
			list_contentName_person.add("實際產量");list_unit_person.add("模");
			list_contentName_person.add("達成率(%)");list_unit_person.add("%");
			list_contentName_person.add("直工人數");list_unit_person.add("人");
			list_contentName_person.add("間工人數");list_unit_person.add("人");
			list_contentName_person.add("全廠總人數");list_unit_person.add("人");
			list_contentName_person.add("直间比");list_unit_person.add("--");
			list_contentName_person.add("直工人均产能");list_unit_person.add("模/人");
			list_contentName_person.add("全廠人均产能");list_unit_person.add("模/人");
			list_contentName_person.add("全廠人均时产能");list_unit_person.add("模/H");
			list_contentName_person.add("直工離職率(%)");list_unit_person.add("%");
			list_contentName_person.add("全廠離職率(%)");list_unit_person.add("%");
			list_contentName_person.add("直接薪資(含加班費+獎金)");list_unit_person.add("USD");
			list_contentName_person.add("間接薪資(含加班費+獎金)");list_unit_person.add("USd");
			list_contentName_person.add("總薪資(含加班費+獎金)");list_unit_person.add("USD");
			list_contentName_person.add("總工時");list_unit_person.add("H");
			list_contentName_person.add("每雙薪資單耗");list_unit_person.add("USD/雙");
			list_contentName_person.add("直工加班費");list_unit_person.add("USD");
			list_contentName_person.add("間工加班費");list_unit_person.add("USD");
			list_contentName_person.add("總加班費");list_unit_person.add("USD");
			list_contentName_person.add("直工人均薪資");list_unit_person.add("USD/人");
			list_contentName_person.add("間工人均薪資");list_unit_person.add("USD/人");
			list_contentName_person.add("直工占薪資比例");list_unit_person.add("%");
			list_contentName_person.add("間工占薪資比例");list_unit_person.add("%");
			list_contentName_person.add("全廠人均薪資");list_unit_person.add("USD/人");
			
			/**
			* 數據源
			* (4)關務損耗
			*/
			//數據內容名稱和單位
			List<String>list_contentName_gw=new ArrayList<String>();
			List<String>list_unit_gw=new ArrayList<String>();
			list_contentName_gw.add("當月總耗用原料");list_unit_gw.add("kg");
			list_contentName_gw.add("當月標准用量");list_unit_gw.add("kg");
			list_contentName_gw.add("總損耗量");list_unit_gw.add("kg");
			list_contentName_gw.add("總損耗率");list_unit_gw.add("%");
			list_contentName_gw.add("無形損耗");list_unit_gw.add("kg");
			list_contentName_gw.add("無形損耗率");list_unit_gw.add("%");
			
			/**
			* 數據源
			* (5)邊粒不良重量分析
			*/
			//數據內容名稱和單位
			List<String>list_contentName_side=new ArrayList<String>();
			List<String>list_unit_side=new ArrayList<String>();
			list_contentName_side.add("粗坯平均單價");list_unit_side.add("USD/KG");
			list_contentName_side.add("邊料(kg)");list_unit_side.add("KG");
			list_contentName_side.add("平均邊料重(g)");list_unit_side.add("G/雙");
			list_contentName_side.add("邊料%");list_unit_side.add("%");
			list_contentName_side.add("邊料金額");list_unit_side.add("USD");
			list_contentName_side.add("不良品雙數");list_unit_side.add("雙");
			list_contentName_side.add("不良品重量");list_unit_side.add("KG");
			list_contentName_side.add("不良重量不良率");list_unit_side.add("%");
			list_contentName_side.add("不良品金額");list_unit_side.add("USD");
			list_contentName_side.add("其它報廢重量");list_unit_side.add("KG");
			list_contentName_side.add("其它報廢金額");list_unit_side.add("USD");
			list_contentName_side.add("總報廢重量");list_unit_side.add("KG");
			list_contentName_side.add("總報廢金額");list_unit_side.add("USD");
			
			/**
			* 數據源
			* (6)庫存
			*/
			//數據內容名稱和單位
			List<String>list_contentName_store=new ArrayList<String>();
			List<String>list_unit_store=new ArrayList<String>();
			list_contentName_store.add("上月成倉庫存數");list_unit_store.add("雙");
			list_contentName_store.add("上月無訂單庫存");list_unit_store.add("雙");
			list_contentName_store.add("上月整理庫存");list_unit_store.add("雙");
			list_contentName_store.add("上月已出未請數");list_unit_store.add("雙");
			list_contentName_store.add("上月已請未出貨");list_unit_store.add("雙");
			list_contentName_store.add("前倉入庫折算後可請款生產雙數");list_unit_store.add("雙");
			list_contentName_store.add("小計-本月可請款數(A)");list_unit_store.add("雙");
			list_contentName_store.add("本月成倉庫存數");list_unit_store.add("雙");
			list_contentName_store.add("本月無訂單庫存");list_unit_store.add("雙");
			list_contentName_store.add("本月整理庫存");list_unit_store.add("雙");
			list_contentName_store.add("本月已出未請數");list_unit_store.add("雙");
			list_contentName_store.add("本月已請未出貨");list_unit_store.add("雙");
			list_contentName_store.add("小計-本月未請款數(B)");list_unit_store.add("雙");
			list_contentName_store.add("本月應請款數(A-B)");list_unit_store.add("雙");
			list_contentName_store.add("本月實際請款數");list_unit_store.add("雙");
			list_contentName_store.add("生產與請款差異數");list_unit_store.add("雙");
			list_contentName_store.add("廠客補、樣品未請款數");list_unit_store.add("雙");
			list_contentName_store.add("無形差異");list_unit_store.add("雙");			
			
			/**
			* 數據源
			* (7)水電油
			*/
			//數據內容名稱和單位
			List<String>list_contentName_wlo=new ArrayList<String>();
			List<String>list_unit_wlo=new ArrayList<String>();
			list_contentName_wlo.add("生產模數");list_unit_wlo.add("雙");
			list_contentName_wlo.add("水用量");list_unit_wlo.add("噸");
			list_contentName_wlo.add("用水單耗");list_unit_wlo.add("噸/模");
			list_contentName_wlo.add("用水金額");list_unit_wlo.add("USD");
			list_contentName_wlo.add("用水金額單耗(模)");list_unit_wlo.add("USD/模");
			list_contentName_wlo.add("用電量");list_unit_wlo.add("度");
			list_contentName_wlo.add("用電單耗(模)");list_unit_wlo.add("度/模");
			list_contentName_wlo.add("用電金額(USD)");list_unit_wlo.add("USD");
			list_contentName_wlo.add("用電金額單耗(模)");list_unit_wlo.add("USD/模");
			list_contentName_wlo.add("蒸氣用量(T)");list_unit_wlo.add("噸");
			list_contentName_wlo.add("用蒸氣單耗(模)");list_unit_wlo.add("噸/模");
			list_contentName_wlo.add("用蒸氣金額(USD)");list_unit_wlo.add("USD");
			list_contentName_wlo.add("用蒸氣金額單耗(模)");list_unit_wlo.add("USD/模");
			list_contentName_wlo.add("柴油用量(公升)");list_unit_wlo.add("公升");
			list_contentName_wlo.add("用油單耗(模)");list_unit_wlo.add("公升/模");
			list_contentName_wlo.add("柴油金額(USD)");list_unit_wlo.add("USD");
			list_contentName_wlo.add("用油金額單耗(模)");list_unit_wlo.add("USD/模");									
			
			/**
			* 數據源
			* (8)回頭料
			*/
			//數據內容名稱和單位
			List<String>list_contentName_back=new ArrayList<String>();
			List<String>list_unit_back=new ArrayList<String>();
			list_contentName_back.add("粗坯用量");list_unit_back.add("kg");
			list_contentName_back.add("回頭料重量");list_unit_back.add("kg");
			list_contentName_back.add("回頭料%");list_unit_back.add("%");
			list_contentName_back.add("油壓退料重量");list_unit_back.add("kg");
			list_contentName_back.add("油壓退料%");list_unit_back.add("%");
			list_contentName_back.add("合計重量");list_unit_back.add("kg");
			list_contentName_back.add("回頭率%");list_unit_back.add("%");
			
			/**
			 * 數據源
			 * (9)色料回收粉
			 */
			//數據內容名稱和單位
			List<String>list_contentName_webcost=new ArrayList<String>();
			List<String>list_unit_webcost=new ArrayList<String>();
			list_contentName_webcost.add("色料用量");list_unit_webcost.add("KG");
			list_contentName_webcost.add("色料單耗");list_unit_webcost.add("g/模");
			list_contentName_webcost.add("藥品用量");list_unit_webcost.add("KG");
			list_contentName_webcost.add("藥品單耗");list_unit_webcost.add("g/模");
			list_contentName_webcost.add("離型劑金額");list_unit_webcost.add("USD");
			list_contentName_webcost.add("離型劑單耗");list_unit_webcost.add("USD/模");
			list_contentName_webcost.add("白色回收粉");list_unit_webcost.add("KG");
			list_contentName_webcost.add("黑色回收粉");list_unit_webcost.add("KG");
			list_contentName_webcost.add("生膠回收粉");list_unit_webcost.add("KG");
			list_contentName_webcost.add("灰色回收粉");list_unit_webcost.add("KG");
			list_contentName_webcost.add("其它回收粉");list_unit_webcost.add("KG");
			
			
		
		
		//表頭內容
		List<String>list_column=new ArrayList<String>();
		list_column.add("項目");
		list_column.add("細項");
		list_column.add("單位");
		list_column.add("1月");//3
		list_column.add("2月");//4
		list_column.add("3月");//5
		list_column.add("Q1");//--6
		list_column.add("4月");//7
		list_column.add("5月");//8
		list_column.add("6月");//9
		list_column.add("Q2");//--10
		list_column.add("上半年");//--11
		list_column.add("7月");//12
		list_column.add("8月");//13
		list_column.add("9月");//14
		list_column.add("Q3");//--15
		list_column.add("10月");//16
		list_column.add("11月");//17
		list_column.add("12月");//18
		list_column.add("Q4");//--19		
		list_column.add("下半年");//--20
		list_column.add("全年");//--21
		//list_column.add("合計");//--22(不要)
		
		//廠名標題名稱
		String factName=webFactSer.selByid(factNo);
		String title=factName+"("+factNo+")每月檢討統計表";
		
		//數據內容
		//factNo="XS";
		//year="2014";
		//獲取每個廠的所有廠別狀態
		List<WebFact>list_fact=webFactSer.findFactById_showA(factNo);
		
		//一個廠中所有廠別狀態的數據(例如：632包括RB,MD)
		//如下多重集合的解釋:
		//(1)List<Double>:一個列的數據(將WWebmachine每個屬性封裝在一個List<Double>,便於填充數據)  ---單元集合(list3)
		//(2)List<List<Double>>一個表的數據(對應多個廠別狀態)                                    ---中間集合(list2)
		//(3)List<List<List<Double>>>多個表的數據(對應多個廠別狀態)                              ---總集合(list1)
		
		List<List<List<Double>>>list_all_webmachine=new ArrayList<List<List<Double>>>();//---總集合(list1)
		List<List<List<Double>>>list_all_inv=new ArrayList<List<List<Double>>>();
		List<List<List<Double>>>list_all_mix1=new ArrayList<List<List<Double>>>();
		List<List<List<Double>>>list_all_person=new ArrayList<List<List<Double>>>();
		List<List<List<Double>>>list_all_gw=new ArrayList<List<List<Double>>>();
		List<List<List<Double>>>list_all_side=new ArrayList<List<List<Double>>>();
		List<List<List<Double>>>list_all_store=new ArrayList<List<List<Double>>>();
		List<List<List<Double>>>list_all_wlo=new ArrayList<List<List<Double>>>();
		List<List<List<Double>>>list_all_back=new ArrayList<List<List<Double>>>();
		List<List<List<Double>>>list_all_webcost=new ArrayList<List<List<Double>>>();
		for(int z=0;z<list_fact.size();z++){//start for1_1
			WebFact fact=list_fact.get(z);
			String factCode=fact.getId().getFactArea();
			List<List<Double>>list_machine=new ArrayList<List<Double>>();//---中間集合(list2)			
			List<List<Double>>list_inv=new ArrayList<List<Double>>();//---中間集合(list2)
			List<List<Double>>list_mix1=new ArrayList<List<Double>>();//---中間集合(list2)
			List<List<Double>>list_person=new ArrayList<List<Double>>();//---中間集合(list2)
			List<List<Double>>list_gw=new ArrayList<List<Double>>();//---中間集合(list2)
			List<List<Double>>list_side=new ArrayList<List<Double>>();//---中間集合(list2)
			List<List<Double>>list_store=new ArrayList<List<Double>>();//---中間集合(list2)
			List<List<Double>>list_wlo=new ArrayList<List<Double>>();//---中間集合(list2)
			List<List<Double>>list_back=new ArrayList<List<Double>>();//---中間集合(list2)
			List<List<Double>>list_webcost=new ArrayList<List<Double>>();//---中間集合(list2)
			
			//注意:中間集合(list2)總共有23項,前面3項(項目,細項,單位)不需要,為了補全數量,所以添加3個空元素
			list_machine.add(null);
			list_machine.add(null);
			list_machine.add(null);
			
			list_inv.add(null);
			list_inv.add(null);
			list_inv.add(null);
			
			list_mix1.add(null);
			list_mix1.add(null);
			list_mix1.add(null);
			
			list_person.add(null);
			list_person.add(null);
			list_person.add(null);
			
			list_gw.add(null);
			list_gw.add(null);
			list_gw.add(null);
			
			list_side.add(null);
			list_side.add(null);
			list_side.add(null);
			
			list_store.add(null);
			list_store.add(null);
			list_store.add(null);
			
			list_wlo.add(null);
			list_wlo.add(null);
			list_wlo.add(null);
			
			list_back.add(null);
			list_back.add(null);
			list_back.add(null);
			
			list_webcost.add(null);
			list_webcost.add(null);
			list_webcost.add(null);
			
			/**
			 * 機台回轉數
			 */
			
			//第一季度
			Double machineEve_q1_sumWorkday=0.0;Double machineEve_q1_sumEverydemo=0.0;
			Double machineEve_q1_sumStandarddemo=0.0;Double machineEve_q1_sumActualdemo=0.0;
			Double machineEve_q1_sumActualpairs=0.0;Double machineEve_q1_avgbuttomweight=0.0;
			Double machineEve_q1_avgbuttomweight2=0.0;Double machineEve_q1_hole=0.0;
			
			//第二季度
			Double machineEve_q2_sumWorkday=0.0;Double machineEve_q2_sumEverydemo=0.0;
			Double machineEve_q2_sumStandarddemo=0.0;Double machineEve_q2_sumActualdemo=0.0;
			Double machineEve_q2_sumActualpairs=0.0;Double machineEve_q2_avgbuttomweight=0.0;
			Double machineEve_q2_avgbuttomweight2=0.0;Double machineEve_q2_hole=0.0;
			
			//第三季度
			Double machineEve_q3_sumWorkday=0.0;Double machineEve_q3_sumEverydemo=0.0;
			Double machineEve_q3_sumStandarddemo=0.0;Double machineEve_q3_sumActualdemo=0.0;
			Double machineEve_q3_sumActualpairs=0.0;Double machineEve_q3_avgbuttomweight=0.0;
			Double machineEve_q3_avgbuttomweight2=0.0;Double machineEve_q3_hole=0.0;
			
			//第四季度
			Double machineEve_q4_sumWorkday=0.0;Double machineEve_q4_sumEverydemo=0.0;
			Double machineEve_q4_sumStandarddemo=0.0;Double machineEve_q4_sumActualdemo=0.0;
			Double machineEve_q4_sumActualpairs=0.0;Double machineEve_q4_avgbuttomweight=0.0;
			Double machineEve_q4_avgbuttomweight2=0.0;Double machineEve_q4_hole=0.0;
			
			//上半年
			Double machineEve_half1_sumWorkday=0.0;Double machineEve_half1_sumEverydemo=0.0;
			Double machineEve_half1_sumStandarddemo=0.0;Double machineEve_half1_sumActualdemo=0.0;
			Double machineEve_half1_sumActualpairs=0.0;Double machineEve_half1_avgbuttomweight=0.0;
			Double machineEve_half1_avgbuttomweight2=0.0;Double machineEve_half1_hole=0.0;
			
			//下半年
			Double machineEve_half2_sumWorkday=0.0;Double machineEve_half2_sumEverydemo=0.0;
			Double machineEve_half2_sumStandarddemo=0.0;Double machineEve_half2_sumActualdemo=0.0;
			Double machineEve_half2_sumActualpairs=0.0;Double machineEve_half2_avgbuttomweight=0.0;
			Double machineEve_half2_avgbuttomweight2=0.0;Double machineEve_half2_hole=0.0;
			//全年
			Double machineEve_year_sumWorkday=0.0;Double machineEve_year_sumEverydemo=0.0;
			Double machineEve_year_sumStandarddemo=0.0;Double machineEve_year_sumActualdemo=0.0;
			Double machineEve_year_sumActualpairs=0.0;Double machineEve_year_avgbuttomweight=0.0;
			Double machineEve_year_avgbuttomweight2=0.0;Double machineEve_year_hole=0.0;
			
			/**
			 * 生產與請款狀況
			 */			
			//第一季度
			Double invEve_q1_sumActualpairs=0.0;Double invEve_q1_invcount=0.0;
			Double invEve_q1_paypair=0.0;
			//第二季度
			Double invEve_q2_sumActualpairs=0.0;Double invEve_q2_invcount=0.0;
			Double invEve_q2_paypair=0.0;
			//第三季度
			Double invEve_q3_sumActualpairs=0.0;Double invEve_q3_invcount=0.0;
			Double invEve_q3_paypair=0.0;
			//第四季度
			Double invEve_q4_sumActualpairs=0.0;Double invEve_q4_invcount=0.0;
			Double invEve_q4_paypair=0.0;
			//上半年
			Double invEve_half1_sumActualpairs=0.0;Double invEve_half1_invcount=0.0;
			Double invEve_half1_paypair=0.0;				
			//下半年
			Double invEve_half2_sumActualpairs=0.0;Double invEve_half2_invcount=0.0;
			Double invEve_half2_paypair=0.0;
			//全年
			Double invEve_year_sumActualpairs=0.0;Double invEve_year_invcount=0.0;
			Double invEve_year_paypair=0.0;
			/**
			 * 出貨與退貨
			 */			
			//第一季度
			Double mix1Eve_q1_sumoutnum=0.0;Double mix1Eve_q1_sumbacknum=0.0;
			//第二季度
			Double mix1Eve_q2_sumoutnum=0.0;Double mix1Eve_q2_sumbacknum=0.0;
			//第三季度
			Double mix1Eve_q3_sumoutnum=0.0;Double mix1Eve_q3_sumbacknum=0.0;
			//第四季度
			Double mix1Eve_q4_sumoutnum=0.0;Double mix1Eve_q4_sumbacknum=0.0;
			//上半年
			Double mix1Eve_half1_sumoutnum=0.0;Double mix1Eve_half1_sumbacknum=0.0;			
			//下半年
			Double mix1Eve_half2_sumoutnum=0.0;Double mix1Eve_half2_sumbacknum=0.0;
			//全年
			Double mix1Eve_year_sumoutnum=0.0;Double mix1Eve_year_sumbacknum=0.0;
			/**
			 * 人員效能
			 */			
			//第一季度
			Double personEve_q1_personzg=0.0;Double personEve_q1_personjg=0.0;
			Double personEve_q1_timezg=0.0;Double personEve_q1_timejg=0.0;
			Double personEve_q1_addtimezg=0.0;Double personEve_q1_addtimejg=0.0;
			Double personEve_q1_leavenumzg=0.0;Double personEve_q1_leavenumjg=0.0;
			Double personEve_q1_wagezgUsd=0.0;Double personEve_q1_wagejgUsd=0.0;
			Double personEve_q1_addmoneyzg=0.0;Double personEve_q1_addmoneyjg=0.0;
			Double personEve_q1_sumStandarddemo=0.0;Double personEve_q1_sumActualdemo=0.0;
			Double personEve_q1_sumActualpairs=0.0;
			//第二季度
			Double personEve_q2_personzg=0.0;Double personEve_q2_personjg=0.0;
			Double personEve_q2_timezg=0.0;Double personEve_q2_timejg=0.0;
			Double personEve_q2_addtimezg=0.0;Double personEve_q2_addtimejg=0.0;
			Double personEve_q2_leavenumzg=0.0;Double personEve_q2_leavenumjg=0.0;
			Double personEve_q2_wagezgUsd=0.0;Double personEve_q2_wagejgUsd=0.0;
			Double personEve_q2_addmoneyzg=0.0;Double personEve_q2_addmoneyjg=0.0;
			Double personEve_q2_sumStandarddemo=0.0;Double personEve_q2_sumActualdemo=0.0;
			Double personEve_q2_sumActualpairs=0.0;
			//第三季度
			Double personEve_q3_personzg=0.0;Double personEve_q3_personjg=0.0;
			Double personEve_q3_timezg=0.0;Double personEve_q3_timejg=0.0;
			Double personEve_q3_addtimezg=0.0;Double personEve_q3_addtimejg=0.0;
			Double personEve_q3_leavenumzg=0.0;Double personEve_q3_leavenumjg=0.0;
			Double personEve_q3_wagezgUsd=0.0;Double personEve_q3_wagejgUsd=0.0;
			Double personEve_q3_addmoneyzg=0.0;Double personEve_q3_addmoneyjg=0.0;
			Double personEve_q3_sumStandarddemo=0.0;Double personEve_q3_sumActualdemo=0.0;
			Double personEve_q3_sumActualpairs=0.0;
			//第四季度
			Double personEve_q4_personzg=0.0;Double personEve_q4_personjg=0.0;
			Double personEve_q4_timezg=0.0;Double personEve_q4_timejg=0.0;
			Double personEve_q4_addtimezg=0.0;Double personEve_q4_addtimejg=0.0;
			Double personEve_q4_leavenumzg=0.0;Double personEve_q4_leavenumjg=0.0;
			Double personEve_q4_wagezgUsd=0.0;Double personEve_q4_wagejgUsd=0.0;
			Double personEve_q4_addmoneyzg=0.0;Double personEve_q4_addmoneyjg=0.0;
			Double personEve_q4_sumStandarddemo=0.0;Double personEve_q4_sumActualdemo=0.0;
			Double personEve_q4_sumActualpairs=0.0;
			//上半年
			Double personEve_half1_personzg=0.0;Double personEve_half1_personjg=0.0;
			Double personEve_half1_timezg=0.0;Double personEve_half1_timejg=0.0;
			Double personEve_half1_addtimezg=0.0;Double personEve_half1_addtimejg=0.0;
			Double personEve_half1_leavenumzg=0.0;Double personEve_half1_leavenumjg=0.0;
			Double personEve_half1_wagezgUsd=0.0;Double personEve_half1_wagejgUsd=0.0;
			Double personEve_half1_addmoneyzg=0.0;Double personEve_half1_addmoneyjg=0.0;
			Double personEve_half1_sumStandarddemo=0.0;Double personEve_half1_sumActualdemo=0.0;
			Double personEve_half1_sumActualpairs=0.0;
			//下半年
			Double personEve_half2_personzg=0.0;Double personEve_half2_personjg=0.0;
			Double personEve_half2_timezg=0.0;Double personEve_half2_timejg=0.0;
			Double personEve_half2_addtimezg=0.0;Double personEve_half2_addtimejg=0.0;
			Double personEve_half2_leavenumzg=0.0;Double personEve_half2_leavenumjg=0.0;
			Double personEve_half2_wagezgUsd=0.0;Double personEve_half2_wagejgUsd=0.0;
			Double personEve_half2_addmoneyzg=0.0;Double personEve_half2_addmoneyjg=0.0;
			Double personEve_half2_sumStandarddemo=0.0;Double personEve_half2_sumActualdemo=0.0;
			Double personEve_half2_sumActualpairs=0.0;
			//全年
			Double personEve_year_personzg=0.0;Double personEve_year_personjg=0.0;
			Double personEve_year_timezg=0.0;Double personEve_year_timejg=0.0;
			Double personEve_year_addtimezg=0.0;Double personEve_year_addtimejg=0.0;
			Double personEve_year_leavenumzg=0.0;Double personEve_year_leavenumjg=0.0;
			Double personEve_year_wagezgUsd=0.0;Double personEve_year_wagejgUsd=0.0;
			Double personEve_year_addmoneyzg=0.0;Double personEve_year_addmoneyjg=0.0;
			Double personEve_year_sumStandarddemo=0.0;Double personEve_year_sumActualdemo=0.0;
			Double personEve_year_sumActualpairs=0.0;
			/**
			 * 關務損耗
			 */
			//第一季度
			Double gwEve_q1_sumActualpairs=0.0;Double gwEve_q1_sideweit=0.0;
			Double gwEve_q1_badweit=0.0;Double gwEve_q1_otherbadweight=0.0;
			Double gwEve_q1_actlost=0.0;Double gwEve_q1_avgbuttomweight2=0.0;
			Double gwEve_q1_otherweight=0.0;
			//第二季度
			Double gwEve_q2_sumActualpairs=0.0;Double gwEve_q2_sideweit=0.0;
			Double gwEve_q2_badweit=0.0;Double gwEve_q2_otherbadweight=0.0;
			Double gwEve_q2_actlost=0.0;Double gwEve_q2_avgbuttomweight2=0.0;
			Double gwEve_q2_otherweight=0.0;
			//第三季度
			Double gwEve_q3_sumActualpairs=0.0;Double gwEve_q3_sideweit=0.0;
			Double gwEve_q3_badweit=0.0;Double gwEve_q3_otherbadweight=0.0;
			Double gwEve_q3_actlost=0.0;Double gwEve_q3_avgbuttomweight2=0.0;
			Double gwEve_q3_otherweight=0.0;
			//第四季度
			Double gwEve_q4_sumActualpairs=0.0;Double gwEve_q4_sideweit=0.0;
			Double gwEve_q4_badweit=0.0;Double gwEve_q4_otherbadweight=0.0;
			Double gwEve_q4_actlost=0.0;Double gwEve_q4_avgbuttomweight2=0.0;
			Double gwEve_q4_otherweight=0.0;
			//上半年
			Double gwEve_half1_sumActualpairs=0.0;Double gwEve_half1_sideweit=0.0;
			Double gwEve_half1_badweit=0.0;Double gwEve_half1_otherbadweight=0.0;
			Double gwEve_half1_actlost=0.0;Double gwEve_half1_avgbuttomweight2=0.0;
			Double gwEve_half1_otherweight=0.0;
			//下半年
			Double gwEve_half2_sumActualpairs=0.0;Double gwEve_half2_sideweit=0.0;
			Double gwEve_half2_badweit=0.0;Double gwEve_half2_otherbadweight=0.0;
			Double gwEve_half2_actlost=0.0;Double gwEve_half2_avgbuttomweight2=0.0;
			Double gwEve_half2_otherweight=0.0;
			//全年
			Double gwEve_year_sumActualpairs=0.0;Double gwEve_year_sideweit=0.0;
			Double gwEve_year_badweit=0.0;Double gwEve_year_otherbadweight=0.0;
			Double gwEve_year_actlost=0.0;Double gwEve_year_avgbuttomweight2=0.0;
			Double gwEve_year_otherweight=0.0;
			/**
			 * 邊料不良重量分析
			 */
			//第一季度
			Double sideEve_q1_sumActualpairs=0.0;Double sideEve_q1_sideweit=0.0;
			Double sideEve_q1_badcount=0.0;Double sideEve_q1_badweit=0.0;
			Double sideEve_q1_otherbadweight=0.0;Double sideEve_q1_avgbuttomweight=0.0;
			Double sideEve_q1_avgbuttomweight2=0.0;Double sideEve_q1_avgprice=0.0;
			Double sideEve_q1_otherweight=0.0;
			
			//第二季度
			Double sideEve_q2_sumActualpairs=0.0;Double sideEve_q2_sideweit=0.0;
			Double sideEve_q2_badcount=0.0;Double sideEve_q2_badweit=0.0;
			Double sideEve_q2_otherbadweight=0.0;Double sideEve_q2_avgbuttomweight=0.0;
			Double sideEve_q2_avgbuttomweight2=0.0;Double sideEve_q2_avgprice=0.0;
			Double sideEve_q2_otherweight=0.0;
			//第三季度
			Double sideEve_q3_sumActualpairs=0.0;Double sideEve_q3_sideweit=0.0;
			Double sideEve_q3_badcount=0.0;Double sideEve_q3_badweit=0.0;
			Double sideEve_q3_otherbadweight=0.0;Double sideEve_q3_avgbuttomweight=0.0;
			Double sideEve_q3_avgbuttomweight2=0.0;Double sideEve_q3_avgprice=0.0;
			Double sideEve_q3_otherweight=0.0;
			//第四季度
			Double sideEve_q4_sumActualpairs=0.0;Double sideEve_q4_sideweit=0.0;
			Double sideEve_q4_badcount=0.0;Double sideEve_q4_badweit=0.0;
			Double sideEve_q4_otherbadweight=0.0;Double sideEve_q4_avgbuttomweight=0.0;
			Double sideEve_q4_avgbuttomweight2=0.0;Double sideEve_q4_avgprice=0.0;
			Double sideEve_q4_otherweight=0.0;
			//上半年
			Double sideEve_half1_sumActualpairs=0.0;Double sideEve_half1_sideweit=0.0;
			Double sideEve_half1_badcount=0.0;Double sideEve_half1_badweit=0.0;
			Double sideEve_half1_otherbadweight=0.0;Double sideEve_half1_avgbuttomweight=0.0;
			Double sideEve_half1_avgbuttomweight2=0.0;Double sideEve_half1_avgprice=0.0;
			Double sideEve_half1_otherweight=0.0;
			//下半年
			Double sideEve_half2_sumActualpairs=0.0;Double sideEve_half2_sideweit=0.0;
			Double sideEve_half2_badcount=0.0;Double sideEve_half2_badweit=0.0;
			Double sideEve_half2_otherbadweight=0.0;Double sideEve_half2_avgbuttomweight=0.0;
			Double sideEve_half2_avgbuttomweight2=0.0;Double sideEve_half2_avgprice=0.0;
			Double sideEve_half2_otherweight=0.0;
			//全年
			Double sideEve_year_sumActualpairs=0.0;Double sideEve_year_sideweit=0.0;
			Double sideEve_year_badcount=0.0;Double sideEve_year_badweit=0.0;
			Double sideEve_year_otherbadweight=0.0;Double sideEve_year_avgbuttomweight=0.0;
			Double sideEve_year_avgbuttomweight2=0.0;Double sideEve_year_avgprice=0.0;
			Double sideEve_year_otherweight=0.0;
			/**
			 * 庫存
			 */
			//第一季度
			Double storeEve_q1_sumhostpairs=0.0;Double storeEve_q1_sumsamplepairs=0.0;
			Double storeEve_q1_invcount=0.0;Double storeEve_q1_hostinvcount=0.0;
			Double storeEve_q1_badcount=0.0;Double storeEve_q1_lagStorenum=0.0;
			Double storeEve_q1_lagNoliststorenum=0.0;Double storeEve_q1_lagMakestorenum=0.0;
			Double storeEve_q1_lagoutOutnum=0.0;Double storeEve_q1_lagInnum=0.0;
			Double storeEve_q1_lagInstorenum=0.0;Double storeEve_q1_storenum=0.0;
			Double storeEve_q1_noliststorenum=0.0;Double storeEve_q1_makestorenum=0.0;
			Double storeEve_q1_outnum=0.0;Double storeEve_q1_innum=0.0;
			Double storeEve_q1_minusnum=0.0;
			
			
			//第二季度
			Double storeEve_q2_sumhostpairs=0.0;Double storeEve_q2_sumsamplepairs=0.0;
			Double storeEve_q2_invcount=0.0;Double storeEve_q2_hostinvcount=0.0;
			Double storeEve_q2_badcount=0.0;Double storeEve_q2_lagStorenum=0.0;
			Double storeEve_q2_lagNoliststorenum=0.0;Double storeEve_q2_lagMakestorenum=0.0;
			Double storeEve_q2_lagoutOutnum=0.0;Double storeEve_q2_lagInnum=0.0;
			Double storeEve_q2_lagInstorenum=0.0;Double storeEve_q2_storenum=0.0;
			Double storeEve_q2_noliststorenum=0.0;Double storeEve_q2_makestorenum=0.0;
			Double storeEve_q2_outnum=0.0;Double storeEve_q2_innum=0.0;
			Double storeEve_q2_minusnum=0.0;
			
			//第三季度
			Double storeEve_q3_sumhostpairs=0.0;Double storeEve_q3_sumsamplepairs=0.0;
			Double storeEve_q3_invcount=0.0;Double storeEve_q3_hostinvcount=0.0;
			Double storeEve_q3_badcount=0.0;Double storeEve_q3_lagStorenum=0.0;
			Double storeEve_q3_lagNoliststorenum=0.0;Double storeEve_q3_lagMakestorenum=0.0;
			Double storeEve_q3_lagoutOutnum=0.0;Double storeEve_q3_lagInnum=0.0;
			Double storeEve_q3_lagInstorenum=0.0;Double storeEve_q3_storenum=0.0;
			Double storeEve_q3_noliststorenum=0.0;Double storeEve_q3_makestorenum=0.0;
			Double storeEve_q3_outnum=0.0;Double storeEve_q3_innum=0.0;
			Double storeEve_q3_minusnum=0.0;
			
			//第四季度
			Double storeEve_q4_sumhostpairs=0.0;Double storeEve_q4_sumsamplepairs=0.0;
			Double storeEve_q4_invcount=0.0;Double storeEve_q4_hostinvcount=0.0;
			Double storeEve_q4_badcount=0.0;Double storeEve_q4_lagStorenum=0.0;
			Double storeEve_q4_lagNoliststorenum=0.0;Double storeEve_q4_lagMakestorenum=0.0;
			Double storeEve_q4_lagoutOutnum=0.0;Double storeEve_q4_lagInnum=0.0;
			Double storeEve_q4_lagInstorenum=0.0;Double storeEve_q4_storenum=0.0;
			Double storeEve_q4_noliststorenum=0.0;Double storeEve_q4_makestorenum=0.0;
			Double storeEve_q4_outnum=0.0;Double storeEve_q4_innum=0.0;
			Double storeEve_q4_minusnum=0.0;
			
			//上半年
			Double storeEve_half1_sumhostpairs=0.0;Double storeEve_half1_sumsamplepairs=0.0;
			Double storeEve_half1_invcount=0.0;Double storeEve_half1_hostinvcount=0.0;
			Double storeEve_half1_badcount=0.0;Double storeEve_half1_lagStorenum=0.0;
			Double storeEve_half1_lagNoliststorenum=0.0;Double storeEve_half1_lagMakestorenum=0.0;
			Double storeEve_half1_lagoutOutnum=0.0;Double storeEve_half1_lagInnum=0.0;
			Double storeEve_half1_lagInstorenum=0.0;Double storeEve_half1_storenum=0.0;
			Double storeEve_half1_noliststorenum=0.0;Double storeEve_half1_makestorenum=0.0;
			Double storeEve_half1_outnum=0.0;Double storeEve_half1_innum=0.0;
			Double storeEve_half1_minusnum=0.0;
			
			//下半年
			Double storeEve_half2_sumhostpairs=0.0;Double storeEve_half2_sumsamplepairs=0.0;
			Double storeEve_half2_invcount=0.0;Double storeEve_half2_hostinvcount=0.0;
			Double storeEve_half2_badcount=0.0;Double storeEve_half2_lagStorenum=0.0;
			Double storeEve_half2_lagNoliststorenum=0.0;Double storeEve_half2_lagMakestorenum=0.0;
			Double storeEve_half2_lagoutOutnum=0.0;Double storeEve_half2_lagInnum=0.0;
			Double storeEve_half2_lagInstorenum=0.0;Double storeEve_half2_storenum=0.0;
			Double storeEve_half2_noliststorenum=0.0;Double storeEve_half2_makestorenum=0.0;
			Double storeEve_half2_outnum=0.0;Double storeEve_half2_innum=0.0;
			Double storeEve_half2_minusnum=0.0;
			//全年
			Double storeEve_year_sumhostpairs=0.0;Double storeEve_year_sumsamplepairs=0.0;
			Double storeEve_year_invcount=0.0;Double storeEve_year_hostinvcount=0.0;
			Double storeEve_year_badcount=0.0;Double storeEve_year_lagStorenum=0.0;
			Double storeEve_year_lagNoliststorenum=0.0;Double storeEve_year_lagMakestorenum=0.0;
			Double storeEve_year_lagoutOutnum=0.0;Double storeEve_year_lagInnum=0.0;
			Double storeEve_year_lagInstorenum=0.0;Double storeEve_year_storenum=0.0;
			Double storeEve_year_noliststorenum=0.0;Double storeEve_year_makestorenum=0.0;
			Double storeEve_year_outnum=0.0;Double storeEve_year_innum=0.0;
			Double storeEve_year_minusnum=0.0;
			/**
			 * 水電油
			 */
			//第一季度			
			Double wloEve_q1_sumactualdemo=0.0;Double wloEve_q1_waterton=0.0;
			Double wloEve_q1_waterusd=0.0;Double wloEve_q1_electricdu=0.0;
			Double wloEve_q1_electricusd=0.0;Double wloEve_q1_gaston=0.0;
			Double wloEve_q1_gasusd=0.0;Double wloEve_q1_oilton=0.0;
			Double wloEve_q1_oilusd=0.0;
			//第二季度
			Double wloEve_q2_sumactualdemo=0.0;Double wloEve_q2_waterton=0.0;
			Double wloEve_q2_waterusd=0.0;Double wloEve_q2_electricdu=0.0;
			Double wloEve_q2_electricusd=0.0;Double wloEve_q2_gaston=0.0;
			Double wloEve_q2_gasusd=0.0;Double wloEve_q2_oilton=0.0;
			Double wloEve_q2_oilusd=0.0;
			//第三季度
			Double wloEve_q3_sumactualdemo=0.0;Double wloEve_q3_waterton=0.0;
			Double wloEve_q3_waterusd=0.0;Double wloEve_q3_electricdu=0.0;
			Double wloEve_q3_electricusd=0.0;Double wloEve_q3_gaston=0.0;
			Double wloEve_q3_gasusd=0.0;Double wloEve_q3_oilton=0.0;
			Double wloEve_q3_oilusd=0.0;
			//第四季度
			Double wloEve_q4_sumactualdemo=0.0;Double wloEve_q4_waterton=0.0;
			Double wloEve_q4_waterusd=0.0;Double wloEve_q4_electricdu=0.0;
			Double wloEve_q4_electricusd=0.0;Double wloEve_q4_gaston=0.0;
			Double wloEve_q4_gasusd=0.0;Double wloEve_q4_oilton=0.0;
			Double wloEve_q4_oilusd=0.0;
			//上半年
			Double wloEve_half1_sumactualdemo=0.0;Double wloEve_half1_waterton=0.0;
			Double wloEve_half1_waterusd=0.0;Double wloEve_half1_electricdu=0.0;
			Double wloEve_half1_electricusd=0.0;Double wloEve_half1_gaston=0.0;
			Double wloEve_half1_gasusd=0.0;Double wloEve_half1_oilton=0.0;
			Double wloEve_half1_oilusd=0.0;
			//下半年
			Double wloEve_half2_sumactualdemo=0.0;Double wloEve_half2_waterton=0.0;
			Double wloEve_half2_waterusd=0.0;Double wloEve_half2_electricdu=0.0;
			Double wloEve_half2_electricusd=0.0;Double wloEve_half2_gaston=0.0;
			Double wloEve_half2_gasusd=0.0;Double wloEve_half2_oilton=0.0;
			Double wloEve_half2_oilusd=0.0;
			//全年
			Double wloEve_year_sumactualdemo=0.0;Double wloEve_year_waterton=0.0;
			Double wloEve_year_waterusd=0.0;Double wloEve_year_electricdu=0.0;
			Double wloEve_year_electricusd=0.0;Double wloEve_year_gaston=0.0;
			Double wloEve_year_gasusd=0.0;Double wloEve_year_oilton=0.0;
			Double wloEve_year_oilusd=0.0;
			/**
			 * 回頭料
			 */
			//第一季度
			Double  backEve_q1_thickusd=0.0;Double  backEve_q1_backfeed=0.0;
			Double  backEve_q1_oilback=0.0;
			//第二季度
			Double  backEve_q2_thickusd=0.0;Double  backEve_q2_backfeed=0.0;
			Double  backEve_q2_oilback=0.0;
			//第三季度
			Double  backEve_q3_thickusd=0.0;Double  backEve_q3_backfeed=0.0;
			Double  backEve_q3_oilback=0.0;
			//第四季度
			Double  backEve_q4_thickusd=0.0;Double  backEve_q4_backfeed=0.0;
			Double  backEve_q4_oilback=0.0;
			//上半年
			Double  backEve_half1_thickusd=0.0;Double  backEve_half1_backfeed=0.0;
			Double  backEve_half1_oilback=0.0;
			//下半年
			Double  backEve_half2_thickusd=0.0;Double  backEve_half2_backfeed=0.0;
			Double  backEve_half2_oilback=0.0;
			//全年
			Double  backEve_year_thickusd=0.0;Double  backEve_year_backfeed=0.0;
			Double  backEve_year_oilback=0.0;
			/**
			 * 色料回收粉
			 */
			//第一季度			
			Double webcostEve_q1_sumActualdemo=0.0;Double webcostEve_q1_gluestoremoney=0.0;
			Double webcostEve_q1_drugsused=0.0;Double webcostEve_q1_leavemoney=0.0;
			Double webcostEve_q1_whitenum=0.0;Double webcostEve_q1_blacknum=0.0;
			Double webcostEve_q1_gluenum=0.0;Double webcostEve_q1_greynum=0.0;
			Double webcostEve_q1_othernum=0.0;
			//第二季度
			Double webcostEve_q2_sumActualdemo=0.0;Double webcostEve_q2_gluestoremoney=0.0;
			Double webcostEve_q2_drugsused=0.0;Double webcostEve_q2_leavemoney=0.0;
			Double webcostEve_q2_whitenum=0.0;Double webcostEve_q2_blacknum=0.0;
			Double webcostEve_q2_gluenum=0.0;Double webcostEve_q2_greynum=0.0;
			Double webcostEve_q2_othernum=0.0;
			//第三季度
			Double webcostEve_q3_sumActualdemo=0.0;Double webcostEve_q3_gluestoremoney=0.0;
			Double webcostEve_q3_drugsused=0.0;Double webcostEve_q3_leavemoney=0.0;
			Double webcostEve_q3_whitenum=0.0;Double webcostEve_q3_blacknum=0.0;
			Double webcostEve_q3_gluenum=0.0;Double webcostEve_q3_greynum=0.0;
			Double webcostEve_q3_othernum=0.0;
			//第四季度
			Double webcostEve_q4_sumActualdemo=0.0;Double webcostEve_q4_gluestoremoney=0.0;
			Double webcostEve_q4_drugsused=0.0;Double webcostEve_q4_leavemoney=0.0;
			Double webcostEve_q4_whitenum=0.0;Double webcostEve_q4_blacknum=0.0;
			Double webcostEve_q4_gluenum=0.0;Double webcostEve_q4_greynum=0.0;
			Double webcostEve_q4_othernum=0.0;
			//上半年
			Double webcostEve_half1_sumActualdemo=0.0;Double webcostEve_half1_gluestoremoney=0.0;
			Double webcostEve_half1_drugsused=0.0;Double webcostEve_half1_leavemoney=0.0;
			Double webcostEve_half1_whitenum=0.0;Double webcostEve_half1_blacknum=0.0;
			Double webcostEve_half1_gluenum=0.0;Double webcostEve_half1_greynum=0.0;
			Double webcostEve_half1_othernum=0.0;
			//下半年
			Double webcostEve_half2_sumActualdemo=0.0;Double webcostEve_half2_gluestoremoney=0.0;
			Double webcostEve_half2_drugsused=0.0;Double webcostEve_half2_leavemoney=0.0;
			Double webcostEve_half2_whitenum=0.0;Double webcostEve_half2_blacknum=0.0;
			Double webcostEve_half2_gluenum=0.0;Double webcostEve_half2_greynum=0.0;
			Double webcostEve_half2_othernum=0.0;
			//全年
			Double webcostEve_year_sumActualdemo=0.0;Double webcostEve_year_gluestoremoney=0.0;
			Double webcostEve_year_drugsused=0.0;Double webcostEve_year_leavemoney=0.0;
			Double webcostEve_year_whitenum=0.0;Double webcostEve_year_blacknum=0.0;
			Double webcostEve_year_gluenum=0.0;Double webcostEve_year_greynum=0.0;
			Double webcostEve_year_othernum=0.0;
		
			
			for(int z_1=1;z_1<13;z_1++){//start for2_2
				//machine
				List<Double>list_machine_2=new ArrayList<Double>();//用於裝每月數據---單元集合(list3)
				List<Double>list_machine_3=new ArrayList<Double>();//用於裝季度數據---單元集合(list3)
				List<Double>list_machine_4=new ArrayList<Double>();//用於裝上下年度數據---單元集合(list3)
				List<Double>list_machine_5=new ArrayList<Double>();//用於裝全年年度數據---單元集合(list3)
				//inv
				List<Double>list_inv_2=new ArrayList<Double>();
				List<Double>list_inv_3=new ArrayList<Double>();
				List<Double>list_inv_4=new ArrayList<Double>();
				List<Double>list_inv_5=new ArrayList<Double>();
				//mix1
				List<Double>list_mix1_2=new ArrayList<Double>();
				List<Double>list_mix1_3=new ArrayList<Double>();
				List<Double>list_mix1_4=new ArrayList<Double>();
				List<Double>list_mix1_5=new ArrayList<Double>();
				//person
				List<Double>list_person_2=new ArrayList<Double>();
				List<Double>list_person_3=new ArrayList<Double>();
				List<Double>list_person_4=new ArrayList<Double>();
				List<Double>list_person_5=new ArrayList<Double>();
				//gw
				List<Double>list_gw_2=new ArrayList<Double>();
				List<Double>list_gw_3=new ArrayList<Double>();
				List<Double>list_gw_4=new ArrayList<Double>();
				List<Double>list_gw_5=new ArrayList<Double>();
				//side
				List<Double>list_side_2=new ArrayList<Double>();
				List<Double>list_side_3=new ArrayList<Double>();
				List<Double>list_side_4=new ArrayList<Double>();
				List<Double>list_side_5=new ArrayList<Double>();
				//store
				List<Double>list_store_2=new ArrayList<Double>();
				List<Double>list_store_3=new ArrayList<Double>();
				List<Double>list_store_4=new ArrayList<Double>();
				List<Double>list_store_5=new ArrayList<Double>();
				//wlo
				List<Double>list_wlo_2=new ArrayList<Double>();
				List<Double>list_wlo_3=new ArrayList<Double>();
				List<Double>list_wlo_4=new ArrayList<Double>();
				List<Double>list_wlo_5=new ArrayList<Double>();
				//back
				List<Double>list_back_2=new ArrayList<Double>();
				List<Double>list_back_3=new ArrayList<Double>();
				List<Double>list_back_4=new ArrayList<Double>();
				List<Double>list_back_5=new ArrayList<Double>();
				//webcost
				List<Double>list_webcost_2=new ArrayList<Double>();
				List<Double>list_webcost_3=new ArrayList<Double>();
				List<Double>list_webcost_4=new ArrayList<Double>();
				List<Double>list_webcost_5=new ArrayList<Double>();
				
				String month="";
				if(z_1<10){
					month="0"+z_1;
				}else{
					month=""+z_1;
				}
				//有算式表達式
				SimpleDateFormat format=new SimpleDateFormat("yyyyMM");
				VWebmachine machine=vwebmachineser.findById(factNo, factCode, year+month);
				VWeboutputinv inv=vinvser.findById(factNo, factCode, year+month);
				SumWebYieldData mix1=sumydateSer.findById(factNo, factCode, year+month);
				VWebperson person=vpersonser.findById(factNo, factCode, year+month);
				VWebgw gw=vgwser.findById(factNo, factCode, year+month);
				VWebside side=vsideser.findById(factNo, factCode, year+month);
				VWebstore store=vstoreser.findById(factNo, factCode, year+month);
				VWebwlo wlo=vwloser.findById(factNo, factCode, year+month);				
				Webbackfeed back=feedSer.findById(factNo,factCode,format.parse(year+month));
				VWebcost webcost=vwebcostSer.findById(factNo, factCode, year+month);
				//無算式表達式
				VWebmachineEve machine_eve=machineeveSer.findById(factNo, factCode, year+month);
				VWeboutputinvEve inv_eve=inveveSer.findById(factNo, factCode, year+month);
				SumWebYieldData mix1_eve=sumydateSer.findById(factNo, factCode, year+month);
				VWebpersonEve person_eve=personeveSer.findById(factNo, factCode, year+month);
				VWebgwEve gw_eve=gweveSer.findById(factNo, factCode, year+month);
				VWebsideEve side_eve=sideeveSer.findById(factNo, factCode, year+month);
				VWebstoreEve store_eve=storeeveSer.findById(factNo, factCode, year+month);
				VWebwloEve wlo_eve=wloeveSer.findById(factNo, factCode, year+month);
				Webbackfeed back_eve=feedSer.findById(factNo,factCode,format.parse(year+month));
				VWebcostEve webcost_eve=vwebcosteveSer.findById(factNo, factCode, year+month);
				
				//定義big賦值給空值
				BigDecimal big=new BigDecimal(0.00);
				//(1)判斷machine,machine_eve
				if(machine_eve!=null){					
					//第一季度
					if(z_1<4){
						machineEve_q1_sumWorkday=machineEve_q1_sumWorkday+machine_eve.getSumWorkday().doubleValue();
						machineEve_q1_sumEverydemo=machineEve_q1_sumEverydemo+machine_eve.getSumEverydemo().doubleValue();
						machineEve_q1_sumStandarddemo=machineEve_q1_sumStandarddemo+machine_eve.getSumStandarddemo().doubleValue();
						machineEve_q1_sumActualdemo=machineEve_q1_sumActualdemo+machine_eve.getSumActualdemo().doubleValue();
						machineEve_q1_sumActualpairs=machineEve_q1_sumActualpairs+machine_eve.getSumActualpairs().doubleValue();
						machineEve_q1_avgbuttomweight=machineEve_q1_avgbuttomweight+machine_eve.getAvgbuttomweight();
						machineEve_q1_avgbuttomweight2=machineEve_q1_avgbuttomweight2+machine_eve.getAvgbuttomweight2();
						machineEve_q1_hole=machineEve_q1_hole+machine_eve.getHole();						
					}
					//第二季度
					if(z_1>3&&z_1<7){
						machineEve_q2_sumWorkday=machineEve_q2_sumWorkday+machine_eve.getSumWorkday().doubleValue();
						machineEve_q2_sumEverydemo=machineEve_q2_sumEverydemo+machine_eve.getSumEverydemo().doubleValue();
						machineEve_q2_sumStandarddemo=machineEve_q2_sumStandarddemo+machine_eve.getSumStandarddemo().doubleValue();
						machineEve_q2_sumActualdemo=machineEve_q2_sumActualdemo+machine_eve.getSumActualdemo().doubleValue();
						machineEve_q2_sumActualpairs=machineEve_q2_sumActualpairs+machine_eve.getSumActualpairs().doubleValue();
						machineEve_q2_avgbuttomweight=machineEve_q2_avgbuttomweight+machine_eve.getAvgbuttomweight();
						machineEve_q2_avgbuttomweight2=machineEve_q2_avgbuttomweight2+machine_eve.getAvgbuttomweight2();
						machineEve_q2_hole=machineEve_q2_hole+machine_eve.getHole();												
					}
					//第三季度
					if(z_1>6&&z_1<10){
						machineEve_q3_sumWorkday=machineEve_q3_sumWorkday+machine_eve.getSumWorkday().doubleValue();
						machineEve_q3_sumEverydemo=machineEve_q3_sumEverydemo+machine_eve.getSumEverydemo().doubleValue();
						machineEve_q3_sumStandarddemo=machineEve_q3_sumStandarddemo+machine_eve.getSumStandarddemo().doubleValue();
						machineEve_q3_sumActualdemo=machineEve_q3_sumActualdemo+machine_eve.getSumActualdemo().doubleValue();
						machineEve_q3_sumActualpairs=machineEve_q3_sumActualpairs+machine_eve.getSumActualpairs().doubleValue();
						machineEve_q3_avgbuttomweight=machineEve_q3_avgbuttomweight+machine_eve.getAvgbuttomweight();
						machineEve_q3_avgbuttomweight2=machineEve_q3_avgbuttomweight2+machine_eve.getAvgbuttomweight2();
						machineEve_q3_hole=machineEve_q3_hole+machine_eve.getHole();
					}
					//第四季度
					if(z_1>9){
						machineEve_q4_sumWorkday=machineEve_q4_sumWorkday+machine_eve.getSumWorkday().doubleValue();
						machineEve_q4_sumEverydemo=machineEve_q4_sumEverydemo+machine_eve.getSumEverydemo().doubleValue();
						machineEve_q4_sumStandarddemo=machineEve_q4_sumStandarddemo+machine_eve.getSumStandarddemo().doubleValue();
						machineEve_q4_sumActualdemo=machineEve_q4_sumActualdemo+machine_eve.getSumActualdemo().doubleValue();
						machineEve_q4_sumActualpairs=machineEve_q4_sumActualpairs+machine_eve.getSumActualpairs().doubleValue();
						machineEve_q4_avgbuttomweight=machineEve_q4_avgbuttomweight+machine_eve.getAvgbuttomweight();
						machineEve_q4_avgbuttomweight2=machineEve_q4_avgbuttomweight2+machine_eve.getAvgbuttomweight2();
						machineEve_q4_hole=machineEve_q4_hole+machine_eve.getHole();											
					}
				}
				if(machine!=null){					
					list_machine_2.add(machine.getVWebmcA001());list_machine_2.add(machine.getVWebmcA002().doubleValue());
					list_machine_2.add(machine.getVWebmcA003().doubleValue());list_machine_2.add(machine.getVWebmcA004().doubleValue());
					list_machine_2.add(machine.getVWebmcA005().doubleValue());list_machine_2.add(machine.getVWebmcA006());
					list_machine_2.add(machine.getVWebmcA007());list_machine_2.add(machine.getVWebmcA008().doubleValue());
					list_machine_2.add(machine.getVWebmcA009().doubleValue());list_machine_2.add(machine.getVWebmcA010().doubleValue());
					list_machine_2.add(machine.getVWebmcA011().doubleValue());
					list_machine.add(list_machine_2);
					
				}else{					
					list_machine.add(null);//每個月為空					
				}
				
				
				//(2)判斷inv,invEve
				if(inv_eve!=null){
					//sumActualpairs有可能為空
					if(inv_eve.getSumActualpairs()==null){
						inv_eve.setSumActualpairs(big);
					}
					if(inv_eve.getPaypairs()==null){
						inv_eve.setPaypairs(big.doubleValue());
					}
					//第一季度
					if(z_1<4){
						invEve_q1_sumActualpairs=invEve_q1_sumActualpairs+inv_eve.getSumActualpairs().doubleValue();
						invEve_q1_invcount=invEve_q1_invcount+inv_eve.getInvcount();
						invEve_q1_paypair=invEve_q1_paypair+inv_eve.getPaypairs();
					}
					//第二季度
					if(z_1>3&&z_1<7){
						invEve_q2_sumActualpairs=invEve_q2_sumActualpairs+inv_eve.getSumActualpairs().doubleValue();
						invEve_q2_invcount=invEve_q2_invcount+inv_eve.getInvcount();
						invEve_q2_paypair=invEve_q2_paypair+inv_eve.getPaypairs();												
					}
					//第三季度
					if(z_1>6&&z_1<10){
						invEve_q3_sumActualpairs=invEve_q3_sumActualpairs+inv_eve.getSumActualpairs().doubleValue();
						invEve_q3_invcount=invEve_q3_invcount+inv_eve.getInvcount();
						invEve_q3_paypair=invEve_q3_paypair+inv_eve.getPaypairs();
					}
					//第四季度
					if(z_1>9){
						invEve_q4_sumActualpairs=invEve_q4_sumActualpairs+inv_eve.getSumActualpairs().doubleValue();
						invEve_q4_invcount=invEve_q4_invcount+inv_eve.getInvcount();
						invEve_q4_paypair=invEve_q4_paypair+inv_eve.getPaypairs();												
					}
				}
				if(inv!=null){
					//判斷vwebinvboon是否null
				    if(inv.getVWebinvB001()==null){
				    	inv.setVWebinvB001(big);
				    }
				    if(inv.getVWebinvB003()==null){
				    	inv.setVWebinvB003(0.00);
				    }
				    if(inv.getVWebinvB004()==null){
				    	inv.setVWebinvB004(big);
				    }
					list_inv_2.add(inv.getVWebinvB001().doubleValue());list_inv_2.add(inv.getVWebinvB002());
					list_inv_2.add(inv.getVWebinvB003());list_inv_2.add(inv.getVWebinvB004().doubleValue());					
					list_inv.add(list_inv_2);
									
				}else{
					//為空時,也要添加一個空null,以補全數量
					list_inv.add(null);//每個月為空					
				}
				
				//(3)判斷mix1,mix1_eve
				if(mix1_eve!=null){
					//sumbacknum,sumoutnum有可能為空
					if(mix1_eve.getSumBacknum()==null){
						mix1_eve.setSumBacknum(big);
					}
					if(mix1_eve.getSumOutnum()==null){
						mix1_eve.setSumOutnum(big);
					}
					//第一季度
					if(z_1<4){
						mix1Eve_q1_sumbacknum=mix1Eve_q1_sumbacknum+mix1_eve.getSumBacknum().doubleValue();
						mix1Eve_q1_sumoutnum=mix1Eve_q1_sumoutnum+mix1_eve.getSumOutnum().doubleValue();
					}
					//第二季度
					if(z_1>3&&z_1<7){
						mix1Eve_q2_sumbacknum=mix1Eve_q2_sumbacknum+mix1_eve.getSumBacknum().doubleValue();
						mix1Eve_q2_sumoutnum=mix1Eve_q2_sumoutnum+mix1_eve.getSumOutnum().doubleValue();												
					}
					//第三季度
					if(z_1>6&&z_1<10){
						mix1Eve_q3_sumbacknum=mix1Eve_q3_sumbacknum+mix1_eve.getSumBacknum().doubleValue();
						mix1Eve_q3_sumoutnum=mix1Eve_q3_sumoutnum+mix1_eve.getSumOutnum().doubleValue();
					}
					//第四季度
					if(z_1>9){
						mix1Eve_q4_sumbacknum=mix1Eve_q4_sumbacknum+mix1_eve.getSumBacknum().doubleValue();
						mix1Eve_q4_sumoutnum=mix1Eve_q4_sumoutnum+mix1_eve.getSumOutnum().doubleValue();												
					}
				}
				if(mix1!=null){
					//判斷sumOutnum與sumBacknum是否為空					
					if(mix1.getSumOutnum()==null){
						mix1.setSumOutnum(big);
					}
					if(mix1.getSumBacknum()==null){
						mix1.setSumBacknum(big);
					}
					list_mix1_2.add(mix1.getSumOutnum().doubleValue());list_mix1_2.add(mix1.getSumBacknum().doubleValue());
					list_mix1_2.add(this.division(mix1.getSumBacknum().doubleValue(), mix1.getSumOutnum().doubleValue()));
					list_mix1.add(list_mix1_2);					
					
				}else{
					//為空時,也要添加一個空null,以補全數量
					list_mix1.add(null);//每個月為空					
				}
				//(4)判斷person,personEve
				if(person_eve!=null){
					if(person_eve.getSumActualpairs()==null){
						person_eve.setSumActualpairs(big);
					}
					//第一季度
					if(z_1<4){
						personEve_q1_addmoneyjg=personEve_q1_addmoneyjg+person_eve.getAddmoneyjg();
						personEve_q1_addmoneyzg=personEve_q1_addmoneyzg+person_eve.getAddmoneyzg();
						personEve_q1_addtimejg=personEve_q1_addtimejg+person_eve.getAddtimejg();
						personEve_q1_addtimezg=personEve_q1_addtimezg+person_eve.getAddtimezg();
						personEve_q1_leavenumjg=personEve_q1_leavenumjg+person_eve.getLeavenumjg();
						personEve_q1_leavenumzg=personEve_q1_leavenumzg+person_eve.getLeavenumzg();
						personEve_q1_personjg=personEve_q1_personjg+person_eve.getPersonjg();
						personEve_q1_personzg=personEve_q1_personzg+person_eve.getPersonzg();
						personEve_q1_sumActualdemo=personEve_q1_sumActualdemo+person_eve.getSumActualdemo().doubleValue();
						personEve_q1_sumActualpairs=personEve_q1_sumActualpairs+person_eve.getSumActualpairs().doubleValue();
						personEve_q1_sumStandarddemo=personEve_q1_sumStandarddemo+person_eve.getSumStandarddemo().doubleValue();
						personEve_q1_timejg=personEve_q1_timejg+person_eve.getTimejg();
						personEve_q1_timezg=personEve_q1_timezg+person_eve.getTimezg();
						personEve_q1_wagejgUsd=personEve_q1_wagejgUsd+person_eve.getWagejgUsd();
						personEve_q1_wagezgUsd=personEve_q1_wagezgUsd+person_eve.getWagezgUsd();
						
					}
					//第二季度
					if(z_1>3&&z_1<7){
						personEve_q2_addmoneyjg=personEve_q2_addmoneyjg+person_eve.getAddmoneyjg();
						personEve_q2_addmoneyzg=personEve_q2_addmoneyzg+person_eve.getAddmoneyzg();
						personEve_q2_addtimejg=personEve_q2_addtimejg+person_eve.getAddtimejg();
						personEve_q2_addtimezg=personEve_q2_addtimezg+person_eve.getAddtimezg();
						personEve_q2_leavenumjg=personEve_q2_leavenumjg+person_eve.getLeavenumjg();
						personEve_q2_leavenumzg=personEve_q2_leavenumzg+person_eve.getLeavenumzg();
						personEve_q2_personjg=personEve_q2_personjg+person_eve.getPersonjg();
						personEve_q2_personzg=personEve_q2_personzg+person_eve.getPersonzg();
						personEve_q2_sumActualdemo=personEve_q2_sumActualdemo+person_eve.getSumActualdemo().doubleValue();
						personEve_q2_sumActualpairs=personEve_q2_sumActualpairs+person_eve.getSumActualpairs().doubleValue();
						personEve_q2_sumStandarddemo=personEve_q2_sumStandarddemo+person_eve.getSumStandarddemo().doubleValue();
						personEve_q2_timejg=personEve_q2_timejg+person_eve.getTimejg();
						personEve_q2_timezg=personEve_q2_timezg+person_eve.getTimezg();
						personEve_q2_wagejgUsd=personEve_q2_wagejgUsd+person_eve.getWagejgUsd();
						personEve_q2_wagezgUsd=personEve_q2_wagezgUsd+person_eve.getWagezgUsd();												
					}
					//第三季度
					if(z_1>6&&z_1<10){
						personEve_q3_addmoneyjg=personEve_q3_addmoneyjg+person_eve.getAddmoneyjg();
						personEve_q3_addmoneyzg=personEve_q3_addmoneyzg+person_eve.getAddmoneyzg();
						personEve_q3_addtimejg=personEve_q3_addtimejg+person_eve.getAddtimejg();
						personEve_q3_addtimezg=personEve_q3_addtimezg+person_eve.getAddtimezg();
						personEve_q3_leavenumjg=personEve_q3_leavenumjg+person_eve.getLeavenumjg();
						personEve_q3_leavenumzg=personEve_q3_leavenumzg+person_eve.getLeavenumzg();
						personEve_q3_personjg=personEve_q3_personjg+person_eve.getPersonjg();
						personEve_q3_personzg=personEve_q3_personzg+person_eve.getPersonzg();
						personEve_q3_sumActualdemo=personEve_q3_sumActualdemo+person_eve.getSumActualdemo().doubleValue();
						personEve_q3_sumActualpairs=personEve_q3_sumActualpairs+person_eve.getSumActualpairs().doubleValue();
						personEve_q3_sumStandarddemo=personEve_q3_sumStandarddemo+person_eve.getSumStandarddemo().doubleValue();
						personEve_q3_timejg=personEve_q3_timejg+person_eve.getTimejg();
						personEve_q3_timezg=personEve_q3_timezg+person_eve.getTimezg();
						personEve_q3_wagejgUsd=personEve_q3_wagejgUsd+person_eve.getWagejgUsd();
						personEve_q3_wagezgUsd=personEve_q3_wagezgUsd+person_eve.getWagezgUsd();
					}
					//第四季度
					if(z_1>9){
						personEve_q4_addmoneyjg=personEve_q4_addmoneyjg+person_eve.getAddmoneyjg();
						personEve_q4_addmoneyzg=personEve_q4_addmoneyzg+person_eve.getAddmoneyzg();
						personEve_q4_addtimejg=personEve_q4_addtimejg+person_eve.getAddtimejg();
						personEve_q4_addtimezg=personEve_q4_addtimezg+person_eve.getAddtimezg();
						personEve_q4_leavenumjg=personEve_q4_leavenumjg+person_eve.getLeavenumjg();
						personEve_q4_leavenumzg=personEve_q4_leavenumzg+person_eve.getLeavenumzg();
						personEve_q4_personjg=personEve_q4_personjg+person_eve.getPersonjg();
						personEve_q4_personzg=personEve_q4_personzg+person_eve.getPersonzg();
						personEve_q4_sumActualdemo=personEve_q4_sumActualdemo+person_eve.getSumActualdemo().doubleValue();
						personEve_q4_sumActualpairs=personEve_q4_sumActualpairs+person_eve.getSumActualpairs().doubleValue();
						personEve_q4_sumStandarddemo=personEve_q4_sumStandarddemo+person_eve.getSumStandarddemo().doubleValue();
						personEve_q4_timejg=personEve_q4_timejg+person_eve.getTimejg();
						personEve_q4_timezg=personEve_q4_timezg+person_eve.getTimezg();
						personEve_q4_wagejgUsd=personEve_q4_wagejgUsd+person_eve.getWagejgUsd();
						personEve_q4_wagezgUsd=personEve_q4_wagezgUsd+person_eve.getWagezgUsd();												
					}
				}
				if(person!=null){
					if(person.getVWebperD017()==null){
						person.setVWebperD017(big);
					}
					list_person_2.add(person.getVWebperD001().doubleValue());list_person_2.add(person.getVWebperD002().doubleValue());
					list_person_2.add(person.getVWebperD003().doubleValue());list_person_2.add(person.getVWebperD004());
					list_person_2.add(person.getVWebperD005());list_person_2.add(person.getVWebperD006().doubleValue());
					list_person_2.add(person.getVWebperD007().doubleValue());list_person_2.add(person.getVWebperD008().doubleValue());
					list_person_2.add(person.getVWebperD009().doubleValue());list_person_2.add(person.getVWebperD010().doubleValue());
					list_person_2.add(person.getVWebperD011().doubleValue());list_person_2.add(person.getVWebperD012().doubleValue());
					list_person_2.add(person.getVWebperD013());list_person_2.add(person.getVWebperD014());
					list_person_2.add(person.getVWebperD015().doubleValue());list_person_2.add(person.getVWebperD016().doubleValue());
					list_person_2.add(person.getVWebperD017().doubleValue());list_person_2.add(person.getVWebperD018());
					list_person_2.add(person.getVWebperD019());list_person_2.add(person.getVWebperD020().doubleValue());
					list_person_2.add(person.getVWebperD021().doubleValue());list_person_2.add(person.getVWebperD022().doubleValue());
					list_person_2.add(person.getVWebperD023().doubleValue());list_person_2.add(person.getVWebperD024().doubleValue());
					list_person_2.add(person.getVWebperD025().doubleValue());					
					list_person.add(list_person_2);
					
					
				}else{
					//為空時,也要添加一個空null,以補全數量
					list_person.add(null);//每個月為空					
				}
				//(5)判斷gw,gw_eve
				if(gw_eve!=null){
					if(gw_eve.getOtherweight()==null){
						gw_eve.setOtherweight(0.0);
					}
					//第一季度
					if(z_1<4){
						gwEve_q1_actlost=gwEve_q1_actlost+gw_eve.getActlost();
						gwEve_q1_avgbuttomweight2=gwEve_q1_avgbuttomweight2+gw_eve.getAvgbuttomweight2();
						gwEve_q1_badweit=gwEve_q1_badweit+gw_eve.getBadweit();
						gwEve_q1_otherbadweight=gwEve_q1_otherbadweight+gw_eve.getOtherbadweight();
						gwEve_q1_sideweit=gwEve_q1_sideweit+gw_eve.getSideweit();
						gwEve_q1_sumActualpairs=gwEve_q1_sumActualpairs+gw_eve.getSumActualpairs().doubleValue();
						gwEve_q1_otherweight=gwEve_q1_otherweight+gw_eve.getOtherweight();
					}
					//第二季度
					if(z_1>3&&z_1<7){
						gwEve_q2_actlost=gwEve_q2_actlost+gw_eve.getActlost();
						gwEve_q2_avgbuttomweight2=gwEve_q2_avgbuttomweight2+gw_eve.getAvgbuttomweight2();
						gwEve_q2_badweit=gwEve_q2_badweit+gw_eve.getBadweit();
						gwEve_q2_otherbadweight=gwEve_q2_otherbadweight+gw_eve.getOtherbadweight();
						gwEve_q2_sideweit=gwEve_q2_sideweit+gw_eve.getSideweit();
						gwEve_q2_sumActualpairs=gwEve_q2_sumActualpairs+gwEve_q2_sumActualpairs;
						gwEve_q2_otherweight=gwEve_q2_otherweight+gw_eve.getOtherweight();
					}
					//第三季度
					if(z_1>6&&z_1<10){
						gwEve_q3_actlost=gwEve_q3_actlost+gw_eve.getActlost();
						gwEve_q3_avgbuttomweight2=gwEve_q3_avgbuttomweight2+gw_eve.getAvgbuttomweight2();
						gwEve_q3_badweit=gwEve_q3_badweit+gw_eve.getBadweit();
						gwEve_q3_otherbadweight=gwEve_q3_otherbadweight+gw_eve.getOtherbadweight();
						gwEve_q3_sideweit=gwEve_q3_sideweit+gw_eve.getSideweit();
						gwEve_q3_sumActualpairs=gwEve_q3_sumActualpairs+gwEve_q3_sumActualpairs;
						gwEve_q3_otherweight=gwEve_q3_otherweight+gw_eve.getOtherweight();
					}
					//第四季度
					if(z_1>9){
						gwEve_q4_actlost=gwEve_q4_actlost+gw_eve.getActlost();
						gwEve_q4_avgbuttomweight2=gwEve_q4_avgbuttomweight2+gw_eve.getAvgbuttomweight2();
						gwEve_q4_badweit=gwEve_q4_badweit+gw_eve.getBadweit();
						gwEve_q4_otherbadweight=gwEve_q4_otherbadweight+gw_eve.getOtherbadweight();
						gwEve_q4_sideweit=gwEve_q4_sideweit+gw_eve.getSideweit();
						gwEve_q4_sumActualpairs=gwEve_q4_sumActualpairs+gwEve_q4_sumActualpairs;
						gwEve_q4_otherweight=gwEve_q4_otherweight+gw_eve.getOtherweight();
					}
					
				}
				if(gw!=null){
					if(gw.getVWebgwE002()==null){
						gw.setVWebgwE002(big);
					}
					if(gw.getVWebgwE003()==null){
						gw.setVWebgwE003(big);
					}
					if(gw.getVWebgwE004()==null){
						gw.setVWebgwE004(big);
					}
					if(gw.getVWebgwE005()==null){
						gw.setVWebgwE005(big);
					}
					if(gw.getVWebgwE006()==null){
						gw.setVWebgwE006(big);
					}
					list_gw_2.add(gw.getVWebgwE001());list_gw_2.add(gw.getVWebgwE002().doubleValue());
					list_gw_2.add(gw.getVWebgwE003().doubleValue());list_gw_2.add(gw.getVWebgwE004().doubleValue());
					list_gw_2.add(gw.getVWebgwE005().doubleValue());list_gw_2.add(gw.getVWebgwE006().doubleValue());					
					list_gw.add(list_gw_2);
					
				}else{
					//為空時,也要添加一個空null,以補全數量
					list_gw.add(null);//每個月為空					
				}
				//(6)判斷side,side_eve
				if(side_eve!=null){
					//第一季度
					if(z_1<4){
						sideEve_q1_avgbuttomweight=sideEve_q1_avgbuttomweight+side_eve.getAvgbuttomweight();
						sideEve_q1_avgbuttomweight2=sideEve_q1_avgbuttomweight2+side_eve.getAvgbuttomweight2();
						sideEve_q1_avgprice=sideEve_q1_avgprice+side_eve.getAvgprice();
						sideEve_q1_badcount=sideEve_q1_badcount+side_eve.getBadcount();
						sideEve_q1_badweit=sideEve_q1_badweit+side_eve.getBadweit();
						sideEve_q1_otherbadweight=sideEve_q1_otherbadweight+side_eve.getOtherbadweight();
						sideEve_q1_sideweit=sideEve_q1_sideweit+side_eve.getSideweit();
						sideEve_q1_sumActualpairs=sideEve_q1_sumActualpairs+side_eve.getSumActualpairs().doubleValue();
						sideEve_q1_otherweight=sideEve_q1_otherweight+side_eve.getOtherweight();
					}
					//第二季度
					if(z_1>3&&z_1<7){
						sideEve_q2_avgbuttomweight=sideEve_q2_avgbuttomweight+side_eve.getAvgbuttomweight();
						sideEve_q2_avgbuttomweight2=sideEve_q2_avgbuttomweight2+side_eve.getAvgbuttomweight2();
						sideEve_q2_avgprice=sideEve_q2_avgprice+side_eve.getAvgprice();
						sideEve_q2_badcount=sideEve_q2_badcount+side_eve.getBadcount();
						sideEve_q2_badweit=sideEve_q2_badweit+side_eve.getBadweit();
						sideEve_q2_otherbadweight=sideEve_q2_otherbadweight+side_eve.getOtherbadweight();
						sideEve_q2_sideweit=sideEve_q2_sideweit+side_eve.getSideweit();
						sideEve_q2_sumActualpairs=sideEve_q2_sumActualpairs+side_eve.getSumActualpairs().doubleValue();
						sideEve_q2_otherweight=sideEve_q2_otherweight+side_eve.getOtherweight();
					}
					//第三季度
					if(z_1>6&&z_1<10){
						sideEve_q3_avgbuttomweight=sideEve_q3_avgbuttomweight+side_eve.getAvgbuttomweight();
						sideEve_q3_avgbuttomweight2=sideEve_q3_avgbuttomweight2+side_eve.getAvgbuttomweight2();
						sideEve_q3_avgprice=sideEve_q3_avgprice+side_eve.getAvgprice();
						sideEve_q3_badcount=sideEve_q3_badcount+side_eve.getBadcount();
						sideEve_q3_badweit=sideEve_q3_badweit+side_eve.getBadweit();
						sideEve_q3_otherbadweight=sideEve_q3_otherbadweight+side_eve.getOtherbadweight();
						sideEve_q3_sideweit=sideEve_q3_sideweit+side_eve.getSideweit();
						sideEve_q3_sumActualpairs=sideEve_q3_sumActualpairs+side_eve.getSumActualpairs().doubleValue();
						sideEve_q3_otherweight=sideEve_q3_otherweight+side_eve.getOtherweight();
					}
					//第四季度
					if(z_1>9){
						sideEve_q4_avgbuttomweight=sideEve_q4_avgbuttomweight+side_eve.getAvgbuttomweight();
						sideEve_q4_avgbuttomweight2=sideEve_q4_avgbuttomweight2+side_eve.getAvgbuttomweight2();
						sideEve_q4_avgprice=sideEve_q4_avgprice+side_eve.getAvgprice();
						sideEve_q4_badcount=sideEve_q4_badcount+side_eve.getBadcount();
						sideEve_q4_badweit=sideEve_q4_badweit+side_eve.getBadweit();
						sideEve_q4_otherbadweight=sideEve_q4_otherbadweight+side_eve.getOtherbadweight();
						sideEve_q4_sideweit=sideEve_q4_sideweit+side_eve.getSideweit();
						sideEve_q4_sumActualpairs=sideEve_q4_sumActualpairs+side_eve.getSumActualpairs().doubleValue();	
						sideEve_q4_otherweight=sideEve_q4_otherweight+side_eve.getOtherweight();
					}
				}
				if(side!=null){
					list_side_2.add(side.getVWebsideF001());list_side_2.add(side.getVWebsideF002());
					list_side_2.add(side.getVWebsideF003().doubleValue());list_side_2.add(side.getVWebsideF004().doubleValue());
					list_side_2.add(side.getVWebsideF005().doubleValue());list_side_2.add(side.getVWebsideF006());
					list_side_2.add(side.getVWebsideF007());list_side_2.add(side.getVWebsideF008().doubleValue());
					list_side_2.add(side.getVWebsideF009().doubleValue());list_side_2.add(side.getVWebsideF010());
					list_side_2.add(side.getVWebsideF011().doubleValue());list_side_2.add(side.getVWebsideF012().doubleValue());
					list_side_2.add(side.getVWebsideF013().doubleValue());
					list_side.add(list_side_2);
					
					
				}else{
					//為空時,也要添加一個空null,以補全數量
					list_side.add(null);//每個月為空					
				}
				//(7)判斷store,store_eve
				if(store_eve!=null){
					//第一季度
					if(z_1<4){
						storeEve_q1_badcount=storeEve_q1_badcount+store_eve.getBadcount();
						storeEve_q1_hostinvcount=storeEve_q1_hostinvcount+store_eve.getHostinvcount();
						storeEve_q1_innum=storeEve_q1_innum+store_eve.getInnum();
						storeEve_q1_invcount=storeEve_q1_invcount+store_eve.getInvcount();
						storeEve_q1_lagInnum=storeEve_q1_lagInnum+store_eve.getLagInnum().doubleValue();
						storeEve_q1_lagInstorenum=storeEve_q1_lagInstorenum+store_eve.getLagInstorenum().doubleValue();
						storeEve_q1_lagMakestorenum=storeEve_q1_lagMakestorenum+store_eve.getLagMakestrorenum().doubleValue();
						storeEve_q1_lagNoliststorenum=storeEve_q1_lagNoliststorenum+store_eve.getLagNoliststrorenum().doubleValue();
						storeEve_q1_lagoutOutnum=storeEve_q1_lagoutOutnum+store_eve.getLagOutnum().doubleValue();
						storeEve_q1_lagStorenum=storeEve_q1_lagStorenum+store_eve.getLagStorenum().doubleValue();
						storeEve_q1_makestorenum=storeEve_q1_makestorenum+store_eve.getMakestrorenum();
						storeEve_q1_minusnum=storeEve_q1_minusnum+store_eve.getMinusnum();
						storeEve_q1_noliststorenum=storeEve_q1_noliststorenum+store_eve.getNoliststrorenum();
						storeEve_q1_outnum=storeEve_q1_outnum+store_eve.getOutnum();
						storeEve_q1_storenum=storeEve_q1_storenum+store_eve.getStorenum();
						storeEve_q1_sumhostpairs=storeEve_q1_sumhostpairs+store_eve.getSumhostpairs().doubleValue();
						storeEve_q1_sumsamplepairs=storeEve_q1_sumsamplepairs+store_eve.getSumsamplepairs().doubleValue();
					}
					//第二季度
					if(z_1>3&&z_1<7){
						storeEve_q2_badcount=storeEve_q2_badcount+store_eve.getBadcount();
						storeEve_q2_hostinvcount=storeEve_q2_hostinvcount+store_eve.getHostinvcount();
						storeEve_q2_innum=storeEve_q2_innum+store_eve.getInnum();
						storeEve_q2_invcount=storeEve_q2_invcount+store_eve.getInvcount();
						storeEve_q2_lagInnum=storeEve_q2_lagInnum+store_eve.getLagInnum().doubleValue();
						storeEve_q2_lagInstorenum=storeEve_q2_lagInstorenum+store_eve.getLagInstorenum().doubleValue();
						storeEve_q2_lagMakestorenum=storeEve_q2_lagMakestorenum+store_eve.getLagMakestrorenum().doubleValue();
						storeEve_q2_lagNoliststorenum=storeEve_q2_lagNoliststorenum+store_eve.getLagNoliststrorenum().doubleValue();
						storeEve_q2_lagoutOutnum=storeEve_q2_lagoutOutnum+store_eve.getLagOutnum().doubleValue();
						storeEve_q2_lagStorenum=storeEve_q2_lagStorenum+store_eve.getLagStorenum().doubleValue();
						storeEve_q2_makestorenum=storeEve_q2_makestorenum+store_eve.getMakestrorenum();
						storeEve_q2_minusnum=storeEve_q2_minusnum+store_eve.getMinusnum();
						storeEve_q2_noliststorenum=storeEve_q2_noliststorenum+store_eve.getNoliststrorenum();
						storeEve_q2_outnum=storeEve_q2_outnum+store_eve.getOutnum();
						storeEve_q2_storenum=storeEve_q2_storenum+store_eve.getStorenum();
						storeEve_q2_sumhostpairs=storeEve_q2_sumhostpairs+store_eve.getSumhostpairs().doubleValue();
						storeEve_q2_sumsamplepairs=storeEve_q2_sumsamplepairs+store_eve.getSumsamplepairs().doubleValue();												
					}
					//第三季度
					if(z_1>6&&z_1<10){
						storeEve_q3_badcount=storeEve_q3_badcount+store_eve.getBadcount();
						storeEve_q3_hostinvcount=storeEve_q3_hostinvcount+store_eve.getHostinvcount();
						storeEve_q3_innum=storeEve_q3_innum+store_eve.getInnum();
						storeEve_q3_invcount=storeEve_q3_invcount+store_eve.getInvcount();
						storeEve_q3_lagInnum=storeEve_q3_lagInnum+store_eve.getLagInnum().doubleValue();
						storeEve_q3_lagInstorenum=storeEve_q3_lagInstorenum+store_eve.getLagInstorenum().doubleValue();
						storeEve_q3_lagMakestorenum=storeEve_q3_lagMakestorenum+store_eve.getLagMakestrorenum().doubleValue();
						storeEve_q3_lagNoliststorenum=storeEve_q3_lagNoliststorenum+store_eve.getLagNoliststrorenum().doubleValue();
						storeEve_q3_lagoutOutnum=storeEve_q3_lagoutOutnum+store_eve.getLagOutnum().doubleValue();
						storeEve_q3_lagStorenum=storeEve_q3_lagStorenum+store_eve.getLagStorenum().doubleValue();
						storeEve_q3_makestorenum=storeEve_q3_makestorenum+store_eve.getMakestrorenum();
						storeEve_q3_minusnum=storeEve_q3_minusnum+store_eve.getMinusnum();
						storeEve_q3_noliststorenum=storeEve_q3_noliststorenum+store_eve.getNoliststrorenum();
						storeEve_q3_outnum=storeEve_q3_outnum+store_eve.getOutnum();
						storeEve_q3_storenum=storeEve_q3_storenum+store_eve.getStorenum();
						storeEve_q3_sumhostpairs=storeEve_q3_sumhostpairs+store_eve.getSumhostpairs().doubleValue();
						storeEve_q3_sumsamplepairs=storeEve_q3_sumsamplepairs+store_eve.getSumsamplepairs().doubleValue();
					}
					//第四季度
					if(z_1>9){
						storeEve_q4_badcount=storeEve_q4_badcount+store_eve.getBadcount();
						storeEve_q4_hostinvcount=storeEve_q4_hostinvcount+store_eve.getHostinvcount();
						storeEve_q4_innum=storeEve_q4_innum+store_eve.getInnum();
						storeEve_q4_invcount=storeEve_q4_invcount+store_eve.getInvcount();
						storeEve_q4_lagInnum=storeEve_q4_lagInnum+store_eve.getLagInnum().doubleValue();
						storeEve_q4_lagInstorenum=storeEve_q4_lagInstorenum+store_eve.getLagInstorenum().doubleValue();
						storeEve_q4_lagMakestorenum=storeEve_q4_lagMakestorenum+store_eve.getLagMakestrorenum().doubleValue();
						storeEve_q4_lagNoliststorenum=storeEve_q4_lagNoliststorenum+store_eve.getLagNoliststrorenum().doubleValue();
						storeEve_q4_lagoutOutnum=storeEve_q4_lagoutOutnum+store_eve.getLagOutnum().doubleValue();
						storeEve_q4_lagStorenum=storeEve_q4_lagStorenum+store_eve.getLagStorenum().doubleValue();
						storeEve_q4_makestorenum=storeEve_q4_makestorenum+store_eve.getMakestrorenum();
						storeEve_q4_minusnum=storeEve_q4_minusnum+store_eve.getMinusnum();
						storeEve_q4_noliststorenum=storeEve_q4_noliststorenum+store_eve.getNoliststrorenum();
						storeEve_q4_outnum=storeEve_q4_outnum+store_eve.getOutnum();
						storeEve_q4_storenum=storeEve_q4_storenum+store_eve.getStorenum();
						storeEve_q4_sumhostpairs=storeEve_q4_sumhostpairs+store_eve.getSumhostpairs().doubleValue();
						storeEve_q4_sumsamplepairs=storeEve_q4_sumsamplepairs+store_eve.getSumsamplepairs().doubleValue();												
					}
				}
				if(store!=null){
					//G001-G007(和G014)為上月數據,有可能出現null,所以要進行判斷
					if(store.getVWebstoreG001()==null){
						store.setVWebstoreG001(big);
					}
					if(store.getVWebstoreG002()==null){
						store.setVWebstoreG002(big);
					}
					if(store.getVWebstoreG003()==null){
						store.setVWebstoreG003(big);
					}
					if(store.getVWebstoreG004()==null){
						store.setVWebstoreG004(big);
					}
					if(store.getVWebstoreG005()==null){
						store.setVWebstoreG005(big);						
					}
					if(store.getVWebstoreG006()==null){
						store.setVWebstoreG006(big);
					}
					if(store.getVWebstoreG007()==null){
						store.setVWebstoreG007(big);
					}
					if(store.getVWebstoreG014()==null){
						store.setVWebstoreG014(big);
					}
					list_store_2.add(store.getVWebstoreG001().doubleValue());list_store_2.add(store.getVWebstoreG002().doubleValue());
					list_store_2.add(store.getVWebstoreG003().doubleValue());list_store_2.add(store.getVWebstoreG004().doubleValue());
					list_store_2.add(store.getVWebstoreG005().doubleValue());list_store_2.add(store.getVWebstoreG006().doubleValue());
					list_store_2.add(store.getVWebstoreG007().doubleValue());
					list_store_2.add(store.getVWebstoreG008());
					list_store_2.add(store.getVWebstoreG009());list_store_2.add(store.getVWebstoreG010());
					list_store_2.add(store.getVWebstoreG011());list_store_2.add(store.getVWebstoreG012());
					list_store_2.add(store.getVWebstoreG013().doubleValue());list_store_2.add(store.getVWebstoreG014().doubleValue());
					list_store_2.add(store.getVWebstoreG015());list_store_2.add(store.getVWebstoreG016());
					list_store_2.add(store.getVWebstoreG017().doubleValue());list_store_2.add(store.getVWebstoreG018().doubleValue());									
					list_store.add(list_store_2);
					
					
				}else{
					//為空時,也要添加一個空null,以補全數量
					list_store.add(null);//每個月為空					
				}
				//(8)判斷wlo,wlo_eve
				if(wlo_eve!=null){
					//第一季度
					if(z_1<4){
						wloEve_q1_electricdu=wloEve_q1_electricdu+wlo_eve.getElectricdu();
						wloEve_q1_electricusd=wloEve_q1_electricusd+wlo_eve.getElectricusd();
						wloEve_q1_gaston=wloEve_q1_gaston+wlo_eve.getGaston();
						wloEve_q1_gasusd=wloEve_q1_gasusd+wlo_eve.getGasusd();
						wloEve_q1_oilton=wloEve_q1_oilton+wlo_eve.getOilton();
						wloEve_q1_oilusd=wloEve_q1_oilusd+wlo_eve.getOilusd();
						wloEve_q1_sumactualdemo=wloEve_q1_sumactualdemo+wlo_eve.getSumactualdemo().doubleValue();
						wloEve_q1_waterton=wloEve_q1_waterton+wlo_eve.getWaterton();
						wloEve_q1_waterusd=wloEve_q1_waterusd+wlo_eve.getWaterusd();
					}
					//第二季度
					if(z_1>3&&z_1<7){
						wloEve_q2_electricdu=wloEve_q2_electricdu+wlo_eve.getElectricdu();
						wloEve_q2_electricusd=wloEve_q2_electricusd+wlo_eve.getElectricusd();
						wloEve_q2_gaston=wloEve_q2_gaston+wlo_eve.getGaston();
						wloEve_q2_gasusd=wloEve_q2_gasusd+wlo_eve.getGasusd();
						wloEve_q2_oilton=wloEve_q2_oilton+wlo_eve.getOilton();
						wloEve_q2_oilusd=wloEve_q2_oilusd+wlo_eve.getOilusd();
						wloEve_q2_sumactualdemo=wloEve_q2_sumactualdemo+wlo_eve.getSumactualdemo().doubleValue();
						wloEve_q2_waterton=wloEve_q2_waterton+wlo_eve.getWaterton();
						wloEve_q2_waterusd=wloEve_q2_waterusd+wlo_eve.getWaterusd();												
					}
					//第三季度
					if(z_1>6&&z_1<10){
						wloEve_q3_electricdu=wloEve_q3_electricdu+wlo_eve.getElectricdu();
						wloEve_q3_electricusd=wloEve_q3_electricusd+wlo_eve.getElectricusd();
						wloEve_q3_gaston=wloEve_q3_gaston+wlo_eve.getGaston();
						wloEve_q3_gasusd=wloEve_q3_gasusd+wlo_eve.getGasusd();
						wloEve_q3_oilton=wloEve_q3_oilton+wlo_eve.getOilton();
						wloEve_q3_oilusd=wloEve_q3_oilusd+wlo_eve.getOilusd();
						wloEve_q3_sumactualdemo=wloEve_q3_sumactualdemo+wlo_eve.getSumactualdemo().doubleValue();
						wloEve_q3_waterton=wloEve_q3_waterton+wlo_eve.getWaterton();
						wloEve_q3_waterusd=wloEve_q3_waterusd+wlo_eve.getWaterusd();
					}
					//第四季度
					if(z_1>9){
						wloEve_q4_electricdu=wloEve_q4_electricdu+wlo_eve.getElectricdu();
						wloEve_q4_electricusd=wloEve_q4_electricusd+wlo_eve.getElectricusd();
						wloEve_q4_gaston=wloEve_q4_gaston+wlo_eve.getGaston();
						wloEve_q4_gasusd=wloEve_q4_gasusd+wlo_eve.getGasusd();
						wloEve_q4_oilton=wloEve_q4_oilton+wlo_eve.getOilton();
						wloEve_q4_oilusd=wloEve_q4_oilusd+wlo_eve.getOilusd();
						wloEve_q4_sumactualdemo=wloEve_q4_sumactualdemo+wlo_eve.getSumactualdemo().doubleValue();
						wloEve_q4_waterton=wloEve_q4_waterton+wlo_eve.getWaterton();
						wloEve_q4_waterusd=wloEve_q4_waterusd+wlo_eve.getWaterusd();																		
					}
				}
				if(wlo!=null){
					list_wlo_2.add(wlo.getVWebwloH001().doubleValue());list_wlo_2.add(wlo.getVWebwloH002());
					list_wlo_2.add(wlo.getVWebwloH003().doubleValue());list_wlo_2.add(wlo.getVWebwloH004());
					list_wlo_2.add(wlo.getVWebwloH005().doubleValue());list_wlo_2.add(wlo.getVWebwloH006());
					list_wlo_2.add(wlo.getVWebwloH007().doubleValue());list_wlo_2.add(wlo.getVWebwloH008());
					list_wlo_2.add(wlo.getVWebwloH009().doubleValue());list_wlo_2.add(wlo.getVWebwloH010());
					list_wlo_2.add(wlo.getVWebwloH011().doubleValue());list_wlo_2.add(wlo.getVWebwloH012());
					list_wlo_2.add(wlo.getVWebwloH013().doubleValue());list_wlo_2.add(wlo.getVWebwloH014().doubleValue());
					list_wlo_2.add(wlo.getVWebwloH015().doubleValue());list_wlo_2.add(wlo.getVWebwloH016());
					list_wlo_2.add(wlo.getVWebwloH017().doubleValue());									
					list_wlo.add(list_wlo_2);
					
				}else{
					//為空時,也要添加一個空null,以補全數量
					list_wlo.add(null);//每個月為空					
				}
				//(9)判斷back,back_eve
				if(back_eve!=null){
					//第一季度
					if(z_1<4){
						backEve_q1_backfeed=backEve_q1_backfeed+back_eve.getBackfeed();
						backEve_q1_oilback=backEve_q1_oilback+back_eve.getOilback();
						backEve_q1_thickusd=backEve_q1_thickusd+back_eve.getThickused();
					}
					//第二季度
					if(z_1>3&&z_1<7){
						backEve_q2_backfeed=backEve_q2_backfeed+back_eve.getBackfeed();
						backEve_q2_oilback=backEve_q2_oilback+back_eve.getOilback();
						backEve_q2_thickusd=backEve_q2_thickusd+back_eve.getThickused();											
					}
					//第三季度
					if(z_1>6&&z_1<10){
						backEve_q3_backfeed=backEve_q3_backfeed+back_eve.getBackfeed();
						backEve_q3_oilback=backEve_q3_oilback+back_eve.getOilback();
						backEve_q3_thickusd=backEve_q3_thickusd+back_eve.getThickused();
					}
					//第四季度
					if(z_1>9){
						backEve_q4_backfeed=backEve_q4_backfeed+back_eve.getBackfeed();
						backEve_q4_oilback=backEve_q4_oilback+back_eve.getOilback();
						backEve_q4_thickusd=backEve_q4_thickusd+back_eve.getThickused();												
					}
				}
				if(back!=null){
					Double back_i003=back.getBackfeed()/back.getThickused();
					Double back_i005=back.getOilback()/back.getThickused();
					Double back_i006=back.getBackfeed()+back.getOilback();
					Double back_i007=back.getBackfeed()/back.getThickused()+back.getOilback()/back.getThickused();
					
					list_back_2.add(back.getThickused());list_back_2.add(back.getBackfeed());
					list_back_2.add(back_i003);list_back_2.add(back.getOilback());
					list_back_2.add(back_i005);list_back_2.add(back_i006);
					list_back_2.add(back_i007);								
					list_back.add(list_back_2);
					
				}else{
					//為空時,也要添加一個空null,以補全數量
					list_back.add(null);//每個月為空
					
				}
				//(8)判斷webcost,webcost_eve
				if(webcost_eve!=null){
					//第一季度
					if(z_1<4){
						webcostEve_q1_sumActualdemo=webcostEve_q1_sumActualdemo+webcost_eve.getSumActualdemo().doubleValue();
						webcostEve_q1_gluestoremoney=webcostEve_q1_gluestoremoney+webcost_eve.getGluestoremoney();
						webcostEve_q1_drugsused=webcostEve_q1_drugsused+webcost_eve.getDrugsused();
						webcostEve_q1_leavemoney=webcostEve_q1_leavemoney+webcost_eve.getLeavemoney();
						webcostEve_q1_whitenum=webcostEve_q1_whitenum+webcost_eve.getWhitenum();
						webcostEve_q1_blacknum=webcostEve_q1_blacknum+webcost_eve.getBlacknum();
						webcostEve_q1_gluenum=webcostEve_q1_gluenum+webcost_eve.getGluenum();
						webcostEve_q1_greynum=webcostEve_q1_greynum+webcost_eve.getGreynum();
						webcostEve_q1_othernum=webcostEve_q1_othernum+webcost_eve.getOthernum();
					}
					//第二季度
					if(z_1>3&&z_1<7){
						webcostEve_q2_sumActualdemo=webcostEve_q2_sumActualdemo+webcost_eve.getSumActualdemo().doubleValue();
						webcostEve_q2_gluestoremoney=webcostEve_q2_gluestoremoney+webcost_eve.getGluestoremoney();
						webcostEve_q2_drugsused=webcostEve_q2_drugsused+webcost_eve.getDrugsused();
						webcostEve_q2_leavemoney=webcostEve_q2_leavemoney+webcost_eve.getLeavemoney();
						webcostEve_q2_whitenum=webcostEve_q2_whitenum+webcost_eve.getWhitenum();
						webcostEve_q2_blacknum=webcostEve_q2_blacknum+webcost_eve.getBlacknum();
						webcostEve_q2_gluenum=webcostEve_q2_gluenum+webcost_eve.getGluenum();
						webcostEve_q2_greynum=webcostEve_q2_greynum+webcost_eve.getGreynum();
						webcostEve_q2_othernum=webcostEve_q2_othernum+webcost_eve.getOthernum();												
					}
					//第三季度
					if(z_1>6&&z_1<10){
						webcostEve_q3_sumActualdemo=webcostEve_q3_sumActualdemo+webcost_eve.getSumActualdemo().doubleValue();
						webcostEve_q3_gluestoremoney=webcostEve_q3_gluestoremoney+webcost_eve.getGluestoremoney();
						webcostEve_q3_drugsused=webcostEve_q3_drugsused+webcost_eve.getDrugsused();
						webcostEve_q3_leavemoney=webcostEve_q3_leavemoney+webcost_eve.getLeavemoney();
						webcostEve_q3_whitenum=webcostEve_q3_whitenum+webcost_eve.getWhitenum();
						webcostEve_q3_blacknum=webcostEve_q3_blacknum+webcost_eve.getBlacknum();
						webcostEve_q3_gluenum=webcostEve_q3_gluenum+webcost_eve.getGluenum();
						webcostEve_q3_greynum=webcostEve_q3_greynum+webcost_eve.getGreynum();
						webcostEve_q3_othernum=webcostEve_q3_othernum+webcost_eve.getOthernum();
					}
					//第四季度
					if(z_1>9){
						webcostEve_q4_sumActualdemo=webcostEve_q4_sumActualdemo+webcost_eve.getSumActualdemo().doubleValue();
						webcostEve_q4_gluestoremoney=webcostEve_q4_gluestoremoney+webcost_eve.getGluestoremoney();
						webcostEve_q4_drugsused=webcostEve_q4_drugsused+webcost_eve.getDrugsused();
						webcostEve_q4_leavemoney=webcostEve_q4_leavemoney+webcost_eve.getLeavemoney();
						webcostEve_q4_whitenum=webcostEve_q4_whitenum+webcost_eve.getWhitenum();
						webcostEve_q4_blacknum=webcostEve_q4_blacknum+webcost_eve.getBlacknum();
						webcostEve_q4_gluenum=webcostEve_q4_gluenum+webcost_eve.getGluenum();
						webcostEve_q4_greynum=webcostEve_q4_greynum+webcost_eve.getGreynum();
						webcostEve_q4_othernum=webcostEve_q4_othernum+webcost_eve.getOthernum();																		
					}
				}
				if(webcost!=null){									
					list_webcost_2.add(webcost.getVWebcostJ001());list_webcost_2.add(webcost.getVWebcostJ002().doubleValue());
					list_webcost_2.add(webcost.getVWebcostJ003());list_webcost_2.add(webcost.getVWebcostJ004().doubleValue());
					list_webcost_2.add(webcost.getVWebcostJ005());list_webcost_2.add(webcost.getVWebcostJ006().doubleValue());
					list_webcost_2.add(webcost.getVWebcostJ007());list_webcost_2.add(webcost.getVWebcostJ008());
					list_webcost_2.add(webcost.getVWebcostJ009());list_webcost_2.add(webcost.getVWebcostJ010());
					list_webcost_2.add(webcost.getVWebcostJ011().doubleValue());
					list_webcost.add(list_webcost_2);					
				}else{
					//為空時,也要添加一個空null,以補全數量
					list_webcost.add(null);//每個月為空					
				}
				
				/**
				 * 季度和年度數據統計
				 */
				//第一季度
				if(z_1==3){
					//孔位數回轉數--machine
					list_machine_3=this.findResult_machine(3,
							machineEve_q1_sumWorkday, machineEve_q1_sumEverydemo, machineEve_q1_sumStandarddemo, machineEve_q1_sumActualdemo, machineEve_q1_sumActualpairs,
							machineEve_q1_avgbuttomweight, machineEve_q1_avgbuttomweight2, 
							machineEve_q1_hole);
					list_machine.add(list_machine_3);
					//生產與請款狀況--inv
					list_inv_3=this.findResult_inv(3,invEve_q1_sumActualpairs, invEve_q1_invcount, invEve_q1_paypair);							
					list_inv.add(list_inv_3);
                    //出貨和退貨--mix1
					list_mix1_3=this.findResult_mix1(3, mix1Eve_q1_sumoutnum, mix1Eve_q1_sumbacknum);							
					list_mix1.add(list_mix1_3);
					//人員效能--person
					list_person_3=this.findResult_person(3, 
							personEve_q1_personzg, personEve_q1_personjg, personEve_q1_timezg, personEve_q1_timejg, 
							personEve_q1_addtimezg, personEve_q1_addtimejg, personEve_q1_leavenumzg, personEve_q1_leavenumjg,
							personEve_q1_wagezgUsd, personEve_q1_wagejgUsd, personEve_q1_addmoneyzg, personEve_q1_addmoneyjg,
							personEve_q1_sumStandarddemo, personEve_q1_sumActualdemo, personEve_q1_sumActualpairs);
					list_person.add(list_person_3);
					//關務損耗--gw
					list_gw_3=this.findResult_gw(3,
							gwEve_q1_sumActualpairs, gwEve_q1_sideweit, 
							gwEve_q1_badweit, gwEve_q1_otherbadweight, gwEve_q1_actlost,
							gwEve_q1_avgbuttomweight2,gwEve_q1_otherweight);						
					list_gw.add(list_gw_3);
					//邊料不良重量分析-side
					list_side_3=this.findResult_side(3, 
							sideEve_q1_sumActualpairs, sideEve_q1_sideweit, sideEve_q1_badcount, 
							sideEve_q1_badweit, sideEve_q1_otherbadweight, sideEve_q1_avgbuttomweight, 
							sideEve_q1_avgbuttomweight2, sideEve_q1_avgprice,sideEve_q1_otherweight);
					list_side.add(list_side_3);
					//庫存--store
					list_store_3=this.findResult_store(3, 
							storeEve_q1_sumhostpairs, storeEve_q1_sumsamplepairs, storeEve_q1_invcount,
							storeEve_q1_hostinvcount, storeEve_q1_badcount, storeEve_q1_lagStorenum, 
							storeEve_q1_lagNoliststorenum, storeEve_q1_lagMakestorenum, storeEve_q1_lagoutOutnum, 
							storeEve_q1_lagInnum, storeEve_q1_lagInstorenum, storeEve_q1_storenum, storeEve_q1_noliststorenum,
							storeEve_q1_makestorenum, storeEve_q1_outnum, storeEve_q1_innum, storeEve_q1_minusnum);				
					list_store.add(list_store_3);					
					//水電油--wlo
					list_wlo_3=this.findResult_wlo(3, 
							wloEve_q1_sumactualdemo, wloEve_q1_waterton, wloEve_q1_waterusd, 
							wloEve_q1_electricdu, wloEve_q1_electricusd, wloEve_q1_gaston,
							wloEve_q1_gasusd, wloEve_q1_oilton, wloEve_q1_oilusd);							
					list_wlo.add(list_wlo_3);
					//回頭料--back
					list_back_3=this.findResult_back(3, backEve_q1_thickusd, backEve_q1_backfeed, backEve_q1_oilback);						
					list_back.add(list_back_3);
					//色料回收粉
					list_webcost_3=this.findResult_webcost(3, webcostEve_q1_sumActualdemo, webcostEve_q1_gluestoremoney, webcostEve_q1_drugsused, 
							webcostEve_q1_leavemoney, webcostEve_q1_whitenum, webcostEve_q1_blacknum, webcostEve_q1_gluenum, 
							webcostEve_q1_greynum, webcostEve_q1_othernum);
					list_webcost.add(list_webcost_3);
				}
				//第二季度,上半年
				if(z_1==6){
					//孔位數回轉數--machine
					machineEve_half1_sumWorkday=machineEve_q1_sumWorkday+machineEve_q2_sumWorkday;
					machineEve_half1_sumEverydemo=machineEve_q1_sumEverydemo+machineEve_q2_sumEverydemo;
					machineEve_half1_sumStandarddemo=machineEve_q1_sumStandarddemo+machineEve_q2_sumStandarddemo;
					machineEve_half1_sumActualdemo=machineEve_q1_sumActualdemo+machineEve_q2_sumActualdemo;
					machineEve_half1_sumActualpairs=machineEve_q1_sumActualpairs+machineEve_q2_sumActualpairs;
					machineEve_half1_avgbuttomweight=machineEve_q1_avgbuttomweight+machineEve_q2_avgbuttomweight;
					machineEve_half1_avgbuttomweight2=machineEve_q1_avgbuttomweight2+machineEve_q2_avgbuttomweight2;
					machineEve_half1_hole=machineEve_q1_hole+machineEve_q2_hole;
					list_machine_3=this.findResult_machine(3,
							machineEve_q2_sumWorkday, machineEve_q2_sumEverydemo, machineEve_q2_sumStandarddemo, machineEve_q2_sumActualdemo, machineEve_q2_sumActualpairs,
							machineEve_q2_avgbuttomweight, machineEve_q2_avgbuttomweight2, 
							machineEve_q2_hole);
					list_machine.add(list_machine_3);
					list_machine_4=this.findResult_machine(6,
							machineEve_half1_sumWorkday, machineEve_half1_sumEverydemo, machineEve_half1_sumStandarddemo, machineEve_half1_sumActualdemo, machineEve_half1_sumActualpairs,
							machineEve_half1_avgbuttomweight, machineEve_half1_avgbuttomweight2, 
							machineEve_half1_hole);											
					list_machine.add(list_machine_4);
					//生產與請款狀況--inv
					invEve_half1_sumActualpairs=invEve_q1_sumActualpairs+invEve_q2_sumActualpairs;
					invEve_half1_invcount=invEve_q1_invcount+invEve_q2_invcount;
					invEve_half1_paypair=invEve_q1_paypair+invEve_q2_invcount;
					list_inv_3=this.findResult_inv(3,invEve_q2_sumActualpairs, invEve_q2_invcount, invEve_q2_paypair);													
					list_inv.add(list_inv_3);
					
					list_inv_4=this.findResult_inv(6,invEve_half1_sumActualpairs, invEve_half1_invcount, invEve_half1_paypair);							
					list_inv.add(list_inv_4);
                    //出貨和退貨--mix1
					mix1Eve_half1_sumbacknum=mix1Eve_q1_sumbacknum+mix1Eve_q2_sumbacknum;
					mix1Eve_half1_sumoutnum=mix1Eve_q1_sumoutnum+mix1Eve_q2_sumoutnum;
					list_mix1_3=this.findResult_mix1(3, mix1Eve_q2_sumoutnum, mix1Eve_q2_sumbacknum);												
					list_mix1.add(list_mix1_3);
												
					list_mix1_4=this.findResult_mix1(6, mix1Eve_half1_sumoutnum, mix1Eve_half1_sumbacknum);							
					list_mix1.add(list_mix1_4);
					//人員效能--person
					personEve_half1_addmoneyjg=personEve_q1_addmoneyjg+personEve_q2_addmoneyjg;
					personEve_half1_addmoneyzg=personEve_q1_addmoneyzg+personEve_q2_addmoneyzg;
					personEve_half1_addtimejg=personEve_q1_addtimejg+personEve_q2_addtimejg;
					personEve_half1_addtimezg=personEve_q1_addtimezg+personEve_q2_addtimezg;
					personEve_half1_leavenumjg=personEve_q1_leavenumjg+personEve_q2_leavenumjg;
					personEve_half1_leavenumzg=personEve_q1_leavenumzg+personEve_q2_leavenumzg;
					personEve_half1_personjg=personEve_q1_personjg+personEve_q2_personjg;
					personEve_half1_personzg=personEve_q1_personzg+personEve_q2_personzg;
					personEve_half1_sumActualdemo=personEve_q1_sumActualdemo+personEve_q2_sumActualdemo;
					personEve_half1_sumActualpairs=personEve_q1_sumActualpairs+personEve_q2_sumActualpairs;
					personEve_half1_sumStandarddemo=personEve_q1_sumStandarddemo+personEve_q2_sumStandarddemo;
					personEve_half1_timejg=personEve_q1_timejg+personEve_q2_timejg;
					personEve_half1_timezg=personEve_q1_timezg+personEve_q2_timezg;
					personEve_half1_wagejgUsd=personEve_q1_wagejgUsd+personEve_q2_wagejgUsd;
					personEve_half1_wagezgUsd=personEve_q1_wagezgUsd+personEve_q2_wagezgUsd;
					list_person_3=this.findResult_person(3, 
							personEve_q2_personzg, personEve_q2_personjg, personEve_q2_timezg, personEve_q2_timejg, 
							personEve_q2_addtimezg, personEve_q2_addtimejg, personEve_q2_leavenumzg, personEve_q2_leavenumjg,
							personEve_q2_wagezgUsd, personEve_q2_wagejgUsd, personEve_q2_addmoneyzg, personEve_q2_addmoneyjg,
							personEve_q2_sumStandarddemo, personEve_q2_sumActualdemo, personEve_q2_sumActualpairs);							
					list_person.add(list_person_3);
					
					list_person_4=this.findResult_person(6, 
							personEve_half1_personzg, personEve_half1_personjg, personEve_half1_timezg, personEve_half1_timejg, 
							personEve_half1_addtimezg, personEve_half1_addtimejg, personEve_half1_leavenumzg, personEve_half1_leavenumjg,
							personEve_half1_wagezgUsd, personEve_half1_wagejgUsd, personEve_half1_addmoneyzg, personEve_half1_addmoneyjg,
							personEve_half1_sumStandarddemo, personEve_half1_sumActualdemo, personEve_half1_sumActualpairs);								
					list_person.add(list_person_4);
					//關務損耗--gw
					gwEve_half1_actlost=gwEve_q1_actlost+gwEve_q2_actlost;
					gwEve_half1_avgbuttomweight2=gwEve_q1_avgbuttomweight2+gwEve_q2_avgbuttomweight2;
					gwEve_half1_badweit=gwEve_q1_badweit+gwEve_q2_badweit;
					gwEve_half1_otherbadweight=gwEve_q1_otherbadweight+gwEve_q2_otherbadweight;
					gwEve_half1_sideweit=gwEve_half1_sideweit+gwEve_q2_sideweit;
					gwEve_half1_sumActualpairs=gwEve_q1_sumActualpairs+gwEve_q2_sumActualpairs;
					gwEve_half1_otherweight=gwEve_q1_otherweight+gwEve_q2_otherweight;
					list_gw_3=this.findResult_gw(3,
							gwEve_q2_sumActualpairs, gwEve_q2_sideweit, 
							gwEve_q2_badweit, gwEve_q2_otherbadweight, gwEve_q2_actlost,
							gwEve_q2_avgbuttomweight2,gwEve_q2_otherweight);						
					list_gw.add(list_gw_3);
					
					list_gw_4=this.findResult_gw(6,
							gwEve_half1_sumActualpairs, gwEve_half1_sideweit, 
							gwEve_half1_badweit, gwEve_half1_otherbadweight, gwEve_half1_actlost,
							gwEve_half1_avgbuttomweight2,gwEve_half1_otherweight);											
					list_gw.add(list_gw_4);
					//邊料不良重量分析-side
					sideEve_half1_avgbuttomweight=sideEve_q1_avgbuttomweight+sideEve_q2_avgbuttomweight;
					sideEve_half1_avgbuttomweight2=sideEve_q1_avgbuttomweight2+sideEve_q2_avgbuttomweight2;
					sideEve_half1_avgprice=sideEve_q1_avgprice+sideEve_q2_avgprice;
					sideEve_half1_badcount=sideEve_q1_badcount+sideEve_q2_badcount;
					sideEve_half1_badweit=sideEve_q1_badweit+sideEve_q2_badweit;
					sideEve_half1_otherbadweight=sideEve_q1_otherbadweight+sideEve_q2_otherbadweight;
					sideEve_half1_sideweit=sideEve_q1_sideweit+sideEve_q2_sideweit;
					sideEve_half1_sumActualpairs=sideEve_q1_sumActualpairs+sideEve_q2_sumActualpairs;
					sideEve_half1_otherweight=sideEve_q1_otherweight+sideEve_q2_otherweight;
					list_side_3=this.findResult_side(3, 
							sideEve_q2_sumActualpairs, sideEve_q2_sideweit, sideEve_q2_badcount, 
							sideEve_q2_badweit, sideEve_q2_otherbadweight, sideEve_q2_avgbuttomweight, 
							sideEve_q2_avgbuttomweight2, sideEve_q2_avgprice,sideEve_q2_otherweight);
					list_side.add(list_side_3);
									
					list_side_4=this.findResult_side(6, 
							sideEve_half1_sumActualpairs, sideEve_half1_sideweit, sideEve_half1_badcount, 
							sideEve_half1_badweit, sideEve_half1_otherbadweight, sideEve_half1_avgbuttomweight, 
							sideEve_half1_avgbuttomweight2, sideEve_half1_avgprice,sideEve_half1_otherweight);
					list_side.add(list_side_4);
					//庫存--store
					storeEve_half1_badcount=storeEve_q1_badcount+storeEve_q2_badcount;
					storeEve_half1_hostinvcount=storeEve_q1_hostinvcount+storeEve_q2_hostinvcount;
					storeEve_half1_innum=storeEve_q1_innum+storeEve_q2_innum;
					storeEve_half1_invcount=storeEve_q1_invcount+storeEve_q2_invcount;
					storeEve_half1_lagInnum=storeEve_q1_lagInnum+storeEve_q2_lagInnum;
					storeEve_half1_lagInstorenum=storeEve_q1_lagInstorenum+storeEve_q2_lagInstorenum;
					storeEve_half1_lagMakestorenum=storeEve_q1_lagMakestorenum+storeEve_q2_lagMakestorenum;
					storeEve_half1_lagNoliststorenum=storeEve_q1_lagNoliststorenum+storeEve_q2_lagNoliststorenum;
					storeEve_half1_lagoutOutnum=storeEve_q1_lagoutOutnum+storeEve_q2_lagoutOutnum;
					storeEve_half1_lagStorenum=storeEve_q1_lagStorenum+storeEve_q2_lagStorenum;
					storeEve_half1_makestorenum=storeEve_q1_makestorenum+storeEve_q2_makestorenum;
					storeEve_half1_minusnum=storeEve_q1_minusnum+storeEve_q2_minusnum;
					storeEve_half1_noliststorenum=storeEve_q1_noliststorenum+storeEve_q2_noliststorenum;
					storeEve_half1_outnum=storeEve_q1_outnum+storeEve_q2_outnum;
					storeEve_half1_storenum=storeEve_q1_storenum+storeEve_q2_storenum;
					storeEve_half1_sumhostpairs=storeEve_q1_sumhostpairs+storeEve_q2_sumhostpairs;
					storeEve_half1_sumsamplepairs=storeEve_q1_sumsamplepairs+storeEve_q2_sumsamplepairs;
					list_store_3=this.findResult_store(3, 
							storeEve_q2_sumhostpairs, storeEve_q2_sumsamplepairs, storeEve_q2_invcount,
							storeEve_q2_hostinvcount, storeEve_q2_badcount, storeEve_q2_lagStorenum, 
							storeEve_q2_lagNoliststorenum, storeEve_q2_lagMakestorenum, storeEve_q2_lagoutOutnum, 
							storeEve_q2_lagInnum, storeEve_q2_lagInstorenum, storeEve_q2_storenum, storeEve_q2_noliststorenum,
							storeEve_q2_makestorenum, storeEve_q2_outnum, storeEve_q2_innum, storeEve_q2_minusnum);								
					list_store.add(list_store_3);
					
					list_store_4=this.findResult_store(6, 
							storeEve_half1_sumhostpairs, storeEve_half1_sumsamplepairs, storeEve_half1_invcount,
							storeEve_half1_hostinvcount, storeEve_half1_badcount, storeEve_half1_lagStorenum, 
							storeEve_half1_lagNoliststorenum, storeEve_half1_lagMakestorenum, storeEve_half1_lagoutOutnum, 
							storeEve_half1_lagInnum, storeEve_half1_lagInstorenum, storeEve_half1_storenum, storeEve_half1_noliststorenum,
							storeEve_half1_makestorenum, storeEve_half1_outnum, storeEve_half1_innum, storeEve_half1_minusnum);						
					list_store.add(list_store_4);
					//水電油--wlo
					wloEve_half1_electricdu=wloEve_q1_electricdu+wloEve_q2_electricdu;
					wloEve_half1_electricusd=wloEve_q1_electricusd+wloEve_q2_electricusd;
					wloEve_half1_gaston=wloEve_q1_gaston+wloEve_q2_gaston;
					wloEve_half1_gasusd=wloEve_q1_gasusd+wloEve_q2_gasusd;
					wloEve_half1_oilton=wloEve_q1_oilton+wloEve_q2_oilton;
					wloEve_half1_oilusd=wloEve_q1_oilusd+wloEve_q2_oilusd;
					wloEve_half1_sumactualdemo=wloEve_q1_sumactualdemo+wloEve_q2_sumactualdemo;
					wloEve_half1_waterton=wloEve_q1_waterton+wloEve_q2_waterton;
					wloEve_half1_waterusd=wloEve_q1_waterusd+wloEve_q2_waterusd;
					list_wlo_3=this.findResult_wlo(3, 
							wloEve_q2_sumactualdemo, wloEve_q2_waterton, wloEve_q2_waterusd, 
							wloEve_q2_electricdu, wloEve_q2_electricusd, wloEve_q2_gaston,
							wloEve_q2_gasusd, wloEve_q2_oilton, wloEve_q2_oilusd);								
					list_wlo.add(list_wlo_3);
					
					list_wlo_4=this.findResult_wlo(6, 
							wloEve_half1_sumactualdemo, wloEve_half1_waterton, wloEve_half1_waterusd, 
							wloEve_half1_electricdu, wloEve_half1_electricusd, wloEve_half1_gaston,
							wloEve_half1_gasusd, wloEve_half1_oilton, wloEve_half1_oilusd);								
					list_wlo.add(list_wlo_4);
					//回頭料--back
					backEve_half1_backfeed=backEve_q1_backfeed+backEve_q2_backfeed;
					backEve_half1_oilback=backEve_q1_oilback+backEve_q2_oilback;
					backEve_half1_thickusd=backEve_q1_thickusd+backEve_q2_thickusd;
					list_back_3=this.findResult_back(3, backEve_q2_thickusd, backEve_q2_backfeed, backEve_q2_oilback);								
					list_back.add(list_back_3);
					
					list_back_4=this.findResult_back(6, backEve_half1_thickusd, backEve_half1_backfeed, backEve_half1_oilback);						
					list_back.add(list_back_4);
					//色料回收粉
					webcostEve_half1_sumActualdemo=webcostEve_q1_sumActualdemo+webcostEve_q2_sumActualdemo;
					webcostEve_half1_gluestoremoney=webcostEve_q1_gluestoremoney+webcostEve_q1_gluestoremoney;
					webcostEve_half1_drugsused=webcostEve_q1_drugsused+webcostEve_q2_drugsused;
					webcostEve_half1_leavemoney=webcostEve_q1_leavemoney+webcostEve_q2_leavemoney;
					webcostEve_half1_whitenum=webcostEve_q1_whitenum+webcostEve_q2_whitenum;
					webcostEve_half1_blacknum=webcostEve_q1_blacknum+webcostEve_q2_blacknum;
					webcostEve_half1_gluenum=webcostEve_q1_gluenum+webcostEve_q2_gluenum;
					webcostEve_half1_greynum=webcostEve_q1_greynum+webcostEve_q2_greynum;
					webcostEve_half1_othernum=webcostEve_q1_othernum+webcostEve_q2_othernum;
					list_webcost_3=this.findResult_webcost(3, webcostEve_q2_sumActualdemo, webcostEve_q2_gluestoremoney, webcostEve_q2_drugsused, 
							webcostEve_q2_leavemoney, webcostEve_q2_whitenum, webcostEve_q2_blacknum, webcostEve_q2_gluenum, 
							webcostEve_q2_greynum, webcostEve_q2_othernum);
					list_webcost.add(list_webcost_3);
					list_webcost_4=this.findResult_webcost(6, webcostEve_half1_sumActualdemo, webcostEve_half1_gluestoremoney, webcostEve_half1_drugsused, webcostEve_half1_leavemoney,
							webcostEve_half1_whitenum, webcostEve_half1_blacknum, webcostEve_half1_gluenum, webcostEve_half1_greynum, webcostEve_half1_othernum);
					list_webcost.add(list_webcost_4);
					
				}
				//第三季度
				if(z_1==9){
					//孔位數回轉數--machine
					list_machine_3=this.findResult_machine(3,
							machineEve_q3_sumWorkday, machineEve_q3_sumEverydemo, machineEve_q3_sumStandarddemo, machineEve_q3_sumActualdemo, machineEve_q3_sumActualpairs,
							machineEve_q3_avgbuttomweight, machineEve_q3_avgbuttomweight2, 
							machineEve_q3_hole);
					list_machine.add(list_machine_3);
					//生產與請款狀況--inv
					list_inv_3=this.findResult_inv(3,invEve_q3_sumActualpairs, invEve_q3_invcount, invEve_q3_paypair);							
					list_inv.add(list_inv_3);
					//出貨和退貨--mix1
					list_mix1_3=this.findResult_mix1(3, mix1Eve_q3_sumoutnum, mix1Eve_q3_sumbacknum);						
					list_mix1.add(list_mix1_3);
					//人員效能--person
					list_person_3=this.findResult_person(3, 
							personEve_q3_personzg, personEve_q3_personjg, personEve_q3_timezg, personEve_q3_timejg, 
							personEve_q3_addtimezg, personEve_q3_addtimejg, personEve_q3_leavenumzg, personEve_q3_leavenumjg,
							personEve_q3_wagezgUsd, personEve_q3_wagejgUsd, personEve_q3_addmoneyzg, personEve_q3_addmoneyjg,
							personEve_q3_sumStandarddemo, personEve_q3_sumActualdemo, personEve_q3_sumActualpairs);	
					list_person.add(list_person_3);
					//關務損耗--gw
					list_gw_3=this.findResult_gw(3,
							gwEve_q3_sumActualpairs, gwEve_q3_sideweit, 
							gwEve_q3_badweit, gwEve_q3_otherbadweight, gwEve_q3_actlost,
							gwEve_q3_avgbuttomweight2,gwEve_q3_otherweight);								
					list_gw.add(list_gw_3);
					//邊料不良重量分析-side
					list_side_3=this.findResult_side(3, 
							sideEve_q3_sumActualpairs, sideEve_q3_sideweit, sideEve_q3_badcount, 
							sideEve_q3_badweit, sideEve_q3_otherbadweight, sideEve_q3_avgbuttomweight, 
							sideEve_q3_avgbuttomweight2, sideEve_q3_avgprice,sideEve_q3_otherweight);
					list_side.add(list_side_3);
					//庫存--store
					list_store_3=this.findResult_store(3, 
							storeEve_q3_sumhostpairs, storeEve_q3_sumsamplepairs, storeEve_q3_invcount,
							storeEve_q3_hostinvcount, storeEve_q3_badcount, storeEve_q3_lagStorenum, 
							storeEve_q3_lagNoliststorenum, storeEve_q3_lagMakestorenum, storeEve_q3_lagoutOutnum, 
							storeEve_q3_lagInnum, storeEve_q3_lagInstorenum, storeEve_q3_storenum, storeEve_q3_noliststorenum,
							storeEve_q3_makestorenum, storeEve_q3_outnum, storeEve_q3_innum, storeEve_q3_minusnum);						
					list_store.add(list_store_3);
					//水電油--wlo
					list_wlo_3=this.findResult_wlo(3, 
							wloEve_q3_sumactualdemo, wloEve_q3_waterton, wloEve_q3_waterusd, 
							wloEve_q3_electricdu, wloEve_q3_electricusd, wloEve_q3_gaston,
							wloEve_q3_gasusd, wloEve_q3_oilton, wloEve_q3_oilusd);								
					list_wlo.add(list_wlo_3);
					//回頭料--back
					list_back_3=this.findResult_back(3, backEve_q3_thickusd, backEve_q3_backfeed, backEve_q3_oilback);						
					list_back.add(list_back_3);
					//色料回收粉
					list_webcost_3=this.findResult_webcost(3, webcostEve_q3_sumActualdemo, webcostEve_q3_gluestoremoney, webcostEve_q3_drugsused, 
							webcostEve_q3_leavemoney, webcostEve_q3_whitenum, webcostEve_q3_blacknum, webcostEve_q3_gluenum, 
							webcostEve_q3_greynum, webcostEve_q3_othernum);
					list_webcost.add(list_webcost_3);
				}
				//第四季度,下半年,全年
				if(z_1==12){
					//孔位數回轉數--machine
					machineEve_half2_sumWorkday=machineEve_q3_sumWorkday+machineEve_q4_sumWorkday;
					machineEve_half2_sumEverydemo=machineEve_q3_sumEverydemo+machineEve_q4_sumEverydemo;
					machineEve_half2_sumStandarddemo=machineEve_q3_sumStandarddemo+machineEve_q4_sumStandarddemo;
					machineEve_half2_sumActualdemo=machineEve_q3_sumActualdemo+machineEve_q4_sumActualdemo;
					machineEve_half2_sumActualpairs=machineEve_q3_sumActualpairs+machineEve_q4_sumActualpairs;
					machineEve_half2_avgbuttomweight=machineEve_q3_avgbuttomweight+machineEve_q4_avgbuttomweight;
					machineEve_half2_avgbuttomweight2=machineEve_q3_avgbuttomweight2+machineEve_q4_avgbuttomweight2;
					machineEve_half2_hole=machineEve_q3_hole+machineEve_q4_hole;
					
					machineEve_year_sumWorkday=machineEve_half1_sumWorkday+machineEve_half2_sumWorkday;
					machineEve_year_sumEverydemo=machineEve_half1_sumEverydemo+machineEve_half2_sumEverydemo;
					machineEve_year_sumStandarddemo=machineEve_half1_sumStandarddemo+machineEve_half2_sumStandarddemo;
					machineEve_year_sumActualdemo=machineEve_half1_sumActualdemo+machineEve_half2_sumActualdemo;
					machineEve_year_sumActualpairs=machineEve_half1_sumActualpairs+machineEve_half2_sumActualpairs;
					machineEve_year_avgbuttomweight=machineEve_half1_avgbuttomweight+machineEve_half2_avgbuttomweight;
					machineEve_year_avgbuttomweight2=machineEve_half1_avgbuttomweight2+machineEve_half2_avgbuttomweight2;
					machineEve_year_hole=machineEve_half1_hole+machineEve_half2_hole;
					list_machine_3=this.findResult_machine(3,
							machineEve_q4_sumWorkday, machineEve_q4_sumEverydemo, machineEve_q4_sumStandarddemo, machineEve_q4_sumActualdemo, machineEve_q4_sumActualpairs,
							machineEve_q4_avgbuttomweight, machineEve_q4_avgbuttomweight2, 
							machineEve_q4_hole);
					list_machine.add(list_machine_3);
					list_machine_4=this.findResult_machine(6,
							machineEve_half2_sumWorkday, machineEve_half2_sumEverydemo, machineEve_half2_sumStandarddemo, machineEve_half2_sumActualdemo, machineEve_half2_sumActualpairs,
							machineEve_half2_avgbuttomweight, machineEve_half2_avgbuttomweight2, 
							machineEve_half2_hole);					
					list_machine.add(list_machine_4);
					list_machine_5=this.findResult_machine(12,
							machineEve_year_sumWorkday, machineEve_year_sumEverydemo, machineEve_year_sumStandarddemo, machineEve_year_sumActualdemo, machineEve_year_sumActualpairs,
							machineEve_year_avgbuttomweight, machineEve_year_avgbuttomweight2, 
							machineEve_year_hole);					
					list_machine.add(list_machine_5);
					//list_machine.add(null);//合計(不要)
					//生產與請款狀況--inv
					invEve_half2_sumActualpairs=invEve_q3_sumActualpairs+invEve_q4_sumActualpairs;
					invEve_half2_invcount=invEve_q3_invcount+invEve_q4_invcount;
					invEve_half2_paypair=invEve_q3_paypair+invEve_q4_invcount;
					
					invEve_year_sumActualpairs=invEve_half1_sumActualpairs+invEve_half2_sumActualpairs;
					invEve_year_invcount=invEve_half1_invcount+invEve_half2_invcount;
					invEve_year_paypair=invEve_half1_paypair+invEve_half2_invcount;
					list_inv_3=this.findResult_inv(3,invEve_q4_sumActualpairs, invEve_q4_invcount, invEve_q4_paypair);														
					list_inv.add(list_inv_3);
					
					list_inv_4=this.findResult_inv(6,invEve_half2_sumActualpairs, invEve_half2_invcount, invEve_half2_paypair);							
					list_inv.add(list_inv_4);
					
					list_inv_5=this.findResult_inv(12,invEve_year_sumActualpairs, invEve_year_invcount, invEve_year_paypair);							
					list_inv.add(list_inv_5);
					//list_inv.add(null);//合計(不要)
                    //出貨和退貨--mix1
					mix1Eve_half2_sumbacknum=mix1Eve_q3_sumbacknum+mix1Eve_q4_sumbacknum;
					mix1Eve_half2_sumoutnum=mix1Eve_q3_sumoutnum+mix1Eve_q4_sumoutnum;
					
					mix1Eve_year_sumbacknum=mix1Eve_half1_sumbacknum+mix1Eve_half2_sumbacknum;
					mix1Eve_year_sumoutnum=mix1Eve_half1_sumoutnum+mix1Eve_half2_sumoutnum;
					list_mix1_3=this.findResult_mix1(3, mix1Eve_q4_sumoutnum, mix1Eve_q4_sumbacknum);													
					list_mix1.add(list_mix1_3);
					
					list_mix1_4=this.findResult_mix1(6, mix1Eve_half2_sumoutnum, mix1Eve_half2_sumbacknum);						
					list_mix1.add(list_mix1_4);	
					
					list_mix1_5=this.findResult_mix1(12, mix1Eve_year_sumoutnum, mix1Eve_year_sumbacknum);						
					list_mix1.add(list_mix1_5);	
					//list_mix1.add(null);//合計(不要)
					//人員效能--person
					personEve_half2_addmoneyjg=personEve_q3_addmoneyjg+personEve_q4_addmoneyjg;
					personEve_half2_addmoneyzg=personEve_q3_addmoneyzg+personEve_q4_addmoneyzg;
					personEve_half2_addtimejg=personEve_q3_addtimejg+personEve_q4_addtimejg;
					personEve_half2_addtimezg=personEve_q3_addtimezg+personEve_q4_addtimezg;
					personEve_half2_leavenumjg=personEve_q3_leavenumjg+personEve_q4_leavenumjg;
					personEve_half2_leavenumzg=personEve_q3_leavenumzg+personEve_q4_leavenumzg;
					personEve_half2_personjg=personEve_q3_personjg+personEve_q4_personjg;
					personEve_half2_personzg=personEve_q3_personzg+personEve_q4_personzg;
					personEve_half2_sumActualdemo=personEve_q3_sumActualdemo+personEve_q4_sumActualdemo;
					personEve_half2_sumActualpairs=personEve_q3_sumActualpairs+personEve_q4_sumActualpairs;
					personEve_half2_sumStandarddemo=personEve_q3_sumStandarddemo+personEve_q4_sumStandarddemo;
					personEve_half2_timejg=personEve_q3_timejg+personEve_q4_timejg;
					personEve_half2_timezg=personEve_q3_timezg+personEve_q4_timezg;
					personEve_half2_wagejgUsd=personEve_q3_wagejgUsd+personEve_q4_wagejgUsd;
					personEve_half2_wagezgUsd=personEve_q3_wagezgUsd+personEve_q4_wagezgUsd;
					
					personEve_year_addmoneyjg=personEve_half1_addmoneyjg+personEve_half2_addmoneyjg;
					personEve_year_addmoneyzg=personEve_half1_addmoneyzg+personEve_half2_addmoneyzg;
					personEve_year_addtimejg=personEve_half1_addtimejg+personEve_half2_addtimejg;
					personEve_year_addtimezg=personEve_half1_addtimezg+personEve_half2_addtimezg;
					personEve_year_leavenumjg=personEve_half1_leavenumjg+personEve_half2_leavenumjg;
					personEve_year_leavenumzg=personEve_half1_leavenumzg+personEve_half2_leavenumzg;
					personEve_year_personjg=personEve_half1_personjg+personEve_half2_personjg;
					personEve_year_personzg=personEve_half1_personzg+personEve_half2_personzg;
					personEve_year_sumActualdemo=personEve_half1_sumActualdemo+personEve_half2_sumActualdemo;
					personEve_year_sumActualpairs=personEve_half1_sumActualpairs+personEve_half2_sumActualpairs;
					personEve_year_sumStandarddemo=personEve_half1_sumStandarddemo+personEve_half2_sumStandarddemo;
					personEve_year_timejg=personEve_half1_timejg+personEve_half2_timejg;
					personEve_year_timezg=personEve_half1_timezg+personEve_half2_timezg;
					personEve_year_wagejgUsd=personEve_half1_wagejgUsd+personEve_half2_wagejgUsd;
					personEve_year_wagezgUsd=personEve_half1_wagezgUsd+personEve_half2_wagezgUsd;
					list_person_3=this.findResult_person(3, 
							personEve_q4_personzg, personEve_q4_personjg, personEve_q4_timezg, personEve_q4_timejg, 
							personEve_q4_addtimezg, personEve_q4_addtimejg, personEve_q4_leavenumzg, personEve_q4_leavenumjg,
							personEve_q4_wagezgUsd, personEve_q4_wagejgUsd, personEve_q4_addmoneyzg, personEve_q4_addmoneyjg,
							personEve_q4_sumStandarddemo, personEve_q4_sumActualdemo, personEve_q4_sumActualpairs);		
					list_person.add(list_person_3);
					
					list_person_4=this.findResult_person(6, 
							personEve_half2_personzg, personEve_half2_personjg, personEve_half2_timezg, personEve_half2_timejg, 
							personEve_half2_addtimezg, personEve_half2_addtimejg, personEve_half2_leavenumzg, personEve_half2_leavenumjg,
							personEve_half2_wagezgUsd, personEve_half2_wagejgUsd, personEve_half2_addmoneyzg, personEve_half2_addmoneyjg,
							personEve_half2_sumStandarddemo, personEve_half2_sumActualdemo, personEve_half2_sumActualpairs);	
					list_person.add(list_person_4);
					
					list_person_5=this.findResult_person(12, 
							personEve_year_personzg, personEve_year_personjg, personEve_year_timezg, personEve_year_timejg, 
							personEve_year_addtimezg, personEve_year_addtimejg, personEve_year_leavenumzg, personEve_year_leavenumjg,
							personEve_year_wagezgUsd, personEve_year_wagejgUsd, personEve_year_addmoneyzg, personEve_year_addmoneyjg,
							personEve_year_sumStandarddemo, personEve_year_sumActualdemo, personEve_year_sumActualpairs);	
					list_person.add(list_person_5);
					//list_person.add(null);//統計(不要)
					//關務損耗--gw
					gwEve_half2_actlost=gwEve_q3_actlost+gwEve_q4_actlost;
					gwEve_half2_avgbuttomweight2=gwEve_q3_avgbuttomweight2+gwEve_q4_avgbuttomweight2;
					gwEve_half2_badweit=gwEve_q3_badweit+gwEve_q4_badweit;
					gwEve_half2_otherbadweight=gwEve_q3_otherbadweight+gwEve_q4_otherbadweight;
					gwEve_half2_sideweit=gwEve_half2_sideweit+gwEve_q4_sideweit;
					gwEve_half2_sumActualpairs=gwEve_q3_sumActualpairs+gwEve_q4_sumActualpairs;
					gwEve_half2_otherweight=gwEve_q3_otherweight+gwEve_q4_otherweight;
					
					gwEve_year_actlost=gwEve_half1_actlost+gwEve_half2_actlost;
					gwEve_year_avgbuttomweight2=gwEve_half1_avgbuttomweight2+gwEve_half2_avgbuttomweight2;
					gwEve_year_badweit=gwEve_half1_badweit+gwEve_half2_badweit;
					gwEve_year_otherbadweight=gwEve_half1_otherbadweight+gwEve_half2_otherbadweight;
					gwEve_year_sideweit=gwEve_year_sideweit+gwEve_half2_sideweit;
					gwEve_year_sumActualpairs=gwEve_half1_sumActualpairs+gwEve_half2_sumActualpairs;
					gwEve_year_otherweight=gwEve_half1_otherweight+gwEve_half2_otherweight;
					list_gw_3=this.findResult_gw(3,
							gwEve_q4_sumActualpairs, gwEve_q4_sideweit, 
							gwEve_q4_badweit, gwEve_q4_otherbadweight, gwEve_q4_actlost,
							gwEve_q4_avgbuttomweight2,gwEve_q4_otherweight);								
					list_gw.add(list_gw_3);
					
					list_gw_4=this.findResult_gw(6,
							gwEve_half2_sumActualpairs, gwEve_half2_sideweit, 
							gwEve_half2_badweit, gwEve_half2_otherbadweight, gwEve_half2_actlost,
							gwEve_half2_avgbuttomweight2,gwEve_half2_otherweight);							
					list_gw.add(list_gw_4);
					
					list_gw_5=this.findResult_gw(12,
							gwEve_year_sumActualpairs, gwEve_year_sideweit, 
							gwEve_year_badweit, gwEve_year_otherbadweight, gwEve_year_actlost,
							gwEve_year_avgbuttomweight2,gwEve_year_otherweight);							
					list_gw.add(list_gw_5);
					//list_gw.add(null);//統計(不要)
					//邊料不良重量分析-side
					sideEve_half2_avgbuttomweight=sideEve_q3_avgbuttomweight+sideEve_q4_avgbuttomweight;
					sideEve_half2_avgbuttomweight2=sideEve_q3_avgbuttomweight2+sideEve_q4_avgbuttomweight2;
					sideEve_half2_avgprice=sideEve_q3_avgprice+sideEve_q4_avgprice;
					sideEve_half2_badcount=sideEve_q3_badcount+sideEve_q4_badcount;
					sideEve_half2_badweit=sideEve_q3_badweit+sideEve_q4_badweit;
					sideEve_half2_otherbadweight=sideEve_q3_otherbadweight+sideEve_q4_otherbadweight;
					sideEve_half2_sideweit=sideEve_q3_sideweit+sideEve_q4_sideweit;
					sideEve_half2_sumActualpairs=sideEve_q3_sumActualpairs+sideEve_q4_sumActualpairs;
					sideEve_half2_otherweight=sideEve_q3_otherweight+sideEve_q4_otherweight;
					
					sideEve_year_avgbuttomweight=sideEve_half1_avgbuttomweight+sideEve_half2_avgbuttomweight;
					sideEve_year_avgbuttomweight2=sideEve_half1_avgbuttomweight2+sideEve_half2_avgbuttomweight2;
					sideEve_year_avgprice=sideEve_half1_avgprice+sideEve_half2_avgprice;
					sideEve_year_badcount=sideEve_half1_badcount+sideEve_half2_badcount;
					sideEve_year_badweit=sideEve_half1_badweit+sideEve_half2_badweit;
					sideEve_year_otherbadweight=sideEve_half1_otherbadweight+sideEve_half2_otherbadweight;
					sideEve_year_sideweit=sideEve_half1_sideweit+sideEve_half2_sideweit;
					sideEve_year_sumActualpairs=sideEve_half1_sumActualpairs+sideEve_half2_sumActualpairs;
					sideEve_year_otherweight=sideEve_half1_otherweight+sideEve_half2_otherweight;
					list_side_3=this.findResult_side(3, 
							sideEve_q4_sumActualpairs, sideEve_q4_sideweit, sideEve_q4_badcount, 
							sideEve_q4_badweit, sideEve_q4_otherbadweight, sideEve_q4_avgbuttomweight, 
							sideEve_q4_avgbuttomweight2, sideEve_q4_avgprice,sideEve_q4_otherweight);
					list_side.add(list_side_3);
					
					list_side_4=this.findResult_side(6, 
							sideEve_half2_sumActualpairs, sideEve_half2_sideweit, sideEve_half2_badcount, 
							sideEve_half2_badweit, sideEve_half2_otherbadweight, sideEve_half2_avgbuttomweight, 
							sideEve_half2_avgbuttomweight2, sideEve_half2_avgprice,sideEve_half2_otherweight);
					list_side.add(list_side_4);
					
					list_side_5=this.findResult_side(12, 
							sideEve_year_sumActualpairs, sideEve_year_sideweit, sideEve_year_badcount, 
							sideEve_year_badweit, sideEve_year_otherbadweight, sideEve_year_avgbuttomweight, 
							sideEve_year_avgbuttomweight2, sideEve_year_avgprice,sideEve_year_otherweight);
					list_side.add(list_side_5);
					//list_side.add(null);//統計(不要)
					//庫存--store
					storeEve_half2_badcount=storeEve_q3_badcount+storeEve_q4_badcount;
					storeEve_half2_hostinvcount=storeEve_q3_hostinvcount+storeEve_q4_hostinvcount;
					storeEve_half2_innum=storeEve_q3_innum+storeEve_q4_innum;
					storeEve_half2_invcount=storeEve_q3_invcount+storeEve_q4_invcount;
					storeEve_half2_lagInnum=storeEve_q3_lagInnum+storeEve_q4_lagInnum;
					storeEve_half2_lagInstorenum=storeEve_q3_lagInstorenum+storeEve_q4_lagInstorenum;
					storeEve_half2_lagMakestorenum=storeEve_q3_lagMakestorenum+storeEve_q4_lagMakestorenum;
					storeEve_half2_lagNoliststorenum=storeEve_q3_lagNoliststorenum+storeEve_q4_lagNoliststorenum;
					storeEve_half2_lagoutOutnum=storeEve_q3_lagoutOutnum+storeEve_q4_lagoutOutnum;
					storeEve_half2_lagStorenum=storeEve_q3_lagStorenum+storeEve_q4_lagStorenum;
					storeEve_half2_makestorenum=storeEve_q3_makestorenum+storeEve_q4_makestorenum;
					storeEve_half2_minusnum=storeEve_q3_minusnum+storeEve_q4_minusnum;
					storeEve_half2_noliststorenum=storeEve_q3_noliststorenum+storeEve_q4_noliststorenum;
					storeEve_half2_outnum=storeEve_q3_outnum+storeEve_q4_outnum;
					storeEve_half2_storenum=storeEve_q3_storenum+storeEve_q4_storenum;
					storeEve_half2_sumhostpairs=storeEve_q3_sumhostpairs+storeEve_q4_sumhostpairs;
					storeEve_half2_sumsamplepairs=storeEve_q3_sumsamplepairs+storeEve_q4_sumsamplepairs;
					
					storeEve_year_badcount=storeEve_half1_badcount+storeEve_half2_badcount;
					storeEve_year_hostinvcount=storeEve_half1_hostinvcount+storeEve_half2_hostinvcount;
					storeEve_year_innum=storeEve_half1_innum+storeEve_half2_innum;
					storeEve_year_invcount=storeEve_half1_invcount+storeEve_half2_invcount;
					storeEve_year_lagInnum=storeEve_half1_lagInnum+storeEve_half2_lagInnum;
					storeEve_year_lagInstorenum=storeEve_half1_lagInstorenum+storeEve_half2_lagInstorenum;
					storeEve_year_lagMakestorenum=storeEve_half1_lagMakestorenum+storeEve_half2_lagMakestorenum;
					storeEve_year_lagNoliststorenum=storeEve_half1_lagNoliststorenum+storeEve_half2_lagNoliststorenum;
					storeEve_year_lagoutOutnum=storeEve_half1_lagoutOutnum+storeEve_half2_lagoutOutnum;
					storeEve_year_lagStorenum=storeEve_half1_lagStorenum+storeEve_half2_lagStorenum;
					storeEve_year_makestorenum=storeEve_half1_makestorenum+storeEve_half2_makestorenum;
					storeEve_year_minusnum=storeEve_half1_minusnum+storeEve_half2_minusnum;
					storeEve_year_noliststorenum=storeEve_half1_noliststorenum+storeEve_half2_noliststorenum;
					storeEve_year_outnum=storeEve_half1_outnum+storeEve_half2_outnum;
					storeEve_year_storenum=storeEve_half1_storenum+storeEve_half2_storenum;
					storeEve_year_sumhostpairs=storeEve_half1_sumhostpairs+storeEve_half2_sumhostpairs;
					storeEve_year_sumsamplepairs=storeEve_half1_sumsamplepairs+storeEve_half2_sumsamplepairs;
					list_store_3=this.findResult_store(3, 
							storeEve_q4_sumhostpairs, storeEve_q4_sumsamplepairs, storeEve_q4_invcount,
							storeEve_q4_hostinvcount, storeEve_q4_badcount, storeEve_q4_lagStorenum, 
							storeEve_q4_lagNoliststorenum, storeEve_q4_lagMakestorenum, storeEve_q4_lagoutOutnum, 
							storeEve_q4_lagInnum, storeEve_q4_lagInstorenum, storeEve_q4_storenum, storeEve_q4_noliststorenum,
							storeEve_q4_makestorenum, storeEve_q4_outnum, storeEve_q4_innum, storeEve_q4_minusnum);								
					list_store.add(list_store_3);
					
					list_store_4=this.findResult_store(6, 
							storeEve_half2_sumhostpairs, storeEve_half2_sumsamplepairs, storeEve_half2_invcount,
							storeEve_half2_hostinvcount, storeEve_half2_badcount, storeEve_half2_lagStorenum, 
							storeEve_half2_lagNoliststorenum, storeEve_half2_lagMakestorenum, storeEve_half2_lagoutOutnum, 
							storeEve_half2_lagInnum, storeEve_half2_lagInstorenum, storeEve_half2_storenum, storeEve_half2_noliststorenum,
							storeEve_half2_makestorenum, storeEve_half2_outnum, storeEve_half2_innum, storeEve_half2_minusnum);							
					list_store.add(list_store_4);
					
					list_store_5=this.findResult_store(12, 
							storeEve_year_sumhostpairs, storeEve_year_sumsamplepairs, storeEve_year_invcount,
							storeEve_year_hostinvcount, storeEve_year_badcount, storeEve_year_lagStorenum, 
							storeEve_year_lagNoliststorenum, storeEve_year_lagMakestorenum, storeEve_year_lagoutOutnum, 
							storeEve_year_lagInnum, storeEve_year_lagInstorenum, storeEve_year_storenum, storeEve_year_noliststorenum,
							storeEve_year_makestorenum, storeEve_year_outnum, storeEve_year_innum, storeEve_year_minusnum);							
					list_store.add(list_store_5);
					//list_store.add(null);//統計(不要)
					//水電油--wlo
					wloEve_half2_electricdu=wloEve_q3_electricdu+wloEve_q4_electricdu;
					wloEve_half2_electricusd=wloEve_q3_electricusd+wloEve_q4_electricusd;
					wloEve_half2_gaston=wloEve_q3_gaston+wloEve_q4_gaston;
					wloEve_half2_gasusd=wloEve_q3_gasusd+wloEve_q4_gasusd;
					wloEve_half2_oilton=wloEve_q3_oilton+wloEve_q4_oilton;
					wloEve_half2_oilusd=wloEve_q3_oilusd+wloEve_q4_oilusd;
					wloEve_half2_sumactualdemo=wloEve_q3_sumactualdemo+wloEve_q4_sumactualdemo;
					wloEve_half2_waterton=wloEve_q3_waterton+wloEve_q4_waterton;
					wloEve_half2_waterusd=wloEve_q3_waterusd+wloEve_q4_waterusd;
					
					wloEve_year_electricdu=wloEve_half1_electricdu+wloEve_half2_electricdu;
					wloEve_year_electricusd=wloEve_half1_electricusd+wloEve_half2_electricusd;
					wloEve_year_gaston=wloEve_half1_gaston+wloEve_half2_gaston;
					wloEve_year_gasusd=wloEve_half1_gasusd+wloEve_half2_gasusd;
					wloEve_year_oilton=wloEve_half1_oilton+wloEve_half2_oilton;
					wloEve_year_oilusd=wloEve_half1_oilusd+wloEve_half2_oilusd;
					wloEve_year_sumactualdemo=wloEve_half1_sumactualdemo+wloEve_half2_sumactualdemo;
					wloEve_year_waterton=wloEve_half1_waterton+wloEve_half2_waterton;
					wloEve_year_waterusd=wloEve_half1_waterusd+wloEve_half2_waterusd;
					list_wlo_3=this.findResult_wlo(3, 
							wloEve_q4_sumactualdemo, wloEve_q4_waterton, wloEve_q4_waterusd, 
							wloEve_q4_electricdu, wloEve_q4_electricusd, wloEve_q4_gaston,
							wloEve_q4_gasusd, wloEve_q4_oilton, wloEve_q4_oilusd);							
					list_wlo.add(list_wlo_3);
					
					list_wlo_4=this.findResult_wlo(6, 
							wloEve_half2_sumactualdemo, wloEve_half2_waterton, wloEve_half2_waterusd, 
							wloEve_half2_electricdu, wloEve_half2_electricusd, wloEve_half2_gaston,
							wloEve_half2_gasusd, wloEve_half2_oilton, wloEve_half2_oilusd);							
					list_wlo.add(list_wlo_4);
					
					list_wlo_5=this.findResult_wlo(12, 
							wloEve_year_sumactualdemo, wloEve_year_waterton, wloEve_year_waterusd, 
							wloEve_year_electricdu, wloEve_year_electricusd, wloEve_year_gaston,
							wloEve_year_gasusd, wloEve_year_oilton, wloEve_year_oilusd);							
					list_wlo.add(list_wlo_5);
					//list_wlo.add(null);//統計(不要)
					//回頭料--back
					backEve_half2_backfeed=backEve_q3_backfeed+backEve_q4_backfeed;
					backEve_half2_oilback=backEve_q3_oilback+backEve_q4_oilback;
					backEve_half2_thickusd=backEve_q3_thickusd+backEve_q4_thickusd;
					
					backEve_year_backfeed=backEve_half1_backfeed+backEve_half2_backfeed;
					backEve_year_oilback=backEve_half1_oilback+backEve_half2_oilback;
					backEve_year_thickusd=backEve_half1_thickusd+backEve_half2_thickusd;
					list_back_3=this.findResult_back(3, backEve_q4_thickusd, backEve_q4_backfeed, backEve_q4_oilback);						
					list_back.add(list_back_3);
					
					list_back_4=this.findResult_back(6, backEve_half2_thickusd, backEve_half2_backfeed, backEve_half2_oilback);							
					list_back.add(list_back_4);
					
					list_back_5=this.findResult_back(12, backEve_year_thickusd, backEve_year_backfeed, backEve_year_oilback);							
					list_back.add(list_back_5);
					//色料回收粉
					webcostEve_half2_sumActualdemo=webcostEve_q3_sumActualdemo+webcostEve_q4_sumActualdemo;
					webcostEve_half2_gluestoremoney=webcostEve_q3_gluestoremoney+webcostEve_q3_gluestoremoney;
					webcostEve_half2_drugsused=webcostEve_q3_drugsused+webcostEve_q4_drugsused;
					webcostEve_half2_leavemoney=webcostEve_q3_leavemoney+webcostEve_q4_leavemoney;
					webcostEve_half2_whitenum=webcostEve_q3_whitenum+webcostEve_q4_whitenum;
					webcostEve_half2_blacknum=webcostEve_q3_blacknum+webcostEve_q4_blacknum;
					webcostEve_half2_gluenum=webcostEve_q3_gluenum+webcostEve_q4_gluenum;
					webcostEve_half2_greynum=webcostEve_q3_greynum+webcostEve_q4_greynum;
					webcostEve_half2_othernum=webcostEve_q3_othernum+webcostEve_q4_othernum;
					
					webcostEve_year_sumActualdemo=webcostEve_half1_sumActualdemo+webcostEve_half2_sumActualdemo;
					webcostEve_year_gluestoremoney=webcostEve_half1_gluestoremoney+webcostEve_half1_gluestoremoney;
					webcostEve_year_drugsused=webcostEve_half1_drugsused+webcostEve_half2_drugsused;
					webcostEve_year_leavemoney=webcostEve_half1_leavemoney+webcostEve_half2_leavemoney;
					webcostEve_year_whitenum=webcostEve_half1_whitenum+webcostEve_half2_whitenum;
					webcostEve_year_blacknum=webcostEve_half1_blacknum+webcostEve_half2_blacknum;
					webcostEve_year_gluenum=webcostEve_half1_gluenum+webcostEve_half2_gluenum;
					webcostEve_year_greynum=webcostEve_half1_greynum+webcostEve_half2_greynum;
					webcostEve_year_othernum=webcostEve_half1_othernum+webcostEve_half2_othernum;
					
					list_webcost_3=this.findResult_webcost(3, webcostEve_q4_sumActualdemo, webcostEve_q4_gluestoremoney, webcostEve_q4_drugsused, 
							webcostEve_q4_leavemoney, webcostEve_q4_whitenum, webcostEve_q4_blacknum, webcostEve_q4_gluenum, 
							webcostEve_q4_greynum, webcostEve_q4_othernum);
					list_webcost.add(list_webcost_3);
					list_webcost_4=this.findResult_webcost(6, webcostEve_half2_sumActualdemo, webcostEve_half2_gluestoremoney, webcostEve_half2_drugsused, webcostEve_half2_leavemoney,
							webcostEve_half2_whitenum, webcostEve_half2_blacknum, webcostEve_half2_gluenum, webcostEve_half2_greynum, webcostEve_half2_othernum);
					list_webcost.add(list_webcost_4);
					list_webcost_5=this.findResult_webcost(12, webcostEve_year_sumActualdemo, webcostEve_year_gluestoremoney, webcostEve_year_drugsused, webcostEve_year_leavemoney,
							webcostEve_year_whitenum, webcostEve_year_blacknum, webcostEve_year_gluenum, webcostEve_year_greynum, webcostEve_year_othernum);
					list_webcost.add(list_webcost_5);

					//list_back.add(null);//統計(不要)
				}
				
				
			}//end for2_2
			
			//一個廠別狀態循環完畢,則要添加一個(中間集合list2）
			list_all_webmachine.add(list_machine);
			list_all_inv.add(list_inv);
			list_all_mix1.add(list_mix1);
			list_all_person.add(list_person);
			list_all_gw.add(list_gw);
			list_all_side.add(list_side);
			list_all_store.add(list_store);
			list_all_wlo.add(list_wlo);
			list_all_back.add(list_back);
			list_all_webcost.add(list_webcost);
		}//end for1_1
		
		
		/**
		 * 填充內容
		 * (1)機台回轉數
		 */
		//填充標題
		HSSFRow row_title=sheet.createRow(0);
		HSSFCell cell_title=row_title.createCell(0);
		cell_title.setCellValue(title);
		cell_title.setCellStyle(cs_title);
		CellRangeAddress cra_title=new CellRangeAddress(0,(short)0,0,list_column.size()-1);
		sheet.addMergedRegion(cra_title);
		for(int a=1;a<list_column.size();a++){
			HSSFCell cell=row_title.createCell(a);
			cell.setCellStyle(cs_title);
		}
		//填充廠別狀態和表頭
		for(int c=0;c<list_fact.size();c++){//start for1
		WebFact fact=list_fact.get(c);
		String factCode=fact.getId().getFactArea();//廠別狀態
		HSSFRow row_factCode=sheet.createRow(2+114*c);
		HSSFCell cell_factCode=row_factCode.createCell(0);
		cell_factCode.setCellValue(factCode);
		cell_factCode.setCellStyle(cs_noborder);
		HSSFRow row_column=sheet.createRow(3+114*c);
		for(int b=0;b<list_column.size();b++){
			HSSFCell cell=row_column.createCell(b);
			cell.setCellValue(list_column.get(b));
			cell.setCellStyle(cs_column);
		}
		for(int d=0;d<list_column.size();d++){//start for2
			int length1=list_contentName_machine.size();
			int length2=list_contentName_inv.size();
			int length3=list_contentName_mix1.size();
			int length4=list_contentName_person.size();
			int length5=list_contentName_gw.size();
			int length6=list_contentName_side.size();
			int length7=list_contentName_store.size();
			int length8=list_contentName_wlo.size();
			int length9=list_contentName_back.size();
			int length10=list_contentName_webcost.size();
			switch(d){
			case 0:
				sheet.setColumnWidth(d, 3500);
				//機台回轉數
				HSSFRow row_a=sheet.createRow(4+114*c);
				HSSFCell cell_a=row_a.createCell(d);
				cell_a.setCellValue("機台回轉數");
				cell_a.setCellStyle(cs);
				CellRangeAddress cra_row_a=new CellRangeAddress((4+114*c),(short)(4+114*c+(length1-1)),d,(short)d);
				sheet.addMergedRegion(cra_row_a);
				for(int e=1;e<length1;e++){
					HSSFRow row_a_2=sheet.createRow(4+e+114*c);
					HSSFCell cell_a_2=row_a_2.createCell(d);
					cell_a_2.setCellStyle(cs);
				}
				//生產與請款狀況
				HSSFRow row_a2=sheet.createRow(4+114*c+length1);
				HSSFCell cell_a2=row_a2.createCell(d);
				cell_a2.setCellValue("生產與請款狀況");
				cell_a2.setCellStyle(cs);
				CellRangeAddress cra_row_a2=new CellRangeAddress(4+114*c+length1,(short)(4+114*c+length1+length2-1),d,(short)d);
				sheet.addMergedRegion(cra_row_a2);
				for(int e2=1;e2<length2;e2++){
					HSSFRow row_a2_2=sheet.createRow(4+114*c+length1+e2);
					HSSFCell cell_a2_2=row_a2_2.createCell(d);
					cell_a2_2.setCellStyle(cs);
				}
				//出貨與退貨
				HSSFRow row_a3=sheet.createRow(4+114*c+length1+length2);
				HSSFCell cell_a3=row_a3.createCell(d);
				cell_a3.setCellValue("出貨與退貨");
				cell_a3.setCellStyle(cs);
				CellRangeAddress cra_row_a3=new CellRangeAddress(4+114*c+length1+length2,
						(short)(4+114*c+length1+length2+length3-1),d,(short)d);
				sheet.addMergedRegion(cra_row_a3);
				for(int e2=1;e2<length3;e2++){
					HSSFRow row_a3_2=sheet.createRow(4+114*c+length1+length2+e2);
					HSSFCell cell_a3_2=row_a3_2.createCell(d);
					cell_a3_2.setCellStyle(cs);
				}
				//人員效能
				HSSFRow row_a4=sheet.createRow(4+114*c+length1+length2+length3);
				HSSFCell cell_a4=row_a4.createCell(d);
				cell_a4.setCellValue("人員效能");
				cell_a4.setCellStyle(cs);
				CellRangeAddress cra_row_a4=new CellRangeAddress(4+114*c+length1+length2+length3,
						(short)(4+114*c+length1+length2+length3+length4-1),d,(short)d);
				sheet.addMergedRegion(cra_row_a4);
				for(int e2=1;e2<length4;e2++){
					HSSFRow row_a3_2=sheet.createRow(4+114*c+length1+length2+length3+e2);
					HSSFCell cell_a3_2=row_a3_2.createCell(d);
					cell_a3_2.setCellStyle(cs);
				}
				//關務損耗
				HSSFRow row_a5=sheet.createRow(4+114*c+length1+length2+length3+length4);
				HSSFCell cell_a5=row_a5.createCell(d);
				cell_a5.setCellValue("關務損耗");
				cell_a5.setCellStyle(cs);
				CellRangeAddress cra_row_a5=new CellRangeAddress(4+114*c+length1+length2+length3+length4,
						(short)(4+114*c+length1+length2+length3+length4+length5-1),d,(short)d);
				sheet.addMergedRegion(cra_row_a5);
				for(int e2=1;e2<length5;e2++){
					HSSFRow row_a3_2=sheet.createRow(4+114*c+length1+length2+length3+length4+e2);
					HSSFCell cell_a3_2=row_a3_2.createCell(d);
					cell_a3_2.setCellStyle(cs);
				}
				//邊料不良重量分析
				HSSFRow row_a6=sheet.createRow(4+114*c+length1+length2+length3+length4+length5);
				HSSFCell cell_a6=row_a6.createCell(d);
				cell_a6.setCellValue("邊料不良重量分析");
				cell_a6.setCellStyle(cs);
				CellRangeAddress cra_row_a6=new CellRangeAddress(4+114*c+length1+length2+length3+length4+length5,
						(short)(4+114*c+length1+length2+length3+length4+length5+length6-1),d,(short)d);
				sheet.addMergedRegion(cra_row_a6);
				for(int e2=1;e2<length6;e2++){
					HSSFRow row_a3_2=sheet.createRow(4+114*c+length1+length2+length3+length4+length5+e2);
					HSSFCell cell_a3_2=row_a3_2.createCell(d);
					cell_a3_2.setCellStyle(cs);
				}
				//庫存
				HSSFRow row_a7=sheet.createRow(4+114*c+length1+length2+length3+length4+length5+length6);
				HSSFCell cell_a7=row_a7.createCell(d);
				cell_a7.setCellValue("庫存");
				cell_a7.setCellStyle(cs);
				CellRangeAddress cra_row_a7=new CellRangeAddress(4+114*c+length1+length2+length3+length4+length5+length6,
						(short)(4+114*c+length1+length2+length3+length4+length5+length6+length7-1),d,(short)d);
				sheet.addMergedRegion(cra_row_a7);
				for(int e2=1;e2<length7;e2++){
					HSSFRow row_a3_2=sheet.createRow(4+114*c+length1+length2+length3+length4+length5+length6+e2);
					HSSFCell cell_a3_2=row_a3_2.createCell(d);
					cell_a3_2.setCellStyle(cs);
				}
				//水電油
				HSSFRow row_a8=sheet.createRow(4+114*c+length1+length2+length3+length4+length5+length6+length7);
				HSSFCell cell_a8=row_a8.createCell(d);
				cell_a8.setCellValue("水電油");
				cell_a8.setCellStyle(cs);
				CellRangeAddress cra_row_a8=new CellRangeAddress(4+114*c+length1+length2+length3+length4+length5+length6+length7,
						(short)(4+114*c+length1+length2+length3+length4+length5+length6+length7+length8-1),d,(short)d);
				sheet.addMergedRegion(cra_row_a8);
				for(int e2=1;e2<length8;e2++){
					HSSFRow row_a3_2=sheet.createRow(4+114*c+length1+length2+length3+length4+length5+length6+length7+e2);
					HSSFCell cell_a3_2=row_a3_2.createCell(d);
					cell_a3_2.setCellStyle(cs);
				}
				//回頭料
				HSSFRow row_a9=sheet.createRow(4+114*c+length1+length2+length3+length4+length5+length6+length7+length8);
				HSSFCell cell_a9=row_a9.createCell(d);
				cell_a9.setCellValue("回頭料");
				cell_a9.setCellStyle(cs);
				CellRangeAddress cra_row_a9=new CellRangeAddress(4+114*c+length1+length2+length3+length4+length5+length6+length7+length8,
						(short)(4+114*c+length1+length2+length3+length4+length5+length6+length7+length8+length9-1),d,(short)d);
				sheet.addMergedRegion(cra_row_a9);
				for(int e2=1;e2<length9;e2++){
					HSSFRow row_a3_2=sheet.createRow(4+114*c+length1+length2+length3+length4+length5+length6+length7+length8+e2);
					HSSFCell cell_a3_2=row_a3_2.createCell(d);
					cell_a3_2.setCellStyle(cs);
				}
				//色料回收粉
				HSSFRow row_a10=sheet.createRow(4+114*c+length1+length2+length3+length4+length5+length6+length7+length8+length9);
				HSSFCell cell_a10=row_a10.createCell(d);
				cell_a10.setCellValue("色料藥品/離型劑/回收粉");
				cell_a10.setCellStyle(cs);
				CellRangeAddress cra_row_a10=new CellRangeAddress(4+114*c+length1+length2+length3+length4+length5+length6+length7+length8+length9,
						(short)(4+114*c+length1+length2+length3+length4+length5+length6+length7+length8+length9+length10-1),d,(short)d);
				sheet.addMergedRegion(cra_row_a10);
				for(int e2=1;e2<length10;e2++){
					HSSFRow row_a3_2=sheet.createRow(4+114*c+length1+length2+length3+length4+length5+length6+length7+length8+length9+e2);
					HSSFCell cell_a3_2=row_a3_2.createCell(d);
					cell_a3_2.setCellStyle(cs);
				}
				break;
			case 1:
				sheet.setColumnWidth(d, 4000);
				//機台回轉數
				for(int f=0;f<length1;f++){
					HSSFRow row_b=sheet.getRow(4+f+114*c);
					HSSFCell cell_b=row_b.createCell(d);
					cell_b.setCellValue(list_contentName_machine.get(f));
					cell_b.setCellStyle(cs);
				}
				//生產與請款狀況
				for(int f2=0;f2<length2;f2++){
					HSSFRow row_b=sheet.getRow(4+114*c+length1+f2);
					HSSFCell cell_b=row_b.createCell(d);
					cell_b.setCellValue(list_contentName_inv.get(f2));
					cell_b.setCellStyle(cs);
				}
				//出貨與退貨
				for(int f2=0;f2<length3;f2++){
					HSSFRow row_b=sheet.getRow(4+114*c+length1+length2+f2);
					HSSFCell cell_b=row_b.createCell(d);
					cell_b.setCellValue(list_contentName_mix1.get(f2));
					cell_b.setCellStyle(cs);
				}
				//人員效能
				for(int f2=0;f2<length4;f2++){
					HSSFRow row_b=sheet.getRow(4+114*c+length1+length2+length3+f2);
					HSSFCell cell_b=row_b.createCell(d);
					cell_b.setCellValue(list_contentName_person.get(f2));
					cell_b.setCellStyle(cs);
				}
				//關務損耗
				for(int f2=0;f2<length5;f2++){
					HSSFRow row_b=sheet.getRow(4+114*c+length1+length2+length3+length4+f2);
					HSSFCell cell_b=row_b.createCell(d);
					cell_b.setCellValue(list_contentName_gw.get(f2));
					cell_b.setCellStyle(cs);
				}
				//邊料不良重量分析
				for(int f2=0;f2<length6;f2++){
					HSSFRow row_b=sheet.getRow(4+114*c+length1+length2+length3+length4+length5+f2);
					HSSFCell cell_b=row_b.createCell(d);
					cell_b.setCellValue(list_contentName_side.get(f2));
					cell_b.setCellStyle(cs);
				}
				//庫存
				for(int f2=0;f2<length7;f2++){
					HSSFRow row_b=sheet.getRow(4+114*c+length1+length2+length3+length4+length5+length6+f2);
					HSSFCell cell_b=row_b.createCell(d);
					cell_b.setCellValue(list_contentName_store.get(f2));
					cell_b.setCellStyle(cs);
				}
				//水電油
				for(int f2=0;f2<length8;f2++){
					HSSFRow row_b=sheet.getRow(4+114*c+length1+length2+length3+length4+length5+length6+length7+f2);
					HSSFCell cell_b=row_b.createCell(d);
					cell_b.setCellValue(list_contentName_wlo.get(f2));
					cell_b.setCellStyle(cs);
				}
				//回頭料
				for(int f2=0;f2<length9;f2++){
					HSSFRow row_b=sheet.getRow(4+114*c+length1+length2+length3+length4+length5+length6+length7+length8+f2);
					HSSFCell cell_b=row_b.createCell(d);
					cell_b.setCellValue(list_contentName_back.get(f2));
					cell_b.setCellStyle(cs);
				}
				//色料回收粉
				for(int f2=0;f2<length10;f2++){
					HSSFRow row_b=sheet.getRow(4+114*c+length1+length2+length3+length4+length5+length6+length7+length8+length9+f2);
					HSSFCell cell_b=row_b.createCell(d);
					cell_b.setCellValue(list_contentName_webcost.get(f2));
					cell_b.setCellStyle(cs);
				}
				break;
			case 2:
				//機台回轉數
				for(int g=0;g<length1;g++){
					HSSFRow row_c=sheet.getRow(4+g+114*c);
					HSSFCell cell_c=row_c.createCell(d);
					cell_c.setCellValue(list_unit_machine.get(g));
					cell_c.setCellStyle(cs);
				}
				//生產與請款狀況
				for(int g2=0;g2<length2;g2++){
					HSSFRow row_c=sheet.getRow(4+114*c+length1+g2);
					HSSFCell cell_c=row_c.createCell(d);
					cell_c.setCellValue(list_unit_inv.get(g2));
					cell_c.setCellStyle(cs);
				}
				//出貨與退貨
				for(int g2=0;g2<length3;g2++){
					HSSFRow row_c=sheet.getRow(4+114*c+length1+length2+g2);
					HSSFCell cell_c=row_c.createCell(d);
					cell_c.setCellValue(list_unit_mix1.get(g2));
					cell_c.setCellStyle(cs);
				}
				//人員效能
				for(int g2=0;g2<length4;g2++){
					HSSFRow row_c=sheet.getRow(4+114*c+length1+length2+length3+g2);
					HSSFCell cell_c=row_c.createCell(d);
					cell_c.setCellValue(list_unit_person.get(g2));
					cell_c.setCellStyle(cs);
				}
				//關務損耗
				for(int g2=0;g2<length5;g2++){
					HSSFRow row_c=sheet.getRow(4+114*c+length1+length2+length3+length4+g2);
					HSSFCell cell_c=row_c.createCell(d);
					cell_c.setCellValue(list_unit_gw.get(g2));
					cell_c.setCellStyle(cs);
				}
				//邊料不良重量分析
				for(int g2=0;g2<length6;g2++){
					HSSFRow row_c=sheet.getRow(4+114*c+length1+length2+length3+length4+length5+g2);
					HSSFCell cell_c=row_c.createCell(d);
					cell_c.setCellValue(list_unit_side.get(g2));
					cell_c.setCellStyle(cs);
				}
				//庫存
				for(int g2=0;g2<length7;g2++){
					HSSFRow row_c=sheet.getRow(4+114*c+length1+length2+length3+length4+length5+length6+g2);
					HSSFCell cell_c=row_c.createCell(d);
					cell_c.setCellValue(list_unit_store.get(g2));
					cell_c.setCellStyle(cs);
				}
				//水電油
				for(int g2=0;g2<length8;g2++){
					HSSFRow row_c=sheet.getRow(4+114*c+length1+length2+length3+length4+length5+length6+length7+g2);
					HSSFCell cell_c=row_c.createCell(d);
					cell_c.setCellValue(list_unit_wlo.get(g2));
					cell_c.setCellStyle(cs);
				}
				//回頭料
				for(int g2=0;g2<length9;g2++){
					HSSFRow row_c=sheet.getRow(4+114*c+length1+length2+length3+length4+length5+length6+length7+length8+g2);
					HSSFCell cell_c=row_c.createCell(d);
					cell_c.setCellValue(list_unit_back.get(g2));
					cell_c.setCellStyle(cs);
				}
				//色料回收粉
				for(int g2=0;g2<length10;g2++){
					HSSFRow row_c=sheet.getRow(4+114*c+length1+length2+length3+length4+length5+length6+length7+length8+length9+g2);
					HSSFCell cell_c=row_c.createCell(d);
					cell_c.setCellValue(list_unit_webcost.get(g2));
					cell_c.setCellStyle(cs);
				}
				break;
			default:
				sheet.setColumnWidth(d, 2500);
				List<List<Double>>list2_machine=list_all_webmachine.get(c);//中間集合list2
				List<Double>list3_machine=list2_machine.get(d);//單元集合list3
				
				List<List<Double>>list2_inv=list_all_inv.get(c);//中間集合list2
				List<Double>list3_inv=list2_inv.get(d);//單元集合list3
				
				List<List<Double>>list2_mix1=list_all_mix1.get(c);//中間集合list2
				List<Double>list3_mix1=list2_mix1.get(d);//單元集合list3
				
				List<List<Double>>list2_person=list_all_person.get(c);//中間集合list2
				List<Double>list3_person=list2_person.get(d);//單元集合list3
				
				List<List<Double>>list2_gw=list_all_gw.get(c);//中間集合list2
				List<Double>list3_gw=list2_gw.get(d);//單元集合list3
				
				List<List<Double>>list2_side=list_all_side.get(c);//中間集合list2
				List<Double>list3_side=list2_side.get(d);//單元集合list3
				
				List<List<Double>>list2_store=list_all_store.get(c);//中間集合list2
				List<Double>list3_store=list2_store.get(d);//單元集合list3
				
				List<List<Double>>list2_wlo=list_all_wlo.get(c);//中間集合list2
				List<Double>list3_wlo=list2_wlo.get(d);//單元集合list3
				
				List<List<Double>>list2_back=list_all_back.get(c);//中間集合list2
				List<Double>list3_back=list2_back.get(d);//單元集合list3
				
				List<List<Double>>list2_webcost=list_all_webcost.get(c);//中間集合list2
				List<Double>list3_webcost=list2_webcost.get(d);//單元集合list3
				
				HSSFCellStyle cs_temp=wb.createCellStyle();//臨時樣式
				//孔位數回轉數--machine
				if(list3_machine!=null){                                                                                                       
					for(int h=0;h<list3_machine.size();h++){						
						if(h==0||h==2||h==3||h==7||h==8){
							cs_temp=cs_poi;
						}else if(h==1||h==5||h==6||h==9){
							cs_temp=cs_poi1;
						}else if(h==4){
							cs_temp=cs_percent;                                                                                                   
						}else{
							cs_temp=cs_percent2;
						}
						
						HSSFRow row=sheet.getRow(4+h+114*c);						
						HSSFCell cell=row.createCell(d);
						cell.setCellValue(list3_machine.get(h));
						cell.setCellStyle(cs_temp);						
					}
				}else{
					for(int h=0;h<list_contentName_machine.size();h++){
						HSSFRow row=sheet.getRow(4+h+114*c);						
						HSSFCell cell=row.createCell(d);
						cell.setCellValue("無數據");
						cell.setCellStyle(cs);
					}
				}
				//生產與請款狀況--inv
				if(list3_inv!=null){
					for(int h=0;h<list3_inv.size();h++){
						if(h==0||h==1){
							cs_temp=cs_poi1;
						}else{
							cs_temp=cs_poi2;
						}
						HSSFRow row=sheet.getRow(4+h+114*c+length1);
						HSSFCell cell=row.createCell(d);
						cell.setCellValue(list3_inv.get(h));
						cell.setCellStyle(cs_temp);
					}					
				}else{
					for(int h=0;h<list_contentName_inv.size();h++){
						HSSFRow row=sheet.getRow(4+h+114*c+length1);						
						HSSFCell cell=row.createCell(d);
						cell.setCellValue("無數據");
						cell.setCellStyle(cs);
					}
				}
				//出貨與退貨--mix1
				if(list3_mix1!=null){					
					for(int h=0;h<list3_mix1.size();h++){
						if(h==2){
							cs_temp=cs_percent2;
						}else{
							cs_temp=cs_poi1;
						}
						HSSFRow row=sheet.getRow(4+h+114*c+length1+length2);
						HSSFCell cell=row.createCell(d);
						cell.setCellValue(list3_mix1.get(h));
						cell.setCellStyle(cs_temp);
					}
					
				}else{
					for(int h=0;h<list_contentName_mix1.size();h++){
						HSSFRow row=sheet.getRow(4+h+114*c+length1+length2);						
						HSSFCell cell=row.createCell(d);
						cell.setCellValue("無數據");
						cell.setCellStyle(cs);
					}
				}
				//人員效能--person
				if(list3_person!=null){
					for(int h=0;h<list3_person.size();h++){
						if(h==2||h==10||h==11||h==22||h==23){
							cs_temp=cs_percent2;
						}else if(h==0||h==1){
							cs_temp=cs_poi1;
						}else if(h==3||h==4||h==5||h==7||h==8){
							cs_temp=cs_poi;
						}else{
							cs_temp=cs_poi2;
						}
						HSSFRow row=sheet.getRow(4+h+114*c+length1+length2+length3);
						HSSFCell cell=row.createCell(d);
						cell.setCellValue(list3_person.get(h));
						cell.setCellStyle(cs_temp);
					}
					
				}else{
					for(int h=0;h<list_contentName_person.size();h++){
						HSSFRow row=sheet.getRow(4+h+114*c+length1+length2+length3);						
						HSSFCell cell=row.createCell(d);
						cell.setCellValue("無數據");
						cell.setCellStyle(cs);
					}
				}
				//關務損耗--gw
				if(list3_gw!=null){
					for(int h=0;h<list3_gw.size();h++){
						if(h==3||h==5){
							cs_temp=cs_percent2;
						}else{
							cs_temp=cs_poi2;
						}
						HSSFRow row=sheet.getRow(4+h+114*c+length1+length2+length3+length4);
						HSSFCell cell=row.createCell(d);
						cell.setCellValue(list3_gw.get(h));
						cell.setCellStyle(cs_temp);
					}
					
				}else{
					for(int h=0;h<list_contentName_gw.size();h++){
						HSSFRow row=sheet.getRow(4+h+114*c+length1+length2+length3+length4);						
						HSSFCell cell=row.createCell(d);
						cell.setCellValue("無數據");
						cell.setCellStyle(cs);
					}
				}
				//邊料不良重量分析--side
				if(list3_side!=null){
					for(int h=0;h<list3_side.size();h++){
						if(h==3||h==7){
							cs_temp=cs_percent2;
						}else{
							cs_temp=cs_poi2;
						}
						HSSFRow row=sheet.getRow(4+h+114*c+length1+length2+length3+length4+length5);
						HSSFCell cell=row.createCell(d);
						cell.setCellValue(list3_side.get(h));
						cell.setCellStyle(cs_temp);
					}
					
				}else{
					for(int h=0;h<list_contentName_side.size();h++){
						HSSFRow row=sheet.getRow(4+h+114*c+length1+length2+length3+length4+length5);						
						HSSFCell cell=row.createCell(d);
						cell.setCellValue("無數據");
						cell.setCellStyle(cs);
					}
				}
				//庫存--store
				if(list3_store!=null){
					for(int h=0;h<list3_store.size();h++){
						HSSFRow row=sheet.getRow(4+h+114*c+length1+length2+length3+length4+length5+length6);
						HSSFCell cell=row.createCell(d);
						cell.setCellValue(list3_store.get(h));
						cell.setCellStyle(cs_poi1);
					}
					
				}else{
					for(int h=0;h<list_contentName_store.size();h++){
						HSSFRow row=sheet.getRow(4+h+114*c+length1+length2+length3+length4+length5+length6);						
						HSSFCell cell=row.createCell(d);
						cell.setCellValue("無數據");
						cell.setCellStyle(cs);
					}
				}
				//水電油--wlo
				if(list3_wlo!=null){
					for(int h=0;h<list3_wlo.size();h++){
						if(h==0){
							cs_temp=cs_poi1;
						}else if(h==1||h==5){
							cs_temp=cs_poi;
						}else if(h==2||h==4||h==6||h==8||h==10||h==12||h==14||h==16){
							cs_temp=cs_poi4;
						}else{
							cs_temp=cs_poi2;
						}
						HSSFRow row=sheet.getRow(4+h+114*c+length1+length2+length3+length4+length5+length6+length7);
						HSSFCell cell=row.createCell(d);
						cell.setCellValue(list3_wlo.get(h));
						cell.setCellStyle(cs_temp);
					}
					
				}else{
					for(int h=0;h<list_contentName_wlo.size();h++){
						HSSFRow row=sheet.getRow(4+h+114*c+length1+length2+length3+length4+length5+length6+length7);						
						HSSFCell cell=row.createCell(d);
						cell.setCellValue("無數據");
						cell.setCellStyle(cs);
					}
				}
				//回頭料--back
				if(list3_back!=null){
					for(int h=0;h<list3_back.size();h++){
						if(h==2||h==4||h==6){
							cs_temp=cs_percent2;
						}else{
							cs_temp=cs_poi2;
						}
						HSSFRow row=sheet.getRow(4+h+114*c+length1+length2+length3+length4+length5+length6+length7+length8);
						HSSFCell cell=row.createCell(d);
						cell.setCellValue(list3_back.get(h));
						cell.setCellStyle(cs_temp);
					}
					
				}else{
					for(int h=0;h<list_contentName_back.size();h++){
						HSSFRow row=sheet.getRow(4+h+114*c+length1+length2+length3+length4+length5+length6+length7+length8);						
						HSSFCell cell=row.createCell(d);
						cell.setCellValue("無數據");
						cell.setCellStyle(cs);
					}
				}
				//色料回收粉--webcost
				if(list3_webcost!=null){
					for(int h=0;h<list3_webcost.size();h++){
						if(h==1||h==3||h==5){
							cs_temp=cs_poi4;
						}else{
							cs_temp=cs_poi2;
						}
						HSSFRow row=sheet.getRow(4+h+114*c+length1+length2+length3+length4+length5+length6+length7+length8+length9);
						HSSFCell cell=row.createCell(d);
						cell.setCellValue(list3_webcost.get(h));
						cell.setCellStyle(cs_temp);
					}
					
				}else{
					for(int h=0;h<list_contentName_webcost.size();h++){
						HSSFRow row=sheet.getRow(4+h+114*c+length1+length2+length3+length4+length5+length6+length7+length8+length9);						
						HSSFCell cell=row.createCell(d);
						cell.setCellValue("無數據");
						cell.setCellStyle(cs);
					}
				}
				break;
			}
		}//end for2
	}//end for1
		
		ServletOutputStream os=response.getOutputStream();
		response.setContentType("application/vnd.ms-excel");
		title=title+".xls";
		int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");
		String fileName="";
		if(msie>0){
			fileName=java.net.URLEncoder.encode(title,"utf-8");
		}else{
			fileName=new String(title.getBytes("utf-8"),"iso-8859-1");
		}
		response.setHeader("Content-disposition","attachment;filename="+fileName);
		//OutputStream os=new FileOutputStream("d:/vmachine.xls");
		wb.write(os);
		os.close();
	}
	/**
	 * 分時間段查詢
	 * @throws ParseException
	 * @throws IOException
	 */
	public void print_month() throws ParseException, IOException{
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
				//無邊框樣式
				HSSFCellStyle cs_noborder=wb.createCellStyle();
				cs_noborder.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cs_noborder.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				//數字格式
				HSSFDataFormat format_hf=wb.createDataFormat();
				HSSFCellStyle cs_percent2=wb.createCellStyle();
				cs_percent2.setDataFormat(format_hf.getFormat("0.00%"));
				cs_percent2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cs_percent2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				cs_percent2.setBorderTop(HSSFCellStyle.BORDER_THIN);
				cs_percent2.setBorderRight(HSSFCellStyle.BORDER_THIN);
				cs_percent2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				cs_percent2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				
				HSSFCellStyle cs_percent1=wb.createCellStyle();
				cs_percent1.setDataFormat(format_hf.getFormat("0.0%"));
				cs_percent1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cs_percent1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				cs_percent1.setBorderTop(HSSFCellStyle.BORDER_THIN);
				cs_percent1.setBorderRight(HSSFCellStyle.BORDER_THIN);
				cs_percent1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				cs_percent1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				
				HSSFCellStyle cs_percent=wb.createCellStyle();
				cs_percent.setDataFormat(format_hf.getFormat("0%"));
				cs_percent.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cs_percent.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				cs_percent.setBorderTop(HSSFCellStyle.BORDER_THIN);
				cs_percent.setBorderRight(HSSFCellStyle.BORDER_THIN);
				cs_percent.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				cs_percent.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				
				HSSFCellStyle cs_poi2=wb.createCellStyle();
				cs_poi2.setDataFormat(format_hf.getFormat("#,###,0.00"));
				cs_poi2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cs_poi2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				cs_poi2.setBorderTop(HSSFCellStyle.BORDER_THIN);
				cs_poi2.setBorderRight(HSSFCellStyle.BORDER_THIN);
				cs_poi2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				cs_poi2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				
				HSSFCellStyle cs_poi4=wb.createCellStyle();
				cs_poi4.setDataFormat(format_hf.getFormat("#,###,0.0000"));
				cs_poi4.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cs_poi4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				cs_poi4.setBorderTop(HSSFCellStyle.BORDER_THIN);
				cs_poi4.setBorderRight(HSSFCellStyle.BORDER_THIN);
				cs_poi4.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				cs_poi4.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				
				HSSFCellStyle cs_poi=wb.createCellStyle();
				cs_poi.setDataFormat(format_hf.getFormat("#,###,0"));
				cs_poi.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cs_poi.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				cs_poi.setBorderTop(HSSFCellStyle.BORDER_THIN);
				cs_poi.setBorderRight(HSSFCellStyle.BORDER_THIN);
				cs_poi.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				cs_poi.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				
				HSSFCellStyle cs_poi1=wb.createCellStyle();
				cs_poi1.setDataFormat(format_hf.getFormat("#,###,0.0"));
				cs_poi1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cs_poi1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				cs_poi1.setBorderTop(HSSFCellStyle.BORDER_THIN);
				cs_poi1.setBorderRight(HSSFCellStyle.BORDER_THIN);
				cs_poi1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				cs_poi1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				/**		
				 * 數據源
				 * (1)機台回轉數
				 */
				//數據內容名稱和單位
				List<String>list_contentName_machine=new ArrayList<String>();
				List<String>list_unit_machine=new ArrayList<String>();
				list_contentName_machine.add("全廠孔位數");list_unit_machine.add("孔");
				list_contentName_machine.add("上班天數");list_unit_machine.add("天");
				list_contentName_machine.add("總上模數");list_unit_machine.add("模");
				list_contentName_machine.add("平均一天上模數");list_unit_machine.add("模/天");
				list_contentName_machine.add("機台利用率");list_unit_machine.add("%");
				list_contentName_machine.add("平均料重");list_unit_machine.add("g/雙");
				list_contentName_machine.add("平均淨重");list_unit_machine.add("g/雙");
				list_contentName_machine.add("標準回轉數");list_unit_machine.add("回");
				list_contentName_machine.add("實際回轉數");list_unit_machine.add("回");
				list_contentName_machine.add("回轉數差異");list_unit_machine.add("回");
				list_contentName_machine.add("達成率(%)");list_unit_machine.add("%");
				
				/**
				 * 數據源
				 * (2)生產與請款狀況
				 */
				//數據內容名稱和單位
				List<String>list_contentName_inv=new ArrayList<String>();
				List<String>list_unit_inv=new ArrayList<String>();
				list_contentName_inv.add("生產雙數");list_unit_inv.add("雙");
				list_contentName_inv.add("請款雙數");list_unit_inv.add("雙");
				list_contentName_inv.add("銷貨收入");list_unit_inv.add("USD");
				list_contentName_inv.add("平均單價");list_unit_inv.add("USD/雙");
																			
					/**
					* 數據源
					* (3)出貨與退貨
					*/
					//數據內容名稱和單位
					List<String>list_contentName_mix1=new ArrayList<String>();
					List<String>list_unit_mix1=new ArrayList<String>();
					list_contentName_mix1.add("出貨數");list_unit_mix1.add("雙");
					list_contentName_mix1.add("退貨數");list_unit_mix1.add("雙");
					list_contentName_mix1.add("退貨率");list_unit_mix1.add("%");
					
					/**
					* 數據源
					* (3)人員效能
					*/
					//數據內容名稱和單位
					List<String>list_contentName_person=new ArrayList<String>();
					List<String>list_unit_person=new ArrayList<String>();
					list_contentName_person.add("標準產量");list_unit_person.add("模");
					list_contentName_person.add("實際產量");list_unit_person.add("模");
					list_contentName_person.add("達成率(%)");list_unit_person.add("%");
					list_contentName_person.add("直工人數");list_unit_person.add("人");
					list_contentName_person.add("間工人數");list_unit_person.add("人");
					list_contentName_person.add("全廠總人數");list_unit_person.add("人");
					list_contentName_person.add("直间比");list_unit_person.add("--");
					list_contentName_person.add("直工人均产能");list_unit_person.add("模/人");
					list_contentName_person.add("全廠人均产能");list_unit_person.add("模/人");
					list_contentName_person.add("全廠人均时产能");list_unit_person.add("模/H");
					list_contentName_person.add("直工離職率(%)");list_unit_person.add("%");
					list_contentName_person.add("全廠離職率(%)");list_unit_person.add("%");
					list_contentName_person.add("直接薪資(含加班費+獎金)");list_unit_person.add("USD");
					list_contentName_person.add("間接薪資(含加班費+獎金)");list_unit_person.add("USd");
					list_contentName_person.add("總薪資(含加班費+獎金)");list_unit_person.add("USD");
					list_contentName_person.add("總工時");list_unit_person.add("H");
					list_contentName_person.add("每雙薪資單耗");list_unit_person.add("USD/雙");
					list_contentName_person.add("直工加班費");list_unit_person.add("USD");
					list_contentName_person.add("間工加班費");list_unit_person.add("USD");
					list_contentName_person.add("總加班費");list_unit_person.add("USD");
					list_contentName_person.add("直工人均薪資");list_unit_person.add("USD/人");
					list_contentName_person.add("間工人均薪資");list_unit_person.add("USD/人");
					list_contentName_person.add("直工占薪資比例");list_unit_person.add("%");
					list_contentName_person.add("間工占薪資比例");list_unit_person.add("%");
					list_contentName_person.add("全廠人均薪資");list_unit_person.add("USD/人");
					
					/**
					* 數據源
					* (3)關務損耗
					*/
					//數據內容名稱和單位
					List<String>list_contentName_gw=new ArrayList<String>();
					List<String>list_unit_gw=new ArrayList<String>();
					list_contentName_gw.add("當月總耗用原料");list_unit_gw.add("kg");
					list_contentName_gw.add("當月標准用量");list_unit_gw.add("kg");
					list_contentName_gw.add("總損耗量");list_unit_gw.add("kg");
					list_contentName_gw.add("總損耗率");list_unit_gw.add("%");
					list_contentName_gw.add("無形損耗");list_unit_gw.add("kg");
					list_contentName_gw.add("無形損耗率");list_unit_gw.add("%");
					
					/**
					* 數據源
					* (3)邊粒不良重量分析
					*/
					//數據內容名稱和單位
					List<String>list_contentName_side=new ArrayList<String>();
					List<String>list_unit_side=new ArrayList<String>();
					list_contentName_side.add("粗坯平均單價");list_unit_side.add("USD/KG");
					list_contentName_side.add("邊料(kg)");list_unit_side.add("KG");
					list_contentName_side.add("平均邊料重(g)");list_unit_side.add("G/雙");
					list_contentName_side.add("邊料%");list_unit_side.add("%");
					list_contentName_side.add("邊料金額");list_unit_side.add("USD");
					list_contentName_side.add("不良品雙數");list_unit_side.add("雙");
					list_contentName_side.add("不良品重量");list_unit_side.add("KG");
					list_contentName_side.add("不良重量不良率");list_unit_side.add("%");
					list_contentName_side.add("不良品金額");list_unit_side.add("USD");
					list_contentName_side.add("其它報廢重量");list_unit_side.add("KG");
					list_contentName_side.add("其它報廢金額");list_unit_side.add("USD");
					list_contentName_side.add("總報廢重量");list_unit_side.add("KG");
					list_contentName_side.add("總報廢金額");list_unit_side.add("USD");
					
					/**
					* 數據源
					* (3)庫存
					*/
					//數據內容名稱和單位
					List<String>list_contentName_store=new ArrayList<String>();
					List<String>list_unit_store=new ArrayList<String>();
					list_contentName_store.add("上月成倉庫存數");list_unit_store.add("雙");
					list_contentName_store.add("上月無訂單庫存");list_unit_store.add("雙");
					list_contentName_store.add("上月整理庫存");list_unit_store.add("雙");
					list_contentName_store.add("上月已出未請數");list_unit_store.add("雙");
					list_contentName_store.add("上月已請未出貨");list_unit_store.add("雙");
					list_contentName_store.add("前倉入庫折算後可請款生產雙數");list_unit_store.add("雙");
					list_contentName_store.add("小計-本月可請款數(A)");list_unit_store.add("雙");
					list_contentName_store.add("本月成倉庫存數");list_unit_store.add("雙");
					list_contentName_store.add("本月無訂單庫存");list_unit_store.add("雙");
					list_contentName_store.add("本月整理庫存");list_unit_store.add("雙");
					list_contentName_store.add("本月已出未請數");list_unit_store.add("雙");
					list_contentName_store.add("本月已請未出貨");list_unit_store.add("雙");
					list_contentName_store.add("小計-本月未請款數(B)");list_unit_store.add("雙");
					list_contentName_store.add("本月應請款數(A-B)");list_unit_store.add("雙");
					list_contentName_store.add("本月實際請款數");list_unit_store.add("雙");
					list_contentName_store.add("生產與請款差異數");list_unit_store.add("雙");
					list_contentName_store.add("廠客補、樣品未請款數");list_unit_store.add("雙");
					list_contentName_store.add("無形差異");list_unit_store.add("雙");			
					
					/**
					* 數據源
					* (3)水電油
					*/
					//數據內容名稱和單位
					List<String>list_contentName_wlo=new ArrayList<String>();
					List<String>list_unit_wlo=new ArrayList<String>();
					list_contentName_wlo.add("生產模數");list_unit_wlo.add("雙");
					list_contentName_wlo.add("水用量");list_unit_wlo.add("噸");
					list_contentName_wlo.add("用水單耗");list_unit_wlo.add("噸/模");
					list_contentName_wlo.add("用水金額");list_unit_wlo.add("USD");
					list_contentName_wlo.add("用水金額單耗(模)");list_unit_wlo.add("USD/模");
					list_contentName_wlo.add("用電量");list_unit_wlo.add("度");
					list_contentName_wlo.add("用電單耗(模)");list_unit_wlo.add("度/模");
					list_contentName_wlo.add("用電金額(USD)");list_unit_wlo.add("USD");
					list_contentName_wlo.add("用電金額單耗(模)");list_unit_wlo.add("USD/模");
					list_contentName_wlo.add("蒸氣用量(T)");list_unit_wlo.add("噸");
					list_contentName_wlo.add("用蒸氣單耗(模)");list_unit_wlo.add("噸/模");
					list_contentName_wlo.add("用蒸氣金額(USD)");list_unit_wlo.add("USD");
					list_contentName_wlo.add("用蒸氣金額單耗(模)");list_unit_wlo.add("USD/模");
					list_contentName_wlo.add("柴油用量(公升)");list_unit_wlo.add("公升");
					list_contentName_wlo.add("用油單耗(模)");list_unit_wlo.add("公升/模");
					list_contentName_wlo.add("柴油金額(USD)");list_unit_wlo.add("USD");
					list_contentName_wlo.add("用油金額單耗(模)");list_unit_wlo.add("USD/模");									
					
					/**
					* 數據源
					* (3)回頭料
					*/
					//數據內容名稱和單位
					List<String>list_contentName_back=new ArrayList<String>();
					List<String>list_unit_back=new ArrayList<String>();
					list_contentName_back.add("粗坯用量");list_unit_back.add("kg");
					list_contentName_back.add("回頭料重量");list_unit_back.add("kg");
					list_contentName_back.add("回頭料%");list_unit_back.add("%");
					list_contentName_back.add("油壓退料重量");list_unit_back.add("kg");
					list_contentName_back.add("油壓退料%");list_unit_back.add("%");
					list_contentName_back.add("合計重量");list_unit_back.add("kg");
					list_contentName_back.add("回頭率%");list_unit_back.add("%");
					/**
					 * 數據源
					 * (9)色料回收粉
					 */
					//數據內容名稱和單位
					List<String>list_contentName_webcost=new ArrayList<String>();
					List<String>list_unit_webcost=new ArrayList<String>();
					list_contentName_webcost.add("色料用量");list_unit_webcost.add("KG");
					list_contentName_webcost.add("色料單耗");list_unit_webcost.add("g/模");
					list_contentName_webcost.add("藥品用量");list_unit_webcost.add("KG");
					list_contentName_webcost.add("藥品單耗");list_unit_webcost.add("g/模");
					list_contentName_webcost.add("離型劑金額");list_unit_webcost.add("USD");
					list_contentName_webcost.add("離型劑單耗");list_unit_webcost.add("USD/模");
					list_contentName_webcost.add("白色回收粉");list_unit_webcost.add("KG");
					list_contentName_webcost.add("黑色回收粉");list_unit_webcost.add("KG");
					list_contentName_webcost.add("生膠回收粉");list_unit_webcost.add("KG");
					list_contentName_webcost.add("灰色回收粉");list_unit_webcost.add("KG");
					list_contentName_webcost.add("其它回收粉");list_unit_webcost.add("KG");
					
									
				//表頭內容
				List<String>list_column=new ArrayList<String>();
				list_column.add("項目");
				list_column.add("細項");
				list_column.add("單位");
				List<String>list_months=this.getMonths(yymm_begin, yymm_end);
				for(int a=0;a<list_months.size();a++){
					String ym=list_months.get(a);
					list_column.add(ym);
				}
				list_column.add("合計");								
				//廠名標題名稱
				String factName=webFactSer.selByid(factNo);
				String title=factName+"("+factNo+")"+yymm_begin+"--"+yymm_end+"每月檢討統計表";
				
				//數據內容				
				//獲取每個廠的所有廠別狀態
				List<WebFact>list_factCode=webFactSer.findFactById_showA(factNo);
				
				//一個廠中所有廠別狀態的數據(例如：632包括RB,MD)
				//如下多重集合的解釋:
				//(1)List<Double>:一個列的數據(將WWebmachine每個屬性封裝在一個List<Double>,便於填充數據)  ---單元集合(list3)
				//(2)List<List<Double>>一個表的數據(對應多個廠別狀態)                                    ---中間集合(list2)
				//(3)List<List<List<Double>>>多個表的數據(對應多個廠別狀態)                              ---總集合(list1)
				
				List<List<List<Double>>>list_all_webmachine=new ArrayList<List<List<Double>>>();//---總集合(list1)
				List<List<List<Double>>>list_all_inv=new ArrayList<List<List<Double>>>();
				List<List<List<Double>>>list_all_mix1=new ArrayList<List<List<Double>>>();
				List<List<List<Double>>>list_all_person=new ArrayList<List<List<Double>>>();
				List<List<List<Double>>>list_all_gw=new ArrayList<List<List<Double>>>();
				List<List<List<Double>>>list_all_side=new ArrayList<List<List<Double>>>();
				List<List<List<Double>>>list_all_store=new ArrayList<List<List<Double>>>();
				List<List<List<Double>>>list_all_wlo=new ArrayList<List<List<Double>>>();
				List<List<List<Double>>>list_all_back=new ArrayList<List<List<Double>>>();
				List<List<List<Double>>>list_all_webcost=new ArrayList<List<List<Double>>>();
				for(int z=0;z<list_factCode.size();z++){//start for1_1
					WebFact fact=list_factCode.get(z);
					String factCode=fact.getId().getFactArea();
					List<List<Double>>list_machine=new ArrayList<List<Double>>();//---中間集合(list2)			
					List<List<Double>>list_inv=new ArrayList<List<Double>>();//---中間集合(list2)
					List<List<Double>>list_mix1=new ArrayList<List<Double>>();//---中間集合(list2)
					List<List<Double>>list_person=new ArrayList<List<Double>>();//---中間集合(list2)
					List<List<Double>>list_gw=new ArrayList<List<Double>>();//---中間集合(list2)
					List<List<Double>>list_side=new ArrayList<List<Double>>();//---中間集合(list2)
					List<List<Double>>list_store=new ArrayList<List<Double>>();//---中間集合(list2)
					List<List<Double>>list_wlo=new ArrayList<List<Double>>();//---中間集合(list2)
					List<List<Double>>list_back=new ArrayList<List<Double>>();//---中間集合(list2)
					List<List<Double>>list_webcost=new ArrayList<List<Double>>();//---中間集合(list2)
					
					//注意:中間集合(list2)總共有23項,前面3項(項目,細項,單位)不需要,為了補全數量,所以添加3個空元素
					list_machine.add(null);
					list_machine.add(null);
					list_machine.add(null);
					
					list_inv.add(null);
					list_inv.add(null);
					list_inv.add(null);
					
					list_mix1.add(null);
					list_mix1.add(null);
					list_mix1.add(null);
					
					list_person.add(null);
					list_person.add(null);
					list_person.add(null);
					
					list_gw.add(null);
					list_gw.add(null);
					list_gw.add(null);
					
					list_side.add(null);
					list_side.add(null);
					list_side.add(null);
					
					list_store.add(null);
					list_store.add(null);
					list_store.add(null);
					
					list_wlo.add(null);
					list_wlo.add(null);
					list_wlo.add(null);
					
					list_back.add(null);
					list_back.add(null);
					list_back.add(null);
					
					list_webcost.add(null);
					list_webcost.add(null);
					list_webcost.add(null);
					
					//合計
					/**
					 * 機台回轉數
					 */										
					Double machineEve_total_sumWorkday=0.0;Double machineEve_total_sumEverydemo=0.0;
					Double machineEve_total_sumStandarddemo=0.0;Double machineEve_total_sumActualdemo=0.0;
					Double machineEve_total_sumActualpairs=0.0;Double machineEve_total_avgbuttomweight=0.0;
					Double machineEve_total_avgbuttomweight2=0.0;Double machineEve_total_hole=0.0;					
					/**
					 * 生產與請款狀況
					 */								
					Double invEve_total_sumActualpairs=0.0;Double invEve_total_invcount=0.0;
					Double invEve_total_paypair=0.0;
					/**
					 * 出貨與退貨
					 */								
					Double mix1Eve_total_sumoutnum=0.0;Double mix1Eve_total_sumbacknum=0.0;
					/**
					 * 人員效能
					 */								
					Double personEve_total_personzg=0.0;Double personEve_total_personjg=0.0;
					Double personEve_total_timezg=0.0;Double personEve_total_timejg=0.0;
					Double personEve_total_addtimezg=0.0;Double personEve_total_addtimejg=0.0;
					Double personEve_total_leavenumzg=0.0;Double personEve_total_leavenumjg=0.0;
					Double personEve_total_wagezgUsd=0.0;Double personEve_total_wagejgUsd=0.0;
					Double personEve_total_addmoneyzg=0.0;Double personEve_total_addmoneyjg=0.0;
					Double personEve_total_sumStandarddemo=0.0;Double personEve_total_sumActualdemo=0.0;
					Double personEve_total_sumActualpairs=0.0;
					/**
					 * 關務損耗
					 */					
					Double gwEve_total_sumActualpairs=0.0;Double gwEve_total_sideweit=0.0;
					Double gwEve_total_badweit=0.0;Double gwEve_total_otherbadweight=0.0;
					Double gwEve_total_actlost=0.0;Double gwEve_total_avgbuttomweight2=0.0;
					Double gwEve_total_otherweight=0.0;
					/**
					 * 邊料不良重量分析
					 */					
					Double sideEve_total_sumActualpairs=0.0;Double sideEve_total_sideweit=0.0;
					Double sideEve_total_badcount=0.0;Double sideEve_total_badweit=0.0;
					Double sideEve_total_otherbadweight=0.0;Double sideEve_total_avgbuttomweight=0.0;
					Double sideEve_total_avgbuttomweight2=0.0;Double sideEve_total_avgprice=0.0;
					Double sideEve_total_otherweight=0.0;
					/**
					 * 庫存
					 */					
					Double storeEve_total_sumhostpairs=0.0;Double storeEve_total_sumsamplepairs=0.0;
					Double storeEve_total_invcount=0.0;Double storeEve_total_hostinvcount=0.0;
					Double storeEve_total_badcount=0.0;Double storeEve_total_lagStorenum=0.0;
					Double storeEve_total_lagNoliststorenum=0.0;Double storeEve_total_lagMakestorenum=0.0;
					Double storeEve_total_lagoutOutnum=0.0;Double storeEve_total_lagInnum=0.0;
					Double storeEve_total_lagInstorenum=0.0;Double storeEve_total_storenum=0.0;
					Double storeEve_total_noliststorenum=0.0;Double storeEve_total_makestorenum=0.0;
					Double storeEve_total_outnum=0.0;Double storeEve_total_innum=0.0;
					Double storeEve_total_minusnum=0.0;
					/**
					 * 水電油
					 */					
					Double wloEve_total_sumactualdemo=0.0;Double wloEve_total_waterton=0.0;
					Double wloEve_total_waterusd=0.0;Double wloEve_total_electricdu=0.0;
					Double wloEve_total_electricusd=0.0;Double wloEve_total_gaston=0.0;
					Double wloEve_total_gasusd=0.0;Double wloEve_total_oilton=0.0;
					Double wloEve_total_oilusd=0.0;
					/**
					 * 回頭料
					 */					
					Double  backEve_total_thickusd=0.0;Double  backEve_total_backfeed=0.0;
					Double  backEve_total_oilback=0.0;
					/**
					 * 色料回收粉
					 */
					Double webcostEve_total_sumActualdemo=0.0;Double webcostEve_total_gluestoremoney=0.0;  
					Double webcostEve_total_drugsused=0.0;Double webcostEve_total_leavemoney=0.0;
					Double webcostEve_total_whitenum=0.0;Double webcostEve_total_blacknum=0.0;
					Double webcostEve_total_gluenum=0.0;Double webcostEve_total_greynum=0.0;
					Double webcostEve_total_othernum=0.0;
					
					
								
					for(int z_1=0;z_1<list_months.size();z_1++){//start for2_2
						//machine
						List<Double>list_machine_2=new ArrayList<Double>();//用於裝每月數據---單元集合(list3)
						List<Double>list_machine_total=new ArrayList<Double>();//用於裝合計數據---單元集合(list3)
						
						//inv
						List<Double>list_inv_2=new ArrayList<Double>();
						List<Double>list_inv_total=new ArrayList<Double>();
						
						//mix1
						List<Double>list_mix1_2=new ArrayList<Double>();
						List<Double>list_mix1_total=new ArrayList<Double>();
						
						//person
						List<Double>list_person_2=new ArrayList<Double>();
						List<Double>list_person_total=new ArrayList<Double>();
						
						//gw
						List<Double>list_gw_2=new ArrayList<Double>();
						List<Double>list_gw_total=new ArrayList<Double>();
						
						//side
						List<Double>list_side_2=new ArrayList<Double>();
						List<Double>list_side_total=new ArrayList<Double>();
						
						//store
						List<Double>list_store_2=new ArrayList<Double>();
						List<Double>list_store_total=new ArrayList<Double>();
						
						//wlo
						List<Double>list_wlo_2=new ArrayList<Double>();
						List<Double>list_wlo_total=new ArrayList<Double>();
						
						//back
						List<Double>list_back_2=new ArrayList<Double>();
						List<Double>list_back_total=new ArrayList<Double>();
						
						//webcost
						List<Double>list_webcost_2=new ArrayList<Double>();
						List<Double>list_webcost_total=new ArrayList<Double>();
						
						
						String yymm=list_months.get(z_1);
						//有算式表達式
						SimpleDateFormat format=new SimpleDateFormat("yyyyMM");
						VWebmachine machine=vwebmachineser.findById(factNo, factCode, yymm);
						VWeboutputinv inv=vinvser.findById(factNo, factCode, yymm);
						SumWebYieldData mix1=sumydateSer.findById(factNo, factCode, yymm);
						VWebperson person=vpersonser.findById(factNo, factCode, yymm);
						VWebgw gw=vgwser.findById(factNo, factCode, yymm);
						VWebside side=vsideser.findById(factNo, factCode, yymm);
						VWebstore store=vstoreser.findById(factNo, factCode, yymm);
						VWebwlo wlo=vwloser.findById(factNo, factCode, yymm);				
						Webbackfeed back=feedSer.findById(factNo,factCode,format.parse(yymm));
						VWebcost webcost=vwebcostSer.findById(factNo, factCode, yymm);
						//無算式表達式
						VWebmachineEve machine_eve=machineeveSer.findById(factNo, factCode, yymm);
						VWeboutputinvEve inv_eve=inveveSer.findById(factNo, factCode, yymm);
						SumWebYieldData mix1_eve=sumydateSer.findById(factNo, factCode, yymm);
						VWebpersonEve person_eve=personeveSer.findById(factNo, factCode, yymm);
						VWebgwEve gw_eve=gweveSer.findById(factNo, factCode, yymm);
						VWebsideEve side_eve=sideeveSer.findById(factNo, factCode, yymm);
						VWebstoreEve store_eve=storeeveSer.findById(factNo, factCode, yymm);
						VWebwloEve wlo_eve=wloeveSer.findById(factNo, factCode, yymm);
						Webbackfeed back_eve=feedSer.findById(factNo,factCode,format.parse(yymm));
						VWebcostEve webcost_eve=vwebcosteveSer.findById(factNo, factCode, yymm);
						
						//定義big賦值給空值
						BigDecimal big=new BigDecimal(0.00);
						//(1)判斷machine,machine_eve
						if(machine_eve!=null){					
							//合計							
								machineEve_total_sumWorkday=machineEve_total_sumWorkday+machine_eve.getSumWorkday().doubleValue();
								machineEve_total_sumEverydemo=machineEve_total_sumEverydemo+machine_eve.getSumEverydemo().doubleValue();
								machineEve_total_sumStandarddemo=machineEve_total_sumStandarddemo+machine_eve.getSumStandarddemo().doubleValue();
								machineEve_total_sumActualdemo=machineEve_total_sumActualdemo+machine_eve.getSumActualdemo().doubleValue();
								machineEve_total_sumActualpairs=machineEve_total_sumActualpairs+machine_eve.getSumActualpairs().doubleValue();
								machineEve_total_avgbuttomweight=machineEve_total_avgbuttomweight+machine_eve.getAvgbuttomweight();
								machineEve_total_avgbuttomweight2=machineEve_total_avgbuttomweight2+machine_eve.getAvgbuttomweight2();
								machineEve_total_hole=machineEve_total_hole+machine_eve.getHole();													
						}
						if(machine!=null){					
							list_machine_2.add(machine.getVWebmcA001());list_machine_2.add(machine.getVWebmcA002().doubleValue());
							list_machine_2.add(machine.getVWebmcA003().doubleValue());list_machine_2.add(machine.getVWebmcA004().doubleValue());
							list_machine_2.add(machine.getVWebmcA005().doubleValue());list_machine_2.add(machine.getVWebmcA006());
							list_machine_2.add(machine.getVWebmcA007());list_machine_2.add(machine.getVWebmcA008().doubleValue());
							list_machine_2.add(machine.getVWebmcA009().doubleValue());list_machine_2.add(machine.getVWebmcA010().doubleValue());
							list_machine_2.add(machine.getVWebmcA011().doubleValue());
							list_machine.add(list_machine_2);
							
						}else{					
							list_machine.add(null);//每個月為空					
						}												
						//(2)判斷inv,invEve
						if(inv_eve!=null){
							//sumActualpairs有可能為空
							if(inv_eve.getSumActualpairs()==null){
								inv_eve.setSumActualpairs(big);
							}
							if(inv_eve.getPaypairs()==null){
								inv_eve.setPaypairs(big.doubleValue());
							}
							//合計
							invEve_total_sumActualpairs=invEve_total_sumActualpairs+inv_eve.getSumActualpairs().doubleValue();
							invEve_total_invcount=invEve_total_invcount+inv_eve.getInvcount();
							invEve_total_paypair=invEve_total_paypair+inv_eve.getPaypairs();							
						}
						if(inv!=null){
							//判斷vwebinvboon是否null
						    if(inv.getVWebinvB001()==null){
						    	inv.setVWebinvB001(big);
						    }
						    if(inv.getVWebinvB003()==null){
						    	inv.setVWebinvB003(0.00);						    	
						    }
						    if(inv.getVWebinvB004()==null){
						    	inv.setVWebinvB004(big);
						    }
							list_inv_2.add(inv.getVWebinvB001().doubleValue());list_inv_2.add(inv.getVWebinvB002());
							list_inv_2.add(inv.getVWebinvB003());list_inv_2.add(inv.getVWebinvB004().doubleValue());					
							list_inv.add(list_inv_2);
											
						}else{
							//為空時,也要添加一個空null,以補全數量
							list_inv.add(null);//每個月為空					
						}
						
						//(3)判斷mix1,mix1_eve
						if(mix1_eve!=null){
							//sumbacknum,sumoutnum有可能為空
							if(mix1_eve.getSumBacknum()==null){
								mix1_eve.setSumBacknum(big);
							}
							if(mix1_eve.getSumOutnum()==null){
								mix1_eve.setSumOutnum(big);
							}
							//合計							
								mix1Eve_total_sumbacknum=mix1Eve_total_sumbacknum+mix1_eve.getSumBacknum().doubleValue();
								mix1Eve_total_sumoutnum=mix1Eve_total_sumoutnum+mix1_eve.getSumOutnum().doubleValue();														
						}
						if(mix1!=null){
							//判斷sumOutnum與sumBacknum是否為空					
							if(mix1.getSumOutnum()==null){
								mix1.setSumOutnum(big);
							}
							if(mix1.getSumBacknum()==null){
								mix1.setSumBacknum(big);
							}
							list_mix1_2.add(mix1.getSumOutnum().doubleValue());list_mix1_2.add(mix1.getSumBacknum().doubleValue());
							list_mix1_2.add(this.division(mix1.getSumBacknum().doubleValue(), mix1.getSumOutnum().doubleValue()));
							list_mix1.add(list_mix1_2);					
							
						}else{
							//為空時,也要添加一個空null,以補全數量
							list_mix1.add(null);//每個月為空					
						}
						//(4)判斷person,personEve
						if(person_eve!=null){
							//合計						
								personEve_total_addmoneyjg=personEve_total_addmoneyjg+person_eve.getAddmoneyjg();
								personEve_total_addmoneyzg=personEve_total_addmoneyzg+person_eve.getAddmoneyzg();
								personEve_total_addtimejg=personEve_total_addtimejg+person_eve.getAddtimejg();
								personEve_total_addtimezg=personEve_total_addtimezg+person_eve.getAddtimezg();
								personEve_total_leavenumjg=personEve_total_leavenumjg+person_eve.getLeavenumjg();
								personEve_total_leavenumzg=personEve_total_leavenumzg+person_eve.getLeavenumzg();
								personEve_total_personjg=personEve_total_personjg+person_eve.getPersonjg();
								personEve_total_personzg=personEve_total_personzg+person_eve.getPersonzg();
								personEve_total_sumActualdemo=personEve_total_sumActualdemo+person_eve.getSumActualdemo().doubleValue();
								personEve_total_sumActualpairs=personEve_total_sumActualpairs+person_eve.getSumActualpairs().doubleValue();
								personEve_total_sumStandarddemo=personEve_total_sumStandarddemo+person_eve.getSumStandarddemo().doubleValue();
								personEve_total_timejg=personEve_total_timejg+person_eve.getPersonjg();
								personEve_total_timezg=personEve_total_timezg+person_eve.getPersonzg();
								personEve_total_wagejgUsd=personEve_total_wagejgUsd+person_eve.getWagejgUsd();
								personEve_total_wagezgUsd=personEve_total_wagezgUsd+person_eve.getWagezgUsd();														
							
						}
						if(person!=null){
							list_person_2.add(person.getVWebperD001().doubleValue());list_person_2.add(person.getVWebperD002().doubleValue());
							list_person_2.add(person.getVWebperD003().doubleValue());list_person_2.add(person.getVWebperD004());
							list_person_2.add(person.getVWebperD005());list_person_2.add(person.getVWebperD006().doubleValue());
							list_person_2.add(person.getVWebperD007().doubleValue());list_person_2.add(person.getVWebperD008().doubleValue());
							list_person_2.add(person.getVWebperD009().doubleValue());list_person_2.add(person.getVWebperD010().doubleValue());
							list_person_2.add(person.getVWebperD011().doubleValue());list_person_2.add(person.getVWebperD012().doubleValue());
							list_person_2.add(person.getVWebperD013());list_person_2.add(person.getVWebperD014());
							list_person_2.add(person.getVWebperD015().doubleValue());list_person_2.add(person.getVWebperD016().doubleValue());
							list_person_2.add(person.getVWebperD017().doubleValue());list_person_2.add(person.getVWebperD018());
							list_person_2.add(person.getVWebperD019());list_person_2.add(person.getVWebperD020().doubleValue());
							list_person_2.add(person.getVWebperD021().doubleValue());list_person_2.add(person.getVWebperD022().doubleValue());
							list_person_2.add(person.getVWebperD023().doubleValue());list_person_2.add(person.getVWebperD024().doubleValue());
							list_person_2.add(person.getVWebperD025().doubleValue());					
							list_person.add(list_person_2);
							
							
						}else{
							//為空時,也要添加一個空null,以補全數量
							list_person.add(null);//每個月為空					
						}
						//(5)判斷gw,gw_eve
						if(gw_eve!=null){
							//合計						
								gwEve_total_actlost=gwEve_total_actlost+gw_eve.getActlost();
								gwEve_total_avgbuttomweight2=gwEve_total_avgbuttomweight2+gw_eve.getAvgbuttomweight2();
								gwEve_total_badweit=gwEve_total_badweit+gw_eve.getBadweit();
								gwEve_total_otherbadweight=gwEve_total_otherbadweight+gw_eve.getOtherbadweight();
								gwEve_total_sideweit=gwEve_total_sideweit+gw_eve.getSideweit();
								gwEve_total_sumActualpairs=gwEve_total_sumActualpairs+gw_eve.getSumActualpairs().doubleValue();																				
						}
						if(gw!=null){
							list_gw_2.add(gw.getVWebgwE001());list_gw_2.add(gw.getVWebgwE002().doubleValue());
							list_gw_2.add(gw.getVWebgwE003().doubleValue());list_gw_2.add(gw.getVWebgwE004().doubleValue());
							list_gw_2.add(gw.getVWebgwE005().doubleValue());list_gw_2.add(gw.getVWebgwE006().doubleValue());					
							list_gw.add(list_gw_2);
							
						}else{
							//為空時,也要添加一個空null,以補全數量
							list_gw.add(null);//每個月為空					
						}
						//(6)判斷side,side_eve
						if(side_eve!=null){
							//合計
								sideEve_total_avgbuttomweight=sideEve_total_avgbuttomweight+side_eve.getAvgbuttomweight();
								sideEve_total_avgbuttomweight2=sideEve_total_avgbuttomweight2+side_eve.getAvgbuttomweight2();
								sideEve_total_avgprice=sideEve_total_avgprice+side_eve.getAvgprice();
								sideEve_total_badcount=sideEve_total_badcount+side_eve.getBadcount();
								sideEve_total_badweit=sideEve_total_badweit+side_eve.getBadweit();
								sideEve_total_otherbadweight=sideEve_total_otherbadweight+side_eve.getOtherbadweight();
								sideEve_total_sideweit=sideEve_total_sideweit+side_eve.getSideweit();
								sideEve_total_sumActualpairs=sideEve_total_sumActualpairs+side_eve.getSumActualpairs().doubleValue();														
						}
						if(side!=null){
							list_side_2.add(side.getVWebsideF001());list_side_2.add(side.getVWebsideF002());
							list_side_2.add(side.getVWebsideF003().doubleValue());list_side_2.add(side.getVWebsideF004().doubleValue());
							list_side_2.add(side.getVWebsideF005().doubleValue());list_side_2.add(side.getVWebsideF006());
							list_side_2.add(side.getVWebsideF007());list_side_2.add(side.getVWebsideF008().doubleValue());
							list_side_2.add(side.getVWebsideF009().doubleValue());list_side_2.add(side.getVWebsideF010());
							list_side_2.add(side.getVWebsideF011().doubleValue());list_side_2.add(side.getVWebsideF012().doubleValue());
							list_side_2.add(side.getVWebsideF013().doubleValue());
							list_side.add(list_side_2);
							
							
						}else{
							//為空時,也要添加一個空null,以補全數量
							list_side.add(null);//每個月為空					
						}
						//(7)判斷store,store_eve
						if(store_eve!=null){
							//合計
								storeEve_total_badcount=storeEve_total_badcount+store_eve.getBadcount();
								storeEve_total_hostinvcount=storeEve_total_hostinvcount+store_eve.getHostinvcount();
								storeEve_total_innum=storeEve_total_innum+store_eve.getInnum();
								storeEve_total_invcount=storeEve_total_invcount+store_eve.getInvcount();
								storeEve_total_lagInnum=storeEve_total_lagInnum+store_eve.getLagInnum().doubleValue();
								storeEve_total_lagInstorenum=storeEve_total_lagInstorenum+store_eve.getLagInstorenum().doubleValue();
								storeEve_total_lagMakestorenum=storeEve_total_lagMakestorenum+store_eve.getLagMakestrorenum().doubleValue();
								storeEve_total_lagNoliststorenum=storeEve_total_lagNoliststorenum+store_eve.getLagNoliststrorenum().doubleValue();
								storeEve_total_lagoutOutnum=storeEve_total_lagoutOutnum+store_eve.getLagOutnum().doubleValue();
								storeEve_total_lagStorenum=storeEve_total_lagStorenum+store_eve.getLagStorenum().doubleValue();
								storeEve_total_makestorenum=storeEve_total_makestorenum+store_eve.getMakestrorenum();
								storeEve_total_minusnum=storeEve_total_minusnum+store_eve.getMinusnum();
								storeEve_total_noliststorenum=storeEve_total_noliststorenum+store_eve.getNoliststrorenum();
								storeEve_total_outnum=storeEve_total_outnum+store_eve.getOutnum();
								storeEve_total_storenum=storeEve_total_storenum+store_eve.getStorenum();
								storeEve_total_sumhostpairs=storeEve_total_sumhostpairs+store_eve.getSumhostpairs().doubleValue();
								storeEve_total_sumsamplepairs=storeEve_total_sumsamplepairs+store_eve.getSumsamplepairs().doubleValue();
						}
						if(store!=null){
							//G001-G007(和G014)為上月數據,有可能出現null,所以要進行判斷
							if(store.getVWebstoreG001()==null){
								store.setVWebstoreG001(big);
							}
							if(store.getVWebstoreG002()==null){
								store.setVWebstoreG002(big);
							}
							if(store.getVWebstoreG003()==null){
								store.setVWebstoreG003(big);
							}
							if(store.getVWebstoreG004()==null){
								store.setVWebstoreG004(big);
							}
							if(store.getVWebstoreG005()==null){
								store.setVWebstoreG005(big);						
							}
							if(store.getVWebstoreG006()==null){
								store.setVWebstoreG006(big);
							}
							if(store.getVWebstoreG007()==null){
								store.setVWebstoreG007(big);
							}
							if(store.getVWebstoreG014()==null){
								store.setVWebstoreG014(big);
							}
							list_store_2.add(store.getVWebstoreG001().doubleValue());list_store_2.add(store.getVWebstoreG002().doubleValue());
							list_store_2.add(store.getVWebstoreG003().doubleValue());list_store_2.add(store.getVWebstoreG004().doubleValue());
							list_store_2.add(store.getVWebstoreG005().doubleValue());list_store_2.add(store.getVWebstoreG006().doubleValue());
							list_store_2.add(store.getVWebstoreG007().doubleValue());
							list_store_2.add(store.getVWebstoreG008());
							list_store_2.add(store.getVWebstoreG009());list_store_2.add(store.getVWebstoreG010());
							list_store_2.add(store.getVWebstoreG011());list_store_2.add(store.getVWebstoreG012());
							list_store_2.add(store.getVWebstoreG013().doubleValue());list_store_2.add(store.getVWebstoreG014().doubleValue());
							list_store_2.add(store.getVWebstoreG015());list_store_2.add(store.getVWebstoreG016());
							list_store_2.add(store.getVWebstoreG017().doubleValue());list_store_2.add(store.getVWebstoreG018().doubleValue());									
							list_store.add(list_store_2);
							
							
						}else{
							//為空時,也要添加一個空null,以補全數量
							list_store.add(null);//每個月為空					
						}
						//(8)判斷wlo,wlo_eve
						if(wlo_eve!=null){
							//合計							
								wloEve_total_electricdu=wloEve_total_electricdu+wlo_eve.getElectricdu();
								wloEve_total_electricusd=wloEve_total_electricusd+wlo_eve.getElectricusd();
								wloEve_total_gaston=wloEve_total_gaston+wlo_eve.getGaston();
								wloEve_total_gasusd=wloEve_total_gasusd+wlo_eve.getGasusd();
								wloEve_total_oilton=wloEve_total_oilton+wlo_eve.getOilton();
								wloEve_total_oilusd=wloEve_total_oilusd+wlo_eve.getOilusd();
								wloEve_total_sumactualdemo=wloEve_total_sumactualdemo+wlo_eve.getSumactualdemo().doubleValue();
								wloEve_total_waterton=wloEve_total_waterton+wlo_eve.getWaterton();
								wloEve_total_waterusd=wloEve_total_waterusd+wlo_eve.getWaterusd();														
						}
						if(wlo!=null){
							list_wlo_2.add(wlo.getVWebwloH001().doubleValue());list_wlo_2.add(wlo.getVWebwloH002());
							list_wlo_2.add(wlo.getVWebwloH003().doubleValue());list_wlo_2.add(wlo.getVWebwloH004());
							list_wlo_2.add(wlo.getVWebwloH005().doubleValue());list_wlo_2.add(wlo.getVWebwloH006());
							list_wlo_2.add(wlo.getVWebwloH007().doubleValue());list_wlo_2.add(wlo.getVWebwloH008());
							list_wlo_2.add(wlo.getVWebwloH009().doubleValue());list_wlo_2.add(wlo.getVWebwloH010());
							list_wlo_2.add(wlo.getVWebwloH011().doubleValue());list_wlo_2.add(wlo.getVWebwloH012());
							list_wlo_2.add(wlo.getVWebwloH013().doubleValue());list_wlo_2.add(wlo.getVWebwloH014().doubleValue());
							list_wlo_2.add(wlo.getVWebwloH015().doubleValue());list_wlo_2.add(wlo.getVWebwloH016());
							list_wlo_2.add(wlo.getVWebwloH017().doubleValue());									
							list_wlo.add(list_wlo_2);
							
						}else{
							//為空時,也要添加一個空null,以補全數量
							list_wlo.add(null);//每個月為空					
						}
						//(9)判斷back,back_eve
						if(back_eve!=null){
							//合計
								backEve_total_backfeed=backEve_total_backfeed+back_eve.getBackfeed();
								backEve_total_oilback=backEve_total_oilback+back_eve.getOilback();
								backEve_total_thickusd=backEve_total_thickusd+back_eve.getThickused();							
						}
						if(back!=null){
							Double back_i003=back.getBackfeed()/back.getThickused();
							Double back_i005=back.getOilback()/back.getThickused();
							Double back_i006=back.getBackfeed()+back.getOilback();
							Double back_i007=back.getBackfeed()/back.getThickused()+back.getOilback()/back.getThickused();
							
							list_back_2.add(back.getThickused());list_back_2.add(back.getBackfeed());
							list_back_2.add(back_i003);list_back_2.add(back.getOilback());
							list_back_2.add(back_i005);list_back_2.add(back_i006);
							list_back_2.add(back_i007);								
							list_back.add(list_back_2);
							
						}else{
							//為空時,也要添加一個空null,以補全數量
							list_back.add(null);//每個月為空
							
						}
						//(10)判斷webcost,webcost_eve
						if(webcost_eve!=null){
							//合計
							webcostEve_total_sumActualdemo=webcostEve_total_sumActualdemo+webcost_eve.getSumActualdemo().doubleValue();
						    webcostEve_total_gluestoremoney=webcostEve_total_gluestoremoney+webcost_eve.getGluestoremoney();
						    webcostEve_total_drugsused=webcostEve_total_drugsused+webcost_eve.getDrugsused();
						    webcostEve_total_leavemoney=webcostEve_total_leavemoney+webcost_eve.getLeavemoney();
						    webcostEve_total_whitenum=webcostEve_total_whitenum+webcost_eve.getWhitenum();
						    webcostEve_total_blacknum=webcostEve_total_blacknum+webcost_eve.getBlacknum();
						    webcostEve_total_gluenum=webcostEve_total_gluenum+webcost_eve.getGluenum();
						    webcostEve_total_greynum=webcostEve_total_greynum+webcost_eve.getGreynum();
						    webcostEve_total_othernum=webcostEve_total_othernum+webcost_eve.getOthernum();						    
						}
						if(webcost!=null){
							list_webcost_2.add(webcost.getVWebcostJ001());
							list_webcost_2.add(webcost.getVWebcostJ002().doubleValue());
							list_webcost_2.add(webcost.getVWebcostJ003());
							list_webcost_2.add(webcost.getVWebcostJ004().doubleValue());
							list_webcost_2.add(webcost.getVWebcostJ005());
							list_webcost_2.add(webcost.getVWebcostJ006().doubleValue());
							list_webcost_2.add(webcost.getVWebcostJ007());
							list_webcost_2.add(webcost.getVWebcostJ008());
							list_webcost_2.add(webcost.getVWebcostJ009());
							list_webcost_2.add(webcost.getVWebcostJ010());
							list_webcost_2.add(webcost.getVWebcostJ011());
							list_webcost.add(list_webcost_2);
						}else{
							//為空時,也要添加一個空null,以補全數量
							list_webcost.add(null);//每個月為空
							
						}
						
						/**
						 * 合計
						 */			
						if(z_1==list_months.size()-1){
							//孔位數回轉數--machine
							list_machine_total=this.findResult_machine(list_months.size(),
									machineEve_total_sumWorkday, machineEve_total_sumEverydemo, machineEve_total_sumStandarddemo, machineEve_total_sumActualdemo, machineEve_total_sumActualpairs,
									machineEve_total_avgbuttomweight, machineEve_total_avgbuttomweight2, 
									machineEve_total_hole);
							list_machine.add(list_machine_total);
							//生產與請款狀況--inv
							list_inv_total=this.findResult_inv(list_months.size(),
									invEve_total_sumActualpairs, invEve_total_invcount, invEve_total_paypair);							
							list_inv.add(list_inv_total);
		                    //出貨和退貨--mix1
							list_mix1_total=this.findResult_mix1(list_months.size(),
									mix1Eve_total_sumoutnum, mix1Eve_total_sumbacknum);							
							list_mix1.add(list_mix1_total);
							//人員效能--person
							list_person_total=this.findResult_person(list_months.size(), 
									personEve_total_personzg, personEve_total_personjg, personEve_total_timezg, personEve_total_timejg, 
									personEve_total_addtimezg, personEve_total_addtimejg, personEve_total_leavenumzg, personEve_total_leavenumjg,
									personEve_total_wagezgUsd, personEve_total_wagejgUsd, personEve_total_addmoneyzg, personEve_total_addmoneyjg,
									personEve_total_sumStandarddemo, personEve_total_sumActualdemo, personEve_total_sumActualpairs);
							list_person.add(list_person_total);
							//關務損耗--gw
							list_gw_total=this.findResult_gw(list_months.size(),
									gwEve_total_sumActualpairs, gwEve_total_sideweit, 
									gwEve_total_badweit, gwEve_total_otherbadweight, gwEve_total_actlost,
									gwEve_total_avgbuttomweight2,gwEve_total_otherweight);						
							list_gw.add(list_gw_total);
							//邊料不良重量分析-side
							list_side_total=this.findResult_side(list_months.size(), 
									sideEve_total_sumActualpairs, sideEve_total_sideweit, sideEve_total_badcount, 
									sideEve_total_badweit, sideEve_total_otherbadweight, sideEve_total_avgbuttomweight, 
									sideEve_total_avgbuttomweight2, sideEve_total_avgprice,sideEve_total_otherweight);
							list_side.add(list_side_total);
							//庫存--store
							list_store_total=this.findResult_store(list_months.size(), 
									storeEve_total_sumhostpairs, storeEve_total_sumsamplepairs, storeEve_total_invcount,
									storeEve_total_hostinvcount, storeEve_total_badcount, storeEve_total_lagStorenum, 
									storeEve_total_lagNoliststorenum, storeEve_total_lagMakestorenum, storeEve_total_lagoutOutnum, 
									storeEve_total_lagInnum, storeEve_total_lagInstorenum, storeEve_total_storenum, storeEve_total_noliststorenum,
									storeEve_total_makestorenum, storeEve_total_outnum, storeEve_total_innum, storeEve_total_minusnum);				
							list_store.add(list_store_total);					
							//水電油--wlo
							list_wlo_total=this.findResult_wlo(list_months.size(), 
									wloEve_total_sumactualdemo, wloEve_total_waterton, wloEve_total_waterusd, 
									wloEve_total_electricdu, wloEve_total_electricusd, wloEve_total_gaston,
									wloEve_total_gasusd, wloEve_total_oilton, wloEve_total_oilusd);							
							list_wlo.add(list_wlo_total);
							//回頭料--back
							list_back_total=this.findResult_back(list_months.size(),
									backEve_total_thickusd, backEve_total_backfeed, backEve_total_oilback);				
							list_back.add(list_back_total);
							//色料回收粉--webcost
							list_webcost_total=this.findResult_webcost(list_months.size(), webcostEve_total_sumActualdemo, webcostEve_total_gluestoremoney, webcostEve_total_drugsused, webcostEve_total_leavemoney, webcostEve_total_whitenum,
									webcostEve_total_blacknum, webcostEve_total_gluenum, webcostEve_total_greynum, webcostEve_total_othernum);				
							list_webcost.add(list_webcost_total);
						}
																														
					}//end for2_2
					
					//一個廠別狀態循環完畢,則要添加一個(中間集合list2）
					list_all_webmachine.add(list_machine);
					list_all_inv.add(list_inv);
					list_all_mix1.add(list_mix1);
					list_all_person.add(list_person);
					list_all_gw.add(list_gw);
					list_all_side.add(list_side);
					list_all_store.add(list_store);
					list_all_wlo.add(list_wlo);
					list_all_back.add(list_back);
					list_all_webcost.add(list_webcost);
				}//end for1_1
				
				
				/**
				 * 填充內容
				 * (1)機台回轉數
				 */
				//填充標題
				HSSFRow row_title=sheet.createRow(0);
				HSSFCell cell_title=row_title.createCell(0);
				cell_title.setCellValue(title);
				cell_title.setCellStyle(cs_title);
				CellRangeAddress cra_title=new CellRangeAddress(0,(short)0,0,list_column.size()-1);
				sheet.addMergedRegion(cra_title);
				for(int a=1;a<list_column.size();a++){
					HSSFCell cell=row_title.createCell(a);
					cell.setCellStyle(cs_title);
				}
				//填充廠別狀態和表頭
				for(int c=0;c<list_factCode.size();c++){//start for1
				WebFact fact=list_factCode.get(c);
				String factCode=fact.getId().getFactArea();//廠別狀態
				HSSFRow row_factCode=sheet.createRow(2+114*c);
				HSSFCell cell_factCode=row_factCode.createCell(0);
				cell_factCode.setCellValue(factCode);
				cell_factCode.setCellStyle(cs_noborder);
				HSSFRow row_column=sheet.createRow(3+114*c);
				for(int b=0;b<list_column.size();b++){
					HSSFCell cell=row_column.createCell(b);
					cell.setCellValue(list_column.get(b));
					cell.setCellStyle(cs_column);
				}
				for(int d=0;d<list_column.size();d++){//start for2
					int length1=list_contentName_machine.size();
					int length2=list_contentName_inv.size();
					int length3=list_contentName_mix1.size();
					int length4=list_contentName_person.size();
					int length5=list_contentName_gw.size();
					int length6=list_contentName_side.size();
					int length7=list_contentName_store.size();
					int length8=list_contentName_wlo.size();
					int length9=list_contentName_back.size();
					int length10=list_contentName_webcost.size();
					switch(d){
					case 0:
						sheet.setColumnWidth(d, 3500);
						//機台回轉數
						HSSFRow row_a=sheet.createRow(4+114*c);
						HSSFCell cell_a=row_a.createCell(d);
						cell_a.setCellValue("機台回轉數");
						cell_a.setCellStyle(cs);
						CellRangeAddress cra_row_a=new CellRangeAddress((4+114*c),(short)(4+114*c+(length1-1)),d,(short)d);
						sheet.addMergedRegion(cra_row_a);
						for(int e=1;e<length1;e++){
							HSSFRow row_a_2=sheet.createRow(4+e+114*c);
							HSSFCell cell_a_2=row_a_2.createCell(d);
							cell_a_2.setCellStyle(cs);
						}
						//生產與請款狀況
						HSSFRow row_a2=sheet.createRow(4+114*c+length1);
						HSSFCell cell_a2=row_a2.createCell(d);
						cell_a2.setCellValue("生產與請款狀況");
						cell_a2.setCellStyle(cs);
						CellRangeAddress cra_row_a2=new CellRangeAddress(4+114*c+length1,(short)(4+114*c+length1+length2-1),d,(short)d);
						sheet.addMergedRegion(cra_row_a2);
						for(int e2=1;e2<length2;e2++){
							HSSFRow row_a2_2=sheet.createRow(4+114*c+length1+e2);
							HSSFCell cell_a2_2=row_a2_2.createCell(d);
							cell_a2_2.setCellStyle(cs);
						}
						//出貨與退貨
						HSSFRow row_a3=sheet.createRow(4+114*c+length1+length2);
						HSSFCell cell_a3=row_a3.createCell(d);
						cell_a3.setCellValue("出貨與退貨");
						cell_a3.setCellStyle(cs);
						CellRangeAddress cra_row_a3=new CellRangeAddress(4+114*c+length1+length2,
								(short)(4+114*c+length1+length2+length3-1),d,(short)d);
						sheet.addMergedRegion(cra_row_a3);
						for(int e2=1;e2<length3;e2++){
							HSSFRow row_a3_2=sheet.createRow(4+114*c+length1+length2+e2);
							HSSFCell cell_a3_2=row_a3_2.createCell(d);
							cell_a3_2.setCellStyle(cs);
						}
						//人員效能
						HSSFRow row_a4=sheet.createRow(4+114*c+length1+length2+length3);
						HSSFCell cell_a4=row_a4.createCell(d);
						cell_a4.setCellValue("人員效能");
						cell_a4.setCellStyle(cs);
						CellRangeAddress cra_row_a4=new CellRangeAddress(4+114*c+length1+length2+length3,
								(short)(4+114*c+length1+length2+length3+length4-1),d,(short)d);
						sheet.addMergedRegion(cra_row_a4);
						for(int e2=1;e2<length4;e2++){
							HSSFRow row_a3_2=sheet.createRow(4+114*c+length1+length2+length3+e2);
							HSSFCell cell_a3_2=row_a3_2.createCell(d);
							cell_a3_2.setCellStyle(cs);
						}
						//關務損耗
						HSSFRow row_a5=sheet.createRow(4+114*c+length1+length2+length3+length4);
						HSSFCell cell_a5=row_a5.createCell(d);
						cell_a5.setCellValue("關務損耗");
						cell_a5.setCellStyle(cs);
						CellRangeAddress cra_row_a5=new CellRangeAddress(4+114*c+length1+length2+length3+length4,
								(short)(4+114*c+length1+length2+length3+length4+length5-1),d,(short)d);
						sheet.addMergedRegion(cra_row_a5);
						for(int e2=1;e2<length5;e2++){
							HSSFRow row_a3_2=sheet.createRow(4+114*c+length1+length2+length3+length4+e2);
							HSSFCell cell_a3_2=row_a3_2.createCell(d);
							cell_a3_2.setCellStyle(cs);
						}
						//邊料不良重量分析
						HSSFRow row_a6=sheet.createRow(4+114*c+length1+length2+length3+length4+length5);
						HSSFCell cell_a6=row_a6.createCell(d);
						cell_a6.setCellValue("邊料不良重量分析");
						cell_a6.setCellStyle(cs);
						CellRangeAddress cra_row_a6=new CellRangeAddress(4+114*c+length1+length2+length3+length4+length5,
								(short)(4+114*c+length1+length2+length3+length4+length5+length6-1),d,(short)d);
						sheet.addMergedRegion(cra_row_a6);
						for(int e2=1;e2<length6;e2++){
							HSSFRow row_a3_2=sheet.createRow(4+114*c+length1+length2+length3+length4+length5+e2);
							HSSFCell cell_a3_2=row_a3_2.createCell(d);
							cell_a3_2.setCellStyle(cs);
						}
						//庫存
						HSSFRow row_a7=sheet.createRow(4+114*c+length1+length2+length3+length4+length5+length6);
						HSSFCell cell_a7=row_a7.createCell(d);
						cell_a7.setCellValue("庫存");
						cell_a7.setCellStyle(cs);
						CellRangeAddress cra_row_a7=new CellRangeAddress(4+114*c+length1+length2+length3+length4+length5+length6,
								(short)(4+114*c+length1+length2+length3+length4+length5+length6+length7-1),d,(short)d);
						sheet.addMergedRegion(cra_row_a7);
						for(int e2=1;e2<length7;e2++){
							HSSFRow row_a3_2=sheet.createRow(4+114*c+length1+length2+length3+length4+length5+length6+e2);
							HSSFCell cell_a3_2=row_a3_2.createCell(d);
							cell_a3_2.setCellStyle(cs);
						}
						//水電油
						HSSFRow row_a8=sheet.createRow(4+114*c+length1+length2+length3+length4+length5+length6+length7);
						HSSFCell cell_a8=row_a8.createCell(d);
						cell_a8.setCellValue("水電油");
						cell_a8.setCellStyle(cs);
						CellRangeAddress cra_row_a8=new CellRangeAddress(4+114*c+length1+length2+length3+length4+length5+length6+length7,
								(short)(4+114*c+length1+length2+length3+length4+length5+length6+length7+length8-1),d,(short)d);
						sheet.addMergedRegion(cra_row_a8);
						for(int e2=1;e2<length8;e2++){
							HSSFRow row_a3_2=sheet.createRow(4+114*c+length1+length2+length3+length4+length5+length6+length7+e2);
							HSSFCell cell_a3_2=row_a3_2.createCell(d);
							cell_a3_2.setCellStyle(cs);
						}
						//回頭料
						HSSFRow row_a9=sheet.createRow(4+114*c+length1+length2+length3+length4+length5+length6+length7+length8);
						HSSFCell cell_a9=row_a9.createCell(d);
						cell_a9.setCellValue("回頭料");
						cell_a9.setCellStyle(cs);
						CellRangeAddress cra_row_a9=new CellRangeAddress(4+114*c+length1+length2+length3+length4+length5+length6+length7+length8,
								(short)(4+114*c+length1+length2+length3+length4+length5+length6+length7+length8+length9-1),d,(short)d);
						sheet.addMergedRegion(cra_row_a9);
						for(int e2=1;e2<length9;e2++){
							HSSFRow row_a3_2=sheet.createRow(4+114*c+length1+length2+length3+length4+length5+length6+length7+length8+e2);
							HSSFCell cell_a3_2=row_a3_2.createCell(d);
							cell_a3_2.setCellStyle(cs);
						}
						//色料回收粉
						HSSFRow row_a10=sheet.createRow(4+114*c+length1+length2+length3+length4+length5+length6+length7+length8+length9);
						HSSFCell cell_a10=row_a10.createCell(d);
						cell_a10.setCellValue("色料藥品\n離型劑\n回收粉");
						cell_a10.setCellStyle(cs);
						CellRangeAddress cra_row_a10=new CellRangeAddress(4+114*c+length1+length2+length3+length4+length5+length6+length7+length8+length9,
								(short)(4+114*c+length1+length2+length3+length4+length5+length6+length7+length8+length9+length10-1),d,(short)d);
						sheet.addMergedRegion(cra_row_a10);
						for(int e2=1;e2<length10;e2++){
							HSSFRow row_a3_2=sheet.createRow(4+114*c+length1+length2+length3+length4+length5+length6+length7+length8+length9+e2);
							HSSFCell cell_a3_2=row_a3_2.createCell(d);
							cell_a3_2.setCellStyle(cs);
						}
						break;
					case 1:
						sheet.setColumnWidth(d, 4000);
						//機台回轉數
						for(int f=0;f<length1;f++){
							HSSFRow row_b=sheet.getRow(4+f+114*c);
							HSSFCell cell_b=row_b.createCell(d);
							cell_b.setCellValue(list_contentName_machine.get(f));
							cell_b.setCellStyle(cs);
						}
						//生產與請款狀況
						for(int f2=0;f2<length2;f2++){
							HSSFRow row_b=sheet.getRow(4+114*c+length1+f2);
							HSSFCell cell_b=row_b.createCell(d);
							cell_b.setCellValue(list_contentName_inv.get(f2));
							cell_b.setCellStyle(cs);
						}
						//出貨與退貨
						for(int f2=0;f2<length3;f2++){
							HSSFRow row_b=sheet.getRow(4+114*c+length1+length2+f2);
							HSSFCell cell_b=row_b.createCell(d);
							cell_b.setCellValue(list_contentName_mix1.get(f2));
							cell_b.setCellStyle(cs);
						}
						//人員效能
						for(int f2=0;f2<length4;f2++){
							HSSFRow row_b=sheet.getRow(4+114*c+length1+length2+length3+f2);
							HSSFCell cell_b=row_b.createCell(d);
							cell_b.setCellValue(list_contentName_person.get(f2));
							cell_b.setCellStyle(cs);
						}
						//關務損耗
						for(int f2=0;f2<length5;f2++){
							HSSFRow row_b=sheet.getRow(4+114*c+length1+length2+length3+length4+f2);
							HSSFCell cell_b=row_b.createCell(d);
							cell_b.setCellValue(list_contentName_gw.get(f2));
							cell_b.setCellStyle(cs);
						}
						//邊料不良重量分析
						for(int f2=0;f2<length6;f2++){
							HSSFRow row_b=sheet.getRow(4+114*c+length1+length2+length3+length4+length5+f2);
							HSSFCell cell_b=row_b.createCell(d);
							cell_b.setCellValue(list_contentName_side.get(f2));
							cell_b.setCellStyle(cs);
						}
						//庫存
						for(int f2=0;f2<length7;f2++){
							HSSFRow row_b=sheet.getRow(4+114*c+length1+length2+length3+length4+length5+length6+f2);
							HSSFCell cell_b=row_b.createCell(d);
							cell_b.setCellValue(list_contentName_store.get(f2));
							cell_b.setCellStyle(cs);
						}
						//水電油
						for(int f2=0;f2<length8;f2++){
							HSSFRow row_b=sheet.getRow(4+114*c+length1+length2+length3+length4+length5+length6+length7+f2);
							HSSFCell cell_b=row_b.createCell(d);
							cell_b.setCellValue(list_contentName_wlo.get(f2));
							cell_b.setCellStyle(cs);
						}
						//回頭料
						for(int f2=0;f2<length9;f2++){
							HSSFRow row_b=sheet.getRow(4+114*c+length1+length2+length3+length4+length5+length6+length7+length8+f2);
							HSSFCell cell_b=row_b.createCell(d);
							cell_b.setCellValue(list_contentName_back.get(f2));
							cell_b.setCellStyle(cs);
						}
						//色料回收粉
						for(int f2=0;f2<length10;f2++){
							HSSFRow row_b=sheet.getRow(4+114*c+length1+length2+length3+length4+length5+length6+length7+length8+length9+f2);
							HSSFCell cell_b=row_b.createCell(d);
							cell_b.setCellValue(list_contentName_webcost.get(f2));
							cell_b.setCellStyle(cs);
						}
						break;
					case 2:
						//機台回轉數
						for(int g=0;g<length1;g++){
							HSSFRow row_c=sheet.getRow(4+g+114*c);
							HSSFCell cell_c=row_c.createCell(d);
							cell_c.setCellValue(list_unit_machine.get(g));
							cell_c.setCellStyle(cs);
						}
						//生產與請款狀況
						for(int g2=0;g2<length2;g2++){
							HSSFRow row_c=sheet.getRow(4+114*c+length1+g2);
							HSSFCell cell_c=row_c.createCell(d);
							cell_c.setCellValue(list_unit_inv.get(g2));
							cell_c.setCellStyle(cs);
						}
						//出貨與退貨
						for(int g2=0;g2<length3;g2++){
							HSSFRow row_c=sheet.getRow(4+114*c+length1+length2+g2);
							HSSFCell cell_c=row_c.createCell(d);
							cell_c.setCellValue(list_unit_mix1.get(g2));
							cell_c.setCellStyle(cs);
						}
						//人員效能
						for(int g2=0;g2<length4;g2++){
							HSSFRow row_c=sheet.getRow(4+114*c+length1+length2+length3+g2);
							HSSFCell cell_c=row_c.createCell(d);
							cell_c.setCellValue(list_unit_person.get(g2));
							cell_c.setCellStyle(cs);
						}
						//關務損耗
						for(int g2=0;g2<length5;g2++){
							HSSFRow row_c=sheet.getRow(4+114*c+length1+length2+length3+length4+g2);
							HSSFCell cell_c=row_c.createCell(d);
							cell_c.setCellValue(list_unit_gw.get(g2));
							cell_c.setCellStyle(cs);
						}
						//邊料不良重量分析
						for(int g2=0;g2<length6;g2++){
							HSSFRow row_c=sheet.getRow(4+114*c+length1+length2+length3+length4+length5+g2);
							HSSFCell cell_c=row_c.createCell(d);
							cell_c.setCellValue(list_unit_side.get(g2));
							cell_c.setCellStyle(cs);
						}
						//庫存
						for(int g2=0;g2<length7;g2++){
							HSSFRow row_c=sheet.getRow(4+114*c+length1+length2+length3+length4+length5+length6+g2);
							HSSFCell cell_c=row_c.createCell(d);
							cell_c.setCellValue(list_unit_store.get(g2));
							cell_c.setCellStyle(cs);
						}
						//水電油
						for(int g2=0;g2<length8;g2++){
							HSSFRow row_c=sheet.getRow(4+114*c+length1+length2+length3+length4+length5+length6+length7+g2);
							HSSFCell cell_c=row_c.createCell(d);
							cell_c.setCellValue(list_unit_wlo.get(g2));
							cell_c.setCellStyle(cs);
						}
						//回頭料
						for(int g2=0;g2<length9;g2++){
							HSSFRow row_c=sheet.getRow(4+114*c+length1+length2+length3+length4+length5+length6+length7+length8+g2);
							HSSFCell cell_c=row_c.createCell(d);
							cell_c.setCellValue(list_unit_back.get(g2));
							cell_c.setCellStyle(cs);
						}
						//色料回收粉
						for(int g2=0;g2<length10;g2++){
							HSSFRow row_c=sheet.getRow(4+114*c+length1+length2+length3+length4+length5+length6+length7+length8+length9+g2);
							HSSFCell cell_c=row_c.createCell(d);
							cell_c.setCellValue(list_unit_webcost.get(g2));
							cell_c.setCellStyle(cs);
						}
						break;
					default:
						sheet.setColumnWidth(d, 2500);
						List<List<Double>>list2_machine=list_all_webmachine.get(c);//中間集合list2
						List<Double>list3_machine=list2_machine.get(d);//單元集合list3
						
						List<List<Double>>list2_inv=list_all_inv.get(c);//中間集合list2
						List<Double>list3_inv=list2_inv.get(d);//單元集合list3
						
						List<List<Double>>list2_mix1=list_all_mix1.get(c);//中間集合list2
						List<Double>list3_mix1=list2_mix1.get(d);//單元集合list3
						
						List<List<Double>>list2_person=list_all_person.get(c);//中間集合list2
						List<Double>list3_person=list2_person.get(d);//單元集合list3
						
						List<List<Double>>list2_gw=list_all_gw.get(c);//中間集合list2
						List<Double>list3_gw=list2_gw.get(d);//單元集合list3
						
						List<List<Double>>list2_side=list_all_side.get(c);//中間集合list2
						List<Double>list3_side=list2_side.get(d);//單元集合list3
						
						List<List<Double>>list2_store=list_all_store.get(c);//中間集合list2
						List<Double>list3_store=list2_store.get(d);//單元集合list3
						
						List<List<Double>>list2_wlo=list_all_wlo.get(c);//中間集合list2
						List<Double>list3_wlo=list2_wlo.get(d);//單元集合list3
						
						List<List<Double>>list2_back=list_all_back.get(c);//中間集合list2
						List<Double>list3_back=list2_back.get(d);//單元集合list3
						
						List<List<Double>>list2_webcost=list_all_webcost.get(c);//中間集合list2
						List<Double>list3_webcost=list2_webcost.get(d);//單元集合list3
						
						HSSFCellStyle cs_temp=wb.createCellStyle();//臨時樣式
						//孔位數回轉數--machine
						if(list3_machine!=null){
							for(int h=0;h<list3_machine.size();h++){						
								if(h==0||h==2||h==3||h==7||h==8){
									cs_temp=cs_poi;
								}else if(h==1||h==5||h==6||h==9){
									cs_temp=cs_poi1;
								}else if(h==4){
									cs_temp=cs_percent;                                                                                                   //20150423
								}else{
									cs_temp=cs_percent2;
								}
								
								HSSFRow row=sheet.getRow(4+h+114*c);						
								HSSFCell cell=row.createCell(d);
								cell.setCellValue(list3_machine.get(h));
								cell.setCellStyle(cs_temp);						
							}
						}else{
							for(int h=0;h<list_contentName_machine.size();h++){
								HSSFRow row=sheet.getRow(4+h+114*c);						
								HSSFCell cell=row.createCell(d);
								cell.setCellValue("無數據");
								cell.setCellStyle(cs);
							}
						}
						//生產與請款狀況--inv
						if(list3_inv!=null){
							for(int h=0;h<list3_inv.size();h++){
								if(h==0||h==1){
									cs_temp=cs_poi1;
								}else{
									cs_temp=cs_poi2;
								}
								HSSFRow row=sheet.getRow(4+h+114*c+length1);
								HSSFCell cell=row.createCell(d);
								cell.setCellValue(list3_inv.get(h));
								cell.setCellStyle(cs_temp);
							}					
						}else{
							for(int h=0;h<list_contentName_inv.size();h++){
								HSSFRow row=sheet.getRow(4+h+114*c+length1);						
								HSSFCell cell=row.createCell(d);
								cell.setCellValue("無數據");
								cell.setCellStyle(cs);
							}
						}
						//出貨與退貨--mix1
						if(list3_mix1!=null){					
							for(int h=0;h<list3_mix1.size();h++){
								if(h==2){
									cs_temp=cs_percent2;
								}else{
									cs_temp=cs_poi1;
								}
								HSSFRow row=sheet.getRow(4+h+114*c+length1+length2);
								HSSFCell cell=row.createCell(d);
								cell.setCellValue(list3_mix1.get(h));
								cell.setCellStyle(cs_temp);
							}
							
						}else{
							for(int h=0;h<list_contentName_mix1.size();h++){
								HSSFRow row=sheet.getRow(4+h+114*c+length1+length2);						
								HSSFCell cell=row.createCell(d);
								cell.setCellValue("無數據");
								cell.setCellStyle(cs);
							}
						}
						//人員效能--person
						if(list3_person!=null){
							for(int h=0;h<list3_person.size();h++){
								if(h==2||h==10||h==11||h==22||h==23){
									cs_temp=cs_percent2;
								}else if(h==0||h==1){
									cs_temp=cs_poi1;
								}else if(h==3||h==4||h==5||h==7||h==8){
									cs_temp=cs_poi;
								}else{
									cs_temp=cs_poi2;
								}
								HSSFRow row=sheet.getRow(4+h+114*c+length1+length2+length3);
								HSSFCell cell=row.createCell(d);
								cell.setCellValue(list3_person.get(h));
								cell.setCellStyle(cs_temp);
							}
							
						}else{
							for(int h=0;h<list_contentName_person.size();h++){
								HSSFRow row=sheet.getRow(4+h+114*c+length1+length2+length3);						
								HSSFCell cell=row.createCell(d);
								cell.setCellValue("無數據");
								cell.setCellStyle(cs);
							}
						}
						//關務損耗--gw
						if(list3_gw!=null){
							for(int h=0;h<list3_gw.size();h++){
								if(h==3||h==5){
									cs_temp=cs_percent2;
								}else{
									cs_temp=cs_poi2;
								}
								HSSFRow row=sheet.getRow(4+h+114*c+length1+length2+length3+length4);
								HSSFCell cell=row.createCell(d);
								cell.setCellValue(list3_gw.get(h));
								cell.setCellStyle(cs_temp);
							}
							
						}else{
							for(int h=0;h<list_contentName_gw.size();h++){
								HSSFRow row=sheet.getRow(4+h+114*c+length1+length2+length3+length4);						
								HSSFCell cell=row.createCell(d);
								cell.setCellValue("無數據");
								cell.setCellStyle(cs);
							}
						}
						//邊料不良重量分析--side
						if(list3_side!=null){
							for(int h=0;h<list3_side.size();h++){
								if(h==3||h==7){
									cs_temp=cs_percent2;
								}else{
									cs_temp=cs_poi2;
								}
								HSSFRow row=sheet.getRow(4+h+114*c+length1+length2+length3+length4+length5);
								HSSFCell cell=row.createCell(d);
								cell.setCellValue(list3_side.get(h));
								cell.setCellStyle(cs_temp);
							}
							
						}else{
							for(int h=0;h<list_contentName_side.size();h++){
								HSSFRow row=sheet.getRow(4+h+114*c+length1+length2+length3+length4+length5);						
								HSSFCell cell=row.createCell(d);
								cell.setCellValue("無數據");
								cell.setCellStyle(cs);
							}
						}
						//庫存--store
						if(list3_store!=null){
							for(int h=0;h<list3_store.size();h++){
								HSSFRow row=sheet.getRow(4+h+114*c+length1+length2+length3+length4+length5+length6);
								HSSFCell cell=row.createCell(d);
								cell.setCellValue(list3_store.get(h));
								cell.setCellStyle(cs_poi1);
							}
							
						}else{
							for(int h=0;h<list_contentName_store.size();h++){
								HSSFRow row=sheet.getRow(4+h+114*c+length1+length2+length3+length4+length5+length6);						
								HSSFCell cell=row.createCell(d);
								cell.setCellValue("無數據");
								cell.setCellStyle(cs);
							}
						}
						//水電油--wlo
						if(list3_wlo!=null){
							for(int h=0;h<list3_wlo.size();h++){
								if(h==0){
									cs_temp=cs_poi1;
								}else if(h==1||h==5){
									cs_temp=cs_poi;
								}else if(h==2||h==4||h==6||h==8||h==10||h==12||h==14||h==16){
									cs_temp=cs_poi4;
								}else{
									cs_temp=cs_poi2;
								}
								HSSFRow row=sheet.getRow(4+h+114*c+length1+length2+length3+length4+length5+length6+length7);
								HSSFCell cell=row.createCell(d);
								cell.setCellValue(list3_wlo.get(h));
								cell.setCellStyle(cs_temp);
							}
							
						}else{
							for(int h=0;h<list_contentName_wlo.size();h++){
								HSSFRow row=sheet.getRow(4+h+114*c+length1+length2+length3+length4+length5+length6+length7);						
								HSSFCell cell=row.createCell(d);
								cell.setCellValue("無數據");
								cell.setCellStyle(cs);
							}
						}
						//回頭料--back
						if(list3_back!=null){
							for(int h=0;h<list3_back.size();h++){
								if(h==2||h==4||h==6){
									cs_temp=cs_percent2;
								}else{
									cs_temp=cs_poi2;
								}
								HSSFRow row=sheet.getRow(4+h+114*c+length1+length2+length3+length4+length5+length6+length7+length8);
								HSSFCell cell=row.createCell(d);
								cell.setCellValue(list3_back.get(h));
								cell.setCellStyle(cs_temp);
							}
							
						}else{
							for(int h=0;h<list_contentName_back.size();h++){
								HSSFRow row=sheet.getRow(4+h+114*c+length1+length2+length3+length4+length5+length6+length7+length8);						
								HSSFCell cell=row.createCell(d);
								cell.setCellValue("無數據");
								cell.setCellStyle(cs);
							}
						}
						//色料回收粉--webcost
						if(list3_webcost!=null){
							for(int h=0;h<list3_webcost.size();h++){
								if(h==1||h==3||h==5){
									cs_temp=cs_poi4;
								}else{
									cs_temp=cs_poi2;
								}
								HSSFRow row=sheet.getRow(4+h+114*c+length1+length2+length3+length4+length5+length6+length7+length8+length9);
								HSSFCell cell=row.createCell(d);
								cell.setCellValue(list3_webcost.get(h));
								cell.setCellStyle(cs_temp);
							}
							
						}else{
							for(int h=0;h<list_contentName_webcost.size();h++){
								HSSFRow row=sheet.getRow(4+h+114*c+length1+length2+length3+length4+length5+length6+length7+length8+length9);						
								HSSFCell cell=row.createCell(d);
								cell.setCellValue("無數據");
								cell.setCellStyle(cs);
							}
						}
						break;
					}
				}//end for2
			}//end for1
				
				ServletOutputStream os=response.getOutputStream();
				response.setContentType("application/vnd.ms-excel");
				title=title+".xls";
				int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");
				String fileName="";
				if(msie>0){
					fileName=java.net.URLEncoder.encode(title,"utf-8");
				}else{
					fileName=new String(title.getBytes("utf-8"),"iso-8859-1");
				}
				response.setHeader("Content-disposition","attachment;filename="+fileName);
				//OutputStream os=new FileOutputStream("d:/vmachine.xls");
				wb.write(os);
				os.close();
	}
	
	
	public void print_tw() throws ParseException, IOException{
		//建立模板
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet();
		/**
		 * 各項樣式
		 */
		//基本樣式
		HSSFCellStyle cs=wb.createCellStyle();
		cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		//無邊框樣式
		HSSFCellStyle cs_noborder=wb.createCellStyle();
		cs_noborder.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_noborder.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		//標題樣式
		HSSFCellStyle cs_title=wb.createCellStyle();
		HSSFFont font_title=wb.createFont();
		font_title.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_title.setFontHeightInPoints((short)20);
		cs_title.setFont(font_title);
		cs_title.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_title.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_title.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_title.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_title.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_title.setBorderLeft(HSSFCellStyle.BORDER_THIN);
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
		//數字格式
		HSSFDataFormat format_hf=wb.createDataFormat();
		HSSFCellStyle cs_percent2=wb.createCellStyle();
		cs_percent2.setDataFormat(format_hf.getFormat("0.00%"));
		cs_percent2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_percent2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_percent2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_percent2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_percent2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_percent2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle cs_percent1=wb.createCellStyle();
		cs_percent1.setDataFormat(format_hf.getFormat("0.0%"));
		cs_percent1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_percent1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_percent1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_percent1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_percent1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_percent1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle cs_percent=wb.createCellStyle();
		cs_percent.setDataFormat(format_hf.getFormat("0%"));
		cs_percent.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_percent.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_percent.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle cs_poi2=wb.createCellStyle();
		cs_poi2.setDataFormat(format_hf.getFormat("#,###,0.00"));
		cs_poi2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle cs_poi4=wb.createCellStyle();
		cs_poi4.setDataFormat(format_hf.getFormat("#,###,0.0000"));
		cs_poi4.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi4.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle cs_poi=wb.createCellStyle();
		cs_poi.setDataFormat(format_hf.getFormat("#,###,0"));
		cs_poi.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle cs_poi1=wb.createCellStyle();
		cs_poi1.setDataFormat(format_hf.getFormat("#,###,0.0"));
		cs_poi1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		//日期格式
		SimpleDateFormat format=new SimpleDateFormat("yyyyMM");
		/**		
		 * 數據源
		 * (1)機台回轉數
		 */
		//數據內容名稱和單位
		List<String>list_contentName_machine=new ArrayList<String>();
		List<String>list_unit_machine=new ArrayList<String>();
		list_contentName_machine.add("全廠孔位數");list_unit_machine.add("孔");
		list_contentName_machine.add("上班天數");list_unit_machine.add("天");
		list_contentName_machine.add("總上模數");list_unit_machine.add("模");
		list_contentName_machine.add("平均一天上模數");list_unit_machine.add("模/天");
		list_contentName_machine.add("機台利用率");list_unit_machine.add("%");
		list_contentName_machine.add("平均料重");list_unit_machine.add("g/雙");
		list_contentName_machine.add("平均淨重");list_unit_machine.add("g/雙");
		list_contentName_machine.add("標準回轉數");list_unit_machine.add("回");
		list_contentName_machine.add("實際回轉數");list_unit_machine.add("回");
		list_contentName_machine.add("回轉數差異");list_unit_machine.add("回");
		list_contentName_machine.add("達成率(%)");list_unit_machine.add("%");
		
		/**
		 * 數據源
		 * (2)生產與請款狀況
		 */
		        //數據內容名稱和單位
				List<String>list_contentName_inv=new ArrayList<String>();
				List<String>list_unit_inv=new ArrayList<String>();
				list_contentName_inv.add("生產雙數");list_unit_inv.add("雙");
				list_contentName_inv.add("請款雙數");list_unit_inv.add("雙");
				list_contentName_inv.add("銷貨收入");list_unit_inv.add("USD");
				list_contentName_inv.add("平均單價");list_unit_inv.add("USD/雙");
																	
			/**
			* 數據源
			* (3)出貨與退貨
			*/
			//數據內容名稱和單位
			List<String>list_contentName_mix1=new ArrayList<String>();
			List<String>list_unit_mix1=new ArrayList<String>();
			list_contentName_mix1.add("出貨數");list_unit_mix1.add("雙");
			list_contentName_mix1.add("退貨數");list_unit_mix1.add("雙");
			list_contentName_mix1.add("退貨率");list_unit_mix1.add("%");
			
			/**
			* 數據源
			* (4)人員效能
			*/
			//數據內容名稱和單位
			List<String>list_contentName_person=new ArrayList<String>();
			List<String>list_unit_person=new ArrayList<String>();
			list_contentName_person.add("標準產量");list_unit_person.add("模");
			list_contentName_person.add("實際產量");list_unit_person.add("模");
			list_contentName_person.add("達成率(%)");list_unit_person.add("%");
			list_contentName_person.add("直工人數");list_unit_person.add("人");
			list_contentName_person.add("間工人數");list_unit_person.add("人");
			list_contentName_person.add("全廠總人數");list_unit_person.add("人");
			list_contentName_person.add("直间比");list_unit_person.add("--");
			list_contentName_person.add("直工人均产能");list_unit_person.add("模/人");
			list_contentName_person.add("全廠人均产能");list_unit_person.add("模/人");
			list_contentName_person.add("全廠人均时产能");list_unit_person.add("模/H");
			list_contentName_person.add("直工離職率(%)");list_unit_person.add("%");
			list_contentName_person.add("全廠離職率(%)");list_unit_person.add("%");
			list_contentName_person.add("直接薪資(含加班費+獎金)");list_unit_person.add("USD");
			list_contentName_person.add("間接薪資(含加班費+獎金)");list_unit_person.add("USD");
			list_contentName_person.add("總薪資(含加班費+獎金)");list_unit_person.add("USD");
			list_contentName_person.add("總工時");list_unit_person.add("H");
			list_contentName_person.add("每雙薪資單耗");list_unit_person.add("USD/雙");
			list_contentName_person.add("直工加班費");list_unit_person.add("USD");
			list_contentName_person.add("間工加班費");list_unit_person.add("USD");
			list_contentName_person.add("總加班費");list_unit_person.add("USD");
			list_contentName_person.add("直工人均薪資");list_unit_person.add("USD/人");
			list_contentName_person.add("間工人均薪資");list_unit_person.add("USD/人");
			list_contentName_person.add("直工占薪資比例");list_unit_person.add("%");
			list_contentName_person.add("間工占薪資比例");list_unit_person.add("%");
			list_contentName_person.add("全廠人均薪資");list_unit_person.add("USD/人");
			
			/**
			* 數據源
			* (5)關務損耗
			*/
			//數據內容名稱和單位
			List<String>list_contentName_gw=new ArrayList<String>();
			List<String>list_unit_gw=new ArrayList<String>();
			list_contentName_gw.add("當月總耗用原料");list_unit_gw.add("kg");
			list_contentName_gw.add("當月標准用量");list_unit_gw.add("kg");
			list_contentName_gw.add("總損耗量");list_unit_gw.add("kg");
			list_contentName_gw.add("總損耗率");list_unit_gw.add("%");
			list_contentName_gw.add("無形損耗");list_unit_gw.add("kg");
			list_contentName_gw.add("無形損耗率");list_unit_gw.add("%");
			
			/**
			* 數據源
			* (6)邊粒不良重量分析
			*/
			//數據內容名稱和單位
			List<String>list_contentName_side=new ArrayList<String>();
			List<String>list_unit_side=new ArrayList<String>();
			list_contentName_side.add("粗坯平均單價");list_unit_side.add("USD/KG");
			list_contentName_side.add("邊料(kg)");list_unit_side.add("KG");
			list_contentName_side.add("平均邊料重(g)");list_unit_side.add("G/雙");
			list_contentName_side.add("邊料%");list_unit_side.add("%");
			list_contentName_side.add("邊料金額");list_unit_side.add("USD");
			list_contentName_side.add("不良品雙數");list_unit_side.add("雙");
			list_contentName_side.add("不良品重量");list_unit_side.add("KG");
			list_contentName_side.add("不良重量不良率");list_unit_side.add("%");
			list_contentName_side.add("不良品金額");list_unit_side.add("USD");
			list_contentName_side.add("其它報廢重量");list_unit_side.add("KG");
			list_contentName_side.add("其它報廢金額");list_unit_side.add("USD");
			list_contentName_side.add("總報廢重量");list_unit_side.add("KG");
			list_contentName_side.add("總報廢金額");list_unit_side.add("USD");
			
			/**
			* 數據源
			* (7)庫存
			*/
			//數據內容名稱和單位
			List<String>list_contentName_store=new ArrayList<String>();
			List<String>list_unit_store=new ArrayList<String>();
			list_contentName_store.add("上月成倉庫存數");list_unit_store.add("雙");
			list_contentName_store.add("上月無訂單庫存");list_unit_store.add("雙");
			list_contentName_store.add("上月整理庫存");list_unit_store.add("雙");
			list_contentName_store.add("上月已出未請數");list_unit_store.add("雙");
			list_contentName_store.add("上月已請未出貨");list_unit_store.add("雙");
			list_contentName_store.add("前倉入庫折算後可請款生產雙數");list_unit_store.add("雙");
			list_contentName_store.add("小計-本月可請款數(A)");list_unit_store.add("雙");
			list_contentName_store.add("本月成倉庫存數");list_unit_store.add("雙");
			list_contentName_store.add("本月無訂單庫存");list_unit_store.add("雙");
			list_contentName_store.add("本月整理庫存");list_unit_store.add("雙");
			list_contentName_store.add("本月已出未請數");list_unit_store.add("雙");
			list_contentName_store.add("本月已請未出貨");list_unit_store.add("雙");
			list_contentName_store.add("小計-本月未請款數(B)");list_unit_store.add("雙");
			list_contentName_store.add("本月應請款數(A-B)");list_unit_store.add("雙");
			list_contentName_store.add("本月實際請款數");list_unit_store.add("雙");
			list_contentName_store.add("生產與請款差異數");list_unit_store.add("雙");
			list_contentName_store.add("廠客補、樣品未請款數");list_unit_store.add("雙");
			list_contentName_store.add("無形差異");list_unit_store.add("雙");			
			
			/**
			* 數據源
			* (8)水電油
			*/
			//數據內容名稱和單位
			List<String>list_contentName_wlo=new ArrayList<String>();
			List<String>list_unit_wlo=new ArrayList<String>();
			list_contentName_wlo.add("生產模數");list_unit_wlo.add("雙");
			list_contentName_wlo.add("水用量");list_unit_wlo.add("噸");
			list_contentName_wlo.add("用水單耗");list_unit_wlo.add("噸/模");
			list_contentName_wlo.add("用水金額");list_unit_wlo.add("USD");
			list_contentName_wlo.add("用水金額單耗(模)");list_unit_wlo.add("USD/模");
			list_contentName_wlo.add("用電量");list_unit_wlo.add("度");
			list_contentName_wlo.add("用電單耗(模)");list_unit_wlo.add("度/模");
			list_contentName_wlo.add("用電金額(USD)");list_unit_wlo.add("USD");
			list_contentName_wlo.add("用電金額單耗(模)");list_unit_wlo.add("USD/模");
			list_contentName_wlo.add("蒸氣用量(T)");list_unit_wlo.add("噸");
			list_contentName_wlo.add("用蒸氣單耗(模)");list_unit_wlo.add("噸/模");
			list_contentName_wlo.add("用蒸氣金額(USD)");list_unit_wlo.add("USD");
			list_contentName_wlo.add("用蒸氣金額單耗(模)");list_unit_wlo.add("USD/模");
			list_contentName_wlo.add("柴油用量(公升)");list_unit_wlo.add("公升");
			list_contentName_wlo.add("用油單耗(模)");list_unit_wlo.add("公升/模");
			list_contentName_wlo.add("柴油金額(USD)");list_unit_wlo.add("USD");
			list_contentName_wlo.add("用油金額單耗(模)");list_unit_wlo.add("USD/模");									
			
			/**
			* 數據源
			* (9)回頭料
			*/
			//數據內容名稱和單位
			List<String>list_contentName_back=new ArrayList<String>();
			List<String>list_unit_back=new ArrayList<String>();
			list_contentName_back.add("粗坯用量");list_unit_back.add("kg");
			list_contentName_back.add("回頭料重量");list_unit_back.add("kg");
			list_contentName_back.add("回頭料%");list_unit_back.add("%");
			list_contentName_back.add("油壓退料重量");list_unit_back.add("kg");
			list_contentName_back.add("油壓退料%");list_unit_back.add("%");
			list_contentName_back.add("合計重量");list_unit_back.add("kg");
			list_contentName_back.add("回頭率%");list_unit_back.add("%");  
			
			/**
			 * 數據源
			 * (9)色料回收粉
			 */
			//數據內容名稱和單位
			List<String>list_contentName_webcost=new ArrayList<String>();
			List<String>list_unit_webcost=new ArrayList<String>();
			list_contentName_webcost.add("色料用量");list_unit_webcost.add("KG");
			list_contentName_webcost.add("色料單耗");list_unit_webcost.add("g/模");
			list_contentName_webcost.add("藥品用量");list_unit_webcost.add("KG");
			list_contentName_webcost.add("藥品單耗");list_unit_webcost.add("g/模");
			list_contentName_webcost.add("離型劑金額");list_unit_webcost.add("USD");
			list_contentName_webcost.add("離型劑單耗");list_unit_webcost.add("USD/模");
			list_contentName_webcost.add("白色回收粉");list_unit_webcost.add("KG");
			list_contentName_webcost.add("黑色回收粉");list_unit_webcost.add("KG");
			list_contentName_webcost.add("生膠回收粉");list_unit_webcost.add("KG");
			list_contentName_webcost.add("灰色回收粉");list_unit_webcost.add("KG");
			list_contentName_webcost.add("其它回收粉");list_unit_webcost.add("KG");
			
			int length1=list_contentName_machine.size();
			int length2=list_contentName_inv.size();
			int length3=list_contentName_mix1.size();
			int length4=list_contentName_person.size();
			int length5=list_contentName_gw.size();
			int length6=list_contentName_side.size();
			int length7=list_contentName_store.size();
			int length8=list_contentName_wlo.size();
			int length9=list_contentName_back.size();
			int length10=list_contentName_webcost.size();
			
			//所有廠別狀態
			List<String>list_factCode=new ArrayList<String>();
			List<List<String>>list_factno_all=new ArrayList<List<String>>();//用於封裝各個factcode的廠別(如果有選擇廠別)
			//如果有選擇廠別狀態,則取所選 的,否則取全部廠別狀態
			if(list_factcode!=null&&list_factcode.size()>0){				
				list_factCode=list_factcode;
				//所選的廠別按factcode封裝起來
				for(int i=0;i<list_factcode.size();i++){
					List<String>list_factno_one=new ArrayList<String>();//用於封裝一個factcode的廠別
					String factcode=list_factcode.get(i);
					for(int j=0;j<list_factno.size();j++){
						String factno=list_factno.get(j);
						String[]factnos=factno.split("_");
						if(factnos[0].equals(factcode)){
							list_factno_one.add(factnos[1]);
						}
					}
					list_factno_all.add(list_factno_one);
				}
			}else{
				List<Object[]>list_objs=webFactSer.findAllFactCode2_showA();
				for(int i=0;i<list_objs.size();i++){
					Object[]obj=list_objs.get(i);
					String fact_code=obj[0].toString();
					list_factCode.add(fact_code);
				}
			}			
			//yymm="201410";
			/**
			 * 開始循環封閉數據
			 */
			/**
			 * 最外層集合List<List<List<Double>>>
			 */				
			//孔位數回轉數(VWebmachine)
			List<List<List<Double>>>list_machine=new ArrayList<List<List<Double>>>();
			//生產與請款狀況(VWeboutputinv)
			List<List<List<Double>>>list_inv=new ArrayList<List<List<Double>>>();
			
			//出貨與退貨(VSumWebmix1)
			List<List<List<Double>>>list_mix1=new ArrayList<List<List<Double>>>();
			
			//人員效能(VWebperson)
			List<List<List<Double>>>list_person=new ArrayList<List<List<Double>>>();
			
			//關務損耗(VWebgw)
			List<List<List<Double>>>list_gw=new ArrayList<List<List<Double>>>();
			
			//邊料不良重量分析(VWebside)
			List<List<List<Double>>>list_side=new ArrayList<List<List<Double>>>();
			
			//庫存(VWebstore)
			List<List<List<Double>>>list_store=new ArrayList<List<List<Double>>>();		
			
			//水電油(VWebwlo)
			List<List<List<Double>>>list_wlo=new ArrayList<List<List<Double>>>();
			
			//回頭料(Webbackfeed)
			List<List<List<Double>>>list_back=new ArrayList<List<List<Double>>>();
			
			//色料回收粉(vwebcost)
			List<List<List<Double>>>list_webcost=new ArrayList<List<List<Double>>>();
			
			/**
			 * big對象填充為null的屬性值
			 */
			BigDecimal big=new BigDecimal(0.00);
			for(int a=0;a<list_factCode.size();a++){//start for1
				/**
				 * 機台回轉數
				 */
				Double machine_a001=0.0;Double machine_a002=0.0;
				Double machine_a003=0.0;Double machine_a004=0.0;
				Double machine_a005=0.0;Double machine_a006=0.0;
				Double machine_a007=0.0;Double machine_a008=0.0;
				Double machine_a009=0.0;Double machine_a010=0.0;
				Double machine_a011=0.0;
				
				/**
				 * 生產與請款狀況
				 */
				Double inv_b001=0.0;Double inv_b002=0.0;
				Double inv_b003=0.0;Double inv_b004=0.0;
				
				/**
				 * 出貨與退貨
				 */
				Double mix1_c001=0.0;Double mix1_c002=0.0;
				Double mix1_c003=0.0;				
				/**
				 * 人員效能
				 */
				Double person_d001=0.0;Double person_d002=0.0;
				Double person_d003=0.0;Double person_d004=0.0;
				Double person_d005=0.0;Double person_d006=0.0;
				Double person_d007=0.0;Double person_d008=0.0;
				Double person_d009=0.0;Double person_d010=0.0;
				Double person_d011=0.0;Double person_d012=0.0;
				Double person_d013=0.0;Double person_d014=0.0;
				Double person_d015=0.0;Double person_d016=0.0;
				Double person_d017=0.0;Double person_d018=0.0;
				Double person_d019=0.0;Double person_d020=0.0;
				Double person_d021=0.0;Double person_d022=0.0;
				Double person_d023=0.0;Double person_d024=0.0;
				Double person_d025=0.0;				
				/**
				 * 關務損耗
				 */
				Double gw_e001=0.0;Double gw_e002=0.0;
				Double gw_e003=0.0;Double gw_e004=0.0;
				Double gw_e005=0.0;Double gw_e006=0.0;
				/**
				 * 邊料不良重量分析
				 */
				Double side_f001=0.0;Double side_f002=0.0;
				Double side_f003=0.0;Double side_f004=0.0;
				Double side_f005=0.0;Double side_f006=0.0;
				Double side_f007=0.0;Double side_f008=0.0;
				Double side_f009=0.0;Double side_f010=0.0;
				Double side_f011=0.0;Double side_f012=0.0;
				Double side_f013=0.0;
				/**
				 * 庫存
				 */
				Double store_g001=0.0;Double store_g002=0.0;
				Double store_g003=0.0;Double store_g004=0.0;
				Double store_g005=0.0;Double store_g006=0.0;
				Double store_g007=0.0;Double store_g008=0.0;
				Double store_g009=0.0;Double store_g010=0.0;
				Double store_g011=0.0;Double store_g012=0.0;
				Double store_g013=0.0;Double store_g014=0.0;
				Double store_g015=0.0;Double store_g016=0.0;
				Double store_g017=0.0;Double store_g018=0.0;								
				/**
				 * 水電油
				 */
				//第一季度
				Double wlo_h001=0.0;Double wlo_h002=0.0;
				Double wlo_h003=0.0;Double wlo_h004=0.0;
				Double wlo_h005=0.0;Double wlo_h006=0.0;
				Double wlo_h007=0.0;Double wlo_h008=0.0;
				Double wlo_h009=0.0;Double wlo_h010=0.0;
				Double wlo_h011=0.0;Double wlo_h012=0.0;
				Double wlo_h013=0.0;Double wlo_h014=0.0;
				Double wlo_h015=0.0;Double wlo_h016=0.0;
				Double wlo_h017=0.0;				
				/**
				 * 回頭料
				 */
				Double  back_i001=0.0;Double  back_i002=0.0;
				Double  back_i003=0.0;Double  back_i004=0.0;
				Double  back_i005=0.0;Double  back_i006=0.0;
				Double  back_i007=0.0;
				/**
				 * 色料回收粉
				 */
				Double webcost_j001=0.0;Double webcost_j002=0.0;
				Double webcost_j003=0.0;Double webcost_j004=0.0;
				Double webcost_j005=0.0;Double webcost_j006=0.0;
				Double webcost_j007=0.0;Double webcost_j008=0.0;
				Double webcost_j009=0.0;Double webcost_j010=0.0;
				Double webcost_j011=0.0;				
				/**
				 * 中間集合List<List<Double>>
				 */				
				//孔位數回轉數(VWebmachine)
				List<List<Double>>list_machine_2=new ArrayList<List<Double>>();
				//生產與請款狀況(VWeboutputinv)
				List<List<Double>>list_inv_2=new ArrayList<List<Double>>();				
				//出貨與退貨(VSumWebmix1)
				List<List<Double>>list_mix1_2=new ArrayList<List<Double>>();				
				//人員效能(VWebperson)
				List<List<Double>>list_person_2=new ArrayList<List<Double>>();				
				//關務損耗(VWebgw)
				List<List<Double>>list_gw_2=new ArrayList<List<Double>>();				
				//邊料不良重量分析(VWebside)
				List<List<Double>>list_side_2=new ArrayList<List<Double>>();				
				//庫存(VWebstore)
				List<List<Double>>list_store_2=new ArrayList<List<Double>>();						
				//水電油(VWebwlo)
				List<List<Double>>list_wlo_2=new ArrayList<List<Double>>();			
				//回頭料(Webbackfeed)
				List<List<Double>>list_back_2=new ArrayList<List<Double>>();
				//色料回收粉
				List<List<Double>>list_webcost_2=new ArrayList<List<Double>>();
				//每個factCode分別所對應的所有factNo和factName;
				
				//合計(与單元集合处在同一个级别)								
				List<Double>list_machine_total=new ArrayList<Double>();
				List<Double>list_inv_total=new ArrayList<Double>();				
				List<Double>list_mix1_total=new ArrayList<Double>();				
				List<Double>list_person_total=new ArrayList<Double>();				
				List<Double>list_gw_total=new ArrayList<Double>();				
				List<Double>list_side_total=new ArrayList<Double>();				
				List<Double>list_store_total=new ArrayList<Double>();						
				List<Double>list_wlo_total=new ArrayList<Double>();			
				List<Double>list_back_total=new ArrayList<Double>();
				List<Double>list_webcost_total=new ArrayList<Double>();
				/**
				 * 機台回轉數
				 */										
				Double machineEve_total_sumWorkday=0.0;Double machineEve_total_sumEverydemo=0.0;
				Double machineEve_total_sumStandarddemo=0.0;Double machineEve_total_sumActualdemo=0.0;
				Double machineEve_total_sumActualpairs=0.0;Double machineEve_total_avgbuttomweight=0.0;
				Double machineEve_total_avgbuttomweight2=0.0;Double machineEve_total_hole=0.0;					
				/**
				 * 生產與請款狀況
				 */								
				Double invEve_total_sumActualpairs=0.0;Double invEve_total_invcount=0.0;
				Double invEve_total_paypair=0.0;
				/**
				 * 出貨與退貨
				 */								
				Double mix1Eve_total_sumoutnum=0.0;Double mix1Eve_total_sumbacknum=0.0;
				/**
				 * 人員效能
				 */								
				Double personEve_total_personzg=0.0;Double personEve_total_personjg=0.0;
				Double personEve_total_timezg=0.0;Double personEve_total_timejg=0.0;
				Double personEve_total_addtimezg=0.0;Double personEve_total_addtimejg=0.0;
				Double personEve_total_leavenumzg=0.0;Double personEve_total_leavenumjg=0.0;
				Double personEve_total_wagezgUsd=0.0;Double personEve_total_wagejgUsd=0.0;
				Double personEve_total_addmoneyzg=0.0;Double personEve_total_addmoneyjg=0.0;
				Double personEve_total_sumStandarddemo=0.0;Double personEve_total_sumActualdemo=0.0;
				Double personEve_total_sumActualpairs=0.0;
				/**
				 * 關務損耗
				 */					
				Double gwEve_total_sumActualpairs=0.0;Double gwEve_total_sideweit=0.0;
				Double gwEve_total_badweit=0.0;Double gwEve_total_otherbadweight=0.0;
				Double gwEve_total_actlost=0.0;Double gwEve_total_avgbuttomweight2=0.0;
				Double gwEve_total_otherweight=0.0;
				/**
				 * 邊料不良重量分析
				 */					
				Double sideEve_total_sumActualpairs=0.0;Double sideEve_total_sideweit=0.0;
				Double sideEve_total_badcount=0.0;Double sideEve_total_badweit=0.0;
				Double sideEve_total_otherbadweight=0.0;Double sideEve_total_avgbuttomweight=0.0;
				Double sideEve_total_avgbuttomweight2=0.0;Double sideEve_total_avgprice=0.0;
				Double sideEve_total_otherweight=0.0;
				/**
				 * 庫存
				 */					
				Double storeEve_total_sumhostpairs=0.0;Double storeEve_total_sumsamplepairs=0.0;
				Double storeEve_total_invcount=0.0;Double storeEve_total_hostinvcount=0.0;
				Double storeEve_total_badcount=0.0;Double storeEve_total_lagStorenum=0.0;
				Double storeEve_total_lagNoliststorenum=0.0;Double storeEve_total_lagMakestorenum=0.0;
				Double storeEve_total_lagoutOutnum=0.0;Double storeEve_total_lagInnum=0.0;
				Double storeEve_total_lagInstorenum=0.0;Double storeEve_total_storenum=0.0;
				Double storeEve_total_noliststorenum=0.0;Double storeEve_total_makestorenum=0.0;
				Double storeEve_total_outnum=0.0;Double storeEve_total_innum=0.0;
				Double storeEve_total_minusnum=0.0;
				/**
				 * 水電油
				 */					
				Double wloEve_total_sumactualdemo=0.0;Double wloEve_total_waterton=0.0;
				Double wloEve_total_waterusd=0.0;Double wloEve_total_electricdu=0.0;
				Double wloEve_total_electricusd=0.0;Double wloEve_total_gaston=0.0;
				Double wloEve_total_gasusd=0.0;Double wloEve_total_oilton=0.0;
				Double wloEve_total_oilusd=0.0;
				/**
				 * 回頭料
				 */					
				Double  backEve_total_thickusd=0.0;Double  backEve_total_backfeed=0.0;
				Double  backEve_total_oilback=0.0;
				/**
				 * 色料回收粉
				 */
				Double webcostEve_total_sumActualdemo=0.0;Double webcostEve_total_gluestoremoney=0.0;  
				Double webcostEve_total_drugsused=0.0;Double webcostEve_total_leavemoney=0.0;
				Double webcostEve_total_whitenum=0.0;Double webcostEve_total_blacknum=0.0;
				Double webcostEve_total_gluenum=0.0;Double webcostEve_total_greynum=0.0;
				Double webcostEve_total_othernum=0.0;
				
				/**
				 * 注意:中間集合要先添加3個null,因為有"項目","細項","單位"
				 */
				for(int z=0;z<3;z++){
					list_machine_2.add(null);
					list_inv_2.add(null);
					list_mix1_2.add(null);
					list_person_2.add(null);
					list_gw_2.add(null);
					list_side_2.add(null);
					list_store_2.add(null);
					list_wlo_2.add(null);
					list_back_2.add(null);
					list_webcost_2.add(null);
				}
				String factCode=list_factCode.get(a);
				List<String>list_factNo=new ArrayList<String>();
				//有選擇廠別時
				if(list_factcode!=null&&list_factcode.size()>0){
					list_factNo=list_factno_all.get(a);
				//無選擇廠別時（默認全部）
				}else{
					List<WebFact>list_webfact=webFactSer.findFactByFactCode_showA(factCode);
					for(int c=0;c<list_webfact.size();c++){
						WebFact fact=list_webfact.get(c);
						String factNo=fact.getId().getFactNo();
						list_factNo.add(factNo);
					}
				}				
				for(int b=0;b<list_factNo.size();b++){//start for2
					/**
					 * 單元集合List<Double>
					 */				
					//孔位數回轉數(VWebmachine)
					List<Double>list_machine_3=new ArrayList<Double>();
					//生產與請款狀況(VWeboutputinv)
					List<Double>list_inv_3=new ArrayList<Double>();					
					//出貨與退貨(VSumWebmix1)
					List<Double>list_mix1_3=new ArrayList<Double>();					
					//人員效能(VWebperson)
					List<Double>list_person_3=new ArrayList<Double>();					
					//關務損耗(VWebgw)
					List<Double>list_gw_3=new ArrayList<Double>();					
					//邊料不良重量分析(VWebside)
					List<Double>list_side_3=new ArrayList<Double>();					
					//庫存(VWebstore)
					List<Double>list_store_3=new ArrayList<Double>();							
					//水電油(VWebwlo)
					List<Double>list_wlo_3=new ArrayList<Double>();					
					//回頭料(Webbackfeed)
					List<Double>list_back_3=new ArrayList<Double>();
					//色料回收粉(vwebcost)
					List<Double>list_webcost_3=new ArrayList<Double>();
					
					String factNo=list_factNo.get(b);
					
					//有版式表达式
					VWebmachine machine=vwebmachineser.findById(factNo, factCode, yymm);
					VWeboutputinv inv=vinvser.findById(factNo, factCode, yymm);
					SumWebYieldData mix1=sumydateSer.findById(factNo, factCode, yymm);
					VWebperson person=vpersonser.findById(factNo, factCode, yymm);
					VWebgw gw=vgwser.findById(factNo, factCode, yymm);
					VWebside side=vsideser.findById(factNo, factCode, yymm);
					VWebstore store=vstoreser.findById(factNo, factCode, yymm);
					VWebwlo wlo=vwloser.findById(factNo, factCode, yymm);
					Webbackfeed back=feedSer.findById(factNo,factCode,format.parse(yymm));
					VWebcost webcost=vwebcostSer.findById(factNo, factCode, yymm);
					
					//無算式表達式
					VWebmachineEve machine_eve=machineeveSer.findById(factNo, factCode, yymm);
					VWeboutputinvEve inv_eve=inveveSer.findById(factNo, factCode, yymm);
					SumWebYieldData mix1_eve=sumydateSer.findById(factNo, factCode, yymm);
					VWebpersonEve person_eve=personeveSer.findById(factNo, factCode, yymm);
					VWebgwEve gw_eve=gweveSer.findById(factNo, factCode, yymm);
					VWebsideEve side_eve=sideeveSer.findById(factNo, factCode, yymm);
					VWebstoreEve store_eve=storeeveSer.findById(factNo, factCode, yymm);
					VWebwloEve wlo_eve=wloeveSer.findById(factNo, factCode, yymm);
					Webbackfeed back_eve=feedSer.findById(factNo,factCode,format.parse(yymm));
					VWebcostEve webcost_eve=vwebcosteveSer.findById(factNo, factCode, yymm);
					
					//孔位數&回轉數(VWebmachine)					
					if(machine!=null){
						machine_a001=machine.getVWebmcA001();machine_a002=machine.getVWebmcA002().doubleValue();
						machine_a003=machine.getVWebmcA003().doubleValue();machine_a004=machine.getVWebmcA004().doubleValue();
						machine_a005=machine.getVWebmcA005().doubleValue();machine_a006=machine.getVWebmcA006();
						machine_a007=machine.getVWebmcA007();machine_a008=machine.getVWebmcA008().doubleValue();
						machine_a009=machine.getVWebmcA009().doubleValue();machine_a010=machine.getVWebmcA010().doubleValue();
						machine_a011=machine.getVWebmcA011().doubleValue();
						list_machine_3.add(machine_a001);list_machine_3.add(machine_a002);
						list_machine_3.add(machine_a003);list_machine_3.add(machine_a004);
						list_machine_3.add(machine_a005);list_machine_3.add(machine_a006);
						list_machine_3.add(machine_a007);list_machine_3.add(machine_a008);
						list_machine_3.add(machine_a009);list_machine_3.add(machine_a010);
						list_machine_3.add(machine_a011);
						list_machine_2.add(list_machine_3);
					}else{
						list_machine_2.add(null);
					}
					//合计
					if(machine_eve!=null){
						  machineEve_total_sumWorkday=machineEve_total_sumWorkday+machine_eve.getSumWorkday().doubleValue();
						  machineEve_total_sumEverydemo=machineEve_total_sumEverydemo+machine_eve.getSumEverydemo().doubleValue();
						  machineEve_total_sumStandarddemo=machineEve_total_sumStandarddemo+machine_eve.getSumStandarddemo().doubleValue(); 
						  machineEve_total_sumActualdemo=machineEve_total_sumActualdemo+machine_eve.getSumActualdemo().doubleValue();
						  machineEve_total_sumActualpairs=machineEve_total_sumActualpairs+machine_eve.getSumActualpairs().doubleValue(); 
						  machineEve_total_avgbuttomweight=machineEve_total_avgbuttomweight+machine_eve.getAvgbuttomweight();
						  machineEve_total_avgbuttomweight2=machineEve_total_avgbuttomweight2+machine_eve.getAvgbuttomweight2(); 
						  machineEve_total_hole=machineEve_total_hole+machine_eve.getHole();	
					}
					//生產與請款狀況(VWeboutputinv)
					
					if(inv!=null){
						if(inv.getVWebinvB001()==null){
							inv.setVWebinvB001(big);
						}
						inv_b001=inv.getVWebinvB001().doubleValue();
						inv_b002=inv.getVWebinvB002();
						inv_b003=inv.getVWebinvB003();
						inv_b004=inv.getVWebinvB004().doubleValue();
						list_inv_3.add(inv_b001);list_inv_3.add(inv_b002);
						list_inv_3.add(inv_b003);list_inv_3.add(inv_b004);
						list_inv_2.add(list_inv_3);
					}else{
						list_inv_2.add(null);
					}
					//合计
					if(inv_eve!=null){
						if(inv_eve.getSumActualpairs()==null){
							inv_eve.setSumActualpairs(big);
						}
						if(inv_eve.getPaypairs()==null){
							inv_eve.setPaypairs(big.doubleValue());
						}
						invEve_total_sumActualpairs=invEve_total_sumActualpairs+inv_eve.getSumActualpairs().doubleValue();
						invEve_total_invcount=invEve_total_invcount+inv_eve.getInvcount();
						invEve_total_paypair=invEve_total_paypair+inv_eve.getPaypairs();
					}
					
					//出貨與退貨(VSumWebmix1)					
					if(mix1!=null){
						if(mix1.getSumActualpairs()==null){
							mix1.setSumActualpairs(big);
						}
						if(mix1.getSumHostpairs()==null){
							mix1.setSumHostpairs(big);
						}
						if(mix1.getSumFactpairs()==null){
							mix1.setSumFactpairs(big);
						}
						if(mix1.getSumSamplepairs()==null){
							mix1.setSumSamplepairs(big);
						}
						if(mix1.getSumOutnum()==null){
							mix1.setSumOutnum(big);
						}
						if(mix1.getSumBacknum()==null){
							mix1.setSumBacknum(big);
						}
						mix1_c001=mix1.getSumOutnum().doubleValue();
						mix1_c002=mix1.getSumBacknum().doubleValue();
						mix1_c003=this.division(mix1.getSumBacknum().doubleValue(), mix1.getSumOutnum().doubleValue());
						list_mix1_3.add(mix1_c001);list_mix1_3.add(mix1_c002);
						list_mix1_3.add(mix1_c003);
						list_mix1_2.add(list_mix1_3);
					}else{
						list_mix1_2.add(null);
					}
					//合计
					if(mix1_eve!=null){
						if(mix1_eve.getSumOutnum()==null){
							mix1_eve.setSumOutnum(big);
						}
						if(mix1_eve.getSumBacknum()==null){
							mix1_eve.setSumBacknum(big);
						}
						mix1Eve_total_sumoutnum=mix1Eve_total_sumoutnum+mix1_eve.getSumOutnum().doubleValue();
						mix1Eve_total_sumbacknum=mix1Eve_total_sumbacknum+mix1_eve.getSumBacknum().doubleValue();
					}
					
					//人員效能(VWebperson)					
					if(person!=null){
						if(person.getVWebperD017()==null){
							person.setVWebperD017(big);
						}
						person_d001=person.getVWebperD001().doubleValue();person_d002=person.getVWebperD002().doubleValue();
						person_d003=person.getVWebperD003().doubleValue();person_d004=person.getVWebperD004();
						person_d005=person.getVWebperD005();person_d006=person.getVWebperD006().doubleValue();
						person_d007=person.getVWebperD007().doubleValue();person_d008=person.getVWebperD008().doubleValue();
						person_d009=person.getVWebperD009().doubleValue();person_d010=person.getVWebperD010().doubleValue();
						person_d011=person.getVWebperD011().doubleValue();person_d012=person.getVWebperD012().doubleValue();
						person_d013=person.getVWebperD013();person_d014=person.getVWebperD014();
						person_d015=person.getVWebperD015().doubleValue();person_d016=person.getVWebperD016().doubleValue();
						person_d017=person.getVWebperD017().doubleValue();person_d018=person.getVWebperD018().doubleValue();
						person_d019=person.getVWebperD019();person_d020=person.getVWebperD020().doubleValue();
						person_d021=person.getVWebperD021().doubleValue();person_d022=person.getVWebperD022().doubleValue();
						person_d023=person.getVWebperD023().doubleValue();person_d024=person.getVWebperD024().doubleValue();						
						person_d025=person.getVWebperD025().doubleValue();
						list_person_3.add(person_d001);list_person_3.add(person_d002);
						list_person_3.add(person_d003);list_person_3.add(person_d004);
						list_person_3.add(person_d005);list_person_3.add(person_d006);
						list_person_3.add(person_d007);list_person_3.add(person_d008);
						list_person_3.add(person_d009);list_person_3.add(person_d010);
						list_person_3.add(person_d011);list_person_3.add(person_d012);
						list_person_3.add(person_d013);list_person_3.add(person_d014);
						list_person_3.add(person_d015);list_person_3.add(person_d016);
						list_person_3.add(person_d017);list_person_3.add(person_d018);
						list_person_3.add(person_d019);list_person_3.add(person_d020);
						list_person_3.add(person_d021);list_person_3.add(person_d022);
						list_person_3.add(person_d023);list_person_3.add(person_d024);
						list_person_3.add(person_d025);
						list_person_2.add(list_person_3);
					}else{
						list_person_2.add(null);
					}
					//合计
					if(person_eve!=null){
						if(person_eve.getSumActualpairs()==null){
							person_eve.setSumActualpairs(big);
						}
						 personEve_total_personzg=personEve_total_personzg+person_eve.getPersonzg();
						 personEve_total_personjg=personEve_total_personjg+person_eve.getPersonjg();
						 personEve_total_timezg=personEve_total_timezg+person_eve.getTimezg();
						 personEve_total_timejg=personEve_total_timejg+person_eve.getTimejg();
						 personEve_total_addtimezg=personEve_total_addtimezg+person_eve.getAddtimezg();
						 personEve_total_addtimejg=personEve_total_addtimejg+person_eve.getAddtimejg();
						 personEve_total_leavenumzg=personEve_total_leavenumzg+person_eve.getLeavenumzg(); 
						 personEve_total_leavenumjg=personEve_total_leavenumjg+person_eve.getLeavenumjg();
						 personEve_total_wagezgUsd=personEve_total_wagezgUsd+person_eve.getWagezgUsd(); 
						 personEve_total_wagejgUsd=personEve_total_wagejgUsd+person_eve.getWagejgUsd();
						 personEve_total_addmoneyzg=personEve_total_addmoneyzg+person_eve.getAddmoneyzg();
						 personEve_total_addmoneyjg=personEve_total_addmoneyjg+person_eve.getAddmoneyjg();
						 personEve_total_sumStandarddemo=personEve_total_sumStandarddemo+person_eve.getSumStandarddemo().doubleValue();
						 personEve_total_sumActualdemo=personEve_total_sumActualdemo+person_eve.getSumActualdemo().doubleValue();
						 personEve_total_sumActualpairs=personEve_total_sumActualpairs+person_eve.getSumActualpairs().doubleValue();
					}
					
					//關務損耗(VWebgw)					
					if(gw!=null){
						gw_e001=gw.getVWebgwE001();gw_e002=gw.getVWebgwE002().doubleValue();
						gw_e003=gw.getVWebgwE003().doubleValue();gw_e004=gw.getVWebgwE004().doubleValue();
						gw_e005=gw.getVWebgwE005().doubleValue();gw_e006=gw.getVWebgwE006().doubleValue();
						list_gw_3.add(gw_e001);list_gw_3.add(gw_e002);
						list_gw_3.add(gw_e003);list_gw_3.add(gw_e004);
						list_gw_3.add(gw_e005);list_gw_3.add(gw_e006);
						list_gw_2.add(list_gw_3);
					}else{
						list_gw_2.add(null);
					}
					//合计
					if(gw_eve!=null){
						if(gw_eve.getSumActualpairs()==null){
							gw_eve.setSumActualpairs(big);
						}
						 gwEve_total_sumActualpairs=gwEve_total_sumActualpairs+gw_eve.getSumActualpairs().doubleValue(); 
						 gwEve_total_sideweit=gwEve_total_sideweit+gw_eve.getSideweit();
						 gwEve_total_badweit=gwEve_total_badweit+gw_eve.getBadweit(); 
						 gwEve_total_otherbadweight=gwEve_total_otherbadweight+gw_eve.getOtherbadweight();
						 gwEve_total_actlost=gwEve_total_actlost+gw_eve.getActlost(); 
						 gwEve_total_avgbuttomweight2=gwEve_total_avgbuttomweight2+gw_eve.getAvgbuttomweight2();
						 gwEve_total_otherweight=gwEve_total_otherweight+gw_eve.getOtherweight();
					}
					
					//邊料不良重量分析(VWebside)					
					if(side!=null){
						if(side.getVWebsideF003()==null){
							side.setVWebsideF003(big);
						}
						if(side.getVWebsideF004()==null){
							side.setVWebsideF004(big);
						}
						if(side.getVWebsideF008()==null){
							side.setVWebsideF008(big);
						}
						side_f001=side.getVWebsideF001();side_f002=side.getVWebsideF002();
						side_f003=side.getVWebsideF003().doubleValue();side_f004=side.getVWebsideF004().doubleValue();
						side_f005=side.getVWebsideF005().doubleValue();side_f006=side.getVWebsideF006();
						side_f007=side.getVWebsideF007();side_f008=side.getVWebsideF008().doubleValue();
						side_f009=side.getVWebsideF009().doubleValue();side_f010=side.getVWebsideF010().doubleValue();
						side_f011=side.getVWebsideF011().doubleValue();side_f012=side.getVWebsideF012().doubleValue();
						side_f013=side.getVWebsideF013().doubleValue();
						list_side_3.add(side_f001);list_side_3.add(side_f002);
						list_side_3.add(side_f003);list_side_3.add(side_f004);
						list_side_3.add(side_f005);list_side_3.add(side_f006);
						list_side_3.add(side_f007);list_side_3.add(side_f008);
						list_side_3.add(side_f009);list_side_3.add(side_f010);
						list_side_3.add(side_f011);list_side_3.add(side_f012);
						list_side_3.add(side_f013);
						list_side_2.add(list_side_3);
					}else{
						list_side_2.add(null);
					}
					//合计
					if(side_eve!=null){
						if(side_eve.getSumActualpairs()==null){
							side_eve.setSumActualpairs(big);
						}
						 sideEve_total_sumActualpairs=sideEve_total_sumActualpairs+side_eve.getSumActualpairs().doubleValue();
						 sideEve_total_sideweit=sideEve_total_sideweit+side_eve.getSideweit();
						 sideEve_total_badcount=sideEve_total_badcount+side_eve.getBadcount();
						 sideEve_total_badweit=sideEve_total_badweit+side_eve.getBadweit();
						 sideEve_total_otherbadweight=sideEve_total_otherbadweight+side_eve.getOtherbadweight(); 
						 sideEve_total_avgbuttomweight=sideEve_total_avgbuttomweight+side_eve.getAvgbuttomweight();
						 sideEve_total_avgbuttomweight2=sideEve_total_avgbuttomweight2+side_eve.getAvgbuttomweight2();
						 sideEve_total_avgprice=sideEve_total_avgprice+side_eve.getAvgprice();
						 sideEve_total_otherweight=sideEve_total_otherweight+side_eve.getOtherweight();
					}
					
					//庫存(VWebstore)				
					if(store!=null){						
						if(store.getVWebstoreG001()==null){							
							store.setVWebstoreG001(big);
						}
						if(store.getVWebstoreG002()==null){
							store.setVWebstoreG002(big);
						}
						if(store.getVWebstoreG003()==null){
							store.setVWebstoreG003(big);
						}
						if(store.getVWebstoreG004()==null){
							store.setVWebstoreG004(big);
						}
						if(store.getVWebstoreG005()==null){
							store.setVWebstoreG005(big);
						}
						if(store.getVWebstoreG006()==null){
							store.setVWebstoreG006(big);
						}
						if(store.getVWebstoreG007()==null){
							store.setVWebstoreG007(big);
						}
						if(store.getVWebstoreG014()==null){
							store.setVWebstoreG014(big);
						}
						if(store.getVWebstoreG017()==null){
							store.setVWebstoreG017(big);
						}
						store_g001=store.getVWebstoreG001().doubleValue();store_g002=store.getVWebstoreG002().doubleValue();
						store_g003=store.getVWebstoreG003().doubleValue();store_g004=store.getVWebstoreG004().doubleValue();
						store_g005=store.getVWebstoreG005().doubleValue();store_g006=store.getVWebstoreG006().doubleValue();
						store_g007=store.getVWebstoreG007().doubleValue();store_g008=store.getVWebstoreG008();
						store_g009=store.getVWebstoreG009();store_g010=store.getVWebstoreG010();
						store_g011=store.getVWebstoreG011();store_g012=store.getVWebstoreG012();
						store_g013=store.getVWebstoreG013().doubleValue();store_g014=store.getVWebstoreG014().doubleValue();
						store_g015=store.getVWebstoreG015().doubleValue();store_g016=store.getVWebstoreG016().doubleValue();
						store_g017=store.getVWebstoreG017().doubleValue();store_g018=store.getVWebstoreG018().doubleValue();
						list_store_3.add(store_g001);list_store_3.add(store_g002);
						list_store_3.add(store_g003);list_store_3.add(store_g004);
						list_store_3.add(store_g005);list_store_3.add(store_g006);
						list_store_3.add(store_g007);list_store_3.add(store_g008);
						list_store_3.add(store_g009);list_store_3.add(store_g010);
						list_store_3.add(store_g011);list_store_3.add(store_g012);
						list_store_3.add(store_g013);list_store_3.add(store_g014);
						list_store_3.add(store_g015);list_store_3.add(store_g016);
						list_store_3.add(store_g017);list_store_3.add(store_g018);
						list_store_2.add(list_store_3);
					}else{
						list_store_2.add(null);
					}
					//合计
					if(store_eve!=null){
						if(store_eve.getSumhostpairs()==null){
							store_eve.setSumhostpairs(big);
						}
						if(store_eve.getSumsamplepairs()==null){
							store_eve.setSumsamplepairs(big);
						}
						 storeEve_total_sumhostpairs=storeEve_total_sumhostpairs+store_eve.getSumhostpairs().doubleValue(); 
						 storeEve_total_sumsamplepairs=storeEve_total_sumsamplepairs+store_eve.getSumsamplepairs().doubleValue();
						 storeEve_total_invcount=storeEve_total_invcount+store_eve.getInvcount();
						 storeEve_total_hostinvcount=storeEve_total_hostinvcount+store_eve.getHostinvcount();
						 storeEve_total_badcount=storeEve_total_badcount+store_eve.getBadcount(); 
						 storeEve_total_lagStorenum=storeEve_total_lagStorenum+store_eve.getLagStorenum().doubleValue();
						 storeEve_total_lagNoliststorenum=storeEve_total_lagNoliststorenum+store_eve.getLagNoliststrorenum().doubleValue();
						 storeEve_total_lagMakestorenum=storeEve_total_lagMakestorenum+store_eve.getLagMakestrorenum().doubleValue();
						 storeEve_total_lagoutOutnum=storeEve_total_lagoutOutnum+store_eve.getLagOutnum().doubleValue();
						 storeEve_total_lagInnum=storeEve_total_lagInnum+store_eve.getLagInnum().doubleValue();
						 storeEve_total_lagInstorenum=storeEve_total_lagInstorenum+store_eve.getLagInstorenum().doubleValue(); 
						 storeEve_total_storenum=storeEve_total_storenum+store_eve.getStorenum();
						 storeEve_total_noliststorenum=storeEve_total_noliststorenum+store_eve.getNoliststrorenum(); 
						 storeEve_total_makestorenum=storeEve_total_makestorenum+store_eve.getMakestrorenum();
						 storeEve_total_outnum=storeEve_total_outnum+store_eve.getOutnum();
						 storeEve_total_innum=storeEve_total_innum+store_eve.getInnum();
						 storeEve_total_minusnum=storeEve_total_minusnum+store_eve.getMinusnum();
					}
					
					//水電油(VWebwlo)					
					if(wlo!=null){
						wlo_h001=wlo.getVWebwloH001().doubleValue();wlo_h002=wlo.getVWebwloH002();
						wlo_h003=wlo.getVWebwloH003().doubleValue();wlo_h004=wlo.getVWebwloH004();
						wlo_h005=wlo.getVWebwloH005().doubleValue();wlo_h006=wlo.getVWebwloH006();
						wlo_h007=wlo.getVWebwloH007().doubleValue();wlo_h008=wlo.getVWebwloH008();
						wlo_h009=wlo.getVWebwloH009().doubleValue();wlo_h010=wlo.getVWebwloH010();
						wlo_h011=wlo.getVWebwloH011().doubleValue();wlo_h012=wlo.getVWebwloH012();
						wlo_h013=wlo.getVWebwloH013().doubleValue();wlo_h014=wlo.getVWebwloH014();
						wlo_h015=wlo.getVWebwloH015().doubleValue();wlo_h016=wlo.getVWebwloH016();
						wlo_h017=wlo.getVWebwloH017().doubleValue();
						list_wlo_3.add(wlo_h001);list_wlo_3.add(wlo_h002);
						list_wlo_3.add(wlo_h003);list_wlo_3.add(wlo_h004);
						list_wlo_3.add(wlo_h005);list_wlo_3.add(wlo_h006);
						list_wlo_3.add(wlo_h007);list_wlo_3.add(wlo_h008);
						list_wlo_3.add(wlo_h009);list_wlo_3.add(wlo_h010);
						list_wlo_3.add(wlo_h011);list_wlo_3.add(wlo_h012);
						list_wlo_3.add(wlo_h013);list_wlo_3.add(wlo_h014);
						list_wlo_3.add(wlo_h015);list_wlo_3.add(wlo_h016);
						list_wlo_3.add(wlo_h017);
						list_wlo_2.add(list_wlo_3);
					}else{
						list_wlo_2.add(null);
					}
					//合计
					if(wlo_eve!=null){
						 wloEve_total_sumactualdemo=wloEve_total_sumactualdemo+wlo_eve.getSumactualdemo().doubleValue(); 
						 wloEve_total_waterton=wloEve_total_waterton+wlo_eve.getWaterton();
						 wloEve_total_waterusd=wloEve_total_waterusd+wlo_eve.getWaterusd(); 
						 wloEve_total_electricdu=wloEve_total_electricdu+wlo_eve.getElectricdu();
						 wloEve_total_electricusd=wloEve_total_electricusd+wlo_eve.getElectricusd(); 
						 wloEve_total_gaston=wloEve_total_gaston+wlo_eve.getGaston();
						 wloEve_total_gasusd=wloEve_total_gasusd+wlo_eve.getGasusd(); 
						 wloEve_total_oilton=wloEve_total_oilton+wlo_eve.getOilton();
						 wloEve_total_oilusd=wloEve_total_oilusd+wlo_eve.getOilusd();
					}
					
					//回頭料(Webbackfeed)					
					if(back!=null){
						back_i001=back.getThickused();
						back_i002=back.getBackfeed();
						back_i003=back.getBackfeed()/back.getThickused();
						back_i004=back.getOilback();
						back_i005=back.getOilback()/back.getThickused();
						back_i006=back.getBackfeed()+back.getOilback();
						back_i007=back.getBackfeed()/back.getThickused()+back.getOilback()/back.getThickused();
						list_back_3.add(back_i001);list_back_3.add(back_i002);
						list_back_3.add(back_i003);list_back_3.add(back_i004);
						list_back_3.add(back_i005);list_back_3.add(back_i006);
						list_back_3.add(back_i007);
						list_back_2.add(list_back_3);
					}else{
						list_back_2.add(null);
					}
					//合计
					if(back_eve!=null){
						  backEve_total_thickusd=backEve_total_thickusd+back_eve.getThickused(); 
						  backEve_total_backfeed=backEve_total_backfeed+back_eve.getBackfeed();
						  backEve_total_oilback=backEve_total_oilback+back_eve.getOilback();
					}
					
					//色料回收粉(vwebcost)					
					if(webcost!=null){
						webcost_j001=webcost.getVWebcostJ001();webcost_j002=webcost.getVWebcostJ002().doubleValue();
						webcost_j003=webcost.getVWebcostJ003();webcost_j004=webcost.getVWebcostJ004().doubleValue();
						webcost_j005=webcost.getVWebcostJ005();webcost_j006=webcost.getVWebcostJ006().doubleValue();
						webcost_j007=webcost.getVWebcostJ007();webcost_j008=webcost.getVWebcostJ008();
						webcost_j009=webcost.getVWebcostJ009();webcost_j010=webcost.getVWebcostJ010();
						webcost_j011=webcost.getVWebcostJ011();
						list_webcost_3.add(webcost_j001);list_webcost_3.add(webcost_j002);
						list_webcost_3.add(webcost_j003);list_webcost_3.add(webcost_j004);
						list_webcost_3.add(webcost_j005);list_webcost_3.add(webcost_j006);
						list_webcost_3.add(webcost_j007);list_webcost_3.add(webcost_j008);
						list_webcost_3.add(webcost_j009);list_webcost_3.add(webcost_j010);
						list_webcost_3.add(webcost_j011);
						list_webcost_2.add(list_webcost_3);
					}else{
						list_webcost_2.add(null);
					}
					//合计
					if(webcost_eve!=null){
						webcostEve_total_sumActualdemo=webcostEve_total_sumActualdemo+webcost_eve.getSumActualdemo().doubleValue();
					    webcostEve_total_gluestoremoney=webcostEve_total_gluestoremoney+webcost_eve.getGluestoremoney();
					    webcostEve_total_drugsused=webcostEve_total_drugsused+webcost_eve.getDrugsused();
					    webcostEve_total_leavemoney=webcostEve_total_leavemoney+webcost_eve.getLeavemoney();
					    webcostEve_total_whitenum=webcostEve_total_whitenum+webcost_eve.getWhitenum();
					    webcostEve_total_blacknum=webcostEve_total_blacknum+webcost_eve.getBlacknum();
					    webcostEve_total_gluenum=webcostEve_total_gluenum+webcost_eve.getGluenum();
					    webcostEve_total_greynum=webcostEve_total_greynum+webcost_eve.getGreynum();
					    webcostEve_total_othernum=webcostEve_total_othernum+webcost_eve.getOthernum();						  
					}
					//所有合计
					if(b==list_factNo.size()-1){
						//注意,machineEve_total_sumWorkday是求平均数的
						int num=list_factNo.size();
						list_machine_total=this.findResult_machine(1, machineEve_total_sumWorkday/num, machineEve_total_sumEverydemo,
								machineEve_total_sumStandarddemo, machineEve_total_sumActualdemo, machineEve_total_sumActualpairs,
								machineEve_total_avgbuttomweight, machineEve_total_avgbuttomweight2, machineEve_total_hole);
						list_inv_total=this.findResult_inv(1, invEve_total_sumActualpairs, invEve_total_invcount, invEve_total_paypair);
						list_mix1_total=this.findResult_mix1(1, mix1Eve_total_sumoutnum, mix1Eve_total_sumbacknum);
						list_person_total=this.findResult_person(1, personEve_total_personzg, personEve_total_personjg, personEve_total_timezg, personEve_total_timejg,
								personEve_total_addtimezg, personEve_total_addtimejg, personEve_total_leavenumzg, personEve_total_leavenumjg,
								personEve_total_wagezgUsd, personEve_total_wagejgUsd, personEve_total_addmoneyzg, personEve_total_addmoneyjg, personEve_total_sumStandarddemo,
								personEve_total_sumActualdemo, personEve_total_sumActualpairs);
						list_gw_total=this.findResult_gw(1, gwEve_total_sumActualpairs, gwEve_total_sideweit, gwEve_total_badweit,
								gwEve_total_otherbadweight, gwEve_total_actlost, gwEve_total_avgbuttomweight2, gwEve_total_otherweight);
						list_side_total=this.findResult_side(1, sideEve_total_sumActualpairs, sideEve_total_sideweit, sideEve_total_badcount,
								sideEve_total_badweit, sideEve_total_otherbadweight, sideEve_total_avgbuttomweight,
								sideEve_total_avgbuttomweight2, sideEve_total_avgprice, sideEve_total_otherweight);
						list_store_total=this.findResult_store(1, storeEve_total_sumhostpairs, storeEve_total_sumsamplepairs, storeEve_total_invcount,
								storeEve_total_hostinvcount, storeEve_total_badcount, storeEve_total_lagStorenum, storeEve_total_lagNoliststorenum, 
								storeEve_total_lagMakestorenum, storeEve_total_lagoutOutnum, storeEve_total_lagInnum, storeEve_total_lagInstorenum, storeEve_total_storenum,
								storeEve_total_noliststorenum, storeEve_total_makestorenum, storeEve_total_outnum, storeEve_total_innum, storeEve_total_minusnum);
						list_wlo_total=this.findResult_wlo(1, wloEve_total_sumactualdemo, wloEve_total_waterton, wloEve_total_waterusd, 
								wloEve_total_electricdu, wloEve_total_electricusd, 
								wloEve_total_gaston, wloEve_total_gasusd, wloEve_total_oilton, wloEve_total_oilusd);
						list_back_total=this.findResult_back(1, backEve_total_thickusd, backEve_total_backfeed, backEve_total_oilback);
						list_webcost_total=this.findResult_webcost(1, webcostEve_total_sumActualdemo, webcostEve_total_gluestoremoney, webcostEve_total_drugsused, webcostEve_total_leavemoney, webcostEve_total_whitenum,
								webcostEve_total_blacknum, webcostEve_total_gluenum, webcostEve_total_greynum, webcostEve_total_othernum);
						
						list_machine_2.add(list_machine_total);
						list_inv_2.add(list_inv_total);
						list_mix1_2.add(list_mix1_total);
						list_person_2.add(list_person_total);
						list_gw_2.add(list_gw_total);
						list_side_2.add(list_side_total);
						list_store_2.add(list_store_total);
						list_wlo_2.add(list_wlo_total);
						list_back_2.add(list_back_total);
						list_webcost_2.add(list_webcost_total);
					}
				}//end for2
				/**
				 * 最外層集合封裝
				 */
				list_machine.add(list_machine_2);
				list_inv.add(list_inv_2);
				list_mix1.add(list_mix1_2);
				list_person.add(list_person_2);
				list_gw.add(list_gw_2);
				list_side.add(list_side_2);
				list_store.add(list_store_2);
				list_wlo.add(list_wlo_2);
				list_back.add(list_back_2);
				list_webcost.add(list_webcost_2);
			}//end for1
			
			/**
			 * 填充內容
			 */
			//填充標題
			String title=yymm+"台灣損益會議各廠檢討項目各廠對比表----分型態";
			HSSFRow row_title=sheet.createRow(0);
			HSSFCell cell_title=row_title.createCell(0);
			cell_title.setCellValue(title);
			cell_title.setCellStyle(cs_title);
			CellRangeAddress cra_title=new CellRangeAddress(0,(short)0,0,(short)10);
			sheet.addMergedRegion(cra_title);
			for(int a=1;a<10;a++){
				HSSFCell cell=row_title.createCell(a);
				cell.setCellStyle(cs_title);
			}
			//填充廠別狀態和表頭
			for(int b=0;b<list_factCode.size();b++){//start for1
				/**
				 * 獲取中間數集
				 */
				List<List<Double>>list_machine2=list_machine.get(b);
				List<List<Double>>list_inv2=list_inv.get(b);
				List<List<Double>>list_mix1_2=list_mix1.get(b);
				List<List<Double>>list_person2=list_person.get(b);
				List<List<Double>>list_gw2=list_gw.get(b);
				List<List<Double>>list_side2=list_side.get(b);
				List<List<Double>>list_store2=list_store.get(b);
				List<List<Double>>list_wlo2=list_wlo.get(b);
				List<List<Double>>list_back2=list_back.get(b);
				List<List<Double>>list_webcost2=list_webcost.get(b);
				//廠別狀態
				String factCode=list_factCode.get(b);
				HSSFRow row_factcode=sheet.createRow(2+114*b);
				HSSFCell cell_factcode=row_factcode.createCell(0);
				cell_factcode.setCellValue(factCode);
				cell_factcode.setCellStyle(cs_noborder);
				//表頭
				List<String>list_factName=new ArrayList<String>();
				//獲取每個factcode對應的所有廠別
				List<String>list_factNo=list_factno_all.get(b);
				for(int a=0;a<list_factNo.size();a++){					
					String factNo=list_factNo.get(a);
					String factName=webFactSer.selByid(factNo);
					list_factName.add(factName);
				}
				list_factName.add(0,"項目");
				list_factName.add(1,"細項");
				list_factName.add(2,"單位");
				list_factName.add("合计");
				HSSFRow row_column=sheet.createRow(3+114*b);
				for(int c=0;c<list_factName.size();c++){//start for2
					/**
					 * 獲取單元數集
					 */
					List<Double>list_machine3=list_machine2.get(c);
					List<Double>list_inv3=list_inv2.get(c);
					List<Double>list_mix1_3=list_mix1_2.get(c);
					List<Double>list_person3=list_person2.get(c);
					List<Double>list_gw3=list_gw2.get(c);
					List<Double>list_side3=list_side2.get(c);
					List<Double>list_store3=list_store2.get(c);
					List<Double>list_wlo3=list_wlo2.get(c);
					List<Double>list_back3=list_back2.get(c);
					List<Double>list_webcost3=list_webcost2.get(c);
					HSSFCell cell_column=row_column.createCell(c);
					cell_column.setCellValue(list_factName.get(c));
					cell_column.setCellStyle(cs_column);
					switch(c){
					case 0:
						sheet.setColumnWidth(c, 3500);
						//孔位數機台回轉數
						HSSFRow row_machine=sheet.createRow(4+114*b);
						HSSFCell cell_machine=row_machine.createCell(c);
						cell_machine.setCellValue("孔位數機台回轉數");
						cell_machine.setCellStyle(cs);
						CellRangeAddress cra_machine=new CellRangeAddress(4+114*b,(short)(4+114*b+length1-1),c,(short)c);
						sheet.addMergedRegion(cra_machine);
						for(int d=1;d<length1;d++){
							HSSFRow row=sheet.createRow(4+114*b+d);
							HSSFCell cell=row.createCell(c);
							cell.setCellStyle(cs);
						}
						//生產與請款狀況
						HSSFRow row_inv=sheet.createRow(4+114*b+length1);
						HSSFCell cell_inv=row_inv.createCell(c);
						cell_inv.setCellValue("生產與請款狀況");
						cell_inv.setCellStyle(cs);
						CellRangeAddress cra_inv=new CellRangeAddress(4+114*b+length1,(short)(4+114*b+length1+length2-1),c,(short)c);
						sheet.addMergedRegion(cra_inv);
						for(int d=1;d<length2;d++){
							HSSFRow row=sheet.createRow(4+114*b+d+length1);
							HSSFCell cell=row.createCell(c);
							cell.setCellStyle(cs);
						}
						//出貨與退貨
						HSSFRow row_mix1=sheet.createRow(4+114*b+length1+length2);
						HSSFCell cell_mix1=row_mix1.createCell(c);
						cell_mix1.setCellValue("出貨與退貨");
						cell_mix1.setCellStyle(cs);
						CellRangeAddress cra_mix1=new CellRangeAddress(4+114*b+length1+length2,
								(short)(4+114*b+length1+length2+length3-1),c,(short)c);
						sheet.addMergedRegion(cra_mix1);
						for(int d=1;d<length3;d++){
							HSSFRow row=sheet.createRow(4+114*b+d+length1+length2);
							HSSFCell cell=row.createCell(c);
							cell.setCellStyle(cs);
						}
						//人員效能
						HSSFRow row_person=sheet.createRow(4+114*b+length1+length2+length3);
						HSSFCell cell_person=row_person.createCell(c);
						cell_person.setCellValue("人員效能");
						cell_person.setCellStyle(cs);
						CellRangeAddress cra_person=new CellRangeAddress(4+114*b+length1+length2+length3,
								(short)(4+114*b+length1+length2+length3+length4-1),c,(short)c);
						sheet.addMergedRegion(cra_person);
						for(int d=1;d<length4;d++){
							HSSFRow row=sheet.createRow(4+114*b+d+length1+length2+length3);
							HSSFCell cell=row.createCell(c);
							cell.setCellStyle(cs);
						}
						//關務損耗
						HSSFRow row_gw=sheet.createRow(4+114*b+length1+length2+length3+length4);
						HSSFCell cell_gw=row_gw.createCell(c);
						cell_gw.setCellValue("關務損耗");
						cell_gw.setCellStyle(cs);
						CellRangeAddress cra_gw=new CellRangeAddress(4+114*b+length1+length2+length3+length4,
								(short)(4+114*b+length1+length2+length3+length4+length5-1),c,(short)c);
						sheet.addMergedRegion(cra_gw);
						for(int d=1;d<length5;d++){
							HSSFRow row=sheet.createRow(4+114*b+d+length1+length2+length3+length4);
							HSSFCell cell=row.createCell(c);
							cell.setCellStyle(cs);
						}
						//邊料不良重量分析
						HSSFRow row_side=sheet.createRow(4+114*b+length1+length2+length3+length4+length5);
						HSSFCell cell_side=row_side.createCell(c);
						cell_side.setCellValue("邊料不良重量分析");
						cell_side.setCellStyle(cs);
						CellRangeAddress cra_side=new CellRangeAddress(4+114*b+length1+length2+length3+length4+length5,
								(short)(4+114*b+length1+length2+length3+length4+length5+length6-1),c,(short)c);
						sheet.addMergedRegion(cra_side);
						for(int d=1;d<length6;d++){
							HSSFRow row=sheet.createRow(4+114*b+d+length1+length2+length3+length4+length5);
							HSSFCell cell=row.createCell(c);
							cell.setCellStyle(cs);
						}
						//庫存
						HSSFRow row_store=sheet.createRow(4+114*b+length1+length2+length3+length4+length5+length6);
						HSSFCell cell_store=row_store.createCell(c);
						cell_store.setCellValue("庫存");
						cell_store.setCellStyle(cs);
						CellRangeAddress cra_store=new CellRangeAddress(4+114*b+length1+length2+length3+length4+length5+length6,
								(short)(4+114*b+length1+length2+length3+length4+length5+length6+length7-1),c,(short)c);
						sheet.addMergedRegion(cra_store);
						for(int d=1;d<length7;d++){
							HSSFRow row=sheet.createRow(4+114*b+d+length1+length2+length3+length4+length5+length6);
							HSSFCell cell=row.createCell(c);
							cell.setCellStyle(cs);
						}
						//水電油
						HSSFRow row_wlo=sheet.createRow(4+114*b+length1+length2+length3+length4+length5+length6+length7);
						HSSFCell cell_wlo=row_wlo.createCell(c);
						cell_wlo.setCellValue("水電油");
						cell_wlo.setCellStyle(cs);
						CellRangeAddress cra_wlo=new CellRangeAddress(4+114*b+length1+length2+length3+length4+length5+length6+length7,
								(short)(4+114*b+length1+length2+length3+length4+length5+length6+length7+length8-1),c,(short)c);
						sheet.addMergedRegion(cra_wlo);
						for(int d=1;d<length8;d++){
							HSSFRow row=sheet.createRow(4+114*b+d+length1+length2+length3+length4+length5+length6+length7);
							HSSFCell cell=row.createCell(c);
							cell.setCellStyle(cs);
						}
						//回頭料
						HSSFRow row_back=sheet.createRow(4+114*b+length1+length2+length3+length4+length5+length6+length7+length8);
						HSSFCell cell_back=row_back.createCell(c);
						cell_back.setCellValue("回頭料");
						cell_back.setCellStyle(cs);
						CellRangeAddress cra_back=new CellRangeAddress(4+114*b+length1+length2+length3+length4+length5+length6+length7+length8,
								(short)(4+114*b+length1+length2+length3+length4+length5+length6+length7+length8+length9-1),c,(short)c);
						sheet.addMergedRegion(cra_back);
						for(int d=1;d<length9;d++){
							HSSFRow row=sheet.createRow(4+114*b+d+length1+length2+length3+length4+length5+length6+length7+length8);
							HSSFCell cell=row.createCell(c);
							cell.setCellStyle(cs);
						}
						//色料回收粉
						HSSFRow row_webcost=sheet.createRow(4+114*b+length1+length2+length3+length4+length5+length6+length7+length8+length9);
						HSSFCell cell_webcost=row_webcost.createCell(c);
						cell_webcost.setCellValue("色料藥品/離型劑/回收粉");
						cell_webcost.setCellStyle(cs);
						CellRangeAddress cra_webcost=new CellRangeAddress(4+114*b+length1+length2+length3+length4+length5+length6+length7+length8+length9,
								(short)(4+114*b+length1+length2+length3+length4+length5+length6+length7+length8+length9+length10-1),c,(short)c);
						sheet.addMergedRegion(cra_webcost);
						for(int d=1;d<length10;d++){
							HSSFRow row=sheet.createRow(4+114*b+d+length1+length2+length3+length4+length5+length6+length7+length8+length9);
							HSSFCell cell=row.createCell(c);
							cell.setCellStyle(cs);
						}
						break;
					case 1:
						sheet.setColumnWidth(c, 4000);
						//孔位數機台回轉數
						for(int d=0;d<length1;d++){
							HSSFRow row_machine2=sheet.getRow(4+114*b+d);
							HSSFCell cell_machine2=row_machine2.createCell(c);
							cell_machine2.setCellValue(list_contentName_machine.get(d));
							cell_machine2.setCellStyle(cs);
						}						
                        //生產與請款狀況
						for(int d=0;d<length2;d++){
							HSSFRow row_inv2=sheet.getRow(4+114*b+d+length1);
							HSSFCell cell_inv2=row_inv2.createCell(c);
							cell_inv2.setCellValue(list_contentName_inv.get(d));
							cell_inv2.setCellStyle(cs);
						}
						//出貨與退貨
						for(int d=0;d<length3;d++){
							HSSFRow row_mix1_2=sheet.getRow(4+114*b+d+length1+length2);
							HSSFCell cell_mix1_2=row_mix1_2.createCell(c);
							cell_mix1_2.setCellValue(list_contentName_mix1.get(d));
							cell_mix1_2.setCellStyle(cs);
						}
						//人員效能
						for(int d=0;d<length4;d++){
							HSSFRow row_person2=sheet.getRow(4+114*b+d+length1+length2+length3);
							HSSFCell cell_person2=row_person2.createCell(c);
							cell_person2.setCellValue(list_contentName_person.get(d));
							cell_person2.setCellStyle(cs);
						}
						//關務損耗
						for(int d=0;d<length5;d++){
							HSSFRow row_gw2=sheet.getRow(4+114*b+d+length1+length2+length3+length4);
							HSSFCell cell_gw2=row_gw2.createCell(c);
							cell_gw2.setCellValue(list_contentName_gw.get(d));
							cell_gw2.setCellStyle(cs);
						}
						//邊料不良重量分析
						for(int d=0;d<length6;d++){
							HSSFRow row_side2=sheet.getRow(4+114*b+d+length1+length2+length3+length4+length5);
							HSSFCell cell_side2=row_side2.createCell(c);
							cell_side2.setCellValue(list_contentName_side.get(d));
							cell_side2.setCellStyle(cs);
						}
						//庫存
						for(int d=0;d<length7;d++){
							HSSFRow row_store2=sheet.getRow(4+114*b+d+length1+length2+length3+length4+length5+length6);
							HSSFCell cell_store2=row_store2.createCell(c);
							cell_store2.setCellValue(list_contentName_store.get(d));
							cell_store2.setCellStyle(cs);
						}
						//水電油
						for(int d=0;d<length8;d++){
							HSSFRow row_wlo2=sheet.getRow(4+114*b+d+length1+length2+length3+length4+length5+length6+length7);
							HSSFCell cell_wlo2=row_wlo2.createCell(c);
							cell_wlo2.setCellValue(list_contentName_wlo.get(d));
							cell_wlo2.setCellStyle(cs);
						}
						//回頭料
						for(int d=0;d<length9;d++){
							HSSFRow row_back2=sheet.getRow(4+114*b+d+length1+length2+length3+length4+length5+length6+length7+length8);
							HSSFCell cell_back2=row_back2.createCell(c);
							cell_back2.setCellValue(list_contentName_back.get(d));
							cell_back2.setCellStyle(cs);
						}
						//色料回收粉
						for(int d=0;d<length10;d++){
							HSSFRow row_webcost2=sheet.getRow(4+114*b+d+length1+length2+length3+length4+length5+length6+length7+length8+length9);
							HSSFCell cell_webcost2=row_webcost2.createCell(c);
							cell_webcost2.setCellValue(list_contentName_webcost.get(d));
							cell_webcost2.setCellStyle(cs);
						}
						break;
					case 2:
                        //孔位數機台回轉數
						for(int d=0;d<length1;d++){
							HSSFRow row_machine2=sheet.getRow(4+114*b+d);
							HSSFCell cell_machine2=row_machine2.createCell(c);
							cell_machine2.setCellValue(list_unit_machine.get(d));
							cell_machine2.setCellStyle(cs);
						}
                        //生產與請款狀況
						for(int d=0;d<length2;d++){
							HSSFRow row_inv2=sheet.getRow(4+114*b+d+length1);
							HSSFCell cell_inv2=row_inv2.createCell(c);
							cell_inv2.setCellValue(list_unit_inv.get(d));
							cell_inv2.setCellStyle(cs);
						}
						//出貨與退貨
						for(int d=0;d<length3;d++){
							HSSFRow row_mix1_2=sheet.getRow(4+114*b+d+length1+length2);
							HSSFCell cell_mix1_2=row_mix1_2.createCell(c);
							cell_mix1_2.setCellValue(list_unit_mix1.get(d));
							cell_mix1_2.setCellStyle(cs);
						}
						//人員效能
						for(int d=0;d<length4;d++){
							HSSFRow row_person2=sheet.getRow(4+114*b+d+length1+length2+length3);
							HSSFCell cell_person2=row_person2.createCell(c);
							cell_person2.setCellValue(list_unit_person.get(d));
							cell_person2.setCellStyle(cs);
						}
						//關務損耗
						for(int d=0;d<length5;d++){
							HSSFRow row_gw2=sheet.getRow(4+114*b+d+length1+length2+length3+length4);
							HSSFCell cell_gw2=row_gw2.createCell(c);
							cell_gw2.setCellValue(list_unit_gw.get(d));
							cell_gw2.setCellStyle(cs);
						}
						//邊料不良重量分析
						for(int d=0;d<length6;d++){
							HSSFRow row_side2=sheet.getRow(4+114*b+d+length1+length2+length3+length4+length5);
							HSSFCell cell_side2=row_side2.createCell(c);
							cell_side2.setCellValue(list_unit_side.get(d));
							cell_side2.setCellStyle(cs);
						}
						//庫存
						for(int d=0;d<length7;d++){
							HSSFRow row_store2=sheet.getRow(4+114*b+d+length1+length2+length3+length4+length5+length6);
							HSSFCell cell_store2=row_store2.createCell(c);
							cell_store2.setCellValue(list_unit_store.get(d));
							cell_store2.setCellStyle(cs);
						}
						//水電油
						for(int d=0;d<length8;d++){
							HSSFRow row_wlo2=sheet.getRow(4+114*b+d+length1+length2+length3+length4+length5+length6+length7);
							HSSFCell cell_wlo2=row_wlo2.createCell(c);
							cell_wlo2.setCellValue(list_unit_wlo.get(d));
							cell_wlo2.setCellStyle(cs);
						}
						//回頭料
						for(int d=0;d<length9;d++){
							HSSFRow row_back2=sheet.getRow(4+114*b+d+length1+length2+length3+length4+length5+length6+length7+length8);
							HSSFCell cell_back2=row_back2.createCell(c);
							cell_back2.setCellValue(list_unit_back.get(d));
							cell_back2.setCellStyle(cs);
						}
						//色料回收粉
						for(int d=0;d<length10;d++){
							HSSFRow row_webcost2=sheet.getRow(4+114*b+d+length1+length2+length3+length4+length5+length6+length7+length8+length9);
							HSSFCell cell_webcost2=row_webcost2.createCell(c);
							cell_webcost2.setCellValue(list_unit_webcost.get(d));
							cell_webcost2.setCellStyle(cs);
						}
						break;
					default:
						//孔位數機台回轉數
						sheet.setColumnWidth(c, 3500);
						HSSFCellStyle cs_temp=wb.createCellStyle();
						for(int d=0;d<length1;d++){
							if(d==0||d==2||d==3||d==7||d==8){
								cs_temp=cs_poi;
							}else if(d==1||d==5||d==6||d==9){
								cs_temp=cs_poi1;
							}else if(d==4){
								cs_temp=cs_percent;                                                                                                   //20150423
							}else{
								cs_temp=cs_percent2;
							}
							HSSFRow row_machine_3=sheet.getRow(4+114*b+d);
							HSSFCell cell_machine_3=row_machine_3.createCell(c);
							if(list_machine3!=null){
								cell_machine_3.setCellValue(list_machine3.get(d));
							}else{
								cell_machine_3.setCellValue("無數據");
							}
							cell_machine_3.setCellStyle(cs_temp);
						}
						//生產與請款狀況
						for(int d=0;d<length2;d++){
							if(d==0||d==1){
								cs_temp=cs_poi1;
							}else{
								cs_temp=cs_poi2;
							}
							HSSFRow row_inv_3=sheet.getRow(4+114*b+d+length1);
							HSSFCell cell_inv_3=row_inv_3.createCell(c);
							if(list_inv3!=null){
								cell_inv_3.setCellValue(list_inv3.get(d));
							}else{
								cell_inv_3.setCellValue("無數據");
							}
							cell_inv_3.setCellStyle(cs_temp);
						}
						//出貨與退貨
						for(int d=0;d<length3;d++){
							if(d==2){
								cs_temp=cs_percent2;
							}else{
								cs_temp=cs_poi1;
							}
							HSSFRow row_mix1_3=sheet.getRow(4+114*b+d+length1+length2);
							HSSFCell cell_mix1_3=row_mix1_3.createCell(c);
							if(list_mix1_3!=null){
								cell_mix1_3.setCellValue(list_mix1_3.get(d));
							}else{
								cell_mix1_3.setCellValue("無數據");
							}
							cell_mix1_3.setCellStyle(cs_temp);
						}
						//人員效能
						for(int d=0;d<length4;d++){
							if(d==2||d==10||d==11||d==22||d==23){
								cs_temp=cs_percent2;
							}else if(d==0||d==1){
								cs_temp=cs_poi1;
							}else if(d==3||d==4||d==5||d==7||d==8){
								cs_temp=cs_poi;
							}else{
								cs_temp=cs_poi2;
							}
							HSSFRow row_person_3=sheet.getRow(4+114*b+d+length1+length2+length3);
							HSSFCell cell_person_3=row_person_3.createCell(c);
							if(list_person3!=null){
								cell_person_3.setCellValue(list_person3.get(d));
							}else{
								cell_person_3.setCellValue("無數據");
							}
							cell_person_3.setCellStyle(cs_temp);
						}
						//關務損耗
						for(int d=0;d<length5;d++){
							if(d==3||d==5){
								cs_temp=cs_percent2;
							}else{
								cs_temp=cs_poi2;
							}
							HSSFRow row_gw_3=sheet.getRow(4+114*b+d+length1+length2+length3+length4);
							HSSFCell cell_gw_3=row_gw_3.createCell(c);
							if(list_gw3!=null){
								cell_gw_3.setCellValue(list_gw3.get(d));
							}else{
								cell_gw_3.setCellValue("無數據");
							}
							cell_gw_3.setCellStyle(cs_temp);
						}
						//邊料不良重量分析
						for(int d=0;d<length6;d++){
							if(d==3||d==7){
								cs_temp=cs_percent2;
							}else{
								cs_temp=cs_poi2;
							}
							HSSFRow row_side_3=sheet.getRow(4+114*b+d+length1+length2+length3+length4+length5);
							HSSFCell cell_side_3=row_side_3.createCell(c);
							if(list_side3!=null){
								cell_side_3.setCellValue(list_side3.get(d));
							}else{
								cell_side_3.setCellValue("無數據");
							}
							cell_side_3.setCellStyle(cs_temp);
						}
						//庫存
						for(int d=0;d<length7;d++){
							HSSFRow row_store_3=sheet.getRow(4+114*b+d+length1+length2+length3+length4+length5+length6);
							HSSFCell cell_store_3=row_store_3.createCell(c);
							if(list_store3!=null){
								cell_store_3.setCellValue(list_store3.get(d));
							}else{
								cell_store_3.setCellValue("無數據");
							}
							cell_store_3.setCellStyle(cs_poi1);
						}
						//水電油
						for(int d=0;d<length8;d++){
							if(d==0){
								cs_temp=cs_poi1;
							}else if(d==1||d==5){
								cs_temp=cs_poi;
							}else if(d==2||d==4||d==6||d==8||d==10||d==12||d==14||d==16){
								cs_temp=cs_poi4;
							}else{
								cs_temp=cs_poi2;
							}
							HSSFRow row_store_3=sheet.getRow(4+114*b+d+length1+length2+length3+length4+length5+length6+length7);
							HSSFCell cell_store_3=row_store_3.createCell(c);
							if(list_store3!=null){
								cell_store_3.setCellValue(list_wlo3.get(d));
							}else{
								cell_store_3.setCellValue("無數據");
							}
							cell_store_3.setCellStyle(cs);
						}
						//回頭料
						for(int d=0;d<length9;d++){
							if(d==2||d==4||d==6){
								cs_temp=cs_percent2;
							}else{
								cs_temp=cs_poi2;
							}
							HSSFRow row_back_3=sheet.getRow(4+114*b+d+length1+length2+length3+length4+length5+length6+length7+length8);
							HSSFCell cell_back_3=row_back_3.createCell(c);
							if(list_back3!=null){
								cell_back_3.setCellValue(list_back3.get(d));
							}else{
								cell_back_3.setCellValue("無數據");
							}
							cell_back_3.setCellStyle(cs_temp);
						}
						//色料回收粉
						for(int d=0;d<length10;d++){
							if(d==1||d==3||d==5){
								cs_temp=cs_poi4;
							}else{
								cs_temp=cs_poi2;
							}
							HSSFRow row_webcost_3=sheet.getRow(4+114*b+d+length1+length2+length3+length4+length5+length6+length7+length8+length9);
							HSSFCell cell_webcost_3=row_webcost_3.createCell(c);
							if(list_webcost3!=null){
								cell_webcost_3.setCellValue(list_webcost3.get(d));
							}else{
								cell_webcost_3.setCellValue("無數據");
							}
							cell_webcost_3.setCellStyle(cs_temp);
						}
						break;
							
					
					}
					
					
				}//end for2
			}//end for1
		ServletOutputStream os=response.getOutputStream();
		response.setContentType("application/vnd.ms-excel");
		title=title+".xls";
		int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");
		String fileName="";
		if(msie>0){
			fileName=java.net.URLEncoder.encode(title,"utf-8");
		}else{
			fileName=new String(title.getBytes("utf-8"),"iso-8859-1");
		}
		response.setHeader("Content-disposition","attachment;filename="+fileName);
		//OutputStream os=new FileOutputStream("d:/vwebmachine_gw.xls");
		wb.write(os);
		os.close();
	}
	
	/**
	 * xlsx格式
	 * @throws ParseException
	 * @throws IOException
	 */
	public void print_tw_xlsx() throws ParseException, IOException{
		//建立模板
		XSSFWorkbook wb=new XSSFWorkbook();
		XSSFSheet sheet=wb.createSheet();
		/**
		 * 各項樣式
		 */
		//基本樣式
		XSSFCellStyle cs=wb.createCellStyle();
		cs.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		//無邊框樣式
		XSSFCellStyle cs_noborder=wb.createCellStyle();
		cs_noborder.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_noborder.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		//標題樣式
		XSSFCellStyle cs_title=wb.createCellStyle();
		XSSFFont font_title=wb.createFont();
		font_title.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		font_title.setFontHeightInPoints((short)20);
		cs_title.setFont(font_title);
		cs_title.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_title.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_title.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_title.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_title.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_title.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		//表頭樣式
		XSSFCellStyle cs_column=wb.createCellStyle();
		XSSFFont font_column=wb.createFont();
		font_column.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		font_column.setFontHeightInPoints((short)12);
		cs_column.setFont(font_column);
		cs_column.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_column.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_column.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_column.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_column.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_column.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_column.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		cs_column.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		//數字格式
		XSSFDataFormat format_hf=wb.createDataFormat();
		XSSFCellStyle cs_percent2=wb.createCellStyle();
		cs_percent2.setDataFormat(format_hf.getFormat("0.00%"));
		cs_percent2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_percent2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_percent2.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_percent2.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_percent2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_percent2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		
		XSSFCellStyle cs_percent1=wb.createCellStyle();
		cs_percent1.setDataFormat(format_hf.getFormat("0.0%"));
		cs_percent1.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_percent1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_percent1.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_percent1.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_percent1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_percent1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		
		XSSFCellStyle cs_percent=wb.createCellStyle();
		cs_percent.setDataFormat(format_hf.getFormat("0%"));
		cs_percent.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_percent.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_percent.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		
		XSSFCellStyle cs_poi2=wb.createCellStyle();
		cs_poi2.setDataFormat(format_hf.getFormat("#,###,0.00"));
		cs_poi2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_poi2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_poi2.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		
		XSSFCellStyle cs_poi4=wb.createCellStyle();
		cs_poi4.setDataFormat(format_hf.getFormat("#,###,0.0000"));
		cs_poi4.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_poi4.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_poi4.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		
		XSSFCellStyle cs_poi=wb.createCellStyle();
		cs_poi.setDataFormat(format_hf.getFormat("#,###,0"));
		cs_poi.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_poi.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_poi.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_poi.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_poi.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_poi.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		
		XSSFCellStyle cs_poi1=wb.createCellStyle();
		cs_poi1.setDataFormat(format_hf.getFormat("#,###,0.0"));
		cs_poi1.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_poi1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_poi1.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_poi1.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_poi1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_poi1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		//日期格式
		SimpleDateFormat format=new SimpleDateFormat("yyyyMM");
		/**		
		 * 數據源
		 * (1)機台回轉數
		 */
		//數據內容名稱和單位
		List<String>list_contentName_machine=new ArrayList<String>();
		List<String>list_unit_machine=new ArrayList<String>();
		list_contentName_machine.add("全廠孔位數");list_unit_machine.add("孔");
		list_contentName_machine.add("上班天數");list_unit_machine.add("天");
		list_contentName_machine.add("總上模數");list_unit_machine.add("模");
		list_contentName_machine.add("平均一天上模數");list_unit_machine.add("模/天");
		list_contentName_machine.add("機台利用率");list_unit_machine.add("%");
		list_contentName_machine.add("平均料重");list_unit_machine.add("g/雙");
		list_contentName_machine.add("平均淨重");list_unit_machine.add("g/雙");
		list_contentName_machine.add("標準回轉數");list_unit_machine.add("回");
		list_contentName_machine.add("實際回轉數");list_unit_machine.add("回");
		list_contentName_machine.add("回轉數差異");list_unit_machine.add("回");
		list_contentName_machine.add("達成率(%)");list_unit_machine.add("%");
		
		/**
		 * 數據源
		 * (2)生產與請款狀況
		 */
		        //數據內容名稱和單位
				List<String>list_contentName_inv=new ArrayList<String>();
				List<String>list_unit_inv=new ArrayList<String>();
				list_contentName_inv.add("生產雙數");list_unit_inv.add("雙");
				list_contentName_inv.add("請款雙數");list_unit_inv.add("雙");
				list_contentName_inv.add("銷貨收入");list_unit_inv.add("USD");
				list_contentName_inv.add("平均單價");list_unit_inv.add("USD/雙");
																	
			/**
			* 數據源
			* (3)出貨與退貨
			*/
			//數據內容名稱和單位
			List<String>list_contentName_mix1=new ArrayList<String>();
			List<String>list_unit_mix1=new ArrayList<String>();
			list_contentName_mix1.add("出貨數");list_unit_mix1.add("雙");
			list_contentName_mix1.add("退貨數");list_unit_mix1.add("雙");
			list_contentName_mix1.add("退貨率");list_unit_mix1.add("%");
			
			/**
			* 數據源
			* (4)人員效能
			*/
			//數據內容名稱和單位
			List<String>list_contentName_person=new ArrayList<String>();
			List<String>list_unit_person=new ArrayList<String>();
			list_contentName_person.add("標準產量");list_unit_person.add("模");
			list_contentName_person.add("實際產量");list_unit_person.add("模");
			list_contentName_person.add("達成率(%)");list_unit_person.add("%");
			list_contentName_person.add("直工人數");list_unit_person.add("人");
			list_contentName_person.add("間工人數");list_unit_person.add("人");
			list_contentName_person.add("全廠總人數");list_unit_person.add("人");
			list_contentName_person.add("直间比");list_unit_person.add("--");
			list_contentName_person.add("直工人均产能");list_unit_person.add("模/人");
			list_contentName_person.add("全廠人均产能");list_unit_person.add("模/人");
			list_contentName_person.add("全廠人均时产能");list_unit_person.add("模/H");
			list_contentName_person.add("直工離職率(%)");list_unit_person.add("%");
			list_contentName_person.add("全廠離職率(%)");list_unit_person.add("%");
			list_contentName_person.add("直接薪資(含加班費+獎金)");list_unit_person.add("USD");
			list_contentName_person.add("間接薪資(含加班費+獎金)");list_unit_person.add("USD");
			list_contentName_person.add("總薪資(含加班費+獎金)");list_unit_person.add("USD");
			list_contentName_person.add("總工時");list_unit_person.add("H");
			list_contentName_person.add("每雙薪資單耗");list_unit_person.add("USD/雙");
			list_contentName_person.add("直工加班費");list_unit_person.add("USD");
			list_contentName_person.add("間工加班費");list_unit_person.add("USD");
			list_contentName_person.add("總加班費");list_unit_person.add("USD");
			list_contentName_person.add("直工人均薪資");list_unit_person.add("USD/人");
			list_contentName_person.add("間工人均薪資");list_unit_person.add("USD/人");
			list_contentName_person.add("直工占薪資比例");list_unit_person.add("%");
			list_contentName_person.add("間工占薪資比例");list_unit_person.add("%");
			list_contentName_person.add("全廠人均薪資");list_unit_person.add("USD/人");
			
			/**
			* 數據源
			* (5)關務損耗
			*/
			//數據內容名稱和單位
			List<String>list_contentName_gw=new ArrayList<String>();
			List<String>list_unit_gw=new ArrayList<String>();
			list_contentName_gw.add("當月總耗用原料");list_unit_gw.add("kg");
			list_contentName_gw.add("當月標准用量");list_unit_gw.add("kg");
			list_contentName_gw.add("總損耗量");list_unit_gw.add("kg");
			list_contentName_gw.add("總損耗率");list_unit_gw.add("%");
			list_contentName_gw.add("無形損耗");list_unit_gw.add("kg");
			list_contentName_gw.add("無形損耗率");list_unit_gw.add("%");
			
			/**
			* 數據源
			* (6)邊粒不良重量分析
			*/
			//數據內容名稱和單位
			List<String>list_contentName_side=new ArrayList<String>();
			List<String>list_unit_side=new ArrayList<String>();
			list_contentName_side.add("粗坯平均單價");list_unit_side.add("USD/KG");
			list_contentName_side.add("邊料(kg)");list_unit_side.add("KG");
			list_contentName_side.add("平均邊料重(g)");list_unit_side.add("G/雙");
			list_contentName_side.add("邊料%");list_unit_side.add("%");
			list_contentName_side.add("邊料金額");list_unit_side.add("USD");
			list_contentName_side.add("不良品雙數");list_unit_side.add("雙");
			list_contentName_side.add("不良品重量");list_unit_side.add("KG");
			list_contentName_side.add("不良重量不良率");list_unit_side.add("%");
			list_contentName_side.add("不良品金額");list_unit_side.add("USD");
			list_contentName_side.add("其它報廢重量");list_unit_side.add("KG");
			list_contentName_side.add("其它報廢金額");list_unit_side.add("USD");
			list_contentName_side.add("總報廢重量");list_unit_side.add("KG");
			list_contentName_side.add("總報廢金額");list_unit_side.add("USD");
			
			/**
			* 數據源
			* (7)庫存
			*/
			//數據內容名稱和單位
			List<String>list_contentName_store=new ArrayList<String>();
			List<String>list_unit_store=new ArrayList<String>();
			list_contentName_store.add("上月成倉庫存數");list_unit_store.add("雙");
			list_contentName_store.add("上月無訂單庫存");list_unit_store.add("雙");
			list_contentName_store.add("上月整理庫存");list_unit_store.add("雙");
			list_contentName_store.add("上月已出未請數");list_unit_store.add("雙");
			list_contentName_store.add("上月已請未出貨");list_unit_store.add("雙");
			list_contentName_store.add("前倉入庫折算後可請款生產雙數");list_unit_store.add("雙");
			list_contentName_store.add("小計-本月可請款數(A)");list_unit_store.add("雙");
			list_contentName_store.add("本月成倉庫存數");list_unit_store.add("雙");
			list_contentName_store.add("本月無訂單庫存");list_unit_store.add("雙");
			list_contentName_store.add("本月整理庫存");list_unit_store.add("雙");
			list_contentName_store.add("本月已出未請數");list_unit_store.add("雙");
			list_contentName_store.add("本月已請未出貨");list_unit_store.add("雙");
			list_contentName_store.add("小計-本月未請款數(B)");list_unit_store.add("雙");
			list_contentName_store.add("本月應請款數(A-B)");list_unit_store.add("雙");
			list_contentName_store.add("本月實際請款數");list_unit_store.add("雙");
			list_contentName_store.add("生產與請款差異數");list_unit_store.add("雙");
			list_contentName_store.add("廠客補、樣品未請款數");list_unit_store.add("雙");
			list_contentName_store.add("無形差異");list_unit_store.add("雙");			
			
			/**
			* 數據源
			* (8)水電油
			*/
			//數據內容名稱和單位
			List<String>list_contentName_wlo=new ArrayList<String>();
			List<String>list_unit_wlo=new ArrayList<String>();
			list_contentName_wlo.add("生產模數");list_unit_wlo.add("雙");
			list_contentName_wlo.add("水用量");list_unit_wlo.add("噸");
			list_contentName_wlo.add("用水單耗");list_unit_wlo.add("噸/模");//2(4位小數)
			list_contentName_wlo.add("用水金額");list_unit_wlo.add("USD");
			list_contentName_wlo.add("用水金額單耗(模)");list_unit_wlo.add("USD/模");//4(4位小數)
			list_contentName_wlo.add("用電量");list_unit_wlo.add("度");
			list_contentName_wlo.add("用電單耗(模)");list_unit_wlo.add("度/模");//6(2位沾染)
			list_contentName_wlo.add("用電金額(USD)");list_unit_wlo.add("USD");
			list_contentName_wlo.add("用電金額單耗(模)");list_unit_wlo.add("USD/模");//8(2位小數)
			list_contentName_wlo.add("蒸氣用量(T)");list_unit_wlo.add("噸");
			list_contentName_wlo.add("用蒸氣單耗(模)");list_unit_wlo.add("噸/模");//10(4位小數)
			list_contentName_wlo.add("用蒸氣金額(USD)");list_unit_wlo.add("USD");
			list_contentName_wlo.add("用蒸氣金額單耗(模)");list_unit_wlo.add("USD/模");//12(4位小數)
			list_contentName_wlo.add("柴油用量(公升)");list_unit_wlo.add("公升");
			list_contentName_wlo.add("用油單耗(模)");list_unit_wlo.add("公升/模");//14(4位小數)
			list_contentName_wlo.add("柴油金額(USD)");list_unit_wlo.add("USD");
			list_contentName_wlo.add("用油金額單耗(模)");list_unit_wlo.add("USD/模");//16(4位小數)									
			
			/**
			* 數據源
			* (9)回頭料
			*/
			//數據內容名稱和單位
			List<String>list_contentName_back=new ArrayList<String>();
			List<String>list_unit_back=new ArrayList<String>();
			list_contentName_back.add("粗坯用量");list_unit_back.add("kg");
			list_contentName_back.add("回頭料重量");list_unit_back.add("kg");
			list_contentName_back.add("回頭料%");list_unit_back.add("%");
			list_contentName_back.add("油壓退料重量");list_unit_back.add("kg");
			list_contentName_back.add("油壓退料%");list_unit_back.add("%");
			list_contentName_back.add("合計重量");list_unit_back.add("kg");
			list_contentName_back.add("回頭率%");list_unit_back.add("%");
			
			/**
			 * 數據源
			 * (9)色料回收粉
			 */
			//數據內容名稱和單位
			List<String>list_contentName_webcost=new ArrayList<String>();
			List<String>list_unit_webcost=new ArrayList<String>();
			list_contentName_webcost.add("色料用量");list_unit_webcost.add("KG");
			list_contentName_webcost.add("色料單耗");list_unit_webcost.add("g/模");
			list_contentName_webcost.add("藥品用量");list_unit_webcost.add("KG");
			list_contentName_webcost.add("藥品單耗");list_unit_webcost.add("g/模");
			list_contentName_webcost.add("離型劑金額");list_unit_webcost.add("USD");
			list_contentName_webcost.add("離型劑單耗");list_unit_webcost.add("USD/模");
			list_contentName_webcost.add("白色回收粉");list_unit_webcost.add("KG");
			list_contentName_webcost.add("黑色回收粉");list_unit_webcost.add("KG");
			list_contentName_webcost.add("生膠回收粉");list_unit_webcost.add("KG");
			list_contentName_webcost.add("灰色回收粉");list_unit_webcost.add("KG");
			list_contentName_webcost.add("其它回收粉");list_unit_webcost.add("KG");
			
			int length1=list_contentName_machine.size();
			int length2=list_contentName_inv.size();
			int length3=list_contentName_mix1.size();
			int length4=list_contentName_person.size();
			int length5=list_contentName_gw.size();
			int length6=list_contentName_side.size();
			int length7=list_contentName_store.size();
			int length8=list_contentName_wlo.size();
			int length9=list_contentName_back.size();
			int length10=list_contentName_webcost.size();
			
			//所有廠別狀態
			List<String>list_factCode=new ArrayList<String>();
			List<List<String>>list_factno_all=new ArrayList<List<String>>();//用於封裝各個factcode的廠別(如果有選擇廠別)
			//如果有選擇廠別狀態,則取所選 的,否則取全部廠別狀態
			if(list_factcode!=null&&list_factcode.size()>0){				
				list_factCode=list_factcode;
				//所選的廠別按factcode封裝起來
				for(int i=0;i<list_factcode.size();i++){
					List<String>list_factno_one=new ArrayList<String>();//用於封裝一個factcode的廠別
					String factcode=list_factcode.get(i);
					for(int j=0;j<list_factno.size();j++){
						String factno=list_factno.get(j);
						String[]factnos=factno.split("_");
						if(factnos[0].equals(factcode)){
							list_factno_one.add(factnos[1]);
						}
					}
					list_factno_all.add(list_factno_one);
				}
			}else{
				List<Object[]>list_objs=webFactSer.findAllFactCode2_showA();
				for(int i=0;i<list_objs.size();i++){
					Object[]obj=list_objs.get(i);
					String fact_code=obj[0].toString();
					list_factCode.add(fact_code);
				}
				for(int i=0;i<list_factCode.size();i++){
					String factcode=list_factCode.get(i);
					List<String>list_factno_one=new ArrayList<String>();//用於封裝一個factcode的廠別
					List<WebFact>list_webfact=webFactSer.findFactByFactCode_showA(factcode);
					for(int c=0;c<list_webfact.size();c++){
						WebFact fact=list_webfact.get(c);
						String factNo=fact.getId().getFactNo();
						list_factno_one.add(factNo);
					}
					list_factno_all.add(list_factno_one);
				}
			}			
			//yymm="201410";
			/**
			 * list_factCode   list_factno_all
			 * 開始循環封閉數據
			 */
			/**
			 * 最外層集合List<List<List<Double>>>
			 */				
			//孔位數回轉數(VWebmachine)
			List<List<List<Double>>>list_machine=new ArrayList<List<List<Double>>>();
			//生產與請款狀況(VWeboutputinv)
			List<List<List<Double>>>list_inv=new ArrayList<List<List<Double>>>();
			
			//出貨與退貨(VSumWebmix1)
			List<List<List<Double>>>list_mix1=new ArrayList<List<List<Double>>>();
			
			//人員效能(VWebperson)
			List<List<List<Double>>>list_person=new ArrayList<List<List<Double>>>();
			
			//關務損耗(VWebgw)
			List<List<List<Double>>>list_gw=new ArrayList<List<List<Double>>>();
			
			//邊料不良重量分析(VWebside)
			List<List<List<Double>>>list_side=new ArrayList<List<List<Double>>>();
			
			//庫存(VWebstore)
			List<List<List<Double>>>list_store=new ArrayList<List<List<Double>>>();		
			
			//水電油(VWebwlo)
			List<List<List<Double>>>list_wlo=new ArrayList<List<List<Double>>>();
			
			//回頭料(Webbackfeed)
			List<List<List<Double>>>list_back=new ArrayList<List<List<Double>>>();
			
			//色料回收粉(vwebcost)
			List<List<List<Double>>>list_webcost=new ArrayList<List<List<Double>>>();
			
			/**
			 * big對象填充為null的屬性值
			 */
			BigDecimal big=new BigDecimal(0.00);
			for(int a=0;a<list_factCode.size();a++){//start for1
				/**
				 * 機台回轉數
				 */
				Double machine_a001=0.0;Double machine_a002=0.0;
				Double machine_a003=0.0;Double machine_a004=0.0;
				Double machine_a005=0.0;Double machine_a006=0.0;
				Double machine_a007=0.0;Double machine_a008=0.0;
				Double machine_a009=0.0;Double machine_a010=0.0;
				Double machine_a011=0.0;
				
				/**
				 * 生產與請款狀況
				 */
				Double inv_b001=0.0;Double inv_b002=0.0;
				Double inv_b003=0.0;Double inv_b004=0.0;
				
				/**
				 * 出貨與退貨
				 */
				Double mix1_c001=0.0;Double mix1_c002=0.0;
				Double mix1_c003=0.0;				
				/**
				 * 人員效能
				 */
				Double person_d001=0.0;Double person_d002=0.0;
				Double person_d003=0.0;Double person_d004=0.0;
				Double person_d005=0.0;Double person_d006=0.0;
				Double person_d007=0.0;Double person_d008=0.0;
				Double person_d009=0.0;Double person_d010=0.0;
				Double person_d011=0.0;Double person_d012=0.0;
				Double person_d013=0.0;Double person_d014=0.0;
				Double person_d015=0.0;Double person_d016=0.0;
				Double person_d017=0.0;Double person_d018=0.0;
				Double person_d019=0.0;Double person_d020=0.0;
				Double person_d021=0.0;Double person_d022=0.0;
				Double person_d023=0.0;Double person_d024=0.0;
				Double person_d025=0.0;				
				/**
				 * 關務損耗
				 */
				Double gw_e001=0.0;Double gw_e002=0.0;
				Double gw_e003=0.0;Double gw_e004=0.0;
				Double gw_e005=0.0;Double gw_e006=0.0;
				/**
				 * 邊料不良重量分析
				 */
				Double side_f001=0.0;Double side_f002=0.0;
				Double side_f003=0.0;Double side_f004=0.0;
				Double side_f005=0.0;Double side_f006=0.0;
				Double side_f007=0.0;Double side_f008=0.0;
				Double side_f009=0.0;Double side_f010=0.0;
				Double side_f011=0.0;Double side_f012=0.0;
				Double side_f013=0.0;
				/**
				 * 庫存
				 */
				Double store_g001=0.0;Double store_g002=0.0;
				Double store_g003=0.0;Double store_g004=0.0;
				Double store_g005=0.0;Double store_g006=0.0;
				Double store_g007=0.0;Double store_g008=0.0;
				Double store_g009=0.0;Double store_g010=0.0;
				Double store_g011=0.0;Double store_g012=0.0;
				Double store_g013=0.0;Double store_g014=0.0;
				Double store_g015=0.0;Double store_g016=0.0;
				Double store_g017=0.0;Double store_g018=0.0;								
				/**
				 * 水電油
				 */
				Double wlo_h001=0.0;Double wlo_h002=0.0;
				Double wlo_h003=0.0;Double wlo_h004=0.0;
				Double wlo_h005=0.0;Double wlo_h006=0.0;
				Double wlo_h007=0.0;Double wlo_h008=0.0;
				Double wlo_h009=0.0;Double wlo_h010=0.0;
				Double wlo_h011=0.0;Double wlo_h012=0.0;
				Double wlo_h013=0.0;Double wlo_h014=0.0;
				Double wlo_h015=0.0;Double wlo_h016=0.0;
				Double wlo_h017=0.0;				
				/**
				 * 回頭料
				 */
				Double  back_i001=0.0;Double  back_i002=0.0;
				Double  back_i003=0.0;Double  back_i004=0.0;
				Double  back_i005=0.0;Double  back_i006=0.0;
				Double  back_i007=0.0;
				/**
				 * 色料回收粉
				 */
				Double webcost_j001=0.0;Double webcost_j002=0.0;
				Double webcost_j003=0.0;Double webcost_j004=0.0;
				Double webcost_j005=0.0;Double webcost_j006=0.0;
				Double webcost_j007=0.0;Double webcost_j008=0.0;
				Double webcost_j009=0.0;Double webcost_j010=0.0;
				Double webcost_j011=0.0;
				
				/**
				 * 中間集合List<List<Double>>
				 */				
				//孔位數回轉數(VWebmachine)
				List<List<Double>>list_machine_2=new ArrayList<List<Double>>();
				//生產與請款狀況(VWeboutputinv)
				List<List<Double>>list_inv_2=new ArrayList<List<Double>>();				
				//出貨與退貨(VSumWebmix1)
				List<List<Double>>list_mix1_2=new ArrayList<List<Double>>();				
				//人員效能(VWebperson)
				List<List<Double>>list_person_2=new ArrayList<List<Double>>();				
				//關務損耗(VWebgw)
				List<List<Double>>list_gw_2=new ArrayList<List<Double>>();				
				//邊料不良重量分析(VWebside)
				List<List<Double>>list_side_2=new ArrayList<List<Double>>();				
				//庫存(VWebstore)
				List<List<Double>>list_store_2=new ArrayList<List<Double>>();						
				//水電油(VWebwlo)
				List<List<Double>>list_wlo_2=new ArrayList<List<Double>>();			
				//回頭料(Webbackfeed)
				List<List<Double>>list_back_2=new ArrayList<List<Double>>();
				//色料回收粉(vwebcost)
				List<List<Double>>list_webcost_2=new ArrayList<List<Double>>();
				//每個factCode分別所對應的所有factNo和factName;
				
				//合計(与單元集合处在同一个级别)								
				List<Double>list_machine_total=new ArrayList<Double>();
				List<Double>list_inv_total=new ArrayList<Double>();				
				List<Double>list_mix1_total=new ArrayList<Double>();				
				List<Double>list_person_total=new ArrayList<Double>();				
				List<Double>list_gw_total=new ArrayList<Double>();				
				List<Double>list_side_total=new ArrayList<Double>();				
				List<Double>list_store_total=new ArrayList<Double>();						
				List<Double>list_wlo_total=new ArrayList<Double>();			
				List<Double>list_back_total=new ArrayList<Double>();
				List<Double>list_webcost_total=new ArrayList<Double>();
				/**
				 * 機台回轉數
				 */										
				Double machineEve_total_sumWorkday=0.0;Double machineEve_total_sumEverydemo=0.0;
				Double machineEve_total_sumStandarddemo=0.0;Double machineEve_total_sumActualdemo=0.0;
				Double machineEve_total_sumActualpairs=0.0;Double machineEve_total_avgbuttomweight=0.0;
				Double machineEve_total_avgbuttomweight2=0.0;Double machineEve_total_hole=0.0;					
				/**
				 * 生產與請款狀況
				 */								
				Double invEve_total_sumActualpairs=0.0;Double invEve_total_invcount=0.0;
				Double invEve_total_paypair=0.0;
				/**
				 * 出貨與退貨
				 */								
				Double mix1Eve_total_sumoutnum=0.0;Double mix1Eve_total_sumbacknum=0.0;
				/**
				 * 人員效能
				 */								
				Double personEve_total_personzg=0.0;Double personEve_total_personjg=0.0;
				Double personEve_total_timezg=0.0;Double personEve_total_timejg=0.0;
				Double personEve_total_addtimezg=0.0;Double personEve_total_addtimejg=0.0;
				Double personEve_total_leavenumzg=0.0;Double personEve_total_leavenumjg=0.0;
				Double personEve_total_wagezgUsd=0.0;Double personEve_total_wagejgUsd=0.0;
				Double personEve_total_addmoneyzg=0.0;Double personEve_total_addmoneyjg=0.0;
				Double personEve_total_sumStandarddemo=0.0;Double personEve_total_sumActualdemo=0.0;
				Double personEve_total_sumActualpairs=0.0;
				/**
				 * 關務損耗
				 */					
				Double gwEve_total_sumActualpairs=0.0;Double gwEve_total_sideweit=0.0;
				Double gwEve_total_badweit=0.0;Double gwEve_total_otherbadweight=0.0;
				Double gwEve_total_actlost=0.0;Double gwEve_total_avgbuttomweight2=0.0;
				Double gwEve_total_otherweight=0.0;
				/**
				 * 邊料不良重量分析
				 */					
				Double sideEve_total_sumActualpairs=0.0;Double sideEve_total_sideweit=0.0;
				Double sideEve_total_badcount=0.0;Double sideEve_total_badweit=0.0;
				Double sideEve_total_otherbadweight=0.0;Double sideEve_total_avgbuttomweight=0.0;
				Double sideEve_total_avgbuttomweight2=0.0;Double sideEve_total_avgprice=0.0;
				Double sideEve_total_otherweight=0.0;
				/**
				 * 庫存
				 */					
				Double storeEve_total_sumhostpairs=0.0;Double storeEve_total_sumsamplepairs=0.0;
				Double storeEve_total_invcount=0.0;Double storeEve_total_hostinvcount=0.0;
				Double storeEve_total_badcount=0.0;Double storeEve_total_lagStorenum=0.0;
				Double storeEve_total_lagNoliststorenum=0.0;Double storeEve_total_lagMakestorenum=0.0;
				Double storeEve_total_lagoutOutnum=0.0;Double storeEve_total_lagInnum=0.0;
				Double storeEve_total_lagInstorenum=0.0;Double storeEve_total_storenum=0.0;
				Double storeEve_total_noliststorenum=0.0;Double storeEve_total_makestorenum=0.0;
				Double storeEve_total_outnum=0.0;Double storeEve_total_innum=0.0;
				Double storeEve_total_minusnum=0.0;
				/**
				 * 水電油
				 */					
				Double wloEve_total_sumactualdemo=0.0;Double wloEve_total_waterton=0.0;
				Double wloEve_total_waterusd=0.0;Double wloEve_total_electricdu=0.0;
				Double wloEve_total_electricusd=0.0;Double wloEve_total_gaston=0.0;
				Double wloEve_total_gasusd=0.0;Double wloEve_total_oilton=0.0;
				Double wloEve_total_oilusd=0.0;
				/**
				 * 回頭料
				 */					
				Double  backEve_total_thickusd=0.0;Double  backEve_total_backfeed=0.0;
				Double  backEve_total_oilback=0.0;
				/**
				 * 色料回收粉
				 */
				Double webcostEve_total_sumActualdemo=0.0;Double webcostEve_total_gluestoremoney=0.0;  
				Double webcostEve_total_drugsused=0.0;Double webcostEve_total_leavemoney=0.0;
				Double webcostEve_total_whitenum=0.0;Double webcostEve_total_blacknum=0.0;
				Double webcostEve_total_gluenum=0.0;Double webcostEve_total_greynum=0.0;
				Double webcostEve_total_othernum=0.0;
				
				/**
				 * 注意:中間集合要先添加3個null,因為有"項目","細項","單位"
				 */
				for(int z=0;z<3;z++){
					list_machine_2.add(null);
					list_inv_2.add(null);
					list_mix1_2.add(null);
					list_person_2.add(null);
					list_gw_2.add(null);
					list_side_2.add(null);
					list_store_2.add(null);
					list_wlo_2.add(null);
					list_back_2.add(null);
					list_webcost_2.add(null);
				}
				String factCode=list_factCode.get(a);
				List<String>list_factNo=new ArrayList<String>();
				list_factNo=list_factno_all.get(a);
				/*//有選擇廠別時
				if(list_factcode!=null&&list_factcode.size()>0){
					list_factNo=list_factno_all.get(a);
				//無選擇廠別時（默認全部）
				}else{
					List<WebFact>list_webfact=webFactSer.findFactByFactCode(factCode);
					for(int c=0;c<list_webfact.size();c++){
						WebFact fact=list_webfact.get(c);
						String factNo=fact.getId().getFactNo();
						list_factNo.add(factNo);
					}
				}	*/			
				for(int b=0;b<list_factNo.size();b++){//start for2
					/**
					 * 單元集合List<Double>
					 */				
					//孔位數回轉數(VWebmachine)
					List<Double>list_machine_3=new ArrayList<Double>();
					//生產與請款狀況(VWeboutputinv)
					List<Double>list_inv_3=new ArrayList<Double>();					
					//出貨與退貨(VSumWebmix1)
					List<Double>list_mix1_3=new ArrayList<Double>();					
					//人員效能(VWebperson)
					List<Double>list_person_3=new ArrayList<Double>();					
					//關務損耗(VWebgw)
					List<Double>list_gw_3=new ArrayList<Double>();					
					//邊料不良重量分析(VWebside)
					List<Double>list_side_3=new ArrayList<Double>();					
					//庫存(VWebstore)
					List<Double>list_store_3=new ArrayList<Double>();							
					//水電油(VWebwlo)
					List<Double>list_wlo_3=new ArrayList<Double>();					
					//回頭料(Webbackfeed)
					List<Double>list_back_3=new ArrayList<Double>();
					//色料回收粉
					List<Double>list_webcost_3=new ArrayList<Double>();
					String factNo=list_factNo.get(b);
					
					//有版式表达式
					VWebmachine machine=vwebmachineser.findById(factNo, factCode, yymm);
					VWeboutputinv inv=vinvser.findById(factNo, factCode, yymm);
					SumWebYieldData mix1=sumydateSer.findById(factNo, factCode, yymm);
					VWebperson person=vpersonser.findById(factNo, factCode, yymm);
					VWebgw gw=vgwser.findById(factNo, factCode, yymm);
					VWebside side=vsideser.findById(factNo, factCode, yymm);
					VWebstore store=vstoreser.findById(factNo, factCode, yymm);
					VWebwlo wlo=vwloser.findById(factNo, factCode, yymm);
					Webbackfeed back=feedSer.findById(factNo,factCode,format.parse(yymm));
					VWebcost webcost=vwebcostSer.findById(factNo, factCode, yymm);
					
					//無算式表達式
					VWebmachineEve machine_eve=machineeveSer.findById(factNo, factCode, yymm);
					VWeboutputinvEve inv_eve=inveveSer.findById(factNo, factCode, yymm);
					SumWebYieldData mix1_eve=sumydateSer.findById(factNo, factCode, yymm);
					VWebpersonEve person_eve=personeveSer.findById(factNo, factCode, yymm);
					VWebgwEve gw_eve=gweveSer.findById(factNo, factCode, yymm);
					VWebsideEve side_eve=sideeveSer.findById(factNo, factCode, yymm);
					VWebstoreEve store_eve=storeeveSer.findById(factNo, factCode, yymm);
					VWebwloEve wlo_eve=wloeveSer.findById(factNo, factCode, yymm);
					Webbackfeed back_eve=feedSer.findById(factNo,factCode,format.parse(yymm));
					VWebcostEve webcost_eve=vwebcosteveSer.findById(factNo, factCode, yymm);
					
					//孔位數&回轉數(VWebmachine)					
					if(machine!=null){
						machine_a001=machine.getVWebmcA001();machine_a002=machine.getVWebmcA002().doubleValue();
						machine_a003=machine.getVWebmcA003().doubleValue();machine_a004=machine.getVWebmcA004().doubleValue();
						machine_a005=machine.getVWebmcA005().doubleValue();machine_a006=machine.getVWebmcA006();
						machine_a007=machine.getVWebmcA007();machine_a008=machine.getVWebmcA008().doubleValue();
						machine_a009=machine.getVWebmcA009().doubleValue();machine_a010=machine.getVWebmcA010().doubleValue();
						machine_a011=machine.getVWebmcA011().doubleValue();
						list_machine_3.add(machine_a001);list_machine_3.add(machine_a002);
						list_machine_3.add(machine_a003);list_machine_3.add(machine_a004);
						list_machine_3.add(machine_a005);list_machine_3.add(machine_a006);
						list_machine_3.add(machine_a007);list_machine_3.add(machine_a008);
						list_machine_3.add(machine_a009);list_machine_3.add(machine_a010);
						list_machine_3.add(machine_a011);
						list_machine_2.add(list_machine_3);
					}else{
						list_machine_2.add(null);
					}
					//合计
					if(machine_eve!=null){
						  machineEve_total_sumWorkday=machineEve_total_sumWorkday+machine_eve.getSumWorkday().doubleValue();
						  machineEve_total_sumEverydemo=machineEve_total_sumEverydemo+machine_eve.getSumEverydemo().doubleValue();
						  machineEve_total_sumStandarddemo=machineEve_total_sumStandarddemo+machine_eve.getSumStandarddemo().doubleValue(); 
						  machineEve_total_sumActualdemo=machineEve_total_sumActualdemo+machine_eve.getSumActualdemo().doubleValue();
						  machineEve_total_sumActualpairs=machineEve_total_sumActualpairs+machine_eve.getSumActualpairs().doubleValue(); 
						  machineEve_total_avgbuttomweight=machineEve_total_avgbuttomweight+machine_eve.getAvgbuttomweight();
						  machineEve_total_avgbuttomweight2=machineEve_total_avgbuttomweight2+machine_eve.getAvgbuttomweight2(); 
						  machineEve_total_hole=machineEve_total_hole+machine_eve.getHole();	
					}
					//生產與請款狀況(VWeboutputinv)
					
					if(inv!=null){
						if(inv.getVWebinvB001()==null){
							inv.setVWebinvB001(big);
						}
						inv_b001=inv.getVWebinvB001().doubleValue();
						inv_b002=inv.getVWebinvB002();
						inv_b003=inv.getVWebinvB003();
						inv_b004=inv.getVWebinvB004().doubleValue();
						list_inv_3.add(inv_b001);list_inv_3.add(inv_b002);
						list_inv_3.add(inv_b003);list_inv_3.add(inv_b004);
						list_inv_2.add(list_inv_3);
					}else{
						list_inv_2.add(null);
					}
					//合计
					if(inv_eve!=null){
						if(inv_eve.getSumActualpairs()==null){
							inv_eve.setSumActualpairs(big);
						}
						if(inv_eve.getPaypairs()==null){
							inv_eve.setPaypairs(big.doubleValue());
						}
						invEve_total_sumActualpairs=invEve_total_sumActualpairs+inv_eve.getSumActualpairs().doubleValue();
						invEve_total_invcount=invEve_total_invcount+inv_eve.getInvcount();
						invEve_total_paypair=invEve_total_paypair+inv_eve.getPaypairs();
					}
					
					//出貨與退貨(VSumWebmix1)					
					if(mix1!=null){
						if(mix1.getSumActualpairs()==null){
							mix1.setSumActualpairs(big);
						}
						if(mix1.getSumHostpairs()==null){
							mix1.setSumHostpairs(big);
						}
						if(mix1.getSumFactpairs()==null){
							mix1.setSumFactpairs(big);
						}
						if(mix1.getSumSamplepairs()==null){
							mix1.setSumSamplepairs(big);
						}
						if(mix1.getSumOutnum()==null){
							mix1.setSumOutnum(big);
						}
						if(mix1.getSumBacknum()==null){
							mix1.setSumBacknum(big);
						}
						mix1_c001=mix1.getSumOutnum().doubleValue();
						mix1_c002=mix1.getSumBacknum().doubleValue();
						mix1_c003=this.division(mix1.getSumBacknum().doubleValue(), mix1.getSumOutnum().doubleValue());
						list_mix1_3.add(mix1_c001);list_mix1_3.add(mix1_c002);
						list_mix1_3.add(mix1_c003);
						list_mix1_2.add(list_mix1_3);
					}else{
						list_mix1_2.add(null);
					}
					//合计
					if(mix1_eve!=null){
						if(mix1_eve.getSumOutnum()==null){
							mix1_eve.setSumOutnum(big);
						}
						if(mix1_eve.getSumBacknum()==null){
							mix1_eve.setSumBacknum(big);
						}
						mix1Eve_total_sumoutnum=mix1Eve_total_sumoutnum+mix1_eve.getSumOutnum().doubleValue();
						mix1Eve_total_sumbacknum=mix1Eve_total_sumbacknum+mix1_eve.getSumBacknum().doubleValue();
					}
					
					//人員效能(VWebperson)					
					if(person!=null){
						if(person.getVWebperD017()==null){
							person.setVWebperD017(big);
						}
						person_d001=person.getVWebperD001().doubleValue();person_d002=person.getVWebperD002().doubleValue();
						person_d003=person.getVWebperD003().doubleValue();person_d004=person.getVWebperD004();
						person_d005=person.getVWebperD005();person_d006=person.getVWebperD006().doubleValue();
						person_d007=person.getVWebperD007().doubleValue();person_d008=person.getVWebperD008().doubleValue();
						person_d009=person.getVWebperD009().doubleValue();person_d010=person.getVWebperD010().doubleValue();
						person_d011=person.getVWebperD011().doubleValue();person_d012=person.getVWebperD012().doubleValue();
						person_d013=person.getVWebperD013();person_d014=person.getVWebperD014();
						person_d015=person.getVWebperD015().doubleValue();person_d016=person.getVWebperD016().doubleValue();
						person_d017=person.getVWebperD017().doubleValue();person_d018=person.getVWebperD018().doubleValue();
						person_d019=person.getVWebperD019();person_d020=person.getVWebperD020().doubleValue();
						person_d021=person.getVWebperD021().doubleValue();person_d022=person.getVWebperD022().doubleValue();
						person_d023=person.getVWebperD023().doubleValue();person_d024=person.getVWebperD024().doubleValue();						
						person_d025=person.getVWebperD025().doubleValue();
						list_person_3.add(person_d001);list_person_3.add(person_d002);
						list_person_3.add(person_d003);list_person_3.add(person_d004);
						list_person_3.add(person_d005);list_person_3.add(person_d006);
						list_person_3.add(person_d007);list_person_3.add(person_d008);
						list_person_3.add(person_d009);list_person_3.add(person_d010);
						list_person_3.add(person_d011);list_person_3.add(person_d012);
						list_person_3.add(person_d013);list_person_3.add(person_d014);
						list_person_3.add(person_d015);list_person_3.add(person_d016);
						list_person_3.add(person_d017);list_person_3.add(person_d018);
						list_person_3.add(person_d019);list_person_3.add(person_d020);
						list_person_3.add(person_d021);list_person_3.add(person_d022);
						list_person_3.add(person_d023);list_person_3.add(person_d024);
						list_person_3.add(person_d025);
						list_person_2.add(list_person_3);
					}else{
						list_person_2.add(null);
					}
					//合计
					if(person_eve!=null){
						if(person_eve.getSumActualpairs()==null){
							person_eve.setSumActualpairs(big);
						}
						 personEve_total_personzg=personEve_total_personzg+person_eve.getPersonzg();
						 personEve_total_personjg=personEve_total_personjg+person_eve.getPersonjg();
						 personEve_total_timezg=personEve_total_timezg+person_eve.getTimezg();
						 personEve_total_timejg=personEve_total_timejg+person_eve.getTimejg();
						 personEve_total_addtimezg=personEve_total_addtimezg+person_eve.getAddtimezg();
						 personEve_total_addtimejg=personEve_total_addtimejg+person_eve.getAddtimejg();
						 personEve_total_leavenumzg=personEve_total_leavenumzg+person_eve.getLeavenumzg(); 
						 personEve_total_leavenumjg=personEve_total_leavenumjg+person_eve.getLeavenumjg();
						 personEve_total_wagezgUsd=personEve_total_wagezgUsd+person_eve.getWagezgUsd(); 
						 personEve_total_wagejgUsd=personEve_total_wagejgUsd+person_eve.getWagejgUsd();
						 personEve_total_addmoneyzg=personEve_total_addmoneyzg+person_eve.getAddmoneyzg();
						 personEve_total_addmoneyjg=personEve_total_addmoneyjg+person_eve.getAddmoneyjg();
						 personEve_total_sumStandarddemo=personEve_total_sumStandarddemo+person_eve.getSumStandarddemo().doubleValue();
						 personEve_total_sumActualdemo=personEve_total_sumActualdemo+person_eve.getSumActualdemo().doubleValue();
						 personEve_total_sumActualpairs=personEve_total_sumActualpairs+person_eve.getSumActualpairs().doubleValue();
					}
					
					//關務損耗(VWebgw)					
					if(gw!=null){
						gw_e001=gw.getVWebgwE001();gw_e002=gw.getVWebgwE002().doubleValue();
						gw_e003=gw.getVWebgwE003().doubleValue();gw_e004=gw.getVWebgwE004().doubleValue();
						gw_e005=gw.getVWebgwE005().doubleValue();gw_e006=gw.getVWebgwE006().doubleValue();
						list_gw_3.add(gw_e001);list_gw_3.add(gw_e002);
						list_gw_3.add(gw_e003);list_gw_3.add(gw_e004);
						list_gw_3.add(gw_e005);list_gw_3.add(gw_e006);
						list_gw_2.add(list_gw_3);
					}else{
						list_gw_2.add(null);
					}
					//合计
					if(gw_eve!=null){
						if(gw_eve.getSumActualpairs()==null){
							gw_eve.setSumActualpairs(big);
						}
						 gwEve_total_sumActualpairs=gwEve_total_sumActualpairs+gw_eve.getSumActualpairs().doubleValue(); 
						 gwEve_total_sideweit=gwEve_total_sideweit+gw_eve.getSideweit();
						 gwEve_total_badweit=gwEve_total_badweit+gw_eve.getBadweit(); 
						 gwEve_total_otherbadweight=gwEve_total_otherbadweight+gw_eve.getOtherbadweight();
						 gwEve_total_actlost=gwEve_total_actlost+gw_eve.getActlost(); 
						 gwEve_total_avgbuttomweight2=gwEve_total_avgbuttomweight2+gw_eve.getAvgbuttomweight2();
						 gwEve_total_otherweight=gwEve_total_otherweight+gw_eve.getOtherweight();
					}
					
					//邊料不良重量分析(VWebside)					
					if(side!=null){
						if(side.getVWebsideF003()==null){
							side.setVWebsideF003(big);
						}
						if(side.getVWebsideF004()==null){
							side.setVWebsideF004(big);
						}
						if(side.getVWebsideF008()==null){
							side.setVWebsideF008(big);
						}
						side_f001=side.getVWebsideF001();side_f002=side.getVWebsideF002();
						side_f003=side.getVWebsideF003().doubleValue();side_f004=side.getVWebsideF004().doubleValue();
						side_f005=side.getVWebsideF005().doubleValue();side_f006=side.getVWebsideF006();
						side_f007=side.getVWebsideF007();side_f008=side.getVWebsideF008().doubleValue();
						side_f009=side.getVWebsideF009().doubleValue();side_f010=side.getVWebsideF010().doubleValue();
						side_f011=side.getVWebsideF011().doubleValue();side_f012=side.getVWebsideF012().doubleValue();
						side_f013=side.getVWebsideF013().doubleValue();
						list_side_3.add(side_f001);list_side_3.add(side_f002);
						list_side_3.add(side_f003);list_side_3.add(side_f004);
						list_side_3.add(side_f005);list_side_3.add(side_f006);
						list_side_3.add(side_f007);list_side_3.add(side_f008);
						list_side_3.add(side_f009);list_side_3.add(side_f010);
						list_side_3.add(side_f011);list_side_3.add(side_f012);
						list_side_3.add(side_f013);
						list_side_2.add(list_side_3);
					}else{
						list_side_2.add(null);
					}
					//合计
					if(side_eve!=null){
						if(side_eve.getSumActualpairs()==null){
							side_eve.setSumActualpairs(big);
						}
						 sideEve_total_sumActualpairs=sideEve_total_sumActualpairs+side_eve.getSumActualpairs().doubleValue();
						 sideEve_total_sideweit=sideEve_total_sideweit+side_eve.getSideweit();
						 sideEve_total_badcount=sideEve_total_badcount+side_eve.getBadcount();
						 sideEve_total_badweit=sideEve_total_badweit+side_eve.getBadweit();
						 sideEve_total_otherbadweight=sideEve_total_otherbadweight+side_eve.getOtherbadweight(); 
						 sideEve_total_avgbuttomweight=sideEve_total_avgbuttomweight+side_eve.getAvgbuttomweight();
						 sideEve_total_avgbuttomweight2=sideEve_total_avgbuttomweight2+side_eve.getAvgbuttomweight2();
						 sideEve_total_avgprice=sideEve_total_avgprice+side_eve.getAvgprice();
						 sideEve_total_otherweight=sideEve_total_otherweight+side_eve.getOtherweight();
					}
					
					//庫存(VWebstore)				
					if(store!=null){						
						if(store.getVWebstoreG001()==null){							
							store.setVWebstoreG001(big);
						}
						if(store.getVWebstoreG002()==null){
							store.setVWebstoreG002(big);
						}
						if(store.getVWebstoreG003()==null){
							store.setVWebstoreG003(big);
						}
						if(store.getVWebstoreG004()==null){
							store.setVWebstoreG004(big);
						}
						if(store.getVWebstoreG005()==null){
							store.setVWebstoreG005(big);
						}
						if(store.getVWebstoreG006()==null){
							store.setVWebstoreG006(big);
						}
						if(store.getVWebstoreG007()==null){
							store.setVWebstoreG007(big);
						}
						if(store.getVWebstoreG014()==null){
							store.setVWebstoreG014(big);
						}
						if(store.getVWebstoreG017()==null){
							store.setVWebstoreG017(big);
						}
						store_g001=store.getVWebstoreG001().doubleValue();store_g002=store.getVWebstoreG002().doubleValue();
						store_g003=store.getVWebstoreG003().doubleValue();store_g004=store.getVWebstoreG004().doubleValue();
						store_g005=store.getVWebstoreG005().doubleValue();store_g006=store.getVWebstoreG006().doubleValue();
						store_g007=store.getVWebstoreG007().doubleValue();store_g008=store.getVWebstoreG008();
						store_g009=store.getVWebstoreG009();store_g010=store.getVWebstoreG010();
						store_g011=store.getVWebstoreG011();store_g012=store.getVWebstoreG012();
						store_g013=store.getVWebstoreG013().doubleValue();store_g014=store.getVWebstoreG014().doubleValue();
						store_g015=store.getVWebstoreG015().doubleValue();store_g016=store.getVWebstoreG016().doubleValue();
						store_g017=store.getVWebstoreG017().doubleValue();store_g018=store.getVWebstoreG018().doubleValue();
						list_store_3.add(store_g001);list_store_3.add(store_g002);
						list_store_3.add(store_g003);list_store_3.add(store_g004);
						list_store_3.add(store_g005);list_store_3.add(store_g006);
						list_store_3.add(store_g007);list_store_3.add(store_g008);
						list_store_3.add(store_g009);list_store_3.add(store_g010);
						list_store_3.add(store_g011);list_store_3.add(store_g012);
						list_store_3.add(store_g013);list_store_3.add(store_g014);
						list_store_3.add(store_g015);list_store_3.add(store_g016);
						list_store_3.add(store_g017);list_store_3.add(store_g018);
						list_store_2.add(list_store_3);
					}else{
						list_store_2.add(null);
					}
					//合计
					if(store_eve!=null){
						if(store_eve.getSumhostpairs()==null){
							store_eve.setSumhostpairs(big);
						}
						if(store_eve.getSumsamplepairs()==null){
							store_eve.setSumsamplepairs(big);
						}
						 storeEve_total_sumhostpairs=storeEve_total_sumhostpairs+store_eve.getSumhostpairs().doubleValue(); 
						 storeEve_total_sumsamplepairs=storeEve_total_sumsamplepairs+store_eve.getSumsamplepairs().doubleValue();
						 storeEve_total_invcount=storeEve_total_invcount+store_eve.getInvcount();
						 storeEve_total_hostinvcount=storeEve_total_hostinvcount+store_eve.getHostinvcount();
						 storeEve_total_badcount=storeEve_total_badcount+store_eve.getBadcount(); 
						 storeEve_total_lagStorenum=storeEve_total_lagStorenum+store_eve.getLagStorenum().doubleValue();
						 storeEve_total_lagNoliststorenum=storeEve_total_lagNoliststorenum+store_eve.getLagNoliststrorenum().doubleValue();
						 storeEve_total_lagMakestorenum=storeEve_total_lagMakestorenum+store_eve.getLagMakestrorenum().doubleValue();
						 storeEve_total_lagoutOutnum=storeEve_total_lagoutOutnum+store_eve.getLagOutnum().doubleValue();
						 storeEve_total_lagInnum=storeEve_total_lagInnum+store_eve.getLagInnum().doubleValue();
						 storeEve_total_lagInstorenum=storeEve_total_lagInstorenum+store_eve.getLagInstorenum().doubleValue(); 
						 storeEve_total_storenum=storeEve_total_storenum+store_eve.getStorenum();
						 storeEve_total_noliststorenum=storeEve_total_noliststorenum+store_eve.getNoliststrorenum(); 
						 storeEve_total_makestorenum=storeEve_total_makestorenum+store_eve.getMakestrorenum();
						 storeEve_total_outnum=storeEve_total_outnum+store_eve.getOutnum();
						 storeEve_total_innum=storeEve_total_innum+store_eve.getInnum();
						 storeEve_total_minusnum=storeEve_total_minusnum+store_eve.getMinusnum();
					}
					
					//水電油(VWebwlo)					
					if(wlo!=null){
						wlo_h001=wlo.getVWebwloH001().doubleValue();wlo_h002=wlo.getVWebwloH002();
						wlo_h003=wlo.getVWebwloH003().doubleValue();wlo_h004=wlo.getVWebwloH004();
						wlo_h005=wlo.getVWebwloH005().doubleValue();wlo_h006=wlo.getVWebwloH006();
						wlo_h007=wlo.getVWebwloH007().doubleValue();wlo_h008=wlo.getVWebwloH008();
						wlo_h009=wlo.getVWebwloH009().doubleValue();wlo_h010=wlo.getVWebwloH010();
						wlo_h011=wlo.getVWebwloH011().doubleValue();wlo_h012=wlo.getVWebwloH012();
						wlo_h013=wlo.getVWebwloH013().doubleValue();wlo_h014=wlo.getVWebwloH014();
						wlo_h015=wlo.getVWebwloH015().doubleValue();wlo_h016=wlo.getVWebwloH016();
						wlo_h017=wlo.getVWebwloH017().doubleValue();
						list_wlo_3.add(wlo_h001);list_wlo_3.add(wlo_h002);
						list_wlo_3.add(wlo_h003);list_wlo_3.add(wlo_h004);
						list_wlo_3.add(wlo_h005);list_wlo_3.add(wlo_h006);
						list_wlo_3.add(wlo_h007);list_wlo_3.add(wlo_h008);
						list_wlo_3.add(wlo_h009);list_wlo_3.add(wlo_h010);
						list_wlo_3.add(wlo_h011);list_wlo_3.add(wlo_h012);
						list_wlo_3.add(wlo_h013);list_wlo_3.add(wlo_h014);
						list_wlo_3.add(wlo_h015);list_wlo_3.add(wlo_h016);
						list_wlo_3.add(wlo_h017);
						list_wlo_2.add(list_wlo_3);
					}else{
						list_wlo_2.add(null);
					}
					//合计
					if(wlo_eve!=null){
						 wloEve_total_sumactualdemo=wloEve_total_sumactualdemo+wlo_eve.getSumactualdemo().doubleValue(); 
						 wloEve_total_waterton=wloEve_total_waterton+wlo_eve.getWaterton();
						 wloEve_total_waterusd=wloEve_total_waterusd+wlo_eve.getWaterusd(); 
						 wloEve_total_electricdu=wloEve_total_electricdu+wlo_eve.getElectricdu();
						 wloEve_total_electricusd=wloEve_total_electricusd+wlo_eve.getElectricusd(); 
						 wloEve_total_gaston=wloEve_total_gaston+wlo_eve.getGaston();
						 wloEve_total_gasusd=wloEve_total_gasusd+wlo_eve.getGasusd(); 
						 wloEve_total_oilton=wloEve_total_oilton+wlo_eve.getOilton();
						 wloEve_total_oilusd=wloEve_total_oilusd+wlo_eve.getOilusd();
					}
					
					//回頭料(Webbackfeed)					
					if(back!=null){
						back_i001=back.getThickused();
						back_i002=back.getBackfeed();
						back_i003=back.getBackfeed()/back.getThickused();
						back_i004=back.getOilback();
						back_i005=back.getOilback()/back.getThickused();
						back_i006=back.getBackfeed()+back.getOilback();
						back_i007=back.getBackfeed()/back.getThickused()+back.getOilback()/back.getThickused();
						list_back_3.add(back_i001);list_back_3.add(back_i002);
						list_back_3.add(back_i003);list_back_3.add(back_i004);
						list_back_3.add(back_i005);list_back_3.add(back_i006);
						list_back_3.add(back_i007);
						list_back_2.add(list_back_3);
					}else{
						list_back_2.add(null);
					}
					//合计
					if(back_eve!=null){
						  backEve_total_thickusd=backEve_total_thickusd+back_eve.getThickused(); 
						  backEve_total_backfeed=backEve_total_backfeed+back_eve.getBackfeed();
						  backEve_total_oilback=backEve_total_oilback+back_eve.getOilback();
					}
					//色料回收粉(vwebcost)					
					if(webcost!=null){
						webcost_j001=webcost.getVWebcostJ001();webcost_j002=webcost.getVWebcostJ002().doubleValue();
						webcost_j003=webcost.getVWebcostJ003();webcost_j004=webcost.getVWebcostJ004().doubleValue();
						webcost_j005=webcost.getVWebcostJ005();webcost_j006=webcost.getVWebcostJ006().doubleValue();
						webcost_j007=webcost.getVWebcostJ007();webcost_j008=webcost.getVWebcostJ008();
						webcost_j009=webcost.getVWebcostJ009();webcost_j010=webcost.getVWebcostJ010();
						webcost_j011=webcost.getVWebcostJ011();
						list_webcost_3.add(webcost_j001);list_webcost_3.add(webcost_j002);
						list_webcost_3.add(webcost_j003);list_webcost_3.add(webcost_j004);
						list_webcost_3.add(webcost_j005);list_webcost_3.add(webcost_j006);
						list_webcost_3.add(webcost_j007);list_webcost_3.add(webcost_j008);
						list_webcost_3.add(webcost_j009);list_webcost_3.add(webcost_j010);
						list_webcost_3.add(webcost_j011);
						list_webcost_2.add(list_webcost_3);
					}else{
						list_webcost_2.add(null);
					}
					
					//所有合计
					if(b==list_factNo.size()-1){
						//注意,machineEve_total_sumWorkday是求平均数的
						int num=list_factNo.size();
						list_machine_total=this.findResult_machine(1, machineEve_total_sumWorkday/num, machineEve_total_sumEverydemo,
								machineEve_total_sumStandarddemo, machineEve_total_sumActualdemo, machineEve_total_sumActualpairs,
								machineEve_total_avgbuttomweight, machineEve_total_avgbuttomweight2, machineEve_total_hole);
						list_inv_total=this.findResult_inv(1, invEve_total_sumActualpairs, invEve_total_invcount, invEve_total_paypair);
						list_mix1_total=this.findResult_mix1(1, mix1Eve_total_sumoutnum, mix1Eve_total_sumbacknum);
						list_person_total=this.findResult_person(1, personEve_total_personzg, personEve_total_personjg, personEve_total_timezg, personEve_total_timejg,
								personEve_total_addtimezg, personEve_total_addtimejg, personEve_total_leavenumzg, personEve_total_leavenumjg,
								personEve_total_wagezgUsd, personEve_total_wagejgUsd, personEve_total_addmoneyzg, personEve_total_addmoneyjg, personEve_total_sumStandarddemo,
								personEve_total_sumActualdemo, personEve_total_sumActualpairs);
						list_gw_total=this.findResult_gw(1, gwEve_total_sumActualpairs, gwEve_total_sideweit, gwEve_total_badweit,
								gwEve_total_otherbadweight, gwEve_total_actlost, gwEve_total_avgbuttomweight2, gwEve_total_otherweight);
						list_side_total=this.findResult_side(1, sideEve_total_sumActualpairs, sideEve_total_sideweit, sideEve_total_badcount,
								sideEve_total_badweit, sideEve_total_otherbadweight, sideEve_total_avgbuttomweight,
								sideEve_total_avgbuttomweight2, sideEve_total_avgprice, sideEve_total_otherweight);
						list_store_total=this.findResult_store(1, storeEve_total_sumhostpairs, storeEve_total_sumsamplepairs, storeEve_total_invcount,
								storeEve_total_hostinvcount, storeEve_total_badcount, storeEve_total_lagStorenum, storeEve_total_lagNoliststorenum, 
								storeEve_total_lagMakestorenum, storeEve_total_lagoutOutnum, storeEve_total_lagInnum, storeEve_total_lagInstorenum, storeEve_total_storenum,
								storeEve_total_noliststorenum, storeEve_total_makestorenum, storeEve_total_outnum, storeEve_total_innum, storeEve_total_minusnum);
						list_wlo_total=this.findResult_wlo(1, wloEve_total_sumactualdemo, wloEve_total_waterton, wloEve_total_waterusd, 
								wloEve_total_electricdu, wloEve_total_electricusd, 
								wloEve_total_gaston, wloEve_total_gasusd, wloEve_total_oilton, wloEve_total_oilusd);
						list_back_total=this.findResult_back(1, backEve_total_thickusd, backEve_total_backfeed, backEve_total_oilback);
						list_webcost_total=this.findResult_webcost(1, webcostEve_total_sumActualdemo, webcostEve_total_gluestoremoney, webcostEve_total_drugsused, webcostEve_total_leavemoney, webcostEve_total_whitenum,
								webcostEve_total_blacknum, webcostEve_total_gluenum, webcostEve_total_greynum, webcostEve_total_othernum);
						
						list_machine_2.add(list_machine_total);
						list_inv_2.add(list_inv_total);
						list_mix1_2.add(list_mix1_total);
						list_person_2.add(list_person_total);
						list_gw_2.add(list_gw_total);
						list_side_2.add(list_side_total);
						list_store_2.add(list_store_total);
						list_wlo_2.add(list_wlo_total);
						list_back_2.add(list_back_total);
						list_webcost_2.add(list_webcost_total);
					}
				}//end for2
				/**
				 * 最外層集合封裝
				 */
				list_machine.add(list_machine_2);
				list_inv.add(list_inv_2);
				list_mix1.add(list_mix1_2);
				list_person.add(list_person_2);
				list_gw.add(list_gw_2);
				list_side.add(list_side_2);
				list_store.add(list_store_2);
				list_wlo.add(list_wlo_2);
				list_back.add(list_back_2);
				list_webcost.add(list_webcost_2);
			}//end for1
			
			/**
			 * list_factCode   list_factno_all
			 * 填充內容
			 */
			//填充標題
			String title=yymm+"台灣損益會議各廠檢討項目各廠對比表----分型態";
			XSSFRow row_title=sheet.createRow(0);
			XSSFCell cell_title=row_title.createCell(0);
			cell_title.setCellValue(title);
			cell_title.setCellStyle(cs_title);
			CellRangeAddress cra_title=new CellRangeAddress(0,(short)0,0,(short)10);
			sheet.addMergedRegion(cra_title);
			for(int a=1;a<10;a++){
				XSSFCell cell=row_title.createCell(a);
				cell.setCellStyle(cs_title);
			}
			//填充廠別狀態和表頭
			for(int b=0;b<list_factCode.size();b++){//start for1
				/**
				 * 獲取中間數集
				 */
				List<List<Double>>list_machine2=list_machine.get(b);
				List<List<Double>>list_inv2=list_inv.get(b);
				List<List<Double>>list_mix1_2=list_mix1.get(b);
				List<List<Double>>list_person2=list_person.get(b);
				List<List<Double>>list_gw2=list_gw.get(b);
				List<List<Double>>list_side2=list_side.get(b);
				List<List<Double>>list_store2=list_store.get(b);
				List<List<Double>>list_wlo2=list_wlo.get(b);
				List<List<Double>>list_back2=list_back.get(b);
				List<List<Double>>list_webcost2=list_webcost.get(b);
				//廠別狀態
				String factCode=list_factCode.get(b);
				XSSFRow row_factcode=sheet.createRow(2+120*b);
				XSSFCell cell_factcode=row_factcode.createCell(0);
				cell_factcode.setCellValue(factCode);
				cell_factcode.setCellStyle(cs_noborder);
				//表頭
				List<String>list_factName=new ArrayList<String>();
				//獲取每個factcode對應的所有廠別
				List<String>list_factNo=list_factno_all.get(b);
				for(int a=0;a<list_factNo.size();a++){					
					String factNo=list_factNo.get(a);
					String factName=webFactSer.selByid(factNo);
					list_factName.add(factName);
				}
				list_factName.add(0,"項目");
				list_factName.add(1,"細項");
				list_factName.add(2,"單位");
				list_factName.add("合计");
				XSSFRow row_column=sheet.createRow(3+120*b);
				for(int c=0;c<list_factName.size();c++){//start for2
					/**
					 * 獲取單元數集
					 */
					List<Double>list_machine3=list_machine2.get(c);
					List<Double>list_inv3=list_inv2.get(c);
					List<Double>list_mix1_3=list_mix1_2.get(c);
					List<Double>list_person3=list_person2.get(c);
					List<Double>list_gw3=list_gw2.get(c);
					List<Double>list_side3=list_side2.get(c);
					List<Double>list_store3=list_store2.get(c);
					List<Double>list_wlo3=list_wlo2.get(c);
					List<Double>list_back3=list_back2.get(c);
					List<Double>list_webcost3=list_webcost2.get(c);
					
					XSSFCell cell_column=row_column.createCell(c);
					cell_column.setCellValue(list_factName.get(c));
					cell_column.setCellStyle(cs_column);
					switch(c){
					case 0:
						sheet.setColumnWidth(c, 3500);
						//孔位數機台回轉數
						XSSFRow row_machine=sheet.createRow(4+120*b);
						XSSFCell cell_machine=row_machine.createCell(c);
						cell_machine.setCellValue("孔位數機台回轉數");
						cell_machine.setCellStyle(cs);
						CellRangeAddress cra_machine=new CellRangeAddress(4+120*b,(short)(4+120*b+length1-1),c,(short)c);
						sheet.addMergedRegion(cra_machine);
						for(int d=1;d<length1;d++){
							XSSFRow row=sheet.createRow(4+120*b+d);
							XSSFCell cell=row.createCell(c);
							cell.setCellStyle(cs);
						}
						//生產與請款狀況
						XSSFRow row_inv=sheet.createRow(4+120*b+length1);
						XSSFCell cell_inv=row_inv.createCell(c);
						cell_inv.setCellValue("生產與請款狀況");
						cell_inv.setCellStyle(cs);
						CellRangeAddress cra_inv=new CellRangeAddress(4+120*b+length1,(short)(4+120*b+length1+length2-1),c,(short)c);
						sheet.addMergedRegion(cra_inv);
						for(int d=1;d<length2;d++){
							XSSFRow row=sheet.createRow(4+120*b+d+length1);
							XSSFCell cell=row.createCell(c);
							cell.setCellStyle(cs);
						}
						//出貨與退貨
						XSSFRow row_mix1=sheet.createRow(4+120*b+length1+length2);
						XSSFCell cell_mix1=row_mix1.createCell(c);
						cell_mix1.setCellValue("出貨與退貨");
						cell_mix1.setCellStyle(cs);
						CellRangeAddress cra_mix1=new CellRangeAddress(4+120*b+length1+length2,
								(short)(4+120*b+length1+length2+length3-1),c,(short)c);
						sheet.addMergedRegion(cra_mix1);
						for(int d=1;d<length3;d++){
							XSSFRow row=sheet.createRow(4+120*b+d+length1+length2);
							XSSFCell cell=row.createCell(c);
							cell.setCellStyle(cs);
						}
						//人員效能
						XSSFRow row_person=sheet.createRow(4+120*b+length1+length2+length3);
						XSSFCell cell_person=row_person.createCell(c);
						cell_person.setCellValue("人員效能");
						cell_person.setCellStyle(cs);
						CellRangeAddress cra_person=new CellRangeAddress(4+120*b+length1+length2+length3,
								(short)(4+120*b+length1+length2+length3+length4-1),c,(short)c);
						sheet.addMergedRegion(cra_person);
						for(int d=1;d<length4;d++){
							XSSFRow row=sheet.createRow(4+120*b+d+length1+length2+length3);
							XSSFCell cell=row.createCell(c);
							cell.setCellStyle(cs);
						}
						//關務損耗
						XSSFRow row_gw=sheet.createRow(4+120*b+length1+length2+length3+length4);
						XSSFCell cell_gw=row_gw.createCell(c);
						cell_gw.setCellValue("關務損耗");
						cell_gw.setCellStyle(cs);
						CellRangeAddress cra_gw=new CellRangeAddress(4+120*b+length1+length2+length3+length4,
								(short)(4+120*b+length1+length2+length3+length4+length5-1),c,(short)c);
						sheet.addMergedRegion(cra_gw);
						for(int d=1;d<length5;d++){
							XSSFRow row=sheet.createRow(4+120*b+d+length1+length2+length3+length4);
							XSSFCell cell=row.createCell(c);
							cell.setCellStyle(cs);
						}
						//邊料不良重量分析
						XSSFRow row_side=sheet.createRow(4+120*b+length1+length2+length3+length4+length5);
						XSSFCell cell_side=row_side.createCell(c);
						cell_side.setCellValue("邊料不良重量分析");
						cell_side.setCellStyle(cs);
						CellRangeAddress cra_side=new CellRangeAddress(4+120*b+length1+length2+length3+length4+length5,
								(short)(4+120*b+length1+length2+length3+length4+length5+length6-1),c,(short)c);
						sheet.addMergedRegion(cra_side);
						for(int d=1;d<length6;d++){
							XSSFRow row=sheet.createRow(4+120*b+d+length1+length2+length3+length4+length5);
							XSSFCell cell=row.createCell(c);
							cell.setCellStyle(cs);
						}
						//庫存
						XSSFRow row_store=sheet.createRow(4+120*b+length1+length2+length3+length4+length5+length6);
						XSSFCell cell_store=row_store.createCell(c);
						cell_store.setCellValue("庫存");
						cell_store.setCellStyle(cs);
						CellRangeAddress cra_store=new CellRangeAddress(4+120*b+length1+length2+length3+length4+length5+length6,
								(short)(4+120*b+length1+length2+length3+length4+length5+length6+length7-1),c,(short)c);
						sheet.addMergedRegion(cra_store);
						for(int d=1;d<length7;d++){
							XSSFRow row=sheet.createRow(4+120*b+d+length1+length2+length3+length4+length5+length6);
							XSSFCell cell=row.createCell(c);
							cell.setCellStyle(cs);
						}
						//水電油
						XSSFRow row_wlo=sheet.createRow(4+120*b+length1+length2+length3+length4+length5+length6+length7);
						XSSFCell cell_wlo=row_wlo.createCell(c);
						cell_wlo.setCellValue("水電油");
						cell_wlo.setCellStyle(cs);
						CellRangeAddress cra_wlo=new CellRangeAddress(4+120*b+length1+length2+length3+length4+length5+length6+length7,
								(short)(4+120*b+length1+length2+length3+length4+length5+length6+length7+length8-1),c,(short)c);
						sheet.addMergedRegion(cra_wlo);
						for(int d=1;d<length8;d++){
							XSSFRow row=sheet.createRow(4+120*b+d+length1+length2+length3+length4+length5+length6+length7);
							XSSFCell cell=row.createCell(c);
							cell.setCellStyle(cs);
						}
						//回頭料
						XSSFRow row_back=sheet.createRow(4+120*b+length1+length2+length3+length4+length5+length6+length7+length8);
						XSSFCell cell_back=row_back.createCell(c);
						cell_back.setCellValue("回頭料");
						cell_back.setCellStyle(cs);
						CellRangeAddress cra_back=new CellRangeAddress(4+120*b+length1+length2+length3+length4+length5+length6+length7+length8,
								(short)(4+120*b+length1+length2+length3+length4+length5+length6+length7+length8+length9-1),c,(short)c);
						sheet.addMergedRegion(cra_back);
						for(int d=1;d<length9;d++){
							XSSFRow row=sheet.createRow(4+120*b+d+length1+length2+length3+length4+length5+length6+length7+length8);
							XSSFCell cell=row.createCell(c);
							cell.setCellStyle(cs);
						}
						//色料回收粉
						XSSFRow row_webcost=sheet.createRow(4+120*b+length1+length2+length3+length4+length5+length6+length7+length8+length9);
						XSSFCell cell_webcost=row_webcost.createCell(c);
						cell_webcost.setCellValue("色料藥品/離型劑/回收粉");
						cell_webcost.setCellStyle(cs);
						CellRangeAddress cra_webcost=new CellRangeAddress(4+120*b+length1+length2+length3+length4+length5+length6+length7+length8+length9,
								(short)(4+120*b+length1+length2+length3+length4+length5+length6+length7+length8+length9+length10-1),c,(short)c);
						sheet.addMergedRegion(cra_webcost);
						for(int d=1;d<length10;d++){
							XSSFRow row=sheet.createRow(4+120*b+d+length1+length2+length3+length4+length5+length6+length7+length8+length9);
							XSSFCell cell=row.createCell(c);
							cell.setCellStyle(cs);
						}
						break;
					case 1:
						sheet.setColumnWidth(c, 4000);
						//孔位數機台回轉數
						for(int d=0;d<length1;d++){
							XSSFRow row_machine2=sheet.getRow(4+120*b+d);
							XSSFCell cell_machine2=row_machine2.createCell(c);
							cell_machine2.setCellValue(list_contentName_machine.get(d));
							cell_machine2.setCellStyle(cs);
						}						
                        //生產與請款狀況
						for(int d=0;d<length2;d++){
							XSSFRow row_inv2=sheet.getRow(4+120*b+d+length1);
							XSSFCell cell_inv2=row_inv2.createCell(c);
							cell_inv2.setCellValue(list_contentName_inv.get(d));
							cell_inv2.setCellStyle(cs);
						}
						//出貨與退貨
						for(int d=0;d<length3;d++){
							XSSFRow row_mix1_2=sheet.getRow(4+120*b+d+length1+length2);
							XSSFCell cell_mix1_2=row_mix1_2.createCell(c);
							cell_mix1_2.setCellValue(list_contentName_mix1.get(d));
							cell_mix1_2.setCellStyle(cs);
						}
						//人員效能
						for(int d=0;d<length4;d++){
							XSSFRow row_person2=sheet.getRow(4+120*b+d+length1+length2+length3);
							XSSFCell cell_person2=row_person2.createCell(c);
							cell_person2.setCellValue(list_contentName_person.get(d));
							cell_person2.setCellStyle(cs);
						}
						//關務損耗
						for(int d=0;d<length5;d++){
							XSSFRow row_gw2=sheet.getRow(4+120*b+d+length1+length2+length3+length4);
							XSSFCell cell_gw2=row_gw2.createCell(c);
							cell_gw2.setCellValue(list_contentName_gw.get(d));
							cell_gw2.setCellStyle(cs);
						}
						//邊料不良重量分析
						for(int d=0;d<length6;d++){
							XSSFRow row_side2=sheet.getRow(4+120*b+d+length1+length2+length3+length4+length5);
							XSSFCell cell_side2=row_side2.createCell(c);
							cell_side2.setCellValue(list_contentName_side.get(d));
							cell_side2.setCellStyle(cs);
						}
						//庫存
						for(int d=0;d<length7;d++){
							XSSFRow row_store2=sheet.getRow(4+120*b+d+length1+length2+length3+length4+length5+length6);
							XSSFCell cell_store2=row_store2.createCell(c);
							cell_store2.setCellValue(list_contentName_store.get(d));
							cell_store2.setCellStyle(cs);
						}
						//水電油
						for(int d=0;d<length8;d++){
							XSSFRow row_wlo2=sheet.getRow(4+120*b+d+length1+length2+length3+length4+length5+length6+length7);
							XSSFCell cell_wlo2=row_wlo2.createCell(c);
							cell_wlo2.setCellValue(list_contentName_wlo.get(d));
							cell_wlo2.setCellStyle(cs);
						}
						//回頭料
						for(int d=0;d<length9;d++){
							XSSFRow row_back2=sheet.getRow(4+120*b+d+length1+length2+length3+length4+length5+length6+length7+length8);
							XSSFCell cell_back2=row_back2.createCell(c);
							cell_back2.setCellValue(list_contentName_back.get(d));
							cell_back2.setCellStyle(cs);
						}
						//色料回收粉
						for(int d=0;d<length10;d++){
							XSSFRow row_webcost2=sheet.getRow(4+120*b+d+length1+length2+length3+length4+length5+length6+length7+length8+length9);
							XSSFCell cell_webcost2=row_webcost2.createCell(c);
							cell_webcost2.setCellValue(list_contentName_webcost.get(d));
							cell_webcost2.setCellStyle(cs);
						}						
						break;
					case 2:
                        //孔位數機台回轉數
						for(int d=0;d<length1;d++){
							XSSFRow row_machine2=sheet.getRow(4+120*b+d);
							XSSFCell cell_machine2=row_machine2.createCell(c);
							cell_machine2.setCellValue(list_unit_machine.get(d));
							cell_machine2.setCellStyle(cs);
						}
                        //生產與請款狀況
						for(int d=0;d<length2;d++){
							XSSFRow row_inv2=sheet.getRow(4+120*b+d+length1);
							XSSFCell cell_inv2=row_inv2.createCell(c);
							cell_inv2.setCellValue(list_unit_inv.get(d));
							cell_inv2.setCellStyle(cs);
						}
						//出貨與退貨
						for(int d=0;d<length3;d++){
							XSSFRow row_mix1_2=sheet.getRow(4+120*b+d+length1+length2);
							XSSFCell cell_mix1_2=row_mix1_2.createCell(c);
							cell_mix1_2.setCellValue(list_unit_mix1.get(d));
							cell_mix1_2.setCellStyle(cs);
						}
						//人員效能
						for(int d=0;d<length4;d++){
							XSSFRow row_person2=sheet.getRow(4+120*b+d+length1+length2+length3);
							XSSFCell cell_person2=row_person2.createCell(c);
							cell_person2.setCellValue(list_unit_person.get(d));
							cell_person2.setCellStyle(cs);
						}
						//關務損耗
						for(int d=0;d<length5;d++){
							XSSFRow row_gw2=sheet.getRow(4+120*b+d+length1+length2+length3+length4);
							XSSFCell cell_gw2=row_gw2.createCell(c);
							cell_gw2.setCellValue(list_unit_gw.get(d));
							cell_gw2.setCellStyle(cs);
						}
						//邊料不良重量分析
						for(int d=0;d<length6;d++){
							XSSFRow row_side2=sheet.getRow(4+120*b+d+length1+length2+length3+length4+length5);
							XSSFCell cell_side2=row_side2.createCell(c);
							cell_side2.setCellValue(list_unit_side.get(d));
							cell_side2.setCellStyle(cs);
						}
						//庫存
						for(int d=0;d<length7;d++){
							XSSFRow row_store2=sheet.getRow(4+120*b+d+length1+length2+length3+length4+length5+length6);
							XSSFCell cell_store2=row_store2.createCell(c);
							cell_store2.setCellValue(list_unit_store.get(d));
							cell_store2.setCellStyle(cs);
						}
						//水電油
						for(int d=0;d<length8;d++){
							XSSFRow row_wlo2=sheet.getRow(4+120*b+d+length1+length2+length3+length4+length5+length6+length7);
							XSSFCell cell_wlo2=row_wlo2.createCell(c);
							cell_wlo2.setCellValue(list_unit_wlo.get(d));
							cell_wlo2.setCellStyle(cs);
						}
						//回頭料
						for(int d=0;d<length9;d++){
							XSSFRow row_back2=sheet.getRow(4+120*b+d+length1+length2+length3+length4+length5+length6+length7+length8);
							XSSFCell cell_back2=row_back2.createCell(c);
							cell_back2.setCellValue(list_unit_back.get(d));
							cell_back2.setCellStyle(cs);
						}
						//色料回收粉
						for(int d=0;d<length10;d++){
							XSSFRow row_webcost2=sheet.getRow(4+120*b+d+length1+length2+length3+length4+length5+length6+length7+length8+length9);
							XSSFCell cell_webcost2=row_webcost2.createCell(c);
							cell_webcost2.setCellValue(list_unit_webcost.get(d));
							cell_webcost2.setCellStyle(cs);
						}
						break;
					default:
						//孔位數機台回轉數
						sheet.setColumnWidth(c, 3500);
						XSSFCellStyle cs_temp=wb.createCellStyle();
						for(int d=0;d<length1;d++){
							if(d==0||d==2||d==3||d==7||d==8){
								cs_temp=cs_poi;
							}else if(d==1||d==5||d==6||d==9){
								cs_temp=cs_poi1;
							}else if(d==4){
								cs_temp=cs_percent;                                                                                                   //20150423
							}else{
								cs_temp=cs_percent2;
							}
							XSSFRow row_machine_3=sheet.getRow(4+120*b+d);
							XSSFCell cell_machine_3=row_machine_3.createCell(c);
							if(list_machine3!=null){
								cell_machine_3.setCellValue(list_machine3.get(d));
							}else{
								cell_machine_3.setCellValue("無數據");
							}
							cell_machine_3.setCellStyle(cs_temp);
						}
						//生產與請款狀況
						for(int d=0;d<length2;d++){
							if(d==0||d==1){
								cs_temp=cs_poi1;
							}else{
								cs_temp=cs_poi2;
							}
							XSSFRow row_inv_3=sheet.getRow(4+120*b+d+length1);
							XSSFCell cell_inv_3=row_inv_3.createCell(c);
							if(list_inv3!=null){
								cell_inv_3.setCellValue(list_inv3.get(d));
							}else{
								cell_inv_3.setCellValue("無數據");
							}
							cell_inv_3.setCellStyle(cs_temp);
						}
						//出貨與退貨
						for(int d=0;d<length3;d++){
							if(d==2){
								cs_temp=cs_percent2;
							}else{
								cs_temp=cs_poi1;
							}
							XSSFRow row_mix1_3=sheet.getRow(4+120*b+d+length1+length2);
							XSSFCell cell_mix1_3=row_mix1_3.createCell(c);
							if(list_mix1_3!=null){
								cell_mix1_3.setCellValue(list_mix1_3.get(d));
							}else{
								cell_mix1_3.setCellValue("無數據");
							}
							cell_mix1_3.setCellStyle(cs_temp);
						}
						//人員效能
						for(int d=0;d<length4;d++){
							if(d==2||d==10||d==11||d==22||d==23){
								cs_temp=cs_percent2;
							}else if(d==0||d==1){
								cs_temp=cs_poi1;
							}else if(d==3||d==4||d==5||d==7||d==8){
								cs_temp=cs_poi;
							}else{
								cs_temp=cs_poi2;
							}
							XSSFRow row_person_3=sheet.getRow(4+120*b+d+length1+length2+length3);
							XSSFCell cell_person_3=row_person_3.createCell(c);
							if(list_person3!=null){
								cell_person_3.setCellValue(list_person3.get(d));
							}else{
								cell_person_3.setCellValue("無數據");
							}
							cell_person_3.setCellStyle(cs_temp);
						}
						//關務損耗
						for(int d=0;d<length5;d++){
							if(d==3||d==5){
								cs_temp=cs_percent2;
							}else{
								cs_temp=cs_poi2;
							}
							XSSFRow row_gw_3=sheet.getRow(4+120*b+d+length1+length2+length3+length4);
							XSSFCell cell_gw_3=row_gw_3.createCell(c);
							if(list_gw3!=null){
								cell_gw_3.setCellValue(list_gw3.get(d));
							}else{
								cell_gw_3.setCellValue("無數據");
							}
							cell_gw_3.setCellStyle(cs_temp);
						}
						//邊料不良重量分析
						for(int d=0;d<length6;d++){
							if(d==3||d==7){
								cs_temp=cs_percent2;
							}else{
								cs_temp=cs_poi2;
							}
							XSSFRow row_side_3=sheet.getRow(4+120*b+d+length1+length2+length3+length4+length5);
							XSSFCell cell_side_3=row_side_3.createCell(c);
							if(list_side3!=null){
								cell_side_3.setCellValue(list_side3.get(d));
							}else{
								cell_side_3.setCellValue("無數據");
							}
							cell_side_3.setCellStyle(cs_temp);
						}
						//庫存
						for(int d=0;d<length7;d++){
							XSSFRow row_store_3=sheet.getRow(4+120*b+d+length1+length2+length3+length4+length5+length6);
							XSSFCell cell_store_3=row_store_3.createCell(c);
							if(list_store3!=null){
								cell_store_3.setCellValue(list_store3.get(d));
							}else{
								cell_store_3.setCellValue("無數據");
							}
							cell_store_3.setCellStyle(cs_poi1);
						}
						//水電油
						for(int d=0;d<length8;d++){
							if(d==0){
								cs_temp=cs_poi1;
							}else if(d==1||d==5){
								cs_temp=cs_poi;
							}else if(d==2||d==4||d==6||d==8||d==10||d==12||d==14||d==16){
								cs_temp=cs_poi4;
							}else{
								cs_temp=cs_poi2;
							}
							XSSFRow row_store_3=sheet.getRow(4+120*b+d+length1+length2+length3+length4+length5+length6+length7);
							XSSFCell cell_store_3=row_store_3.createCell(c);
							if(list_store3!=null){
								cell_store_3.setCellValue(list_wlo3.get(d));
							}else{
								cell_store_3.setCellValue("無數據");
							}
							cell_store_3.setCellStyle(cs_temp);
						}
						//回頭料
						for(int d=0;d<length9;d++){
							if(d==2||d==4||d==6){
								cs_temp=cs_percent2;
							}else{
								cs_temp=cs_poi2;
							}
							XSSFRow row_back_3=sheet.getRow(4+120*b+d+length1+length2+length3+length4+length5+length6+length7+length8);
							XSSFCell cell_back_3=row_back_3.createCell(c);
							if(list_back3!=null){
								cell_back_3.setCellValue(list_back3.get(d));
							}else{
								cell_back_3.setCellValue("無數據");
							}
							cell_back_3.setCellStyle(cs_temp);
						}
						//色料回收粉
						for(int d=0;d<length10;d++){
							if(d==1||d==3||d==5){
								cs_temp=cs_poi4;
							}else{
								cs_temp=cs_poi2;
							}
							XSSFRow row_webcost_3=sheet.getRow(4+120*b+d+length1+length2+length3+length4+length5+length6+length7+length8+length9);
							XSSFCell cell_webcost_3=row_webcost_3.createCell(c);
							if(list_webcost3!=null){
								cell_webcost_3.setCellValue(list_webcost3.get(d));
							}else{
								cell_webcost_3.setCellValue("無數據");
							}
							cell_webcost_3.setCellStyle(cs_temp);
						}
						break;
							
					
					}
					
					
				}//end for2
			}//end for1
		ServletOutputStream os=response.getOutputStream();
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		title=title+".xlsx";
		int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");
		String fileName="";
		if(msie>0){
			fileName=java.net.URLEncoder.encode(title,"utf-8");
		}else{
			fileName=new String(title.getBytes("utf-8"),"iso-8859-1");
		}
		response.setHeader("Content-disposition","attachment;filename="+fileName);
		//OutputStream os=new FileOutputStream("d:/vwebmachine_gw.xls");
		wb.write(os);
		os.close();
	}


	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	
	/**
	 * 公式方法
	 * 機台回轉數
	 * (3,
							machineEve_q4_sumWorkday, machineEve_q4_sumEverydemo, machineEve_q4_sumStandarddemo,
							 machineEve_q4_sumActualdemo, machineEve_q4_sumActualpairs,
							machineEve_q4_avgbuttomweight, machineEve_q4_avgbuttomweight2, 
							machineEve_q4_hole)
	 */
	public List<Double> findResult_machine(int index,
			Double sumWorkday,
			Double sumEverydemo,
			Double sumStandarddemo,
			Double sumActualdemo,
			Double sumActualpairs,
			Double avgbuttomweight,
			Double avgbuttomweight2,
			Double hole
			){
		List<Double>list=new ArrayList<Double>();
		Double VWebmcA001=hole/index;
		Double VWebmcA002=sumWorkday/index;
		Double VWebmcA003=sumEverydemo/index;
		Double VWebmcA004=division(sumEverydemo,sumWorkday);
		Double VWebmcA005=division(division(sumEverydemo,sumWorkday),hole/index);//--記住hole要除以月數
		Double VWebmcA006=division(avgbuttomweight,sumActualpairs)*1000;
		Double VWebmcA007=division(avgbuttomweight2,sumActualpairs)*1000;
		Double VWebmcA008=division(sumStandarddemo,sumEverydemo);
		Double VWebmcA009=division(sumActualdemo,sumEverydemo);
		Double VWebmcA010=division(sumActualdemo,sumEverydemo)-division(sumStandarddemo,sumEverydemo);
		Double VWebmcA011=division(division(sumActualdemo,sumEverydemo),division(sumStandarddemo,sumEverydemo));
		list.add(VWebmcA001);
		list.add(VWebmcA002);
		list.add(VWebmcA003);
		list.add(VWebmcA004);
		list.add(VWebmcA005);
		list.add(VWebmcA006);
		list.add(VWebmcA007);
		list.add(VWebmcA008);
		list.add(VWebmcA009);
		list.add(VWebmcA010);
		list.add(VWebmcA011);
		return list;		
	}
	
	/**
	 * 公式方法
	 * 生產與請款狀況
	 */
	public List<Double>findResult_inv(int index,
			Double sumActualpairs,
			Double invcount,
			Double paypair
			){
		List<Double>list=new ArrayList<Double>();
		Double VWebinvB001=sumActualpairs/index;//除月數
		Double VWebinvB002=invcount/index;//除月數
		Double VWebinvB003=paypair/index;//除月數
		Double VWebinvB004=division(VWebinvB003,VWebinvB002);
		list.add(VWebinvB001);
		list.add(VWebinvB002);
		list.add(VWebinvB003);
		list.add(VWebinvB004);
		return list;
		
	}
	/**
	 * 公式方法
	 * 出貨與退貨
	 */
	public List<Double>findResult_mix1(int index,
			Double sumOutnum,
			Double sumBacknum
			){
		List<Double>list=new ArrayList<Double>();
		Double vmixC001=sumOutnum/index;
		Double vmixC002=sumBacknum/index;
		Double vmixC003=division(vmixC002,vmixC001);
		list.add(vmixC001);
		list.add(vmixC002);
		list.add(vmixC003);
		return list;
	}
	
	/**
	 * 公式方法
	 * 人員效能
	 */
	public List<Double>findResult_person(int index,
			Double personzg,
			Double personjg,
			Double timezg,
			Double timejg,
			Double addtimezg,
			Double addtimejg,
			Double leavenumzg,
			Double leavenumjg,
			Double wagezgUsd,
			Double wagejgUsd,
			Double addmoneyzg,
			Double addmoneyjg,
			Double sumStandarddemo,
			Double sumActualdemo,
			Double sumActualpairs
			){
		List<Double>list=new ArrayList<Double>();
		Double VWebperD001=sumStandarddemo/index;//除月數
		Double VWebperD002=sumActualdemo/index;//除月數
		Double VWebperD003=division(VWebperD002,VWebperD001);
		Double VWebperD004=personzg/index;//除月數
		Double VWebperD005=personjg/index;//除月數
		Double VWebperD006=(personzg+personjg)/index;//記住要要除月數
		Double VWebperD007=division(personzg,personjg);
		Double VWebperD008=division(sumActualdemo,personzg);
		Double VWebperD009=division(sumActualdemo,(personzg+personjg));
		Double VWebperD010=division(sumActualdemo,(timezg+timejg+addtimezg+addtimejg));
		Double VWebperD011=division(leavenumzg,personzg);
		Double VWebperD012=division((leavenumzg+leavenumjg),(personzg+personjg));
		Double VWebperD013=wagezgUsd/index;//除月數
		Double VWebperD014=wagejgUsd/index;//除月數
		Double VWebperD015=(wagezgUsd+wagejgUsd)/index;//除月數
		Double VWebperD016=(timezg+timejg+addtimezg+addtimejg)/index;//除月數
		Double VWebperD017=division((wagezgUsd+wagejgUsd),sumActualpairs);
		Double VWebperD018=addmoneyzg/index;//除月數
		Double VWebperD019=addmoneyjg/index;//除月數
		Double VWebperD020=(addmoneyzg+addmoneyjg)/index;//除月數
		Double VWebperD021=division(wagezgUsd,personzg);
		Double VWebperD022=division(wagejgUsd,personjg);
		Double VWebperD023=division(wagezgUsd,(wagezgUsd+wagejgUsd));
		Double VWebperD024=division(wagejgUsd,(wagezgUsd+wagejgUsd));
		Double VWebperD025=division((wagezgUsd+wagejgUsd),(personzg+personjg));
		list.add(VWebperD001);list.add(VWebperD002);
		list.add(VWebperD003);list.add(VWebperD004);
		list.add(VWebperD005);list.add(VWebperD006);
		list.add(VWebperD007);list.add(VWebperD008);
		list.add(VWebperD009);list.add(VWebperD010);
		list.add(VWebperD011);list.add(VWebperD012);
		list.add(VWebperD013);list.add(VWebperD014);
		list.add(VWebperD015);list.add(VWebperD016);
		list.add(VWebperD017);list.add(VWebperD018);
		list.add(VWebperD019);list.add(VWebperD020);
		list.add(VWebperD021);list.add(VWebperD022);
		list.add(VWebperD023);list.add(VWebperD024);
		list.add(VWebperD025);
		return list;		
	}
	
	/**
	 * 公式方法
	 * 關務損耗
	 */
	public List<Double>findResult_gw(int index,
			Double sumActualpairs,
			Double sideweit,
			Double badweit,
			Double otherbadweight,
			Double actlost,
			Double avgbuttomweight2,
			Double otherweight
			){
		List<Double>list=new ArrayList<Double>();
		Double VWebgwE001=actlost/index;//除月數
		Double VWebgwE002=(avgbuttomweight2+otherweight)/index;//記住除月數的平方
		Double VWebgwE003=VWebgwE001-VWebgwE002;
		Double VWebgwE004=division(VWebgwE003,VWebgwE001);
		Double VWebgwE005=VWebgwE003-(sideweit+badweit+otherbadweight)/index;//除月數
		Double VWebgwE006=division(VWebgwE005,VWebgwE001);
		list.add(VWebgwE001);
		list.add(VWebgwE002);
		list.add(VWebgwE003);
		list.add(VWebgwE004);
		list.add(VWebgwE005);
		list.add(VWebgwE006);
		return list;
		
	}
	
	/**
	 * 公式方法
	 * 邊料不良重量分析
	 */
	public List<Double>findResult_side(int index,
			Double sumActualpairs,
			Double sideweit,
			Double badcount,
			Double badweit,
			Double otherbadweight,
			Double avgbuttomweight,
			Double avgbuttomweight2,
			Double avgprice,
			Double otherweight
			){
		List<Double>list=new ArrayList<Double>();
		Double VWebsideF001=division(avgprice,avgbuttomweight);
		Double VWebsideF002=sideweit/index;//除月數
		Double VWebsideF003=division(sideweit,sumActualpairs)*1000;
		Double VWebsideF004=division(sideweit/index,((avgbuttomweight2+otherweight)/index+sideweit/index+badweit/index));//除月數
		Double VWebsideF005=VWebsideF002*VWebsideF001;
		Double VWebsideF006=badcount/index;//除月數
		Double VWebsideF007=badweit/index;//除月數
		Double VWebsideF008=division(badweit/index,((avgbuttomweight2+otherweight)/index+sideweit/index+badweit/index));//除月數
		Double VWebsideF009=VWebsideF007*VWebsideF001;
		Double VWebsideF010=otherbadweight/index;//除月數
		Double VWebsideF011=VWebsideF010*VWebsideF001;
		Double VWebsideF012=VWebsideF002+VWebsideF007+VWebsideF010;
		Double VWebsideF013=VWebsideF005+VWebsideF009+VWebsideF011;
		list.add(VWebsideF001);list.add(VWebsideF002);
		list.add(VWebsideF003);list.add(VWebsideF004);
		list.add(VWebsideF005);list.add(VWebsideF006);
		list.add(VWebsideF007);list.add(VWebsideF008);
		list.add(VWebsideF009);list.add(VWebsideF010);
		list.add(VWebsideF011);list.add(VWebsideF012);
		list.add(VWebsideF013);		
		return list;
	}
	
	/**
	 * 公式方法
	 * 庫存
	 */
	public List<Double>findResult_store(int index,
			Double sumhostpairs,
			Double sumsamplepairs,
			Double invcount,
			Double hostinvcount,
			Double badcount,
			Double lagStorenum,
			Double lagNoliststrorenum,
			Double lagMakestrorenum,
			Double lagOutnum,
			Double lagInnum,
			Double lagInstorenum,
			Double storenum,
			Double noliststorenum,
			Double makestorenum,
			Double outnum,
			Double innum,
			Double minusnum
			){
		List<Double>list=new ArrayList<Double>();
		Double VWebstoreG001=lagStorenum/index;//除月數
		Double VWebstoreG002=lagNoliststrorenum/index;//除月數
		Double VWebstoreG003=lagMakestrorenum/index;//除月數
		Double VWebstoreG004=lagOutnum/index;//除月數
		Double VWebstoreG005=lagInnum/index;//除月數
		Double VWebstoreG006=lagInstorenum/index;//除月數
		Double VWebstoreG007=(lagStorenum+lagMakestrorenum+lagOutnum+lagInstorenum-lagInnum)/index;//除月數
        Double VWebstoreG008=storenum/index;//除月數;
        Double VWebstoreG009=noliststorenum/index;//除月數;
        Double VWebstoreG010=makestorenum/index;//除月數;
        Double VWebstoreG011=outnum/index;//除月數;
        Double VWebstoreG012=innum/index;//除月數;
        Double VWebstoreG013=(storenum+makestorenum+outnum-innum)/index;//除月數
        Double VWebstoreG014=VWebstoreG007-VWebstoreG013;
        Double VWebstoreG015=invcount/index;//除月數
        Double VWebstoreG016=minusnum/index;//除月數;
        Double VWebstoreG017=(sumhostpairs+sumsamplepairs-hostinvcount)/index;//除月數
        Double VWebstoreG018=(minusnum-badcount)/index;//除月數
        list.add(VWebstoreG001);list.add(VWebstoreG002);                             
        list.add(VWebstoreG003);list.add(VWebstoreG004);       
        list.add(VWebstoreG005);list.add(VWebstoreG006);      
        list.add(VWebstoreG007);list.add(VWebstoreG008);
        list.add(VWebstoreG009);list.add(VWebstoreG010);
        list.add(VWebstoreG011);list.add(VWebstoreG012);
        list.add(VWebstoreG013);list.add(VWebstoreG014);
        list.add(VWebstoreG015);list.add(VWebstoreG016);
        list.add(VWebstoreG017);list.add(VWebstoreG018);
		return list;
		
	}
	
	/**
	 * 公式方法
	 * 水電油
	 */
	public List<Double>findResult_wlo(int index,
			Double sumactualdemo,
			Double waterton,
			Double waterusd,
			Double electricdu,
			Double electricusd,
			Double gaston,
			Double gasusd,
			Double oilton,
			Double oilusd
			){
		
		List<Double>list=new ArrayList<Double>();
		Double VWebwloH001=sumactualdemo/index;//除月數
		Double VWebwloH002=waterton/index;//除月數
		Double VWebwloH003=division(VWebwloH002,VWebwloH001);
		Double VWebwloH004=waterusd/index;//除月數
		Double VWebwloH005=division(VWebwloH004,VWebwloH001);
		Double VWebwloH006=electricdu/index;//除月數
		Double VWebwloH007=division(VWebwloH006,VWebwloH001);//除月數
		Double VWebwloH008=electricusd/index;//除月數;
		Double VWebwloH009=division(VWebwloH008,VWebwloH001);
		Double VWebwloH010=gaston/index;//除月數
		Double VWebwloH011=division(VWebwloH010,VWebwloH001);
		Double VWebwloH012=gasusd/index;//除月數
		Double VWebwloH013=division(VWebwloH012,VWebwloH001);
		Double VWebwloH014=oilton/index;//除月數
		Double VWebwloH015=division(VWebwloH014,VWebwloH001);
		Double VWebwloH016=oilusd/index;//除月數;
		Double VWebwloH017=division(VWebwloH016,VWebwloH001);
		list.add(VWebwloH001);list.add(VWebwloH002);
		list.add(VWebwloH003);list.add(VWebwloH004);
		list.add(VWebwloH005);list.add(VWebwloH006);
		list.add(VWebwloH007);list.add(VWebwloH008);
		list.add(VWebwloH009);list.add(VWebwloH010);
		list.add(VWebwloH011);list.add(VWebwloH012);
		list.add(VWebwloH013);list.add(VWebwloH014);
		list.add(VWebwloH015);list.add(VWebwloH016);
		list.add(VWebwloH017);
		return list;		
	}
	
	/**
	 * 公式方法
	 * 回頭料
	 */
	public List<Double>findResult_back(int index,
			Double thickused,
			Double backfeed,
			Double oilback
			){
		List<Double>list=new ArrayList<Double>();
		Double back001=thickused/index;//除月數
		Double back002=backfeed/index;//除月數
		Double back003=division(back002,back001);		
		Double back004=oilback/index;//除月數
		Double back005=division(back004,back001);		
		Double back006=back002+back004;
		Double back007=back003+back005;
		list.add(back001);list.add(back002);
		list.add(back003);list.add(back004);
		list.add(back005);list.add(back006);
		list.add(back007);		
		return list;
	}
	
	/**
	 * 公式方法
	 * 水電油
	 */
	public List<Double>findResult_webcost(int index,
			Double sumActualdemo,
			Double gluestoremoney,
			Double drugsused,
			Double leavemoney,
			Double whitenum,
			Double blacknum,
			Double gluenum,
			Double greynum,
			Double othernum
			){
		
		List<Double>list=new ArrayList<Double>();
		Double VWebwloH001=gluestoremoney/index;//除月數
		Double VWebwloH002=division(gluestoremoney*1000,sumActualdemo);
		Double VWebwloH003=drugsused/index;
		Double VWebwloH004=division(drugsused*1000,sumActualdemo);
		Double VWebwloH005=leavemoney/index;
		Double VWebwloH006=division(leavemoney,sumActualdemo);
		Double VWebwloH007=whitenum/index;
		Double VWebwloH008=blacknum/index;
		Double VWebwloH009=gluenum/index;
		Double VWebwloH010=greynum/index;
		Double VWebwloH011=othernum/index;		
		list.add(VWebwloH001);list.add(VWebwloH002);
		list.add(VWebwloH003);list.add(VWebwloH004);
		list.add(VWebwloH005);list.add(VWebwloH006);
		list.add(VWebwloH007);list.add(VWebwloH008);
		list.add(VWebwloH009);list.add(VWebwloH010);
		list.add(VWebwloH011);
		return list;		
	}
	
	
	
	
	
	/**
	 * 避免除數為0時報錯
	 */
	public Double division(Double d1,Double d2){
		Double db=0.00;
		/*if(d2!=0.00){
			db=d1/d2;
		}*/
		db=d1/d2;
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
		//System.out.print(list);
		return list;					
	}
	

	

}
