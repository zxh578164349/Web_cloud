package entity;

/**
 * Kpifact entity. @author MyEclipse Persistence Tools
 */

public class Kpifact implements java.io.Serializable {

	// Fields

	private KpifactId id;
	private Double thisYield;
	private Double avgCircle;
	private Double avgCirclehour;
	private Double factaddRate;
	private Double productRate;
	private Double storeNum;
	private Double outRequest;
	private Double outrequestRate;
	private Double avgFactpro;
	private Double avgZgpro;
	private Double avgPerpro;
	private Double avgPermoney;
	private Double permoney;
	private Double waterTon;
	private Double lightDu;
	private Double gasUsd;
	private Double wasteUsd;
	private Double mainRate;
	private Double sideRate;
	private Double wasteRate;
	private Double wasteFact;
	private Double wasteNo;
	private Double zjRate;
	private Double hurtNum;
	private Double zgleaveRate;
	private Double factleaveRate;
	private String username;
	private String usernameUd;
	private Double gasTon;
	private Double mutiRate;
	private Double slIncome;
	private Double waterUsd;
	private Double lightUsd;
	private Double bheadRate;
	private Double bpreRate;
	private Double bflowRate;
	private Double drugWast;
	private Double clrWast;
	private Double leaveUsd;
	private Double pcostRate;
	private Double ccostRate;
	private Double perPrice;
	private Double perSalar;
	private Double uhealRate;

	// Constructors

	/** default constructor */
	public Kpifact() {
	}

	/** minimal constructor */
	public Kpifact(KpifactId id) {
		this.id = id;
	}

	/** full constructor */
	public Kpifact(KpifactId id, Double thisYield, Double avgCircle,
			Double avgCirclehour, Double factaddRate, Double productRate,
			Double storeNum, Double outRequest, Double outrequestRate,
			Double avgFactpro, Double avgZgpro, Double avgPerpro,
			Double avgPermoney, Double permoney, Double waterTon,
			Double lightDu, Double gasUsd, Double wasteUsd, Double mainRate,
			Double sideRate, Double wasteRate, Double wasteFact,
			Double wasteNo, Double zjRate, Double hurtNum, Double zgleaveRate,
			Double factleaveRate, String username, String usernameUd,
			Double gasTon, Double mutiRate, Double slIncome, Double waterUsd,
			Double lightUsd, Double bheadRate, Double bpreRate,
			Double bflowRate, Double drugWast, Double clrWast, Double leaveUsd,
			Double pcostRate, Double ccostRate, Double perPrice,
			Double perSalar, Double uhealRate) {
		this.id = id;
		this.thisYield = thisYield;
		this.avgCircle = avgCircle;
		this.avgCirclehour = avgCirclehour;
		this.factaddRate = factaddRate;
		this.productRate = productRate;
		this.storeNum = storeNum;
		this.outRequest = outRequest;
		this.outrequestRate = outrequestRate;
		this.avgFactpro = avgFactpro;
		this.avgZgpro = avgZgpro;
		this.avgPerpro = avgPerpro;
		this.avgPermoney = avgPermoney;
		this.permoney = permoney;
		this.waterTon = waterTon;
		this.lightDu = lightDu;
		this.gasUsd = gasUsd;
		this.wasteUsd = wasteUsd;
		this.mainRate = mainRate;
		this.sideRate = sideRate;
		this.wasteRate = wasteRate;
		this.wasteFact = wasteFact;
		this.wasteNo = wasteNo;
		this.zjRate = zjRate;
		this.hurtNum = hurtNum;
		this.zgleaveRate = zgleaveRate;
		this.factleaveRate = factleaveRate;
		this.username = username;
		this.usernameUd = usernameUd;
		this.gasTon = gasTon;
		this.mutiRate = mutiRate;
		this.slIncome = slIncome;
		this.waterUsd = waterUsd;
		this.lightUsd = lightUsd;
		this.bheadRate = bheadRate;
		this.bpreRate = bpreRate;
		this.bflowRate = bflowRate;
		this.drugWast = drugWast;
		this.clrWast = clrWast;
		this.leaveUsd = leaveUsd;
		this.pcostRate = pcostRate;
		this.ccostRate = ccostRate;
		this.perPrice = perPrice;
		this.perSalar = perSalar;
		this.uhealRate = uhealRate;
	}

	// Property accessors

	public KpifactId getId() {
		return this.id;
	}

	public void setId(KpifactId id) {
		this.id = id;
	}

	public Double getThisYield() {
		return this.thisYield;
	}

	public void setThisYield(Double thisYield) {
		this.thisYield = thisYield;
	}

	public Double getAvgCircle() {
		return this.avgCircle;
	}

	public void setAvgCircle(Double avgCircle) {
		this.avgCircle = avgCircle;
	}

	public Double getAvgCirclehour() {
		return this.avgCirclehour;
	}

	public void setAvgCirclehour(Double avgCirclehour) {
		this.avgCirclehour = avgCirclehour;
	}

	public Double getFactaddRate() {
		return this.factaddRate;
	}

	public void setFactaddRate(Double factaddRate) {
		this.factaddRate = factaddRate;
	}

	public Double getProductRate() {
		return this.productRate;
	}

	public void setProductRate(Double productRate) {
		this.productRate = productRate;
	}

	public Double getStoreNum() {
		return this.storeNum;
	}

	public void setStoreNum(Double storeNum) {
		this.storeNum = storeNum;
	}

	public Double getOutRequest() {
		return this.outRequest;
	}

	public void setOutRequest(Double outRequest) {
		this.outRequest = outRequest;
	}

	public Double getOutrequestRate() {
		return this.outrequestRate;
	}

	public void setOutrequestRate(Double outrequestRate) {
		this.outrequestRate = outrequestRate;
	}

