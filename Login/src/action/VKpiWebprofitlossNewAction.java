/**
 * 
 */
package action;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;
import services.IVKpiWebprofitlossServices;
import services.IWebFactServices;
import services.IWeballobjBServices;
import util.GlobalMethod;
import com.opensymphony.xwork2.ActionSupport;
import entity.VKpiWebprofitloss;
import entity.VKpiWebprofitlossId;
import entity.VKpiWebprofitlossItems;
import entity.WebFact;
import entity.WebFactId;
import entity.WeballobjB;
import entity.WeballobjBId;


public class VKpiWebprofitlossNewAction extends ActionSupport implements ServletResponseAware{
	private String factNo;
	private String yymm;
	private String yymm2;
	private String factname;
	private List<String>list_factcode;
	private List<String>list_factno;
	private final static String RISERATE="漲幅比率";
	private final static int NUM=31;//print_tw  多少箇項目（29+1）
	private final static int NUM2=2;//集合移除相同元素后的箇數
	private final static String STR_LONG="-999999";
	private final static String STR_DB="-999999.0";
	private final static String STR_BIG="-99999900.00%";
	private final static Double DB1=-999999.0;
	private IVKpiWebprofitlossServices vkpiprofitser;
	private IWeballobjBServices weballobjbser;
	private IWebFactServices webFactSer;
	private javax.servlet.http.HttpServletResponse response;
	
	
	
	
	public void setWeballobjbser(IWeballobjBServices weballobjbser) {
		this.weballobjbser = weballobjbser;
	}
	public String getFactNo(){
		return factNo;
	}
	public void setFactNo(String factNo){
		this.factNo=factNo;
	}
	public String getYymm(){
		return yymm;
	}
	public void setYymm(String yymm){
		this.yymm=yymm;
	}
	public String getYymm2(){
		return yymm2;
	}
	public void setYymm2(String yymm2){
		this.yymm2=yymm2;
	}
	
	
	public String getFactname(){
		return factname;
	}
	public void setFactname(String factname){
		this.factname=factname;
	}
	
	public List<String> getList_factcode(){
		return list_factcode;
	}
	public void setList_factcode(List<String> list_factcode){
		this.list_factcode=list_factcode;
	}
	public List<String> getList_factno(){
		return list_factno;
	}
	public void setList_factno(List<String> list_factno){
		this.list_factno=list_factno;
	}
	public void setVkpiprofitser(IVKpiWebprofitlossServices vkpiprofitser){
		this.vkpiprofitser=vkpiprofitser;
	}
	public void setWebFactSer(IWebFactServices webFactSer){
		this.webFactSer=webFactSer;
	}
	public void setServletResponse(HttpServletResponse response){
		// TODO Auto-generated method stub
		this.response=response;
	}
	
	public void print_month() throws ParseException, IOException{		
		XSSFWorkbook wb=new XSSFWorkbook();
		XSSFSheet sheet=wb.createSheet(factNo);
		Map<String,Object>map_style=GlobalMethod.findStyles2007(wb);				
		List<String>list_months=GlobalMethod.findMonths(yymm,yymm2);
		List<Object[]>list_factcodes=new ArrayList<Object[]>();
		list_factcodes=webFactSer.findByFactNo_showA_order(factNo);
		if(list_factcodes.size()==0){
			list_factcodes=webFactSer.findByFactNo_order(factNo);//如果有效的factcode數量爲0, 則查詢所有的factcode,否則,初始化表格時,報空指針錯誤
		}
		List<WeballobjB>list_vkpipros=weballobjbser.findAllobj(factNo, yymm, yymm2);
		List<String>list_items=this.findItems();
		Map<String,Object>map=new LinkedHashMap<String,Object>();
		for(Object[] factcode:list_factcodes){
			List<WeballobjB>list=new LinkedList<WeballobjB>();
			for(String month:list_months){
				list.add(new WeballobjB(new WeballobjBId(new WebFact(new WebFactId(factNo,(String)factcode[0])),month)));
			}
			for(int i=0;i<list.size();i++){
				WeballobjB loss=list.get(i);
				for(WeballobjB obj:list_vkpipros){
					if(loss.getId().getFact().getId().getFactNo().equals(obj.getId().getFact().getId().getFactNo())&&loss.getId().getFact().getId().getFactArea().equals(obj.getId().getFact().getId().getFactArea())&&
							loss.getId().getYymm().equals(obj.getId().getYymm())){
						list.remove(i);
						list.add(i,obj);
					}
				}
			}
			map.put((String)factcode[0],list);						
		}
				
		this.init_more(sheet,map,map_style,list_items,1);				
		
		ServletOutputStream os=response.getOutputStream();
		//response.setContentType("application/vnd.ms-excel");
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		String fileName="report.xlsx";
		int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");
		if(msie>0){
			fileName=java.net.URLEncoder.encode(fileName,"utf-8");
		}else{
			fileName=new String(fileName.getBytes("utf-8"),"iso8859-1");
		}
		response.setHeader("Content-disposition","attachment;filename="+fileName);
		wb.write(os);
		os.close();
		
	}
	
