package action;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.impl.WebFactDaoImpl;

import entity.*;
import entity.WebYielePrediction;

import services.IWebEstProductServices;
import services.IWebFactServices;
import services.IWebYieldDataServices;
import services.IWebYielePredictionServices;

public class Printer_Auto {

	private IWebYieldDataServices dataSer;
	private IWebFactServices webFactSer;
	private IWebYielePredictionServices preSer;
	private IWebEstProductServices estProSer;
	private String yymm;
	

	public String getYymm() {
		return yymm;
	}

	public void setYymm(String yymm) {
		this.yymm = yymm;
	}

	public void setPreSer(IWebYielePredictionServices preSer) {
		this.preSer = preSer;
	}

	public void setDataSer(IWebYieldDataServices dataSer) {
		this.dataSer = dataSer;
	}

	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}
	

	public void setEstProSer(IWebEstProductServices estProSer) {
		this.estProSer = estProSer;
	}

	public static String dateAdd(int days) {
		Calendar canlendar = Calendar.getInstance();
		canlendar.add(Calendar.DATE, days);
		String result = (new SimpleDateFormat("yyyyMMdd")).format(canlendar
				.getTime());
		return result;
	}

	public static String getFirstDate() {
		GregorianCalendar ger = new GregorianCalendar();
		int currenMonth = (int) ger.get(Calendar.MONTH) + 1;
		int currenYear = (int) ger.get(Calendar.YEAR);
		StringBuffer date = new StringBuffer();
		date.append(currenYear);
		if (currenMonth < 10) {
			date.append("0" + currenMonth);
		} else {
			date.append(currenMonth);
		}
		date.append("01");
		return date.toString();
	}

	public void print2() {
		XSSFWorkbook wb = new XSSFWorkbook();
		// 创建第一个sheet
		XSSFSheet sheet = wb.createSheet("產量預估與產量統計");
		sheet.setColumnWidth(0, 3800);
		// sheet.createRow(0).setHeightInPoints(50);
		// 單元格樣式
		XSSFCellStyle cs = wb.createCellStyle();
		cs.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		// 设置字体:
		// 紅字體
		XSSFCellStyle cs_font_red = wb.createCellStyle();
		XSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 10);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
		font.setColor(XSSFFont.COLOR_RED);
		cs_font_red.setFont(font);
		cs_font_red.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_font_red.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_font_red.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_font_red.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_font_red.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_font_red.setBorderTop(XSSFCellStyle.BORDER_THIN);

		// 藍字體
		XSSFCellStyle cs_font_blue = wb.createCellStyle();
		XSSFFont font_blue = wb.createFont();
		font_blue.setFontHeightInPoints((short) 10);
		font_blue.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
		font_blue.setColor(IndexedColors.BLUE.getIndex());
		cs_font_blue.setFont(font_blue);
		cs_font_blue.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_font_blue.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_font_blue.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_font_blue.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_font_blue.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_font_blue.setBorderTop(XSSFCellStyle.BORDER_THIN);

		// 綠字體
		XSSFCellStyle cs_font_green = wb.createCellStyle();
		XSSFFont font_green = wb.createFont();
		font_green.setFontHeightInPoints((short) 10);
		font_green.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
		font_green.setColor(IndexedColors.GREEN.getIndex());
		cs_font_green.setFont(font_green);
		cs_font_green.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_font_green.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_font_green.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_font_green.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_font_green.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_font_green.setBorderTop(XSSFCellStyle.BORDER_THIN);

		// 粗字體
		XSSFCellStyle cs_font = wb.createCellStyle();
		XSSFFont font_bold = wb.createFont();
		font_bold.setFontHeightInPoints((short) 10);
		font_bold.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		cs_font.setFont(font_bold);
		cs_font.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_font.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_font.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_font.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_font.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_font.setBorderTop(XSSFCellStyle.BORDER_THIN);

		// 標題字體
		XSSFCellStyle cs_head = wb.createCellStyle();
		XSSFFont font_head = wb.createFont();
		font_head.setFontHeightInPoints((short) 20);
		font_head.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		cs_head.setFont(font_head);
		cs_head.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_head.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_head.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_head.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_head.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_head.setBorderTop(XSSFCellStyle.BORDER_THIN);

		// 粗字體和黃色背景
		XSSFCellStyle cs_font_bgyellow = wb.createCellStyle();
		XSSFFont font_bold_bgyellow = wb.createFont();
		font_bold_bgyellow.setFontHeightInPoints((short) 10);
		font_bold_bgyellow.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		cs_font_bgyellow.setFont(font_bold_bgyellow);
		cs_font_bgyellow.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_font_bgyellow.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_font_bgyellow.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_font_bgyellow.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_font_bgyellow.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_font_bgyellow.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_font_bgyellow
				.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		cs_font_bgyellow.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

		// 粗字體和淺黃色背景
		XSSFCellStyle cs_font_bgyellow2 = wb.createCellStyle();
		XSSFFont font_bold_bgyellow2 = wb.createFont();
		font_bold_bgyellow2.setFontHeightInPoints((short) 10);
		font_bold_bgyellow2.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);// 粗体显示
		cs_font_bgyellow2.setFont(font_bold_bgyellow2);
		cs_font_bgyellow2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_font_bgyellow2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_font_bgyellow2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_font_bgyellow2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_font_bgyellow2.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_font_bgyellow2.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_font_bgyellow2.setFillForegroundColor(IndexedColors.LIGHT_YELLOW
				.getIndex());
		cs_font_bgyellow2.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

		// 紅粗字體
		XSSFCellStyle cs_font_red_bold = wb.createCellStyle();
		XSSFFont font_red_bold = wb.createFont();
		font_red_bold.setFontHeightInPoints((short) 10);
		font_red_bold.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		font_red_bold.setColor(XSSFFont.COLOR_RED);
		cs_font_red_bold.setFont(font_red_bold);
		cs_font_red_bold.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_font_red_bold.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_font_red_bold.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_font_red_bold.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_font_red_bold.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_font_red_bold.setBorderTop(XSSFCellStyle.BORDER_THIN);

		// 數字格式
		XSSFDataFormat format_poi = wb.createDataFormat();
		XSSFCellStyle cs_data = wb.createCellStyle();
		cs_data.setDataFormat(format_poi.getFormat("0.0%"));
		cs_data.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_data.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_data.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_data.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_data.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_data.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

		XSSFCellStyle cs_thousand = wb.createCellStyle();
		cs_thousand.setDataFormat(format_poi.getFormat("#,##0.0"));
		cs_thousand.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_thousand.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_thousand.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_thousand.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_thousand.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_thousand.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

		// 帶黃背景顏色
		XSSFCellStyle cs_thousand_lyellow = wb.createCellStyle();
		cs_thousand_lyellow.setDataFormat(format_poi.getFormat("#,##0.0"));
		cs_thousand_lyellow.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_thousand_lyellow.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_thousand_lyellow.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_thousand_lyellow.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_thousand_lyellow.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_thousand_lyellow.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_thousand_lyellow.setFillForegroundColor(IndexedColors.LIGHT_YELLOW
				.getIndex());
		cs_thousand_lyellow.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

		XSSFCellStyle cs_thousand_person = wb.createCellStyle();
		cs_thousand_person.setDataFormat(format_poi.getFormat("#,##0"));
		cs_thousand_person.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_thousand_person.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_thousand_person.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_thousand_person.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_thousand_person.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_thousand_person.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

		List list_factNo = webFactSer.findAllFact();
		List<String> factcodelist = webFactSer.findAllFactCode();
		GregorianCalendar ger = new GregorianCalendar();
		int currenMonth = (int) ger.get(Calendar.MONTH) + 1;
		int currenYear = (int) ger.get(Calendar.YEAR);

		DateFormat format = new SimpleDateFormat("yyyyMM");
		String date = format.format(new Date());

		List<Webestproduct> alllist = new ArrayList();
		List<List<WebYieldData>> alllist2 = new ArrayList();
		List<String> factnoCodelist = new ArrayList();
		for (int factno = 0; factno < list_factNo.size(); factno++) {// start
																		// "for"
			List factAreas = webFactSer
					.findFactCodeByFactNo(((Object[]) list_factNo.get(factno))[0]
							.toString());
			String factSname = webFactSer.selByid(((Object[]) list_factNo
					.get(factno))[0].toString());
			for (int factcode = 0; factcode < factAreas.size(); factcode++) {
				List<Webestproduct> list = estProSer.findByFactcode(
						((Object[]) list_factNo.get(factno))[0].toString(),
						(String) factAreas.get(factcode), date);
				List<WebYieldData> list2 = dataSer.findDataByFactcode(
						((Object[]) list_factNo.get(factno))[0].toString(),
						(String) factAreas.get(factcode), date);
				List<Webestproduct> listnull = estProSer.findNullYpre(
						((Object[]) list_factNo.get(factno))[0].toString(),
						(String) factAreas.get(factcode), date);
				List<WebYieldData> listnull2 = dataSer.findNullYdata(
						((Object[]) list_factNo.get(factno))[0].toString(),
						(String) factAreas.get(factcode), date);
				StringBuffer factnoCode = new StringBuffer();
				Webestproduct pre = null;
				WebYieldData ydata = null;
				if (list.size() > 0 && listnull.size() == 0 || list2.size() > 0
						&& listnull2.size() == 0 || list2.size() > 0
						&& listnull2.size() > 0) {
					factnoCode.append(factSname);
					factnoCode.append("("
							+ ((Object[]) list_factNo.get(factno))[0]
									.toString() + ")-");
					factnoCode.append((String) factAreas.get(factcode));
					factnoCodelist.add(factnoCode.toString());
					if (list2.size() == 0) {
						ydata = new WebYieldData();
						list2.add(ydata);
					}
					if (list.size() == 0) {
						pre = new Webestproduct();
						alllist.add(pre);
					}
					if (list.size() > 0) {
						pre = list.get(0);
						alllist.add(pre);
					}
					if (list2.size() > 0) {
						alllist2.add(list2);
					}
				}

				if (list.size() == 0 && list2.size() == 0) {
					factnoCode.append(factSname);
					factnoCode.append("("
							+ ((Object[]) list_factNo.get(factno))[0]
									.toString() + ")-");
					factnoCode.append((String) factAreas.get(factcode));
					factnoCode.append("還沒有添加數據!!");
					factnoCodelist.add(factnoCode.toString());
					pre = new Webestproduct();
					ydata = new WebYieldData();
					alllist.add(pre);
					list2.add(ydata);
					alllist2.add(list2);
				}
				if (listnull.size() > 0 && listnull2.size() > 0) {
					factnoCode.append(factSname);
					factnoCode.append("("
							+ ((Object[]) list_factNo.get(factno))[0]
									.toString() + ")_");
					factnoCode.append((String) factAreas.get(factcode));
					factnoCode.append("還沒有添加數據!!");
					factnoCodelist.add(factnoCode.toString());
					pre = list.get(0);
					alllist.add(pre);
					alllist2.add(list2);
				}
				if (list.size() > 0 && listnull.size() > 0 && list2.size() == 0) {
					factnoCode.append(factSname);
					factnoCode.append("("
							+ ((Object[]) list_factNo.get(factno))[0]
									.toString() + ")_");
					factnoCode.append((String) factAreas.get(factcode));
					factnoCode.append("還沒有添加數據!!");
					factnoCodelist.add(factnoCode.toString());
					ydata = new WebYieldData();
					pre = list.get(0);
					list2.add(ydata);
					alllist.add(pre);
					alllist2.add(list2);
				}
			}
		}// end "for"

		int z_length = 0;
		if (currenMonth == 1 || currenMonth == 3 || currenMonth == 5
				|| currenMonth == 7 || currenMonth == 8 || currenMonth == 10
				|| currenMonth == 12) {
			z_length = 32 + 1;
		}
		if (currenMonth == 4 || currenMonth == 6 || currenMonth == 9
				|| currenMonth == 11) {
			z_length = 31 + 1;
		}
		if (currenMonth == 2) {
			if (currenYear % 4 == 0 && currenYear % 100 != 0
					|| currenYear % 400 == 0) {
				z_length = 30 + 1;
			} else {
				z_length = 29 + 1;
			}
		}
		int totalHeight = 1;
		int height = 0;
		// ********上部********//
		int width = 0;
		/*
		 * 標題設置
		 */
		sheet.createRow(0 + totalHeight - 1).setHeightInPoints(50);// 設置標題高度
		CellRangeAddress reg_head = new CellRangeAddress(0 + totalHeight - 1,
				(short) 0 + totalHeight - 1, 0, (short) alllist.size() * 5);
		sheet.addMergedRegion(reg_head);
		sheet.getRow(0 + totalHeight - 1).createCell(0)
				.setCellValue(date + "加久各工廠每日產量達成狀況匯總表");
		sheet.getRow(0 + totalHeight - 1).getCell(0).setCellStyle(cs_head);
		for (int i_head = 1; i_head < alllist.size() * 5 + 1; i_head++) {
			sheet.getRow(0 + totalHeight - 1).createCell(i_head)
					.setCellStyle(cs_head);
		}

		for (int i = 0; i < alllist.size() + factcodelist.size(); i++) {// for 1
			if (i < alllist.size()) { // start "if_top"
				List<String> cols = new ArrayList();
				cols.add("日期/產量/廠別");
				cols.add("戰力分析模數");
				cols.add("預計生產模數");
				cols.add("預計請款雙數");
				cols.add("機台孔位數");
				cols.add("總機孔");
				cols.add("有效孔位");
				cols.add("工程/樣品");
				cols.add("補料孔位");
				cols.add("其他");
				if (i > 0) {
					width = width - 1;
				}
				for (int j = 0; j < cols.size(); j++) { // for 2
					if (j == 5) {
						break;
					}
					if (i == 0) {
						if (j < 5) {
							sheet.createRow(j + totalHeight)
									.createCell(0 + width)
									.setCellValue(cols.get(j));
							sheet.getRow(j + totalHeight).getCell(0 + width)
									.setCellStyle(cs);
						}
						sheet.getRow(j + totalHeight).createCell(1 + width);
						sheet.getRow(j + totalHeight).getCell(1 + width)
								.setCellStyle(cs_thousand);
						if (j == 4) {
							CellRangeAddress region1 = new CellRangeAddress(j
									+ totalHeight, (short) j + totalHeight + 3,
									0 + width, (short) 0 + width);
							sheet.addMergedRegion(region1);
							sheet.getRow(j + totalHeight).getCell(0 + width)
									.setCellStyle(cs_font_bgyellow2);
							sheet.createRow(j + totalHeight + 1)
									.createCell(0 + width)
									.setCellStyle(cs_font_bgyellow2);
							sheet.createRow(j + totalHeight + 2)
									.createCell(0 + width)
									.setCellStyle(cs_font_bgyellow2);
							sheet.createRow(j + totalHeight + 3)
									.createCell(0 + width)
									.setCellStyle(cs_font_bgyellow2);
						}
					} else {
						if (j < 5) {
							sheet.getRow(j + totalHeight).createCell(0 + width)
									.setCellValue(cols.get(j));
							sheet.getRow(j + totalHeight).getCell(0 + width)
									.setCellStyle(cs);
						}
						sheet.getRow(j + totalHeight).createCell(1 + width);
						sheet.getRow(j + totalHeight).getCell(1 + width)
								.setCellStyle(cs_thousand);
						if (j == 4) {
							CellRangeAddress region1 = new CellRangeAddress(j
									+ totalHeight, (short) j + totalHeight + 3,
									0 + width, (short) 0 + width);
							sheet.addMergedRegion(region1);
							sheet.getRow(j + totalHeight).getCell(0 + width)
									.setCellStyle(cs_font_bgyellow2);
							sheet.getRow(j + totalHeight + 1)
									.createCell(0 + width)
									.setCellStyle(cs_font_bgyellow2);
							sheet.getRow(j + totalHeight + 2)
									.createCell(0 + width)
									.setCellStyle(cs_font_bgyellow2);
							sheet.getRow(j + totalHeight + 3)
									.createCell(0 + width)
									.setCellStyle(cs_font_bgyellow2);
						}
					}

					switch (j) {
					case 0:
						String factNoAndCode = factnoCodelist.get(i);
						// sheet.setColumnWidth(1+width, 4000);
						sheet.getRow(j + totalHeight).setHeightInPoints(40);
						sheet.getRow(j + totalHeight).getCell(1 + width)
								.setCellValue(factNoAndCode);
						CellRangeAddress region1 = new CellRangeAddress(j
								+ totalHeight, (short) j + totalHeight,
								1 + width, (short) 5 + width);
						sheet.addMergedRegion(region1);
						if (factNoAndCode.contains("沒有添加數據")) {
							sheet.getRow(j + totalHeight).getCell(1 + width)
									.setCellStyle(cs_font_red_bold);
						} else {
							sheet.getRow(j + totalHeight).getCell(1 + width)
									.setCellStyle(cs_font);
						}
						sheet.getRow(j + totalHeight).createCell(2 + width)
								.setCellStyle(cs);
						sheet.getRow(j + totalHeight).createCell(3 + width)
								.setCellStyle(cs);
						sheet.getRow(j + totalHeight).createCell(4 + width)
								.setCellStyle(cs);
						sheet.getRow(j + totalHeight).createCell(5 + width)
								.setCellStyle(cs);
						// sheet.getRow(j + totalHeight).createCell(6 +
						// width).setCellStyle(cs);
						// sheet.getRow(j+totalHeight).createCell(7+width).setCellStyle(cs);
						break;
					case 1:
						Double force = (alllist.get(i)).getMachinepower();

						if (force != null) {
							sheet.getRow(j + totalHeight).getCell(1 + width)
									.setCellValue(force);
						} else {
							sheet.getRow(j + totalHeight).getCell(1 + width)
									.setCellValue("");
						}
						CellRangeAddress region2 = new CellRangeAddress(j
								+ totalHeight, (short) j + totalHeight,
								1 + width, (short) 5 + width);
						sheet.addMergedRegion(region2);
						sheet.getRow(j + totalHeight).createCell(2 + width)
								.setCellStyle(cs);
						sheet.getRow(j + totalHeight).createCell(3 + width)
								.setCellStyle(cs);
						sheet.getRow(j + totalHeight).createCell(4 + width)
								.setCellStyle(cs);
						sheet.getRow(j + totalHeight).createCell(5 + width)
								.setCellStyle(cs);
						// sheet.getRow(j + totalHeight).createCell(6 +
						// width).setCellStyle(cs);
						// sheet.getRow(j+totalHeight).createCell(7+width).setCellStyle(cs);
						break;
					case 2:
						Double expect = (alllist.get(i))
								.getEstmodel();

						if (expect != null) {
							sheet.getRow(j + totalHeight).getCell(1 + width)
									.setCellValue(expect);
						} else {
							sheet.getRow(j + totalHeight).getCell(1 + width)
									.setCellValue("");
						}
						CellRangeAddress region3 = new CellRangeAddress(j
								+ totalHeight, (short) j + totalHeight,
								1 + width, (short) 5 + width);
						sheet.addMergedRegion(region3);
						sheet.getRow(j + totalHeight).createCell(2 + width)
								.setCellStyle(cs);
						sheet.getRow(j + totalHeight).createCell(3 + width)
								.setCellStyle(cs);
						sheet.getRow(j + totalHeight).createCell(4 + width)
								.setCellStyle(cs);
						sheet.getRow(j + totalHeight).createCell(5 + width)
								.setCellStyle(cs);
						// sheet.getRow(j + totalHeight).createCell(6 +
						// width).setCellStyle(cs);
						// sheet.getRow(j+totalHeight).createCell(7+width).setCellStyle(cs);
						break;
					case 3:
						Double expPay = (alllist.get(i)).getEstpay();

						if (expPay != null) {
							sheet.getRow(j + totalHeight).getCell(1 + width)
									.setCellValue(expPay);
						} else {
							sheet.getRow(j + totalHeight).getCell(1 + width)
									.setCellValue("");
						}
						CellRangeAddress region4 = new CellRangeAddress(j
								+ totalHeight, (short) j + totalHeight,
								1 + width, (short) 5 + width);
						sheet.addMergedRegion(region4);
						sheet.getRow(j + totalHeight).createCell(2 + width)
								.setCellStyle(cs);
						sheet.getRow(j + totalHeight).createCell(3 + width)
								.setCellStyle(cs);
						sheet.getRow(j + totalHeight).createCell(4 + width)
								.setCellStyle(cs);
						sheet.getRow(j + totalHeight).createCell(5 + width)
								.setCellStyle(cs);
						// sheet.getRow(j + totalHeight).createCell(6 +
						// width).setCellStyle(cs);
						// sheet.getRow(j+totalHeight).createCell(7+width).setCellStyle(cs);
						break;
					case 4:
						sheet.getRow(j + totalHeight).getCell(1 + width)
								.setCellValue(cols.get(j + 1));
						CellRangeAddress hole_cell = new CellRangeAddress(j
								+ totalHeight, j + totalHeight + 3, 1 + width,
								1 + width);
						sheet.addMergedRegion(hole_cell);
						sheet.getRow(j + totalHeight).getCell(1 + width)
								.setCellStyle(cs_font_bgyellow2);
						sheet.getRow(j + totalHeight + 1).createCell(1 + width)
								.setCellStyle(cs_font_bgyellow2);
						sheet.getRow(j + totalHeight + 2).createCell(1 + width)
								.setCellStyle(cs_font_bgyellow2);
						sheet.getRow(j + totalHeight + 3).createCell(1 + width)
								.setCellStyle(cs_font_bgyellow2);
						CellRangeAddress hole_cell_value = new CellRangeAddress(
								j + totalHeight, j + totalHeight + 3,
								2 + width, 2 + width);
						sheet.addMergedRegion(hole_cell_value);
						sheet.getRow(j + totalHeight + 1).createCell(2 + width)
								.setCellStyle(cs_font_bgyellow2);
						sheet.getRow(j + totalHeight + 2).createCell(2 + width)
								.setCellStyle(cs_font_bgyellow2);
						sheet.getRow(j + totalHeight + 3).createCell(2 + width)
								.setCellStyle(cs_font_bgyellow2);
						Double hole = (alllist.get(i)).getTotalhole();//--------------------------------------------總機孔
						if (hole != null) {
							sheet.getRow(j + totalHeight).createCell(2 + width)
									.setCellValue(hole);
						} else {
							sheet.getRow(j + totalHeight).createCell(2 + width)
									.setCellValue("");
						}
						sheet.getRow(j + totalHeight).getCell(2 + width)
								.setCellStyle(cs_thousand_lyellow);
						sheet.getRow(j + totalHeight).createCell(3 + width)
								.setCellValue(cols.get(j + 2));

						Double posNum = (alllist.get(i)).getHole();//--------------------------------------------有效孔位數

						if (posNum != null) {
							sheet.getRow(j + totalHeight).createCell(4 + width)
									.setCellValue(posNum);
						} else {
							sheet.getRow(j + totalHeight).createCell(4 + width)
									.setCellValue("");
						}
						sheet.getRow(j + totalHeight).getCell(3 + width)
								.setCellStyle(cs_font_bgyellow2);
						sheet.getRow(j + totalHeight).getCell(4 + width)
								.setCellStyle(cs_thousand_lyellow);
						CellRangeAddress posNum_cell_value = new CellRangeAddress(
								j + totalHeight, j + totalHeight, 4 + width,
								5 + width);
						sheet.addMergedRegion(posNum_cell_value);
						sheet.getRow(j + totalHeight).createCell(5 + width)
								.setCellStyle(cs_font_bgyellow2);
						// sheet.getRow(j + totalHeight).createCell(6 +
						// width).setCellStyle(cs);
						// sheet.getRow(j+totalHeight).createCell(7+width).setCellStyle(cs);
						String temp = cols.get(j + 3);
						sheet.getRow(j + totalHeight + 1).createCell(3 + width)
								.setCellValue(temp);
						Double sample = (alllist.get(i)).getSample();
						if (sample != null) {
							sheet.getRow(j + totalHeight + 1)
									.createCell(4 + width).setCellValue(sample);
						} else {
							sheet.getRow(j + totalHeight + 1)
									.createCell(4 + width).setCellValue("");
						}
						sheet.getRow(j + totalHeight + 1).getCell(3 + width)
								.setCellStyle(cs_font_bgyellow2);
						sheet.getRow(j + totalHeight + 1).getCell(4 + width)
								.setCellStyle(cs_thousand_lyellow);
						CellRangeAddress sample_cell_value = new CellRangeAddress(
								j + totalHeight + 1, j + totalHeight + 1,
								4 + width, 5 + width);
						sheet.addMergedRegion(sample_cell_value);
						sheet.getRow(j + totalHeight + 1).createCell(5 + width)
								.setCellStyle(cs_font_bgyellow2);
						// sheet.getRow(j + totalHeight + 1).createCell(6 +
						// width).setCellStyle(cs);
						// sheet.getRow(j+totalHeight+1).createCell(7+width).setCellStyle(cs);
						sheet.getRow(j + totalHeight + 2).createCell(3 + width)
								.setCellValue(cols.get(j + 4));
						Double acce = (alllist.get(i)).getAccessories();
						if (acce != null) {
							sheet.getRow(j + totalHeight + 2)
									.createCell(4 + width).setCellValue(acce);
						} else {
							sheet.getRow(j + totalHeight + 2)
									.createCell(4 + width).setCellValue("");
						}
						sheet.getRow(j + totalHeight + 2).getCell(3 + width)
								.setCellStyle(cs_font_bgyellow2);

						sheet.getRow(j + totalHeight + 2).getCell(4 + width)
								.setCellStyle(cs_thousand_lyellow);
						CellRangeAddress acce_cell_value = new CellRangeAddress(
								j + totalHeight + 2, j + totalHeight + 2,
								4 + width, 5 + width);
						sheet.addMergedRegion(acce_cell_value);
						sheet.getRow(j + totalHeight + 2).createCell(5 + width)
								.setCellStyle(cs_font_bgyellow2);
						// sheet.getRow(j + totalHeight + 2).createCell(6 +
						// width).setCellStyle(cs);
						// sheet.getRow(j+totalHeight+2).createCell(7+width).setCellStyle(cs);
						sheet.getRow(j + totalHeight + 3).createCell(3 + width)
								.setCellValue(cols.get(j + 5));
						Double other = (alllist.get(i)).getOther();
						if (other != null) {
							sheet.getRow(j + totalHeight + 3)
									.createCell(4 + width).setCellValue(other);
						} else {
							sheet.getRow(j + totalHeight + 3)
									.createCell(4 + width).setCellValue("");
						}
						sheet.getRow(j + totalHeight + 3).getCell(3 + width)
								.setCellStyle(cs_font_bgyellow2);
						sheet.getRow(j + totalHeight + 3).getCell(4 + width)
								.setCellStyle(cs_thousand_lyellow);
						CellRangeAddress other_cell_value = new CellRangeAddress(
								j + totalHeight + 3, j + totalHeight + 3,
								4 + width, 5 + width);
						sheet.addMergedRegion(other_cell_value);
						sheet.getRow(j + totalHeight + 3).createCell(5 + width)
								.setCellStyle(cs_font_bgyellow2);
						// sheet.getRow(j + totalHeight + 3).createCell(6 +
						// width).setCellStyle(cs);
						// sheet.getRow(j+totalHeight+3).createCell(7+width).setCellStyle(cs);
						break;
					}
				} // end for 2
				width = width + 6;
			}// end "if_top"

			if (i >= alllist.size()) { // start "if top2"
				String temp = factcodelist.get(factcodelist.size()
						- (alllist.size() + factcodelist.size() - (i + 1)) - 1);
				CellRangeAddress region1 = new CellRangeAddress(
						6 + totalHeight, (short) 7 + totalHeight, width,
						(short) 1 + width);
				sheet.addMergedRegion(region1);
				sheet.getRow(6 + totalHeight).createCell(width)
						.setCellStyle(cs_font_bgyellow);
				sheet.getRow(6 + totalHeight).getCell(width).setCellValue(temp);
				sheet.getRow(6 + totalHeight).createCell(1 + width)
						.setCellStyle(cs_font_bgyellow);
				sheet.getRow(7 + totalHeight).createCell(width)
						.setCellStyle(cs_font_bgyellow);
				sheet.getRow(7 + totalHeight).createCell(1 + width)
						.setCellStyle(cs_font_bgyellow);
				width = width + 2;
			}// end "if top2"
		}// end for 1

		height = 8;
		width = 0;
		// **********下部*******//
		for (int x = 0; x < alllist2.size() + factcodelist.size(); x++) { // for
																			// 1
			List<String> cols = new ArrayList();
			List<Object[]> sum_list = new ArrayList();
			if (x < alllist2.size()) {
				cols.add("日期");
				cols.add("上模數");
				cols.add("人數");
				cols.add("標準產量");
				cols.add("實際產量");
				cols.add("達成率(%)");
				// cols.add("天數");
				// cols.add("工作日/假日");
			}

			if (x >= alllist2.size()) {
				cols.add("日期");
				cols.add("標準產量");
				cols.add("實際產量");
				String temp = factcodelist
						.get(factcodelist.size()
								- (alllist2.size() + factcodelist.size() - (x + 1))
								- 1);
				for (int x2 = 0; x2 < 31; x2++) {
					StringBuffer yymmdd = new StringBuffer();
					yymmdd.append(date);
					if ((x2 + 1) < 10) {
						yymmdd.append("0" + (x2 + 1));
					} else {
						yymmdd.append(x2 + 1);
					}
					Object[] list_temp = dataSer.totalWithFactCode(temp,
							yymmdd.toString());
					sum_list.add(list_temp);
				}
			}

			double sumStandardOutput = 0;// 標準產量合計
			double sumActualYield = 0;// 實際產量合計
			double sumAchievingRate = 0;// 月達成率
			double sum_standardoutput_all = 0;// 標準產量(廠別狀態統計)
			double sum_actualyield_all = 0;// 實際產量(廠別狀態統計)

			if (x < alllist2.size()) {// start "if 無廠別狀態統計"
				for (int y = 0; y < cols.size(); y++) { // for 2
					if (x == 0) {
						if (y == 0) {
							sheet.createRow(height + totalHeight)
									.createCell(y + width)
									.setCellValue(cols.get(y));
						} else {
							sheet.getRow(height + totalHeight)
									.createCell(y + width)
									.setCellValue(cols.get(y));
						}
						sheet.getRow(height + totalHeight).getCell(y + width)
								.setCellStyle(cs);
					}
					if (x > 0) {
						if (y == 0) {
							continue;
						}
						sheet.getRow(height + totalHeight)
								.createCell(y + width)
								.setCellValue(cols.get(y));
						sheet.getRow(height + totalHeight).getCell(y + width)
								.setCellStyle(cs);
					}

					double sumOnModulus = 0;// 上模數合計
					double sumPersonnum = 0;// 人數合計
					double sumDayCount = 0;// 總天數
					if (sumStandardOutput != 0) {
						sumAchievingRate = sumActualYield / sumStandardOutput;
					}

					/*
					 * int z_length=0;
					 * if(all==1||all==3||all==5||all==7||all==8||
					 * all==10||all==12){ z_length=32; }else{ z_length=31; }
					 */
					for (int z = 0; z < z_length; z++) {// for 3
						int day = 0;
						Double onModulus = null;
						Double personnum = null;
						Double standardOutput = null;
						Double actualYield = null;
						Double achievingRate = null;
						Double daycount = null;
						String holiday = null;
						switch (y) {
						case 0:
							StringBuffer date_month = new StringBuffer();
							// date.append(year + "/");
							if (currenMonth < 10) {
								date_month.append("0" + currenMonth + "/");
							} else {
								date_month.append(currenMonth + "/");
							}
							if (z + 1 < 10) {
								date_month.append("0" + (z + 1));
							} else {
								date_month.append(z + 1);
							}
							if (z < z_length - 2) {
								sheet.createRow(z + height + totalHeight + 1)
										.createCell(y + width)
										.setCellValue(date_month.toString());
								sheet.getRow(z + height + totalHeight + 1)
										.getCell(y + width).setCellStyle(cs);
							}
							if (z == z_length - 2) {
								sheet.createRow(z + height + totalHeight + 1)
										.createCell(y + width)
										.setCellValue("合計");
								sheet.getRow(z + height + totalHeight + 1)
										.getCell(y + width)
										.setCellStyle(cs_font);
							}
							if (z == z_length - 1) {
								sheet.createRow(z + height + totalHeight + 1)
										.createCell(y + width)
										.setCellValue("工作天數");
								sheet.getRow(z + height + totalHeight + 1)
										.getCell(y + width)
										.setCellStyle(cs_font);

							}
							/*
							 * else { sheet.createRow(z + height + totalHeight +
							 * 1).createCell(y +
							 * width).setCellValue(date.toString());
							 * sheet.getRow(z + height + totalHeight +
							 * 1).getCell(y + width).setCellStyle(cs); }
							 */
							break;
						case 1:
							if (sheet.getRow(z + height + totalHeight + 1)
									.getCell(y + width) == null) {
								// sheet.getRow(z+height+totalHeight+1).createCell(y+width).setCellStyle(cs);
								StringBuffer olddate = new StringBuffer();
								SimpleDateFormat dateFormat = new SimpleDateFormat(
										"yyyyMMdd");
								olddate.append(date);
								if (z == 31) {
									olddate.append(z);
								}
								if (z < 9) {
									olddate.append("0");
									olddate.append(z + 1);
								}
								if (z >= 9) {
									olddate.append(z + 1);
								}

								String temp1 = olddate.toString();
								String temp2 = dateFormat.format(new Date());
								try {
									Date beginDate = dateFormat.parse(temp1);
									Date endDate = dateFormat.parse(temp2);
									long beginNum = beginDate.getTime();
									long endNum = endDate.getTime();
									if (beginNum > endNum) {
										sheet.getRow(
												z + height + totalHeight + 1)
												.createCell(y + width)
												.setCellStyle(cs_font_blue);
										// sheet.getRow(z+height+totalHeight+1).getCell(y+width).setCellValue("日期超前");
									} else {
										sheet.getRow(
												z + height + totalHeight + 1)
												.createCell(y + width)
												.setCellStyle(cs_font_red);
										sheet.getRow(
												z + height + totalHeight + 1)
												.getCell(y + width)
												.setCellValue("無數據");
									}
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							try {
								day = alllist2.get(x).get(z).getId()
										.getYymmdd().getDate();
								onModulus = alllist2.get(x).get(z)
										.getOnModulus();
								personnum = alllist2.get(x).get(z)
										.getPersonnum();
								daycount = alllist2.get(x).get(z).getDaycount();
								achievingRate = alllist2.get(x).get(z)
										.getAchievingRate();
								holiday = alllist2.get(x).get(z)
										.getWorkorholiday();
								if (onModulus != null && z == day - 1) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue(onModulus);
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_thousand);
								}
								if (onModulus != null && z != day - 1) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue(onModulus);
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_thousand);
								}
								if (onModulus == null && z == day - 1
										&& holiday.equals("1")) {

									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue("假日");
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_font_blue);
								}

								if (onModulus == null && z != day - 1
										&& holiday.equals("1")) {

									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue("假日");
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_font_blue);
								}
								if (onModulus == null && z == day - 1
										&& holiday.equals("2")) {

									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue("未排產");
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_font_green);
								}

								if (onModulus == null && z != day - 1
										&& holiday.equals("2")) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue("未排產");
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_font_green);
								}
								if (onModulus == null
										&& z == day - 1
										&& (personnum != null
												|| daycount != null || achievingRate != null)) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue("無數據");
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_font_red);
								}

								if (onModulus == null
										&& z != day - 1
										&& (personnum != null
												|| daycount != null || achievingRate != null)) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue("無數據");
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_font_red);
								}
								sumOnModulus = sumOnModulus + onModulus;
							} catch (Exception e) {
								// TODO: handle exception
							}
							if (z == z_length - 2) {
								sheet.getRow(z + height + totalHeight + 1)
										.createCell(y + width)
										.setCellValue(sumOnModulus);
								sheet.getRow(z + height + totalHeight + 1)
										.getCell(y + width)
										.setCellStyle(cs_thousand);
							}

							break;
						case 2:
							if (sheet.getRow(z + height + totalHeight + 1)
									.getCell(y + width) == null) {
								// sheet.getRow(z+height+totalHeight+1).createCell(y+width).setCellStyle(cs);
								StringBuffer olddate = new StringBuffer();
								SimpleDateFormat dateFormat = new SimpleDateFormat(
										"yyyyMMdd");
								olddate.append(date);
								if (z == 31) {
									olddate.append(z);
								}
								if (z < 9) {
									olddate.append("0");
									olddate.append(z + 1);
								}
								if (z >= 9) {
									olddate.append(z + 1);
								}

								String temp1 = olddate.toString();
								String temp2 = dateFormat.format(new Date());

								try {
									Date beginDate = dateFormat.parse(temp1);

									Date endDate = dateFormat.parse(temp2);

									long beginNum = beginDate.getTime();
									long endNum = endDate.getTime();
									if (beginNum > endNum) {
										sheet.getRow(
												z + height + totalHeight + 1)
												.createCell(y + width)
												.setCellStyle(cs_font_blue);
										// sheet.getRow(z+height+totalHeight+1).getCell(y+width).setCellValue("日期超前");
									} else {
										sheet.getRow(
												z + height + totalHeight + 1)
												.createCell(y + width)
												.setCellStyle(cs_font_red);
										sheet.getRow(
												z + height + totalHeight + 1)
												.getCell(y + width)
												.setCellValue("無數據");
									}
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							// Double personnum=null;
							try {
								day = alllist2.get(x).get(z).getId()
										.getYymmdd().getDate();

								personnum = alllist2.get(x).get(z)
										.getPersonnum();

								onModulus = alllist2.get(x).get(z)
										.getOnModulus();

								daycount = alllist2.get(x).get(z).getDaycount();

								achievingRate = alllist2.get(x).get(z)
										.getAchievingRate();

								holiday = alllist2.get(x).get(z)
										.getWorkorholiday();

								if (personnum != null && z == day - 1) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue(personnum);
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_thousand_person);
								}
								if (personnum != null && z != day - 1) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue(personnum);
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_thousand_person);
								}
								if (personnum == null && z == day - 1
										&& holiday.equals("1")) {

									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue("假日");
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_font_blue);
								}

								if (personnum == null && z != day - 1
										&& holiday.equals("1")) {

									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue("假日");
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_font_blue);
								}
								if (personnum == null && z == day - 1
										&& holiday.equals("2")) {

									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue("未排產");
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_font_green);
								}

								if (personnum == null && z != day - 1
										&& holiday.equals("2")) {

									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue("未排產");
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_font_green);
								}
								if (personnum == null
										&& z == day - 1
										&& (onModulus != null
												|| daycount != null || achievingRate != null)) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue("無數據");
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_font_red);
								}
								if (personnum == null
										&& z != day - 1
										&& (onModulus != null
												|| daycount != null || achievingRate != null)) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue("無數據");
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_font_red);
								}
								sumPersonnum = sumPersonnum + personnum;

							} catch (Exception e) {
								// TODO: handle exception
							}
							if (z == z_length - 2) {
								sheet.getRow(z + height + totalHeight + 1)
										.createCell(y + width)
										.setCellValue(sumPersonnum);
								sheet.getRow(z + height + totalHeight + 1)
										.getCell(y + width)
										.setCellStyle(cs_thousand_person);
							}
							break;
						case 3:
							if (sheet.getRow(z + height + totalHeight + 1)
									.getCell(y + width) == null) {
								// sheet.getRow(z+height+totalHeight+1).createCell(y+width).setCellStyle(cs);
								StringBuffer olddate = new StringBuffer();
								SimpleDateFormat dateFormat = new SimpleDateFormat(
										"yyyyMMdd");
								olddate.append(date);
								if (z == 31) {
									olddate.append(z);
								}
								if (z < 9) {
									olddate.append("0");
									olddate.append(z + 1);
								}
								if (z >= 9) {
									olddate.append(z + 1);
								}

								String temp1 = olddate.toString();
								String temp2 = dateFormat.format(new Date());
								try {
									Date beginDate = dateFormat.parse(temp1);

									Date endDate = dateFormat.parse(temp2);

									long beginNum = beginDate.getTime();
									long endNum = endDate.getTime();
									if (beginNum > endNum) {
										sheet.getRow(
												z + height + totalHeight + 1)
												.createCell(y + width)
												.setCellStyle(cs_font_blue);
										// sheet.getRow(z+height+totalHeight+1).getCell(y+width).setCellValue("日期超前");
									} else {
										sheet.getRow(
												z + height + totalHeight + 1)
												.createCell(y + width)
												.setCellStyle(cs_font_red);
										sheet.getRow(
												z + height + totalHeight + 1)
												.getCell(y + width)
												.setCellValue("無數據");
									}
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							try {
								day = alllist2.get(x).get(z).getId()
										.getYymmdd().getDate();
								onModulus = alllist2.get(x).get(z)
										.getOnModulus();
								personnum = alllist2.get(x).get(z)
										.getPersonnum();
								daycount = alllist2.get(x).get(z).getDaycount();
								standardOutput = alllist2.get(x).get(z)
										.getStandardOutput();
								holiday = alllist2.get(x).get(z)
										.getWorkorholiday();
								if (standardOutput != null && z == day - 1) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue(standardOutput);
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_thousand);
								}
								if (standardOutput != null && z != day - 1) {

									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue(standardOutput);
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_thousand);
								}
								if (standardOutput == null && z == day - 1
										&& holiday.equals("1")) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue("假日");
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_font_blue);
								}
								if (standardOutput == null && z != day - 1
										&& holiday.equals("1")) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue("假日");
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_font_blue);
								}
								if (standardOutput == null && z == day - 1
										&& holiday.equals("2")) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue("未排產");
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_font_green);
								}
								if (standardOutput == null && z != day - 1
										&& holiday.equals("2")) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue("未排產");
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_font_green);
								}
								if (standardOutput == null
										&& z == day - 1
										&& (onModulus != null
												|| personnum != null || daycount != null)) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue("無數據");
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_font_red);
								}
								if (standardOutput == null
										&& z != day - 1
										&& (onModulus != null
												|| personnum != null || daycount != null)) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue("無數據");
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_font_red);
								}
								sumStandardOutput = sumStandardOutput
										+ standardOutput;

							} catch (Exception e) {
								// TODO: handle exception
							}
							if (z == z_length - 2) {
								sheet.getRow(z + height + totalHeight + 1)
										.createCell(y + width)
										.setCellValue(sumStandardOutput);
								sheet.getRow(z + height + totalHeight + 1)
										.getCell(y + width)
										.setCellStyle(cs_thousand);
							}
							break;
						case 4:
							if (sheet.getRow(z + height + totalHeight + 1)
									.getCell(y + width) == null) {
								// sheet.getRow(z+height+totalHeight+1).createCell(y+width).setCellStyle(cs);
								StringBuffer olddate = new StringBuffer();
								SimpleDateFormat dateFormat = new SimpleDateFormat(
										"yyyyMMdd");
								olddate.append(date);
								if (z == 31) {
									olddate.append(z);
								}
								if (z < 9) {
									olddate.append("0");
									olddate.append(z + 1);
								}
								if (z >= 9) {
									olddate.append(z + 1);
								}
								String temp1 = olddate.toString();
								String temp2 = dateFormat.format(new Date());
								try {
									Date beginDate = dateFormat.parse(temp1);
									Date endDate = dateFormat.parse(temp2);
									long beginNum = beginDate.getTime();
									long endNum = endDate.getTime();
									if (beginNum > endNum) {
										sheet.getRow(
												z + height + totalHeight + 1)
												.createCell(y + width)
												.setCellStyle(cs_font_blue);
										// sheet.getRow(z+height+totalHeight+1).getCell(y+width).setCellValue("日期超前");
									} else {
										sheet.getRow(
												z + height + totalHeight + 1)
												.createCell(y + width)
												.setCellStyle(cs_font_red);
										sheet.getRow(
												z + height + totalHeight + 1)
												.getCell(y + width)
												.setCellValue("無數據");
									}
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							try {
								day = alllist2.get(x).get(z).getId()
										.getYymmdd().getDate();
								onModulus = alllist2.get(x).get(z)
										.getOnModulus();
								personnum = alllist2.get(x).get(z)
										.getPersonnum();
								daycount = alllist2.get(x).get(z).getDaycount();
								actualYield = alllist2.get(x).get(z)
										.getActualYield();
								holiday = alllist2.get(x).get(z)
										.getWorkorholiday();
								if (actualYield != null && z == day - 1) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue(actualYield);
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_thousand);
								}
								if (actualYield != null & z != day - 1) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue(actualYield);
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_thousand);
								}
								if (actualYield == null && z == day - 1
										&& holiday.equals("1")) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue("假日");
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_font_blue);
								}
								if (actualYield == null && z != day - 1
										&& holiday.equals("1")) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue("假日");
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_font_blue);
								}
								if (actualYield == null && z == day - 1
										&& holiday.equals("2")) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue("未排產");
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_font_green);
								}
								if (actualYield == null && z != day - 1
										&& holiday.equals("2")) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue("未排產");
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_font_green);
								}
								if (actualYield == null
										&& z == day - 1
										&& (onModulus != null
												|| personnum != null || daycount != null)) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue("無數據");
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_font_red);
								}
								if (actualYield == null
										&& z != day - 1
										&& (onModulus != null
												|| personnum != null || daycount != null)) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue("無數據");
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_font_red);
								}
								sumActualYield = sumActualYield + actualYield;

							} catch (Exception e) {
								// TODO: handle exception
							}
							if (z == z_length - 2) {
								sheet.getRow(z + height + totalHeight + 1)
										.createCell(y + width)
										.setCellValue(sumActualYield);
								sheet.getRow(z + height + totalHeight + 1)
										.getCell(y + width)
										.setCellStyle(cs_thousand);
							}

							break;
						case 5:
							if (sheet.getRow(z + height + totalHeight + 1)
									.getCell(y + width) == null) {
								// sheet.getRow(z+height+totalHeight+1).createCell(y+width).setCellStyle(cs);
								StringBuffer olddate = new StringBuffer();
								SimpleDateFormat dateFormat = new SimpleDateFormat(
										"yyyyMMdd");
								olddate.append(date);
								if (z == 31) {
									olddate.append(z);
								}
								if (z < 9) {
									olddate.append("0");
									olddate.append(z + 1);
								}
								if (z >= 9) {
									olddate.append(z + 1);
								}

								String temp1 = olddate.toString();
								String temp2 = dateFormat.format(new Date());
								try {
									Date beginDate = dateFormat.parse(temp1);
									Date endDate = dateFormat.parse(temp2);
									long beginNum = beginDate.getTime();
									long endNum = endDate.getTime();
									if (beginNum > endNum) {
										sheet.getRow(
												z + height + totalHeight + 1)
												.createCell(y + width)
												.setCellStyle(cs_font_blue);
										// sheet.getRow(z+height+totalHeight+1).getCell(y+width).setCellValue("日期超前");
									} else {
										sheet.getRow(
												z + height + totalHeight + 1)
												.createCell(y + width)
												.setCellStyle(cs_font_red);
										sheet.getRow(
												z + height + totalHeight + 1)
												.getCell(y + width)
												.setCellValue("無數據");
									}
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							try {
								day = alllist2.get(x).get(z).getId()
										.getYymmdd().getDate();
								onModulus = alllist2.get(x).get(z)
										.getOnModulus();
								personnum = alllist2.get(x).get(z)
										.getPersonnum();
								achievingRate = alllist2.get(x).get(z)
										.getAchievingRate();
								daycount = alllist2.get(x).get(z).getDaycount();
								holiday = alllist2.get(x).get(z)
										.getWorkorholiday();
								if (achievingRate != null && z == day - 1) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue(achievingRate);
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_data);
								}
								if (achievingRate != null && z != day - 1) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue(achievingRate);
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_data);
								}
								if (achievingRate == null && z == day - 1
										&& holiday.equals("1")) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue("假日");
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_font_blue);
								}
								if (achievingRate == null && z != day - 1
										&& holiday.equals("1")) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue("假日");
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_font_blue);
								}
								if (achievingRate == null && z == day - 1
										&& holiday.equals("2")) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue("未排產");
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_font_green);
								}
								if (achievingRate == null && z != day - 1
										&& holiday.equals("2")) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue("未排產");
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_font_green);
								}
								if (achievingRate == null
										&& z == day - 1
										&& (onModulus != null
												|| personnum != null || daycount != null)) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue("無數據");
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_font_red);
								}
								if (achievingRate == null
										&& z != day - 1
										&& (onModulus != null
												|| personnum != null || daycount != null)) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue("無數據");
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_font_red);
								}
								sumDayCount = sumDayCount + daycount;
							} catch (Exception e) {
								// TODO: handle exception
							}
							if (z == z_length - 2) {
								sheet.getRow(z + height + totalHeight + 1)
										.createCell(y + width)
										.setCellValue(sumAchievingRate);
								sheet.getRow(z + height + totalHeight + 1)
										.getCell(y + width)
										.setCellStyle(cs_data);
							}
							/*
							 * 計算總天數
							 */
							if (z == z_length - 1) {
								CellRangeAddress region_daycount = new CellRangeAddress(
										z + height + totalHeight + 1, (short) z
												+ height + totalHeight + 1, y
												+ width - 4, (short) y + width);
								sheet.addMergedRegion(region_daycount);
								sheet.getRow(z + height + totalHeight + 1)
										.createCell(y + width)
										.setCellStyle(cs_thousand);
								// 由於應用了初始化時,日期超前的單元格格式,所以要轉成"cs_thousand"格式
								sheet.getRow(z + height + totalHeight + 1)
										.getCell(y + width - 4)
										.setCellStyle(cs_thousand);
								sheet.getRow(z + height + totalHeight + 1)
										.getCell(y + width - 4)
										.setCellValue(sumDayCount);
							}

							break;
						}
					}// end for 3
				}// end for 2
				width = width + 5;

			}// end "if 無廠別狀態統計"

			if (x >= alllist2.size()) {// start "if有廠別狀態統計"
				for (int y = 0; y < cols.size(); y++) { // for 2
					if (x == 0) {
						if (y == 0) {
							sheet.createRow(height + totalHeight)
									.createCell(y + width)
									.setCellValue(cols.get(y));
						} else {
							sheet.getRow(height + totalHeight)
									.createCell(y + width)
									.setCellValue(cols.get(y));
						}
						sheet.getRow(height + totalHeight).getCell(y + width)
								.setCellStyle(cs);
					}
					if (x > 0) {
						if (y == 0) {
							continue;
						}
						sheet.getRow(height + totalHeight)
								.createCell(y + width)
								.setCellValue(cols.get(y));
						sheet.getRow(height + totalHeight).getCell(y + width)
								.setCellStyle(cs);
					}
					for (int z = 0; z < z_length; z++) {// for 3

						switch (y) {
						case 0:
							StringBuffer date_month_code = new StringBuffer();
							// date.append(year + "/");
							if (currenMonth < 10) {
								date_month_code.append("0" + currenMonth + "/");
							} else {
								date_month_code.append(currenMonth + "/");
							}
							if (z + 1 < 10) {
								date_month_code.append("0" + (z + 1));
							} else {
								date_month_code.append(z + 1);
							}
							if (z == z_length - 2) {
								sheet.createRow(z + height + totalHeight + 1)
										.createCell(y + width)
										.setCellValue("合計");
								sheet.getRow(z + height + totalHeight + 1)
										.getCell(y + width)
										.setCellStyle(cs_font);
							} else {
								sheet.createRow(z + height + totalHeight + 1)
										.createCell(y + width)
										.setCellValue(
												date_month_code.toString());
								sheet.getRow(z + height + totalHeight + 1)
										.getCell(y + width).setCellStyle(cs);
							}
							break;

						case 1:
							Double sum_standardoutput = null;
							/*
							 * if(z==z_length-2){
							 * sum_standardoutput=(Double)sum_list.get(z-1)[0];
							 * }
							 */
							if (z < z_length - 2) {
								sum_standardoutput = (Double) sum_list.get(z)[0];
							}
							/*
							 * else{
							 * sum_standardoutput=(Double)sum_list.get(z)[0]; }
							 */
							if (sum_standardoutput == null) {
								sheet.getRow(z + height + totalHeight + 1)
										.createCell(y + width)
										.setCellStyle(cs_thousand);
							} else {
								sheet.getRow(z + height + totalHeight + 1)
										.createCell(y + width)
										.setCellValue(sum_standardoutput);
								sheet.getRow(z + height + totalHeight + 1)
										.getCell(y + width)
										.setCellStyle(cs_thousand);
								sum_standardoutput_all = sum_standardoutput_all
										+ sum_standardoutput;
							}
							if (z == z_length - 2) {
								sheet.getRow(z + height + totalHeight + 1)
										.createCell(y + width)
										.setCellValue(sum_standardoutput_all);
								sheet.getRow(z + height + totalHeight + 1)
										.getCell(y + width)
										.setCellStyle(cs_thousand);
							}

							break;
						case 2:
							Double sum_actualyield = null;
							/*
							 * if(z==z_length-2){
							 * sum_actualyield=(Double)sum_list.get(z-1)[1]; }
							 */
							if (z < z_length - 2) {
								sum_actualyield = (Double) sum_list.get(z)[1];
							}
							/*
							 * else{ sum_actualyield=(Double)sum_list.get(z)[1];
							 * }
							 */
							if (sum_actualyield == null) {
								sheet.getRow(z + height + totalHeight + 1)
										.createCell(y + width)
										.setCellStyle(cs_thousand);
							} else {
								sheet.getRow(z + height + totalHeight + 1)
										.createCell(y + width)
										.setCellValue(sum_actualyield);
								sheet.getRow(z + height + totalHeight + 1)
										.getCell(y + width)
										.setCellStyle(cs_thousand);
								sum_actualyield_all = sum_actualyield_all
										+ sum_actualyield;
							}
							if (z == z_length - 2) {
								sheet.getRow(z + height + totalHeight + 1)
										.createCell(y + width)
										.setCellValue(sum_actualyield_all);
								sheet.getRow(z + height + totalHeight + 1)
										.getCell(y + width)
										.setCellStyle(cs_thousand);
							}

							break;
						}
					}// end for 3
				}// end for 2
				width = width + 2;

			}// end "有廠別狀態統計"

		}// end for 1
		try {
			OutputStream os = new FileOutputStream("D:/" + date + ".xls");
			wb.write(os);
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void print() {
		// 工作区
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建第一个sheet
		HSSFSheet sheet = wb.createSheet("產量預估與產量統計");

		HSSFSheet sheet2 = wb.createSheet("產量預估與產量統計_分頁");
		sheet2.setColumnWidth(0, 3800);

		sheet.setColumnWidth(0, 3800);
		// 單元格樣式
		HSSFCellStyle cs = wb.createCellStyle();
		cs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		// 淺黃色背景單元格
		HSSFCellStyle cs_lyellow = wb.createCellStyle();
		cs_lyellow.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_lyellow.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_lyellow.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_lyellow.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_lyellow.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_lyellow.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_lyellow
				.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		cs_lyellow.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

		// 设置字体:
		// 紅字體
		HSSFCellStyle cs_font_red = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 10);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		font.setColor(HSSFFont.COLOR_RED);
		cs_font_red.setFont(font);
		cs_font_red.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_font_red.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_font_red.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_font_red.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_font_red.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_font_red.setBorderTop(HSSFCellStyle.BORDER_THIN);

		// 粗字體
		HSSFCellStyle cs_font_blue = wb.createCellStyle();
		HSSFFont font_blue = wb.createFont();
		font_blue.setFontHeightInPoints((short) 10);
		font_blue.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		font_blue.setColor(IndexedColors.BLUE.getIndex());
		cs_font_blue.setFont(font_blue);
		cs_font_blue.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_font_blue.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_font_blue.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_font_blue.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_font_blue.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_font_blue.setBorderTop(HSSFCellStyle.BORDER_THIN);

		// 粗字體
		HSSFCellStyle cs_font_green = wb.createCellStyle();
		HSSFFont font_green = wb.createFont();
		font_green.setFontHeightInPoints((short) 10);
		font_green.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		font_green.setColor(IndexedColors.GREEN.getIndex());
		cs_font_green.setFont(font_green);
		cs_font_green.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_font_green.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_font_green.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_font_green.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_font_green.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_font_green.setBorderTop(HSSFCellStyle.BORDER_THIN);

		// 粗字體
		HSSFCellStyle cs_font = wb.createCellStyle();
		HSSFFont font_bold = wb.createFont();
		font_bold.setFontHeightInPoints((short) 10);
		font_bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		cs_font.setFont(font_bold);
		cs_font.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_font.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_font.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_font.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_font.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_font.setBorderTop(HSSFCellStyle.BORDER_THIN);

		// 標題字體
		HSSFCellStyle cs_head = wb.createCellStyle();
		HSSFFont font_head = wb.createFont();
		font_head.setFontHeightInPoints((short) 20);
		font_head.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		cs_head.setFont(font_head);
		cs_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_head.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_head.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_head.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_head.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_head.setBorderTop(HSSFCellStyle.BORDER_THIN);

		// 紅粗字體
		HSSFCellStyle cs_font_red_bold = wb.createCellStyle();
		HSSFFont font_red_bold = wb.createFont();
		font_red_bold.setFontHeightInPoints((short) 10);
		font_red_bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		font_red_bold.setColor(HSSFFont.COLOR_RED);
		cs_font_red_bold.setFont(font_red_bold);
		cs_font_red_bold.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_font_red_bold.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_font_red_bold.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_font_red_bold.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_font_red_bold.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_font_red_bold.setBorderTop(HSSFCellStyle.BORDER_THIN);

		// 粗字體和背景顏色
		HSSFCellStyle cs_font_bgyellow = wb.createCellStyle();
		HSSFFont font_bold_bgyellow = wb.createFont();
		font_bold_bgyellow.setFontHeightInPoints((short) 10);
		font_bold_bgyellow.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		cs_font_bgyellow.setFont(font_bold_bgyellow);
		cs_font_bgyellow.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_font_bgyellow.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_font_bgyellow.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_font_bgyellow.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_font_bgyellow.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_font_bgyellow.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_font_bgyellow
				.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		cs_font_bgyellow.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

		/*
		 * font.setFontName("黑体"); font.setFontHeightInPoints((short)
		 * 16);//设置字体大小
		 * 
		 * XSSFFont font2 = wb.createFont(); font2.setFontName("仿宋_GB2312");
		 * font2.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);//粗体显示
		 * font2.setFontHeightInPoints((short) 12);
		 * cs.setFont(font);//选择需要用到的字体格式 //设置列宽: sheet.setColumnWidth(0, 3766);
		 * //第一个参数代表列id(从0开始),第2个参数代表宽度值 //合併單元格 CellRangeAddress region1 = new
		 * CellRangeAddress(rowNumber, rowNumber, (short) 0, (short)
		 * 11);//参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列 sheet.addMergedRegion(region1);
		 */
		// 數字格式
		HSSFDataFormat format_poi = wb.createDataFormat();

		HSSFCellStyle cs_data = wb.createCellStyle();
		cs_data.setDataFormat(format_poi.getFormat("0.0%"));
		cs_data.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_data.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_data.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_data.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_data.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_data.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		HSSFCellStyle cs_thousand = wb.createCellStyle();
		cs_thousand.setDataFormat(format_poi.getFormat("#,##0.0"));
		cs_thousand.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_thousand.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_thousand.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_thousand.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_thousand.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_thousand.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		// 淺黃背景顏色
		HSSFCellStyle cs_thousand_lyellow = wb.createCellStyle();
		cs_thousand_lyellow.setDataFormat(format_poi.getFormat("#,##0.0"));
		cs_thousand_lyellow.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_thousand_lyellow.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_thousand_lyellow.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_thousand_lyellow.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_thousand_lyellow.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_thousand_lyellow.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_thousand_lyellow.setFillForegroundColor(IndexedColors.LIGHT_YELLOW
				.getIndex());
		cs_thousand_lyellow.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

		HSSFCellStyle cs_thousand_person = wb.createCellStyle();
		cs_thousand_person.setDataFormat(format_poi.getFormat("#,##0"));
		cs_thousand_person.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_thousand_person.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_thousand_person.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_thousand_person.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_thousand_person.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_thousand_person.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		// 设置自动换行:
		cs.setWrapText(true);// 设置自动换行

		List list_factNo = webFactSer.findAllFact();
		List<String> factcodelist = webFactSer.findAllFactCode_show();
		/*GregorianCalendar ger = new GregorianCalendar();
		int currenMonth = (int) ger.get(Calendar.MONTH) + 1;
		int currenYear = (int) ger.get(Calendar.YEAR);
		DateFormat format = new SimpleDateFormat("yyyyMM");		
		String date = format.format(new Date());
		*/
		int currenMonth=Integer.parseInt(yymm.substring(4, 6));                                                                     ///////////////currenMonth///////
		int currenYear=Integer.parseInt(yymm.substring(0, 4));
				
		String date=yymm;                                                                                                            //////////////date//////////////
		List<Webestproduct> alllist = new ArrayList();
		List<List<WebYieldData>> alllist2 = new ArrayList();
		List<String> factnoCodelist = new ArrayList();
		String firstdate = getFirstDate();
		String lastdate = dateAdd(-1);
		for (int factno = 0; factno < list_factNo.size(); factno++) {// start
																		// "for"
			List factAreas = webFactSer
					.findFactCodeByFactNo_show(((Object[]) list_factNo.get(factno))[0]                                                /****只查找要顯示的廠別狀態****/
							.toString());
			String factSname = webFactSer.selByid(((Object[]) list_factNo
					.get(factno))[0].toString());
			for (int factcode = 0; factcode < factAreas.size(); factcode++) {
				List<Webestproduct> list = estProSer.findByFactcode(
						((Object[]) list_factNo.get(factno))[0].toString(),
						(String) factAreas.get(factcode), date);
				List<WebYieldData> list2 =
				dataSer.findDataByFactcode(((Object[])list_factNo.get(factno))[0].toString(),(String)factAreas.get(factcode),date);
				//List<WebYieldData> list2 = dataSer.findDataByFactcode2(
						//((Object[]) list_factNo.get(factno))[0].toString(),
						//(String) factAreas.get(factcode), firstdate, lastdate);
				List<Webestproduct> listnull = estProSer.findNullYpre(
						((Object[]) list_factNo.get(factno))[0].toString(),
						(String) factAreas.get(factcode), date);
				List<WebYieldData> listnull2 = dataSer.findNullYdata(
						((Object[]) list_factNo.get(factno))[0].toString(),
						(String) factAreas.get(factcode), date);
				StringBuffer factnoCode = new StringBuffer();
				Webestproduct pre = null;
				WebYieldData ydata = null;
				if (list.size() > 0 && listnull.size() == 0 || list2.size() > 0
						&& listnull2.size() == 0 || list2.size() > 0
						&& listnull2.size() > 0) {
					factnoCode.append(factSname);
					factnoCode.append("("
							+ ((Object[]) list_factNo.get(factno))[0]
									.toString() + ")-");
					factnoCode.append((String) factAreas.get(factcode));
					factnoCodelist.add(factnoCode.toString());
					if (list2.size() == 0) {
						ydata = new WebYieldData();
						list2.add(ydata);
					}
					if (list.size() == 0) {
						pre = new Webestproduct();
						alllist.add(pre);
					}
					if (list.size() > 0) {
						pre = list.get(0);
						alllist.add(pre);
					}
					if (list2.size() > 0) {
						alllist2.add(list2);
					}
				}

				if (list.size() == 0 && list2.size() == 0) {
					factnoCode.append(factSname);
					factnoCode.append("("
							+ ((Object[]) list_factNo.get(factno))[0]
									.toString() + ")-");
					factnoCode.append((String) factAreas.get(factcode));
					factnoCode.append("還沒有添加數據!!");
					factnoCodelist.add(factnoCode.toString());
					pre = new Webestproduct();
					ydata = new WebYieldData();
					alllist.add(pre);
					list2.add(ydata);
					alllist2.add(list2);
				}
				if (listnull.size() > 0 && listnull2.size() > 0) {
					factnoCode.append(factSname);
					factnoCode.append("("
							+ ((Object[]) list_factNo.get(factno))[0]
									.toString() + ")_");
					factnoCode.append((String) factAreas.get(factcode));
					factnoCode.append("還沒有添加數據!!");
					factnoCodelist.add(factnoCode.toString());
					pre = list.get(0);
					alllist.add(pre);
					alllist2.add(list2);
				}
				if (list.size() > 0 && listnull.size() > 0 && list2.size() == 0) {
					factnoCode.append(factSname);
					factnoCode.append("("
							+ ((Object[]) list_factNo.get(factno))[0]
									.toString() + ")_");
					factnoCode.append((String) factAreas.get(factcode));
					factnoCode.append("還沒有添加數據!!");
					factnoCodelist.add(factnoCode.toString());
					ydata = new WebYieldData();
					pre = list.get(0);
					list2.add(ydata);
					alllist.add(pre);
					alllist2.add(list2);
				}
			}
		}// end "for"

		int z_length = 0;
		if (currenMonth == 1 || currenMonth == 3 || currenMonth == 5
				|| currenMonth == 7 || currenMonth == 8 || currenMonth == 10
				|| currenMonth == 12) {
			z_length = 32 + 1;
		}
		if (currenMonth == 4 || currenMonth == 6 || currenMonth == 9
				|| currenMonth == 11) {
			z_length = 31 + 1;
		}
		if (currenMonth == 2) {
			if (currenYear % 4 == 0 && currenYear % 100 != 0
					|| currenYear % 400 == 0) {
				z_length = 30 + 1;
			} else {
				z_length = 29 + 1;
			}
		}

		int totalHeight = 1;
		int height = 0;
		// ********上部********//
		int width = 0;

		/*
		 * 標題設置
		 */

		sheet.createRow(0 + totalHeight - 1).setHeightInPoints(50);// 設置標題高度
		CellRangeAddress reg_head = new CellRangeAddress(0 + totalHeight - 1,
				(short) 0 + totalHeight - 1, 0, (short) alllist.size() * 5);
		sheet.addMergedRegion(reg_head);
		sheet.getRow(0 + totalHeight - 1).createCell(0)
				.setCellValue(date + "加久各工廠每日產量達成狀況匯總表");
		sheet.getRow(0 + totalHeight - 1).getCell(0).setCellStyle(cs_head);
		for (int i_head = 1; i_head < alllist.size() * 5 + 1; i_head++) {
			sheet.getRow(0 + totalHeight - 1).createCell(i_head)
					.setCellStyle(cs_head);
		}

		/*
		 * sheet.createRow(0+totalHeight-1).setHeightInPoints(50);//設置標題高度
		 * StringBuffer tempName_head = new StringBuffer();
		 * tempName_head.append(year+"年"); tempName_head.append(all+"月");
		 * 
		 * CellRangeAddress reg_head=new
		 * CellRangeAddress(0+totalHeight-1,(short)
		 * 0+totalHeight-1,0,(short)alllist.size()*5);
		 * sheet.addMergedRegion(reg_head);
		 * sheet.getRow(0+totalHeight-1).createCell
		 * (0).setCellValue(tempName_head+"加久各工廠每日產量達成狀況匯總表");
		 * sheet.getRow(0+totalHeight-1).getCell(0).setCellStyle(cs_head);
		 * for(int i_head=1;i_head<alllist.size()*5+1;i_head++){
		 * sheet.getRow(0+totalHeight
		 * -1).createCell(i_head).setCellStyle(cs_head); }
		 */
		for (int i = 0; i < alllist.size() + factcodelist.size(); i++) {// for 1
			/**
			 * 如果alllist.size()+factcodelist.size()>41就分頁
			 */

			if (i >= alllist.size() && i <= 40) {
				String temp_code = factcodelist.get(factcodelist.size()
						- (alllist.size() + factcodelist.size() - (i + 1)) - 1);
				sheet.getRow(6 + totalHeight).createCell(width)
						.setCellStyle(cs_font_bgyellow);
				sheet.getRow(6 + totalHeight).getCell(width)
						.setCellValue(temp_code);
				sheet.getRow(6 + totalHeight).createCell(width + 1)
						.setCellStyle(cs_font_bgyellow);

				sheet.getRow(7 + totalHeight).createCell(width)
						.setCellStyle(cs_font_bgyellow);
				sheet.getRow(7 + totalHeight).createCell(1 + width)
						.setCellStyle(cs_font_bgyellow);
				CellRangeAddress region = new CellRangeAddress(6 + totalHeight,
						(short) 7 + totalHeight, width, (short) 1 + width);
				sheet.addMergedRegion(region);
				width = width + 2;
				continue;
			}
			if (i > 40) {
				String temp_code = factcodelist.get(factcodelist.size()
						- (alllist.size() + factcodelist.size() - (i + 1)) - 1);
				if (i == 41) {
					width = 0;
					sheet2.createRow(6 + totalHeight);
					sheet2.createRow(7 + totalHeight);
				}
				sheet2.getRow(6 + totalHeight).createCell(width)
						.setCellStyle(cs_font_bgyellow);
				sheet2.getRow(6 + totalHeight).getCell(width)
						.setCellValue(temp_code);
				sheet2.getRow(6 + totalHeight).createCell(width + 1)
						.setCellStyle(cs_font_bgyellow);

				sheet2.getRow(7 + totalHeight).createCell(width)
						.setCellStyle(cs_font_bgyellow);
				sheet2.getRow(7 + totalHeight).createCell(1 + width)
						.setCellStyle(cs_font_bgyellow);

				if (i == 41) {

					sheet2.getRow(6 + totalHeight).createCell(2 + width)
							.setCellStyle(cs_font_bgyellow);
					sheet2.getRow(7 + totalHeight).createCell(2 + width)
							.setCellStyle(cs_font_bgyellow);
					CellRangeAddress region1 = new CellRangeAddress(
							6 + totalHeight, (short) 7 + totalHeight, width,
							(short) 2 + width);
					sheet2.addMergedRegion(region1);
					width = width + 3;

				} else {
					CellRangeAddress region2 = new CellRangeAddress(
							6 + totalHeight, (short) 7 + totalHeight, width,
							(short) 1 + width);
					sheet2.addMergedRegion(region2);
					width = width + 2;
				}
				continue;
			}
			if (i < alllist.size()) { // start "if buttom"
				List<String> cols = new ArrayList();
				cols.add("日期/產量/廠別");
				cols.add("戰力分析模數");
				cols.add("預計生產模數");
				cols.add("預計請款雙數");
				cols.add("機台孔位數");
				cols.add("總機孔");
				cols.add("有效孔位");
				cols.add("工程/樣品");
				cols.add("補料孔位");
				cols.add("其他");

				if (i > 0) {
					width = width - 1;
				}
				for (int j = 0; j < cols.size(); j++) { // for 2

					if (j == 5) {
						break;
					}

					if (i == 0) {
						if (j < 5) {

							sheet.createRow(j + totalHeight)
									.createCell(0 + width)
									.setCellValue(cols.get(j));
							sheet.getRow(j + totalHeight).getCell(0 + width)
									.setCellStyle(cs);
						}
						sheet.getRow(j + totalHeight).createCell(1 + width);
						sheet.getRow(j + totalHeight).getCell(1 + width)
								.setCellStyle(cs_thousand);
						if (j == 4) {
							CellRangeAddress region1 = new CellRangeAddress(j
									+ totalHeight, (short) j + totalHeight + 3,
									0 + width, (short) 0 + width);
							sheet.addMergedRegion(region1);
							sheet.getRow(j + totalHeight).getCell(0 + width)
									.setCellStyle(cs_lyellow);
							sheet.createRow(j + totalHeight + 1)
									.createCell(0 + width)
									.setCellStyle(cs_lyellow);
							sheet.createRow(j + totalHeight + 2)
									.createCell(0 + width)
									.setCellStyle(cs_lyellow);
							sheet.createRow(j + totalHeight + 3)
									.createCell(0 + width)
									.setCellStyle(cs_lyellow);
						}
					} else {
						if (j < 5) {
							sheet.getRow(j + totalHeight).createCell(0 + width)
									.setCellValue(cols.get(j));
							sheet.getRow(j + totalHeight).getCell(0 + width)
									.setCellStyle(cs);
						}
						sheet.getRow(j + totalHeight).createCell(1 + width);
						sheet.getRow(j + totalHeight).getCell(1 + width)
								.setCellStyle(cs_thousand);
						if (j == 4) {
							// 注意:0+width要加1,要不然導致"有效孔位"至"其它"會合併
							CellRangeAddress region1 = new CellRangeAddress(j
									+ totalHeight, (short) j + totalHeight + 3,
									0 + width + 1, (short) 0 + width + 1);
							sheet.addMergedRegion(region1);
							sheet.getRow(j + totalHeight)
									.getCell(0 + width + 1)
									.setCellStyle(cs_lyellow);
							sheet.getRow(j + totalHeight + 1)
									.createCell(0 + width + 1)
									.setCellStyle(cs_lyellow);
							sheet.getRow(j + totalHeight + 2)
									.createCell(0 + width + 1)
									.setCellStyle(cs_lyellow);
							sheet.getRow(j + totalHeight + 3)
									.createCell(0 + width + 1)
									.setCellStyle(cs_lyellow);
						}
					}

					switch (j) {
					case 0:
						String factNoAndCode = factnoCodelist.get(i);
						// sheet.setColumnWidth(1+width, 4000);
						sheet.getRow(j + totalHeight).setHeightInPoints(40);

						sheet.getRow(j + totalHeight).getCell(1 + width)
								.setCellValue(factNoAndCode);

						CellRangeAddress region1 = new CellRangeAddress(j
								+ totalHeight, (short) j + totalHeight,
								1 + width, (short) 5 + width);
						sheet.addMergedRegion(region1);
						if (factNoAndCode.contains("沒有添加數據")) {
							sheet.getRow(j + totalHeight).getCell(1 + width)
									.setCellStyle(cs_font_red_bold);
						} else {
							sheet.getRow(j + totalHeight).getCell(1 + width)
									.setCellStyle(cs_font);
						}
						sheet.getRow(j + totalHeight).createCell(2 + width)
								.setCellStyle(cs);
						sheet.getRow(j + totalHeight).createCell(3 + width)
								.setCellStyle(cs);
						sheet.getRow(j + totalHeight).createCell(4 + width)
								.setCellStyle(cs);
						sheet.getRow(j + totalHeight).createCell(5 + width)
								.setCellStyle(cs);
						// sheet.getRow(j + totalHeight).createCell(6 +
						// width).setCellStyle(cs);
						// sheet.getRow(j+totalHeight).createCell(7+width).setCellStyle(cs);
						break;
					case 1:
						Double force = (alllist.get(i)).getMachinepower();
						if (force != null) {
							sheet.getRow(j + totalHeight).getCell(1 + width)
									.setCellValue(force);
						} else {
							sheet.getRow(j + totalHeight).getCell(1 + width)
									.setCellValue("");
						}
						CellRangeAddress region2 = new CellRangeAddress(j
								+ totalHeight, (short) j + totalHeight,
								1 + width, (short) 5 + width);
						sheet.addMergedRegion(region2);
						sheet.getRow(j + totalHeight).createCell(2 + width)
								.setCellStyle(cs);
						sheet.getRow(j + totalHeight).createCell(3 + width)
								.setCellStyle(cs);
						sheet.getRow(j + totalHeight).createCell(4 + width)
								.setCellStyle(cs);
						sheet.getRow(j + totalHeight).createCell(5 + width)
								.setCellStyle(cs);
						// sheet.getRow(j + totalHeight).createCell(6 +
						// width).setCellStyle(cs);
						// sheet.getRow(j+totalHeight).createCell(7+width).setCellStyle(cs);
						break;
					case 2:
						Double expect = (alllist.get(i))
								.getEstmodel();

						if (expect != null) {
							sheet.getRow(j + totalHeight).getCell(1 + width)
									.setCellValue(expect);
						} else {
							sheet.getRow(j + totalHeight).getCell(1 + width)
									.setCellValue("");
						}
						CellRangeAddress region3 = new CellRangeAddress(j
								+ totalHeight, (short) j + totalHeight,
								1 + width, (short) 5 + width);
						sheet.addMergedRegion(region3);
						sheet.getRow(j + totalHeight).createCell(2 + width)
								.setCellStyle(cs);
						sheet.getRow(j + totalHeight).createCell(3 + width)
								.setCellStyle(cs);
						sheet.getRow(j + totalHeight).createCell(4 + width)
								.setCellStyle(cs);
						sheet.getRow(j + totalHeight).createCell(5 + width)
								.setCellStyle(cs);
						// sheet.getRow(j + totalHeight).createCell(6 +
						// width).setCellStyle(cs);
						// sheet.getRow(j+totalHeight).createCell(7+width).setCellStyle(cs);
						break;
					case 3:
						Double expPay = (alllist.get(i)).getEstpay();

						if (expPay != null) {
							sheet.getRow(j + totalHeight).getCell(1 + width)
									.setCellValue(expPay);
						} else {
							sheet.getRow(j + totalHeight).getCell(1 + width)
									.setCellValue("");
						}
						CellRangeAddress region4 = new CellRangeAddress(j
								+ totalHeight, (short) j + totalHeight,
								1 + width, (short) 5 + width);
						sheet.addMergedRegion(region4);
						sheet.getRow(j + totalHeight).createCell(2 + width)
								.setCellStyle(cs);
						sheet.getRow(j + totalHeight).createCell(3 + width)
								.setCellStyle(cs);
						sheet.getRow(j + totalHeight).createCell(4 + width)
								.setCellStyle(cs);
						sheet.getRow(j + totalHeight).createCell(5 + width)
								.setCellStyle(cs);
						// sheet.getRow(j + totalHeight).createCell(6 +
						// width).setCellStyle(cs);
						// sheet.getRow(j+totalHeight).createCell(7+width).setCellStyle(cs);
						break;
					case 4:
						sheet.getRow(j + totalHeight).getCell(1 + width)
								.setCellValue(cols.get(j + 1));
						CellRangeAddress hole_cell = new CellRangeAddress(j
								+ totalHeight, j + totalHeight + 3, 1 + width,
								1 + width);
						sheet.addMergedRegion(hole_cell);
						sheet.getRow(j + totalHeight).getCell(1 + width)
								.setCellStyle(cs_lyellow);
						sheet.getRow(j + totalHeight + 1).createCell(1 + width)
								.setCellStyle(cs_lyellow);
						sheet.getRow(j + totalHeight + 2).createCell(1 + width)
								.setCellStyle(cs_lyellow);
						sheet.getRow(j + totalHeight + 3).createCell(1 + width)
								.setCellStyle(cs_lyellow);
						CellRangeAddress hole_cell_value = new CellRangeAddress(
								j + totalHeight, j + totalHeight + 3,
								2 + width, 2 + width);
						sheet.addMergedRegion(hole_cell_value);
						sheet.getRow(j + totalHeight + 1).createCell(2 + width)
								.setCellStyle(cs_lyellow);
						sheet.getRow(j + totalHeight + 2).createCell(2 + width)
								.setCellStyle(cs_lyellow);
						sheet.getRow(j + totalHeight + 3).createCell(2 + width)
								.setCellStyle(cs_lyellow);
						Double hole = (alllist.get(i)).getTotalhole();//-------------------------------------------總機孔
						if (hole != null) {
							sheet.getRow(j + totalHeight).createCell(2 + width)
									.setCellValue(hole);
						} else {
							sheet.getRow(j + totalHeight).createCell(2 + width)
									.setCellValue("");
						}
						sheet.getRow(j + totalHeight).getCell(2 + width)
								.setCellStyle(cs_thousand_lyellow);
						sheet.getRow(j + totalHeight).createCell(3 + width)
								.setCellValue(cols.get(j + 2));
						Double posNum = (alllist.get(i)).getHole();//------------------------------------有效孔位數
						if (posNum != null) {
							sheet.getRow(j + totalHeight).createCell(4 + width)
									.setCellValue(posNum);
						} else {
							sheet.getRow(j + totalHeight).createCell(4 + width)
									.setCellValue("");
						}
						sheet.getRow(j + totalHeight).getCell(3 + width)
								.setCellStyle(cs_lyellow);
						sheet.getRow(j + totalHeight).getCell(4 + width)
								.setCellStyle(cs_thousand_lyellow);
						CellRangeAddress posNum_cell_value = new CellRangeAddress(
								j + totalHeight, j + totalHeight, 4 + width,
								5 + width);
						sheet.addMergedRegion(posNum_cell_value);
						sheet.getRow(j + totalHeight).createCell(5 + width)
								.setCellStyle(cs_lyellow);
						// sheet.getRow(j + totalHeight).createCell(6 +
						// width).setCellStyle(cs);
						// sheet.getRow(j+totalHeight).createCell(7+width).setCellStyle(cs);
						String temp = cols.get(j + 3);
						sheet.getRow(j + totalHeight + 1).createCell(3 + width)
								.setCellValue(temp);
						Double sample = (alllist.get(i)).getSample();
						if (sample != null) {
							sheet.getRow(j + totalHeight + 1)
									.createCell(4 + width).setCellValue(sample);
						} else {
							sheet.getRow(j + totalHeight + 1)
									.createCell(4 + width).setCellValue("");
						}
						sheet.getRow(j + totalHeight + 1).getCell(3 + width)
								.setCellStyle(cs_lyellow);
						sheet.getRow(j + totalHeight + 1).getCell(4 + width)
								.setCellStyle(cs_thousand_lyellow);
						CellRangeAddress sample_cell_value = new CellRangeAddress(
								j + totalHeight + 1, j + totalHeight + 1,
								4 + width, 5 + width);
						sheet.addMergedRegion(sample_cell_value);
						sheet.getRow(j + totalHeight + 1).createCell(5 + width)
								.setCellStyle(cs_lyellow);
						// sheet.getRow(j + totalHeight + 1).createCell(6 +
						// width).setCellStyle(cs);
						// sheet.getRow(j+totalHeight+1).createCell(7+width).setCellStyle(cs);
						sheet.getRow(j + totalHeight + 2).createCell(3 + width)
								.setCellValue(cols.get(j + 4));
						Double acce = (alllist.get(i)).getAccessories();
						if (acce != null) {
							sheet.getRow(j + totalHeight + 2)
									.createCell(4 + width).setCellValue(acce);
						} else {
							sheet.getRow(j + totalHeight + 2)
									.createCell(4 + width).setCellValue("");
						}
						sheet.getRow(j + totalHeight + 2).getCell(3 + width)
								.setCellStyle(cs_lyellow);
						sheet.getRow(j + totalHeight + 2).getCell(4 + width)
								.setCellStyle(cs_thousand_lyellow);
						CellRangeAddress acce_cell_value = new CellRangeAddress(
								j + totalHeight + 2, j + totalHeight + 2,
								4 + width, 5 + width);
						sheet.addMergedRegion(acce_cell_value);
						sheet.getRow(j + totalHeight + 2).createCell(5 + width)
								.setCellStyle(cs_lyellow);
						// sheet.getRow(j + totalHeight + 2).createCell(6 +
						// width).setCellStyle(cs);
						// sheet.getRow(j+totalHeight+2).createCell(7+width).setCellStyle(cs);
						sheet.getRow(j + totalHeight + 3).createCell(3 + width)
								.setCellValue(cols.get(j + 5));
						Double other = (alllist.get(i)).getOther();
						if (other != null) {
							sheet.getRow(j + totalHeight + 3)
									.createCell(4 + width).setCellValue(other);
						} else {
							sheet.getRow(j + totalHeight + 3)
									.createCell(4 + width).setCellValue("");
						}
						sheet.getRow(j + totalHeight + 3).getCell(3 + width)
								.setCellStyle(cs_lyellow);
						sheet.getRow(j + totalHeight + 3).getCell(4 + width)
								.setCellStyle(cs_thousand_lyellow);
						CellRangeAddress other_cell_value = new CellRangeAddress(
								j + totalHeight + 3, j + totalHeight + 3,
								4 + width, 5 + width);
						sheet.addMergedRegion(other_cell_value);
						sheet.getRow(j + totalHeight + 3).createCell(5 + width)
								.setCellStyle(cs_lyellow);
						// sheet.getRow(j + totalHeight + 3).createCell(6 +
						// width).setCellStyle(cs);
						// sheet.getRow(j+totalHeight+3).createCell(7+width).setCellStyle(cs);
						break;
					}
				} // end for 2
				width = width + 6;
			} // end "if buttom"
		}// end for 1

		height = 8;
		width = 0;
		// **********下部*******//
		for (int x = 0; x < alllist2.size() + factcodelist.size(); x++) { // for
																			// 1

			List<String> cols = new ArrayList();
			List<Object[]> sum_list = new ArrayList();
			cols.add("日期");
			cols.add("上模數");
			cols.add("人數");
			cols.add("標準產量");
			cols.add("實際產量");
			cols.add("達成率(%)");
			// cols.add("天數");
			// cols.add("工作日/假日");

			if (x >= alllist2.size()) {
				String temp = factcodelist
						.get(factcodelist.size()
								- (alllist2.size() + factcodelist.size() - (x + 1))
								- 1);

				for (int x2 = 0; x2 < z_length; x2++) {
					StringBuffer yymmdd = new StringBuffer();
					yymmdd.append(date);
					if ((x2 + 1) < 10) {
						yymmdd.append("0" + (x2 + 1));
					} else {
						yymmdd.append(x2 + 1);
					}
					Object[] list_temp = dataSer.totalWithFactCode(temp,                                                                          //////////////
							yymmdd.toString());
					sum_list.add(list_temp);
				}
			}

			double sumStandardOutput = 0;// 標準產量合計
			double sumActualYield = 0;// 實際產量合計
			double sumAchievingRate = 0;// 月達成率

			double sum_standardoutput_all = 0;// 標準產量(廠別狀態統計)
			double sum_actualyield_all = 0;// 實際產量(廠別狀態統計)

			double sumOnModulus = 0;// 上模數合計
			double sumPersonnum = 0;// 人數合計
			double sumDayCount = 0;// 總天數

			double sumNodate=0;//未輸入數據統計20160330
			/**
			 * 如果alllist2.size()+factcodelist.size()>41就分頁
			 */
			if (x >= alllist2.size()) {// start "if 有廠別狀態統計"
				cols.clear();
				cols.add("日期");
				cols.add("標準產量");
				cols.add("實際產量");
				if (x <= 40) {// start "if x<41"
					for (int y_code = 1; y_code < cols.size(); y_code++) {// start
																			// "for2_2"
						if (x == alllist2.size()) {
							if (y_code == 0) {
								sheet.createRow(height + totalHeight)
										.createCell(y_code + width)
										.setCellValue(cols.get(y_code));
							} else {
								sheet.getRow(height + totalHeight)
										.createCell(y_code + width)
										.setCellValue(cols.get(y_code));
							}
							sheet.getRow(height + totalHeight)
									.getCell(y_code + width).setCellStyle(cs);
						}
						if (x > alllist2.size()) {
							if (y_code == 0) {
								continue;
							}
							sheet.getRow(height + totalHeight)
									.createCell(y_code + width)
									.setCellValue(cols.get(y_code));
							sheet.getRow(height + totalHeight)
									.getCell(y_code + width).setCellStyle(cs);
						}
						for (int z_code = 0; z_code < z_length; z_code++) {// start
																			// "for3_3"
							switch (y_code) {
							case 1:
								Double sum_standardoutput = null;
								/*
								 * if(z_code==z_length-1){
								 * sum_standardoutput=(Double
								 * )sum_list.get(z_code-1)[0]; }else{
								 * sum_standardoutput
								 * =(Double)sum_list.get(z_code)[0]; }
								 */
								if (z_code < z_length - 2) {
									sum_standardoutput = (Double) sum_list
											.get(z_code)[0];
								}
								if (sum_standardoutput == null) {
									sheet.getRow(
											z_code + height + totalHeight + 1)
											.createCell(y_code + width)
											.setCellStyle(cs_thousand);
								} else {
									sheet.getRow(
											z_code + height + totalHeight + 1)
											.createCell(y_code + width)
											.setCellValue(sum_standardoutput);
									sheet.getRow(
											z_code + height + totalHeight + 1)
											.getCell(y_code + width)
											.setCellStyle(cs_thousand);
									sum_standardoutput_all = sum_standardoutput_all
											+ sum_standardoutput;
								}
								if (z_code == z_length - 2) {
									sheet.getRow(
											z_code + height + totalHeight + 1)
											.createCell(y_code + width)
											.setCellValue(
													sum_standardoutput_all);
									sheet.getRow(
											z_code + height + totalHeight + 1)
											.getCell(y_code + width)
											.setCellStyle(cs_thousand);
								}
								break;
							case 2:
								Double sum_actualyield = null;
								/*
								 * if(z_code==z_length-1){
								 * sum_actualyield=(Double
								 * )sum_list.get(z_code-1)[1]; }else{
								 * sum_actualyield
								 * =(Double)sum_list.get(z_code)[1]; }
								 */
								if (z_code < z_length - 2) {
									sum_actualyield = (Double) sum_list
											.get(z_code)[1];
								}
								if (sum_actualyield == null) {
									sheet.getRow(
											z_code + height + totalHeight + 1)
											.createCell(y_code + width)
											.setCellStyle(cs_thousand);
								} else {
									sheet.getRow(
											z_code + height + totalHeight + 1)
											.createCell(y_code + width)
											.setCellValue(sum_actualyield);
									sheet.getRow(
											z_code + height + totalHeight + 1)
											.getCell(y_code + width)
											.setCellStyle(cs_thousand);
									sum_actualyield_all = sum_actualyield_all
											+ sum_actualyield;
								}
								if (z_code == z_length - 2) {
									sheet.getRow(
											z_code + height + totalHeight + 1)
											.createCell(y_code + width)
											.setCellValue(sum_actualyield_all);
									sheet.getRow(
											z_code + height + totalHeight + 1)
											.getCell(y_code + width)
											.setCellStyle(cs_thousand);
								}
								break;
							}
						}// end "for3_3"

					}// end "for2_2"
					width = width + 2;
					continue;
				}// end "if x<41"

				if (x > 40) {// start "if 下部分頁"

					if (x == 41) {
						width = 0;
					}

					for (int y_page = 0; y_page < cols.size(); y_page++) {// start
																			// "for 下部分頁_1"
						if (x == 41) {
							if (y_page == 0) {
								sheet2.createRow(height + totalHeight)
										.createCell(y_page + width)
										.setCellValue(cols.get(y_page));
							} else {
								sheet2.getRow(height + totalHeight)
										.createCell(y_page + width)
										.setCellValue(cols.get(y_page));
							}
							sheet2.getRow(height + totalHeight)
									.getCell(y_page + width).setCellStyle(cs);
						}
						if (x > 41) {
							if (y_page == 0) {
								continue;
							}
							sheet2.getRow(height + totalHeight)
									.createCell(y_page + width)
									.setCellValue(cols.get(y_page));
							sheet2.getRow(height + totalHeight)
									.getCell(y_page + width).setCellStyle(cs);

						}

						for (int z_page = 0; z_page < z_length - 1; z_page++) {// start"for 下部分頁_2"
							switch (y_page) {
							case 0:
								StringBuffer date_month = new StringBuffer();
								// date.append(year + "/");
								if (currenMonth < 10) {
									date_month.append("0" + currenMonth + "/");
								} else {
									date_month.append(currenMonth + "/");
								}
								if (z_page + 1 < 10) {
									date_month.append("0" + (z_page + 1));
								} else {
									date_month.append(z_page + 1);
								}
								if (z_page == z_length - 2) {
									sheet2.createRow(
											z_page + height + totalHeight + 1)
											.createCell(y_page + width)
											.setCellValue("合計");
									sheet2.getRow(
											z_page + height + totalHeight + 1)
											.getCell(y_page + width)
											.setCellStyle(cs_font);
								}
								if (z_page < z_length - 2) {
									sheet2.createRow(
											z_page + height + totalHeight + 1)
											.createCell(y_page + width)
											.setCellValue(date_month.toString());
									sheet2.getRow(
											z_page + height + totalHeight + 1)
											.getCell(y_page + width)
											.setCellStyle(cs);
								}
								/*
								 * else { sheet2.createRow(z_page + height +
								 * totalHeight + 1).createCell(y_page +
								 * width).setCellValue(date.toString());
								 * sheet2.getRow(z_page + height + totalHeight +
								 * 1).getCell(y_page + width).setCellStyle(cs);
								 * }
								 */
								break;

							case 1:
								Double sum_standardoutput = null;
								/*
								 * if(z_page==z_length-1){
								 * sum_standardoutput=(Double
								 * )sum_list.get(z_page-1)[0]; }else{
								 * sum_standardoutput
								 * =(Double)sum_list.get(z_page)[0]; }
								 */

								sum_standardoutput = (Double) sum_list
										.get(z_page)[0];

								if (sum_standardoutput == null) {
									sheet2.getRow(
											z_page + height + totalHeight + 1)
											.createCell(y_page + width)
											.setCellStyle(cs_thousand);
								} else {
									sheet2.getRow(
											z_page + height + totalHeight + 1)
											.createCell(y_page + width)
											.setCellValue(sum_standardoutput);
									sheet2.getRow(
											z_page + height + totalHeight + 1)
											.getCell(y_page + width)
											.setCellStyle(cs_thousand);
									sum_standardoutput_all = sum_standardoutput_all
											+ sum_standardoutput;
								}
								if (z_page == z_length - 2) {
									sheet2.getRow(
											z_page + height + totalHeight + 1)
											.createCell(y_page + width)
											.setCellValue(
													sum_standardoutput_all);
									sheet2.getRow(
											z_page + height + totalHeight + 1)
											.getCell(y_page + width)
											.setCellStyle(cs_thousand);
								}
								break;
							case 2:
								Double sum_actualyield = null;
								/*
								 * if(z_page==z_length-1){
								 * sum_actualyield=(Double
								 * )sum_list.get(z_page-1)[1]; }else{
								 * sum_actualyield
								 * =(Double)sum_list.get(z_page)[1]; }
								 */
								if (z_page < z_length - 2) {
									sum_actualyield = (Double) sum_list
											.get(z_page)[1];
								}
								if (sum_actualyield == null) {
									sheet2.getRow(
											z_page + height + totalHeight + 1)
											.createCell(y_page + width)
											.setCellStyle(cs_thousand);
								} else {
									sheet2.getRow(
											z_page + height + totalHeight + 1)
											.createCell(y_page + width)
											.setCellValue(sum_actualyield);
									sheet2.getRow(
											z_page + height + totalHeight + 1)
											.getCell(y_page + width)
											.setCellStyle(cs_thousand);
									sum_actualyield_all = sum_actualyield_all
											+ sum_actualyield;
								}
								if (z_page == z_length - 2) {
									sheet2.getRow(
											z_page + height + totalHeight + 1)
											.createCell(y_page + width)
											.setCellValue(sum_actualyield_all);
									sheet2.getRow(
											z_page + height + totalHeight + 1)
											.getCell(y_page + width)
											.setCellStyle(cs_thousand);
								}
								break;

							}
						}// end "for 下部分頁_2"

					}// end "for 下部分頁_1"
					width = width + 2;
					continue;
				}// end "if 下部分頁"
			}// end "if 有廠別狀態統計"

			for (int y = 0; y < cols.size(); y++) { // for 2

				if (x == 0) {
					if (y == 0) {
						sheet.createRow(height + totalHeight)
								.createCell(y + width)
								.setCellValue(cols.get(y));
					} else {
						sheet.getRow(height + totalHeight)
								.createCell(y + width)
								.setCellValue(cols.get(y));
					}
					sheet.getRow(height + totalHeight).getCell(y + width)
							.setCellStyle(cs);
				}
				if (x > 0) {
					if (y == 0) {
						continue;
					}
					sheet.getRow(height + totalHeight).createCell(y + width)
							.setCellValue(cols.get(y));
					sheet.getRow(height + totalHeight).getCell(y + width)
							.setCellStyle(cs);
				}

				if (sumStandardOutput != 0) {
					sumAchievingRate = sumActualYield / sumStandardOutput;
				}
				for (int z = 0; z < z_length; z++) {// for 3

					int day = 0;
					Double onModulus = null;
					Double personnum = null;
					Double standardOutput = null;
					Double actualYield = null;
					Double achievingRate = null;
					Double daycount = null;
					String holiday = null;
					switch (y) {
					case 0:
						StringBuffer date_month = new StringBuffer();
						// date.append(year + "/");
						if (currenMonth < 10) {
							date_month.append("0" + currenMonth + "/");
						} else {
							date_month.append(currenMonth + "/");
						}
						if (z + 1 < 10) {
							date_month.append("0" + (z + 1));
						} else {
							date_month.append(z + 1);
						}
						if (z == z_length - 1) {
							sheet.createRow(z + height + totalHeight + 1)
									.createCell(y + width).setCellValue("工作天數");
							sheet.getRow(z + height + totalHeight + 1)
									.getCell(y + width).setCellStyle(cs_font);
							
							sheet.createRow(z + height + totalHeight + 2)
							.createCell(y + width)
							.setCellValue("沒有輸入統計");
					        sheet.getRow(z + height + totalHeight + 2)
							.getCell(y + width)
							.setCellStyle(cs_font);
					        
					        sheet.createRow(z + height + totalHeight + 3)
							.createCell(y + width)
							.setCellValue("沒有輸入統計1");
					        sheet.getRow(z + height + totalHeight + 3)
							.getCell(y + width)
							.setCellStyle(cs_font);
						}
						if (z == z_length - 2) {
							sheet.createRow(z + height + totalHeight + 1)
									.createCell(y + width).setCellValue("合計");
							sheet.getRow(z + height + totalHeight + 1)
									.getCell(y + width).setCellStyle(cs_font);
						}
						if (z < z_length - 2) {
							sheet.createRow(z + height + totalHeight + 1)
									.createCell(y + width)
									.setCellValue(date_month.toString());
							sheet.getRow(z + height + totalHeight + 1)
									.getCell(y + width).setCellStyle(cs);
						}
						/*
						 * else { sheet.createRow(z + height + totalHeight +
						 * 1).createCell(y +
						 * width).setCellValue(date.toString()); sheet.getRow(z
						 * + height + totalHeight + 1).getCell(y +
						 * width).setCellStyle(cs); }
						 */
						break;
					case 1:
						if (sheet.getRow(z + height + totalHeight + 1).getCell(
								y + width) == null) {
							StringBuffer olddate = new StringBuffer();
							SimpleDateFormat dateFormat = new SimpleDateFormat(
									"yyyyMMdd");
							olddate.append(date);
							if (z == z_length - 1) {
								olddate.append(z);
							}
							if (z < 9) {
								olddate.append("0");
								olddate.append(z + 1);
							}
							if (z >= 9) {
								olddate.append(z + 1);
							}
							String temp1 = olddate.toString();
							String temp2 = dateFormat.format(new Date());
							try {
								Date beginDate = dateFormat.parse(temp1);
								Date endDate = dateFormat.parse(temp2);
								long beginNum = beginDate.getTime();
								long endNum = endDate.getTime();
								if (beginNum >= endNum) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellStyle(cs_font_blue);
									// sheet.getRow(z+height+totalHeight+1).getCell(y+width).setCellValue("日期超前");
								} else {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellStyle(cs_font_red);
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellValue("無數據");
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						// Double onModulus=null;
						if (x < alllist2.size()) {// start "if 1"
							try {
								day = alllist2.get(x).get(z).getId()
										.getYymmdd().getDate();
								onModulus = alllist2.get(x).get(z)
										.getOnModulus();
								personnum = alllist2.get(x).get(z)
										.getPersonnum();
								daycount = alllist2.get(x).get(z).getDaycount();
								achievingRate = alllist2.get(x).get(z)
										.getAchievingRate();
								holiday = alllist2.get(x).get(z)
										.getWorkorholiday();
								if (onModulus != null && z == day - 1) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue(onModulus);
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_thousand);
								}
								if (onModulus != null && z != day - 1) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue(onModulus);
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_thousand);
								}

								if (onModulus == null && z == day - 1
										&& holiday.equals("1")) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue("假日");
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_font_blue);
								}

								if (onModulus == null && z != day - 1
										&& holiday.equals("1")) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue("假日");
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_font_blue);
								}
								if (onModulus == null && z == day - 1
										&& holiday.equals("2")) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue("未排產");
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_font_green);
								}

								if (onModulus == null && z != day - 1
										&& holiday.equals("2")) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue("未排產");
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_font_green);
								}
								if (onModulus == null
										&& z == day - 1
										&& (personnum != null
												|| daycount != null || achievingRate != null)) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue("無數據");
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_font_red);
								}
								if (onModulus == null
										&& z != day - 1
										&& (personnum != null
												|| daycount != null || achievingRate != null)) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue("無數據");
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_font_red);
								}
								sumOnModulus = sumOnModulus + onModulus;
							} catch (Exception e) {
								// TODO: handle exception
							}
							if (z == z_length - 2) {
								sheet.getRow(z + height + totalHeight + 1)
										.createCell(y + width)
										.setCellValue(sumOnModulus);
								sheet.getRow(z + height + totalHeight + 1)
										.getCell(y + width)
										.setCellStyle(cs_thousand);
							}
						}// end "if 1"

						break;
					case 2:
						if (sheet.getRow(z + height + totalHeight + 1).getCell(
								y + width) == null) {
							// sheet.getRow(z+height+totalHeight+1).createCell(y+width).setCellStyle(cs);
							StringBuffer olddate = new StringBuffer();
							SimpleDateFormat dateFormat = new SimpleDateFormat(
									"yyyyMMdd");
							olddate.append(date);
							if (z == z_length - 1) {
								olddate.append(z);
							}
							if (z < 9) {
								olddate.append("0");
								olddate.append(z + 1);
							}
							if (z >= 9) {
								olddate.append(z + 1);
							}
							String temp1 = olddate.toString();
							String temp2 = dateFormat.format(new Date());
							try {
								Date beginDate = dateFormat.parse(temp1);
								Date endDate = dateFormat.parse(temp2);
								long beginNum = beginDate.getTime();
								long endNum = endDate.getTime();
								if (beginNum >= endNum) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellStyle(cs_font_blue);
									// sheet.getRow(z+height+totalHeight+1).getCell(y+width).setCellValue("日期超前");
								} else {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellStyle(cs_font_red);
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellValue("無數據");
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						// Double personnum=null;
						if (x < alllist2.size()) {// start "if 2"
							try {
								day = alllist2.get(x).get(z).getId()
										.getYymmdd().getDate();
								personnum = alllist2.get(x).get(z)
										.getPersonnum();
								onModulus = alllist2.get(x).get(z)
										.getOnModulus();
								daycount = alllist2.get(x).get(z).getDaycount();
								achievingRate = alllist2.get(x).get(z)
										.getAchievingRate();
								holiday = alllist2.get(x).get(z)
										.getWorkorholiday();
								if (personnum != null && z == day - 1) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue(personnum);
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_thousand_person);
								}
								if (personnum != null && z != day - 1) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue(personnum);
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_thousand_person);
								}
								if (personnum == null && z == day - 1
										&& holiday.equals("1")) {

									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue("假日");
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_font_blue);
								}
								if (personnum == null && z != day - 1
										&& holiday.equals("1")) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue("假日");
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_font_blue);
								}
								if (personnum == null && z == day - 1
										&& holiday.equals("2")) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue("未排產");
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_font_green);
								}
								if (personnum == null && z != day - 1
										&& holiday.equals("2")) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue("未排產");
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_font_green);
								}
								if (personnum == null
										&& z == day - 1
										&& (onModulus != null
												|| daycount != null || achievingRate != null)) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue("無數據");
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_font_red);
								}
								if (personnum == null
										&& z != day - 1
										&& (onModulus != null
												|| daycount != null || achievingRate != null)) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue("無數據");
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_font_red);
								}
								sumPersonnum = sumPersonnum + personnum;

							} catch (Exception e) {
								// TODO: handle exception
							}
							if (z == z_length - 2) {
								sheet.getRow(z + height + totalHeight + 1)
										.createCell(y + width)
										.setCellValue(sumPersonnum);
								sheet.getRow(z + height + totalHeight + 1)
										.getCell(y + width)
										.setCellStyle(cs_thousand_person);
							}
						}// end "if 2"

						break;
					case 3:
						if (sheet.getRow(z + height + totalHeight + 1).getCell(
								y + width) == null) {
							// sheet.getRow(z+height+totalHeight+1).createCell(y+width).setCellStyle(cs);
							StringBuffer olddate = new StringBuffer();
							SimpleDateFormat dateFormat = new SimpleDateFormat(
									"yyyyMMdd");
							olddate.append(date);
							if (z == z_length - 1) {
								olddate.append(z);
							}
							if (z < 9) {
								olddate.append("0");
								olddate.append(z + 1);
							}
							if (z >= 9) {
								olddate.append(z + 1);
							}

							String temp1 = olddate.toString();
							String temp2 = dateFormat.format(new Date());
							try {
								Date beginDate = dateFormat.parse(temp1);
								Date endDate = dateFormat.parse(temp2);
								long beginNum = beginDate.getTime();
								long endNum = endDate.getTime();
								if (beginNum >= endNum) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellStyle(cs_font_blue);
									// sheet.getRow(z+height+totalHeight+1).getCell(y+width).setCellValue("日期超前");
								} else {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellStyle(cs_font_red);
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellValue("無數據");
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						if (x < alllist2.size()) {// start "if 3"
							try {
								day = alllist2.get(x).get(z).getId()
										.getYymmdd().getDate();
								onModulus = alllist2.get(x).get(z)
										.getOnModulus();
								personnum = alllist2.get(x).get(z)
										.getPersonnum();
								daycount = alllist2.get(x).get(z).getDaycount();
								standardOutput = alllist2.get(x).get(z)
										.getStandardOutput();
								holiday = alllist2.get(x).get(z)
										.getWorkorholiday();
								if (standardOutput != null && z == day - 1) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue(standardOutput);
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_thousand);
								}
								if (standardOutput != null && z != day - 1) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue(standardOutput);
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_thousand);
								}
								if (standardOutput == null && z == day - 1
										&& holiday.equals("1")) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue("假日");
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_font_blue);
								}
								if (standardOutput == null && z != day - 1
										&& holiday.equals("1")) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue("假日");
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_font_blue);
								}
								if (standardOutput == null && z == day - 1
										&& holiday.equals("2")) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue("未排產");
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_font_green);
								}
								if (standardOutput == null && z != day - 1
										&& holiday.equals("2")) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue("未排產");
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_font_green);
								}
								if (standardOutput == null
										&& z == day - 1
										&& (onModulus != null
												|| personnum != null || daycount != null)) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue("無數據");
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_font_red);
								}
								if (standardOutput == null
										&& z != day - 1
										&& (onModulus != null
												|| personnum != null || daycount != null)) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue("無數據");
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_font_red);
								}
								sumStandardOutput = sumStandardOutput
										+ standardOutput;

							} catch (Exception e) {
								// TODO: handle exception
							}
							if (z == z_length - 2) {
								sheet.getRow(z + height + totalHeight + 1)
										.createCell(y + width)
										.setCellValue(sumStandardOutput);
								sheet.getRow(z + height + totalHeight + 1)
										.getCell(y + width)
										.setCellStyle(cs_thousand);
							}
						}// end "if 3"

						break;
					case 4:
						if (sheet.getRow(z + height + totalHeight + 1).getCell(
								y + width) == null) {
							// sheet.getRow(z+height+totalHeight+1).createCell(y+width).setCellStyle(cs);
							StringBuffer olddate = new StringBuffer();
							SimpleDateFormat dateFormat = new SimpleDateFormat(
									"yyyyMMdd");
							olddate.append(date);
							if (z == z_length - 1) {
								olddate.append(z);
							}
							if (z < 9) {
								olddate.append("0");
								olddate.append(z + 1);
							}
							if (z >= 9) {
								olddate.append(z + 1);
							}

							String temp1 = olddate.toString();
							String temp2 = dateFormat.format(new Date());
							try {
								Date beginDate = dateFormat.parse(temp1);
								Date endDate = dateFormat.parse(temp2);
								long beginNum = beginDate.getTime();
								long endNum = endDate.getTime();
								if (beginNum >= endNum) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellStyle(cs_font_blue);
									// sheet.getRow(z+height+totalHeight+1).getCell(y+width).setCellValue("日期超前");
								} else {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellStyle(cs_font_red);
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellValue("無數據");
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						if (x < alllist2.size()) {// start "if 4"
							try {
								day = alllist2.get(x).get(z).getId()
										.getYymmdd().getDate();
								onModulus = alllist2.get(x).get(z)
										.getOnModulus();
								personnum = alllist2.get(x).get(z)
										.getPersonnum();
								daycount = alllist2.get(x).get(z).getDaycount();
								actualYield = alllist2.get(x).get(z)
										.getActualYield();
								holiday = alllist2.get(x).get(z)
										.getWorkorholiday();
								if (actualYield != null && z == day - 1) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue(actualYield);
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_thousand);
								}
								if (actualYield != null & z != day - 1) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue(actualYield);
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_thousand);
								}
								if (actualYield == null && z == day - 1
										&& holiday.equals("1")) {

									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue("假日");
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_font_blue);
								}
								if (actualYield == null && z != day - 1
										&& holiday.equals("1")) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue("假日");
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_font_blue);
								}
								if (actualYield == null && z == day - 1
										&& holiday.equals("2")) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue("未排產");
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_font_green);
								}
								if (actualYield == null && z != day - 1
										&& holiday.equals("2")) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue("未排產");
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_font_green);
								}
								if (actualYield == null
										&& z == day - 1
										&& (onModulus != null
												|| personnum != null || daycount != null)) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue("無數據");
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_font_red);
								}
								if (actualYield == null
										&& z != day - 1
										&& (onModulus != null
												|| personnum != null || daycount != null)) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue("無數據");
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_font_red);
								}
								sumActualYield = sumActualYield + actualYield;

							} catch (Exception e) {
								// TODO: handle exception
							}
							if (z == z_length - 2) {
								sheet.getRow(z + height + totalHeight + 1)
										.createCell(y + width)
										.setCellValue(sumActualYield);
								sheet.getRow(z + height + totalHeight + 1)
										.getCell(y + width)
										.setCellStyle(cs_thousand);
							}
						}// end "if 4"

						break;
					case 5:
						if (sheet.getRow(z + height + totalHeight + 1).getCell(
								y + width) == null) {
							// sheet.getRow(z+height+totalHeight+1).createCell(y+width).setCellStyle(cs);
							StringBuffer olddate = new StringBuffer();
							SimpleDateFormat dateFormat = new SimpleDateFormat(
									"yyyyMMdd");
							olddate.append(date);
							if (z == z_length - 1) {
								olddate.append(z);
							}
							if (z < 9) {
								olddate.append("0");
								olddate.append(z + 1);
							}
							if (z >= 9) {
								olddate.append(z + 1);
							}
							String temp1 = olddate.toString();
							String temp2 = dateFormat.format(new Date());
							try {
								Date beginDate = dateFormat.parse(temp1);
								Date endDate = dateFormat.parse(temp2);
								long beginNum = beginDate.getTime();
								long endNum = endDate.getTime();
								if (beginNum >= endNum) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellStyle(cs_font_blue);
									// sheet.getRow(z+height+totalHeight+1).getCell(y+width).setCellValue("日期超前");
								} else {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellStyle(cs_font_red);
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellValue("無數據");
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						if (x < alllist2.size()) {// start "if 5"
							try {
								day = alllist2.get(x).get(z).getId()
										.getYymmdd().getDate();
								onModulus = alllist2.get(x).get(z)
										.getOnModulus();
								personnum = alllist2.get(x).get(z)
										.getPersonnum();
								achievingRate = alllist2.get(x).get(z)
										.getAchievingRate();
								daycount = alllist2.get(x).get(z).getDaycount();
								holiday = alllist2.get(x).get(z)
										.getWorkorholiday();
								if (achievingRate != null && z == day - 1) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue(achievingRate);
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_data);
								}
								if (achievingRate != null && z != day - 1) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue(achievingRate);
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_data);
								}
								if (achievingRate == null && z == day - 1
										&& holiday.equals("1")) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue("假日");
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_font_blue);
								}

								if (achievingRate == null && z != day - 1
										&& holiday.equals("1")) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue("假日");
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_font_blue);
								}
								if (achievingRate == null && z == day - 1
										&& holiday.equals("2")) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue("未排產");
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_font_green);
									sumNodate=sumNodate+1;
								}

								if (achievingRate == null && z != day - 1
										&& holiday.equals("2")) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue("未排產");
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_font_green);
								}
								if (achievingRate == null
										&& z == day - 1
										&& (onModulus != null
												|| personnum != null || daycount != null)) {
									sheet.getRow(z + height + totalHeight + 1)
											.createCell(y + width)
											.setCellValue("無數據");
									sheet.getRow(z + height + totalHeight + 1)
											.getCell(y + width)
											.setCellStyle(cs_font_red);
									
								}
								if (achievingRate == null
										&& z != day - 1
										&& (onModulus != null
												|| personnum != null || daycount != null)) {
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).createCell(y + width)
											.setCellValue("無數據");
									sheet.getRow(
											(day - 1) + height + totalHeight
													+ 1).getCell(y + width)
											.setCellStyle(cs_font_red);
									
								}
								sumDayCount = sumDayCount + daycount;
							} catch (Exception e) {
								// TODO: handle exception
							}
							if (z == z_length - 2) {
								sheet.getRow(z + height + totalHeight + 1)
										.createCell(y + width)
										.setCellValue(sumAchievingRate);
								sheet.getRow(z + height + totalHeight + 1)
										.getCell(y + width)
										.setCellStyle(cs_data);
							}
							/*
							 * 計算總天數
							 */
							if (z == z_length - 1) {
								CellRangeAddress region_daycount = new CellRangeAddress(
										z + height + totalHeight + 1, (short) z
												+ height + totalHeight + 1, y
												+ width - 4, (short) y + width);
								sheet.addMergedRegion(region_daycount);
								sheet.getRow(z + height + totalHeight + 1)
										.createCell(y + width)
										.setCellStyle(cs_thousand);
								// 由於應用了初始化時,日期超前的單元格格式,所以要轉成"cs_thousand"格式
								sheet.getRow(z + height + totalHeight + 1)
										.getCell(y + width - 4)
										.setCellStyle(cs_thousand);
								sheet.getRow(z + height + totalHeight + 1)
										.getCell(y + width - 4)
										.setCellValue(sumDayCount);
								
								sheet.getRow(z + height + totalHeight + 2)
								.createCell(y + width-4 )
								.setCellStyle(cs_head);
						        sheet.getRow(z + height + totalHeight + 2)
								.getCell(y + width -4)
								.setCellValue(sumNodate);
						        						        
							}
						}// end "if 5"

						break;
					/*
					 * case 6: if (sheet.getRow(z + height + totalHeight +
					 * 1).getCell(y + width) == null) { //
					 * sheet.getRow(z+height+
					 * totalHeight+1).createCell(y+width).setCellStyle(cs);
					 * StringBuffer olddate = new StringBuffer();
					 * SimpleDateFormat dateFormat = new
					 * SimpleDateFormat("yyyyMMdd"); olddate.append(yymm); if (z
					 * == z_length-1) { olddate.append(z); } if (z < 9) {
					 * olddate.append("0"); olddate.append(z + 1); } if (z >= 9)
					 * { olddate.append(z + 1); } String temp1 =
					 * olddate.toString(); String temp2 = dateFormat.format(new
					 * Date()); try { Date beginDate = dateFormat.parse(temp1);
					 * Date endDate = dateFormat.parse(temp2); long beginNum =
					 * beginDate.getTime(); long endNum = endDate.getTime(); if
					 * (beginNum > endNum) { sheet.getRow(z + height+
					 * totalHeight+ 1).createCell(y +
					 * width).setCellStyle(cs_font_blue); //
					 * sheet.getRow(z+height
					 * +totalHeight+1).getCell(y+width).setCellValue("日期超前"); }
					 * else { sheet.getRow(z + height+ totalHeight+
					 * 1).createCell(y + width).setCellStyle(cs_font_red);
					 * sheet.getRow(z + height+ totalHeight+ 1).getCell(y +
					 * width).setCellValue("無數據"); } } catch (ParseException e)
					 * { // TODO Auto-generated catch block e.printStackTrace();
					 * } } if(x<alllist2.size()){//start "if 6" try { day =
					 * alllist2.get(x).get(z).getId().getYymmdd().getDate();
					 * onModulus = alllist2.get(x).get(z).getOnModulus();
					 * personnum = alllist2.get(x).get(z).getPersonnum();
					 * achievingRate =
					 * alllist2.get(x).get(z).getAchievingRate(); daycount =
					 * alllist2.get(x).get(z).getDaycount(); holiday =
					 * alllist2.get(x).get(z).getWorkorholiday(); if (daycount
					 * != null && z == day - 1) { sheet.getRow(z + height +
					 * totalHeight+ 1).createCell(y +
					 * width).setCellValue(daycount); sheet.getRow(z + height +
					 * totalHeight+ 1).getCell(y +
					 * width).setCellStyle(cs_thousand); } if (daycount != null
					 * && z != day - 1) { sheet.getRow((day - 1) + height+
					 * totalHeight + 1).createCell(y +
					 * width).setCellValue(daycount); sheet.getRow((day - 1) +
					 * height+ totalHeight + 1).getCell(y +
					 * width).setCellStyle(cs_thousand); } if (daycount == null
					 * && z == day - 1&& holiday.equals("1")) { sheet.getRow(z +
					 * height + totalHeight+ 1).createCell(y +
					 * width).setCellValue("假日"); sheet.getRow(z + height +
					 * totalHeight+ 1).getCell(y +
					 * width).setCellStyle(cs_font_blue); } if (daycount == null
					 * && z != day - 1&& holiday.equals("1")) {
					 * sheet.getRow((day - 1) + height+ totalHeight +
					 * 1).createCell(y + width).setCellValue("假日");
					 * sheet.getRow((day - 1) + height+ totalHeight +
					 * 1).getCell(y + width).setCellStyle(cs_font_blue); } if
					 * (daycount == null && z == day - 1&& holiday.equals("2"))
					 * { sheet.getRow(z + height + totalHeight+ 1).createCell(y
					 * + width).setCellValue("未排產"); sheet.getRow(z + height +
					 * totalHeight+ 1).getCell(y +
					 * width).setCellStyle(cs_font_green); } if (daycount ==
					 * null && z != day - 1&& holiday.equals("2")) {
					 * sheet.getRow((day - 1) + height+ totalHeight +
					 * 1).createCell(y + width).setCellValue("未排產");
					 * sheet.getRow((day - 1) + height+ totalHeight +
					 * 1).getCell(y + width).setCellStyle(cs_font_green); } if
					 * (daycount == null&& z == day - 1&& (achievingRate !=
					 * null|| onModulus != null || personnum != null)) {
					 * sheet.getRow(z + height + totalHeight+ 1).createCell(y +
					 * width).setCellValue("無數據"); sheet.getRow(z + height +
					 * totalHeight+ 1).getCell(y +
					 * width).setCellStyle(cs_font_red); } if (daycount ==
					 * null&& z != day - 1&& (achievingRate != null|| onModulus
					 * != null || personnum != null)) { sheet.getRow((day - 1) +
					 * height+ totalHeight + 1).createCell(y +
					 * width).setCellValue("無數據"); sheet.getRow((day - 1) +
					 * height+ totalHeight + 1).getCell(y +
					 * width).setCellStyle(cs_font_red); } sumDayCount =
					 * sumDayCount + daycount;
					 * 
					 * } catch (Exception e) { // TODO: handle exception } if (z
					 * == z_length-2) { sheet.getRow(z + height + totalHeight +
					 * 1).createCell(y + width).setCellValue(sumDayCount);
					 * sheet.getRow(z + height + totalHeight + 1).getCell(y +
					 * width).setCellStyle(cs_thousand); } }//end "if 6"
					 * 
					 * break;
					 */

					}
				}// end for 3
			}// end for 2
			width = width + 5;
		}// end for 1
		totalHeight = totalHeight + 44;

		OutputStream os;
		try {
			os = new FileOutputStream("D:/" + date + ".xls");
			wb.write(os);
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
