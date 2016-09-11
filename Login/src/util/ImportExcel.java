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
			for (int rowIx = row_head; rowIx <= maxRowIx; rowIx++) {//start for b
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
			}//end for b
			
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
	
	
	
	
	
	
    /*************************************************************導入新的kpi數據*************************************************************************/
	/**
	 * 導入新的kpi數據
	 * @Title: impWeballobjExl
	 * @Description: TODO
	 * @param @param book
	 * @param @return
	 * @return Map<String,Object>
	 * @throws
	 * @author web
	 * @date 2016/3/24
	 */
	  public static Map<String,Object> impWeballobjExl(Workbook wb){
		  Map<String ,Object>map=new HashMap<String,Object>();
		  int sheets=wb.getNumberOfSheets();//獲取所有的sheet
		  FormulaEvaluator eval=wb.getCreationHelper().createFormulaEvaluator();
		  for(int i=0;i<sheets;i++){//for a			  
			  int headrow=wb.getSheetAt(i).getFirstRowNum()+1;//排除標題
			  int lastrow=wb.getSheetAt(i).getLastRowNum();	
			  
			  int temp=0;
			  Row row=wb.getSheetAt(i).getRow(headrow);
			  while(row==null){
				  temp++;
				  row=wb.getSheetAt(i).getRow(headrow+temp);
			  }
			  int firstcol=row.getFirstCellNum();//排隊序號列
			  int lastcol=row.getLastCellNum();
			  List<String>list=new ArrayList<String>();
			  for(int j=headrow;j<=lastrow;j++){//for b				  
				  if(wb.getSheetAt(i).getRow(j)==null){
					  continue;
				  }
				  StringBuilder sb=new StringBuilder();
				  for(int k=firstcol;k<lastcol;k++){//for c					  
					  Cell cell=wb.getSheetAt(i).getRow(j).getCell(k);
					  if(cell==null){
						  cell=wb.getSheetAt(i).getRow(j).createCell(k);//如果新建的單元格沒有給數據類型，則默認爲空類型:Cell.CELL_TYPE_BLANK	
					  }
					  CellValue cellValue = eval.evaluate(cell);//如果Cell沒有給類型，cellValue同樣爲null,所以爲null空，默認cellValue=new CellValue("0.0");
					  if(cellValue==null){
						  cellValue=new CellValue("0.0");
					  }
					  switch(cellValue.getCellType()){
					  case Cell.CELL_TYPE_STRING:
						  sb.append(SEPARATOR+cellValue.getStringValue());
						  break;
					  case Cell.CELL_TYPE_NUMERIC:
						  sb.append(SEPARATOR+cellValue.getNumberValue());
						  break;
					  case Cell.CELL_TYPE_BLANK:
						  if(k<firstcol){
							  sb.append(SEPARATOR+"null");
						  }else{
							  sb.append(SEPARATOR+0.0);
						  }
					  }					  	  
				  }//for c
				
				  list.add(sb.toString());
			  }//for b
			  map.put(wb.getSheetAt(i).getSheetName(), list);
		  }//for a
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
		public static Map<String,Object> exportListFromStream(InputStream is,
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
		public static Map<String,Object> exportListFromFile(File file)
				throws IOException {
			return exportListFromStream(new FileInputStream(file),
					FilenameUtils.getExtension(file.getName()));
		}
	
	/*************************************************************導入新的kpi數據*************************************************************************/
	
	
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
		
		
		/*String path="e:\\導入格式2.xls";
		Map<String, Object>map=null;
		try{
			map=exportListFromFile(new File(path));
			for(String str:map.keySet()){
				for(String str2:(List<String>)map.get(str)){
					System.out.println(str2);
					for(int i=0;i<str2.split("__").length;i++){
						System.out.println(str2.split("__")[i]);
					}					
					System.out.println("********************************");
				}
			}
			
		
			List<List<Weballobj>>list_a=new ArrayList<List<Weballobj>>();
			for(String key:map.keySet()){//for a
				List<Weballobj>list_b=new ArrayList<Weballobj>();
				List<String>list=(List<String>)map.get(key);
				System.out.println("***********表頭："+list.get(0));
				if(!list.get(0).equals("_序號_項目_單位")){
					System.out.println("工作薄sheet:"+key+"結構不符合要求");
					continue;
				}
				String[] array_head =list.get(0).split("__");
				for(int i=4;i<array_head.length-3;i++){//for b
					WebFact fact=new WebFact(new WebFactId("631",array_head[i]));
					Weballobj obj=new Weballobj(new WeballobjId(fact,"201601"));													
					obj.setObjA100(Double.valueOf(list.get(1).split("__")[i]));
					obj.setObjA101(Double.valueOf(list.get(2).split("__")[i]));
					obj.setObjA102(Double.valueOf(list.get(3).split("__")[i]));
					obj.setObjA103(Double.valueOf(list.get(4).split("__")[i]));
					obj.setObjA104(Double.valueOf(list.get(5).split("__")[i]));
					obj.setObjA105(Double.valueOf(list.get(6).split("__")[i]));
					obj.setObjA106(Double.valueOf(list.get(7).split("__")[i]));
					obj.setObjA107(Double.valueOf(list.get(8).split("__")[i]));
					obj.setObjA108(Double.valueOf(list.get(9).split("__")[i]));
					obj.setObjA109(Double.valueOf(list.get(10).split("__")[i]));
					obj.setObjA110(Double.valueOf(list.get(11).split("__")[i]));
					obj.setObjA111(Double.valueOf(list.get(12).split("__")[i]));
					obj.setObjA112(Double.valueOf(list.get(13).split("__")[i]));
					obj.setObjA113(Double.valueOf(list.get(14).split("__")[i]));
					obj.setObjA114(Double.valueOf(list.get(15).split("__")[i]));
					obj.setObjA115(Double.valueOf(list.get(16).split("__")[i]));
					obj.setObjA116(Double.valueOf(list.get(17).split("__")[i]));
					obj.setObjA117(Double.valueOf(list.get(18).split("__")[i]));
					obj.setObjA118(Double.valueOf(list.get(19).split("__")[i]));
					obj.setObjA119(Double.valueOf(list.get(20).split("__")[i]));
					obj.setObjA120(Double.valueOf(list.get(21).split("__")[i]));
					obj.setObjA121(Double.valueOf(list.get(22).split("__")[i]));
					obj.setObjA122(Double.valueOf(list.get(23).split("__")[i]));
					obj.setObjA123(Double.valueOf(list.get(24).split("__")[i]));
					obj.setObjA124(Double.valueOf(list.get(25).split("__")[i]));
					obj.setObjA125(Double.valueOf(list.get(26).split("__")[i]));
					obj.setObjA126(Double.valueOf(list.get(27).split("__")[i]));
					obj.setObjA127(Double.valueOf(list.get(28).split("__")[i]));
					obj.setObjA128(Double.valueOf(list.get(29).split("__")[i]));
					obj.setObjA129(Double.valueOf(list.get(30).split("__")[i]));
					obj.setObjA130(Double.valueOf(list.get(31).split("__")[i]));
					obj.setObjA131(Double.valueOf(list.get(32).split("__")[i]));
					obj.setObjA132(Double.valueOf(list.get(33).split("__")[i]));
					obj.setObjA133(Double.valueOf(list.get(34).split("__")[i]));
					obj.setObjA134(Double.valueOf(list.get(35).split("__")[i]));
					obj.setObjA135(Double.valueOf(list.get(36).split("__")[i]));
					obj.setObjA136(Double.valueOf(list.get(37).split("__")[i]));
					obj.setObjA137(Double.valueOf(list.get(38).split("__")[i]));
					obj.setObjA138(Double.valueOf(list.get(39).split("__")[i]));
					obj.setObjA139(Double.valueOf(list.get(40).split("__")[i]));
					obj.setObjA140(Double.valueOf(list.get(41).split("__")[i]));
					obj.setObjA141(Double.valueOf(list.get(42).split("__")[i]));
					obj.setObjA142(Double.valueOf(list.get(43).split("__")[i]));
					obj.setObjA143(Double.valueOf(list.get(44).split("__")[i]));
					obj.setObjA144(Double.valueOf(list.get(45).split("__")[i]));
					obj.setObjA145(Double.valueOf(list.get(46).split("__")[i]));
					obj.setObjA146(Double.valueOf(list.get(47).split("__")[i]));
					obj.setObjA147(Double.valueOf(list.get(48).split("__")[i]));
					obj.setObjA148(Double.valueOf(list.get(49).split("__")[i]));
					obj.setObjA149(Double.valueOf(list.get(50).split("__")[i]));
					obj.setObjA150(Double.valueOf(list.get(51).split("__")[i]));
					obj.setObjA151(Double.valueOf(list.get(52).split("__")[i]));
					obj.setObjA152(Double.valueOf(list.get(53).split("__")[i]));
					obj.setObjA153(Double.valueOf(list.get(54).split("__")[i]));
					obj.setObjA154(Double.valueOf(list.get(55).split("__")[i]));
					obj.setObjA155(Double.valueOf(list.get(56).split("__")[i]));
					obj.setObjA156(Double.valueOf(list.get(57).split("__")[i]));
					obj.setObjA157(Double.valueOf(list.get(58).split("__")[i]));
					obj.setObjA158(Double.valueOf(list.get(59).split("__")[i]));
					obj.setObjA159(Double.valueOf(list.get(60).split("__")[i]));
					obj.setObjA160(Double.valueOf(list.get(61).split("__")[i]));
					obj.setObjA161(Double.valueOf(list.get(62).split("__")[i]));
					obj.setObjA162(Double.valueOf(list.get(63).split("__")[i]));
					obj.setObjA163(Double.valueOf(list.get(64).split("__")[i]));
					obj.setObjA164(Double.valueOf(list.get(65).split("__")[i]));
					obj.setObjA165(Double.valueOf(list.get(66).split("__")[i]));
					obj.setObjA166(Double.valueOf(list.get(67).split("__")[i]));
					obj.setObjA167(Double.valueOf(list.get(68).split("__")[i]));
					obj.setObjA168(Double.valueOf(list.get(69).split("__")[i]));
					obj.setObjA169(Double.valueOf(list.get(70).split("__")[i]));
					obj.setObjA170(Double.valueOf(list.get(71).split("__")[i]));
					obj.setObjA171(Double.valueOf(list.get(72).split("__")[i]));
					obj.setObjA172(Double.valueOf(list.get(73).split("__")[i]));
					obj.setObjA173(Double.valueOf(list.get(74).split("__")[i]));
					obj.setObjA174(Double.valueOf(list.get(75).split("__")[i]));
					obj.setObjA175(Double.valueOf(list.get(76).split("__")[i]));
					obj.setObjA176(Double.valueOf(list.get(77).split("__")[i]));
					obj.setObjA177(Double.valueOf(list.get(78).split("__")[i]));
					obj.setObjA178(Double.valueOf(list.get(79).split("__")[i]));
					obj.setObjA179(Double.valueOf(list.get(80).split("__")[i]));
					obj.setObjA180(Double.valueOf(list.get(81).split("__")[i]));
					obj.setObjA181(Double.valueOf(list.get(82).split("__")[i]));
					obj.setObjA182(Double.valueOf(list.get(83).split("__")[i]));
					obj.setObjA183(Double.valueOf(list.get(84).split("__")[i]));
					obj.setObjA184(Double.valueOf(list.get(85).split("__")[i]));
					obj.setObjA185(Double.valueOf(list.get(86).split("__")[i]));
					obj.setObjA186(Double.valueOf(list.get(87).split("__")[i]));
					obj.setObjA187(Double.valueOf(list.get(88).split("__")[i]));
					obj.setObjA188(Double.valueOf(list.get(89).split("__")[i]));
					obj.setObjA189(Double.valueOf(list.get(90).split("__")[i]));
					obj.setObjA190(Double.valueOf(list.get(91).split("__")[i]));
					obj.setObjA191(Double.valueOf(list.get(92).split("__")[i]));
					obj.setObjA192(Double.valueOf(list.get(93).split("__")[i]));
					obj.setObjA193(Double.valueOf(list.get(94).split("__")[i]));
					obj.setObjA194(Double.valueOf(list.get(95).split("__")[i]));
					obj.setObjA195(Double.valueOf(list.get(96).split("__")[i]));
					obj.setObjA196(Double.valueOf(list.get(97).split("__")[i]));
					obj.setObjA197(Double.valueOf(list.get(98).split("__")[i]));
					obj.setObjA198(Double.valueOf(list.get(99).split("__")[i]));
					obj.setObjA199(Double.valueOf(list.get(100).split("__")[i]));
					obj.setObjA200(Double.valueOf(list.get(101).split("__")[i]));
					obj.setObjA201(Double.valueOf(list.get(102).split("__")[i]));
					obj.setObjA202(Double.valueOf(list.get(103).split("__")[i]));
					
					list_b.add(obj);
				}//for b
				list_a.add(list_b);
			}//for a
			System.out.println(list_a.size());
			System.out.println(list_a.get(0).size());
			System.out.println(list_a.get(0).get(0).getObjA200());
			System.out.println(list_a.get(0).get(1).getObjA201());
			
			
		}catch(Exception e){
			System.out.println(e);
		}*/
		
		/*List<Integer>list=new ArrayList<Integer>();
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
			
		}*/
		
		
		
		
		

	}

}