	public void print_tw() throws IOException{
		XSSFWorkbook wb=new XSSFWorkbook();
		XSSFSheet sheet=wb.createSheet("匯總");
		XSSFSheet sheet2=wb.createSheet();
		Map<String,Object>map_style=GlobalMethod.findStyles2007(wb);		
		XSSFCellStyle cs=(XSSFCellStyle)map_style.get("cs");
		XSSFCellStyle cs_red_bg=(XSSFCellStyle)map_style.get("cs_red_bg");
		XSSFCellStyle cs_lblue_bg=(XSSFCellStyle)map_style.get("cs_lblue_bg");
		XSSFCellStyle cs_itatic=(XSSFCellStyle)map_style.get("cs_itatic");
		List<WeballobjB>list_source=weballobjbser.findWeballobjB(list_factcode, yymm);
		Map<String,Object>map=new LinkedHashMap<String,Object>();
		List<String>list_items=this.findItems();
		for(String factcode:list_factcode){
			List<WeballobjB>list_obj=new LinkedList<WeballobjB>();
			for(String factno:list_factno){
				if(factcode.equals(factno.split("_")[0])){
					list_obj.add(new WeballobjB(new WeballobjBId(new WebFact(new WebFactId(factno,factcode)),yymm)));
				}
			}
			for(int i=0;i<list_obj.size();i++){
				WeballobjB obj=list_obj.get(i);
				for(WeballobjB loss:list_source){
					if(obj.getId().getFact().getId().getFactArea().equals(loss.getId().getFact().getId().getFactArea())&&obj.getId().getFact().getId().getFactNo().split("_")[1].equals(loss.getId().getFact().getId().getFactNo())){
						list_obj.remove(i);
						list_obj.add(i,loss);
					}
				}
			}
			map.put(factcode,list_obj);
		}
		this.init_more(sheet,map,map_style,list_items,0);					
		/******************sheet2********************/
		
		//this.init_more(sheet2,map,map_style,0);
		this.init(sheet2,map,0);
		
		
		/*******************************按factcode 標出第1名與最后1名*************************************/
		Map<String,Object>map_result=this.packageToListDouble_map(map);
		int col_index=0;
		for(String factcode:map_result.keySet()){
			List<List<List<Double>>>list_all=(List<List<List<Double>>>)map_result.get(factcode);
			List<List<Double>>list_doubles=list_all.get(0);
			List<List<Double>>list_doubles_sort=list_all.get(1);
			int length=list_doubles.get(0).size()-2;//每箇factcode所含有的factno箇數
			List<XSSFCellStyle>list_styles=new ArrayList<XSSFCellStyle>();
			for(int i=0;i<list_doubles.size();i++){
				List<Double>list_double=list_doubles.get(i);
				List<Double>list_double_sort=list_doubles_sort.get(i);
				if(i==0){
					/*for(int j=0;j<list_double.size()-2;j++){
						sheet2.getRow(2+i).getCell(3+j+col_index).setCellValue(list_double.get(j));
						sheet2.getRow(2+i).getCell(3+j+col_index).setCellStyle(cs_head);
					}*/
				}else{
					this.isRedOrGreen(i,list_styles,map_style,list_items);
					for(int j=0;j<list_double.size()-2;j++){
						//sheet2.getRow(2+i).getCell(3+j+col_index).setCellValue(list_double.get(j));
						if(list_double_sort.size()>=NUM2&&list_double.get(j)==list_double.get(list_double.size()-2)&&!list_double.get(j).equals(DB1)){
							//sheet2.getRow(2+i).getCell(3+j+col_index).setCellStyle(temp);
							sheet.getRow(2+i).getCell(3+j+col_index).setCellStyle(list_styles.get(0));
						}else if(list_double_sort.size()>=NUM2&&list_double.get(j)==list_double.get(list_double.size()-1)&&!list_double.get(j).equals(DB1)){
							//sheet2.getRow(2+i).getCell(3+j+col_index).setCellStyle(temp);
							sheet.getRow(2+i).getCell(3+j+col_index).setCellStyle(list_styles.get(1));
						}else{
							//sheet2.getRow(2+i).getCell(3+j+col_index).setCellStyle(cs);
							sheet.getRow(2+i).getCell(3+j+col_index).setCellStyle(cs);
						}								
					}
				}
			}
			col_index=col_index+length;
		}
		/*******************************按factcode 標出第1名與最后1名*************************************/
		
		/*List<List<Double>>list_doubles=this.packageToListDouble(map).get(0);
		List<List<Double>>list_doubles_sort=this.packageToListDouble(map).get(1);
		List<XSSFCellStyle>list_styles=new ArrayList<XSSFCellStyle>();
		for(int i=0;i<list_doubles.size();i++){			
			List<Double>list_double=list_doubles.get(i);
			List<Double>list_double_sort=list_doubles_sort.get(i);
			if(i==0){
				for(int j=0;j<list_double.size()-2;j++){
					sheet2.getRow(2+i).getCell(3+j).setCellValue(list_double.get(j));
					sheet2.getRow(2+i).getCell(3+j).setCellStyle(cs_head);
				}
			}else{
				this.isRedOrGreen(i,list_styles,cs_red_bg,cs_lblue_bg);
				for(int j=0;j<list_double.size()-2;j++){
					//sheet2.getRow(2+i).getCell(3+j).setCellValue(list_double.get(j));
					if(list_double_sort.size()>3&&list_double.get(j)<=list_double.get(list_double.size()-2)&&!list_double.get(j).equals(DB1)){
						//sheet2.getRow(2+i).getCell(3+j).setCellStyle(temp);
						sheet.getRow(2+i).getCell(3+j).setCellStyle(list_styles.get(0));
					}else if(list_double_sort.size()>3&&list_double.get(j)>=list_double.get(list_double.size()-1)&&!list_double.get(j).equals(DB1)){
						//sheet2.getRow(2+i).getCell(3+j).setCellStyle(temp);
						sheet.getRow(2+i).getCell(3+j).setCellStyle(list_styles.get(1));
					}else{
						//sheet2.getRow(2+i).getCell(3+j).setCellStyle(cs);
						sheet.getRow(2+i).getCell(3+j).setCellStyle(cs);
					}								
				}
			}
			
		}*/
		
		
		sheet.createRow(NUM+5).createCell(1).setCellStyle(cs_red_bg);
		sheet.createRow(NUM+6).createCell(1).setCellStyle(cs_lblue_bg);
		
		sheet.getRow(NUM+5).createCell(2).setCellValue(":最前1名");
		sheet.getRow(NUM+6).createCell(2).setCellValue(":最后1名");
		sheet.getRow(NUM+5).getCell(2).setCellStyle(cs_itatic);
		sheet.getRow(NUM+6).getCell(2).setCellStyle(cs_itatic);
		/******************sheet2********************/
		
		
		ServletOutputStream os=response.getOutputStream();
		//response.setContentType("application/vnd.ms-excel");
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		String fileName="report_total.xlsx";
		int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");
		if(msie>0){
			fileName=java.net.URLEncoder.encode(fileName,"utf-8");
		}else{
			fileName=new String(fileName.getBytes("utf-8"),"iso8859-1");
		}
		response.setHeader("Content-disposition","attachment;filename="+fileName);
		wb.write(os);
		os.close();
	}
	
		
	/**
	 * 
	 * @Title: init_more
	 * @Description: TODO
	 * @param @param sheet
	 * @param @param map
	 * @param @param map_style
	 * @param @param mk   1:print_month報表     0:print_tw報表
	 * @param @throws IOException
	 * @return void
	 * @throws
	 * @author web
	 * @date 2016/9/12
	 */
	public void init_more(XSSFSheet sheet,Map<String,Object>map,Map<String,Object>map_style,List<String>list_items,int mk) throws IOException{	
		
		this.init(sheet,map,mk);
		
		//樣式
		XSSFCellStyle cs_title=(XSSFCellStyle)map_style.get("cs_title");
		XSSFCellStyle cs_head=(XSSFCellStyle)map_style.get("cs_head");
		XSSFCellStyle cs_head2=(XSSFCellStyle)map_style.get("cs_head2");
		XSSFCellStyle cs=(XSSFCellStyle)map_style.get("cs");
		//標題
		CellRangeAddress cra_title=new CellRangeAddress(0,(short)0,0,(short)5);
		sheet.addMergedRegion(cra_title);
		if(factname!=null){
			sheet.getRow(0).getCell(0).setCellValue("重點指標匯總_"+factname);
		}else{
			sheet.getRow(0).getCell(0).setCellValue("各廠重點指標匯總_"+yymm);
		}
		
		for(int i=0;i<4;i++){
			sheet.getRow(0).getCell(i).setCellStyle(cs_title);
		}
		
		CellRangeAddress cra=new CellRangeAddress(1,(short)1,0,(short)2);
		sheet.addMergedRegion(cra);
		sheet.getRow(1).getCell(0).setCellValue("廠別狀態");
		for(int i=0;i<3;i++){
			sheet.getRow(1).getCell(i).setCellStyle(cs_head2);
		}
		
		List<String>list_head=new ArrayList<String>();
		list_head.add("項次");
		list_head.add("項目");
		list_head.add("單位");						
		for(int i=0;i<list_head.size();i++){
			sheet.getRow(2).getCell(i).setCellValue(list_head.get(i));
			sheet.getRow(2).getCell(i).setCellStyle(cs_head);
		}
		
		//List<String>list_items=this.findItems();
		for(int i=0;i<list_items.size();i++){
			sheet.getRow(3+i).getCell(0).setCellValue(i+1);
			sheet.getRow(3+i).getCell(1).setCellValue(list_items.get(i).split("__")[0]);//項目
			sheet.getRow(3+i).getCell(2).setCellValue(list_items.get(i).split("__")[1]);//單位
			for(int j=0;j<3;j++){
				sheet.getRow(3+i).getCell(j).setCellStyle(cs);
			}
		}
		
		int temp=0;
		for(String factcode:map.keySet()){
			List<WeballobjB>list_obj=(List<WeballobjB>)map.get(factcode);	
			int length=list_obj.size();
			if(length>1&&mk==1){
				length++;
			}
			sheet.getRow(1).getCell(3+temp).setCellValue(factcode);
			CellRangeAddress cra1=new CellRangeAddress(1,(short)1,3+temp,(short)2+temp+length);
			sheet.addMergedRegion(cra1);
			for(int i=0;i<length;i++){
				sheet.getRow(1).getCell(3+temp+i).setCellStyle(cs_head2);
			}
			
			List<List<String>>list_pack=this.packageTostring(list_obj,list_items,mk);			
			for(int a=0;a<list_pack.size();a++){
				List<String>list=list_pack.get(a);
				for(int b=0;b<list.size();b++){
					sheet.getRow(2+b).getCell(3+a+temp).setCellValue(this.isMyNull(list.get(b)));
					if(b==0){
						sheet.getRow(2+b).getCell(3+a+temp).setCellStyle(cs_head);
					}else if(mk==1){
						sheet.getRow(2+b).getCell(3+a+temp).setCellStyle(cs);
					}										
				}
			}
			temp=temp+length;
		}				
	}
	
