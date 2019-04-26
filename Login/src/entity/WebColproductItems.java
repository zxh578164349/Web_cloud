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
	private String orderFactoryAndMan;	
	private String purpose;
	private Double numbers;
	private Double weight;
	private Double remainNum;
	private Double unhealthNum;
	private String picMan;
	private String paymk;
	private Double numbersb;//量產數量
	private Double weightb;
	private String remarks;
	private WebUser cuser;
	private WebUser upuser;
	private String createDate;
	private String updateDate;
	private String numbersbMk;//是否量產
	private String stage;//階段   （試模階段  或  樣品階段）
	private Integer testnums;//試模次數    （第幾次）
	

	// Constructors

	
	public WebUser getCuser() {
		return cuser;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getNumbersbMk() {
		return numbersbMk;
	}

	public void setNumbersbMk(String numbersbMk) {
		this.numbersbMk = numbersbMk;
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
			String orderFactoryAndMan, String purpose,
			Double numbers, Double weight, Double remainNum,
			Double unhealthNum, String picMan, String paymk, Double numbersb,
			Double weightb, String remarks) {
		this.iid = iid;
		this.webColproductMain = webColproductMain;
		this.colDate = colDate;
		this.importmant = importmant;
		this.shape = shape;
		this.CStructure = CStructure;
		this.orderFactoryAndMan = orderFactoryAndMan;		
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
	
	public String getOrderFactoryAndMan() {
		return orderFactoryAndMan;
	}

	public void setOrderFactoryAndMan(String orderFactoryAndMan) {
		this.orderFactoryAndMan = orderFactoryAndMan;
	}

	public String getPurpose() {
		return this.purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public Double getNumbers() {
		return this.numbers;
	}

	public void setNumbers(Double numbers) {
		this.numbers = numbers;
	}

	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getRemainNum() {
		return this.remainNum;
	}

	public void setRemainNum(Double remainNum) {
		this.remainNum = remainNum;
	}

	public Double getUnhealthNum() {
		return this.unhealthNum;
	}

	public void setUnhealthNum(Double unhealthNum) {
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

	public Double getNumbersb() {
		return this.numbersb;
	}

	public void setNumbersb(Double numbersb) {
		this.numbersb = numbersb;
	}

	public Double getWeightb() {
		return this.weightb;
	}

	public void setWeightb(Double weightb) {
		this.weightb = weightb;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getTestnums() {
		return testnums;
	}

	public void setTestnums(Integer testnums) {
		this.testnums = testnums;
	}
	
	

}