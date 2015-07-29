package entity;

/**
 * WebSide entity. @author MyEclipse Persistence Tools
 */

public class WebSide implements java.io.Serializable {

	// Fields

	private WebSideId id;
	private String factCode;
	private Double sideWeit;
	private Double sideAvg;
	private Double sideExp;
	private Double sideCash;
	private Double badCount;
	private Double badWeit;
	private Double badExp;
	private Double badCash;
	private Double badSidew;
	private Double badSidec;
	private String dateB;
	private String dateE;
	private String lockMk;
	private String userNo;
	private String dateTime;

	// Constructors

	/** default constructor */
	public WebSide() {
	}

	/** minimal constructor */
	public WebSide(WebSideId id) {
		this.id = id;
	}

	/** full constructor */
	public WebSide(WebSideId id, String factCode, Double sideWeit,
			Double sideAvg, Double sideExp, Double sideCash, Double badCount,
			Double badWeit, Double badExp, Double badCash, Double badSidew,
			Double badSidec, String dateB, String dateE, String lockMk,
			String userNo, String dateTime) {
		this.id = id;
		this.factCode = factCode;
		this.sideWeit = sideWeit;
		this.sideAvg = sideAvg;
		this.sideExp = sideExp;
		this.sideCash = sideCash;
		this.badCount = badCount;
		this.badWeit = badWeit;
		this.badExp = badExp;
		this.badCash = badCash;
		this.badSidew = badSidew;
		this.badSidec = badSidec;
		this.dateB = dateB;
		this.dateE = dateE;
		this.lockMk = lockMk;
		this.userNo = userNo;
		this.dateTime = dateTime;
	}

	// Property accessors

	public WebSideId getId() {
		return this.id;
	}

	public void setId(WebSideId id) {
		this.id = id;
	}

	public String getFactCode() {
		return this.factCode;
	}

	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}

	public Double getSideWeit() {
		return this.sideWeit;
	}

	public void setSideWeit(Double sideWeit) {
		this.sideWeit = sideWeit;
	}

	public Double getSideAvg() {
		return this.sideAvg;
	}

	public void setSideAvg(Double sideAvg) {
		this.sideAvg = sideAvg;
	}

	public Double getSideExp() {
		return this.sideExp;
	}

	public void setSideExp(Double sideExp) {
		this.sideExp = sideExp;
	}

	public Double getSideCash() {
		return this.sideCash;
	}

	public void setSideCash(Double sideCash) {
		this.sideCash = sideCash;
	}

	public Double getBadCount() {
		return this.badCount;
	}

	public void setBadCount(Double badCount) {
		this.badCount = badCount;
	}

	public Double getBadWeit() {
		return this.badWeit;
	}

	public void setBadWeit(Double badWeit) {
		this.badWeit = badWeit;
	}

	public Double getBadExp() {
		return this.badExp;
	}

	public void setBadExp(Double badExp) {
		this.badExp = badExp;
	}

	public Double getBadCash() {
		return this.badCash;
	}

	public void setBadCash(Double badCash) {
		this.badCash = badCash;
	}

	public Double getBadSidew() {
		return this.badSidew;
	}

	public void setBadSidew(Double badSidew) {
		this.badSidew = badSidew;
	}

	public Double getBadSidec() {
		return this.badSidec;
	}

	public void setBadSidec(Double badSidec) {
		this.badSidec = badSidec;
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