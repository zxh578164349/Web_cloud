/**
 * 
 */
package mail;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeUtility;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import action.AutoSendEmailAction;

import entity.WebCc;
import entity.WebEmail;
import entity.WebFact;

import services.IWebEmailService;
import services.IWebFactorderServices;

/**   
 *    
 * 项目名称：Login   
 * 类名称：AutoSendWebfactorder   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/6/8 下午1:39:13   
 * 修改人：Administrator   
 * 修改时间：2016/6/8 下午1:39:13   
 * 修改备注：   
 * @version    
 *    
 **/
public class AutoSendWebfactorder extends QuartzJobBean{

	/**
	 * 日期:2016/6/8
	 * 描述:
	 */
	
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		try {
			this.init();
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void init() throws HttpException, IOException{
		String yymm=new SimpleDateFormat("yyyyMM").format(new Date());
		HttpClient client = new HttpClient();
		//HttpMethod method = new GetMethod("http://203.85.73.161/Login/webfactOrder_print_email?yymm="+yymm);	
		//HttpMethod method = new GetMethod("http://172.17.18.173:8080/Login/webfactOrder_print_email?yymm="+yymm+"&yymm2="+yymm);
		HttpMethod method = new GetMethod("http://localhost:8080/Login/webfactOrder_print_email?yymm="+yymm+"&yymm2="+yymm+"&autoEmailMk=1");
		client.executeMethod(method);
		method.releaseConnection();
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring-action.xml","spring-dao.xml", "spring.xml","spring-services.xml"});
		IWebFactorderServices webfactorderSer=(IWebFactorderServices)ac.getBean("webfactorderSer");
		List<WebFact>list_facts=webfactorderSer.findNoinput(yymm);
		IWebEmailService eSer =(IWebEmailService)ac.getBean("emailService");
				
		/*List<WebEmail> email = eSer.getEmail("Y");
		String[] mail = new String[email.size()];
		for (int i = 0; i < email.size(); i++) {
			if (email.get(i).getName() != null
					|| !email.get(i).getName().equals("")) {
				//mail[i] = MimeUtility.encodeText(email.get(i).getName())+ "<"+ email.get(i).getEmail()+ ">";
				//解決收件人中文亂碼的問題，因爲因爲數據是utf-8編碼
				mail[i] = MimeUtility.encodeText(email.get(i).getName(),"utf-8","Q")+ "<"+ email.get(i).getEmail()+ ">";
			} else {
				mail[i] = email.get(i).getEmail();
			}
		}		
		List<WebCc> Cc = eSer.getCC("Y");
		String[] cc = new String[Cc.size()];
		for (int j = 0; j < Cc.size(); j++) {
			if (Cc.get(j).getName() != null
					|| !Cc.get(j).getName().equals("")) {
				//cc[j] = MimeUtility.encodeText(Cc.get(j).getName())+ "<" + Cc.get(j).getEmail() + ">";
				//解決收件人中文亂碼的問題，因爲因爲數據是utf-8編碼
				cc[j] = MimeUtility.encodeText(Cc.get(j).getName(),"utf-8","Q")+ "<" + Cc.get(j).getEmail() + ">";		
			} else {
				cc[j] = Cc.get(j).getName() + Cc.get(j).getEmail();
			}
		}*/
		
		/*String[] cc = { "kyinfo.David@yyin.yydg.com.cn"};
		String[] mail={"kyinfo.David@yyin.yydg.com.cn"};
		String[] mail={MimeUtility.encodeText("張錫洪")+"<kyinfo.David@yyin.yydg.com.cn>"};				
		String[] cc = {MimeUtility.encodeText("張錫洪")+"<kyinfo.David@yyin.yydg.com.cn>"};*/
		String[] mail={MimeUtility.encodeText("張錫洪")+"<zxh578164349@qq.com>"};				
		String[] cc = {MimeUtility.encodeText("張錫洪")+"<zxh578164349@qq.com>"};
		AutoSendEmailAction send = new AutoSendEmailAction();
		send.sendmail(mail, cc, "標題", "內容", yymm);
		File file = new File("d://" + yymm + ".xls");
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			}
		}
		
												
	}

}
