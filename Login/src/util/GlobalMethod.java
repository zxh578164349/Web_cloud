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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mail.MailSenderInfo;
import mail.SimpleMailSender;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import entity.WebTabpom;
import entity.WebTabpomfile;
import entity.WebType;
import entity.WebUser;
import entity.Webestproduct;
import entity.WebestproductId;


public class GlobalMethod extends HibernateDaoSupport{
	private static final String URL="http://203.85.73.161/Login/";
	private static final String URL2="http://203.85.73.161/Login";
	private static final String EMAIL="kyuen@yydg.com.cn";

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
	 * @Description: Excel2003
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
		//斜體
		HSSFFont font_itatic=wb.createFont();
		font_itatic.setItalic(true);
				
		HSSFCellStyle cs_itatic=wb.createCellStyle();
		cs_itatic.setFont(font_itatic);
		map.put("cs_itatic",cs_itatic);
		//標題樣式
		HSSFCellStyle cs_title=wb.createCellStyle();
		HSSFFont font_title=wb.createFont();
		font_title.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_title.setFontHeightInPoints((short)20);
		cs_title.setFont(font_title);
		cs_title.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_title.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		map.put("cs_title", cs_title);
		
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
		HSSFCellStyle cs_head=wb.createCellStyle();
		HSSFFont font_head=wb.createFont();
		font_head.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_head.setFontHeightInPoints((short)12);
		cs_head.setFont(font_head);
		cs_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_head.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_head.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_head.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_head.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_head.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_head.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		cs_head.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		map.put("cs_head", cs_head);
		
		HSSFCellStyle cs_red_bg=wb.createCellStyle();		
		cs_red_bg.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_red_bg.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_red_bg.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_red_bg.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_red_bg.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_red_bg.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_red_bg.setFillForegroundColor(IndexedColors.RED.getIndex());
		cs_red_bg.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		map.put("cs_red_bg", cs_red_bg);
		
		
		HSSFCellStyle cs_lblue_bg=wb.createCellStyle();		
		cs_lblue_bg.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_lblue_bg.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_lblue_bg.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_lblue_bg.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_lblue_bg.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_lblue_bg.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_lblue_bg.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
		cs_lblue_bg.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		map.put("cs_lblue_bg", cs_lblue_bg);
		
