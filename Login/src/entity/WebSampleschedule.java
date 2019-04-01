package entity;

/**
 * WebSampleschedule entity. @author MyEclipse Persistence Tools
 */

public class WebSampleschedule implements java.io.Serializable {

	// Fields

	private Integer ssid;
	private WebUser webUserByUpdateuser;
	private WebUser webUserByCreateuser;
	private String stype;
	private String dateA;
	private String samplelevel;
	private String brand;
	private String customer;
	private String customerb;
	private String shape;
	private String orderno;
	private Double pairs;
	private String sizes;
	private String samplemaerial;
	private String characteristic;
	private String dateB;
	private String statusA;
	private String statusB;
	private String statusC;
	private String dateC;
	private String dateD;
	private String dateE;
	private String perpose;
	private String questions;
	private String pairsmk;
	private String outputnotice;
	private String createDate;
	private String updateDate;

	// Constructors

	/** default constructor */
	public WebSampleschedule() {
	}

	/** minimal constructor */
	public WebSampleschedule(Integer ssid) {
		this.ssid = ssid;
	}

	/** full constructor */
	public WebSampleschedule(Integer ssid, WebUser webUserByUpdateuser,
			WebUser webUserByCreateuser, String stype, String dateA,
			String samplelevel, String brand, String customer,
			String customerb, String shape, String orderno, Double pairs,
			String sizes, String samplemaerial, String characteristic,
			String dateB, String statusA, String statusB, String statusC,
			String dateC, String dateD, String dateE, String perpose,
			String questions, String pairsmk, String outputnotice,
			String createDate, String updateDate) {
		this.ssid = ssid;
		this.webUserByUpdateuser = webUserByUpdateuser;
		this.webUserByCreateuser = webUserByCreateuser;
		this.stype = stype;
		this.dateA = dateA;
		this.samplelevel = samplelevel;
		this.brand = brand;
		this.customer = customer;
		this.customerb = customerb;
		this.shape = shape;
		this.orderno = orderno;
		this.pairs = pairs;
		this.sizes = sizes;
		this.samplemaerial = samplemaerial;
		this.characteristic = characteristic;
		this.dateB = dateB;
		this.statusA = statusA;
		this.statusB = statusB;
		this.statusC = statusC;
		this.dateC = dateC;
		this.dateD = dateD;
		this.dateE = dateE;
		this.perpose = perpose;
		this.questions = questions;
		this.pairsmk = pairsmk;
		this.outputnotice = outputnotice;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	// Property accessors

	public Integer getSsid() {
		return this.ssid;
	}

	public void setSsid(Integer ssid) {
		this.ssid = ssid;
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

	

	public String getStype() {
		return stype;
	}

	public void setStype(String stype) {
		this.stype = stype;
	}

	public String getDateA() {
		return this.dateA;
	}

	public void setDateA(String dateA) {
		this.dateA = dateA;
	}

	public String getSamplelevel() {
		return this.samplelevel;
	}

	public void setSamplelevel(String samplelevel) {
		this.samplelevel = samplelevel;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getCustomerb() {
		return this.customerb;
	}

	public void setCustomerb(String customerb) {
		this.customerb = customerb;
	}

	public String getShape() {
		return this.shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public String getOrderno() {
		return this.orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public Double getPairs() {
		return this.pairs;
	}

	public void setPairs(Double pairs) {
		this.pairs = pairs;
	}

	public String getSizes() {
		return this.sizes;
	}

	public void setSizes(String sizes) {
		this.sizes = sizes;
	}

	public String getSamplemaerial() {
		return this.samplemaerial;
	}

	public void setSamplemaerial(String samplemaerial) {
		this.samplemaerial = samplemaerial;
	}

	public String getCharacteristic() {
		return this.characteristic;
	}

	public void setCharacteristic(String characteristic) {
		this.characteristic = characteristic;
	}

	public String getDateB() {
		return this.dateB;
	}

	public void setDateB(String dateB) {
		this.dateB = dateB;
	}

	public String getStatusA() {
		return this.statusA;
	}

	public void setStatusA(String statusA) {
		this.statusA = statusA;
	}

	public String getStatusB() {
		return this.statusB;
	}

	public void setStatusB(String statusB) {
		this.statusB = statusB;
	}

	public String getStatusC() {
		return this.statusC;
	}

	public void setStatusC(String statusC) {
		this.statusC = statusC;
	}

	public String getDateC() {
		return this.dateC;
	}

	public void setDateC(String dateC) {
		this.dateC = dateC;
	}

	public String getDateD() {
		return this.dateD;
	}

	public void setDateD(String dateD) {
		this.dateD = dateD;
	}

	public String getDateE() {
		return this.dateE;
	}

	public void setDateE(String dateE) {
		this.dateE = dateE;
	}

	public String getPerpose() {
		return this.perpose;
	}

	public void setPerpose(String perpose) {
		this.perpose = perpose;
	}

	public String getQuestions() {
		return this.questions;
	}

	public void setQuestions(String questions) {
		this.questions = questions;
	}

	public String getPairsmk() {
		return this.pairsmk;
	}

	public void setPairsmk(String pairsmk) {
		this.pairsmk = pairsmk;
	}

	public String getOutputnotice() {
		return this.outputnotice;
	}

	public void setOutputnotice(String outputnotice) {
		this.outputnotice = outputnotice;
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