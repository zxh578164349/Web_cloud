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
import util.GlobalMethod;

import com.opensymphony.xwork2.ActionSupport;

import entity.VKpiWebprofitloss;
import entity.VKpiWebprofitlossId;
import entity.VKpiWebprofitlossItems;
import entity.WebFact;
import entity.WebFactId;

/**   
 *    
 * 项目名称：Login   
 * 类名称：VKpiWebprofitlossAction   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/9/6 下午2:28:01   
 * 修改人：Administrator   
 * 修改时间：2016/9/6 下午2:28:01   
 * 修改备注：   
 * @version    
 *    
 **/
public class VKpiWebprofitlossAction extends ActionSupport implements ServletResponseAware{
	private String factNo;
	private String yymm;
	private String yymm2;
	private String factname;
	private List<String>list_factcode;
	private List<String>list_factno;
	private final static String RISERATE="漲福比率";
	private final static int NUM=30;//print_tw  多少箇項目（29+1）
	private final static int NUM2=2;//集合移除相同元素后的箇數
	private final static String STR_LONG="-999999";
	private final static String STR_DB="-999999.0";
	private final static String STR_BIG="-99999900.00%";
	private final static Double DB1=-999999.0;
	private final static Long LG1=-999999L;
	private IVKpiWebprofitlossServices vkpiprofitser;
	private IWebFactServices webFactSer;
	private javax.servlet.http.HttpServletResponse response;
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
		List<Object[]>list_factcodes=webFactSer.findByFactNo_showA_order(factNo);
		List<VKpiWebprofitloss>list_vkpipros=vkpiprofitser.findVKpiWebprofitloss(factNo,yymm,yymm2);
		List<String>list_items=this.findItems();
		Map<String,Object>map=new LinkedHashMap<String,Object>();
		for(Object[] factcode:list_factcodes){
			List<VKpiWebprofitloss>list=new LinkedList<VKpiWebprofitloss>();
			for(String month:list_months){
				list.add(new VKpiWebprofitloss(new VKpiWebprofitlossId(new WebFact(new WebFactId(factNo,(String)factcode[0])),month)));
			}
			for(int i=0;i<list.size();i++){
				VKpiWebprofitloss loss=list.get(i);
				for(VKpiWebprofitloss obj:list_vkpipros){
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
		List<VKpiWebprofitloss>list_source=vkpiprofitser.findVKpiWebprofitloss(list_factcode,yymm);
		Map<String,Object>map=new LinkedHashMap<String,Object>();
		List<String>list_items=this.findItems();
		for(String factcode:list_factcode){
			List<VKpiWebprofitloss>list_obj=new LinkedList<VKpiWebprofitloss>();
			for(String factno:list_factno){
				if(factcode.equals(factno.split("_")[0])){
					list_obj.add(new VKpiWebprofitloss(new VKpiWebprofitlossId(new WebFact(new WebFactId(factno,factcode)),yymm)));
				}
			}
			for(int i=0;i<list_obj.size();i++){
				VKpiWebprofitloss obj=list_obj.get(i);
				for(VKpiWebprofitloss loss:list_source){
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
			List<VKpiWebprofitloss>list_obj=(List<VKpiWebprofitloss>)map.get(factcode);	
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
				List<VKpiWebprofitloss>list_obj=(List<VKpiWebprofitloss>)map.get(factcode);
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
	public List<List<String>> packageTostring(List<VKpiWebprofitloss>list_obj,List<String>list_items,int mk){
		List<List<String>>result=new ArrayList<List<String>>();
		DecimalFormat frm=new DecimalFormat("0.00%");
		for(int i=0;i<list_obj.size();i++){
			VKpiWebprofitloss obj=list_obj.get(i);
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
			if(list_obj.size()>1&&i==list_obj.size()-1&&mk==1){//漲福比率
				List<String>list2=new ArrayList<String>();
				VKpiWebprofitloss obj1=list_obj.get(list_obj.size()-2);
				VKpiWebprofitloss obj2=list_obj.get(list_obj.size()-1);
				list2.add(RISERATE);
				list2.add(this.isMyNull(frm,obj2.getVKw01().doubleValue(),obj1.getVKw01().doubleValue(),obj1.getVKw01().doubleValue()));
				list2.add(this.isMyNull(frm,obj2.getVKw02().doubleValue(),obj1.getVKw02().doubleValue(),obj1.getVKw02().doubleValue()));
				list2.add(this.isMyNull(frm,obj2.getVKw03().doubleValue(),obj1.getVKw03().doubleValue(),obj1.getVKw03().doubleValue()));
				list2.add(this.isMyNull(frm,obj2.getVKw04(),obj1.getVKw04(),obj1.getVKw04()));
				list2.add(this.isMyNull(frm,obj2.getVKw05().doubleValue(),obj1.getVKw05().doubleValue(),obj1.getVKw05().doubleValue()));
				list2.add(this.isMyNull(frm,obj2.getVKw06().doubleValue(),obj1.getVKw06().doubleValue(),obj1.getVKw06().doubleValue()));
				list2.add(this.isMyNull(frm,obj2.getVKw07().doubleValue(),obj1.getVKw07().doubleValue(),obj1.getVKw07().doubleValue()));
				list2.add(this.isMyNull(frm,obj2.getVKw08().doubleValue(),obj1.getVKw08().doubleValue(),obj1.getVKw08().doubleValue()));
				list2.add(this.isMyNull(frm,obj2.getVKw09().doubleValue(),obj1.getVKw09().doubleValue(),obj1.getVKw09().doubleValue()));
				list2.add(this.isMyNull(frm,obj2.getVKw10().doubleValue(),obj1.getVKw10().doubleValue(),obj1.getVKw10().doubleValue()));
				list2.add(this.isMyNull(frm,obj2.getVKw11().doubleValue(),obj1.getVKw11().doubleValue(),obj1.getVKw11().doubleValue()));
				list2.add(this.isMyNull(frm,obj2.getVKw12().doubleValue(),obj1.getVKw12().doubleValue(),obj1.getVKw12().doubleValue()));
				list2.add(this.isMyNull(frm,obj2.getVKw13(),obj1.getVKw13(),obj1.getVKw13()));
				list2.add(this.isMyNull(frm,obj2.getVKw14().doubleValue(),obj1.getVKw14().doubleValue(),obj1.getVKw14().doubleValue()));
				list2.add(this.isMyNull(frm,obj2.getVKw15().doubleValue(),obj1.getVKw15().doubleValue(),obj1.getVKw15().doubleValue()));
				list2.add(this.isMyNull(frm,obj2.getVKw16().doubleValue(),obj1.getVKw16().doubleValue(),obj1.getVKw16().doubleValue()));
				list2.add(this.isMyNull(frm,obj2.getVKw17().doubleValue(),obj1.getVKw17().doubleValue(),obj1.getVKw17().doubleValue()));
				list2.add(this.isMyNull(frm,obj2.getVKw18().doubleValue(),obj1.getVKw18().doubleValue(),obj1.getVKw18().doubleValue()));
				list2.add(this.isMyNull(frm,obj2.getVKw19(),obj1.getVKw19(),obj1.getVKw19()));
				list2.add(this.isMyNull(frm,obj2.getVKw20().doubleValue(),obj1.getVKw20().doubleValue(),obj1.getVKw20().doubleValue()));
				list2.add(this.isMyNull(frm,obj2.getVKw21().doubleValue(),obj1.getVKw21().doubleValue(),obj1.getVKw21().doubleValue()));
				list2.add(this.isMyNull(frm,obj2.getVKw22(),obj1.getVKw22(),obj1.getVKw22()));
				list2.add(this.isMyNull(frm,obj2.getVKw23().doubleValue(),obj1.getVKw23().doubleValue(),obj1.getVKw23().doubleValue()));
				list2.add(this.isMyNull(frm,obj2.getVKw24().doubleValue(),obj1.getVKw24().doubleValue(),obj1.getVKw24().doubleValue()));
				list2.add(this.isMyNull(frm,obj2.getVKw25().doubleValue(),obj1.getVKw25().doubleValue(),obj1.getVKw25().doubleValue()));
				list2.add(this.isMyNull(frm,obj2.getVKw26().doubleValue(),obj1.getVKw26().doubleValue(),obj1.getVKw26().doubleValue()));
				list2.add(this.isMyNull(frm,obj2.getVKw27().doubleValue(),obj1.getVKw27().doubleValue(),obj1.getVKw27().doubleValue()));
				list2.add(this.isMyNull(frm,obj2.getVKw28().doubleValue(),obj1.getVKw28().doubleValue(),obj1.getVKw28().doubleValue()));
				list2.add(this.isMyNull(frm,obj2.getVKw29().doubleValue(),obj1.getVKw29().doubleValue(),obj1.getVKw29().doubleValue()));
				result.add(list2);
			}						
		}
		return result;
		
	}
	
			
	public List<List<List<Double>>> packageToListDouble(Map<String,Object>map){
		List<List<List<Double>>>list_all=new ArrayList<List<List<Double>>>();
		List<List<Double>>list_result=new ArrayList<List<Double>>();
		for(int i=0;i<NUM;i++){
			List<Double>list=new ArrayList<Double>();
			list_result.add(list);
		}
		
			for(String factcode:map.keySet()){
				List<VKpiWebprofitloss>list_obj=(List<VKpiWebprofitloss>)map.get(factcode);
				for(VKpiWebprofitloss obj:list_obj){
					list_result.get(0).add(-1.0);
					list_result.get(1).add(obj.getVKw01().doubleValue());
					list_result.get(2).add(obj.getVKw02().doubleValue());
					list_result.get(3).add(obj.getVKw03().doubleValue());			
					list_result.get(4).add(obj.getVKw04());
					list_result.get(5).add(obj.getVKw05().doubleValue());
					list_result.get(6).add(obj.getVKw06().doubleValue());
					list_result.get(7).add(obj.getVKw07().doubleValue());
					list_result.get(8).add(obj.getVKw08().doubleValue());
					list_result.get(9).add(obj.getVKw09().doubleValue());
					list_result.get(10).add(obj.getVKw10().doubleValue());
					list_result.get(11).add(obj.getVKw11().doubleValue());
					list_result.get(12).add(obj.getVKw12().doubleValue());
					list_result.get(13).add(obj.getVKw13());
					list_result.get(14).add(obj.getVKw14().doubleValue());
					list_result.get(15).add(obj.getVKw15().doubleValue());
					list_result.get(16).add(obj.getVKw16().doubleValue());
					list_result.get(17).add(obj.getVKw17().doubleValue());
					list_result.get(18).add(obj.getVKw18().doubleValue());
					list_result.get(19).add(obj.getVKw19());
					list_result.get(20).add(obj.getVKw20().doubleValue());
					list_result.get(21).add(obj.getVKw21().doubleValue());
					list_result.get(22).add(obj.getVKw22());
					list_result.get(23).add(obj.getVKw23().doubleValue());
					list_result.get(24).add(obj.getVKw24().doubleValue());
					list_result.get(25).add(obj.getVKw25().doubleValue());
					list_result.get(26).add(obj.getVKw26().doubleValue());
					list_result.get(27).add(obj.getVKw27().doubleValue());
					list_result.get(28).add(obj.getVKw28().doubleValue());
					list_result.get(29).add(obj.getVKw29().doubleValue());
				}
			}			
			List<List<Double>>list_result_sort=this.deepCloneList(list_result);//克隆集合
			for(int i=0;i<list_result_sort.size();i++){
				List<Double>list=list_result_sort.get(i);
				List<Double>list2=list_result.get(i);
				GlobalMethod.removeSameDouble(list);//集合排序			
				this.removeDB1(list);//排序后，去掉空的數據，但這裏,DB1代表爲空數據		
				if(list.size()>3){
					list2.add(list.get(2));
					list2.add(list.get(list.size()-3));
				}
				if(list.size()<=3){
					list2.add(DB1);list2.add(DB1);
				}
				
			}			
		list_all.add(list_result);
		list_all.add(list_result_sort);//去掉重複的,已排序的,list_result與list_result_sort長度一樣
		return list_all;
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
				
				List<VKpiWebprofitloss>list_obj=(List<VKpiWebprofitloss>)map.get(factcode);
				for(VKpiWebprofitloss obj:list_obj){
					list_result.get(0).add(-1.0);
					list_result.get(1).add(obj.getVKw01().doubleValue());
					list_result.get(2).add(obj.getVKw02().doubleValue());
					list_result.get(3).add(obj.getVKw03().doubleValue());			
					list_result.get(4).add(obj.getVKw04());
					list_result.get(5).add(obj.getVKw05().doubleValue());
					list_result.get(6).add(obj.getVKw06().doubleValue());
					list_result.get(7).add(obj.getVKw07().doubleValue());
					list_result.get(8).add(obj.getVKw08().doubleValue());
					list_result.get(9).add(obj.getVKw09().doubleValue());
					list_result.get(10).add(obj.getVKw10().doubleValue());
					list_result.get(11).add(obj.getVKw11().doubleValue());
					list_result.get(12).add(obj.getVKw12().doubleValue());
					list_result.get(13).add(obj.getVKw13());
					list_result.get(14).add(obj.getVKw14().doubleValue());
					list_result.get(15).add(obj.getVKw15().doubleValue());
					list_result.get(16).add(obj.getVKw16().doubleValue());
					list_result.get(17).add(obj.getVKw17().doubleValue());
					list_result.get(18).add(obj.getVKw18().doubleValue());
					list_result.get(19).add(obj.getVKw19());
					list_result.get(20).add(obj.getVKw20().doubleValue());
					list_result.get(21).add(obj.getVKw21().doubleValue());
					list_result.get(22).add(obj.getVKw22());
					list_result.get(23).add(obj.getVKw23().doubleValue());
					list_result.get(24).add(obj.getVKw24().doubleValue());
					list_result.get(25).add(obj.getVKw25().doubleValue());
					list_result.get(26).add(obj.getVKw26().doubleValue());
					list_result.get(27).add(obj.getVKw27().doubleValue());
					list_result.get(28).add(obj.getVKw28().doubleValue());
					list_result.get(29).add(obj.getVKw29().doubleValue());
				}
				
				
				List<List<Double>>list_result_sort=this.deepCloneList(list_result);//克隆集合
				for(int i=0;i<list_result_sort.size();i++){
					List<Double>list=list_result_sort.get(i);
					List<Double>list2=list_result.get(i);
					GlobalMethod.removeSameDouble(list);//集合排序,移除相同元素			
					this.removeDB1(list);//排序后，去掉空的數據，但這裏,DB1代表爲空數據			
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
		if(str.equals(STR_BIG)||str.equals(STR_LONG)||str.equals(STR_DB)){
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
	public String isMyNull(DecimalFormat frm,Double d1,Double d2,Double d3){
		String result=null;
		if(d1.equals(DB1)||d2.equals(DB1)||d3.equals(DB1)){
			result="無";
		}else{
			result=frm.format(GlobalMethod.division(d1-d2, d3));
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
			if(list.get(i).equals(DB1)){
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
	public List<String> isPercents(VKpiWebprofitloss obj,List<String> list_items){
		List<Object>list=new ArrayList<Object>();
		//List<String>list_items=this.findItems();
		List<String>list_result=new ArrayList<String>();
		DecimalFormat frm=new DecimalFormat("0.00%");
		list.add(obj.getVKw01());
		list.add(obj.getVKw02());
		list.add(obj.getVKw03());			
		list.add(obj.getVKw04());
		list.add(obj.getVKw05());
		list.add(obj.getVKw06());
		list.add(obj.getVKw07());
		list.add(obj.getVKw08());
		list.add(obj.getVKw09());
		list.add(obj.getVKw10());
		list.add(obj.getVKw11());
		list.add(obj.getVKw12());
		list.add(obj.getVKw13());
		list.add(obj.getVKw14());
		list.add(obj.getVKw15());
		list.add(obj.getVKw16());
		list.add(obj.getVKw17());
		list.add(obj.getVKw18());
		list.add(obj.getVKw19());
		list.add(obj.getVKw20());
		list.add(obj.getVKw21());
		list.add(obj.getVKw22());
		list.add(obj.getVKw23());
		list.add(obj.getVKw24());
		list.add(obj.getVKw25());
		list.add(obj.getVKw26());
		list.add(obj.getVKw27());
		list.add(obj.getVKw28());
		list.add(obj.getVKw29());		
		for(int i=0;i<list.size();i++){			
			if(list_items.get(i).split("__")[1].equals("%")){
				list_result.add(frm.format(list.get(i)));
			}else{
				list_result.add(list.get(i).toString());
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
		List<VKpiWebprofitloss>list_vkpipros=vkpiprofitser.findVKpiWebprofitloss(factNo,yymm,yymm2);
		List<String>list_items=this.findItems();
		Map<String,Object>map=new LinkedHashMap<String,Object>();
		for(Object[] factcode:list_factcodes){
			List<VKpiWebprofitloss>list=new LinkedList<VKpiWebprofitloss>();
			for(String month:list_months){
				list.add(new VKpiWebprofitloss(new VKpiWebprofitlossId(new WebFact(new WebFactId(factNo,(String)factcode[0])),month)));
			}
			for(int i=0;i<list.size();i++){
				VKpiWebprofitloss loss=list.get(i);
				for(VKpiWebprofitloss obj:list_vkpipros){
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
		List<VKpiWebprofitloss>list_source=vkpiprofitser.findVKpiWebprofitloss(list_factcode,yymm);
		Map<String,Object>map=new LinkedHashMap<String,Object>();
		List<String>list_items=this.findItems();
		for(String factcode:list_factcode){
			List<VKpiWebprofitloss>list_obj=new LinkedList<VKpiWebprofitloss>();
			for(String factno:list_factno){
				if(factcode.equals(factno.split("_")[0])){
					list_obj.add(new VKpiWebprofitloss(new VKpiWebprofitlossId(new WebFact(new WebFactId(factno,factcode)),yymm)));
				}
			}
			for(int i=0;i<list_obj.size();i++){
				VKpiWebprofitloss obj=list_obj.get(i);
				for(VKpiWebprofitloss loss:list_source){
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
		
		/*List<List<Double>>list_doubles=this.packageToListDouble(map).get(0);
		List<List<Double>>list_doubles_sort=this.packageToListDouble(map).get(1);
		List<HSSFCellStyle>list_styles=new ArrayList<HSSFCellStyle>();
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
			List<VKpiWebprofitloss>list_obj=(List<VKpiWebprofitloss>)map.get(factcode);	
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
				List<VKpiWebprofitloss>list_obj=(List<VKpiWebprofitloss>)map.get(factcode);
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
