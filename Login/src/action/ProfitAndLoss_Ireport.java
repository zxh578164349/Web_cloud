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
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;

import entity.SumWebYieldData;
import entity.VSumWebmix1;
import entity.VWebmachine;
import entity.WebFact;

import services.ISumWebYieldDataServices;
import services.IVSumWebmix1Services;
import services.IVWebmachineServices;
import services.IWebFactServices;

public class ProfitAndLoss_Ireport {
	private IVWebmachineServices vwebmachineser;//機台回轉數
	private ISumWebYieldDataServices sumydateSer;//出貨與退貨(修改)
	private IWebFactServices webFactSer;
	private String factNo;
	private String year;
	

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

	public void setVwebmachineser(IVWebmachineServices vwebmachineser) {
		this.vwebmachineser = vwebmachineser;
	}
					
	public void setSumydateSer(ISumWebYieldDataServices sumydateSer) {
		this.sumydateSer = sumydateSer;
	}

	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}

	public void print() throws ParseException, IOException{
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet();
		
		HSSFFont font=wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints((short)20);
		
		HSSFCellStyle cs=wb.createCellStyle();
		cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		
		HSSFCellStyle cs_yellow=wb.createCellStyle();
		cs_yellow.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_yellow.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_yellow.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		cs_yellow.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		HSSFCellStyle cs_pink=wb.createCellStyle();
		cs_pink.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_pink.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_pink.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_pink.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_pink.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_pink.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_pink.setFillForegroundColor(IndexedColors.PINK.getIndex());
		cs_pink.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		HSSFCellStyle cs_grey=wb.createCellStyle();
		cs_grey.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_grey.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_grey.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_grey.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_grey.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_grey.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_grey.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		cs_grey.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		factNo="XS";
		year="2015";
		//String factCode="RB";
		
		/**
		 * (1)孔位數&回轉數
		 */
		
		/**********************項目******************************/
		List<String>list_col_vwebmachine=new ArrayList<String>();
		list_col_vwebmachine.add("項目/月份");
		list_col_vwebmachine.add("全廠孔位數");
		list_col_vwebmachine.add("上班天數 ");
		list_col_vwebmachine.add("總上模數");
		list_col_vwebmachine.add("平均一天上模數");
		list_col_vwebmachine.add("機台利用率");
		list_col_vwebmachine.add("平均單重(g)");
		list_col_vwebmachine.add("標準回轉數");
		list_col_vwebmachine.add("實際回轉數");
		list_col_vwebmachine.add("回轉數差異");
		list_col_vwebmachine.add("達成率(%)");
		
		/**********************項目******************************/
		
		/**********************數據源******************************/
		List<WebFact>list_fact=webFactSer.findFactById_showA(factNo);//獲取每個廠的所有廠別狀態
						
		List<String>list_month=new ArrayList<String>();				
		Calendar cal=Calendar.getInstance();
		cal.setTime(new SimpleDateFormat("yyyy").parse(year));		
		for(int i=0;i<12;i++){//start for
			if(i>1){
				cal.add(Calendar.MONTH, 1);
			}else{
				cal.add(Calendar.MONTH, i);
			}			
			String yymm=new SimpleDateFormat("yyyyMM").format(cal.getTime());
			list_month.add(yymm);							
		}//end for
		
		
		List<List<List<Double>>>list3_vwebmachine=new ArrayList<List<List<Double>>>();
		for(int h=0;h<list_fact.size();h++){//start for1
			String factCode=list_fact.get(h).getId().getFactArea();
			List<List<Double>>list1_vwebmachine=new ArrayList<List<Double>>();
			for(int i=0;i<list_month.size();i++){//start for
				String yymm=list_month.get(i);
				VWebmachine machine=vwebmachineser.findById(factNo, factCode, yymm);
				if(machine==null){
					list1_vwebmachine.add(null);
				}else{
					List<Double>list2_vwebmachine=new ArrayList<Double>();
					Double a001=machine.getVWebmcA001();
					Double a002=machine.getVWebmcA002().doubleValue();
					Double a003=machine.getVWebmcA003().doubleValue();
					Double a004=machine.getVWebmcA004().doubleValue();
					Double a005=machine.getVWebmcA005().doubleValue();
					Double a006=machine.getVWebmcA006();
					Double a008=machine.getVWebmcA008().doubleValue();
					Double a009=machine.getVWebmcA009().doubleValue();
					Double a010=machine.getVWebmcA010().doubleValue();
					Double a011=machine.getVWebmcA011().doubleValue();
					list2_vwebmachine.add(a001);list2_vwebmachine.add(a002);
					list2_vwebmachine.add(a003);list2_vwebmachine.add(a004);
					list2_vwebmachine.add(a005);list2_vwebmachine.add(a006);
					list2_vwebmachine.add(a008);list2_vwebmachine.add(a009);
					list2_vwebmachine.add(a010);list2_vwebmachine.add(a011);				
					list1_vwebmachine.add(list2_vwebmachine);
				}		
			}//end for
			list3_vwebmachine.add(list1_vwebmachine);
		}//end for1
		/**********************數據源******************************/
		
		sheet.setColumnWidth(0, 4500);
		sheet.setColumnWidth(1, 4500);
		sheet.createRow(1).createCell(0).setCellValue("回轉數與孔位數");
		sheet.getRow(1).getCell(0).setCellStyle(cs_yellow);
		
		for(int h=0;h<list_fact.size();h++){//start for1
			int temp_y=list_col_vwebmachine.size()*h+h;					
			/**********************初始化表格******************************/	
			for(int y=2+temp_y;y<list_col_vwebmachine.size()+2+temp_y;y++){
				sheet.createRow(y);
				for(int x=0;x<list_month.size()+2;x++){
					if(y==2+temp_y){
						sheet.getRow(y).createCell(x).setCellStyle(cs_grey);						
					}else if(y==2+6+temp_y){
						sheet.getRow(y).createCell(x).setCellStyle(cs_pink);
					}else{
						sheet.getRow(y).createCell(x).setCellStyle(cs);
					}								
				}
			}
			/**********************初始化表格******************************/
			
			/**********************填充數據******************************/
			List<List<Double>>list1_vwebmachine=list3_vwebmachine.get(h);
			String factCode=list_fact.get(h).getId().getFactArea();
			sheet.getRow(2+temp_y).getCell(0).setCellValue(factCode);
			CellRangeAddress cra=new CellRangeAddress(2+temp_y,(short)2+list_col_vwebmachine.size()-1+temp_y,0,(short)0);
			sheet.addMergedRegion(cra);
			
			for(int y=0;y<list_col_vwebmachine.size();y++){
				sheet.getRow(y+2+temp_y).getCell(1).setCellValue(list_col_vwebmachine.get(y));
			}
					
			for(int x=0;x<list_month.size();x++){//start for
				sheet.getRow(2+temp_y).getCell(x+2).setCellValue(list_month.get(x));
				List<Double>list=list1_vwebmachine.get(x);
				if(list==null){
					for(int y=0;y<list_col_vwebmachine.size()-1;y++){
						sheet.getRow(3+y+temp_y).getCell(x+2).setCellValue("無數據");
					}
				}else{
					for(int y=0;y<list_col_vwebmachine.size()-1;y++){
						sheet.getRow(3+y+temp_y).getCell(x+2).setCellValue(list.get(y));
					}
				}			
			}//end for
			
		}//end for1
		/**********************填充數據******************************/
		
		int height_vwebmachine=2+list_fact.size()+list_col_vwebmachine.size()*list_fact.size();//高度記錄
		
		
		/**
		 * 出貨與退貨
		 */
		/****************************项目*************************************/
		List<String>list_col_sumwebyieldate=new ArrayList<String>();
		list_col_sumwebyieldate.add("項目/月份");
		list_col_sumwebyieldate.add("出貨數");
		list_col_sumwebyieldate.add("退货数");
		list_col_sumwebyieldate.add("退貨率");		
		/****************************项目*************************************/
		
		/****************************数据源*************************************/
		List<List<List<Double>>>list3_sumwebyieldate=new ArrayList<List<List<Double>>>();
		for(int h=0;h<list_fact.size();h++){//start for1
			String factCode=list_fact.get(h).getId().getFactArea();
			List<List<Double>>list1_sumwebyieldate=new ArrayList<List<Double>>();
			for(int i=0;i<list_month.size();i++){//start for
				String yymm=list_month.get(i);
				SumWebYieldData sumydate=sumydateSer.findById(factNo, factCode, yymm);
				if(sumydate==null){
					list1_sumwebyieldate.add(null);
				}else{
					List<Double>list2_sumwebyieldate=new ArrayList<Double>();
					Double c001=sumydate.getSumOutnum().doubleValue();
					Double c002=sumydate.getSumBacknum().doubleValue();
					Double c003=this.division(c002, c001);
					list2_sumwebyieldate.add(c001);
					list2_sumwebyieldate.add(c002);
					list2_sumwebyieldate.add(c003);
					list1_sumwebyieldate.add(list2_sumwebyieldate);
				}
			}//end for
			list3_sumwebyieldate.add(list1_sumwebyieldate);
		}//end for1
		/****************************数据源*************************************/
		
		sheet.createRow(height_vwebmachine+2).createCell(0).setCellValue("出貨與退貨");
		sheet.getRow(height_vwebmachine+2).getCell(0).setCellStyle(cs_yellow);
		
		for(int h=0;h<list_fact.size();h++){//start for1
			int temp_y=list_col_sumwebyieldate.size()*h+h;	
			/**********************初始化表格******************************/	
			for(int y=0;y<list_col_sumwebyieldate.size();y++){
				sheet.createRow(y+3+temp_y+height_vwebmachine);
				for(int x=0;x<list_month.size()+2;x++){
					if(y==0){
						sheet.getRow(y+3+temp_y+height_vwebmachine).createCell(x).setCellStyle(cs_grey);
					}else{
						sheet.getRow(y+3+temp_y+height_vwebmachine).createCell(x).setCellStyle(cs);
					}
																			
				}
			}
			/**********************初始化表格******************************/
			/**********************填充數據******************************/
			List<List<Double>>list1_sumwebyieldate=list3_sumwebyieldate.get(h);
			String factCode=list_fact.get(h).getId().getFactArea();
			sheet.getRow(3+temp_y+height_vwebmachine).getCell(0).setCellValue(factCode);
			CellRangeAddress cra=new CellRangeAddress(3+temp_y+height_vwebmachine,(short)3+list_col_sumwebyieldate.size()-1+temp_y+height_vwebmachine,0,(short)0);
			sheet.addMergedRegion(cra);
			
			for(int y=0;y<list_col_sumwebyieldate.size();y++){
				sheet.getRow(y+3+temp_y+height_vwebmachine).getCell(1).setCellValue(list_col_sumwebyieldate.get(y));
			}
					
			for(int x=0;x<list_month.size();x++){//start for
				sheet.getRow(3+temp_y+height_vwebmachine).getCell(x+2).setCellValue(list_month.get(x));
				List<Double>list=list1_sumwebyieldate.get(x);
				if(list==null){
					for(int y=0;y<list_col_sumwebyieldate.size()-1;y++){
						sheet.getRow(4+y+temp_y+height_vwebmachine).getCell(x+2).setCellValue("無數據");
					}
				}else{
					for(int y=0;y<list_col_sumwebyieldate.size()-1;y++){
						sheet.getRow(4+y+temp_y+height_vwebmachine).getCell(x+2).setCellValue(list.get(y));
					}
				}			
			}//end for
			
			/**********************填充數據******************************/
		}//end for1
		
		
		OutputStream os=new FileOutputStream("d:/profitandLoss.xls");
		wb.write(os);
		os.close();
		
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
	

}
