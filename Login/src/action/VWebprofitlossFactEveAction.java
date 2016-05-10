/**
 * 
 */
package action;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;
import entity.VWebprofitlossFactEve;
import entity.VWebprofitlossFactEveId;
import services.IVWebprofitlossFactEveServices;
import services.IWebFactServices;
import util.GlobalMethod;

/**   
 *    
 * 项目名称：Login   
 * 类名称：VWebprofitlossFactEveAction   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/5/10 下午2:04:14   
 * 修改人：Administrator   
 * 修改时间：2016/5/10 下午2:04:14   
 * 修改备注：   
 * @version    
 *    
 **/
public class VWebprofitlossFactEveAction implements ServletResponseAware{

	/**
	 * 日期:2016/5/10
	 * 描述:
	 */
	private javax.servlet.http.HttpServletResponse response;
	private String year;
	private String yymm;
	private String yymm2;
	private String factNo;
	private List<String>list_factno;
	private String reportType;//報表類型 1=全廠的      2=分月份的       3=臺灣的
	private IWebFactServices webFactSer;
	private IVWebprofitlossFactEveServices vwebprolossfacteveSer;
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
	public List<String> getList_factno() {
		return list_factno;
	}
	public void setList_factno(List<String> list_factno) {
		this.list_factno = list_factno;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}
	public void setVwebprolossfacteveSer(
			IVWebprofitlossFactEveServices vwebprolossfacteveSer) {
		this.vwebprolossfacteveSer = vwebprolossfacteveSer;
	}
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	
	public void print_fact() throws IOException{
		reportType="1";
		year="2016";
		factNo="631";
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
		//List<String>list_factcode=webFactSer.findByFactNo_showA(factNo);
		List<String>list_factcode=new ArrayList<String>();
		list_factcode.add("sheet");
		for(String factcode:list_factcode){//for a
			List<List<VWebprofitlossFactEve>>list_all=new ArrayList<List<VWebprofitlossFactEve>>();			
				List<VWebprofitlossFactEve>list_a=new ArrayList<VWebprofitlossFactEve>();
				List<VWebprofitlossFactEve>list_b=new ArrayList<VWebprofitlossFactEve>();
				for(String month:list_month){
					list_a.add(new VWebprofitlossFactEve(new VWebprofitlossFactEveId(factNo,month)));
				}
				for(String month:list_month2){
					list_b.add(new VWebprofitlossFactEve(new VWebprofitlossFactEveId(factNo,month)));
				}
				list_all.add(list_a);
				list_all.add(list_b);
						
			map_data.put(factcode, list_all);
		}//for a
		
		//今年數據（包括上年的12份數據）
		List<VWebprofitlossFactEve>list_eve=vwebprolossfacteveSer.findByYymm(factNo,list_month.get(0), list_month.get(12));//開始：上年12月      結束：今年12月
		//上年數據
		List<VWebprofitlossFactEve>list_eve2=vwebprolossfacteveSer.findByYymm(factNo,last_year+"01",last_year+"12");
		List<List<VWebprofitlossFactEve>>list_eve_all=new ArrayList<List<VWebprofitlossFactEve>>();
		list_eve_all.add(list_eve);
		list_eve_all.add(list_eve2);
		
		for(String factcode:map_data.keySet()){//for a
			List<List<VWebprofitlossFactEve>> list_all=(List<List<VWebprofitlossFactEve>>)map_data.get(factcode);
			for(int b=0;b<list_all.size();b++){//for b
				for(int c=0;c<list_all.get(b).size();c++){//for c
					for(VWebprofitlossFactEve eve:list_eve_all.get(b)){
						if(list_all.get(b).get(c).getId().getFactNo().equals(eve.getId().getFactNo())						   
						   &&list_all.get(b).get(c).getId().getYymm().equals(eve.getId().getYymm())){
							list_all.get(b).remove(c);
							list_all.get(b).add(c, eve);
							break;
						}					
					}
				}//for c
			}//for b
			
			List<VWebprofitlossFactEve>list7_a=this.findList7(list_all.get(0));//今年季度，年度統計
			List<VWebprofitlossFactEve>list7_b=this.findList7(list_all.get(1));//上年季度，年度統計
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
	
	public void print_month(){
		reportType="2";
		
	}
	
	public void print_tw(){
		reportType="3";
		
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
		
		list_1.add("總銷貨收入__USD");
		list_1.add("成本合計 __USD");
		list_1.add("工資合計 __USD");
		list_1.add("費用合計 __USD");
		list_1.add("其他  __USD");
		list_1.add("本期損益(USD)__USD");
		
		list_2.add("總原料庫存量KG__KG");
		list_2.add("總原料庫存金額(USD)__USD");
		list_2.add("平均單價__USD");
		list_2.add("漲幅__%");
		list_2.add("膠類庫存量(KG)__KG");
		list_2.add("膠類庫存量(頓)__噸");
		list_2.add("膠類金額(USD)__USD");
		list_2.add("平均單價__USD");
		list_2.add("漲幅__%");
		
		list_3.add("生產模數__模");
		list_3.add("水用量(噸)__噸");
		list_3.add("用水單秏(模)__噸/模");
		list_3.add("用水金額(USD)__USD");
		list_3.add("用水金額單秏(模)__USD/模");
		list_3.add("用電量(度)__度");
		list_3.add("用電單秏(模)__度/模");
		list_3.add("用電金額(USD)__USD");
		list_3.add("用電金額單秏(模)__USD/模");
		list_3.add("蒸氣用量(T)__噸");
		list_3.add("用蒸氣單秏(模)__噸/模");
		list_3.add("用蒸氣金額(USD)__USD");
		list_3.add("用蒸氣金額單秏(模)__USD/模");
		list_3.add("柴油用量(公升)__公升");
		list_3.add("用油單秏(模)__公升/模");
		list_3.add("柴油金額(USD)__USD");
		list_3.add("用油金額單秏(模)__USD/模");
		
		list_4.add("預計請款__雙");
		list_4.add("請款金額__USD");
		list_4.add("平均單價__雙");
		
		list_5.add("預計生產__模");
		list_5.add("機臺標准戰力__模");
		list_5.add("機孔達成率__%");
		
		map.put("損益彙總", list_1);
		map.put("全廠原料庫存", list_2);
		map.put("水電油", list_3);
		map.put("下月請款狀況", list_4);
		map.put("下月生產狀況", list_5);
		
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
			if(reportType.equals("3")){
				sheet.getRow(0).getCell(0).setCellValue(yymm+"台灣損益會議各廠檢討項目各廠對比表----全廠總計");
			}else{
				sheet.getRow(0).getCell(0).setCellValue(factNo+"每月檢討項目統計表---全廠(各型態之合計)");
			}
			
			for(int a=0;a<11;a++){
				sheet.getRow(0).getCell(a).setCellStyle(cs_head);
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
	public List<VWebprofitlossFactEve> findList7(List<VWebprofitlossFactEve>list){
		List<VWebprofitlossFactEve>list7=new ArrayList<VWebprofitlossFactEve>();
		for(int a=0;a<7;a++){
			list7.add(new VWebprofitlossFactEve());
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
	public void findTotal(List<VWebprofitlossFactEve>list_a,List<VWebprofitlossFactEve>list_b,List<VWebprofitlossFactEve>list_c,int a,int b,int c){
		list_a.get(a).setHole(list_b.get(b).getHole().add(list_c.get(c).getHole()));
		list_a.get(a).setObjA100(list_b.get(b).getObjA100().add(list_c.get(c).getObjA100()));
		list_a.get(a).setObjA101(list_b.get(b).getObjA101().add(list_c.get(c).getObjA101()));
		list_a.get(a).setObjA102(list_b.get(b).getObjA102().add(list_c.get(c).getObjA102()));
		list_a.get(a).setObjA103(list_b.get(b).getObjA103().add(list_c.get(c).getObjA103()));
		list_a.get(a).setObjA104(list_b.get(b).getObjA104().add(list_c.get(c).getObjA104()));
		list_a.get(a).setObjA105(list_b.get(b).getObjA105().add(list_c.get(c).getObjA105()));
		list_a.get(a).setObjA106(list_b.get(b).getObjA106().add(list_c.get(c).getObjA106()));
		list_a.get(a).setObjA107(list_b.get(b).getObjA107().add(list_c.get(c).getObjA107()));
		list_a.get(a).setObjA108(list_b.get(b).getObjA108().add(list_c.get(c).getObjA108()));
		list_a.get(a).setObjA109(list_b.get(b).getObjA109().add(list_c.get(c).getObjA109()));
		list_a.get(a).setObjA110(list_b.get(b).getObjA110().add(list_c.get(c).getObjA110()));
		list_a.get(a).setObjA111(list_b.get(b).getObjA111().add(list_c.get(c).getObjA111()));
		list_a.get(a).setObjA112(list_b.get(b).getObjA112().add(list_c.get(c).getObjA112()));
		list_a.get(a).setObjA113(list_b.get(b).getObjA113().add(list_c.get(c).getObjA113()));
		list_a.get(a).setObjA114(list_b.get(b).getObjA114().add(list_c.get(c).getObjA114()));
		list_a.get(a).setObjA115(list_b.get(b).getObjA115().add(list_c.get(c).getObjA115()));
		list_a.get(a).setObjA116(list_b.get(b).getObjA116().add(list_c.get(c).getObjA116()));
		list_a.get(a).setObjA117(list_b.get(b).getObjA117().add(list_c.get(c).getObjA117()));
		list_a.get(a).setObjA118(list_b.get(b).getObjA118().add(list_c.get(c).getObjA118()));
		list_a.get(a).setObjA119(list_b.get(b).getObjA119().add(list_c.get(c).getObjA119()));
		list_a.get(a).setObjA120(list_b.get(b).getObjA120().add(list_c.get(c).getObjA120()));
		list_a.get(a).setObjA121(list_b.get(b).getObjA121().add(list_c.get(c).getObjA121()));
		list_a.get(a).setObjA122(list_b.get(b).getObjA122().add(list_c.get(c).getObjA122()));
		list_a.get(a).setObjA123(list_b.get(b).getObjA123().add(list_c.get(c).getObjA123()));
		list_a.get(a).setObjA124(list_b.get(b).getObjA124().add(list_c.get(c).getObjA124()));
		list_a.get(a).setObjA125(list_b.get(b).getObjA125().add(list_c.get(c).getObjA125()));
		list_a.get(a).setObjA126(list_b.get(b).getObjA126().add(list_c.get(c).getObjA126()));
		list_a.get(a).setObjA127(list_b.get(b).getObjA127().add(list_c.get(c).getObjA127()));
		list_a.get(a).setObjA128(list_b.get(b).getObjA128().add(list_c.get(c).getObjA128()));
		list_a.get(a).setObjA129(list_b.get(b).getObjA129().add(list_c.get(c).getObjA129()));
		list_a.get(a).setObjA130(list_b.get(b).getObjA130().add(list_c.get(c).getObjA130()));
		list_a.get(a).setObjA131(list_b.get(b).getObjA131().add(list_c.get(c).getObjA131()));
		list_a.get(a).setObjA132(list_b.get(b).getObjA132().add(list_c.get(c).getObjA132()));
		list_a.get(a).setObjA133(list_b.get(b).getObjA133().add(list_c.get(c).getObjA133()));
		list_a.get(a).setObjA134(list_b.get(b).getObjA134().add(list_c.get(c).getObjA134()));
		list_a.get(a).setObjA135(list_b.get(b).getObjA135().add(list_c.get(c).getObjA135()));
		list_a.get(a).setObjA136(list_b.get(b).getObjA136().add(list_c.get(c).getObjA136()));
		list_a.get(a).setObjA137(list_b.get(b).getObjA137().add(list_c.get(c).getObjA137()));
		list_a.get(a).setObjA138(list_b.get(b).getObjA138().add(list_c.get(c).getObjA138()));
		list_a.get(a).setObjA139(list_b.get(b).getObjA139().add(list_c.get(c).getObjA139()));
		list_a.get(a).setObjA140(list_b.get(b).getObjA140().add(list_c.get(c).getObjA140()));
		list_a.get(a).setObjA141(list_b.get(b).getObjA141().add(list_c.get(c).getObjA141()));
		list_a.get(a).setObjA142(list_b.get(b).getObjA142().add(list_c.get(c).getObjA142()));
		list_a.get(a).setObjA143(list_b.get(b).getObjA143().add(list_c.get(c).getObjA143()));
		list_a.get(a).setObjA144(list_b.get(b).getObjA144().add(list_c.get(c).getObjA144()));
		list_a.get(a).setObjA145(list_b.get(b).getObjA145().add(list_c.get(c).getObjA145()));
		list_a.get(a).setObjA146(list_b.get(b).getObjA146().add(list_c.get(c).getObjA146()));
		list_a.get(a).setObjA147(list_b.get(b).getObjA147().add(list_c.get(c).getObjA147()));
		list_a.get(a).setObjA148(list_b.get(b).getObjA148().add(list_c.get(c).getObjA148()));
		list_a.get(a).setObjA149(list_b.get(b).getObjA149().add(list_c.get(c).getObjA149()));
		list_a.get(a).setObjA150(list_b.get(b).getObjA150().add(list_c.get(c).getObjA150()));
		list_a.get(a).setObjA151(list_b.get(b).getObjA151().add(list_c.get(c).getObjA151()));
		list_a.get(a).setObjA152(list_b.get(b).getObjA152().add(list_c.get(c).getObjA152()));
		list_a.get(a).setObjA153(list_b.get(b).getObjA153().add(list_c.get(c).getObjA153()));
		list_a.get(a).setObjA154(list_b.get(b).getObjA154().add(list_c.get(c).getObjA154()));
		list_a.get(a).setObjA155(list_b.get(b).getObjA155().add(list_c.get(c).getObjA155()));
		list_a.get(a).setObjA156(list_b.get(b).getObjA156().add(list_c.get(c).getObjA156()));
		list_a.get(a).setObjA157(list_b.get(b).getObjA157().add(list_c.get(c).getObjA157()));
		list_a.get(a).setObjA158(list_b.get(b).getObjA158().add(list_c.get(c).getObjA158()));
		list_a.get(a).setObjA159(list_b.get(b).getObjA159().add(list_c.get(c).getObjA159()));
		list_a.get(a).setObjA160(list_b.get(b).getObjA160().add(list_c.get(c).getObjA160()));
		list_a.get(a).setObjA161(list_b.get(b).getObjA161().add(list_c.get(c).getObjA161()));
		list_a.get(a).setObjA162(list_b.get(b).getObjA162().add(list_c.get(c).getObjA162()));
		list_a.get(a).setObjA163(list_b.get(b).getObjA163().add(list_c.get(c).getObjA163()));
		list_a.get(a).setObjA164(list_b.get(b).getObjA164().add(list_c.get(c).getObjA164()));
		list_a.get(a).setObjA165(list_b.get(b).getObjA165().add(list_c.get(c).getObjA165()));
		list_a.get(a).setObjA166(list_b.get(b).getObjA166().add(list_c.get(c).getObjA166()));
		list_a.get(a).setObjA167(list_b.get(b).getObjA167().add(list_c.get(c).getObjA167()));
		list_a.get(a).setObjA168(list_b.get(b).getObjA168().add(list_c.get(c).getObjA168()));
		list_a.get(a).setObjA169(list_b.get(b).getObjA169().add(list_c.get(c).getObjA169()));
		list_a.get(a).setObjA170(list_b.get(b).getObjA170().add(list_c.get(c).getObjA170()));
		list_a.get(a).setObjA171(list_b.get(b).getObjA171().add(list_c.get(c).getObjA171()));
		list_a.get(a).setObjA172(list_b.get(b).getObjA172().add(list_c.get(c).getObjA172()));
		list_a.get(a).setObjA173(list_b.get(b).getObjA173().add(list_c.get(c).getObjA173()));
		list_a.get(a).setObjA174(list_b.get(b).getObjA174().add(list_c.get(c).getObjA174()));
		list_a.get(a).setObjA175(list_b.get(b).getObjA175().add(list_c.get(c).getObjA175()));
		list_a.get(a).setObjA176(list_b.get(b).getObjA176().add(list_c.get(c).getObjA176()));
		list_a.get(a).setObjA177(list_b.get(b).getObjA177().add(list_c.get(c).getObjA177()));
		list_a.get(a).setObjA178(list_b.get(b).getObjA178().add(list_c.get(c).getObjA178()));
		list_a.get(a).setObjA179(list_b.get(b).getObjA179().add(list_c.get(c).getObjA179()));
		list_a.get(a).setObjA180(list_b.get(b).getObjA180().add(list_c.get(c).getObjA180()));
		list_a.get(a).setObjA181(list_b.get(b).getObjA181().add(list_c.get(c).getObjA181()));
		list_a.get(a).setObjA182(list_b.get(b).getObjA182().add(list_c.get(c).getObjA182()));
		list_a.get(a).setObjA183(list_b.get(b).getObjA183().add(list_c.get(c).getObjA183()));
		list_a.get(a).setObjA184(list_b.get(b).getObjA184().add(list_c.get(c).getObjA184()));
		list_a.get(a).setObjA185(list_b.get(b).getObjA185().add(list_c.get(c).getObjA185()));
		list_a.get(a).setObjA186(list_b.get(b).getObjA186().add(list_c.get(c).getObjA186()));
		list_a.get(a).setObjA187(list_b.get(b).getObjA187().add(list_c.get(c).getObjA187()));
		list_a.get(a).setObjA188(list_b.get(b).getObjA188().add(list_c.get(c).getObjA188()));
		list_a.get(a).setObjA189(list_b.get(b).getObjA189().add(list_c.get(c).getObjA189()));
		list_a.get(a).setObjA190(list_b.get(b).getObjA190().add(list_c.get(c).getObjA190()));
		list_a.get(a).setObjA191(list_b.get(b).getObjA191().add(list_c.get(c).getObjA191()));
		list_a.get(a).setObjA192(list_b.get(b).getObjA192().add(list_c.get(c).getObjA192()));
		list_a.get(a).setObjA193(list_b.get(b).getObjA193().add(list_c.get(c).getObjA193()));
		list_a.get(a).setObjA194(list_b.get(b).getObjA194().add(list_c.get(c).getObjA194()));
		list_a.get(a).setObjA195(list_b.get(b).getObjA195().add(list_c.get(c).getObjA195()));
		list_a.get(a).setObjA196(list_b.get(b).getObjA196().add(list_c.get(c).getObjA196()));
		list_a.get(a).setObjA197(list_b.get(b).getObjA197().add(list_c.get(c).getObjA197()));
		list_a.get(a).setObjA198(list_b.get(b).getObjA198().add(list_c.get(c).getObjA198()));
		list_a.get(a).setObjA199(list_b.get(b).getObjA199().add(list_c.get(c).getObjA199()));
		list_a.get(a).setObjA200(list_b.get(b).getObjA200().add(list_c.get(c).getObjA200()));
		list_a.get(a).setObjA201(list_b.get(b).getObjA201().add(list_c.get(c).getObjA201()));
		list_a.get(a).setObjA202(list_b.get(b).getObjA202().add(list_c.get(c).getObjA202()));

		list_a.get(a).setMachinepower(list_b.get(b).getMachinepower().add(list_c.get(c).getMachinepower()));
		list_a.get(a).setEstdays(list_b.get(b).getEstdays().add(list_c.get(c).getEstdays()));
		list_a.get(a).setEsteverymodel(list_b.get(b).getEsteverymodel().add(list_c.get(c).getEsteverymodel()));
		list_a.get(a).setEsteverypeople(list_b.get(b).getEsteverypeople().add(list_c.get(c).getEsteverypeople()));
		list_a.get(a).setEstmodel(list_b.get(b).getEstmodel().add(list_c.get(c).getEstmodel()));
		list_a.get(a).setEstnum(list_b.get(b).getEstnum().add(list_c.get(c).getEstnum()));
		list_a.get(a).setEstpay(list_b.get(b).getEstpay().add(list_c.get(c).getEstpay()));
		list_a.get(a).setEstmoney(list_b.get(b).getEstmoney().add(list_c.get(c).getEstmoney()));
		list_a.get(a).setTotalhole(list_b.get(b).getTotalhole().add(list_c.get(c).getTotalhole()));
		list_a.get(a).setSample(list_b.get(b).getSample().add(list_c.get(c).getSample()));
		list_a.get(a).setAccessories(list_b.get(b).getAccessories().add(list_c.get(c).getAccessories()));
		list_a.get(a).setOther(list_b.get(b).getOther().add(list_c.get(c).getOther()));
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
		list_a.get(a).setSumWorkhours(list_b.get(b).getSumWorkhours().add(list_c.get(c).getSumWorkhours()));
	}
	
	public List<String> findResult(int index, VWebprofitlossFactEve eve,
			VWebprofitlossFactEve eve2) {// eve2:爲上箇月的數據
		List<String> list = new ArrayList<String>();
		DecimalFormat format = new DecimalFormat("#,##0");
		DecimalFormat format1 = new DecimalFormat("#,##0.0");
		DecimalFormat format2 = new DecimalFormat("#,##0.00");
		// DecimalFormat format4=new DecimalFormat("#,##0.0000");
		DecimalFormat format5 = new DecimalFormat("#,##0.00000");
		DecimalFormat format_per1 = new DecimalFormat("0.0%");
		DecimalFormat format_per2 = new DecimalFormat("0.00%");

		String A001 = format2.format(eve.getObjA100());
		String A002 = format2.format(eve.getObjA110());
		String A003 = format2.format(eve.getObjA128().add(eve.getObjA129()));
		String A004 = format2.format(eve.getObjA134());
		String A005 = format2.format(eve.getObjA135());
		String A006 = format2.format(eve.getObjA100().doubleValue()
				- eve.getObjA110().doubleValue()
				- eve.getObjA128().doubleValue()
				- eve.getObjA129().doubleValue()
				- eve.getObjA134().doubleValue()
				+ eve.getObjA135().doubleValue());
		String B001 = format2.format(eve.getObjA177());
		String B002 = format2.format(eve.getObjA178());
		String B003 = format2.format(this.division(eve.getObjA178()
				.doubleValue(), eve.getObjA177().doubleValue()));
		String B004 = format_per2.format(this.division(this.division(eve
				.getObjA178().doubleValue(), eve.getObjA177().doubleValue()),
				this.division(eve2.getObjA178().doubleValue(), eve2
						.getObjA177().doubleValue())) - 1);
		String B005 = format2.format(eve.getObjA179());
		String B006 = format2.format(eve.getObjA179().doubleValue() / 1000);
		String B007 = format2.format(eve.getObjA180());
		String B008 = format2.format(this.division(eve.getObjA180()
				.doubleValue(), eve.getObjA179().doubleValue()));
		String B009 = format_per2.format((this.division(this.division(eve
				.getObjA180().doubleValue(), eve.getObjA179().doubleValue()),
				this.division(eve2.getObjA180().doubleValue(), eve2
						.getObjA179().doubleValue())) - 1));
		String C001 = format2.format(eve.getSumActualdemo());
		String C002 = format2.format(eve.getObjA153());
		String C003 = format5.format(this.division(eve.getObjA153()
				.doubleValue(), eve.getSumActualdemo().doubleValue()));
		String C004 = format2.format(eve.getObjA154());
		String C005 = format5.format(this.division(eve.getObjA154()
				.doubleValue(), eve.getSumActualdemo().doubleValue()));
		String C006 = format2.format(eve.getObjA155());
		String C007 = format5.format(this.division(eve.getObjA155()
				.doubleValue(), eve.getSumActualdemo().doubleValue()));
		String C008 = format2.format(eve.getObjA156());
		String C009 = format5.format(this.division(eve.getObjA156()
				.doubleValue(), eve.getSumActualdemo().doubleValue()));
		String C010 = format2.format(eve.getObjA157());
		String C011 = format5.format(this.division(eve.getObjA157()
				.doubleValue(), eve.getSumActualdemo().doubleValue()));
		String C012 = format2.format(eve.getObjA158());
		String C013 = format5.format(this.division(eve.getObjA158()
				.doubleValue(), eve.getSumActualdemo().doubleValue()));
		String C014 = format2.format(eve.getObjA159());
		String C015 = format5.format(this.division(eve.getObjA159()
				.doubleValue(), eve.getSumActualdemo().doubleValue()));
		String C016 = format2.format(eve.getObjA160());
		String C017 = format5.format(this.division(eve.getObjA160()
				.doubleValue(), eve.getSumActualdemo().doubleValue()));
		String D001 = format2.format(eve.getEstpay());
		String D002 = format2.format(eve.getEstmoney());
		String D003 = format2.format(this.division(eve.getEstmoney()
				.doubleValue(), eve.getEstpay().doubleValue()));
		String D004 = format2.format(eve.getEstmodel());
		String D005 = format2.format(eve.getMachinepower());
		String D006 = format_per2.format(this.division(eve.getEstmodel()
				.doubleValue(), eve.getMachinepower().doubleValue()));

		list.add(A001);
		list.add(A002);
		list.add(A003);
		list.add(A004);
		list.add(A005);
		list.add(A006);
		list.add(B001);
		list.add(B002);
		list.add(B003);
		list.add(B004);
		list.add(B005);
		list.add(B006);
		list.add(B007);
		list.add(B008);
		list.add(B009);
		list.add(C001);
		list.add(C002);
		list.add(C003);
		list.add(C004);
		list.add(C005);
		list.add(C006);
		list.add(C007);
		list.add(C008);
		list.add(C009);
		list.add(C010);
		list.add(C011);
		list.add(C012);
		list.add(C013);
		list.add(C014);
		list.add(C015);
		list.add(C016);
		list.add(C017);
		list.add(D001);
		list.add(D002);
		list.add(D003);
		list.add(D004);
		list.add(D005);
		list.add(D006);

		return list;

	}

}
