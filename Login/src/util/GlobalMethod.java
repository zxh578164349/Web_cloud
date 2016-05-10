package util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.struts2.ServletActionContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import services.IKyzExpectmatmLogServices;

import com.opensymphony.xwork2.ActionContext;

import entity.KyzExpectmatmLog;
import entity.WebUser;


public class GlobalMethod extends HibernateDaoSupport{
	

	public static void print(List list,String factNo,String yymm,String yymm2,String file,HttpServletResponse response) throws IOException{
		//List<Webwlo>list=wloService.findByAny(factNo, yymm, yymm2);
		if(list.size()>0){
			StringBuffer fileName=new StringBuffer();
			Map<String,Object>map=new HashMap<String,Object>();
			map.put("factNo", factNo);
			fileName.append("report");
			if(factNo!=null&&!factNo.equals("")){
				fileName.append("_"+factNo);
			}
			if(yymm!=null&&!yymm.equals("")){
				fileName.append("_"+yymm);
			}
			if(yymm2!=null&&!yymm2.equals("")){
				fileName.append("_"+yymm2);
			}
			JasperHelper.exportmain("excel", map,file, list,fileName.toString(), "jasper/input/");
		}else{
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>alert('無數據');</script>");
		}
	}
	
	/**
	 * kpi目標搜索打印
	 * @param list
	 * @param factNo
	 * @param yymm
	 * @param yymm2
	 * @param file
	 * @param response
	 * @throws IOException
	 */
	public static void print_kpifact(List list,String factNo,String yyyy,String file,HttpServletResponse response) throws IOException{
		if(list.size()>0){
			StringBuffer fileName=new StringBuffer();
			Map<String,Object>map=new HashMap<String,Object>();
			fileName.append("KPI_pur_report");
			map.put("factNO", factNo);
			map.put("yyyy", yyyy);
			JasperHelper.exportmain("excel", map, file, list, fileName.toString(), "jasper/input/");
		}else{
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>alert('無數據');</script>");
		}
	}
	
	/**
	 * 實驗室物性搜索打印
	 * @param list
	 * @param factNo
	 * @param yymm
	 * @param yymm2
	 * @param file
	 * @param response
	 * @throws IOException
	 */
	public static void print_webtabpom(List list,String pomName,String brank,String yymm,String yymm2,String file,HttpServletResponse response) throws IOException{
		//List<Webwlo>list=wloService.findByAny(factNo, yymm, yymm2);
		if(list.size()>0){
			StringBuffer fileName=new StringBuffer();
			Map<String,Object>map=new HashMap<String,Object>();
			map.put("brank", brank);
			fileName.append("report");
			if(brank!=null&&!brank.equals("")){
				fileName.append("_"+brank);
			}
			if(yymm!=null&&!yymm.equals("")){
				fileName.append("_"+yymm);
			}
			if(yymm2!=null&&!yymm2.equals("")){
				fileName.append("_"+yymm2);
			}
			JasperHelper.exportmain("excel", map,file, list,fileName.toString(), "jasper/webtabpom/");
		}else{
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>alert('無數據');</script>");
		}
	}
	/**
	 * 工厂订单搜索打印
	 * @param list
	 * @param factNo
	 * @param yymm
	 * @param yymm2
	 * @param file
	 * @param response
	 * @throws IOException
	 */
	public static void print_webfactorder(List list,String file,String yymm,HttpServletResponse response) throws IOException{
		if(list.size()>0){
			StringBuffer fileName=new StringBuffer();
			Map<String,Object>map=new HashMap<String,Object>();
			Map<String,Object>sub_map=new HashMap<String,Object>();
			sub_map.put("sub_list", list);
			map.put("sub_map",sub_map );
			map.put("yymm", yymm);
			fileName.append("report");
			map.put("SUBREPORT_DIR",ServletActionContext.getRequest().getRealPath("/jasper/input/")+ "/");
			JasperHelper.exportmain("excel", map,file, list,fileName.toString(), "jasper/input/");
		}else{
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>alert('無數據');</script>");
		}
	}
	/**
	 * 實驗室物性搜索打印
	 * @param list
	 * @param factNo
	 * @param yymm
	 * @param yymm2
	 * @param file
	 * @param response
	 * @throws IOException
	 */
	public static void print_webphonebook(List list,String factNo,String department,String file,HttpServletResponse response) throws IOException{
		//List<Webwlo>list=wloService.findByAny(factNo, yymm, yymm2);
		if(list.size()>0){
			StringBuffer fileName=new StringBuffer();
			Map<String,Object>map=new HashMap<String,Object>();
			map.put("factNo", factNo);
			fileName.append("report");
			if(factNo!=null&&!factNo.equals("")){
				fileName.append("_"+factNo);
			}
			if(department!=null&&!department.equals("")){
				fileName.append("_"+department);
			}
			JasperHelper.exportmain("excel", map,file, list,fileName.toString(), "jasper/input/");
		}else{
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>alert('無數據');</script>");
		}
	}
	
	
	public static String findPageBean(String public_factno,String yymm,String yymm2){
		ActionContext.getContext().getSession().remove("public_factno");
		ActionContext.getContext().getSession().remove("public_yymm");
		ActionContext.getContext().getSession().remove("public_yymm2");
		public_factno=(String) ActionContext.getContext().getSession().get("factNo");//用戶登錄時記錄的廠別
		return public_factno;
	}
	public static void findPageBean2(String public_factno,String yymm,String yymm2){
		ActionContext.getContext().getSession().remove("public_factno");
		ActionContext.getContext().getSession().remove("public_yymm");
		ActionContext.getContext().getSession().remove("public_yymm2");
		if (public_factno != null && !public_factno.equals("") && !public_factno.equals("tw")) {
			ActionContext.getContext().getSession().put("public_factno", public_factno);					
		}
		if (yymm != null && !yymm.equals("")) {
			ActionContext.getContext().getSession().put("public_yymm", yymm);
		}
		if (yymm2 != null && !yymm2.equals("")) {
			ActionContext.getContext().getSession().put("public_yymm2", yymm2);
		}
	}
	public static String[] findPageBean3(String public_factno,String yymm,String yymm2){
		public_factno = (String) ActionContext.getContext().getSession().get("public_factno");				
		yymm = (String) ActionContext.getContext().getSession().get("public_yymm");
		yymm2 = (String) ActionContext.getContext().getSession().get("public_yymm2");
		if (public_factno == null || public_factno.equals("") || public_factno.equals("tw")) {
			public_factno = (String) ActionContext.getContext().getSession().get("factNo");					
		}
		String[]objs={public_factno,yymm,yymm2};
		return objs;
	}
	