	public void init(XSSFSheet sheet,Map<String,Object>map,int mk){
		sheet.setColumnWidth(1,4500);		
		for(int i=0;i<NUM+2;i++){
			XSSFRow row=sheet.createRow(i);
			int index=3;
			for(String factcode:map.keySet()){
				if(index==3){
					for(int j=0;j<index;j++){
						row.createCell(j);
					}
				}
				List<WeballobjB>list_obj=(List<WeballobjB>)map.get(factcode);
				for(int j=0;j<list_obj.size()+mk;j++){					
					row.createCell(index);
					if(i==0){
						sheet.setColumnWidth(index,3000);
					}
					index++;
					
				}
			}			
		}
	}
	
	
	/**
	 * 
	 * @Title: findItems
	 * @Description: __0:越大真好（降序）    __1:越少真好（升序）        __2:不分大小
	 * @param @return
	 * @return List<String>
	 * @throws
	 * @author web
	 * @date 2016/9/13
	 */
	public List<String>findItems(){
		List<String>list=new ArrayList<String>();						
		/*list.add("機臺孔位數__孔__2");
		list.add("機台利用率__%__0");
		list.add("生產數__模__0");
		list.add("請款雙數__雙__0");
		list.add("實際回轉數__回__0");
		list.add("直工__人__1");
		list.add("間工__人__1");
		list.add("全廠總人數__人__1");
		list.add("直間比__:__0");
		list.add("直工人均產能__模__0");
		list.add("全廠人均產能__模__0");
		list.add("時產能__雙__0");
		list.add("時迴轉__模/H__0");
		list.add("加班費__USD__1");
		list.add("成本率__%__1");
		list.add("利潤率__%__0");
		list.add("總損耗__%__1");
		list.add("平均邊料重__G/雙__1");
		list.add("邊料率__%__1");
		list.add("不良重量__KG__1");
		list.add("不良率__%__1");
		list.add("退貨率__%__1");
		list.add("成倉庫存__雙__1");
		list.add("用水單耗__USD/模__1");
		list.add("用電單耗__USD/模__1");
		list.add("蒸汽單耗__USD/模__1");
		list.add("蒸汽單耗__KG/模__1");
		list.add("色料藥品單耗__G/雙__1");
		list.add("色料藥品單耗__USD/雙__1");*/

		List<VKpiWebprofitlossItems>list_items=vkpiprofitser.findItems();
		for(VKpiWebprofitlossItems item:list_items){
			list.add(item.getItemName());
		}
		return list;
		
	}
	
