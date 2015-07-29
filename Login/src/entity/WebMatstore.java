package entity;

/**
 * WebMatstore entity. @author MyEclipse Persistence Tools
 */

public class WebMatstore implements java.io.Serializable {

	// Fields

	private WebMatstoreId id;
	private String factCode;
	private Double matStore;
	private Double matCash;
	private Double matPrice;
	private Double upMat;
	private Double RWeit;
	private Double RTon;
	private Double RCash;
	private Double RPrice;
	private Double upR;
	private String dateB;
	private String dateE;
	private String lockMk;
	private String userNo;
	private String dateTime;

	// Constructors

	/** default constructor */
	public WebMatstore() {
	}

	/** minimal constructor */
	public WebMatstore(WebMatstoreId id) {
		this.id = id;
	}

	/** full constructor */
	public WebMatstore(WebMatstoreId id, String factCode, Double matStore,
			Double matCash, Double matPrice, Double upMat, Double RWeit,
			Double RTon, Double RCash, Double RPrice, Double upR, String dateB,
			String dateE, String lockMk, String userNo, String dateTime) {
		this.id = id;
		this.factCode = factCode;
		this.matStore = matStore;
		this.matCash = matCash;
		this.matPrice = matPrice;
		this.upMat = upMat;
		this.RWeit = RWeit;
		this.RTon = RTon;
		this.RCash = RCash;
		this.RPrice = RPrice;
		this.upR = upR;
		this.dateB = dateB;
		this.dateE = dateE;
		this.lockMk = lockMk;
		this.userNo = userNo;
		this.dateTime = dateTime;
	}

	// Property accessors

	public WebMatstoreId getId() {
		return this.id;
	}

	public void setId(WebMatstoreId id) {
		this.id = id;
	}

	public String getFactCode() {
		return this.factCode;
	}

	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}

	public Double getMatStore() {
		return this.matStore;
	}

	public void setMatStore(Double matStore) {
		this.matStore = matStore;
	}

	public Double getMatCash() {
		return this.matCash;
	}

	public void setMatCash(Double matCash) {
		this.matCash = matCash;
	}

	public Double getMatPrice() {
		return this.matPrice;
	}

	public void setMatPrice(Double matPrice) {
		this.matPrice = matPrice;
	}

	public Double getUpMat() {
		return this.upMat;
	}

	public void setUpMat(Double upMat) {
		this.upMat = upMat;
	}

	public Double getRWeit() {
		return this.RWeit;
	}

	public void setRWeit(Double RWeit) {
		this.RWeit = RWeit;
	}

	public Double getRTon() {
		return this.RTon;
	}

	public void setRTon(Double RTon) {
		this.RTon = RTon;
	}

	public Double getRCash() {
		return this.RCash;
	}

	public void setRCash(Double RCash) {
		this.RCash = RCash;
	}

	public Double getRPrice() {
		return this.RPrice;
	}

	public void setRPrice(Double RPrice) {
		this.RPrice = RPrice;
	}

	public Double getUpR() {
		return this.upR;
	}

	public void setUpR(Double upR) {
		this.upR = upR;
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