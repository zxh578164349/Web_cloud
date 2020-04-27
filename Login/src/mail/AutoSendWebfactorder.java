/**
 * 
 */
package mail;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import entity.WebEmailAll;
import entity.WebFact;
import entity.custom.ProjectConfig;

import services.IWebEmailService;
import services.IWebFactorderServices;
import util.GlobalMethod;

/**
 * 
* 項目名稱：WebLogin   
* 類名稱：AutoSendWebfactorder   
* 類描述：工廠訂單郵件通知
* 創建人：KY2
 */
public class AutoSendWebfactorder extends QuartzJobBean{
	private static final ProjectConfig pc=GlobalMethod.findProjectConfig();

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
					if(ips.get(i).equals(pc.getpHostLoaclB())){
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
		Calendar cal=Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, -1);
		String yymm2=new SimpleDateFormat("yyyyMM").format(cal.getTime());
		String yymm=new SimpleDateFormat("yyyy").format(cal.getTime())+"01";//20170106
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(pc.getpUrl()+"/webfactOrder_print_email?yymm="+yymm+"&yymm2="+yymm2+"&autoEmailMk=1");
		//HttpMethod method = new GetMethod(pc.getPurllocal()+"/webfactOrder_print_email?yymm="+yymm+"&yymm2="+yymm2+"&autoEmailMk=1");		
		client.executeMethod(method);
		method.releaseConnection();
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring-action.xml","spring-dao.xml", "spring.xml","spring-services.xml"});
		IWebFactorderServices webfactorderSer=(IWebFactorderServices)ac.getBean("webfactorderSer");		
		IWebEmailService eSer =(IWebEmailService)ac.getBean("emailService");		
		StringBuffer fact_strs=new StringBuffer();
		List<String>list_date=GlobalMethod.getDateNum(yymm, yymm2);	
		for(String month:list_date){
			List<WebFact>list_facts=webfactorderSer.findNoinput(month);
			fact_strs.append("<span style='color:blue'>"+month+"月份</span><br/>");
			for(WebFact fact:list_facts){
				fact_strs.append(fact.getFactSname()+"_"+fact.getId().getFactArea()+"<br/>");
			}
		}
				
		List<WebEmailAll> email = eSer.findEmail(1,"0");
		//List<WebEmailAll> email=eSer.findEmail(4, "0");
		String[] mail = new String[email.size()];
		for (int i = 0; i < email.size(); i++) {
			if (email.get(i).getUsername() != null
					|| !email.get(i).getUsername().equals("")) {
				//mail[i] = MimeUtility.encodeText(email.get(i).getName())+ "<"+ email.get(i).getEmail()+ ">";
				//解決收件人中文亂碼的問題，因爲因爲數據是utf-8編碼
				mail[i] = email.get(i).getFactNo()+"_"+MimeUtility.encodeText(email.get(i).getUsername(),"utf-8","Q")+ "<"+ email.get(i).getEmail()+ ">";
			} else {
				mail[i] = email.get(i).getEmail();
			}
		}		
		List<WebEmailAll> Cc = eSer.findEmail(1,"1");
		//List<WebEmailAll> Cc=eSer.findEmail(4, "1");
		String[] cc = new String[Cc.size()];
		for (int j = 0; j < Cc.size(); j++) {
			if (Cc.get(j).getUsername() != null
					|| !Cc.get(j).getUsername().equals("")) {
				//cc[j] = MimeUtility.encodeText(Cc.get(j).getName())+ "<" + Cc.get(j).getEmail() + ">";
				//解決收件人中文亂碼的問題，因爲因爲數據是utf-8編碼
				cc[j] = Cc.get(j).getFactNo()+"_"+MimeUtility.encodeText(Cc.get(j).getUsername(),"utf-8","Q")+ "<" + Cc.get(j).getEmail() + ">";		
			} else {
				cc[j] = Cc.get(j).getEmail();
			}
		}
					
		AutoSendEmailAction send = new AutoSendEmailAction();		
		String title="各廠訂單導入狀況";
		String affixName="各廠訂單導入狀況.xls";
		StringBuffer content=new StringBuffer();
		content.append("未導入訂單工廠如下:<br/><br/>");
		content.append("<span style='color:red;font-size:16px'>");		
		content.append(fact_strs);
		content.append("</span><br/><br/>");
		content.append("本郵件自動發送,請勿回復!如需回复，請回复到"+pc.getpEmail()+"咨訊室");
		
		String classes_path=Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String filepath=classes_path.replace("/WEB-INF/classes","/TEMPFILES/"+yymm+".xls");//附檔的路徑20170222				
		send.sendmail(mail, cc, title, content.toString(),affixName,filepath);
		//File file = new File("d://" + yymm + ".xls");
		File file=new File(filepath);
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			}
		}
		
												
	}		

}