	/**
	 * 
	 * @Title: packageTostring
	 * @Description: TODO
	 * @param @param list_obj
	 * @param @param mk  1:工廠報表    0:臺灣報表
	 * @param @return
	 * @return List<List<String>>
	 * @throws
	 * @author web
	 * @date 2016/9/8
	 */
	public List<List<String>> packageTostring(List<WeballobjB>list_obj,List<String>list_items,int mk){
		List<List<String>>result=new ArrayList<List<String>>();
		DecimalFormat frm=new DecimalFormat("0.00%");
		for(int i=0;i<list_obj.size();i++){
			WeballobjB obj=list_obj.get(i);
			List<String>list=new ArrayList<String>();
			if(mk==0){
				if(obj.getId().getFact().getFactSname()!=null){
					list.add(obj.getId().getFact().getFactSname());
				}else{
					list.add(obj.getId().getFact().getId().getFactNo().split("_")[2]);
				}
				
			}else{
				list.add(obj.getId().getYymm());
			}
			List<String>list_str=this.isPercents(obj,list_items);
			for(String str:list_str){
				list.add(str);
			}			
			result.add(list);
			if(list_obj.size()>1&&i==list_obj.size()-1&&mk==1){//漲幅比率
				List<String>list2=new ArrayList<String>();
				WeballobjB obj1=list_obj.get(list_obj.size()-2);
				WeballobjB obj2=list_obj.get(list_obj.size()-1);
				list2.add(RISERATE);
				list2.add(isMyNull(frm,obj2.getObjA1(),obj1.getObjA1()));
				list2.add(isMyNull(frm,obj2.getObjA2(),obj1.getObjA2()));
				list2.add(isMyNull(frm,obj2.getObjA3(),obj1.getObjA3()));
				list2.add(isMyNull(frm,obj2.getObjA4(),obj1.getObjA4()));
				list2.add(isMyNull(frm,obj2.getObjA5(),obj1.getObjA5()));
				list2.add(isMyNull(frm,obj2.getObjA6(),obj1.getObjA6()));
				list2.add(isMyNull(frm,obj2.getObjA7(),obj1.getObjA7()));
				list2.add(isMyNull(frm,obj2.getObjA8(),obj1.getObjA8()));
				list2.add(isMyNull(frm,obj2.getObjA9(),obj1.getObjA9()));
				list2.add(isMyNull(frm,obj2.getObjA10(),obj1.getObjA10()));
				list2.add(isMyNull(frm,obj2.getObjA11(),obj1.getObjA11()));
				list2.add(isMyNull(frm,obj2.getObjA12(),obj1.getObjA12()));
				list2.add(isMyNull(frm,obj2.getObjA13(),obj1.getObjA13()));
				list2.add(isMyNull(frm,obj2.getObjA14(),obj1.getObjA14()));
				list2.add(isMyNull(frm,obj2.getObjA15(),obj1.getObjA15()));
				list2.add(isMyNull(frm,obj2.getObjA16(),obj1.getObjA16()));
				list2.add(isMyNull(frm,obj2.getObjA17(),obj1.getObjA17()));
				list2.add(isMyNull(frm,obj2.getObjA18(),obj1.getObjA18()));
				list2.add(isMyNull(frm,obj2.getObjA19(),obj1.getObjA19()));
				list2.add(isMyNull(frm,obj2.getObjA20(),obj1.getObjA20()));
				list2.add(isMyNull(frm,obj2.getObjA21(),obj1.getObjA21()));
				list2.add(isMyNull(frm,obj2.getObjA22(),obj1.getObjA22()));
				list2.add(isMyNull(frm,obj2.getObjA23(),obj1.getObjA23()));
				list2.add(isMyNull(frm,obj2.getObjA24(),obj1.getObjA24()));
				list2.add(isMyNull(frm,obj2.getObjA25(),obj1.getObjA25()));
				list2.add(isMyNull(frm,obj2.getObjA26(),obj1.getObjA26()));
				list2.add(isMyNull(frm,obj2.getObjA27(),obj1.getObjA27()));
				list2.add(isMyNull(frm,obj2.getObjA28(),obj1.getObjA28()));
				list2.add(isMyNull(frm,obj2.getObjA29(),obj1.getObjA29()));
				list2.add(isMyNull(frm,obj2.getObjA30(),obj1.getObjA30()));
				result.add(list2);
			}						
		}
		return result;
		
	}
	
			
	
	
	
