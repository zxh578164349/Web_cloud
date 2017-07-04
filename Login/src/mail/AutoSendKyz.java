package mail;
import java.util.List;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import services.IKyVisabillmServices;
import util.GlobalMethod;
import entity.KyVisabillm;


public class AutoSendKyz extends QuartzJobBean{
	public static final String KIP="192.168.199.101";

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
					if(ips.get(i).equals(KIP)){
						this.init();
						break;
					}else if(i==ips.size()-1){
						System.out.println("本機不需要發送Email");
					}
				}
			}
			//this.init();
		}catch(Exception e){
			System.out.println("函文定時發送"+e);
			e.printStackTrace();
		}														
	}
	
	public void init(){
		ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-dao.xml","spring-services.xml","spring-projectconfig.xml"});		
		IKyVisabillmServices visabillmSer=(IKyVisabillmServices)ac.getBean("visabillmSer");	
		List<KyVisabillm>list_vbm=visabillmSer.findByVisaMk("Y");//所有未簽核完畢的函文			
		GlobalMethod.sendEmailA(ac,list_vbm);		
	}
}
