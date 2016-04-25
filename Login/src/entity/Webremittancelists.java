package entity;

/**
 * Webremittancelists entity. @author MyEclipse Persistence Tools
 */

public class Webremittancelists implements java.io.Serializable {

	// Fields

	private WebremittancelistsId id;
	private String currency;
	private String toBank;
	private String toAccount;
	private Double payment;
	private Double cost;
	private Double acAmount;
	private String remark;
	private String reDate;
	private String udUsername;
	private String udDate;

	// Constructors

	/** default constructor */
	public Webremittancelists() {
	}

	/** minimal constructor */
	public Webremittancelists(WebremittancelistsId id) {
		this.id = id;
	}

	/** full constructor */
	public Webremittancelists(WebremittancelistsId id, String currency,
			String toBank, String toAccount, Double payment, Double cost,
			Double acAmount, String remark, String reDate, String udUsername,
			String udDate) {
		this.id = id;
		this.currency = currency;
		this.toBank = toBank;
		this.toAccount = toAccount;
		this.payment = payment;
		this.cost = cost;
		this.acAmount = acAmount;
		this.remark = remark;
		this.reDate = reDate;
		this.udUsername = udUsername;
		this.udDate = udDate;
	}

	// Property accessors

	public WebremittancelistsId getId() {
		return this.id;
	}

	public void setId(WebremittancelistsId id) {
		this.id = id;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getToBank() {
		return this.toBank;
	}

	public void setToBank(String toBank) {
		this.toBank = toBank;
	}

	public String getToAccount() {
		return this.toAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}

	public Double getPayment() {
		return this.payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}

	public Double getCost() {
		return this.cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getAcAmount() {
		return this.acAmount;
	}

	public void setAcAmount(Double acAmount) {
		this.acAmount = acAmount;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReDate() {
		return this.reDate;
	}

	public void setReDate(String reDate) {
		this.reDate = reDate;
	}

	public String getUdUsername() {
		return this.udUsername;
	}

	public void setUdUsername(String udUsername) {
		this.udUsername = udUsername;
	}

	public String getUdDate() {
		return this.udDate;
	}

	public void setUdDate(String udDate) {
		this.udDate = udDate;
	}

}