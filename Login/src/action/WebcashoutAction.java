package action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import services.IVWebydataawebcashoutServices;
import services.IWebCashoutServices;
import services.IWebEstProductServices;
import services.IWebFactServices;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyzExpectmatmLog;
import entity.VWebydataawebcashout;
import entity.WebFact;
import entity.WebUser;
import entity.Webcashout;

public class WebcashoutAction extends ActionSupport implements ServletResponseAware{
	private IWebCashoutServices cashoutSer;
	private IWebEstProductServices estProSer;
	private IWebFactServices webFactSer;
	private IVWebydataawebcashoutServices vcashoutSer;
	private String factNo;
	private String factCode;
	private String date;
	private String yymm;
	private String type;
	private int page;
	private PageBean bean;
	private Webcashout cashout;
	private HttpServletResponse response;
	private String ajaxResult;//申請函文時返回的ajax結果,   0:提交成功       1:提交失敗
	private String yymm2;
	private int backIndex;//返回標識      0或null:不走返回路徑         1:走返回路徑
	
	
	public int getBackIndex() {
		return backIndex;
	}


	public void setBackIndex(int backIndex) {
		this.backIndex = backIndex;
	}


	public String getYymm2() {
		return yymm2;
	}


	public void setYymm2(String yymm2) {
		this.yymm2 = yymm2;
	}


	public String getAjaxResult() {
		return ajaxResult;
	}


	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Webcashout getCashout() {
		return cashout;
	}


	public void setCashout(Webcashout cashout) {
		this.cashout = cashout;
	}