		HSSFCellStyle cs_head2=wb.createCellStyle();
		HSSFFont font_head2=wb.createFont();
		font_head2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_head2.setFontHeightInPoints((short)12);
		cs_head2.setFont(font_head2);
		cs_head2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_head2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_head2.setBorderTop(HSSFCellStyle.BORDER_THICK);
		cs_head2.setBorderRight(HSSFCellStyle.BORDER_THICK);
		cs_head2.setBorderBottom(HSSFCellStyle.BORDER_THICK);
		cs_head2.setBorderLeft(HSSFCellStyle.BORDER_THICK);
		cs_head2.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		cs_head2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		map.put("cs_head2", cs_head2);
		
		
		
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
	 * 所有樣式
	 * @Title: findStyles
	 * @Description: Excel2007
	 * @param @param wb
	 * @param @return
	 * @return Map<String,Object>
	 * @throws
	 * @author web
	 * @date 2016/4/6
	 */
	public static Map<String,Object> findStyles2007(XSSFWorkbook wb){
		/**
		 * 報表相關樣式
		 */
		Map<String,Object>map=new HashMap<String,Object>();
		
		//斜體
		XSSFFont font_itatic=wb.createFont();
		font_itatic.setItalic(true);
		
		XSSFCellStyle cs_itatic=wb.createCellStyle();
		cs_itatic.setFont(font_itatic);
		map.put("cs_itatic",cs_itatic);
		//標題樣式
		XSSFCellStyle cs_title=wb.createCellStyle();
		XSSFFont font_title=wb.createFont();
		font_title.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		font_title.setFontHeightInPoints((short)20);
		cs_title.setFont(font_title);
		cs_title.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_title.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		map.put("cs_title", cs_title);
		
		//標準單元格樣式
		XSSFCellStyle cs=wb.createCellStyle();
		cs.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		map.put("cs", cs);
		//表頭樣式
		XSSFCellStyle cs_head=wb.createCellStyle();
		XSSFFont font_head=wb.createFont();
		font_head.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		font_head.setFontHeightInPoints((short)12);
		cs_head.setFont(font_head);
		cs_head.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_head.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_head.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_head.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_head.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_head.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_head.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		cs_head.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		map.put("cs_head", cs_head);
		
		XSSFCellStyle cs_head2=wb.createCellStyle();
		XSSFFont font_head2=wb.createFont();
		font_head2.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		font_head2.setFontHeightInPoints((short)12);
		cs_head2.setFont(font_head2);
		cs_head2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_head2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_head2.setBorderTop(XSSFCellStyle.BORDER_THICK);
		cs_head2.setBorderRight(XSSFCellStyle.BORDER_THICK);
		cs_head2.setBorderBottom(XSSFCellStyle.BORDER_THICK);
		cs_head2.setBorderLeft(XSSFCellStyle.BORDER_THICK);
		cs_head2.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		cs_head2.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		map.put("cs_head2", cs_head2);
		
		XSSFCellStyle cs_red_bg=wb.createCellStyle();		
		cs_red_bg.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_red_bg.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_red_bg.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_red_bg.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_red_bg.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_red_bg.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_red_bg.setFillForegroundColor(IndexedColors.RED.getIndex());
		cs_red_bg.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		map.put("cs_red_bg", cs_red_bg);
		
		
		XSSFCellStyle cs_lblue_bg=wb.createCellStyle();		
		cs_lblue_bg.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_lblue_bg.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_lblue_bg.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_lblue_bg.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_lblue_bg.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_lblue_bg.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_lblue_bg.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
		cs_lblue_bg.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		map.put("cs_lblue_bg", cs_lblue_bg);
		
		
		//紅色加粗字體
		XSSFFont font_red=wb.createFont();
		font_red.setColor(IndexedColors.RED.getIndex());
		font_red.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		
		/**********************分類+項目+單位*****************************/
		XSSFFont font_bold = wb.createFont();
		font_bold.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		//font_bold.setFontHeightInPoints((short) 10);
		// 藍色背景粗字體
		XSSFCellStyle cs_blue = wb.createCellStyle();
		cs_blue.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_blue.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_blue.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_blue.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_blue.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_blue.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_blue.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		cs_blue.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		cs_blue.setFont(font_bold);
		map.put("cs_blue", cs_blue);
		// 標準粗字體樣式
		XSSFCellStyle cs_bold = wb.createCellStyle();
		cs_bold.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_bold.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_bold.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_bold.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_bold.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_bold.setBorderLeft(XSSFCellStyle.BORDER_THIN);		
		cs_bold.setFont(font_bold);
		map.put("cs_bold", cs_bold);
		
		//紅色粗字體樣式
		XSSFCellStyle cs_red=wb.createCellStyle();
		cs_red.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_red.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);		
		cs_red.setFont(font_red);
		map.put("cs_red", cs_red);
		/**********************分類+項目+單位*****************************/
		
		/**
		 * 數字格式（有背景顏色與無背景顏色）
		 */
		
		XSSFDataFormat format=wb.createDataFormat();
		//無背景
		XSSFCellStyle cs_percent=wb.createCellStyle();
		cs_percent.setDataFormat(format.getFormat("0.00%"));
		cs_percent.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_percent.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_percent.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		map.put("cs_percent", cs_percent);
		
		XSSFCellStyle cs_poi=wb.createCellStyle();
		cs_poi.setDataFormat(format.getFormat("#,###,0"));
		cs_poi.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_poi.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_poi.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_poi.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_poi.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_poi.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		map.put("cs_poi", cs_poi);
		
		XSSFCellStyle cs_poi1=wb.createCellStyle();
		cs_poi1.setDataFormat(format.getFormat("#,###,0.0"));
		cs_poi1.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_poi1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_poi1.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_poi1.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_poi1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_poi1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		map.put("cs_poi1", cs_poi1);
		
		XSSFCellStyle cs_poi2=wb.createCellStyle();
		cs_poi2.setDataFormat(format.getFormat("#,###,0.00"));
		cs_poi2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_poi2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_poi2.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		map.put("cs_poi2", cs_poi2);
		