	public Double getAvgFactpro() {
		return this.avgFactpro;
	}

	public void setAvgFactpro(Double avgFactpro) {
		this.avgFactpro = avgFactpro;
	}

	public Double getAvgZgpro() {
		return this.avgZgpro;
	}

	public void setAvgZgpro(Double avgZgpro) {
		this.avgZgpro = avgZgpro;
	}

	public Double getAvgPerpro() {
		return this.avgPerpro;
	}

	public void setAvgPerpro(Double avgPerpro) {
		this.avgPerpro = avgPerpro;
	}

	public Double getAvgPermoney() {
		return this.avgPermoney;
	}

	public void setAvgPermoney(Double avgPermoney) {
		this.avgPermoney = avgPermoney;
	}

	public Double getPermoney() {
		return this.permoney;
	}

	public void setPermoney(Double permoney) {
		this.permoney = permoney;
	}

	public Double getWaterTon() {
		return this.waterTon;
	}

	public void setWaterTon(Double waterTon) {
		this.waterTon = waterTon;
	}

	public Double getLightDu() {
		return this.lightDu;
	}

	public void setLightDu(Double lightDu) {
		this.lightDu = lightDu;
	}

	public Double getGasUsd() {
		return this.gasUsd;
	}

	public void setGasUsd(Double gasUsd) {
		this.gasUsd = gasUsd;
	}

	public Double getWasteUsd() {
		return this.wasteUsd;
	}

	public void setWasteUsd(Double wasteUsd) {
		this.wasteUsd = wasteUsd;
	}

	public Double getMainRate() {
		return this.mainRate;
	}

	public void setMainRate(Double mainRate) {
		this.mainRate = mainRate;
	}

	public Double getSideRate() {
		return this.sideRate;
	}

	public void setSideRate(Double sideRate) {
		this.sideRate = sideRate;
	}

	public Double getWasteRate() {
		return this.wasteRate;
	}

	public void setWasteRate(Double wasteRate) {
		this.wasteRate = wasteRate;
	}

	public Double getWasteFact() {
		return this.wasteFact;
	}

	public void setWasteFact(Double wasteFact) {
		this.wasteFact = wasteFact;
	}

	public Double getWasteNo() {
		return this.wasteNo;
	}

	public void setWasteNo(Double wasteNo) {
		this.wasteNo = wasteNo;
	}

	public Double getZjRate() {
		return this.zjRate;
	}

	public void setZjRate(Double zjRate) {
		this.zjRate = zjRate;
	}

	public Double getHurtNum() {
		return this.hurtNum;
	}

	public void setHurtNum(Double hurtNum) {
		this.hurtNum = hurtNum;
	}

	public Double getZgleaveRate() {
		return this.zgleaveRate;
	}

	public void setZgleaveRate(Double zgleaveRate) {
		this.zgleaveRate = zgleaveRate;
	}

	public Double getFactleaveRate() {
		return this.factleaveRate;
	}

	public void setFactleaveRate(Double factleaveRate) {
		this.factleaveRate = factleaveRate;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsernameUd() {
		return this.usernameUd;
	}

	public void setUsernameUd(String usernameUd) {
		this.usernameUd = usernameUd;
	}

	public Double getGasTon() {
		return this.gasTon;
	}

	public void setGasTon(Double gasTon) {
		this.gasTon = gasTon;
	}

	public Double getMutiRate() {
		return this.mutiRate;
	}

	public void setMutiRate(Double mutiRate) {
		this.mutiRate = mutiRate;
	}

	public Double getSlIncome() {
		return this.slIncome;
	}

	public void setSlIncome(Double slIncome) {
		this.slIncome = slIncome;
	}

	public Double getWaterUsd() {
		return this.waterUsd;
	}

	public void setWaterUsd(Double waterUsd) {
		this.waterUsd = waterUsd;
	}

	public Double getLightUsd() {
		return this.lightUsd;
	}

	public void setLightUsd(Double lightUsd) {
		this.lightUsd = lightUsd;
	}

	public Double getBheadRate() {
		return this.bheadRate;
	}

	public void setBheadRate(Double bheadRate) {
		this.bheadRate = bheadRate;
	}

	public Double getBpreRate() {
		return this.bpreRate;
	}

	public void setBpreRate(Double bpreRate) {
		this.bpreRate = bpreRate;
	}

	public Double getBflowRate() {
		return this.bflowRate;
	}

	public void setBflowRate(Double bflowRate) {
		this.bflowRate = bflowRate;
	}

	public Double getDrugWast() {
		return this.drugWast;
	}

	public void setDrugWast(Double drugWast) {
		this.drugWast = drugWast;
	}

	public Double getClrWast() {
		return this.clrWast;
	}

	public void setClrWast(Double clrWast) {
		this.clrWast = clrWast;
	}

	public Double getLeaveUsd() {
		return this.leaveUsd;
	}

	public void setLeaveUsd(Double leaveUsd) {
		this.leaveUsd = leaveUsd;
	}

	public Double getPcostRate() {
		return this.pcostRate;
	}

	public void setPcostRate(Double pcostRate) {
		this.pcostRate = pcostRate;
	}

	public Double getCcostRate() {
		return this.ccostRate;
	}

	public void setCcostRate(Double ccostRate) {
		this.ccostRate = ccostRate;
	}

	public Double getPerPrice() {
		return this.perPrice;
	}

	public void setPerPrice(Double perPrice) {
		this.perPrice = perPrice;
	}

	public Double getPerSalar() {
		return this.perSalar;
	}

	public void setPerSalar(Double perSalar) {
		this.perSalar = perSalar;
	}

	public Double getUhealRate() {
		return this.uhealRate;
	}

	public void setUhealRate(Double uhealRate) {
		this.uhealRate = uhealRate;
	}

}