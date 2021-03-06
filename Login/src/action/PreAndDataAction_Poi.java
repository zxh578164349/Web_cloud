package action;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.Region;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import services.IWebEstProductServices;
import services.IWebFactServices;
import services.IWebYieldDataServices;
import util.GlobalMethod;

import com.opensymphony.xwork2.ActionSupport;

import entity.*;

public class PreAndDataAction_Poi extends ActionSupport implements
		ServletResponseAware {
	private javax.servlet.http.HttpServletResponse response;
	private String year;
	private String month;
	private String lmonth;
	private String result;
	private IWebYieldDataServices dataSer;
	private IWebFactServices webFactSer;
	private IWebEstProductServices estProSer;
	private List<String> factnolist=new ArrayList<String>();
	private String type;
	private List<String> factcodelist;
	private String sdate;
	private String edate;
	private int emailMk;
	
	
	

	public int getEmailMk() {
		return emailMk;
	}

	public void setEmailMk(int emailMk) {
		this.emailMk = emailMk;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public List<String> getFactcodelist() {
		return factcodelist;
	}

	public void setFactcodelist(List<String> factcodelist) {
		this.factcodelist = factcodelist;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}

	public List<String> getFactnolist() {
		return factnolist;
	}

	public void setFactnolist(List<String> factnolist) {
		this.factnolist = factnolist;
	}
	
	public void setDataSer(IWebYieldDataServices dataSer) {
		this.dataSer = dataSer;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getLmonth() {
		return lmonth;
	}

	public void setLmonth(String lmonth) {
		this.lmonth = lmonth;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}
	
	public void setEstProSer(IWebEstProductServices estProSer) {
		this.estProSer = estProSer;
	}

	/**
	 * Excel2003
	 * @Title: print2Y_hb
	 * @Description: TODO
	 * @param 
	 * @return void
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws
	 * @author web
	 * @date 2016/6/7
	 */
	public void print2Y_hb() throws ParseException, IOException{
		List<WebFact>list_facts=null;
		if(emailMk==1){
			list_facts=webFactSer.findAll();//所有廠別(factShow='0')
			factcodelist=webFactSer.findFactCodeshow();
		}else{
			list_facts=webFactSer.findByList(factnolist);//頁面選擇的廠別
		}
		List<String>list_months=GlobalMethod.findMonths(sdate, edate);//所有月份
		List<Webestproduct>list_pros=estProSer.findByYymm(sdate, edate);//查得的預計生產數據
		DateFormat fmt=new SimpleDateFormat("yyyyMM");
		DateFormat fmt2=new SimpleDateFormat("yyyyMMdd");
		String today=fmt2.format(new Date());//今天
		List<String>list_a_str=new ArrayList<String>();
		List<String>list_b_str=new ArrayList<String>();
		//List<String>list_c_str=new ArrayList<String>();
		List<String>list_d_str=new ArrayList<String>();
		list_a_str.add("日期/產量/廠別");
		list_a_str.add("戰力分析模數");
		list_a_str.add("預計生產模數");
		list_a_str.add("預計請款雙數");
		list_a_str.add("機台孔位數");
						
		list_b_str.add("有效孔位");
		list_b_str.add("工程/樣品");
		list_b_str.add("補料孔位");
		list_b_str.add("其他");
								
		list_d_str.add("上模數");
		list_d_str.add("人數");
		list_d_str.add("標準產量");
		list_d_str.add("實際產量");
		list_d_str.add("達成率(%)");
		
		
		Map<String,Object>map_a=new LinkedHashMap<String,Object>();
		Map<String,Object>map_b=new LinkedHashMap<String,Object>();
		
		for(String month:list_months){//for a
			List<String>days=GlobalMethod.findDaysOfMonth(month,"yyyyMMdd");
			List<Webestproduct>list_a=new ArrayList<Webestproduct>();
			List<WebYieldData>list_ydatas=dataSer.findByYymm(month);						
			List<List<WebYieldData>>list_b=new ArrayList<List<WebYieldData>>();
			for(WebFact fact:list_facts){//for b1
				list_a.add(new Webestproduct(new WebestproductId(fact.getId().getFactNo(),fact.getId().getFactArea(),fmt.parse(month),"zd")));			
				List<WebYieldData>list_b1=new ArrayList<WebYieldData>();
				for(String day:days){
					if(day.compareTo(today)>=0){
						continue;
					}
					list_b1.add(new WebYieldData(new WebYieldDataId(fact.getId().getFactNo(),fact.getId().getFactArea(),fmt2.parse(day))));
				}
				list_b.add(list_b1);
				
			}//for b1
			for(int i=0;i<list_a.size();i++ ){//for b2
				Webestproduct pro=list_a.get(i);
				for(Webestproduct pro2:list_pros){
					if(pro.getId().getFactNo().equals(pro2.getId().getFactNo())&&
							pro.getId().getFactCode().equals(pro2.getId().getFactCode())&&
							fmt.format(pro.getId().getYymm()).equals(fmt.format(pro2.getId().getYymm()))){
						//pro=pro2;
						list_a.remove(i);
						list_a.add(i,pro2);
						break;
					}
				}
			}//for b2
			for(List<WebYieldData> list:list_b){//for b3
				for(int i=0;i<list.size();i++){
					WebYieldData ydata=list.get(i);
					for(WebYieldData ydata2:list_ydatas){
						if(ydata.getId().getFactNo().equals(ydata2.getId().getFactNo())&&
								ydata.getId().getFactCode().equals(ydata2.getId().getFactCode())&&
								fmt2.format(ydata.getId().getYymmdd()).equals(fmt2.format(ydata2.getId().getYymmdd()))){
							//ydata=ydata2;
							list.remove(i);
							list.add(i,ydata2);
							break;
						}
					}
				}
			}//for b3
			map_a.put(month, list_a);
			map_b.put(month, list_b);						
		}//for a
		
		/****************************各箇欄位數據統計  20161006***************************/
		WebFact heJi=new WebFact(new WebFactId("","計"));
		heJi.setFactSname("合");
		list_facts.add(heJi);
		this.dayTotal(map_a,map_b);
		/****************************各箇欄位數據統計  20161006***************************/
		
				
		Workbook wb=null;
		String fileName=null;		
		if(type.equals("Excel2003")){
			wb=this.init(list_months, list_facts, map_a, map_b, list_a_str, list_b_str, list_d_str);			
			response.setContentType("application/vnd.ms-excel");
			fileName="report.xls";
		}else{
			wb=this.init_2007(list_months, list_facts, map_a, map_b, list_a_str, list_b_str, list_d_str);
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			fileName="report.xlsx";
		}
		
		if(emailMk==1){//發送郵件報表
			//OutputStream os=new FileOutputStream("d:\\"+sdate+".xls");
			OutputStream os=new FileOutputStream(ServletActionContext.getServletContext().getRealPath("TEMPFILES\\"+sdate+".xls"));
			wb.write(os);
			os.close();	
		}else{//網頁訪問報表
			ServletOutputStream os=response.getOutputStream();	
			int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");//判斷是否為IE瀏覽器,大於0則為IE瀏覽器		
			if(msie>0){
				fileName=java.net.URLEncoder.encode(fileName,"utf-8");//解決IE中文文件不能下載的問題
			}else{
				fileName=new String(fileName.getBytes("utf-8"),"iso-8859-1");//解決非IE中文名亂碼問題
			}		
			response.setHeader("Content-disposition", "attachment;filename="+fileName);
			wb.write(os);
			os.close();	
		}						
	}
	
	
	
	public HSSFWorkbook init(List<String>list_months,List<WebFact>list_facts,Map<String,Object>map_a,Map<String,Object>map_b,
			List<String>list_a_str,List<String>list_b_str,List<String>list_d_str) throws IOException, ParseException{
		HSSFWorkbook wb=new HSSFWorkbook();
		Map<String,Object>map_style=this.findStyles(wb);
		//HSSFCellStyle cs=(HSSFCellStyle)map_style.get("cs");
		HSSFCellStyle cs_title=(HSSFCellStyle)map_style.get("cs_title");
		HSSFSheet sheet=wb.createSheet("sheet1");
		HSSFSheet sheet2=null;		
		for(int i=0;i<50*list_months.size();i++){
			sheet.createRow(i);
			for(int j=0;j<list_facts.size()*5+1;j++){
				if(j>251){					
					continue;
				}
				sheet.getRow(i).createCell(j);
			}
		}
		if(list_facts.size()>49){
			sheet2=wb.createSheet("sheet2");
			for(int i=0;i<50*list_months.size();i++){
				sheet2.createRow(i);
				for(int j=0;j<list_facts.size()*5-245;j++){
					if(j>251){					
						continue;
					}
					sheet2.getRow(i).createCell(j);
				}
			}
			sheet2.setColumnWidth(0, 4500);
		}
		
		sheet.setColumnWidth(0, 4500);
		
		sheet.getRow(0).getCell(0).setCellValue("加久各工廠每日產量達成狀況匯總表");
		CellRangeAddress cra_title = new CellRangeAddress(0, 0, 0, 12);
		sheet.addMergedRegion(cra_title);
		for(int a=0;a<6;a++){
			sheet.getRow(0).getCell(a).setCellStyle(cs_title);
		}		
		this.init(sheet,sheet2, map_style, list_facts,list_months,map_a,map_b,list_a_str,list_b_str,list_d_str);
		if(factcodelist!=null&&factcodelist.size()>0){
			this.fcodeTotal(wb,map_style, factcodelist, list_months);
		}		
		return wb;
	}
	public void init(HSSFSheet sheet,HSSFSheet sheet2,Map<String,Object> map_style, List<WebFact> list_facts,List<String> list_months,Map<String,Object>map_a,Map<String,Object>map_b,
			List<String>list_a_str,List<String>list_b_str,List<String>list_d_str) throws IOException, ParseException {							
		//HSSFCellStyle cs=(HSSFCellStyle)map_style.get("cs");
		List<String>list_c_str=new ArrayList<String>();	
				
		for(int i=0;i<list_months.size();i++){
			list_c_str.clear();
			List<String>days=GlobalMethod.findDaysOfMonth(list_months.get(i),"MM/dd");
			list_c_str.clear();
			list_c_str.add("日期");
			for(String day:days){
				list_c_str.add(day);
			}
			list_c_str.add("合計");
			list_c_str.add("工作天數");			
			List<Webestproduct>list_pros=(List<Webestproduct>)map_a.get(list_months.get(i));
			List<List<WebYieldData>>list_ydatas=(List<List<WebYieldData>>)map_b.get(list_months.get(i));												
			this.init(sheet,sheet2, map_style, list_facts, list_a_str, list_b_str, list_c_str,list_d_str,days,list_pros,list_ydatas,i);						
		}				
		
	}
	
	public void init(HSSFSheet sheet,HSSFSheet sheet2,Map<String,Object> map_style, List<WebFact> list_facts,
			List<String>list_a_str,List<String>list_b_str,List<String>list_c_str,List<String>list_d_str,List<String>days,
			List<Webestproduct>list_pros,List<List<WebYieldData>>list_ydatas,int index){
		
		HSSFCellStyle cs=(HSSFCellStyle)map_style.get("cs");
		//HSSFCellStyle cs_thousand_person=(HSSFCellStyle)map_style.get("cs_thousand_person");
		//HSSFCellStyle cs_data=(HSSFCellStyle)map_style.get("cs_data");
		
		HSSFCellStyle cs_thousand=(HSSFCellStyle)map_style.get("cs_thousand");
		HSSFCellStyle cs_thousand_lyellow=(HSSFCellStyle)map_style.get("cs_thousand_lyellow");	
		for(int a=0;a<list_a_str.size();a++){
			sheet.getRow(a+1+44*index).getCell(0).setCellValue(list_a_str.get(a));
			sheet.getRow(a+1+44*index).getCell(0).setCellStyle(cs);
						
			if(list_facts.size()>49){//if sheet2
				sheet2.getRow(a+1+44*index).getCell(0).setCellValue(list_a_str.get(a));
				sheet2.getRow(a+1+44*index).getCell(0).setCellStyle(cs);
			}//if sheet2
		}
		
		/**********************機臺孔位數***********************/
		CellRangeAddress cra_a=new CellRangeAddress(5+44*index,8+44*index,0,0);
		sheet.addMergedRegion(cra_a);
		for(int i=0;i<4;i++){
			sheet.getRow(5+44*index+i).getCell(0).setCellStyle(cs_thousand_lyellow);
		}
		if(list_facts.size()>49){//if sheet2
			sheet2.addMergedRegion(cra_a);
			for(int i=0;i<4;i++){
				sheet2.getRow(5+44*index+i).getCell(0).setCellStyle(cs_thousand_lyellow);
			}
		}//if sheet2
		/**********************機臺孔位數***********************/
		
		for(int b=0;b<list_facts.size();b++){//for b
			/*********************************sheet2**************************************************/
			if(b>49){// if sheet2
				Webestproduct pro=list_pros.get(b);			
				for(int i=0;i<4;i++){
					CellRangeAddress cra=new CellRangeAddress(i+1+44*index,i+1+44*index,1+5*(b-50),5+5*(b-50));
					sheet2.addMergedRegion(cra);	
					for(int j=0;j<5;j++){
						sheet2.getRow(i+1+44*index).getCell(j+1+5*(b-50)).setCellStyle(cs_thousand);
					}
				}
				sheet2.getRow(1+44*index).getCell(1+5*(b-50)).setCellValue(list_facts.get(b).getFactSname()+"_"+list_facts.get(b).getId().getFactArea());
				sheet2.getRow(2+44*index).getCell(1+5*(b-50)).setCellValue(pro.getMachinepower()==null?0:pro.getMachinepower());
				sheet2.getRow(3+44*index).getCell(1+5*(b-50)).setCellValue(pro.getEstmodel()==null?0:pro.getEstmodel());
				sheet2.getRow(4+44*index).getCell(1+5*(b-50)).setCellValue(pro.getEstpay()==null?0:pro.getEstpay());
				
				for(int i=1;i<3;i++){
					CellRangeAddress cra=new CellRangeAddress(5+44*index,8+44*index,i+5*(b-50),i+5*(b-50));
					sheet2.addMergedRegion(cra);	
					for(int j=0;j<4;j++){
						sheet2.getRow(5+j+44*index).getCell(i+5*(b-50)).setCellStyle(cs_thousand_lyellow);
					}
				}
				sheet2.getRow(5+44*index).getCell(1+5*(b-50)).setCellValue("總機孔");
				sheet2.getRow(5+44*index).getCell(2+5*(b-50)).setCellValue(pro.getTotalhole()==null?0:pro.getTotalhole());
				for(int i=0;i<list_b_str.size();i++){
					sheet2.getRow(5+i+44*index).getCell(3+5*(b-50)).setCellValue(list_b_str.get(i));
					sheet2.getRow(5+i+44*index).getCell(3+5*(b-50)).setCellStyle(cs_thousand_lyellow);
					CellRangeAddress cra=new CellRangeAddress(5+i+44*index,5+i+44*index,4+5*(b-50),5+5*(b-50));
					sheet2.addMergedRegion(cra);
					for(int j=0;j<2;j++){
						sheet2.getRow(5+i+44*index).getCell(4+j+5*(b-50)).setCellStyle(cs_thousand_lyellow);
					}
					//sheet2.getRow(5+i+44*index).getCell(4+5*(b-50)).setCellStyle(cs_thousand_lyellow);
				}
				sheet2.getRow(5+44*index).getCell(4+5*(b-50)).setCellValue(pro.getHole()==null?0:pro.getHole());
				sheet2.getRow(6+44*index).getCell(4+5*(b-50)).setCellValue(pro.getSample()==null?0:pro.getSample());
				sheet2.getRow(7+44*index).getCell(4+5*(b-50)).setCellValue(pro.getAccessories()==null?0:pro.getAccessories());
				sheet2.getRow(8+44*index).getCell(4+5*(b-50)).setCellValue(pro.getOther()==null?0:pro.getOther());
				continue;
			}//if sheet2
			/*********************************sheet2**************************************************/
			
			Webestproduct pro=list_pros.get(b);			
			for(int i=0;i<4;i++){
				CellRangeAddress cra=new CellRangeAddress(i+1+44*index,i+1+44*index,1+5*b,5+5*b);
				sheet.addMergedRegion(cra);	
				for(int j=0;j<5;j++){
					sheet.getRow(i+1+44*index).getCell(j+1+5*b).setCellStyle(cs_thousand);
				}
			}
			sheet.getRow(1+44*index).getCell(1+5*b).setCellValue(list_facts.get(b).getFactSname()+"_"+list_facts.get(b).getId().getFactArea());
			sheet.getRow(2+44*index).getCell(1+5*b).setCellValue(pro.getMachinepower()==null?0:pro.getMachinepower());
			sheet.getRow(3+44*index).getCell(1+5*b).setCellValue(pro.getEstmodel()==null?0:pro.getEstmodel());
			sheet.getRow(4+44*index).getCell(1+5*b).setCellValue(pro.getEstpay()==null?0:pro.getEstpay());
			
			
			
			for(int i=1;i<3;i++){
				CellRangeAddress cra=new CellRangeAddress(5+44*index,8+44*index,i+5*b,i+5*b);
				sheet.addMergedRegion(cra);	
				for(int j=0;j<4;j++){
					sheet.getRow(5+j+44*index).getCell(i+5*b).setCellStyle(cs_thousand_lyellow);
				}
			}
			sheet.getRow(5+44*index).getCell(1+5*b).setCellValue("總機孔");
			sheet.getRow(5+44*index).getCell(2+5*b).setCellValue(pro.getTotalhole()==null?0:pro.getTotalhole());
			for(int i=0;i<list_b_str.size();i++){
				sheet.getRow(5+i+44*index).getCell(3+5*b).setCellValue(list_b_str.get(i));
				sheet.getRow(5+i+44*index).getCell(3+5*b).setCellStyle(cs_thousand_lyellow);
				CellRangeAddress cra=new CellRangeAddress(5+i+44*index,5+i+44*index,4+5*b,5+5*b);
				sheet.addMergedRegion(cra);
				for(int j=0;j<2;j++){
					sheet.getRow(5+i+44*index).getCell(4+j+5*b).setCellStyle(cs_thousand_lyellow);
				}
				//sheet.getRow(5+i+44*index).getCell(4+5*b).setCellStyle(cs_thousand_lyellow);
			}
			sheet.getRow(5+44*index).getCell(4+5*b).setCellValue(pro.getHole()==null?0:pro.getHole());
			sheet.getRow(6+44*index).getCell(4+5*b).setCellValue(pro.getSample()==null?0:pro.getSample());
			sheet.getRow(7+44*index).getCell(4+5*b).setCellValue(pro.getAccessories()==null?0:pro.getAccessories());
			sheet.getRow(8+44*index).getCell(4+5*b).setCellValue(pro.getOther()==null?0:pro.getOther());
		}//for b
		
		
		
		
		
		//--------------------------------------------------------------------下------------------------------------------------------------------
		for(int i=0;i<list_c_str.size();i++){
			sheet.getRow(9+i+44*index).getCell(0).setCellValue(list_c_str.get(i));
			sheet.getRow(9+i+44*index).getCell(0).setCellStyle(cs);
			
			if(list_facts.size()>49){//if sheet2
				sheet2.getRow(9+i+44*index).getCell(0).setCellValue(list_c_str.get(i));
				sheet2.getRow(9+i+44*index).getCell(0).setCellStyle(cs);
			}//if sheet2
		}						
		for(int b=0;b<list_facts.size();b++){//for b2
			WebYieldData ydata=new WebYieldData(0.0,0.0,0.0,0.0,0.0);//合計
			
			/*********************************sheet2**************************************************/
			if(b>49){//if sheet2
				for(int c=0;c<list_d_str.size();c++){
					sheet2.getRow(9+44*index).getCell(1+c+5*(b-50)).setCellValue(list_d_str.get(c));
					sheet2.getRow(9+44*index).getCell(1+c+5*(b-50)).setCellStyle(cs);
				}
				List<WebYieldData>list_ydata=list_ydatas.get(b);						
				for(int c=0;c<days.size();c++){
					if(c>list_ydata.size()-1){
						for(int i=0;i<5;i++){
							sheet2.getRow(10+c+44*index).getCell(1+5*(b-50)+i).setCellStyle(cs);//填充超出今天空的部分
						}
						continue;
					}
					this.fillYdata(map_style,list_ydata.get(c), sheet2, 10+c+44*index, 1+5*(b-50));	//sheet2下表填充數據									
					/***********************************************合計******************************************************/
					this.getTotal(ydata, list_ydata.get(c).getOnModulus()==null?0:list_ydata.get(c).getOnModulus(),
							      list_ydata.get(c).getPersonnum()==null?0:list_ydata.get(c).getPersonnum(),
								  list_ydata.get(c).getStandardOutput()==null?0:list_ydata.get(c).getStandardOutput(), 
								  list_ydata.get(c).getActualYield()==null?0:list_ydata.get(c).getActualYield(),
								  list_ydata.get(c).getDaycount()==null?0:list_ydata.get(c).getDaycount());
					
					if(c==list_ydata.size()-1){
						this.fillYdata_total(map_style,list_d_str,ydata, sheet2, 10+days.size()+44*index, 1+5*(b-50));//sheet2下表填充合計
						/*for(int d=0;d<list_d_str.size();d++){
							if(d==list_d_str.size()-1){
								sheet2.getRow(10+days.size()+44*index).getCell(1+d+5*(b-50)).setCellStyle(cs_data);
							}else{
								sheet2.getRow(10+days.size()+44*index).getCell(1+d+5*(b-50)).setCellStyle(cs);
							}							
							sheet2.getRow(11+days.size()+44*index).getCell(1+d+5*(b-50)).setCellStyle(cs);//天數						
						}
						CellRangeAddress cra=new CellRangeAddress(11+days.size()+44*index,11+days.size()+44*index,1+5*(b-50),5+5*(b-50));
						sheet2.addMergedRegion(cra);*/
					}
					/***********************************************合計******************************************************/
				}
				continue;
			}//if sheet2
			/*********************************sheet2**************************************************/
			
			for(int c=0;c<list_d_str.size();c++){
				sheet.getRow(9+44*index).getCell(1+c+5*b).setCellValue(list_d_str.get(c));
				sheet.getRow(9+44*index).getCell(1+c+5*b).setCellStyle(cs);
			}
			List<WebYieldData>list_ydata=list_ydatas.get(b);						
			for(int c=0;c<days.size();c++){
				if(c>list_ydata.size()-1){
					for(int i=0;i<5;i++){
						sheet.getRow(10+c+44*index).getCell(1+5*b+i).setCellStyle(cs);//填充超出今天空的部分
					}					
					continue;
				}
				this.fillYdata(map_style,list_ydata.get(c), sheet, 10+c+44*index, 1+5*b);//下表填充數據
				
				/***********************************************合計******************************************************/
				this.getTotal(ydata, list_ydata.get(c).getOnModulus()==null?0:list_ydata.get(c).getOnModulus(),
						      list_ydata.get(c).getPersonnum()==null?0:list_ydata.get(c).getPersonnum(),
							  list_ydata.get(c).getStandardOutput()==null?0:list_ydata.get(c).getStandardOutput(), 
							  list_ydata.get(c).getActualYield()==null?0:list_ydata.get(c).getActualYield(),
							  list_ydata.get(c).getDaycount()==null?0:list_ydata.get(c).getDaycount());
				
				if(c==list_ydata.size()-1){
					this.fillYdata_total(map_style,list_d_str,ydata, sheet, 10+days.size()+44*index, 1+5*b);//填充合計
					/*for(int d=0;d<list_d_str.size();d++){
						if(d==list_d_str.size()-1){
							sheet.getRow(10+days.size()+44*index).getCell(1+d+5*b).setCellStyle(cs_data);
						}else{
							sheet.getRow(10+days.size()+44*index).getCell(1+d+5*b).setCellStyle(cs);
						}						
						sheet.getRow(11+days.size()+44*index).getCell(1+d+5*b).setCellStyle(cs);//天數						
					}
					CellRangeAddress cra=new CellRangeAddress(11+days.size()+44*index,11+days.size()+44*index,1+5*b,5+5*b);
					sheet.addMergedRegion(cra);*/
				}
				/***********************************************合計******************************************************/
			}
		}//for b2						
	}		
	public void fillYdata(Map<String,Object> map_style,WebYieldData ydata,HSSFSheet sheet,int x,int y){
		HSSFCellStyle temp=(HSSFCellStyle)map_style.get("cs");
		if(ydata.getWorkorholiday()==null||ydata.getWorkorholiday().equals("")){
			temp=(HSSFCellStyle)map_style.get("cs_font_red");
			for(int i=0;i<5;i++){
				sheet.getRow(x).getCell(y+i).setCellValue("無數據");
			}
		}else{
			if(ydata.getWorkorholiday().equals("0")){				
				sheet.getRow(x).getCell(y).setCellValue(ydata.getOnModulus()==null?0.0:ydata.getOnModulus());
				sheet.getRow(x).getCell(y+1).setCellValue(ydata.getPersonnum()==null?0.0:ydata.getPersonnum());
				sheet.getRow(x).getCell(y+2).setCellValue(ydata.getStandardOutput()==null?0.0:ydata.getStandardOutput());
				sheet.getRow(x).getCell(y+3).setCellValue(ydata.getActualYield()==null?0.0:ydata.getActualYield());
				sheet.getRow(x).getCell(y+4).setCellValue(GlobalMethod.division(ydata.getActualYield(), ydata.getStandardOutput()));
			}
			if(ydata.getWorkorholiday().equals("1")){
				temp=(HSSFCellStyle)map_style.get("cs_font_blue");
				for(int i=0;i<5;i++){
					sheet.getRow(x).getCell(y+i).setCellValue("假日");
				}
			}
			if(ydata.getWorkorholiday().equals("2")){
				temp=(HSSFCellStyle)map_style.get("cs_font_green");
				for(int i=0;i<5;i++){
					sheet.getRow(x).getCell(y+i).setCellValue("未排產");
				}
			}
		}
		for(int i=0;i<5;i++){			
			if(ydata.getWorkorholiday()!=null&&ydata.getWorkorholiday().equals("0")){
				if(i==1){
					temp=(HSSFCellStyle)map_style.get("cs_thousand_person");
				}
				else if(i==4){
					temp=(HSSFCellStyle)map_style.get("cs_data");
				}else{
					temp=(HSSFCellStyle)map_style.get("cs_thousand") ;
				}
			}		
			sheet.getRow(x).getCell(y+i).setCellStyle(temp);
			
		}
	}
	public void fillYdata_total(Map<String,Object> map_style,List<String>list_d_str,WebYieldData ydata,HSSFSheet sheet,int x,int y){
		HSSFCellStyle temp=(HSSFCellStyle)map_style.get("cs");
		HSSFCellStyle cs_thousand=(HSSFCellStyle)map_style.get("cs_thousand");
		sheet.getRow(x).getCell(y).setCellValue(ydata.getOnModulus());
		sheet.getRow(x).getCell(y+1).setCellValue(ydata.getPersonnum());
		sheet.getRow(x).getCell(y+2).setCellValue(ydata.getStandardOutput());
		sheet.getRow(x).getCell(y+3).setCellValue(ydata.getActualYield());
		sheet.getRow(x).getCell(y+4).setCellValue(GlobalMethod.division(ydata.getActualYield(), ydata.getStandardOutput()));
		sheet.getRow(x+1).getCell(y).setCellValue(ydata.getDaycount());	
		
		for(int i=0;i<list_d_str.size();i++){
			if(i==1){
				temp=(HSSFCellStyle)map_style.get("cs_thousand_person");
			}else if(i==4){
				temp=(HSSFCellStyle)map_style.get("cs_data");
			}else{
				temp=(HSSFCellStyle)map_style.get("cs_thousand") ;
			}
			sheet.getRow(x).getCell(y+i).setCellStyle(temp);
			sheet.getRow(x+1).getCell(y+i).setCellStyle(cs_thousand);
		}
		CellRangeAddress cra=new CellRangeAddress(x+1,x+1,y,4+y);
		sheet.addMergedRegion(cra);
		
	}
	
	
	
	
	//---------------------------------------------------------------Excel2007---------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------------------------
	public XSSFWorkbook init_2007(List<String>list_months,List<WebFact>list_facts,Map<String,Object>map_a,Map<String,Object>map_b,
			List<String>list_a_str,List<String>list_b_str,List<String>list_d_str) throws IOException, ParseException{
		XSSFWorkbook wb=new XSSFWorkbook();
		Map<String,Object>map_style=this.findStyle2(wb);		
		XSSFCellStyle cs_title=(XSSFCellStyle)map_style.get("cs_title");
		XSSFSheet sheet=wb.createSheet("sheet1");
		XSSFSheet sheet2=null;		
		for(int i=0;i<50*list_months.size();i++){
			sheet.createRow(i);
			for(int j=0;j<list_facts.size()*5+1;j++){
				/*if(j>251){					
					continue;
				}*/
				sheet.getRow(i).createCell(j);
			}
		}
		/*if(list_facts.size()>49){
			sheet2=wb.createSheet("sheet2");
			for(int i=0;i<50*list_months.size();i++){
				sheet2.createRow(i);
				for(int j=0;j<list_facts.size()*5-245;j++){
					if(j>251){					
						continue;
					}
					sheet2.getRow(i).createCell(j);
				}
			}
			sheet2.setColumnWidth(0, 4500);
		}*/
		
		sheet.setColumnWidth(0, 4500);
		
		sheet.getRow(0).getCell(0).setCellValue("加久各工廠每日產量達成狀況匯總表");
		CellRangeAddress cra_title = new CellRangeAddress(0, 0, 0, 12);
		sheet.addMergedRegion(cra_title);
		for(int a=0;a<6;a++){
			sheet.getRow(0).getCell(a).setCellStyle(cs_title);
		}		
		this.init_2007(sheet,sheet2, map_style, list_facts,list_months,map_a,map_b,list_a_str,list_b_str,list_d_str);
		if(factcodelist!=null&&factcodelist.size()>0){
			this.fcodeTotal_2007(wb,map_style, factcodelist, list_months);
		}
		
		return wb;
	}
	public void init_2007(XSSFSheet sheet,XSSFSheet sheet2,Map<String,Object> map_style, List<WebFact> list_facts,List<String> list_months,Map<String,Object>map_a,Map<String,Object>map_b,
			List<String>list_a_str,List<String>list_b_str,List<String>list_d_str) throws IOException, ParseException {							
		//XSSFCellStyle cs=(XSSFCellStyle)map_style.get("cs");
		List<String>list_c_str=new ArrayList<String>();	
				
		for(int i=0;i<list_months.size();i++){
			list_c_str.clear();
			List<String>days=GlobalMethod.findDaysOfMonth(list_months.get(i),"MM/dd");
			list_c_str.clear();
			list_c_str.add("日期");
			for(String day:days){
				list_c_str.add(day);
			}
			list_c_str.add("合計");
			list_c_str.add("工作天數");			
			List<Webestproduct>list_pros=(List<Webestproduct>)map_a.get(list_months.get(i));
			List<List<WebYieldData>>list_ydatas=(List<List<WebYieldData>>)map_b.get(list_months.get(i));												
			this.init_2007(sheet,sheet2, map_style, list_facts, list_a_str, list_b_str, list_c_str,list_d_str,days,list_pros,list_ydatas,i);						
		}				
		
	}
	
	public void init_2007(XSSFSheet sheet,XSSFSheet sheet2,Map<String,Object> map_style, List<WebFact> list_facts,
			List<String>list_a_str,List<String>list_b_str,List<String>list_c_str,List<String>list_d_str,List<String>days,
			List<Webestproduct>list_pros,List<List<WebYieldData>>list_ydatas,int index){
		
		XSSFCellStyle cs=(XSSFCellStyle)map_style.get("cs");				
		XSSFCellStyle cs_thousand=(XSSFCellStyle)map_style.get("cs_thousand");
		XSSFCellStyle cs_thousand_lyellow=(XSSFCellStyle)map_style.get("cs_thousand_lyellow");	
		for(int a=0;a<list_a_str.size();a++){
			sheet.getRow(a+1+44*index).getCell(0).setCellValue(list_a_str.get(a));
			sheet.getRow(a+1+44*index).getCell(0).setCellStyle(cs);
						
			/*if(list_facts.size()>49){//if sheet2
				sheet2.getRow(a+1+44*index).getCell(0).setCellValue(list_a_str.get(a));
				sheet2.getRow(a+1+44*index).getCell(0).setCellStyle(cs);
			}//if sheet2
*/		}
		/**********************機臺孔位數***********************/
		CellRangeAddress cra_a=new CellRangeAddress(5+44*index,8+44*index,0,0);
		sheet.addMergedRegion(cra_a);
		for(int i=0;i<4;i++){
			sheet.getRow(5+44*index+i).getCell(0).setCellStyle(cs_thousand_lyellow);
		}
		
		/**********************機臺孔位數***********************/
		for(int b=0;b<list_facts.size();b++){//for b
			/*********************************sheet2**************************************************/
			/*if(b>49){// if sheet2
				Webestproduct pro=list_pros.get(b);			
				for(int i=0;i<4;i++){
					CellRangeAddress cra=new CellRangeAddress(i+1+44*index,i+1+44*index,1+5*(b-50),5+5*(b-50));
					sheet2.addMergedRegion(cra);	
					for(int j=0;j<5;j++){
						sheet2.getRow(i+1+44*index).getCell(j+1+5*(b-50)).setCellStyle(cs_thousand);
					}
				}
				sheet2.getRow(1+44*index).getCell(1+5*(b-50)).setCellValue(list_facts.get(b).getFactSname()+"_"+list_facts.get(b).getId().getFactArea());
				sheet2.getRow(2+44*index).getCell(1+5*(b-50)).setCellValue(pro.getMachinepower()==null?0:pro.getMachinepower());
				sheet2.getRow(3+44*index).getCell(1+5*(b-50)).setCellValue(pro.getEstmodel()==null?0:pro.getEstmodel());
				sheet2.getRow(4+44*index).getCell(1+5*(b-50)).setCellValue(pro.getEstpay()==null?0:pro.getEstpay());
				
				for(int i=0;i<3;i++){
					CellRangeAddress cra=new CellRangeAddress(5+44*index,8+44*index,i+5*(b-50),i+5*(b-50));
					sheet2.addMergedRegion(cra);	
					for(int j=0;j<4;j++){
						sheet2.getRow(5+j+44*index).getCell(i+5*(b-50)).setCellStyle(cs_thousand_lyellow);
					}
				}
				sheet2.getRow(5+44*index).getCell(1+5*(b-50)).setCellValue("總機孔");
				sheet2.getRow(5+44*index).getCell(2+5*(b-50)).setCellValue(pro.getTotalhole()==null?0:pro.getTotalhole());
				for(int i=0;i<list_b_str.size();i++){
					sheet2.getRow(5+i+44*index).getCell(3+5*(b-50)).setCellValue(list_b_str.get(i));
					sheet2.getRow(5+i+44*index).getCell(3+5*(b-50)).setCellStyle(cs_thousand_lyellow);
					CellRangeAddress cra=new CellRangeAddress(5+i+44*index,5+i+44*index,4+5*(b-50),5+5*(b-50));
					sheet2.addMergedRegion(cra);
					for(int j=0;j<2;j++){
						sheet2.getRow(5+i+44*index).getCell(4+j+5*(b-50)).setCellStyle(cs_thousand_lyellow);
					}
					sheet2.getRow(5+i+44*index).getCell(4+5*(b-50)).setCellStyle(cs_thousand_lyellow);
				}
				sheet2.getRow(5+44*index).getCell(4+5*(b-50)).setCellValue(pro.getHole()==null?0:pro.getHole());
				sheet2.getRow(6+44*index).getCell(4+5*(b-50)).setCellValue(pro.getSample()==null?0:pro.getSample());
				sheet2.getRow(7+44*index).getCell(4+5*(b-50)).setCellValue(pro.getAccessories()==null?0:pro.getAccessories());
				sheet2.getRow(8+44*index).getCell(4+5*(b-50)).setCellValue(pro.getOther()==null?0:pro.getOther());
				continue;
			}//if sheet2
*/			/*********************************sheet2**************************************************/
			
			Webestproduct pro=list_pros.get(b);			
			for(int i=0;i<4;i++){
				CellRangeAddress cra=new CellRangeAddress(i+1+44*index,i+1+44*index,1+5*b,5+5*b);
				sheet.addMergedRegion(cra);	
				for(int j=0;j<5;j++){
					sheet.getRow(i+1+44*index).getCell(j+1+5*b).setCellStyle(cs_thousand);
				}
			}
			sheet.getRow(1+44*index).getCell(1+5*b).setCellValue(list_facts.get(b).getFactSname()+"_"+list_facts.get(b).getId().getFactArea());
			sheet.getRow(2+44*index).getCell(1+5*b).setCellValue(pro.getMachinepower()==null?0:pro.getMachinepower());
			sheet.getRow(3+44*index).getCell(1+5*b).setCellValue(pro.getEstmodel()==null?0:pro.getEstmodel());
			sheet.getRow(4+44*index).getCell(1+5*b).setCellValue(pro.getEstpay()==null?0:pro.getEstpay());
			
			for(int i=1;i<3;i++){
				CellRangeAddress cra=new CellRangeAddress(5+44*index,8+44*index,i+5*b,i+5*b);
				sheet.addMergedRegion(cra);	
				for(int j=0;j<4;j++){
					sheet.getRow(5+j+44*index).getCell(i+5*b).setCellStyle(cs_thousand_lyellow);
				}
			}
			sheet.getRow(5+44*index).getCell(1+5*b).setCellValue("總機孔");
			sheet.getRow(5+44*index).getCell(2+5*b).setCellValue(pro.getTotalhole()==null?0:pro.getTotalhole());
			for(int i=0;i<list_b_str.size();i++){
				sheet.getRow(5+i+44*index).getCell(3+5*b).setCellValue(list_b_str.get(i));
				sheet.getRow(5+i+44*index).getCell(3+5*b).setCellStyle(cs_thousand_lyellow);
				CellRangeAddress cra=new CellRangeAddress(5+i+44*index,5+i+44*index,4+5*b,5+5*b);
				sheet.addMergedRegion(cra);
				for(int j=0;j<2;j++){
					sheet.getRow(5+i+44*index).getCell(4+j+5*b).setCellStyle(cs_thousand_lyellow);
				}
				//sheet.getRow(5+i+44*index).getCell(4+5*b).setCellStyle(cs_thousand_lyellow);
			}
			sheet.getRow(5+44*index).getCell(4+5*b).setCellValue(pro.getHole()==null?0:pro.getHole());
			sheet.getRow(6+44*index).getCell(4+5*b).setCellValue(pro.getSample()==null?0:pro.getSample());
			sheet.getRow(7+44*index).getCell(4+5*b).setCellValue(pro.getAccessories()==null?0:pro.getAccessories());
			sheet.getRow(8+44*index).getCell(4+5*b).setCellValue(pro.getOther()==null?0:pro.getOther());
		}//for b
		
		
		
		
		//--------------------------------------------------------------------下------------------------------------------------------------------
		for(int i=0;i<list_c_str.size();i++){
			sheet.getRow(9+i+44*index).getCell(0).setCellValue(list_c_str.get(i));
			sheet.getRow(9+i+44*index).getCell(0).setCellStyle(cs);
			
			/*if(list_facts.size()>49){//if sheet2
				sheet2.getRow(9+i+44*index).getCell(0).setCellValue(list_c_str.get(i));
				sheet2.getRow(9+i+44*index).getCell(0).setCellStyle(cs);
			}//if sheet2
*/		}						
		for(int b=0;b<list_facts.size();b++){//for b2
			WebYieldData ydata=new WebYieldData(0.0,0.0,0.0,0.0,0.0);//合計
			
			/*********************************sheet2**************************************************/
			/*if(b>49){//if sheet2
				for(int c=0;c<list_d_str.size();c++){
					sheet2.getRow(9+44*index).getCell(1+c+5*(b-50)).setCellValue(list_d_str.get(c));
					sheet2.getRow(9+44*index).getCell(1+c+5*(b-50)).setCellStyle(cs);
				}
				List<WebYieldData>list_ydata=list_ydatas.get(b);						
				for(int c=0;c<days.size();c++){
					if(c>list_ydata.size()-1){
						for(int i=0;i<5;i++){
							sheet2.getRow(10+c+44*index).getCell(1+5*(b-50)+i).setCellStyle(cs);//填充超出今天空的部分
						}
						continue;
					}
					this.fillYdata_2007(map_style,list_ydata.get(c), sheet2, 10+c+44*index, 1+5*(b-50));	//sheet2下表填充數據									
					*//***********************************************合計******************************************************//*
					this.getTotal(ydata, list_ydata.get(c).getOnModulus()==null?0:list_ydata.get(c).getOnModulus(),
							      list_ydata.get(c).getPersonnum()==null?0:list_ydata.get(c).getPersonnum(),
								  list_ydata.get(c).getStandardOutput()==null?0:list_ydata.get(c).getStandardOutput(), 
								  list_ydata.get(c).getActualYield()==null?0:list_ydata.get(c).getActualYield(),
								  list_ydata.get(c).getDaycount()==null?0:list_ydata.get(c).getDaycount());
					
					if(c==list_ydata.size()-1){
						this.fillYdata_total_2007(map_style,list_d_str,ydata, sheet2, 10+days.size()+44*index, 1+5*(b-50));//sheet2下表填充合計						
					}
					*//***********************************************合計******************************************************//*
				}
				continue;
			}//if sheet2
*/			/*********************************sheet2**************************************************/
			
			for(int c=0;c<list_d_str.size();c++){
				sheet.getRow(9+44*index).getCell(1+c+5*b).setCellValue(list_d_str.get(c));
				sheet.getRow(9+44*index).getCell(1+c+5*b).setCellStyle(cs);
			}
			List<WebYieldData>list_ydata=list_ydatas.get(b);						
			for(int c=0;c<days.size();c++){
				if(c>list_ydata.size()-1){
					for(int i=0;i<5;i++){
						sheet.getRow(10+c+44*index).getCell(1+5*b+i).setCellStyle(cs);//填充超出今天空的部分
					}					
					continue;
				}
				this.fillYdata_2007(map_style,list_ydata.get(c), sheet, 10+c+44*index, 1+5*b);//下表填充數據
				
				/***********************************************合計******************************************************/
				this.getTotal(ydata, list_ydata.get(c).getOnModulus()==null?0:list_ydata.get(c).getOnModulus(),
						      list_ydata.get(c).getPersonnum()==null?0:list_ydata.get(c).getPersonnum(),
							  list_ydata.get(c).getStandardOutput()==null?0:list_ydata.get(c).getStandardOutput(), 
							  list_ydata.get(c).getActualYield()==null?0:list_ydata.get(c).getActualYield(),
							  list_ydata.get(c).getDaycount()==null?0:list_ydata.get(c).getDaycount());
				
				if(c==list_ydata.size()-1){
					this.fillYdata_total_2007(map_style,list_d_str,ydata, sheet, 10+days.size()+44*index, 1+5*b);//填充合計
				}
				/***********************************************合計******************************************************/
			}
		}//for b2						
	}		
	public void fillYdata_2007(Map<String,Object> map_style,WebYieldData ydata,XSSFSheet sheet,int x,int y){
		XSSFCellStyle temp=(XSSFCellStyle)map_style.get("cs");
		if(ydata.getWorkorholiday()==null||ydata.getWorkorholiday().equals("")){
			temp=(XSSFCellStyle)map_style.get("cs_font_red");
			for(int i=0;i<5;i++){
				sheet.getRow(x).getCell(y+i).setCellValue("無數據");
			}
		}else{
			if(ydata.getWorkorholiday().equals("0")){				
				sheet.getRow(x).getCell(y).setCellValue(ydata.getOnModulus());
				sheet.getRow(x).getCell(y+1).setCellValue(ydata.getPersonnum());
				sheet.getRow(x).getCell(y+2).setCellValue(ydata.getStandardOutput());
				sheet.getRow(x).getCell(y+3).setCellValue(ydata.getActualYield());
				sheet.getRow(x).getCell(y+4).setCellValue(GlobalMethod.division(ydata.getActualYield(), ydata.getStandardOutput()));
			}
			if(ydata.getWorkorholiday().equals("1")){
				temp=(XSSFCellStyle)map_style.get("cs_font_blue");
				for(int i=0;i<5;i++){
					sheet.getRow(x).getCell(y+i).setCellValue("假日");
				}
			}
			if(ydata.getWorkorholiday().equals("2")){
				temp=(XSSFCellStyle)map_style.get("cs_font_green");
				for(int i=0;i<5;i++){
					sheet.getRow(x).getCell(y+i).setCellValue("未排產");
				}
			}
		}
		for(int i=0;i<5;i++){			
			if(ydata.getWorkorholiday()!=null&&ydata.getWorkorholiday().equals("0")){
				if(i==1){
					temp=(XSSFCellStyle)map_style.get("cs_thousand_person");
				}
				else if(i==4){
					temp=(XSSFCellStyle)map_style.get("cs_data");
				}else{
					temp=(XSSFCellStyle)map_style.get("cs_thousand") ;
				}
			}		
			sheet.getRow(x).getCell(y+i).setCellStyle(temp);
			
		}
	}
	public void fillYdata_total_2007(Map<String,Object> map_style,List<String>list_d_str,WebYieldData ydata,XSSFSheet sheet,int x,int y){
		XSSFCellStyle temp=(XSSFCellStyle)map_style.get("cs");
		XSSFCellStyle cs_thousand=(XSSFCellStyle)map_style.get("cs_thousand");
		sheet.getRow(x).getCell(y).setCellValue(ydata.getOnModulus());
		sheet.getRow(x).getCell(y+1).setCellValue(ydata.getPersonnum());
		sheet.getRow(x).getCell(y+2).setCellValue(ydata.getStandardOutput());
		sheet.getRow(x).getCell(y+3).setCellValue(ydata.getActualYield());
		sheet.getRow(x).getCell(y+4).setCellValue(GlobalMethod.division(ydata.getActualYield(), ydata.getStandardOutput()));
		sheet.getRow(x+1).getCell(y).setCellValue(ydata.getDaycount());	
		
		for(int i=0;i<list_d_str.size();i++){
			if(i==1){
				temp=(XSSFCellStyle)map_style.get("cs_thousand_person");
			}else if(i==4){
				temp=(XSSFCellStyle)map_style.get("cs_data");
			}else{
				temp=(XSSFCellStyle)map_style.get("cs_thousand") ;
			}
			sheet.getRow(x).getCell(y+i).setCellStyle(temp);
			sheet.getRow(x+1).getCell(y+i).setCellStyle(cs_thousand);
		}
		CellRangeAddress cra=new CellRangeAddress(x+1,x+1,y,4+y);
		sheet.addMergedRegion(cra);
		
	}
	
	
	public void fcodeTotal(HSSFWorkbook wb,Map<String,Object>map_style,List<String>factcodes,List<String>months) throws ParseException{
		List<String>list_head2=new ArrayList<String>();
		HSSFCellStyle cs_font_bgyellow=(HSSFCellStyle)map_style.get("cs_font_bgyellow");
		HSSFCellStyle cs_thousand=(HSSFCellStyle)map_style.get("cs_thousand");
		list_head2.add("標準產量");
		list_head2.add("實際產量");
		
			HSSFSheet sheet=wb.createSheet("統計");
			sheet.setColumnWidth(0, 4500);
			/***********************初始化行，列*************************/
			for(int a=0;a<35*(1+months.size());a++){
				sheet.createRow(a);
				for(int b=0;b<factcodes.size()*list_head2.size()+1;b++){
					sheet.getRow(a).createCell(b);
				}
			}
			/***********************初始化行，列*************************/
			int index=0;
			for(int a=0;a<months.size();a++){//for a
				index=35*a;
				List<String>days=GlobalMethod.findDaysOfMonth(months.get(a),"yyyyMMdd");
				List<String>list_head=GlobalMethod.findDaysOfMonth(months.get(a),"MM/dd");				
				list_head.add("合計");
				List<List<VWebydatabyfcode>>list_a=new ArrayList<List<VWebydatabyfcode>>();
				List<VWebydatabyfcode>list_ydatas2=dataSer.findByYymm2(months.get(a));
				for(String factcode:factcodes){//for b
					List<VWebydatabyfcode>list_a1=new ArrayList<VWebydatabyfcode>();
					for(String day:days){
						list_a1.add(new VWebydatabyfcode(new VWebydatabyfcodeId(factcode,day),new BigDecimal(0.0),new BigDecimal(0.0)));
					}
					list_a.add(list_a1);
				}//for b
				for(List<VWebydatabyfcode>list_a1:list_a){//for b2
					for(int i=0;i<list_a1.size();i++){
						VWebydatabyfcode obj=list_a1.get(i);
						for(VWebydatabyfcode obj2:list_ydatas2){
							if(obj.getId().getFactCode().equals(obj2.getId().getFactCode())&&
							   obj.getId().getYymmdd().equals(obj2.getId().getYymmdd())){
								list_a1.remove(i);
								list_a1.add(i,obj2);
							}
						}
					}
				}//for b2
				
				/********************************填充表格********************************************/
				sheet.getRow(0+index).getCell(0).setCellValue("日期");
				CellRangeAddress cra_date=new CellRangeAddress(0+index,1+index,0,0);
				sheet.addMergedRegion(cra_date);
				for(int i=0;i<2;i++){
					sheet.getRow(i+index).getCell(0).setCellStyle(cs_font_bgyellow);
				}
				for(int b=0;b<list_head.size();b++){
					sheet.getRow(b+2+index).getCell(0).setCellValue(list_head.get(b));
					sheet.getRow(b+2+index).getCell(0).setCellStyle(cs_font_bgyellow);
				}
				
				
				for(int b=0;b<factcodes.size();b++){
					sheet.getRow(0+index).getCell(1+list_head2.size()*b).setCellValue(factcodes.get(b));
					CellRangeAddress cra=new CellRangeAddress(0+index,0+index,1+list_head2.size()*b,2+list_head2.size()*b);
					sheet.addMergedRegion(cra);
					for(int c=0;c<list_head2.size();c++){
						sheet.getRow(0+index).getCell(c+1+list_head2.size()*b).setCellStyle(cs_font_bgyellow);
						sheet.getRow(1+index).getCell(c+1+list_head2.size()*b).setCellValue(list_head2.get(c));
						sheet.getRow(1+index).getCell(c+1+list_head2.size()*b).setCellStyle(cs_font_bgyellow);
					}					
				}
								
				for(int b=0;b<list_a.size();b++){
					Double sum_StandardOutput=0.0;
					Double sum_ActualYield=0.0;
					List<VWebydatabyfcode>list_a1=list_a.get(b);
					for(int c=0;c<list_a1.size();c++){
						sheet.getRow(2+c+index).getCell(1+list_head2.size()*b).setCellValue(list_a1.get(c).getStandardOutput().doubleValue());
						sheet.getRow(2+c+index).getCell(2+list_head2.size()*b).setCellValue(list_a1.get(c).getActualYield().doubleValue());
						sum_StandardOutput=sum_StandardOutput+list_a1.get(c).getStandardOutput().doubleValue();
						sum_ActualYield=sum_ActualYield+list_a1.get(c).getActualYield().doubleValue();
                        if(c==list_a1.size()-1){
                        	sheet.getRow(3+c+index).getCell(1+list_head2.size()*b).setCellValue(sum_StandardOutput);
    						sheet.getRow(3+c+index).getCell(2+list_head2.size()*b).setCellValue(sum_ActualYield);
    						sum_StandardOutput=null;
    						sum_ActualYield=null;
						}
						for(int d=0;d<list_head2.size();d++){
							sheet.getRow(2+c+index).getCell(1+d+list_head2.size()*b).setCellStyle(cs_thousand);
							sheet.getRow(2+c+index).getCell(1+d+list_head2.size()*b).setCellStyle(cs_thousand);
							if(c==list_a1.size()-1){
								sheet.getRow(3+c+index).getCell(1+d+list_head2.size()*b).setCellStyle(cs_thousand);
								sheet.getRow(3+c+index).getCell(1+d+list_head2.size()*b).setCellStyle(cs_thousand);
							}
						}
						
					}
				}
				/********************************填充表格********************************************/											
			}//for a												
	}
	public void fcodeTotal_2007(XSSFWorkbook wb,Map<String,Object>map_style,List<String>factcodes,List<String>months) throws ParseException{
		List<String>list_head2=new ArrayList<String>();
		XSSFCellStyle cs_font_bgyellow=(XSSFCellStyle)map_style.get("cs_font_bgyellow");
		XSSFCellStyle cs_thousand=(XSSFCellStyle)map_style.get("cs_thousand");
		list_head2.add("標準產量");
		list_head2.add("實際產量");
		
			XSSFSheet sheet=wb.createSheet("統計");
			sheet.setColumnWidth(0, 4500);
			/***********************初始化行，列*************************/
			for(int a=0;a<35*(1+months.size());a++){
				sheet.createRow(a);
				for(int b=0;b<factcodes.size()*list_head2.size()+1;b++){
					sheet.getRow(a).createCell(b);
				}
			}
			/***********************初始化行，列*************************/
			int index=0;
			for(int a=0;a<months.size();a++){//for a
				index=35*a;
				List<String>days=GlobalMethod.findDaysOfMonth(months.get(a),"yyyyMMdd");
				List<String>list_head=GlobalMethod.findDaysOfMonth(months.get(a),"MM/dd");				
				list_head.add("合計");
				List<List<VWebydatabyfcode>>list_a=new ArrayList<List<VWebydatabyfcode>>();
				List<VWebydatabyfcode>list_ydatas2=dataSer.findByYymm2(months.get(a));
				for(String factcode:factcodes){//for b
					List<VWebydatabyfcode>list_a1=new ArrayList<VWebydatabyfcode>();
					for(String day:days){
						list_a1.add(new VWebydatabyfcode(new VWebydatabyfcodeId(factcode,day),new BigDecimal(0.0),new BigDecimal(0.0)));
					}
					list_a.add(list_a1);
				}//for b
				for(List<VWebydatabyfcode>list_a1:list_a){//for b2
					for(int i=0;i<list_a1.size();i++){
						VWebydatabyfcode obj=list_a1.get(i);
						for(VWebydatabyfcode obj2:list_ydatas2){
							if(obj.getId().getFactCode().equals(obj2.getId().getFactCode())&&
							   obj.getId().getYymmdd().equals(obj2.getId().getYymmdd())){
								list_a1.remove(i);
								list_a1.add(i,obj2);
							}
						}
					}
				}//for b2
				
				/********************************填充表格********************************************/
				sheet.getRow(0+index).getCell(0).setCellValue("日期");
				CellRangeAddress cra_date=new CellRangeAddress(0+index,1+index,0,0);
				sheet.addMergedRegion(cra_date);
				for(int i=0;i<2;i++){
					sheet.getRow(i+index).getCell(0).setCellStyle(cs_font_bgyellow);
				}
				for(int b=0;b<list_head.size();b++){
					sheet.getRow(b+2+index).getCell(0).setCellValue(list_head.get(b));
					sheet.getRow(b+2+index).getCell(0).setCellStyle(cs_font_bgyellow);
				}
				
				
				for(int b=0;b<factcodes.size();b++){
					sheet.getRow(0+index).getCell(1+list_head2.size()*b).setCellValue(factcodes.get(b));
					CellRangeAddress cra=new CellRangeAddress(0+index,0+index,1+list_head2.size()*b,2+list_head2.size()*b);
					sheet.addMergedRegion(cra);
					for(int c=0;c<list_head2.size();c++){
						sheet.getRow(0+index).getCell(c+1+list_head2.size()*b).setCellStyle(cs_font_bgyellow);
						sheet.getRow(1+index).getCell(c+1+list_head2.size()*b).setCellValue(list_head2.get(c));
						sheet.getRow(1+index).getCell(c+1+list_head2.size()*b).setCellStyle(cs_font_bgyellow);
					}					
				}
								
				for(int b=0;b<list_a.size();b++){
					Double sum_StandardOutput=0.0;
					Double sum_ActualYield=0.0;
					List<VWebydatabyfcode>list_a1=list_a.get(b);
					for(int c=0;c<list_a1.size();c++){
						sheet.getRow(2+c+index).getCell(1+list_head2.size()*b).setCellValue(list_a1.get(c).getStandardOutput().doubleValue());
						sheet.getRow(2+c+index).getCell(2+list_head2.size()*b).setCellValue(list_a1.get(c).getActualYield().doubleValue());
						sum_StandardOutput=sum_StandardOutput+list_a1.get(c).getStandardOutput().doubleValue();
						sum_ActualYield=sum_ActualYield+list_a1.get(c).getActualYield().doubleValue();
                        if(c==list_a1.size()-1){
                        	sheet.getRow(3+c+index).getCell(1+list_head2.size()*b).setCellValue(sum_StandardOutput);
    						sheet.getRow(3+c+index).getCell(2+list_head2.size()*b).setCellValue(sum_ActualYield);
    						sum_StandardOutput=null;
    						sum_ActualYield=null;
						}
						for(int d=0;d<list_head2.size();d++){
							sheet.getRow(2+c+index).getCell(1+d+list_head2.size()*b).setCellStyle(cs_thousand);
							sheet.getRow(2+c+index).getCell(1+d+list_head2.size()*b).setCellStyle(cs_thousand);
							if(c==list_a1.size()-1){
								sheet.getRow(3+c+index).getCell(1+d+list_head2.size()*b).setCellStyle(cs_thousand);
								sheet.getRow(3+c+index).getCell(1+d+list_head2.size()*b).setCellStyle(cs_thousand);
							}
						}
						
					}
				}
				/********************************填充表格********************************************/											
			}//for a												
	}
	
	public WebYieldData getTotal(WebYieldData ydata,Double onModulus,Double personnum,Double standardOutput,Double actualYield,Double daycount){
		ydata.setOnModulus(ydata.getOnModulus()+onModulus);
		ydata.setPersonnum(ydata.getPersonnum()+personnum);
		ydata.setStandardOutput(ydata.getStandardOutput()+standardOutput);
		ydata.setActualYield(ydata.getActualYield()+actualYield);
		ydata.setDaycount(ydata.getDaycount()+daycount);
		return ydata;
	}
	public Map<String,Object>findStyles(HSSFWorkbook wb){
		Map<String,Object>map=new HashMap<String,Object>();
		// 單元格樣式
		HSSFCellStyle cs = wb.createCellStyle();
		cs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		map.put("cs", cs);

		// 淺黃色背景單元格
		HSSFCellStyle cs_lyellow = wb.createCellStyle();
		cs_lyellow.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_lyellow.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_lyellow.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_lyellow.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_lyellow.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_lyellow.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_lyellow.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());				
		cs_lyellow.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		map.put("cs_lyellow", cs_lyellow);

		// 设置字体:
		// 紅字體
		HSSFCellStyle cs_font_red = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 10);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		font.setColor(HSSFFont.COLOR_RED);
		cs_font_red.setFont(font);
		cs_font_red.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_font_red.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_font_red.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_font_red.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_font_red.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_font_red.setBorderTop(HSSFCellStyle.BORDER_THIN);
		map.put("cs_font_red", cs_font_red);

		// 藍字體
		HSSFCellStyle cs_font_blue = wb.createCellStyle();
		HSSFFont font_blue = wb.createFont();
		font_blue.setFontHeightInPoints((short) 10);
		font_blue.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		font_blue.setColor(IndexedColors.BLUE.getIndex());
		cs_font_blue.setFont(font_blue);
		cs_font_blue.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_font_blue.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);				
		cs_font_blue.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_font_blue.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_font_blue.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_font_blue.setBorderTop(HSSFCellStyle.BORDER_THIN);
		map.put("cs_font_blue", cs_font_blue);

		// 綠字體
		HSSFCellStyle cs_font_green = wb.createCellStyle();
		HSSFFont font_green = wb.createFont();
		font_green.setFontHeightInPoints((short) 10);
		font_green.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		font_green.setColor(IndexedColors.GREEN.getIndex());
		cs_font_green.setFont(font_green);
		cs_font_green.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_font_green.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);			
		cs_font_green.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_font_green.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_font_green.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_font_green.setBorderTop(HSSFCellStyle.BORDER_THIN);
		map.put("cs_font_green", cs_font_green);

		// 粗字體
		HSSFCellStyle cs_font = wb.createCellStyle();
		HSSFFont font_bold = wb.createFont();
		font_bold.setFontHeightInPoints((short) 10);
		font_bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		cs_font.setFont(font_bold);
		cs_font.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_font.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_font.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_font.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_font.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_font.setBorderTop(HSSFCellStyle.BORDER_THIN);
		map.put("cs_font", cs_font);

		// 標題字體
		HSSFCellStyle cs_title = wb.createCellStyle();
		HSSFFont font_title = wb.createFont();
		font_title.setFontHeightInPoints((short) 20);
		font_title.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示		
		cs_title.setFont(font_title);
		cs_title.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_title.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		
		map.put("cs_title", cs_title);
		
		// 紅粗字體
		HSSFCellStyle cs_font_red_bold = wb.createCellStyle();
		HSSFFont font_red_bold = wb.createFont();
		font_red_bold.setFontHeightInPoints((short) 10);
		font_red_bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		font_red_bold.setColor(HSSFFont.COLOR_RED);
		cs_font_red_bold.setFont(font_red_bold);
		cs_font_red_bold.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_font_red_bold.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);				
		cs_font_red_bold.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_font_red_bold.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_font_red_bold.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_font_red_bold.setBorderTop(HSSFCellStyle.BORDER_THIN);
		map.put("cs_font_red_bold", cs_font_red_bold);

		// 粗字體和背景顏色
		HSSFCellStyle cs_font_bgyellow = wb.createCellStyle();
		HSSFFont font_bold_bgyellow = wb.createFont();
		font_bold_bgyellow.setFontHeightInPoints((short) 10);
		font_bold_bgyellow.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		cs_font_bgyellow.setFont(font_bold_bgyellow);
		cs_font_bgyellow.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_font_bgyellow.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);				
		cs_font_bgyellow.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_font_bgyellow.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_font_bgyellow.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_font_bgyellow.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_font_bgyellow.setFillForegroundColor(IndexedColors.YELLOW.getIndex());				
		cs_font_bgyellow.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		map.put("cs_font_bgyellow", cs_font_bgyellow);
		
		// 數字格式
		HSSFDataFormat format = wb.createDataFormat();

		HSSFCellStyle cs_data = wb.createCellStyle();
		cs_data.setDataFormat(format.getFormat("0.0%"));
		cs_data.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_data.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_data.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_data.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_data.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_data.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		map.put("cs_data", cs_data);

		HSSFCellStyle cs_thousand = wb.createCellStyle();
		cs_thousand.setDataFormat(format.getFormat("#,##0.0"));
		cs_thousand.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_thousand.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_thousand.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_thousand.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_thousand.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_thousand.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		map.put("cs_thousand", cs_thousand);

		// 淺黃背景顏色
		HSSFCellStyle cs_thousand_lyellow = wb.createCellStyle();
		cs_thousand_lyellow.setDataFormat(format.getFormat("#,##0.0"));
		cs_thousand_lyellow.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_thousand_lyellow.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_thousand_lyellow.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_thousand_lyellow.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_thousand_lyellow.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_thousand_lyellow.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);			
		cs_thousand_lyellow.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());										
		cs_thousand_lyellow.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		map.put("cs_thousand_lyellow", cs_thousand_lyellow);
				
		HSSFCellStyle cs_thousand_person = wb.createCellStyle();
		cs_thousand_person.setDataFormat(format.getFormat("#,##0"));
		cs_thousand_person.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_thousand_person.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_thousand_person.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_thousand_person.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_thousand_person.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_thousand_person.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);	
		map.put("cs_thousand_person", cs_thousand_person);
		
		
		return map;
	}		
	public Map<String,Object>findStyle2(XSSFWorkbook wb){
		Map<String,Object>map=new HashMap<String,Object>();
		// 單元格樣式
		XSSFCellStyle cs = wb.createCellStyle();
		cs.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		map.put("cs", cs);
		// 设置字体:
		// 紅字體
		XSSFCellStyle cs_font_red = wb.createCellStyle();
		XSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 10);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
		font.setColor(XSSFFont.COLOR_RED);
		cs_font_red.setFont(font);
		cs_font_red.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_font_red.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_font_red.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_font_red.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_font_red.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_font_red.setBorderTop(XSSFCellStyle.BORDER_THIN);
		map.put("cs_font_red", cs_font_red);

		// 藍字體
		XSSFCellStyle cs_font_blue = wb.createCellStyle();
		XSSFFont font_blue = wb.createFont();
		font_blue.setFontHeightInPoints((short) 10);
		font_blue.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
		font_blue.setColor(IndexedColors.BLUE.getIndex());
		cs_font_blue.setFont(font_blue);
		cs_font_blue.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_font_blue.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);				
		cs_font_blue.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_font_blue.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_font_blue.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_font_blue.setBorderTop(XSSFCellStyle.BORDER_THIN);
		map.put("cs_font_blue", cs_font_blue);

		// 綠字體
		XSSFCellStyle cs_font_green = wb.createCellStyle();
		XSSFFont font_green = wb.createFont();
		font_green.setFontHeightInPoints((short) 10);
		font_green.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
		font_green.setColor(IndexedColors.GREEN.getIndex());
		cs_font_green.setFont(font_green);
		cs_font_green.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_font_green.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);				
		cs_font_green.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_font_green.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_font_green.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_font_green.setBorderTop(XSSFCellStyle.BORDER_THIN);
		map.put("cs_font_green", cs_font_green);

		// 粗字體
		XSSFCellStyle cs_font = wb.createCellStyle();
		XSSFFont font_bold = wb.createFont();
		font_bold.setFontHeightInPoints((short) 10);
		font_bold.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		cs_font.setFont(font_bold);
		cs_font.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_font.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_font.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_font.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_font.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_font.setBorderTop(XSSFCellStyle.BORDER_THIN);
		map.put("cs_font", cs_font);

		// 標題字體
		XSSFCellStyle cs_title = wb.createCellStyle();
		XSSFFont font_title = wb.createFont();
		font_title.setFontHeightInPoints((short) 20);
		font_title.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		cs_title.setFont(font_title);
		cs_title.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_title.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_title.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_title.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_title.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_title.setBorderTop(XSSFCellStyle.BORDER_THIN);
		map.put("cs_title", cs_title);

		// 粗字體和黃色背景
		XSSFCellStyle cs_font_bgyellow = wb.createCellStyle();
		XSSFFont font_bold_bgyellow = wb.createFont();
		font_bold_bgyellow.setFontHeightInPoints((short) 10);
		font_bold_bgyellow.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		cs_font_bgyellow.setFont(font_bold_bgyellow);
		cs_font_bgyellow.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_font_bgyellow.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);				
		cs_font_bgyellow.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_font_bgyellow.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_font_bgyellow.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_font_bgyellow.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_font_bgyellow.setFillForegroundColor(IndexedColors.YELLOW.getIndex());				
		cs_font_bgyellow.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		map.put("cs_font_bgyellow", cs_font_bgyellow);

		// 粗字體和淺黃色背景
		XSSFCellStyle cs_font_bgyellow2 = wb.createCellStyle();
		XSSFFont font_bold_bgyellow2 = wb.createFont();
		font_bold_bgyellow2.setFontHeightInPoints((short) 10);
		font_bold_bgyellow2.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);// 粗体显示
		cs_font_bgyellow2.setFont(font_bold_bgyellow2);
		cs_font_bgyellow2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_font_bgyellow2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);				
		cs_font_bgyellow2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_font_bgyellow2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_font_bgyellow2.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_font_bgyellow2.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_font_bgyellow2.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());										
		cs_font_bgyellow2.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		map.put("cs_font_bgyellow2", cs_font_bgyellow2);
				

		// 紅粗字體
		XSSFCellStyle cs_font_red_bold = wb.createCellStyle();
		XSSFFont font_red_bold = wb.createFont();
		font_red_bold.setFontHeightInPoints((short) 10);
		font_red_bold.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		font_red_bold.setColor(XSSFFont.COLOR_RED);
		cs_font_red_bold.setFont(font_red_bold);
		cs_font_red_bold.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_font_red_bold.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);				
		cs_font_red_bold.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_font_red_bold.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_font_red_bold.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_font_red_bold.setBorderTop(XSSFCellStyle.BORDER_THIN);
		map.put("cs_font_red_bold", cs_font_red_bold);
		
		// 數字格式
		XSSFDataFormat format = wb.createDataFormat();
		XSSFCellStyle cs_data = wb.createCellStyle();
		cs_data.setDataFormat(format.getFormat("0.0%"));
		cs_data.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_data.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_data.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_data.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_data.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_data.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		map.put("cs_data", cs_data);

		XSSFCellStyle cs_thousand = wb.createCellStyle();
		cs_thousand.setDataFormat(format.getFormat("#,##0.0"));
		cs_thousand.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_thousand.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_thousand.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_thousand.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_thousand.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_thousand.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		map.put("cs_thousand", cs_thousand);

		// 帶黃背景顏色
		XSSFCellStyle cs_thousand_lyellow = wb.createCellStyle();
		cs_thousand_lyellow.setDataFormat(format.getFormat("#,##0.0"));
		cs_thousand_lyellow.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_thousand_lyellow.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_thousand_lyellow.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_thousand_lyellow.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_thousand_lyellow.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_thousand_lyellow.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);				
		cs_thousand_lyellow.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());										
		cs_thousand_lyellow.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		map.put("cs_thousand_lyellow", cs_thousand_lyellow);
		
		XSSFCellStyle cs_thousand_person = wb.createCellStyle();
		cs_thousand_person.setDataFormat(format.getFormat("#,##0"));
		cs_thousand_person.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_thousand_person.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_thousand_person.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_thousand_person.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_thousand_person.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_thousand_person.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		map.put("cs_thousand_person", cs_thousand_person);
		return map;				
	}
	
	
	/**
	 * 
	 * @Title: dayTotal
	 * @Description: 各箇欄位數據統計
	 * @param @param map_a
	 * @param @param map_b
	 * @return void
	 * @throws
	 * @author web
	 * @date 2016/10/6
	 */
	public void dayTotal(Map<String,Object>map_a,Map<String,Object>map_b){
		for(String month:map_a.keySet()){
			Webestproduct pro=new Webestproduct();
			Double machinepower=0.0;
			Double estmodel=0.0;
			Double estpay=0.0;
			Double totalhole=0.0;
			Double hole=0.0;
			Double sample=0.0;
			Double accessories=0.0;
			Double other=0.0;					
			List<Webestproduct>list_a=(List<Webestproduct>)map_a.get(month);
			List<List<WebYieldData>>list_b=(List<List<WebYieldData>>)map_b.get(month);
			for(Webestproduct obj:list_a){
				machinepower=machinepower+(obj.getMachinepower()==null?0.0:obj.getMachinepower());
				estmodel=estmodel+(obj.getEstmodel()==null?0.0:obj.getEstmodel());
				estpay=estpay+(obj.getEstpay()==null?0.0:obj.getEstpay());
				totalhole=totalhole+(obj.getTotalhole()==null?0.0:obj.getTotalhole());
				hole=hole+(obj.getHole()==null?0.0:obj.getHole());
				sample=sample+(obj.getSample()==null?0.0:obj.getSample());
				accessories=accessories+(obj.getAccessories()==null?0.0:obj.getAccessories());
				other=other+(obj.getOther()==null?0.0:obj.getOther());
			}
			pro.setMachinepower(machinepower);
			pro.setEstmodel(estmodel);
			pro.setEstpay(estpay);
			pro.setTotalhole(totalhole);
			pro.setHole(hole);
			pro.setSample(sample);
			pro.setAccessories(accessories);
			pro.setOther(other);
			list_a.add(pro);
			
			
			
			List<WebYieldData>list_ydata=new ArrayList<WebYieldData>();
			List<WebYieldData>list1=list_b.get(0);
			for(int i=0;i<list1.size();i++){
				Double onModulus=0.0;
				Double personnum=0.0;
				Double standardOutput=0.0;
				Double actualYield=0.0;
				Double daycount=0.0;
				WebYieldData ydata=new WebYieldData();
				for(List<WebYieldData> list2:list_b){
					
						onModulus=onModulus+(list2.get(i).getOnModulus()==null?0.0:list2.get(i).getOnModulus());
						personnum=personnum+(list2.get(i).getPersonnum()==null?0.0:list2.get(i).getPersonnum());
						standardOutput=standardOutput+(list2.get(i).getStandardOutput()==null?0.0:list2.get(i).getStandardOutput());
						actualYield=actualYield+(list2.get(i).getActualYield()==null?0.0:list2.get(i).getActualYield());
						daycount=daycount+(list2.get(i).getDaycount()==null?0.0:list2.get(i).getDaycount());
					
					
				}
				ydata.setOnModulus(onModulus);
				ydata.setPersonnum(personnum);
				ydata.setStandardOutput(standardOutput);
				ydata.setActualYield(actualYield);
				ydata.setDaycount(daycount);
				ydata.setAchievingRate(GlobalMethod.division(actualYield,standardOutput));
				
				ydata.setWorkorholiday("0");
				list_ydata.add(ydata);								
			}
			list_b.add(list_ydata);				
		}		
	}		
}
