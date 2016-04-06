/**
 * 
 */
package action;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

import entity.VWebbussort;
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
	private IVWebbussortServices vwebbusssorSer;
	private IWebFactServices webFactSer;
	private javax.servlet.http.HttpServletResponse response;
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
		yymm="201601";
		yymm2="201603";
		HSSFWorkbook wb=new HSSFWorkbook();
		Map<String,Object>map=this.findStyles(wb);
		HSSFCellStyle cs=(HSSFCellStyle)map.get("cs");
		HSSFCellStyle cs_head=(HSSFCellStyle)map.get("cs_head");
		HSSFCellStyle cs_column=(HSSFCellStyle)map.get("cs_column");
		
		List<VWebbussort>lists=vwebbusssorSer.findByYymm(yymm, yymm2);
		List<String>list_months=GlobalMethod.findMonths(yymm, yymm2);
		List<Object[]>list_facts=webFactSer.findFactAble2();
		
		Map<String,String>map_types=this.findTypes();//類型
		List<String>list_items=this.findItems();//項目
		List<String>list_units=this.findUnits();//單位
		
		/********************數據源處理*************************/
	    List<List<VWebbussort>>list_all=new ArrayList<List<VWebbussort>>();
	    for(String month:list_months){
	    	List<VWebbussort>list=new ArrayList<VWebbussort>();
	    	for(Object[] obj:list_facts){
	    		list.add(new VWebbussort(new VWebbussortId(obj[0].toString(),month)));	    		
	    	}
	    	list_all.add(list);
	    }
	    for(List<VWebbussort>lists2:list_all){
	    	for(VWebbussort sort2:lists2){
	    		for(VWebbussort sort:lists){
	    			if(sort2.getId().getFactNo().equals(sort.getId().getFactNo())&&sort2.getId().getYymm().equals(sort.getId().getYymm())){
	    				sort2=sort;
	    				break;
	    			}
	    		}
	    	}
	    }
	    
		/********************數據源處理*************************/
	    
	    
	    /********************初始化表格*************************/
	    for(String month:list_months){
	    	wb.createSheet(month);
	    	for(int b=0;b<list_items.size()+5;b++){
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
	    	sheet.getRow(0).getCell(0).setCellStyle(cs_head);
	    	for(int a=1;a<6;a++){
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
	    	for(int a=0;a<map_types.size();a++){
	    		sheet.getRow(2+a).getCell(0).setCellValue(map_types.get(a));
	    		sheet.getRow(2+a).getCell(0).setCellStyle(cs);
	    	}
	    	for(int a=0;a<list_items.size();a++){
	    		sheet.getRow(2+a).getCell(1).setCellValue(list_items.get(a));
	    		sheet.getRow(2+a).getCell(1).setCellStyle(cs);
	    	}
	    	for(int a=0;a<list_units.size();a++){
	    		sheet.getRow(2+a).getCell(2).setCellValue(list_units.get(a));
	    		sheet.getRow(2+a).getCell(2).setCellStyle(cs);
	    	}
	    	/********************分類+項目+單位*******************/
	    	
	    }//for1

		try {
			OutputStream os = new FileOutputStream("E:/" + "websort.xls");
			wb.write(os);
			os.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    
		
	}
	
	/**
	 * 所有分類
	 * @Title: findTypes
	 * @Description: TODO
	 * @param @return
	 * @return List<String>
	 * @throws
	 * @author web
	 * @date 2016/4/6
	 */
	public Map<String,String> findTypes() {
		Map<String,String>map = new HashMap<String,String>();
		map.put("產能","產能");
		map.put("水","水");
		map.put("電","電");
		map.put("蒸汽","蒸汽");
		map.put("總工務費用金額(USD)成倉","總工務費用金額(USD)成倉");
		map.put("原物料","原物料");
		map.put("防霜劑","防霜劑");
		map.put("色料","色料");
		map.put("促進劑","促進劑");
		map.put("防粘劑","防粘劑");
		map.put("油漆/處理劑","油漆/處理劑");
		map.put("離型劑","離型劑");
		map.put("人工費用","人工費用");
		map.put("其他","其他");
		map.put("廢品倉","廢品倉");
		
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
		List<String>list_17=new ArrayList<String>();
		List<String>list_18=new ArrayList<String>();
		List<String>list_19=new ArrayList<String>();
		List<String>list_20=new ArrayList<String>();
		List<String>list_21=new ArrayList<String>();
		List<String>list_22=new ArrayList<String>();
		List<String>list_23=new ArrayList<String>();
		
		//產能
		list_1.add("產能模");
		list_1.add("產能雙");
		list_1.add("生產天數");
		//水
		
		list_2.add("用水量");
		list_2.add("用水金額");
		list_2.add("用量單耗");
		list_2.add("費用單耗");
		list_2.add("用量單耗排名");
		
		

		return map;

	}
	
	/**
	 * 所有項目
	 * @Title: findItems
	 * @Description: TODO
	 * @param @return
	 * @return List<String>
	 * @throws
	 * @author web
	 * @date 2016/4/6
	 */
	public List<String> findItems() {
		List<String> list = new ArrayList<String>();
		list.add("產能模");
		list.add("產能雙");
		list.add("生產天數");
		list.add("用水量");
		list.add("用水金額");
		list.add("用量單耗");
		list.add("費用單耗");
		list.add("用量單耗排名");
		list.add("用電量(度)");
		list.add("用電費用");
		list.add("用量單耗");
		list.add("費用單耗");
		list.add("用量單耗排名");
		list.add("蒸汽用量");
		list.add("蒸汽費用");
		list.add("用量單耗");
		list.add("費用單耗");
		list.add("用量單耗排名");
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
		list.add("費用小計單耗");
		list.add("金額單耗排名");
		list.add("成品庫存");
		list.add("天數");
		list.add("天數排名");
		list.add("原料庫存量");
		list.add("原料庫存金額");
		list.add("原料庫存天數");
		list.add("天數排名");
		list.add("呆滯料庫存");
		list.add("呆滯料庫存金額(USD)");
		list.add("庫存量排名");
		list.add("防霜劑用量");
		list.add("防霜劑金額");
		list.add("防霜劑用量單耗");
		list.add("防霜劑金額單耗");
		list.add("用量單耗排名");
		list.add("色料用量");
		list.add("色料金額");
		list.add("色料用量單耗");
		list.add("色料金額單耗");
		list.add("用量單耗排名");
		list.add("藥品用量");
		list.add("藥品金額");
		list.add("藥品用量單耗");
		list.add("藥品金額單耗");
		list.add("用量單耗排名");
		list.add("防粘劑用量");
		list.add("防粘劑金額");
		list.add("防粘劑用量單耗");
		list.add("防粘劑金額單耗");
		list.add("用量單耗排名");
		list.add("油漆溶劑用量");
		list.add("油漆溶劑金額");
		list.add("油漆溶劑用量單耗");
		list.add("油漆溶劑金額單耗");
		list.add("用量單耗排名");
		list.add("離型劑用量");
		list.add("離型劑金額");
		list.add("離型劑用量單耗");
		list.add("離型劑金額單耗");
		list.add("用量單耗排名");
		list.add("直接工資");
		list.add("直工工資單耗");
		list.add("直工工資單耗排名");
		list.add("間接工資");
		list.add("間接工資單耗");
		list.add("間接工資單耗排名");
		list.add("加班費金額");
		list.add("加班費單耗");
		list.add("加班費單耗排名");
		list.add("獎金金額");
		list.add("獎金單耗");
		list.add("獎金單耗排名");
		list.add("其加金額");
		list.add("其加單耗");
		list.add("其加單耗排名");
		list.add("模具修理費");
		list.add("差旅費");
		list.add("交際費用");
		list.add("包裝費用");
		list.add("其它費用小計");
		list.add("費用小計單耗");
		list.add("費用單耗排名");
		list.add("廢品倉報廢重量");
		list.add("廢品倉報廢金額");
		list.add("重量單耗");
		list.add("重量單耗排名");
		
		return list;
	}
	
	/**
	 * 所有單位
	 * @Title: findUnits
	 * @Description: TODO
	 * @param @return
	 * @return List<String>
	 * @throws
	 * @author web
	 * @date 2016/4/6
	 */
	public List<String> findUnits() {
		List<String> list = new ArrayList<String>();
		list.add("模");
		list.add("雙");
		list.add("天");
		list.add("噸");
		list.add("USD");
		list.add("噸/模");
		list.add("USD/模");
		list.add("排名原則");
		list.add("度");
		list.add("USD");
		list.add("度/模");
		list.add("USD/模");
		list.add("排名原則");
		list.add("噸");
		list.add("USD");
		list.add("噸/模");
		list.add("USD/模");
		list.add("排名原則");
		list.add("USD");
		list.add("USD");
		list.add("USD");
		list.add("USD");
		list.add("USD");
		list.add("USD");
		list.add("USD");
		list.add("USD");
		list.add("USD");
		list.add("USD");
		list.add("USD");
		list.add("USD");
		list.add("USD/模");
		list.add("排名原則");
		list.add("雙");
		list.add("天");
		list.add("排名原則");
		list.add("KG");
		list.add("USD");
		list.add("天");
		list.add("排名原則");
		list.add("KG");
		list.add("USD");
		list.add("排名原則");
		list.add("KG");
		list.add("USD");
		list.add("KG/模");
		list.add("USD/模");
		list.add("排名原則");
		list.add("KG");
		list.add("USD");
		list.add("KG/模");
		list.add("USD/模");
		list.add("排名原則");
		list.add("KG");
		list.add("USD");
		list.add("KG/模");
		list.add("USD/模");
		list.add("排名原則");
		list.add("KG");
		list.add("USD");
		list.add("KG/模");
		list.add("USD/模");
		list.add("排名原則");
		list.add("KG");
		list.add("USD");
		list.add("KG/模");
		list.add("USD/模");
		list.add("排名原則");
		list.add("KG");
		list.add("USD");
		list.add("KG/模");
		list.add("USD/模");
		list.add("排名原則");
		list.add("USD");
		list.add("USD/模");
		list.add("排名原則");
		list.add("USD");
		list.add("USD/模");
		list.add("排名原則");
		list.add("USD");
		list.add("USD/模");
		list.add("排名原則");
		list.add("USD");
		list.add("USD/模");
		list.add("排名原則");
		list.add("USD");
		list.add("USD/模");
		list.add("排名原則");
		list.add("USD");
		list.add("USD");
		list.add("USD");
		list.add("USD");
		list.add("USD");
		list.add("USD/模");
		list.add("排名原則");
		list.add("KG");
		list.add("USD");
		list.add("KG/模");
		list.add("排名原則");
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
	
	

}