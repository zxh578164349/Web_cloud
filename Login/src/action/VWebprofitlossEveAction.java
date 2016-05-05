/**
 * 
 */
package action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
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
	
	public void print_fact(){
		HSSFWorkbook wb=new HSSFWorkbook();
		Map<String,Object>map_data=new HashMap<String,Object>();
		Map<String,Object>map_cs=GlobalMethod.findStyles(wb);
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
	public void init(HSSFWorkbook wb,Map<String,Object>map_data,Map<String,Object>map_cs){
		List<String>list_head=findHead();
		Map<String,Object>map=findItems();
		
		//注意：初始行：150，初始列：30;項目有擴展時，要在這裏擴展行與列
		for(String factcode:map_data.keySet()){//for
			HSSFSheet sheet=wb.createSheet(factcode);
			for(int a=0;a<150;a++){
				sheet.createRow(a);
				for(int b=0;b<30;b++){
					sheet.getRow(a).createCell(b);
				}
			}			
		}//for
		
	}
	
	public List<String>findHead(){
		List<String>list=new ArrayList<String>();
		list.add("項目");
		list.add("細項");
		list.add("單位");
		list.add("1月");
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
		list.add("全年");
		return list;
	}
		
}
