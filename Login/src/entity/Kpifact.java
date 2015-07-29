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
			Double factleaveRate,String username) {
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
		this.username=username;
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
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

}