package action;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import mail.MailSenderInfo;
import mail.SimpleMailSender;

import services.IKyVisaBillsServices;
import services.IKyVisabillmServices;
import services.IKyzExpectmatmLogServices;
import services.IKyzVisaFlowServices;
import services.IWebBussinessletterServices;
import services.IWebFactServices;
import services.IWebuserEmailServices;
import util.GlobalMethod;
import util.JasperHelper;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.spreada.utils.chinese.ZHConverter;

import entity.KyVisabillm;
import entity.KyVisabills;
import entity.KyzContactletter;
import entity.KyzExpectmatmFile;
import entity.KyzExpectmatmLog;
import entity.KyzExpectmats;
import entity.KyzVisaflow;
import entity.WebBussinessletter;
import entity.WebUser;
import entity_temp.VisabillsTemp;

public class WebBussinessletterAction extends ActionSupport implements ServletResponseAware{
	private String billNo;
	private String factNo;
	private WebBussinessletter bussletter;
	private PageBean bean;
	private int page;
	private String isnull;
	private String dateFrom;
	private String dateEnd;
	private String timeFrom;
	private String timeEnd;
	private String ajaxResult;//申請函文時返回的ajax結果,   0:提交成功       1:提交失敗
	private String lookordown;
	private String visaSort;
	private String readMk;//備註標記
	private String itemNo;
	private int backIndex;//返回標識      0或null:不走返回路徑         1:走返回路徑
	private IWebBussinessletterServices webbussletterSer;
	private IKyVisabillmServices visabillmSer;
	private javax.servlet.http.HttpServletResponse response;
	private IKyzExpectmatmLogServices kyzExpLogSer;
	private IWebuserEmailServices webuseremailSer;
	
	
	public int getBackIndex() {
		return backIndex;
	}
	public void setBackIndex(int backIndex) {
		this.backIndex = backIndex;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getReadMk() {
		return readMk;
	}
	public void setReadMk(String readMk) {
		this.readMk = readMk;
	}
	public String getVisaSort() {
		return visaSort;
	}
	public void setVisaSort(String visaSort) {
		this.visaSort = visaSort;
	}
	public String getAjaxResult() {
		return ajaxResult;
	}
	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}
	public String getLookordown() {
		return lookordown;
	}
	public void setLookordown(String lookordown) {
		this.lookordown = lookordown;
	}
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	public String getTimeFrom() {
		return timeFrom;
	}
	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}
	public String getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	public String getIsnull() {
		return isnull;
	}
	public void setIsnull(String isnull) {
		this.isnull = isnull;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getFactNo() {
		return factNo;
	}
	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}
	public WebBussinessletter getBussletter() {
		return bussletter;
	}
	public void setBussletter(WebBussinessletter bussletter) {
		this.bussletter = bussletter;
	}
	public PageBean getBean() {
		return bean;
	}
	public void setBean(PageBean bean) {
		this.bean = bean;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setWebbussletterSer(IWebBussinessletterServices webbussletterSer) {
		this.webbussletterSer = webbussletterSer;
	}
	
	
	public void setVisabillmSer(IKyVisabillmServices visabillmSer) {
		this.visabillmSer = visabillmSer;
	}
	
	
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	
	public void setKyzExpLogSer(IKyzExpectmatmLogServices kyzExpLogSer) {
		this.kyzExpLogSer = kyzExpLogSer;
	}
	
	
	public void setWebuseremailSer(IWebuserEmailServices webuseremailSer){
		this.webuseremailSer=webuseremailSer;
	}
	public String add() throws ParseException{				
		String result="add";
		DateFormat fmt1=new SimpleDateFormat("yyyyMMdd");
		DateFormat fmt2=new SimpleDateFormat("yyyyMMddhhmm");
		bussletter.setDateFrom(fmt1.parse(dateFrom));
		bussletter.setDateEnd(fmt1.parse(dateEnd));
		if(timeFrom!=null&&!timeFrom.equals("")){
			bussletter.setTimeFrom(fmt2.parse(timeFrom));
		}
		if(timeEnd!=null&&!timeEnd.equals("")){
			bussletter.setTimeEnd(fmt2.parse(timeEnd));
		}			
			try{
				bussletter.setVisaSortM(bussletter.getVisaSort().substring(0,2));
				if(isnull.equals("isNull")){
				//bussletter.setVisaSortM(bussletter.getVisaSort().substring(0,2));
				webbussletterSer.add(bussletter);
				KyVisabillm vbm=visabillmSer.findById(bussletter.getFactNo(),bussletter.getVisaSort(), bussletter.getBlNo());
				List<String>list_emailPwd=webuseremailSer.findByFactNoAEmailPwd2(vbm.getId().getFactNo(),vbm.getSignerNext());
				/**
				 * 發送郵件
				 */
				GlobalMethod.sendNewEmail(vbm,list_emailPwd);//發送郵件								
			    ajaxResult="0";			      
				}else{
					//bussletter.setVisaSortM(bussletter.getVisaSort().substring(0,2));
					webbussletterSer.add(bussletter);
					ajaxResult="0";
				}
			}catch(Exception e){
				ajaxResult="1";
			}			
		
		return result;
	}
	public String findPageBean(){
		ActionContext.getContext().getSession().remove("allrow");//首次進入，清除分頁的總條數（dao層中的allrow）
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		ActionContext.getContext().getSession().remove("public_billNo");
		ActionContext.getContext().getSession().remove("public_factNo");
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		bean=webbussletterSer.findPageBean(25, page, billNo, factNo,user);
		return "beanList";	
	}
	public String findPageBean2(){
		ActionContext.getContext().getSession().remove("allrow");//首次進入，清除分頁的總條數（dao層中的allrow）
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
			if(billNo==null||billNo.equals("")){
				factNo="nothing";
			}
		}
		ActionContext.getContext().getSession().put("public_billNo", billNo);
		ActionContext.getContext().getSession().put("public_factNo", factNo);
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		bean=webbussletterSer.findPageBean(25, page, billNo, factNo,user);
		return "beanList1";
	}
	public String findPageBean3(){
		String result="beanList1";
		if(backIndex==1){
			result="beanList";
		}
		billNo=(String)ActionContext.getContext().getSession().get("public_billNo");
		factNo=(String)ActionContext.getContext().getSession().get("public_factNo");
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");			
		}
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		bean=webbussletterSer.findPageBean(25, page, billNo, factNo,user);
		return result;
	}
	public long sumDate(Date d1,Date d2){
		return GlobalMethod.sumDate(d1, d2)+1;//注意，出差所用的天数为相差天数+1天
	}
	public String findById(){
		bussletter=webbussletterSer.findById(billNo);
		return "findById";
	}
	public String delete(){
		try{
			/*********************刪除記錄**************************/
			KyzExpectmatmLog log=new KyzExpectmatmLog();
			log.setObj("WebBussinessletter");
			log.setBillNo(billNo);
			WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
			log.setUsername(user.getUsername());
			webbussletterSer.delete(billNo,log);
			
		}catch(Exception e){
			System.out.println(e);
		}		
		return "delete";
	}
	
	public void print(String factNo,String billNo,String visaSort) throws IOException{
		/*List<WebBussinessletter>list=new ArrayList<WebBussinessletter>();
		Map<String,Object>map=new HashMap<String,Object>();
		String factname=webFactSer.selByid(factNo);
		String unit="";//承辦單位
		WebBussinessletter letter=webbussletterSer.findById(billNo);
		if(letter==null){
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>alert('單號為"+billNo+"的函文不存在!');window.close()</script>");
			return null;
		}else{
			*//*******************簡轉繁體********************//*						
			letter.setAddress(ZHConverter.convert(letter.getAddress(), ZHConverter.TRADITIONAL));
			letter.setGAgent(ZHConverter.convert(letter.getGAgent(), ZHConverter.TRADITIONAL));
			letter.setPlanList(ZHConverter.convert(letter.getPlanList(), ZHConverter.TRADITIONAL));
			letter.setPosition(ZHConverter.convert(letter.getPosition(), ZHConverter.TRADITIONAL));
			letter.setUnit(ZHConverter.convert(letter.getUnit(), ZHConverter.TRADITIONAL));
			letter.setUsername(ZHConverter.convert(letter.getUsername(), ZHConverter.TRADITIONAL));
			*//*******************簡轉繁體********************//*
			letter.setSumDate((int)GlobalMethod.sumDate(letter.getDateFrom(), letter.getDateEnd())+1);//出差天數
			list.add(letter);
		}
		if(letter.getUnit()!=null&&!letter.getUnit().equals("")){
			unit="("+letter.getUnit()+")";
		}
		String result=factname+unit+"人員出差申請書";
		map.put("SUBREPORT_DIR",ServletActionContext.getRequest().getRealPath("/jasper/audit/")+ "/");
		map.put("pic", ServletActionContext.getRequest().getRealPath("/jasper/audit/images/")+ "/");//圖片路徑		
		map.put("pfactno", factNo);
		map.put("pbillno",billNo);
		map.put("title",result);						
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		KyVisabillm vbm=visabillmSer.findById(factNo, visaSort, billNo);
		List<KyVisabills>list_visa=vbm.getKyVisabillses();
		List<KyzVisaflow>list_visaflow=visaSer.findByType(factNo,visaSort);
		
		*//**
		 * 最後個不用審核的,就去掉
		 *//*
		int nos=visabillSer.findBillsWithNo(visaSort, billNo);
		if(nos>0){
			for(int i=0;i<nos;i++){
				list_visa.remove(list_visa.size()-1);
				list_visaflow.remove(list_visaflow.size()-1);
			}
		}
		
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
		*//*********************簡體轉繁體******************//*
		for(int i=0;i<list_visabillstemp.size();i++){
			list_visabillstemp.get(i).setMemo(ZHConverter.convert(list_visabillstemp.get(i).getMemo(), ZHConverter.TRADITIONAL));
			list_visabillstemp.get(i).setVisaName(ZHConverter.convert(list_visabillstemp.get(i).getVisaName(), ZHConverter.TRADITIONAL));
			list_visabillstemp.get(i).setVisaNameAndMk(ZHConverter.convert(list_visabillstemp.get(i).getVisaNameAndMk(), ZHConverter.TRADITIONAL));
			list_visabillstemp.get(i).setVisaRank(ZHConverter.convert(list_visabillstemp.get(i).getVisaRank(), ZHConverter.TRADITIONAL));			
		}
		*//*********************簡體轉繁體******************//*
		
		
		Map<String,Object> visa_map=new HashMap<String,Object>();
		visa_map.put("list_visa", list_visabillstemp);
		
		map.put("visa_map", visa_map);
		函文附檔
		//String pic_file=ServletActionContext.getRequest().getRealPath("/KyzexpFile/"+id.getBillNo()+"/")+"/";//函文附檔圖片路徑(附檔在項目的路徑)
		String pic_file=new File("d:\\KyzletterexpFile_backup\\"+billNo).toString();//函文附檔圖片路徑(附檔在D盤的路徑)		
		List<KyzExpectmatmFile>list_kyzexpfile=kyzexpfileSer.findByBillNo(billNo);
		if(pic_file!=null&&list_kyzexpfile.size()>0){
			map.put("pic_file", pic_file+"\\");
			Map file_map=new HashMap<String,Object>();
			file_map.put("list_kyzexpfile", list_kyzexpfile);
			map.put("file_map", file_map);
		}*/	
		
		Map<String,Object>map_result=webbussletterSer.print(factNo, billNo, visaSort,null);
		if(map_result!=null&&map_result.size()>0){
			Map<String,Object>map=(Map<String,Object>)map_result.get("map");
			List<WebBussinessletter>list=(List<WebBussinessletter>)map_result.get("list");
			if(lookordown!=null){
				if(lookordown.equals("look")){
					JasperHelper.exportmain("line", map,"webbussletter.jasper", list,billNo, "jasper/audit/");
				}else{
					JasperHelper.exportmain("pdf", map,"webbussletter.jasper", list,billNo, "jasper/audit/");
				}
			}else{
				JasperHelper.exportmain("pdf", map,"webbussletter.jasper", list,billNo, "jasper/audit/");
			}
		}										
	}
	public void print2() throws IOException{
		this.print(factNo, billNo, visaSort);
	}
	
	public String findById_layer(){
		this.findById();
		return "findById_layer";
	}
	
	public String findById_layer_bootstrap(){
		this.findById();
		return "findById_layer_bootstrap";
	}
	

		
	

}
