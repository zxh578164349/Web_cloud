package entity;

/**
 * WebProfitcount entity. @author MyEclipse Persistence Tools
 */

public class WebProfitcount implements java.io.Serializable {

	// Fields

	private WebProfitcountId id;
	private Double invCount;
	private Double costCount;
	private Double wageCount;
	private Double cashCount;
	private Double otherCount;
	private Double thisProfit;
	private Double realAvg;
	private Double cashEvg;
	private String dateB;
	private String dateE;
	private String lockMk;
	private String userNo;
	private String dateTime;
	private String factCode;

	// Constructors

	/** default constructor */
	public WebProfitcount() {
	}

	/** minimal constructor */
	public WebProfitcount(WebProfitcountId id) {
		this.id = id;
	}

	/** full constructor */
	public WebProfitcount(WebProfitcountId id, Double invCount,
			Double costCount, Double wageCount, Double cashCount,
			Double otherCount, Double thisProfit, Double realAvg,
			Double cashEvg, String dateB, String dateE, String lockMk,
			String userNo, String dateTime, String factCode) {
		this.id = id;
		this.invCount = invCount;
		this.costCount = costCount;
		this.wageCount = wageCount;
		this.cashCount = cashCount;
		this.otherCount = otherCount;
		this.thisProfit = thisProfit;
		this.realAvg = realAvg;
		this.cashEvg = cashEvg;
		this.dateB = dateB;
		this.dateE = dateE;
		this.lockMk = lockMk;
		this.userNo = userNo;
		this.dateTime = dateTime;
		this.factCode = factCode;
	}

	// Property accessors

	public WebProfitcountId getId() {
		return this.id;
	}

	public void setId(WebProfitcountId id) {
		this.id = id;
	}

	public Double getInvCount() {
		return this.invCount;
	}

	public void setInvCount(Double invCount) {
		this.invCount = invCount;
	}

	public Double getCostCount() {
		return this.costCount;
	}

	public void setCostCount(Double costCount) {
		this.costCount = costCount;
	}

	public Double getWageCount() {
		return this.wageCount;
	}

	public void setWageCount(Double wageCount) {
		this.wageCount = wageCount;
	}

	public Double getCashCount() {
		return this.cashCount;
	}

	public void setCashCount(Double cashCount) {
		this.cashCount = cashCount;
	}

	public Double getOtherCount() {
		return this.otherCount;
	}

	public void setOtherCount(Double otherCount) {
		this.otherCount = otherCount;
	}

	public Double getThisProfit() {
		return this.thisProfit;
	}

	public void setThisProfit(Double thisProfit) {
		this.thisProfit = thisProfit;
	}

	public Double getRealAvg() {
		return this.realAvg;
	}

	public void setRealAvg(Double realAvg) {
		this.realAvg = realAvg;
	}

	public Double getCashEvg() {
		return this.cashEvg;
	}

	public void setCashEvg(Double cashEvg) {
		this.cashEvg = cashEvg;
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

	public String getFactCode() {
		return this.factCode;
	}

	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}

}