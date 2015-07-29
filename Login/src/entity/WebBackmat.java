package entity;

/**
 * WebBackmat entity. @author MyEclipse Persistence Tools
 */

public class WebBackmat implements java.io.Serializable {

	// Fields

	private WebBackmatId id;
	private String factCode;
	private Double backMat1;
	private Double backMat2;
	private Double backMat3;
	private Double backMat4;
	private String dateB;
	private String dateE;
	private String lockMk;
	private String userNo;
	private String dateTime;

	// Constructors

	/** default constructor */
	public WebBackmat() {
	}

	/** minimal constructor */
	public WebBackmat(WebBackmatId id) {
		this.id = id;
	}

	/** full constructor */
	public WebBackmat(WebBackmatId id, String factCode, Double backMat1,
			Double backMat2, Double backMat3, Double backMat4, String dateB,
			String dateE, String lockMk, String userNo, String dateTime) {
		this.id = id;
		this.factCode = factCode;
		this.backMat1 = backMat1;
		this.backMat2 = backMat2;
		this.backMat3 = backMat3;
		this.backMat4 = backMat4;
		this.dateB = dateB;
		this.dateE = dateE;
		this.lockMk = lockMk;
		this.userNo = userNo;
		this.dateTime = dateTime;
	}

	// Property accessors

	public WebBackmatId getId() {
		return this.id;
	}

	public void setId(WebBackmatId id) {
		this.id = id;
	}

	public String getFactCode() {
		return this.factCode;
	}

	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}

	public Double getBackMat1() {
		return this.backMat1;
	}

	public void setBackMat1(Double backMat1) {
		this.backMat1 = backMat1;
	}

	public Double getBackMat2() {
		return this.backMat2;
	}

	public void setBackMat2(Double backMat2) {
		this.backMat2 = backMat2;
	}

	public Double getBackMat3() {
		return this.backMat3;
	}

	public void setBackMat3(Double backMat3) {
		this.backMat3 = backMat3;
	}

	public Double getBackMat4() {
		return this.backMat4;
	}

	public void setBackMat4(Double backMat4) {
		this.backMat4 = backMat4;
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