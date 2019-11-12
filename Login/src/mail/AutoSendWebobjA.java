package mail;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import javax.mail.internet.MimeUtility;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.hibernate.transform.ToListResultTransformer;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import action.AutoSendEmailAction;

import entity.WebCc;
import entity.WebEmail;
import entity.WebEmailAll;
import entity.WebFact;
import entity.WebFactId;
import entity.WebydataNoinput;
import entity.WebydataNoinputId;
import entity.custom.ProjectConfig;

import services.IWebEmailService;
import services.IWebEstProductServices;
import services.IWebObjsAServices;
import services.IWebYieldDataServices;
import services.IWebydataNoinputServices;
import util.GlobalMethod;

/**
 * 系统启动时的监听类 初始化系统数据
 * 
 * @author jhoneder
 * 
 */
public class AutoSendWebobjA extends QuartzJobBean {
	private static final ProjectConfig pc=GlobalMethod.findProjectConfig();
	private String yymm;
	private String yymmdd;
	private String ajaxResult;//申請函文時返回的ajax結果,   0:提交成功       1:提交失敗
		
	
	public String getYymmdd() {
		return yymmdd;
	}

	public void setYymmdd(String yymmdd) {
		this.yymmdd = yymmdd;
	}

	public String getAjaxResult() {
		return ajaxResult;
	}

	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}

	public String getYymm() {
		return yymm;
	}

	public void setYymm(String yymm) {
		this.yymm = yymm;
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		// context销毁时，销毁初始化数据
	}	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		try{
			List<String> ips=GlobalMethod.findIp2();				
			if(ips.size()==0){
				this.init();
			}else{
				for(int i=0;i<ips.size();i++){
					if(ips.get(i).equals(pc.getpHostLoaclB())){
						this.init();
						break;
					}else if(i==ips.size()-1){
						System.out.println("本機不需要發送Email");
					}
				}
			}
			//this.init();
			
		}catch(Exception e){
			
		}	
	}
	
	public void init(){
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");		
		if(yymmdd==null||yymmdd.equals("")){
			Calendar calendar=Calendar.getInstance();
			//當每月的2號是星期一，也就是1號是星期天，則要減2天，以發送上個月全部產量資料
			if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.MONDAY){
				calendar.add(Calendar.DATE, -2);
			}else{
				calendar.add(Calendar.DATE, -1);
			}
			yymmdd=format.format(calendar.getTime());
			
		}
		try {
			Date bdate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(bdate);
			/*if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY|| cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){*/
			if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){	
				System.err.print("ok");
			} else {
				HttpClient client = new HttpClient();
				HttpMethod method = new GetMethod(pc.getpUrl()+"/webobja_print_tw2?yymmdd="+yymmdd+"&emailMk=1");
				//HttpMethod method = new GetMethod(pc.getPurllocal()+"/webobja_print_tw2?yymmdd="+yymmdd+"&emailMk=1");				
				//HttpMethod method = new GetMethod("http://203.85.73.161/"+pname+"/print2Ypoi_print2Y_hb?sdate="+yymm+"&edate="+yymm+"&emailMk=1&type=Excel2003");//(在不同的機器上注意修改IP和端口)
				//HttpMethod method = new GetMethod("http://172.17.18.173:8080/"+pname+"/print2Ypoi_print2Y_hb?sdate="+yymm+"&edate="+yymm+"&emailMk=1&type=Excel2003");
				//HttpMethod method = new GetMethod("http://localhost:8080/"+pname+"/print2Ypoi_print2Y_hb?sdate="+yymm+"&edate="+yymm+"&emailMk=1&type=Excel2003");
				
				client.executeMethod(method);
				method.releaseConnection();
				ApplicationContext ac = new ClassPathXmlApplicationContext(
						new String[] { "spring-action.xml",
								"spring-dao.xml", "spring.xml",
								"spring-services.xml" });				
				IWebEmailService eSer = (IWebEmailService) ac.getBean("emailService");						
				List<WebEmailAll> email = eSer.findEmail(5, "0");
				//List<WebEmailAll> email = eSer.findEmail(4, "0");
				String[] mail = new String[email.size()];
				for (int i = 0; i < email.size(); i++) {
					if (email.get(i).getUsername() != null
							|| !email.get(i).getUsername().equals("")) {
						//mail[i] = MimeUtility.encodeText(email.get(i).getName())+ "<"+ email.get(i).getEmail()+ ">";
						//解決收件人中文亂碼的問題，因爲因爲數據是utf-8編碼
						mail[i] = MimeUtility.encodeText(email.get(i).getUsername(),"utf-8","Q")+ "<"+ email.get(i).getEmail()+ ">";
					} else {
						mail[i] = email.get(i).getEmail();
					}
				}
				List<WebEmailAll> Cc = eSer.findEmail(5, "1");
				//List<WebEmailAll> Cc = eSer.findEmail(4, "1");
				String[] cc = new String[Cc.size()];
				for (int j = 0; j < Cc.size(); j++) {
					if (Cc.get(j).getUsername() != null
							|| !Cc.get(j).getUsername().equals("")) {
						//cc[j] = MimeUtility.encodeText(Cc.get(j).getName())+ "<" + Cc.get(j).getEmail() + ">";
						//解決收件人中文亂碼的問題，因爲因爲數據是utf-8編碼
						cc[j] = MimeUtility.encodeText(Cc.get(j).getUsername(),"utf-8","Q")+ "<" + Cc.get(j).getEmail() + ">";		
					} else {
						cc[j] = Cc.get(j).getUsername() + Cc.get(j).getEmail();
					}
				}												
				AutoSendEmailAction send = new AutoSendEmailAction();
				//郵件內容
				String affixName=yymmdd+"各廠訊息彙總日報表.xlsx";
				StringBuffer content=new StringBuffer();
				content.append(this.findNoInput(ac, yymmdd));
				content.append("本郵件自動發送,請勿回復!如需回复，請回复到"+pc.getpEmail()+"咨訊室或者"+pc.getPlgx()+"譚香林!");
				
				//發送郵件
				String classes_path=Thread.currentThread().getContextClassLoader().getResource("").getPath();			
				String filepath=classes_path.replace("/WEB-INF/classes","/TEMPFILES/"+yymmdd+".xlsx");//附檔的路徑20170222
				send.sendmail(
						mail,
						cc,
						yymmdd+"各廠訊息彙總報表",																
						content.toString(),						
						affixName,
						filepath);				
				File file=new File(filepath);
				if (file.exists()) {
					if (file.isFile()) {
						file.delete();
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 手動發送郵件
	 * @throws ParseException 
	 */
	public String print_manual() throws ParseException {
		SimpleDateFormat tformat=new SimpleDateFormat("yyyyMMdd");
		if (yymmdd == null || yymmdd.equals("")) {
			Calendar calendar=Calendar.getInstance();
			// 當每月的2號是星期一，也就是1號是星期天，則要減2天，以發送上個月全部產量資料
			if (calendar.get(Calendar.DAY_OF_MONTH) == 2 && calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
				calendar.add(Calendar.DATE,-2);
			} else {
				calendar.add(Calendar.DATE,-1);
			}
			yymmdd=tformat.format(calendar.getTime());
		}
		try {
			Date bdate=new Date();
			Calendar cal=Calendar.getInstance();
			cal.setTime(bdate);
			/*
			 * if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY||
			 * cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			 */
			if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				System.err.print("ok");
			} else {
				HttpClient client=new HttpClient();
				HttpMethod method = new GetMethod(pc.getpUrl()+"/webobja_print_tw2?yymmdd="+yymmdd+"&emailMk=1");	
				//HttpMethod method = new GetMethod(pc.getPurllocal()+"/webobja_print_tw2?yymmdd="+yymmdd+"&emailMk=1");
				//HttpMethod method=new GetMethod("http://203.85.73.161/"+pname+"/print2Ypoi_print2Y_hb?sdate=" + yymm + "&edate=" + yymm+ "&emailMk=1&type=Excel2003");// (在不同的機器上注意修改IP和端口)						
				//HttpMethod method=new GetMethod("http://172.17.18.173:8080/"+pname+"/print2Ypoi_print2Y_hb?sdate="+yymm+"&edate="+yymm+"&emailMk=1&type=Excel2003");
				// HttpMethod method=new GetMethod("http://localhost:8080/"+pname+"/print2Ypoi_print2Y_hb?sdate="+yymm+"&edate="+yymm+"&emailMk=1&type=Excel2003");
				client.executeMethod(method);
				method.releaseConnection();
				ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"spring-action.xml","spring-dao.xml","spring.xml","spring-services.xml"});
				IWebEmailService eSer=(IWebEmailService)ac.getBean("emailService");

				List<WebEmailAll> email=eSer.findEmail(5, "0");
				//List<WebEmailAll> email=eSer.findEmail(4, "0");
				String[] mail=new String[email.size()];
				for (int i=0; i < email.size(); i++) {
					if (email.get(i).getUsername() != null || !email.get(i).getUsername().equals("")) {
						// mail[i] =
						// MimeUtility.encodeText(email.get(i).getName())+ "<"+
						// email.get(i).getEmail()+ ">";
						// 解決收件人中文亂碼的問題，數據庫是以utf-8編碼
						mail[i]=MimeUtility.encodeText(email.get(i).getUsername(),"utf-8","Q") + "<" + email.get(i).getEmail() + ">";
					} else {
						mail[i]=email.get(i).getEmail();
					}
				}
				List<WebEmailAll> Cc=eSer.findEmail(5, "1");
				//List<WebEmailAll> Cc=eSer.findEmail(4, "1");
				String[] cc=new String[Cc.size()];
				for (int j=0; j < Cc.size(); j++) {
					if (Cc.get(j).getUsername() != null || !Cc.get(j).getUsername().equals("")) {
						// cc[j] = MimeUtility.encodeText(Cc.get(j).getName())+
						// "<" + Cc.get(j).getEmail() + ">";
						// 解決收件人中文亂碼的問題，數據庫是以utf-8編碼
						cc[j]=MimeUtility.encodeText(Cc.get(j).getUsername(),"utf-8","Q") + "<" + Cc.get(j).getEmail() + ">";

					} else {
						cc[j]=Cc.get(j).getUsername() + Cc.get(j).getEmail();
					}
				}			 

				String classes_path=Thread.currentThread().getContextClassLoader().getResource("").getPath();			
				String filepath=classes_path.replace("/WEB-INF/classes","/TEMPFILES/"+yymmdd+".xlsx");
				AutoSendEmailAction send=new AutoSendEmailAction();
				String affixName=yymmdd+"各廠訊息彙總日報表.xls";				
				// 郵件內容				
				StringBuffer content=new StringBuffer();
				content.append(this.findNoInput(ac, yymmdd));
				content.append("本郵件自動發送,請勿回復!如需回复，請回复到"+pc.getpEmail()+"咨訊室或者"+pc.getPlgx()+"譚香林!");					
				// 發送郵件
				send.sendmail(mail,cc,yymmdd+"各廠訊息彙總報表",content.toString(),affixName,filepath);
			 
			   //File file=new File("d://" + yymm + ".xls");	
				File file=new File(filepath);
				if (file.exists()) {
					if (file.isFile()) {
						file.delete();
					}
				}
			}
			ajaxResult="0";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ajaxResult="1";
		}
		return "print_manual";
	}
	
	public ProjectConfig findProjectConfig(){
		ProjectConfig pro=GlobalMethod.findProjectConfig();
		return pro;
	}
	
	public String findNoInput(ApplicationContext ac,String yymmdd){
		StringBuffer result=new StringBuffer();
		IWebObjsAServices webobjaservices=(IWebObjsAServices)ac.getBean("webobjaservices");
		List<String[]>list=webobjaservices.findNoInput(yymmdd);
		if(list==null||list.size()==0){
			result.append("<span style='color:blue'>都已輸入數據</span><br/>");
		}else{
			result.append("未輸入數據的廠別如下：<br/><br/>").append("<span style='color:red'>");
			for(Object[] obj:list){
				result.append((String)obj[0]).append("(").append((String)obj[1]).append(")<br/>");
			}
			result.append("</span>");
		}
		result.append("<br/><br/><br/>");
		return result.toString();
		
	}
	
}