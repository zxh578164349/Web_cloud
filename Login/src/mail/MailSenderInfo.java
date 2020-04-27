/**
 * 
 */
package mail;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Properties;

import util.GlobalMethod;

import com.sun.mail.util.MailSSLSocketFactory;

import entity.custom.ProjectConfig;

/**
 * @author 张锡洪
 */
public class MailSenderInfo {
	private static final ProjectConfig pc=GlobalMethod.findProjectConfig();  
    // 邮件发送者的地址    
    private String fromAddress;    
    // 邮件接收者的地址    
    private String toAddress;    
    // 登陆邮件发送服务器的用户名和密码    
    private String userName;    
    private String password;    
    // 是否需要身份验证    
    private boolean validate = false;    
    // 邮件主题    
    private String subject;    
    // 邮件的文本内容    
    private String content;    
    // 邮件附件的文件名    
    private String[] attachFileNames;  
    
    /**   
      * 获得邮件会话属性   
      */    
    public Properties getProperties(){    
    	Properties p = new Properties();   	
        //p.put("mail.smtp.host", this.mailServerHost);    
        //p.put("mail.smtp.port", this.mailServerPort);    
        p.put("mail.smtp.auth", validate ? "true" : "false");
        
       // p.setProperty("mail.smtp.auth", "true");
  		p.setProperty("mail.transport.protocol", "smtp");
  		//p.setProperty("mail.host", "dgmail.yydg.com.cn");//云端要用域名  		
  		p.setProperty("mail.host", pc.getpSmtp());
  		//p.setProperty("mail.host", "172.17.5.84");    //有時候DNS解釋不到域名,所以直接用ip地址代替
  		//p.put("http.proxyHost", "172.17.18.14");
  		//p.put("http.proxyPort", "808");
  		//p.setProperty("mail.host", "61.20.35.47");//云端的收发邮件服务器
  	//p.setProperty("mail.smtp.starttls.enable", "true");//加密发送
  		if("smtp.office365.com".equals(pc.getpSmtp())){
  			p.setProperty("mail.smtp.starttls.enable","true");//加密发送
  		}else{
  			p.setProperty("mail.smtp.port", "465");//改smtp端口		
  	  		p.setProperty("mail.smtp.socketFactory.port", "465"); 		
  	  		p.setProperty("mail.smtp.ssl.enable", "true");///加密发送
  		}
  		
  		//以下设置，邮件加密发送，不需要证书验证
  		MailSSLSocketFactory sf = null;
  		try {
  			sf = new MailSSLSocketFactory();
  		} catch (GeneralSecurityException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}  
  	    sf.setTrustAllHosts(true);
  	    p.put("mail.smtp.ssl.socketFactory", sf);
        return p;    
    }    
  
    public boolean isValidate() {    
      return validate;    
    }   
    public void setValidate(boolean validate) {    
      this.validate = validate;    
    }   
    public String[] getAttachFileNames() {    
      return attachFileNames;    
    }   
    public void setAttachFileNames(String[] fileNames) {    
      this.attachFileNames = fileNames;    
    }   
    public String getFromAddress() {    
      //return fromAddress; 
    	return pc.getPfromAddress();
    }    
    public void setFromAddress(String fromAddress) {    
      this.fromAddress = fromAddress;    
    }   
    public String getPassword() {    
      //return password;  
    	return pc.getpEmailPwd();
    }   
    public void setPassword(String password) {    
      this.password = password;    
    }   
    public String getToAddress() {    
      return toAddress;    
    }    
    public void setToAddress(String toAddress) {    
      this.toAddress = toAddress;    
    }    
    public String getUserName() {    
      //return userName;    
    	return pc.getpEmail();
    }   
    public void setUserName(String userName) {    
      this.userName = userName;    
    }   
    public String getSubject() {    
      return subject;    
    }   
    public void setSubject(String subject) {    
      this.subject = subject;    
    }   
    public String getContent() {    
      return content;    
    }   
    public void setContent(String textContent) {    
      this.content = textContent;    
    } 
    
    
    

}
