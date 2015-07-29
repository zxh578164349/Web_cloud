package entity;

/**
 * WebBackpf entity. @author MyEclipse Persistence Tools
 */

public class WebBackpf implements java.io.Serializable {

	// Fields

	private WebBackpfId id;
	private String factCode;
	private Double pfWeit;
	private Double backPf;
	private Double backExp;
	private Double backOutput;
	private Double backOutputexp;
	private Double backExp2;
	private String dateB;
	private String dateE;
	private String lockMk;
	private String userNo;
	private String dateTime;

	// Constructors

	/** default constructor */
	public WebBackpf() {
	}

	/** minimal constructor */
	public WebBackpf(WebBackpfId id) {
		this.id = id;
	}

	/** full constructor */
	public WebBackpf(WebBackpfId id, String factCode, Double pfWeit,
			Double backPf, Double backExp, Double backOutput,
			Double backOutputexp, Double backExp2, String dateB, String dateE,
			String lockMk, String userNo, String dateTime) {
		this.id = id;
		this.factCode = factCode;
		this.pfWeit = pfWeit;
		this.backPf = backPf;
		this.backExp = backExp;
		this.backOutput = backOutput;
		this.backOutputexp = backOutputexp;
		this.backExp2 = backExp2;
		this.dateB = dateB;
		this.dateE = dateE;
		this.lockMk = lockMk;
		this.userNo = userNo;
		this.dateTime = dateTime;
	}

	// Property accessors

	public WebBackpfId getId() {
		return this.id;
	}

	public void setId(WebBackpfId id) {
		this.id = id;
	}

	public String getFactCode() {
		return this.factCode;
	}

	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}

	public Double getPfWeit() {
		return this.pfWeit;
	}

	public void setPfWeit(Double pfWeit) {
		this.pfWeit = pfWeit;
	}

	public Double getBackPf() {
		return this.backPf;
	}

	public void setBackPf(Double backPf) {
		this.backPf = backPf;
	}

	public Double getBackExp() {
		return this.backExp;
	}

	public void setBackExp(Double backExp) {
		this.backExp = backExp;
	}

	public Double getBackOutput() {
		return this.backOutput;
	}

	public void setBackOutput(Double backOutput) {
		this.backOutput = backOutput;
	}

	public Double getBackOutputexp() {
		return this.backOutputexp;
	}

	public void setBackOutputexp(Double backOutputexp) {
		this.backOutputexp = backOutputexp;
	}

	public Double getBackExp2() {
		return this.backExp2;
	}

	public void setBackExp2(Double backExp2) {
		this.backExp2 = backExp2;
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