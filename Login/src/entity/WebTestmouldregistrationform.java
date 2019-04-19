package entity;

/**
 * WebTestmouldregistrationform entity. @author MyEclipse Persistence Tools
 */

public class WebTestmouldregistrationform implements java.io.Serializable {

	// Fields

	private Integer tid;
	private WebUser webUserByUpdateuser;
	private WebUser webUserByCreateuser;
	private String tdate;
	private String customer;
	private String brand;
	private String season;
	private String shape;
	private String modelno;
	private String factname;
	private String picMan;
	private Double nums;
	private Double unhealthNums;
	private Double weights;
	private String createDate;
	private String updateDate;	
	private WebMonths yymm;
	
	

	// Constructors

	/** default constructor */
	public WebTestmouldregistrationform() {
	}

	/** minimal constructor */
	public WebTestmouldregistrationform(Integer tid) {
		this.tid = tid;
	}

	/** full constructor */
	public WebTestmouldregistrationform(Integer tid,
			WebUser webUserByUpdateuser, WebUser webUserByCreateuser,
			String tdate, String customer, String brand, String season,
			String shape, String modelno, String factname, String picMan,
			Double nums, Double unhealthNums, Double weights,
			String createDate, String updateDate) {
		this.tid = tid;
		this.webUserByUpdateuser = webUserByUpdateuser;
		this.webUserByCreateuser = webUserByCreateuser;
		this.tdate = tdate;
		this.customer = customer;
		this.brand = brand;
		this.season = season;
		this.shape = shape;
		this.modelno = modelno;
		this.factname = factname;
		this.picMan = picMan;
		this.nums = nums;
		this.unhealthNums = unhealthNums;
		this.weights = weights;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	// Property accessors
	
	

	public Integer getTid() {
		return this.tid;
	}

	public WebMonths getYymm() {
		return yymm;
	}

	public void setYymm(WebMonths yymm) {
		this.yymm = yymm;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public WebUser getWebUserByUpdateuser() {
		return this.webUserByUpdateuser;
	}

	public void setWebUserByUpdateuser(WebUser webUserByUpdateuser) {
		this.webUserByUpdateuser = webUserByUpdateuser;
	}

	public WebUser getWebUserByCreateuser() {
		return this.webUserByCreateuser;
	}

	public void setWebUserByCreateuser(WebUser webUserByCreateuser) {
		this.webUserByCreateuser = webUserByCreateuser;
	}

	public String getTdate() {
		return this.tdate;
	}

	public void setTdate(String tdate) {
		this.tdate = tdate;
	}

	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSeason() {
		return this.season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getShape() {
		return this.shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public String getModelno() {
		return this.modelno;
	}

	public void setModelno(String modelno) {
		this.modelno = modelno;
	}

	public String getFactname() {
		return this.factname;
	}

	public void setFactname(String factname) {
		this.factname = factname;
	}

	public String getPicMan() {
		return this.picMan;
	}

	public void setPicMan(String picMan) {
		this.picMan = picMan;
	}

	

	public Double getNums() {
		return nums;
	}

	public void setNums(Double nums) {
		this.nums = nums;
	}

	public Double getUnhealthNums() {
		return unhealthNums;
	}

	public void setUnhealthNums(Double unhealthNums) {
		this.unhealthNums = unhealthNums;
	}

	public Double getWeights() {
		return this.weights;
	}

	public void setWeights(Double weights) {
		this.weights = weights;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

}