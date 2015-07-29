package entity;

/**
 * WebPfavgprice entity. @author MyEclipse Persistence Tools
 */

public class WebPfavgprice implements java.io.Serializable {

	// Fields

	private WebPfavgpriceId id;
	private String factCode;
	private Double price;
	private String dateB;
	private String dateE;
	private String lockMk;
	private String userNo;
	private String dateTime;

	// Constructors

	/** default constructor */
	public WebPfavgprice() {
	}

	/** minimal constructor */
	public WebPfavgprice(WebPfavgpriceId id) {
		this.id = id;
	}

	/** full constructor */
	public WebPfavgprice(WebPfavgpriceId id, String factCode, Double price,
			String dateB, String dateE, String lockMk, String userNo,
			String dateTime) {
		this.id = id;
		this.factCode = factCode;
		this.price = price;
		this.dateB = dateB;
		this.dateE = dateE;
		this.lockMk = lockMk;
		this.userNo = userNo;
		this.dateTime = dateTime;
	}

	// Property accessors

	public WebPfavgpriceId getId() {
		return this.id;
	}

	public void setId(WebPfavgpriceId id) {
		this.id = id;
	}

	public String getFactCode() {
		return this.factCode;
	}

	public void setFactCode(String factCode) {
		this.factCode = factCode;
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