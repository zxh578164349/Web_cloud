/**
 * 
 */
package action;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.record.chart.BeginRecord;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

import entity.VWebbussort;
import entity.VWebbussortFcode;
import entity.VWebbussortFcodeId;
import entity.VWebbussortId;

import services.IVWebbussortServices;
import services.IWebFactServices;
import util.GlobalMethod;

/**   
 *    
 * 项目名称：Login   
 * 类名称：WebbussortAction   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/6 上午10:22:35   
 * 修改人：Administrator   
 * 修改时间：2016/4/6 上午10:22:35   
 * 修改备注：   
 * @version    
 *    
 **/
public class VWebbussortAction extends ActionSupport implements ServletResponseAware{
	private String yymm;
	private String yymm2;
	private List<String>list_factno;
	private List<String>list_factcode;
	private IVWebbussortServices vwebbusssorSer;
	private IWebFactServices webFactSer;
	private javax.servlet.http.HttpServletResponse response;
	
	
	public List<String> getList_factno() {
		return list_factno;
	}
	public void setList_factno(List<String> list_factno) {
		this.list_factno = list_factno;
	}
	public List<String> getList_factcode() {
		return list_factcode;
	}
	public void setList_factcode(List<String> list_factcode) {
		this.list_factcode = list_factcode;
	}
	public String getYymm() {
		return yymm;
	}
	public void setYymm(String yymm) {
		this.yymm = yymm;
	}
	public String getYymm2() {
		return yymm2;
	}
	public void setYymm2(String yymm2) {
		this.yymm2 = yymm2;
	}
	public void setVwebbusssorSer(IVWebbussortServices vwebbusssorSer) {
		this.vwebbusssorSer = vwebbusssorSer;
	}
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	
	
	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}
	public void print() throws ParseException{		
		HSSFWorkbook wb=new HSSFWorkbook();
		Map<String,Object>map=this.findStyles(wb);
		HSSFCellStyle cs=(HSSFCellStyle)map.get("cs");				
		List<VWebbussort>lists=vwebbusssorSer.findByYymm(yymm, yymm2);//查詢到的數據
		List<String>list_months=GlobalMethod.findMonths(yymm, yymm2);//所有月份
		List<Object[]>list_facts=webFactSer.findFactAble2();//所有廠別		
		Map<String,Object>map_types=this.findTypes();//類型和項目 		
		List<String>list_temp=findTemps();//臨時集合
		/********************數據源處理*************************/
	    //List<List<VWebbussort>>list_all=new ArrayList<List<VWebbussort>>();
	    Map<String,Object>map_all=new LinkedHashMap<String,Object>();
	    for(String month:list_months){
	    	List<VWebbussort>list=new ArrayList<VWebbussort>();
	    	for(Object[] obj:list_facts){
	    		list.add(new VWebbussort(new VWebbussortId(obj[0].toString(),month)));
	    		//list.add(null);
	    	}
	    	map_all.put(month,list);
	    }
	    for(String month:map_all.keySet()){//for
	    	/*for(VWebbussort sort2:(List<VWebbussort>)map_all.get(month)){
	    		for(VWebbussort sort:lists){
	    			if(sort2.getId().getFactNo().equals(sort.getId().getFactNo())&&sort2.getId().getYymm().equals(sort.getId().getYymm())){
	    				sort2=sort;
	    				break;
	    			}
	    		}
	    	}*/
	    	
	    	for(int a=0;a<((List<VWebbussort>)map_all.get(month)).size();a++){
	    		VWebbussort sort2=((List<VWebbussort>)map_all.get(month)).get(a);
	    		for(int b=0;b<lists.size();b++){
	    			VWebbussort sort=lists.get(b);
	    			if(sort2.getId().getFactNo().equals(sort.getId().getFactNo())&&sort2.getId().getYymm().equals(sort.getId().getYymm())){
	    				((List<VWebbussort>)map_all.get(month)).remove(a);
	    				((List<VWebbussort>)map_all.get(month)).add(a, sort);
	    				//sort2=sort;
	    				break;
	    			}
	    		}
	    		
	    	}	    			
	    }//for
	   
	    //List<List<List<BigDecimal>>>list_all2=new ArrayList<List<List<BigDecimal>>>();
	    Map<String,Object>map_all2=new LinkedHashMap<String,Object>();
		for (String month:map_all.keySet()) {
			List<List<BigDecimal>> listtemp = new ArrayList<List<BigDecimal>>();
			for (VWebbussort sort : (List<VWebbussort>)map_all.get(month)) {
				List<BigDecimal> list_b = new ArrayList<BigDecimal>();
				if (sort!= null) {
					list_b.add(sort.getA01());
					list_b.add(sort.getA02());
					list_b.add(sort.getA03());
					list_b.add(sort.getA04());
					list_b.add(sort.getA05());
					list_b.add(sort.getA06());
					list_b.add(sort.getA07());
					list_b.add(sort.getSortA06());
					list_b.add(sort.getA08());
					list_b.add(sort.getA09());
					list_b.add(sort.getA10());
					list_b.add(sort.getA11());
					list_b.add(sort.getSortA10());
					list_b.add(sort.getA12());
					list_b.add(sort.getA13());
					list_b.add(sort.getA14());
					list_b.add(sort.getA15());
					list_b.add(sort.getSortA14());
					list_b.add(sort.getA16());
					list_b.add(sort.getA17());
					list_b.add(sort.getA18());
					list_b.add(sort.getA19());
					list_b.add(sort.getA20());
					list_b.add(sort.getA21());
					list_b.add(sort.getA22());
					list_b.add(sort.getA23());
					list_b.add(sort.getA24());
					list_b.add(sort.getA25());
					list_b.add(sort.getA26());
					list_b.add(sort.getA27());
					list_b.add(sort.getA28());
					list_b.add(sort.getSortA28());
					list_b.add(sort.getA29());
					list_b.add(sort.getA30());
					list_b.add(sort.getSortA30());
					list_b.add(sort.getA31());
					list_b.add(sort.getA32());
					list_b.add(sort.getA33());
					list_b.add(sort.getSortA33());
					list_b.add(sort.getA34());
					list_b.add(sort.getA35());
					list_b.add(sort.getSortA34());
					list_b.add(sort.getA36());
					list_b.add(sort.getA37());
					list_b.add(sort.getA38());
					list_b.add(sort.getA39());
					list_b.add(sort.getSortA38());
					list_b.add(sort.getA40());
					list_b.add(sort.getA41());
					list_b.add(sort.getA42());
					list_b.add(sort.getA43());
					list_b.add(sort.getSortA42());
					list_b.add(sort.getA44());
					list_b.add(sort.getA45());
					list_b.add(sort.getA46());
					list_b.add(sort.getA47());
					list_b.add(sort.getSortA46());
					list_b.add(sort.getA48());
					list_b.add(sort.getA49());
					list_b.add(sort.getA50());
					list_b.add(sort.getA51());
					list_b.add(sort.getSortA50());
					list_b.add(sort.getA52());
					list_b.add(sort.getA53());
					list_b.add(sort.getA54());
					list_b.add(sort.getA55());
					list_b.add(sort.getSortA54());
					list_b.add(sort.getA56());
					list_b.add(sort.getA57());
					list_b.add(sort.getA58());
					list_b.add(sort.getA59());
					list_b.add(sort.getSortA58());
					list_b.add(sort.getA60());
					list_b.add(sort.getA61());
					list_b.add(sort.getSortA61());
					list_b.add(sort.getA62());
					list_b.add(sort.getA63());
					list_b.add(sort.getSortA63());
					list_b.add(sort.getA64());
					list_b.add(sort.getA65());
					list_b.add(sort.getSortA65());
					list_b.add(sort.getA66());
					list_b.add(sort.getA67());
					list_b.add(sort.getSortA67());
					list_b.add(sort.getA68());
					list_b.add(sort.getA69());
					list_b.add(sort.getSortA69());
					list_b.add(sort.getA70());
					list_b.add(sort.getA71());
					list_b.add(sort.getA72());
					list_b.add(sort.getA73());
					list_b.add(sort.getA74());
					list_b.add(sort.getA75());
					list_b.add(sort.getSortA75());
					list_b.add(sort.getA76());
					list_b.add(sort.getA77());
					list_b.add(sort.getA78());
					list_b.add(sort.getSortA78());
				}
				listtemp.add(list_b);
			}
			map_all2.put(month,listtemp);
		}
		
		/*****************************數據排名(重點)*******************************/
		//for_a:月份循環
		//for_b:項目循環
		//for_c:廠別循環
		 for(String month:map_all.keySet()){//for_a
		    	List<BigDecimal>list=new ArrayList<BigDecimal>();
		    	List<BigDecimal>list2=new ArrayList<BigDecimal>();
		    	List<Integer>list3=new ArrayList<Integer>();
		    	for(int a=0;a<findTemps().size();a++){//for_b		    		
		    		for(int b=0;b<((List<List<BigDecimal>>)map_all2.get(month)).size();b++){//for_c
			    		if(findTemps().get(a).equals("compare obj")){
			    			if(b==0){
			    				//清除内容，为下一次排名做准备
			    				list.clear();
			    				list2.clear();
			    				list3.clear();
			    			}
			    			list.add(((List<List<BigDecimal>>)map_all2.get(month)).get(b).get(a)==null?new BigDecimal(0):((List<List<BigDecimal>>)map_all2.get(month)).get(b).get(a));	
			    			list2.add(((List<List<BigDecimal>>)map_all2.get(month)).get(b).get(a)==null?new BigDecimal(0):((List<List<BigDecimal>>)map_all2.get(month)).get(b).get(a));
			    		}		    		
			    		if(findTemps().get(a).equals("排名")){
			    			Collections.sort(list2);
				    		for(int x=0;x<list.size();x++){
				    			list3.add(GlobalMethod.getIndex(list.get(x), list2));
				    		}
			    			((List<List<BigDecimal>>)map_all2.get(month)).get(b).set(a, new BigDecimal(list3.get(b)));				    		
			    		}
			    	}//for_c
		    	}//for_b		    	
		    }//for_a
		 /*****************************數據排名（重點）*******************************/
		 
		 
	    
		/********************數據源處理*************************/
	    
	    
	   /********************表格初始化和固定內容*************************/
	   this.printStaticContent(wb, map, list_months, list_facts,list_temp, map_types);
	   /********************表格初始化和固定內容*************************/
	   
	   /********************填充數據源*************************/
	   for(String month:map_all2.keySet()){
		   for(int a=0;a<((List<List<BigDecimal>>)map_all2.get(month)).size();a++){
			   int tempsize=((List<List<BigDecimal>>)map_all2.get(month)).get(a).size();
			   if(tempsize==0){
				   /*tempsize=list_temp.size();
				   for(int b=0;b<tempsize;b++){				   
					   wb.getSheet(month).getRow(2+b).getCell(3+a).setCellValue("無數據");
					   wb.getSheet(month).getRow(2+b).getCell(3+a).setCellStyle(cs);
				   }*/				   
			   }else{
				   for(int b=0;b<tempsize;b++){				   
					   wb.getSheet(month).getRow(2+b).getCell(3+a).setCellValue(((List<List<BigDecimal>>)map_all2.get(month)).get(a).get(b)==null?0:((List<List<BigDecimal>>)map_all2.get(month)).get(a).get(b).doubleValue());
					   wb.getSheet(month).getRow(2+b).getCell(3+a).setCellStyle(cs);
				   }
			   }
			  
		   }
	   }	   
	   /********************填充數據源*************************/
	   
	   
		try {
			/*OutputStream os = new FileOutputStream("E:/" + "websort.xls");
			wb.write(os);
			os.close();	*/
			ServletOutputStream os=response.getOutputStream();
			response.setContentType("application/vnd.ms-excel");
			int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");//判斷是否為IE瀏覽器,大於0則為IE瀏覽器
			String fileName="report"+yymm+"-"+yymm2+".xls";
			if(msie>0){
				fileName=java.net.URLEncoder.encode(fileName,"utf-8");//解決IE中文文件不能下載的問題
			}else{
				fileName=new String(fileName.getBytes("utf-8"),"iso-8859-1");//解決非IE中文名亂碼問題
			}		
			response.setHeader("Content-disposition", "attachment;filename="+fileName);					
			wb.write(os);
			os.close();						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    
		
	}
	
	/**
	 * 分廠別狀態
	 * @Title: print_fcode
	 * @Description: TODO
	 * @param 
	 * @return void
	 * @throws ParseException 
	 * @throws
	 * @author web
	 * @date 2016/4/19
	 */
	public void print_fcode() throws ParseException{
			
		HSSFWorkbook wb=new HSSFWorkbook();
		Map<String,Object>map=this.findStyles(wb);
		HSSFCellStyle cs=(HSSFCellStyle)map.get("cs");				
		List<VWebbussort>lists=vwebbusssorSer.findByYymm(yymm, yymm2);//查詢到的數據
		List<String>list_months=GlobalMethod.findMonths(yymm, yymm2);//所有月份
		List<Object[]>list_facts=webFactSer.findFactAble2();//所有廠別		
		Map<String,Object>map_types=this.findTypes();//類型和項目 		
		List<String>list_temp=findTemps();//臨時集合
		
		List<VWebbussortFcode>lists2=vwebbusssorSer.findByYymm2(yymm, yymm2);//查詢到的數據

		// 如果頁面上有選factcode
		if (list_factcode != null && list_factcode.size() > 0) {
			for(String month:list_months){
				Map<String,Object>map_fcode=new LinkedHashMap<String,Object>();
				for (String fcode:list_factcode) {
					List<VWebbussortFcode> list = new ArrayList<VWebbussortFcode>();
					for (String fact:list_factno) {
						String[] objs = fact.split("_");						
							list.add(new VWebbussortFcode(new VWebbussortFcodeId(objs[1],fcode,month)));						
					}
					
				}
			}
			
			// 如果沒有沒有,則默認全部
		} else {
			List<Object[]> list_objs = webFactSer.findAllFactCode2_showA();
			for (int i = 0; i < list_objs.size(); i++) {
				Object[] obj = list_objs.get(i);
				String fact_code = obj[0].toString();
			}
		}
		
		
		
		/********************數據源處理*************************/
	    Map<String,Object>map_all=new LinkedHashMap<String,Object>();
	    for(String month:list_months){
	    	List<VWebbussort>list=new ArrayList<VWebbussort>();
	    	for(Object[] obj:list_facts){
	    		list.add(new VWebbussort(new VWebbussortId(obj[0].toString(),month)));
	    	}
	    	map_all.put(month,list);
	    }
	    for(String month:map_all.keySet()){//for	    		    	
	    	for(int a=0;a<((List<VWebbussort>)map_all.get(month)).size();a++){
	    		VWebbussort sort2=((List<VWebbussort>)map_all.get(month)).get(a);
	    		for(int b=0;b<lists.size();b++){
	    			VWebbussort sort=lists.get(b);
	    			if(sort2.getId().getFactNo().equals(sort.getId().getFactNo())&&sort2.getId().getYymm().equals(sort.getId().getYymm())){
	    				((List<VWebbussort>)map_all.get(month)).remove(a);
	    				((List<VWebbussort>)map_all.get(month)).add(a, sort);
	    				break;
	    			}
	    		}
	    		
	    	}	    			
	    }//for
	   
	    Map<String,Object>map_all2=new LinkedHashMap<String,Object>();
		for (String month:map_all.keySet()) {
			List<List<BigDecimal>> listtemp = new ArrayList<List<BigDecimal>>();
			for (VWebbussort sort : (List<VWebbussort>)map_all.get(month)) {
				List<BigDecimal> list_b = new ArrayList<BigDecimal>();
				if (sort!= null) {
					list_b.add(sort.getA01());
					list_b.add(sort.getA02());
					list_b.add(sort.getA03());
					list_b.add(sort.getA04());
					list_b.add(sort.getA05());
					list_b.add(sort.getA06());
					list_b.add(sort.getA07());
					list_b.add(sort.getSortA06());
					list_b.add(sort.getA08());
					list_b.add(sort.getA09());
					list_b.add(sort.getA10());
					list_b.add(sort.getA11());
					list_b.add(sort.getSortA10());
					list_b.add(sort.getA12());
					list_b.add(sort.getA13());
					list_b.add(sort.getA14());
					list_b.add(sort.getA15());
					list_b.add(sort.getSortA14());
					list_b.add(sort.getA16());
					list_b.add(sort.getA17());
					list_b.add(sort.getA18());
					list_b.add(sort.getA19());
					list_b.add(sort.getA20());
					list_b.add(sort.getA21());
					list_b.add(sort.getA22());
					list_b.add(sort.getA23());
					list_b.add(sort.getA24());
					list_b.add(sort.getA25());
					list_b.add(sort.getA26());
					list_b.add(sort.getA27());
					list_b.add(sort.getA28());
					list_b.add(sort.getSortA28());
					list_b.add(sort.getA29());
					list_b.add(sort.getA30());
					list_b.add(sort.getSortA30());
					list_b.add(sort.getA31());
					list_b.add(sort.getA32());
					list_b.add(sort.getA33());
					list_b.add(sort.getSortA33());
					list_b.add(sort.getA34());
					list_b.add(sort.getA35());
					list_b.add(sort.getSortA34());
					list_b.add(sort.getA36());
					list_b.add(sort.getA37());
					list_b.add(sort.getA38());
					list_b.add(sort.getA39());
					list_b.add(sort.getSortA38());
					list_b.add(sort.getA40());
					list_b.add(sort.getA41());
					list_b.add(sort.getA42());
					list_b.add(sort.getA43());
					list_b.add(sort.getSortA42());
					list_b.add(sort.getA44());
					list_b.add(sort.getA45());
					list_b.add(sort.getA46());
					list_b.add(sort.getA47());
					list_b.add(sort.getSortA46());
					list_b.add(sort.getA48());
					list_b.add(sort.getA49());
					list_b.add(sort.getA50());
					list_b.add(sort.getA51());
					list_b.add(sort.getSortA50());
					list_b.add(sort.getA52());
					list_b.add(sort.getA53());
					list_b.add(sort.getA54());
					list_b.add(sort.getA55());
					list_b.add(sort.getSortA54());
					list_b.add(sort.getA56());
					list_b.add(sort.getA57());
					list_b.add(sort.getA58());
					list_b.add(sort.getA59());
					list_b.add(sort.getSortA58());
					list_b.add(sort.getA60());
					list_b.add(sort.getA61());
					list_b.add(sort.getSortA61());
					list_b.add(sort.getA62());
					list_b.add(sort.getA63());
					list_b.add(sort.getSortA63());
					list_b.add(sort.getA64());
					list_b.add(sort.getA65());
					list_b.add(sort.getSortA65());
					list_b.add(sort.getA66());
					list_b.add(sort.getA67());
					list_b.add(sort.getSortA67());
					list_b.add(sort.getA68());
					list_b.add(sort.getA69());
					list_b.add(sort.getSortA69());
					list_b.add(sort.getA70());
					list_b.add(sort.getA71());
					list_b.add(sort.getA72());
					list_b.add(sort.getA73());
					list_b.add(sort.getA74());
					list_b.add(sort.getA75());
					list_b.add(sort.getSortA75());
					list_b.add(sort.getA76());
					list_b.add(sort.getA77());
					list_b.add(sort.getA78());
					list_b.add(sort.getSortA78());
				}
				listtemp.add(list_b);
			}
			map_all2.put(month,listtemp);
		}
		
		/*****************************數據排名(重點)*******************************/
		//for_a:月份循環
		//for_b:項目循環
		//for_c:廠別循環
		 for(String month:map_all.keySet()){//for_a
		    	List<BigDecimal>list=new ArrayList<BigDecimal>();
		    	List<BigDecimal>list2=new ArrayList<BigDecimal>();
		    	List<Integer>list3=new ArrayList<Integer>();
		    	for(int a=0;a<findTemps().size();a++){//for_b		    		
		    		for(int b=0;b<((List<List<BigDecimal>>)map_all2.get(month)).size();b++){//for_c
			    		if(findTemps().get(a).equals("compare obj")){
			    			if(b==0){
			    				//清除内容，为下一次排名做准备
			    				list.clear();
			    				list2.clear();
			    				list3.clear();
			    			}
			    			list.add(((List<List<BigDecimal>>)map_all2.get(month)).get(b).get(a)==null?new BigDecimal(0):((List<List<BigDecimal>>)map_all2.get(month)).get(b).get(a));	
			    			list2.add(((List<List<BigDecimal>>)map_all2.get(month)).get(b).get(a)==null?new BigDecimal(0):((List<List<BigDecimal>>)map_all2.get(month)).get(b).get(a));
			    		}		    		
			    		if(findTemps().get(a).equals("排名")){
			    			Collections.sort(list2);
				    		for(int x=0;x<list.size();x++){
				    			list3.add(GlobalMethod.getIndex(list.get(x), list2));
				    		}
			    			((List<List<BigDecimal>>)map_all2.get(month)).get(b).set(a, new BigDecimal(list3.get(b)));				    		
			    		}
			    	}//for_c
		    	}//for_b		    	
		    }//for_a
		 /*****************************數據排名（重點）*******************************/
		 
		 
	    
		/********************數據源處理*************************/
	    
	    
	   /********************表格初始化和固定內容*************************/
	   this.printStaticContent(wb, map, list_months, list_facts,list_temp, map_types);
	   /********************表格初始化和固定內容*************************/
	   
	   /********************填充數據源*************************/
	   for(String month:map_all2.keySet()){
		   for(int a=0;a<((List<List<BigDecimal>>)map_all2.get(month)).size();a++){
			   int tempsize=((List<List<BigDecimal>>)map_all2.get(month)).get(a).size();
			   if(tempsize==0){
				   /*tempsize=list_temp.size();
				   for(int b=0;b<tempsize;b++){				   
					   wb.getSheet(month).getRow(2+b).getCell(3+a).setCellValue("無數據");
					   wb.getSheet(month).getRow(2+b).getCell(3+a).setCellStyle(cs);
				   }*/				   
			   }else{
				   for(int b=0;b<tempsize;b++){				   
					   wb.getSheet(month).getRow(2+b).getCell(3+a).setCellValue(((List<List<BigDecimal>>)map_all2.get(month)).get(a).get(b)==null?0:((List<List<BigDecimal>>)map_all2.get(month)).get(a).get(b).doubleValue());
					   wb.getSheet(month).getRow(2+b).getCell(3+a).setCellStyle(cs);
				   }
			   }
			  
		   }
	   }	   
	   /********************填充數據源*************************/
	   
	   
		try {
			/*OutputStream os = new FileOutputStream("E:/" + "websort.xls");
			wb.write(os);
			os.close();	*/
			ServletOutputStream os=response.getOutputStream();
			response.setContentType("application/vnd.ms-excel");
			int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");//判斷是否為IE瀏覽器,大於0則為IE瀏覽器
			String fileName="report"+yymm+"-"+yymm2+".xls";
			if(msie>0){
				fileName=java.net.URLEncoder.encode(fileName,"utf-8");//解決IE中文文件不能下載的問題
			}else{
				fileName=new String(fileName.getBytes("utf-8"),"iso-8859-1");//解決非IE中文名亂碼問題
			}		
			response.setHeader("Content-disposition", "attachment;filename="+fileName);					
			wb.write(os);
			os.close();						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
	 * 所有分類  所有項目
	 * @Title: findTypes
	 * @Description: TODO
	 * @param @return
	 * @return List<String>
	 * @throws
	 * @author web
	 * @date 2016/4/6
	 */
	public Map<String,Object> findTypes() {
		Map<String,Object>map = new LinkedHashMap<String,Object>();
		List<String>list_1=new ArrayList<String>();
		List<String>list_2=new ArrayList<String>();
		List<String>list_3=new ArrayList<String>();
		List<String>list_4=new ArrayList<String>();
		List<String>list_5=new ArrayList<String>();
		List<String>list_6=new ArrayList<String>();
		List<String>list_7=new ArrayList<String>();
		List<String>list_8=new ArrayList<String>();
		List<String>list_9=new ArrayList<String>();
		List<String>list_10=new ArrayList<String>();
		List<String>list_11=new ArrayList<String>();
		List<String>list_12=new ArrayList<String>();
		List<String>list_13=new ArrayList<String>();
		List<String>list_14=new ArrayList<String>();
		List<String>list_15=new ArrayList<String>();
		List<String>list_16=new ArrayList<String>();	
						
		//產能
		list_1.add("產能模__模");
		list_1.add("產能雙__雙");
		list_1.add("生產天數__天");
		//水
		list_2.add("用水量__噸");
		list_2.add("用水金額__USD");
		list_2.add("用量單耗__噸/模");
		list_2.add("費用單耗__USD/模");
		list_2.add("用量單耗排名__排名原則");
		//電
		list_3.add("用電量(度)__度");
		list_3.add("用電費用__USD");
		list_3.add("用量單耗__度/模");
		list_3.add("費用單耗__USD/模");
		list_3.add("用量單耗排名__排名原則");
		//蒸汽
		list_4.add("蒸汽用量__噸");
		list_4.add("蒸汽費用__USD");
		list_4.add("用量單耗__噸/模");
		list_4.add("費用單耗__USD/模");
		list_4.add("用量單耗排名__排名原則");
		//總工務費用金額(USD)
		list_5.add("雜項購置__USD");
		list_5.add("雜項支出-其他__USD");
		list_5.add("電腦耗材__USD");
		list_5.add("文具用品類__USD");
		list_5.add("修繕類-機器設備__USD");
		list_5.add("修繕費-其它類__USD");
		list_5.add("車輛維修費__USD");
		list_5.add("服裝費__USD");
		list_5.add("清潔/消毒費__USD");
		list_5.add("工程整改費__USD");
		list_5.add("工傷__USD");
		list_5.add("費用小計__USD");
		list_5.add("費用小計單耗__USD/模");
		list_5.add("金額單耗排名__排名原則");
		//成倉
		list_6.add("成品庫存__雙");
		list_6.add("天數__天");
		list_6.add("天數排名__排名原則");
		//原物料
		list_7.add("原料庫存量__KG");
		list_7.add("原料庫存金額__USD");
		list_7.add("原料庫存天數__天");
		list_7.add("天數排名__排名原則");
		list_7.add("呆滯料庫存__KG");
		list_7.add("呆滯料庫存金額(USD)__USD");
		list_7.add("呆滯料庫存量排名__排名原則");
		//防霜劑
		list_8.add("防霜劑用量__KG");
		list_8.add("防霜劑金額__USD");
		list_8.add("防霜劑用量單耗__KG/模");
		list_8.add("防霜劑金額單耗__USD/模");
		list_8.add("用量單耗排名__排名原則");
		//色料
		list_9.add("色料用量__KG");
		list_9.add("色料金額__USD");
		list_9.add("色料用量單耗__KG/模");
		list_9.add("色料金額單耗__USD/模");
		list_9.add("用量單耗排名__排名原則");
		//促進劑
		list_10.add("藥品用量__KG");
		list_10.add("藥品金額__USD");
		list_10.add("藥品用量單耗__KG/模");
		list_10.add("藥品金額單耗__USD/模");
		list_10.add("用量單耗排名__排名原則");
		//防粘劑
		list_11.add("防粘劑用量__KG");
		list_11.add("防粘劑金額__USD");
		list_11.add("防粘劑用量單耗__KG/模");
		list_11.add("防粘劑金額單耗__USD/模");
		list_11.add("用量單耗排名__排名原則");
		//油漆/處理劑
		list_12.add("油漆溶劑用量__KG");
		list_12.add("油漆溶劑金額__USD");
		list_12.add("油漆溶劑用量單耗__KG/模");
		list_12.add("油漆溶劑金額單耗__USD/模");
		list_12.add("用量單耗排名__排名原則");
		//離型劑
		list_13.add("離型劑用量__KG");
		list_13.add("離型劑金額__USD");
		list_13.add("離型劑用量單耗__KG/模");
		list_13.add("離型劑金額單耗__USD/模");
		list_13.add("用量單耗排名__排名原則");
		//人工費用
		list_14.add("直接工資__USD");
		list_14.add("直工工資單耗__USD/模");
		list_14.add("直工工資單耗排名__排名原則");
		list_14.add("間接工資__USD");
		list_14.add("間接工資單耗__USD/模");
		list_14.add("間接工資單耗排名__排名原則");
		list_14.add("加班費金額__USD");
		list_14.add("加班費單耗__USD/模");
		list_14.add("加班費單耗排名__排名原則");
		list_14.add("獎金金額__USD");
		list_14.add("獎金單耗__USD/模");
		list_14.add("獎金單耗排名__排名原則");
		list_14.add("其加金額__USD");
		list_14.add("其加單耗__USD/模");
		list_14.add("其加單耗排名__排名原則");
		//其他
		list_15.add("模具修理費__USD");
		list_15.add("差旅費__USD");
		list_15.add("交際費用__USD");
		list_15.add("包裝費用__USD");
		list_15.add("其它費用小計__USD");
		list_15.add("費用小計單耗__USD/模");
		list_15.add("費用單耗排名__排名原則");
		//廢品倉
		list_16.add("廢品倉報廢重量__KG");
		list_16.add("廢品倉報廢金額__USD");
		list_16.add("重量單耗__KG/模");
		list_16.add("重量單耗排名__排名原則");
		
		map.put("產能",list_1);
		map.put("水",list_2);
		map.put("電",list_3);
		map.put("蒸汽",list_4);
		map.put("總工務費用金額(USD)成倉",list_5);
		map.put("成倉", list_6);
		map.put("原物料",list_7);
		map.put("防霜劑",list_8);
		map.put("色料",list_9);
		map.put("促進劑",list_10);
		map.put("防粘劑",list_11);
		map.put("油漆/處理劑",list_12);
		map.put("離型劑",list_13);
		map.put("人工費用",list_14);
		map.put("其他",list_15);
		map.put("廢品倉",list_16);

		return map;

	}
	
		
	
	
	/**
	 * 臨時集合
	 * 【compare obj】:代表要排名的項目
	 * 【排名】：顯示排名出來的結果
	 * 注意，一定要與各實體對象各項一一對應
	 * 實體對象各項有增刪時，此也要同時更新
	 * @Title: findTemps
	 * @Description: TODO
	 * @param @return
	 * @return List<String>
	 * @throws
	 * @author web
	 * @date 2016/4/11
	 */
	public List<String> findTemps() {
		List<String> list = new ArrayList<String>();

		list.add("產能模");
		list.add("產能雙");
		list.add("生產天數");
		list.add("用水量");
		list.add("用水金額");
		list.add("compare obj");
		list.add("費用單耗");
		list.add("排名");
		list.add("用電量(度)");
		list.add("用電費用");
		list.add("compare obj");
		list.add("費用單耗");
		list.add("排名");
		list.add("蒸汽用量");
		list.add("蒸汽費用");
		list.add("compare obj");
		list.add("費用單耗");
		list.add("排名");
		list.add("雜項購置");
		list.add("雜項支出-其他");
		list.add("電腦耗材");
		list.add("文具用品類");
		list.add("修繕類-機器設備");
		list.add("修繕費-其它類");
		list.add("車輛維修費");
		list.add("服裝費");
		list.add("清潔/消毒費");
		list.add("工程整改費");
		list.add("工傷");
		list.add("費用小計");
		list.add("compare obj");
		list.add("排名");
		list.add("成品庫存");
		list.add("compare obj");
		list.add("排名");
		list.add("原料庫存量");
		list.add("原料庫存金額");
		list.add("compare obj");
		list.add("排名");
		list.add("compare obj");
		list.add("呆滯料庫存金額(USD)");
		list.add("排名");
		list.add("防霜劑用量");
		list.add("防霜劑金額");
		list.add("compare obj");
		list.add("防霜劑金額單耗");
		list.add("排名");
		list.add("色料用量");
		list.add("色料金額");
		list.add("compare obj");
		list.add("色料金額單耗");
		list.add("排名");
		list.add("藥品用量");
		list.add("藥品金額");
		list.add("compare obj");
		list.add("藥品金額單耗");
		list.add("排名");
		list.add("防粘劑用量");
		list.add("防粘劑金額");
		list.add("compare obj");
		list.add("防粘劑金額單耗");
		list.add("排名");
		list.add("油漆溶劑用量");
		list.add("油漆溶劑金額");
		list.add("compare obj");
		list.add("油漆溶劑金額單耗");
		list.add("排名");
		list.add("離型劑用量");
		list.add("離型劑金額");
		list.add("compare obj");
		list.add("離型劑金額單耗");
		list.add("排名");
		list.add("直接工資");
		list.add("compare obj");
		list.add("排名");
		list.add("間接工資");
		list.add("compare obj");
		list.add("排名");
		list.add("加班費金額");
		list.add("compare obj");
		list.add("排名");
		list.add("獎金金額");
		list.add("compare obj");
		list.add("排名");
		list.add("其加金額");
		list.add("compare obj");
		list.add("排名");
		list.add("模具修理費");
		list.add("差旅費");
		list.add("交際費用");
		list.add("包裝費用");
		list.add("其它費用小計");
		list.add("compare obj");
		list.add("排名");
		list.add("廢品倉報廢重量");
		list.add("廢品倉報廢金額");
		list.add("compare obj");
		list.add("排名");
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
	 * 表格固定內容
	 * @Title: printStaticContent
	 * @Description: TODO
	 * @param @param sheet
	 * @return void
	 * @throws
	 * @author web
	 * @date 2016/4/7
	 */
	public void printStaticContent(HSSFWorkbook wb,Map<String,Object>map,List<String>list_months,List<Object[]>list_facts,List<String>list_temp,Map<String,Object>map_types){
		HSSFCellStyle cs=(HSSFCellStyle)map.get("cs");
		HSSFCellStyle cs_head=(HSSFCellStyle)map.get("cs_head");
		HSSFCellStyle cs_column=(HSSFCellStyle)map.get("cs_column");
		HSSFCellStyle cs_blue=(HSSFCellStyle)map.get("cs_blue");
		HSSFCellStyle cs_bold=(HSSFCellStyle)map.get("cs_bold");
		 /********************初始化表格*************************/
	    for(String month:list_months){
	    	wb.createSheet(month);
	    	for(int b=0;b<list_temp.size()+5;b++){
		    	wb.getSheet(month).createRow(b);
		    	for(int c=0;c<list_facts.size()+5;c++){
		    		//wb.getSheet(month).getRow(b).createCell(c).setCellStyle(cs);
		    		wb.getSheet(month).getRow(b).createCell(c);
		    		wb.getSheet(month).setColumnWidth(c, 4500);
		    	}
		    }
	    }	    
	    /********************初始化表格*************************/
	    
	    
	    for(String month:list_months){//for 1
	    	HSSFSheet sheet=wb.getSheet(month);
	    	/*******************標題*****************/
	    	CellRangeAddress cra_title=new CellRangeAddress(0,(short)0,0,(short)6);
	    	sheet.addMergedRegion(cra_title);
	    	sheet.getRow(0).getCell(0).setCellValue(month+"加久各廠數據面檢核評比表");
	    	for(int a=0;a<6;a++){
	    		sheet.getRow(0).getCell(a).setCellStyle(cs_head);
	    	}
	    	/*******************標題*****************/
	    	
	    	/*******************表頭*****************/
	    	List<String>list_head=new ArrayList<String>();
	    	list_head.add("分類");
	    	list_head.add("項目");
	    	list_head.add("單位");
	    	for(Object[] objs:list_facts){
	    		list_head.add(objs[1].toString());
	    	}
	    	for(int a=0;a<list_head.size();a++){
	    		sheet.getRow(1).getCell(a).setCellValue(list_head.get(a));
	    		sheet.getRow(1).getCell(a).setCellStyle(cs_column);
	    	}
	    	/*******************表頭*****************/
	    	
	    	/********************分類+項目+單位*******************/
	    	int idx1=2;
    		int idx2=2;
	    	for(String key:map_types.keySet()){
	    		 idx1=idx2;
	    		 idx2=idx1+((List<String>)map_types.get(key)).size();
	    		CellRangeAddress cra_type=new CellRangeAddress(idx1,idx2-1,0,0);
	    		sheet.addMergedRegion(cra_type);
	    		sheet.getRow(idx1).getCell(0).setCellValue(key);
	    		for(int x=idx1;x<idx2;x++){
	    			sheet.getRow(x).getCell(0).setCellStyle(cs_bold);
	    		}
	    		//項目,單位
	    		for(int y=0;y<((List<String>)map_types.get(key)).size();y++){
	    			for(int z=0;z<((List<String>)map_types.get(key)).get(y).split("__").length;z++){
	    				sheet.getRow(y+idx1).getCell(1+z).setCellValue(((List<String>)map_types.get(key)).get(y).split("__")[z]);
	    				if(((List<String>)map_types.get(key)).get(y).contains("排名")){
		    				CellRangeAddress cra=new CellRangeAddress(y+idx1,y+idx1,1,2);//合併單元格，隻顯示第一單元 格內容
			    			sheet.addMergedRegion(cra);
		    				sheet.getRow(y+idx1).getCell(1+z).setCellStyle(cs_blue);
		    			}else{
		    				sheet.getRow(y+idx1).getCell(1+z).setCellStyle(cs_bold);
		    			}
	    			}	    				    				    			
	    		}
	    	}	    	
	    	/********************分類+項目+單位*******************/
	    	
	    }//for1
	}
	
	public List<BigDecimal> initList(){
		List<BigDecimal>list=new ArrayList<BigDecimal>();
		for(int a=0;a<100;a++){
			list.add(new BigDecimal(0.0));
		}
		return list;
	}
	
}
