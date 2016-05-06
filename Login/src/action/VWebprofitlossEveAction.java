/**
 * 
 */
package action;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.interceptor.ServletResponseAware;

import entity.VWebprofitlossEve;
import entity.VWebprofitlossEveId;

import services.IVWebprofitlossEveServices;
import services.IWebFactServices;
import util.GlobalMethod;

/**   
 *    
 * 项目名称：Login   
 * 类名称：VWebprofitlossEveAction   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/5/5 上午9:07:56   
 * 修改人：Administrator   
 * 修改时间：2016/5/5 上午9:07:56   
 * 修改备注：   
 * @version    
 *    
 **/
public class VWebprofitlossEveAction implements ServletResponseAware{
	private String year;
	private String yymm;
	private String yymm2;
	private String factNo;
	private IVWebprofitlossEveServices  vwebprolossSer;
	private IWebFactServices webFactSer;
	private javax.servlet.http.HttpServletResponse response;
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
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
	
	public String getFactNo() {
		return factNo;
	}
	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}
	public void setVwebprolossSer(IVWebprofitlossEveServices vwebprolossSer) {
		this.vwebprolossSer = vwebprolossSer;
	}
	
			
	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}
	public void setServletResponse(HttpServletResponse reponse) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	
	public void print_fact() throws IOException{
		year="2016";
		factNo="XS";
		
		HSSFWorkbook wb=new HSSFWorkbook();
		Map<String,Object>map_data=new HashMap<String,Object>();
		Map<String,Object>map_cs=GlobalMethod.findStyles(wb);
		HSSFCellStyle cs=(HSSFCellStyle)map_cs.get("cs");
		List<String>list_head=findHead();//表頭
		//獲取一年所有月份+上一年的12份
		List<String>list_month=new ArrayList<String>();
		list_month.add(Integer.parseInt(year)-1+"12");
		for(int a=1;a<13;a++){
			if(a<10){
				list_month.add(year+"0"+a);
			}else{
				list_month.add(year+a);
			}
		}
		
		//獲取一箇廠的factCode
		List<String>list_factcode=webFactSer.findByFactNo_showA(factNo);
		for(String factcode:list_factcode){//for a
			List<VWebprofitlossEve>list=new ArrayList<VWebprofitlossEve>();
			for(String month:list_month){
				list.add(new VWebprofitlossEve(new VWebprofitlossEveId(factNo,factcode,month)));
			}
			map_data.put(factcode, list);
		}//for a
		
		List<VWebprofitlossEve>list_eve=vwebprolossSer.findByYymm(list_month.get(0), list_month.get(12));//開始：上年12月      結束：今年12月
		
		for(String factcode:map_data.keySet()){//for a
			List<VWebprofitlossEve> list=(List<VWebprofitlossEve>)map_data.get(factcode);
			for(int b=0;b<list.size();b++){//for b
				for(VWebprofitlossEve eve:list_eve){
					if(list.get(b).getId().getFactNo().equals(eve.getId().getFactNo())
					   &&list.get(b).getId().getFactCode().equals(eve.getId().getFactCode())
					   &&list.get(b).getId().getYymm().equals(eve.getId().getYymm())){
						list.remove(b);
						list.add(b, eve);
						break;
					}					
				}
			}//for b
			List<List<String>>list_a=new ArrayList<List<String>>();
			for(int b=1;b<list.size();b++){
				List<String>list_b=this.findResult(1, list.get(b), list.get(b-1));
				list_a.add(list_b);
			}
			map_data.put(factcode, list_a);
			
			
			
			
		}//for a
		
		
		
		
		this.init(wb, map_data, map_cs,list_head);
		for(String factcode:map_data.keySet()){
			HSSFSheet sheet=wb.getSheet(factcode);
			List<List<String>>list_a=(List<List<String>>)map_data.get(factcode);
			for(int a=0;a<list_a.size();a++){
				List<String>list_b=list_a.get(a);
				for(int b=0;b<list_b.size();b++){
					sheet.getRow(4+b).getCell(3+a).setCellValue(list_b.get(b));
					sheet.getRow(4+b).getCell(3+a).setCellStyle(cs);
				}
			}
		}
		OutputStream os=new FileOutputStream("e:\\webprofiless.xls");
		wb.write(os);
		os.close();
	}
	
	public void print_month() throws ParseException, IOException{
		factNo="632";
		HSSFWorkbook wb=new HSSFWorkbook();
		Map<String,Object>map_data=new HashMap<String,Object>();
		Map<String,Object>map_cs=GlobalMethod.findStyles(wb);
		List<String>list_head=findHead2();//表頭
		List<String>list_month=new ArrayList<String>();
		Calendar cal=Calendar.getInstance();
		DateFormat fmt=new SimpleDateFormat("yyyyMM");
		cal.setTime(fmt.parse(yymm));
		cal.add(Calendar.MONTH, -1);
		list_month.add(fmt.format(cal.getTime()));//開始月份的上月
		for(int a=3;a<list_head.size()-1;a++){
			list_month.add(list_head.get(a));
		}
		
		//獲取一箇廠的factCode
				List<String>list_factcode=webFactSer.findByFactNo_showA(factNo);
				for(String factcode:list_factcode){//for a
					List<VWebprofitlossEve>list=new ArrayList<VWebprofitlossEve>();
					for(String month:list_month){
						list.add(new VWebprofitlossEve(new VWebprofitlossEveId(factNo,factcode,month)));
					}
					map_data.put(factcode, list);
				}//for a
				
				List<VWebprofitlossEve>list_eve=vwebprolossSer.findByYymm(yymm, yymm2);
				for(String factcode:map_data.keySet()){//for a
					List<VWebprofitlossEve> list=(List<VWebprofitlossEve>)map_data.get(factcode);
					for(int b=0;b<list.size();b++){
						for(VWebprofitlossEve eve:list_eve){
							if(list.get(b).getId().getFactNo().equals(eve.getId().getFactNo())
							   &&list.get(b).getId().getFactCode().equals(eve.getId().getFactCode())
							   &&list.get(b).getId().getYymm().equals(eve.getId().getYymm())){
								list.remove(b);
								list.add(b, eve);
								break;
							}					
						}
					}
				}//for a
				
				this.init(wb, map_data, map_cs,list_head);
				OutputStream os=new FileOutputStream("e:\\webprofiless.xls");
				wb.write(os);
				os.close();
		
		
	}
	
	
	/**
	 * 項目,細項,單位
	 * @Title: findItems
	 * @Description: TODO
	 * @param @return
	 * @return List<String>
	 * @throws
	 * @author web
	 * @date 2016/5/5
	 */
	public Map<String,Object> findItems() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<String> list_1 = new ArrayList<String>();
		List<String> list_2 = new ArrayList<String>();
		List<String> list_3 = new ArrayList<String>();
		List<String> list_4 = new ArrayList<String>();
		List<String> list_5 = new ArrayList<String>();
		List<String> list_6 = new ArrayList<String>();
		List<String> list_7 = new ArrayList<String>();
		List<String> list_8 = new ArrayList<String>();
		List<String> list_9 = new ArrayList<String>();
		List<String> list_10 = new ArrayList<String>();

		list_1.add("全廠孔位數 (不含工程開發機台)__孔");
		list_1.add("上班天數__天");
		list_1.add("總上模數__模");
		list_1.add("平均一天上模數__模/天");
		list_1.add("機台利用率__%");
		list_1.add("平均料重__g/雙");
		list_1.add("平均淨重__g/雙");
		list_1.add("標準回轉數__回");
		list_1.add("實際回轉數__回");
		list_1.add("回轉數差異__回");
		list_1.add("達成率(%)__%");

		list_2.add("生產雙數__雙");
		list_2.add("請款雙數__雙");
		list_2.add("銷貨收入__USD");
		list_2.add("平均單價__USD/雙");

		list_3.add("出貨數__雙");
		list_3.add("退貨數__雙");
		list_3.add("退貨率__%");

		list_4.add("標準產量__模");
		list_4.add("實際產量__模");
		list_4.add("達成率(%)__%");
		list_4.add("直工人數__人");
		list_4.add("間工人數__人");
		list_4.add("全廠總人數__人");
		list_4.add("直间比__#VALUE!");
		list_4.add("直工人均产能__模/人");
		list_4.add("全廠人均产能__模/人");
		list_4.add("全廠人均时产能__模/H");
		list_4.add("直工離職率(%)__%");
		list_4.add("全廠離職率(%)__%");
		list_4.add("直接薪資(含加班費+獎金)__USD");
		list_4.add("間接薪資(含加班費+獎金)__USD");
		list_4.add("總薪資(含加班費+獎金)__USD");
		list_4.add("總工時__H");
		list_4.add("每雙薪資單耗__USD/雙");
		list_4.add("直工加班費__USD");
		list_4.add("間工加班費__USD");
		list_4.add("總加班費__USD");
		list_4.add("直工人均薪資__USD/人");
		list_4.add("間工人均薪資__USD/人");
		list_4.add("直工占薪資比例__%");
		list_4.add("間工占薪資比例__%");
		list_4.add("全廠人均薪資__USD/人");

		list_5.add("當月總耗用原料__KG");
		list_5.add("當月標准用量__KG");
		list_5.add("總損耗量__KG");
		list_5.add("總損耗率__%");
		list_5.add("無形損耗__KG");
		list_5.add("無形損耗率__%");

		list_6.add("粗坯平均單價__USD/KG");
		list_6.add("邊料(kg)__KG");
		list_6.add("平均邊料重(g)__G/雙");
		list_6.add("邊料%__%");
		list_6.add("邊料金額__USD");
		list_6.add("不良品雙數__雙");
		list_6.add("不良品重量__KG");
		list_6.add("不良重量不良率__%");
		list_6.add("不良品金額__USD");
		list_6.add("其它報廢重量__KG");
		list_6.add("其它報廢金額__USD");
		list_6.add("總報廢重量__KG");
		list_6.add("總報廢金額__USD");

		list_7.add("上月成倉庫存數__雙");
		list_7.add("上月無訂單庫存__雙");
		list_7.add("上月整理庫存__雙");
		list_7.add("上月已出未請數__雙");
		list_7.add("上月已請未出貨__雙");
		list_7.add("前倉入庫折算後可請款生產雙數__雙");
		list_7.add("小計-本月可請款數(A)__雙");
		list_7.add("本月成倉庫存數__雙");
		list_7.add("本月無訂單庫存__雙");
		list_7.add("本月整理庫存__雙");
		list_7.add("本月已出未請數__雙");
		list_7.add("本月已請未出貨__雙");
		list_7.add("小計-本月未請款數(B)__雙");
		list_7.add("本月應請款數(A-B)__雙");
		list_7.add("本月實際請款數__雙");
		list_7.add("生產與請款差異數__雙");
		list_7.add("1.廠客補、樣品未請款數__雙");
		list_7.add("2.無形差異__雙");

		list_8.add("生產模數__雙");
		list_8.add("水用量(噸)__噸");
		list_8.add("用水單耗__噸/模");
		list_8.add("用水金額__USD");
		list_8.add("用水金額單耗(模)__USD/模");
		list_8.add("用電量(度)__度");
		list_8.add("用電單耗(模)__度/模");
		list_8.add("用電金額(USD)__USD");
		list_8.add("用電金額單耗(模)__USD/模");
		list_8.add("蒸氣用量(T)__噸");
		list_8.add("用蒸氣單耗(模)__噸/模");
		list_8.add("用蒸氣金額(USD)__USD");
		list_8.add("用蒸氣金額單耗(模)__USD/模");
		list_8.add("柴油用量(公升)__公升");
		list_8.add("用油單耗(模)__公升/模");
		list_8.add("柴油金額(USD)__USD");
		list_8.add("用油金額單耗(模)__USD/模");

		list_9.add("粗坯用量__KG");
		list_9.add("回頭料重量__KG");
		list_9.add("回頭料%__%");
		list_9.add(" 油壓退料重量__KG");
		list_9.add("油壓退料%__%");
		list_9.add("合計重量__KG");
		list_9.add("回頭率%__%");

		list_10.add("色料用量__KG");
		list_10.add("色料單耗__g/模");
		list_10.add("藥品用量__KG");
		list_10.add("藥品單耗__g/模");
		list_10.add("離型劑金額__USD");
		list_10.add("離型劑單耗__USD/模");
		list_10.add("白色回收粉__KG");
		list_10.add("黑色回收粉__KG");
		list_10.add("生膠回收粉__KG");
		list_10.add("灰色回收粉__KG");
		list_10.add("其它回收粉__KG");
		
		map.put("孔位數&回轉數", list_1);
		map.put("生產與請款狀況", list_2);
		map.put("出貨與退貨", list_3);
		map.put("人員效能", list_4);
		map.put("關務損耗", list_5);
		map.put("邊料不良重量分析", list_6);
		map.put("庫存", list_7);
		map.put("水電油", list_8);
		map.put("回頭料", list_9);
		map.put("色料藥品&離型劑&回收粉", list_10);
		return map;
	}
	
	
	
	public List<String> findResult(int index, VWebprofitlossEve eve,VWebprofitlossEve eve2) {//eve2:爲上箇月的數據
		List<String> list = new ArrayList<String>();

		DecimalFormat format=new DecimalFormat("#,##0");
		DecimalFormat format1=new DecimalFormat("#,##0.0");
		DecimalFormat format2=new DecimalFormat("#,##0.00");
		//DecimalFormat format4=new DecimalFormat("#,##0.0000");
		DecimalFormat format5=new DecimalFormat("#,##0.00000");
		DecimalFormat format_per1=new DecimalFormat("0.0%");
		DecimalFormat format_per2=new DecimalFormat("0.00%");
		// 孔位數&回轉數
		String A001 = format.format(eve.getHole());
		String A002 = format.format(eve.getSumWorkdays());
		String A003 = format.format(eve.getSumEverydemo());
		String A004 = format.format(this.division(eve.getSumEverydemo().doubleValue(), eve.getSumWorkdays().doubleValue()));
		String A005 = format_per2.format(this.division(eve.getSumEverydemo().doubleValue(), eve.getSumWorkdays().doubleValue()*eve.getHole()));
		String A006 = format2.format(this.division(eve.getObjA116(), eve.getSumActualpairs().doubleValue()*1000));
		String A007 = format2.format(this.division(eve.getObjA117(), eve.getSumActualpairs().doubleValue()*1000));
		String A008 = format2.format(this.division(eve.getSumStandarddemo().doubleValue(), eve.getSumEverydemo().doubleValue()));
		String A009 = format2.format(this.division(eve.getSumActualdemo().doubleValue(), eve.getSumEverydemo().doubleValue()));
		String A010 = format2.format(this.division(eve.getSumActualdemo().doubleValue()-eve.getSumStandarddemo().doubleValue(),eve.getSumEverydemo().doubleValue()));
		String A011 = format_per2.format(this.division(eve.getSumActualdemo().doubleValue(), eve.getSumStandarddemo().doubleValue()));

		// 生產與請款狀況
		String B001 = format1.format(eve.getSumActualpairs());
		String B002 = format1.format(eve.getObjA101());
		String B003 = format2.format(eve.getObjA100());
		String B004 = format2.format(this.division(eve.getObjA100(), eve.getObjA101()));

		// 出貨與退貨
		String C001 =format1.format(eve.getSumOutnum());
		String C002 = format1.format(eve.getSumBacknum());
		String C003 = format_per2.format(this.division(eve.getSumBacknum().doubleValue(), eve.getSumOutnum().doubleValue()));

		// 人員效能
		String D001 = format.format(eve.getSumStandarddemo());
		String D002 = format.format(eve.getSumActualdemo());
		String D003 = format_per1.format(this.division(eve.getSumActualdemo().doubleValue(), eve.getSumStandarddemo().doubleValue()));
		String D004 = format.format(eve.getObjA119());
		String D005 = format.format(eve.getObjA120());
		String D006 = format.format(eve.getObjA119()+eve.getObjA120());
		String D007 = format2.format(this.division(eve.getObjA119().doubleValue(), eve.getObjA120().doubleValue()));
		String D008 = format2.format(this.division(eve.getSumActualdemo().doubleValue(), eve.getObjA119().doubleValue()));
		String D009 = format2.format(this.division(eve.getSumActualdemo().doubleValue(), eve.getObjA119().doubleValue()+eve.getObjA120().doubleValue()));
		String D010 = format2.format(this.division(eve.getSumActualdemo().doubleValue(), eve.getObjA121()+eve.getObjA122()+eve.getObjA123()+eve.getObjA124()));
		String D011 = format_per2.format(this.division(eve.getObjA125().doubleValue(), eve.getObjA119().doubleValue()));
		String D012 = format_per2.format(this.division(eve.getObjA125().doubleValue()+eve.getObjA126().doubleValue(), eve.getObjA119().doubleValue()+eve.getObjA120().doubleValue()));
		String D013 = format2.format(eve.getObjA128());
		String D014 = format2.format(eve.getObjA129());
		String D015 = format2.format(eve.getObjA128()+eve.getObjA129());
		String D016 = format2.format(eve.getObjA121()+eve.getObjA122()+eve.getObjA123()+eve.getObjA124());
		String D017 = format5.format(this.division(eve.getObjA128()+eve.getObjA129(), eve.getSumActualdemo().doubleValue()));
		String D018 = format2.format(eve.getObjA123());
		String D019 = format2.format(eve.getObjA124());
		String D020 = format2.format(eve.getObjA123()+eve.getObjA124());
		String D021 = format2.format(this.division(eve.getObjA128(), eve.getObjA119().doubleValue()));
		String D022 = format2.format(this.division(eve.getObjA129(), eve.getObjA120().doubleValue()));
		String D023 = format_per2.format(this.division(eve.getObjA128(), eve.getObjA128()+eve.getObjA129()));
		String D024 = format_per2.format(this.division(eve.getObjA129(), eve.getObjA128()+eve.getObjA129()));
		String D025 = format2.format(this.division(eve.getObjA128()+eve.getObjA129(), eve.getObjA119().doubleValue()+eve.getObjA120().doubleValue()));

		// 關務損耗
		String E001 = format2.format(eve.getObjA115());
		String E002 = format2.format(eve.getObjA116()+eve.getObjA118());
		String E003 = format2.format(eve.getObjA115()-eve.getObjA116()-eve.getObjA118());
		String E004 = format_per2.format(this.division(eve.getObjA115()-eve.getObjA116()-eve.getObjA118(), eve.getObjA116()+eve.getObjA118()));
		String E005 = format2.format(eve.getObjA115()-eve.getObjA116()-eve.getObjA118()-eve.getObjA161()-eve.getObjA165()-eve.getObjA167());
		String E006 = format_per2.format(this.division(eve.getObjA115()-eve.getObjA116()-eve.getObjA118()-eve.getObjA161()-eve.getObjA165()-eve.getObjA167(), eve.getObjA116()+eve.getObjA118()));

		// 邊料不良重量分析
		String F001 = format2.format(this.division(eve.getObjA110(), eve.getObjA116()+eve.getObjA118()));
		String F002 = format2.format(eve.getObjA161());
		String F003 = format2.format(this.division(eve.getObjA161(), eve.getSumActualpairs().doubleValue()));
		String F004 = format_per2.format(this.division(eve.getObjA161(), eve.getObjA116()+eve.getObjA118()+eve.getObjA161()+eve.getObjA165()));
		String F005 = format2.format(this.division(eve.getObjA161()*eve.getObjA110(), eve.getObjA116()+eve.getObjA118()));
		String F006 = format2.format(eve.getObjA163());
		String F007 = format2.format(eve.getObjA165());
		String F008 = format_per2.format(this.division(eve.getObjA165(), eve.getObjA116()+eve.getObjA118()+eve.getObjA161()+eve.getObjA165()));
		String F009 = format2.format(this.division(eve.getObjA165()*eve.getObjA110(), eve.getObjA116()+eve.getObjA118()));
		String F010 = format2.format(eve.getObjA167());
		String F011 = format2.format(this.division(eve.getObjA167()*eve.getObjA110(), eve.getObjA116()+eve.getObjA118()));
		String F012 = format2.format(eve.getObjA161()+eve.getObjA165()+eve.getObjA167());
		String F013 = format2.format(this.division(eve.getObjA161()*eve.getObjA110()+eve.getObjA165()*eve.getObjA110()+eve.getObjA167()*eve.getObjA110(), eve.getObjA116()+eve.getObjA118()));

		// 庫存
		String G001 = format1.format(eve2.getObjA169());
		String G002 = format1.format(eve2.getObjA170());
		String G003 = format1.format(eve2.getObjA171());
		String G004 = format1.format(eve2.getObjA172());
		String G005 = format1.format(eve2.getObjA173());
		String G006 = format1.format(eve2.getObjA174());
		String G007 = format1.format(eve2.getObjA169()+eve2.getObjA171()+eve2.getObjA172()+eve2.getObjA174()-eve2.getObjA173());
		String G008 = format1.format(eve.getObjA169());
		String G009 = format1.format(eve.getObjA170());
		String G010 = format1.format(eve.getObjA171());
		String G011 = format1.format(eve.getObjA172());
		String G012 = format1.format(eve.getObjA173());
		String G013 = format1.format(eve.getObjA169()+eve.getObjA171()+eve.getObjA172()-eve.getObjA173());
		String G014 = format1.format(eve2.getObjA169()+eve2.getObjA171()+eve2.getObjA172()+eve2.getObjA174()-eve2.getObjA173()-(eve.getObjA169()+eve.getObjA171()+eve.getObjA172()-eve.getObjA173()));
		String G015 = format1.format(eve.getObjA101());
		String G016 = format1.format(eve.getObjA175());
		String G017 = format1.format(eve.getSumHostpairs().doubleValue()+eve.getSumSamplepairs().doubleValue()-eve.getObjA103()-eve.getObjA105());
		String G018 = format1.format(eve.getObjA175()-eve.getObjA163());

		// 水電油
		String H001 = format1.format(eve.getSumActualdemo());
		String H002 = format2.format(eve.getObjA153());
		String H003 = format5.format(this.division(eve.getObjA153(), eve.getSumActualdemo().doubleValue()));
		String H004 = format2.format(eve.getObjA154());
		String H005 = format5.format(this.division(eve.getObjA154(), eve.getSumActualdemo().doubleValue()));
		String H006 = format.format(eve.getObjA155());
		String H007 = format5.format(this.division(eve.getObjA155(), eve.getSumActualdemo().doubleValue()));
		String H008 = format2.format(eve.getObjA156());
		String H009 = format5.format(this.division(eve.getObjA156(), eve.getSumActualdemo().doubleValue()));
		String H010 = format2.format(eve.getObjA157());
		String H011 = format5.format(this.division(eve.getObjA157(), eve.getSumActualdemo().doubleValue()));
		String H012 = format2.format(eve.getObjA158());
		String H013 = format5.format(this.division(eve.getObjA158(), eve.getSumActualdemo().doubleValue()));
		String H014 = format2.format(eve.getObjA159());
		String H015 = format5.format(this.division(eve.getObjA159(), eve.getSumActualdemo().doubleValue()));
		String H016 = format2.format(eve.getObjA160());
		String H017 = format5.format(this.division(eve.getObjA160(), eve.getSumActualdemo().doubleValue()));

		// 回頭料
		String I001 = format2.format(eve.getObjA200());
		String I002 = format2.format(eve.getObjA201());
		String I003 = format_per2.format(this.division(eve.getObjA201(), eve.getObjA200()));
		String I004 = format2.format(eve.getObjA202());
		String I005 = format_per2.format(this.division(eve.getObjA202(), eve.getObjA200()));
		String I006 = format2.format(eve.getObjA201()+eve.getObjA202());
		String I007 = format_per2.format(this.division(eve.getObjA201()+eve.getObjA202(), eve.getObjA200()));

		// 色料藥品&離型劑&回收粉
		String J001 = format2.format(eve.getObjA183());
		String J002 = format2.format(this.division(eve.getObjA183(), eve.getSumActualdemo().doubleValue()));
		String J003 = format2.format(eve.getObjA185());
		String J004 = format2.format(this.division(eve.getObjA185(), eve.getSumActualdemo().doubleValue()));
		String J005 = format2.format(eve.getObjA188());
		String J006 = format5.format(this.division(eve.getObjA188(), eve.getSumActualdemo().doubleValue()));
		String J007 = format2.format(eve.getObjA195());
		String J008 = format2.format(eve.getObjA196());
		String J009 = format2.format(eve.getObjA197());
		String J010 = format2.format(eve.getObjA198());
		String J011 = format2.format(eve.getObjA199());

		list.add(A001);
		list.add(A002);
		list.add(A003);
		list.add(A004);
		list.add(A005);
		list.add(A006);
		list.add(A007);
		list.add(A008);
		list.add(A009);
		list.add(A010);
		list.add(A011);

		list.add(B001);
		list.add(B002);
		list.add(B003);
		list.add(B004);

		list.add(C001);
		list.add(C002);
		list.add(C003);

		list.add(D001);
		list.add(D002);
		list.add(D003);
		list.add(D004);
		list.add(D005);
		list.add(D006);
		list.add(D007);
		list.add(D008);
		list.add(D009);
		list.add(D010);
		list.add(D011);
		list.add(D012);
		list.add(D013);
		list.add(D014);
		list.add(D015);
		list.add(D016);
		list.add(D017);
		list.add(D018);
		list.add(D019);
		list.add(D020);
		list.add(D021);
		list.add(D022);
		list.add(D023);
		list.add(D024);
		list.add(D025);

		list.add(E001);
		list.add(E002);
		list.add(E003);
		list.add(E004);
		list.add(E005);
		list.add(E006);

		list.add(F001);
		list.add(F002);
		list.add(F003);
		list.add(F004);
		list.add(F005);
		list.add(F006);
		list.add(F007);
		list.add(F008);
		list.add(F009);
		list.add(F010);
		list.add(F011);
		list.add(F012);
		list.add(F013);

		list.add(G001);
		list.add(G002);
		list.add(G003);
		list.add(G004);
		list.add(G005);
		list.add(G006);
		list.add(G007);
		list.add(G008);
		list.add(G009);
		list.add(G010);
		list.add(G011);
		list.add(G012);
		list.add(G013);
		list.add(G014);
		list.add(G015);
		list.add(G016);
		list.add(G017);
		list.add(G018);

		list.add(H001);
		list.add(H002);
		list.add(H003);
		list.add(H004);
		list.add(H005);
		list.add(H006);
		list.add(H007);
		list.add(H008);
		list.add(H009);
		list.add(H010);
		list.add(H011);
		list.add(H012);
		list.add(H013);
		list.add(H014);
		list.add(H015);
		list.add(H016);
		list.add(H017);

		list.add(I001);
		list.add(I002);
		list.add(I003);
		list.add(I004);
		list.add(I005);
		list.add(I006);
		list.add(I007);

		list.add(J001);
		list.add(J002);
		list.add(J003);
		list.add(J004);
		list.add(J005);
		list.add(J006);
		list.add(J007);
		list.add(J008);
		list.add(J009);
		list.add(J010);
		list.add(J011);

		return list;

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
	
	/**
	 * 初始化表格
	 * @Title: init
	 * @Description: TODO
	 * @param 
	 * @return void
	 * @throws
	 * @author web
	 * @date 2016/5/5
	 */
	public void init(HSSFWorkbook wb,Map<String,Object>map_data,Map<String,Object>map_cs,List<String>list_head){
		
		Map<String,Object>map=findItems();//左三列
		HSSFCellStyle cs_head=(HSSFCellStyle)map_cs.get("cs_head");
		HSSFCellStyle cs_column=(HSSFCellStyle)map_cs.get("cs_column");
		HSSFCellStyle cs_bold=(HSSFCellStyle)map_cs.get("cs_bold");
				
		//注意：初始行：150，初始列：30;項目有擴展時，要在這裏擴展行與列
		for(String factcode:map_data.keySet()){//for
			HSSFSheet sheet=wb.createSheet(factcode);
			for(int a=0;a<2;a++){
				sheet.setColumnWidth(a, 4800);
			}			
			for(int a=0;a<150;a++){
				sheet.createRow(a);
				for(int b=0;b<30;b++){
					sheet.getRow(a).createCell(b);
				}
			}
			//標題
			CellRangeAddress cra_head=new CellRangeAddress(0,0,0,10);
			sheet.addMergedRegion(cra_head);
			sheet.getRow(0).getCell(0).setCellValue(factNo+"每月檢討統計表");
			for(int a=0;a<11;a++){
				sheet.getRow(0).getCell(a).setCellStyle(cs_head);
			}
			//廠別狀態
			sheet.getRow(2).getCell(0).setCellValue(factcode);
			//表頭
			for(int a=0;a<list_head.size();a++){
				sheet.getRow(3).getCell(a).setCellValue(list_head.get(a));
				sheet.getRow(3).getCell(a).setCellStyle(cs_column);
			}
			//項目,細項，單位
			int idx=4;
			int idx2=4;			
			for(String item:map.keySet()){				
				idx=idx2;
				idx2=idx+((List<String>)map.get(item)).size();
				CellRangeAddress cra_left=new CellRangeAddress(idx,idx2-1,0,0);
				sheet.addMergedRegion(cra_left);
				sheet.getRow(idx).getCell(0).setCellValue(item);
				for(int a=idx;a<idx2;a++){
					sheet.getRow(a).getCell(1).setCellValue(((List<String>)map.get(item)).get(a-idx).split("__")[0]);
					sheet.getRow(a).getCell(2).setCellValue(((List<String>)map.get(item)).get(a-idx).split("__")[1]);
					for(int b=0;b<3;b++){
						sheet.getRow(a).getCell(b).setCellStyle(cs_bold);
					}					
				}
			}									
		}//for
		
	}
	
	/**
	 * 對工廠（全年）
	 * @Title: findHead
	 * @Description: TODO
	 * @param @return
	 * @return List<String>
	 * @throws
	 * @author web
	 * @date 2016/5/6
	 */
	public List<String>findHead(){
		List<String>list=new ArrayList<String>();
		list.add("項目");
		list.add("細項");
		list.add("單位");
		/*list.add("1月");
		list.add("2月");
		list.add("3月");
		list.add("Q1");
		list.add("4月");
		list.add("5月");
		list.add("6月");
		list.add("Q2");
		list.add("上半年");
		list.add("7月");
		list.add("8月");
		list.add("9月");
		list.add("Q3");
		list.add("10月");
		list.add("11月");
		list.add("12月");
		list.add("Q4");
		list.add("下半年");
		list.add("全年");*/
		
		list.add("1月");
		list.add("2月");
		list.add("3月");

		list.add("4月");
		list.add("5月");
		list.add("6月");

		list.add("7月");
		list.add("8月");
		list.add("9月");

		list.add("10月");
		list.add("11月");
		list.add("12月");

		return list;
	}
	
	/**
	 * 對工廠（多箇月）
	 * @Title: findHead2
	 * @Description: TODO
	 * @param @return
	 * @return List<String>
	 * @throws ParseException 
	 * @throws
	 * @author web
	 * @date 2016/5/6
	 */
	public List<String>findHead2() throws ParseException{
		yymm="201602";
		yymm2="201605";
		List<String>list=new ArrayList<String>();
		list.add("項目");
		list.add("細項");
		list.add("單位");		
		List<String>list2=GlobalMethod.findMonths(yymm, yymm2);
		for(String month:list2){
			list.add(month);
		}
		list.add("合計");
		return list;
	}
	
	public List<VWebprofitlossEve> findList7(List<VWebprofitlossEve>list){
		List<VWebprofitlossEve>list7=new ArrayList<VWebprofitlossEve>();
		for(int a=0;a<list7.size();a++){
			list7.add(new VWebprofitlossEve());
		}
		int b_index=0;
		for(int b=1;b<list.size();b++){//for b
			b_index=(b-1)/3+1;
			list7.get(b_index).setHole(list7.get(b_index).getHole()+list.get(b).getHole());			
			list7.get(b_index).setObjA100(list7.get(b_index).getObjA100()+list.get(b).getObjA100());
			list7.get(b_index).setObjA101(list7.get(b_index).getObjA101()+list.get(b).getObjA101());
			list7.get(b_index).setObjA102(list7.get(b_index).getObjA102()+list.get(b).getObjA102());
			list7.get(b_index).setObjA103(list7.get(b_index).getObjA103()+list.get(b).getObjA103());
			list7.get(b_index).setObjA104(list7.get(b_index).getObjA104()+list.get(b).getObjA104());
			list7.get(b_index).setObjA105(list7.get(b_index).getObjA105()+list.get(b).getObjA105());
			list7.get(b_index).setObjA106(list7.get(b_index).getObjA106()+list.get(b).getObjA106());
			list7.get(b_index).setObjA107(list7.get(b_index).getObjA107()+list.get(b).getObjA107());
			list7.get(b_index).setObjA108(list7.get(b_index).getObjA108()+list.get(b).getObjA108());
			list7.get(b_index).setObjA109(list7.get(b_index).getObjA109()+list.get(b).getObjA109());
			list7.get(b_index).setObjA110(list7.get(b_index).getObjA110()+list.get(b).getObjA110());
			list7.get(b_index).setObjA111(list7.get(b_index).getObjA111()+list.get(b).getObjA111());
			list7.get(b_index).setObjA112(list7.get(b_index).getObjA112()+list.get(b).getObjA112());
			list7.get(b_index).setObjA113(list7.get(b_index).getObjA113()+list.get(b).getObjA113());
			list7.get(b_index).setObjA114(list7.get(b_index).getObjA114()+list.get(b).getObjA114());
			list7.get(b_index).setObjA115(list7.get(b_index).getObjA115()+list.get(b).getObjA115());
			list7.get(b_index).setObjA116(list7.get(b_index).getObjA116()+list.get(b).getObjA116());
			list7.get(b_index).setObjA117(list7.get(b_index).getObjA117()+list.get(b).getObjA117());
			list7.get(b_index).setObjA118(list7.get(b_index).getObjA118()+list.get(b).getObjA118());
			list7.get(b_index).setObjA119(list7.get(b_index).getObjA119()+list.get(b).getObjA119());
			list7.get(b_index).setObjA120(list7.get(b_index).getObjA120()+list.get(b).getObjA120());
			list7.get(b_index).setObjA121(list7.get(b_index).getObjA121()+list.get(b).getObjA121());
			list7.get(b_index).setObjA122(list7.get(b_index).getObjA122()+list.get(b).getObjA122());
			list7.get(b_index).setObjA123(list7.get(b_index).getObjA123()+list.get(b).getObjA123());
			list7.get(b_index).setObjA124(list7.get(b_index).getObjA124()+list.get(b).getObjA124());
			list7.get(b_index).setObjA125(list7.get(b_index).getObjA125()+list.get(b).getObjA125());
			list7.get(b_index).setObjA126(list7.get(b_index).getObjA126()+list.get(b).getObjA126());
			list7.get(b_index).setObjA127(list7.get(b_index).getObjA127()+list.get(b).getObjA127());
			list7.get(b_index).setObjA128(list7.get(b_index).getObjA128()+list.get(b).getObjA128());
			list7.get(b_index).setObjA129(list7.get(b_index).getObjA129()+list.get(b).getObjA129());
			list7.get(b_index).setObjA130(list7.get(b_index).getObjA130()+list.get(b).getObjA130());
			list7.get(b_index).setObjA131(list7.get(b_index).getObjA131()+list.get(b).getObjA131());
			list7.get(b_index).setObjA132(list7.get(b_index).getObjA132()+list.get(b).getObjA132());
			list7.get(b_index).setObjA133(list7.get(b_index).getObjA133()+list.get(b).getObjA133());
			list7.get(b_index).setObjA134(list7.get(b_index).getObjA134()+list.get(b).getObjA134());
			list7.get(b_index).setObjA135(list7.get(b_index).getObjA135()+list.get(b).getObjA135());
			list7.get(b_index).setObjA136(list7.get(b_index).getObjA136()+list.get(b).getObjA136());
			list7.get(b_index).setObjA137(list7.get(b_index).getObjA137()+list.get(b).getObjA137());
			list7.get(b_index).setObjA138(list7.get(b_index).getObjA138()+list.get(b).getObjA138());
			list7.get(b_index).setObjA139(list7.get(b_index).getObjA139()+list.get(b).getObjA139());
			list7.get(b_index).setObjA140(list7.get(b_index).getObjA140()+list.get(b).getObjA140());
			list7.get(b_index).setObjA141(list7.get(b_index).getObjA141()+list.get(b).getObjA141());
			list7.get(b_index).setObjA142(list7.get(b_index).getObjA142()+list.get(b).getObjA142());
			list7.get(b_index).setObjA143(list7.get(b_index).getObjA143()+list.get(b).getObjA143());
			list7.get(b_index).setObjA144(list7.get(b_index).getObjA144()+list.get(b).getObjA144());
			list7.get(b_index).setObjA145(list7.get(b_index).getObjA145()+list.get(b).getObjA145());
			list7.get(b_index).setObjA146(list7.get(b_index).getObjA146()+list.get(b).getObjA146());
			list7.get(b_index).setObjA147(list7.get(b_index).getObjA147()+list.get(b).getObjA147());
			list7.get(b_index).setObjA148(list7.get(b_index).getObjA148()+list.get(b).getObjA148());
			list7.get(b_index).setObjA149(list7.get(b_index).getObjA149()+list.get(b).getObjA149());
			list7.get(b_index).setObjA150(list7.get(b_index).getObjA150()+list.get(b).getObjA150());
			list7.get(b_index).setObjA151(list7.get(b_index).getObjA151()+list.get(b).getObjA151());
			list7.get(b_index).setObjA152(list7.get(b_index).getObjA152()+list.get(b).getObjA152());
			list7.get(b_index).setObjA153(list7.get(b_index).getObjA153()+list.get(b).getObjA153());
			list7.get(b_index).setObjA154(list7.get(b_index).getObjA154()+list.get(b).getObjA154());
			list7.get(b_index).setObjA155(list7.get(b_index).getObjA155()+list.get(b).getObjA155());
			list7.get(b_index).setObjA156(list7.get(b_index).getObjA156()+list.get(b).getObjA156());
			list7.get(b_index).setObjA157(list7.get(b_index).getObjA157()+list.get(b).getObjA157());
			list7.get(b_index).setObjA158(list7.get(b_index).getObjA158()+list.get(b).getObjA158());
			list7.get(b_index).setObjA159(list7.get(b_index).getObjA159()+list.get(b).getObjA159());
			list7.get(b_index).setObjA160(list7.get(b_index).getObjA160()+list.get(b).getObjA160());
			list7.get(b_index).setObjA161(list7.get(b_index).getObjA161()+list.get(b).getObjA161());
			list7.get(b_index).setObjA162(list7.get(b_index).getObjA162()+list.get(b).getObjA162());
			list7.get(b_index).setObjA163(list7.get(b_index).getObjA163()+list.get(b).getObjA163());
			list7.get(b_index).setObjA164(list7.get(b_index).getObjA164()+list.get(b).getObjA164());
			list7.get(b_index).setObjA165(list7.get(b_index).getObjA165()+list.get(b).getObjA165());
			list7.get(b_index).setObjA166(list7.get(b_index).getObjA166()+list.get(b).getObjA166());
			list7.get(b_index).setObjA167(list7.get(b_index).getObjA167()+list.get(b).getObjA167());
			list7.get(b_index).setObjA168(list7.get(b_index).getObjA168()+list.get(b).getObjA168());
			list7.get(b_index).setObjA169(list7.get(b_index).getObjA169()+list.get(b).getObjA169());
			list7.get(b_index).setObjA170(list7.get(b_index).getObjA170()+list.get(b).getObjA170());
			list7.get(b_index).setObjA171(list7.get(b_index).getObjA171()+list.get(b).getObjA171());
			list7.get(b_index).setObjA172(list7.get(b_index).getObjA172()+list.get(b).getObjA172());
			list7.get(b_index).setObjA173(list7.get(b_index).getObjA173()+list.get(b).getObjA173());
			list7.get(b_index).setObjA174(list7.get(b_index).getObjA174()+list.get(b).getObjA174());
			list7.get(b_index).setObjA175(list7.get(b_index).getObjA175()+list.get(b).getObjA175());
			list7.get(b_index).setObjA176(list7.get(b_index).getObjA176()+list.get(b).getObjA176());
			list7.get(b_index).setObjA177(list7.get(b_index).getObjA177()+list.get(b).getObjA177());
			list7.get(b_index).setObjA178(list7.get(b_index).getObjA178()+list.get(b).getObjA178());
			list7.get(b_index).setObjA179(list7.get(b_index).getObjA179()+list.get(b).getObjA179());
			list7.get(b_index).setObjA180(list7.get(b_index).getObjA180()+list.get(b).getObjA180());
			list7.get(b_index).setObjA181(list7.get(b_index).getObjA181()+list.get(b).getObjA181());
			list7.get(b_index).setObjA182(list7.get(b_index).getObjA182()+list.get(b).getObjA182());
			list7.get(b_index).setObjA183(list7.get(b_index).getObjA183()+list.get(b).getObjA183());
			list7.get(b_index).setObjA184(list7.get(b_index).getObjA184()+list.get(b).getObjA184());
			list7.get(b_index).setObjA185(list7.get(b_index).getObjA185()+list.get(b).getObjA185());
			list7.get(b_index).setObjA186(list7.get(b_index).getObjA186()+list.get(b).getObjA186());
			list7.get(b_index).setObjA187(list7.get(b_index).getObjA187()+list.get(b).getObjA187());
			list7.get(b_index).setObjA188(list7.get(b_index).getObjA188()+list.get(b).getObjA188());
			list7.get(b_index).setObjA189(list7.get(b_index).getObjA189()+list.get(b).getObjA189());
			list7.get(b_index).setObjA190(list7.get(b_index).getObjA190()+list.get(b).getObjA190());
			list7.get(b_index).setObjA191(list7.get(b_index).getObjA191()+list.get(b).getObjA191());
			list7.get(b_index).setObjA192(list7.get(b_index).getObjA192()+list.get(b).getObjA192());
			list7.get(b_index).setObjA193(list7.get(b_index).getObjA193()+list.get(b).getObjA193());
			list7.get(b_index).setObjA194(list7.get(b_index).getObjA194()+list.get(b).getObjA194());
			list7.get(b_index).setObjA195(list7.get(b_index).getObjA195()+list.get(b).getObjA195());
			list7.get(b_index).setObjA196(list7.get(b_index).getObjA196()+list.get(b).getObjA196());
			list7.get(b_index).setObjA197(list7.get(b_index).getObjA197()+list.get(b).getObjA197());
			list7.get(b_index).setObjA198(list7.get(b_index).getObjA198()+list.get(b).getObjA198());
			list7.get(b_index).setObjA199(list7.get(b_index).getObjA199()+list.get(b).getObjA199());
			list7.get(b_index).setObjA200(list7.get(b_index).getObjA200()+list.get(b).getObjA200());
			list7.get(b_index).setObjA201(list7.get(b_index).getObjA201()+list.get(b).getObjA201());
			list7.get(b_index).setObjA202(list7.get(b_index).getObjA202()+list.get(b).getObjA202());
			list7.get(b_index).setHole(list7.get(b_index).getHole()+list.get(b).getHole());
			
			list7.get(b_index).setMachinepower(list7.get(b_index).getMachinepower()+list.get(b).getMachinepower());
			list7.get(b_index).setEstdays(list7.get(b_index).getEstdays()+list.get(b).getEstdays());
			list7.get(b_index).setEsteverymodel(list7.get(b_index).getEsteverymodel()+list.get(b).getEsteverymodel());
			list7.get(b_index).setEsteverypeople(list7.get(b_index).getEsteverypeople()+list.get(b).getEsteverypeople());
			list7.get(b_index).setEstmodel(list7.get(b_index).getEstmodel()+list.get(b).getEstmodel());
			list7.get(b_index).setEstnum(list7.get(b_index).getEstnum()+list.get(b).getEstnum());
			list7.get(b_index).setEstpay(list7.get(b_index).getEstpay()+list.get(b).getEstpay());
			list7.get(b_index).setEstmoney(list7.get(b_index).getEstmoney()+list.get(b).getEstmoney());
			list7.get(b_index).setTotalhole(list7.get(b_index).getTotalhole()+list.get(b).getTotalhole());
			list7.get(b_index).setSample(list7.get(b_index).getSample()+list.get(b).getSample());
			list7.get(b_index).setAccessories(list7.get(b_index).getAccessories()+list.get(b).getAccessories());
			list7.get(b_index).setOther(list7.get(b_index).getOther()+list.get(b).getOther());
			list7.get(b_index).setSumWorkdays(list7.get(b_index).getSumWorkdays().add(list.get(b).getSumWorkdays()));
			list7.get(b_index).setSumEverypeople(list7.get(b_index).getSumEverypeople().add(list.get(b).getSumEverypeople()));
			list7.get(b_index).setSumEverydemo(list7.get(b_index).getSumEverydemo().add(list.get(b).getSumEverydemo()));
			list7.get(b_index).setSumStandarddemo(list7.get(b_index).getSumStandarddemo().add(list.get(b).getSumStandarddemo()));
			list7.get(b_index).setSumActualdemo(list7.get(b_index).getSumActualdemo().add(list.get(b).getSumActualdemo()));
			list7.get(b_index).setSumActualpairs(list7.get(b_index).getSumActualpairs().add(list.get(b).getSumActualpairs()));
			list7.get(b_index).setSumHostpairs(list7.get(b_index).getSumHostpairs().add(list.get(b).getSumHostpairs()));
			list7.get(b_index).setSumFactpairs(list7.get(b_index).getSumFactpairs().add(list.get(b).getSumFactpairs()));
			list7.get(b_index).setSumSamplepairs(list7.get(b_index).getSumSamplepairs().add(list.get(b).getSumSamplepairs()));
			list7.get(b_index).setSumOutnum(list7.get(b_index).getSumOutnum().add(list.get(b).getSumOutnum()));
			list7.get(b_index).setSumBacknum(list7.get(b_index).getSumBacknum().add(list.get(b).getSumBacknum()));
			list7.get(b_index).setSumWorkhours(list7.get(b_index).getSumWorkhours()+list.get(b).getSumWorkhours());
			if(b==12){
				for(int c=0;c<3;c++){
					list7.get(c+5).setObjA100(list7.get(2*c+1).getObjA100()+list7.get(2*c+2).getObjA100());
					list7.get(c+5).setObjA101(list7.get(2*c+1).getObjA101()+list7.get(2*c+2).getObjA101());
					list7.get(c+5).setObjA102(list7.get(2*c+1).getObjA102()+list7.get(2*c+2).getObjA102());
					list7.get(c+5).setObjA103(list7.get(2*c+1).getObjA103()+list7.get(2*c+2).getObjA103());
					list7.get(c+5).setObjA104(list7.get(2*c+1).getObjA104()+list7.get(2*c+2).getObjA104());
					list7.get(c+5).setObjA105(list7.get(2*c+1).getObjA105()+list7.get(2*c+2).getObjA105());
					list7.get(c+5).setObjA106(list7.get(2*c+1).getObjA106()+list7.get(2*c+2).getObjA106());
					list7.get(c+5).setObjA107(list7.get(2*c+1).getObjA107()+list7.get(2*c+2).getObjA107());
					list7.get(c+5).setObjA108(list7.get(2*c+1).getObjA108()+list7.get(2*c+2).getObjA108());
					list7.get(c+5).setObjA109(list7.get(2*c+1).getObjA109()+list7.get(2*c+2).getObjA109());
					list7.get(c+5).setObjA110(list7.get(2*c+1).getObjA110()+list7.get(2*c+2).getObjA110());
					list7.get(c+5).setObjA111(list7.get(2*c+1).getObjA111()+list7.get(2*c+2).getObjA111());
					list7.get(c+5).setObjA112(list7.get(2*c+1).getObjA112()+list7.get(2*c+2).getObjA112());
					list7.get(c+5).setObjA113(list7.get(2*c+1).getObjA113()+list7.get(2*c+2).getObjA113());
					list7.get(c+5).setObjA114(list7.get(2*c+1).getObjA114()+list7.get(2*c+2).getObjA114());
					list7.get(c+5).setObjA115(list7.get(2*c+1).getObjA115()+list7.get(2*c+2).getObjA115());
					list7.get(c+5).setObjA116(list7.get(2*c+1).getObjA116()+list7.get(2*c+2).getObjA116());
					list7.get(c+5).setObjA117(list7.get(2*c+1).getObjA117()+list7.get(2*c+2).getObjA117());
					list7.get(c+5).setObjA118(list7.get(2*c+1).getObjA118()+list7.get(2*c+2).getObjA118());
					list7.get(c+5).setObjA119(list7.get(2*c+1).getObjA119()+list7.get(2*c+2).getObjA119());
					list7.get(c+5).setObjA120(list7.get(2*c+1).getObjA120()+list7.get(2*c+2).getObjA120());
					list7.get(c+5).setObjA121(list7.get(2*c+1).getObjA121()+list7.get(2*c+2).getObjA121());
					list7.get(c+5).setObjA122(list7.get(2*c+1).getObjA122()+list7.get(2*c+2).getObjA122());
					list7.get(c+5).setObjA123(list7.get(2*c+1).getObjA123()+list7.get(2*c+2).getObjA123());
					list7.get(c+5).setObjA124(list7.get(2*c+1).getObjA124()+list7.get(2*c+2).getObjA124());
					list7.get(c+5).setObjA125(list7.get(2*c+1).getObjA125()+list7.get(2*c+2).getObjA125());
					list7.get(c+5).setObjA126(list7.get(2*c+1).getObjA126()+list7.get(2*c+2).getObjA126());
					list7.get(c+5).setObjA127(list7.get(2*c+1).getObjA127()+list7.get(2*c+2).getObjA127());
					list7.get(c+5).setObjA128(list7.get(2*c+1).getObjA128()+list7.get(2*c+2).getObjA128());
					list7.get(c+5).setObjA129(list7.get(2*c+1).getObjA129()+list7.get(2*c+2).getObjA129());
					list7.get(c+5).setObjA130(list7.get(2*c+1).getObjA130()+list7.get(2*c+2).getObjA130());
					list7.get(c+5).setObjA131(list7.get(2*c+1).getObjA131()+list7.get(2*c+2).getObjA131());
					list7.get(c+5).setObjA132(list7.get(2*c+1).getObjA132()+list7.get(2*c+2).getObjA132());
					list7.get(c+5).setObjA133(list7.get(2*c+1).getObjA133()+list7.get(2*c+2).getObjA133());
					list7.get(c+5).setObjA134(list7.get(2*c+1).getObjA134()+list7.get(2*c+2).getObjA134());
					list7.get(c+5).setObjA135(list7.get(2*c+1).getObjA135()+list7.get(2*c+2).getObjA135());
					list7.get(c+5).setObjA136(list7.get(2*c+1).getObjA136()+list7.get(2*c+2).getObjA136());
					list7.get(c+5).setObjA137(list7.get(2*c+1).getObjA137()+list7.get(2*c+2).getObjA137());
					list7.get(c+5).setObjA138(list7.get(2*c+1).getObjA138()+list7.get(2*c+2).getObjA138());
					list7.get(c+5).setObjA139(list7.get(2*c+1).getObjA139()+list7.get(2*c+2).getObjA139());
					list7.get(c+5).setObjA140(list7.get(2*c+1).getObjA140()+list7.get(2*c+2).getObjA140());
					list7.get(c+5).setObjA141(list7.get(2*c+1).getObjA141()+list7.get(2*c+2).getObjA141());
					list7.get(c+5).setObjA142(list7.get(2*c+1).getObjA142()+list7.get(2*c+2).getObjA142());
					list7.get(c+5).setObjA143(list7.get(2*c+1).getObjA143()+list7.get(2*c+2).getObjA143());
					list7.get(c+5).setObjA144(list7.get(2*c+1).getObjA144()+list7.get(2*c+2).getObjA144());
					list7.get(c+5).setObjA145(list7.get(2*c+1).getObjA145()+list7.get(2*c+2).getObjA145());
					list7.get(c+5).setObjA146(list7.get(2*c+1).getObjA146()+list7.get(2*c+2).getObjA146());
					list7.get(c+5).setObjA147(list7.get(2*c+1).getObjA147()+list7.get(2*c+2).getObjA147());
					list7.get(c+5).setObjA148(list7.get(2*c+1).getObjA148()+list7.get(2*c+2).getObjA148());
					list7.get(c+5).setObjA149(list7.get(2*c+1).getObjA149()+list7.get(2*c+2).getObjA149());
					list7.get(c+5).setObjA150(list7.get(2*c+1).getObjA150()+list7.get(2*c+2).getObjA150());
					list7.get(c+5).setObjA151(list7.get(2*c+1).getObjA151()+list7.get(2*c+2).getObjA151());
					list7.get(c+5).setObjA152(list7.get(2*c+1).getObjA152()+list7.get(2*c+2).getObjA152());
					list7.get(c+5).setObjA153(list7.get(2*c+1).getObjA153()+list7.get(2*c+2).getObjA153());
					list7.get(c+5).setObjA154(list7.get(2*c+1).getObjA154()+list7.get(2*c+2).getObjA154());
					list7.get(c+5).setObjA155(list7.get(2*c+1).getObjA155()+list7.get(2*c+2).getObjA155());
					list7.get(c+5).setObjA156(list7.get(2*c+1).getObjA156()+list7.get(2*c+2).getObjA156());
					list7.get(c+5).setObjA157(list7.get(2*c+1).getObjA157()+list7.get(2*c+2).getObjA157());
					list7.get(c+5).setObjA158(list7.get(2*c+1).getObjA158()+list7.get(2*c+2).getObjA158());
					list7.get(c+5).setObjA159(list7.get(2*c+1).getObjA159()+list7.get(2*c+2).getObjA159());
					list7.get(c+5).setObjA160(list7.get(2*c+1).getObjA160()+list7.get(2*c+2).getObjA160());
					list7.get(c+5).setObjA161(list7.get(2*c+1).getObjA161()+list7.get(2*c+2).getObjA161());
					list7.get(c+5).setObjA162(list7.get(2*c+1).getObjA162()+list7.get(2*c+2).getObjA162());
					list7.get(c+5).setObjA163(list7.get(2*c+1).getObjA163()+list7.get(2*c+2).getObjA163());
					list7.get(c+5).setObjA164(list7.get(2*c+1).getObjA164()+list7.get(2*c+2).getObjA164());
					list7.get(c+5).setObjA165(list7.get(2*c+1).getObjA165()+list7.get(2*c+2).getObjA165());
					list7.get(c+5).setObjA166(list7.get(2*c+1).getObjA166()+list7.get(2*c+2).getObjA166());
					list7.get(c+5).setObjA167(list7.get(2*c+1).getObjA167()+list7.get(2*c+2).getObjA167());
					list7.get(c+5).setObjA168(list7.get(2*c+1).getObjA168()+list7.get(2*c+2).getObjA168());
					list7.get(c+5).setObjA169(list7.get(2*c+1).getObjA169()+list7.get(2*c+2).getObjA169());
					list7.get(c+5).setObjA170(list7.get(2*c+1).getObjA170()+list7.get(2*c+2).getObjA170());
					list7.get(c+5).setObjA171(list7.get(2*c+1).getObjA171()+list7.get(2*c+2).getObjA171());
					list7.get(c+5).setObjA172(list7.get(2*c+1).getObjA172()+list7.get(2*c+2).getObjA172());
					list7.get(c+5).setObjA173(list7.get(2*c+1).getObjA173()+list7.get(2*c+2).getObjA173());
					list7.get(c+5).setObjA174(list7.get(2*c+1).getObjA174()+list7.get(2*c+2).getObjA174());
					list7.get(c+5).setObjA175(list7.get(2*c+1).getObjA175()+list7.get(2*c+2).getObjA175());
					list7.get(c+5).setObjA176(list7.get(2*c+1).getObjA176()+list7.get(2*c+2).getObjA176());
					list7.get(c+5).setObjA177(list7.get(2*c+1).getObjA177()+list7.get(2*c+2).getObjA177());
					list7.get(c+5).setObjA178(list7.get(2*c+1).getObjA178()+list7.get(2*c+2).getObjA178());
					list7.get(c+5).setObjA179(list7.get(2*c+1).getObjA179()+list7.get(2*c+2).getObjA179());
					list7.get(c+5).setObjA180(list7.get(2*c+1).getObjA180()+list7.get(2*c+2).getObjA180());
					list7.get(c+5).setObjA181(list7.get(2*c+1).getObjA181()+list7.get(2*c+2).getObjA181());
					list7.get(c+5).setObjA182(list7.get(2*c+1).getObjA182()+list7.get(2*c+2).getObjA182());
					list7.get(c+5).setObjA183(list7.get(2*c+1).getObjA183()+list7.get(2*c+2).getObjA183());
					list7.get(c+5).setObjA184(list7.get(2*c+1).getObjA184()+list7.get(2*c+2).getObjA184());
					list7.get(c+5).setObjA185(list7.get(2*c+1).getObjA185()+list7.get(2*c+2).getObjA185());
					list7.get(c+5).setObjA186(list7.get(2*c+1).getObjA186()+list7.get(2*c+2).getObjA186());
					list7.get(c+5).setObjA187(list7.get(2*c+1).getObjA187()+list7.get(2*c+2).getObjA187());
					list7.get(c+5).setObjA188(list7.get(2*c+1).getObjA188()+list7.get(2*c+2).getObjA188());
					list7.get(c+5).setObjA189(list7.get(2*c+1).getObjA189()+list7.get(2*c+2).getObjA189());
					list7.get(c+5).setObjA190(list7.get(2*c+1).getObjA190()+list7.get(2*c+2).getObjA190());
					list7.get(c+5).setObjA191(list7.get(2*c+1).getObjA191()+list7.get(2*c+2).getObjA191());
					list7.get(c+5).setObjA192(list7.get(2*c+1).getObjA192()+list7.get(2*c+2).getObjA192());
					list7.get(c+5).setObjA193(list7.get(2*c+1).getObjA193()+list7.get(2*c+2).getObjA193());
					list7.get(c+5).setObjA194(list7.get(2*c+1).getObjA194()+list7.get(2*c+2).getObjA194());
					list7.get(c+5).setObjA195(list7.get(2*c+1).getObjA195()+list7.get(2*c+2).getObjA195());
					list7.get(c+5).setObjA196(list7.get(2*c+1).getObjA196()+list7.get(2*c+2).getObjA196());
					list7.get(c+5).setObjA197(list7.get(2*c+1).getObjA197()+list7.get(2*c+2).getObjA197());
					list7.get(c+5).setObjA198(list7.get(2*c+1).getObjA198()+list7.get(2*c+2).getObjA198());
					list7.get(c+5).setObjA199(list7.get(2*c+1).getObjA199()+list7.get(2*c+2).getObjA199());
					list7.get(c+5).setObjA200(list7.get(2*c+1).getObjA200()+list7.get(2*c+2).getObjA200());
					list7.get(c+5).setObjA201(list7.get(2*c+1).getObjA201()+list7.get(2*c+2).getObjA201());
					list7.get(c+5).setObjA202(list7.get(2*c+1).getObjA202()+list7.get(2*c+2).getObjA202());
					list7.get(c+5).setHole(list7.get(2*c+1).getHole()+list7.get(2*c+2).getHole());
					
					list7.get(c+5).setMachinepower(list7.get(2*c+1).getMachinepower()+list7.get(2*c+2).getMachinepower());
					list7.get(c+5).setEstdays(list7.get(2*c+1).getEstdays()+list7.get(2*c+2).getEstdays());
					list7.get(c+5).setEsteverymodel(list7.get(2*c+1).getEsteverymodel()+list7.get(2*c+2).getEsteverymodel());
					list7.get(c+5).setEsteverypeople(list7.get(2*c+1).getEsteverypeople()+list7.get(2*c+2).getEsteverypeople());
					list7.get(c+5).setEstmodel(list7.get(2*c+1).getEstmodel()+list7.get(2*c+2).getEstmodel());
					list7.get(c+5).setEstnum(list7.get(2*c+1).getEstnum()+list7.get(2*c+2).getEstnum());
					list7.get(c+5).setEstpay(list7.get(2*c+1).getEstpay()+list7.get(2*c+2).getEstpay());
					list7.get(c+5).setEstmoney(list7.get(2*c+1).getEstmoney()+list7.get(2*c+2).getEstmoney());
					list7.get(c+5).setTotalhole(list7.get(2*c+1).getTotalhole()+list7.get(2*c+2).getTotalhole());
					list7.get(c+5).setSample(list7.get(2*c+1).getSample()+list7.get(2*c+2).getSample());
					list7.get(c+5).setAccessories(list7.get(2*c+1).getAccessories()+list7.get(2*c+2).getAccessories());
					list7.get(c+5).setOther(list7.get(2*c+1).getOther()+list7.get(2*c+2).getOther());
					list7.get(c+5).setSumWorkdays(list7.get(2*c+1).getSumWorkdays().add(list7.get(2*c+2).getSumWorkdays()));
					list7.get(c+5).setSumEverypeople(list7.get(2*c+1).getSumEverypeople().add(list7.get(2*c+2).getSumEverypeople()));
					list7.get(c+5).setSumEverydemo(list7.get(2*c+1).getSumEverydemo().add(list7.get(2*c+2).getSumEverydemo()));
					list7.get(c+5).setSumStandarddemo(list7.get(2*c+1).getSumStandarddemo().add(list7.get(2*c+2).getSumStandarddemo()));
					list7.get(c+5).setSumActualdemo(list7.get(2*c+1).getSumActualdemo().add(list7.get(2*c+2).getSumActualdemo()));
					list7.get(c+5).setSumActualpairs(list7.get(2*c+1).getSumActualpairs().add(list7.get(2*c+2).getSumActualpairs()));
					list7.get(c+5).setSumHostpairs(list7.get(2*c+1).getSumHostpairs().add(list7.get(2*c+2).getSumHostpairs()));
					list7.get(c+5).setSumFactpairs(list7.get(2*c+1).getSumFactpairs().add(list7.get(2*c+2).getSumFactpairs()));
					list7.get(c+5).setSumSamplepairs(list7.get(2*c+1).getSumSamplepairs().add(list7.get(2*c+2).getSumSamplepairs()));
					list7.get(c+5).setSumOutnum(list7.get(2*c+1).getSumOutnum().add(list7.get(2*c+2).getSumOutnum()));
					list7.get(c+5).setSumBacknum(list7.get(2*c+1).getSumBacknum().add(list7.get(2*c+2).getSumBacknum()));
					list7.get(c+5).setSumWorkhours(list7.get(2*c+1).getSumWorkhours()+list7.get(2*c+2).getSumWorkhours());
				}
			}
		}//for b
		return list7;
	}
		
}
