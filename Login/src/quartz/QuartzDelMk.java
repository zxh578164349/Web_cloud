/**
 * 
 */
package quartz;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import entity.KyVisabillm;
import entity.KyzContactletter;
import entity.KyzExpectmatm;
import entity.WebBussinessletter;

import services.IKyVisabillmServices;
import services.IKyzContactLetterServices;
import services.IKyzExpectmatmServices;
import services.IWebBussinessletterServices;

/**
 * @author Administrator
 * 20160216
 * 每隔一箇月爲函文添加刪除標記
 *
 */
public class QuartzDelMk extends QuartzJobBean{


	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-dao.xml","spring-services.xml"});
		IKyzExpectmatmServices kyzSer=(IKyzExpectmatmServices)ac.getBean("kyzSer");
		IKyVisabillmServices visabillmSer=(IKyVisabillmServices)ac.getBean("visabillmSer");
		IKyzContactLetterServices kyzletterSer=(IKyzContactLetterServices)ac.getBean("kyzletterSer");
		IWebBussinessletterServices webbussletterSer=(IWebBussinessletterServices)ac.getBean("webbussletterSer");
		
		try{
			List<KyzExpectmatm>list_kyz=kyzSer.findBefor2Month();			
			//kyzSer.addLarge(list_kyz);
			System.out.println(list_kyz.size());
			
			List<KyzContactletter>list_cletter=kyzletterSer.findBefor2Month();
			//kyzletterSer.addLarge(list_cletter);
			System.out.println(list_cletter.size());
			
			List<WebBussinessletter>list_bussletter=webbussletterSer.findBefor2Month();
			//webbussletterSer.addLarge(list_bussletter);
			System.out.println(list_bussletter.size());
			
			List<KyVisabillm>list_vbm=visabillmSer.findBefor2Month();
			//visabillmSer.addLarge(list_vbm);
			System.out.println(list_vbm.size());
		}catch(Exception e){
			System.out.println("action*********************************"+e+"**********************************action");
		}				
	}
	

}
