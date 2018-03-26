package mail;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import services.IWebUserService;

import entity.WebUser;
import entity.WebWeeklyreport;
import entity.custom.ProjectConfig;

public class AutoSendWebWeeklyreport extends QuartzJobBean{

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-dao.xml","spring-services.xml","spring-projectconfig.xml"});	
		IWebUserService webUserService=(IWebUserService)ac.getBean("webUserService");
		List<WebUser>list=webUserService.findByWeeklyMk();
		MailSenderInfo mailInfo = new MailSenderInfo();
		SimpleMailSender sms = new SimpleMailSender();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		Calendar cal=Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		String sdate=sdf.format(cal.getTime());
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		String edate=sdf.format(cal.getTime());
		cal.add(Calendar.DAY_OF_WEEK, -7);		
		
		ProjectConfig pc=(ProjectConfig)ac.getBean("proconfig");
		String url=null;
		
		
		for(WebUser user:list){
			StringBuffer content=new StringBuffer();
			/*content.append("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/><title>mail</title></head><body>");
			content.append("<form action='http://172.17.18.173:8080/WebLogin/webweekly_email_url' method='post'>");
			content.append("<input type='text' name='uid' value='1'/>");
			content.append("<input type='text' name='sdate' value='"+sdate+"'/>");
			content.append("<input type='text' name='edate' value='"+edate+"'/>");
			content.append("<input type='submit' value='添加'/></form>");
			content.append("<a href='http://172.17.18.173:8080/WebLogin/webweekly_email_url?uid=1&sdate="+sdate+"&edate="+edate+"'>添加</a>");
			content.append("</body></html>");*/
			//url="http://192.168.1.112:8080/WebLogin/webweekly_email_url?uid="+user.getId()+"&sdate="+sdate+"&edate="+edate+"&uname="+user.getName();
			url=pc.getpUrl()+"/webweekly_email_url?uid="+user.getId()+"&sdate="+sdate+"&edate="+edate+"&uname="+user.getName();
			content.append(sdate+"-"+edate+"週報告：");
			content.append("<a href='"+url+"'>");		
			content.append("點擊進入</a>");
			mailInfo.setContent(content.toString());
			mailInfo.setSubject("業務每週報告表"+sdate+"-"+edate);
			mailInfo.setToAddress(user.getEmail());
			sms.sendHtmlMail(mailInfo);	
			
			content.append("<br/><span style='color:blue'>"+user.getUsername()+"("+user.getName()+")"+"</span>");
			mailInfo.setContent(content.toString());
			mailInfo.setToAddress(pc.getpEmail());
			sms.sendHtmlMail(mailInfo);
			url=null;
		}
		
		
		
			
		
	}

}
