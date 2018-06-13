package mail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeUtility;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import action.AutoSendEmailAction;

import services.IWebEmailService;
import services.IWebFactorderServices;
import services.IWebUserService;
import services.IWebWeeklyreportServices;
import util.GlobalMethod;

import entity.WebEmailAll;
import entity.WebUser;
import entity.WebWeeklyreport;
import entity.custom.ProjectConfig;

public class AutoSendWebWeeklyreportItems extends QuartzJobBean{
	private static final ProjectConfig pc=GlobalMethod.findProjectConfig();

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub	
		
		try{
			//this.init();			
			List<String>ips=GlobalMethod.findIp2();
			if(ips.size()==0){
				this.init();
			}else{
				for(int a=0;a<ips.size();a++){
					if(ips.get(a).equals(pc.getpHostLoaclB())){
						this.init();
						break;
					}else if(a==ips.size()-1){
						System.out.println("本機不需要發送Email");
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
						
	}
	
	public void init(){
		try {	
			ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-dao.xml","spring-services.xml","spring-projectconfig.xml"});
			IWebWeeklyreportServices webweeklyreportservices=(IWebWeeklyreportServices)ac.getBean("webweeklyreportservices");			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
			Calendar cal_a=Calendar.getInstance();
			cal_a.setFirstDayOfWeek(Calendar.MONDAY);			
			cal_a.add(Calendar.DAY_OF_WEEK, -7);//注意：發送上周的報告
			cal_a.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			/*cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			String sdate_last=sdf.format(cal.getTime());
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			String edate_last=sdf.format(cal.getTime());									
			List<WebWeeklyreport>list_obj=webweeklyreportservices.findByEdate(sdate_last);						
			Map<String,Object>map_data=new HashMap<String,Object>();
			map_data.put(sdate_last+"_"+edate_last,list_obj);*/
			
			List<String>list_date=new ArrayList<String>();
			for(int i=0;i<4;i++){
				list_date.add(sdf.format(cal_a.getTime()));
				cal_a.add(Calendar.DAY_OF_MONTH, -7);
			}
			List<WebWeeklyreport>list_obj=webweeklyreportservices.findByEdate(list_date.get(3),list_date.get(0));
			Map<String,Object>map=new LinkedHashMap<String,Object>();
			for(String date:list_date){
				Calendar cal=Calendar.getInstance();
				cal.setTime(sdf.parse(date));
				cal.add(Calendar.DAY_OF_MONTH, 6);
				String edate=sdf.format(cal.getTime());
				List<WebWeeklyreport>objs=new ArrayList<WebWeeklyreport>();
				for(WebWeeklyreport obj:list_obj){
					if(obj.getSDate().equals(date)){
						objs.add(obj);
					}
				}
				map.put(date+"_"+edate,objs);
			}
											
			//Workbook wb=excel2003(map_data);
			Workbook wb=excel2007(map);
			//OutputStream os=new FileOutputStream("d:\\"+sdate+"~"+edate+".xlsx");
			//String filepath=ServletActionContext.getServletContext().getRealPath("TEMPFILES\\"+sdate+"-"+edate+".xlsx");報空指針
			String classes_path=Thread.currentThread().getContextClassLoader().getResource("").getPath();
			String filepath=classes_path.replace("/WEB-INF/classes","/TEMPFILES/"+list_date.get(3)+"-"+list_date.get(0)+".xlsx");
			OutputStream os=new FileOutputStream(filepath);
			wb.write(os);
			os.close();
			
			IWebEmailService eSer =(IWebEmailService)ac.getBean("emailService");
			List<WebEmailAll> email = eSer.findEmail(4,"0");
			String[] mail = new String[email.size()];
			for (int i = 0; i < email.size(); i++) {
				if (email.get(i).getUsername() != null&&!email.get(i).getUsername().equals("")) {						
					//mail[i] = MimeUtility.encodeText(email.get(i).getName())+ "<"+ email.get(i).getEmail()+ ">";
					//解決收件人中文亂碼的問題，因爲因爲數據是utf-8編碼
					mail[i] = MimeUtility.encodeText(email.get(i).getUsername(),"utf-8","Q")+ "<"+ email.get(i).getEmail()+ ">";
				} else {
					mail[i] = email.get(i).getEmail();
				}
			}		
			List<WebEmailAll> Cc = eSer.findEmail(4,"1");
			String[] cc = new String[Cc.size()];
			for (int j = 0; j < Cc.size(); j++) {
				if (Cc.get(j).getUsername() != null&&!Cc.get(j).getUsername().equals("")) {						
					//cc[j] = MimeUtility.encodeText(Cc.get(j).getName())+ "<" + Cc.get(j).getEmail() + ">";
					//解決收件人中文亂碼的問題，因爲因爲數據是utf-8編碼
					cc[j] = MimeUtility.encodeText(Cc.get(j).getUsername(),"utf-8","Q")+ "<" + Cc.get(j).getEmail() + ">";		
				} else {
					cc[j] = Cc.get(j).getEmail();
				}
			}
			AutoSendEmailAction send = new AutoSendEmailAction();
			send.sendmail(mail, cc, "業務最近4周報告匯總表("+list_date.get(3)+"-"+list_date.get(0)+")", "", "業務最近4周報告匯總表("+list_date.get(3)+"-"+list_date.get(0)+").xlsx", filepath);
			File file=new File(filepath);
			if(file.exists()){
				if(file.isFile()){
					file.delete();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public static void excel2003(HSSFSheet sheet,String sdate,String edate,List<WebWeeklyreport>list_obj,Map<String,Object>map_styles){
		//IWebWeeklyreportServices webweeklyreportservices=(IWebWeeklyreportServices)ac.getBean("webweeklyreportservices");						
		//List<WebWeeklyreport>list_obj=webweeklyreportservices.findByEdate(sdate);		
		for(int a=0;a<list_obj.size()+3;a++){
			sheet.createRow(a);			
			for(int b=0;b<5;b++){
				sheet.getRow(a).createCell(b);
				if(a==0){
					if(b<=1){
						sheet.setColumnWidth(b, 4000);
					}else{
						sheet.setColumnWidth(b, 15000);
					}
					
				}
			}
		}
		
		List<List<WebWeeklyreport>>list_all=new ArrayList<List<WebWeeklyreport>>();		
		for(int a=0;a<list_obj.size();a++){
			List<WebWeeklyreport>l1=new ArrayList<WebWeeklyreport>();
			l1.add(list_obj.get(a));
			for(int b=list_obj.size()-1;b>a;b--){
				if(list_obj.get(a).getWebUser().getId()==list_obj.get(b).getWebUser().getId()){
					l1.add(list_obj.get(b));
					list_obj.remove(b);
				}
			}
			list_all.add(l1);
			
		}						
		//Map<String,Object>map=GlobalMethod.findStyles(wb);
		HSSFCellStyle cs_title=(HSSFCellStyle)map_styles.get("cs_title");
		HSSFCellStyle cs=(HSSFCellStyle)map_styles.get("cs");
		HSSFCellStyle cs_head=(HSSFCellStyle)map_styles.get("cs_head");
		HSSFCellStyle cs_left=(HSSFCellStyle)map_styles.get("cs_left");
		HSSFCellStyle cs_right=(HSSFCellStyle)map_styles.get("cs_right");
		cs_left.setWrapText(true);
		cs_title.setAlignment(HSSFCellStyle.ALIGN_LEFT);		
		sheet.getRow(0).getCell(0).setCellValue("業務每週報告匯總表");
		for(int a=0;a<3;a++){
			sheet.getRow(0).getCell(0).setCellStyle(cs_title);
		}
		CellRangeAddress cra_title=new CellRangeAddress(0,0,0,2);
		sheet.addMergedRegion(cra_title);
		sheet.getRow(0).getCell(3).setCellValue("日期："+sdate+" ~ "+edate);
		sheet.getRow(0).getCell(3).setCellStyle(cs_right);
		
		List<String>list_head=new ArrayList<String>();
		list_head.add("業務");
		list_head.add("品牌");
		list_head.add("本周報告事項");
		list_head.add("上周報告事項");
		
		for(int a=0;a<list_head.size();a++){
			sheet.getRow(1).getCell(a).setCellValue(list_head.get(a));
			sheet.getRow(1).getCell(a).setCellStyle(cs_head);
		}
										
		int index_y=2;
		for(List<WebWeeklyreport> l1:list_all){			
			sheet.getRow(index_y).getCell(0).setCellValue(l1.get(0).getWebUser().getName());
			for(int b=0;b<l1.size();b++){
				sheet.getRow(index_y+b).getCell(0).setCellStyle(cs);
			}
			CellRangeAddress cra=new CellRangeAddress(index_y,index_y+l1.size()-1,0,0);
			sheet.addMergedRegion(cra);	
			
			for(int b=0;b<l1.size();b++){
				sheet.getRow(index_y+b).getCell(1).setCellValue(l1.get(b).getWebErpBrankProcess().getName());
				sheet.getRow(index_y+b).getCell(2).setCellValue(l1.get(b).getRContent());
				sheet.getRow(index_y+b).getCell(3).setCellValue(l1.get(b).getRContentLast());
				for(int c=1;c<4;c++){
					sheet.getRow(index_y+b).getCell(c).setCellStyle(cs_left);					
				}
				}
			
			index_y=index_y+l1.size();
			
		}
	}
	
	public static HSSFWorkbook excel2003(Map<String,Object>map_data){
		HSSFWorkbook wb=new HSSFWorkbook();
		Map<String,Object>map_styles=GlobalMethod.findStyles(wb);
		for(String date:map_data.keySet()){
			String sdate=date.split("_")[0];
			String edate=date.split("_")[1];
			List<WebWeeklyreport>list_obj=(List<WebWeeklyreport>)map_data.get(date);
			HSSFSheet sheet=wb.createSheet(sdate);
			excel2003(sheet,sdate,edate,list_obj,map_styles);
		}
		return wb;
	}
	
	public static void excel2007(XSSFSheet sheet,String sdate,String edate,List<WebWeeklyreport>list_obj,Map<String,Object>map_styles){
		for(int a=0;a<list_obj.size()+3;a++){
			sheet.createRow(a);			
			for(int b=0;b<5;b++){
				sheet.getRow(a).createCell(b);
				if(a==0){
					if(b<=1){
						sheet.setColumnWidth(b, 4000);
					}else{
						sheet.setColumnWidth(b, 15000);
					}
					
				}
			}
		}
		
		List<List<WebWeeklyreport>>list_all=new ArrayList<List<WebWeeklyreport>>();		
		for(int a=0;a<list_obj.size();a++){
			List<WebWeeklyreport>l1=new ArrayList<WebWeeklyreport>();
			l1.add(list_obj.get(a));
			for(int b=list_obj.size()-1;b>a;b--){
				if(list_obj.get(a).getWebUser().getId()==list_obj.get(b).getWebUser().getId()){
					l1.add(list_obj.get(b));
					list_obj.remove(b);
				}
			}
			list_all.add(l1);
			
		}						
		//Map<String,Object>map=GlobalMethod.findStyles2007(wb);
		XSSFCellStyle cs_title=(XSSFCellStyle)map_styles.get("cs_title");
		XSSFCellStyle cs=(XSSFCellStyle)map_styles.get("cs");
		XSSFCellStyle cs_head=(XSSFCellStyle)map_styles.get("cs_head");
		XSSFCellStyle cs_left=(XSSFCellStyle)map_styles.get("cs_left");
		XSSFCellStyle cs_right=(XSSFCellStyle)map_styles.get("cs_right");
		cs_left.setWrapText(true);	
		cs_title.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		sheet.getRow(0).getCell(0).setCellValue("業務每週報告匯總表");
		for(int a=0;a<3;a++){
			sheet.getRow(0).getCell(0).setCellStyle(cs_title);
		}
		CellRangeAddress cra_title=new CellRangeAddress(0,0,0,2);
		sheet.addMergedRegion(cra_title);
		sheet.getRow(0).getCell(3).setCellValue("日期："+sdate+" ~ "+edate);
		sheet.getRow(0).getCell(3).setCellStyle(cs_right);
		
		List<String>list_head=new ArrayList<String>();
		list_head.add("業務");
		list_head.add("品牌");
		list_head.add("本周報告事項");
		list_head.add("上周報告事項");
		
		for(int a=0;a<list_head.size();a++){
			sheet.getRow(1).getCell(a).setCellValue(list_head.get(a));
			sheet.getRow(1).getCell(a).setCellStyle(cs_head);
		}
								
		
		int index_y=2;
		for(List<WebWeeklyreport> l1:list_all){			
			sheet.getRow(index_y).getCell(0).setCellValue(l1.get(0).getWebUser().getName());
			for(int b=0;b<l1.size();b++){
				sheet.getRow(index_y+b).getCell(0).setCellStyle(cs);
			}
			CellRangeAddress cra=new CellRangeAddress(index_y,index_y+l1.size()-1,0,0);
			sheet.addMergedRegion(cra);	
			
			for(int b=0;b<l1.size();b++){
				sheet.getRow(index_y+b).getCell(1).setCellValue(l1.get(b).getWebErpBrankProcess().getName());
				sheet.getRow(index_y+b).getCell(2).setCellValue(l1.get(b).getRContent());
				sheet.getRow(index_y+b).getCell(3).setCellValue(l1.get(b).getRContentLast());
				for(int c=1;c<4;c++){
					sheet.getRow(index_y+b).getCell(c).setCellStyle(cs_left);					
				}
				}
			
			index_y=index_y+l1.size();
			
		}
		
	}
	
	public static XSSFWorkbook excel2007(Map<String,Object>map_data){		
		//IWebWeeklyreportServices webweeklyreportservices=(IWebWeeklyreportServices)ac.getBean("webweeklyreportservices");						
		//List<WebWeeklyreport>list_obj=webweeklyreportservices.findByEdate(sdate);		
		
		XSSFWorkbook wb=new XSSFWorkbook();
		Map<String,Object>map_styles=GlobalMethod.findStyles2007(wb);
		for(String date:map_data.keySet()){
			String sdate=date.split("_")[0];
			String edate=date.split("_")[1];
			List<WebWeeklyreport>list_obj=(List<WebWeeklyreport>)map_data.get(date);
			XSSFSheet sheet=wb.createSheet(sdate);
			excel2007(sheet,sdate,edate,list_obj,map_styles);
		}		
		return wb;
	}

}
