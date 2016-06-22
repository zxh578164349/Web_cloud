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
import services.IKyzExpectmatmsServices;
import services.IKyzVisaFlowServices;
import services.IWebBussinessletterServices;
import services.IWebFactServices;
import services.IWebuserEmailAServices;
import services.IWebuserEmailServices;
import util.GlobalMethod;
import util.JasperHelper;

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
import entity_temp.VisabillsTemp;

public class AutoSendKyz extends QuartzJobBean{
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
		List<KyVisabillm>list_vbm=visabillmSer.findByVisaMk("Y");//所有未簽核完畢的函文			
		GlobalMethod.sendEmailA(ac,list_vbm);		
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
			// parameters.put("wheresql","and status='3'");
			/*
			 * parameters.put("wheresql",""); String diver =
			 * "oracle.jdbc.driver.OracleDriver"; String url =
			 * "jdbc:oracle:thin:@192.168.1.156:1521:orcl"; String username =
			 * "qqwcrm0625"; String password = "qqwcrm"; ReportDataSource
			 * datasource = new ReportDataSource(); datasource.setDiver(diver);
			 * datasource.setUrl(url); datasource.setUsername(username);
			 * datasource.setPassword(password); Connection
			 * con=getConnection(datasource);
			 */
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

}
