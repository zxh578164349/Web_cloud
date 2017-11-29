package action;

import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.sun.mail.util.MailSSLSocketFactory;

public class AutoSendEmailAction {
	private static final String MAIL_USER = "kyuen@yydg.com.cn";

	private static final String MAIL_PASSWORD = "yydgmail";

	private static final String MAIL_FROM_SMTP = "<kyuen@yydg.com.cn>";

	public void sendmail(String[] mailArray, String[] cc, String subject,String content,String affixName,String filepath) {			
		Properties props = new Properties();				
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "dgmail.yydg.com.cn");
		//props.setProperty("mail.host", "172.17.5.84");//因為有時候解釋不了域名,所以直接用地址代替(内网IP)
		//props.setProperty("mail.host", "125.88.14.11");//因為有時候解釋不了域名,所以直接用地址代替(外网IP)
		//props.setProperty("mail.host", "61.20.35.47");
		//props.setProperty("mail.pop.port", "995");
		props.setProperty("mail.smtp.port", "465");//改smtp端口
		props.setProperty("mail.smtp.socketFactory.port","465");
		//props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		//props.setProperty("mail.smtp.socketFactory.fallback", "false");
		//props.setProperty("mail.smtp.starttls.enable","true");//加密发送
		props.setProperty("mail.smtp.ssl.enable", "true");///加密发送
		//以下设置，邮件加密发送，不需要证书验证
		MailSSLSocketFactory sf = null;
		try {
			sf = new MailSSLSocketFactory();
		} catch (GeneralSecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
	    sf.setTrustAllHosts(true);
	    props.put("mail.smtp.ssl.socketFactory", sf);
		
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(MAIL_USER, MAIL_PASSWORD);
			}
		});
		session.setDebug(true);
		Message msg = new MimeMessage(session);
		Transport transport = null;
		try {
			// 15,6
			msg.setFrom(new InternetAddress(MimeUtility.encodeText("加久公共信息","utf-8","Q")+ MAIL_FROM_SMTP));					
			sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
			msg.setSubject("=?BIG5?B?" + enc.encode(subject.getBytes()) + "?=");
			Multipart multipart = new MimeMultipart();
			BodyPart contentPart = new MimeBodyPart();
			contentPart.setContent(content, "text/html;charset=utf-8");
			multipart.addBodyPart(contentPart);
			BodyPart messageBodyPart = new MimeBodyPart();
			//DateFormat format = new SimpleDateFormat("yyyyMM");
			//String date = format.format(new Date());
			//DataSource source = new FileDataSource("d://" + yymm + ".xls");
			DataSource source = new FileDataSource(filepath);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName("=?BIG5?B?" + enc.encode(affixName.getBytes()) + "?="+ ".xls");					
			multipart.addBodyPart(messageBodyPart);
			msg.setContent(multipart);
			msg.setSentDate(new Date());//發送時間
			int len = mailArray.length;
			InternetAddress address[] = new InternetAddress[len];
			for (int i = 0; i < mailArray.length; i++) {
				/*address[i] = new InternetAddress(
						MimeUtility.encodeText(mailArray[i]));*/		
				address[i]=new InternetAddress(mailArray[i]);
			}
			if (cc != null) {
				int ccLen = cc.length;
				Address[] ccAdresses = new InternetAddress[ccLen];
				for (int j = 0; j < cc.length; j++) {
					ccAdresses[j] = new InternetAddress(cc[j]);
				}
				msg.setRecipients(Message.RecipientType.CC, ccAdresses);
			}
			msg.addRecipients(Message.RecipientType.TO, address);
			transport.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (transport != null) {
					transport.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}