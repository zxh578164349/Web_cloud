/**
 * 
 */
package action;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

import entity.VWebuseless;
import entity.VWebuselessId;
import entity.WebFact;

import services.IVWebuselessServices;
import services.IWebFactServices;
import util.GlobalMethod;

/**   
 *    
 * 项目名称：Login   
 * 类名称：VWebuselessAction   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/13 下午2:57:08   
 * 修改人：Administrator   
 * 修改时间：2016/4/13 下午2:57:08   
 * 修改备注：   
 * @version    
 *    
 **/
public class VWebuselessAction extends ActionSupport implements ServletResponseAware{
	private IVWebuselessServices vwebuselessSer;
	private IWebFactServices webFactSer;
	private String yymm;
	private String yymm2;//下月日期
	private String yymm3;//上月日期
	private javax.servlet.http.HttpServletResponse response;

	
	public String getYymm3() {
		return yymm3;
	}

	public void setYymm3(String yymm3) {
		this.yymm3 = yymm3;
	}

	public String getYymm2() {
		return yymm2;
	}

	public void setYymm2(String yymm2) {
		this.yymm2 = yymm2;
	}

	public String getYymm() {
		return yymm;
	}

	public void setYymm(String yymm) {
		this.yymm = yymm;
	}

	public void setVwebuselessSer(IVWebuselessServices vwebuselessSer) {
		this.vwebuselessSer = vwebuselessSer;
	}

	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}
	
	/**
	 * 日期:2016/4/18
	 * 描述:
	 */	
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	
	public void print() throws ParseException, IOException{	
		HSSFWorkbook wb=new HSSFWorkbook();
		Map<String,Object>map=findStyles(wb);
		Calendar cal=Calendar.getInstance();
		DateFormat frm=new SimpleDateFormat("yyyyMM");
		cal.setTime(frm.parse(yymm));
		cal.add(Calendar.MONTH, -1);
		yymm3=frm.format(cal.getTime());//上月日期		
		List<VWebuseless>list_all=new ArrayList<VWebuseless>();
		List<VWebuseless>list_all2=new ArrayList<VWebuseless>();
		List<WebFact>list_facts=webFactSer.findFactAble();
		List<VWebuseless>lists=vwebuselessSer.findByYymm(yymm);
		List<VWebuseless>lists2=vwebuselessSer.findByYymm(yymm3);
		
		/*************************數據源********************************/
		for(WebFact fact:list_facts){
			list_all.add(new VWebuseless(new VWebuselessId(fact.getId().getFactNo(),fact.getId().getFactArea(),yymm)));
			list_all2.add(new VWebuseless(new VWebuselessId(fact.getId().getFactNo(),fact.getId().getFactArea(),yymm3)));
		}
		//當月數據
		for(int a=0;a<list_all.size();a++){
			for(VWebuseless less_a:lists){
				if(list_all.get(a).getId().getFactNo().equals(less_a.getId().getFactNo())&&list_all.get(a).getId().getFactCode().equals(less_a.getId().getFactCode())){
					list_all.remove(a);
					list_all.add(a,less_a);
				}
			}
		}
		//上月數據
		for(int b=0;b<list_all2.size();b++){
			for(VWebuseless less_a:lists2){
				if(list_all2.get(b).getId().getFactNo().equals(less_a.getId().getFactNo())&&list_all2.get(b).getId().getFactCode().equals(less_a.getId().getFactCode())){
					list_all2.remove(b);
					list_all2.add(b,less_a);
				}
			}
		}
		
		//各實體封裝到list<Double>
		List<List<BigDecimal>>list_all_db=new ArrayList<List<BigDecimal>>();
		VWebuseless total_obj=new VWebuseless();//本月統計
		VWebuseless total_obj2=new VWebuseless();//上月統計
		List<BigDecimal>list_total=new ArrayList<BigDecimal>();//本月統計
		List<BigDecimal>list_total2=new ArrayList<BigDecimal>();//上月統計
		List<BigDecimal>list_total3=new ArrayList<BigDecimal>();//差異統計
		for (int a=0;a<list_all.size();a++) {
			List<BigDecimal>list_obj=new ArrayList<BigDecimal>();
			list_obj.add(list_all.get(a).getWebA1());
			list_obj.add(list_all.get(a).getWebA2());
			list_obj.add(list_all.get(a).getWebA3());
			list_obj.add(list_all.get(a).getWebA4());
			list_obj.add(new BigDecimal(list_all.get(a).getWebA5()));
			list_obj.add(list_all.get(a).getWebA6());
			list_obj.add(new BigDecimal(list_all.get(a).getWebA7()));
			list_obj.add(new BigDecimal(list_all.get(a).getWebA8()));
			list_obj.add(list_all.get(a).getWebA9());
			list_obj.add(list_all.get(a).getWebA10());
			
			list_obj.add(list_all2.get(a).getWebA10());//注意，這裏是上月的無用率
			list_obj.add(list_all.get(a).getWebA10().subtract(list_all2.get(a).getWebA10()));//當月減上月的無用率
			list_all_db.add(list_obj);
			
			total_obj.setWebA1(total_obj.getWebA1().add(list_all.get(a).getWebA1()));
			total_obj.setWebA2(total_obj.getWebA2().add(list_all.get(a).getWebA2()));
			total_obj.setWebA3(total_obj.getWebA3().add(list_all.get(a).getWebA3()));
			total_obj.setWebA4(total_obj.getWebA4().add(list_all.get(a).getWebA4()));
			total_obj.setWebA5(total_obj.getWebA5()+list_all.get(a).getWebA5());
			total_obj.setWebA6(total_obj.getWebA6().add(list_all.get(a).getWebA6()));
			total_obj.setWebA7(total_obj.getWebA7()+list_all.get(a).getWebA7());
			total_obj.setWebA8(total_obj.getWebA8()+list_all.get(a).getWebA8());
			total_obj.setWebA9(total_obj.getWebA9().add(list_all.get(a).getWebA9()));			
			
			total_obj2.setWebA1(total_obj2.getWebA1().add(list_all2.get(a).getWebA1()));
			total_obj2.setWebA2(total_obj2.getWebA2().add(list_all2.get(a).getWebA2()));
			total_obj2.setWebA3(total_obj2.getWebA3().add(list_all2.get(a).getWebA3()));
			total_obj2.setWebA4(total_obj2.getWebA4().add(list_all2.get(a).getWebA4()));
			total_obj2.setWebA5(total_obj2.getWebA5()+list_all2.get(a).getWebA5());
			total_obj2.setWebA6(total_obj2.getWebA6().add(list_all2.get(a).getWebA6()));
			total_obj2.setWebA7(total_obj2.getWebA7()+list_all2.get(a).getWebA7());
			total_obj2.setWebA8(total_obj2.getWebA8()+list_all2.get(a).getWebA8());
			total_obj2.setWebA9(total_obj2.getWebA9().add(list_all2.get(a).getWebA9()));			
			
			//添加統計
			if(a==list_all.size()-1){//if
				//添加本月統計
				list_total.add(total_obj.getWebA1());
				list_total.add(total_obj.getWebA2());
				list_total.add(total_obj.getWebA3());
				list_total.add(total_obj.getWebA4());
				list_total.add(new BigDecimal(total_obj.getWebA5()));
				list_total.add(total_obj.getWebA6());
				list_total.add(new BigDecimal(total_obj.getWebA7()));
				list_total.add(new BigDecimal(total_obj.getWebA8()));
				list_total.add(total_obj.getWebA9());
				list_total.add(total_obj.getWebA9().divide(total_obj.getWebA1(),4,BigDecimal.ROUND_HALF_UP));
				list_total.add(null);
				list_total.add(null);
				list_all_db.add(list_total);
				
				//添加上月統計
				list_total2.add(total_obj2.getWebA1());
				list_total2.add(total_obj2.getWebA2());
				list_total2.add(total_obj2.getWebA3());
				list_total2.add(total_obj2.getWebA4());
				list_total2.add(new BigDecimal(total_obj2.getWebA5()));
				list_total2.add(total_obj2.getWebA6());
				list_total2.add(new BigDecimal(total_obj2.getWebA7()));
				list_total2.add(new BigDecimal(total_obj2.getWebA8()));
				list_total2.add(total_obj2.getWebA9());
				list_total2.add(total_obj2.getWebA9().divide(total_obj2.getWebA1(),4,BigDecimal.ROUND_HALF_UP));
				list_total2.add(null);
				list_total2.add(null);
				list_all_db.add(list_total2);	
				
				//差異率統計
				list_total3.add(total_obj.getWebA1().subtract(total_obj2.getWebA1()));
				list_total3.add(total_obj.getWebA2().subtract(total_obj2.getWebA2()));
				list_total3.add(total_obj.getWebA3().subtract(total_obj2.getWebA3()));
				list_total3.add(total_obj.getWebA4().subtract(total_obj2.getWebA4()));
				list_total3.add(new BigDecimal(total_obj.getWebA5()-total_obj2.getWebA5()));
				list_total3.add(total_obj.getWebA6().subtract(total_obj2.getWebA6()));
				list_total3.add(new BigDecimal(total_obj.getWebA7()-total_obj2.getWebA7()));
				list_total3.add(new BigDecimal(total_obj.getWebA8()-total_obj2.getWebA8()));
				list_total3.add(total_obj.getWebA9().subtract(total_obj2.getWebA9()));
				list_total3.add(total_obj.getWebA9().divide(total_obj.getWebA1(),4,BigDecimal.ROUND_HALF_UP).subtract((total_obj2.getWebA9().divide(total_obj2.getWebA1(),4,BigDecimal.ROUND_HALF_UP))));
				list_total3.add(null);
				list_total3.add(null);
				list_all_db.add(list_total3);
			}//if	
		}				
		/*************************數據源********************************/
		
		
		/*************************初始化報表********************************/
		init(wb,list_facts,map,yymm,yymm2);
		/*************************初始化報表********************************/
		
		for(int a=0;a<list_all_db.size();a++){
			for(int b=0;b<list_all_db.get(a).size();b++){
				if(list_all_db.get(a).get(b)==null){
					wb.getSheet(yymm).getRow(a+2).getCell(b+1).setCellValue("--");
				}else{
					wb.getSheet(yymm).getRow(a+2).getCell(b+1).setCellValue(list_all_db.get(a).get(b).doubleValue());
				}				
				wb.getSheet(yymm).getRow(a+2).getCell(b+1).setCellStyle(this.findStyleOne(map, b));
			}
		}
								
		
			/*OutputStream os = new FileOutputStream("E:/" + "webless.xls");
			wb.write(os);
			os.close();	*/
			ServletOutputStream os=response.getOutputStream();
			response.setContentType("application/vnd.ms-excel");
			int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");//判斷是否為IE瀏覽器,大於0則為IE瀏覽器
			String fileName="report"+"_"+yymm+".xls";
			if(msie>0){
				fileName=java.net.URLEncoder.encode(fileName,"utf-8");//解決IE中文文件不能下載的問題
			}else{
				fileName=new String(fileName.getBytes("utf-8"),"iso-8859-1");//解決非IE中文名亂碼問題
			}		
			response.setHeader("Content-disposition", "attachment;filename="+fileName);					
			wb.write(os);
			os.close();								
	}
	
	
	
	/**
	 * 所有樣式
	 * @Title: findStyles
	 * @Description: TODO
	 * @param @param wb
	 * @param @return
	 * @return Map<String,Object>
	 * @throws
	 * @author web
	 * @date 2016/4/6
	 */
	public Map<String,Object> findStyles(HSSFWorkbook wb){
		/**
		 * 報表相關樣式
		 */
		Map<String,Object>map=new HashMap<String,Object>();
		//標題樣式
		HSSFCellStyle cs_head=wb.createCellStyle();
		HSSFFont font_head=wb.createFont();
		font_head.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_head.setFontHeightInPoints((short)20);
		cs_head.setFont(font_head);
		cs_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_head.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		map.put("cs_head", cs_head);
		
		//標準單元格樣式
		HSSFCellStyle cs=wb.createCellStyle();
		cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		map.put("cs", cs);
		//表頭樣式
		HSSFCellStyle cs_column=wb.createCellStyle();
		HSSFFont font_column=wb.createFont();
		font_column.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_column.setFontHeightInPoints((short)12);
		cs_column.setFont(font_column);
		cs_column.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_column.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_column.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_column.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_column.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_column.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_column.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		cs_column.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		map.put("cs_column", cs_column);
		
		
		
		//紅色加粗字體
		HSSFFont font_red=wb.createFont();
		font_red.setColor(IndexedColors.RED.getIndex());
		font_red.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		/**********************分類+項目+單位*****************************/
		HSSFFont font_bold = wb.createFont();
		font_bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		//font_bold.setFontHeightInPoints((short) 10);
		// 藍色背景粗字體
		HSSFCellStyle cs_blue = wb.createCellStyle();
		cs_blue.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_blue.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_blue.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_blue.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_blue.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_blue.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_blue.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		cs_blue.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cs_blue.setFont(font_bold);
		map.put("cs_blue", cs_blue);
		// 標準粗字體樣式
		HSSFCellStyle cs_bold = wb.createCellStyle();
		cs_bold.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_bold.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_bold.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_bold.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_bold.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_bold.setBorderLeft(HSSFCellStyle.BORDER_THIN);		
		cs_bold.setFont(font_bold);
		map.put("cs_bold", cs_bold);
		/**********************分類+項目+單位*****************************/
		
		/**
		 * 數字格式（有背景顏色與無背景顏色）
		 */
		
		HSSFDataFormat format=wb.createDataFormat();
		//無背景
		HSSFCellStyle cs_percent=wb.createCellStyle();
		cs_percent.setDataFormat(format.getFormat("0.00%"));
		cs_percent.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_percent.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_percent.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		map.put("cs_percent", cs_percent);
		
		HSSFCellStyle cs_poi=wb.createCellStyle();
		cs_poi.setDataFormat(format.getFormat("#,###,0"));
		cs_poi.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		map.put("cs_poi", cs_poi);
		
		HSSFCellStyle cs_poi1=wb.createCellStyle();
		cs_poi1.setDataFormat(format.getFormat("#,###,0.0"));
		cs_poi1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		map.put("cs_poi1", cs_poi1);
		
		HSSFCellStyle cs_poi2=wb.createCellStyle();
		cs_poi2.setDataFormat(format.getFormat("#,###,0.00"));
		cs_poi2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		map.put("cs_poi2", cs_poi2);
		
		HSSFCellStyle cs_poi4=wb.createCellStyle();
		cs_poi4.setDataFormat(format.getFormat("#,###,0.0000"));
		cs_poi4.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi4.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		map.put("cs_poi4", cs_poi4);
		//有背景
		HSSFCellStyle cs_percent_bg=wb.createCellStyle();
		cs_percent_bg.setDataFormat(format.getFormat("0.00%"));
		cs_percent_bg.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_percent_bg.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_percent_bg.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_percent_bg.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_percent_bg.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_percent_bg.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		//cs_percent_bg.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		//cs_percent_bg.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cs_percent_bg.setFont(font_red);
		map.put("cs_percent_bg", cs_percent_bg);
		
		
		HSSFCellStyle cs_poi_bg=wb.createCellStyle();
		cs_poi_bg.setDataFormat(format.getFormat("#,###,0"));
		cs_poi_bg.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi_bg.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi_bg.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi_bg.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi_bg.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi_bg.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		//cs_poi_bg.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		//cs_poi_bg.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cs_poi_bg.setFont(font_red);
		map.put("cs_poi_bg", cs_poi_bg);
		
		HSSFCellStyle cs_poi1_bg=wb.createCellStyle();
		cs_poi1_bg.setDataFormat(format.getFormat("#,###,0.0"));
		cs_poi1_bg.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi1_bg.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi1_bg.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi1_bg.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi1_bg.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi1_bg.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		//cs_poi1_bg.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		//cs_poi1_bg.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cs_poi1_bg.setFont(font_red);
		map.put("cs_poi1_bg", cs_poi1_bg);
		
		HSSFCellStyle cs_poi2_bg=wb.createCellStyle();
		cs_poi2_bg.setDataFormat(format.getFormat("#,###,0.00"));
		cs_poi2_bg.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi2_bg.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi2_bg.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi2_bg.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi2_bg.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi2_bg.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		//cs_poi2_bg.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		//cs_poi2_bg.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cs_poi2_bg.setFont(font_red);
		map.put("cs_poi2_bg", cs_poi2_bg);
		
		HSSFCellStyle cs_poi4_bg=wb.createCellStyle();
		cs_poi4_bg.setDataFormat(format.getFormat("#,###,0.0000"));
		cs_poi4_bg.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi4_bg.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi4_bg.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi4_bg.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi4_bg.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi4_bg.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		//cs_poi4_bg.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		//cs_poi4_bg.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cs_poi4_bg.setFont(font_red);
		map.put("cs_poi4_bg", cs_poi4_bg);
		return map;
	}
	
	/**
	 * 樣式選擇
	 * @Title: findStyleOne
	 * @Description: TODO
	 * @param @return
	 * @return HSSFCellStyle
	 * @throws
	 * @author web
	 * @date 2016/4/15
	 */
	public HSSFCellStyle findStyleOne(Map<String,Object>map,int index){
		HSSFCellStyle temp=(HSSFCellStyle)map.get("cs");
		if(index>8){
			temp=(HSSFCellStyle)map.get("cs_percent");
		}
		return temp;
	}
	public void init(HSSFWorkbook wb,List<WebFact>list_facts,Map<String,Object>map,String yymm,String yymm2) throws ParseException{
		//HSSFCellStyle cs=(HSSFCellStyle)map.get("cs");
		HSSFCellStyle cs_head=(HSSFCellStyle)map.get("cs_head");
		HSSFCellStyle cs_column=(HSSFCellStyle)map.get("cs_column");
		HSSFCellStyle cs_bold=(HSSFCellStyle)map.get("cs_bold");
		List<String>list_months=GlobalMethod.findMonths(yymm, yymm2);//所有的月份
		List<String>list_facts2=new ArrayList<String>();//所有的廠別			
		for(WebFact fact:list_facts){
			list_facts2.add(fact.getFactSname()+"("+fact.getId().getFactArea()+")");
		}
		list_facts2.add("本月合計");
		list_facts2.add("上月合計");
		list_facts2.add("差異-(A-B)");
		
		List<String>list_clms=new ArrayList<String>();
		list_clms.add("廠別型態");
		list_clms.add("生產雙數");
		list_clms.add("廠補雙數");
		list_clms.add("客補雙數");
		list_clms.add("樣品雙數");
		list_clms.add("報廢雙數");
		list_clms.add("合計");
		list_clms.add("客補請款雙數");
		list_clms.add("樣品請款雙數");
		list_clms.add("無用值雙數");
		list_clms.add("本月無用率");//index=10
		list_clms.add("上月無用率");
		list_clms.add("差異");
		
		for(String month:list_months){//for
			HSSFSheet sheet=wb.createSheet(month);
			for(int a=0;a<list_facts2.size()+2;a++){
				sheet.createRow(a);
				for(int b=0;b<list_clms.size();b++){
					sheet.getRow(a).createCell(b);
					sheet.setColumnWidth(b, 4500);
				}
			}
			sheet.getRow(0).getCell(0).setCellValue(yymm+"各廠無用值比較表");
			CellRangeAddress cra=new CellRangeAddress(0,0,0,6);
			sheet.addMergedRegion(cra);
			for(int a=0;a<6;a++){
				sheet.getRow(0).getCell(a).setCellStyle(cs_head);
			}
			for(int a=0;a<list_clms.size();a++){
				sheet.getRow(1).getCell(a).setCellValue(list_clms.get(a));
				sheet.getRow(1).getCell(a).setCellStyle(cs_column);
			}
			for(int a=0;a<list_facts2.size();a++){
				sheet.getRow(a+2).getCell(0).setCellValue(list_facts2.get(a));
				sheet.getRow(a+2).getCell(0).setCellStyle(cs_bold);
			}
			
		}//for
		

		
	}

	
	

}
