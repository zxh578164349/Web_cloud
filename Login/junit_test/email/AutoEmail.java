package email;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.mail.internet.MimeUtility;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import action.AutoSendEmailAction;

import services.IWebEmailService;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations={"classpath:spring.xml", "classpath:spring-dao.xml" ,    "classpath:spring-services.xml"})
public class AutoEmail {
	@Resource(name= "emailService") //�`�Jbean�x
    private IWebEmailService emailService;
	@Test	
	public void test() throws UnsupportedEncodingException {
		AutoSendEmailAction send = new AutoSendEmailAction();
		String[]to={MimeUtility.encodeText("�x","utf-8","Q")+"<kyinfo.David@yyin.yydg.com.cn>"};
		String[]cc={MimeUtility.encodeText("�x")+"<zxh578164349@qq.com>"};
		send.sendmail(to, cc, "�D�D����", "���e����", "�������");				
	}

}
