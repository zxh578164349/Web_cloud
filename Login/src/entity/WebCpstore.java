package entity;

/**
 * WebCpstore entity. @author MyEclipse Persistence Tools
 */

public class WebCpstore implements java.io.Serializable {

	// Fields

	private WebCpstoreId id;
	private String factCode;
	private Double cpstoreB;
	private Double noodrB;
	private Double noinvB;
	private Double cpT;
	private Double cpstoreT;
	private Double noodrT;
	private Double noinvT;
	private Double invT;
	private Double invactT;
	private Double cpinvExp;
	private Double otherT;
	private Double otherLoss;
	private String dateB;
	private String dateE;
	private String lockMk;
	private String userNo;
	private String dateTime;

	// Constructors

	/** default constructor */
	public WebCpstore() {
	}

	/** minimal constructor */
	public WebCpstore(WebCpstoreId id) {
		this.id = id;
	}

	/** full constructor */
	public WebCpstore(WebCpstoreId id, String factCode, Double cpstoreB,
			Double noodrB, Double noinvB, Double cpT, Double cpstoreT,
			Double noodrT, Double noinvT, Double invT, Double invactT,
			Double cpinvExp, Double otherT, Double otherLoss, String dateB,
			String dateE, String lockMk, String userNo, String dateTime) {
		this.id = id;
		this.factCode = factCode;
		this.cpstoreB = cpstoreB;
		this.noodrB = noodrB;
		this.noinvB = noinvB;
		this.cpT = cpT;
		this.cpstoreT = cpstoreT;
		this.noodrT = noodrT;
		this.noinvT = noinvT;
		this.invT = invT;
		this.invactT = invactT;
		this.cpinvExp = cpinvExp;
		this.otherT = otherT;
		this.otherLoss = otherLoss;
		this.dateB = dateB;
		this.dateE = dateE;
		this.lockMk = lockMk;
		this.userNo = userNo;
		this.dateTime = dateTime;
	}

	// Property accessors

	public WebCpstoreId getId() {
		return this.id;
	}

	public void setId(WebCpstoreId id) {
		this.id = id;
	}

	public String getFactCode() {
		return this.factCode;
	}

	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}

	public Double getCpstoreB() {
		return this.cpstoreB;
	}

	public void setCpstoreB(Double cpstoreB) {
		this.cpstoreB = cpstoreB;
	}

	public Double getNoodrB() {
		return this.noodrB;
	}

	public void setNoodrB(Double noodrB) {
		this.noodrB = noodrB;
	}

	public Double getNoinvB() {
		return this.noinvB;
	}

	public void setNoinvB(Double noinvB) {
		this.noinvB = noinvB;
	}

	public Double getCpT() {
		return this.cpT;
	}

	public void setCpT(Double cpT) {
		this.cpT = cpT;
	}

	public Double getCpstoreT() {
		return this.cpstoreT;
	}

	public void setCpstoreT(Double cpstoreT) {
		this.cpstoreT = cpstoreT;
	}

	public Double getNoodrT() {
		return this.noodrT;
	}

	public void setNoodrT(Double noodrT) {
		this.noodrT = noodrT;
	}

	public Double getNoinvT() {
		return this.noinvT;
	}

	public void setNoinvT(Double noinvT) {
		this.noinvT = noinvT;
	}

	public Double getInvT() {
		return this.invT;
	}

	public void setInvT(Double invT) {
		this.invT = invT;
	}

	public Double getInvactT() {
		return this.invactT;
	}

	public void setInvactT(Double invactT) {
		this.invactT = invactT;
	}

	public Double getCpinvExp() {
		return this.cpinvExp;
	}

	public void setCpinvExp(Double cpinvExp) {
		this.cpinvExp = cpinvExp;
	}

	public Double getOtherT() {
		return this.otherT;
	}

	public void setOtherT(Double otherT) {
		this.otherT = otherT;
	}

	public Double getOtherLoss() {
		return this.otherLoss;
	}

	public void setOtherLoss(Double otherLoss) {
		this.otherLoss = otherLoss;
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