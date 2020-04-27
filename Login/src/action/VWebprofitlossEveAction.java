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

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import entity.VWebprofitlossEve;
import entity.VWebprofitlossEveId;

import services.IVWebprofitlossEveServices;
import services.IWebFactServices;
import util.GlobalMethod;

/**
 * 
* 項目名稱：WebLogin   
* 類名稱：VWebprofitlossEveAction   
* 類描述：分形態損益表(new)
* 創建人：KY2
 */
public class VWebprofitlossEveAction implements ServletResponseAware{
	private String year;
	private String yymm;
	private String yymm2;
	private String factNo;
	private List<String>list_factcode;//所選的廠別狀態
	private List<String>list_factno;//所選的所有的廠別（包含各個廠別狀態）
	private String reportType;//報表類型 1=全廠的      2=分月份的       3=臺灣的
	private IVWebprofitlossEveServices  vwebprolossSer;
	private IWebFactServices webFactSer;
	private javax.servlet.http.HttpServletResponse response;
	
	
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public List<String> getList_factcode() {
		return list_factcode;
	}
	public void setList_factcode(List<String> list_factcode) {
		this.list_factcode = list_factcode;
	}
	public List<String> getList_factno() {
		return list_factno;
	}
	public void setList_factno(List<String> list_factno) {
		this.list_factno = list_factno;
	}
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
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	
	/**
	 * 全年報表
	 * @throws IOException
	 */
	public void print_fact() throws IOException{
		reportType="1";
		HSSFWorkbook wb=new HSSFWorkbook();
		Map<String,Object>map_data=new HashMap<String,Object>();
		Map<String,Object>map_cs=GlobalMethod.findStyles(wb);
		HSSFCellStyle cs=(HSSFCellStyle)map_cs.get("cs");
		List<String>list_head=findHead();//表頭
		//獲取一年所有月份+上一年的12份
		int last_year=Integer.parseInt(year)-1;
		List<String>list_month=new ArrayList<String>();
		List<String>list_month2=new ArrayList<String>();
		list_month.add(last_year+"12");
		list_month2.add("0000");
		for(int a=1;a<13;a++){
			if(a<10){
				list_month.add(year+"0"+a);
				list_month2.add(last_year+"0"+a);
			}else{
				list_month.add(year+a);
				list_month2.add(last_year+a+"");
			}			
		}
				
		//獲取一箇廠的factCode
		List<String>list_factcode=webFactSer.findByFactNo_showA(factNo);
		for(String factcode:list_factcode){//for a
			List<List<VWebprofitlossEve>>list_all=new ArrayList<List<VWebprofitlossEve>>();			
				List<VWebprofitlossEve>list_a=new ArrayList<VWebprofitlossEve>();
				List<VWebprofitlossEve>list_b=new ArrayList<VWebprofitlossEve>();
				for(String month:list_month){
					list_a.add(new VWebprofitlossEve(new VWebprofitlossEveId(factNo,factcode,month)));
				}
				for(String month:list_month2){
					list_b.add(new VWebprofitlossEve(new VWebprofitlossEveId(factNo,factcode,month)));
				}
				list_all.add(list_a);
				list_all.add(list_b);
						
			map_data.put(factcode, list_all);
		}//for a
		
		//今年數據（包括上年的12份數據）
		List<VWebprofitlossEve>list_eve=vwebprolossSer.findByYymm(factNo,list_month.get(0), list_month.get(12));//開始：上年12月      結束：今年12月
		//上年數據
		List<VWebprofitlossEve>list_eve2=vwebprolossSer.findByYymm(factNo,last_year+"01",last_year+"12");
		List<List<VWebprofitlossEve>>list_eve_all=new ArrayList<List<VWebprofitlossEve>>();
		list_eve_all.add(list_eve);
		list_eve_all.add(list_eve2);
		
		for(String factcode:map_data.keySet()){//for a
			List<List<VWebprofitlossEve>> list_all=(List<List<VWebprofitlossEve>>)map_data.get(factcode);
			for(int b=0;b<list_all.size();b++){//for b
				for(int c=0;c<list_all.get(b).size();c++){//for c
					for(VWebprofitlossEve eve:list_eve_all.get(b)){
						if(list_all.get(b).get(c).getId().getFactNo().equals(eve.getId().getFactNo())
						   &&list_all.get(b).get(c).getId().getFactCode().equals(eve.getId().getFactCode())
						   &&list_all.get(b).get(c).getId().getYymm().equals(eve.getId().getYymm())){
							list_all.get(b).remove(c);
							list_all.get(b).add(c, eve);
							break;
						}					
					}
				}//for c
			}//for b
			
			List<VWebprofitlossEve>list7_a=this.findList7(list_all.get(0));//今年季度，年度統計
			List<VWebprofitlossEve>list7_b=this.findList7(list_all.get(1));//上年季度，年度統計
			List<List<String>>list_a=new ArrayList<List<String>>();
			for(int b=1;b<list_all.get(0).size();b++){//for b
				List<String>list_b=this.findResult(1, list_all.get(0).get(b), list_all.get(0).get(b-1));
				list_a.add(list_b);
				switch(b){
				case 3:
					list_a.add(this.findResult(3, list7_a.get(0), list7_b.get(0)));//第一季度
					break;
				case 6:
					list_a.add(this.findResult(3, list7_a.get(1), list7_b.get(1)));//第二季度
					list_a.add(this.findResult(6, list7_a.get(4), list7_b.get(4)));//上半年
					break;
				case 9:
					list_a.add(this.findResult(3, list7_a.get(2), list7_b.get(2)));//第三季度
					break;
				case 12:
					list_a.add(this.findResult(3, list7_a.get(3), list7_b.get(3)));//第四季度
					list_a.add(this.findResult(6, list7_a.get(5), list7_b.get(5)));//下半年
					list_a.add(this.findResult(12, list7_a.get(6), list7_b.get(6)));//全年
				}
			}//for b
			map_data.put(factcode, list_a);												
		}//for a
		
		//開始打印報表
		this.init(wb, map_data, map_cs,list_head,null);
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
		//OutputStream os=new FileOutputStream("e:\\webprofiless.xls");		
		ServletOutputStream os=response.getOutputStream();
		response.setContentType("application/vnd.ms-excel");
		int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");//判斷是否為IE瀏覽器,大於0則為IE瀏覽器
		String fileName="report_fact.xls";
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
	 * 月份報表
	 * @throws ParseException
	 * @throws IOException
	 */
	public void print_month() throws ParseException, IOException{
		reportType="2";
		HSSFWorkbook wb=new HSSFWorkbook();
		Map<String,Object>map_data=new HashMap<String,Object>();
		Map<String,Object>map_cs=GlobalMethod.findStyles(wb);
		List<String>list_head=findHead2();//表頭
		HSSFCellStyle cs=(HSSFCellStyle)map_cs.get("cs");
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
				
				List<VWebprofitlossEve>list_eve=vwebprolossSer.findByYymm(factNo,list_month.get(0), yymm2);
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
					List<VWebprofitlossEve>list_total=new ArrayList<VWebprofitlossEve>();//當月統計
					List<VWebprofitlossEve>list_total2=new ArrayList<VWebprofitlossEve>();//上月統計
				    list_total.add(new VWebprofitlossEve());
					list_total2.add(new VWebprofitlossEve());
					for(int b=1;b<list.size();b++){//for b		b=1開始			
						list_total.add(new VWebprofitlossEve());
						this.findTotal(list_total, list_total, list, 0, 0, b);						
					}//for b
					for(int b=0;b<list.size()-1;b++){//for b	b=0開始		
						list_total.add(new VWebprofitlossEve());
						this.findTotal(list_total2, list_total2, list, 0, 0, b);						
					}//for b
					
					List<List<String>>list_a=new ArrayList<List<String>>();
					for(int b=1;b<list.size();b++){//for b
						List<String>list_b=this.findResult(1, list.get(b), list.get(b-1));
						list_a.add(list_b);
					}//for b
					List<String>list_b=this.findResult(list.size()-1, list_total.get(0), list_total2.get(0));//list.size()-1是所選取的月數
					list_a.add(list_b);
					map_data.put(factcode, list_a);										
				}//for a
				
				
				//開始打印報表
				this.init(wb, map_data, map_cs,list_head,null);
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
				//OutputStream os=new FileOutputStream("e:\\webprofiless.xls");
				ServletOutputStream os=response.getOutputStream();
				response.setContentType("application/vnd.ms-excel");
				int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");//判斷是否為IE瀏覽器,大於0則為IE瀏覽器
				String fileName="report_month.xls";
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
	 * 分形態損益表-台灣
	 * @throws IOException
	 * @throws ParseException
	 */
	public void print_tw() throws IOException, ParseException{
		reportType="3";
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFCellStyle cs=(HSSFCellStyle)GlobalMethod.findStyles(wb).get("cs");
		Calendar cal=Calendar.getInstance();
		DateFormat fmt=new SimpleDateFormat("yyyyMM");
		cal.setTime(fmt.parse(yymm));
		cal.add(Calendar.MONTH, -1);
		String last_month=fmt.format(cal.getTime());//上月
		Map<String,Object>map_cs=GlobalMethod.findStyles(wb);
		Map<String,Object>map_data=new LinkedHashMap<String,Object>();
		Map<String,Object>map_head=new LinkedHashMap<String,Object>();
		List<VWebprofitlossEve>list_eve=vwebprolossSer.findByYymm(yymm);//本月數據
		List<VWebprofitlossEve>list_eve2=vwebprolossSer.findByYymm(last_month);//上月數據
		List<List<VWebprofitlossEve>>list_eve_all=new ArrayList<List<VWebprofitlossEve>>();
		list_eve_all.add(list_eve);
		list_eve_all.add(list_eve2);
		if(list_factcode!=null&&list_factcode.size()>0){
			for(String factcode:list_factcode){//for a
				List<VWebprofitlossEve>list=new ArrayList<VWebprofitlossEve>();//本月
				List<VWebprofitlossEve>list2=new ArrayList<VWebprofitlossEve>();//上月
				List<List<VWebprofitlossEve>>list_all=new ArrayList<List<VWebprofitlossEve>>();
				List<String>list_head=new ArrayList<String>();
				list_head.add("項目");
				list_head.add("細項");
				list_head.add("單位");
				for(String fact:list_factno){
					if(factcode.equals(fact.split("_")[0])){
						list.add(new VWebprofitlossEve(new VWebprofitlossEveId(fact.split("_")[1],factcode,yymm)));
						list2.add(new VWebprofitlossEve(new VWebprofitlossEveId(fact.split("_")[1],factcode,last_month)));
						list_head.add(fact.split("_")[2]);
					}
				}
				list_all.add(list);
				list_all.add(list2);
				list_head.add("合計");
				map_data.put(factcode, list_all);
				map_head.put(factcode, list_head);
			}//for a
			
			for(String factcode:map_data.keySet()){//for
				List<List<VWebprofitlossEve>>list_all=(List<List<VWebprofitlossEve>>)map_data.get(factcode);
				for(List<VWebprofitlossEve> list:list_all){
					for(int a=0;a<list.size();a++){//for a
						for(int b=0;b<list_eve_all.size();b++){//for b
							for(VWebprofitlossEve eve:list_eve_all.get(b)){
								if(list.get(a).getId().getFactNo().equals(eve.getId().getFactNo())&&list.get(a).getId().getFactCode().equals(eve.getId().getFactCode())&&
										list.get(a).getId().getYymm().equals(eve.getId().getYymm())){
									list.remove(a);
									list.add(a,eve);
									break;
								}
							}
						}//for b					
					}//for a
				}
				List<List<String>>list_result_all=new ArrayList<List<String>>();
				List<VWebprofitlossEve>list_total=new ArrayList<VWebprofitlossEve>();//本月合計
				List<VWebprofitlossEve>list_total2=new ArrayList<VWebprofitlossEve>();//上月合計
				list_total.add(new VWebprofitlossEve());
				list_total2.add(new VWebprofitlossEve());
				for(int a=0;a<list_all.get(0).size();a++){
					List<String>list=this.findResult(1, list_all.get(0).get(a), list_all.get(1).get(a));
					list_result_all.add(list);
					this.findTotal(list_total, list_total, list_all.get(0), 0, 0, a);
					this.findTotal(list_total2, list_total2, list_all.get(1), 0, 0, a);
				}				
				List<String> list_retusl_total=this.findResult(list_all.get(0).size(), list_total.get(0), list_total2.get(0));
				list_result_all.add(list_retusl_total);
				map_data.put(factcode, list_result_all);				
			}//for
		}
		
		this.init(wb, map_data, map_cs, null, map_head);
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
		//OutputStream os=new FileOutputStream("e:\\webprofiless.xls");
		ServletOutputStream os=response.getOutputStream();
		response.setContentType("application/vnd.ms-excel");
		int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");//判斷是否為IE瀏覽器,大於0則為IE瀏覽器
		String fileName="report_tw.xls";
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
		list_4.add("直间比__--");
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
	
	
	/**
	 * 數據格式
	 * @param index
	 * @param eve
	 * @param eve2
	 * @return
	 */
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
		String A001 = format.format(eve.getHole()/index);
		String A002 = format.format(eve.getSumWorkdays().doubleValue()/index);
		String A003 = format.format(eve.getSumEverydemo().doubleValue()/index);
		String A004 = format.format(this.division(eve.getSumEverydemo().doubleValue(), eve.getSumWorkdays().doubleValue()));
		String A005 = format_per2.format(this.division(eve.getSumEverydemo().doubleValue(), eve.getSumWorkdays().doubleValue()*eve.getHole()/index));
		String A006 = format2.format(this.division(eve.getObjA116(), eve.getSumActualpairs().doubleValue()*1000));
		String A007 = format2.format(this.division(eve.getObjA117(), eve.getSumActualpairs().doubleValue()*1000));
		String A008 = format2.format(this.division(eve.getSumStandarddemo().doubleValue(), eve.getSumEverydemo().doubleValue()));
		String A009 = format2.format(this.division(eve.getSumActualdemo().doubleValue(), eve.getSumEverydemo().doubleValue()));
		String A010 = format2.format(this.division(eve.getSumActualdemo().doubleValue()-eve.getSumStandarddemo().doubleValue(),eve.getSumEverydemo().doubleValue()));
		String A011 = format_per2.format(this.division(eve.getSumActualdemo().doubleValue(), eve.getSumStandarddemo().doubleValue()));

		// 生產與請款狀況
		String B001 = format1.format(eve.getSumActualpairs().doubleValue()/index);
		String B002 = format1.format(eve.getObjA101()/index);
		String B003 = format2.format(eve.getObjA100()/index);
		String B004 = format2.format(this.division(eve.getObjA100(), eve.getObjA101()));

		// 出貨與退貨
		String C001 =format1.format(eve.getSumOutnum().doubleValue()/index);
		String C002 = format1.format(eve.getSumBacknum().doubleValue()/index);
		String C003 = format_per2.format(this.division(eve.getSumBacknum().doubleValue(), eve.getSumOutnum().doubleValue()));

		// 人員效能
		String D001 = format.format(eve.getSumStandarddemo().doubleValue()/index);
		String D002 = format.format(eve.getSumActualdemo().doubleValue()/index);
		String D003 = format_per1.format(this.division(eve.getSumActualdemo().doubleValue(), eve.getSumStandarddemo().doubleValue()));
		String D004 = format.format(eve.getObjA119()/index);
		String D005 = format.format(eve.getObjA120()/index);
		String D006 = format.format((eve.getObjA119()+eve.getObjA120())/index);
		String D007 = format2.format(this.division(eve.getObjA119().doubleValue(), eve.getObjA120().doubleValue()));
		String D008 = format2.format(this.division(eve.getSumActualdemo().doubleValue(), eve.getObjA119().doubleValue()));
		String D009 = format2.format(this.division(eve.getSumActualdemo().doubleValue(), eve.getObjA119().doubleValue()+eve.getObjA120().doubleValue()));
		String D010 = format2.format(this.division(eve.getSumActualdemo().doubleValue(), eve.getObjA121()+eve.getObjA122()+eve.getObjA123()+eve.getObjA124()));
		String D011 = format_per2.format(this.division(eve.getObjA125().doubleValue(), eve.getObjA119().doubleValue()));
		String D012 = format_per2.format(this.division(eve.getObjA125().doubleValue()+eve.getObjA126().doubleValue(), eve.getObjA119().doubleValue()+eve.getObjA120().doubleValue()));
		String D013 = format2.format(eve.getObjA128()/index);
		String D014 = format2.format(eve.getObjA129()/index);
		String D015 = format2.format((eve.getObjA128()+eve.getObjA129())/index);
		String D016 = format2.format((eve.getObjA121()+eve.getObjA122()+eve.getObjA123()+eve.getObjA124())/index);
		String D017 = format5.format(this.division(eve.getObjA128()+eve.getObjA129(), eve.getSumActualdemo().doubleValue()));
		String D018 = format2.format(eve.getObjA123()/index);
		String D019 = format2.format(eve.getObjA124()/index);
		String D020 = format2.format((eve.getObjA123()+eve.getObjA124())/index);
		String D021 = format2.format(this.division(eve.getObjA128(), eve.getObjA119().doubleValue()));
		String D022 = format2.format(this.division(eve.getObjA129(), eve.getObjA120().doubleValue()));
		String D023 = format_per2.format(this.division(eve.getObjA128(), eve.getObjA128()+eve.getObjA129()));
		String D024 = format_per2.format(this.division(eve.getObjA129(), eve.getObjA128()+eve.getObjA129()));
		String D025 = format2.format(this.division(eve.getObjA128()+eve.getObjA129(), eve.getObjA119().doubleValue()+eve.getObjA120().doubleValue()));

		// 關務損耗
		String E001 = format2.format(eve.getObjA115()/index);
		String E002 = format2.format((eve.getObjA116()+eve.getObjA118())/index);
		String E003 = format2.format((eve.getObjA115()-eve.getObjA116()-eve.getObjA118())/index);
		String E004 = format_per2.format(this.division(eve.getObjA115()-eve.getObjA116()-eve.getObjA118(), eve.getObjA116()+eve.getObjA118()));
		String E005 = format2.format((eve.getObjA115()-eve.getObjA116()-eve.getObjA118()-eve.getObjA161()-eve.getObjA165()-eve.getObjA167())/index);
		String E006 = format_per2.format((this.division(eve.getObjA115()-eve.getObjA116()-eve.getObjA118()-eve.getObjA161()-eve.getObjA165()-eve.getObjA167(), eve.getObjA116()+eve.getObjA118()))/index);

		// 邊料不良重量分析
		String F001 = format2.format(this.division(eve.getObjA110(), eve.getObjA116()+eve.getObjA118()));
		String F002 = format2.format(eve.getObjA161()/index);
		String F003 = format2.format(this.division(eve.getObjA161(), eve.getSumActualpairs().doubleValue()));
		String F004 = format_per2.format(this.division(eve.getObjA161(), eve.getObjA116()+eve.getObjA118()+eve.getObjA161()+eve.getObjA165()));
		String F005 = format2.format(this.division(eve.getObjA161()*eve.getObjA110(), eve.getObjA116()+eve.getObjA118())/index);
		String F006 = format2.format(eve.getObjA163()/index);
		String F007 = format2.format(eve.getObjA165()/index);
		String F008 = format_per2.format(this.division(eve.getObjA165(), eve.getObjA116()+eve.getObjA118()+eve.getObjA161()+eve.getObjA165()));
		String F009 = format2.format(this.division(eve.getObjA165()*eve.getObjA110(), eve.getObjA116()+eve.getObjA118())/index);
		String F010 = format2.format(eve.getObjA167()/index);
		String F011 = format2.format(this.division(eve.getObjA167()*eve.getObjA110(), eve.getObjA116()+eve.getObjA118())/index);
		String F012 = format2.format((eve.getObjA161()+eve.getObjA165()+eve.getObjA167())/index);
		String F013 = format2.format(this.division(eve.getObjA161()*eve.getObjA110()+eve.getObjA165()*eve.getObjA110()+eve.getObjA167()*eve.getObjA110(), eve.getObjA116()+eve.getObjA118())/index);

		// 庫存
		String G001 = format1.format(eve2.getObjA169()/index);
		String G002 = format1.format(eve2.getObjA170()/index);
		String G003 = format1.format(eve2.getObjA171()/index);
		String G004 = format1.format(eve2.getObjA172()/index);
		String G005 = format1.format(eve2.getObjA173()/index);
		String G006 = format1.format(eve2.getObjA174()/index);
		String G007 = format1.format((eve2.getObjA169()+eve2.getObjA171()+eve2.getObjA172()+eve2.getObjA174()-eve2.getObjA173())/index);
		String G008 = format1.format(eve.getObjA169()/index);
		String G009 = format1.format(eve.getObjA170()/index);
		String G010 = format1.format(eve.getObjA171()/index);
		String G011 = format1.format(eve.getObjA172()/index);
		String G012 = format1.format(eve.getObjA173()/index);
		String G013 = format1.format((eve.getObjA169()+eve.getObjA171()+eve.getObjA172()-eve.getObjA173())/index);
		String G014 = format1.format((eve2.getObjA169()+eve2.getObjA171()+eve2.getObjA172()+eve2.getObjA174()-eve2.getObjA173()-(eve.getObjA169()+eve.getObjA171()+eve.getObjA172()-eve.getObjA173()))/index);
		String G015 = format1.format(eve.getObjA101()/index);
		String G016 = format1.format(eve.getObjA175()/index);
		String G017 = format1.format((eve.getSumHostpairs().doubleValue()+eve.getSumSamplepairs().doubleValue()-eve.getObjA103()-eve.getObjA105())/index);
		String G018 = format1.format((eve.getObjA175()-eve.getObjA163())/index);

		// 水電油
		String H001 = format1.format(eve.getSumActualdemo().doubleValue()/index);
		String H002 = format2.format(eve.getObjA153()/index);
		String H003 = format5.format(this.division(eve.getObjA153(), eve.getSumActualdemo().doubleValue()));
		String H004 = format2.format(eve.getObjA154()/index);
		String H005 = format5.format(this.division(eve.getObjA154(), eve.getSumActualdemo().doubleValue()));
		String H006 = format.format(eve.getObjA155()/index);
		String H007 = format5.format(this.division(eve.getObjA155(), eve.getSumActualdemo().doubleValue()));
		String H008 = format2.format(eve.getObjA156()/index);
		String H009 = format5.format(this.division(eve.getObjA156(), eve.getSumActualdemo().doubleValue()));
		String H010 = format2.format(eve.getObjA157()/index);
		String H011 = format5.format(this.division(eve.getObjA157(), eve.getSumActualdemo().doubleValue()));
		String H012 = format2.format(eve.getObjA158()/index);
		String H013 = format5.format(this.division(eve.getObjA158(), eve.getSumActualdemo().doubleValue()));
		String H014 = format2.format(eve.getObjA159()/index);
		String H015 = format5.format(this.division(eve.getObjA159(), eve.getSumActualdemo().doubleValue()));
		String H016 = format2.format(eve.getObjA160()/index);
		String H017 = format5.format(this.division(eve.getObjA160(), eve.getSumActualdemo().doubleValue()));

		// 回頭料
		String I001 = format2.format(eve.getObjA200()/index);
		String I002 = format2.format(eve.getObjA201()/index);
		String I003 = format_per2.format(this.division(eve.getObjA201(), eve.getObjA200()));
		String I004 = format2.format(eve.getObjA202()/index);
		String I005 = format_per2.format(this.division(eve.getObjA202(), eve.getObjA200()));
		String I006 = format2.format((eve.getObjA201()+eve.getObjA202())/index);
		String I007 = format_per2.format(this.division(eve.getObjA201()+eve.getObjA202(), eve.getObjA200()));

		// 色料藥品&離型劑&回收粉
		String J001 = format2.format(eve.getObjA183()/index);
		String J002 = format2.format(this.division(eve.getObjA183(), eve.getSumActualdemo().doubleValue()));
		String J003 = format2.format(eve.getObjA185()/index);
		String J004 = format2.format(this.division(eve.getObjA185(), eve.getSumActualdemo().doubleValue()));
		String J005 = format2.format(eve.getObjA188()/index);
		String J006 = format5.format(this.division(eve.getObjA188(), eve.getSumActualdemo().doubleValue()));
		String J007 = format2.format(eve.getObjA195()/index);
		String J008 = format2.format(eve.getObjA196()/index);
		String J009 = format2.format(eve.getObjA197()/index);
		String J010 = format2.format(eve.getObjA198()/index);
		String J011 = format2.format(eve.getObjA199()/index);

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
	public void init(HSSFWorkbook wb,Map<String,Object>map_data,Map<String,Object>map_cs,List<String>list_head,Map<String,Object>map_head){		
		Map<String,Object>map=findItems();//左三列
		HSSFCellStyle cs_title=(HSSFCellStyle)map_cs.get("cs_title");
		HSSFCellStyle cs_head=(HSSFCellStyle)map_cs.get("cs_head");
		HSSFCellStyle cs_bold=(HSSFCellStyle)map_cs.get("cs_bold");
				
		//注意：初始行：150，初始列：30;項目有擴展時，要在這裏擴展行與列
		for(String factcode:map_data.keySet()){//for
			HSSFSheet sheet=wb.createSheet(factcode);
			for(int a=0;a<2;a++){
				sheet.setColumnWidth(a, 4800);
			}
			for(int a=2;a<35;a++){
				sheet.setColumnWidth(a, 4000);
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
			if(reportType.equals("3")){
				sheet.getRow(0).getCell(0).setCellValue(yymm+"台灣損益會議各廠檢討項目各廠對比表----分型態");
			}else{
				sheet.getRow(0).getCell(0).setCellValue(factNo+"每月檢討統計表");
			}
			
			for(int a=0;a<11;a++){
				sheet.getRow(0).getCell(a).setCellStyle(cs_title);
			}
			//廠別狀態
			sheet.getRow(2).getCell(0).setCellValue(factcode);
			//表頭
			/*************臺灣的表頭根據factcode動態變化的*************/
			if(reportType.equals("3")){
				list_head=(List<String>)map_head.get(factcode);
			}
			/*************臺灣的表頭根據factcode動態變化的*************/
			for(int a=0;a<list_head.size();a++){
				sheet.getRow(3).getCell(a).setCellValue(list_head.get(a));
				sheet.getRow(3).getCell(a).setCellStyle(cs_head);
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
	
	/**
	 * 季度，年度統計
	 * @Title: findList7
	 * @Description: TODO
	 * @param @param list
	 * @param @return
	 * @return List<VWebprofitlossEve>
	 * @throws
	 * @author web
	 * @date 2016/5/9
	 */
	public List<VWebprofitlossEve> findList7(List<VWebprofitlossEve>list){
		List<VWebprofitlossEve>list7=new ArrayList<VWebprofitlossEve>();
		for(int a=0;a<7;a++){
			list7.add(new VWebprofitlossEve());
		}
		int b_index=0;
		for(int b=1;b<list.size();b++){//for b
			b_index=(b-1)/3;			
			this.findTotal(list7,list7, list, b_index, b_index, b);
			if(b==12){
				for(int c=0;c<3;c++){					
					this.findTotal(list7, list7, list7, c+4, 2*c, 2*c+1);
				}
			}
		}//for b
		return list7;
	}
	
	/**
	 * 數據統計
	 * @Title: findTotal
	 * @Description: TODO
	 * @param @param list_to
	 * @param @param list_from
	 * @param @param a
	 * @param @param b
	 * @param @param c
	 * @return void
	 * @throws
	 * @author web
	 * @date 2016/5/9
	 */
	public void findTotal(List<VWebprofitlossEve>list_a,List<VWebprofitlossEve>list_b,List<VWebprofitlossEve>list_c,int a,int b,int c){
		list_a.get(a).setHole(list_b.get(b).getHole()+list_c.get(c).getHole());
		list_a.get(a).setObjA100(list_b.get(b).getObjA100()+list_c.get(c).getObjA100());
		list_a.get(a).setObjA101(list_b.get(b).getObjA101()+list_c.get(c).getObjA101());
		list_a.get(a).setObjA102(list_b.get(b).getObjA102()+list_c.get(c).getObjA102());
		list_a.get(a).setObjA103(list_b.get(b).getObjA103()+list_c.get(c).getObjA103());
		list_a.get(a).setObjA104(list_b.get(b).getObjA104()+list_c.get(c).getObjA104());
		list_a.get(a).setObjA105(list_b.get(b).getObjA105()+list_c.get(c).getObjA105());
		list_a.get(a).setObjA106(list_b.get(b).getObjA106()+list_c.get(c).getObjA106());
		list_a.get(a).setObjA107(list_b.get(b).getObjA107()+list_c.get(c).getObjA107());
		list_a.get(a).setObjA108(list_b.get(b).getObjA108()+list_c.get(c).getObjA108());
		list_a.get(a).setObjA109(list_b.get(b).getObjA109()+list_c.get(c).getObjA109());
		list_a.get(a).setObjA110(list_b.get(b).getObjA110()+list_c.get(c).getObjA110());
		list_a.get(a).setObjA111(list_b.get(b).getObjA111()+list_c.get(c).getObjA111());
		list_a.get(a).setObjA112(list_b.get(b).getObjA112()+list_c.get(c).getObjA112());
		list_a.get(a).setObjA113(list_b.get(b).getObjA113()+list_c.get(c).getObjA113());
		list_a.get(a).setObjA114(list_b.get(b).getObjA114()+list_c.get(c).getObjA114());
		list_a.get(a).setObjA115(list_b.get(b).getObjA115()+list_c.get(c).getObjA115());
		list_a.get(a).setObjA116(list_b.get(b).getObjA116()+list_c.get(c).getObjA116());
		list_a.get(a).setObjA117(list_b.get(b).getObjA117()+list_c.get(c).getObjA117());
		list_a.get(a).setObjA118(list_b.get(b).getObjA118()+list_c.get(c).getObjA118());
		list_a.get(a).setObjA119(list_b.get(b).getObjA119()+list_c.get(c).getObjA119());
		list_a.get(a).setObjA120(list_b.get(b).getObjA120()+list_c.get(c).getObjA120());
		list_a.get(a).setObjA121(list_b.get(b).getObjA121()+list_c.get(c).getObjA121());
		list_a.get(a).setObjA122(list_b.get(b).getObjA122()+list_c.get(c).getObjA122());
		list_a.get(a).setObjA123(list_b.get(b).getObjA123()+list_c.get(c).getObjA123());
		list_a.get(a).setObjA124(list_b.get(b).getObjA124()+list_c.get(c).getObjA124());
		list_a.get(a).setObjA125(list_b.get(b).getObjA125()+list_c.get(c).getObjA125());
		list_a.get(a).setObjA126(list_b.get(b).getObjA126()+list_c.get(c).getObjA126());
		list_a.get(a).setObjA127(list_b.get(b).getObjA127()+list_c.get(c).getObjA127());
		list_a.get(a).setObjA128(list_b.get(b).getObjA128()+list_c.get(c).getObjA128());
		list_a.get(a).setObjA129(list_b.get(b).getObjA129()+list_c.get(c).getObjA129());
		list_a.get(a).setObjA130(list_b.get(b).getObjA130()+list_c.get(c).getObjA130());
		list_a.get(a).setObjA131(list_b.get(b).getObjA131()+list_c.get(c).getObjA131());
		list_a.get(a).setObjA132(list_b.get(b).getObjA132()+list_c.get(c).getObjA132());
		list_a.get(a).setObjA133(list_b.get(b).getObjA133()+list_c.get(c).getObjA133());
		list_a.get(a).setObjA134(list_b.get(b).getObjA134()+list_c.get(c).getObjA134());
		list_a.get(a).setObjA135(list_b.get(b).getObjA135()+list_c.get(c).getObjA135());
		list_a.get(a).setObjA136(list_b.get(b).getObjA136()+list_c.get(c).getObjA136());
		list_a.get(a).setObjA137(list_b.get(b).getObjA137()+list_c.get(c).getObjA137());
		list_a.get(a).setObjA138(list_b.get(b).getObjA138()+list_c.get(c).getObjA138());
		list_a.get(a).setObjA139(list_b.get(b).getObjA139()+list_c.get(c).getObjA139());
		list_a.get(a).setObjA140(list_b.get(b).getObjA140()+list_c.get(c).getObjA140());
		list_a.get(a).setObjA141(list_b.get(b).getObjA141()+list_c.get(c).getObjA141());
		list_a.get(a).setObjA142(list_b.get(b).getObjA142()+list_c.get(c).getObjA142());
		list_a.get(a).setObjA143(list_b.get(b).getObjA143()+list_c.get(c).getObjA143());
		list_a.get(a).setObjA144(list_b.get(b).getObjA144()+list_c.get(c).getObjA144());
		list_a.get(a).setObjA145(list_b.get(b).getObjA145()+list_c.get(c).getObjA145());
		list_a.get(a).setObjA146(list_b.get(b).getObjA146()+list_c.get(c).getObjA146());
		list_a.get(a).setObjA147(list_b.get(b).getObjA147()+list_c.get(c).getObjA147());
		list_a.get(a).setObjA148(list_b.get(b).getObjA148()+list_c.get(c).getObjA148());
		list_a.get(a).setObjA149(list_b.get(b).getObjA149()+list_c.get(c).getObjA149());
		list_a.get(a).setObjA150(list_b.get(b).getObjA150()+list_c.get(c).getObjA150());
		list_a.get(a).setObjA151(list_b.get(b).getObjA151()+list_c.get(c).getObjA151());
		list_a.get(a).setObjA152(list_b.get(b).getObjA152()+list_c.get(c).getObjA152());
		list_a.get(a).setObjA153(list_b.get(b).getObjA153()+list_c.get(c).getObjA153());
		list_a.get(a).setObjA154(list_b.get(b).getObjA154()+list_c.get(c).getObjA154());
		list_a.get(a).setObjA155(list_b.get(b).getObjA155()+list_c.get(c).getObjA155());
		list_a.get(a).setObjA156(list_b.get(b).getObjA156()+list_c.get(c).getObjA156());
		list_a.get(a).setObjA157(list_b.get(b).getObjA157()+list_c.get(c).getObjA157());
		list_a.get(a).setObjA158(list_b.get(b).getObjA158()+list_c.get(c).getObjA158());
		list_a.get(a).setObjA159(list_b.get(b).getObjA159()+list_c.get(c).getObjA159());
		list_a.get(a).setObjA160(list_b.get(b).getObjA160()+list_c.get(c).getObjA160());
		list_a.get(a).setObjA161(list_b.get(b).getObjA161()+list_c.get(c).getObjA161());
		list_a.get(a).setObjA162(list_b.get(b).getObjA162()+list_c.get(c).getObjA162());
		list_a.get(a).setObjA163(list_b.get(b).getObjA163()+list_c.get(c).getObjA163());
		list_a.get(a).setObjA164(list_b.get(b).getObjA164()+list_c.get(c).getObjA164());
		list_a.get(a).setObjA165(list_b.get(b).getObjA165()+list_c.get(c).getObjA165());
		list_a.get(a).setObjA166(list_b.get(b).getObjA166()+list_c.get(c).getObjA166());
		list_a.get(a).setObjA167(list_b.get(b).getObjA167()+list_c.get(c).getObjA167());
		list_a.get(a).setObjA168(list_b.get(b).getObjA168()+list_c.get(c).getObjA168());
		list_a.get(a).setObjA169(list_b.get(b).getObjA169()+list_c.get(c).getObjA169());
		list_a.get(a).setObjA170(list_b.get(b).getObjA170()+list_c.get(c).getObjA170());
		list_a.get(a).setObjA171(list_b.get(b).getObjA171()+list_c.get(c).getObjA171());
		list_a.get(a).setObjA172(list_b.get(b).getObjA172()+list_c.get(c).getObjA172());
		list_a.get(a).setObjA173(list_b.get(b).getObjA173()+list_c.get(c).getObjA173());
		list_a.get(a).setObjA174(list_b.get(b).getObjA174()+list_c.get(c).getObjA174());
		list_a.get(a).setObjA175(list_b.get(b).getObjA175()+list_c.get(c).getObjA175());
		list_a.get(a).setObjA176(list_b.get(b).getObjA176()+list_c.get(c).getObjA176());
		list_a.get(a).setObjA177(list_b.get(b).getObjA177()+list_c.get(c).getObjA177());
		list_a.get(a).setObjA178(list_b.get(b).getObjA178()+list_c.get(c).getObjA178());
		list_a.get(a).setObjA179(list_b.get(b).getObjA179()+list_c.get(c).getObjA179());
		list_a.get(a).setObjA180(list_b.get(b).getObjA180()+list_c.get(c).getObjA180());
		list_a.get(a).setObjA181(list_b.get(b).getObjA181()+list_c.get(c).getObjA181());
		list_a.get(a).setObjA182(list_b.get(b).getObjA182()+list_c.get(c).getObjA182());
		list_a.get(a).setObjA183(list_b.get(b).getObjA183()+list_c.get(c).getObjA183());
		list_a.get(a).setObjA184(list_b.get(b).getObjA184()+list_c.get(c).getObjA184());
		list_a.get(a).setObjA185(list_b.get(b).getObjA185()+list_c.get(c).getObjA185());
		list_a.get(a).setObjA186(list_b.get(b).getObjA186()+list_c.get(c).getObjA186());
		list_a.get(a).setObjA187(list_b.get(b).getObjA187()+list_c.get(c).getObjA187());
		list_a.get(a).setObjA188(list_b.get(b).getObjA188()+list_c.get(c).getObjA188());
		list_a.get(a).setObjA189(list_b.get(b).getObjA189()+list_c.get(c).getObjA189());
		list_a.get(a).setObjA190(list_b.get(b).getObjA190()+list_c.get(c).getObjA190());
		list_a.get(a).setObjA191(list_b.get(b).getObjA191()+list_c.get(c).getObjA191());
		list_a.get(a).setObjA192(list_b.get(b).getObjA192()+list_c.get(c).getObjA192());
		list_a.get(a).setObjA193(list_b.get(b).getObjA193()+list_c.get(c).getObjA193());
		list_a.get(a).setObjA194(list_b.get(b).getObjA194()+list_c.get(c).getObjA194());
		list_a.get(a).setObjA195(list_b.get(b).getObjA195()+list_c.get(c).getObjA195());
		list_a.get(a).setObjA196(list_b.get(b).getObjA196()+list_c.get(c).getObjA196());
		list_a.get(a).setObjA197(list_b.get(b).getObjA197()+list_c.get(c).getObjA197());
		list_a.get(a).setObjA198(list_b.get(b).getObjA198()+list_c.get(c).getObjA198());
		list_a.get(a).setObjA199(list_b.get(b).getObjA199()+list_c.get(c).getObjA199());
		list_a.get(a).setObjA200(list_b.get(b).getObjA200()+list_c.get(c).getObjA200());
		list_a.get(a).setObjA201(list_b.get(b).getObjA201()+list_c.get(c).getObjA201());
		list_a.get(a).setObjA202(list_b.get(b).getObjA202()+list_c.get(c).getObjA202());

		list_a.get(a).setMachinepower(list_b.get(b).getMachinepower()+list_c.get(c).getMachinepower());
		list_a.get(a).setEstdays(list_b.get(b).getEstdays()+list_c.get(c).getEstdays());
		list_a.get(a).setEsteverymodel(list_b.get(b).getEsteverymodel()+list_c.get(c).getEsteverymodel());
		list_a.get(a).setEsteverypeople(list_b.get(b).getEsteverypeople()+list_c.get(c).getEsteverypeople());
		list_a.get(a).setEstmodel(list_b.get(b).getEstmodel()+list_c.get(c).getEstmodel());
		list_a.get(a).setEstnum(list_b.get(b).getEstnum()+list_c.get(c).getEstnum());
		list_a.get(a).setEstpay(list_b.get(b).getEstpay()+list_c.get(c).getEstpay());
		list_a.get(a).setEstmoney(list_b.get(b).getEstmoney()+list_c.get(c).getEstmoney());
		list_a.get(a).setTotalhole(list_b.get(b).getTotalhole()+list_c.get(c).getTotalhole());
		list_a.get(a).setSample(list_b.get(b).getSample()+list_c.get(c).getSample());
		list_a.get(a).setAccessories(list_b.get(b).getAccessories()+list_c.get(c).getAccessories());
		list_a.get(a).setOther(list_b.get(b).getOther()+list_c.get(c).getOther());
		list_a.get(a).setSumWorkdays(list_b.get(b).getSumWorkdays().add(list_c.get(c).getSumWorkdays()));
		list_a.get(a).setSumEverypeople(list_b.get(b).getSumEverypeople().add(list_c.get(c).getSumEverypeople()));
		list_a.get(a).setSumEverydemo(list_b.get(b).getSumEverydemo().add(list_c.get(c).getSumEverydemo()));
		list_a.get(a).setSumStandarddemo(list_b.get(b).getSumStandarddemo().add(list_c.get(c).getSumStandarddemo()));
		list_a.get(a).setSumActualdemo(list_b.get(b).getSumActualdemo().add(list_c.get(c).getSumActualdemo()));
		list_a.get(a).setSumActualpairs(list_b.get(b).getSumActualpairs().add(list_c.get(c).getSumActualpairs()));
		list_a.get(a).setSumHostpairs(list_b.get(b).getSumHostpairs().add(list_c.get(c).getSumHostpairs()));
		list_a.get(a).setSumFactpairs(list_b.get(b).getSumFactpairs().add(list_c.get(c).getSumFactpairs()));
		list_a.get(a).setSumSamplepairs(list_b.get(b).getSumSamplepairs().add(list_c.get(c).getSumSamplepairs()));
		list_a.get(a).setSumOutnum(list_b.get(b).getSumOutnum().add(list_c.get(c).getSumOutnum()));
		list_a.get(a).setSumBacknum(list_b.get(b).getSumBacknum().add(list_c.get(c).getSumBacknum()));
		list_a.get(a).setSumWorkhours(list_b.get(b).getSumWorkhours()+list_c.get(c).getSumWorkhours());
	}
		
}
