package util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;


public class GlobalMethod {
	
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
	
	public static void main(String[] args) {
		/*List<String>list=GlobalMethod.getDateNum("201506","201601");
		for(String date:list){
			System.out.println(date);
		}*/
		Map<String,Object>map=new LinkedHashMap<String,Object>();
		map.put("a", 1);
		map.put("b", 2);
		map.put("c", 3);
		map.put("d", 4);
		map.put("e", 5);
		map.put("f", 6);
		List<String>list=new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		list.add("f");
		List<Integer>list_all=new ArrayList<Integer>();
		for(int i=0;i<list.size();i++){
			if(map.size()==0){
				list_all.add(0);
			}
			for(String key:map.keySet()){
				if(list.get(i).equals(key)){
					list_all.add((Integer)map.get(key));
					map.remove(key);
				}else{
					list_all.add(0);
				}
				break;
			}
			
		}
		for(Integer iii:list_all){
			System.out.println(iii);
		}
		
		
	}

}
