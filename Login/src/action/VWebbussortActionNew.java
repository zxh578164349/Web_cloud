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
import entity.VWebbussortFcode;
import entity.VWebbussortFcodeId;
import entity.VWebbussortId;
import entity.VWebbussort;
import entity.VWebbussortId;
import entity.WebFact;
import entity.WebFactId;
import entity.WebVwebbussortItemn;
import entity.WebVwebussortSubitem;

import services.IVWebbussortServices;
import services.IWebFactServices;
import services.IWebVwebbussortItemnServices;
import util.GlobalMethod;

/**
 * 
* 項目名稱：WebLogin   
* 類名稱：VWebbussortActionNew   
* 類描述：經營評比
* 創建人：KY2
 */
public class VWebbussortActionNew extends ActionSupport implements ServletResponseAware{
	private String yymm;
	private String yymm2;
	private List<String>list_factno;
	private List<String>list_factcode;
	private IVWebbussortServices vwebbusssorSer;
	private IWebFactServices webFactSer;
	private IWebVwebbussortItemnServices webbussitemSer;
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
	
	public void setWebbussitemSer(IWebVwebbussortItemnServices webbussitemSer) {
		this.webbussitemSer = webbussitemSer;
	}
	
	/**
	 * 經營評比（不分形態）
	 * @throws ParseException
	 */
	public void print() throws ParseException{		
		HSSFWorkbook wb=new HSSFWorkbook();
		Map<String,Object>map=this.findStyles(wb);
		HSSFCellStyle cs=(HSSFCellStyle)map.get("cs");
		HSSFCellStyle cs_red2=(HSSFCellStyle)map.get("cs_red2");
		List<VWebbussort>lists=vwebbusssorSer.findByYymm(yymm, yymm2);//查詢到的數據
		List<String>list_months=GlobalMethod.findMonths(yymm, yymm2);//所有月份
		List<Object[]>list_facts=webFactSer.findFactAble2();//所有廠別		
		Map<String,Object>map_types=this.findTypes();//類型和項目 		
		List<WebVwebussortSubitem>list_temp=findTemps();//臨時集合
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
					//產能
					list_b.add(sort.getA01());
					list_b.add(sort.getA02());
					list_b.add(sort.getA03());
					//原物料
					list_b.add(sort.getA31());
					list_b.add(sort.getA32());
					list_b.add(sort.getA33());
					list_b.add(sort.getSortA33());					
					list_b.add(sort.getA34());
					list_b.add(sort.getA35());
					list_b.add(sort.getSortA34());
					//防霜劑
					list_b.add(sort.getA36());
					list_b.add(sort.getA37());
					list_b.add(sort.getA38());
					list_b.add(sort.getA39());
					list_b.add(sort.getSortA38());
					//色料
					list_b.add(sort.getA40());
					list_b.add(sort.getA41());
					list_b.add(sort.getA42());
					list_b.add(sort.getA43());
					list_b.add(sort.getSortA42());
					//促進劑
					list_b.add(sort.getA44());
					list_b.add(sort.getA45());
					list_b.add(sort.getA46());
					list_b.add(sort.getA47());
					list_b.add(sort.getSortA46());
					//防粘劑
					list_b.add(sort.getA48());
					list_b.add(sort.getA49());
					list_b.add(sort.getA50());
					list_b.add(sort.getA51());
					list_b.add(sort.getSortA50());
					//油漆處理劑
					list_b.add(sort.getA52());
					list_b.add(sort.getA53());
					list_b.add(sort.getA54());
					list_b.add(sort.getA55());
					list_b.add(sort.getSortA54());
					//離型劑
					list_b.add(sort.getA56());
					list_b.add(sort.getA57());
					list_b.add(sort.getA58());
					list_b.add(sort.getA59());
					list_b.add(sort.getSortA58());
					//水
					list_b.add(sort.getA04());//用水量
					list_b.add(sort.getA05());//用水金額
					//北越加久在水"用量單耗"不排名,就把"用量單耗"設爲最大，排名就在最後，便於去除
					if(sort.getId().getFactNo().equals("GH")||sort.getId().getFactNo().equals("FVAS")){
						sort.setA06(new BigDecimal(999999999.00));
					}
					list_b.add(sort.getA06());//用量單耗
					list_b.add(sort.getA07());//費用單耗
					list_b.add(sort.getSortA06());//用量排名
					//電
					list_b.add(sort.getA08());
					list_b.add(sort.getA09());
					list_b.add(sort.getA10());
					list_b.add(sort.getA11());
					list_b.add(sort.getSortA10());
					//蒸汽
					list_b.add(sort.getA12());
					list_b.add(sort.getA13());
					list_b.add(sort.getA14());
					list_b.add(sort.getA15());
					list_b.add(sort.getSortA14());
					//總工務費用
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
					//其它
					list_b.add(sort.getA70());
					list_b.add(sort.getA71());
					list_b.add(sort.getA72());
					list_b.add(sort.getA73());
					list_b.add(sort.getA74());
					list_b.add(sort.getA75());
					list_b.add(sort.getSortA75());
					//人工費用
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
					//成倉
					list_b.add(sort.getA29());
					list_b.add(sort.getA30());
					list_b.add(sort.getSortA30());
					//廢品倉
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
		 for(String month:map_all2.keySet()){//for_a
		    	List<BigDecimal>list=new ArrayList<BigDecimal>();
		    	List<BigDecimal>list2=new ArrayList<BigDecimal>();
		    	List<Integer>list3=new ArrayList<Integer>();
		    	for(int a=0;a<list_temp.size();a++){//for_b		    		
		    		for(int b=0;b<((List<List<BigDecimal>>)map_all2.get(month)).size();b++){//for_c
			    		if(list_temp.get(a).getItemname().contains("compare obj")){
			    			if(b==0){
			    				//清除内容，为下一次排名做准备
			    				list.clear();
			    				list2.clear();
			    				list3.clear();
			    			}
			    			list.add(((List<List<BigDecimal>>)map_all2.get(month)).get(b).get(a)==null?new BigDecimal(0):((List<List<BigDecimal>>)map_all2.get(month)).get(b).get(a));	
			    			list2.add(((List<List<BigDecimal>>)map_all2.get(month)).get(b).get(a)==null?new BigDecimal(0):((List<List<BigDecimal>>)map_all2.get(month)).get(b).get(a));
			    		}		    		
			    		if(list_temp.get(a).getItemname().contains("排名")){
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
					   
					   /******************************北越加九用水方面不排名處理******************************/
					   if((wb.getSheet(month).getRow(1).getCell(3+a).getStringCellValue().equals("北越加九")||
							   wb.getSheet(month).getRow(1).getCell(3+a).getStringCellValue().equals("越南賜佳") )
							   &&(list_temp.get(b).getWebVwebbussortItemn().getMid()==900) ){
						   wb.getSheet(month).getRow(2+b).getCell(3+a).setCellValue("--");
						   wb.getSheet(month).getRow(2+b).getCell(3+a).setCellStyle(cs_red2);
					   }
					   /******************************北越加九用水方面不排名處理******************************/
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
	 * 經營評比（分形態）
	 * @throws ParseException
	 */
	public void print_fcode() throws ParseException{
			
		HSSFWorkbook wb=new HSSFWorkbook();
		Map<String,Object>map=this.findStyles(wb);
		HSSFCellStyle cs=(HSSFCellStyle)map.get("cs");	
		HSSFCellStyle cs_red2=(HSSFCellStyle)map.get("cs_red2");
		//List<VWebbussort>lists=vwebbusssorSer.findByYymm(yymm, yymm2);//查詢到的數據
		List<String>list_months=GlobalMethod.findMonths(yymm, yymm2);//所有月份
		List<Object[]>list_facts=webFactSer.findFactAble2();//所有廠別		
		Map<String,Object>map_types=this.findTypes();//類型和項目 		
		List<WebVwebussortSubitem>list_temp=findTemps();//臨時集合
		
		List<VWebbussortFcode>lists2=vwebbusssorSer.findByYymm2(yymm, yymm2);//查詢到的數據

		// 如果頁面上有選factcode
		Map<String,Object>map_month=new LinkedHashMap<String,Object>();
		if (list_factcode != null && list_factcode.size() > 0) {
			for(String month:list_months){
				Map<String,Object>map_fcode=new LinkedHashMap<String,Object>();
				for (String fcode:list_factcode) {
					List<VWebbussortFcode> list = new ArrayList<VWebbussortFcode>();
					for (String fact:list_factno) {
						String[] objs = fact.split("_");
						if(objs[0].equals(fcode)){
							list.add(new VWebbussortFcode(new VWebbussortFcodeId(new WebFact(new WebFactId(objs[1],fcode),objs[2]),month)));
						}													
					}
					map_fcode.put(fcode, list);
				}
				map_month.put(month, map_fcode);
			}
			
			// 如果沒有沒有,則默認全部
		} else {
			/*List<Object[]> list_objs = webFactSer.findAllFactCode2_showA();
			for (int i = 0; i < list_objs.size(); i++) {
				Object[] obj = list_objs.get(i);
				String fact_code = obj[0].toString();
			}*/
		}
		
		
		
		/********************數據源處理*************************/	  
	    for(String month:map_month.keySet()){//for	
	    	for(String fcode:((Map<String,Object>)map_month.get(month)).keySet()){
	    		Map<String,Object>map_fcode=((Map<String,Object>)map_month.get(month));
	    		for(int a=0;a<((List<VWebbussortFcode>)map_fcode.get(fcode)).size();a++){
	    			VWebbussortFcode sort2=((List<VWebbussortFcode>)map_fcode.get(fcode)).get(a);
		    		for(int b=0;b<lists2.size();b++){
		    			VWebbussortFcode sort=lists2.get(b);
		    			if(sort2.getId().getFact().getId().getFactNo().equals(sort.getId().getFact().getId().getFactNo())&&sort2.getId().getFact().getId().getFactArea().equals(sort.getId().getFact().getId().getFactArea())&&sort2.getId().getYymm().equals(sort.getId().getYymm())){
		    				((List<VWebbussortFcode>)map_fcode.get(fcode)).remove(a);
		    				((List<VWebbussortFcode>)map_fcode.get(fcode)).add(a, sort);
		    				break;
		    			}
		    		}
		    		
		    	}
	    	}
	    		    			
	    }//for
	   
	    Map<String,Object>map_month2=new LinkedHashMap<String,Object>();	    
		for (String month:map_month.keySet()) {
			Map<String,Object>map_fcode=((Map<String,Object>)map_month.get(month));
			Map<String,Object>map_fcode2=new LinkedHashMap<String,Object>();
			for(String fcode:map_fcode.keySet()){				
				List<List<BigDecimal>> listtemp = new ArrayList<List<BigDecimal>>();
				for (VWebbussortFcode sort : (List<VWebbussortFcode>)map_fcode.get(fcode)) {
					List<BigDecimal> list_b = new ArrayList<BigDecimal>();
					if (sort!= null) {
						//產能
						list_b.add(sort.getA01());
						list_b.add(sort.getA02());
						list_b.add(sort.getA03());
						//原物料
						list_b.add(sort.getA31());
						list_b.add(sort.getA32());
						list_b.add(sort.getA33());
						list_b.add(sort.getSortA33());					
						list_b.add(sort.getA34());
						list_b.add(sort.getA35());
						list_b.add(sort.getSortA34());
						//防霜劑
						list_b.add(sort.getA36());
						list_b.add(sort.getA37());
						list_b.add(sort.getA38());
						list_b.add(sort.getA39());
						list_b.add(sort.getSortA38());
						//色料
						list_b.add(sort.getA40());
						list_b.add(sort.getA41());
						list_b.add(sort.getA42());
						list_b.add(sort.getA43());
						list_b.add(sort.getSortA42());
						//促進劑
						list_b.add(sort.getA44());
						list_b.add(sort.getA45());
						list_b.add(sort.getA46());
						list_b.add(sort.getA47());
						list_b.add(sort.getSortA46());
						//防粘劑
						list_b.add(sort.getA48());
						list_b.add(sort.getA49());
						list_b.add(sort.getA50());
						list_b.add(sort.getA51());
						list_b.add(sort.getSortA50());
						//油漆處理劑
						list_b.add(sort.getA52());
						list_b.add(sort.getA53());
						list_b.add(sort.getA54());
						list_b.add(sort.getA55());
						list_b.add(sort.getSortA54());
						//離型劑
						list_b.add(sort.getA56());
						list_b.add(sort.getA57());
						list_b.add(sort.getA58());
						list_b.add(sort.getA59());
						list_b.add(sort.getSortA58());
						//水
						list_b.add(sort.getA04());//用水量
						list_b.add(sort.getA05());//用水金額
						//北越加久在水"用量單耗"不排名,就把"用量單耗"設爲最大，排名就在最後，便於去除
						if(sort.getId().getFact().getId().getFactNo().equals("GH")||sort.getId().getFact().getId().getFactNo().equals("FVAS")){
							sort.setA06(new BigDecimal(999999999.00));
						}
						list_b.add(sort.getA06());//用量單耗
						list_b.add(sort.getA07());//費用單耗
						list_b.add(sort.getSortA06());//用量排名
						//電
						list_b.add(sort.getA08());
						list_b.add(sort.getA09());
						list_b.add(sort.getA10());
						list_b.add(sort.getA11());
						list_b.add(sort.getSortA10());
						//蒸汽
						list_b.add(sort.getA12());
						list_b.add(sort.getA13());
						list_b.add(sort.getA14());
						list_b.add(sort.getA15());
						list_b.add(sort.getSortA14());
						//總工務費用
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
						//其它
						list_b.add(sort.getA70());
						list_b.add(sort.getA71());
						list_b.add(sort.getA72());
						list_b.add(sort.getA73());
						list_b.add(sort.getA74());
						list_b.add(sort.getA75());
						list_b.add(sort.getSortA75());
						//人工費用
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
						//成倉
						list_b.add(sort.getA29());
						list_b.add(sort.getA30());
						list_b.add(sort.getSortA30());
						//廢品倉
						list_b.add(sort.getA76());
						list_b.add(sort.getA77());
						list_b.add(sort.getA78());
						list_b.add(sort.getSortA78());
						
						
						
					}
					listtemp.add(list_b);
				}
				map_fcode2.put(fcode,listtemp);
			}			
			map_month2.put(month,map_fcode2);
		}
		
		/*****************************數據排名(重點)*******************************/
		//for_a:月份循環
		//for_b:項目循環
		//for_c:廠別循環
		 for(String month:map_month2.keySet()){//for_a	
			 Map<String,Object>map_fcode=(Map<String,Object>)map_month2.get(month);
			 for(String fcode:map_fcode.keySet()){
				List<BigDecimal>list=new ArrayList<BigDecimal>();
			    List<BigDecimal>list2=new ArrayList<BigDecimal>();
			    List<Integer>list3=new ArrayList<Integer>();
		    	for(int a=0;a<list_temp.size();a++){//for_b		    				    				    			
				    	for(int b=0;b<((List<List<BigDecimal>>)map_fcode.get(fcode)).size();b++){//for_c
				    		if(list_temp.get(a).getItemname().contains("compare obj")){
				    			if(b==0){
				    				//清除内容，为下一次排名做准备
				    				list.clear();
				    				list2.clear();
				    				list3.clear();
				    			}
				    			list.add(((List<List<BigDecimal>>)map_fcode.get(fcode)).get(b).get(a)==null?new BigDecimal(0):((List<List<BigDecimal>>)map_fcode.get(fcode)).get(b).get(a));	
				    			list2.add(((List<List<BigDecimal>>)map_fcode.get(fcode)).get(b).get(a)==null?new BigDecimal(0):((List<List<BigDecimal>>)map_fcode.get(fcode)).get(b).get(a));
				    		}		    		
				    		if(list_temp.get(a).getItemname().contains("排名")){
				    			Collections.sort(list2);
					    		for(int x=0;x<list.size();x++){
					    			list3.add(GlobalMethod.getIndex(list.get(x), list2));
					    		}
				    			((List<List<BigDecimal>>)map_fcode.get(fcode)).get(b).set(a, new BigDecimal(list3.get(b)));				    		
				    		}
				    	}//for_c		    				    		
		    	}//for_b
			 }	
		    }//for_a
		 /*****************************數據排名（重點）*******************************/
		 
		 
	    
		/********************數據源處理*************************/
	    
	    
	   /********************表格初始化和固定內容*************************/
	   this.printStaticContent2(wb, map, list_months, list_facts,list_temp, map_types,map_month);
	   /********************表格初始化和固定內容*************************/
	   
	   /********************填充數據源*************************/
	   for(String month:map_month2.keySet()){//for a
		   int y_increment=0;//廠別狀態自增1
	       int y_fcode=0;	//廠別狀態增長量
	       int y_index=0;//y軸座標跟蹤
		   Map<String,Object>map_fcode=(Map<String,Object>)map_month2.get(month);		   
		   for(String fcode:map_fcode.keySet()){// for b			   
			 //計算廠別狀態增長量
	    		if(y_increment>0){
		    		y_fcode=(list_temp.size()+3)*y_increment;
		    	}
			   for(int a=0;a<((List<List<BigDecimal>>)map_fcode.get(fcode)).size();a++){
				   int tempsize=((List<List<BigDecimal>>)map_fcode.get(fcode)).get(a).size();
				   if(tempsize==0){
					  /* tempsize=list_temp.size();
					   for(int b=0;b<tempsize;b++){				   
						   wb.getSheet(month).getRow(2+b).getCell(3+a).setCellValue("無數據");
						   wb.getSheet(month).getRow(2+b).getCell(3+a).setCellStyle(cs);
					   }*/				   
				   }else{
					   for(int b=0;b<tempsize;b++){	
						   y_index=4+b+y_fcode;//y軸座標跟蹤
						   wb.getSheet(month).getRow(y_index).getCell(3+a).setCellValue(((List<List<BigDecimal>>)map_fcode.get(fcode)).get(a).get(b)==null?0:((List<List<BigDecimal>>)map_fcode.get(fcode)).get(a).get(b).doubleValue());
						   wb.getSheet(month).getRow(y_index).getCell(3+a).setCellStyle(cs);
						   /******************************北越加九用水方面不排名處理******************************/
						   if((wb.getSheet(month).getRow(3+y_fcode).getCell(3+a).getStringCellValue().equals("北越加九")||
								   wb.getSheet(month).getRow(3+y_fcode).getCell(3+a).getStringCellValue().equals("越南賜佳") )
								   &&(list_temp.get(b).getWebVwebbussortItemn().getMid()==900)){
							   wb.getSheet(month).getRow(y_index).getCell(3+a).setCellValue("--");
							   wb.getSheet(month).getRow(y_index).getCell(3+a).setCellStyle(cs_red2);
						   }
						   /******************************北越加九用水方面不排名處理******************************/
						   
						   /********************過濾處理********************/
						   if(fcode.equals("MD")){
							   if(list_temp.get(b).getWebVwebbussortItemn().getMid()==300||
								  list_temp.get(b).getWebVwebbussortItemn().getMid()==600){
								   wb.getSheet(month).getRow(y_index).getCell(3+a).setCellValue("/");
								   wb.getSheet(month).getRow(y_index).getCell(3+a).setCellStyle(cs_red2);
							   }
						   }
						   if(fcode.equals("IP")){
							   if(list_temp.get(b).getWebVwebbussortItemn().getMid()==300||
								  list_temp.get(b).getWebVwebbussortItemn().getMid()==500||	   
								  list_temp.get(b).getWebVwebbussortItemn().getMid()==600){
								   wb.getSheet(month).getRow(y_index).getCell(3+a).setCellValue("/");
								   wb.getSheet(month).getRow(y_index).getCell(3+a).setCellStyle(cs_red2);
							   }
						   }
						   if(fcode.equals("PU")){
							   if(list_temp.get(b).getWebVwebbussortItemn().getMid()==300||
								  list_temp.get(b).getWebVwebbussortItemn().getMid()==500||
								  list_temp.get(b).getWebVwebbussortItemn().getMid()==600||
								  list_temp.get(b).getWebVwebbussortItemn().getMid()==1100){
								   wb.getSheet(month).getRow(y_index).getCell(3+a).setCellValue("/");
								   wb.getSheet(month).getRow(y_index).getCell(3+a).setCellStyle(cs_red2);
							   }
						   }
						   if(fcode.equals("DJ")){
							   if(list_temp.get(b).getWebVwebbussortItemn().getMid()==200||
								  list_temp.get(b).getWebVwebbussortItemn().getMid()==300||
								  list_temp.get(b).getWebVwebbussortItemn().getMid()==400||
								  list_temp.get(b).getWebVwebbussortItemn().getMid()==500||
								  list_temp.get(b).getWebVwebbussortItemn().getMid()==600||
								  list_temp.get(b).getWebVwebbussortItemn().getMid()==800){
								   wb.getSheet(month).getRow(y_index).getCell(3+a).setCellValue("/");
								   wb.getSheet(month).getRow(y_index).getCell(3+a).setCellStyle(cs_red2);
							   }
						   }
						   if(fcode.equals("鞋墊")){
							   if(list_temp.get(b).getWebVwebbussortItemn().getMid()==300||
								  list_temp.get(b).getWebVwebbussortItemn().getMid()==400||
								  list_temp.get(b).getWebVwebbussortItemn().getMid()==500||
								  list_temp.get(b).getWebVwebbussortItemn().getMid()==600||
							      list_temp.get(b).getWebVwebbussortItemn().getMid()==700||
								  list_temp.get(b).getWebVwebbussortItemn().getMid()==800){
								   wb.getSheet(month).getRow(y_index).getCell(3+a).setCellValue("/");
								   wb.getSheet(month).getRow(y_index).getCell(3+a).setCellStyle(cs_red2);
							   }
						   }
						   /********************過濾處理********************/
						   
					   }
				   }
				  
			   }
			   y_increment++;
		   }//for b
		   
	   }//for a	   
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
		List<WebVwebbussortItemn>list=webbussitemSer.findAll();
		List<WebVwebussortSubitem>list2=webbussitemSer.findAll2();
		for(WebVwebbussortItemn item:list){
			List<WebVwebussortSubitem>list3=new ArrayList<WebVwebussortSubitem>();
			for(WebVwebussortSubitem sub:list2){
				if(item.getMid().equals(sub.getWebVwebbussortItemn().getMid())){
					list3.add(sub);
				}
			}
			map.put(item.getItemname()+"__"+item.getMid(), list3);
		}
		
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
	public List<WebVwebussortSubitem> findTemps() {		
		List<WebVwebussortSubitem>list=webbussitemSer.findAll2();
		return list;

	}
	
	/**
	 * 表格樣式
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
		
		//紅色粗字體樣式(有邊框)
		HSSFCellStyle cs_red=wb.createCellStyle();
		cs_red.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_red.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		
		cs_red.setFont(font_red);
		map.put("cs_red", cs_red);
		
		//紅色粗字體樣式(有邊框)
		HSSFCellStyle cs_red2=wb.createCellStyle();
		cs_red2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_red2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_red2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_red2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_red2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_red2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_red2.setFont(font_red);
		map.put("cs_red2", cs_red2);
		
		//紅色粗字體樣式(有邊框背景)
		HSSFCellStyle cs_red3=wb.createCellStyle();
		cs_red3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_red3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_red3.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_red3.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_red3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_red3.setBorderLeft(HSSFCellStyle.BORDER_THIN);		
		cs_red3.setFillForegroundColor(IndexedColors.RED.getIndex());
		cs_red3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cs_red3.setFont(font_bold);
		map.put("cs_red3", cs_red3);
		
		
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
	public void printStaticContent(HSSFWorkbook wb,Map<String,Object>map,List<String>list_months,List<Object[]>list_facts,List<WebVwebussortSubitem>list_temp,Map<String,Object>map_types){
		//HSSFCellStyle cs=(HSSFCellStyle)map.get("cs");
		HSSFCellStyle cs_head=(HSSFCellStyle)map.get("cs_head");
		HSSFCellStyle cs_column=(HSSFCellStyle)map.get("cs_column");
		HSSFCellStyle cs_blue=(HSSFCellStyle)map.get("cs_blue");
		HSSFCellStyle cs_bold=(HSSFCellStyle)map.get("cs_bold");
		 /********************初始化表格*************************/
	    for(String month:list_months){
	    	wb.createSheet(month);
	    	for(int b=0;b<list_temp.size()+4;b++){
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
	    	for(String key:map_types.keySet()){//for a
	    		 idx1=idx2;
	    		 idx2=idx1+((List<WebVwebussortSubitem>)map_types.get(key)).size();
	    		CellRangeAddress cra_type=new CellRangeAddress(idx1,idx2-1,0,0);
	    		sheet.addMergedRegion(cra_type);
	    		sheet.getRow(idx1).getCell(0).setCellValue(key.split("__")[0]);
	    		for(int x=idx1;x<idx2;x++){
	    			sheet.getRow(x).getCell(0).setCellStyle(cs_bold);
	    			
	    		}
	    		//項目,單位
	    		for(int y=0;y<((List<WebVwebussortSubitem>)map_types.get(key)).size();y++){//for b
	    			for(int z=0;z<((List<WebVwebussortSubitem>)map_types.get(key)).get(y).getItemname().split("__").length;z++){
	    				if(z>1){
	    					continue;
	    				}
	    				sheet.getRow(y+idx1).getCell(1+z).setCellValue(((List<WebVwebussortSubitem>)map_types.get(key)).get(y).getItemname().split("__")[z]);
	    				if(((List<WebVwebussortSubitem>)map_types.get(key)).get(y).getItemname().contains("排名")){
		    				CellRangeAddress cra=new CellRangeAddress(y+idx1,y+idx1,1,2);//合併單元格，隻顯示第一單元 格內容
			    			sheet.addMergedRegion(cra);
		    				sheet.getRow(y+idx1).getCell(1+z).setCellStyle(cs_blue);
		    			}else{
		    				sheet.getRow(y+idx1).getCell(1+z).setCellStyle(cs_bold);
		    			}
	    			}	    				    				    			
	    		}//for b
	    	}//for a	    	
	    	/********************分類+項目+單位*******************/
	    	
	    }//for1
	}
	
	/**
	 * 表格固定內容(分廠別狀態)
	 * @Title: printStaticContent
	 * @Description: TODO
	 * @param @param sheet
	 * @return void
	 * @throws
	 * @author web
	 * @date 2016/4/7
	 */
	public void printStaticContent2(HSSFWorkbook wb,Map<String,Object>map,List<String>list_months,List<Object[]>list_facts,List<WebVwebussortSubitem>list_temp,Map<String,Object>map_types,Map<String,Object>map_month){
		//HSSFCellStyle cs=(HSSFCellStyle)map.get("cs");
		HSSFCellStyle cs_head=(HSSFCellStyle)map.get("cs_head");
		HSSFCellStyle cs_column=(HSSFCellStyle)map.get("cs_column");
		HSSFCellStyle cs_blue=(HSSFCellStyle)map.get("cs_blue");
		HSSFCellStyle cs_bold=(HSSFCellStyle)map.get("cs_bold");
		HSSFCellStyle cs_red=(HSSFCellStyle)map.get("cs_red");
		HSSFCellStyle cs_red3=(HSSFCellStyle)map.get("cs_red3");
		 /********************初始化表格*************************/
	    for(String month:map_month.keySet()){
	    	wb.createSheet(month);
	    	Map<String,Object>map_fcode=(Map<String,Object>)map_month.get(month);
	    	int y_increment=0;
	    	for(String fcode:map_fcode.keySet()){
	    		for(int b=0;b<list_temp.size()+4*(y_increment+1);b++){
			    	wb.getSheet(month).createRow(b+list_temp.size()*y_increment);
			    	for(int c=0;c<list_facts.size()+5;c++){
			    		//wb.getSheet(month).getRow(b).createCell(c).setCellStyle(cs);
			    		wb.getSheet(month).getRow(b+list_temp.size()*y_increment).createCell(c);
			    		wb.getSheet(month).setColumnWidth(c, 4500);
			    	}
			    }
	    		y_increment++;
	    	}	    	
	    }	    
	    /********************初始化表格*************************/
	    
	    
	    for(String month:map_month.keySet()){//for 1
	    	int y_increment=0;//廠別狀態自增1
	    	int y_fcode=0;	//廠別狀態增長量
	    	int y_index=0;//y軸座標跟蹤
	    	
	    	HSSFSheet sheet=wb.getSheet(month);
	    	/*******************標題*****************/
	    	CellRangeAddress cra_title=new CellRangeAddress(0,(short)0,0,(short)6);
	    	sheet.addMergedRegion(cra_title);
	    	sheet.getRow(0).getCell(0).setCellValue(month+"加久各廠數據面檢核評比表");
	    	for(int a=0;a<6;a++){
	    		sheet.getRow(0).getCell(a).setCellStyle(cs_head);
	    	}
	    	/*******************標題*****************/
	    		    	
	    	Map<String,Object>map_fcode=(Map<String,Object>)map_month.get(month);	    	    	
	    	for(String fcode:map_fcode.keySet()){//for 2	
	    		HSSFCellStyle cs_temp=cs_bold;
	    		//計算廠別狀態增長量
	    		if(y_increment>0){
		    		y_fcode=(list_temp.size()+3)*y_increment;
		    	}
	    		/*******************廠別狀態*****************/
	    		y_index=2+y_fcode;//y軸座標跟蹤
	    		
		    	sheet.getRow(y_index).getCell(0).setCellValue(fcode);
	    		sheet.getRow(y_index).getCell(0).setCellStyle(cs_red);
	    		/*******************廠別狀態*****************/
	    		
	    		/*******************表頭*****************/
	    		y_index=y_index+1;//y軸座標跟蹤
	    		
	    		List<String>list_head=new ArrayList<String>();
		    	list_head.add("分類");
		    	list_head.add("項目");
		    	list_head.add("單位");
		    	for(VWebbussortFcode objs:(List<VWebbussortFcode>)map_fcode.get(fcode)){
		    		list_head.add(objs.getId().getFact().getFactSname());
		    	}
		    	for(int a=0;a<list_head.size();a++){		    		
		    			sheet.getRow(y_index).getCell(a).setCellValue(list_head.get(a));
			    		sheet.getRow(y_index).getCell(a).setCellStyle(cs_column);		    				    		
		    	}
		    	/*******************表頭*****************/
		    	
		    	
		    	
		    	/********************分類+項目+單位*******************/
		    	y_index=y_index+1;//y軸座標跟蹤
		    	
		    	int idx1=y_index;
	    		int idx2=y_index;
		    	for(String key:map_types.keySet()){
		    		/********************過濾處理********************/
		    		if(fcode.equals("MD")){
	    				if(key.split("__")[1].equals("300")||
	    					key.split("__")[1].equals("600")){
	    					cs_temp=cs_red3;
	    				}else{
	    					cs_temp=cs_bold;
	    				}
	    			}
	    			if(fcode.equals("IP")){
	    				if(key.split("__")[1].equals("300")||
		    				key.split("__")[1].equals("500")||
		    				key.split("__")[1].equals("600")){
		    					cs_temp=cs_red3;
		    				}else{
		    					cs_temp=cs_bold;
		    				}
	    			}
	    			if(fcode.equals("PU")){
	    				if(key.split("__")[1].equals("300")||
			    				key.split("__")[1].equals("500")||
			    				key.split("__")[1].equals("600")||
			    				key.split("__")[1].equals("1100")){
			    					cs_temp=cs_red3;
			    				}else{
			    					cs_temp=cs_bold;
			    				}
	    			}
	    			if(fcode.equals("DJ")){
	    				if(key.split("__")[1].equals("200")||
			    				key.split("__")[1].equals("300")||
			    				key.split("__")[1].equals("400")||
			    				key.split("__")[1].equals("500")||
			    				key.split("__")[1].equals("600")||				    				
			    				key.split("__")[1].equals("800")){
			    					cs_temp=cs_red3;
			    				}else{
			    					cs_temp=cs_bold;
			    				}
	    			}
	    			if(fcode.equals("鞋墊")){
	    				if(key.split("__")[1].equals("300")||
			    				key.split("__")[1].equals("400")||
			    				key.split("__")[1].equals("500")||
			    				key.split("__")[1].equals("600")||
			    				key.split("__")[1].equals("700")||				    				
			    				key.split("__")[1].equals("800")){
			    					cs_temp=cs_red3;
			    				}else{
			    					cs_temp=cs_bold;
			    				}
	    			}
	    			/********************過濾處理********************/
		    		idx1=idx2;
		    		idx2=idx1+((List<WebVwebussortSubitem>)map_types.get(key)).size();
		    		CellRangeAddress cra_type=new CellRangeAddress(idx1,idx2-1,0,0);
		    		sheet.addMergedRegion(cra_type);
		    		sheet.getRow(idx1).getCell(0).setCellValue(key.split("__")[0]);
		    		for(int x=idx1;x<idx2;x++){		    			
		    			sheet.getRow(x).getCell(0).setCellStyle(cs_temp);		    			
		    		}
		    		//項目,單位
		    		for(int y=0;y<((List<WebVwebussortSubitem>)map_types.get(key)).size();y++){
		    			for(int z=0;z<((List<WebVwebussortSubitem>)map_types.get(key)).get(y).getItemname().split("__").length;z++){
		    				if(z>1){
		    					continue;
		    				}
		    				sheet.getRow(y+idx1).getCell(1+z).setCellValue(((List<WebVwebussortSubitem>)map_types.get(key)).get(y).getItemname().split("__")[z]);
		    				if(((List<WebVwebussortSubitem>)map_types.get(key)).get(y).getItemname().contains("排名")){
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
		    	y_increment++;
	    	}//for 2
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
