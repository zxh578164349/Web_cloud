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
		/**********************1：表循环***********************/
		for(int a=0;a<workbook.getNumberOfSheets();a++){//for a
			Sheet sheet = workbook.getSheetAt(a);			
			// 解析公式结果
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			List<String> list = new ArrayList<String>();

			//int minRowIx = sheet.getFirstRowNum()+1;
			int row_head = sheet.getFirstRowNum()+1;
			int maxRowIx = sheet.getLastRowNum();//（getLastRowNum獲取的行數可能比實際行數少1或不少,視測試情況而定）
			
			short minColIx = sheet.getRow(row_head).getFirstCellNum();
			int maxColIx = sheet.getRow(row_head).getLastCellNum();
			
			//如果表头的列数少于7，则退出当前循环，进入下一张sheet
			if(maxColIx-minColIx<7&&(maxRowIx-row_head>1000||maxRowIx-row_head<3)){
				continue;
			}
			//如果表头不符合规定的名字，也要进入下一张sheet
			if(
					(!sheet.getRow(row_head).getCell(minColIx).getStringCellValue().equals("序號")||
					!sheet.getRow(row_head).getCell(minColIx+1).getStringCellValue().equals("單位")||
					!sheet.getRow(row_head).getCell(minColIx+2).getStringCellValue().equals("姓名")||
					!sheet.getRow(row_head).getCell(minColIx+3).getStringCellValue().equals("職務")||
					!sheet.getRow(row_head).getCell(minColIx+4).getStringCellValue().equals("內線")||
					!sheet.getRow(row_head).getCell(minColIx+5).getStringCellValue().equals("手機")||
					!sheet.getRow(row_head).getCell(minColIx+6).getStringCellValue().equals("郵箱")||
					!sheet.getRow(row_head).getCell(minColIx+7).getStringCellValue().equals("短號"))){
				continue;
			}
			/**********************2：行循环***********************/
			for (int rowIx = row_head; rowIx <= maxRowIx; rowIx++) {
				Row row = sheet.getRow(rowIx);
				StringBuilder sb = new StringBuilder();
				if(row==null){
					continue;//空行，则进入到下一row
				}
				/**********************3：列循环***********************/
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
					sb.append(SEPARATOR+(cell.getStringCellValue().toString().trim()));										
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
		/*String path="e:\\FVAS.xls";
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
		}*/
		
		/*String path="i:\\二廠.xls";
		Map<String, Object>list=null;
		try{
			list=exportListFromExcel(new File(path));
			for(String str:list.keySet()){
				for(String str2:(List<String>)list.get(str)){
					System.out.println(str2);
				}
			}						
		}catch(Exception e){
			System.out.println(e);
		}*/
		List<Integer>list=new ArrayList<Integer>();
		for(int i=1;i<888;i++){
			list.add(i);
		}
		List<List<Integer>>list_all=new ArrayList<List<Integer>>();
		int a=list.size()/100;
		int b=list.size()%100;
		int c=b==0?a:(a+1);
		System.out.println((list.size()/100)*100);
		System.out.println(list.size()%100);
		for(int i=0;i<=list.size();i++){
			if(list.size()%100==0){
				if(i%100==0&&i>0){
					List<Integer>list_one=list.subList(i-100, i);
					list_all.add(list_one);
				}
				
			}else{
				if(i%100==0&&i>0){
					List<Integer>list_one=list.subList(i-100, i);
					list_all.add(list_one);
				}else if(i==(list.size()/100)*100+1){
					List<Integer>list_one=list.subList(i-1, list.size());	
					list_all.add(list_one);
				}				
			}
			
		}
		for(int i=0;i<list_all.size();i++){
			//System.out.println(list_all.get(i));
		}
		
		String temp="dfdf'dfdfdf";
		System.out.println(temp.replace("\'", " "));
		
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