	public Map<String,Object> packageToListDouble_map(Map<String,Object>map){
		Map<String,Object>map_result=new LinkedHashMap<String,Object>();
		
		
			for(String factcode:map.keySet()){
				List<List<List<Double>>>list_all=new ArrayList<List<List<Double>>>();
				List<List<Double>>list_result=new ArrayList<List<Double>>();
				for(int i=0;i<NUM;i++){
					List<Double>list=new ArrayList<Double>();
					list_result.add(list);
				}
				
				List<WeballobjB>list_obj=(List<WeballobjB>)map.get(factcode);
				for(WeballobjB obj:list_obj){
					list_result.get(0).add(-1.0);										
					list_result.get(1).add(obj.getObjA1());
					list_result.get(2).add(obj.getObjA2());
					list_result.get(3).add(obj.getObjA3());
					list_result.get(4).add(obj.getObjA4());
					list_result.get(5).add(obj.getObjA5());
					list_result.get(6).add(obj.getObjA6());
					list_result.get(7).add(obj.getObjA7());
					list_result.get(8).add(obj.getObjA8());
					list_result.get(9).add(obj.getObjA9());
					list_result.get(10).add(obj.getObjA10());
					list_result.get(11).add(obj.getObjA11());
					list_result.get(12).add(obj.getObjA12());
					list_result.get(13).add(obj.getObjA13());
					list_result.get(14).add(obj.getObjA14());
					list_result.get(15).add(obj.getObjA15());
					list_result.get(16).add(obj.getObjA16());
					list_result.get(17).add(obj.getObjA17());
					list_result.get(18).add(obj.getObjA18());
					list_result.get(19).add(obj.getObjA19());
					list_result.get(20).add(obj.getObjA20());
					list_result.get(21).add(obj.getObjA21());
					list_result.get(22).add(obj.getObjA22());
					list_result.get(23).add(obj.getObjA23());
					list_result.get(24).add(obj.getObjA24());
					list_result.get(25).add(obj.getObjA25());
					list_result.get(26).add(obj.getObjA26());
					list_result.get(27).add(obj.getObjA27());
					list_result.get(28).add(obj.getObjA28());
					list_result.get(29).add(obj.getObjA29());
					list_result.get(30).add(obj.getObjA30());										
				}
				
				
				List<List<Double>>list_result_sort=this.deepCloneList(list_result);//克隆集合
				for(int i=0;i<list_result_sort.size();i++){
					List<Double>list=list_result_sort.get(i);
					List<Double>list2=list_result.get(i);
					this.removeDB1(list);//去掉空的數據
					GlobalMethod.removeSameDouble(list);//集合排序,移除相同元素											
					if(list.size()>=NUM2){
						list2.add(list.get(0));//第1名，最后1名放到集合中，用于比較
						list2.add(list.get(list.size()-1));
					}
					if(list.size()<NUM2){
						list2.add(DB1);list2.add(DB1);
					}
					
				}			
			list_all.add(list_result);
			list_all.add(list_result_sort);//list_result_sort:去掉重複的已排序的集合,list_result與list_result_sort長度一樣
			map_result.put(factcode,list_all);	
			}			
			
		return map_result;
	}
	
	
	
	/**
	 * 判斷是否無數據
	 * @return
	 */
	public String isMyNull(String str){
		//if(str.equals(STR_BIG)||str.equals(STR_LONG)||str.equals(STR_DB)){
		if(str==null){					
			str="無";
		}
		return str;
	}
	
	
	/**
	 * 判斷漲福是否無數據
	 * @param frm
	 * @param d1
	 * @param d2
	 * @param d3
	 * @return
	 */
	public String isMyNull(DecimalFormat frm,Double d1,Double d2){
		String result=null;
		//if(d1.equals(DB1)||d2.equals(DB1)||d3.equals(DB1)){
		if(d1==null||d2==null){
			result="無";
		}else{
			if(d1-d2<0){
				result=frm.format(-Math.abs(GlobalMethod.division(d1-d2, d2)));//d1-d2<0,取负数，表示负涨幅    Math.abc取絕對值
			}else{
				result=frm.format(Math.abs(GlobalMethod.division(d1-d2, d2)));//d1-d2>=0,取正数，表示正涨幅
			}			
		}
		return result;
	}
	
	/**
	 * 去除DB1
	 * @Title: removeDB1
	 * @Description: TODO
	 * @param @param list
	 * @return void
	 * @throws
	 * @author web
	 * @date 2016/9/12
	 */
	public void removeDB1(List<Double>list){
		for(int i=0;i<list.size();i++){
			//if(list.get(i).equals(DB1)){
			if(list.get(i)==null){
				list.remove(i);
			}
		}
	}
	
	/**
	 * 克隆集合
	 * @Title: deepCloneList
	 * @Description: TODO
	 * @param @param list
	 * @param @return
	 * @return List<List<Double>>
	 * @throws
	 * @author web
	 * @date 2016/9/12
	 */
	public List<List<Double>> deepCloneList(List<List<Double>> list){
		List<List<Double>>list_result=new ArrayList<List<Double>>();
		for(List<Double> list_a:list){
			List<Double>list_result_a=new ArrayList<Double>();
			for(Double db:list_a){
				list_result_a.add(db);
			}
			list_result.add(list_result_a);
		}
		return list_result;
		
	}
	
