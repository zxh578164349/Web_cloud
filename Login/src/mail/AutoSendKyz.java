package mail;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import services.IKyVisabillmServices;

import com.opensymphony.xwork2.ActionContext;

import entity.KyVisabillm;

public class AutoSendKyz extends QuartzJobBean{

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-dao.xml","spring-services.xml","spring-action.xml"});
		IKyVisabillmServices visabillmSer=(IKyVisabillmServices)ac.getBean("visabillmSer");
		List<KyVisabillm>list_vbm=visabillmSer.findByVisaMk("Y");		
		String subject="";
		String result="";
		
		MailSenderInfo mailInfo = new MailSenderInfo();
		SimpleMailSender sms = new SimpleMailSender();
		for(int i=0;i<list_vbm.size();i++){			
			String signerNext=list_vbm.get(i).getSignerNext();
			String factNo=list_vbm.get(i).getId().getFactNo();
			String billNo=list_vbm.get(i).getId().getBillNo();
			String visaSort=list_vbm.get(i).getId().getVisaSort();
			String visaMk=list_vbm.get(i).getVisaMk();
			String emailUrl="http://172.17.18.173:8080/Login/vbm_findById_email?visaSort="+visaSort+"& billNo="+billNo
			         +"& factNo="+factNo+"& email="+signerNext;
			if(visaMk.equals("N")){
				subject="函文審核(下一位)_"+billNo+"("+factNo+")"+"(定时)";
			}
			if(visaMk.equals("T")){				
					subject="函文退回通知_"+billNo+"("+factNo+")"+"(定时)";				
			}
			  
			if(signerNext.contains("@")){
				 mailInfo.setValidate(true);    
			      mailInfo.setUserName("kyuen@yydg.com.cn"); 
			      mailInfo.setPassword("yydgmail");//您的邮箱密码    
			      mailInfo.setFromAddress("<kyuen@yydg.com.cn>");    
			      mailInfo.setToAddress(signerNext);    
			      mailInfo.setSubject(subject);    			      
			      mailInfo.setContent("函文單號:"+"<span style='color:red'>"+billNo+"</span>"+"&nbsp;&nbsp;廠別:"+factNo+
			    		  "<br/>點擊單號直接審核:<a href='"+emailUrl+"'>"+billNo+"(云端)</a>"+				      
					      "<hr/>"+
			    		 result+"如需查詢以往單據請登錄加久網站:(云端)<a href='http://203.85.73.161/Login'>http://203.85.73.161/Login</a>" +		            
			      		"<br/>進入[KPI數據]--[函文審核]中查找對應單號審核,"+	      		    		
			    		"<hr/>"+
			      		"<br/>本郵件自動發送,請勿回復!如需回復或者問題，請回复到kyinfo.lp@yydg.com.cn劉平!<br/>"+
			    		"<hr/>"
			    		);   			           
			      sms.sendHtmlMail(mailInfo);//发送html格式
			}		       	          			
		}
		
		/*List<String>list=new ArrayList<String>();
		list.add("kyinfo.David@yyin.yydg.com.cn");
		list.add("zxh578164349@qq.com");
		MailSenderInfo mailInfo=new MailSenderInfo();
		SimpleMailSender sms=new SimpleMailSender();
		for(int i=0;i<list.size();i++){
			
			mailInfo.setValidate(true);
			mailInfo.setUserName("kyuen@yydg.com.cn");
			mailInfo.setPassword("yydgmail");
			mailInfo.setFromAddress("<kyuen@yydg.com.cn>");
			mailInfo.setToAddress(list.get(i));
			mailInfo.setSubject("定时发送测试");
			mailInfo.setContent("定时发送测试");			
			sms.sendHtmlMail(mailInfo);
		}*/
				
		
	}

}
