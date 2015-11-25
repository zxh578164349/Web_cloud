package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.interceptor.ServletResponseAware;

import services.IWebFactServices;
import services.IWebFixedLogServices;
import services.IWebFixedServices;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.WebFixed;
import entity.WebFixedLog;

public class WebFixedAction extends ActionSupport implements
		ServletResponseAware {
	private IWebFixedServices fixSer;
	private String factNo;
	private PageBean bean;
	private int page;
	private String id;
	private WebFixed fix;
	private IWebFactServices webFactSer;
	private String isnull;
	private String yymm;
	private IWebFixedLogServices fixlogSer;
	private WebFixedLog log;
	private String fixedassetsId;
	private javax.servlet.http.HttpServletResponse response;
	private String goclear;
	private String factNo_print;
	private String yymm_s;
	private String lostmk;
	

	public String getLostmk() {
		return lostmk;
	}

	public void setLostmk(String lostmk) {
		this.lostmk = lostmk;
	}

	public String getYymm_s() {
		return yymm_s;
	}

	public void setYymm_s(String yymm_s) {
		this.yymm_s = yymm_s;
	}

	public String getFactNo_print() {
		return factNo_print;
	}

	public void setFactNo_print(String factNo_print) {
		this.factNo_print = factNo_print;
	}

	public String getGoclear() {
		return goclear;
	}

	public void setGoclear(String goclear) {
		this.goclear = goclear;
	}

	public WebFixedLog getLog() {
		return log;
	}

	public void setLog(WebFixedLog log) {
		this.log = log;
	}

	public String getFixedassetsId() {
		return fixedassetsId;
	}

	public void setFixedassetsId(String fixedassetsId) {
		this.fixedassetsId = fixedassetsId;
	}

	public void setFixlogSer(IWebFixedLogServices fixlogSer) {
		this.fixlogSer = fixlogSer;
	}

	public String getYymm() {
		return yymm;
	}

	public void setYymm(String yymm) {
		this.yymm = yymm;
	}

	public String getIsnull() {
		return isnull;
	}

	public void setIsnull(String isnull) {
		this.isnull = isnull;
	}

	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}

	public WebFixed getFix() {
		return fix;
	}

	public void setFix(WebFixed fix) {
		this.fix = fix;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public PageBean getBean() {
		return bean;
	}

	public void setBean(PageBean bean) {
		this.bean = bean;
	}

	public String getFactNo() {
		return factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public void setFixSer(IWebFixedServices fixSer) {
		this.fixSer = fixSer;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	public String findPageBean() {
		//ActionContext.getContext().getApplication().clear();
		ActionContext.getContext().getSession().remove("public_factNo");
		ActionContext.getContext().getSession().remove("public_yymm");
		ActionContext.getContext().getSession().remove("public_yymms");
		ActionContext.getContext().getSession().remove("public_lostmk");
		factNo = (String) ActionContext.getContext().getSession().get("factNo");
		bean = fixSer.findPageBean(25, page, factNo, yymm,yymm_s,lostmk);
		return "beanList";
	}

	public String findPageBean2() {
		//ActionContext.getContext().getApplication().clear();
		ActionContext.getContext().getSession().remove("public_factNo");
		ActionContext.getContext().getSession().remove("public_yymm");
		ActionContext.getContext().getSession().remove("public_yymms");
		ActionContext.getContext().getSession().remove("public_lostmk");
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			ActionContext.getContext().getSession().put("public_factNo", factNo);					
		}

		if (yymm != null && !yymm.equals("")) {
			ActionContext.getContext().getSession().put("public_yymm", yymm);
		}
		if(yymm_s!=null&&!yymm_s.equals("")){
			ActionContext.getContext().getSession().put("public_yymms", yymm_s);
		}
		if(lostmk!=null&&!lostmk.equals("")){
			ActionContext.getContext().getSession().put("public_lostmk", lostmk);
		}

		bean = fixSer.findPageBean(25, page, factNo, yymm,yymm_s,lostmk);
		return "beanList1";
	}

	public String findPageBean3() {
		factNo = (String) ActionContext.getContext().getSession().get("public_factNo");				
		yymm = (String) ActionContext.getContext().getSession().get("public_yymm");				
		yymm_s=(String)ActionContext.getContext().getSession().get("public_yymms");
		lostmk=(String)ActionContext.getContext().getSession().get("public_lostmk");

		if (factNo == null || factNo.equals("") || factNo.equals("tw")) {
			factNo = (String) ActionContext.getContext().getSession().get("factNo");					
		}
		bean = fixSer.findPageBean(25, page, factNo, yymm,yymm_s,lostmk);

		return "beanList1";

	}

	public String findPageBean2_print() {
		ActionContext.getContext().getApplication().clear();
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			ActionContext.getContext().getApplication()
					.put("print_fix_factNo", factNo);

		} else {
			factNo = (String) ActionContext.getContext().getSession()
					.get("factNo");
		}

		if (yymm != null && !yymm.equals("")) {
			ActionContext.getContext().getApplication()
					.put("print_fix_yymm", yymm);
		}
		
		bean = fixSer.findPageBean(25, page, factNo, yymm,null,lostmk);
		return "list";
	}

	public String findPageBean3_print() {
		factNo = (String) ActionContext.getContext().getApplication()
				.get("print_fix_factNo");

		yymm = (String) ActionContext.getContext().getApplication()
				.get("print_fix_yymm");

		if (factNo == null || factNo.equals("") || factNo.equals("tw")) {
			factNo = (String) ActionContext.getContext().getSession()
					.get("factNo");
		}
		bean = fixSer.findPageBean(25, page, factNo, yymm,null,lostmk);
		return "list";
	}

	public String findById() {
		fix = fixSer.findById(id);
		return "findById";
	}

	public String findById2() {
		fix = fixSer.findById(id);
		return "findById2";
	}

	public String delete() {
		fixSer.deleteWebFixed(id);
		return "delete";
	}

	public String addFix() {
		factNo = (String) ActionContext.getContext().getSession().get("factNo");
		String result = null;
		if (isnull.equals("isnull")) {// start if1
			List fixedassetsidlist = fixSer.findAllFixedassetsId();
			int maxNum2 = 0;
			for (int j = 0; j < fixedassetsidlist.size(); j++) {
				int temp = Integer.parseInt((String) fixedassetsidlist.get(j));
				if (temp > maxNum2) {
					maxNum2 = temp;
				}
			}
			String temp = (maxNum2 + 1) + "";
			fix.setFixedassetsId(temp);

			List<WebFixed> list = fixSer.findByFactNo(factNo, yymm,null,null);
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					if (fix.getFixedId().equals(list.get(i).getFixedId())) {
						break;
					} else if (i == list.size() - 1) {
						fixSer.addWebFixed(fix);
						result = "addFix";
					}
				}
			} else {
				fixSer.addWebFixed(fix);
				result = "addFix";
			}
		}// end if1

		if (goclear != null && goclear.equals("yclear")) {// start if2
			List<WebFixed> list2 = fixSer.findByFactNo(factNo, yymm,null,null);
			for (int y = 0; y < list2.size(); y++) {
				if (fix.getFixedId().equals(list2.get(y).getFixedId())) {
					break;
				} else if (y == list2.size() - 1) {
					fixSer.addWebFixed(fix);
					result = "addFix";
				}
			}
		}// end if2

		if (goclear != null && goclear.equals("nclear")) {// start if3
			fixSer.addWebFixed(fix);
			result = "addFix";
		}// end if3

		if (result == null) {
			response.setContentType("text/html;charset=utf-8");
			String temp = fix.getFixedId();
			try {
				response.getWriter()
						.print("<script>alert('數據庫已存在("
								+ temp
								+ ")!請重新添加');window.location.href='saveAndUpdate/fixedSaveOrUpdate.jsp'</script>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public String addlog() {
		/*
		 * fix=fixSer.findById(fixedassetsId);
		 * if(log.getChangedate()!=null&&!log.getChangedate().equals("")){
		 * fix.setAddTime(log.getChangedate()); }
		 * fix.setFactNo(log.getFactnoTo());
		 * fix.setFactcode(log.getFactcodeTo()); if(log.getFixedId()==null){
		 * fix.setFixedId(null); }else{ fix.setFixedId(log.getFixedId()); }
		 * log.setLogtime(new Date()); fixSer.addWebFixed(fix);
		 * fixlogSer.add(log); return "addlog";
		 */

		factNo = (String) ActionContext.getContext().getSession().get("factNo");
		List<WebFixed> list = fixSer.findByFactNo(factNo, yymm,null,null);
		String result = null;
		fix = fixSer.findById(fixedassetsId);
		fix.setAddTime(log.getChangedate());
		fix.setFactNo(log.getFactnoTo());
		fix.setFactcode(log.getFactcodeTo());
		fix.setAssetname(log.getAssetname());
		log.setLogtime(new Date());
		for (int i = 0; i < list.size(); i++) {
			if (log.getFixedId().equals(list.get(i).getFixedId())) {
				break;
			} else if (i == list.size() - 1) {
				fix.setFixedId(log.getFixedId());
				fixSer.addWebFixed(fix);
				fixlogSer.add(log);
				result = "addlog";
			}
		}
		if (result == null) {
			String temp = log.getFixedId();
			response.setContentType("text/html;charset=utf-8");
			try {
				response.getWriter().print(
						"<script>alert('數據庫已存在" + temp
								+ ",請重新調撥!');history.back(-1)</script>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*
		 * if(log.getFixedId()==null){ fix.setFixedId(null); }else{
		 * fix.setFixedId(log.getFixedId()); }
		 */

		/*
		 * fixSer.addWebFixed(fix); fixlogSer.add(log);
		 */
		return result;
	}

	public void toExcel() {
		response.reset();
		try {
			ServletOutputStream os = response.getOutputStream();
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,charset=utf-8");
			String tempName = "";
			if (factNo == null || factNo.equals("")) {
				tempName = "fixed.xlsx";
			} else {
				tempName = factNo + "_fixed.xlsx";
			}
			String fileName = new String(tempName.toString().getBytes("utf-8"),
					"ISO8859-1");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName);
			// 工作区
			XSSFWorkbook wb = new XSSFWorkbook();
			// 创建第一个sheet
			XSSFSheet sheet = wb.createSheet("固定資產");
			sheet.setColumnWidth(4, 4000);
			sheet.setColumnWidth(5, 6000);
			sheet.setColumnWidth(7, 4000);
			sheet.setColumnWidth(9, 14000);
			sheet.setColumnWidth(10, 8000);
			sheet.setColumnWidth(18, 4000);
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

			// 粗字體
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

			// 粗字體
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

			// 粗字體和背景顏色
			XSSFCellStyle cs_font_bgyellow = wb.createCellStyle();
			XSSFFont font_bold_bgyellow = wb.createFont();
			font_bold_bgyellow.setFontHeightInPoints((short) 10);
			font_bold_bgyellow.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
			cs_font_bgyellow.setFont(font_bold_bgyellow);
			cs_font_bgyellow.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			cs_font_bgyellow
					.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
			cs_font_bgyellow.setBorderBottom(XSSFCellStyle.BORDER_THIN);
			cs_font_bgyellow.setBorderLeft(XSSFCellStyle.BORDER_THIN);
			cs_font_bgyellow.setBorderRight(XSSFCellStyle.BORDER_THIN);
			cs_font_bgyellow.setBorderTop(XSSFCellStyle.BORDER_THIN);
			cs_font_bgyellow.setFillForegroundColor(IndexedColors.YELLOW
					.getIndex());
			cs_font_bgyellow.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
			cs_font_bgyellow.setWrapText(true);

			// 紅粗字體
			XSSFCellStyle cs_font_red_bold = wb.createCellStyle();
			XSSFFont font_red_bold = wb.createFont();
			font_red_bold.setFontHeightInPoints((short) 10);
			font_red_bold.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
			font_red_bold.setColor(XSSFFont.COLOR_RED);
			cs_font_red_bold.setFont(font_red_bold);
			cs_font_red_bold.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			cs_font_red_bold
					.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
			cs_font_red_bold.setBorderBottom(XSSFCellStyle.BORDER_THIN);
			cs_font_red_bold.setBorderLeft(XSSFCellStyle.BORDER_THIN);
			cs_font_red_bold.setBorderRight(XSSFCellStyle.BORDER_THIN);
			cs_font_red_bold.setBorderTop(XSSFCellStyle.BORDER_THIN);

			/*
			 * font.setFontName("黑体"); font.setFontHeightInPoints((short)
			 * 16);//设置字体大小
			 * 
			 * XSSFFont font2 = wb.createFont(); font2.setFontName("仿宋_GB2312");
			 * font2.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);//粗体显示
			 * font2.setFontHeightInPoints((short) 12);
			 * cs.setFont(font);//选择需要用到的字体格式 //设置列宽: sheet.setColumnWidth(0,
			 * 3766); //第一个参数代表列id(从0开始),第2个参数代表宽度值 //合併單元格 CellRangeAddress
			 * region1 = new CellRangeAddress(rowNumber, rowNumber, (short) 0,
			 * (short) 11);//参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列
			 * sheet.addMergedRegion(region1);
			 */
			// 數字格式
			XSSFDataFormat format = wb.createDataFormat();

			XSSFCellStyle cs_data = wb.createCellStyle();
			cs_data.setDataFormat(format.getFormat("0.0%"));
			cs_data.setBorderBottom(XSSFCellStyle.BORDER_THIN);
			cs_data.setBorderLeft(XSSFCellStyle.BORDER_THIN);
			cs_data.setBorderRight(XSSFCellStyle.BORDER_THIN);
			cs_data.setBorderTop(XSSFCellStyle.BORDER_THIN);
			cs_data.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			cs_data.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

			XSSFCellStyle cs_thousand = wb.createCellStyle();
			cs_thousand.setDataFormat(format.getFormat("#,###.##"));
			cs_thousand.setBorderBottom(XSSFCellStyle.BORDER_THIN);
			cs_thousand.setBorderLeft(XSSFCellStyle.BORDER_THIN);
			cs_thousand.setBorderRight(XSSFCellStyle.BORDER_THIN);
			cs_thousand.setBorderTop(XSSFCellStyle.BORDER_THIN);
			cs_thousand.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			cs_thousand.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

			XSSFCellStyle cs_thousand_person = wb.createCellStyle();
			cs_thousand_person.setDataFormat(format.getFormat("#,##0"));
			cs_thousand_person.setBorderBottom(XSSFCellStyle.BORDER_THIN);
			cs_thousand_person.setBorderLeft(XSSFCellStyle.BORDER_THIN);
			cs_thousand_person.setBorderRight(XSSFCellStyle.BORDER_THIN);
			cs_thousand_person.setBorderTop(XSSFCellStyle.BORDER_THIN);
			cs_thousand_person.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			cs_thousand_person
					.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
			// 设置自动换行:
			cs.setWrapText(true);// 设置自动换行

			List<WebFixed> list = fixSer.findByFactNo(factNo, yymm,yymm_s,lostmk);
			List<String> listcol = new ArrayList();
			listcol.add("序號");
			listcol.add("廠別");
			listcol.add("廠別狀態");
			listcol.add("集團財編");
			listcol.add("財產編號");
			listcol.add("名稱");
			listcol.add("大分類ID");
			listcol.add("大分類名稱");
			listcol.add("小分類ID");
			listcol.add("小分類名稱");
			listcol.add("幣別");
			listcol.add("購入單價");
			listcol.add("型號");
			listcol.add("品牌");
			listcol.add("產地");
			listcol.add("單位ID");
			listcol.add("單位名稱");
			listcol.add("增加方式ID");
			listcol.add("增加方式名稱");
			listcol.add("折舊計算方式ID");
			listcol.add("折舊計算方式名稱");
			//listcol.add("幣別");
			listcol.add("進廠日期");
			listcol.add("驗收日期");
			listcol.add("存放位置/保管人");
			listcol.add("處分");
			listcol.add("區域");
			listcol.add("是否可刪除");
			listcol.add("處分");

			for (int x = 0; x < listcol.size(); x++) {// start "for 1"
				if (x == 0) {
					sheet.createRow(0).createCell(x)
							.setCellValue(listcol.get(x));
				} else {
					sheet.getRow(0).createCell(x).setCellValue(listcol.get(x));
				}
				sheet.getRow(0).getCell(x).setCellStyle(cs_font_bgyellow);
				for (int y = 0; y < list.size(); y++) {// start "for 2"
					String factno = list.get(y).getFactNo();
					String factcode = list.get(y).getFactcode();
					String fixedId = list.get(y).getFixedId();
					String assetscoding = list.get(y).getAssetscoding();
					String assetname = list.get(y).getAssetname();
					String majorId = list.get(y).getMajorId();
					String majorName = list.get(y).getMajorName();
					String subId = list.get(y).getSubId();
					String subName = list.get(y).getSubName();
					String model = list.get(y).getModel();
					String brand = list.get(y).getBrand();
					String manufacturer = list.get(y).getManufacturer();
					String unitsId = list.get(y).getUnitsId();
					String unitsName = list.get(y).getUnitsName();
					String addwaysId = list.get(y).getAddwaysId();
					String addwaysName = list.get(y).getAddwaysName();
					String methodId = list.get(y).getMethodId();
					String methodName = list.get(y).getMethodName();
					String currency = list.get(y).getCurrency();
					String addTime = list.get(y).getAddTime();
					String delMk = list.get(y).getDelMk();
					Double priceIn = list.get(y).getPriceIn();
					String checkDate=list.get(y).getCheckdate();
					String keeper=list.get(y).getKeeper();
					String lostMk=list.get(y).getLostMk();
					String areaName=list.get(y).getAreaName();
					String lostmk_temp=list.get(y).getLostMk();
					switch (x) {
					case 0:
						sheet.createRow(y + 1).createCell(x)
								.setCellValue(y + 1);
						sheet.getRow(y + 1).getCell(x)
								.setCellStyle(cs_font_bgyellow);
						break;
					case 1:
						if (factno != null) {
							sheet.getRow(y + 1).createCell(x)
									.setCellValue(factno);
							sheet.getRow(y + 1).getCell(x).setCellStyle(cs);
						} else {
							sheet.getRow(y + 1).createCell(x).setCellStyle(cs);
						}
						break;
					case 2:
						if (factcode != null) {
							sheet.getRow(y + 1).createCell(x)
									.setCellValue(factcode);
							sheet.getRow(y + 1).getCell(x).setCellStyle(cs);
						} else {
							sheet.getRow(y + 1).createCell(x).setCellStyle(cs);
						}
						break;
					case 3:
						if (fixedId != null) {
							sheet.getRow(y + 1).createCell(x)
									.setCellValue(fixedId);
							sheet.getRow(y + 1).getCell(x).setCellStyle(cs);
						} else {
							sheet.getRow(y + 1).createCell(x).setCellStyle(cs);
						}
						break;
					case 4:

						if (assetscoding != null) {
							sheet.getRow(y + 1).createCell(x)
									.setCellValue(assetscoding);
							sheet.getRow(y + 1).getCell(x).setCellStyle(cs);
						} else {
							sheet.getRow(y + 1).createCell(x).setCellStyle(cs);
						}
						break;
					case 5:

						if (assetname != null) {
							sheet.getRow(y + 1).createCell(x)
									.setCellValue(assetname);
							sheet.getRow(y + 1).getCell(x).setCellStyle(cs);
						} else {
							sheet.getRow(y + 1).createCell(x).setCellStyle(cs);
						}
						break;
					case 6:
						if (majorId != null) {
							sheet.getRow(y + 1).createCell(x)
									.setCellValue(majorId);
							sheet.getRow(y + 1).getCell(x).setCellStyle(cs);
						} else {
							sheet.getRow(y + 1).createCell(x).setCellStyle(cs);
						}
						break;
					case 7:
						if (majorName != null) {
							sheet.getRow(y + 1).createCell(x)
									.setCellValue(majorName);
							sheet.getRow(y + 1).getCell(x).setCellStyle(cs);
						} else {
							sheet.getRow(y + 1).createCell(x).setCellStyle(cs);
						}
						break;
					case 8:
						if (subId != null) {
							sheet.getRow(y + 1).createCell(x)
									.setCellValue(subId);
							sheet.getRow(y + 1).getCell(x).setCellStyle(cs);
						} else {
							sheet.getRow(y + 1).createCell(x).setCellStyle(cs);
						}
						break;
					case 9:
						if (subName != null) {
							sheet.getRow(y + 1).createCell(x)
									.setCellValue(subName);
							sheet.getRow(y + 1).getCell(x).setCellStyle(cs);
						} else {
							sheet.getRow(y + 1).createCell(x).setCellStyle(cs);
						}
						break;
					case 10:
						if (currency != null) {
							sheet.getRow(y + 1).createCell(x)
									.setCellValue(currency);
							sheet.getRow(y + 1).getCell(x).setCellStyle(cs);
						} else {
							sheet.getRow(y + 1).createCell(x).setCellStyle(cs);
						}
						break;
					case 11:
						if (priceIn != null) {
							sheet.getRow(y + 1).createCell(x)
									.setCellValue(priceIn);
							sheet.getRow(y + 1).getCell(x)
									.setCellStyle(cs_thousand);
						} else {
							sheet.getRow(y + 1).createCell(x).setCellStyle(cs);
						}
						break;
					case 12:
						if (model != null) {
							sheet.getRow(y + 1).createCell(x)
									.setCellValue(model);
							sheet.getRow(y + 1).getCell(x).setCellStyle(cs);
						} else {
							sheet.getRow(y + 1).createCell(x).setCellStyle(cs);
						}
						break;
					case 13:
						if (brand != null) {
							sheet.getRow(y + 1).createCell(x)
									.setCellValue(brand);
							sheet.getRow(y + 1).getCell(x).setCellStyle(cs);
						} else {
							sheet.getRow(y + 1).createCell(x).setCellStyle(cs);
						}
						break;
					case 14:
						if (manufacturer != null) {
							sheet.getRow(y + 1).createCell(x)
									.setCellValue(manufacturer);
							sheet.getRow(y + 1).getCell(x).setCellStyle(cs);
						} else {
							sheet.getRow(y + 1).createCell(x).setCellStyle(cs);
						}
						break;
					case 15:
						if (unitsId != null) {
							sheet.getRow(y + 1).createCell(x)
									.setCellValue(unitsId);
							sheet.getRow(y + 1).getCell(x).setCellStyle(cs);
						} else {
							sheet.getRow(y + 1).createCell(x).setCellStyle(cs);
						}
						break;
					case 16:
						if (unitsName != null) {
							sheet.getRow(y + 1).createCell(x)
									.setCellValue(unitsName);
							sheet.getRow(y + 1).getCell(x).setCellStyle(cs);
						} else {
							sheet.getRow(y + 1).createCell(x).setCellStyle(cs);
						}
						break;
					case 17:
						if (addwaysId != null) {
							sheet.getRow(y + 1).createCell(x)
									.setCellValue(addwaysId);
							sheet.getRow(y + 1).getCell(x).setCellStyle(cs);
						} else {
							sheet.getRow(y + 1).createCell(x).setCellStyle(cs);
						}
						break;
					case 18:
						if (addwaysName != null) {
							sheet.getRow(y + 1).createCell(x)
									.setCellValue(addwaysName);
							sheet.getRow(y + 1).getCell(x).setCellStyle(cs);
						} else {
							sheet.getRow(y + 1).createCell(x).setCellStyle(cs);
						}
						break;
					case 19:
						if (methodId != null) {
							sheet.getRow(y + 1).createCell(x)
									.setCellValue(methodId);
							sheet.getRow(y + 1).getCell(x).setCellStyle(cs);
						} else {
							sheet.getRow(y + 1).createCell(x).setCellStyle(cs);
						}
						break;
					case 20:
						if (methodName != null) {
							sheet.getRow(y + 1).createCell(x)
									.setCellValue(methodName);
							sheet.getRow(y + 1).getCell(x).setCellStyle(cs);
						} else {
							sheet.getRow(y + 1).createCell(x).setCellStyle(cs);
						}
						break;
				/*	case 20:
						if (currency != null) {
							sheet.getRow(y + 1).createCell(x)
									.setCellValue(currency);
							sheet.getRow(y + 1).getCell(x).setCellStyle(cs);
						} else {
							sheet.getRow(y + 1).createCell(x).setCellStyle(cs);
						}
						break;*/
					case 21:
						if (addTime != null) {
							sheet.getRow(y + 1).createCell(x)
									.setCellValue(addTime);
							sheet.getRow(y + 1).getCell(x).setCellStyle(cs);
						} else {
							sheet.getRow(y + 1).createCell(x).setCellStyle(cs);
						}
						break;
					case 22:
						if (checkDate != null) {
							sheet.getRow(y + 1).createCell(x)
									.setCellValue(checkDate);
							sheet.getRow(y + 1).getCell(x).setCellStyle(cs);
						} else {
							sheet.getRow(y + 1).createCell(x).setCellStyle(cs);
						}
						break;
					case 23:
						if(keeper!=null){
							sheet.getRow(y+1).createCell(x).setCellValue(keeper);
							sheet.getRow(y+1).getCell(x).setCellStyle(cs);
						}else{
							sheet.getRow(y+1).createCell(x).setCellStyle(cs);
						}
						break;
					case 24:
						if(lostMk!=null){
							if(lostMk.equals("Y")){
								sheet.getRow(y+1).createCell(x).setCellValue("報廢");
							}
							if(lostMk.equals("N")){
								sheet.getRow(y+1).createCell(x).setCellValue("正常");
							}
							if(lostMk.equals("N1")){
								sheet.getRow(y+1).createCell(x).setCellValue("使用中");
							}
							if(lostMk.equals("N2")){
								sheet.getRow(y+1).createCell(x).setCellValue("出售");
							}
							if(lostMk.equals("N3")){
								sheet.getRow(y+1).createCell(x).setCellValue("閒置");
							}
							sheet.getRow(y+1).getCell(x).setCellStyle(cs);
						}else{
							sheet.getRow(y+1).createCell(x).setCellStyle(cs);
						}
						break;
					case 25:
						if(areaName!=null){
							sheet.getRow(y+1).createCell(x).setCellValue(areaName);
							sheet.getRow(y+1).getCell(x).setCellStyle(cs);
						}else{
							sheet.getRow(y+1).createCell(x).setCellStyle(cs);
						}																		
						break;
						
					case 26:
						if (delMk != null) {
							if (delMk.equals("Y")) {
								sheet.getRow(y + 1).createCell(x)
										.setCellValue("是");
							} else {
								sheet.getRow(y + 1).createCell(x)
										.setCellValue("否");
							}
							sheet.getRow(y + 1).getCell(x).setCellStyle(cs);
						} else {
							sheet.getRow(y + 1).createCell(x).setCellStyle(cs);
						}
						break;
					case 27:
						if(lostmk_temp!=null){
							if(lostmk_temp.equals("Y")){
								sheet.getRow(y+1).createCell(x).setCellValue("已報廢");
							}
							if(lostmk_temp.equals("N")){
								sheet.getRow(y+1).createCell(x).setCellValue("未報廢");
							}
							if(lostmk_temp.equals("N1")){
								sheet.getRow(y+1).createCell(x).setCellValue("使用中");
							}
							if(lostmk_temp.equals("N2")){
								sheet.getRow(y+1).createCell(x).setCellValue("出售");
							}
							if(lostmk_temp.equals("N3")){
								sheet.getRow(y+1).createCell(x).setCellValue("閒置");
							}
							sheet.getRow(y+1).getCell(x).setCellStyle(cs);
						}else{
							sheet.getRow(y+1).createCell(x).setCellStyle(cs);
						}
						break;

					}

				}// end "for 2"
			}// end "for 1"

			// 写文件
			wb.write(os);
			// 关闭输出流
			os.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String formatDouble(double s) {
		DecimalFormat format = new DecimalFormat(",###.##");
		String temp = format.format(s);
		return temp;
		// return temp.replace(",", "");
	}

	public String formatDouble2(double s) {
		DecimalFormat format = new DecimalFormat("#.##");
		String temp = format.format(s);
		return temp;
	}

}
