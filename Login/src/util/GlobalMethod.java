package util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletResponse;

import mail.MailSenderInfo;
import mail.SimpleMailSender;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import services.IKyVisabillmServices;
import services.IKyzExpectmatmLogServices;
import services.IKyzVisaFlowServices;
import services.IWebuserEmailAServices;
import services.IWebuserEmailServices;

import com.opensymphony.xwork2.ActionContext;

import entity.KyVisabillm;
import entity.KyVisabills;
import entity.KyzExpectmatmLog;
import entity.WebUser;
import entity.Webestproduct;
import entity.WebestproductId;


public class GlobalMethod extends HibernateDaoSupport{
	private static final String URL="http://203.85.73.161/Login/";
	

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
	public static Double division(Double d1,Double d2){
		Double db=0.00;
		if(d2!=0.00){
			db=d1/d2;
		}
		return db;
	}
	
	/**
	 * 查找本機IP
	 * @Title: findIp
	 * @Description: TODO
	 * @param @return
	 * @return String
	 * @throws
	 * @author web
	 * @date 2016/5/30
	 */
	public static String findIp(){
		String ipAddress = null; 
		try{			
			ipAddress =ServletActionContext.getRequest().getHeader("x-forwarded-for");									  
		      if(ipAddress == null || ipAddress.length() == 0 ||"unknown".equalsIgnoreCase(ipAddress)){ 									  
		          ipAddress =ServletActionContext.getRequest().getHeader("Proxy-Client-IP");									  
		         }
		      if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {									 
		          ipAddress =ServletActionContext.getRequest().getHeader("WL-Proxy-Client-IP");								  
		         } 
		      if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {									 
		          ipAddress =ServletActionContext.getRequest().getRemoteAddr(); 
		          if(ipAddress.equals("127.0.0.1")){
		             InetAddress inet =null;
		             try { 
		            	 inet = InetAddress.getLocalHost(); 
		             }catch (Exception e) { 
		            	 e.printStackTrace(); 
		            	 }
		              ipAddress = inet.getHostAddress();
		           } 
		        } 
		      if (ipAddress!= null && ipAddress.length() > 15) {
		           if (ipAddress.indexOf(",") > 0) { 
		        	   ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));						 
		                  }  
		      }
		}catch(Exception e){
			e.printStackTrace();
		}		
	      return ipAddress;
	}
	
	/**
	 * (quartz專用)獲取本機IP2
	 * @Title: findIp2
	 * @Description: TODO
	 * @param @return
	 * @return String[]
	 * @throws
	 * @author web
	 * @date 2016/5/30
	 */
	public static List<String> findIp2(){		
		  List<String> res = new ArrayList<String>();  
	        Enumeration netInterfaces;  
	        try {  
	            netInterfaces = NetworkInterface.getNetworkInterfaces();  
	            InetAddress ip = null;  
	            while (netInterfaces.hasMoreElements()) {  
	                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();  	                         
	                Enumeration nii = ni.getInetAddresses();  
	                while (nii.hasMoreElements()) {  
	                    ip = (InetAddress) nii.nextElement();  
	                    if (ip.getHostAddress().indexOf(":") == -1) {  
	                        res.add(ip.getHostAddress());  
	                        //System.out.println("本机的ip=" + ip.getHostAddress());  
	                    }  
	                }  
	            }  
	        } catch (SocketException e) {  
	            e.printStackTrace();  
	        }  
	        return res;
	        //return (String[]) res.toArray(new String[0]); 	        
	}
	
	
	
	/**
	 * 獲取物理地址
	 * @Title: getMacAddress
	 * @Description: TODO
	 * @param @return
	 * @return String
	 * @throws
	 * @author web
	 * @date 2016/5/30
	 */
	  public static String getMacAddress() {  
	        String mac = null;  
	        String line = "";  	  
	        String os = System.getProperty("os.name");  	  
	        if (os != null && os.startsWith("Windows")) {  
	            try {  
	                String command = "cmd.exe /c ipconfig /all";  
	                Process p = Runtime.getRuntime().exec(command);  	  
	                BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));   	                         	  
	                while ((line = br.readLine()) != null) {  
	                    if (line.indexOf("Physical Address") > 0||line.indexOf("實體位址")>0) {  
	                        int index = line.indexOf(":") + 2;  	  
	                        mac = line.substring(index);  	  
	                        break;  
	                    }  
	                }  	  
	                br.close();  	  
	            } catch (IOException e) {  
	            }  
	        }  	  
	        return mac;  
	    }  
	
	  /**
	   * 获取某月的所有天数
	   * @param yymm
	   * @return
	   * @throws ParseException
	   */
	  public static List<String> findDaysOfMonth(String yymm,String fmt) throws ParseException{
		  List<String>list=new ArrayList<String>();
		  Calendar cal=Calendar.getInstance();
		  cal.setTime(new SimpleDateFormat("yyyyMM").parse(yymm));
		  int maxnum=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		  for(int i=1;i<=maxnum;i++){
			  cal.set(Calendar.DAY_OF_MONTH, i);
			  list.add(new SimpleDateFormat(fmt).format(cal.getTime()));
		  }
		  return list;
	  }
	  		
	
	
	 public static void testTask(ExecutorService exec, int timeout) {  
	        MyTask task = new MyTask();  
	        Future<Boolean> future = exec.submit(task);  
	        Boolean taskResult = null;  
	        String failReason = null;  
	        try {  
	            // 等待计算结果，最长等待timeout秒，timeout秒后中止任务  
	            taskResult = future.get(timeout, TimeUnit.SECONDS);  
	        } catch (InterruptedException e) {  
	            failReason = "主线程在等待计算结果时被中断！";  
	        } catch (ExecutionException e) {  
	            failReason = "主线程等待计算结果，但计算抛出异常！";  
	        } catch (TimeoutException e) {  
	            failReason = "主线程等待计算结果超时，因此中断任务线程！";  
	            exec.shutdownNow();  
	        }  
	  
	        System.out.println("\ntaskResult : " + taskResult);  
	        System.out.println("failReason : " + failReason);  
	    } 
	 
	 
	 /**
	  * 函文審核通知email
	  * @Title: sendEmailA
	  * @Description: TODO
	  * @param @param ac
	  * @param @param list_vbm
	  * @return void
	  * @throws
	  * @author web
	  * @date 2016/6/22
	  */
	 public static void sendEmailA(ApplicationContext ac,List<KyVisabillm>list_vbm){		   
		    IWebuserEmailServices webuseremailSer=(IWebuserEmailServices)ac.getBean("webuseremailSer");
			IWebuserEmailAServices webuseremailaSer=(IWebuserEmailAServices)ac.getBean("webuseremailaSer");
			IKyzVisaFlowServices visaSer=(IKyzVisaFlowServices)ac.getBean("visaSer");
			IKyVisabillmServices visabillmSer=(IKyVisabillmServices)ac.getBean("visabillmSer");
		    String subject="";
			String result="";
			String content="";		
			if(list_vbm.size()>0){//start if
			MailSenderInfo mailInfo = new MailSenderInfo();
			MailSenderInfo mailInfo2 = new MailSenderInfo();
			SimpleMailSender sms = new SimpleMailSender();
			mailInfo.setValidate(true);
			mailInfo2.setValidate(true);	   
				for(int i=0;i<list_vbm.size();i++){// start for1	
					List<String>list_email=new ArrayList<String>();
					String signerNext=list_vbm.get(i).getSignerNext();
					String factNo=list_vbm.get(i).getId().getFactNo();
					String billNo=list_vbm.get(i).getId().getBillNo();
					String visaSort=list_vbm.get(i).getId().getVisaSort();
					String visaMk=list_vbm.get(i).getVisaMk();				
					list_email.add(signerNext);
					 /******************20151113备签人请使用方法findByFactNoAEmailPwd2(String factNo,String email)**********************/				
					/***************如果是臺灣加久，備簽人同時也是申請人，那麼根據流程代號找到申請人（也就是備簽人）*******************/
					if(factNo.equals("GJ")){
						String visaSinger=visaSer.findVisaSigner(factNo, visaSort);
						list_email.add(visaSinger);
					}
					List<String>list_emailPwd=webuseremailSer.findByFactNoAEmailPwd2(factNo, signerNext);
						if(list_emailPwd.size()>0){
							for(int j=0;j<list_emailPwd.size();j++){
								list_email.add(list_emailPwd.get(j));
							}
						}
						/******************20151113备签人请使用方法findByFactNoAEmailPwd2(String factNo,String email)**********************/
						
					/***************************************中途知會人的email20160217********************************************/
					List<String>list_emailPwd_a=webuseremailaSer.findByEmail(factNo, signerNext, visaSort);
					for(int k=0;k<list_emailPwd_a.size();k++){
						list_email.add(list_emailPwd_a.get(k));
					}
					/***************************************中途知會人的email20160217********************************************/
					
					list_email.add("kyuen@yydg.com.cn");
					String emailUrl=URL+"vbm_findById_email?visaSort="+visaSort+"&billNo="+billNo
					         +"&factNo="+factNo+"&email="+signerNext;
					String emailUrl2=URL+"vbm_findById_email2?visaSort="+visaSort+"&billNo="+billNo
					         +"&factNo="+factNo+"&email="+signerNext;
					if(visaMk.equals("N")){
						subject="函文審核定時通知_"+billNo+"("+factNo+")";
						content="函文單號:"+"<span style='color:red'>"+billNo+"</span>"+"&nbsp;&nbsp;廠別:"+factNo+
					    		  "<br/>點擊單號直接審核:<a href='"+emailUrl2+"'>"+billNo+"</a>(電腦適用)"+
					    		  "<br/>點擊單號直接審核:<a href='"+emailUrl+"'>"+billNo+"</a>(手機平板適用)"+				    		 
							      "<hr/>"+
					    		 result+"如需查詢以往單據請登錄加久網站:(云端)<a href='http://203.85.73.161/Login'>http://203.85.73.161/Login</a>" +		            
					      		"<br/>進入[KPI數據]--[函文審核]中查找對應單號審核"+			    		
					    		"<hr/>"+
					      		"<br/>本郵件定時自動發送,請勿回復!如需回復或者問題，請回复到kyinfo.lp@yydg.com.cn劉平!<br/>"+
					    		"<hr/>";
					}
					if(visaMk.equals("T")){
						////由於出差函文流程中可能不包括申請人， 所有需要從函文中獲取申請email 20160621
						if(list_vbm.get(i).getId().getBillNo().substring(0,2).equals("BM")){
							if(list_vbm.get(i).getWebbussletter().getUserEmail()!=null&&!list_vbm.get(i).getWebbussletter().getUserEmail().equals("")){
								list_email.add(list_vbm.get(i).getWebbussletter().getUserEmail());
							}
						}						
							subject="函文退回定時通知_"+billNo+"("+factNo+")";//退回函文隻發送一次，所以也要鎖定狀態emailMk	
							list_vbm.get(i).setEmailMk("Y");
							visabillmSer.add(list_vbm.get(i));
							content="函文單號:"+"<span style='color:red'>"+billNo+"</span>"+"&nbsp;&nbsp;"+"不通過，備註如下:"+
						    		  "<br/>"+
						    		  "<span style='color:red'>"+(list_vbm.get(i).getMemoMk()==null?"無備註":list_vbm.get(i).getMemoMk())+"</span>"+				    		 
								      "<hr/>"+
						    		 result+"詳情請登錄加久網站:(云端)<a href='http://203.85.73.161/Login'>http://203.85.73.161/Login</a>" +		            
						      		"<br/>進入[KPI數據]--[函文審核]中查找對應單號審核"+			    		
						    		"<hr/>"+
						      		"<br/>本郵件定時自動發送,請勿回復!如需回復或者問題，請回复到kyinfo.lp@yydg.com.cn劉平!<br/>"+
						    		"<hr/>";
					}
					for(int j=0;j<list_email.size();j++){//start for2
						  mailInfo.setToAddress(list_email.get(j));
						  if(list_email.get(j).equals("kyuen@yydg.com.cn")){						  
							  String content_msg=content+"下一箇簽核:<span style='color:blue'>"+signerNext+"</span>";
							  mailInfo.setContent(content_msg);					      
						  }else{
							  mailInfo.setContent(content);
						  }
					      mailInfo.setSubject(subject);    			       				    		  			           
					      sms.sendHtmlMail(mailInfo);//发送html格式
					}//end for2								
				}//end for1
			}//end if	
		 
	 }
	 
	 /**
	  * 函文審核完畢通知email
	  * @Title: sendEmailB
	  * @Description: TODO
	  * @param @param local_factNo
	  * @param @param local_billNo
	  * @param @param local_visaSort
	  * @param @param vbm2
	  * @param @param ac
	  * @return void
	  * @throws
	  * @author web
	  * @date 2016/6/22
	  */
	 public static void sendEmailB(String local_factNo,String local_billNo,String local_visaSort,KyVisabillm vbm2,ApplicationContext ac){
		 IWebuserEmailAServices webuseremailaSer=(IWebuserEmailAServices)ac.getBean("webuseremailaSer");/******知會人********/		
			List<KyVisabills>list_visa2=vbm2.getKyVisabillses();
			//这个类主要是设置邮件   
			List<String>list_emails=new ArrayList<String>();//所有發送人
			list_emails.add("kyuen@yydg.com.cn");
			//由於出差函文流程中可能不包括申請人， 所有需要從函文中獲取申請email 20160621
			if(vbm2.getId().getBillNo().substring(0,2).equals("BM")){
				if(vbm2.getWebbussletter().getUserEmail()!=null&&!vbm2.getWebbussletter().getUserEmail().equals("")){
					list_emails.add(vbm2.getWebbussletter().getUserEmail());
				}
			}		
			for(KyVisabills bills:list_visa2){
				list_emails.add(bills.getVisaSigner());
				if(bills.getFlowMk().equals("Y")){//要簽核的人才需要通知知會人
					List<String>list_emailPwd=webuseremailaSer.findByEmail(local_factNo,bills.getVisaSigner(),local_visaSort);
					for(String str:list_emailPwd){
						list_emails.add(str);
					}
				}
			}
			
			String[] attachFileNames = { "d:/" + local_billNo + ".pdf" };// 附件
			SimpleMailSender sms = new SimpleMailSender();
			MailSenderInfo mailInfo = new MailSenderInfo();
			
			for(String email:list_emails){//for
				mailInfo.setValidate(true);			
				mailInfo.setSubject("函文知會定時通知(審核完畢)_" + local_billNo + "("
						+ local_factNo + ")");

				mailInfo.setAttachFileNames(attachFileNames);
				mailInfo.setContent("單號為:" + "<span style='color:red'>"
						+ local_billNo + "</span>" + "的函文已審核完畢,請查看附件"
						+ "<br/>本郵件自動定時發送，請勿回覆");

				String toAddress = email;
				mailInfo.setToAddress(toAddress);
				sms.sendHtmlMail(mailInfo);// 发送html格式
			}//for
			File file = new File("d:/" + local_billNo + ".pdf");
			if (file.exists()) {
				if (file.isFile()) {
					file.delete();
				}
			}
	 }
	 
	 /**
	  * 執行js腳本文件
	  * @Title: runJs
	  * @Description: TODO
	  * @param @throws FileNotFoundException
	  * @param @throws ScriptException
	  * @param @throws NoSuchMethodException
	  * @return void
	  * @throws
	  * @author web
	  * @date 2016/7/6
	  */
	 public static void runJs() throws FileNotFoundException, ScriptException, NoSuchMethodException{
		 ScriptEngine eng=new ScriptEngineManager().getEngineByName("javascript");
			Bindings bid=eng.createBindings();
			bid.put("index",1);
			eng.setBindings(bid,ScriptContext.ENGINE_SCOPE);
			Scanner scan=new Scanner(System.in);
			while(scan.hasNext()){
				int first=scan.nextInt();
				int sec=scan.nextInt();
				System.out.println("輸入參數："+first+" "+sec);
				eng.eval(new FileReader("f:\\myjs.js"));
				if(eng instanceof Invocable){
					Invocable in=(Invocable)eng;
					Double result=(Double)in.invokeFunction("test",first,sec);
					System.out.println("輸出結果："+result.intValue());
				}
			}
	 }
	 
	 /**
	  * 上傳文件
	  * @Title: uploadFile
	  * @Description: TODO
	  * @param @param upFile  要上傳的源文件
	  * @param @param downFilepath  上傳文件后的保存路徑
	  * @return void
	 * @throws IOException 
	 * @throws
	  * @author web
	  * @date 2016/7/8
	  */
	 public static void uploadFile(File upFile,String  downFilepath) throws IOException{
		 BufferedInputStream in=new BufferedInputStream(new FileInputStream(upFile));
		 BufferedOutputStream out=new BufferedOutputStream(new FileOutputStream(downFilepath));
		 byte[]bytes=new byte[1024];
		 int len=0;
		 while((len=in.read(bytes))!=-1){
			 out.write(bytes,0,len);
		 }
		 out.close();
		 in.close();
	 }
	 			 
	 /**
	  * 新函文發送email
	  * @Title: sendNewEmail
	  * @Description: TODO
	  * @param @param vbm
	  * @param @param list_emailPwd
	  * @return void
	  * @throws
	  * @author web
	  * @date 2016/7/8
	  */
	 public static void sendNewEmail(KyVisabillm vbm,List<String>list_emailPwd){
		 List<String>list=new ArrayList<String>();
		 list.add(vbm.getSignerNext());
		 for(String str:list_emailPwd){
			 list.add(str);
		 }
		 list.add("kyuen@yydg.com.cn");
		 String emailUrl_in=URL+"vbm_findById_email?visaSort="+vbm.getId().getVisaSort()+"&billNo="+vbm.getId().getBillNo()
		         +"&factNo="+vbm.getId().getFactNo()+"&email="+vbm.getSignerNext();	
		 String emailUrl_in2=URL+"vbm_findById_email2?visaSort="+vbm.getId().getVisaSort()+"&billNo="+vbm.getId().getBillNo()
		         +"&factNo="+vbm.getId().getFactNo()+"&email="+vbm.getSignerNext();
		 MailSenderInfo mailinfo=new MailSenderInfo();
		 SimpleMailSender sms = new SimpleMailSender();  
			mailinfo.setValidate(true);					
			mailinfo.setSubject("新函文初次審核"+vbm.getId().getBillNo()+"("+vbm.getId().getFactNo()+")");
			mailinfo.setContent("單號:<span style='color:red'>"+vbm.getId().getBillNo()+"</span>"+"&nbsp;&nbsp;廠別:"+vbm.getId().getFactNo()+								
					"<br/>點擊單號直接審核:<a href='"+emailUrl_in2+"'>"+vbm.getId().getBillNo()+"</a>(電腦適用)"+
					"<br/>點擊單號直接審核:<a href='"+emailUrl_in+"'>"+vbm.getId().getBillNo()+"</a>(手機平板適用)"+
					"<hr/>"+
					"如需查詢以往單據請登陸:(云端)<a href='"+URL+"'>"+URL+"</a>" +							
					"<br/>進入[KPI數據]--[函文審核]查找對應單號審核" +									
					"<hr/>"+
					"<br/>本郵件自動發送,請勿回復!如需回復或者問題，請回复到kyinfo.lp@yydg.com.cn劉平!<br/>"+
					"<hr/>");
		      for(String email:list){		    	  
		    	  mailinfo.setToAddress(email);
		    	  sms.sendHtmlMail(mailinfo);
		      } 				          		       			 
	 }
	 
	 public static void main(String[] args) throws ParseException, FileNotFoundException, ScriptException, NoSuchMethodException {
			List<Webestproduct>list=new ArrayList<Webestproduct>();
			List<String>list2=new ArrayList<String>();
			List<Webestproduct>list3=new ArrayList<Webestproduct>();
			List<Webestproduct>list4=new ArrayList<Webestproduct>();
			Map<String,Object>map=new LinkedHashMap<String,Object>();
			list2.add("RB");
			list2.add("MD");
			list2.add("PU");
	        /*for(int i=0;i<list2.size();i++){
	        	list.add(new Webestproduct(new WebestproductId("631",list2.get(i),new SimpleDateFormat("yyyyMM").parse("201605"),"zd")));
	        	list3.add(new Webestproduct(new WebestproductId("631",list2.get(i),new SimpleDateFormat("yyyyMM").parse("201605"),"zd")));
	        }
	        for(int i=0;i<list3.size();i++){
	        	list3.get(i).setAccessories(3.2-i);
	        }
	        for(Webestproduct pro:list){
	        	System.out.println(pro.getAccessories());
	        }
	        System.out.println("------------------------");
	        list3.remove(0);
	       
	        for(int i=0;i<list.size();i++){
	        	Webestproduct pro=list.get(i);
	        	for(Webestproduct pro2:list3){
	        		if(pro.getId().getFactCode().equals(pro2.getId().getFactCode())&&
	        				pro.getId().getFactNo().equals(pro2.getId().getFactNo())&&
	        				new SimpleDateFormat("yyyyMM").format(pro.getId().getYymm()).equals(new SimpleDateFormat("yyyyMM").format(pro2.getId().getYymm()))){
	        			//pro=pro2;
	        			list.remove(i);
	        			list.add(i,pro2);
	        		}
	        	}
	        	list4.add(pro);
	        	System.out.println(pro.getAccessories());
	        }
	        for(Webestproduct pro:list){
	        	System.out.println(pro.getAccessories());
	        }
	        map.put("201605", list);*/
	        /*for(Webestproduct pro:(List<Webestproduct>)map.get("201605")){
	        	System.out.println(pro.getAccessories());
	        }*/
	        
	        
			/*DateFormat frm=new SimpleDateFormat("yyyyMM");
			DateFormat frm2=new SimpleDateFormat("yyyyMMdd");
			List<String>months=new ArrayList<String>();
			months.add("201601");
			months.add("201602");
			months.add("201603");
			months.add("201604");
			Calendar cal=Calendar.getInstance();
			for(String month:months){
				cal.setTime(frm.parse(month));
				//cal.set(Calendar.DAY_OF_MONTH, 1);
				int maxnum=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				for(int i=1;i<=maxnum;i++){
					cal.set(Calendar.DAY_OF_MONTH, i);
					System.out.println(frm2.format(cal.getTime()));
				}
				System.out.println("-------------------------------------------------");
				
			}*/
			
			/*System.out.println("Start ...");  
			  
	        ExecutorService exec = Executors.newCachedThreadPool();  
	          
	        testTask(exec, 15); // 任务成功结束后等待计算结果，不需要等到15秒  
	        testTask(exec, 20); // 只等待5秒，任务还没结束，所以将任务中止  
	 
	        exec.shutdown();  
	        System.out.println("End!");
			Map<String,Double>map1=new LinkedHashMap<String,Double>();
			System.out.println(map1.size());*/
			//runJs();
			/*StringBuffer t1=new StringBuffer();
			StringBuilder t2=new StringBuilder();
			long start=System.currentTimeMillis();
			for(int i=0;i<1000000;i++){
				t1.append(i);
			}
			System.out.println("StringBuffer運行時間："+(System.currentTimeMillis()-start));
			
			long start2=System.currentTimeMillis();
			for(int i=0;i<1000000;i++){
				t2.append(i);
			}
			System.out.println("StringBuilder運行時間："+(System.currentTimeMillis()-start2));*/
			
			for(Season s:Season.values()){
				System.out.println(s);
				System.out.println("最舒服的季節："+Season.getComfortableSeason().getCon());
				System.out.println(Season.Summer.ordinal());;
			}
			
						
		}
	 enum Season{
		 Spring("春天"),Summer("夏天"),Autumn("秋天"),Winter("冬天");
		 private String con;
		 Season(String con){this.con=con;}
		 public String getCon(){
			 return con;
		 }
		 public void setCon(String con){
			 this.con=con;
		 }
		 
		 public static Season getComfortableSeason(){
			 return Spring;
		 }
		 
		 }
	 
	

}
class MyTask implements Callable<Boolean> {  	  
    public Boolean call() throws Exception {  
        // 总计耗时约10秒  
        for (int i = 0; i < 100L; i++) {  
            Thread.sleep(100); // 睡眠0.1秒  
            System.out.print('-');  
        } 
        return Boolean.TRUE;  
    }  
}


