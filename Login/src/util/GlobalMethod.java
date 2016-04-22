package util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
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
        
        
        List<String>list1=new ArrayList<String>();
        list1.add("a");list1.add("b");list1.add("c");
        List<String>list2=new ArrayList<String>();
        list2.add("a");list2.add("b");
        System.out.println(list1.containsAll(list2));
       
       
						
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

}