		XSSFCellStyle cs_poi4=wb.createCellStyle();
		cs_poi4.setDataFormat(format.getFormat("#,###,0.0000"));
		cs_poi4.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_poi4.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_poi4.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		map.put("cs_poi4", cs_poi4);
		//有背景
		XSSFCellStyle cs_percent_bg=wb.createCellStyle();
		cs_percent_bg.setDataFormat(format.getFormat("0.00%"));
		cs_percent_bg.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_percent_bg.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_percent_bg.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_percent_bg.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_percent_bg.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_percent_bg.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		//cs_percent_bg.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		//cs_percent_bg.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		cs_percent_bg.setFont(font_red);
		map.put("cs_percent_bg", cs_percent_bg);
		
		
		XSSFCellStyle cs_poi_bg=wb.createCellStyle();
		cs_poi_bg.setDataFormat(format.getFormat("#,###,0"));
		cs_poi_bg.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_poi_bg.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_poi_bg.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_poi_bg.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_poi_bg.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_poi_bg.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		//cs_poi_bg.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		//cs_poi_bg.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		cs_poi_bg.setFont(font_red);
		map.put("cs_poi_bg", cs_poi_bg);
		
		XSSFCellStyle cs_poi1_bg=wb.createCellStyle();
		cs_poi1_bg.setDataFormat(format.getFormat("#,###,0.0"));
		cs_poi1_bg.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_poi1_bg.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_poi1_bg.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_poi1_bg.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_poi1_bg.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_poi1_bg.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		//cs_poi1_bg.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		//cs_poi1_bg.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		cs_poi1_bg.setFont(font_red);
		map.put("cs_poi1_bg", cs_poi1_bg);
		
		XSSFCellStyle cs_poi2_bg=wb.createCellStyle();
		cs_poi2_bg.setDataFormat(format.getFormat("#,###,0.00"));
		cs_poi2_bg.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_poi2_bg.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_poi2_bg.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_poi2_bg.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_poi2_bg.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_poi2_bg.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		//cs_poi2_bg.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		//cs_poi2_bg.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		cs_poi2_bg.setFont(font_red);
		map.put("cs_poi2_bg", cs_poi2_bg);
		
