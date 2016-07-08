/**
 * 
 */
package action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import mail.MailSenderInfo;
import mail.SimpleMailSender;
import services.IKyVisabillmServices;
import services.IWebremittancelistServices;
import services.IWebremittancelistsServices;
import services.IWebuserEmailServices;
import util.GlobalMethod;
import util.JasperHelper;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyVisabillm;
import entity.KyzExpectmatm;
import entity.KyzExpectmatmLog;
import entity.WebBussinessletter;
import entity.WebUser;
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
public class WebremittancelistAction extends ActionSupport implements ServletResponseAware{
	private Webremittancelist webremit;
	private String factNo;
	private String billNo;
	private String visaSort;//大分類
	private String visaType;//小分類
	private int page;
	private String saveOrUpdate;
	private String ajaxResult;
	private String yymm;
	private PageBean bean;
	private String readMk;//備註標記
	private String itemNo;
	private String lookordown;
	private int maxItemno;//當前最大序號
	private int backIndex;//返回標識      0或null:不走返回路徑         1:走返回路徑
	private IWebremittancelistServices webremiSer;
	private IKyVisabillmServices visabillmSer;
	private IWebuserEmailServices webuseremailSer;
	private IWebremittancelistsServices webremitsSer;
	private javax.servlet.http.HttpServletResponse response;
	
	
	
	
	public int getBackIndex() {
		return backIndex;
	}
	public void setBackIndex(int backIndex) {
		this.backIndex = backIndex;
	}
	public int getMaxItemno() {
		return maxItemno;
	}
	public void setMaxItemno(int maxItemno) {
		this.maxItemno = maxItemno;
	}
	public String getVisaType() {
		return visaType;
	}
	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}
	public String getLookordown() {
		return lookordown;
	}
	public void setLookordown(String lookordown) {
		this.lookordown = lookordown;
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
	public PageBean getBean() {
		return bean;
	}
	public void setBean(PageBean bean) {
		this.bean = bean;
	}
	public String getYymm() {
		return yymm;
	}
	public void setYymm(String yymm) {
		this.yymm = yymm;
	}
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
	
	public String getVisaSort() {
		return visaSort;
	}
	public void setVisaSort(String visaSort) {
		this.visaSort = visaSort;
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
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	
	
	public void setWebremitsSer(IWebremittancelistsServices webremitsSer) {
		this.webremitsSer = webremitsSer;
	}
	public void add() throws IOException{
		response.setContentType("text/html;charset=utf-8");
		try{
			if(saveOrUpdate.equals("save")){
				webremiSer.add(webremit);			
					KyVisabillm vbm=visabillmSer.findById(webremit.getWebtype().getId().getFactNo(), webremit.getVisaType(), webremit.getBillNo());	
					List<String>list_emailPwd=webuseremailSer.findByFactNoAEmailPwd2(vbm.getId().getFactNo(),vbm.getSignerNext());
					
					GlobalMethod.sendNewEmail(vbm,list_emailPwd);//發送郵件															
			}else{
				webremiSer.add(webremit);					
			}
			response.getWriter().print("<script>window.parent.layer.msg('提交成功',3,1);window.parent.loadUrl('webremit_findPageBean')</script>");
		}catch(Exception e){
			response.getWriter().print("<script>window.parent.layer.msg('提交失敗',3,3)</script>");
		}
		
	}
	
	public String makeBillNo(){
		String maxbillno=webremiSer.findMaxBillNo(factNo, yymm);
		StringBuffer result=new StringBuffer();
		if(maxbillno==null||maxbillno.equals("")){
			result.append("RM"+factNo+yymm+"01");
		}else{
			result.append(maxbillno.subSequence(0, maxbillno.length()-2));
			int number=Integer.parseInt(maxbillno.substring(maxbillno.length()-2, maxbillno.length()));	
			if(number<9){
				result.append("0"+(number+1));
			}else{
				result.append(number+1);
			}
		}
		ajaxResult=result.toString();
		return "makeBillNo";
				
	}
	
	public String findPageBean(){
		ActionContext.getContext().getSession().remove("allrow");//dao層
		ActionContext.getContext().getSession().remove("public_visaTypem");
		ActionContext.getContext().getSession().remove("public_billNo");
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		ActionContext.getContext().getSession().put("public_factno", factNo);
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		bean=webremiSer.findPageBean(25, page, visaSort, factNo, billNo,user);
		return "beanList";
	}
	public String findPageBean2(){
		ActionContext.getContext().getSession().remove("allrow");//dao層
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		ActionContext.getContext().getSession().put("public_visaTypem", visaSort);
		ActionContext.getContext().getSession().put("public_billNo", billNo);
		ActionContext.getContext().getSession().put("public_factno", factNo);
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		bean=webremiSer.findPageBean(25, page, visaSort, factNo, billNo,user);
		return "beanList1";
	}
	public String findPageBean3(){
		String result="";
		if(backIndex==1){
			result="beanList";
		}else{
			result="beanList1";
		}
		factNo=(String)ActionContext.getContext().getSession().get("public_factno");
		visaSort=(String)ActionContext.getContext().getSession().get("public_visaTypem");
		billNo=(String)ActionContext.getContext().getSession().get("public_billNo");
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		bean=webremiSer.findPageBean(25, page, visaSort, factNo, billNo,user);		
		return result;
		
	}
	public String findById(){
		webremit=webremiSer.findById(billNo);
		for(int i=0;i<webremit.getWebremittancelistses().size();i++){
			if(Integer.parseInt(webremit.getWebremittancelistses().get(i).getId().getItemNo())>maxItemno){
				maxItemno=Integer.parseInt(webremit.getWebremittancelistses().get(i).getId().getItemNo());//求最大序號
			}
		}
		return "findById";
	}
	
	/**
	 * 不查找webtype類型名稱
	 * @Title: findById_notype
	 * @Description: TODO
	 * @param 
	 * @return void
	 * @throws
	 * @author web
	 * @date 2016/4/29
	 */
	public void findById_notype(){
		webremit=webremiSer.findById_notype(billNo);
	}
	/********************函文審核彈出頁面**************************/
	public String findById_layer(){
		this.findById();
		return "findById_layer";
	}
	/********************函文審核彈出頁面**************************/
	
	
	/********************刪除細項彈出頁面**************************/
	//1 開始進入細項刪除頁面
	public String findById_layer2(){
		this.findById_notype();
		return "findById_layer2";
	}
	
	//2 進入細項刪除頁面后，刪除細項，ajax返回的頁面（不帶任何樣式，便于與細項刪除頁面整合）
	public String findById_layer3(){
		this.findById_notype();
		return "findById_layer3";
	}
	/********************刪除細項彈出頁面**************************/
	
	public String delete(){
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setObj("Webremittancelist");
		log.setBillNo(billNo);
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		log.setUsername(user.getUsername());
		webremiSer.delete(billNo,log);		
		return "delete";
	}
	
	public String delete_list(){
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setObj("Webremittancelists");
		log.setBillNo(billNo);
		log.setContent("No."+itemNo);		
		webremitsSer.delete(billNo,itemNo,log);		
		return "delete_list";
	}
	
	public void print() throws IOException{				
		Map<String,Object>map_result=webremiSer.print(factNo, billNo, visaType,null);
		if(map_result!=null&&map_result.size()>0){
			Map<String,Object>map=(Map<String,Object>)map_result.get("map");
			List<Webremittancelist>list=(List<Webremittancelist>)map_result.get("list");
			if(lookordown!=null){
				if(lookordown.equals("look")){
					JasperHelper.exportmain("line", map,"webremittancelist.jasper", list,billNo, "jasper/audit/");
				}else{
					JasperHelper.exportmain("pdf", map,"webremittancelist.jasper", list,billNo, "jasper/audit/");
				}
			}else{
				JasperHelper.exportmain("pdf", map,"webremittancelist.jasper", list,billNo, "jasper/audit/");
			}
		}										
	}
	
	
	
	
}
