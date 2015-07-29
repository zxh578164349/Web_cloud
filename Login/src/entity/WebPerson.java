package entity;

/**
 * WebPerson entity. @author MyEclipse Persistence Tools
 */

public class WebPerson implements java.io.Serializable {

	// Fields

	private WebPersonId id;
	private String factCode;
	private Double demoStand;
	private Double demoAct;
	private Double personZg;
	private Double personJg;
	private Double personCount;
	private Double personZjg;
	private Double personZgavg;
	private Double personAvg;
	private Double personAvgh;
	private Double leaveZg;
	private Double leaveCount;
	private Double wageZg;
	private Double wageJg;
	private Double wageCount;
	private Double timeCount;
	private Double outputHr;
	private Double profitPair;
	private Double addZg;
	private Double addJg;
	private Double addCount;
	private Double wageZgavg;
	private Double wageJgavg;
	private Double wageZgexp;
	private Double wageJgexp;
	private Double wageAvg;
	private Double personAvghz;
	private String dateB;
	private String dateE;
	private String lockMk;
	private String userNo;
	private String dateTime;

	// Constructors

	/** default constructor */
	public WebPerson() {
	}

	/** minimal constructor */
	public WebPerson(WebPersonId id) {
		this.id = id;
	}

	/** full constructor */
	public WebPerson(WebPersonId id, String factCode, Double demoStand,
			Double demoAct, Double personZg, Double personJg,
			Double personCount, Double personZjg, Double personZgavg,
			Double personAvg, Double personAvgh, Double leaveZg,
			Double leaveCount, Double wageZg, Double wageJg, Double wageCount,
			Double timeCount, Double outputHr, Double profitPair, Double addZg,
			Double addJg, Double addCount, Double wageZgavg, Double wageJgavg,
			Double wageZgexp, Double wageJgexp, Double wageAvg,
			Double personAvghz, String dateB, String dateE, String lockMk,
			String userNo, String dateTime) {
		this.id = id;
		this.factCode = factCode;
		this.demoStand = demoStand;
		this.demoAct = demoAct;
		this.personZg = personZg;
		this.personJg = personJg;
		this.personCount = personCount;
		this.personZjg = personZjg;
		this.personZgavg = personZgavg;
		this.personAvg = personAvg;
		this.personAvgh = personAvgh;
		this.leaveZg = leaveZg;
		this.leaveCount = leaveCount;
		this.wageZg = wageZg;
		this.wageJg = wageJg;
		this.wageCount = wageCount;
		this.timeCount = timeCount;
		this.outputHr = outputHr;
		this.profitPair = profitPair;
		this.addZg = addZg;
		this.addJg = addJg;
		this.addCount = addCount;
		this.wageZgavg = wageZgavg;
		this.wageJgavg = wageJgavg;
		this.wageZgexp = wageZgexp;
		this.wageJgexp = wageJgexp;
		this.wageAvg = wageAvg;
		this.personAvghz = personAvghz;
		this.dateB = dateB;
		this.dateE = dateE;
		this.lockMk = lockMk;
		this.userNo = userNo;
		this.dateTime = dateTime;
	}

	// Property accessors

	public WebPersonId getId() {
		return this.id;
	}

	public void setId(WebPersonId id) {
		this.id = id;
	}

	public String getFactCode() {
		return this.factCode;
	}

	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}

	public Double getDemoStand() {
		return this.demoStand;
	}

	public void setDemoStand(Double demoStand) {
		this.demoStand = demoStand;
	}

	public Double getDemoAct() {
		return this.demoAct;
	}

	public void setDemoAct(Double demoAct) {
		this.demoAct = demoAct;
	}

	public Double getPersonZg() {
		return this.personZg;
	}

	public void setPersonZg(Double personZg) {
		this.personZg = personZg;
	}

	public Double getPersonJg() {
		return this.personJg;
	}

	public void setPersonJg(Double personJg) {
		this.personJg = personJg;
	}

	public Double getPersonCount() {
		return this.personCount;
	}

	public void setPersonCount(Double personCount) {
		this.personCount = personCount;
	}

	public Double getPersonZjg() {
		return this.personZjg;
	}

	public void setPersonZjg(Double personZjg) {
		this.personZjg = personZjg;
	}

	public Double getPersonZgavg() {
		return this.personZgavg;
	}

	public void setPersonZgavg(Double personZgavg) {
		this.personZgavg = personZgavg;
	}

	public Double getPersonAvg() {
		return this.personAvg;
	}

	public void setPersonAvg(Double personAvg) {
		this.personAvg = personAvg;
	}

	public Double getPersonAvgh() {
		return this.personAvgh;
	}

	public void setPersonAvgh(Double personAvgh) {
		this.personAvgh = personAvgh;
	}

	public Double getLeaveZg() {
		return this.leaveZg;
	}

	public void setLeaveZg(Double leaveZg) {
		this.leaveZg = leaveZg;
	}

	public Double getLeaveCount() {
		return this.leaveCount;
	}

	public void setLeaveCount(Double leaveCount) {
		this.leaveCount = leaveCount;
	}

	public Double getWageZg() {
		return this.wageZg;
	}

	public void setWageZg(Double wageZg) {
		this.wageZg = wageZg;
	}

	public Double getWageJg() {
		return this.wageJg;
	}

	public void setWageJg(Double wageJg) {
		this.wageJg = wageJg;
	}

	public Double getWageCount() {
		return this.wageCount;
	}

	public void setWageCount(Double wageCount) {
		this.wageCount = wageCount;
	}

	public Double getTimeCount() {
		return this.timeCount;
	}

	public void setTimeCount(Double timeCount) {
		this.timeCount = timeCount;
	}

	public Double getOutputHr() {
		return this.outputHr;
	}

	public void setOutputHr(Double outputHr) {
		this.outputHr = outputHr;
	}

	public Double getProfitPair() {
		return this.profitPair;
	}

	public void setProfitPair(Double profitPair) {
		this.profitPair = profitPair;
	}

	public Double getAddZg() {
		return this.addZg;
	}

	public void setAddZg(Double addZg) {
		this.addZg = addZg;
	}

	public Double getAddJg() {
		return this.addJg;
	}

	public void setAddJg(Double addJg) {
		this.addJg = addJg;
	}

	public Double getAddCount() {
		return this.addCount;
	}

	public void setAddCount(Double addCount) {
		this.addCount = addCount;
	}

	public Double getWageZgavg() {
		return this.wageZgavg;
	}

	public void setWageZgavg(Double wageZgavg) {
		this.wageZgavg = wageZgavg;
	}

	public Double getWageJgavg() {
		return this.wageJgavg;
	}

	public void setWageJgavg(Double wageJgavg) {
		this.wageJgavg = wageJgavg;
	}

	public Double getWageZgexp() {
		return this.wageZgexp;
	}

	public void setWageZgexp(Double wageZgexp) {
		this.wageZgexp = wageZgexp;
	}

	public Double getWageJgexp() {
		return this.wageJgexp;
	}

	public void setWageJgexp(Double wageJgexp) {
		this.wageJgexp = wageJgexp;
	}

	public Double getWageAvg() {
		return this.wageAvg;
	}

	public void setWageAvg(Double wageAvg) {
		this.wageAvg = wageAvg;
	}

	public Double getPersonAvghz() {
		return this.personAvghz;
	}

	public void setPersonAvghz(Double personAvghz) {
		this.personAvghz = personAvghz;
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