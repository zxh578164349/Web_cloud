package email;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.internet.MimeUtility;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import action.AutoSendEmailAction;

import services.IWebEmailService;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations={"classpath:spring.xml", "classpath:spring-dao.xml" ,    "classpath:spring-services.xml"})
public class importExcel {
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
	@Resource(name= "emailService") //�`�Jbean�x
    private IWebEmailService emailService;
	@Test	
	public void test() throws FileNotFoundException, IOException {
		HSSFWorkbook workbook=new HSSFWorkbook(new FileInputStream("d:\\631_temp.xls"));
		Sheet sheet = workbook.getSheetAt(0);

		// 解析公式结果
		FormulaEvaluator evaluator = workbook.getCreationHelper()
				.createFormulaEvaluator();

		List<String> list = new ArrayList<String>();

		//int minRowIx = sheet.getFirstRowNum()+1;
		int row_head = sheet.getFirstRowNum()+1;
		int maxRowIx = sheet.getLastRowNum();//getLastRowNum獲取的行數比實際行數少1

		for (int rowIx = row_head; rowIx <= maxRowIx-1; rowIx++) {//注意：rowIx <= maxRowIx（減去最后一行匯總）
			Row row = sheet.getRow(rowIx);
			StringBuilder sb = new StringBuilder();
			//不允許表頭爲空行null
			if(row==null){
				list.clear();
				break;
			}
			short minColIx = row.getFirstCellNum();
			int maxColIx = row.getLastCellNum()-1;//getLastCellNum獲取到實際列數(減去最後一列匯總20160122)
			if(maxColIx-minColIx>12&&maxRowIx-row_head>2000){
				list.clear();
				break;
			}
			
			if(rowIx==row_head
					&&!sheet.getRow(row_head).getCell(minColIx).toString().equals("廠別")&&
					!sheet.getRow(row_head).getCell(minColIx+1).toString().equals("品牌")&&
					!sheet.getRow(row_head).getCell(minColIx+2).toString().equals("客戶")&&
					!sheet.getRow(row_head).getCell(minColIx+3).toString().equals("模具")&&
					!sheet.getRow(row_head).getCell(minColIx+4).toString().equals("部件")){
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
		for(String str:list){
			System.out.println(str);
		}
		
				
	}
	


}
