package util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
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

		// 解析公式结果
		FormulaEvaluator evaluator = workbook.getCreationHelper()
				.createFormulaEvaluator();

		List<String> list = new ArrayList<String>();

		int minRowIx = sheet.getFirstRowNum()+1;
		int maxRowIx = sheet.getLastRowNum();//getLastRowNum獲取的行數比實際行數少1
		for (int rowIx = minRowIx; rowIx <= maxRowIx; rowIx++) {//注意：rowIx <= maxRowIx
			Row row = sheet.getRow(rowIx);
			StringBuilder sb = new StringBuilder();
			short minColIx = row.getFirstCellNum();
			short maxColIx = row.getLastCellNum();//getLastCellNum獲取到實際列數
			if(maxColIx-minColIx!=18){
				list.clear();
				break;
			}
			if(!sheet.getRow(minRowIx).getCell(minColIx).toString().equals("廠別")&&!sheet.getRow(minRowIx).getCell(minColIx).toString().equals("厂别")){
				list.clear();
				break;
			}
			for (short colIx = minColIx; colIx < maxColIx; colIx++) {//注意：colIx < maxColIx,與外循環的rowIx <= maxRowIx不同
				Cell cell = row.getCell(new Integer(colIx));
				CellValue cellValue = evaluator.evaluate(cell);
				if (cellValue == null) {
					//continue;(原先爲空的話，忽略掉)
					
					/*************現在，改爲如果爲空，創建新單元格，幷給值-2，標記這這裏爲空****************/
					cell=row.createCell(colIx);
					cell.setCellValue(-2);
					cellValue=evaluator.evaluate(cell);
					/*************現在，改爲如果爲空，創建新單元格，幷給值-2，標記這這裏爲空****************/
				}
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path="d:\\北越&鞋塑2015接單匯總-1201.xls";
		//String path="e:\\qqq.xlsx";
		List<String>list=null;
		try{
			list=exportListFromExcel(new File(path),1);
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

	}

}
