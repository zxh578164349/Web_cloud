package entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * WebBussinessletter entity. @author MyEclipse Persistence Tools
 */

public class WebBussinessletter implements java.io.Serializable {

	// Fields

	private String blNo;
	private String unit;
	private String username;
	private String position;
	private String GAgent;
	private String address;
	private Date dateFrom;
	private Date dateEnd;
	private Date timeFrom;
	private Date timeEnd;
	private String createDate;
	private String factNo;
	private String planList="";
	private String visaSort;
	private Integer sumDate;
	private String userAccount;
	private KyVisabillm vbm;
	private String delMk;//刪除標記     0或空 未刪除     1刪除
	private String visaSortM;//類別大類
	private String userEmail;//申請人Email
	private Integer userId;
	private String useremail;
	private WebDepartment depId;
	private VWebFact factNo2;//關聯工廠名稱(不需要映射列名)

	// Constructors

	public String getUseremail(){
		return useremail;
	}

	public void setUseremail(String useremail){
		this.useremail=useremail;
	}

	/** default constructor */
	public WebBussinessletter() {
	}

	/** minimal constructor */
	public WebBussinessletter(String blNo) {
		this.blNo = blNo;
	}

	/** full constructor */
	public WebBussinessletter(String blNo, String unit, String username,
			String position, String GAgent, String address, Date dateFrom,
			Date dateEnd, Date timeFrom, Date timeEnd,
			String createDate, String factNo, String planList) {
		this.blNo = blNo;
		this.unit = unit;
		this.username = username;
		this.position = position;
		this.GAgent = GAgent;
		this.address = address;
		this.dateFrom = dateFrom;
		this.dateEnd = dateEnd;
		this.timeFrom = timeFrom;
		this.timeEnd = timeEnd;
		this.createDate = createDate;
		this.factNo = factNo;
		this.planList = planList;
	}

	// Property accessors

	public String getBlNo() {
		return this.blNo;
	}

	public void setBlNo(String blNo) {
		this.blNo = blNo;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getGAgent() {
		return this.GAgent;
	}

	public void setGAgent(String GAgent) {
		this.GAgent = GAgent;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}



	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Date getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(Date timeFrom) {
		this.timeFrom = timeFrom;
	}

	public Date getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getFactNo() {
		return this.factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	

	public String getPlanList() {
		return planList;
	}

	public void setPlanList(String planList) {
		this.planList = planList;
	}

	public String getVisaSort() {
		return visaSort;
	}

	public void setVisaSort(String visaSort) {
		this.visaSort = visaSort;
	}

	public Integer getSumDate() {
		return sumDate;
	}

	public void setSumDate(Integer sumDate) {
		this.sumDate = sumDate;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public KyVisabillm getVbm() {
		return vbm;
	}

	public void setVbm(KyVisabillm vbm) {
		this.vbm = vbm;
	}

	public String getDelMk() {
		return delMk;
	}

	public void setDelMk(String delMk) {
		this.delMk = delMk;
	}

	public String getVisaSortM() {
		return visaSortM;
	}

	public void setVisaSortM(String visaSortM) {
		this.visaSortM = visaSortM;
	}

	public String getUserEmail(){
		return userEmail;
	}

	public void setUserEmail(String userEmail){
		this.userEmail=userEmail;
	}

	public Integer getUserId(){
		return userId;
	}

	public void setUserId(Integer userId){
		this.userId=userId;
	}

	public WebDepartment getDepId() {
		return depId;
	}

	public void setDepId(WebDepartment depId) {
		this.depId = depId;
	}

	public VWebFact getFactNo2() {
		return factNo2;
	}

	public void setFactNo2(VWebFact factNo2) {
		this.factNo2 = factNo2;
	}
	
	
	
	
	
	

}