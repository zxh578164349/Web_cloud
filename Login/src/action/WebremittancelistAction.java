/**
 * 
 */
package action;

import java.util.List;

import mail.MailSenderInfo;
import mail.SimpleMailSender;
import services.IKyVisabillmServices;
import services.IWebremittancelistServices;
import services.IWebuserEmailServices;

import com.opensymphony.xwork2.ActionSupport;

import entity.KyVisabillm;
import entity.KyzExpectmatm;
import entity.Webremittancelist;

/**   
 *    
 * 项目名称：Login   
 * 类名称：WebremittancelistAction   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/25 下午4:01:50   
 * 修改人：Administrator   
 * 修改时间：2016/4/25 下午4:01:50   
 * 修改备注：   
 * @version    
 *    
 **/
public class WebremittancelistAction extends ActionSupport{
	private Webremittancelist webremit;
	private String factNo;
	private String billNo;
	private String visaTypem;
	private int page;
	private String saveOrUpdate;
	private String ajaxResult;
	private IWebremittancelistServices webremiSer;
	private IKyVisabillmServices visabillmSer;
	private IWebuserEmailServices webuseremailSer;
	public Webremittancelist getWebremit() {
		return webremit;
	}
	public void setWebremit(Webremittancelist webremit) {
		this.webremit = webremit;
	}
	public String getFactNo() {
		return factNo;
	}
	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getVisaTypem() {
		return visaTypem;
	}
	public void setVisaTypem(String visaTypem) {
		this.visaTypem = visaTypem;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getSaveOrUpdate() {
		return saveOrUpdate;
	}
	public void setSaveOrUpdate(String saveOrUpdate) {
		this.saveOrUpdate = saveOrUpdate;
	}
	public String getAjaxResult() {
		return ajaxResult;
	}
	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}
	public void setWebremiSer(IWebremittancelistServices webremiSer) {
		this.webremiSer = webremiSer;
	}
	
	
	public void setVisabillmSer(IKyVisabillmServices visabillmSer) {
		this.visabillmSer = visabillmSer;
	}
	public void setWebuseremailSer(IWebuserEmailServices webuseremailSer) {
		this.webuseremailSer = webuseremailSer;
	}
	public void add(){
		if(saveOrUpdate.equals("save")){
				ajaxResult="0";				
				KyVisabillm vbm=visabillmSer.findById(webremit.getFactNo(), webremit.getVisaType(), webremit.getBillNo());							
				String emailUrl_in="http://203.85.73.161/Login/vbm_findById_email?visaSort="+webremit.getVisaType()+"&billNo="+webremit.getBillNo()
				         +"&factNo="+webremit.getFactNo()+"&email="+vbm.getSignerNext();
				String emailUrl_in2="http://203.85.73.161/Login/vbm_findById_email2?visaSort="+webremit.getVisaType()+"&billNo="+webremit.getBillNo()
				         +"&factNo="+webremit.getFactNo()+"&email="+vbm.getSignerNext();							
				/**
				 * 發送郵件
				 */														
				String singernext=vbm.getSignerNext();
				String vbm_billno=vbm.getId().getBillNo();
				String vbm_factno=vbm.getId().getFactNo();
				MailSenderInfo mailinfo=new MailSenderInfo();
				mailinfo.setValidate(true);					
				mailinfo.setToAddress(singernext);
				mailinfo.setSubject("新函文初次審核-"+vbm_billno+"("+vbm_factno+")");
				mailinfo.setContent("單號:<span style='color:red'>"+vbm_billno+"</span>"+"&nbsp;&nbsp;廠別:"+vbm_factno+																	
						"<br/>點擊單號直接審核:<a href='"+emailUrl_in2+"'>"+vbm_billno+"</a>(電腦適用)"+
						"<br/>點擊單號直接審核:<a href='"+emailUrl_in+"'>"+vbm_billno+"</a>(手機平板適用)"+
						"<hr/>"+
						"如需查詢以往單據請登陸:(云端)<a href='http://203.85.73.161/Login'>http://203.85.73.161/Login</a>" +									
						"<br/>進入[KPI數據]--[函文審核]查找對應單號審核" +									
						"<hr/>"+
						"<br/>本郵件自動發送,請勿回復!如需回復或者問題，請回复到kyinfo.lp@yydg.com.cn劉平!<br/>"+
						"<hr/>");
			    //这个类主要来发送邮件   
			      SimpleMailSender sms = new SimpleMailSender();   
			         // sms.sendTextMail(mailInfo);//发送文体格式    
			      sms.sendHtmlMail(mailinfo);//发送html格式  
																
			 /**
		       * 发送给备签人  20150817
		       */			
			 /******************20151113备签人请使用方法findByFactNoAEmailPwd2(String factNo,String email)**********************/			     
		      
		      List<String>list_emailPwd=webuseremailSer.findByFactNoAEmailPwd2(webremit.getFactNo(),vbm.getSignerNext());
		      if(list_emailPwd.size()>0){//if
		    	  for(int i=0;i<list_emailPwd.size();i++){
		    		  MailSenderInfo mailInfo2 = new MailSenderInfo();    				         
				      mailInfo2.setValidate(true);    					        
				      mailInfo2.setToAddress(list_emailPwd.get(i));    
				      mailInfo2.setSubject("新函文初次審核"+vbm.getId().getBillNo()+"("+vbm.getId().getFactNo()+")");    
				      mailInfo2.setContent("函文單號:"+"<span style='color:red'>"+vbm.getId().getBillNo()+"</span>"+"&nbsp;&nbsp;廠別:"+vbm.getId().getFactNo()+
				    		  "<br/>點擊單號直接審核:<a href='"+emailUrl_in2+"'>"+vbm.getId().getBillNo()+"</a>(電腦適用)"+
				    		  "<br/>點擊單號直接審核:<a href='"+emailUrl_in+"'>"+vbm.getId().getBillNo()+"</a>(手機平板適用)"+			    		  	
						      "<hr/>"+
				    		 "如需查詢以往單據請登錄加久網站:(云端)<a href='http://203.85.73.161/Login'>http://203.85.73.161/Login</a>" +			      		
				      		"<br/>進入[KPI數據]--[函文審核]中查找對應單號審核,"+	      		    		
				    		"<hr/>"+
				      		"<br/>本郵件自動發送,請勿回復!如需回復或者問題，請回复到kyinfo.lp@yydg.com.cn劉平!<br/>"+
				    		"<hr/>"
				    		);      
				         //这个类主要来发送邮件   
				      SimpleMailSender sms2 = new SimpleMailSender();   
				      sms2.sendHtmlMail(mailInfo2);//发送html格式  
			      } 
		      }//if
		      
		      
		      /**
		       * 測試主站kyuen@yydg.com.cn有沒有收到郵件
		       */
		      MailSenderInfo mailinfo3=new MailSenderInfo();
		      mailinfo3.setValidate(true);
		      /*mailinfo3.setUserName("kyuen@yydg.com.cn");
		      mailinfo3.setPassword("yydgmail");
		      mailinfo3.setFromAddress("<kyuen@yydg.com.cn>");*/
		      mailinfo3.setToAddress("kyuen@yydg.com.cn");
		      mailinfo3.setSubject("新函文初次審核(總站已收到)");
		      mailinfo3.setContent("請登錄加久網站:(云端)<a href='http://203.85.73.161/Login'>http://203.85.73.161/Login</a>" +							
						"<br/>進入[KPI數據]--[函文審核]查找對應單號進行審核" +
						"<br/>單號:<span style='color:red'>"+vbm.getId().getBillNo()+"<span>"+"&nbsp;&nbsp;廠別:"+vbm.getId().getFactNo());
		      SimpleMailSender sms3=new SimpleMailSender();
		      sms3.sendHtmlMail(mailinfo3);
		}else{
			
		}
	}
	
	
	
}
