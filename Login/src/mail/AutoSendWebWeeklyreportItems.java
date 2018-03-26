package mail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub	
		
		try{
			//this.init();
			ProjectConfig config=GlobalMethod.findProjectConfig();
			String ip=config.getpHostLoaclB();
			List<String>ips=GlobalMethod.findIp2();
			if(ips.size()==0){
				this.init();
			}else{
				for(int a=0;a<ips.size();a++){
					if(ips.get(a).equals(ip)){
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
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
			Calendar cal=Calendar.getInstance();
			cal.setFirstDayOfWeek(Calendar.MONDAY);
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			String sdate=sdf.format(cal.getTime());
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			String edate=sdf.format(cal.getTime());
			//Workbook wb=this.excel2003(ac,sdate,edate);
			Workbook wb=this.excel2007(ac,sdate, edate);
			//OutputStream os=new FileOutputStream("d:\\"+sdate+"~"+edate+".xlsx");
			//String filepath=ServletActionContext.getServletContext().getRealPath("TEMPFILES\\"+sdate+"-"+edate+".xlsx");報空指針
			String classes_path=Thread.currentThread().getContextClassLoader().getResource("").getPath();
			String filepath=classes_path.replace("/WEB-INF/classes","/TEMPFILES/"+sdate+"-"+edate+".xlsx");
			OutputStream os=new FileOutputStream(filepath);
			wb.write(os);
			os.close();
			
			IWebEmailService eSer =(IWebEmailService)ac.getBean("emailService");
			List<WebEmailAll> email = eSer.findEmail("E1");
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
			List<WebEmailAll> Cc = eSer.findCC("E1");
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
			send.sendmail(mail, cc, "業務每週報告匯總表("+sdate+"-"+edate+")", "", "業務每週報告匯總表("+sdate+"-"+edate+").xlsx", filepath);
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
		}
		
		
		
		
	}
	
	public HSSFWorkbook excel2003(ApplicationContext ac,String sdate,String edate){			
		IWebWeeklyreportServices webweeklyreportservices=(IWebWeeklyreportServices)ac.getBean("webweeklyreportservices");						
		List<WebWeeklyreport>list_obj=webweeklyreportservices.findByEdate(sdate);		
		
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet("sheet1");
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
		Map<String,Object>map=GlobalMethod.findStyles(wb);
		HSSFCellStyle cs_title=(HSSFCellStyle)map.get("cs_title");
		HSSFCellStyle cs=(HSSFCellStyle)map.get("cs");
		HSSFCellStyle cs_head=(HSSFCellStyle)map.get("cs_head");
		HSSFCellStyle cs_left=(HSSFCellStyle)map.get("cs_left");
		HSSFCellStyle cs_right=(HSSFCellStyle)map.get("cs_right");
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
		
		return wb;
	}
	
	public XSSFWorkbook excel2007(ApplicationContext ac,String sdate,String edate){		
		IWebWeeklyreportServices webweeklyreportservices=(IWebWeeklyreportServices)ac.getBean("webweeklyreportservices");						
		List<WebWeeklyreport>list_obj=webweeklyreportservices.findByEdate(sdate);		
		
		XSSFWorkbook wb=new XSSFWorkbook();
		XSSFSheet sheet=wb.createSheet("sheet1");
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
		Map<String,Object>map=GlobalMethod.findStyles2007(wb);
		XSSFCellStyle cs_title=(XSSFCellStyle)map.get("cs_title");
		XSSFCellStyle cs=(XSSFCellStyle)map.get("cs");
		XSSFCellStyle cs_head=(XSSFCellStyle)map.get("cs_head");
		XSSFCellStyle cs_left=(XSSFCellStyle)map.get("cs_left");
		XSSFCellStyle cs_right=(XSSFCellStyle)map.get("cs_right");
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
		
		return wb;
	}

}
