package util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import entity.WebFact;
import entity.WebFactId;
import entity.Weballobj;
import entity.WeballobjId;


public class ImportExcel_B {
	/**
	 * Excel 2003
	 */
	private final static String XLS = "xls";
	/**
	 * Excel 2007
	 */
	private final static String XLSX = "xlsx";
	/**
	 * 分隔符
	 */
	private final static String SEPARATOR = "__";
	
	
	
	
	
	
	/***********************************************************導入工廠訂單****************************************************************************/
	/**
	 * 由指定的Sheet导出至List
	 * 
	 * @param workbook
	 * @param sheetNum
	 * @return
	 * @throws IOException
	 */
	private static List<String> exportListFromExcel(Workbook workbook,
			int sheetNum) {		
		int totalSheets=workbook.getNumberOfSheets();
		List<String> list = new ArrayList<String>();
		if(totalSheets==1){// if 隻允許文檔存在1張sheet
			Sheet sheet = workbook.getSheetAt(sheetNum);			
			// 解析公式结果
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();						
			int row_head = sheet.getFirstRowNum()+1;	    
			int maxRowIx = sheet.getLastRowNum();//（getLastRowNum獲取的行數可能比實際行數少1或不少,視測試情況而定）
			//当前只有一张sheet
			/**********************1：表循环***********************/
			for(int all=0;all<1;all++){//for all
				short minColIx = sheet.getRow(row_head).getFirstCellNum();
				int maxColIx = sheet.getRow(row_head).getLastCellNum()-1;//getLastCellNum獲取到實際列數(減去最後一列匯總20160122)
				//如果表头的列数不符合，并且总行数也超支，则退出第1张sheet(总共也只有sheet)
				if(maxColIx-minColIx>19&&(maxRowIx-row_head>2000||maxRowIx-row_head<3)){
					break;
				}
				//如果表头不符合名称，也要退出第1张sheet
				if(	!sheet.getRow(row_head).getCell(minColIx).getStringCellValue().equals("廠別")||
						!sheet.getRow(row_head).getCell(minColIx+1).getStringCellValue().equals("廠別狀態")||
						!sheet.getRow(row_head).getCell(minColIx+2).getStringCellValue().equals("品牌")||
						!sheet.getRow(row_head).getCell(minColIx+3).getStringCellValue().contains("客")||
						!sheet.getRow(row_head).getCell(minColIx+4).getStringCellValue().equals("模具")||
						!sheet.getRow(row_head).getCell(minColIx+5).getStringCellValue().equals("部件")){
					break;
				}
				/**********************2：行循环***********************/
				for (int rowIx = row_head; rowIx <= maxRowIx-1; rowIx++) {//注意：rowIx <= maxRowIx//注意：rowIx <= maxRowIx（減去最后一行匯總20160122）
					Row row = sheet.getRow(rowIx);
					StringBuilder sb = new StringBuilder();
					if(row==null){
						continue;//如果当前空行，则进入下一行
					}
					/**********************3：列循环***********************/
					for (short colIx = minColIx; colIx < maxColIx; colIx++) {//注意：colIx < maxColIx,與外循環的rowIx <= maxRowIx不同
						Cell cell = row.getCell(colIx);
						if(cell==null){
							cell=row.createCell(colIx);														
						}
						cell.setCellType(Cell.CELL_TYPE_STRING);
						CellValue cellValue = evaluator.evaluate(cell);
						if(cellValue==null||cellValue.getStringValue().trim().equals("")){
							if(colIx<minColIx+6){									
								cell.setCellValue(sheet.getRow(rowIx-1).getCell(colIx).getStringCellValue());
							}else{
								cell.setCellValue(0);
							}
							cellValue=evaluator.evaluate(cell);
						}						
						// 经过公式解析，最后只存在Boolean、Numeric和String三种数据类型，此外就是Error了
						// 其余数据类型，根据官方文档，完全可以忽略http://poi.apache.org/spreadsheet/eval.html
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_BOOLEAN:
							sb.append(SEPARATOR + cellValue.getBooleanValue());
							break;
						case Cell.CELL_TYPE_NUMERIC:
							// 这里的日期类型会被转换为数字类型，需要判别后区分处理
							if (DateUtil.isCellDateFormatted(cell)) {
								sb.append(SEPARATOR + cell.getDateCellValue());
							} else {
								sb.append(SEPARATOR + cellValue.getNumberValue());
							}
							break;
						case Cell.CELL_TYPE_STRING:
							if(cellValue.getStringValue().contains("'")){
								sb.append(SEPARATOR + cellValue.getStringValue().replace("'", " ").trim());//如果含有單引號，要去掉，否則會搜不到數據20160202
							}else{
								sb.append(SEPARATOR + cellValue.getStringValue().trim());
							}							
							break;
						case Cell.CELL_TYPE_FORMULA:
							break;
						case Cell.CELL_TYPE_BLANK:
							break;
						case Cell.CELL_TYPE_ERROR:
							break;
						default:
							break;
						}
					}
					list.add(sb.toString());
				}
			}//for all
			
		}//if				
		return list;
	}
	
	/**
	 * 由Excel流的Sheet导出至List
	 * 
	 * @param is
	 * @param extensionName
	 * @param sheetNum
	 * @return
	 * @throws IOException
	 */
	public static List<String> exportListFromExcel(InputStream is,
			String extensionName, int sheetNum) throws IOException {

		Workbook workbook = null;

		if (extensionName.toLowerCase().equals(XLS)) {
			workbook = new HSSFWorkbook(is);
		} else if (extensionName.toLowerCase().equals(XLSX)) {
			workbook = new XSSFWorkbook(is);
		}
        is.close();
		return exportListFromExcel(workbook, sheetNum);
	}
	/**
	 * 由Excel文件的Sheet导出至List
	 * 
	 * @param file
	 * @param sheetNum
	 * @return
	 */
	public static List<String> exportListFromExcel(File file, int sheetNum)
			throws IOException {
		return exportListFromExcel(new FileInputStream(file),
				FilenameUtils.getExtension(file.getName()), sheetNum);
	}
	
	/***********************************************************導入工廠訂單****************************************************************************/
	
								
	
	/************************************************************* 導入新的kpi數據 *************************************************************************/
	/**
	 * 導入新的kpi數據
	 * 
	 * @Title: impWeballobjExl
	 * @Description: TODO
	 * @param @param book
	 * @param @return
	 * @return Map<String,Object>
	 * @throws
	 * @author web
	 * @date 2016/3/24
	 */
	public static Map<String, Object> impWeballobjExl(Workbook wb) {
		Map<String, Object> map = new HashMap<String, Object>();
		int sheets = wb.getNumberOfSheets();// 獲取所有的sheet
		FormulaEvaluator eval = wb.getCreationHelper().createFormulaEvaluator();
		for (int i = 0; i < sheets; i++) {// for a
			int headrow = wb.getSheetAt(i).getFirstRowNum() + 1;// 排除標題
			int lastrow = wb.getSheetAt(i).getLastRowNum();
			int tt = wb.getSheetAt(i).getPhysicalNumberOfRows();
			if (tt == 0 && lastrow == 0) {// 判斷表格是否有內容，如果單元格有空格，也會表示有內容
				break;
			}

			int temp = 0;
			Row row = wb.getSheetAt(i).getRow(headrow);
			while (row == null || isRowEmpty(row)) {// 判斷row是否null或者空行
				temp++;
				row = wb.getSheetAt(i).getRow(headrow + temp);
			}
			int firstcol = row.getFirstCellNum();// 排隊序號列
			int lastcol = row.getLastCellNum();

			while (row.getCell(firstcol) == null
					|| row.getCell(firstcol).getCellType() == Cell.CELL_TYPE_BLANK) {
				firstcol++;
			}
			while (row.getCell(lastcol) == null
					|| row.getCell(lastcol).getCellType() == Cell.CELL_TYPE_BLANK) {
				lastcol--;
			}

			List<String> list = new ArrayList<String>();
			for (int j = headrow; j <= lastrow; j++) {// for b
				if (wb.getSheetAt(i).getRow(j) == null
						|| isRowEmpty(wb.getSheetAt(i).getRow(j))) {// //判斷row是否null或者空行
					continue;
				}

				StringBuilder sb = new StringBuilder();
				for (int k = firstcol; k <= lastcol; k++) {// for c
					Cell cell = wb.getSheetAt(i).getRow(j).getCell(k);
					if (cell == null) {
						cell = wb.getSheetAt(i).getRow(j).createCell(k);// 如果新建的單元格沒有給數據類型，則默認爲空類型:Cell.CELL_TYPE_BLANK
					}
					CellValue cellValue = eval.evaluate(cell);// 如果Cell沒有給類型，cellValue同樣爲null,所以爲null空，默認cellValue=new
																// CellValue("0.0");
					if (cellValue == null) {
						cellValue = new CellValue("0.0");
					}
					switch (cellValue.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						sb.append(SEPARATOR + cellValue.getStringValue().trim());
						break;
					case Cell.CELL_TYPE_NUMERIC:
						sb.append(SEPARATOR + cellValue.getNumberValue());
						break;
					case Cell.CELL_TYPE_BLANK:
						if (k < firstcol) {
							sb.append(SEPARATOR + "null");
						} else {
							sb.append(SEPARATOR + 0.0);
						}
						break;
					}
				}// for c

				list.add(sb.toString());
			}// for b
			map.put(wb.getSheetAt(i).getSheetName(), list);
		}// for a
		return map;

	}

	/**
	 * 由流stream導入workbook
	 * 
	 * @param is
	 * @param extensionName
	 * @param sheetNum
	 * @return
	 * @throws IOException
	 */
	public static Map<String, Object> exportListFromStream(InputStream is,
			String extensionName) throws IOException {

		Workbook workbook = null;

		if (extensionName.toLowerCase().equals(XLS)) {
			workbook = new HSSFWorkbook(is);
		} else if (extensionName.toLowerCase().equals(XLSX)) {
			workbook = new XSSFWorkbook(is);
		}
		is.close();
		return impWeballobjExl(workbook);
	}

	/**
	 * 由Excel文件導入到流stream
	 * 
	 * @param file
	 * @param sheetNum
	 * @return
	 */
	public static Map<String, Object> exportListFromFile(File file)
			throws IOException {
		return exportListFromStream(new FileInputStream(file),
				FilenameUtils.getExtension(file.getName()));
	}

	/************************************************************* 導入新的kpi數據 *************************************************************************/
						
					 	 
	/************************************************************* 導入預計生產資料數據 *************************************************************************/
	/**
	 * 由流stream導入workbook
	 * 
	 * @param is
	 * @param extensionName
	 * @param sheetNum
	 * @return
	 * @throws IOException
	 */
	public static Map<String, Object> exportListFromStream2(InputStream is,
			String extensionName) throws IOException {

		Workbook workbook = null;

		if (extensionName.toLowerCase().equals(XLS)) {
			workbook = new HSSFWorkbook(is);
		} else if (extensionName.toLowerCase().equals(XLSX)) {
			workbook = new XSSFWorkbook(is);
		}
		is.close();
		return impWeballobjExl(workbook);
	}

	/**
	 * 由Excel文件導入到流stream
	 * 
	 * @param file
	 * @param sheetNum
	 * @return
	 */
	public static Map<String, Object> exportListFromFile2(File file)
			throws IOException {
		return exportListFromStream(new FileInputStream(file),
				FilenameUtils.getExtension(file.getName()));
	}

	/************************************************************* 導入預計生產資料數據 *************************************************************************/
	 
	 
			
			
	/********************************** 導入 (1)每日開發試模登記表 (2)樣品接單進度狀況表 (3)原物料&粗胚進耗存登記表 ***********************/
	/**
	 * 導入新的kpi數據
	 * 
	 * @Title: impWeballobjExl
	 * @Description: TODO
	 * @param @param book
	 * @param @param entityType判斷導入的是什麼數據表，時行相應的數據串連
	 * @param @return
	 * @return Map<String,Object>
	 * @throws
	 * @author web
	 * @date 2019/3/29
	 */
	public static Map<String, Object> impWeballobjExl(Workbook wb,
			String entityType) {
		Map<String, Object> map = new HashMap<String, Object>();
		int sheets = wb.getNumberOfSheets();// 獲取所有的sheet
		FormulaEvaluator eval = wb.getCreationHelper().createFormulaEvaluator();
		for (int i = 0; i < sheets; i++) {// for a
			int headrow = wb.getSheetAt(i).getFirstRowNum() + 1;// 排除標題
			int lastrow = wb.getSheetAt(i).getLastRowNum();
			int tt = wb.getSheetAt(i).getPhysicalNumberOfRows();
			if (tt == 0 && lastrow == 0) {// 判斷表格是否有內容，如果單元格有空格，也會表示有內容
				break;
			}

			int temp = 0;
			Row row = wb.getSheetAt(i).getRow(headrow);
			while (row == null || isRowEmpty(row)) {// 判斷row是否null或者空行
				temp++;
				row = wb.getSheetAt(i).getRow(headrow + temp);
			}
			int firstcol = row.getFirstCellNum();// 排隊序號列
			int lastcol = row.getLastCellNum();

			while (row.getCell(firstcol) == null
					|| row.getCell(firstcol).getCellType() == Cell.CELL_TYPE_BLANK) {
				firstcol++;
			}
			while (row.getCell(lastcol) == null
					|| row.getCell(lastcol).getCellType() == Cell.CELL_TYPE_BLANK) {
				lastcol--;
			}

			List<String> list = new ArrayList<String>();
			for (int j = headrow; j <= lastrow; j++) {// for b
				if (wb.getSheetAt(i).getRow(j) == null
						|| isRowEmpty(wb.getSheetAt(i).getRow(j))) {// //判斷row是否null或者空行
					continue;
				}

				StringBuilder sb = new StringBuilder();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				for (int k = firstcol; k <= lastcol; k++) {// for c
					Cell cell = wb.getSheetAt(i).getRow(j).getCell(k);
					if (cell == null) {
						cell = wb.getSheetAt(i).getRow(j).createCell(k);// 如果新建的單元格沒有給數據類型，則默認爲空類型:Cell.CELL_TYPE_BLANK
					}
					CellValue cellValue = eval.evaluate(cell);// 如果Cell沒有給類型，cellValue同樣爲null,所以爲null空，默認cellValue=new
																// CellValue("");
					if (cellValue == null) {
						cellValue = new CellValue("");
					}
					switch (cellValue.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						sb.append(SEPARATOR + cellValue.getStringValue().trim());
						break;
					case Cell.CELL_TYPE_NUMERIC:
						// String ff=cell.getCellStyle().getDataFormatString();
						if (org.apache.poi.ss.usermodel.DateUtil
								.isCellDateFormatted(cell)) {
							Date date = org.apache.poi.ss.usermodel.DateUtil
									.getJavaDate(cell.getNumericCellValue());
							sb.append(SEPARATOR + sdf.format(date));
						} else {
							sb.append(SEPARATOR + cellValue.getNumberValue());
						}
						break;
					case Cell.CELL_TYPE_BLANK:
						if (k < firstcol) {
							sb.append(SEPARATOR + "null");
						} else {
							sb.append(SEPARATOR + "");
						}
						break;
					}
				}// for c

				list.add(sb.toString());
			}// for b
			map.put(wb.getSheetAt(i).getSheetName(), list);
		}// for a
		return map;

	}

	/**
	 * 由流stream導入workbook
	 * 
	 * @param is
	 * @param extensionName
	 * @param sheetNum
	 * @return
	 * @throws IOException
	 */
	public static Map<String, Object> exportListFromStream(InputStream is,
			String extensionName, String entityType) throws IOException {

		Workbook workbook = null;

		if (extensionName.toLowerCase().equals(XLS)) {
			workbook = new HSSFWorkbook(is);
		} else if (extensionName.toLowerCase().equals(XLSX)) {
			workbook = new XSSFWorkbook(is);
		}
		is.close();
		return impWeballobjExl(workbook, entityType);
	}

	/**
	 * 由Excel文件導入到流stream
	 * 
	 * @param file
	 * @param sheetNum
	 * @return
	 */
	public static Map<String, Object> exportListFromFile(File file,
			String entityType) throws IOException {
		return exportListFromStream(new FileInputStream(file),
				FilenameUtils.getExtension(file.getName()), entityType);
	}

	/********************************** 導入 (1)每日開發試模登記表 (2)樣品接單進度狀況表 (3)原物料&粗胚進耗存登記表 ***********************/
			
	/**
	 * 判斷是否空行20181011
	 * 
	 * @param row
	 * @return
	 */
	public static boolean isRowEmpty(Row row) {
		for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
			Cell cell = row.getCell(i);
			if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK) {
				return false;
			}
		}
		return true;
	}			
				
				
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		String a="1603300000";
		String b="1603311400";
		try {
			long time1=new SimpleDateFormat("yyMMddhhmm").parse(a).getTime();
			long time2=new Date().getTime();
			long result=(time2-time1)/(1000*60);
			System.out.println(result);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}

}
