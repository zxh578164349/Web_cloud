/**
 * 
 */
package action;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import services.IKpifactServices;
import services.IVKpifactEveNewServices;
import services.IVKpifactEveServices;
import services.IVKpifactNewServices;
import services.IVKpifactServices;
import services.IWebFactServices;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.Kpifact;
import entity.VKpifact;
import entity.VKpifactEve;
import entity.VKpifactEveNew;
import entity.VKpifactNew;
import entity.WebFact;

/**   
 *    
 * 项目名称：Login   
 * 类名称：VkpifactnewAction   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/13 上午8:57:02   
 * 修改人：Administrator   
 * 修改时间：2016/4/13 上午8:57:02   
 * 修改备注：   
 * @version    
 *    
 **/
public class VkpifactnewAction extends ActionSupport implements ServletResponseAware{
	private IVKpifactNewServices vkpinewSer;
	private IVKpifactEveNewServices vkpievenewSer;
	private IWebFactServices webFactSer;	
	private IKpifactServices kpiSer;
	private String yymm;
	private String factNo;
	private String year;
	private String yymm_begin;
	private String yymm_end;
	private List<String>list_factcode;
	private List<String>list_factno;
	private javax.servlet.http.HttpServletResponse response;
	private final static int cursor=45;//循環廠別狀態時的迭代量

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
	public String getFactNo() {
		return factNo;
	}
	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}
	
	public String getYymm_begin() {
		return yymm_begin;
	}
	public void setYymm_begin(String yymm_begin) {
		this.yymm_begin = yymm_begin;
	}
	public String getYymm_end() {
		return yymm_end;
	}
	public void setYymm_end(String yymm_end) {
		this.yymm_end = yymm_end;
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
	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}		
	public void setKpiSer(IKpifactServices kpiSer) {
		this.kpiSer = kpiSer;
	}		
	public void setVkpinewSer(IVKpifactNewServices vkpinewSer) {
		this.vkpinewSer = vkpinewSer;
	}
	public void setVkpievenewSer(IVKpifactEveNewServices vkpievenewSer) {
		this.vkpievenewSer = vkpievenewSer;
	}
	/**
	 * 日期:2016/4/13
	 * 描述:
	 */
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	
	public void print_fact() throws IOException{		
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet("KPI-工廠");
		
		/**
		 * 報表相關樣式
		 */
		Map<String,Object>map=findStyles(wb);
		//標題樣式
		HSSFCellStyle cs_head=(HSSFCellStyle)map.get("cs_head");
		
		
		//標準單元格樣式
		HSSFCellStyle cs=(HSSFCellStyle)map.get("cs");		
		//表頭樣式
		HSSFCellStyle cs_column=(HSSFCellStyle)map.get("cs_column");
													
		/**
		 * 獲取要循環的數據
		 */
		List<WebFact>list_factcode=webFactSer.findFactById_showA(factNo);				
		List<String>list_str=findListstrAndUnit().get(0);		
		List<String>list_unit=findListstrAndUnit().get(1);
		          	
		          
		//表頭內容
		List<String>list_column=new ArrayList<String>();
		list_column.add("項次");
		list_column.add("管制指標");
		list_column.add("單位");
		list_column.add(yymm+"目標值");//20150330
		list_column.add("1月");
		list_column.add("2月");
		list_column.add("3月");
		list_column.add("Q1");
		list_column.add("4月");
		list_column.add("5月");
		list_column.add("6月");
		list_column.add("Q2");
		list_column.add("上半年");
		list_column.add("7月");
		list_column.add("8月");
		list_column.add("9月");
		list_column.add("Q3");
		list_column.add("10月");
		list_column.add("11月");
		list_column.add("12月");
		list_column.add("Q4");
		list_column.add("下半年");
		list_column.add("全年");
		
		String factName=webFactSer.selByid(factNo);
		String title=factName+"("+factNo+")"+year+"年"+"KPI匯總表";
		
		/**
		 * 獲取季度,上下半年,全年數據
		 */
		//最外層集合
		List<List<List<Double>>>list1_all=new ArrayList<List<List<Double>>>();
		for(int a=0;a<list_factcode.size();a++){//start for1
			List<VKpifactEveNew>list_kpieve=new ArrayList<VKpifactEveNew>();
			for(int i=0;i<8;i++){
				list_kpieve.add(new VKpifactEveNew());
			}
			
			/**
			 * 中間集合
			 * 用於裝季度與上下半年與全年的數據
			 */
			
			List<List<Double>>list2_all=new ArrayList<List<Double>>();
			/**
			 * 單元集合
			 */
			//第一季度
			List<Double>list3_q1=new ArrayList<Double>();
			//第二季度
			List<Double>list3_q2=new ArrayList<Double>();
			//第三季度
			List<Double>list3_q3=new ArrayList<Double>();
			//第四季度
			List<Double>list3_q4=new ArrayList<Double>();
			//上半年
			List<Double>list3_half1=new ArrayList<Double>();
			//下半年
			List<Double>list3_half2=new ArrayList<Double>();
			//全年
			List<Double>list3_year=new ArrayList<Double>();
			WebFact fact=list_factcode.get(a);
			String factCode=fact.getId().getFactArea();
			for(int b=1;b<13;b++){//start for2						
				String month="";
				if(b<10){
					month="0"+b;
				}else{
					month=""+b;
				}
				VKpifactEveNew eve=vkpievenewSer.findById(factNo, factCode, year+month)==null?new VKpifactEveNew():vkpievenewSer.findById(factNo, factCode, year+month);
				if(eve!=null){//start if
					List<VKpifactEveNew>list_temp=new ArrayList<VKpifactEveNew>();
					list_temp.add(eve);
					  int b_index=(b-1)/3+1;
					  this.findTotal(list_kpieve, list_kpieve, list_temp, b_index, b_index, 0);
					  /*list_kpieve.get(b_index).setSumEverydemo(list_kpieve.get(b_index).getSumEverydemo().add(eve.getSumEverydemo()));
				      list_kpieve.get(b_index).setSumStandarddemo(list_kpieve.get(b_index).getSumStandarddemo().add(eve.getSumStandarddemo()));
				      list_kpieve.get(b_index).setSumActualdemo(list_kpieve.get(b_index).getSumActualdemo().add(eve.getSumActualdemo()));
				      list_kpieve.get(b_index).setSumActualpairs(list_kpieve.get(b_index).getSumActualpairs().add(eve.getSumActualpairs()));
				      list_kpieve.get(b_index).setSumFactpairs(list_kpieve.get(b_index).getSumFactpairs().add(eve.getSumFactpairs()));
				      list_kpieve.get(b_index).setHole(list_kpieve.get(b_index).getHole()+eve.getHole());
				      list_kpieve.get(b_index).setObja100(list_kpieve.get(b_index).getObja100()+eve.getObja100());
				      list_kpieve.get(b_index).setObja101(list_kpieve.get(b_index).getObja101()+eve.getObja101());
				      list_kpieve.get(b_index).setObja102(list_kpieve.get(b_index).getObja102()+eve.getObja102());
				      list_kpieve.get(b_index).setObja103(list_kpieve.get(b_index).getObja103()+eve.getObja103());
				      list_kpieve.get(b_index).setObja104(list_kpieve.get(b_index).getObja104()+eve.getObja104());
				      list_kpieve.get(b_index).setObja105(list_kpieve.get(b_index).getObja105()+eve.getObja105());
				      list_kpieve.get(b_index).setObja106(list_kpieve.get(b_index).getObja106()+eve.getObja106());
				      list_kpieve.get(b_index).setObja107(list_kpieve.get(b_index).getObja107()+eve.getObja107());
				      list_kpieve.get(b_index).setObja108(list_kpieve.get(b_index).getObja108()+eve.getObja108());
				      list_kpieve.get(b_index).setObja109(list_kpieve.get(b_index).getObja109()+eve.getObja109());
				      list_kpieve.get(b_index).setObja110(list_kpieve.get(b_index).getObja110()+eve.getObja110());
				      list_kpieve.get(b_index).setObja111(list_kpieve.get(b_index).getObja111()+eve.getObja111());
				      list_kpieve.get(b_index).setObja112(list_kpieve.get(b_index).getObja112()+eve.getObja112());
				      list_kpieve.get(b_index).setObja113(list_kpieve.get(b_index).getObja113()+eve.getObja113());
				      list_kpieve.get(b_index).setObja114(list_kpieve.get(b_index).getObja114()+eve.getObja114());
				      list_kpieve.get(b_index).setObja115(list_kpieve.get(b_index).getObja115()+eve.getObja115());
				      list_kpieve.get(b_index).setObja116(list_kpieve.get(b_index).getObja116()+eve.getObja116());
				      list_kpieve.get(b_index).setObja117(list_kpieve.get(b_index).getObja117()+eve.getObja117());
				      list_kpieve.get(b_index).setObja118(list_kpieve.get(b_index).getObja118()+eve.getObja118());
				      list_kpieve.get(b_index).setObja119(list_kpieve.get(b_index).getObja119()+eve.getObja119());
				      list_kpieve.get(b_index).setObja120(list_kpieve.get(b_index).getObja120()+eve.getObja120());
				      list_kpieve.get(b_index).setObja121(list_kpieve.get(b_index).getObja121()+eve.getObja121());
				      list_kpieve.get(b_index).setObja122(list_kpieve.get(b_index).getObja122()+eve.getObja122());
				      list_kpieve.get(b_index).setObja123(list_kpieve.get(b_index).getObja123()+eve.getObja123());
				      list_kpieve.get(b_index).setObja124(list_kpieve.get(b_index).getObja124()+eve.getObja124());
				      list_kpieve.get(b_index).setObja125(list_kpieve.get(b_index).getObja125()+eve.getObja125());
				      list_kpieve.get(b_index).setObja126(list_kpieve.get(b_index).getObja126()+eve.getObja126());
				      list_kpieve.get(b_index).setObja127(list_kpieve.get(b_index).getObja127()+eve.getObja127());
				      list_kpieve.get(b_index).setObja128(list_kpieve.get(b_index).getObja128()+eve.getObja128());
				      list_kpieve.get(b_index).setObja129(list_kpieve.get(b_index).getObja129()+eve.getObja129());
				      list_kpieve.get(b_index).setObja130(list_kpieve.get(b_index).getObja130()+eve.getObja130());
				      list_kpieve.get(b_index).setObja131(list_kpieve.get(b_index).getObja131()+eve.getObja131());
				      list_kpieve.get(b_index).setObja132(list_kpieve.get(b_index).getObja132()+eve.getObja132());
				      list_kpieve.get(b_index).setObja133(list_kpieve.get(b_index).getObja133()+eve.getObja133());
				      list_kpieve.get(b_index).setObja134(list_kpieve.get(b_index).getObja134()+eve.getObja134());
				      list_kpieve.get(b_index).setObja135(list_kpieve.get(b_index).getObja135()+eve.getObja135());
				      list_kpieve.get(b_index).setObja136(list_kpieve.get(b_index).getObja136()+eve.getObja136());
				      list_kpieve.get(b_index).setObja137(list_kpieve.get(b_index).getObja137()+eve.getObja137());
				      list_kpieve.get(b_index).setObja138(list_kpieve.get(b_index).getObja138()+eve.getObja138());
				      list_kpieve.get(b_index).setObja139(list_kpieve.get(b_index).getObja139()+eve.getObja139());
				      list_kpieve.get(b_index).setObja140(list_kpieve.get(b_index).getObja140()+eve.getObja140());
				      list_kpieve.get(b_index).setObja141(list_kpieve.get(b_index).getObja141()+eve.getObja141());
				      list_kpieve.get(b_index).setObja142(list_kpieve.get(b_index).getObja142()+eve.getObja142());
				      list_kpieve.get(b_index).setObja143(list_kpieve.get(b_index).getObja143()+eve.getObja143());
				      list_kpieve.get(b_index).setObja144(list_kpieve.get(b_index).getObja144()+eve.getObja144());
				      list_kpieve.get(b_index).setObja145(list_kpieve.get(b_index).getObja145()+eve.getObja145());
				      list_kpieve.get(b_index).setObja146(list_kpieve.get(b_index).getObja146()+eve.getObja146());
				      list_kpieve.get(b_index).setObja147(list_kpieve.get(b_index).getObja147()+eve.getObja147());
				      list_kpieve.get(b_index).setObja148(list_kpieve.get(b_index).getObja148()+eve.getObja148());
				      list_kpieve.get(b_index).setObja149(list_kpieve.get(b_index).getObja149()+eve.getObja149());
				      list_kpieve.get(b_index).setObja150(list_kpieve.get(b_index).getObja150()+eve.getObja150());
				      list_kpieve.get(b_index).setObja151(list_kpieve.get(b_index).getObja151()+eve.getObja151());
				      list_kpieve.get(b_index).setObja152(list_kpieve.get(b_index).getObja152()+eve.getObja152());
				      list_kpieve.get(b_index).setObja153(list_kpieve.get(b_index).getObja153()+eve.getObja153());
				      list_kpieve.get(b_index).setObja154(list_kpieve.get(b_index).getObja154()+eve.getObja154());
				      list_kpieve.get(b_index).setObja155(list_kpieve.get(b_index).getObja155()+eve.getObja155());
				      list_kpieve.get(b_index).setObja156(list_kpieve.get(b_index).getObja156()+eve.getObja156());
				      list_kpieve.get(b_index).setObja157(list_kpieve.get(b_index).getObja157()+eve.getObja157());
				      list_kpieve.get(b_index).setObja158(list_kpieve.get(b_index).getObja158()+eve.getObja158());
				      list_kpieve.get(b_index).setObja159(list_kpieve.get(b_index).getObja159()+eve.getObja159());
				      list_kpieve.get(b_index).setObja160(list_kpieve.get(b_index).getObja160()+eve.getObja160());
				      list_kpieve.get(b_index).setObja161(list_kpieve.get(b_index).getObja161()+eve.getObja161());
				      list_kpieve.get(b_index).setObja162(list_kpieve.get(b_index).getObja162()+eve.getObja162());
				      list_kpieve.get(b_index).setObja163(list_kpieve.get(b_index).getObja163()+eve.getObja163());
				      list_kpieve.get(b_index).setObja164(list_kpieve.get(b_index).getObja164()+eve.getObja164());
				      list_kpieve.get(b_index).setObja165(list_kpieve.get(b_index).getObja165()+eve.getObja165());
				      list_kpieve.get(b_index).setObja166(list_kpieve.get(b_index).getObja166()+eve.getObja166());
				      list_kpieve.get(b_index).setObja167(list_kpieve.get(b_index).getObja167()+eve.getObja167());
				      list_kpieve.get(b_index).setObja168(list_kpieve.get(b_index).getObja168()+eve.getObja168());
				      list_kpieve.get(b_index).setObja169(list_kpieve.get(b_index).getObja169()+eve.getObja169());
				      list_kpieve.get(b_index).setObja170(list_kpieve.get(b_index).getObja170()+eve.getObja170());
				      list_kpieve.get(b_index).setObja171(list_kpieve.get(b_index).getObja171()+eve.getObja171());
				      list_kpieve.get(b_index).setObja172(list_kpieve.get(b_index).getObja172()+eve.getObja172());
				      list_kpieve.get(b_index).setObja173(list_kpieve.get(b_index).getObja173()+eve.getObja173());
				      list_kpieve.get(b_index).setObja174(list_kpieve.get(b_index).getObja174()+eve.getObja174());
				      list_kpieve.get(b_index).setObja175(list_kpieve.get(b_index).getObja175()+eve.getObja175());
				      list_kpieve.get(b_index).setObja176(list_kpieve.get(b_index).getObja176()+eve.getObja176());
				      list_kpieve.get(b_index).setObja177(list_kpieve.get(b_index).getObja177()+eve.getObja177());
				      list_kpieve.get(b_index).setObja178(list_kpieve.get(b_index).getObja178()+eve.getObja178());
				      list_kpieve.get(b_index).setObja179(list_kpieve.get(b_index).getObja179()+eve.getObja179());
				      list_kpieve.get(b_index).setObja180(list_kpieve.get(b_index).getObja180()+eve.getObja180());
				      list_kpieve.get(b_index).setObja181(list_kpieve.get(b_index).getObja181()+eve.getObja181());
				      list_kpieve.get(b_index).setObja182(list_kpieve.get(b_index).getObja182()+eve.getObja182());
				      list_kpieve.get(b_index).setObja183(list_kpieve.get(b_index).getObja183()+eve.getObja183());
				      list_kpieve.get(b_index).setObja184(list_kpieve.get(b_index).getObja184()+eve.getObja184());
				      list_kpieve.get(b_index).setObja185(list_kpieve.get(b_index).getObja185()+eve.getObja185());
				      list_kpieve.get(b_index).setObja186(list_kpieve.get(b_index).getObja186()+eve.getObja186());
				      list_kpieve.get(b_index).setObja187(list_kpieve.get(b_index).getObja187()+eve.getObja187());
				      list_kpieve.get(b_index).setObja188(list_kpieve.get(b_index).getObja188()+eve.getObja188());
				      list_kpieve.get(b_index).setObja189(list_kpieve.get(b_index).getObja189()+eve.getObja189());
				      list_kpieve.get(b_index).setObja190(list_kpieve.get(b_index).getObja190()+eve.getObja190());
				      list_kpieve.get(b_index).setObja191(list_kpieve.get(b_index).getObja191()+eve.getObja191());
				      list_kpieve.get(b_index).setObja192(list_kpieve.get(b_index).getObja192()+eve.getObja192());
				      list_kpieve.get(b_index).setObja193(list_kpieve.get(b_index).getObja193()+eve.getObja193());
				      list_kpieve.get(b_index).setObja194(list_kpieve.get(b_index).getObja194()+eve.getObja194());
				      list_kpieve.get(b_index).setObja195(list_kpieve.get(b_index).getObja195()+eve.getObja195());
				      list_kpieve.get(b_index).setObja196(list_kpieve.get(b_index).getObja196()+eve.getObja196());
				      list_kpieve.get(b_index).setObja197(list_kpieve.get(b_index).getObja197()+eve.getObja197());
				      list_kpieve.get(b_index).setObja198(list_kpieve.get(b_index).getObja198()+eve.getObja198());
				      list_kpieve.get(b_index).setObja199(list_kpieve.get(b_index).getObja199()+eve.getObja199());
				      list_kpieve.get(b_index).setObja200(list_kpieve.get(b_index).getObja200()+eve.getObja200());
				      list_kpieve.get(b_index).setObja201(list_kpieve.get(b_index).getObja201()+eve.getObja201());
				      list_kpieve.get(b_index).setObja202(list_kpieve.get(b_index).getObja202()+eve.getObja202());*/
				      
				      if(b==12){
				    	  for(int i=0;i<3;i++){
				    		  this.findTotal(list_kpieve, list_kpieve, list_kpieve, i+5, 2*i+1, 2*i+2);
				    		 /* list_kpieve.get(i+5).setSumEverydemo(list_kpieve.get(2*i+1).getSumEverydemo().add(list_kpieve.get(2*i+2).getSumEverydemo()));
						      list_kpieve.get(i+5).setSumStandarddemo(list_kpieve.get(2*i+1).getSumStandarddemo().add(list_kpieve.get(2*i+2).getSumStandarddemo()));
						      list_kpieve.get(i+5).setSumActualdemo(list_kpieve.get(2*i+1).getSumActualdemo().add(list_kpieve.get(2*i+2).getSumActualdemo()));
						      list_kpieve.get(i+5).setSumActualpairs(list_kpieve.get(2*i+1).getSumActualpairs().add(list_kpieve.get(2*i+2).getSumActualpairs()));
						      list_kpieve.get(i+5).setSumFactpairs(list_kpieve.get(2*i+1).getSumFactpairs().add(list_kpieve.get(2*i+2).getSumFactpairs()));
						      list_kpieve.get(i+5).setHole(list_kpieve.get(2*i+1).getHole()+list_kpieve.get(2*i+2).getHole());						      
						      list_kpieve.get(i+5).setObja100(list_kpieve.get(2*i+1).getObja100()+list_kpieve.get(2*i+2).getObja100());
						      list_kpieve.get(i+5).setObja101(list_kpieve.get(2*i+1).getObja101()+list_kpieve.get(2*i+2).getObja101());
						      list_kpieve.get(i+5).setObja102(list_kpieve.get(2*i+1).getObja102()+list_kpieve.get(2*i+2).getObja102());
						      list_kpieve.get(i+5).setObja103(list_kpieve.get(2*i+1).getObja103()+list_kpieve.get(2*i+2).getObja103());
						      list_kpieve.get(i+5).setObja104(list_kpieve.get(2*i+1).getObja104()+list_kpieve.get(2*i+2).getObja104());
						      list_kpieve.get(i+5).setObja105(list_kpieve.get(2*i+1).getObja105()+list_kpieve.get(2*i+2).getObja105());
						      list_kpieve.get(i+5).setObja106(list_kpieve.get(2*i+1).getObja106()+list_kpieve.get(2*i+2).getObja106());
						      list_kpieve.get(i+5).setObja107(list_kpieve.get(2*i+1).getObja107()+list_kpieve.get(2*i+2).getObja107());
						      list_kpieve.get(i+5).setObja108(list_kpieve.get(2*i+1).getObja108()+list_kpieve.get(2*i+2).getObja108());
						      list_kpieve.get(i+5).setObja109(list_kpieve.get(2*i+1).getObja109()+list_kpieve.get(2*i+2).getObja109());
						      list_kpieve.get(i+5).setObja110(list_kpieve.get(2*i+1).getObja110()+list_kpieve.get(2*i+2).getObja110());
						      list_kpieve.get(i+5).setObja111(list_kpieve.get(2*i+1).getObja111()+list_kpieve.get(2*i+2).getObja111());
						      list_kpieve.get(i+5).setObja112(list_kpieve.get(2*i+1).getObja112()+list_kpieve.get(2*i+2).getObja112());
						      list_kpieve.get(i+5).setObja113(list_kpieve.get(2*i+1).getObja113()+list_kpieve.get(2*i+2).getObja113());
						      list_kpieve.get(i+5).setObja114(list_kpieve.get(2*i+1).getObja114()+list_kpieve.get(2*i+2).getObja114());
						      list_kpieve.get(i+5).setObja115(list_kpieve.get(2*i+1).getObja115()+list_kpieve.get(2*i+2).getObja115());
						      list_kpieve.get(i+5).setObja116(list_kpieve.get(2*i+1).getObja116()+list_kpieve.get(2*i+2).getObja116());
						      list_kpieve.get(i+5).setObja117(list_kpieve.get(2*i+1).getObja117()+list_kpieve.get(2*i+2).getObja117());
						      list_kpieve.get(i+5).setObja118(list_kpieve.get(2*i+1).getObja118()+list_kpieve.get(2*i+2).getObja118());
						      list_kpieve.get(i+5).setObja119(list_kpieve.get(2*i+1).getObja119()+list_kpieve.get(2*i+2).getObja119());
						      list_kpieve.get(i+5).setObja120(list_kpieve.get(2*i+1).getObja120()+list_kpieve.get(2*i+2).getObja120());
						      list_kpieve.get(i+5).setObja121(list_kpieve.get(2*i+1).getObja121()+list_kpieve.get(2*i+2).getObja121());
						      list_kpieve.get(i+5).setObja122(list_kpieve.get(2*i+1).getObja122()+list_kpieve.get(2*i+2).getObja122());
						      list_kpieve.get(i+5).setObja123(list_kpieve.get(2*i+1).getObja123()+list_kpieve.get(2*i+2).getObja123());
						      list_kpieve.get(i+5).setObja124(list_kpieve.get(2*i+1).getObja124()+list_kpieve.get(2*i+2).getObja124());
						      list_kpieve.get(i+5).setObja125(list_kpieve.get(2*i+1).getObja125()+list_kpieve.get(2*i+2).getObja125());
						      list_kpieve.get(i+5).setObja126(list_kpieve.get(2*i+1).getObja126()+list_kpieve.get(2*i+2).getObja126());
						      list_kpieve.get(i+5).setObja127(list_kpieve.get(2*i+1).getObja127()+list_kpieve.get(2*i+2).getObja127());
						      list_kpieve.get(i+5).setObja128(list_kpieve.get(2*i+1).getObja128()+list_kpieve.get(2*i+2).getObja128());
						      list_kpieve.get(i+5).setObja129(list_kpieve.get(2*i+1).getObja129()+list_kpieve.get(2*i+2).getObja129());
						      list_kpieve.get(i+5).setObja130(list_kpieve.get(2*i+1).getObja130()+list_kpieve.get(2*i+2).getObja130());
						      list_kpieve.get(i+5).setObja131(list_kpieve.get(2*i+1).getObja131()+list_kpieve.get(2*i+2).getObja131());
						      list_kpieve.get(i+5).setObja132(list_kpieve.get(2*i+1).getObja132()+list_kpieve.get(2*i+2).getObja132());
						      list_kpieve.get(i+5).setObja133(list_kpieve.get(2*i+1).getObja133()+list_kpieve.get(2*i+2).getObja133());
						      list_kpieve.get(i+5).setObja134(list_kpieve.get(2*i+1).getObja134()+list_kpieve.get(2*i+2).getObja134());
						      list_kpieve.get(i+5).setObja135(list_kpieve.get(2*i+1).getObja135()+list_kpieve.get(2*i+2).getObja135());
						      list_kpieve.get(i+5).setObja136(list_kpieve.get(2*i+1).getObja136()+list_kpieve.get(2*i+2).getObja136());
						      list_kpieve.get(i+5).setObja137(list_kpieve.get(2*i+1).getObja137()+list_kpieve.get(2*i+2).getObja137());
						      list_kpieve.get(i+5).setObja138(list_kpieve.get(2*i+1).getObja138()+list_kpieve.get(2*i+2).getObja138());
						      list_kpieve.get(i+5).setObja139(list_kpieve.get(2*i+1).getObja139()+list_kpieve.get(2*i+2).getObja139());
						      list_kpieve.get(i+5).setObja140(list_kpieve.get(2*i+1).getObja140()+list_kpieve.get(2*i+2).getObja140());
						      list_kpieve.get(i+5).setObja141(list_kpieve.get(2*i+1).getObja141()+list_kpieve.get(2*i+2).getObja141());
						      list_kpieve.get(i+5).setObja142(list_kpieve.get(2*i+1).getObja142()+list_kpieve.get(2*i+2).getObja142());
						      list_kpieve.get(i+5).setObja143(list_kpieve.get(2*i+1).getObja143()+list_kpieve.get(2*i+2).getObja143());
						      list_kpieve.get(i+5).setObja144(list_kpieve.get(2*i+1).getObja144()+list_kpieve.get(2*i+2).getObja144());
						      list_kpieve.get(i+5).setObja145(list_kpieve.get(2*i+1).getObja145()+list_kpieve.get(2*i+2).getObja145());
						      list_kpieve.get(i+5).setObja146(list_kpieve.get(2*i+1).getObja146()+list_kpieve.get(2*i+2).getObja146());
						      list_kpieve.get(i+5).setObja147(list_kpieve.get(2*i+1).getObja147()+list_kpieve.get(2*i+2).getObja147());
						      list_kpieve.get(i+5).setObja148(list_kpieve.get(2*i+1).getObja148()+list_kpieve.get(2*i+2).getObja148());
						      list_kpieve.get(i+5).setObja149(list_kpieve.get(2*i+1).getObja149()+list_kpieve.get(2*i+2).getObja149());
						      list_kpieve.get(i+5).setObja150(list_kpieve.get(2*i+1).getObja150()+list_kpieve.get(2*i+2).getObja150());
						      list_kpieve.get(i+5).setObja151(list_kpieve.get(2*i+1).getObja151()+list_kpieve.get(2*i+2).getObja151());
						      list_kpieve.get(i+5).setObja152(list_kpieve.get(2*i+1).getObja152()+list_kpieve.get(2*i+2).getObja152());
						      list_kpieve.get(i+5).setObja153(list_kpieve.get(2*i+1).getObja153()+list_kpieve.get(2*i+2).getObja153());
						      list_kpieve.get(i+5).setObja154(list_kpieve.get(2*i+1).getObja154()+list_kpieve.get(2*i+2).getObja154());
						      list_kpieve.get(i+5).setObja155(list_kpieve.get(2*i+1).getObja155()+list_kpieve.get(2*i+2).getObja155());
						      list_kpieve.get(i+5).setObja156(list_kpieve.get(2*i+1).getObja156()+list_kpieve.get(2*i+2).getObja156());
						      list_kpieve.get(i+5).setObja157(list_kpieve.get(2*i+1).getObja157()+list_kpieve.get(2*i+2).getObja157());
						      list_kpieve.get(i+5).setObja158(list_kpieve.get(2*i+1).getObja158()+list_kpieve.get(2*i+2).getObja158());
						      list_kpieve.get(i+5).setObja159(list_kpieve.get(2*i+1).getObja159()+list_kpieve.get(2*i+2).getObja159());
						      list_kpieve.get(i+5).setObja160(list_kpieve.get(2*i+1).getObja160()+list_kpieve.get(2*i+2).getObja160());
						      list_kpieve.get(i+5).setObja161(list_kpieve.get(2*i+1).getObja161()+list_kpieve.get(2*i+2).getObja161());
						      list_kpieve.get(i+5).setObja162(list_kpieve.get(2*i+1).getObja162()+list_kpieve.get(2*i+2).getObja162());
						      list_kpieve.get(i+5).setObja163(list_kpieve.get(2*i+1).getObja163()+list_kpieve.get(2*i+2).getObja163());
						      list_kpieve.get(i+5).setObja164(list_kpieve.get(2*i+1).getObja164()+list_kpieve.get(2*i+2).getObja164());
						      list_kpieve.get(i+5).setObja165(list_kpieve.get(2*i+1).getObja165()+list_kpieve.get(2*i+2).getObja165());
						      list_kpieve.get(i+5).setObja166(list_kpieve.get(2*i+1).getObja166()+list_kpieve.get(2*i+2).getObja166());
						      list_kpieve.get(i+5).setObja167(list_kpieve.get(2*i+1).getObja167()+list_kpieve.get(2*i+2).getObja167());
						      list_kpieve.get(i+5).setObja168(list_kpieve.get(2*i+1).getObja168()+list_kpieve.get(2*i+2).getObja168());
						      list_kpieve.get(i+5).setObja169(list_kpieve.get(2*i+1).getObja169()+list_kpieve.get(2*i+2).getObja169());
						      list_kpieve.get(i+5).setObja170(list_kpieve.get(2*i+1).getObja170()+list_kpieve.get(2*i+2).getObja170());
						      list_kpieve.get(i+5).setObja171(list_kpieve.get(2*i+1).getObja171()+list_kpieve.get(2*i+2).getObja171());
						      list_kpieve.get(i+5).setObja172(list_kpieve.get(2*i+1).getObja172()+list_kpieve.get(2*i+2).getObja172());
						      list_kpieve.get(i+5).setObja173(list_kpieve.get(2*i+1).getObja173()+list_kpieve.get(2*i+2).getObja173());
						      list_kpieve.get(i+5).setObja174(list_kpieve.get(2*i+1).getObja174()+list_kpieve.get(2*i+2).getObja174());
						      list_kpieve.get(i+5).setObja175(list_kpieve.get(2*i+1).getObja175()+list_kpieve.get(2*i+2).getObja175());
						      list_kpieve.get(i+5).setObja176(list_kpieve.get(2*i+1).getObja176()+list_kpieve.get(2*i+2).getObja176());
						      list_kpieve.get(i+5).setObja177(list_kpieve.get(2*i+1).getObja177()+list_kpieve.get(2*i+2).getObja177());
						      list_kpieve.get(i+5).setObja178(list_kpieve.get(2*i+1).getObja178()+list_kpieve.get(2*i+2).getObja178());
						      list_kpieve.get(i+5).setObja179(list_kpieve.get(2*i+1).getObja179()+list_kpieve.get(2*i+2).getObja179());
						      list_kpieve.get(i+5).setObja180(list_kpieve.get(2*i+1).getObja180()+list_kpieve.get(2*i+2).getObja180());
						      list_kpieve.get(i+5).setObja181(list_kpieve.get(2*i+1).getObja181()+list_kpieve.get(2*i+2).getObja181());
						      list_kpieve.get(i+5).setObja182(list_kpieve.get(2*i+1).getObja182()+list_kpieve.get(2*i+2).getObja182());
						      list_kpieve.get(i+5).setObja183(list_kpieve.get(2*i+1).getObja183()+list_kpieve.get(2*i+2).getObja183());
						      list_kpieve.get(i+5).setObja184(list_kpieve.get(2*i+1).getObja184()+list_kpieve.get(2*i+2).getObja184());
						      list_kpieve.get(i+5).setObja185(list_kpieve.get(2*i+1).getObja185()+list_kpieve.get(2*i+2).getObja185());
						      list_kpieve.get(i+5).setObja186(list_kpieve.get(2*i+1).getObja186()+list_kpieve.get(2*i+2).getObja186());
						      list_kpieve.get(i+5).setObja187(list_kpieve.get(2*i+1).getObja187()+list_kpieve.get(2*i+2).getObja187());
						      list_kpieve.get(i+5).setObja188(list_kpieve.get(2*i+1).getObja188()+list_kpieve.get(2*i+2).getObja188());
						      list_kpieve.get(i+5).setObja189(list_kpieve.get(2*i+1).getObja189()+list_kpieve.get(2*i+2).getObja189());
						      list_kpieve.get(i+5).setObja190(list_kpieve.get(2*i+1).getObja190()+list_kpieve.get(2*i+2).getObja190());
						      list_kpieve.get(i+5).setObja191(list_kpieve.get(2*i+1).getObja191()+list_kpieve.get(2*i+2).getObja191());
						      list_kpieve.get(i+5).setObja192(list_kpieve.get(2*i+1).getObja192()+list_kpieve.get(2*i+2).getObja192());
						      list_kpieve.get(i+5).setObja193(list_kpieve.get(2*i+1).getObja193()+list_kpieve.get(2*i+2).getObja193());
						      list_kpieve.get(i+5).setObja194(list_kpieve.get(2*i+1).getObja194()+list_kpieve.get(2*i+2).getObja194());
						      list_kpieve.get(i+5).setObja195(list_kpieve.get(2*i+1).getObja195()+list_kpieve.get(2*i+2).getObja195());
						      list_kpieve.get(i+5).setObja196(list_kpieve.get(2*i+1).getObja196()+list_kpieve.get(2*i+2).getObja196());
						      list_kpieve.get(i+5).setObja197(list_kpieve.get(2*i+1).getObja197()+list_kpieve.get(2*i+2).getObja197());
						      list_kpieve.get(i+5).setObja198(list_kpieve.get(2*i+1).getObja198()+list_kpieve.get(2*i+2).getObja198());
						      list_kpieve.get(i+5).setObja199(list_kpieve.get(2*i+1).getObja199()+list_kpieve.get(2*i+2).getObja199());
						      list_kpieve.get(i+5).setObja200(list_kpieve.get(2*i+1).getObja200()+list_kpieve.get(2*i+2).getObja200());
						      list_kpieve.get(i+5).setObja201(list_kpieve.get(2*i+1).getObja201()+list_kpieve.get(2*i+2).getObja201());
						      list_kpieve.get(i+5).setObja202(list_kpieve.get(2*i+1).getObja202()+list_kpieve.get(2*i+2).getObja202());	*/				      
				    	  }
				    	  list3_q1=this.findResult(b/4,list_kpieve.get(1));//list_kpieve.get(1)第一季度							
						  list2_all.add(list3_q1);
						  list3_q2=this.findResult(b/4,list_kpieve.get(2));	//	list_kpieve.get(2):第二季度					
						  list3_half1=this.findResult(b/2,list_kpieve.get(5));//list_kpieve.get(5):上半年										
						  list2_all.add(list3_q2);
						  list2_all.add(list3_half1);
						  list3_q3=this.findResult(b/4,list_kpieve.get(3));//list_kpieve.get(3):第三季度							
						  list2_all.add(list3_q3);
						  list3_q4=this.findResult(b/4,list_kpieve.get(4));	//list_kpieve.get(3):第四季度						
						  list3_half2=this.findResult(b/2,list_kpieve.get(6));	//list_kpieve.get(5):下半年						
						  list3_year=this.findResult(b,list_kpieve.get(7));	//list_kpieve.get(6):全年						
						  list2_all.add(list3_q4);
						  list2_all.add(list3_half2);
						  list2_all.add(list3_year);
				      }																		
				}// end if						
			}//end for2
			list1_all.add(list2_all);
		}// end for1
		
		
		
		//填充標題(放在循環外面)
		CellRangeAddress addTitle=new CellRangeAddress(0,(short)0,0,(short)list_column.size()-1);
		sheet.addMergedRegion(addTitle);
		sheet.createRow(0).createCell(0).setCellValue(title);
		sheet.getRow(0).getCell(0).setCellStyle(cs_head);
		
		for(int a=1;a<list_column.size();a++){
			sheet.getRow(0).createCell(a).setCellStyle(cs_head);
		}
		for(int k=0;k<list_factcode.size();k++){//start for1			
			WebFact fact=list_factcode.get(k);
			String factCode=fact.getId().getFactArea();	
			Kpifact kpi_pur=kpiSer.findById(factNo, factCode, yymm);//每年的預計目標(20150327)
			List<Double> list_content_pur=new ArrayList<Double>();//每年的預計目標封裝在一個list，以便於for循環(20150327)
			if(kpi_pur!=null){//start if2(20150327)
				list_content_pur=kpiFactToDouble(kpi_pur);	//KpiFact目標各項封裝到List<Double>									
			}//end if2
						
			//填充表頭(包括廠別狀態)
			sheet.createRow(2+cursor*k).createCell(0).setCellValue("形態:"+factCode);
			HSSFRow row_columnHead=sheet.createRow(3+cursor*k);			
			for(int h=0;h<list_column.size();h++){
				String column=list_column.get(h);
				HSSFCell cell_columnHead=row_columnHead.createCell(h);
				cell_columnHead.setCellValue(column);
				cell_columnHead.setCellStyle(cs_column);
				if(h==1||h==3){
					sheet.setColumnWidth(h, 4000);//列寬
				}else{
					sheet.setColumnWidth(h, 3000);//列寬
				}
				
			}
			//填充內容
			for(int i=0;i<12;i++){//start for2
				
				int temp_num=0;
				if(i>2&&i<6){
					temp_num=1;
				}
				if(i>5&&i<9){
					temp_num=3;
				}
				if(i>8&&i<12){
					temp_num=4;
				}
				
				String month="";
				if(i+1<10){
					month="0"+(i+1);
				}else{
					month=""+(i+1);
				}
				switch(i){				
				case 0:					
					for(int j=0;j<list_str.size();j++){//start for3
						
						String index="";
						if(j+1<10){
							index="0"+(j+1);
						}else{
							index=""+(j+1);
						}
						HSSFRow row=sheet.createRow(4+j+cursor*k);
						HSSFCell cell0=row.createCell(i);
						HSSFCell cell1=row.createCell(i+1);
						HSSFCell cell2=row.createCell(i+2);
						HSSFCell cell3=row.createCell(i+3);//20150330
						cell0.setCellValue(index);
						cell0.setCellStyle(cs);
						cell1.setCellValue(list_str.get(j));
						cell1.setCellStyle(cs);
						cell2.setCellValue(list_unit.get(j));
						cell2.setCellStyle(cs);
						HSSFCellStyle cs_temp=findStyle(wb, j,map);//數字格式的選擇	
						if(kpi_pur!=null){																				
							cell3.setCellValue(list_content_pur.get(j));
							cell3.setCellStyle(cs_temp);
						}else{
							cell3.setCellValue("無數據");
							cell3.setCellStyle(cs);
						}
						
					}//end for3
					break;								
				}
				VKpifactNew kpi=vkpinewSer.findById(factNo, factCode,year+month);								
				List<Double> list_content=new ArrayList<Double>();
				if(kpi!=null){// start if1
					BigDecimal big=new BigDecimal(0.00);
					/*if(kpi.getFactaddRate()==null){
						kpi.setFactaddRate(big);
					}
					if(kpi.getOutrequestRate()==null){
						kpi.setOutrequestRate(big);
					}
					if(kpi.getAvgPermoney()==null){
						kpi.setAvgPermoney(big);
					}
					if(kpi.getPermoney()==null){
						kpi.setPermoney(big);
					}
					if(kpi.getWasteFact()==null){
						kpi.setWasteFact(big);
					}
					if(kpi.getWasteNo()==null){
						kpi.setWasteNo(big);
					}
					if(kpi.getWasteUsd()==null){
						kpi.setWasteUsd(big);
					}
					if(kpi.getAvgCirclehour()==null){
						kpi.setAvgCirclehour(big);
					}*/
					list_content=VkpiFactToDouble(kpi);//VKpiFact對象各項封裝List<Double>集合
					
											
				}//end if1
																									
				/**
				 * 補全季度與年度統計
				 */
				List<List<Double>> list2=list1_all.get(k);//中間集合,每個中間集合包括1-4季度,上下半年,全年數庫
				List<Double>list3_q1=list2.get(0);//第一季度
				List<Double>list3_q2=list2.get(1);//第二季度
				List<Double>list3_half1=list2.get(2);//上半年
				List<Double>list3_q3=list2.get(3);//第三季度
				List<Double>list3_q4=list2.get(4);//第四季度
				List<Double>list3_half2=list2.get(5);//下半年
				List<Double>list3_year=list2.get(6);//全年
																				
				for(int j=0;j<list_str.size();j++){
					HSSFRow row=sheet.getRow(4+j+cursor*k);
					HSSFCell cell=row.createCell(i+4+temp_num);
					HSSFCellStyle cs_temp=findStyle(wb,j,map);//格式的選擇											
					
					if(kpi!=null&&kpi_pur!=null){						
						Double num1=list_content.get(j);//實際值
						Double num2=list_content_pur.get(j);//預計值	
						cs_temp=findStyle(wb,num1,num2,j,map);//數字格式的選擇																	
					}													
					if(kpi!=null){
						cell.setCellValue(list_content.get(j));
					}else{
						cell.setCellValue("無數據");
					}
					cell.setCellStyle(cs_temp);	
					
					if(i==2){
						HSSFCell cell2=row.createCell(i+5+temp_num);//第一季度
						cell2.setCellValue(list3_q1.get(j));
						if(kpi_pur!=null){//start if
							Double num1=list3_q1.get(j);
							Double num2=list_content_pur.get(j);							
							cell2.setCellStyle(findStyle(wb,num1,num2,j,map));
						}//end if
						
					}
					if(i==5){
						HSSFCell cell3_1=row.createCell(i+5+temp_num);//第二季度
						HSSFCell cell3_2=row.createCell(i+6+temp_num);//上半年
						cell3_1.setCellValue(list3_q2.get(j));
						cell3_2.setCellValue(list3_half1.get(j));
						if(kpi_pur!=null){//start if
							Double num1=list3_q2.get(j);
							Double num2=list3_half1.get(j);
							Double num3=list_content_pur.get(j);							
							cell3_1.setCellStyle(findStyle(wb,num1,num3,j,map));
							cell3_2.setCellStyle(findStyle(wb,num2,num3,j,map));
						}//end if
						
					}
					if(i==8){
						HSSFCell cell4=row.createCell(i+5+temp_num);//第三季度
						cell4.setCellValue(list3_q3.get(j));
						if(kpi_pur!=null){//start if
							Double num1=list3_q3.get(j);
							Double num2=list_content_pur.get(j);							
							cell4.setCellStyle(findStyle(wb,num1,num2,j,map));
						}//end if
						
					}
					if(i==11){
						HSSFCell cell5=row.createCell(i+5+temp_num);//第四季度
						HSSFCell cell5_2=row.createCell(i+6+temp_num);//下半年
						HSSFCell cell5_3=row.createCell(i+7+temp_num);//全年
						cell5.setCellValue(list3_q4.get(j));
						cell5_2.setCellValue(list3_half2.get(j));
						cell5_3.setCellValue(list3_year.get(j));						
						if(kpi_pur!=null){//start if
							Double num_q4=list3_q4.get(j);
							Double num_half2=list3_half2.get(j);
							Double num_year=list3_year.get(j);
							Double num=list_content_pur.get(j);							
							cell5.setCellStyle(findStyle(wb,num_q4,num,j,map));
							cell5_2.setCellStyle(findStyle(wb,num_half2,num,j,map));
							cell5_3.setCellStyle(findStyle(wb,num_year,num,j,map));
						}//end if
					}
			    }
				/**
				 * 結束4
				 */
				
				/**
				 * 結束2
				 */								
			}//end for2
		}//end for1
		ServletOutputStream os=response.getOutputStream();
		response.setContentType("application/vnd.ms-excel");
		title=title+".xls";
		int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");//判斷是否為IE瀏覽器,大於0則為IE瀏覽器
		String fileName="";
		if(msie>0){
			fileName=java.net.URLEncoder.encode(title,"utf-8");//解決IE中文文件不能下載的問題
		}else{
			fileName=new String(title.getBytes("utf-8"),"iso-8859-1");//解決非IE中文名亂碼問題
		}		
		response.setHeader("Content-disposition", "attachment;filename="+fileName);
		wb.write(os);
		os.close();
	}
	
	public void print_month() throws IOException, ParseException{		
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet("KPI-工廠");		
		/**
		 * 報表相關樣式
		 */
		Map<String,Object>map=findStyles(wb);
		//標題樣式
		HSSFCellStyle cs_head=(HSSFCellStyle)map.get("cs_head");		
		//標準單元格樣式
		HSSFCellStyle cs=(HSSFCellStyle)map.get("cs");		
		//表頭樣式
		HSSFCellStyle cs_column=(HSSFCellStyle)map.get("cs_column");
						
		/**
		 * 獲取要循環的數據
		 */
		List<WebFact>list_factcode=webFactSer.findFactById_showA(factNo);				
		List<String>list_str=findListstrAndUnit().get(0);		
		List<String>list_unit=findListstrAndUnit().get(1);
		
		
		
		
		//表頭內容
		List<String>list_column=new ArrayList<String>();
		//獲取所有月份
		List<String>list_months=this.getMonths(yymm_begin, yymm_end);		
		list_column.add("項次");
		list_column.add("管制指標");
		list_column.add("單位");
		for(int a=0;a<list_months.size();a++){
			String ym=list_months.get(a);
			list_column.add(ym);
		}
		list_column.add("合計");
		
		String factName=webFactSer.selByid(factNo);
		String title=factName+"("+factNo+")"+yymm_begin+"--"+yymm_end+"KPI匯總表";
		
		/**
		 * 獲取季度,上下半年,全年數據
		 */
		//最外層集合
		List<List<List<Double>>>list1_all=new ArrayList<List<List<Double>>>();
		for(int a=0;a<list_factcode.size();a++){//start for1
			List<VKpifactEveNew>list_kpieve=new ArrayList<VKpifactEveNew>();
			for(int i=0;i<1;i++){
				list_kpieve.add(new VKpifactEveNew());
			}
									
			//中間集合,用於裝"合計"數據
			List<List<Double>>list2_total=new ArrayList<List<Double>>();
			
			//單元集合,合計
			List<Double>list3_total=new ArrayList<Double>();
			
			WebFact fact=list_factcode.get(a);
			String factCode=fact.getId().getFactArea();						
			for(int b=0;b<list_months.size();b++){//start for2
				String ym=list_months.get(b);
				VKpifactEveNew eve=vkpievenewSer.findById(factNo, factCode,ym)==null?new VKpifactEveNew():vkpievenewSer.findById(factNo, factCode,ym);
				if(eve!=null){//start if
					List<VKpifactEveNew>list_temp=new ArrayList<VKpifactEveNew>();
					list_temp.add(eve);
					//合計
					this.findTotal(list_kpieve, list_kpieve, list_temp, 0, 0, 0);
					/*list_kpieve.get(0).setSumEverydemo(list_kpieve.get(0).getSumEverydemo().add(eve.getSumEverydemo()));
				      list_kpieve.get(0).setSumStandarddemo(list_kpieve.get(0).getSumStandarddemo().add(eve.getSumStandarddemo()));
				      list_kpieve.get(0).setSumActualdemo(list_kpieve.get(0).getSumActualdemo().add(eve.getSumActualdemo()));
				      list_kpieve.get(0).setSumActualpairs(list_kpieve.get(0).getSumActualpairs().add(eve.getSumActualpairs()));
				      list_kpieve.get(0).setSumFactpairs(list_kpieve.get(0).getSumFactpairs().add(eve.getSumFactpairs()));
				      list_kpieve.get(0).setHole(list_kpieve.get(0).getHole()+eve.getHole());
				      list_kpieve.get(0).setObja100(list_kpieve.get(0).getObja100()+eve.getObja100());
				      list_kpieve.get(0).setObja101(list_kpieve.get(0).getObja101()+eve.getObja101());
				      list_kpieve.get(0).setObja102(list_kpieve.get(0).getObja102()+eve.getObja102());
				      list_kpieve.get(0).setObja103(list_kpieve.get(0).getObja103()+eve.getObja103());
				      list_kpieve.get(0).setObja104(list_kpieve.get(0).getObja104()+eve.getObja104());
				      list_kpieve.get(0).setObja105(list_kpieve.get(0).getObja105()+eve.getObja105());
				      list_kpieve.get(0).setObja106(list_kpieve.get(0).getObja106()+eve.getObja106());
				      list_kpieve.get(0).setObja107(list_kpieve.get(0).getObja107()+eve.getObja107());
				      list_kpieve.get(0).setObja108(list_kpieve.get(0).getObja108()+eve.getObja108());
				      list_kpieve.get(0).setObja109(list_kpieve.get(0).getObja109()+eve.getObja109());
				      list_kpieve.get(0).setObja110(list_kpieve.get(0).getObja110()+eve.getObja110());
				      list_kpieve.get(0).setObja111(list_kpieve.get(0).getObja111()+eve.getObja111());
				      list_kpieve.get(0).setObja112(list_kpieve.get(0).getObja112()+eve.getObja112());
				      list_kpieve.get(0).setObja113(list_kpieve.get(0).getObja113()+eve.getObja113());
				      list_kpieve.get(0).setObja114(list_kpieve.get(0).getObja114()+eve.getObja114());
				      list_kpieve.get(0).setObja115(list_kpieve.get(0).getObja115()+eve.getObja115());
				      list_kpieve.get(0).setObja116(list_kpieve.get(0).getObja116()+eve.getObja116());
				      list_kpieve.get(0).setObja117(list_kpieve.get(0).getObja117()+eve.getObja117());
				      list_kpieve.get(0).setObja118(list_kpieve.get(0).getObja118()+eve.getObja118());
				      list_kpieve.get(0).setObja119(list_kpieve.get(0).getObja119()+eve.getObja119());
				      list_kpieve.get(0).setObja120(list_kpieve.get(0).getObja120()+eve.getObja120());
				      list_kpieve.get(0).setObja121(list_kpieve.get(0).getObja121()+eve.getObja121());
				      list_kpieve.get(0).setObja122(list_kpieve.get(0).getObja122()+eve.getObja122());
				      list_kpieve.get(0).setObja123(list_kpieve.get(0).getObja123()+eve.getObja123());
				      list_kpieve.get(0).setObja124(list_kpieve.get(0).getObja124()+eve.getObja124());
				      list_kpieve.get(0).setObja125(list_kpieve.get(0).getObja125()+eve.getObja125());
				      list_kpieve.get(0).setObja126(list_kpieve.get(0).getObja126()+eve.getObja126());
				      list_kpieve.get(0).setObja127(list_kpieve.get(0).getObja127()+eve.getObja127());
				      list_kpieve.get(0).setObja128(list_kpieve.get(0).getObja128()+eve.getObja128());
				      list_kpieve.get(0).setObja129(list_kpieve.get(0).getObja129()+eve.getObja129());
				      list_kpieve.get(0).setObja130(list_kpieve.get(0).getObja130()+eve.getObja130());
				      list_kpieve.get(0).setObja131(list_kpieve.get(0).getObja131()+eve.getObja131());
				      list_kpieve.get(0).setObja132(list_kpieve.get(0).getObja132()+eve.getObja132());
				      list_kpieve.get(0).setObja133(list_kpieve.get(0).getObja133()+eve.getObja133());
				      list_kpieve.get(0).setObja134(list_kpieve.get(0).getObja134()+eve.getObja134());
				      list_kpieve.get(0).setObja135(list_kpieve.get(0).getObja135()+eve.getObja135());
				      list_kpieve.get(0).setObja136(list_kpieve.get(0).getObja136()+eve.getObja136());
				      list_kpieve.get(0).setObja137(list_kpieve.get(0).getObja137()+eve.getObja137());
				      list_kpieve.get(0).setObja138(list_kpieve.get(0).getObja138()+eve.getObja138());
				      list_kpieve.get(0).setObja139(list_kpieve.get(0).getObja139()+eve.getObja139());
				      list_kpieve.get(0).setObja140(list_kpieve.get(0).getObja140()+eve.getObja140());
				      list_kpieve.get(0).setObja141(list_kpieve.get(0).getObja141()+eve.getObja141());
				      list_kpieve.get(0).setObja142(list_kpieve.get(0).getObja142()+eve.getObja142());
				      list_kpieve.get(0).setObja143(list_kpieve.get(0).getObja143()+eve.getObja143());
				      list_kpieve.get(0).setObja144(list_kpieve.get(0).getObja144()+eve.getObja144());
				      list_kpieve.get(0).setObja145(list_kpieve.get(0).getObja145()+eve.getObja145());
				      list_kpieve.get(0).setObja146(list_kpieve.get(0).getObja146()+eve.getObja146());
				      list_kpieve.get(0).setObja147(list_kpieve.get(0).getObja147()+eve.getObja147());
				      list_kpieve.get(0).setObja148(list_kpieve.get(0).getObja148()+eve.getObja148());
				      list_kpieve.get(0).setObja149(list_kpieve.get(0).getObja149()+eve.getObja149());
				      list_kpieve.get(0).setObja150(list_kpieve.get(0).getObja150()+eve.getObja150());
				      list_kpieve.get(0).setObja151(list_kpieve.get(0).getObja151()+eve.getObja151());
				      list_kpieve.get(0).setObja152(list_kpieve.get(0).getObja152()+eve.getObja152());
				      list_kpieve.get(0).setObja153(list_kpieve.get(0).getObja153()+eve.getObja153());
				      list_kpieve.get(0).setObja154(list_kpieve.get(0).getObja154()+eve.getObja154());
				      list_kpieve.get(0).setObja155(list_kpieve.get(0).getObja155()+eve.getObja155());
				      list_kpieve.get(0).setObja156(list_kpieve.get(0).getObja156()+eve.getObja156());
				      list_kpieve.get(0).setObja157(list_kpieve.get(0).getObja157()+eve.getObja157());
				      list_kpieve.get(0).setObja158(list_kpieve.get(0).getObja158()+eve.getObja158());
				      list_kpieve.get(0).setObja159(list_kpieve.get(0).getObja159()+eve.getObja159());
				      list_kpieve.get(0).setObja160(list_kpieve.get(0).getObja160()+eve.getObja160());
				      list_kpieve.get(0).setObja161(list_kpieve.get(0).getObja161()+eve.getObja161());
				      list_kpieve.get(0).setObja162(list_kpieve.get(0).getObja162()+eve.getObja162());
				      list_kpieve.get(0).setObja163(list_kpieve.get(0).getObja163()+eve.getObja163());
				      list_kpieve.get(0).setObja164(list_kpieve.get(0).getObja164()+eve.getObja164());
				      list_kpieve.get(0).setObja165(list_kpieve.get(0).getObja165()+eve.getObja165());
				      list_kpieve.get(0).setObja166(list_kpieve.get(0).getObja166()+eve.getObja166());
				      list_kpieve.get(0).setObja167(list_kpieve.get(0).getObja167()+eve.getObja167());
				      list_kpieve.get(0).setObja168(list_kpieve.get(0).getObja168()+eve.getObja168());
				      list_kpieve.get(0).setObja169(list_kpieve.get(0).getObja169()+eve.getObja169());
				      list_kpieve.get(0).setObja170(list_kpieve.get(0).getObja170()+eve.getObja170());
				      list_kpieve.get(0).setObja171(list_kpieve.get(0).getObja171()+eve.getObja171());
				      list_kpieve.get(0).setObja172(list_kpieve.get(0).getObja172()+eve.getObja172());
				      list_kpieve.get(0).setObja173(list_kpieve.get(0).getObja173()+eve.getObja173());
				      list_kpieve.get(0).setObja174(list_kpieve.get(0).getObja174()+eve.getObja174());
				      list_kpieve.get(0).setObja175(list_kpieve.get(0).getObja175()+eve.getObja175());
				      list_kpieve.get(0).setObja176(list_kpieve.get(0).getObja176()+eve.getObja176());
				      list_kpieve.get(0).setObja177(list_kpieve.get(0).getObja177()+eve.getObja177());
				      list_kpieve.get(0).setObja178(list_kpieve.get(0).getObja178()+eve.getObja178());
				      list_kpieve.get(0).setObja179(list_kpieve.get(0).getObja179()+eve.getObja179());
				      list_kpieve.get(0).setObja180(list_kpieve.get(0).getObja180()+eve.getObja180());
				      list_kpieve.get(0).setObja181(list_kpieve.get(0).getObja181()+eve.getObja181());
				      list_kpieve.get(0).setObja182(list_kpieve.get(0).getObja182()+eve.getObja182());
				      list_kpieve.get(0).setObja183(list_kpieve.get(0).getObja183()+eve.getObja183());
				      list_kpieve.get(0).setObja184(list_kpieve.get(0).getObja184()+eve.getObja184());
				      list_kpieve.get(0).setObja185(list_kpieve.get(0).getObja185()+eve.getObja185());
				      list_kpieve.get(0).setObja186(list_kpieve.get(0).getObja186()+eve.getObja186());
				      list_kpieve.get(0).setObja187(list_kpieve.get(0).getObja187()+eve.getObja187());
				      list_kpieve.get(0).setObja188(list_kpieve.get(0).getObja188()+eve.getObja188());
				      list_kpieve.get(0).setObja189(list_kpieve.get(0).getObja189()+eve.getObja189());
				      list_kpieve.get(0).setObja190(list_kpieve.get(0).getObja190()+eve.getObja190());
				      list_kpieve.get(0).setObja191(list_kpieve.get(0).getObja191()+eve.getObja191());
				      list_kpieve.get(0).setObja192(list_kpieve.get(0).getObja192()+eve.getObja192());
				      list_kpieve.get(0).setObja193(list_kpieve.get(0).getObja193()+eve.getObja193());
				      list_kpieve.get(0).setObja194(list_kpieve.get(0).getObja194()+eve.getObja194());
				      list_kpieve.get(0).setObja195(list_kpieve.get(0).getObja195()+eve.getObja195());
				      list_kpieve.get(0).setObja196(list_kpieve.get(0).getObja196()+eve.getObja196());
				      list_kpieve.get(0).setObja197(list_kpieve.get(0).getObja197()+eve.getObja197());
				      list_kpieve.get(0).setObja198(list_kpieve.get(0).getObja198()+eve.getObja198());
				      list_kpieve.get(0).setObja199(list_kpieve.get(0).getObja199()+eve.getObja199());
				      list_kpieve.get(0).setObja200(list_kpieve.get(0).getObja200()+eve.getObja200());
				      list_kpieve.get(0).setObja201(list_kpieve.get(0).getObja201()+eve.getObja201());
				      list_kpieve.get(0).setObja202(list_kpieve.get(0).getObja202()+eve.getObja202());	*/				  																																						
				}// end if				
				//合計				
					if(b==list_months.size()-1){
						list3_total=this.findResult(b,list_kpieve.get(0));
						list2_total.add(list3_total);
					}																																			
			}//end for2
			list1_all.add(list2_total);
		}// end for1
		
		
		//填充標題（放在循環外面）
		CellRangeAddress addTitle=new CellRangeAddress(0,(short)0,0,(short)list_column.size()-1);
		sheet.addMergedRegion(addTitle);
		sheet.createRow(0).createCell(0).setCellValue(title);
		sheet.getRow(0).getCell(0).setCellStyle(cs_head);
		for(int a=1;a<list_column.size();a++){
			sheet.getRow(0).createCell(a).setCellStyle(cs_head);
		}
		for(int k=0;k<list_factcode.size();k++){//start for1												
			WebFact fact=list_factcode.get(k);
			String factCode=fact.getId().getFactArea();
			
			//填充表頭(包括廠別狀態)
			sheet.createRow(2+cursor*k).createCell(0).setCellValue("形態:"+factCode);
			HSSFRow row_columnHead=sheet.createRow(3+cursor*k);			
			for(int h=0;h<list_column.size();h++){
				String column=list_column.get(h);
				HSSFCell cell_columnHead=row_columnHead.createCell(h);
				cell_columnHead.setCellValue(column);
				cell_columnHead.setCellStyle(cs_column);
				sheet.setColumnWidth(h, 3000);//列寬
			}
			//填充內容
			for(int i=0;i<list_months.size();i++){//start for2
				//獲取每個日期
				String ym=list_months.get(i);
				int temp_num=0;
				
				/*if(i==list_months.size()-1){
					temp_num=1;
				}*/
				switch(i){				
				case 0:					
					for(int j=0;j<list_str.size();j++){//start for3
						
						String index="";
						if(j+1<10){
							index="0"+(j+1);
						}else{
							index=""+(j+1);
						}
						HSSFRow row=sheet.createRow(4+j+cursor*k);
						HSSFCell cell0=row.createCell(i);
						HSSFCell cell1=row.createCell(i+1);
						HSSFCell cell2=row.createCell(i+2);
						cell0.setCellValue(index);
						cell0.setCellStyle(cs);
						cell1.setCellValue(list_str.get(j));
						cell1.setCellStyle(cs);
						cell2.setCellValue(list_unit.get(j));
						cell2.setCellStyle(cs);																	
					}//end for3
					break;								
				}
				VKpifactNew kpi=vkpinewSer.findById(factNo, factCode,ym);
				List<Double> list_content=new ArrayList<Double>();
				if(kpi!=null){// start if1
					BigDecimal big=new BigDecimal(0.00);
					if(kpi.getFactaddRate()==null){
						kpi.setFactaddRate(big);
					}
					if(kpi.getOutrequestRate()==null){
						kpi.setOutrequestRate(big);
					}
					if(kpi.getAvgPermoney()==null){
						kpi.setAvgPermoney(big);
					}
					if(kpi.getPermoney()==null){
						kpi.setPermoney(big);
					}
					if(kpi.getWasteFact()==null){
						kpi.setWasteFact(big);
					}
					if(kpi.getWasteNo()==null){
						kpi.setWasteNo(big);
					}
					if(kpi.getWasteUsd()==null){
						kpi.setWasteUsd(big);
					}
					if(kpi.getAvgCirclehour()==null){
						kpi.setAvgCirclehour(big);
					}
					list_content=this.VkpiFactToDouble(kpi);//VKpiFact對象各項封裝List<Double>集合
					
				}//end if1
													
				/**
				 * 補全"合計"統計
				 */
				List<List<Double>> list2=list1_all.get(k);//中間集合,每個中間集合"合計"數據
				List<Double>list3_total=list2.get(0);//合計							
				if(kpi!=null){	
					for(int j=0;j<list_str.size();j++){
						HSSFRow row=sheet.getRow(4+j+cursor*k);
						HSSFCell cell=row.createCell(i+3+temp_num);						
						HSSFCellStyle cs_temp=this.findStyle(wb, j,map);
						cell.setCellValue(list_content.get(j));
						cell.setCellStyle(cs_temp);																		
						if(i==list_months.size()-1){
							HSSFCell cell2=row.createCell(i+4+temp_num);//合計
							cell2.setCellValue(list3_total.get(j));
							cell2.setCellStyle(cs_temp);
						}
						
				    }				
				}else{
					for(int j=0;j<list_str.size();j++){
						HSSFRow row=sheet.getRow(4+j+cursor*k);
						HSSFCell cell=row.createCell(i+3+temp_num);						
						HSSFCellStyle cs_temp=this.findStyle(wb, j,map);
						cell.setCellValue("無數據");						
						cell.setCellStyle(cs_temp);												
						if(i==list_months.size()-1){							
							HSSFCell cell2=row.createCell(i+4+temp_num);//第一季度
							cell2.setCellValue(list3_total.get(j));
							cell2.setCellStyle(cs_temp);
						}						
					}
					
				}
				
			}//end for2
		}//end for1
		ServletOutputStream os=response.getOutputStream();
		response.setContentType("application/vnd.ms-excel");
		title=title+".xls";
		int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");//判斷是否為IE瀏覽器,大於0則為IE瀏覽器
		String fileName="";
		if(msie>0){
			fileName=java.net.URLEncoder.encode(title,"utf-8");//解決IE中文文件不能下載的問題
		}else{
			fileName=new String(title.getBytes("utf-8"),"iso-8859-1");//解決非IE中文名亂碼問題
		}		
		response.setHeader("Content-disposition", "attachment;filename="+fileName);
		wb.write(os);
		os.close();
	}
	
	
	public void print_tw() throws IOException{
		//創建模板
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet();
		/**
		 * 模板相關樣式
		 */
		Map<String,Object>map=this.findStyles(wb);
		//標題樣式
		HSSFCellStyle cs_head=(HSSFCellStyle)map.get("cs_head");		
		//標準單元格樣式
		HSSFCellStyle cs=(HSSFCellStyle)map.get("cs");		
		//表頭樣式
		HSSFCellStyle cs_column=(HSSFCellStyle)map.get("cs_column");
	
		//粗藍色字體		
		HSSFFont font_blue=wb.createFont();
		font_blue.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_blue.setFontHeightInPoints((short)12);
		font_blue.setColor(IndexedColors.BLUE.getIndex());
		
		
							
		/**
		 * 數據獲取
		 */
		//各項數據名稱和各項單位
		List<String>list_contentName=findListstrAndUnit().get(0);
		List<String>list_unit=findListstrAndUnit().get(1);
		
	
		//所有廠別狀態
		List<String>list_factCode=new ArrayList<String>();
		List<List<String>>list_factNo_all=new ArrayList<List<String>>();
		//如果頁面上有選factcode
        if(list_factcode!=null&&list_factcode.size()>0){
        	list_factCode=list_factcode;
			for(int i=0;i<list_factcode.size();i++){
				String factCode=list_factcode.get(i);
				List<String>list_factNo=new ArrayList<String>();
				for(int y=0;y<list_factno.size();y++){
					String temp=list_factno.get(y);
					String[]objs=temp.split("_");
					if(objs[0].equals(factCode)){
						list_factNo.add(objs[1]);
					}
				}
				list_factNo_all.add(list_factNo);
			}
		//如果沒有沒有,則默認全部	
		}else{
			List<Object[]>list_objs=webFactSer.findAllFactCode2_showA();
			for(int i=0;i<list_objs.size();i++){
				Object[]obj=list_objs.get(i);
				String fact_code=obj[0].toString();
				list_factCode.add(fact_code);
			}
		}
		
		for(int a=0;a<list_factCode.size();a++){//start for1
			//每個狀態所包含的廠別
			String factCode=list_factCode.get(a);
			List<String>list_factName=new ArrayList<String>();
			List<String>list_factNo=new ArrayList<String>();
			//如果頁面上有選擇廠別
			if(list_factno!=null&&list_factno.size()>0){
				list_factNo=list_factNo_all.get(a);
				for(int i=0;i<list_factNo.size();i++){
					String factno=list_factNo.get(i);
					String factname=webFactSer.selByid(factno);
					list_factName.add(factname);
				}
			//如果頁面上沒有選擇廠別,則默認全部
			}else{
				List<WebFact>list_webfact=webFactSer.findFactByFactCode_showA(factCode);
				for(int b=0;b<list_webfact.size();b++){
					WebFact fact=list_webfact.get(b);
					String factno=fact.getId().getFactNo();
					String factSname=fact.getFactSname();
					list_factName.add(factSname);
					list_factNo.add(factno);
				}
			}			
			list_factName.add(0,"項次");
			list_factName.add(1,"管制指標");
			list_factName.add(2,"單位");
			list_factNo.add(0,"項次");
			list_factNo.add(1,"管制指標");
			list_factNo.add(2,"單位");
			//填充各個表的廠別狀態和表頭
			HSSFRow row_factCode=sheet.createRow(2+cursor*a);
			HSSFRow row_column=sheet.createRow(3+cursor*a);
			HSSFCell cell_factCode=row_factCode.createCell(0);
			cell_factCode.setCellValue(factCode);
			cell_factCode.getCellStyle().setFont(font_blue);
			for(int a_1=0;a_1<list_factName.size();a_1++){				
				String column=list_factName.get(a_1);
				HSSFCell cell=row_column.createCell(a_1);
				cell.setCellValue(column);
				cell.setCellStyle(cs_column);
				sheet.setColumnWidth(a_1, 3000);//列寬
			}
			
			//填充內容
			for(int b=0;b<list_factNo.size();b++){//start for2			
				String factNo=list_factNo.get(b);
				VKpifactNew kpi=vkpinewSer.findById(factNo, factCode, yymm);
				List<Double>list_kpi=new ArrayList<Double>();
				if(kpi!=null){
					BigDecimal big=new BigDecimal(0.00);
					if(kpi.getFactaddRate()==null){
						kpi.setFactaddRate(big);
					}
					if(kpi.getOutrequestRate()==null){
						kpi.setOutrequestRate(big);
					}
					if(kpi.getAvgPermoney()==null){
						kpi.setAvgPermoney(big);
					}
					if(kpi.getPermoney()==null){
						kpi.setPermoney(big);
					}
					if(kpi.getWasteUsd()==null){
						kpi.setWasteUsd(big);
					}
					if(kpi.getWasteFact()==null){
						kpi.setWasteFact(big);
					}
					if(kpi.getWasteNo()==null){
						kpi.setWasteNo(big);
					}
					if(kpi.getAvgCirclehour()==null){
						kpi.setAvgCirclehour(big);
					}
					list_kpi=this.VkpiFactToDouble(kpi);					
				}				
				for(int c=0;c<list_contentName.size();c++){//start for3
					HSSFRow row=null;
					if(b==0){
						row=sheet.createRow(4+c+cursor*a);
					}else{
						row=sheet.getRow(4+c+cursor*a);
					}					
					switch(b){
					case 0:	                   
						String index="";
						if(c+1<10){
							index="0"+(c+1);
						}else{
							index=""+(c+1);
						}
						HSSFCell cell_0=row.createCell(b);
						cell_0.setCellValue(index);
						cell_0.setCellStyle(cs);						
	                   break;
					case 1:
						String columnName=list_contentName.get(c);
						HSSFCell cell_1=row.createCell(b);
						cell_1.setCellValue(columnName);
						cell_1.setCellStyle(cs);
						break;
					case 2:
						String unit=list_unit.get(c);
						HSSFCell cell_2=row.createCell(b);
						cell_2.setCellValue(unit);
						cell_2.setCellStyle(cs);
						break;
					default:																		
						HSSFCell cell_3=row.createCell(b);						
						if(kpi==null){
							cell_3.setCellValue("無數據");
						}else{
							Double kpi_content=list_kpi.get(c);
							cell_3.setCellValue(kpi_content);
						}
						HSSFCellStyle cs_temp=this.findStyle(wb, c,map);
						cell_3.setCellStyle(cs_temp);
						break;
					}
					
				}//end for3				
				
			}//end for2
		}//end for1
		
		/**
		 * 填充標題
		 */
		HSSFRow row_title=sheet.createRow(0);
		HSSFCell cell_title=row_title.createCell(0);
		cell_title.setCellValue(yymm+"各廠KPI比較資料");
		cell_title.setCellStyle(cs_head);
		CellRangeAddress addTitle=new CellRangeAddress(0,(short)0,0,(short)20);
		sheet.addMergedRegion(addTitle);
		for(int z=1;z<20;z++){
			HSSFCell cell=row_title.createCell(z);
			cell.setCellStyle(cs_head);
		}
		ServletOutputStream os=response.getOutputStream();		
		response.setContentType("application/vnd.ms-excel");
		String title=yymm+"各廠KPI比較資料"+".xls";
		String fileName="";
		int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");//判斷瀏覽器的類型,大於0則為IE瀏覽器
		if(msie>0){
			fileName=java.net.URLEncoder.encode(title,"utf-8");//解決IE中文文件不能下載的問題
		}else{
			fileName=new String(title.getBytes("utf-8"),"iso8859-1");//解決非IE中文名亂碼問題
		}
		response.setHeader("Content-disposition","attachment;filename="+fileName);
		wb.write(os);
		os.close();
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
	 * 獲取一個時間段的所有日期
	 * @param yymm_begin
	 * @param yymm_end
	 * @return
	 * @throws ParseException
	 */
	public List<String> getMonths(String yymm_begin,String yymm_end) throws ParseException{
		List<String>list=new ArrayList<String>();
		SimpleDateFormat format=new SimpleDateFormat("yyyyMM");
		Date date1=format.parse(yymm_begin);
		Date date2=format.parse(yymm_end);
		Calendar calendar1=Calendar.getInstance();
		Calendar calendar2=Calendar.getInstance();
		calendar1.setTime(date1);
		calendar2.setTime(date2);		
		while(calendar1.getTime().getTime()<calendar2.getTime().getTime()||calendar1.getTime().getTime()==calendar2.getTime().getTime()){	
			String result=format.format(calendar1.getTime());
			list.add(result);
			calendar1.add(Calendar.MONTH, 1);					
		}
		System.out.print(list);
		return list;					
	}
	
	/**
	 * 各項的項目與單位
	 * @Title: findListstrAndUnit
	 * @Description: TODO
	 * @param @return
	 * @return List<List<String>>
	 * @throws
	 * @author web
	 * @date 2016/3/18
	 */
	public List<List<String>>findListstrAndUnit(){
		List<List<String>>list_all=new ArrayList<List<String>>();
		List<String>list_str=new ArrayList<String>();
		List<String>list_unit=new ArrayList<String>();
		
		list_str.add("當月產量");
		list_unit.add("模");// 0 (0位小數) (0<目標)
		list_str.add("月均回轉 ");
		list_unit.add("回");// 1(1位小數) (1<目標)
		list_str.add("時迴轉");
		list_unit.add("模/H");// 2(1位小數) (2<目標)
		list_str.add("機臺利用率");
		list_unit.add("%");// (2位小數) (3>目標)
		list_str.add("產能達成率");
		list_unit.add("%");// (2位小數) (4<=目標)
		list_str.add("直工人均产能");
		list_unit.add("模/人");// (0位小數) (5<目標)
		list_str.add("全厂人均产能");
		list_unit.add("模/人");// (0位小數) (6<目標)
		list_str.add("全廠人均時產能");
		list_unit.add("模/H");// (2位小數) (7<目標)
		list_str.add("成倉庫存");
		list_unit.add("雙");// 5(整數)(0位小數) (8>目標)
		list_str.add("已出未請");
		list_unit.add("雙");// 6(整數)(0位小數) (9>目標)
		list_str.add("生產與請款差異率");
		list_unit.add("%"); // (2位小數) (10>目標)
		list_str.add("銷貨收入");
		list_unit.add("USD");// (0位小數) (11>目標)
		list_str.add("主材料成本比率");
		list_unit.add("%");// (2位小數) (12>目標)
		list_str.add("人工成本率");
		list_unit.add("%");// (2位) (13<目標)
		list_str.add("費用成本率");
		list_unit.add("%");// (2位) (14<目標)
		list_str.add("修繕單耗");
		list_unit.add("USD/模");// (4位小數) (15>目標)
		list_str.add("平均單價");
		list_unit.add("USD/雙");// (2位小數) (16>目標)
		list_str.add("全廠人均薪資");
		list_unit.add("USD/人");// (0位小數) (17<目標)
		list_str.add("人均產值");
		list_unit.add("USD/人");// (2位小數) (18<目標)
		list_str.add("人薪產值");
		list_unit.add("--");// (2位小數) (19<目標)
		list_str.add("全廠總損耗");
		list_unit.add("%");// (2位小數) (20>目標)
		list_str.add("無形損耗");
		list_unit.add("%"); // (2位小數) (21>目標)
		list_str.add("邊料率");
		list_unit.add("%");// (2位小數) (22>目標)
		list_str.add("不良率");
		list_unit.add("%");// (2位小數) (23<目標)
		list_str.add("報廢率");
		list_unit.add("%");// (2位小數) (24>目標)
		list_str.add("廠補率");
		list_unit.add("%");// (2位小數) (25>目標)
		list_str.add("水用量单耗");
		list_unit.add("噸/模");// (4位小數) (26>目標)
		list_str.add("用水金額單耗");
		list_unit.add("USD/模");// (4位小數) (27<目標)
		list_str.add("电度数单耗");
		list_unit.add("度/模");// (4位小數) (28>目標)
		list_str.add("用電金額單耗");
		list_unit.add("USD/模");// (4位小數) (29<目標)
		list_str.add("蒸汽用量單耗");
		list_unit.add("噸/模");// (4位小數) (30>目標)
		list_str.add("用汽金額單耗");
		list_unit.add("USD/模");// (4位小數) (31<目標)
		list_str.add("回頭料%");
		list_unit.add("%");// (2位小數) (32<目標)
		list_str.add("油壓退料%");
		list_unit.add("%");// (2位小數) (33<目標)
		list_str.add("回流率%");
		list_unit.add("%");// (2位小數) (34<目標)
		list_str.add("藥品用量單耗");
		list_unit.add("g/模");// (4位) (35<目標)
		list_str.add("色料用量單耗");
		list_unit.add("g/模");// （4位） (36<目標)
		list_str.add("離型劑金額單耗");
		list_unit.add("USD/模");// （4位） (37<目標)
		list_str.add("直間比");
		list_unit.add("--");// (2位小數) (38<目標)
		list_str.add("直工離職率");
		list_unit.add("%");// (2位小數) (39>目標)
		list_str.add("全廠離職率");
		list_unit.add("%");// (2位小數) (40>目標)
		list_str.add("工傷件數");
		list_unit.add("件");// (0位小數) (41>目標)
		
		list_all.add(list_str);
		list_all.add(list_unit);
		return list_all;
		
	}
	
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
	
	/**
	 * 數字樣式，以及是否達標時的樣式
	 * @Title: findStyle
	 * @Description: TODO
	 * @param @return
	 * @return Map<String,Object>
	 * @throws
	 * @author web
	 * @date 2016/3/18
	 */
	public HSSFCellStyle findStyle(HSSFWorkbook wb,Double num1,Double num2,int j,Map<String,Object>map){
		HSSFCellStyle cs_temp2=null;
		cs_temp2=findStyle(wb,j,map);		
		boolean flag=true;
		if((j>=0&&j<=7)||j==11||j==16||j==18||j==19||j==38){
			flag=num1<num2;
		}else{
			flag=num1>num2;
		}
		if(flag){//start if
			cs_temp2=null;
			cs_temp2=findStyle_red(wb, j,map);			
		}//end if
		return cs_temp2;
	}
	
	/**
	 * 數字樣式選擇(紅色字體)
	 * @Title: findStyle
	 * @Description: TODO
	 * @param @return
	 * @return Map<String,Object>
	 * @throws
	 * @author web
	 * @date 2016/3/18
	 */
	public HSSFCellStyle findStyle_red(HSSFWorkbook wb,int j,Map<String,Object>map){
		HSSFCellStyle cs_temp2=null;
		cs_temp2=(HSSFCellStyle)map.get("cs");		
			//數字格式的選擇
			if(j==0||j==5||j==6||j==8||j==9||j==11||j==17||j==41){
				cs_temp2=null;
				cs_temp2= (HSSFCellStyle)map.get("cs_poi_bg");
			}else if(j==1||j==2){
				cs_temp2=null;
				cs_temp2=(HSSFCellStyle)map.get("cs_poi1_bg");
			}else if(j==15||(j>=26&&j<=31)||(j>=35&&j<38)){
				cs_temp2=null;
				cs_temp2=(HSSFCellStyle)map.get("cs_poi4_bg");
			}else if(j==7||j==16||j==18||j==19||j==38){
				cs_temp2=null;
				cs_temp2=(HSSFCellStyle)map.get("cs_poi2_bg");				
			}else{
				cs_temp2=null;
				cs_temp2=(HSSFCellStyle)map.get("cs_percent_bg");
			}			
		return cs_temp2;
	}
	/**
	 * 數字樣式選擇(黑色字體)
	 * @Title: findStyle
	 * @Description: TODO
	 * @param @return
	 * @return Map<String,Object>
	 * @throws
	 * @author web
	 * @date 2016/3/18
	 */
	public HSSFCellStyle findStyle(HSSFWorkbook wb,int j,Map<String,Object> map){		
		HSSFCellStyle cs_temp2=null;
		cs_temp2=(HSSFCellStyle)map.get("cs");		
			//數字格式的選擇
			if(j==0||j==5||j==6||j==8||j==9||j==11||j==17||j==41){
				cs_temp2=null;
				cs_temp2= (HSSFCellStyle)map.get("cs_poi");
			}else if(j==1||j==2){
				cs_temp2=null;
				cs_temp2=(HSSFCellStyle)map.get("cs_poi1");
			}else if(j==15||(j>=26&&j<=31)||(j>=35&&j<38)){
				cs_temp2=null;
				cs_temp2=(HSSFCellStyle)map.get("cs_poi4");
			}else if(j==7||j==16||j==18||j==19||j==38){
				cs_temp2=null;
				cs_temp2=(HSSFCellStyle)map.get("cs_poi2");				
			}else{
				cs_temp2=null;
				cs_temp2=(HSSFCellStyle)map.get("cs_percent");
			}			
		return cs_temp2;
	}
	
	
	/**
	 * VKpiFact對象各項封裝List<Double>集合
	 * @return
	 */
	public List<Double> VkpiFactToDouble(VKpifactNew kpi){
		List<Double>list_content=new ArrayList<Double>();
		list_content.add(kpi.getThisYield().doubleValue());//1當月產量
		list_content.add(kpi.getAvgCircle().doubleValue());//2月均回轉
		list_content.add(kpi.getAvgCirclehour().doubleValue());//時迴轉
		list_content.add(kpi.getMutiRate().doubleValue());//機臺利用率
		list_content.add(kpi.getProductRate().doubleValue());//產能達成率
		list_content.add(kpi.getAvgZgpro().doubleValue());//直工人均产能
		list_content.add(kpi.getAvgPerpro().doubleValue());//全厂人均产能
		list_content.add(kpi.getAvgFactpro().doubleValue());//全廠人均時產能
		list_content.add(kpi.getStoreNum().doubleValue());//6  成倉庫存
		list_content.add(kpi.getOutRequest().doubleValue());//7 已出未請
		list_content.add(kpi.getOutrequestRate().doubleValue());//--null 生產與請款差異率
		list_content.add(kpi.getSlIncome());//銷貨收入
		list_content.add(kpi.getMainRate().doubleValue());//主材料成本比率
		list_content.add(kpi.getPcostRate().doubleValue());//人工成本率
		list_content.add(kpi.getCcostRate().doubleValue());//費用成本率
		list_content.add(kpi.getWasteUsd().doubleValue());//修繕單耗
		list_content.add(kpi.getPerPrice().doubleValue());//平均單價
		list_content.add(kpi.getPerSalar().doubleValue());//全廠人均薪資
		list_content.add(kpi.getAvgPermoney().doubleValue());//--null 人均產值
		list_content.add(kpi.getPermoney().doubleValue());//--null  人薪產值
		list_content.add(kpi.getWasteFact().doubleValue());//全廠總損耗
		list_content.add(kpi.getWasteNo().doubleValue());//無形損耗
		list_content.add(kpi.getSideRate().doubleValue());//邊料率
		list_content.add(kpi.getUhealRate().doubleValue());//不良率
		list_content.add(kpi.getWasteRate().doubleValue());//報廢率									
		list_content.add(kpi.getFactaddRate().doubleValue());//--null 廠補率
		list_content.add(kpi.getWaterTon().doubleValue());//水用量单耗
		list_content.add(kpi.getWaterUsd().doubleValue());//用水金額單耗
		list_content.add(kpi.getLightDu().doubleValue());//电度数单耗
		list_content.add(kpi.getLightUsd().doubleValue());//用電金額單耗
		list_content.add(kpi.getGasTon().doubleValue());//蒸汽用量單耗
		list_content.add(kpi.getGasUsd().doubleValue());//用汽金額單耗
		list_content.add(kpi.getBheadRate().doubleValue());//回頭料%
		list_content.add(kpi.getBpreRate().doubleValue());//油壓退料%
		list_content.add(kpi.getBflowRate().doubleValue());//回流率%
		list_content.add(kpi.getDrugWast().doubleValue());//藥品用量單耗
		list_content.add(kpi.getClrWast().doubleValue());//色料用量單耗
		list_content.add(kpi.getLeaveUsd().doubleValue());//離型劑金額單耗
		list_content.add(kpi.getZjRate().doubleValue());//直間比		
		list_content.add(kpi.getZgleaveRate().doubleValue());//直工離職率
		list_content.add(kpi.getFactleaveRate().doubleValue());//全廠離職率
		list_content.add(kpi.getHurtNum().doubleValue());//24  //工傷件數
		return list_content;
	}
	
	/**
	 * KpiFact目標各項封裝到List<Double>
	 * @param kpi_pur
	 * @return
	 */
	public List<Double> kpiFactToDouble(Kpifact kpi_pur){
		List<Double>list_content_pur=new ArrayList<Double>();
		list_content_pur.add(kpi_pur.getThisYield()==null?0:kpi_pur.getThisYield());//當月產量
		list_content_pur.add(kpi_pur.getAvgCircle()==null?0:kpi_pur.getAvgCircle());//月均回轉
		list_content_pur.add(kpi_pur.getAvgCirclehour()==null?0:kpi_pur.getAvgCirclehour());//時回轉
		list_content_pur.add(kpi_pur.getMutiRate()==null?0:kpi_pur.getMutiRate());//機臺利用率
		list_content_pur.add(kpi_pur.getProductRate()==null?0:kpi_pur.getProductRate());//產能達成率
		list_content_pur.add(kpi_pur.getAvgZgpro()==null?0:kpi_pur.getAvgZgpro());//直工人均产能
		list_content_pur.add(kpi_pur.getAvgPerpro()==null?0:kpi_pur.getAvgPerpro());//全厂人均产能
		list_content_pur.add(kpi_pur.getAvgFactpro()==null?0:kpi_pur.getAvgFactpro());//全廠人均時產能
		list_content_pur.add(kpi_pur.getStoreNum()==null?0:kpi_pur.getStoreNum());//成倉庫存
		list_content_pur.add(kpi_pur.getOutRequest()==null?0:kpi_pur.getOutRequest());//已出未請
		list_content_pur.add(kpi_pur.getOutrequestRate()==null?0:kpi_pur.getOutrequestRate());//生產與請款差異率
		list_content_pur.add(kpi_pur.getSlIncome()==null?0:kpi_pur.getSlIncome());//銷貨收入
		list_content_pur.add(kpi_pur.getMainRate()==null?0:kpi_pur.getMainRate());//主材料成本比率
		list_content_pur.add(kpi_pur.getPcostRate()==null?0:kpi_pur.getPcostRate());//人工成本率
		list_content_pur.add(kpi_pur.getCcostRate()==null?0:kpi_pur.getCcostRate());//費用成本率
		list_content_pur.add(kpi_pur.getWasteUsd()==null?0:kpi_pur.getWasteUsd());//修繕單耗
		list_content_pur.add(kpi_pur.getPerPrice()==null?0:kpi_pur.getPerPrice());//平均單價
		list_content_pur.add(kpi_pur.getPerSalar()==null?0:kpi_pur.getPerSalar());//全廠人均薪資
		list_content_pur.add(kpi_pur.getAvgPermoney()==null?0:kpi_pur.getAvgPermoney());//人均產值
		list_content_pur.add(kpi_pur.getPermoney()==null?0:kpi_pur.getPermoney());//人薪產值
		list_content_pur.add(kpi_pur.getWasteFact()==null?0:kpi_pur.getWasteFact());//全廠總損耗
		list_content_pur.add(kpi_pur.getWasteNo()==null?0:kpi_pur.getWasteNo());//無形損耗
		list_content_pur.add(kpi_pur.getSideRate()==null?0:kpi_pur.getSideRate());//邊料率
		list_content_pur.add(kpi_pur.getUhealRate()==null?0:kpi_pur.getUhealRate());//不良率
		list_content_pur.add(kpi_pur.getWasteRate()==null?0:kpi_pur.getWasteRate());//報廢率
		list_content_pur.add(kpi_pur.getFactaddRate()==null?0:kpi_pur.getFactaddRate());//廠補率
		list_content_pur.add(kpi_pur.getWaterTon()==null?0:kpi_pur.getWaterTon());//水用量单耗
		list_content_pur.add(kpi_pur.getWasteUsd()==null?0:kpi_pur.getWasteUsd());//用水金額單耗
		list_content_pur.add(kpi_pur.getLightDu()==null?0:kpi_pur.getLightDu());//电度数单耗
		list_content_pur.add(kpi_pur.getLightUsd()==null?0:kpi_pur.getLightUsd());//用電金額單耗
		list_content_pur.add(kpi_pur.getGasTon()==null?0:kpi_pur.getGasTon());//蒸汽用量單耗
		list_content_pur.add(kpi_pur.getGasUsd()==null?0:kpi_pur.getGasUsd());//用汽金額單耗
		list_content_pur.add(kpi_pur.getBheadRate()==null?0:kpi_pur.getBheadRate());//回頭料%
		list_content_pur.add(kpi_pur.getBpreRate()==null?0:kpi_pur.getBpreRate());//油壓退料%
		list_content_pur.add(kpi_pur.getBflowRate()==null?0:kpi_pur.getBflowRate());//回流率%
		list_content_pur.add(kpi_pur.getDrugWast()==null?0:kpi_pur.getDrugWast());//藥品用量單耗
		list_content_pur.add(kpi_pur.getClrWast()==null?0:kpi_pur.getClrWast());//色料用量單耗
		list_content_pur.add(kpi_pur.getLeaveUsd()==null?0:kpi_pur.getLeaveUsd());//離型劑金額單耗
		list_content_pur.add(kpi_pur.getZjRate()==null?0:kpi_pur.getZjRate());//直間比		
		list_content_pur.add(kpi_pur.getZgleaveRate()==null?0:kpi_pur.getZgleaveRate());//直工離職率
		list_content_pur.add(kpi_pur.getFactleaveRate()==null?0:kpi_pur.getFactleaveRate());//全廠離職率
		list_content_pur.add(kpi_pur.getHurtNum()==null?0:kpi_pur.getHurtNum());//工傷件數	
		return list_content_pur;
	}
	public List<Double> findResult(int index,VKpifactEveNew eve
			) {
		if(index==0){
			index=1;
		}
		DecimalFormat format=new DecimalFormat("####0");
		DecimalFormat format1=new DecimalFormat("####0.0");
		DecimalFormat format2=new DecimalFormat("####0.00");
		DecimalFormat format4=new DecimalFormat("####0.0000");
		List<Double>list=new ArrayList<Double>();
		//當月產量
		Double thisYield=Double.valueOf(format1.format(eve.getSumActualdemo().doubleValue()/index));//
		//月均回轉Double avgCircle=this.division(sumActualdemo, sumEverydemo);
		Double avgCircle=Double.valueOf(format.format(this.division(eve.getSumActualdemo().doubleValue(), eve.getSumEverydemo().doubleValue())));
		//時回轉Double avgCirclehour=this.division(sumActualdemo, sumWorkhours);
		Double avgCirclehour=Double.valueOf(format1.format(this.division(eve.getSumActualdemo().doubleValue(), eve.getSumWorkhours())));
		//機臺利用率Double muti_rate=sumEverydemo/sum_workday/hole;
		Double muti_rate=Double.valueOf(format4.format(this.division(eve.getSumEverydemo().doubleValue(),eve.getHole()*eve.getSumWorkdays().doubleValue())));
		//產能達成率Double productRate=this.division(sumActualdemo, sumStandarddemo);
		Double productRate=Double.valueOf(format4.format(this.division( eve.getSumActualdemo().doubleValue(),eve.getSumStandarddemo().doubleValue())));
		//直工人均产能Double avgZgpro=this.division(sumActualdemo, personzg);
		Double avgZgpro=Double.valueOf(format.format(this.division( eve.getSumActualdemo().doubleValue(),eve.getObja119())));
		//全厂人均产能Double avgPerpro=this.division(sumActualdemo, (personzg+personjg));
		Double avgPerpro=Double.valueOf(format.format(this.division( eve.getSumActualdemo().doubleValue(), (eve.getObja119()+eve.getObja120()))));
		//全廠人均時產能Double avgFactpro=this.division(sumActualdemo, (timezg+timejg+addtimezg+addtimejg));
		Double avgFactpro=Double.valueOf(format2.format(this.division( eve.getSumActualdemo().doubleValue(), ( eve.getObja121()+eve.getObja122() +eve.getObja123()+eve.getObja124()))));
		//成倉庫存Double storeNum=storenum/index;
		Double storeNum=Double.valueOf(format.format(eve.getObja169()/index));
		//已出未請Double outRequest=outnum/index;
		Double outRequest=Double.valueOf(format.format( eve.getObja172()/index));
		//生產與請款差異率Double outrequestRate=this.division(minusnum, instorenum);
		Double outrequestRate=Double.valueOf(format4.format(this.division( eve.getObja175(),eve.getObja174())));
		//銷貨收入Double sl_income=sellcount/index;
		Double sl_income=Double.valueOf(format4.format(eve.getObja100()/index));	
		//主材料成本比率Double mainRate=this.division(costcount, sellcount);
		Double mainRate=Double.valueOf(format4.format(this.division(eve.getObja109(),eve.getObja100())));
		//人工成本率Double pcost_rate=(wagezgUsd+wagejgUsd)/sellcount;
		Double pcost_rate=Double.valueOf(format4.format(this.division(eve.getObja128()+eve.getObja129(), eve.getObja100())));
		//費用成本率Double ccost_rate=cashcount/sellcount;
		Double ccost_rate=Double.valueOf(format4.format(this.division(eve.getObja134(), eve.getObja100())));
		//修繕單耗Double wasteUsd=this.division(repairmoney, sumActualdemo);
		Double wasteUsd=Double.valueOf(format4.format(this.division(eve.getObja142()+eve.getObja143(),eve.getSumActualdemo().doubleValue())));
		//平均單價Double per_price=payPairs/invcount;
		Double per_price=Double.valueOf(format4.format(this.division(eve.getObja102(), eve.getObja101())));
		//全廠人均薪資Double per_salar=(wagezgUsd+wagejgUsd)/(personzg+personjg);
		Double per_salar=Double.valueOf(format4.format(this.division(eve.getObja128()+eve.getObja129(), eve.getObja119()+eve.getObja120())));
		//人均產值Double avgPermoney=this.division(productnum,(personzg+personjg));
		Double avgPermoney=Double.valueOf(format2.format(this.division(eve.getObja111(),(eve.getObja119()+eve.getObja120()))));
		//人薪產值Double permoney=this.division(productnum, (wagezgUsd+wagejgUsd));
		Double permoney=Double.valueOf(format2.format(this.division(eve.getObja111(), (eve.getObja128()+eve.getObja129()))));
		//全廠總損耗Double wasteFact=this.division((actlost-avgbuttomweight2-noglueweight), (avgbuttomweight2+noglueweight));
		Double wasteFact=Double.valueOf(format4.format(this.division((eve.getObja115()-eve.getObja117()-eve.getObja118()), (eve.getObja117()+eve.getObja118()))));
		//無形損耗Double wasteNo=this.division((actlost-avgbuttomweight2-noglueweight-sideweit-badweit-otherbadweight), (avgbuttomweight2+noglueweight));
		Double wasteNo=Double.valueOf(format4.format(this.division((eve.getObja115()-eve.getObja117()-eve.getObja118()-eve.getObja161()-eve.getObja165()-eve.getObja167()), (eve.getObja117()+eve.getObja118()))));
		//邊料率Double sideRate=this.division(sideweit, (avgbuttomweight2+sideweit+badweit));
		Double sideRate=Double.valueOf(format4.format(this.division(eve.getObja161(), ( eve.getObja116()+eve.getObja161()+eve.getObja165()+eve.getObja167()))));
		//不良率Double uheal_rate=badcount/(sumActualpairs+badcount);
		Double uheal_rate=Double.valueOf(format4.format(this.division(eve.getObja163(), eve.getSumActualpairs().doubleValue()+eve.getObja163())));
		//報廢率Double wasteRate=this.division(badweit, (avgbuttomweight2+sideweit+badweit));
		Double wasteRate=Double.valueOf(format4.format(this.division(eve.getObja161()+eve.getObja165()+eve.getObja167(), (eve.getObja116()+eve.getObja161()+eve.getObja165()+eve.getObja167()))));		
		//廠補率Double factaddRate=this.division(sumFactpairs, sumActualpairs);
		Double factaddRate=Double.valueOf(format4.format(this.division(eve.getSumFactpairs().doubleValue(),eve.getSumActualpairs().doubleValue())));		
		//水用量单耗Double waterTon=this.division(waterton, sumActualdemo);
		Double waterTon=Double.valueOf(format4.format(this.division(eve.getObja153(),eve.getSumActualdemo().doubleValue())));
		//用水金額單耗Double water_usd=waterusd/sumActualdemo;
		Double water_usd=Double.valueOf(format4.format(this.division(eve.getObja154(), eve.getSumActualdemo().doubleValue())));
		//电度数单耗Double lightDu=this.division(electricdu, sumActualdemo);
		Double lightDu=Double.valueOf(format4.format(this.division(eve.getObja155(),eve.getSumActualdemo().doubleValue())));
		//用電金額單耗Double light_usd=electricued/sumActualdemo;
		Double light_usd=Double.valueOf(format4.format(this.division(eve.getObja156(), eve.getSumActualdemo().doubleValue())));
		//蒸汽用量單耗Double gasTon=this.division(gaston, sumActualdemo);
		Double gasTon=Double.valueOf(format4.format(this.division( eve.getObja157(),eve.getSumActualdemo().doubleValue())));
		//用汽金額單耗Double gas_usd=gasusd/sumActualdemo;
		Double gas_usd=Double.valueOf(format4.format(this.division(eve.getObja158(), eve.getSumActualdemo().doubleValue())));				
		//回頭料%Double bhead_rate=backFeed/thickUsed;
		Double bhead_rate=Double.valueOf(format4.format(this.division(eve.getObja201(), eve.getObja200())));
		//油壓退料%Double bpre_rate=oilBack/thickUsed;
		Double bpre_rate=Double.valueOf(format4.format(this.division(eve.getObja202(), eve.getObja200())));
		//回流率%Double bflow_rate=(backFeed+oilBack)/thickUsed;
		Double bflow_rate=Double.valueOf(format4.format(this.division(eve.getObja201()+eve.getObja202(), eve.getObja200())));
		//藥品用量單耗Double drug_wast=drugsUsed/sumActualdemo;
		Double drug_wast=Double.valueOf(format4.format(this.division(eve.getObja185(), eve.getSumActualdemo().doubleValue())));
		//色料用量單耗Double clr_wast=colorUsed/sumActualdemo;
		Double clr_wast=Double.valueOf(format4.format(this.division(eve.getObja183(), eve.getSumActualdemo().doubleValue())));
		//離型劑金額單耗Double leave_usd=leaveMoney/sumActualdemo;
		Double leave_usd=Double.valueOf(format4.format(this.division(eve.getObja188(), eve.getSumActualdemo().doubleValue())));
		//直間比Double zjRate=this.division(personzg, personjg);
		Double zjRate=Double.valueOf(format2.format(this.division( eve.getObja119(),eve.getObja120())));	
		//直工離職率Double zgleaveRate=this.division(leavenumzg, personzg);
		Double zgleaveRate=Double.valueOf(format4.format(this.division(eve.getObja125(),eve.getObja119())));
		//全廠離職率Double factleaveRate=this.division((leavenumzg+leavenumjg), (personzg+personjg));
		Double factleaveRate=Double.valueOf(format4.format(this.division((eve.getObja125()+eve.getObja126()), (eve.getObja119()+eve.getObja120()))));
		//工傷件數Double hurtNum=hurtnum/index;
		Double hurtNum=Double.valueOf(format2.format(eve.getObja127()/index));
		
		
		
		list.add(thisYield);//當月產量
		list.add(avgCircle);//月均回轉
		list.add(avgCirclehour);//時迴轉
		list.add(muti_rate);//機臺利用率
		list.add(productRate);//產能達成率
		list.add(avgZgpro);//直工人均产能
		list.add(avgPerpro);//全厂人均产能
		list.add(avgFactpro);//全廠人均時產能
		list.add(storeNum);//成倉庫存
		list.add(outRequest);//已出未請
		list.add(outrequestRate);//生產與請款差異率
		list.add(sl_income);//銷貨收入
		list.add(mainRate);//主材料成本比率
		list.add(pcost_rate);//人工成本率
		list.add(ccost_rate);//費用成本率
		list.add(wasteUsd);//修繕單耗
		list.add(per_price);//平均單價
		list.add(per_salar);//全廠人均薪資
		list.add(avgPermoney);//人均產值
		list.add(permoney);//人薪產值
		list.add(wasteFact);//全廠總損耗
		list.add(wasteNo);//無形損耗
		list.add(sideRate);//邊料率
		list.add(uheal_rate);//不良率
		list.add(wasteRate);//報廢率					
		list.add(factaddRate);//廠補率		
		list.add(waterTon);//水用量单耗
		list.add(water_usd);//用水金額單耗
		list.add(lightDu);//电度数单耗
		list.add(light_usd);//用電金額單耗
		list.add(gasTon);//蒸汽用量單耗
		list.add(gas_usd);//用汽金額單耗
		list.add(bhead_rate);//回頭料%
		list.add(bpre_rate);//油壓退料%
		list.add(bflow_rate);//回流率%
		list.add(drug_wast);//藥品用量單耗
		list.add(clr_wast);//色料用量單耗
		list.add(leave_usd);//離型劑金額單耗						
		list.add(zjRate);//直間比		
		list.add(zgleaveRate);//直工離職率
		list.add(factleaveRate);//全廠離職率
		list.add(hurtNum);//工傷件數			
		return list;
	}
	
	/**
	 * 數據統計
	 * @Title: findTotal
	 * @Description: TODO
	 * @param @param list_a
	 * @param @param list_b
	 * @param @param list_c
	 * @param @param a
	 * @param @param b
	 * @param @param c
	 * @return void
	 * @throws
	 * @author web
	 * @date 2016/5/11
	 */
	public void findTotal(List<VKpifactEveNew>list_a,List<VKpifactEveNew>list_b,List<VKpifactEveNew>list_c,int a,int b,int c){
		  list_a.get(a).setSumEverydemo(list_b.get(b).getSumEverydemo().add(list_c.get(c).getSumEverydemo()));
	      list_a.get(a).setSumStandarddemo(list_b.get(b).getSumStandarddemo().add(list_c.get(c).getSumStandarddemo()));
	      list_a.get(a).setSumActualdemo(list_b.get(b).getSumActualdemo().add(list_c.get(c).getSumActualdemo()));
	      list_a.get(a).setSumActualpairs(list_b.get(b).getSumActualpairs().add(list_c.get(c).getSumActualpairs()));
	      list_a.get(a).setSumFactpairs(list_b.get(b).getSumFactpairs().add(list_c.get(c).getSumFactpairs()));
	      list_a.get(a).setHole(list_b.get(b).getHole()+list_c.get(c).getHole());						      
	      list_a.get(a).setObja100(list_b.get(b).getObja100()+list_c.get(c).getObja100());
	      list_a.get(a).setObja101(list_b.get(b).getObja101()+list_c.get(c).getObja101());
	      list_a.get(a).setObja102(list_b.get(b).getObja102()+list_c.get(c).getObja102());
	      list_a.get(a).setObja103(list_b.get(b).getObja103()+list_c.get(c).getObja103());
	      list_a.get(a).setObja104(list_b.get(b).getObja104()+list_c.get(c).getObja104());
	      list_a.get(a).setObja105(list_b.get(b).getObja105()+list_c.get(c).getObja105());
	      list_a.get(a).setObja106(list_b.get(b).getObja106()+list_c.get(c).getObja106());
	      list_a.get(a).setObja107(list_b.get(b).getObja107()+list_c.get(c).getObja107());
	      list_a.get(a).setObja108(list_b.get(b).getObja108()+list_c.get(c).getObja108());
	      list_a.get(a).setObja109(list_b.get(b).getObja109()+list_c.get(c).getObja109());
	      list_a.get(a).setObja110(list_b.get(b).getObja110()+list_c.get(c).getObja110());
	      list_a.get(a).setObja111(list_b.get(b).getObja111()+list_c.get(c).getObja111());
	      list_a.get(a).setObja112(list_b.get(b).getObja112()+list_c.get(c).getObja112());
	      list_a.get(a).setObja113(list_b.get(b).getObja113()+list_c.get(c).getObja113());
	      list_a.get(a).setObja114(list_b.get(b).getObja114()+list_c.get(c).getObja114());
	      list_a.get(a).setObja115(list_b.get(b).getObja115()+list_c.get(c).getObja115());
	      list_a.get(a).setObja116(list_b.get(b).getObja116()+list_c.get(c).getObja116());
	      list_a.get(a).setObja117(list_b.get(b).getObja117()+list_c.get(c).getObja117());
	      list_a.get(a).setObja118(list_b.get(b).getObja118()+list_c.get(c).getObja118());
	      list_a.get(a).setObja119(list_b.get(b).getObja119()+list_c.get(c).getObja119());
	      list_a.get(a).setObja120(list_b.get(b).getObja120()+list_c.get(c).getObja120());
	      list_a.get(a).setObja121(list_b.get(b).getObja121()+list_c.get(c).getObja121());
	      list_a.get(a).setObja122(list_b.get(b).getObja122()+list_c.get(c).getObja122());
	      list_a.get(a).setObja123(list_b.get(b).getObja123()+list_c.get(c).getObja123());
	      list_a.get(a).setObja124(list_b.get(b).getObja124()+list_c.get(c).getObja124());
	      list_a.get(a).setObja125(list_b.get(b).getObja125()+list_c.get(c).getObja125());
	      list_a.get(a).setObja126(list_b.get(b).getObja126()+list_c.get(c).getObja126());
	      list_a.get(a).setObja127(list_b.get(b).getObja127()+list_c.get(c).getObja127());
	      list_a.get(a).setObja128(list_b.get(b).getObja128()+list_c.get(c).getObja128());
	      list_a.get(a).setObja129(list_b.get(b).getObja129()+list_c.get(c).getObja129());
	      list_a.get(a).setObja130(list_b.get(b).getObja130()+list_c.get(c).getObja130());
	      list_a.get(a).setObja131(list_b.get(b).getObja131()+list_c.get(c).getObja131());
	      list_a.get(a).setObja132(list_b.get(b).getObja132()+list_c.get(c).getObja132());
	      list_a.get(a).setObja133(list_b.get(b).getObja133()+list_c.get(c).getObja133());
	      list_a.get(a).setObja134(list_b.get(b).getObja134()+list_c.get(c).getObja134());
	      list_a.get(a).setObja135(list_b.get(b).getObja135()+list_c.get(c).getObja135());
	      list_a.get(a).setObja136(list_b.get(b).getObja136()+list_c.get(c).getObja136());
	      list_a.get(a).setObja137(list_b.get(b).getObja137()+list_c.get(c).getObja137());
	      list_a.get(a).setObja138(list_b.get(b).getObja138()+list_c.get(c).getObja138());
	      list_a.get(a).setObja139(list_b.get(b).getObja139()+list_c.get(c).getObja139());
	      list_a.get(a).setObja140(list_b.get(b).getObja140()+list_c.get(c).getObja140());
	      list_a.get(a).setObja141(list_b.get(b).getObja141()+list_c.get(c).getObja141());
	      list_a.get(a).setObja142(list_b.get(b).getObja142()+list_c.get(c).getObja142());
	      list_a.get(a).setObja143(list_b.get(b).getObja143()+list_c.get(c).getObja143());
	      list_a.get(a).setObja144(list_b.get(b).getObja144()+list_c.get(c).getObja144());
	      list_a.get(a).setObja145(list_b.get(b).getObja145()+list_c.get(c).getObja145());
	      list_a.get(a).setObja146(list_b.get(b).getObja146()+list_c.get(c).getObja146());
	      list_a.get(a).setObja147(list_b.get(b).getObja147()+list_c.get(c).getObja147());
	      list_a.get(a).setObja148(list_b.get(b).getObja148()+list_c.get(c).getObja148());
	      list_a.get(a).setObja149(list_b.get(b).getObja149()+list_c.get(c).getObja149());
	      list_a.get(a).setObja150(list_b.get(b).getObja150()+list_c.get(c).getObja150());
	      list_a.get(a).setObja151(list_b.get(b).getObja151()+list_c.get(c).getObja151());
	      list_a.get(a).setObja152(list_b.get(b).getObja152()+list_c.get(c).getObja152());
	      list_a.get(a).setObja153(list_b.get(b).getObja153()+list_c.get(c).getObja153());
	      list_a.get(a).setObja154(list_b.get(b).getObja154()+list_c.get(c).getObja154());
	      list_a.get(a).setObja155(list_b.get(b).getObja155()+list_c.get(c).getObja155());
	      list_a.get(a).setObja156(list_b.get(b).getObja156()+list_c.get(c).getObja156());
	      list_a.get(a).setObja157(list_b.get(b).getObja157()+list_c.get(c).getObja157());
	      list_a.get(a).setObja158(list_b.get(b).getObja158()+list_c.get(c).getObja158());
	      list_a.get(a).setObja159(list_b.get(b).getObja159()+list_c.get(c).getObja159());
	      list_a.get(a).setObja160(list_b.get(b).getObja160()+list_c.get(c).getObja160());
	      list_a.get(a).setObja161(list_b.get(b).getObja161()+list_c.get(c).getObja161());
	      list_a.get(a).setObja162(list_b.get(b).getObja162()+list_c.get(c).getObja162());
	      list_a.get(a).setObja163(list_b.get(b).getObja163()+list_c.get(c).getObja163());
	      list_a.get(a).setObja164(list_b.get(b).getObja164()+list_c.get(c).getObja164());
	      list_a.get(a).setObja165(list_b.get(b).getObja165()+list_c.get(c).getObja165());
	      list_a.get(a).setObja166(list_b.get(b).getObja166()+list_c.get(c).getObja166());
	      list_a.get(a).setObja167(list_b.get(b).getObja167()+list_c.get(c).getObja167());
	      list_a.get(a).setObja168(list_b.get(b).getObja168()+list_c.get(c).getObja168());
	      list_a.get(a).setObja169(list_b.get(b).getObja169()+list_c.get(c).getObja169());
	      list_a.get(a).setObja170(list_b.get(b).getObja170()+list_c.get(c).getObja170());
	      list_a.get(a).setObja171(list_b.get(b).getObja171()+list_c.get(c).getObja171());
	      list_a.get(a).setObja172(list_b.get(b).getObja172()+list_c.get(c).getObja172());
	      list_a.get(a).setObja173(list_b.get(b).getObja173()+list_c.get(c).getObja173());
	      list_a.get(a).setObja174(list_b.get(b).getObja174()+list_c.get(c).getObja174());
	      list_a.get(a).setObja175(list_b.get(b).getObja175()+list_c.get(c).getObja175());
	      list_a.get(a).setObja176(list_b.get(b).getObja176()+list_c.get(c).getObja176());
	      list_a.get(a).setObja177(list_b.get(b).getObja177()+list_c.get(c).getObja177());
	      list_a.get(a).setObja178(list_b.get(b).getObja178()+list_c.get(c).getObja178());
	      list_a.get(a).setObja179(list_b.get(b).getObja179()+list_c.get(c).getObja179());
	      list_a.get(a).setObja180(list_b.get(b).getObja180()+list_c.get(c).getObja180());
	      list_a.get(a).setObja181(list_b.get(b).getObja181()+list_c.get(c).getObja181());
	      list_a.get(a).setObja182(list_b.get(b).getObja182()+list_c.get(c).getObja182());
	      list_a.get(a).setObja183(list_b.get(b).getObja183()+list_c.get(c).getObja183());
	      list_a.get(a).setObja184(list_b.get(b).getObja184()+list_c.get(c).getObja184());
	      list_a.get(a).setObja185(list_b.get(b).getObja185()+list_c.get(c).getObja185());
	      list_a.get(a).setObja186(list_b.get(b).getObja186()+list_c.get(c).getObja186());
	      list_a.get(a).setObja187(list_b.get(b).getObja187()+list_c.get(c).getObja187());
	      list_a.get(a).setObja188(list_b.get(b).getObja188()+list_c.get(c).getObja188());
	      list_a.get(a).setObja189(list_b.get(b).getObja189()+list_c.get(c).getObja189());
	      list_a.get(a).setObja190(list_b.get(b).getObja190()+list_c.get(c).getObja190());
	      list_a.get(a).setObja191(list_b.get(b).getObja191()+list_c.get(c).getObja191());
	      list_a.get(a).setObja192(list_b.get(b).getObja192()+list_c.get(c).getObja192());
	      list_a.get(a).setObja193(list_b.get(b).getObja193()+list_c.get(c).getObja193());
	      list_a.get(a).setObja194(list_b.get(b).getObja194()+list_c.get(c).getObja194());
	      list_a.get(a).setObja195(list_b.get(b).getObja195()+list_c.get(c).getObja195());
	      list_a.get(a).setObja196(list_b.get(b).getObja196()+list_c.get(c).getObja196());
	      list_a.get(a).setObja197(list_b.get(b).getObja197()+list_c.get(c).getObja197());
	      list_a.get(a).setObja198(list_b.get(b).getObja198()+list_c.get(c).getObja198());
	      list_a.get(a).setObja199(list_b.get(b).getObja199()+list_c.get(c).getObja199());
	      list_a.get(a).setObja200(list_b.get(b).getObja200()+list_c.get(c).getObja200());
	      list_a.get(a).setObja201(list_b.get(b).getObja201()+list_c.get(c).getObja201());
	      list_a.get(a).setObja202(list_b.get(b).getObja202()+list_c.get(c).getObja202());		
	}
	

}
