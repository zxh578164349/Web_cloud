package entity;

/**
 * WebMachin entity. @author MyEclipse Persistence Tools
 */

public class WebMachin implements java.io.Serializable {

	// Fields

	private WebMachinId id;
	private String factCode;
	private Double machineNum;
	private Double workDay;
	private Double realDemo;
	private Double dayDemo;
	private Double weitEvg;
	private Double turnNo;
	private Double turnAct;
	private String dateB;
	private String dateE;
	private String lockMk;
	private String userNo;
	private String dateTime;

	// Constructors

	/** default constructor */
	public WebMachin() {
	}

	/** minimal constructor */
	public WebMachin(WebMachinId id) {
		this.id = id;
	}

	/** full constructor */
	public WebMachin(WebMachinId id, String factCode, Double machineNum,
			Double workDay, Double realDemo, Double dayDemo, Double weitEvg,
			Double turnNo, Double turnAct, String dateB, String dateE,
			String lockMk, String userNo, String dateTime) {
		this.id = id;
		this.factCode = factCode;
		this.machineNum = machineNum;
		this.workDay = workDay;
		this.realDemo = realDemo;
		this.dayDemo = dayDemo;
		this.weitEvg = weitEvg;
		this.turnNo = turnNo;
		this.turnAct = turnAct;
		this.dateB = dateB;
		this.dateE = dateE;
		this.lockMk = lockMk;
		this.userNo = userNo;
		this.dateTime = dateTime;
	}

	// Property accessors

	public WebMachinId getId() {
		return this.id;
	}

	public void setId(WebMachinId id) {
		this.id = id;
	}

	public String getFactCode() {
		return this.factCode;
	}

	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}

	public Double getMachineNum() {
		return this.machineNum;
	}

	public void setMachineNum(Double machineNum) {
		this.machineNum = machineNum;
	}

	public Double getWorkDay() {
		return this.workDay;
	}

	public void setWorkDay(Double workDay) {
		this.workDay = workDay;
	}

	public Double getRealDemo() {
		return this.realDemo;
	}

	public void setRealDemo(Double realDemo) {
		this.realDemo = realDemo;
	}

	public Double getDayDemo() {
		return this.dayDemo;
	}

	public void setDayDemo(Double dayDemo) {
		this.dayDemo = dayDemo;
	}

	public Double getWeitEvg() {
		return this.weitEvg;
	}

	public void setWeitEvg(Double weitEvg) {
		this.weitEvg = weitEvg;
	}

	public Double getTurnNo() {
		return this.turnNo;
	}

	public void setTurnNo(Double turnNo) {
		this.turnNo = turnNo;
	}

	public Double getTurnAct() {
		return this.turnAct;
	}

	public void setTurnAct(Double turnAct) {
		this.turnAct = turnAct;
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