	/**
	 * 判斷正序  反序
	 * @Title: isRedOrGreen
	 * @Description: 
	 * @param @param row
	 * @param @param cs_red
	 * @param @param cs_green
	 * @param @return
	 * @return XSSFCellStyle
	 * @throws
	 * @author web
	 * @date 2016/9/13
	 */
	public List<XSSFCellStyle> isRedOrGreen(int row,List<XSSFCellStyle>list_styles,Map<String,Object>map_style,List<String>list_items){
		list_styles.clear();
		XSSFCellStyle cs_red_bg=(XSSFCellStyle)map_style.get("cs_red_bg");
		XSSFCellStyle cs_lblue_bg=(XSSFCellStyle)map_style.get("cs_lblue_bg");
		XSSFCellStyle cs=(XSSFCellStyle)map_style.get("cs");
		List<String>list=new ArrayList<String>();
		list.add("factno__factno__factno");//1
		//List<String>list_items=this.findItems();
		for(String item:list_items){
			list.add(item);
		}				
		if(list.get(row).split("__")[2].equals("0")){//0:越大越好（降序）    1:越少越好（升序）   
			list_styles.add(cs_lblue_bg);
			list_styles.add(cs_red_bg);
		}else if(list.get(row).split("__")[2].equals("1")){					
			list_styles.add(cs_red_bg);
			list_styles.add(cs_lblue_bg);
		}else{
			list_styles.add(cs);
			list_styles.add(cs);
		}
		
		return list_styles;
	}
	
	/**
	 * 
	 * @Title: isPercents
	 * @Description: 判斷哪些數據用 % 號
	 * @param @param obj
	 * @param @return
	 * @return List<String>
	 * @throws
	 * @author web
	 * @date 2016/9/20
	 */
	public List<String> isPercents(WeballobjB obj,List<String> list_items){
		List<Object>list=new ArrayList<Object>();
		//List<String>list_items=this.findItems();
		List<String>list_result=new ArrayList<String>();
		DecimalFormat frm=new DecimalFormat("0.00%");
		list.add(obj.getObjA1());
		list.add(obj.getObjA2());
		list.add(obj.getObjA3());
		list.add(obj.getObjA4());
		list.add(obj.getObjA5());
		list.add(obj.getObjA6());
		list.add(obj.getObjA7());
		list.add(obj.getObjA8());
		list.add(obj.getObjA9());
		list.add(obj.getObjA10());
		list.add(obj.getObjA11());
		list.add(obj.getObjA12());
		list.add(obj.getObjA13());
		list.add(obj.getObjA14());
		list.add(obj.getObjA15());
		list.add(obj.getObjA16());
		list.add(obj.getObjA17());
		list.add(obj.getObjA18());
		list.add(obj.getObjA19());
		list.add(obj.getObjA20());
		list.add(obj.getObjA21());
		list.add(obj.getObjA22());
		list.add(obj.getObjA23());
		list.add(obj.getObjA24());
		list.add(obj.getObjA25());
		list.add(obj.getObjA26());
		list.add(obj.getObjA27());
		list.add(obj.getObjA28());
		list.add(obj.getObjA29());
		list.add(obj.getObjA30());

		for(int i=0;i<list.size();i++){
			if(list.get(i)!=null){
				if(list_items.get(i).split("__")[1].equals("%")){
					list_result.add(frm.format(list.get(i)));
				}else{
					list_result.add(list.get(i).toString());							
				}
			}else{
				list_result.add("無");
			}						
		}			
		return list_result;
	}
	
			
	
	/**************************************************2003版本分界線*****************************************************/
	/**************************************************2003版本分界線*****************************************************/
	public void print_month_2003() throws ParseException, IOException{		
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet(factNo);
		Map<String,Object>map_style=GlobalMethod.findStyles(wb);				
		List<String>list_months=GlobalMethod.findMonths(yymm,yymm2);
		List<Object[]>list_factcodes=webFactSer.findByFactNo_showA_order(factNo);
		List<WeballobjB>list_vkpipros=weballobjbser.findAllobj(factNo, yymm, yymm2);
		List<String>list_items=this.findItems();
		Map<String,Object>map=new LinkedHashMap<String,Object>();
		for(Object[] factcode:list_factcodes){
			List<WeballobjB>list=new LinkedList<WeballobjB>();
			for(String month:list_months){
				list.add(new WeballobjB(new WeballobjBId(new WebFact(new WebFactId(factNo,(String)factcode[0])),month)));
			}
			for(int i=0;i<list.size();i++){
				WeballobjB loss=list.get(i);
				for(WeballobjB obj:list_vkpipros){
					if(loss.getId().getFact().getId().getFactNo().equals(obj.getId().getFact().getId().getFactNo())&&loss.getId().getFact().getId().getFactArea().equals(obj.getId().getFact().getId().getFactArea())&&
							loss.getId().getYymm().equals(obj.getId().getYymm())){
						list.remove(i);
						list.add(i,obj);
					}
				}
			}
			map.put((String)factcode[0],list);						
		}
				
		this.init_more_2003(sheet,map,map_style,list_items,1);				
		
		ServletOutputStream os=response.getOutputStream();
		response.setContentType("application/vnd.ms-excel");
		//response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		String fileName="report.xls";
		int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");
		if(msie>0){
			fileName=java.net.URLEncoder.encode(fileName,"utf-8");
		}else{
			fileName=new String(fileName.getBytes("utf-8"),"iso8859-1");
		}
		response.setHeader("Content-disposition","attachment;filename="+fileName);
		wb.write(os);
		os.close();
		
	}
	