	/**
	 * 計算兩箇Date之間相差的天數
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static long sumDate(Date d1,Date d2){
		Calendar c1=Calendar.getInstance();
		Calendar c2=Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		long result=(c2.getTimeInMillis()-c1.getTimeInMillis())/(24*60*60*1000);
		return result;
	}
	
	/**
	 * 計算兩箇日期之間的所有日期
	 * @param yymm
	 * @param yymm2
	 * @return
	 */
	public static List<String>getDateNum(String yymm,String yymm2){
		List<String>list=new ArrayList<String>();
		DateFormat fmt=new SimpleDateFormat("yyyyMM");
		try {
			Date date1 = fmt.parse(yymm);
			Date date2=fmt.parse(yymm2);
			Calendar cal=Calendar.getInstance();
			while(date1.before(date2)||date1.equals(date2)){
				cal.setTime(date1);
				list.add(fmt.format(cal.getTime()));
				cal.add(Calendar.MONTH, 1);
				date1=cal.getTime();
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 分解數據subList
	 * 20160201
	 * @param list
	 * @param size
	 * @return
	 */
	public static List<List<String>>subList(List<String>list,int size){
		List<List<String>>list_all=new ArrayList<List<String>>();
		for(int i=0;i<=list.size();i++){
			if(list.size()%size==0){
				if(i%size==0&&i>0){
					List<String>list_one=new ArrayList<String>(list.subList(i-size, i));
					list_all.add(list_one);
				}				
			}else{
				if(i%size==0&&i>0){
					List<String>list_one=new ArrayList<String>(list.subList(i-size, i));
					list_all.add(list_one);
				}else if(i==(list.size()/size)*size+1){
					List<String>list_one=new ArrayList<String>(list.subList(i-1, list.size()));	
					list_all.add(list_one);
				}				
			}			
		}
		return list_all;
	}
	
	
	 public static int getIndex(int a_1, int[] b) {
	        int temp = 1;
	        if(a_1 == b[0]) {
	            return temp;
	        }
	        for(int i=temp; i<b.length; i++) {
	            if(a_1 == b[i]) {
	                return ++temp;
	            }else {
	                if(b[i-1] == b[i]) {
	                     
	                }else {
	                    temp ++;
	                }                
	            }
	        }
	        return 0;        
	    }
	 public static int getIndex(BigDecimal a_1, List<BigDecimal>list) {
	        int temp = 1;
	        if(a_1.compareTo(list.get(0))==0) {
	            return temp;
	        }
	        for(int i=temp; i<list.size(); i++) {
	            if(a_1.compareTo(list.get(i))==0) {
	                return ++temp;
	            }else {
	                if(list.get(i-1).compareTo(list.get(i)) ==0 ) {
	                     
	                }else {
	                    temp ++;
	                }                
	            }
	        }
	        return 0;        
	    }

	
	/**
	 * 刪除文件夾
	 * @param file
	 */
	public static void deletefile(File file){
		if(file.isFile()){
			file.delete();
		}
		if(file.isDirectory()){
			File[]files=file.listFiles();
			for(int i=0;i<files.length;i++){
				deletefile(files[i]);
			}
			file.delete();
		}
		
	}
	
	/**
	 * 數據刪除記錄(失敗)
	 * @Title: deleteLog
	 * @Description: TODO
	 * @param 
	 * @return void
	 * @throws
	 * @author web
	 * @date 2016/2/25
	 */
	public static void deleteLog (String title){
		/*********************刪除記錄**************************/
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setBillNo(title);
		log.setDeldate(new Date());
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		log.setUsername(user.getUsername());
		//kyzExpLogSer.add(log);
	}
	
	/**
	 * 計算兩箇日期之間的月數
	 * @Title: findMonths
	 * @Description: TODO
	 * @param @param yymm
	 * @param @param yymm2
	 * @param @return
	 * @return List<String>
	 * @throws ParseException 
	 * @throws
	 * @author web
	 * @date 2016/4/6
	 */
	public static List<String> findMonths(String yymm,String yymm2) throws ParseException{
		List<String>list=new ArrayList<String>();
		if(yymm2==null||yymm2.equals("")){
			list.add(yymm);
		}else{
			DateFormat frm=new SimpleDateFormat("yyyyMM");
			Calendar cal=Calendar.getInstance();
			Calendar cal2=Calendar.getInstance();
			cal.setTime(frm.parse(yymm));
			cal2.setTime(frm.parse(yymm2));
			while(cal.getTime().getTime()<=cal2.getTime().getTime()){
				list.add(frm.format(cal.getTime()));
				cal.add(Calendar.MONTH, 1);
			}
		}		
		return list;
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
	public static Map<String,Object> findStyles(HSSFWorkbook wb){
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
		
		//紅色粗字體樣式
		HSSFCellStyle cs_red=wb.createCellStyle();
		cs_red.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_red.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		
		cs_red.setFont(font_red);
		map.put("cs_red", cs_red);
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
	 * 避免除數為0的方法
	 * @param d1
	 * @param d2
	 * @return
	 */
	public Double division(Double d1,Double d2){
		Double db=0.00;
		if(d2!=0.00){
			db=d1/d2;
		}
		return db;
	}
	
	public static void main(String[] args) throws ParseException {
		Integer[] a = {0,3,0,4,5};//原始
        int[] b = new int[a.length];//有序
        int[] c = new int[a.length];//返回結果
        /*for(int i=0; i<a.length; i++) {
            b[i] = a[i];
            System.out.print(b[i] + "\t");
        }
        System.out.println();
        Arrays.sort(b);
        for(int i=0; i<a.length; i++) {
            System.out.print(b[i] + "\t");
        }
        System.out.println();
        for(int i=0; i<a.length; i++) {
            c[i] = getIndex(a[i], b);
            System.out.print(c[i] + "\t");
        }
        
        
        System.out.println("-------------------------------------------------------");
        DateFormat frm=new SimpleDateFormat("yyyyMM");
        Calendar cal=Calendar.getInstance();
        cal.setTime(frm.parse("201604"));
        cal.add(Calendar.MONTH, -1);
        System.out.println(frm.format(cal.getTime()));*/
        
        BigDecimal aa=new BigDecimal(2);
        BigDecimal bb=new BigDecimal(4);
        BigDecimal cc=new BigDecimal(6);
        BigDecimal dd=new BigDecimal(8);
        BigDecimal ff=new BigDecimal(0);
        System.out.println(aa.divide(bb));
        System.out.println(cc.divide(aa).subtract(dd.divide(bb)));
        //System.out.println(aa.divide(ff));
        

       
						
	}

}
