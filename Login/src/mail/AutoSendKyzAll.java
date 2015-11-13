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
import services.IWebFactServices;
import services.IWebuserEmailServices;

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
		ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-dao.xml","spring-services.xml","spring-action.xml"});
		IKyVisabillmServices visabillmSer=(IKyVisabillmServices)ac.getBean("visabillmSer");
		List<KyVisabillm>list_vbm=visabillmSer.findByVisaMk2("Y");//所有已經簽核完畢的函文，但未發送email知會		
		
		if(list_vbm.size()>0){//start if
			MailSenderInfo mailInfo=new MailSenderInfo();
			SimpleMailSender sms=new SimpleMailSender();
			mailInfo.setValidate(true);
			/*mailInfo.setUserName("kyuen@yydg.com.cn");
			mailInfo.setFromAddress("<kyuen@yydg.com.cn>");			
			mailInfo.setPassword("yydgmail");*/
			mailInfo.setToAddress("kyuen@yydg.com.cn");//收件人爲本機，檢測Email是否發送成功
			mailInfo.setContent("簽核完畢");
			for(int i=0;i<list_vbm.size();i++){//start for			
				String factNo=list_vbm.get(i).getId().getFactNo();
				String billNo=list_vbm.get(i).getId().getBillNo();
				String visaSort=list_vbm.get(i).getId().getVisaSort();
				/*String visaMk=list_vbm.get(i).getVisaMk();
				String emailUrl="http://203.85.73.161/Login/vbm_findById_email?visaSort="+visaSort+"& billNo="+billNo
				         +"& factNo="+factNo+"& email="+signerNext;*/
				
				try {
					if(billNo.substring(0, 2).equals("EM")){
						this.addVisabillsAndEmail(factNo, billNo, visaSort);
					}else{
						this.addVisabillsAndEmail2(factNo, billNo, visaSort);
					}
					mailInfo.setSubject("發送Email成功"+billNo);
					sms.sendHtmlMail(mailInfo);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					mailInfo.setSubject("發送Email失敗"+billNo);
					sms.sendHtmlMail(mailInfo);
				}						
				list_vbm.get(i).setEmailMk("Y");//表示已發送郵件
				visabillmSer.add(list_vbm.get(i));
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
	public void addVisabillsAndEmail(String local_factNo,String local_billNo,String local_visaSort) throws IOException{
		
		/**
		 * 打印
		 */
		this.print_KyzExpectmatm(local_factNo, local_billNo, local_visaSort);
		
		/**
		 * 發郵件
		 * 由於要給所有人(包括不要審核的)發送郵件,所以要重新從數據庫中獲取,而不能使用上面已有的list_visa
		 */
		this.sendEmail(local_factNo, local_billNo, local_visaSort);      
		
		
	}
	
	/**
	 * 內部聯絡函
	 * @param local_factNo
	 * @param local_billNo
	 * @param local_visaSort
	 * @throws IOException
	 */
	public void addVisabillsAndEmail2(String local_factNo,String local_billNo,String local_visaSort) throws IOException{
		/**
		 * 打印
		 */
		this.print_KyzContactletter(local_factNo, local_billNo, local_visaSort);
		
		/**
		 * 發郵件
		 * 由於要給所有人(包括不要審核的)發送郵件,所以要重新從數據庫中獲取,而不能使用上面已有的list_visa
		 */
		 this.sendEmail(local_factNo, local_billNo, local_visaSort);     
		
								
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
	
	
	public void print_KyzExpectmatm(String local_factNo,String local_billNo,String local_visaSort){
		ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-dao.xml","spring-services.xml","spring-action.xml"});
		IWebFactServices webFactSer=(IWebFactServices)ac.getBean("webFactSer");
		IKyzExpectmatmServices kyzSer=(IKyzExpectmatmServices)ac.getBean("kyzSer");
		IKyVisabillmServices visabillmSer=(IKyVisabillmServices)ac.getBean("visabillmSer");
		IKyzVisaFlowServices visaSer=(IKyzVisaFlowServices)ac.getBean("visaSer");
		IKyVisaBillsServices visabillSer=(IKyVisaBillsServices)ac.getBean("visabillSer");
		IKyzExpectmatmFileServices kyzexpfileSer=(IKyzExpectmatmFileServices)ac.getBean("kyzexpfileSer");
		IWebuserEmailServices webuseremailSer=(IWebuserEmailServices)ac.getBean("webuseremailSer");
		KyzExpectmatmId kyzid=new KyzExpectmatmId();
		kyzid.setBillNo(local_billNo);
		kyzid.setFactNo(local_factNo);
		String factname=webFactSer.selByid(kyzid.getFactNo());
		String factCode="";
		List<KyzExpectmatm> listkyz=kyzSer.findById_Print(kyzid);
		/*if(listkyz.size()==0){
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>alert('單號為"+billNo+"的函文不存在!');window.close()</script>");
			return null;
		}*/
		/**************************簡轉繁體***************************/
		listkyz.get(0).setSecNo(ZHConverter.convert(listkyz.get(0).getSecNo(), ZHConverter.TRADITIONAL));
		listkyz.get(0).setUserNm(ZHConverter.convert(listkyz.get(0).getUserNm(), ZHConverter.TRADITIONAL));			
		listkyz.get(0).setMemoMk(ZHConverter.convert(listkyz.get(0).getMemoMk(), ZHConverter.TRADITIONAL));
		listkyz.get(0).setMemoSmk(ZHConverter.convert(listkyz.get(0).getMemoSmk(), ZHConverter.TRADITIONAL));
		/**************************簡轉繁體***************************/
		factCode=listkyz.get(0).getFactCode();
		String result=factname+"("+factCode+")"+"費用申請單";
		map = new HashMap<String, Object>();
		//map.put("SUBREPORT_DIR",ServletActionContext.getRequest().getRealPath("/jasper/audit/")+ "/");		
		//map.put("pic", ServletActionContext.getRequest().getRealPath("/jasper/audit/images/")+ "/");//圖片路徑
		String path1=ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/jasper/audit/")+ "/";
		String path2=ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/jasper/audit/images/")+ "/";
		map.put("SUBREPORT_DIR",ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/jasper/audit/")+ "/");
		map.put("pic", ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/jasper/audit/images/")+ "/");//圖片路徑
		map.put("pfactno", kyzid.getFactNo());
		map.put("pbillno",kyzid.getBillNo());
		map.put("title",result);
		List<KyzExpectmats> sub_list = new ArrayList<KyzExpectmats>();
		KyzExpectmats temp=new KyzExpectmats();
						
		/*for(int i=0;i<listkyz.get(0).getKyzExpectmatses().size();i++){
			KyzExpectmats kyzs=listkyz.get(0).getKyzExpectmatses().get(i);
			sub_list.add(kyzs);
		}
		while(sub_list.size()<10){
			sub_list.add(temp);
		}*/
		if(listkyz.get(0).getKyzExpectmatses().size()==1){
			KyzExpectmats kyzss=listkyz.get(0).getKyzExpectmatses().get(0);
			if(kyzss.getMatNo()==null&&kyzss.getItemNm()==null){
				listkyz.get(0).setKyzsMk("1");
			}else{
				for(int i=0;i<listkyz.get(0).getKyzExpectmatses().size();i++){
					KyzExpectmats kyzs=listkyz.get(0).getKyzExpectmatses().get(i);
					kyzs.setMatNo(ZHConverter.convert(kyzs.getMatNo(), ZHConverter.TRADITIONAL));
					kyzs.setQtyPair(ZHConverter.convert(kyzs.getQtyPair(), ZHConverter.TRADITIONAL));
					kyzs.setItemNm(ZHConverter.convert(kyzs.getItemNm(), ZHConverter.TRADITIONAL));
					sub_list.add(kyzs);
				}
				while(sub_list.size()<10){
					sub_list.add(temp);
				}
			}
		}else{
			for(int i=0;i<listkyz.get(0).getKyzExpectmatses().size();i++){
				KyzExpectmats kyzs=listkyz.get(0).getKyzExpectmatses().get(i);
				kyzs.setMatNo(ZHConverter.convert(kyzs.getMatNo(), ZHConverter.TRADITIONAL));
				kyzs.setQtyPair(ZHConverter.convert(kyzs.getQtyPair(), ZHConverter.TRADITIONAL));
				kyzs.setItemNm(ZHConverter.convert(kyzs.getItemNm(), ZHConverter.TRADITIONAL));
				sub_list.add(kyzs);
			}
			while(sub_list.size()<10){
				sub_list.add(temp);
			}
		}
		
		
		Map sub_map=new HashMap<String,Object>();
		sub_map.put("sub_list", sub_list);
					
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		KyVisabillm vbm=visabillmSer.findById(local_factNo, local_visaSort, local_billNo);
		List<KyVisabills>list_visa=vbm.getKyVisabillses();		
		List<KyzVisaflow>list_visaflow=visaSer.findByType(local_factNo,local_visaSort);
		
		/**
		 * 如果最後不用審核,則去掉
		 */
		int nos=visabillSer.findBillsWithNo(local_visaSort, local_billNo);
		if(nos>0){
			for(int i=0;i<nos;i++){
				list_visa.remove(list_visa.size()-1);
				list_visaflow.remove(list_visaflow.size()-1);
			}
		}
		/*if(list_visa.get(list_visa.size()-3).getFlowMk().equals("N")){
			list_visa.remove(list_visa.size()-1);//(>=1000的最後三位都不要審核，則總長度-3  20150803)
			list_visa.remove(list_visa.size()-1);
			list_visa.remove(list_visa.size()-1);
		}else if(list_visa.get(list_visa.size()-2).getFlowMk().equals("N")){
			list_visa.remove(list_visa.size()-1);
			list_visa.remove(list_visa.size()-1);
		}else if(list_visa.get(list_visa.size()-1).getFlowMk().equals("N")){
			list_visa.remove(list_visa.size()-1);
		}
		
		if(list_visaflow.get(list_visaflow.size()-3).getFlowMk().equals("N")){
			list_visaflow.remove(list_visaflow.size()-1);//(>=1000的最後三位都不要審核，則總長度-3    20150803)
			list_visaflow.remove(list_visaflow.size()-1);
			list_visaflow.remove(list_visaflow.size()-1);
		}else if(list_visaflow.get(list_visaflow.size()-2).getFlowMk().equals("N")){
			list_visaflow.remove(list_visaflow.size()-1);
			list_visaflow.remove(list_visaflow.size()-1);
		}else if(list_visaflow.get(list_visaflow.size()-1).getFlowMk().equals("N")){
			list_visaflow.remove(list_visaflow.size()-1);
		}*/
		List<VisabillsTemp>list_visabillstemp=new ArrayList();		
		for(int i=0;i<list_visa.size();i++){//for
			VisabillsTemp visabillstemp=new VisabillsTemp();
			String visa_result="";
			String visamk_temp="";
			Date date=null;			
			String datestr=list_visa.get(i).getDateVisa();
			try {
				if(datestr!=null){
					date=format.parse(datestr);
					visabillstemp.setCreateDate(date);
				}
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String name=list_visa.get(i).getVisaRank();
			String visamk=list_visa.get(i).getVisaMk();
			String visadate=list_visa.get(i).getDateVisa();
			String memo=list_visa.get(i).getMemo();
			if(visamk.equals("Y")){
				visamk_temp="(已審核)";
			}
			if(visamk.equals("N")){
				visamk_temp="(未審核)";
			}
			if(visamk.equals("T")){
				visamk_temp="(未通過)";
			}			
			visa_result=name+visamk_temp;
			visabillstemp.setVisaNameAndMk(visa_result);
			if(list_visa.size()==list_visaflow.size()){
				String visaRank=list_visaflow.get(i).getVisaRank();
				visabillstemp.setVisaRank(visaRank+":");
			}
			
			if(memo!=null){
				visabillstemp.setMemo("(備註:"+memo+")");
			}
			visabillstemp.setVisaSigner(list_visa.get(i).getVisaSigner());
			visabillstemp.setVisaMk(list_visa.get(i).getVisaMk());
			visabillstemp.setVisaName(name);
			list_visabillstemp.add(visabillstemp);
		}//for
		/*********************簡體轉繁體******************/
		for(int i=0;i<list_visabillstemp.size();i++){
			list_visabillstemp.get(i).setMemo(ZHConverter.convert(list_visabillstemp.get(i).getMemo(), ZHConverter.TRADITIONAL));
			list_visabillstemp.get(i).setVisaName(ZHConverter.convert(list_visabillstemp.get(i).getVisaName(), ZHConverter.TRADITIONAL));
			list_visabillstemp.get(i).setVisaNameAndMk(ZHConverter.convert(list_visabillstemp.get(i).getVisaNameAndMk(), ZHConverter.TRADITIONAL));
			list_visabillstemp.get(i).setVisaRank(ZHConverter.convert(list_visabillstemp.get(i).getVisaRank(), ZHConverter.TRADITIONAL));			
		}
		/*********************簡體轉繁體******************/
						
		Map visa_map=new HashMap<String,Object>();
		visa_map.put("list_visa", list_visabillstemp);
		
		map.put("sub_map", sub_map);
		map.put("visa_map", visa_map);
		Map main_map=new HashMap<String,Object>();    /*把list（List<KyzExpectmatm> list=kyzSer.findById_Print(id)）放在一个子表,便于打印  20150804*/
		main_map.put("list_main", listkyz);
		map.put("main_map", main_map);
		
		/*函文附檔*/
		//String pic_file=ServletActionContext.getRequest().getRealPath("/KyzexpFile/"+kyzid.getBillNo()+"/")+"/";//函文附檔圖片路徑
		String pic_file=new File("d:\\KyzexpFile_backup\\"+kyzid.getBillNo()).toString();//函文附檔圖片路徑(附檔在D盤的路徑)
		List<KyzExpectmatmFile>list_kyzexpfile=kyzexpfileSer.findByBillNo(kyzid.getBillNo());
		if(pic_file!=null&&list_kyzexpfile.size()>0){
			map.put("pic_file", pic_file+"\\");
			Map file_map=new HashMap<String,Object>();
			file_map.put("list_kyzexpfile", list_kyzexpfile);
			map.put("file_map", file_map);
		}		
		this.exportmain("auto", map,"matterApplication.jasper", listkyz,local_billNo, "jasper/audit/");
	}
	
	
	public void print_KyzContactletter(String local_factNo,String local_billNo,String local_visaSort){
		ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-dao.xml","spring-services.xml","spring-action.xml"});
		IWebFactServices webFactSer=(IWebFactServices)ac.getBean("webFactSer");
		IKyzContactLetterServices kyzletterSer=(IKyzContactLetterServices)ac.getBean("kyzletterSer");
		IKyVisabillmServices visabillmSer=(IKyVisabillmServices)ac.getBean("visabillmSer");
		IKyzVisaFlowServices visaSer=(IKyzVisaFlowServices)ac.getBean("visaSer");
		IKyVisaBillsServices visabillSer=(IKyVisaBillsServices)ac.getBean("visabillSer");
		IWebuserEmailServices webuseremailSer=(IWebuserEmailServices)ac.getBean("webuseremailSer");
		IKyzExpectmatmFileServices kyzexpfileSer=(IKyzExpectmatmFileServices)ac.getBean("kyzexpfileSer");
		List<KyzContactletter>list=new ArrayList<KyzContactletter>();
		Map<String,Object>map=new HashMap<String,Object>();
		String factname=webFactSer.selByid(local_factNo);
		String factCode="";
		KyzContactletter letter=kyzletterSer.findById(local_factNo,local_billNo);
		/*if(letter==null){
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>alert('單號為"+local_billNo+"的函文不存在!');window.close()</script>");
			return null;
		}else{
			list.add(letter);
		}*/	
		/*******************簡轉繁體********************/						
		letter.setUserNm(ZHConverter.convert(letter.getUserNm(), ZHConverter.TRADITIONAL));
		letter.setToUser(ZHConverter.convert(letter.getToUser(), ZHConverter.TRADITIONAL));
		letter.setChargeList(ZHConverter.convert(letter.getChargeList(), ZHConverter.TRADITIONAL));
		letter.setTitle(ZHConverter.convert(letter.getTitle(), ZHConverter.TRADITIONAL));
		letter.setMemoMk(ZHConverter.convert(letter.getMemoMk(), ZHConverter.TRADITIONAL));
		/*******************簡轉繁體********************/
		list.add(letter);
		factCode=letter.getFactCode();
		String result=factname+"("+factCode+")"+"內部聯絡函";
		//map = new HashMap<String, Object>();
		//map.put("SUBREPORT_DIR",ServletActionContext.getRequest().getRealPath("/jasper/audit/")+ "/");
		//map.put("pic", ServletActionContext.getRequest().getRealPath("/jasper/audit/images/")+ "/");//圖片路徑	
		map.put("SUBREPORT_DIR",ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/jasper/audit/")+ "/");
		map.put("pic", ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/jasper/audit/images/")+ "/");//圖片路徑
		map.put("pfactno", local_factNo);
		map.put("pbillno",local_billNo);
		map.put("title",result);
		List<KyzExpectmats> sub_list = new ArrayList<KyzExpectmats>();		
		KyzExpectmats temp=new KyzExpectmats();		
				
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		KyVisabillm vbm=visabillmSer.findById(local_factNo, local_visaSort, local_billNo);
		List<KyVisabills>list_visa=vbm.getKyVisabillses();
		List<KyzVisaflow>list_visaflow=visaSer.findByType(local_factNo,local_visaSort);
		int num1=list_visa.size();
		int num2=list_visaflow.size();
		/**
		 * 最後個不用審核的,就去掉
		 */
		int nos=visabillSer.findBillsWithNo(local_visaSort, local_billNo);
		if(nos>0){
			for(int i=0;i<nos;i++){
				list_visa.remove(list_visa.size()-1);
				list_visaflow.remove(list_visaflow.size()-1);
			}
		}		
		/*if(list_visa.get(list_visa.size()-3).getFlowMk().equals("N")){//(>=1000的，後面三個都不要簽核   20150803)
			list_visa.remove(list_visa.size()-1);
			list_visa.remove(list_visa.size()-1);
			list_visa.remove(list_visa.size()-1);
		}else if(list_visa.get(list_visa.size()-2).getFlowMk().equals("N")){
			list_visa.remove(list_visa.size()-1);
			list_visa.remove(list_visa.size()-1);
		}else if(list_visa.get(list_visa.size()-1).getFlowMk().equals("N")){
			list_visa.remove(list_visa.size()-1);
		}
		if(list_visaflow.get(list_visaflow.size()-3).getFlowMk().equals("N")){//(>=1000的，後面三個都不要簽核   20150803)
			list_visaflow.remove(list_visaflow.size()-1);
			list_visaflow.remove(list_visaflow.size()-1);
			list_visaflow.remove(list_visaflow.size()-1);
		}else if(list_visaflow.get(list_visaflow.size()-2).getFlowMk().equals("N")){
			list_visaflow.remove(list_visaflow.size()-1);
			list_visaflow.remove(list_visaflow.size()-1);
		}else if(list_visaflow.get(list_visaflow.size()-1).getFlowMk().equals("N")){
			list_visaflow.remove(list_visaflow.size()-1);
		}*/
		List<VisabillsTemp>list_visabillstemp=new ArrayList();		
		for(int i=0;i<list_visa.size();i++){//for
			VisabillsTemp visabillstemp=new VisabillsTemp();
			String visa_result="";
			String visamk_temp="";
			Date date=null;
			
			String datestr=list_visa.get(i).getDateVisa();
			try {
				if(datestr!=null){
					date=format.parse(datestr);
					visabillstemp.setCreateDate(date);
				}
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String name=list_visa.get(i).getVisaRank();
			String visamk=list_visa.get(i).getVisaMk();
			String visadate=list_visa.get(i).getDateVisa();
			String memo=list_visa.get(i).getMemo();
			if(visamk.equals("Y")){
				visamk_temp="(已審核)";
			}
			if(visamk.equals("N")){
				visamk_temp="(未審核)";
			}
			if(visamk.equals("T")){
				visamk_temp="(未通過)";
			}			
			visa_result=name+visamk_temp;
			visabillstemp.setVisaNameAndMk(visa_result);			
			if(list_visa.size()==list_visaflow.size()){
				String visaRank=list_visaflow.get(i).getVisaRank();
				visabillstemp.setVisaRank(visaRank+":");
			}
			if(memo!=null){
				visabillstemp.setMemo("(備註:"+memo+")");
			}
			visabillstemp.setVisaSigner(list_visa.get(i).getVisaSigner());
			visabillstemp.setVisaMk(list_visa.get(i).getVisaMk());
			visabillstemp.setVisaName(name);
			list_visabillstemp.add(visabillstemp);
		}//for
		/*********************簡體轉繁體******************/
		for(int i=0;i<list_visabillstemp.size();i++){
			list_visabillstemp.get(i).setMemo(ZHConverter.convert(list_visabillstemp.get(i).getMemo(), ZHConverter.TRADITIONAL));
			list_visabillstemp.get(i).setVisaName(ZHConverter.convert(list_visabillstemp.get(i).getVisaName(), ZHConverter.TRADITIONAL));
			list_visabillstemp.get(i).setVisaNameAndMk(ZHConverter.convert(list_visabillstemp.get(i).getVisaNameAndMk(), ZHConverter.TRADITIONAL));
			list_visabillstemp.get(i).setVisaRank(ZHConverter.convert(list_visabillstemp.get(i).getVisaRank(), ZHConverter.TRADITIONAL));			
		}
		/*********************簡體轉繁體******************/
		
		
		Map visa_map=new HashMap<String,Object>();
		visa_map.put("list_visa", list_visabillstemp);
		
		map.put("visa_map", visa_map);
		Map main_map=new HashMap<String,Object>();    /*把list（List<KyzExpectmatm> list=kyzSer.findById_Print(id)）放在一个子表,便于打印  20150804*/
		main_map.put("list_main", list);
		map.put("main_map", main_map);
		/*函文附檔*/
		//String pic_file=ServletActionContext.getRequest().getRealPath("/KyzexpFile/"+id.getBillNo()+"/")+"/";//函文附檔圖片路徑(附檔在項目的路徑)
		String pic_file=new File("d:\\KyzletterexpFile_backup\\"+local_billNo).toString();//函文附檔圖片路徑(附檔在D盤的路徑)
		List<KyzExpectmatmFile>list_kyzexpfile=kyzexpfileSer.findByBillNo(local_billNo);
		if(pic_file!=null&&list_kyzexpfile.size()>0){
			map.put("pic_file", pic_file+"\\");
			Map file_map=new HashMap<String,Object>();
			file_map.put("list_kyzexpfile", list_kyzexpfile);
			map.put("file_map", file_map);
		}
				
		this.exportmain("auto", map,"kyz_contactletter.jasper", list,local_billNo, "jasper/audit/");
		
	}
	
	
	public void sendEmail(String local_factNo,String local_billNo,String local_visaSort){
		ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-dao.xml","spring-services.xml","spring-action.xml"});
		IKyVisabillmServices visabillmSer=(IKyVisabillmServices)ac.getBean("visabillmSer");
		IWebuserEmailServices webuseremailSer=(IWebuserEmailServices)ac.getBean("webuseremailSer");
		KyVisabillm vbm2=visabillmSer.findById(local_factNo, local_visaSort, local_billNo);
		List<KyVisabills>list_visa2=vbm2.getKyVisabillses();
		//这个类主要是设置邮件   
		
		String[] attachFileNames = { "d:/" + local_billNo + ".pdf" };// 附件
		SimpleMailSender sms = new SimpleMailSender();
		MailSenderInfo mailInfo = new MailSenderInfo();
				
		for (int i = 0; i < list_visa2.size(); i++) {//for			
			// 这个类主要来发送邮件			
			mailInfo.setValidate(true);
			/*mailInfo.setUserName("kyuen@yydg.com.cn");
			mailInfo.setPassword("yydgmail");// 您的邮箱密码
			mailInfo.setFromAddress("<kyuen@yydg.com.cn>");*/
			mailInfo.setSubject("函文知會定時通知(審核完畢)_" + local_billNo + "("
					+ local_factNo + ")");

			mailInfo.setAttachFileNames(attachFileNames);
			mailInfo.setContent("單號為:" + "<span style='color:red'>"
					+ local_billNo + "</span>" + "的函文已審核完畢,請查看附件"
					+ "<br/>本郵件自動定時發送，請勿回覆");

			String toAddress = list_visa2.get(i).getVisaSigner();
			mailInfo.setToAddress(toAddress);
			sms.sendHtmlMail(mailInfo);// 发送html格式

			// 给备签人发送邮件
			/******************20151113备签人请使用方法findByFactNoAEmailPwd2(String factNo,String email)**********************/
			/*String emailPwd = webuseremailSer.findEmailPWD(local_factNo,list_visa2.get(i).getVisaSigner());			
			if (emailPwd != null) {				
				mailInfo.setValidate(true);
				mailInfo.setUserName("kyuen@yydg.com.cn");
				mailInfo.setPassword("yydgmail");// 您的邮箱密码
				mailInfo.setFromAddress("<kyuen@yydg.com.cn>");
				mailInfo.setSubject("函文知會定時通知(審核完畢)_" + local_billNo + "("
						+ local_factNo + ")");
				mailInfo.setAttachFileNames(attachFileNames);
				mailInfo.setContent("單號為:" + "<span style='color:red'>"
						+ local_billNo + "</span>" + "的函文已審核完畢,請查看附件"
						+ "<br/>本郵件自動定時發送，請勿回覆");
				mailInfo.setToAddress(emailPwd);
				sms.sendHtmlMail(mailInfo);// 发送html格式
			}*/
			List<String>list_emailPwd=webuseremailSer.findByFactNoAEmailPwd2(local_factNo,list_visa2.get(i).getVisaSigner());
			if(list_emailPwd.size()>0){//if
				for(int j=0;j<list_emailPwd.size();j++){
					mailInfo.setValidate(true);					
					mailInfo.setSubject("函文知會定時通知(審核完畢)_" + local_billNo + "("
							+ local_factNo + ")");
					mailInfo.setAttachFileNames(attachFileNames);
					mailInfo.setContent("單號為:" + "<span style='color:red'>"
							+ local_billNo + "</span>" + "的函文已審核完畢,請查看附件"
							+ "<br/>本郵件自動定時發送，請勿回覆");
					mailInfo.setToAddress(list_emailPwd.get(j));
					sms.sendHtmlMail(mailInfo);// 发送html格式
				}
			}//if
		}//for
	       
	          File file = new File("d:/" + local_billNo + ".pdf");
				if (file.exists()) {
					if (file.isFile()) {
						file.delete();
					}
				}
	}
	
	/*public void sendTest(String local_factNo,String local_billNo,String local_visaSort){
		ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-dao.xml","spring-services.xml","spring-action.xml"});
		IKyVisabillmServices visabillmSer=(IKyVisabillmServices)ac.getBean("visabillmSer");
		IWebuserEmailServices webuseremailSer=(IWebuserEmailServices)ac.getBean("webuseremailSer");
		KyVisabillm vbm2=visabillmSer.findById(local_factNo, local_visaSort, local_billNo);
		List<KyVisabills>list_visa2=vbm2.getKyVisabillses();
		//这个类主要是设置邮件   
		String[] attachFileNames = { "d:/" + local_billNo + ".pdf" };// 附件
		MailSenderInfo mailInfo = new MailSenderInfo();
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		for (int i = 0; i < list_visa2.size(); i++) {
			
			mailInfo.setValidate(true);
			mailInfo.setUserName("kyuen@yydg.com.cn");
			mailInfo.setPassword("yydgmail");// 您的邮箱密码
			mailInfo.setFromAddress("<kyuen@yydg.com.cn>");
			mailInfo.setSubject("函文知會定時通知(審核完畢)_" + local_billNo + "("
					+ local_factNo + ")");

			// String[] attachFileNames={"d:/"+local_billNo+".pdf"};
			mailInfo.setAttachFileNames(attachFileNames);
			mailInfo.setContent("單號為:" + "<span style='color:red'>"
					+ local_billNo + "</span>" + "的函文已審核完畢,請查看附件"
					+ "<br/>本郵件自動定時發送，請勿回覆");

			String toAddress = list_visa2.get(i).getVisaSigner();
			mailInfo.setToAddress(toAddress);
			sms.sendHtmlMail(mailInfo);// 发送html格式
			
			// 给备签人发送邮件
			String emailPwd = webuseremailSer.findEmailPWD(local_factNo,list_visa2.get(i).getVisaSigner());
			if(emailPwd!=null){
				
				mailInfo.setValidate(true);
				mailInfo.setUserName("kyuen@yydg.com.cn");
				mailInfo.setPassword("yydgmail");// 您的邮箱密码
				mailInfo.setFromAddress("<kyuen@yydg.com.cn>");
				mailInfo.setSubject("函文知會定時通知(審核完畢)_" + local_billNo + "("
						+ local_factNo + ")");
				mailInfo.setAttachFileNames(attachFileNames);
				mailInfo.setContent("單號為:" + "<span style='color:red'>"
						+ local_billNo + "</span>" + "的函文已審核完畢,請查看附件"
						+ "<br/>本郵件自動定時發送，請勿回覆");

				mailInfo.setToAddress(emailPwd);
				sms.sendHtmlMail(mailInfo);// 发送html格式
			}
		}
	       
	          File file = new File("d:/" + local_billNo + ".pdf");
				if (file.exists()) {
					if (file.isFile()) {
						file.delete();
					}
				}
	}*/

	

}