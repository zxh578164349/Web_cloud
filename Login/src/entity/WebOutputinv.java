package entity;

/**
 * WebOutputinv entity. @author MyEclipse Persistence Tools
 */

public class WebOutputinv implements java.io.Serializable {

	// Fields

	private WebOutputinvId id;
	private Double output;
	private Double invCount;
	private Double sellCount;
	private Double price;
	private String dateB;
	private String dateE;
	private String lockMk;
	private String userNo;
	private String dateTime;

	// Constructors

	/** default constructor */
	public WebOutputinv() {
	}

	/** minimal constructor */
	public WebOutputinv(WebOutputinvId id) {
		this.id = id;
	}

	/** full constructor */
	public WebOutputinv(WebOutputinvId id, Double output, Double invCount,
			Double sellCount, Double price, String dateB, String dateE,
			String lockMk, String userNo, String dateTime) {
		this.id = id;
		this.output = output;
		this.invCount = invCount;
		this.sellCount = sellCount;
		this.price = price;
		this.dateB = dateB;
		this.dateE = dateE;
		this.lockMk = lockMk;
		this.userNo = userNo;
		this.dateTime = dateTime;
	}

	// Property accessors

	public WebOutputinvId getId() {
		return this.id;
	}

	public void setId(WebOutputinvId id) {
		this.id = id;
	}

	public Double getOutput() {
		return this.output;
	}

	public void setOutput(Double output) {
		this.output = output;
	}

	public Double getInvCount() {
		return this.invCount;
	}

	public void setInvCount(Double invCount) {
		this.invCount = invCount;
	}

	public Double getSellCount() {
		return this.sellCount;
	}

	public void setSellCount(Double sellCount) {
		this.sellCount = sellCount;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDateB() {
		return this.dateB;
	}

	public void setDateB(String dateB) {
		this.dateB = dateB;
	}

	public String getDateE() {
		return this.dateE;
	}

	public void setDateE(String dateE) {
		this.dateE = dateE;
	}

	public String getLockMk() {
		return this.lockMk;
	}

	public void setLockMk(String lockMk) {
		this.lockMk = lockMk;
	}

	public String getUserNo() {
		return this.userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

}