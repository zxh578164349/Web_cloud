package entity;

/**
 * WebOutback entity. @author MyEclipse Persistence Tools
 */

public class WebOutback implements java.io.Serializable {

	// Fields

	private WebOutbackId id;
	private String factCode;
	private Double outCount;
	private Double backCount;
	private Double backExp;
	private String dateB;
	private String dateE;
	private String lockMk;
	private String userNo;
	private String dateTime;

	// Constructors

	/** default constructor */
	public WebOutback() {
	}

	/** minimal constructor */
	public WebOutback(WebOutbackId id) {
		this.id = id;
	}

	/** full constructor */
	public WebOutback(WebOutbackId id, String factCode, Double outCount,
			Double backCount, Double backExp, String dateB, String dateE,
			String lockMk, String userNo, String dateTime) {
		this.id = id;
		this.factCode = factCode;
		this.outCount = outCount;
		this.backCount = backCount;
		this.backExp = backExp;
		this.dateB = dateB;
		this.dateE = dateE;
		this.lockMk = lockMk;
		this.userNo = userNo;
		this.dateTime = dateTime;
	}

	// Property accessors

	public WebOutbackId getId() {
		return this.id;
	}

	public void setId(WebOutbackId id) {
		this.id = id;
	}

	public String getFactCode() {
		return this.factCode;
	}

	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}

	public Double getOutCount() {
		return this.outCount;
	}

	public void setOutCount(Double outCount) {
		this.outCount = outCount;
	}

	public Double getBackCount() {
		return this.backCount;
	}

	public void setBackCount(Double backCount) {
		this.backCount = backCount;
	}

	public Double getBackExp() {
		return this.backExp;
	}

	public void setBackExp(Double backExp) {
		this.backExp = backExp;
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