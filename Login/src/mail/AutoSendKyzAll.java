package mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.base.JRBaseReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.struts2.ServletActionContext;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.context.ContextLoader;

import services.IKyVisaBillsServices;
import services.IKyVisabillmServices;
import services.IKyzContactLetterServices;
import services.IKyzExpectmatmFileServices;
import services.IKyzExpectmatmServices;
import services.IKyzVisaFlowServices;
import services.IWebBussinessletterServices;
import services.IWebFactServices;
import services.IWebremittancelistServices;
import services.IWebuserEmailAServices;
import services.IWebuserEmailServices;
import util.GlobalMethod;
import util.JasperHelper;
import util.Log4j;

import com.opensymphony.xwork2.ActionContext;
import com.spreada.utils.chinese.ZHConverter;

import entity.KyVisabillm;
import entity.KyVisabills;
import entity.KyzContactletter;
import entity.KyzExpectmatm;
import entity.KyzExpectmatmFile;
import entity.KyzExpectmatmId;
import entity.KyzExpectmats;
import entity.KyzVisaflow;
import entity.WebBussinessletter;
import entity.Webremittancelist;
import entity_temp.VisabillsTemp;

public class AutoSendKyzAll extends QuartzJobBean{
	private Map<String, Object> map;
	

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public static final String PDF_TYPE_AUTO="auto";
	
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
		IKyVisabillmServices visabillmSer=(IKyVisabillmServices)ac.getBean("visabillmSer");
		List<KyVisabillm>list_vbm=visabillmSer.findByVisaMk2("Y");//所有已經簽核完畢的函文，但未發送email知會		
		
