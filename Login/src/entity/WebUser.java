package entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * WebUser entity. @author MyEclipse Persistence Tools
 */

public class WebUser implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;//帐号
	private String pwd;//密码
	private String ip;//IP
	private String workno;//工号
	private String factno;//厂别
	private String name;//姓名
	private Integer available;//是否可用     0为可用
	private String email;//Email
	private String userread;//是否只读   1为只读
	private String logdate;//登錄時間，如果超過3個月沒有登錄，就刪除該用戶
	private String emailpassword;//备签邮箱
	private String department;//部門
	private String post;//職務
    private String adminMk;//管理員標識
    private String erpfactno;
    private String userType;//用戶類型
    private String weeklyreportMk;//是否發送業務每週報告郵件      Y:  是       N:否
	private List<WebJurisdiction> webJurisdictions = new ArrayList<WebJurisdiction>();
	private List<WebOperationToUser>webOperationToUsers=new ArrayList<WebOperationToUser>();//操作權限

	// Constructors

	/** default constructor */
	public WebUser() {
	}

	public WebUser(Integer id){
		this.id=id;
	}
	/** minimal constructor */
	public WebUser(Integer id, String username, String pwd, String workno,
			String factno, Integer available) {
		this.id = id;
		this.username = username;
		this.pwd = pwd;
		this.workno = workno;
		this.factno = factno;
		this.available = available;
	}

	/** full constructor */
	public WebUser(Integer id, String username, String pwd, String ip,
			String workno, String factno, String name, Integer available,
			String email,String userread, List<WebJurisdiction> webJurisdictions,String logdate,String emailpassword) {
		this.id = id;
		this.username = username;
		this.pwd = pwd;
		this.ip = ip;
		this.workno = workno;
		this.factno = factno;
		this.name = name;
		this.available = available;
		this.email = email;
		this.userread=userread;
		this.webJurisdictions = webJurisdictions;
		this.logdate=logdate;
		this.emailpassword=emailpassword;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getWorkno() {
		return this.workno;
	}

	public void setWorkno(String workno) {
		this.workno = workno;
	}

	public String getFactno() {
		return this.factno;
	}

	public void setFactno(String factno) {
		this.factno = factno;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAvailable() {
		return this.available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	public String getUserread() {
		return userread;
	}

	public void setUserread(String userread) {
		this.userread = userread;
	}

	public List<WebJurisdiction> getWebJurisdictions() {
		return this.webJurisdictions;
	}

	public void setWebJurisdictions(List<WebJurisdiction> webJurisdictions) {
		this.webJurisdictions = webJurisdictions;
	}

	public String getLogdate() {
		return logdate;
	}

	public void setLogdate(String logdate) {
		this.logdate = logdate;
	}

	public String getEmailpassword() {
		return emailpassword;
	}

	public void setEmailpassword(String emailpassword) {
		this.emailpassword = emailpassword;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getAdminMk() {
		return adminMk;
	}

	public void setAdminMk(String adminMk) {
		this.adminMk = adminMk;
	}

	public String getErpfactno(){
		return erpfactno;
	}

	public void setErpfactno(String erpfactno){
		this.erpfactno=erpfactno;
	}

	public String getUserType(){
		return userType;
	}

	public void setUserType(String userType){
		this.userType=userType;
	}

	public List<WebOperationToUser> getWebOperationToUsers(){
		return webOperationToUsers;
	}

	public void setWebOperationToUsers(List<WebOperationToUser> webOperationToUsers){
		this.webOperationToUsers=webOperationToUsers;
	}

	public String getWeeklyreportMk() {
		return weeklyreportMk;
	}

	public void setWeeklyreportMk(String weeklyreportMk) {
		this.weeklyreportMk = weeklyreportMk;
	}

	
	
	
	
	
	

	
			
	
	

}