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
import util.GlobalMethod;

/**
 * @author Administrator
 * 20160216
 * 每隔兩箇月爲函文添加刪除標記
 *
 */
public class QuartzDelMk extends QuartzJobBean{

	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub	
		try{
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
			//this.init();
		}catch(Exception e){
			System.out.println(e);
		}					
	}
	
	public void init(){
		ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-dao.xml","spring-services.xml"});
		IKyzExpectmatmServices kyzSer=(IKyzExpectmatmServices)ac.getBean("kyzSer");
		IKyVisabillmServices visabillmSer=(IKyVisabillmServices)ac.getBean("visabillmSer");
		IKyzContactLetterServices kyzletterSer=(IKyzContactLetterServices)ac.getBean("kyzletterSer");
		IWebBussinessletterServices webbussletterSer=(IWebBussinessletterServices)ac.getBean("webbussletterSer");
		
		try{
			//每隔兩箇月爲函文添加刪除標記,同時給函文添加刪除標記20160216
			/*List<KyzExpectmatm>list_kyz=kyzSer.findBefor2Month();			
			kyzSer.addLarge(list_kyz);
			
			List<KyzContactletter>list_cletter=kyzletterSer.findBefor2Month();
			kyzletterSer.addLarge(list_cletter);
			
			List<WebBussinessletter>list_bussletter=webbussletterSer.findBefor2Month();
			webbussletterSer.addLarge(list_bussletter);
			
			List<KyVisabillm>list_vbm=visabillmSer.findBefor2Month();
			visabillmSer.addLarge(list_vbm);*/
			
			//每隔兩箇月爲沒有簽核的函文添加刪除標記20160929
			List<KyVisabillm>list_vbm_n=visabillmSer.findBefor2Month2();
			for(KyVisabillm vbm:list_vbm_n){
				System.out.println(vbm.getId().getBillNo());
			}
			System.out.println("合計："+list_vbm_n.size());
			visabillmSer.addLarge(list_vbm_n);
		}catch(Exception e){
			System.out.println("action*********************************"+e+"**********************************action");
		}
	}
	

}
