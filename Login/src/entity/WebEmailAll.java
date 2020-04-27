package entity;

/**
 * WebEmailAll entity. @author MyEclipse Persistence Tools
 */

/**
 * 
* 項目名稱：WebLogin   
* 類名稱：WebEmailAll   
* 類描述：郵件發送管理
* 創建人：KY2
 */
public class WebEmailAll implements java.io.Serializable{

	// Fields

	private Integer eid;
	private String factNo;
	private String factCode;
	private String factPart;
	private String email;
	private String username;
	private String toMail;//收件人
	private String toCc;//抄送人
	private String emailType;//郵件類型   E0:工廠訂單     E1:業務周報告彙總   E2:每日產量報表      
	private String emailOrCc;//發送還是抽送     0 發送        1 抽送
	private String emailMk;//是否發送郵件  Y 發送    N 不發送
	private WebEmailType emailTypeFk;//郵件類別外鍵   E1:工廠訂單     E2:業務周報告彙總   E3:每日產量報表      
	private WebUser createUser;
	private WebUser updateUser;
	private String createDate;
	private String updateDate;

	// Constructors

	

	/** default constructor */
	public WebEmailAll(){
	}

	public WebEmailType getEmailTypeFk() {
		return emailTypeFk;
	}

	public void setEmailTypeFk(WebEmailType emailTypeFk) {
		this.emailTypeFk = emailTypeFk;
	}

	/** minimal constructor */
	public WebEmailAll(Integer eid){
		this.eid=eid;
	}

	/** full constructor */
	public WebEmailAll(Integer eid,String factNo,String factCode,String factPart,String email,String username,String toMail,String toCc){
		this.eid=eid;
		this.factNo=factNo;
		this.factCode=factCode;
		this.factPart=factPart;
		this.email=email;
		this.username=username;
		this.toMail=toMail;
		this.toCc=toCc;		
	}

	// Property accessors

	public Integer getEid(){
		return this.eid;
	}

	public void setEid(Integer eid){
		this.eid=eid;
	}

	public String getFactNo(){
		return this.factNo;
	}

	public void setFactNo(String factNo){
		this.factNo=factNo;
	}

	public String getFactCode(){
		return this.factCode;
	}

	public void setFactCode(String factCode){
		this.factCode=factCode;
	}

	public String getFactPart(){
		return this.factPart;
	}

	public void setFactPart(String factPart){
		this.factPart=factPart;
	}

	public String getEmail(){
		return this.email;
	}

	public void setEmail(String email){
		this.email=email;
	}

	public String getUsername(){
		return this.username;
	}

	public void setUsername(String username){
		this.username=username;
	}

	public String getToMail(){
		return this.toMail;
	}

	public void setToMail(String toMail){
		this.toMail=toMail;
	}

	public String getToCc(){
		return this.toCc;
	}

	public void setToCc(String toCc){
		this.toCc=toCc;
	}
	
	public String getEmailType() {
		return emailType;
	}

	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	public String getEmailOrCc() {
		return emailOrCc;
	}

	public void setEmailOrCc(String emailOrCc) {
		this.emailOrCc = emailOrCc;
	}

	public String getEmailMk() {
		return emailMk;
	}

	public void setEmailMk(String emailMk) {
		this.emailMk = emailMk;
	}

	public WebUser getCreateUser() {
		return createUser;
	}

	public void setCreateUser(WebUser createUser) {
		this.createUser = createUser;
	}

	public WebUser getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(WebUser updateUser) {
		this.updateUser = updateUser;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
	
	
	

}