	public String getFactNo() {
		return factNo;
	}


	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}


	public String getFactCode() {
		return factCode;
	}


	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getYymm() {
		return yymm;
	}


	public void setYymm(String yymm) {
		this.yymm = yymm;
	}


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	public PageBean getBean() {
		return bean;
	}


	public void setBean(PageBean bean) {
		this.bean = bean;
	}


	public void setCashoutSer(IWebCashoutServices cashoutSer) {
		this.cashoutSer = cashoutSer;
	}
	
	
	public void setEstProSer(IWebEstProductServices estProSer) {
		this.estProSer = estProSer;
	}
	


	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}
	


	public void setVcashoutSer(IVWebydataawebcashoutServices vcashoutSer) {
		this.vcashoutSer = vcashoutSer;
	}


	public String add() throws ParseException{
		DateFormat fm=new SimpleDateFormat("yyyyMMdd");
		Date dt=fm.parse(date);
		String result=null;
		cashout.getId().setYymmdd(dt);
		try{
			cashoutSer.add(cashout);
			result="add";
			ajaxResult="0";
		}catch(Exception e){
			result="add";
			e.printStackTrace();
			ajaxResult="1";
		}		
		return result;
	}
	public String delete(){
		/*********************刪除記錄**************************/
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setObj("Webcashout");
		log.setFactCode(factCode);
		log.setFactNo(factNo);
		log.setYymm(yymm);
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		log.setUsername(user.getUsername());
		cashoutSer.delete(factNo, factCode, yymm,log);
		return "delete";
	}
	public String findPageBean(){
		//ActionContext.getContext().getApplication().clear();
		ActionContext.getContext().getSession().remove("public_factno");
		ActionContext.getContext().getSession().remove("public_yymm");
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		bean=cashoutSer.findPageBean(20,page, factNo, factCode, yymm);
		return "findPageBean";
	}
	public String findPageBean2(){
		//ActionContext.getContext().getApplication().clear();
		ActionContext.getContext().getSession().remove("public_factno");
		ActionContext.getContext().getSession().remove("public_yymm");
		bean=cashoutSer.findPageBean(20,page, factNo, factCode, yymm);
		ActionContext.getContext().getSession().put("public_factno", factNo);
		ActionContext.getContext().getSession().put("public_yymm", yymm);
		//ActionContext.getContext().getApplication().put("public_factCode", factCode);
		return "findPageBean1";
	}
	public String findPageBean3(){
		String result="findPageBean1";
		if(backIndex==1){
			result="findPageBean";
		}
		factNo=(String)ActionContext.getContext().getSession().get("public_factno");
		yymm=(String)ActionContext.getContext().getSession().get("public_yymm");
		//factCode=(String)ActionContext.getContext().getApplication().get("public_factCode");
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		bean=cashoutSer.findPageBean(20,page, factNo, factCode, yymm);
		return result;
	}
	public String findById(){
		cashout=cashoutSer.findById(factNo, factCode, yymm);
		return "findById";
	}
	
	
	/**
	 *合併單元格
	 * CellRangeAddress reg = new CellRangeAddress(y1,(short)y2,x1,(short)x2)
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public void report() throws IOException, ParseException{
		XSSFWorkbook book=new XSSFWorkbook();
		XSSFSheet sheet=book.createSheet("report");
		
        /*樣式*/
		
		//標題字體
		XSSFFont font_title=book.createFont();
		font_title.setFontHeightInPoints((short)20);
		font_title.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		//紅色字體
		XSSFFont font_red=book.createFont();
		font_red.setColor(IndexedColors.RED.getIndex());
		font_red.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		//綠色字體
		XSSFFont font_green=book.createFont();
		font_green.setColor(IndexedColors.GREEN.getIndex());
		font_green.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		//藍色字體
		XSSFFont font_blue=book.createFont();
		font_blue.setColor(IndexedColors.BLUE.getIndex());
		font_blue.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		//初始化樣式
		XSSFCellStyle cs_init=book.createCellStyle();
		cs_init.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_init.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_init.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_init.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		//標準樣式
		XSSFCellStyle cs=book.createCellStyle();
		cs.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		//標題樣式	
		XSSFCellStyle cs_title=book.createCellStyle();
		cs_title.setFont(font_title);
		cs_title.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_title.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		//紅色樣式
		XSSFCellStyle cs_red=book.createCellStyle();
		cs_red.setFont(font_red);
		cs_red.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_red.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_red.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_red.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_red.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_red.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		//綠色樣式
		XSSFCellStyle cs_green=book.createCellStyle();
		cs_green.setFont(font_green);
		cs_green.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_green.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_green.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_green.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_green.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_green.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		//藍色字體
		XSSFCellStyle cs_blue=book.createCellStyle();
		cs_blue.setFont(font_blue);
		cs_blue.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_blue.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_blue.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_blue.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_blue.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_blue.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

				
		//數字格式
		XSSFDataFormat format=book.createDataFormat();
		XSSFCellStyle cs_percent=book.createCellStyle();
		cs_percent.setDataFormat(format.getFormat("0.00%"));
		cs_percent.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cs_percent.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cs_percent.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_percent.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		cs_percent.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		
		/**(上部)數據源**/
		//(1)左列名
		List<String>list_col_top=new ArrayList<String>();
		list_col_top.add("廠別型態");
		list_col_top.add("預計生產雙數(雙)");
		list_col_top.add("預計請款雙數(雙)");
		list_col_top.add("預計請款金額(USD)");
		//(2)數據
		/*yymm="201507";
		factNo="all";*/
		
		//合計數據
		Double total_A=0.0;
		Double total_B=0.0;
		Double total_C=0.0;
		List<Object[]>list_top_all=new ArrayList<Object[]>();
		List<WebFact>list_fact=new ArrayList<WebFact>();
		if(factNo.equals("all")){
			list_fact=webFactSer.findAllFact_2();
		}else{
			list_fact=webFactSer.findFactById_show(factNo);
		}
		Object[]obj_temp=new Object[4];
		for(int i=0;i<list_fact.size();i++){
			String factno=list_fact.get(i).getId().getFactNo();
			String factcode=list_fact.get(i).getId().getFactArea();
			//自動判斷【預計生產】的類型
			List<String>list_type=estProSer.findtypeById(factno, factcode, yymm);
			if(list_type.size()==1){
				type=list_type.get(0);
			}
			if(list_type.size()==2){
				if(list_type.get(0).equals("tz")||list_type.get(1).equals("tz")){
					type="tz";
				}
			}
			if(list_type.size()==0){
				type="nothing";
			}
			
			Object[] obj=estProSer.reportWebCashout(factno, factcode, yymm,type);
			if(obj==null){
				obj=obj_temp;
			}else{
				total_A=total_A+(Double)obj[0];
				total_B=total_B+(Double)obj[1];
				total_C=total_C+(Double)obj[2];
			}
			list_top_all.add(obj);			
		}		
		for(int i=0;i<list_top_all.size();i++){
			
		}
		
						
		/***���D***/
		String title="";
		if(factNo.equals("all")){			
			title="所有工廠"+yymm+"生產與請款達成狀況統計分析表";
		}else{
			title="("+list_fact.get(0).getFactSname()+")"+yymm+"生產與請款達成狀況統計分析表";
		}		
		/**(下部)數據源**/
		//(1)日期(yyyy/MM)格式
		List<String>list_days=this.getDates(yymm).get(0);
		//(2)左列名
		List<String>list_col_buttom=new ArrayList<String>();
		for(int i=0;i<list_days.size();i++){
			list_col_buttom.add("當日實際數");
			list_col_buttom.add("累積數");
			list_col_buttom.add("累積達成%");
			list_col_buttom.add("累積欠數");
		}		
		//(3)表頭
		List<String>list_col_buttom2=new ArrayList<String>();
		List<String>list_unit=new ArrayList<String>();//單位		
		for(int i=0;i<list_fact.size();i++){
			list_col_buttom2.add("生產檢核");
			list_col_buttom2.add("交單雙數");
			list_col_buttom2.add("折算金額");
			list_col_buttom2.add("請款雙數");
			list_col_buttom2.add("請款金額");
			
			list_unit.add("雙");
			list_unit.add("雙");
			list_unit.add("USD");
			list_unit.add("雙");
			list_unit.add("USD");
		}
		//(4)數據
		List<String>list_days2=this.getDates(yymm).get(1);//日期(yyyyMMdd)格式
		List<List<VWebydataawebcashout>>list_vcashout_all=new ArrayList<List<VWebydataawebcashout>>();
		DateFormat fmt=new SimpleDateFormat("yyyyMMdd");
		for(int i=0;i<list_fact.size();i++){//start for1
			List<VWebydataawebcashout>list_vcashout=new ArrayList<VWebydataawebcashout>();
			String factno=list_fact.get(i).getId().getFactNo();
			String factcode=list_fact.get(i).getId().getFactArea();
			for(int j=0;j<list_days2.size();j++){//start for2
				String yymmdd=list_days2.get(j);
				long date=fmt.parse(yymmdd).getTime();	
				long today=fmt.parse(fmt.format(new Date())).getTime(); 
				if(date<today){
					VWebydataawebcashout vcashout=vcashoutSer.findByIdOne(factno, factcode, yymmdd);
					list_vcashout.add(vcashout);
				}else{
					break;
				}							
			}//end for2
			list_vcashout_all.add(list_vcashout);
		}//end for1
	    //(5)合計數據
		
		//內外層循環要反過來，外層循環天數，內循環廠別
			int a_length=list_vcashout_all.get(0).size();//外層循環次數,因為各個元素的長度都一親，故取第一個
			List<Double[]>list_total_top=new ArrayList<Double[]>();
			for(int i=0;i<a_length;i++){//外循環天數
				Double total_onModulus=0.0;
				Double total_realpairs=0.0;
				Double total_convertmoney=0.0;
				Double total_realcashoutpairs=0.0;
				Double total_realcashoutmoney=0.0;
				String yymmdd="null";
				if(list_vcashout_all.get(0).get(i)!=null){
					yymmdd=new SimpleDateFormat("yyyyMMdd").format(list_vcashout_all.get(0).get(i).getId().getYymmdd());//取出各個yymmdd
				}				
				for(int j=0;j<list_fact.size();j++){//內循環廠別
					String factno=list_fact.get(j).getId().getFactNo();
					String factcode=list_fact.get(j).getId().getFactArea();
					VWebydataawebcashout vcashout=vcashoutSer.findByIdOne(factno, factcode, yymmdd);
					if(vcashout!=null){
						total_onModulus=total_onModulus+(vcashout.getOnModulus()==null?0.0:vcashout.getOnModulus());
						total_realpairs=total_realpairs+(vcashout.getRealpairs()==null?0.0:vcashout.getRealpairs());
						total_convertmoney=total_convertmoney+(vcashout.getConvertmoney()==null?0.0:vcashout.getConvertmoney());
						total_realcashoutpairs=total_realcashoutpairs+(vcashout.getRealcashoutpairs()==null?0.0:vcashout.getRealcashoutpairs());
						total_realcashoutmoney=total_realcashoutmoney+(vcashout.getRealcashoutmoney()==null?0.0:vcashout.getRealcashoutmoney());
					}				
				}
				Double[]obj={total_onModulus,total_realpairs,total_convertmoney,total_realcashoutpairs,total_realcashoutmoney};
				list_total_top.add(obj);
			}
	
		
		


							
		/******開始打印*****/
		//初始化單元格
		int height_top=list_col_top.size()+1;
		int height_buttom=list_days.size()*4+2;
		int width=list_top_all.size()*5+2;
		int width_total=0;
		//if(factNo.equals("all")){
			width_total=5;
		//}
		int width_all=width+width_total;
		this.init(sheet,cs_init,height_top+height_buttom,width_all);
		
		XSSFCellStyle cs_temp=null;//臨時樣式
		
		//標題			
		CellRangeAddress reg_title = new CellRangeAddress(0 ,(short)0, 0, (short) width_all-1);//(y1,y2,x1,x2)
		sheet.addMergedRegion(reg_title);
		sheet.getRow(0).getCell(0).setCellValue(title);
		sheet.getRow(0).getCell(0).setCellStyle(cs_title);
				
		for(int i=1;i<list_top_all.size()+2;i++){
			sheet.getRow(0).getCell(i).setCellStyle(cs_title);
		}
		/*************y軸游標=1****************/	
		int index_y=1;							
		/**上部內容**/		
		//(1)左列名
		for(int x=0;x<list_col_top.size();x++){//start for1
			String col=list_col_top.get(x);
			CellRangeAddress reg= new CellRangeAddress(x+index_y,(short)x+index_y, 0, (short)1);
			sheet.addMergedRegion(reg);
			sheet.getRow(x+index_y).getCell(0).setCellValue(col);
			sheet.getRow(x+index_y).getCell(0).setCellStyle(cs);
			sheet.getRow(x+index_y).getCell(1).setCellStyle(cs);			
		}//end for1
		
		/*************y軸游標=1,x=2****************/
		//(2)數據
				
		for(int x=0;x<list_top_all.size();x++){//start for1
			Object[]obj=list_top_all.get(x);
			//String factno=list_fact.get(x).getId().getFactNo();
			String factcode=list_fact.get(x).getId().getFactArea();
			String factname=list_fact.get(x).getFactSname();
			String type=(String)obj[3];			
			if(type!=null){
				if(type.equals("zd")){
					type="(暫定)";
					cs_temp=cs_green;
				}
				if(type.equals("tz")){
					type="(調整)";
					cs_temp=cs_blue;
				}
			}else{
				type="(無數據)";
				cs_temp=cs_red;
			}
			
			CellRangeAddress reg = new CellRangeAddress(index_y ,(short)index_y, 2+5*x, (short)2+5*x+4);
			sheet.addMergedRegion(reg);
			sheet.getRow(index_y).getCell(2+5*x).setCellValue(factname+"-"+factcode+type);
			sheet.getRow(index_y).getCell(2+5*x).setCellStyle(cs_temp);
			for(int i=1;i<5;i++){
				sheet.getRow(index_y).getCell(2+5*x+i).setCellStyle(cs_temp);
			}
			
			/*************y軸游標=2,x=2****************/						
			for(int y=0;y<obj.length-1;y++){//注意,obj.length-1,因為obj中新增了一個type
				CellRangeAddress reg2= new CellRangeAddress(index_y+1+y ,(short)index_y+1+y, 2+5*x, (short)2+5*x+4);
				sheet.addMergedRegion(reg2);
				Double temp=(Double)obj[y];
				if(temp==null){
					//temp=0.0;
					cs_temp=cs_red;
					sheet.getRow(index_y+1+y).getCell(2+5*x).setCellValue("無數據");
				}else{
					cs_temp=cs;
					sheet.getRow(index_y+1+y).getCell(2+5*x).setCellValue(temp);										
				}								
				sheet.getRow(index_y+1+y).getCell(2+5*x).setCellStyle(cs_temp);
				for(int i=1;i<5;i++){
					sheet.getRow(index_y+1+y).getCell(2+5*x+i).setCellStyle(cs_temp);
				}				
			}//end for2						
		}//end for1
		/*************y軸游標=1,x=width****************/	
		//(3)�X�p		
		//if(factNo.equals("all")){
			Object[]obj_total={"合計",total_A,total_B,total_C};
			for(int i=0;i<obj_total.length;i++){
				CellRangeAddress reg=new CellRangeAddress(1+i,(short)1+i,width,(short)width+4);
				sheet.addMergedRegion(reg);
				if(i==0){
					sheet.getRow(1+i).getCell(width).setCellValue((String)obj_total[i]);
				}else{
					sheet.getRow(1+i).getCell(width).setCellValue((Double)obj_total[i]);
				}
				for(int j=0;j<5;j++){
					sheet.getRow(1+i).getCell(width+j).setCellStyle(cs);
					sheet.setColumnWidth(width+j, 3500);
				}
				
			}
		//}
				
		/**下部內容**/
		/*************y軸游標=6****************/
		//(1)【日期】，【項目】列名
		index_y=5;
		CellRangeAddress reg_date=new CellRangeAddress(index_y,(short)index_y+1,0,(short)0);
		sheet.addMergedRegion(reg_date);
		sheet.getRow(index_y).getCell(0).setCellValue("日期");
		sheet.getRow(index_y).getCell(0).setCellStyle(cs);
		sheet.getRow(index_y+1).getCell(0).setCellStyle(cs);
		
		CellRangeAddress reg_item=new CellRangeAddress(index_y,(short)index_y+1,1,(short)1);
		sheet.addMergedRegion(reg_item);
		sheet.getRow(index_y).getCell(1).setCellValue("項目");
		sheet.getRow(index_y).getCell(1).setCellStyle(cs);
		sheet.getRow(index_y+1).getCell(1).setCellStyle(cs);
		
		/*************y軸游標=7****************/
		//(2)日期
		index_y=7;
		for(int i=0;i<list_days.size();i++){
			String date=list_days.get(i);
			CellRangeAddress reg=new CellRangeAddress(index_y+4*i,(short)index_y+4*i+3,0,0);
			sheet.addMergedRegion(reg);
			sheet.getRow(index_y+4*i).getCell(0).setCellValue(date);
			for(int j=0;j<4;j++){
				sheet.getRow(index_y+4*i+j).getCell(0).setCellStyle(cs);
			}				
		}
		//(3)左列名
		for(int i=0;i<list_col_buttom.size();i++){
			sheet.setColumnWidth(1,4000);
			String col=list_col_buttom.get(i);
			sheet.getRow(index_y+i).getCell(1).setCellValue(col);
			sheet.getRow(index_y+i).getCell(1).setCellStyle(cs);
		}
		
		/*************y軸游標=5,x=2****************/
		//(4)表頭
		index_y=5;
		for(int i=0;i<list_col_buttom2.size();i++){
			sheet.setColumnWidth(i+2, 3500);
			String col=list_col_buttom2.get(i);
			String unit=list_unit.get(i);
			sheet.getRow(index_y).getCell(i+2).setCellValue(col);
			sheet.getRow(index_y).getCell(i+2).setCellStyle(cs);
			sheet.getRow(index_y+1).getCell(i+2).setCellValue(unit);
			sheet.getRow(index_y+1).getCell(i+2).setCellStyle(cs);
			
		}
		/*************y軸游標=7,x=2****************/
		//(5)數據
		index_y=7;		
		for(int i=0;i<list_vcashout_all.size();i++){//start for1
			List<VWebydataawebcashout>list_vcashout=list_vcashout_all.get(i);
			Object[]obj_top=list_top_all.get(i);
			Double D1=0.0;
			Double D2=0.0;
			Double D3=0.0;
			Double D4=0.0;
			Double D5=0.0;														
			for(int j=0;j<list_vcashout.size();j++){//start for2
				VWebydataawebcashout vcashout=list_vcashout.get(j);
				if(vcashout!=null){
					cs_temp=cs;
					Double onModulus=(vcashout.getOnModulus()==null)?0.0:vcashout.getOnModulus();
					Double realpairs=(vcashout.getRealpairs()==null)?0.0:vcashout.getRealpairs();
					Double convertmoney=(vcashout.getConvertmoney()==null)?0.0:vcashout.getConvertmoney();
					Double realcashoutpairs=(vcashout.getRealcashoutpairs()==null)?0.0:vcashout.getRealcashoutpairs();
					Double realcashoutmoney=(vcashout.getRealcashoutmoney()==null)?0.0:vcashout.getRealcashoutmoney();
					//String workorholiday=vcashout.getWorkorholiday();
														
					sheet.getRow(index_y+4*j).getCell(2+5*i).setCellValue(onModulus);
					sheet.getRow(index_y+4*j).getCell(3+5*i).setCellValue(realpairs);
					sheet.getRow(index_y+4*j).getCell(4+5*i).setCellValue(convertmoney);
					sheet.getRow(index_y+4*j).getCell(5+5*i).setCellValue(realcashoutpairs);
					sheet.getRow(index_y+4*j).getCell(6+5*i).setCellValue(realcashoutmoney);
					//sheet.getRow(8+4*j).getCell(6+5*i).setCellValue(workorholiday);
					
					D1=D1+onModulus;
					D2=D2+realpairs;
					D3=D3+convertmoney;
					D4=D4+realcashoutpairs;
					D5=D5+realcashoutmoney;
										
				}else{
					cs_temp=cs_red;
					sheet.getRow(index_y+4*j).getCell(2+5*i).setCellValue("無數據");
					sheet.getRow(index_y+4*j).getCell(3+5*i).setCellValue("無數據");
					sheet.getRow(index_y+4*j).getCell(4+5*i).setCellValue("無數據");
					sheet.getRow(index_y+4*j).getCell(5+5*i).setCellValue("無數據");
					sheet.getRow(index_y+4*j).getCell(6+5*i).setCellValue("無數據");				
					/*sheet.getRow(index_y+4*j).getCell(2+5*i).setCellValue(0);
					sheet.getRow(index_y+4*j).getCell(3+5*i).setCellValue(0);
					sheet.getRow(index_y+4*j).getCell(4+5*i).setCellValue(0);
					sheet.getRow(index_y+4*j).getCell(5+5*i).setCellValue(0);
					sheet.getRow(index_y+4*j).getCell(6+5*i).setCellValue(0);*/
					//sheet.getRow(8+4*j).getCell(6+5*i).setCellValue("無數據");
				}				
				sheet.getRow(index_y+4*j).getCell(2+5*i).setCellStyle(cs_temp);
				sheet.getRow(index_y+4*j).getCell(3+5*i).setCellStyle(cs_temp);
				sheet.getRow(index_y+4*j).getCell(4+5*i).setCellStyle(cs_temp);
				sheet.getRow(index_y+4*j).getCell(5+5*i).setCellStyle(cs_temp);
				sheet.getRow(index_y+4*j).getCell(6+5*i).setCellStyle(cs_temp);
				//sheet.getRow(8+4*j).getCell(6+5*i).setCellStyle(cs);
				
				Double[]dbl_D={D1,D2,D3,D4,D5};
								
				
				sheet.getRow(index_y+1+4*j).getCell(2+5*i).setCellValue(dbl_D[0]);
				sheet.getRow(index_y+1+4*j).getCell(3+5*i).setCellValue(dbl_D[1]);
				sheet.getRow(index_y+1+4*j).getCell(4+5*i).setCellValue(dbl_D[2]);
				sheet.getRow(index_y+1+4*j).getCell(5+5*i).setCellValue(dbl_D[3]);
				sheet.getRow(index_y+1+4*j).getCell(6+5*i).setCellValue(dbl_D[4]);
				if(obj_top[0]==null&&obj_top[1]==null&&obj_top[2]==null){
					sheet.getRow(index_y+2+4*j).getCell(2+5*i).setCellValue(0);
					sheet.getRow(index_y+2+4*j).getCell(3+5*i).setCellValue(0);
					sheet.getRow(index_y+2+4*j).getCell(4+5*i).setCellValue(0);
					sheet.getRow(index_y+2+4*j).getCell(5+5*i).setCellValue(0);
					sheet.getRow(index_y+2+4*j).getCell(6+5*i).setCellValue(0);
					sheet.getRow(index_y+3+4*j).getCell(2+5*i).setCellValue(0-dbl_D[0]);
					sheet.getRow(index_y+3+4*j).getCell(3+5*i).setCellValue(0-dbl_D[1]);
					sheet.getRow(index_y+3+4*j).getCell(4+5*i).setCellValue(0-dbl_D[2]);
					sheet.getRow(index_y+3+4*j).getCell(5+5*i).setCellValue(0-dbl_D[3]);
					sheet.getRow(index_y+3+4*j).getCell(6+5*i).setCellValue(0-dbl_D[4]);
				}else{
					sheet.getRow(index_y+2+4*j).getCell(2+5*i).setCellValue(dbl_D[0]/(Double)obj_top[0]);
					sheet.getRow(index_y+2+4*j).getCell(3+5*i).setCellValue(dbl_D[1]/(Double)obj_top[1]);
					sheet.getRow(index_y+2+4*j).getCell(4+5*i).setCellValue(dbl_D[2]/(Double)obj_top[2]);
					sheet.getRow(index_y+2+4*j).getCell(5+5*i).setCellValue(dbl_D[3]/(Double)obj_top[1]);
					sheet.getRow(index_y+2+4*j).getCell(6+5*i).setCellValue(dbl_D[4]/(Double)obj_top[2]);
					sheet.getRow(index_y+3+4*j).getCell(2+5*i).setCellValue((Double)obj_top[0]-dbl_D[0]);
					sheet.getRow(index_y+3+4*j).getCell(3+5*i).setCellValue((Double)obj_top[1]-dbl_D[1]);
					sheet.getRow(index_y+3+4*j).getCell(4+5*i).setCellValue((Double)obj_top[2]-dbl_D[2]);
					sheet.getRow(index_y+3+4*j).getCell(5+5*i).setCellValue((Double)obj_top[1]-dbl_D[3]);
					sheet.getRow(index_y+3+4*j).getCell(6+5*i).setCellValue((Double)obj_top[2]-dbl_D[4]);
				}																						
				sheet.getRow(index_y+1+4*j).getCell(2+5*i).setCellStyle(cs);
				sheet.getRow(index_y+1+4*j).getCell(3+5*i).setCellStyle(cs);
				sheet.getRow(index_y+1+4*j).getCell(4+5*i).setCellStyle(cs);
				sheet.getRow(index_y+1+4*j).getCell(5+5*i).setCellStyle(cs);
				sheet.getRow(index_y+1+4*j).getCell(6+5*i).setCellStyle(cs);
				sheet.getRow(index_y+2+4*j).getCell(2+5*i).setCellStyle(cs_percent);
				sheet.getRow(index_y+2+4*j).getCell(3+5*i).setCellStyle(cs_percent);
				sheet.getRow(index_y+2+4*j).getCell(4+5*i).setCellStyle(cs_percent);
				sheet.getRow(index_y+2+4*j).getCell(5+5*i).setCellStyle(cs_percent);
				sheet.getRow(index_y+2+4*j).getCell(6+5*i).setCellStyle(cs_percent);
				sheet.getRow(index_y+3+4*j).getCell(2+5*i).setCellStyle(cs);
				sheet.getRow(index_y+3+4*j).getCell(3+5*i).setCellStyle(cs);
				sheet.getRow(index_y+3+4*j).getCell(4+5*i).setCellStyle(cs);
				sheet.getRow(index_y+3+4*j).getCell(5+5*i).setCellStyle(cs);
				sheet.getRow(index_y+3+4*j).getCell(6+5*i).setCellStyle(cs);															
			}//end for2
			
		}//end for1
		
		/*************y軸游標=height_top,x=width****************/
		//(5)合計數據
		index_y=height_top;
		//if(factNo.equals("all")){
			for(int i=0;i<list_col_buttom2.size()/list_fact.size();i++){
				String col=list_col_buttom2.get(i);
				String unit=list_unit.get(i);
				sheet.getRow(index_y).getCell(width+i).setCellValue(col);
				sheet.getRow(index_y+1).getCell(width+i).setCellValue(unit);
				sheet.getRow(index_y).getCell(width+i).setCellStyle(cs);
				sheet.getRow(index_y+1).getCell(width+i).setCellStyle(cs);
			}
			Double total_D1=0.0;
			Double total_D2=0.0;
			Double total_D3=0.0;
			Double total_D4=0.0;
			Double total_D5=0.0;
			for(int i=0;i<list_total_top.size();i++){
				Double[]obj_total_buttom=list_total_top.get(i);
				total_D1=total_D1+obj_total_buttom[0];
				total_D2=total_D2+obj_total_buttom[1];
				total_D3=total_D3+obj_total_buttom[2];
				total_D4=total_D4+obj_total_buttom[3];
				total_D5=total_D5+obj_total_buttom[4];				
				for(int j=0;j<obj_total_buttom.length;j++){
					sheet.getRow(index_y+2+4*i).getCell(width+j).setCellValue(obj_total_buttom[j]);										
					sheet.getRow(index_y+2+4*i).getCell(width+j).setCellStyle(cs);										
				}
				sheet.getRow(index_y+3+4*i).getCell(width).setCellValue(total_D1);
				sheet.getRow(index_y+3+4*i).getCell(width+1).setCellValue(total_D2);
				sheet.getRow(index_y+3+4*i).getCell(width+2).setCellValue(total_D3);
				sheet.getRow(index_y+3+4*i).getCell(width+3).setCellValue(total_D4);
				sheet.getRow(index_y+3+4*i).getCell(width+4).setCellValue(total_D5);
				sheet.getRow(index_y+4+4*i).getCell(width).setCellValue(total_D1/total_A);
				sheet.getRow(index_y+4+4*i).getCell(width+1).setCellValue(total_D2/total_B);
				sheet.getRow(index_y+4+4*i).getCell(width+2).setCellValue(total_D3/total_C);
				sheet.getRow(index_y+4+4*i).getCell(width+3).setCellValue(total_D4/total_B);
				sheet.getRow(index_y+4+4*i).getCell(width+4).setCellValue(total_D5/total_C);
				sheet.getRow(index_y+5+4*i).getCell(width).setCellValue(total_A-total_D1);
				sheet.getRow(index_y+5+4*i).getCell(width+1).setCellValue(total_B-total_D2);
				sheet.getRow(index_y+5+4*i).getCell(width+2).setCellValue(total_C-total_D3);
				sheet.getRow(index_y+5+4*i).getCell(width+3).setCellValue(total_B-total_D4);
				sheet.getRow(index_y+5+4*i).getCell(width+4).setCellValue(total_C-total_D5);
				
				sheet.getRow(index_y+3+4*i).getCell(width).setCellStyle(cs);
				sheet.getRow(index_y+3+4*i).getCell(width+1).setCellStyle(cs);
				sheet.getRow(index_y+3+4*i).getCell(width+2).setCellStyle(cs);
				sheet.getRow(index_y+3+4*i).getCell(width+3).setCellStyle(cs);
				sheet.getRow(index_y+3+4*i).getCell(width+4).setCellStyle(cs);
				sheet.getRow(index_y+4+4*i).getCell(width).setCellStyle(cs_percent);
				sheet.getRow(index_y+4+4*i).getCell(width+1).setCellStyle(cs_percent);
				sheet.getRow(index_y+4+4*i).getCell(width+2).setCellStyle(cs_percent);
				sheet.getRow(index_y+4+4*i).getCell(width+3).setCellStyle(cs_percent);
				sheet.getRow(index_y+4+4*i).getCell(width+4).setCellStyle(cs_percent);
				sheet.getRow(index_y+5+4*i).getCell(width).setCellStyle(cs);
				sheet.getRow(index_y+5+4*i).getCell(width+1).setCellStyle(cs);
				sheet.getRow(index_y+5+4*i).getCell(width+2).setCellStyle(cs);
				sheet.getRow(index_y+5+4*i).getCell(width+3).setCellStyle(cs);
				sheet.getRow(index_y+5+4*i).getCell(width+4).setCellStyle(cs);
			}			
		//}
		
		
			

		
								
		ServletOutputStream os=response.getOutputStream();		
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		String fileName=yymm+".xlsx";;
		int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");//判斷瀏覽器的類型,大於0則為IE瀏覽器
		if(msie>0){
			fileName=java.net.URLEncoder.encode(fileName,"utf-8");//解決IE中文文件不能下載的問題
		}else{
			fileName=new String(fileName.getBytes("utf-8"),"iso8859-1");//解決非IE中文名亂碼問題
		}
		response.setHeader("Content-disposition","attachment;filename="+fileName);
		book.write(os);
		os.close();
		
		
		
				
		
		
	}


	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	
	/**
	 * 初始化單元格   
	 * @param sheet
	 * @param row_num(高)
	 * @param col_num(寬)
	 */
	public void init(XSSFSheet sheet,XSSFCellStyle cs,int row_num,int col_num){
		for(int i=0;i<row_num;i++){
			XSSFRow row=sheet.createRow(i);
			for(int j=0;j<col_num;j++){
				row.createCell(j).setCellStyle(cs);
			}
		}
	}
	
	public List<List<String>> getDates(String yymm){
		List<List<String>>list_all=new ArrayList<List<String>>();
		List<String>list=new ArrayList<String>();
		List<String>list2=new ArrayList<String>();
		int year=Integer.parseInt(yymm.substring(0, 4));
		int month=Integer.parseInt(yymm.substring(4))-1;
		Calendar ca=Calendar.getInstance();
		ca.set(year, month,1);
		int max=ca.getActualMaximum(Calendar.DAY_OF_MONTH);
		int min=ca.getActualMinimum(Calendar.DAY_OF_MONTH);
		for(int i=min;i<=max;i++){
			ca.set(year, month,i);
			list.add(new SimpleDateFormat("MM/dd").format(ca.getTime()));
			list2.add(new SimpleDateFormat("yyyyMMdd").format(ca.getTime()));
		}
		list_all.add(list);
		list_all.add(list2);
		return list_all;
	}
	

}