		XSSFCellStyle cs_poi4_bg=wb.createCellStyle();
		cs_poi4_bg.setDataFormat(format.getFormat("#,###,0.0000"));
		cs_poi4_bg.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_poi4_bg.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_poi4_bg.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_poi4_bg.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_poi4_bg.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_poi4_bg.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		//cs_poi4_bg.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		//cs_poi4_bg.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
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
			String content="";		
			if(list_vbm.size()>0){//start if
			MailSenderInfo mailInfo = new MailSenderInfo();
			SimpleMailSender sms = new SimpleMailSender();
			mailInfo.setValidate(true);
				for(int i=0;i<list_vbm.size();i++){// start for1	
					List<String>list_email=new ArrayList<String>();
					String signerNext=list_vbm.get(i).getSignerNext();
					String factNo=list_vbm.get(i).getId().getFactNo();
					String billNo=list_vbm.get(i).getId().getBillNo();
					String visaSort=list_vbm.get(i).getId().getVisaSort();
					String visaMk=list_vbm.get(i).getVisaMk();										
					if("liujung@mail.gj.com.tw".equals(signerNext.toLowerCase())){//劉小姐隻發送一次:liujung@mail.gj.com.tw 20161213
						if(list_vbm.get(i).getOneMk()==null){
							list_vbm.get(i).setOneMk("1");//標識隻發送一次
							visabillmSer.add(list_vbm.get(i));
							list_email.add(signerNext);
						}
					}else{
						list_email.add(signerNext);
					}										
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
					
					list_email.add(EMAIL);
					String emailUrl=URL+"vbm_findById_email?visaSort="+visaSort+"&billNo="+billNo
					         +"&factNo="+factNo+"&email="+signerNext;
					String emailUrl2=URL+"vbm_findById_email2?visaSort="+visaSort+"&billNo="+billNo
					         +"&factNo="+factNo+"&email="+signerNext;
					if(visaMk.equals("N")){
						subject="函文審核定時通知_"+list_vbm.get(i).getGeneral();
						content=list_vbm.get(i).getGeneral()+"<br/>"+
								"函文單號:"+"<span style='color:red'>"+billNo+"</span>"+"&nbsp;&nbsp;廠別:"+factNo+
					    		  "<br/>點擊單號直接審核:<a href='"+emailUrl2+"'>"+billNo+"</a>(電腦適用)"+
					    		  "<br/>點擊單號直接審核:<a href='"+emailUrl+"'>"+billNo+"</a>(手機平板適用)"+				    		 
							      "<hr/>"+
					    		  "如需查詢以往單據請登錄加久網站:(云端)<a href='"+URL2+"'>"+URL2+"</a>" +		            
					      		"<br/>進入[KPI數據]--[函文審核]中查找對應單號審核"+			    		
					    		"<hr/>"+
					      		"<br/>本郵件定時自動發送,請勿回復!如需回復或者問題，請回复到"+EMAIL+"資訊室!<br/>"+
					    		"<hr/>";
					}
					if(visaMk.equals("T")){
						////由於出差函文流程中可能不包括申請人， 所有需要從函文中獲取申請email 20160621
						if(list_vbm.get(i).getId().getBillNo().substring(0,2).equals("BM")){
							if(list_vbm.get(i).getWebbussletter().getUserEmail()!=null&&!list_vbm.get(i).getWebbussletter().getUserEmail().equals("")){
								list_email.add(list_vbm.get(i).getWebbussletter().getUserEmail());
							}
						}						
							subject="函文退回定時通知_"+list_vbm.get(i).getGeneral();;//退回函文隻發送一次，所以也要鎖定狀態emailMk	
							list_vbm.get(i).setEmailMk("Y");
							visabillmSer.add(list_vbm.get(i));
							content=list_vbm.get(i).getGeneral()+"<br/>"+
							         "函文單號:"+"<span style='color:red'>"+billNo+"</span>"+"&nbsp;&nbsp;"+"不通過，備註如下:"+
						    		  "<br/>"+
						    		  "<span style='color:red'>"+(list_vbm.get(i).getMemoMk()==null?"無備註":list_vbm.get(i).getMemoMk())+"</span>"+				    		 
								      "<hr/>"+
						    		 "詳情請登錄加久網站:(云端)<a href='"+URL2+"'>"+URL2+"</a>" +		            
						      		"<br/>進入[KPI數據]--[函文審核]中查找對應單號審核"+			    		
						    		"<hr/>"+
						      		"<br/>本郵件定時自動發送,請勿回復!如需回復或者問題，請回复到"+EMAIL+"資訊室!<br/>"+
						    		"<hr/>";
					}
					for(int j=0;j<list_email.size();j++){//start for2
						  mailInfo.setToAddress(list_email.get(j));
						  if(list_email.get(j).equals(EMAIL)){						  
							  String content_msg=content+"下一箇簽核:<span style='color:blue'>"+signerNext+"</span>";
							  mailInfo.setContent(content_msg);					      
						  }else{
							  mailInfo.setContent(content);
						  }
					      mailInfo.setSubject(subject);    			       				    		  			           
					      sms.sendHtmlMail(mailInfo);//发送html格式					      
					}//end for2		
					subject=null;//清空
					content=null;
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
			list_emails.add(EMAIL);
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
				mailInfo.setSubject("函文知會定時通知(審核完畢)_" +vbm2.getGeneral());

				mailInfo.setAttachFileNames(attachFileNames);
				mailInfo.setContent(vbm2.getGeneral() +"<br/>"+
						"單號為:" + "<span style='color:red'>"
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
		 list.add(EMAIL);
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
					"<br/>本郵件自動發送,請勿回復!如需回復或者問題，請回复到"+EMAIL+"資訊室!<br/>"+
					"<hr/>");
		      for(String email:list){		    	  
		    	  mailinfo.setToAddress(email);
		    	  sms.sendHtmlMail(mailinfo);
		      } 				          		       			 
	 }
	 
	 /**
	  * 減簽函文郵件
	  * @Title: sendEmail_minus
	  * @Description: TODO
	  * @param @param content
	  * @param @param subject
	  * @param @param factNo
	  * @param @param billNo
	  * @param @param visaSort
	  * @param @param email
	  * @return void
	  * @throws
	  * @author web
	  * @date 2016/12/26
	  */
	 public static void sendEmail_minus(String factNo,String billNo,String visaSort,String email){
		 //****************通知下一位签核人***************/
		 List<String>list=new ArrayList<String>();
		 list.add(email);
		 list.add(EMAIL);
		 SimpleMailSender sms = new SimpleMailSender();
		 MailSenderInfo mailInfo = new MailSenderInfo();
  		 String emailUrl_in=URL+"vbm_findById_email?visaSort="+visaSort+"&billNo="+billNo
		         +"&factNo="+factNo+"&email="+email;
  		String emailUrl_in2=URL+"vbm_findById_email2?visaSort="+visaSort+"&billNo="+billNo
		         +"&factNo="+factNo+"&email="+email;
  		 
  		 mailInfo.setSubject("函文減簽(下一位審核)_"+billNo+"("+factNo+")");
  		 mailInfo.setContent(
 	    		"函文單號:"+"<span style='color:red'>"+billNo+"</span>"+"&nbsp;&nbsp;廠別:"+factNo+
 	    		"<br/>點擊單號直接審核:<a href='"+emailUrl_in2+"'>"+billNo+"</a>(電腦適用)"+
 	    		"<br/>點擊單號直接審核:<a href='"+emailUrl_in+"'>"+billNo+"</a>(手機平板適用)"+
 	    		"<br/>如需查詢以往單據請登錄加久網站:((云端))<a href='"+URL2+"'>"+URL2+"</a>" +	            
 	      		"<br/>進入[KPI數據]--[函文審核]中查找對應單號審核,"+	      	    		
 	    		"<hr/>"+	      		
 	    		"<br/>本郵件自動發送,請勿回復!如需回復或者問題，請回复到"+EMAIL+"資訊室!<br/>" +
 	    		"<hr/>");
  		 for(String address:list){
  			mailInfo.setToAddress(address);
  			sms.sendHtmlMail(mailInfo);
  		 }		 
  		//****************通知下一位签核人***************/
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
	 
	 public static void uploadFiles(List<File> upFiles,List<String>filesFileName,String downFilepath) throws IOException{		
		 for(int i=0;i<upFiles.size();i++){
			 if(upFiles.get(i)!=null){
				 String path=downFilepath+filesFileName.get(i);
				 uploadFile(upFiles.get(i),path);
			 }			
		 }		
	 }
	 
	 public static void uploadFiles(List<BufferedInputStream>ins,List<BufferedOutputStream>outs) throws IOException{		 
		 byte[]bytes=new byte[1024];
		 int len=0;
		 for(int i=0;i<ins.size();i++){			 
			 while((len=ins.get(i).read(bytes))!=-1){
				 outs.get(i).write(bytes,0,len);
			 }
			 outs.get(i).close();
			 ins.get(i).close();
		 }		 
	 }
	 
	 
	 			 
	 
	 
	 
	 /**
	  * 返回Date日期
	  * @Title: to_date
	  * @Description: TODO
	  * @param @param source
	  * @param @param format
	  * @param @return
	  * @param @throws ParseException
	  * @return Date
	  * @throws
	  * @author web
	  * @date 2016/11/18
	  */
	 public static Date to_date(String source, String format) throws ParseException {  	        
	        SimpleDateFormat sdf = new SimpleDateFormat(format);  
	        Date date = sdf.parse(source);  
	        return date;  
	    }
	 
	 /**
	  * 返回String日期
	  * @Title: to_date
	  * @Description: TODO
	  * @param @param source
	  * @param @param format
	  * @param @return
	  * @param @throws ParseException
	  * @return Date
	  * @throws
	  * @author web
	  * @date 2016/11/18
	  */
	 public static String to_date2(Date source, String format) throws ParseException {  	        
	        SimpleDateFormat sdf = new SimpleDateFormat(format);  
	        String date = sdf.format(source);  
	        return date;  
	    }
	 
	 /**
	  * 根據函文簽核人數來選擇不同的函文文件模板
	  * @Title: getSubfile
	  * @Description: TODO
	  * @param @param num
	  * @param @return
	  * @return String
	  * @throws
	  * @author web
	  * @date 2016/8/5
	  */
	 public static String getSubfile(int num){
		 String result="sub_file.jasper";
		 if(num<=3){
			 result="sub_file_3.jasper";
		 }
		 if(num>3&&num<6){
			 result="sub_file_6.jasper";
		 }
		 if(num>=6&&num<=9){
			 result="sub_file_9.jasper";
		 }
		 return result;		 
	 }
	 
	 public static String randomString(int num){
		Random rd=new Random(num);
		 StringBuilder sb=new StringBuilder();
		 while(true){
			 int k=rd.nextInt(27);
			 if(k==0){
				 break;
			 }
			 sb.append((char)('`'+k));
		 }
		 return sb.toString();		 		 		 
	 }
	 
	 /**
	  * 移除相同元素並排序
	  * @param list
	  * @return
	  */
	 public static List<Double> removeSameDouble(List<Double>list){
		 for(int i=0;i<list.size();i++){
			 for(int j=list.size()-1;j>i;j--){
				 if(list.get(j).equals(list.get(i))){
					 list.remove(j);
				 }
			 }
		 }
		 Collections.sort(list);
		 return list;
	 }
	 
	 
	 
	 

	//****************************************多個文件打包壓縮並下載  20161030*************************************
	public static HttpServletResponse downLoadFiles(List<File> files,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			/**
			 * 这个集合就是你想要打包的所有文件， 这里假设已经准备好了所要打包的文件
			 */
			// List<File> files = new ArrayList<File>();

			/**
			 * 创建一个临时压缩文件， 我们会把文件流全部注入到这个文件中 这里的文件你可以自定义是.rar还是.zip
			 */
			File file = new File("d:/report_package.rar");
			if (!file.exists()) {
				file.createNewFile();
			}
			response.reset();
			// response.getWriter()
			// 创建文件输出流
			FileOutputStream fous = new FileOutputStream(file);
			/**
			 * 打包的方法我们会用到ZipOutputStream这样一个输出流, 所以这里我们把输出流转换一下
			 */
			// org.apache.tools.zip.ZipOutputStream zipOut
			// = new org.apache.tools.zip.ZipOutputStream(fous);
			ZipOutputStream zipOut = new ZipOutputStream(fous);
			/**
			 * 这个方法接受的就是一个所要打包文件的集合， 还有一个ZipOutputStream
			 */
			zipFile(files, zipOut);
			zipOut.close();
			fous.close();
			return downloadZip(file, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * 直到文件的打包已经成功了， 文件的打包过程被我封装在FileUtil.zipFile这个静态方法中，
		 * 稍后会呈现出来，接下来的就是往客户端写数据了
		 */
		// OutputStream out = response.getOutputStream();

		return response;
	}
	/**
	* 把接受的全部文件打成压缩包 
	* @param List<File>; 
	* @param org.apache.tools.zip.ZipOutputStream 
	*/
	public static void zipFile(List files, ZipOutputStream outputStream) {
		int size = files.size();
		for (int i = 0; i < size; i++) {
			File file = (File) files.get(i);
			zipFile(file, outputStream);
		}
	}
	
	
	public static HttpServletResponse downloadZip(File file,
			HttpServletResponse response) {
		try {
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(
					file.getPath()));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();

			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ file.getName());
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				File f = new File(file.getPath());
				f.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return response;
	}
	/** 
	* 根据输入的文件与输出流对文件进行打包
	* @param File
	* @param org.apache.tools.zip.ZipOutputStream
	*/
	public static void zipFile(File inputFile, ZipOutputStream ouputStream) {
		try {
			if (inputFile.exists()) {
				/**
				 * 如果是目录的话这里是不采取操作的， 至于目录的打包正在研究中
				 */
				if (inputFile.isFile()) {
					FileInputStream IN = new FileInputStream(inputFile);
					BufferedInputStream bins = new BufferedInputStream(IN, 512);
					// org.apache.tools.zip.ZipEntry
					//ZipEntry entry = new ZipEntry(inputFile.getName());
					ZipEntry entry = new ZipEntry(System.currentTimeMillis()+"_"+inputFile.getName());
					ouputStream.putNextEntry(entry);
					// 向压缩文件中输出数据
					int nNumber;
					byte[] buffer = new byte[512];
					while ((nNumber = bins.read(buffer)) != -1) {
						ouputStream.write(buffer, 0, nNumber);
					}
					// 关闭创建的流对象
					bins.close();
					IN.close();
				} else {
					try {
						File[] files = inputFile.listFiles();
						for (int i = 0; i < files.length; i++) {
							zipFile(files[i], ouputStream);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//****************************************多個文件打包壓縮並下載  20161030*************************************
	
	/**
	 * 獲取文件流
	 * @Title: getFileInput
	 * @Description: TODO
	 * @param @param path
	 * @param @return
	 * @param @throws FileNotFoundException
	 * @return InputStream
	 * @throws
	 * @author web
	 * @date 2016/11/17
	 */
	public static InputStream getFileInput(String path) throws FileNotFoundException{
		FileInputStream stream=new FileInputStream(path);
		return stream;
	}
	
	/**
	 * 獲取用戶的信息
	 * @Title: getLoginUser
	 * @Description: TODO
	 * @param @return
	 * @return WebUser
	 * @throws
	 * @author web
	 * @date 2016/11/18
	 */
	public static WebUser getLoginUser(){
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		return user;
	}
	
	public static void uploadfile(WebTabpom tabpom) throws IOException{
		//File uploadFile=new File(ServletActionContext.getServletContext().getRealPath("KyzexpFile\\"+tabpom.getPomNo()));//附檔上傳到項目
		/*File uploadFile_backup=new File("d:\\WebtabpomFile_backup\\"+tabpom.getPomNo());//附檔上傳到D盤(為了避免更新項目時丟失附檔,所在上傳到D盤)			
		if(!uploadFile_backup.exists()){
			uploadFile_backup.mkdirs();
		}*/
		List<WebTabpomfile>list_tabfile=(List<WebTabpomfile>)ActionContext.getContext().getSession().get("list_tabfile");			
		if(list_tabfile!=null&&list_tabfile.size()>0){
			for(WebTabpomfile obj:list_tabfile){
				obj.getId().setWebTabpom(tabpom);
			}						
			tabpom.setWebTabpomfiles(list_tabfile);
			tabpom.setFileMk("1");//標示是否帶有附檔						
		}				
}
	
	/**
	 * 根據不同的函文類型來更改相應的函文標題，內容（函文定時審核通知）
	 * @Title: vbmCotentsTypes
	 * @Description: TODO
	 * @param @param list
	 * @return void
	 * @throws
	 * @author web
	 * @date 2016/12/14
	 */
	public static void vbmCotentsTypes(List<KyVisabillm>list){
		for(KyVisabillm vbm:list){
			vbmCotentsType(vbm);
		}
	}
	public static void vbmCotentsType(KyVisabillm vbm){		
			if(vbm.getId().getBillNo().substring(0,2).equals("BM")){
				 vbm.getWebbussletter().getUserEmail();//獲取出差函文申請人的Email				
				 vbm.setGeneral("("+vbm.getFactNo2().getFactSname()+")"+vbm.getWebbussletter().getUsername()+
						 "人員出差申請書_"+vbm.getId().getBillNo()+"("+vbm.getId().getFactNo()+")");
			}
			if(vbm.getId().getBillNo().substring(0,2).equals("GJ")){
				vbm.setGeneral("("+vbm.getFactNo2().getFactSname()+")"+vbm.getFormula().getFormulaName()+"配方單_"+
						vbm.getId().getBillNo()+"("+vbm.getId().getFactNo()+")");
			}
			if(vbm.getId().getBillNo().substring(0,2).equals("CM")){												
				vbm.setGeneral("("+vbm.getFactNo2().getFactSname()+")"+vbm.getKyzletter().getTitle()+"_"+
						vbm.getId().getBillNo()+"("+vbm.getId().getFactNo()+")");
			}
			if(vbm.getId().getBillNo().substring(0,2).equals("EM")){				
				vbm.setGeneral("("+vbm.getFactNo2().getFactSname()+")"+vbm.getKyzexp().getMemoSmk()+"_"+
						vbm.getId().getBillNo()+"("+vbm.getId().getFactNo()+")");
			}			
			vbm.getWebtype().getTypeName();		
	}
	
	
	/**
	 * 根據不同的函文類型來更改相應的函文標題，內容（函文定時審核完畢）
	 * @Title: vbmCotentsTypes
	 * @Description: TODO
	 * @param @param list
	 * @return void
	 * @throws
	 * @author web
	 * @date 2016/12/14
	 */
	public static void vbmCotentsTypes2(List<KyVisabillm>list){
		vbmCotentsTypes(list);
		for(KyVisabillm vbm:list){
			vbm.getKyVisabillses().size();
		}
	}
	 
	 
	 
	 public static void main(String[] args) throws ParseException, FileNotFoundException, ScriptException, NoSuchMethodException, ClassNotFoundException {
			/**集合排序
		    List<Integer>list=new ArrayList<Integer>();
			list.add(1);list.add(1);list.add(2);list.add(2);list.add(3);list.add(3);
			list.add(1);list.add(1);list.add(1);list.add(2);list.add(2);list.add(2);
			for(Integer ii:list){
				System.out.print(ii+"\t");
			}
			System.out.println("*****************");
			for(int i=0;i<list.size()-1;i++){
				for(int j=0;j<list.size()-1-i;j++){
					if(list.get(j)>list.get(j+1)){
						list.add(j,list.get(j+1));
						list.add(j+2,list.get(j+1));
						list.remove(j+1);
						list.remove(j+2);
					}
				}
				
			}
			for(Integer ii:list){
				System.out.print(ii+"\t");
			}**/
		 /*List<String>list=new ArrayList<String>();
		 list.add("04");list.add("02");list.add("01");list.add("02");list.add("03");list.add("01");list.add("ZZ");
		 for(String kk:list){
			 System.out.print(kk+"\t");
		 }
		 System.out.println("*********************");
		 for(int i=0;i<list.size()-1;i++){
			 for(int j=0;j<list.size()-1-i;j++){
				 if(list.get(j).compareTo(list.get(j+1))>0){
					 list.add(j,list.get(j+1));
					 list.add(j+2,list.get(j+1));
					 list.remove(j+1);
					 list.remove(j+2);
				 }
			 }
		 }
		 String dd;
		 for(String kk:list){
			 dd=kk;
			 System.out.print(dd+"\t");
		 }*/
		 
		 KyVisabillm vbm=new KyVisabillm();
		 System.out.println("1".equals(null));
		 System.out.println("1".equals("0"));
		 System.out.println("1".equals("1"));
		 if(vbm.getKyzexp()!=null){
			 System.out.println("1".equals(vbm.getKyzexp().getEmerMk())); 
		 }else{
			 System.out.println("*******");
		 }
		 
			
		}
	 
	 public static <T> void test_a(T x) throws ClassNotFoundException{
		 System.out.println(x+"getClass類型："+x.getClass().getName());
		 List<T>list=new ArrayList<T>();
		 list.add((T)"a");  
		 list.add((T)new Double(12.3));
		 list.add((T)new Integer(15));
		 list.add((T)new Long(5));
		 for(T t:list){
			 System.out.println("list元素類型"+t+":"+t.getClass().getName());
		 }	 
	 }
	
	 
	 public static List<Double> maopaoList(){
		 List<Double>list=new ArrayList<Double>();
		 list.add(1.11);list.add(2.22);list.add(3.33);list.add(4.44);list.add(0.1);list.add(0.3);list.add(1.5);		 
		for(int i=0;i<list.size();i++){
			 for(int j=list.size()-1;j>i;j--){
				 Double a=list.get(i);Double b=list.get(j); 
				 if(list.get(i)>list.get(j)){
					 list.remove(i);list.add(i,b);
					 list.remove(j);list.add(j,a);
				 }
				 
			 }
		 }
		 return list;
	 }
	 
	 /**
	  * 
	  * @Title: exchangeAToB
	  * @Description: 兩箇數交換
	  * @param @param a
	  * @param @param b
	  * @return void
	  * @throws
	  * @author web
	  * @date 2016/9/13
	  */
	 public static void exchangeAToB(Double a,Double b){
		 Double temp=a;
		 a=b;
		 b=temp;
		 
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


