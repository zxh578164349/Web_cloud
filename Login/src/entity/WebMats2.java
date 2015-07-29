package entity;

/**
 * WebMats2 entity. @author MyEclipse Persistence Tools
 */

public class WebMats2 implements java.io.Serializable {

	// Fields

	private WebMats2Id id;
	private String factCode;
	private Double outputCount;
	private Double outputCash;
	private Double cashExp;
	private String dateB;
	private String dateE;
	private String lockMk;
	private String userNo;
	private String dateTime;
	private Double haoyongCount;
	private Double countDanhao;

	// Constructors

	/** default constructor */
	public WebMats2() {
	}

	/** minimal constructor */
	public WebMats2(WebMats2Id id) {
		this.id = id;
	}

	/** full constructor */
	public WebMats2(WebMats2Id id, String factCode, Double outputCount,
			Double outputCash, Double cashExp, String dateB, String dateE,
			String lockMk, String userNo, String dateTime, Double haoyongCount,
			Double countDanhao) {
		this.id = id;
		this.factCode = factCode;
		this.outputCount = outputCount;
		this.outputCash = outputCash;
		this.cashExp = cashExp;
		this.dateB = dateB;
		this.dateE = dateE;
		this.lockMk = lockMk;
		this.userNo = userNo;
		this.dateTime = dateTime;
		this.haoyongCount = haoyongCount;
		this.countDanhao = countDanhao;
	}

	// Property accessors

	public WebMats2Id getId() {
		return this.id;
	}

	public void setId(WebMats2Id id) {
		this.id = id;
	}

	public String getFactCode() {
		return this.factCode;
	}

	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}

	public Double getOutputCount() {
		return this.outputCount;
	}

	public void setOutputCount(Double outputCount) {
		this.outputCount = outputCount;
	}

	public Double getOutputCash() {
		return this.outputCash;
	}

	public void setOutputCash(Double outputCash) {
		this.outputCash = outputCash;
	}

	public Double getCashExp() {
		return this.cashExp;
	}

	public void setCashExp(Double cashExp) {
		this.cashExp = cashExp;
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

	public Double getHaoyongCount() {
		return this.haoyongCount;
	}

	public void setHaoyongCount(Double haoyongCount) {
		this.haoyongCount = haoyongCount;
	}

	public Double getCountDanhao() {
		return this.countDanhao;
	}

	public void setCountDanhao(Double countDanhao) {
		this.countDanhao = countDanhao;
	}

}