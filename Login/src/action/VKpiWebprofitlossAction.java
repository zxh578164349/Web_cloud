/**
 * 
 */
package action;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.util.CellRangeAddress;
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
		XSSFCellStyle cs_head2=(XSSFCellStyle)map_style.get("cs_head2");
		XSSFCellStyle cs_head=(XSSFCellStyle)map_style.get("cs_head");
		XSSFCellStyle cs=(XSSFCellStyle)map_style.get("cs");		
		List<String>list_months=GlobalMethod.findMonths(yymm,yymm2);
		List<Object[]>list_factcodes=webFactSer.findByFactNo_showA_order(factNo);
		List<VKpiWebprofitloss>list_vkpipros=vkpiprofitser.findVKpiWebprofitloss(factNo,yymm,yymm2);
		
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
				
		this.init(sheet,map,map_style,1);
		int temp=0;
		for(String factcode:map.keySet()){
			List<VKpiWebprofitloss>list_obj=(List<VKpiWebprofitloss>)map.get(factcode);
			int length=list_obj.size();
			if(length>1){
				length++;
			}
			sheet.getRow(1).getCell(3+temp*length).setCellValue(factcode);
			CellRangeAddress cra=new CellRangeAddress(1,(short)1,3+temp*length,(short)2+(temp+1)*length);
			sheet.addMergedRegion(cra);
			for(int i=0;i<length;i++){
				sheet.getRow(1).getCell(3+temp*length+i).setCellStyle(cs_head2);
			}
			
			List<List<String>>list_pack=this.packageTostring(list_obj,0);
			for(int a=0;a<list_pack.size();a++){
				List<String>list=list_pack.get(a);
				for(int b=0;b<list.size();b++){
					sheet.getRow(2+b).getCell(3+a+temp*length).setCellValue(list.get(b));
					if(b==0){
						sheet.getRow(2+b).getCell(3+a+temp*length).setCellStyle(cs_head);
					}else{
						sheet.getRow(2+b).getCell(3+a+temp*length).setCellStyle(cs);
					}
					
				}
			}
			temp++;
		}
		
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
		Map<String,Object>map_style=GlobalMethod.findStyles2007(wb);
		XSSFCellStyle cs_head2=(XSSFCellStyle)map_style.get("cs_head2");
		XSSFCellStyle cs_head=(XSSFCellStyle)map_style.get("cs_head");
		XSSFCellStyle cs=(XSSFCellStyle)map_style.get("cs");
		List<VKpiWebprofitloss>list_source=vkpiprofitser.findVKpiWebprofitloss(list_factcode,yymm);
		Map<String,Object>map=new LinkedHashMap<String,Object>();
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
		this.init(sheet,map,map_style,0);
		int temp=0;
		for(String factcode:map.keySet()){			
			List<VKpiWebprofitloss>list_obj=(List<VKpiWebprofitloss>)map.get(factcode);	
			int length=list_obj.size();
			sheet.getRow(1).getCell(3+temp).setCellValue(factcode);
			CellRangeAddress cra=new CellRangeAddress(1,(short)1,3+temp,(short)2+temp+length);
			sheet.addMergedRegion(cra);
			for(int i=0;i<length;i++){
				sheet.getRow(1).getCell(3+temp+i).setCellStyle(cs_head2);
			}
			
			List<List<String>>list_pack=this.packageTostring(list_obj,1);
			
			for(int a=0;a<list_pack.size();a++){
				List<String>list=list_pack.get(a);
				for(int b=0;b<list.size();b++){
					sheet.getRow(2+b).getCell(3+a+temp).setCellValue(list.get(b));
					if(b==0){
						sheet.getRow(2+b).getCell(3+a+temp).setCellStyle(cs_head);
					}else{
						sheet.getRow(2+b).getCell(3+a+temp).setCellStyle(cs);
					}
					
				}
			}
			temp=temp+length;
		}
		
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
	
	public void init(XSSFSheet sheet,Map<String,Object>map,Map<String,Object>map_style,int mk) throws IOException{
		sheet.setColumnWidth(1,4500);		
		for(int i=0;i<28;i++){
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
					index++;
					if(i==0){
						sheet.setColumnWidth(index,3000);
					}
				}
			}			
		}
		
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
			sheet.getRow(0).getCell(0).setCellValue("重點指標匯總");
		}
		
		for(int i=0;i<5;i++){
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
		
		List<String>list_items=this.findItems();
		for(int i=0;i<list_items.size();i++){
			sheet.getRow(3+i).getCell(0).setCellValue(i+1);
			sheet.getRow(3+i).getCell(1).setCellValue(list_items.get(i).split("__")[0]);//項目
			sheet.getRow(3+i).getCell(2).setCellValue(list_items.get(i).split("__")[1]);//單位
			for(int j=0;j<3;j++){
				sheet.getRow(3+i).getCell(j).setCellStyle(cs);
			}
		}
		
				
	}
	
	public List<String>findItems(){
		List<String>list=new ArrayList<String>();
		list.add("機台利用率__%");//1
		list.add("實際回轉數__回");
		list.add("成本率__%");//3
		list.add("利潤率__%");//4
		list.add("退貨率__%");//5
		list.add("直工__人");
		list.add("間工__人");
		list.add("全廠總人數__人");
		list.add("直間比__:");
		list.add("直工人均產能__模");
		list.add("全廠人均產能__模");
		list.add("時產能__模/H");
		list.add("總損耗__%");//13
		list.add("平均邊料重__G/雙");
		list.add("邊料率__%");//15
		list.add("不良重量__KG");
		list.add("不良率__%");//17
		list.add("用水單耗__USD/雙");
		list.add("用電單耗__USD/雙");
		list.add("蒸氣單耗__噸/雙");
		list.add("色料藥品單耗__G/雙");
		list.add("色料藥品單耗__USD/雙");
		list.add("加班費__USD");
		list.add("油壓回收率__%");//24
		return list;
		
	}
	
	/**
	 * 
	 * @Title: packageTostring
	 * @Description: TODO
	 * @param @param list_obj
	 * @param @param isTotal  0:工廠報表    1:臺灣報表
	 * @param @return
	 * @return List<List<String>>
	 * @throws
	 * @author web
	 * @date 2016/9/8
	 */
	public List<List<String>> packageTostring(List<VKpiWebprofitloss>list_obj,int isTotal){
		List<List<String>>result=new ArrayList<List<String>>();
		DecimalFormat frm=new DecimalFormat("0.00%");
		for(int i=0;i<list_obj.size();i++){
			VKpiWebprofitloss obj=list_obj.get(i);
			List<String>list=new ArrayList<String>();
			if(isTotal==1){
				if(obj.getId().getFact().getFactSname()!=null){
					list.add(obj.getId().getFact().getFactSname());
				}else{
					list.add(obj.getId().getFact().getId().getFactNo().split("_")[2]);
				}
				
			}else{
				list.add(obj.getId().getYymm());
			}			
			list.add(frm.format(obj.getVKw01()));
			list.add(obj.getVKw02().toString());
			list.add(frm.format(obj.getVKw03()));			
			list.add(frm.format(obj.getVKw04()));
			list.add(frm.format(obj.getVKw05()));
			list.add(obj.getVKw06().toString());
			list.add(obj.getVKw07().toString());
			list.add(obj.getVKw08().toString());
			list.add(obj.getVKw09().toString());
			list.add(obj.getVKw10().toString());
			list.add(obj.getVKw11().toString());
			list.add(obj.getVKw12().toString());
			list.add(frm.format(obj.getVKw13()));
			list.add(obj.getVKw14().toString());
			list.add(frm.format(obj.getVKw15()));
			list.add(obj.getVKw16().toString());
			list.add(frm.format(obj.getVKw17()));
			list.add(obj.getVKw18().toString());
			list.add(obj.getVKw19().toString());
			list.add(obj.getVKw20().toString());
			list.add(obj.getVKw21().toString());
			list.add(obj.getVKw22().toString());
			list.add(obj.getVKw23().toString());
			list.add(frm.format(obj.getVKw24()));
			result.add(list);
			if(list_obj.size()>1&&i==list_obj.size()-1&&isTotal==0){
				List<String>list2=new ArrayList<String>();
				VKpiWebprofitloss obj1=list_obj.get(list_obj.size()-2);
				VKpiWebprofitloss obj2=list_obj.get(list_obj.size()-1);
				list2.add(RISERATE);
				list2.add(frm.format(GlobalMethod.division(obj2.getVKw01().doubleValue()-obj1.getVKw01().doubleValue(),obj1.getVKw01().doubleValue())));
				list2.add(frm.format(GlobalMethod.division(obj2.getVKw02().doubleValue()-obj1.getVKw02().doubleValue(),obj1.getVKw02().doubleValue())));
				list2.add(frm.format(GlobalMethod.division(obj2.getVKw03().doubleValue()-obj1.getVKw03().doubleValue(),obj1.getVKw03().doubleValue())));
				list2.add(frm.format(GlobalMethod.division(obj2.getVKw04()-obj1.getVKw04(),obj1.getVKw04())));
				list2.add(frm.format(GlobalMethod.division(obj2.getVKw05().doubleValue()-obj1.getVKw05().doubleValue(),obj1.getVKw05().doubleValue())));
				list2.add(frm.format(GlobalMethod.division(obj2.getVKw06().doubleValue()-obj1.getVKw06().doubleValue(),obj1.getVKw06().doubleValue())));
				list2.add(frm.format(GlobalMethod.division(obj2.getVKw07().doubleValue()-obj1.getVKw07().doubleValue(),obj1.getVKw07().doubleValue())));
				list2.add(frm.format(GlobalMethod.division(obj2.getVKw08().doubleValue()-obj1.getVKw08().doubleValue(),obj1.getVKw08().doubleValue())));
				list2.add(frm.format(GlobalMethod.division(obj2.getVKw09().doubleValue()-obj1.getVKw09().doubleValue(),obj1.getVKw09().doubleValue())));
				list2.add(frm.format(GlobalMethod.division(obj2.getVKw10().doubleValue()-obj1.getVKw10().doubleValue(),obj1.getVKw10().doubleValue())));
				list2.add(frm.format(GlobalMethod.division(obj2.getVKw11().doubleValue()-obj1.getVKw11().doubleValue(),obj1.getVKw11().doubleValue())));
				list2.add(frm.format(GlobalMethod.division(obj2.getVKw12().doubleValue()-obj1.getVKw12().doubleValue(),obj1.getVKw12().doubleValue())));
				list2.add(frm.format(GlobalMethod.division(obj2.getVKw13().doubleValue()-obj1.getVKw13().doubleValue(),obj1.getVKw13().doubleValue())));
				list2.add(frm.format(GlobalMethod.division(obj2.getVKw14().doubleValue()-obj1.getVKw14().doubleValue(),obj1.getVKw14().doubleValue())));
				list2.add(frm.format(GlobalMethod.division(obj2.getVKw15().doubleValue()-obj1.getVKw15().doubleValue(),obj1.getVKw15().doubleValue())));
				list2.add(frm.format(GlobalMethod.division(obj2.getVKw16()-obj1.getVKw16(),obj1.getVKw16())));
				list2.add(frm.format(GlobalMethod.division(obj2.getVKw17().doubleValue()-obj1.getVKw17().doubleValue(),obj1.getVKw17().doubleValue())));
				list2.add(frm.format(GlobalMethod.division(obj2.getVKw18().doubleValue()-obj1.getVKw18().doubleValue(),obj1.getVKw18().doubleValue())));
				list2.add(frm.format(GlobalMethod.division(obj2.getVKw19().doubleValue()-obj1.getVKw19().doubleValue(),obj1.getVKw19().doubleValue())));
				list2.add(frm.format(GlobalMethod.division(obj2.getVKw20().doubleValue()-obj1.getVKw20().doubleValue(),obj1.getVKw20().doubleValue())));
				list2.add(frm.format(GlobalMethod.division(obj2.getVKw21().doubleValue()-obj1.getVKw21().doubleValue(),obj1.getVKw21().doubleValue())));
				list2.add(frm.format(GlobalMethod.division(obj2.getVKw22().doubleValue()-obj1.getVKw22().doubleValue(),obj1.getVKw22().doubleValue())));
				list2.add(frm.format(GlobalMethod.division(obj2.getVKw23().doubleValue()-obj1.getVKw23().doubleValue(),obj1.getVKw23().doubleValue())));
				list2.add(frm.format(GlobalMethod.division(obj2.getVKw24().doubleValue()-obj1.getVKw24().doubleValue(),obj1.getVKw24().doubleValue())));
				result.add(list2);
			}						
		}
		return result;
		
	}
	
}
