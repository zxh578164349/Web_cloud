package action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import entity.KyzExpectmatmLog;
import entity.WebEmailAll;
import entity.WebEmailType;
import net.sf.json.JSONArray;
import services.IWebEmailService;
import util.PageBean;

/**
 * 
* 項目名稱：WebLogin   
* 類名稱：WebEmailAllAction   
* 類描述：郵件發送管理
* 創建人：KY2
 */
public class WebEmailAllAction {
	private IWebEmailService emailService;
	private JSONArray jsons;
	private String ajaxResult;
	private int page;
	private PageBean bean;
	private int backIndex;//返回標識      0或null:不走返回路徑         1:走返回路徑
	private WebEmailAll obj;
	private int eid;
	private String email;
	private String uname;
	private String emailOrcc;
	private String ename;
	private String emailMk;
	
	
	public String getEmailMk() {
		return emailMk;
	}
	public void setEmailMk(String emailMk) {
		this.emailMk = emailMk;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getEmailOrcc() {
		return emailOrcc;
	}
	public void setEmailOrcc(String emailOrcc) {
		this.emailOrcc = emailOrcc;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public JSONArray getJsons() {
		return jsons;
	}
	public void setJsons(JSONArray jsons) {
		this.jsons = jsons;
	}
	public String getAjaxResult() {
		return ajaxResult;
	}
	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public PageBean getBean() {
		return bean;
	}
	public void setBean(PageBean bean) {
		this.bean = bean;
	}
	public int getBackIndex() {
		return backIndex;
	}
	public void setBackIndex(int backIndex) {
		this.backIndex = backIndex;
	}
	public WebEmailAll getObj() {
		return obj;
	}
	public void setObj(WebEmailAll obj) {
		this.obj = obj;
	}
	public void setEmailService(IWebEmailService emailService) {
		this.emailService = emailService;
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	public String add(){
		try{
			emailService.add(obj);
			ajaxResult="0";
		}catch(Exception e){
			e.printStackTrace();
			ajaxResult="1";
		}
		return "add";
	}
	
	/**
	 * 分頁查詢
	 * @return
	 */
	public String findPageBean(){
		ActionContext.getContext().getSession().remove("allrow");	
		bean=emailService.findPageBean(page, 0, eid, email, uname,emailOrcc,emailMk);
		ActionContext.getContext().getSession().put("eid", eid);
		ActionContext.getContext().getSession().put("email", email);
		ActionContext.getContext().getSession().put("uname", uname);
		ActionContext.getContext().getSession().put("emailOrcc", emailOrcc);
		ActionContext.getContext().getSession().put("emailMk", emailMk);
		return "findPageBean";		
	}
	
	/**
	 * 分頁查詢2
	 * @return
	 */
	public String findPageBean2(){		
		this.findPageBean();
		return "findPageBean1";
	}
	
	/**
	 * 分頁查詢3
	 * @return
	 */
	public String findPageBean3(){
		eid=(Integer)ActionContext.getContext().getSession().get("eid");
		email=(String)ActionContext.getContext().getSession().get("email");
		uname=(String)ActionContext.getContext().getSession().get("uname");
		emailOrcc=(String)ActionContext.getContext().getSession().get("emailOrcc");
		emailMk=(String)ActionContext.getContext().getSession().get("emailMk");
		bean=emailService.findPageBean(page, 0, eid, email, uname,emailOrcc,emailMk);
		String result="findPageBean1";
		if(backIndex==1){
			result="findPageBean";
		}
		return result;
		
	}
	
	public String findAllEmailTypes(){
		List<WebEmailType>list=emailService.findAllEmailTypes();
		jsons=JSONArray.fromObject(list);
		return "findAllEmailTypes";
	}
	
	public String findById(){
		obj=emailService.findById(eid);
		return "findById";
	}
	
	/**
	 * 刪除
	 * @return
	 */
	public String delete(){
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setContent("Email:"+email+",ename:"+ename);
		log.setObj("WebEmailAll");
		log.setUsername(null);//如果為null,自動設定為登錄人帳號
		emailService.delete(eid, log);
		return "delete";
	}
	
	
	public String goDisabled(){
		obj=emailService.findById(eid);
		obj.setEmailMk("N");
		emailService.add(obj);
		return "goDisabled";		
	}
	
	/**
	 * 檢測添加數據是否存在
	 * @return
	 */
	public String findByEmailAndEtypeAndEmailOrCc(){
		obj=emailService.findByEmailAndEtypeAndEmailOrCc(email, eid, emailOrcc);
		if(obj==null){
			ajaxResult="0";
		}else{
			ajaxResult="1";
		}
		return "findByEmailAndEtypeAndEmailOrCc";
	}
	
	
	
	
	
	
	
	
	
	

}
