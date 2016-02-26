package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ActionSupport;

import entity.KyFact;
import entity.WebMachin;
import entity.WebMachinId;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class readexcel extends ActionSupport {
	public static List<String[]> getSheetMap(File file) throws BiffException,
			IOException {
		Workbook book = Workbook.getWorkbook(file);
		Sheet[] sheetList = book.getSheets();// 获得工作表对象数组
		String[] mes = null;
		Sheet sheets = sheetList[0];
		int cols = sheets.getColumns();// col列数
		int rows = sheets.getRows();// 行数
		int nullcols = 0;
		List<String[]> cellList = new ArrayList<String[]>();// NEW一个单元格集合
		for (int i = 0; i < rows; i++) {
			mes = new String[cols];// 每一行NEW一个新的列数组，cols是列的长度
			nullcols = 0;
			for (int j = 0; j < cols; j++) {
				String cur = sheets.getCell(j, i).getContents();// 从EXCEL取得每个单元格中的数据
				if (null == cur || "" == cur) {
					nullcols++;
				}
				mes[j] = cur;// 将单元格的数据存入列数组中
			}
			if (cols != nullcols)
				cellList.add(mes);// 将每列的数组存入每个页的集合中
		}

		book.close();
		return cellList;
	}

	public List<String[]> fill(String url) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException, ParseException {
		List<String[]> infoList = new ArrayList<String[]>();
		File file = new File(url);
		try {
			infoList = readexcel.getSheetMap(file);
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return infoList;

	}
}
