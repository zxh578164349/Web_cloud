package util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
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


public class ImportExcel {
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
		Sheet sheet = workbook.getSheetAt(sheetNum);
		int totalSheets=workbook.getNumberOfSheets();

		// 解析公式结果
		FormulaEvaluator evaluator = workbook.getCreationHelper()
				.createFormulaEvaluator();

		List<String> list = new ArrayList<String>();

		//int minRowIx = sheet.getFirstRowNum()+1;
		int row_head = sheet.getFirstRowNum()+1;	    
		int maxRowIx = sheet.getLastRowNum();//（getLastRowNum獲取的行數可能比實際行數少1或不少,視測試情況而定）

		if(totalSheets==1){// if 隻允許文檔存在1張sheet
			for (int rowIx = row_head; rowIx <= maxRowIx-1; rowIx++) {//注意：rowIx <= maxRowIx//注意：rowIx <= maxRowIx（減去最后一行匯總20160122）
				Row row = sheet.getRow(rowIx);
				StringBuilder sb = new StringBuilder();
				//不允許表頭爲空行null
				if(row==null){
					list.clear();
					break;
				}
				short minColIx = row.getFirstCellNum();
				int maxColIx = row.getLastCellNum()-1;//getLastCellNum獲取到實際列數(減去最後一列匯總20160122)
				if(maxColIx-minColIx>19&&(maxRowIx-row_head>2000||maxRowIx-row_head<3)){
					list.clear();
					break;
				}
				if(rowIx==row_head&&
						(!sheet.getRow(row_head).getCell(minColIx).getStringCellValue().equals("廠別")||
						!sheet.getRow(row_head).getCell(minColIx+1).getStringCellValue().equals("廠別狀態")||
						!sheet.getRow(row_head).getCell(minColIx+2).getStringCellValue().equals("品牌")||
						!sheet.getRow(row_head).getCell(minColIx+3).getStringCellValue().contains("客")||
						!sheet.getRow(row_head).getCell(minColIx+4).getStringCellValue().equals("模具")||
						!sheet.getRow(row_head).getCell(minColIx+5).getStringCellValue().equals("部件"))){
					list.clear();
					break;
				}
				for (short colIx = minColIx; colIx < maxColIx; colIx++) {//注意：colIx < maxColIx,與外循環的rowIx <= maxRowIx不同
					Cell cell = row.getCell(new Integer(colIx));
										
					CellValue cellValue = evaluator.evaluate(cell);					
					if (cellValue == null) {//if1
						//continue;(原先爲空的話，忽略掉)
						
						/*************現在，改爲如果爲空，創建新單元格，幷給新值;如果是非數據列，則取上一行的的值；否則給值0，標記這這裏爲空****************/
						cell=row.createCell(colIx);																			
							if(colIx<minColIx+6){
								sheet.getRow(rowIx-1).getCell(colIx).setCellType(Cell.CELL_TYPE_STRING);//先轉換成CELL_TYPE_STRING，否則可能報數據類型不對的情況20160128
								cell.setCellValue(sheet.getRow(rowIx-1).getCell(colIx).getStringCellValue());
							}else{
								cell.setCellValue(0);
							}
							cellValue=evaluator.evaluate(cell);
							/*************現在，改爲如果爲空，創建新單元格，幷給新值;如果是非數據列，則取上一行的的值；否則給值0，標記這這裏爲空****************/
								
					}//if1
					// 经过公式解析，最后只存在Boolean、Numeric和String三种数据类型，此外就是Error了
					// 其余数据类型，根据官方文档，完全可以忽略http://poi.apache.org/spreadsheet/eval.html
					switch (cellValue.getCellType()) {
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
						sb.append(SEPARATOR + cellValue.getStringValue());
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
	
	
	
	
	/***********************************************************導入聯系資料****************************************************************************/
	/**
	 * 由指定的Sheet导出至List
	 * 
	 * @param workbook
	 * @param sheetNum
	 * @return
	 * @throws IOException
	 */
	private static Map<String,Object> exportListFromExcel(Workbook workbook) {			
		
		Map<String,Object>map=new HashMap<String,Object>();
		for(int a=0;a<workbook.getNumberOfSheets();a++){//for a
			Sheet sheet = workbook.getSheetAt(a);
			
			// 解析公式结果
			FormulaEvaluator evaluator = workbook.getCreationHelper()
					.createFormulaEvaluator();

			List<String> list = new ArrayList<String>();

			//int minRowIx = sheet.getFirstRowNum()+1;
			int row_head = sheet.getFirstRowNum()+1;
			int maxRowIx = sheet.getLastRowNum();//（getLastRowNum獲取的行數可能比實際行數少1或不少,視測試情況而定）

			for (int rowIx = row_head; rowIx <= maxRowIx; rowIx++) {
				Row row = sheet.getRow(rowIx);
				StringBuilder sb = new StringBuilder();
				//不允許表頭爲空行null
				if(row==null){
					//list.clear();
					break;
				}
				short minColIx = row.getFirstCellNum();
				int maxColIx = row.getLastCellNum();
				if(maxColIx-minColIx>18&&(maxRowIx-row_head>2000||maxRowIx-row_head<3)){
					list.clear();
					break;
				}
				
				if(rowIx==row_head&&
						(!sheet.getRow(row_head).getCell(minColIx).getStringCellValue().equals("序號")||
						!sheet.getRow(row_head).getCell(minColIx+1).getStringCellValue().equals("單位")||
						!sheet.getRow(row_head).getCell(minColIx+2).getStringCellValue().equals("姓名")||
						!sheet.getRow(row_head).getCell(minColIx+3).getStringCellValue().equals("職務")||
						!sheet.getRow(row_head).getCell(minColIx+4).getStringCellValue().equals("內線")||
						!sheet.getRow(row_head).getCell(minColIx+5).getStringCellValue().equals("手機")||
						!sheet.getRow(row_head).getCell(minColIx+6).getStringCellValue().equals("郵箱")||
						!sheet.getRow(row_head).getCell(minColIx+7).getStringCellValue().equals("短號"))){
					list.clear();
					break;
				}
				for (short colIx = minColIx; colIx < maxColIx; colIx++) {//注意：減去備註這一列
					Cell cell = row.getCell(new Integer(colIx));
					if(cell!=null){
						cell.setCellType(Cell.CELL_TYPE_STRING);
						if(cell.getStringCellValue().trim().equals("")||cell.getStringCellValue()==null){
							cell.setCellValue("空");
						}
					}else{
						cell=row.createCell(colIx);
						cell.setCellValue("空");
					}
					sb.append(SEPARATOR+(cell.getStringCellValue()));										
				}
				list.add(sb.toString());
			}
			
			if(list!=null&&list.size()>1){
				map.put(sheet.getSheetName(), list);
			}
			
		}//end for a				
		return map;
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
	public static Map<String,Object> exportListFromExcel(InputStream is,
			String extensionName) throws IOException {

		Workbook workbook = null;

		if (extensionName.toLowerCase().equals(XLS)) {
			workbook = new HSSFWorkbook(is);
		} else if (extensionName.toLowerCase().equals(XLSX)) {
			workbook = new XSSFWorkbook(is);
		}
        is.close();
		return exportListFromExcel(workbook);
	}
	/**
	 * 由Excel文件的Sheet导出至List
	 * 
	 * @param file
	 * @param sheetNum
	 * @return
	 */
	public static Map<String,Object> exportListFromExcel(File file)
			throws IOException {
		return exportListFromExcel(new FileInputStream(file),
				FilenameUtils.getExtension(file.getName()));
	}

	/***********************************************************導入聯系資料****************************************************************************/	
	/**
	 * @param args
	 */
	
	
	
	
	
	
	/***********************************************************測試20160128****************************************************************************/
	/**
	 * 由指定的Sheet导出至List
	 * 
	 * @param workbook
	 * @param sheetNum
	 * @return
	 * @throws IOException
	 */
	private static Map<String,Object> exportListFromExcel2(Workbook workbook) {			
		
		Map<String,Object>map=new HashMap<String,Object>();
		for(int a=0;a<workbook.getNumberOfSheets();a++){//for a
			Sheet sheet = workbook.getSheetAt(a);
			
			// 解析公式结果
			FormulaEvaluator evaluator = workbook.getCreationHelper()
					.createFormulaEvaluator();

			List<String> list = new ArrayList<String>();

			//int minRowIx = sheet.getFirstRowNum()+1;
			int row_head = sheet.getFirstRowNum();
			int maxRowIx = sheet.getLastRowNum();//（getLastRowNum獲取的行數可能比實際行數少1或不少,視測試情況而定）

			for (int rowIx = row_head; rowIx <= maxRowIx; rowIx++) {
				Row row = sheet.getRow(rowIx);
				StringBuilder sb = new StringBuilder();
				//不允許表頭爲空行null
				if(row==null){
					//list.clear();
					break;
				}
				short minColIx = row.getFirstCellNum();
				int maxColIx = row.getLastCellNum();
				if(maxColIx-minColIx>18&&(maxRowIx-row_head>2000||maxRowIx-row_head<3)){
					list.clear();
					break;
				}
				
				/*if(rowIx==row_head&&
						(!sheet.getRow(row_head).getCell(minColIx).getStringCellValue().equals("序號")||
						!sheet.getRow(row_head).getCell(minColIx+1).getStringCellValue().equals("單位")||
						!sheet.getRow(row_head).getCell(minColIx+2).getStringCellValue().equals("姓名")||
						!sheet.getRow(row_head).getCell(minColIx+3).getStringCellValue().equals("職務")||
						!sheet.getRow(row_head).getCell(minColIx+4).getStringCellValue().equals("內線")||
						!sheet.getRow(row_head).getCell(minColIx+5).getStringCellValue().equals("手機")||
						!sheet.getRow(row_head).getCell(minColIx+6).getStringCellValue().equals("郵箱")||
						!sheet.getRow(row_head).getCell(minColIx+7).getStringCellValue().equals("短號"))){
					list.clear();
					break;
				}*/
				for (short colIx = minColIx; colIx < maxColIx; colIx++) {//注意：減去備註這一列
					Cell cell = row.getCell(new Integer(colIx));
					if(cell!=null){
						cell.setCellType(Cell.CELL_TYPE_STRING);
						if(cell.getStringCellValue().trim().equals("")||cell.getStringCellValue()==null){
							cell.setCellValue("空");
						}
					}else{
						cell=row.createCell(colIx);
						cell.setCellValue("空");
					}
					sb.append((cell.getStringCellValue()));										
				}
				list.add(sb.toString());
			}
			
			if(list!=null&&list.size()>1){
				map.put(sheet.getSheetName(), list);
			}
			
		}//end for a				
		return map;
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
	public static Map<String,Object> exportListFromExcel2(InputStream is,
			String extensionName) throws IOException {

		Workbook workbook = null;

		if (extensionName.toLowerCase().equals(XLS)) {
			workbook = new HSSFWorkbook(is);
		} else if (extensionName.toLowerCase().equals(XLSX)) {
			workbook = new XSSFWorkbook(is);
		}
        is.close();
		return exportListFromExcel2(workbook);
	}
	/**
	 * 由Excel文件的Sheet导出至List
	 * 
	 * @param file
	 * @param sheetNum
	 * @return
	 */
	public static Map<String,Object> exportListFromExcel2(File file)
			throws IOException {
		return exportListFromExcel2(new FileInputStream(file),
				FilenameUtils.getExtension(file.getName()));
	}

	/***********************************************************測試20160128****************************************************************************/	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path="e:\\FVAS.xls";
		List<String>list=null;
		try{
			list=exportListFromExcel(new File(path),0);
			if(list.size()==0){
				System.out.println("数据結構錯誤");
			}else{
				for(int i=0;i<list.size();i++){
					System.out.println(i+1+":"+list.get(i));
				}
			}						
		}catch(Exception e){
			System.out.println(e);
		}
		
		/*String path="e:\\jy-2.xls";
		Map<String, Object> map;
		try {
			HSSFWorkbook wb=new HSSFWorkbook();
			HSSFSheet sheet=wb.createSheet("sheet1");
			for(int i=0;i<10;i++){
				sheet.setColumnWidth(i, 7000);
			}
			map = exportListFromExcel2(new File(path));
			for(String key:map.keySet()){
				System.out.println(key);
				for(String str:(List<String>)map.get(key)){
					System.out.println(str);
				}
				
					
				
				List<String>list=(List<String>)map.get(key);
				for(int j=0;j<list.size();j++){
					sheet.createRow(j);
					String[]objs=list.get(j).split(",");
					for(int i=0;i<objs.length;i++){
						String str=objs[i];
						sheet.getRow(j).createCell(i).setCellValue(str);
					}
				}
				
				
				
				
				OutputStream os=new FileOutputStream("e:\\tttttt2.xls");
				wb.write(os);
				os.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		

	}

}