	public void print_tw_2003() throws IOException{
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet("匯總");
		HSSFSheet sheet2=wb.createSheet();
		Map<String,Object>map_style=GlobalMethod.findStyles(wb);		
		HSSFCellStyle cs=(HSSFCellStyle)map_style.get("cs");
		HSSFCellStyle cs_red_bg=(HSSFCellStyle)map_style.get("cs_red_bg");
		HSSFCellStyle cs_lblue_bg=(HSSFCellStyle)map_style.get("cs_lblue_bg");
		HSSFCellStyle cs_itatic=(HSSFCellStyle)map_style.get("cs_itatic");
		List<WeballobjB>list_source=weballobjbser.findWeballobjB(list_factcode, yymm);
		Map<String,Object>map=new LinkedHashMap<String,Object>();
		List<String>list_items=this.findItems();
		for(String factcode:list_factcode){
			List<WeballobjB>list_obj=new LinkedList<WeballobjB>();
			for(String factno:list_factno){
				if(factcode.equals(factno.split("_")[0])){
					list_obj.add(new WeballobjB(new WeballobjBId(new WebFact(new WebFactId(factno,factcode)),yymm)));
				}
			}
			for(int i=0;i<list_obj.size();i++){
				WeballobjB obj=list_obj.get(i);
				for(WeballobjB loss:list_source){
					if(obj.getId().getFact().getId().getFactArea().equals(loss.getId().getFact().getId().getFactArea())&&obj.getId().getFact().getId().getFactNo().split("_")[1].equals(loss.getId().getFact().getId().getFactNo())){
						list_obj.remove(i);
						list_obj.add(i,loss);
					}
				}
			}
			map.put(factcode,list_obj);
		}
		this.init_more_2003(sheet,map,map_style,list_items,0);					
		/******************sheet2********************/
		
		//this.init_more(sheet2,map,map_style,0);
		this.init_2003(sheet2,map,0);
		
		
		/*******************************按factcode 標出第1名與最后1名*************************************/
		Map<String,Object>map_result=this.packageToListDouble_map(map);
		int col_index=0;
		for(String factcode:map_result.keySet()){
			List<List<List<Double>>>list_all=(List<List<List<Double>>>)map_result.get(factcode);
			List<List<Double>>list_doubles=list_all.get(0);
			List<List<Double>>list_doubles_sort=list_all.get(1);
			int length=list_doubles.get(0).size()-2;//每箇factcode所含有的factno箇數
			List<HSSFCellStyle>list_styles=new ArrayList<HSSFCellStyle>();
			for(int i=0;i<list_doubles.size();i++){
				List<Double>list_double=list_doubles.get(i);
				List<Double>list_double_sort=list_doubles_sort.get(i);
				if(i==0){
					/*for(int j=0;j<list_double.size()-2;j++){
						sheet2.getRow(2+i).getCell(3+j+col_index).setCellValue(list_double.get(j));
						sheet2.getRow(2+i).getCell(3+j+col_index).setCellStyle(cs_head);
					}*/
				}else{
					this.isRedOrGreen_2003(i,list_styles,map_style,list_items);
					for(int j=0;j<list_double.size()-2;j++){
						//sheet2.getRow(2+i).getCell(3+j+col_index).setCellValue(list_double.get(j));
						if(list_double_sort.size()>=NUM2&&list_double.get(j)==list_double.get(list_double.size()-2)&&!list_double.get(j).equals(DB1)){
							//sheet2.getRow(2+i).getCell(3+j+col_index).setCellStyle(temp);
							sheet.getRow(2+i).getCell(3+j+col_index).setCellStyle(list_styles.get(0));
						}else if(list_double_sort.size()>=NUM2&&list_double.get(j)==list_double.get(list_double.size()-1)&&!list_double.get(j).equals(DB1)){
							//sheet2.getRow(2+i).getCell(3+j+col_index).setCellStyle(temp);
							sheet.getRow(2+i).getCell(3+j+col_index).setCellStyle(list_styles.get(1));
						}else{
							//sheet2.getRow(2+i).getCell(3+j+col_index).setCellStyle(cs);
							sheet.getRow(2+i).getCell(3+j+col_index).setCellStyle(cs);
						}								
					}
				}
			}
			col_index=col_index+length;
		}
		/*******************************按factcode 標出第1名與最后1名*************************************/
								
		sheet.createRow(NUM+5).createCell(1).setCellStyle(cs_red_bg);
		sheet.createRow(NUM+6).createCell(1).setCellStyle(cs_lblue_bg);
		
		sheet.getRow(NUM+5).createCell(2).setCellValue(":最前1名");
		sheet.getRow(NUM+6).createCell(2).setCellValue(":最后1名");
		sheet.getRow(NUM+5).getCell(2).setCellStyle(cs_itatic);
		sheet.getRow(NUM+6).getCell(2).setCellStyle(cs_itatic);
		/******************sheet2********************/
		
		
		ServletOutputStream os=response.getOutputStream();
		response.setContentType("application/vnd.ms-excel");
		//response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		String fileName="report_total.xls";
		int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");
		if(msie>0){
			fileName=java.net.URLEncoder.encode(fileName,"utf-8");
		}else{
			fileName=new String(fileName.getBytes("utf-8"),"iso8859-1");
		}
		response.setHeader("Content-disposition","attachment;filename="+fileName);
		wb.write(os);
		os.close();
	}
	
		
	/**
	 * 
	 * @Title: init_more
	 * @Description: TODO
	 * @param @param sheet
	 * @param @param map
	 * @param @param map_style
	 * @param @param mk   1:print_month報表     0:print_tw報表
	 * @param @throws IOException
	 * @return void
	 * @throws
	 * @author web
	 * @date 2016/9/12
	 */
	public void init_more_2003(HSSFSheet sheet,Map<String,Object>map,Map<String,Object>map_style,List<String>list_items,int mk) throws IOException{	
		
		this.init_2003(sheet,map,mk);
		
		//樣式
		HSSFCellStyle cs_title=(HSSFCellStyle)map_style.get("cs_title");
		HSSFCellStyle cs_head=(HSSFCellStyle)map_style.get("cs_head");
		HSSFCellStyle cs_head2=(HSSFCellStyle)map_style.get("cs_head2");
		HSSFCellStyle cs=(HSSFCellStyle)map_style.get("cs");
		//標題
		CellRangeAddress cra_title=new CellRangeAddress(0,(short)0,0,(short)5);
		sheet.addMergedRegion(cra_title);
		if(factname!=null){
			sheet.getRow(0).getCell(0).setCellValue("重點指標匯總_"+factname);
		}else{
			sheet.getRow(0).getCell(0).setCellValue("各廠重點指標匯總_"+yymm);
		}
		
		for(int i=0;i<4;i++){
			sheet.getRow(0).getCell(i).setCellStyle(cs_title);
		}
		
		CellRangeAddress cra=new CellRangeAddress(1,(short)1,0,(short)2);
		sheet.addMergedRegion(cra);
		sheet.getRow(1).getCell(0).setCellValue("廠別狀態");
		for(int i=0;i<3;i++){
			sheet.getRow(1).getCell(i).setCellStyle(cs_head2);
		}
		
		List<String>list_head=new ArrayList<String>();
		list_head.add("項次");
		list_head.add("項目");
		list_head.add("單位");						
		for(int i=0;i<list_head.size();i++){
			sheet.getRow(2).getCell(i).setCellValue(list_head.get(i));
			sheet.getRow(2).getCell(i).setCellStyle(cs_head);
		}
		
		//List<String>list_items=this.findItems();
		for(int i=0;i<list_items.size();i++){
			sheet.getRow(3+i).getCell(0).setCellValue(i+1);
			sheet.getRow(3+i).getCell(1).setCellValue(list_items.get(i).split("__")[0]);//項目
			sheet.getRow(3+i).getCell(2).setCellValue(list_items.get(i).split("__")[1]);//單位
			for(int j=0;j<3;j++){
				sheet.getRow(3+i).getCell(j).setCellStyle(cs);
			}
		}
		
		int temp=0;
		for(String factcode:map.keySet()){
			List<WeballobjB>list_obj=(List<WeballobjB>)map.get(factcode);	
			int length=list_obj.size();
			if(length>1&&mk==1){
				length++;
			}
			sheet.getRow(1).getCell(3+temp).setCellValue(factcode);
			CellRangeAddress cra1=new CellRangeAddress(1,(short)1,3+temp,(short)2+temp+length);
			sheet.addMergedRegion(cra1);
			for(int i=0;i<length;i++){
				sheet.getRow(1).getCell(3+temp+i).setCellStyle(cs_head2);
			}
			
			List<List<String>>list_pack=this.packageTostring(list_obj,list_items,mk);			
			for(int a=0;a<list_pack.size();a++){
				List<String>list=list_pack.get(a);
				for(int b=0;b<list.size();b++){
					sheet.getRow(2+b).getCell(3+a+temp).setCellValue(this.isMyNull(list.get(b)));
					if(b==0){
						sheet.getRow(2+b).getCell(3+a+temp).setCellStyle(cs_head);
					}else if(mk==1){
						sheet.getRow(2+b).getCell(3+a+temp).setCellStyle(cs);
					}										
				}
			}
			temp=temp+length;
		}				
	}
	
