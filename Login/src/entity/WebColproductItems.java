package entity;

/**
 * WebColproductItems entity. @author MyEclipse Persistence Tools
 */

public class WebColproductItems implements java.io.Serializable {

	// Fields

	private Integer iid;
	private WebColproductMain webColproductMain;
	private String colDate;
	private String importmant;
	private String shape;
	private String CStructure;
	private String orderFactory;
	private String orderMan;
	private String purpose;
	private Integer numbers;
	private Integer weight;
	private Integer remainNum;
	private Integer unhealthNum;
	private String picMan;
	private String paymk;
	private Integer numbersb;
	private Integer weightb;
	private String remarks;
	private WebUser cuser;
	private WebUser upuser;
	private String createDate;
	private String updateDate;
	

	// Constructors

	public WebUser getCuser() {
		return cuser;
	}

	public void setCuser(WebUser cuser) {
		this.cuser = cuser;
	}

	public WebUser getUpuser() {
		return upuser;
	}

	public void setUpuser(WebUser upuser) {
		this.upuser = upuser;
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

	/** default constructor */
	public WebColproductItems() {
	}

	/** minimal constructor */
	public WebColproductItems(Integer iid) {
		this.iid = iid;
	}

	/** full constructor */
	public WebColproductItems(Integer iid, WebColproductMain webColproductMain,
			String colDate, String importmant, String shape, String CStructure,
			String orderFactory, String orderMan, String purpose,
			Integer numbers, Integer weight, Integer remainNum,
			Integer unhealthNum, String picMan, String paymk, Integer numbersb,
			Integer weightb, String remarks) {
		this.iid = iid;
		this.webColproductMain = webColproductMain;
		this.colDate = colDate;
		this.importmant = importmant;
		this.shape = shape;
		this.CStructure = CStructure;
		this.orderFactory = orderFactory;
		this.orderMan = orderMan;
		this.purpose = purpose;
		this.numbers = numbers;
		this.weight = weight;
		this.remainNum = remainNum;
		this.unhealthNum = unhealthNum;
		this.picMan = picMan;
		this.paymk = paymk;
		this.numbersb = numbersb;
		this.weightb = weightb;
		this.remarks = remarks;
	}

	// Property accessors

	public Integer getIid() {
		return this.iid;
	}

	public void setIid(Integer iid) {
		this.iid = iid;
	}

	public WebColproductMain getWebColproductMain() {
		return this.webColproductMain;
	}

	public void setWebColproductMain(WebColproductMain webColproductMain) {
		this.webColproductMain = webColproductMain;
	}

	public String getColDate() {
		return this.colDate;
	}

	public void setColDate(String colDate) {
		this.colDate = colDate;
	}

	public String getImportmant() {
		return this.importmant;
	}

	public void setImportmant(String importmant) {
		this.importmant = importmant;
	}

	public String getShape() {
		return this.shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public String getCStructure() {
		return this.CStructure;
	}

	public void setCStructure(String CStructure) {
		this.CStructure = CStructure;
	}

	public String getOrderFactory() {
		return this.orderFactory;
	}

	public void setOrderFactory(String orderFactory) {
		this.orderFactory = orderFactory;
	}

	public String getOrderMan() {
		return this.orderMan;
	}

	public void setOrderMan(String orderMan) {
		this.orderMan = orderMan;
	}

	public String getPurpose() {
		return this.purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public Integer getNumbers() {
		return this.numbers;
	}

	public void setNumbers(Integer numbers) {
		this.numbers = numbers;
	}

	public Integer getWeight() {
		return this.weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getRemainNum() {
		return this.remainNum;
	}

	public void setRemainNum(Integer remainNum) {
		this.remainNum = remainNum;
	}

	public Integer getUnhealthNum() {
		return this.unhealthNum;
	}

	public void setUnhealthNum(Integer unhealthNum) {
		this.unhealthNum = unhealthNum;
	}
	
	public String getPicMan() {
		return picMan;
	}

	public void setPicMan(String picMan) {
		this.picMan = picMan;
	}

	public String getPaymk() {
		return paymk;
	}

	public void setPaymk(String paymk) {
		this.paymk = paymk;
	}

	public Integer getNumbersb() {
		return this.numbersb;
	}

	public void setNumbersb(Integer numbersb) {
		this.numbersb = numbersb;
	}

	public Integer getWeightb() {
		return this.weightb;
	}

	public void setWeightb(Integer weightb) {
		this.weightb = weightb;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}