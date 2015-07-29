package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class textJxl {
	/**
	 * 生成Excel文件
	 * 
	 * @param path
	 *            文件路径
	 * @param sheetName
	 *            工作表名称
	 * @param dataTitles
	 *            数据标题
	 */
	public void createExcelFile(String path, String sheetName,
			String[] dataTitles) {
		WritableWorkbook workbook;
		try {

			OutputStream os = new FileOutputStream(path);
			WorkbookSettings workbookSettings = new WorkbookSettings();
			workbookSettings.setEncoding("utf-8"); // 解决中文乱码，或GBK
			workbook = Workbook.createWorkbook(os);
			WritableSheet sheet = workbook.createSheet(sheetName, 0); // 添加第一个工作表
			initialSheetSetting(sheet);
			WritableFont wf = new WritableFont(WritableFont.TIMES, 14,
					WritableFont.NO_BOLD, false);// 最后一个为是否italic
			wf.setColour(Colour.RED);
			WritableCellFormat wcf = new WritableCellFormat(wf);
			// 对齐方式
			wcf.setAlignment(Alignment.CENTRE);
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			// 边框
			wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
			// 背景色
			wcf.setBackground(Colour.WHITE);
			Label label;
			for (int i = 0; i < dataTitles.length; i++) {
				// Label(列号,行号,内容,风格)
				label = new Label(i, 0, dataTitles[i], getTitleCellFormat());
				sheet.addCell(label);
			}
			// 插入一行
			insertRowData(sheet, 1, new String[] { "200201001", "黃三", "100",
					"60", "100", "260" },
					getDataCellFormat(CellType.STRING_FORMULA));
			// 一个一个插入行
			label = new Label(0, 2, "200201002",
					getDataCellFormat(CellType.STRING_FORMULA));
			sheet.addCell(label);
			label = new Label(1, 2, "李四",
					getDataCellFormat(CellType.STRING_FORMULA));
			sheet.addCell(label);
			insertOneCellData(sheet, 2, 2, 70.5,
					getDataCellFormat(CellType.NUMBER));
			insertOneCellData(sheet, 3, 2, 90.523,
					getDataCellFormat(CellType.NUMBER));
			insertOneCellData(sheet, 4, 2, 60.5,
					getDataCellFormat(CellType.NUMBER));
			insertFormula(sheet, 5, 2, "C3+D3+E3",
					getDataCellFormat(CellType.NUMBER_FORMULA));
			label = new Label(0, 3, "2563352",
					getDataCellFormat(CellType.STRING_FORMULA));
			sheet.addCell(label);
			label = new Label(1, 3, "我的",
					getDataCellFormat(CellType.STRING_FORMULA));
			sheet.addCell(label);
			insertOneCellData(sheet, 2, 3, 55,
					getDataCellFormat(CellType.NUMBER));
			insertOneCellData(sheet, 3, 3, 33,
					getDataCellFormat(CellType.NUMBER));
			insertOneCellData(sheet, 4, 3, 14,
					getDataCellFormat(CellType.NUMBER));
			insertFormula(sheet, 5, 3, "C4+D4+E4",
					getDataCellFormat(CellType.NUMBER_FORMULA));
			insertRowData(sheet, 4, new String[] { "200201001", "黃三s", "10",
					"60", "100" }, getDataCellFormat(CellType.STRING_FORMULA));
			insertFormula(sheet, 5, 4, "C5+D5+E5",
					getDataCellFormat(CellType.NUMBER_FORMULA));
			// 插入日期 列開始,行開始,列結束,行結束
			// mergeCellsAndInsertData(sheet, 0, 5, 5, 5, new Date(),
			// getDataCellFormat(CellType.DATE));
			workbook.write();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化表格属性
	 * 
	 * @param sheet
	 */
	public void initialSheetSetting(WritableSheet sheet) {
		try {
			// sheet.getSettings().setProtected(true); //设置xls的保护，单元格为只读的
			sheet.getSettings().setDefaultColumnWidth(10); // 设置列的默认宽度
			// sheet.setRowView(2,false);//行高自动扩展
			// setRowView(int row,int height);--行高
			// setColumnView(int col,int width); --列宽
			sheet.setColumnView(0, 20);// 设置第一列宽度

			// sheet.setRowView(0, 500);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 插入公式
	 * 
	 * @param sheet
	 * @param col
	 * @param row
	 * @param formula
	 * @param format
	 */
	public void insertFormula(WritableSheet sheet, Integer col, Integer row,
			String formula, WritableCellFormat format) {
		try {
			Formula f = new Formula(col, row, formula, format);
			sheet.addCell(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 插入一行数据
	 * 
	 * @param sheet
	 *            工作表
	 * @param row
	 *            行号
	 * @param content
	 *            内容
	 * @param format
	 *            风格
	 */
	public void insertRowData(WritableSheet sheet, Integer row,
			String[] dataArr, WritableCellFormat format) {
		try {
			Label label;
			for (int i = 0; i < dataArr.length; i++) {
				label = new Label(i, row, dataArr[i], format);
				sheet.addCell(label);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 插入单元格数据
	 * 
	 * @param sheet
	 * @param col
	 * @param row
	 * @param data
	 */
	public void insertOneCellData(WritableSheet sheet, Integer col,
			Integer row, Object data, WritableCellFormat format) {
		try {
			if (data instanceof Double) {
				jxl.write.Number labelNF = new jxl.write.Number(col, row,
						(Double) data, format);
				sheet.addCell(labelNF);
			} else if (data instanceof Boolean) {
				jxl.write.Boolean labelB = new jxl.write.Boolean(col, row,
						(Boolean) data, format);
				sheet.addCell(labelB);
			} else if (data instanceof Date) {
				jxl.write.DateTime labelDT = new jxl.write.DateTime(col, row,
						(Date) data, format);
				sheet.addCell(labelDT);
				setCellComments(labelDT, "这是个创建表的日期说明！");
			} else {
				Label label = new Label(col, row, data.toString(), format);
				sheet.addCell(label);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 合并单元格，并插入数据
	 * 
	 * @param sheet
	 * @param col_start
	 * @param row_start
	 * @param col_end
	 * @param row_end
	 * @param data
	 * @param format
	 */
	public void mergeCellsAndInsertData(WritableSheet sheet, Integer col_start,
			Integer row_start, Integer col_end, Integer row_end, Object data,
			WritableCellFormat format) {
		try {
			sheet.mergeCells(col_start, row_start, col_end, row_end);// 左上角到右下角
			insertOneCellData(sheet, col_start, row_start, data, format);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 给单元格加注释
	 * 
	 * @param label
	 * @param comments
	 */
	public void setCellComments(Object label, String comments) {
		WritableCellFeatures cellFeatures = new WritableCellFeatures();
		cellFeatures.setComment(comments);
		if (label instanceof jxl.write.Number) {
			jxl.write.Number num = (jxl.write.Number) label;
			num.setCellFeatures(cellFeatures);
		} else if (label instanceof jxl.write.Boolean) {
			jxl.write.Boolean bool = (jxl.write.Boolean) label;
			bool.setCellFeatures(cellFeatures);
		} else if (label instanceof jxl.write.DateTime) {
			jxl.write.DateTime dt = (jxl.write.DateTime) label;
			dt.setCellFeatures(cellFeatures);
		} else {
			Label _label = (Label) label;
			_label.setCellFeatures(cellFeatures);
		}
	}

	/**
	 * 得到数据表头格式
	 * 
	 * @return
	 */
	public WritableCellFormat getTitleCellFormat() {
		WritableCellFormat wcf = null;
		try {
			// 字体样式
			WritableFont wf = new WritableFont(WritableFont.TIMES, 14,
					WritableFont.NO_BOLD, false);// 最后一个为是否italic
			wf.setColour(Colour.RED);
			wcf = new WritableCellFormat(wf);
			// 对齐方式
			wcf.setAlignment(Alignment.CENTRE);
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			// 边框
			wcf.setBorder(Border.ALL, BorderLineStyle.THIN);

			// 背景色
			wcf.setBackground(Colour.GREY_25_PERCENT);
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return wcf;
	}

	/**
	 * 得到数据格式
	 * 
	 * @return
	 */
	public WritableCellFormat getDataCellFormat(CellType type) {
		WritableCellFormat wcf = null;
		try {
			// 字体样式
			if (type == CellType.NUMBER || type == CellType.NUMBER_FORMULA) {// 数字
				NumberFormat nf = new NumberFormat("#.00");
				wcf = new WritableCellFormat(nf);
			} else if (type == CellType.DATE || type == CellType.DATE_FORMULA) {// 日期
				jxl.write.DateFormat df = new jxl.write.DateFormat(
						"yyyy-MM-dd hh:mm:ss");
				wcf = new jxl.write.WritableCellFormat(df);
			} else {
				WritableFont wf = new WritableFont(WritableFont.TIMES, 10,
						WritableFont.NO_BOLD, false);// 最后一个为是否italic
				wcf = new WritableCellFormat(wf);
			}
			// 对齐方式
			wcf.setAlignment(Alignment.CENTRE);
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			// 边框
			wcf.setBorder(Border.LEFT, BorderLineStyle.THIN);
			wcf.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
			wcf.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			// 背景色
			wcf.setBackground(Colour.WHITE);
			wcf.setWrap(true);// 自动换行
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return wcf;
	}

	public static void main(String[] args) {
		String[] titles = { "学号", "姓名", "语文", "数学", "英语", "总分" };
		textJxl jxl = new textJxl();
		String filePath = "E:/test.xls";
		jxl.createExcelFile(filePath, "成绩单", titles);

	}
}