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
import services.IWebuserEmailServices;

import com.opensymphony.xwork2.ActionContext;

import entity.KyVisabillm;

public class AutoSendKyz extends QuartzJobBean{

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-dao.xml","spring-services.xml","spring-action.xml"});
		IKyVisabillmServices visabillmSer=(IKyVisabillmServices)ac.getBean("visabillmSer");
		IWebuserEmailServices webuseremailSer=(IWebuserEmailServices)ac.getBean("webuseremailSer");
		List<KyVisabillm>list_vbm=visabillmSer.findByVisaMk("Y");//所有未簽核心完畢的函文		
		String subject="";
		String result="";
		String content="";
		
		if(list_vbm.size()>0){//start if
		MailSenderInfo mailInfo = new MailSenderInfo();
		SimpleMailSender sms = new SimpleMailSender();
		mailInfo.setValidate(true);    
	    mailInfo.setUserName("kyuen@yydg.com.cn"); 
	    mailInfo.setPassword("yydgmail");//您的邮箱密码    
	    mailInfo.setFromAddress("<kyuen@yydg.com.cn>");
			for(int i=0;i<list_vbm.size();i++){// start for1	
				List<String>list_email=new ArrayList<String>();
				String signerNext=list_vbm.get(i).getSignerNext();
				String factNo=list_vbm.get(i).getId().getFactNo();
				String billNo=list_vbm.get(i).getId().getBillNo();
				String visaSort=list_vbm.get(i).getId().getVisaSort();
				String visaMk=list_vbm.get(i).getVisaMk();
				String emailPwd = webuseremailSer.findEmailPWD(factNo,signerNext);//備簽人Email
				list_email.add(signerNext);
				if(emailPwd!=null){
					list_email.add(emailPwd);
				}
				list_email.add("kyuen@yydg.com.cn");
				String emailUrl="http://203.85.73.161/Login/vbm_findById_email?visaSort="+visaSort+"&billNo="+billNo
				         +"&factNo="+factNo+"&email="+signerNext;
				String emailUrl2="http://203.85.73.161/Login/vbm_findById_email2?visaSort="+visaSort+"&billNo="+billNo
				         +"&factNo="+factNo+"&email="+signerNext;
				if(visaMk.equals("N")){
					subject="函文審核定時通知_"+billNo+"("+factNo+")";
					content="函文單號:"+"<span style='color:red'>"+billNo+"</span>"+"&nbsp;&nbsp;廠別:"+factNo+
				    		  "<br/>點擊單號直接審核:<a href='"+emailUrl2+"'>"+billNo+"</a>(電腦適用)"+
				    		  "<br/>點擊單號直接審核:<a href='"+emailUrl+"'>"+billNo+"</a>(手機平板適用)"+				    		 
						      "<hr/>"+
				    		 result+"如需查詢以往單據請登錄加久網站:(云端)<a href='http://203.85.73.161/Login'>http://203.85.73.161/Login</a>" +		            
				      		"<br/>進入[KPI數據]--[函文審核]中查找對應單號審核"+			    		
				    		"<hr/>"+
				      		"<br/>本郵件定時自動發送,請勿回復!如需回復或者問題，請回复到kyinfo.lp@yydg.com.cn劉平!<br/>"+
				    		"<hr/>";
				}
				if(visaMk.equals("T")){				
						subject="函文退回定時通知_"+billNo+"("+factNo+")";//退回函文隻發送一次，所以也要鎖定狀態emailMk	
						list_vbm.get(i).setEmailMk("Y");
						visabillmSer.add(list_vbm.get(i));
						content="函文單號:"+"<span style='color:red'>"+billNo+"</span>"+"&nbsp;&nbsp;廠別:"+factNo+"不通過，備註如下："+
					    		  "<br/>"+
					    		  list_vbm.get(i).getMemoMk()==null?"無":list_vbm.get(i).getMemoMk()+				    		 
							      "<hr/>"+
					    		 result+"詳情請登錄加久網站:(云端)<a href='http://203.85.73.161/Login'>http://203.85.73.161/Login</a>" +		            
					      		"<br/>進入[KPI數據]--[函文審核]中查找對應單號審核"+			    		
					    		"<hr/>"+
					      		"<br/>本郵件定時自動發送,請勿回復!如需回復或者問題，請回复到kyinfo.lp@yydg.com.cn劉平!<br/>"+
					    		"<hr/>";
				}
				for(int j=0;j<list_email.size();j++){//start for2
					  mailInfo.setToAddress(list_email.get(j));    
				      mailInfo.setSubject(subject);    			      
				      mailInfo.setContent(content); 				    		  			           
				      sms.sendHtmlMail(mailInfo);//发送html格式
				}//end for2				  									      				      						       	          			
			}//end for1
		}//end if
		
									
	}

}
