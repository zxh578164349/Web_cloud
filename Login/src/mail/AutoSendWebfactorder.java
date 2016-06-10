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
import util.GlobalMethod;

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
			//this.init();
			List<String> ips=GlobalMethod.findIp2();				
			if(ips.size()==0){
				this.init();
			}else{
				for(int i=0;i<ips.size();i++){
					if(ips.get(i).equals("192.168.199.101")){
						this.init();
						break;
					}else if(i==ips.size()-1){
						System.out.println("本機不需要發送Email");
					}
				}
			}
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
		HttpMethod method = new GetMethod("http://203.85.73.161/Login/webfactOrder_print_email?yymm="+yymm+"&yymm2="+yymm+"&autoEmailMk=1");	
		//HttpMethod method = new GetMethod("http://172.17.18.173:8080/Login/webfactOrder_print_email?yymm="+yymm+"&yymm2="+yymm+"&autoEmailMk=1");
		//HttpMethod method = new GetMethod("http://localhost:8080/Login/webfactOrder_print_email?yymm="+yymm+"&yymm2="+yymm+"&autoEmailMk=1");
		client.executeMethod(method);
		method.releaseConnection();
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring-action.xml","spring-dao.xml", "spring.xml","spring-services.xml"});
		IWebFactorderServices webfactorderSer=(IWebFactorderServices)ac.getBean("webfactorderSer");
		List<WebFact>list_facts=webfactorderSer.findNoinput(yymm);
		IWebEmailService eSer =(IWebEmailService)ac.getBean("emailService");
		StringBuffer fact_strs=new StringBuffer();
		for(WebFact fact:list_facts){
			fact_strs.append(fact.getFactSname()+"_"+fact.getId().getFactArea()+"<br/>");
		}		
		List<WebEmail> email = eSer.getEmail("Y");
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
		}
			
		/*String[] mail={MimeUtility.encodeText("張錫洪")+"<kyinfo.David@yyin.yydg.com.cn>"};				
		String[] cc = {MimeUtility.encodeText("張錫洪")+"<kyinfo.David@yyin.yydg.com.cn>"};
		String[] mail={MimeUtility.encodeText("張錫洪")+"<zxh578164349@qq.com>"};				
		String[] cc = {MimeUtility.encodeText("張錫洪")+"<zxh578164349@qq.com>"};*/
		AutoSendEmailAction send = new AutoSendEmailAction();
		String title=yymm+"各廠訂單導入狀況";
		String affixName=yymm+"各廠訂單導入狀況";
		StringBuffer content=new StringBuffer();
		content.append(yymm+"未導入訂單工廠如下:<br/><br/>");
		content.append("<span style='color:red;font-size:16px'>");		
		content.append(fact_strs);
		content.append("</span><br/><br/>");
		content.append("本郵件自動發送,請勿回復!如需回复，請回复到kyinfo@yydg.com.cn咨訊室");
		send.sendmail(mail, cc, title, content.toString(), yymm,affixName);
		File file = new File("d://" + yymm + ".xls");
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			}
		}
		
												
	}

}