	public void init_2003(HSSFSheet sheet,Map<String,Object>map,int mk){
		sheet.setColumnWidth(1,4500);		
		for(int i=0;i<NUM+2;i++){
			HSSFRow row=sheet.createRow(i);
			int index=3;
			for(String factcode:map.keySet()){
				if(index==3){
					for(int j=0;j<index;j++){
						row.createCell(j);
					}
				}
				List<WeballobjB>list_obj=(List<WeballobjB>)map.get(factcode);
				for(int j=0;j<list_obj.size()+mk;j++){					
					row.createCell(index);
					if(i==0){
						sheet.setColumnWidth(index,3000);
					}
					index++;
					
				}
			}			
		}
	}
	
	/**
	 * 判斷正序  反序
	 * @Title: isRedOrGreen
	 * @Description: 
	 * @param @param row
	 * @param @param cs_red
	 * @param @param cs_green
	 * @param @return
	 * @return XSSFCellStyle
	 * @throws
	 * @author web
	 * @date 2016/9/13
	 */
	public List<HSSFCellStyle> isRedOrGreen_2003(int row,List<HSSFCellStyle>list_styles,Map<String,Object>map_style,List<String>list_items){
		list_styles.clear();
		HSSFCellStyle cs_red_bg=(HSSFCellStyle)map_style.get("cs_red_bg");
		HSSFCellStyle cs_lblue_bg=(HSSFCellStyle)map_style.get("cs_lblue_bg");
		HSSFCellStyle cs=(HSSFCellStyle)map_style.get("cs");
		List<String>list=new ArrayList<String>();
		list.add("factno__factno__factno");//1
		//List<String>list_items=this.findItems();
		for(String item:list_items){
			list.add(item);
		}				
		if(list.get(row).split("__")[2].equals("0")){//0:越大真好（降序）    1:越少真好（升序）   
			list_styles.add(cs_lblue_bg);
			list_styles.add(cs_red_bg);
		}else if(list.get(row).split("__")[2].equals("1")){					
			list_styles.add(cs_red_bg);
			list_styles.add(cs_lblue_bg);
		}else{
			list_styles.add(cs);
			list_styles.add(cs);
		}
		
		return list_styles;
	}
	
	
	
	
}
