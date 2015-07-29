package action;

import java.io.IOException;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
//import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.struts2.interceptor.ServletResponseAware;

import services.IWebFactServices;
import services.IWebYieldDataServices;
import services.IWebYielePredictionServices;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.*;

public class PreAndDataAction extends ActionSupport implements
		ServletResponseAware {
	private IWebYielePredictionServices preSer;
	private IWebYieldDataServices dataSer;
	private List<String> factnolist;
	private List<String> factcodelist;
	private String year;
	private String month;
	private String lmonth;
	private IWebFactServices webFactSer;
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}

	public String getLmonth() {
		return lmonth;
	}

	public void setLmonth(String lmonth) {
		this.lmonth = lmonth;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public List<String> getFactnolist() {
		return factnolist;
	}

	public void setFactnolist(List<String> factnolist) {
		this.factnolist = factnolist;
	}

	public List<String> getFactcodelist() {
		return factcodelist;
	}

	public void setFactcodelist(List<String> factcodelist) {
		this.factcodelist = factcodelist;
	}

	private javax.servlet.http.HttpServletResponse response;

	public void setDataSer(IWebYieldDataServices dataSer) {
		this.dataSer = dataSer;
	}

	public void setPreSer(IWebYielePredictionServices preSer) {
		this.preSer = preSer;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	public String print2Y() {
		response.reset();

		try {
			ServletOutputStream os = response.getOutputStream();
			response.setContentType("html/csv");
			// response.setContentType("application/vnd.ms-excel");html/csv
			// response.setContentType("text/HTML");
			StringBuffer tempName = new StringBuffer();
			tempName.append(year);
			tempName.append(month);
			if (lmonth != null && !lmonth.equals("") && !lmonth.equals("01")) {
				tempName.append("-" + lmonth);
			}
			tempName.append(".xls");
			String fileName = new String(tempName.toString().getBytes("utf-8"),
					"ISO8859-1");
			// response.setHeader("Content-disposition",
			// "attachment; filename="+fileName);
			// response.setHeader("Content-disposition",
			// "inline; filename="+fileName);
			response.setHeader("Content-disposition", result + fileName);

			// 樣式設置
			WritableWorkbook wb = Workbook.createWorkbook(os);
			WritableSheet sheet = wb.createSheet("總表", 0);// 表0
			SheetSettings settings = sheet.getSettings(); // sheet格式
			settings.setDefaultColumnWidth(14);// 默认字体是10,所以刚好适应12个字符

			WritableSheet sheet1 = null;
			WritableSheet sheet2 = null;
			if (factnolist.size() * factcodelist.size() * 5 > 256) {
				sheet1 = wb.createSheet("總表1", 1);// 表1
				SheetSettings settings1 = sheet1.getSettings();
				settings1.setDefaultColumnWidth(14);
			}
			if (factnolist.size() * factcodelist.size() * 5 > 512) {
				sheet2 = wb.createSheet("總表2", 2);// 表1
				SheetSettings settings2 = sheet2.getSettings();
				settings2.setDefaultColumnWidth(14);
			}

			// 设置边框
			jxl.write.WritableCellFormat wcf = new jxl.write.WritableCellFormat();
			wcf.setAlignment(Alignment.CENTRE);// 水平居中
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直居中
			wcf.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THICK);// 黑色邊框
			wcf.setWrap(true);// 自動換行
			// wcf.setBackground(Color.yellow);//設置背景

			jxl.write.WritableCellFormat wcfNum = new jxl.write.WritableCellFormat();
			wcfNum.setAlignment(Alignment.CENTRE);
			wcfNum.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcfNum.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			wcfNum.setWrap(true);

			// 日期格式
			DateFormat dateFormat = new DateFormat("yyyy-MM-dd");
			jxl.write.WritableCellFormat wcfDate = new jxl.write.WritableCellFormat(
					dateFormat);
			wcfDate.setAlignment(Alignment.CENTRE);
			wcfDate.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcfDate.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			wcfDate.setWrap(true);

			// 數字格式
			// WritableCellFormat wcfNum2 = new WritableCellFormat
			// (NumberFormats.THOUSANDS_FLOAT);//兩位小數
			// WritableCellFormat wcfNum2_percent = new WritableCellFormat
			// (NumberFormats.PERCENT_FLOAT);//兩位小數
			NumberFormat nf = new jxl.write.NumberFormat("#,##0.0");
			NumberFormat nf_percent = new jxl.write.NumberFormat("0.0%");
			jxl.write.WritableCellFormat wcfNum2 = new jxl.write.WritableCellFormat(
					nf);
			jxl.write.WritableCellFormat wcfNum2_percent = new jxl.write.WritableCellFormat(
					nf_percent);

			wcfNum2.setAlignment(Alignment.CENTRE);
			wcfNum2.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcfNum2.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			wcfNum2.setWrap(true);
			wcfNum2_percent.setAlignment(Alignment.CENTRE);
			wcfNum2_percent.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcfNum2_percent.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			wcfNum2_percent.setWrap(true);

			// 廠別字體加粗
			// 定义格式 字体 下划线 斜体 粗体 颜色
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			WritableCellFormat wcfCode = new WritableCellFormat(wf); // 单元格定义
			wcfCode.setAlignment(Alignment.CENTRE);
			wcfCode.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcfCode.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			WritableFont wf2 = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.RED);
			WritableCellFormat wcfCode2 = new WritableCellFormat(wf2);
			wcfCode2.setAlignment(Alignment.CENTRE);
			wcfCode2.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcfCode2.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			// 搜索創建好的xls文件
			// Workbook book=Workbook.getWorkbook(new File("D:/firstXls.xls"));
			// 創建工作薄
			// WritableWorkbook wb=Workbook.createWorkbook(new
			// File("D:/firstXls.xls"),book);
			// WritableWorkbook wb=Workbook.createWorkbook(new
			// File("D:/firstXls.xls"));

			/*
			 * WritableSheet sheet=wb.getSheet(0); if(sheet==null){
			 * sheet=wb.createSheet("總表", 0); }
			 */

			int firstMonth = Integer.parseInt(month);
			int lastMonth = 0;
			if (lmonth == null || lmonth.equals("")) {
				lastMonth = firstMonth;
			} else {
				lastMonth = Integer.parseInt(lmonth);
			}

			int height = 0;// **各個表所佔的高度(取最大值)**
			/*
			 * List<String>factlist=new ArrayList(); factlist.add("RB");
			 * factlist.add("MD"); factlist.add("PU"); factlist.add("IP");
			 */

			for (int z = firstMonth; z < lastMonth + 1; z++) { // totalALL
				height = (z - firstMonth) * 43;

				// 查找時間yymm
				StringBuffer yymm = new StringBuffer();
				yymm.append(year);
				yymm.append("0" + z); // ******* yymm.append(month) *****///
				// SimpleDateFormat format=new SimpleDateFormat("yyyyMM");
				// Date date=format.parse(yymm.toString());

				List<List<WebYielePrediction>> alllist = new ArrayList();
				List<List<WebYieldData>> alllist2 = new ArrayList();
				List<String> factnoCodelist = new ArrayList();
				for (int factno = 0; factno < factnolist.size(); factno++) {
					String factSname = webFactSer.selByid(factnolist
							.get(factno));
					List factAreas = webFactSer.findFactCodeByFactNo(factnolist
							.get(factno));
					for (int factcode = 0; factcode < factAreas.size(); factcode++) {
						List<WebYielePrediction> list = preSer.findByFactcode(
								factnolist.get(factno),
								(String) factAreas.get(factcode),
								yymm.toString());
						List<WebYieldData> list2 = dataSer.findDataByFactcode(
								factnolist.get(factno),
								(String) factAreas.get(factcode),
								yymm.toString());
						List<WebYielePrediction> listnull = preSer
								.findNullYpre(factnolist.get(factno),
										(String) factAreas.get(factcode),
										yymm.toString());
						List<WebYieldData> listnull2 = dataSer.findNullYdata(
								factnolist.get(factno),
								(String) factAreas.get(factcode),
								yymm.toString());
						StringBuffer factnoCode = new StringBuffer();

						/*
						 * if(list.size()>0&&list2.size()>0){
						 * factnoCode.append(factSname);
						 * factnoCode.append("("+factnolist.get(factno)+")-(");
						 * factnoCode
						 * .append((String)factAreas.get(factcode)+")");
						 * factnoCode.append("("+z+"月份)");
						 * factnoCodelist.add(factnoCode.toString());
						 * alllist.add(list); alllist2.add(list2); }
						 * if(list.size()>0&&list2.size()==0){
						 * factnoCode.append(factSname);
						 * factnoCode.append("("+factnolist.get(factno)+")-(");
						 * factnoCode
						 * .append((String)factAreas.get(factcode)+")");
						 * factnoCode.append("("+z+"月份)");
						 * factnoCodelist.add(factnoCode.toString());
						 * alllist.add(list); WebYieldData data=new
						 * WebYieldData(); list2.add(data); alllist2.add(list2);
						 * } if(list2.size()>0&&list.size()==0){
						 * factnoCode.append(factSname);
						 * factnoCode.append("("+factnolist.get(factno)+")-(");
						 * factnoCode
						 * .append((String)factAreas.get(factcode)+")");
						 * factnoCode.append("("+z+"月份)");
						 * factnoCodelist.add(factnoCode.toString());
						 * alllist2.add(list2); WebYielePrediction pre=new
						 * WebYielePrediction(); list.add(pre);
						 * alllist.add(list); }
						 */

						if (list.size() > 0 && listnull.size() == 0
								|| list2.size() > 0 && listnull2.size() == 0) {
							factnoCode.append(factSname);
							factnoCode.append("(" + factnolist.get(factno)
									+ ")-(");
							factnoCode.append((String) factAreas.get(factcode)
									+ ")");
							factnoCode.append("(" + z + "月份)");
							factnoCodelist.add(factnoCode.toString());
							alllist.add(list);
							if (list2.size() == 0) {
								WebYieldData data = new WebYieldData();
								list2.add(data);
							}
							if (list.size() == 0) {
								WebYielePrediction pre = new WebYielePrediction();
								list.add(pre);
							}
							alllist2.add(list2);
						}

						if (list.size() == 0 && list2.size() == 0) {

							factnoCode.append(factSname);
							factnoCode.append("(" + factnolist.get(factno)
									+ ")-(");
							factnoCode.append((String) factAreas.get(factcode)
									+ ")");
							factnoCode.append("(" + z + "月份)");
							factnoCode.append("還沒有添加數據!!");
							factnoCodelist.add(factnoCode.toString());
							WebYielePrediction pre = new WebYielePrediction();
							WebYieldData data = new WebYieldData();
							list.add(pre);
							list2.add(data);
							alllist.add(list);
							alllist2.add(list2);

						}
						if (listnull.size() > 0 && listnull2.size() > 0) {
							factnoCode.append(factSname);
							factnoCode.append("(" + factnolist.get(factno)
									+ ")_(");
							factnoCode.append((String) factAreas.get(factcode)
									+ "");
							factnoCode.append("(" + z + "月份)");
							factnoCode.append("還沒有添加數據!!");
							factnoCodelist.add(factnoCode.toString());
							alllist.add(list);
							alllist2.add(list2);
						}
						if (list.size() > 0 && listnull.size() > 0
								&& list2.size() == 0) {
							factnoCode.append(factSname);
							factnoCode.append("(" + factnolist.get(factno)
									+ ")_(");
							factnoCode.append((String) factAreas.get(factcode)
									+ "");
							factnoCode.append("(" + z + "月份)");
							factnoCode.append("還沒有添加數據!!");
							factnoCodelist.add(factnoCode.toString());
							WebYieldData data = new WebYieldData();
							list2.add(data);
							alllist.add(list);
							alllist2.add(list2);
						}

					}
				}

				// /***判斷列數是否大於256***/
				// /***小於256就不用判斷***/
				// if(alllist.size()*5<256||alllist2.size()*5<256){ //if ALL1

				int allLength = 256 / 5;

				for (int all = 0; all < 2; all++) {// All
					if (all == 0) { // if 1
						List<String> cols = new ArrayList();
						cols.add("日期/產量/廠別");
						cols.add("戰力分析模數");
						cols.add("預計生產模數");
						cols.add("預計請款雙數");
						cols.add("機台孔位數");
						cols.add("總機孔");
						cols.add("正批");
						cols.add("工程/樣品");
						cols.add("輔料");
						cols.add("其他");

						int weight = 0;
						int length = 0;
						if (alllist.size() * 5 > 256) {
							length = 256 / 5;
						} else {
							length = alllist.size();
						}
						for (int i = 0; i < length; i++) {// for 1
															// /***(i<alllist.size())****/
							if (i > 0) {
								weight = weight - 1;
							}
							for (int j = 0; j < cols.size(); j++) {// for 2
								if (j < 5 && i == 0) {
									sheet.addCell(new Label(i + weight, j
											+ height, cols.get(j), wcf));
									// sheet.mergeCells(i+weight, j, i+1+weight,
									// j);
									if (j == 4) {
										sheet.mergeCells(i + weight,
												j + height, i + weight, j
														+ height + 3);
									}
								}

								if (j == 5) {
									break;
								}

								for (int k = 0; k < alllist.get(i).size(); k++) {// for
																					// 3
									switch (j) {
									case 0:
										// String
										// factcode=alllist.get(i).get(k).getFactCode();
										String noAndCode = factnoCodelist
												.get(i);
										if (noAndCode.contains("沒有添加數據")) {
											sheet.addCell(new Label(i + k + 1
													+ weight, j + height,
													noAndCode, wcfCode2));
											sheet.mergeCells(
													i + k + 1 + weight, j
															+ height, i + k + 5
															+ weight, j
															+ height);
										} else {
											sheet.addCell(new Label(i + k + 1
													+ weight, j + height,
													noAndCode, wcfCode));
											sheet.mergeCells(
													i + k + 1 + weight, j
															+ height, i + k + 5
															+ weight, j
															+ height);
										}

										/*
										 * if(factcode!=null){ sheet.addCell(new
										 * Label
										 * (i+k+1+weight,j,factcode,wcfNum));
										 * sheet.mergeCells(i+k+1+weight, j,
										 * i+k+5+weight, j); }else{
										 * sheet.addCell(new
										 * Label(i+k+1+weight,j,null,wcfNum));
										 * sheet.mergeCells(i+k+1+weight, j,
										 * i+k+5+weight, j); }
										 */
										break;
									case 1:
										Double force = alllist.get(i).get(k)
												.getForceAnalysis();
										if (force != null) {
											sheet.addCell(new jxl.write.Number(
													i + k + 1 + weight, j
															+ height, force,
													wcfNum2));
											sheet.mergeCells(
													i + k + 1 + weight, j
															+ height, i + k + 5
															+ weight, j
															+ height);
										} else {
											sheet.addCell(new Label(i + k + 1
													+ weight, j + height, null,
													wcfNum));
											sheet.mergeCells(
													i + k + 1 + weight, j
															+ height, i + k + 5
															+ weight, j
															+ height);
										}
										break;
									case 2:
										Double expect = alllist.get(i).get(k)
												.getExpectedProduction();
										if (expect != null) {
											sheet.addCell(new jxl.write.Number(
													i + k + 1 + weight, j
															+ height, expect,
													wcfNum2));
											sheet.mergeCells(
													i + k + 1 + weight, j
															+ height, i + k + 5
															+ weight, j
															+ height);
										} else {
											sheet.addCell(new Label(i + k + 1
													+ weight, j + height, null,
													wcfNum));
											sheet.mergeCells(
													i + k + 1 + weight, j
															+ height, i + k + 5
															+ weight, j
															+ height);
										}
										break;
									case 3:
										Double expPay = alllist.get(i).get(k)
												.getExpectedPayment();
										if (expPay != null) {
											sheet.addCell(new jxl.write.Number(
													i + k + 1 + weight, j
															+ height, expPay,
													wcfNum2));
											sheet.mergeCells(
													i + k + 1 + weight, j
															+ height, i + k + 5
															+ weight, j
															+ height);
										} else {
											sheet.addCell(new Label(i + k + 1
													+ weight, j + height, null,
													wcfNum));
											sheet.mergeCells(
													i + k + 1 + weight, j
															+ height, i + k + 5
															+ weight, j
															+ height);
										}
										break;
									case 4:
										sheet.addCell(new Label(i + k + 1
												+ weight, j + height, cols
												.get(j + 1), wcfNum));
										sheet.mergeCells(i + k + 1 + weight, j
												+ height, i + k + 1 + weight, j
												+ height + 3);
										Double hole = alllist.get(i).get(k)
												.getHole();
										if (hole != null) {
											sheet.addCell(new jxl.write.Number(
													i + k + 2 + weight, j
															+ height, hole,
													wcfNum2));
											sheet.mergeCells(
													i + k + 2 + weight, j
															+ height, i + k + 2
															+ weight, j
															+ height + 3);
										} else {
											sheet.addCell(new Label(i + k + 2
													+ weight, j + height, null,
													wcfNum));
											sheet.mergeCells(
													i + k + 2 + weight, j
															+ height, i + k + 2
															+ weight, j
															+ height + 3);
										}
										sheet.addCell(new Label(i + k + 3
												+ weight, j + height, cols
												.get(j + 2), wcfNum));
										Double posNum = alllist.get(i).get(k)
												.getPositiveNumber();
										if (posNum != null) {
											sheet.addCell(new jxl.write.Number(
													i + k + 4 + weight, j
															+ height, posNum,
													wcfNum2));
											sheet.mergeCells(
													i + k + 4 + weight, j
															+ height, i + k + 5
															+ weight, j
															+ height);
										} else {
											sheet.addCell(new Label(i + k + 4
													+ weight, j + height, null,
													wcfNum));
											sheet.mergeCells(
													i + k + 4 + weight, j
															+ height, i + k + 5
															+ weight, j
															+ height);
										}
										sheet.addCell(new Label(i + k + 3
												+ weight, j + height + 1, cols
												.get(j + 3), wcfNum));
										Double sample = alllist.get(i).get(k)
												.getSample();
										if (sample != null) {
											sheet.addCell(new jxl.write.Number(
													i + k + 4 + weight, j
															+ height + 1,
													sample, wcfNum2));
											sheet.mergeCells(
													i + k + 4 + weight, j
															+ height + 1, i + k
															+ 5 + weight, j
															+ height + 1);
										} else {
											sheet.addCell(new Label(i + k + 4
													+ weight, j + height + 1,
													null, wcfNum));
											sheet.mergeCells(
													i + k + 4 + weight, j
															+ height + 1, i + k
															+ 5 + weight, j
															+ height + 1);
										}
										sheet.addCell(new Label(i + k + 3
												+ weight, j + height + 2, cols
												.get(j + 4), wcfNum));
										Double acce = alllist.get(i).get(k)
												.getAccessories();
										if (acce != null) {
											sheet.addCell(new jxl.write.Number(
													i + k + 4 + weight, j
															+ height + 2, acce,
													wcfNum2));
											sheet.mergeCells(
													i + k + 4 + weight, j
															+ height + 2, i + k
															+ 5 + weight, j
															+ height + 2);
										} else {
											sheet.addCell(new Label(i + k + 4
													+ weight, j + height + 2,
													null, wcfNum));
											sheet.mergeCells(
													i + k + 4 + weight, j
															+ height + 2, i + k
															+ 5 + weight, j
															+ height + 2);
										}
										sheet.addCell(new Label(i + k + 3
												+ weight, j + height + 3, cols
												.get(j + 5), wcfNum));
										Double other = alllist.get(i).get(k)
												.getOther();
										if (other != null) {
											sheet.addCell(new jxl.write.Number(
													i + k + 4 + weight, j
															+ height + 3,
													other, wcfNum2));
											sheet.mergeCells(
													i + k + 4 + weight, j
															+ height + 3, i + k
															+ 5 + weight, j
															+ height + 3);

										} else {
											sheet.addCell(new Label(i + k + 4
													+ weight, j + height + 3,
													null, wcfNum));
											sheet.mergeCells(
													i + k + 4 + weight, j
															+ height + 3, i + k
															+ 5 + weight, j
															+ height + 3);
										}
										break;
									}

								}// end for 3

							}// end for 2
							weight = weight + 5;
						}// end for 1

					}// end "if 1"

					if (all == 1) {// if 2
						List<String> cols = new ArrayList();
						cols.add("日期");
						cols.add("上模數");
						cols.add("人數");
						cols.add("標準產量");
						cols.add("實際產量");
						cols.add("達成率(%)");

						height = height + 8;
						int width = 0;
						int length = 0;
						if (alllist2.size() * 5 > 256) {
							length = 256 / 5;
						} else {
							length = alllist2.size();
						}
						// double sumStandardOutput=0;//標準產量合計
						// ActionContext.getContext().getSession().put("",
						// sumStandardOutput);
						for (int i = 0; i < length; i++) {// for 1
															// //*****i<alllist2.size()******/
							/*
							 * if(i>1){ width=width-1; }
							 */
							double sumStandardOutput = 0;// 標準產量合計
							double sumActualYield = 0;// 實際產量合計

							for (int j = 0; j < cols.size(); j++) {// for 2

								if (i > 0 && cols.get(j).equals("日期")) {
									continue;
								}
								sheet.addCell(new Label(j + width, height, cols
										.get(j), wcf));

								// **/添加合計條件**/
								double sumOnModulus = 0;// 上模數合計
								double sumPersonnum = 0;// 人數合計

								double sumAchievingRate = 0;// 月達成率
								String st_sumAchievingRate = "0";
								if (sumStandardOutput != 0
										&& sumActualYield != 0) {
									sumAchievingRate = sumActualYield
											/ sumStandardOutput;
									DecimalFormat format = new DecimalFormat(
											"#.0%");
									st_sumAchievingRate = format
											.format(sumAchievingRate);
								}

								for (int k = 0; k < 32; k++) {// for 3
									if (sheet
											.getCell(j + width, 1 + height + k)
											.getContents() == "") { // 先添加空單元格,避免有空位置
										sheet.addCell(new Label(j + width, 1
												+ height + k, null, wcfNum));
									}

									int day = 0;
									switch (j) {
									case 0:
										StringBuffer date = new StringBuffer();
										date.append(year + "/");
										date.append(z + "/"); // ********date.append(month+"/")*********//
										date.append(k + 1);
										// sheet.addCell(new Label(j+width,
										// 1+height+k,date.toString(),wcfNum));
										if (k == 31) {
											sheet.addCell(new Label(j + width,
													1 + height + k, "合計",
													wcfCode)); // 添加合計20140424
										} else {
											sheet.addCell(new Label(j + width,
													1 + height + k, date
															.toString(), wcfNum));
										}
										/*
										 * Date date=new Date(); try {
										 * date=alllist2
										 * .get(i).get(k).getId().getYymmdd();
										 * sheet.addCell(new DateTime(j+width,
										 * 1+height+k, date,wcfDate)); } catch
										 * (Exception e) { // TODO: handle
										 * exception sheet.addCell(new
										 * Label(j+width,
										 * 1+height+k,null,wcfNum)); }
										 */
										break;

									case 1:

										try {
											day = alllist2.get(i).get(k)
													.getId().getYymmdd()
													.getDate();
											Double onModulus = alllist2.get(i)
													.get(k).getOnModulus();
											if (onModulus != null
													&& k == day - 1) {
												sheet.addCell(new jxl.write.Number(
														j + width, 1 + height
																+ k, onModulus,
														wcfNum2));
												sumOnModulus = sumOnModulus
														+ onModulus;
											}
											if (onModulus != null
													&& k != day - 1) {
												sheet.addCell(new jxl.write.Number(
														j + width, 1 + height
																+ (day - 1),
														onModulus, wcfNum2));
												// sheet.addCell(new
												// Label(j+width,
												// 1+height+k,null,wcfNum));
												sumOnModulus = sumOnModulus
														+ onModulus;
											}
											if (onModulus == null) {
												sheet.addCell(new Label(j
														+ width,
														1 + height + k, null,
														wcfNum));
											}
										} catch (Exception e) {
											// TODO: handle exception
											if (sheet.getCell(j + width,
													1 + height + k)
													.getContents() == "") {
												sheet.addCell(new Label(j
														+ width,
														1 + height + k, null,
														wcfNum));
											}
										}
										if (k == 31) {
											sheet.addCell(new jxl.write.Number(
													j + width, 1 + height + k,
													sumOnModulus, wcfNum2));
										}

										break;
									case 2:

										try {
											day = alllist2.get(i).get(k)
													.getId().getYymmdd()
													.getDate();
											Double personnum = alllist2.get(i)
													.get(k).getPersonnum();
											if (personnum != null
													&& k == day - 1) {
												sheet.addCell(new jxl.write.Number(
														j + width, 1 + height
																+ k, personnum,
														wcfNum2));
												sumPersonnum = sumPersonnum
														+ personnum;

											}
											if (personnum != null
													&& k != day - 1) {
												sheet.addCell(new jxl.write.Number(
														j + width, 1 + height
																+ (day - 1),
														personnum, wcfNum2));
												// sheet.addCell(new
												// Label(j+width,
												// 1+height+k,null,wcfNum));
												sumPersonnum = sumPersonnum
														+ personnum;
											}
											if (personnum == null) {
												sheet.addCell(new Label(j
														+ width,
														1 + height + k, null,
														wcfNum));
											}

										} catch (Exception e) {
											// TODO: handle exception
											if (sheet.getCell(j + width,
													1 + height + k)
													.getContents() == "") {
												sheet.addCell(new Label(j
														+ width,
														1 + height + k, null,
														wcfNum));
											}
										}
										if (k == 31) {
											sheet.addCell(new jxl.write.Number(
													j + width, 1 + height + k,
													sumPersonnum, wcfNum2));
										}
										break;
									case 3:
										try {
											day = alllist2.get(i).get(k)
													.getId().getYymmdd()
													.getDate();
											Double standardOutput = alllist2
													.get(i).get(k)
													.getStandardOutput();
											if (standardOutput != null
													&& k == day - 1) {
												sheet.addCell(new jxl.write.Number(
														j + width, 1 + height
																+ k,
														standardOutput, wcfNum2));
												sumStandardOutput = sumStandardOutput
														+ standardOutput;
											}
											if (standardOutput != null
													&& k != day - 1) {
												sheet.addCell(new jxl.write.Number(
														j + width, 1 + height
																+ (day - 1),
														standardOutput, wcfNum2));
												// sheet.addCell(new
												// Label(j+width,
												// 1+height+k,null,wcfNum));
												sumStandardOutput = sumStandardOutput
														+ standardOutput;
											}
											if (standardOutput == null) {
												sheet.addCell(new Label(j
														+ width,
														1 + height + k, null,
														wcfNum));
											}

										} catch (Exception e) {
											// TODO: handle exception
											if (sheet.getCell(j + width,
													1 + height + k)
													.getContents() == "") {
												sheet.addCell(new Label(j
														+ width,
														1 + height + k, null,
														wcfNum));
											}
										}
										if (k == 31) {
											sheet.addCell(new jxl.write.Number(
													j + width, 1 + height + k,
													sumStandardOutput, wcfNum2));
										}
										break;
									case 4:
										try {
											day = alllist2.get(i).get(k)
													.getId().getYymmdd()
													.getDate();
											Double actualYield = alllist2
													.get(i).get(k)
													.getActualYield();
											if (actualYield != null
													&& k == day - 1) {
												sheet.addCell(new jxl.write.Number(
														j + width, 1 + height
																+ k,
														actualYield, wcfNum2));
												sumActualYield = sumActualYield
														+ actualYield;
											}
											if (actualYield != null
													&& k != day - 1) {
												sheet.addCell(new jxl.write.Number(
														j + width, 1 + height
																+ (day - 1),
														actualYield, wcfNum2));
												// sheet.addCell(new
												// Label(j+width,
												// 1+height+k,null,wcfNum));
												sumActualYield = sumActualYield
														+ actualYield;

											}
											if (actualYield == null) {
												sheet.addCell(new Label(j
														+ width,
														1 + height + k, null,
														wcfNum));
											}
										} catch (Exception e) {
											// TODO: handle exception
											if (sheet.getCell(j + width,
													1 + height + k)
													.getContents() == "") {
												sheet.addCell(new Label(j
														+ width,
														1 + height + k, null,
														wcfNum));
											}
										}
										if (k == 31) {
											sheet.addCell(new jxl.write.Number(
													j + width, 1 + height + k,
													sumActualYield, wcfNum2));
										}
										break;
									case 5:
										try {
											day = alllist2.get(i).get(k)
													.getId().getYymmdd()
													.getDate();
											Double achievingRate = alllist2
													.get(i).get(k)
													.getAchievingRate();
											// String
											// st_achievingRate=achievingRate.toString()+"%";
											if (achievingRate != null
													&& k == day - 1) {
												sheet.addCell(new jxl.write.Number(
														j + width, 1 + height
																+ k,
														achievingRate,
														wcfNum2_percent));
												// sheet.addCell(new
												// Label(j+width,
												// 1+height+k,st_achievingRate,wcfNum));
											}
											if (achievingRate != null
													&& k != day - 1) {
												sheet.addCell(new jxl.write.Number(
														j + width, 1 + height
																+ (day - 1),
														achievingRate,
														wcfNum2_percent));
												// sheet.addCell(new
												// Label(j+width,
												// 1+height+(day-1),st_achievingRate,wcfNum));
												// sheet.addCell(new
												// Label(j+width,
												// 1+height+k,null,wcfNum));
											}
											if (achievingRate == null) {
												sheet.addCell(new Label(j
														+ width,
														1 + height + k, null,
														wcfNum));
											}

										} catch (Exception e) {
											// TODO: handle exception
											if (sheet.getCell(j + width,
													1 + height + k)
													.getContents() == "") {
												sheet.addCell(new Label(j
														+ width,
														1 + height + k, null,
														wcfNum));
											}
										}
										if (k == 31) {
											sheet.addCell(new Label(j + width,
													1 + height + k,
													st_sumAchievingRate, wcfNum));
										}
										break;
									}
								}// end for 3

							}// end for 2

							width = width + 5;
						}// end for 1
					}// end "if 2"

				}// end All

				// }//end "if ALL1"

				// /***判斷列數是否大於256***/
				// /***大於256***/
				if (alllist.size() * 5 > 256 || alllist2.size() * 5 > 256) { // if
																				// ALL2
					height = (z - firstMonth) * 43;
					/*
					 * WritableSheet sheet1=wb.createSheet("總表1", 1);//表1
					 * SheetSettings settings1=sheet1.getSettings();
					 * settings1.setDefaultColumnWidth(14);
					 */

					for (int all = 0; all < 2; all++) {// All
						if (all == 0) { // if 1
							List<String> cols = new ArrayList();
							cols.add("日期/產量/廠別");
							cols.add("戰力分析模數");
							cols.add("預計生產模數");
							cols.add("預計請款雙數");
							cols.add("機台孔位數");
							cols.add("總機孔");
							cols.add("正批");
							cols.add("工程/樣品");
							cols.add("輔料");
							cols.add("其他");

							int weight = 0;

							// int length=0;
							/*
							 * if(alllist.size()%2==0){ length=alllist.size()/2;
							 * }else{ length=alllist.size()/2+1; }
							 */
							// length=256/5;

							// /***并列循環***/
							// 并列循環A

							// 關掉循環A

							// /***并列循環***/
							// 并列循環B
							int minus = -(allLength + weight);// 清0,歸至原點
																// //******* int
																// minus=-(length+weight)*******/
							int length = 0;
							if (alllist.size() * 5 > 512) {
								length = 512 / 5;
							} else {
								length = alllist.size();
							}
							for (int i = allLength; i < length; i++) {// for 1
																		// //*******
																		// i<alllist.size()*******/
								if (i > allLength) { // ** i>length***/
									weight = weight - 1;
								}
								for (int j = 0; j < cols.size(); j++) {// for 2
									if (j < 5 && i == allLength) { // **
																	// j<5&&i==length***/
										sheet1.addCell(new Label(i + weight
												+ minus, j + height, cols
												.get(j), wcf));
										// sheet1.mergeCells(i+weight, j,
										// i+1+weight, j);
										if (j == 4) {
											sheet1.mergeCells(i + weight
													+ minus, j + height, i
													+ weight + minus, j
													+ height + 3);
										}
									}

									if (j == 5) {
										break;
									}

									for (int k = 0; k < alllist.get(i).size(); k++) {// for
																						// 3
										switch (j) {
										case 0:
											// String
											// factcode=alllist.get(i).get(k).getFactCode();
											String noAndCode = factnoCodelist
													.get(i);
											if (noAndCode.contains("沒有添加數據")) {
												sheet1.addCell(new Label(i + k
														+ 1 + weight + minus, j
														+ height, noAndCode,
														wcfCode2));
												sheet1.mergeCells(i + k + 1
														+ weight + minus, j
														+ height, i + k + 5
														+ weight + minus, j
														+ height);
											} else {
												sheet1.addCell(new Label(i + k
														+ 1 + weight + minus, j
														+ height, noAndCode,
														wcfCode));
												sheet1.mergeCells(i + k + 1
														+ weight + minus, j
														+ height, i + k + 5
														+ weight + minus, j
														+ height);
											}

											/*
											 * if(factcode!=null){
											 * sheet1.addCell(new
											 * Label(i+k+1+weight
											 * ,j,factcode,wcfNum));
											 * sheet1.mergeCells(i+k+1+weight,
											 * j, i+k+5+weight, j); }else{
											 * sheet1.addCell(new
											 * Label(i+k+1+weight
											 * ,j,null,wcfNum));
											 * sheet1.mergeCells(i+k+1+weight,
											 * j, i+k+5+weight, j); }
											 */
											break;
										case 1:
											Double force = alllist.get(i)
													.get(k).getForceAnalysis();
											if (force != null) {
												sheet1.addCell(new jxl.write.Number(
														i + k + 1 + weight
																+ minus, j
																+ height,
														force, wcfNum2));
												sheet1.mergeCells(i + k + 1
														+ weight + minus, j
														+ height, i + k + 5
														+ weight + minus, j
														+ height);
											} else {
												sheet1.addCell(new Label(i + k
														+ 1 + weight + minus, j
														+ height, null, wcfNum));
												sheet1.mergeCells(i + k + 1
														+ weight + minus, j
														+ height, i + k + 5
														+ weight + minus, j
														+ height);
											}
											break;
										case 2:
											Double expect = alllist.get(i)
													.get(k)
													.getExpectedProduction();
											if (expect != null) {
												sheet1.addCell(new jxl.write.Number(
														i + k + 1 + weight
																+ minus, j
																+ height,
														expect, wcfNum2));
												sheet1.mergeCells(i + k + 1
														+ weight + minus, j
														+ height, i + k + 5
														+ weight + minus, j
														+ height);
											} else {
												sheet1.addCell(new Label(i + k
														+ 1 + weight + minus, j
														+ height, null, wcfNum));
												sheet1.mergeCells(i + k + 1
														+ weight + minus, j
														+ height, i + k + 5
														+ weight + minus, j
														+ height);
											}
											break;
										case 3:
											Double expPay = alllist.get(i)
													.get(k)
													.getExpectedPayment();
											if (expPay != null) {
												sheet1.addCell(new jxl.write.Number(
														i + k + 1 + weight
																+ minus, j
																+ height,
														expPay, wcfNum2));
												sheet1.mergeCells(i + k + 1
														+ weight + minus, j
														+ height, i + k + 5
														+ weight + minus, j
														+ height);
											} else {
												sheet1.addCell(new Label(i + k
														+ 1 + weight + minus, j
														+ height, null, wcfNum));
												sheet1.mergeCells(i + k + 1
														+ weight + minus, j
														+ height, i + k + 5
														+ weight + minus, j
														+ height);
											}
											break;
										case 4:
											sheet1.addCell(new Label(i + k + 1
													+ weight + minus, j
													+ height, cols.get(j + 1),
													wcfNum));
											sheet1.mergeCells(i + k + 1
													+ weight + minus, j
													+ height, i + k + 1
													+ weight + minus, j
													+ height + 3);
											Double hole = alllist.get(i).get(k)
													.getHole();
											if (hole != null) {
												sheet1.addCell(new jxl.write.Number(
														i + k + 2 + weight
																+ minus, j
																+ height, hole,
														wcfNum2));
												sheet1.mergeCells(i + k + 2
														+ weight + minus, j
														+ height, i + k + 2
														+ weight + minus, j
														+ height + 3);
											} else {
												sheet1.addCell(new Label(i + k
														+ 2 + weight + minus, j
														+ height, null, wcfNum));
												sheet1.mergeCells(i + k + 2
														+ weight + minus, j
														+ height, i + k + 2
														+ weight + minus, j
														+ height + 3);
											}
											sheet1.addCell(new Label(i + k + 3
													+ weight + minus, j
													+ height, cols.get(j + 2),
													wcfNum));
											Double posNum = alllist.get(i)
													.get(k).getPositiveNumber();
											if (posNum != null) {
												sheet1.addCell(new jxl.write.Number(
														i + k + 4 + weight
																+ minus, j
																+ height,
														posNum, wcfNum2));
												sheet1.mergeCells(i + k + 4
														+ weight + minus, j
														+ height, i + k + 5
														+ weight + minus, j
														+ height);
											} else {
												sheet1.addCell(new Label(i + k
														+ 4 + weight + minus, j
														+ height, null, wcfNum));
												sheet1.mergeCells(i + k + 4
														+ weight + minus, j
														+ height, i + k + 5
														+ weight + minus, j
														+ height);
											}
											sheet1.addCell(new Label(i + k + 3
													+ weight + minus, j
													+ height + 1, cols
													.get(j + 3), wcfNum));
											Double sample = alllist.get(i)
													.get(k).getSample();
											if (sample != null) {
												sheet1.addCell(new jxl.write.Number(
														i + k + 4 + weight
																+ minus, j
																+ height + 1,
														sample, wcfNum2));
												sheet1.mergeCells(i + k + 4
														+ weight + minus, j
														+ height + 1, i + k + 5
														+ weight + minus, j
														+ height + 1);
											} else {
												sheet1.addCell(new Label(i + k
														+ 4 + weight + minus, j
														+ height + 1, null,
														wcfNum));
												sheet1.mergeCells(i + k + 4
														+ weight + minus, j
														+ height + 1, i + k + 5
														+ weight + minus, j
														+ height + 1);
											}
											sheet1.addCell(new Label(i + k + 3
													+ weight + minus, j
													+ height + 2, cols
													.get(j + 4), wcfNum));
											Double acce = alllist.get(i).get(k)
													.getAccessories();
											if (acce != null) {
												sheet1.addCell(new jxl.write.Number(
														i + k + 4 + weight
																+ minus, j
																+ height + 2,
														acce, wcfNum2));
												sheet1.mergeCells(i + k + 4
														+ weight + minus, j
														+ height + 2, i + k + 5
														+ weight + minus, j
														+ height + 2);
											} else {
												sheet1.addCell(new Label(i + k
														+ 4 + weight + minus, j
														+ height + 2, null,
														wcfNum));
												sheet1.mergeCells(i + k + 4
														+ weight + minus, j
														+ height + 2, i + k + 5
														+ weight + minus, j
														+ height + 2);
											}
											sheet1.addCell(new Label(i + k + 3
													+ weight + minus, j
													+ height + 3, cols
													.get(j + 5), wcfNum));
											Double other = alllist.get(i)
													.get(k).getOther();
											if (other != null) {
												sheet1.addCell(new jxl.write.Number(
														i + k + 4 + weight
																+ minus, j
																+ height + 3,
														other, wcfNum2));
												sheet1.mergeCells(i + k + 4
														+ weight + minus, j
														+ height + 3, i + k + 5
														+ weight + minus, j
														+ height + 3);

											} else {
												sheet1.addCell(new Label(i + k
														+ 4 + weight + minus, j
														+ height + 3, null,
														wcfNum));
												sheet1.mergeCells(i + k + 4
														+ weight + minus, j
														+ height + 3, i + k + 5
														+ weight + minus, j
														+ height + 3);
											}
											break;
										}

									}// end for 3

								}// end for 2
								weight = weight + 5;
							}// end for 1

						}// end "if 1"

						if (all == 1) {// if 2
							List<String> cols = new ArrayList();
							cols.add("日期");
							cols.add("上模數");
							cols.add("人數");
							cols.add("標準產量");
							cols.add("實際產量");
							cols.add("達成率%");

							height = height + 8;
							int width = 0;

							/*
							 * int length=0; if(alllist2.size()%2==0){
							 * length=alllist2.size()/2; }else{
							 * length=alllist2.size()/2+1; }
							 */
							// /***并列循環***/
							// 并列循環A

							// 關掉循環A

							// /***并列循環***/
							// 并列循環B
							int minus = -width;// 清0,歸至原點 //***minus=-width-1**/

							int length = 0;
							if (alllist2.size() * 5 > 512) {
								length = 512 / 5;
							} else {
								length = alllist2.size();
							}
							for (int i = allLength; i < length; i++) {// for 1
																		// //***
																		// i=length**/
								/*
								 * if(i>1){ width=width-1; }
								 */

								double sumStandardOutput = 0;// 標準產量合計
								double sumActualYield = 0;// 實際產量合計

								for (int j = 0; j < cols.size(); j++) {// for 2

									if (i > allLength
											&& cols.get(j).equals("日期")) { // ****i>length****/
										continue;
									}
									sheet1.addCell(new Label(j + width + minus,
											height, cols.get(j), wcf));

									// **/添加合計條件**/
									double sumOnModulus = 0;// 上模數合計
									double sumPersonnum = 0;// 人數合計

									double sumAchievingRate = 0;// 月達成率
									String st_sumAchievingRate = "0";
									if (sumStandardOutput != 0
											&& sumActualYield != 0) {
										sumAchievingRate = sumActualYield
												/ sumStandardOutput;
										DecimalFormat format = new DecimalFormat(
												"#.0%");
										st_sumAchievingRate = format
												.format(sumAchievingRate);
									}

									for (int k = 0; k < 31 + 1; k++) {// for 3

										if (sheet1.getCell(j + width + minus,
												1 + height + k).getContents() == "") { // 先添加空單元格,避免有空位置
											sheet1.addCell(new Label(j + width
													+ minus, 1 + height + k,
													null, wcfNum));
										}

										int day = 0;

										switch (j) {
										case 0:
											StringBuffer date = new StringBuffer();
											date.append(year + "/");
											date.append(z + "/"); // *********date.append(month+"/")********//
											date.append(k + 1);
											// sheet1.addCell(new
											// Label(j+width+minus,
											// 1+height+k,date.toString(),wcfNum));
											if (k == 31) {
												sheet1.addCell(new Label(j
														+ width + minus, 1
														+ height + k, "合計",
														wcfCode));
											} else {
												sheet1.addCell(new Label(j
														+ width + minus, 1
														+ height + k, date
														.toString(), wcfNum));
											}

											break;

										case 1:

											try {
												day = alllist2.get(i).get(k)
														.getId().getYymmdd()
														.getDate();
												Double onModulus = alllist2
														.get(i).get(k)
														.getOnModulus();
												if (onModulus != null
														&& k == day - 1) {
													sheet1.addCell(new jxl.write.Number(
															j + width + minus,
															1 + height + k,
															onModulus, wcfNum));
													sumOnModulus = sumOnModulus
															+ onModulus;
												}
												if (onModulus != null
														&& k != day - 1) {
													sheet1.addCell(new jxl.write.Number(
															j + width + minus,
															1 + height
																	+ (day - 1),
															onModulus, wcfNum));
													// sheet1.addCell(new
													// Label(j+width+minus,
													// 1+height+k,null,wcfNum));
													sumOnModulus = sumOnModulus
															+ onModulus;
												}
												if (onModulus == null) {
													sheet1.addCell(new Label(j
															+ width + minus, 1
															+ height + k, null,
															wcfNum));
												}
											} catch (Exception e) {
												// TODO: handle exception
												if (sheet1.getCell(
														j + width + minus,
														1 + height + k)
														.getContents() == "") {
													sheet1.addCell(new Label(j
															+ width + minus, 1
															+ height + k, null,
															wcfNum));
												}
											}
											if (k == 31) {
												sheet1.addCell(new jxl.write.Number(
														j + width + minus, 1
																+ height + k,
														sumOnModulus, wcfNum));
											}
											break;
										case 2:

											try {
												day = alllist2.get(i).get(k)
														.getId().getYymmdd()
														.getDate();
												Double personnum = alllist2
														.get(i).get(k)
														.getPersonnum();
												if (personnum != null
														&& k == day - 1) {
													sheet1.addCell(new jxl.write.Number(
															j + width + minus,
															1 + height + k,
															personnum, wcfNum));
													sumPersonnum = sumPersonnum
															+ personnum;

												}
												if (personnum != null
														&& k != day - 1) {
													sheet1.addCell(new jxl.write.Number(
															j + width + minus,
															1 + height
																	+ (day - 1),
															personnum, wcfNum));
													// sheet1.addCell(new
													// Label(j+width+minus,
													// 1+height+k,null,wcfNum));
													sumPersonnum = sumPersonnum
															+ personnum;
												}
												if (personnum == null) {
													sheet1.addCell(new Label(j
															+ width + minus, 1
															+ height + k, null,
															wcfNum));
												}

											} catch (Exception e) {
												// TODO: handle exception
												if (sheet1.getCell(
														j + width + minus,
														1 + height + k)
														.getContents() == "") {
													sheet1.addCell(new Label(j
															+ width + minus, 1
															+ height + k, null,
															wcfNum));
												}
											}
											if (k == 31) {
												sheet1.addCell(new jxl.write.Number(
														j + width + minus, 1
																+ height + k,
														sumPersonnum, wcfNum));
											}
											break;
										case 3:
											try {
												day = alllist2.get(i).get(k)
														.getId().getYymmdd()
														.getDate();
												Double standardOutput = alllist2
														.get(i).get(k)
														.getStandardOutput();
												if (standardOutput != null
														&& k == day - 1) {
													sheet1.addCell(new jxl.write.Number(
															j + width + minus,
															1 + height + k,
															standardOutput,
															wcfNum));
													sumStandardOutput = sumStandardOutput
															+ standardOutput;
												}
												if (standardOutput != null
														&& k != day - 1) {
													sheet1.addCell(new jxl.write.Number(
															j + width + minus,
															1 + height
																	+ (day - 1),
															standardOutput,
															wcfNum));
													// sheet1.addCell(new
													// Label(j+width+minus,
													// 1+height+k,null,wcfNum));
													sumStandardOutput = sumStandardOutput
															+ standardOutput;
												}
												if (standardOutput == null) {
													sheet1.addCell(new Label(j
															+ width + minus, 1
															+ height + k, null,
															wcfNum));
												}

											} catch (Exception e) {
												// TODO: handle exception
												if (sheet1.getCell(
														j + width + minus,
														1 + height + k)
														.getContents() == "") {
													sheet1.addCell(new Label(j
															+ width + minus, 1
															+ height + k, null,
															wcfNum));
												}
											}
											if (k == 31) {
												sheet1.addCell(new jxl.write.Number(
														j + width + minus, 1
																+ height + k,
														sumStandardOutput,
														wcfNum));
											}
											break;
										case 4:
											try {
												day = alllist2.get(i).get(k)
														.getId().getYymmdd()
														.getDate();
												Double actualYield = alllist2
														.get(i).get(k)
														.getActualYield();
												if (actualYield != null
														&& k == day - 1) {
													sheet1.addCell(new jxl.write.Number(
															j + width + minus,
															1 + height + k,
															actualYield, wcfNum));
													sumActualYield = sumActualYield
															+ actualYield;
												}
												if (actualYield != null
														&& k != day - 1) {
													sheet1.addCell(new jxl.write.Number(
															j + width + minus,
															1 + height
																	+ (day - 1),
															actualYield, wcfNum));
													// sheet1.addCell(new
													// Label(j+width+minus,
													// 1+height+k,null,wcfNum));
													sumActualYield = sumActualYield
															+ actualYield;
												}
												if (actualYield == null) {
													sheet1.addCell(new Label(j
															+ width + minus, 1
															+ height + k, null,
															wcfNum));
												}
											} catch (Exception e) {
												// TODO: handle exception
												if (sheet1.getCell(
														j + width + minus,
														1 + height + k)
														.getContents() == "") {
													sheet1.addCell(new Label(j
															+ width + minus, 1
															+ height + k, null,
															wcfNum));
												}
											}
											if (k == 31) {
												sheet1.addCell(new jxl.write.Number(
														j + width + minus, 1
																+ height + k,
														sumActualYield, wcfNum));
											}
											break;
										case 5:
											try {
												day = alllist2.get(i).get(k)
														.getId().getYymmdd()
														.getDate();
												Double achievingRate = alllist2
														.get(i).get(k)
														.getAchievingRate();
												// String
												// st_achievingRate=achievingRate.toString()+"%";
												if (achievingRate != null
														&& k == day - 1) {
													sheet1.addCell(new jxl.write.Number(
															j + width + minus,
															1 + height + k,
															achievingRate,
															wcfNum2_percent));
													// sheet1.addCell(new
													// Label(j+width+minus,
													// 1+height+k,st_achievingRate,wcfNum));
												}
												if (achievingRate != null
														&& k != day - 1) {
													sheet1.addCell(new jxl.write.Number(
															j + width + minus,
															1 + height
																	+ (day - 1),
															achievingRate,
															wcfNum2_percent));
													// sheet1.addCell(new
													// Label(j+width+minus,
													// 1+height+k,null,wcfNum));
													// sheet1.addCell(new
													// Label(j+width+minus,
													// 1+height+(day-1),st_achievingRate,wcfNum));
												}
												if (achievingRate == null) {
													sheet1.addCell(new Label(j
															+ width + minus, 1
															+ height + k, null,
															wcfNum));
												}

											} catch (Exception e) {
												// TODO: handle exception
												if (sheet1.getCell(
														j + width + minus,
														1 + height + k)
														.getContents() == "") {
													sheet1.addCell(new Label(j
															+ width + minus, 1
															+ height + k, null,
															wcfNum));
												}
											}
											break;
										}
									}// end for 3

								}// end for 2
								width = width + 5;
							}// end for 1

						}// end "if 2"

					}// end All

				}// end "if ALL2"

				// *****判斷列數是否大於512***/
				// ******大於512******/
				if (alllist.size() * 5 > 512 || alllist2.size() * 5 > 512) {
					height = (z - firstMonth) * 43;// if ALL3
					allLength = 512 / 5;

					/*
					 * WritableSheet sheet2=wb.createSheet("總表2", 2);//表1
					 * SheetSettings settings2=sheet2.getSettings();
					 * settings2.setDefaultColumnWidth(14);
					 */

					for (int all = 0; all < 2; all++) {// All
						if (all == 0) { // if 1
							List<String> cols = new ArrayList();
							cols.add("日期/產量/廠別");
							cols.add("戰力分析模數");
							cols.add("預計生產模數");
							cols.add("預計請款雙數");
							cols.add("機台孔位數");
							cols.add("總機孔");
							cols.add("正批");
							cols.add("工程/樣品");
							cols.add("輔料");
							cols.add("其他");

							int weight = 0;

							// int length=0;
							/*
							 * if(alllist.size()%2==0){ length=alllist.size()/2;
							 * }else{ length=alllist.size()/2+1; }
							 */
							// length=256/5;

							// /***并列循環***/
							// 并列循環A

							// 關掉循環A

							// /***并列循環***/
							// 并列循環B
							int minus = -(allLength + weight);// 清0,歸至原點
																// //******* int
																// minus=-(length+weight)*******/
							for (int i = allLength; i < alllist.size(); i++) {// for
																				// 1
								if (i > allLength) { // ** i>length***/
									weight = weight - 1;
								}
								for (int j = 0; j < cols.size(); j++) {// for 2
									if (j < 5 && i == allLength) { // **
																	// j<5&&i==length***/
										sheet2.addCell(new Label(i + weight
												+ minus, j + height, cols
												.get(j), wcf));
										// sheet2.mergeCells(i+weight, j,
										// i+1+weight, j);
										if (j == 4) {
											sheet2.mergeCells(i + weight
													+ minus, j + height, i
													+ weight + minus, j
													+ height + 3);
										}
									}

									if (j == 5) {
										break;
									}

									for (int k = 0; k < alllist.get(i).size(); k++) {// for
																						// 3
										switch (j) {
										case 0:
											// String
											// factcode=alllist.get(i).get(k).getFactCode();

											String noAndCode = factnoCodelist
													.get(i);
											if (noAndCode.contains("沒有添加數據")) {
												sheet2.addCell(new Label(i + k
														+ 1 + weight + minus, j
														+ height, noAndCode,
														wcfCode2));
												sheet2.mergeCells(i + k + 1
														+ weight + minus, j
														+ height, i + k + 5
														+ weight + minus, j
														+ height);
											} else {
												sheet2.addCell(new Label(i + k
														+ 1 + weight + minus, j
														+ height, noAndCode,
														wcfCode));
												sheet2.mergeCells(i + k + 1
														+ weight + minus, j
														+ height, i + k + 5
														+ weight + minus, j
														+ height);
											}

											/*
											 * if(factcode!=null){
											 * sheet2.addCell(new
											 * Label(i+k+1+weight
											 * ,j,factcode,wcfNum));
											 * sheet2.mergeCells(i+k+1+weight,
											 * j, i+k+5+weight, j); }else{
											 * sheet2.addCell(new
											 * Label(i+k+1+weight
											 * ,j,null,wcfNum));
											 * sheet2.mergeCells(i+k+1+weight,
											 * j, i+k+5+weight, j); }
											 */
											break;
										case 1:
											Double force = alllist.get(i)
													.get(k).getForceAnalysis();
											if (force != null) {
												sheet2.addCell(new jxl.write.Number(
														i + k + 1 + weight
																+ minus, j
																+ height,
														force, wcfNum));
												sheet2.mergeCells(i + k + 1
														+ weight + minus, j
														+ height, i + k + 5
														+ weight + minus, j
														+ height);
											} else {
												sheet2.addCell(new Label(i + k
														+ 1 + weight + minus, j
														+ height, null, wcfNum));
												sheet2.mergeCells(i + k + 1
														+ weight + minus, j
														+ height, i + k + 5
														+ weight + minus, j
														+ height);
											}
											break;
										case 2:
											Double expect = alllist.get(i)
													.get(k)
													.getExpectedProduction();
											if (expect != null) {
												sheet2.addCell(new jxl.write.Number(
														i + k + 1 + weight
																+ minus, j
																+ height,
														expect, wcfNum));
												sheet2.mergeCells(i + k + 1
														+ weight + minus, j
														+ height, i + k + 5
														+ weight + minus, j
														+ height);
											} else {
												sheet2.addCell(new Label(i + k
														+ 1 + weight + minus, j
														+ height, null, wcfNum));
												sheet2.mergeCells(i + k + 1
														+ weight + minus, j
														+ height, i + k + 5
														+ weight + minus, j
														+ height);
											}
											break;
										case 3:
											Double expPay = alllist.get(i)
													.get(k)
													.getExpectedPayment();
											if (expPay != null) {
												sheet2.addCell(new jxl.write.Number(
														i + k + 1 + weight
																+ minus, j
																+ height,
														expPay, wcfNum));
												sheet2.mergeCells(i + k + 1
														+ weight + minus, j
														+ height, i + k + 5
														+ weight + minus, j
														+ height);
											} else {
												sheet2.addCell(new Label(i + k
														+ 1 + weight + minus, j
														+ height, null, wcfNum));
												sheet2.mergeCells(i + k + 1
														+ weight + minus, j
														+ height, i + k + 5
														+ weight + minus, j
														+ height);
											}
											break;
										case 4:
											sheet2.addCell(new Label(i + k + 1
													+ weight + minus, j
													+ height, cols.get(j + 1),
													wcfNum));
											sheet2.mergeCells(i + k + 1
													+ weight + minus, j
													+ height, i + k + 1
													+ weight + minus, j
													+ height + 3);
											Double hole = alllist.get(i).get(k)
													.getHole();
											if (hole != null) {
												sheet2.addCell(new jxl.write.Number(
														i + k + 2 + weight
																+ minus, j
																+ height, hole,
														wcfNum));
												sheet2.mergeCells(i + k + 2
														+ weight + minus, j
														+ height, i + k + 2
														+ weight + minus, j
														+ height + 3);
											} else {
												sheet2.addCell(new Label(i + k
														+ 2 + weight + minus, j
														+ height, null, wcfNum));
												sheet2.mergeCells(i + k + 2
														+ weight + minus, j
														+ height, i + k + 2
														+ weight + minus, j
														+ height + 3);
											}
											sheet2.addCell(new Label(i + k + 3
													+ weight + minus, j
													+ height, cols.get(j + 2),
													wcfNum));
											Double posNum = alllist.get(i)
													.get(k).getPositiveNumber();
											if (posNum != null) {
												sheet2.addCell(new jxl.write.Number(
														i + k + 4 + weight
																+ minus, j
																+ height,
														posNum, wcfNum));
												sheet2.mergeCells(i + k + 4
														+ weight + minus, j
														+ height, i + k + 5
														+ weight + minus, j
														+ height);
											} else {
												sheet2.addCell(new Label(i + k
														+ 4 + weight + minus, j
														+ height, null, wcfNum));
												sheet2.mergeCells(i + k + 4
														+ weight + minus, j
														+ height, i + k + 5
														+ weight + minus, j
														+ height);
											}
											sheet2.addCell(new Label(i + k + 3
													+ weight + minus, j
													+ height + 1, cols
													.get(j + 3), wcfNum));
											Double sample = alllist.get(i)
													.get(k).getSample();
											if (sample != null) {
												sheet2.addCell(new jxl.write.Number(
														i + k + 4 + weight
																+ minus, j
																+ height + 1,
														sample, wcfNum));
												sheet2.mergeCells(i + k + 4
														+ weight + minus, j
														+ height + 1, i + k + 5
														+ weight + minus, j
														+ height + 1);
											} else {
												sheet2.addCell(new Label(i + k
														+ 4 + weight + minus, j
														+ height + 1, null,
														wcfNum));
												sheet2.mergeCells(i + k + 4
														+ weight + minus, j
														+ height + 1, i + k + 5
														+ weight + minus, j
														+ height + 1);
											}
											sheet2.addCell(new Label(i + k + 3
													+ weight + minus, j
													+ height + 2, cols
													.get(j + 4), wcfNum));
											Double acce = alllist.get(i).get(k)
													.getAccessories();
											if (acce != null) {
												sheet2.addCell(new jxl.write.Number(
														i + k + 4 + weight
																+ minus, j
																+ height + 2,
														acce, wcfNum));
												sheet2.mergeCells(i + k + 4
														+ weight + minus, j
														+ height + 2, i + k + 5
														+ weight + minus, j
														+ height + 2);
											} else {
												sheet2.addCell(new Label(i + k
														+ 4 + weight + minus, j
														+ height + 2, null,
														wcfNum));
												sheet2.mergeCells(i + k + 4
														+ weight + minus, j
														+ height + 2, i + k + 5
														+ weight + minus, j
														+ height + 2);
											}
											sheet2.addCell(new Label(i + k + 3
													+ weight + minus, j
													+ height + 3, cols
													.get(j + 5), wcfNum));
											Double other = alllist.get(i)
													.get(k).getOther();
											if (other != null) {
												sheet2.addCell(new jxl.write.Number(
														i + k + 4 + weight
																+ minus, j
																+ height + 3,
														other, wcfNum));
												sheet2.mergeCells(i + k + 4
														+ weight + minus, j
														+ height + 3, i + k + 5
														+ weight + minus, j
														+ height + 3);

											} else {
												sheet2.addCell(new Label(i + k
														+ 4 + weight + minus, j
														+ height + 3, null,
														wcfNum));
												sheet2.mergeCells(i + k + 4
														+ weight + minus, j
														+ height + 3, i + k + 5
														+ weight + minus, j
														+ height + 3);
											}
											break;
										}

									}// end for 3

								}// end for 2
								weight = weight + 5;
							}// end for 1

						}// end "if 1"

						if (all == 1) {// if 2
							List<String> cols = new ArrayList();
							cols.add("日期");
							cols.add("上模數");
							cols.add("人數");
							cols.add("標準產量");
							cols.add("實際產量");
							cols.add("達成率%");

							height = height + 8;
							int width = 0;

							/*
							 * int length=0; if(alllist2.size()%2==0){
							 * length=alllist2.size()/2; }else{
							 * length=alllist2.size()/2+1; }
							 */
							// /***并列循環***/
							// 并列循環A

							// 關掉循環A

							// /***并列循環***/
							// 并列循環B
							int minus = -width;// 清0,歸至原點 //***minus=-width-1**/
							for (int i = allLength; i < alllist2.size(); i++) {// for
																				// 1
																				// //***
																				// i=length**/
								/*
								 * if(i>1){ width=width-1; }
								 */
								double sumStandardOutput = 0;// 標準產量合計
								double sumActualYield = 0;// 實際產量合計

								for (int j = 0; j < cols.size(); j++) {// for 2

									if (i > allLength
											&& cols.get(j).equals("日期")) { // ****i>length****/
										continue;
									}
									sheet2.addCell(new Label(j + width + minus,
											height, cols.get(j), wcf));

									// **/添加合計條件**/
									double sumOnModulus = 0;// 上模數合計
									double sumPersonnum = 0;// 人數合計
									double sumAchievingRate = 0;// 月達成率
									String st_sumAchievingRate = "0";
									if (sumStandardOutput != 0
											&& sumActualYield != 0) {
										sumAchievingRate = sumActualYield
												/ sumStandardOutput;
										DecimalFormat format = new DecimalFormat(
												"#.0%");
										st_sumAchievingRate = format
												.format(sumAchievingRate);
									}

									for (int k = 0; k < 31 + 1; k++) {// for 3

										if (sheet2.getCell(j + width + minus,
												1 + height + k).getContents() == "") { // 先添加空單元格,避免有空位置
											sheet2.addCell(new Label(j + width
													+ minus, 1 + height + k,
													null, wcfNum));
										}

										int day = 0;

										switch (j) {
										case 0:
											StringBuffer date = new StringBuffer();
											date.append(year + "/");
											date.append(z + "/"); // ********date.append(month+"/")******//
											date.append(k + 1);
											// sheet2.addCell(new
											// Label(j+width+minus,
											// 1+height+k,date.toString(),wcfNum));
											if (k == 31) {
												sheet2.addCell(new Label(j
														+ width + minus, 1
														+ height + k, "合計",
														wcfCode));
											} else {
												sheet2.addCell(new Label(j
														+ width + minus, 1
														+ height + k, date
														.toString(), wcfNum));
											}

											break;

										case 1:

											try {
												day = alllist2.get(i).get(k)
														.getId().getYymmdd()
														.getDate();
												Double onModulus = alllist2
														.get(i).get(k)
														.getOnModulus();
												if (onModulus != null
														&& k == day - 1) {
													sheet2.addCell(new jxl.write.Number(
															j + width + minus,
															1 + height + k,
															onModulus, wcfNum));
													sumOnModulus = sumOnModulus
															+ onModulus;
												}
												if (onModulus != null
														&& k != day - 1) {
													sheet2.addCell(new jxl.write.Number(
															j + width + minus,
															1 + height
																	+ (day - 1),
															onModulus, wcfNum));
													// sheet2.addCell(new
													// Label(j+width+minus,
													// 1+height+k,null,wcfNum));
													sumOnModulus = sumOnModulus
															+ onModulus;
												}
												if (onModulus == null) {
													sheet2.addCell(new Label(j
															+ width + minus, 1
															+ height + k, null,
															wcfNum));
												}
											} catch (Exception e) {
												// TODO: handle exception
												if (sheet2.getCell(
														j + width + minus,
														1 + height + k)
														.getContents() == "") {
													sheet2.addCell(new Label(j
															+ width + minus, 1
															+ height + k, null,
															wcfNum));
												}
											}
											if (k == 31) {
												sheet2.addCell(new jxl.write.Number(
														j + width + minus, 1
																+ height + k,
														sumOnModulus, wcfNum));
											}
											break;
										case 2:

											try {
												day = alllist2.get(i).get(k)
														.getId().getYymmdd()
														.getDate();
												Double personnum = alllist2
														.get(i).get(k)
														.getPersonnum();
												if (personnum != null
														&& k == day - 1) {
													sheet2.addCell(new jxl.write.Number(
															j + width + minus,
															1 + height + k,
															personnum, wcfNum));
													sumPersonnum = sumPersonnum
															+ personnum;

												}
												if (personnum != null
														&& k != day - 1) {
													sheet2.addCell(new jxl.write.Number(
															j + width + minus,
															1 + height
																	+ (day - 1),
															personnum, wcfNum));
													// sheet2.addCell(new
													// Label(j+width+minus,
													// 1+height+k,null,wcfNum));
													sumPersonnum = sumPersonnum
															+ personnum;
												}
												if (personnum == null) {
													sheet2.addCell(new Label(j
															+ width + minus, 1
															+ height + k, null,
															wcfNum));
												}

											} catch (Exception e) {
												// TODO: handle exception
												if (sheet2.getCell(
														j + width + minus,
														1 + height + k)
														.getContents() == "") {
													sheet2.addCell(new Label(j
															+ width + minus, 1
															+ height + k, null,
															wcfNum));
												}
											}
											if (k == 31) {
												sheet2.addCell(new jxl.write.Number(
														j + width + minus, 1
																+ height + k,
														sumPersonnum, wcfNum));
											}
											break;
										case 3:
											try {
												day = alllist2.get(i).get(k)
														.getId().getYymmdd()
														.getDate();
												Double standardOutput = alllist2
														.get(i).get(k)
														.getStandardOutput();
												if (standardOutput != null
														&& k == day - 1) {
													sheet2.addCell(new jxl.write.Number(
															j + width + minus,
															1 + height + k,
															standardOutput,
															wcfNum));
													sumStandardOutput = sumStandardOutput
															+ standardOutput;
												}
												if (standardOutput != null
														&& k != day - 1) {
													sheet2.addCell(new jxl.write.Number(
															j + width + minus,
															1 + height
																	+ (day - 1),
															standardOutput,
															wcfNum));
													// sheet2.addCell(new
													// Label(j+width+minus,
													// 1+height+k,null,wcfNum));
													sumStandardOutput = sumStandardOutput
															+ standardOutput;
												}
												if (standardOutput == null) {
													sheet2.addCell(new Label(j
															+ width + minus, 1
															+ height + k, null,
															wcfNum));
												}

											} catch (Exception e) {
												// TODO: handle exception
												if (sheet2.getCell(
														j + width + minus,
														1 + height + k)
														.getContents() == "") {
													sheet2.addCell(new Label(j
															+ width + minus, 1
															+ height + k, null,
															wcfNum));
												}
											}
											if (k == 31) {
												sheet2.addCell(new jxl.write.Number(
														j + width + minus, 1
																+ height + k,
														sumStandardOutput,
														wcfNum));
											}
											break;
										case 4:
											try {
												day = alllist2.get(i).get(k)
														.getId().getYymmdd()
														.getDate();
												Double actualYield = alllist2
														.get(i).get(k)
														.getActualYield();
												if (actualYield != null
														&& k == day - 1) {
													sheet2.addCell(new jxl.write.Number(
															j + width + minus,
															1 + height + k,
															actualYield, wcfNum));
													sumActualYield = sumActualYield
															+ actualYield;
												}
												if (actualYield != null
														&& k != day - 1) {
													sheet2.addCell(new jxl.write.Number(
															j + width + minus,
															1 + height
																	+ (day - 1),
															actualYield, wcfNum));
													// sheet2.addCell(new
													// Label(j+width+minus,
													// 1+height+k,null,wcfNum));
													sumActualYield = sumActualYield
															+ actualYield;
												}
												if (actualYield == null) {
													sheet2.addCell(new Label(j
															+ width + minus, 1
															+ height + k, null,
															wcfNum));
												}
											} catch (Exception e) {
												// TODO: handle exception
												if (sheet2.getCell(
														j + width + minus,
														1 + height + k)
														.getContents() == "") {
													sheet2.addCell(new Label(j
															+ width + minus, 1
															+ height + k, null,
															wcfNum));
												}
											}
											if (k == 31) {
												sheet2.addCell(new jxl.write.Number(
														j + width + minus, 1
																+ height + k,
														sumActualYield, wcfNum));
											}
											break;
										case 5:
											try {
												day = alllist2.get(i).get(k)
														.getId().getYymmdd()
														.getDate();
												Double achievingRate = alllist2
														.get(i).get(k)
														.getAchievingRate();
												// String
												// st_achievingRate=achievingRate.toString()+"%";
												if (achievingRate != null
														&& k == day - 1) {
													sheet2.addCell(new jxl.write.Number(
															j + width + minus,
															1 + height + k,
															achievingRate,
															wcfNum2_percent));
													// sheet2.addCell(new
													// Label(j+width+minus,
													// 1+height+k,st_achievingRate,wcfNum));
												}
												if (achievingRate != null
														&& k != day - 1) {
													sheet2.addCell(new jxl.write.Number(
															j + width + minus,
															1 + height
																	+ (day - 1),
															achievingRate,
															wcfNum2_percent));
													// sheet2.addCell(new
													// Label(j+width+minus,
													// 1+height+k,null,wcfNum));
													// sheet2.addCell(new
													// Label(j+width+minus,
													// 1+height+(day-1),st_achievingRate,wcfNum));
												}
												if (achievingRate == null) {
													sheet2.addCell(new Label(j
															+ width + minus, 1
															+ height + k, null,
															wcfNum));
												}

											} catch (Exception e) {
												// TODO: handle exception
												if (sheet2.getCell(
														j + width + minus,
														1 + height + k)
														.getContents() == "") {
													sheet2.addCell(new Label(j
															+ width + minus, 1
															+ height + k, null,
															wcfNum));
												}
											}
											break;
										}
									}// end for 3

								}// end for 2
								width = width + 5;
							}// end for 1

						}// end "if 2"

					}// end All

				}// end "if ALL3"

			}// end totalALL

			wb.write();
			wb.close();

			// response.resetBuffer();//刷新
			// System.out.print("PrintList OK");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