		if(list_vbm.size()>0){//start if
			MailSenderInfo mailInfo=new MailSenderInfo();
			SimpleMailSender sms=new SimpleMailSender();
			mailInfo.setValidate(true);			
			mailInfo.setToAddress("kyuen@yydg.com.cn");//收件人爲本機，檢測Email是否發送成功
			mailInfo.setContent("簽核完畢");
			for(int i=0;i<list_vbm.size();i++){//start for			
				String factNo=list_vbm.get(i).getId().getFactNo();
				String billNo=list_vbm.get(i).getId().getBillNo();
				String visaSort=list_vbm.get(i).getId().getVisaSort();								
				try {					
					if(billNo.substring(0, 2).equals("EM")){
						this.addVisabillsAndEmail(factNo, billNo, visaSort,list_vbm.get(i),ac);
					}
					if(billNo.substring(0,2).equals("CM")){
						this.addVisabillsAndEmail2(factNo, billNo, visaSort,list_vbm.get(i),ac);
					}
					if(billNo.substring(0,2).equals("BM")){
						this.addVisabillsAndEmail3(factNo, billNo, visaSort,list_vbm.get(i),ac);
					}
					if(billNo.substring(0,2).equals("RM")){
						this.addVisabillsAndEmail4(factNo, billNo, visaSort, list_vbm.get(i), ac);
					}
					mailInfo.setSubject("發送Email成功"+billNo);
					sms.sendHtmlMail(mailInfo);
										
					list_vbm.get(i).setEmailMk("Y");//表示已發送郵件
					visabillmSer.add(list_vbm.get(i));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
					mailInfo.setSubject("發送Email失敗"+billNo);
					sms.sendHtmlMail(mailInfo);					
					System.out.println(i+"_"+billNo+"Faile");
					Log4j.getError(e);//log4j日記
				}												
			}//end for
		}//end if
	}
	
	/**
	 * 費用函文
	 * @param local_factNo
	 * @param local_billNo
	 * @param local_visaSort
	 * @throws IOException
	 */
	public void addVisabillsAndEmail(String local_factNo,String local_billNo,String local_visaSort,KyVisabillm vbm,ApplicationContext ac) throws IOException{
		
		/**
		 * 打印
		 */
		this.print_KyzExpectmatm(local_factNo, local_billNo, local_visaSort,vbm,ac);
		
		/**
		 * 發郵件
		 * 由於要給所有人(包括不要審核的)發送郵件,所以要重新從數據庫中獲取,而不能使用上面已有的list_visa
		 */
		this.sendEmail(local_factNo, local_billNo, local_visaSort,vbm,ac);      
		
		
	}
	
	/**
	 * 內部聯絡函
	 * @param local_factNo
	 * @param local_billNo
	 * @param local_visaSort
	 * @throws IOException
	 */
	public void addVisabillsAndEmail2(String local_factNo,String local_billNo,String local_visaSort,KyVisabillm vbm,ApplicationContext ac) throws IOException{
		/**
		 * 打印
		 */
		this.print_KyzContactletter(local_factNo, local_billNo, local_visaSort,vbm,ac);		
		/**
		 * 發郵件
		 * 由於要給所有人(包括不要審核的)發送郵件,所以要重新從數據庫中獲取,而不能使用上面已有的list_visa
		 */
		 this.sendEmail(local_factNo, local_billNo, local_visaSort,vbm,ac);
		
								
	}
	
	/**
	 * 人員出差函文
	 * @param local_factNo
	 * @param local_billNo
	 * @param local_visaSort
	 * @throws IOException
	 */
	public void addVisabillsAndEmail3(String local_factNo,String local_billNo,String local_visaSort,KyVisabillm vbm,ApplicationContext ac) throws IOException{
		/**
		 * 打印
		 */
		this.print_webbussletter(local_factNo, local_billNo, local_visaSort,vbm,ac);
		
		/**
		 * 發郵件
		 * 由於要給所有人(包括不要審核的)發送郵件,所以要重新從數據庫中獲取,而不能使用上面已有的list_visa
		 */
		 this.sendEmail(local_factNo, local_billNo, local_visaSort,vbm,ac);     										
	}
	
	/**
	 *加伟鞋材对公费用汇款清单
	 * @param local_factNo
	 * @param local_billNo
	 * @param local_visaSort
	 * @throws IOException
	 */
	public void addVisabillsAndEmail4(String local_factNo,String local_billNo,String local_visaSort,KyVisabillm vbm,ApplicationContext ac) throws IOException{
		/**
		 * 打印
		 */
		this.print_webremittancelist(local_factNo, local_billNo, local_visaSort,vbm,ac);
		
		/**
		 * 發郵件
		 * 由於要給所有人(包括不要審核的)發送郵件,所以要重新從數據庫中獲取,而不能使用上面已有的list_visa
		 */
		 this.sendEmail(local_factNo, local_billNo, local_visaSort,vbm,ac);     										
	}
	
				
	public  void prepareReport(JasperReport jasperReport, String type) {
		//logger.debug("The method======= prepareReport() start.......................");
		if ("excel".equals(type))
			try {
				Field margin = JRBaseReport.class
						.getDeclaredField("leftMargin");
				margin.setAccessible(true);
				margin.setInt(jasperReport, 0);
				margin = JRBaseReport.class.getDeclaredField("topMargin");
				margin.setAccessible(true);
				margin.setInt(jasperReport, 0);
				margin = JRBaseReport.class.getDeclaredField("bottomMargin");
				margin.setAccessible(true);
				margin.setInt(jasperReport, 0);
				Field pageHeight = JRBaseReport.class
						.getDeclaredField("pageHeight");
				pageHeight.setAccessible(true);
				pageHeight.setInt(jasperReport, 2147483647);
			} catch (Exception exception) {
			}
	}
	private  void exportPdf_auto(JasperPrint jasperPrint,
			String defaultFilename) throws IOException, JRException {
		//response.setContentType("application/pdf");
		String defaultname = null;
		if (defaultFilename.trim() != null && defaultFilename != null) {
			defaultname = defaultFilename + ".pdf";
		} else {
			defaultname = "export.pdf";
		}
		String fileName = new String(defaultname.getBytes("utf-8"), "ISO8859-1");
		/*response.setHeader("Content-disposition", "attachment; filename="
				+ fileName);*/

		//ServletOutputStream ouputStream = response.getOutputStream();
		OutputStream ouputStream=new FileOutputStream("D:/" + defaultname);
		JasperExportManager.exportReportToPdfStream(jasperPrint, ouputStream);
		ouputStream.flush();
		ouputStream.close();

	}
	private  void export(Collection datas, Map a, String type,
			String defaultFilename, InputStream is) {
		//logger.debug("导出判断     The method======= export() start.......................");
		try {
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(is);
			prepareReport(jasperReport, type);
			JRDataSource ds = new JRBeanCollectionDataSource(datas);			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, a, ds);
			
			if (PDF_TYPE_AUTO.equals(type)) {
				exportPdf_auto(jasperPrint, defaultFilename);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public  void exportmain(String exportType, Map parameters,
			String jaspername, List lists, String defaultFilename, String url) {
		//logger.debug("进入导出    The method======= exportmain() start.......................");
		ActionContext ct = ActionContext.getContext();
		//HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);						
		//HttpServletResponse response = ServletActionContext.getResponse();		
		String filenurl = null;
		//filenurl = ServletActionContext.getRequest().getRealPath(url + jaspername);// jasper文件放在WebRoot/ireport/xx.jasper</span>	
		filenurl=ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath(url + jaspername);
		
		File file = new File(filenurl);
		InputStream is = null;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		export(lists, parameters, exportType, defaultFilename, is);
	}
	
	
	public void print_KyzExpectmatm(String local_factNo,String local_billNo,String local_visaSort,KyVisabillm vbm,ApplicationContext ac){
		IKyzExpectmatmServices kyzSer=(IKyzExpectmatmServices)ac.getBean("kyzSer");			
		Map<String,Object>map_result=kyzSer.print(local_factNo, local_billNo,local_visaSort,vbm);
		if(map_result!=null&&map_result.size()>0){
			map=(Map<String,Object>)map_result.get("map");
			List<KyzExpectmatm>listkyz=(List<KyzExpectmatm>)map_result.get("list");
			this.exportmain("auto", map,"matterApplication.jasper", listkyz,local_billNo, "jasper/audit/");
		}
	}
	
	
	public void print_KyzContactletter(String local_factNo,String local_billNo,String local_visaSort,KyVisabillm vbm,ApplicationContext ac){
		IKyzContactLetterServices kyzletterSer=(IKyzContactLetterServices)ac.getBean("kyzletterSer");						
		Map<String,Object>map_result=kyzletterSer.print(local_factNo, local_billNo, local_visaSort,vbm);
		if(map_result!=null&&map_result.size()>0){
			map=(Map<String,Object>)map_result.get("map");
			List<KyzContactletter>list=(List<KyzContactletter>)map_result.get("list");
			this.exportmain("auto", map,"kyz_contactletter.jasper", list,local_billNo, "jasper/audit/");
		}		
	}
	
	public void print_webbussletter(String factNo,String billNo,String visaSort,KyVisabillm vbm,ApplicationContext ac){
		IWebBussinessletterServices webbussletterSer=(IWebBussinessletterServices)ac.getBean("webbussletterSer");										
				Map<String,Object>map_result=webbussletterSer.print(factNo, billNo, visaSort,vbm);
				if(map_result!=null&&map_result.size()>0){
					map=(Map<String,Object>)map_result.get("map");
					List<WebBussinessletter>list=(List<WebBussinessletter>)map_result.get("list");
					this.exportmain("auto", map,"webbussletter.jasper", list,billNo, "jasper/audit/");
				}			
	}
	
	public void print_webremittancelist(String factNo,String billNo,String visaSort,KyVisabillm vbm,ApplicationContext ac){
		IWebremittancelistServices webremiSer=(IWebremittancelistServices)ac.getBean("webremiSer");
		Map<String,Object>map_result=webremiSer.print(factNo, billNo, visaSort, vbm);
		map=(Map<String,Object>)map_result.get("map");
		List<Webremittancelist>list=(List<Webremittancelist>)map_result.get("list");
		this.exportmain("auto", map, "webremittancelist.jasper", list, billNo, "jasper/audit/");
	}
	
	
	public void sendEmail(String local_factNo,String local_billNo,String local_visaSort,KyVisabillm vbm2,ApplicationContext ac){
		//ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-dao.xml","spring-services.xml"});
		//IKyVisabillmServices visabillmSer=(IKyVisabillmServices)ac.getBean("visabillmSer");
		//IWebuserEmailServices webuseremailSer=(IWebuserEmailServices)ac.getBean("webuseremailSer");/****備簽人****/
		IWebuserEmailAServices webuseremailaSer=(IWebuserEmailAServices)ac.getBean("webuseremailaSer");/******知會人********/
		//KyVisabillm vbm2=visabillmSer.findById(local_factNo, local_visaSort, local_billNo);//用參數傳遞vbm,減少連接數據庫
		List<KyVisabills>list_visa2=vbm2.getKyVisabillses();
		//这个类主要是设置邮件   
		List<String>list_emails=new ArrayList<String>();//所有發送人
		list_emails.add("kyuen@yydg.com.cn");
		//由於出差函文流程中可能不包括申請人， 所有需要從函文中獲取申請email 20160621
		if(vbm2.getWebbussletter().getUserEmail()!=null&&!vbm2.getWebbussletter().getUserEmail().equals("")){
			list_emails.add(vbm2.getWebbussletter().getUserEmail());
		}
		for(KyVisabills bills:list_visa2){
			list_emails.add(bills.getVisaSigner());
			if(bills.getFlowMk().equals("Y")){//要簽核的人才需要通知知會人
				List<String>list_emailPwd=webuseremailaSer.findByEmail(local_factNo,bills.getVisaSigner(),local_visaSort);
				for(String str:list_emailPwd){
					list_emails.add(str);
				}
			}
		}
		
		String[] attachFileNames = { "d:/" + local_billNo + ".pdf" };// 附件
		SimpleMailSender sms = new SimpleMailSender();
		MailSenderInfo mailInfo = new MailSenderInfo();
		
		for(String email:list_emails){//for
			mailInfo.setValidate(true);			
			mailInfo.setSubject("函文知會定時通知(審核完畢)_" + local_billNo + "("
					+ local_factNo + ")");

			mailInfo.setAttachFileNames(attachFileNames);
			mailInfo.setContent("單號為:" + "<span style='color:red'>"
					+ local_billNo + "</span>" + "的函文已審核完畢,請查看附件"
					+ "<br/>本郵件自動定時發送，請勿回覆");

			String toAddress = email;
			mailInfo.setToAddress(toAddress);
			sms.sendHtmlMail(mailInfo);// 发送html格式
		}//for
		File file = new File("d:/" + local_billNo + ".pdf");
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			}
		}							       	          
	}
	
}
