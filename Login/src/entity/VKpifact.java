package entity;

import java.math.BigDecimal;

/**
 * VKpifact entity. @author MyEclipse Persistence Tools
 */

public class VKpifact implements java.io.Serializable {

	// Fields

	private VKpifactId id;
	private BigDecimal thisYield;//當月產量
	private BigDecimal avgCircle;//月均回轉
	private BigDecimal avgCirclehour;//時回轉
	private BigDecimal mutiRate;//機臺利用率
	private BigDecimal productRate;//產能達成率
	private BigDecimal avgZgpro;//直工人均产能
	private BigDecimal avgPerpro;//全厂人均产能
	private BigDecimal avgFactpro;//全廠人均時產能
	private Double storeNum;//成倉庫存
	private Double outRequest;//已出未請
	private BigDecimal outrequestRate;//生產與請款差異率
	private Double slIncome;//銷貨收入
	private BigDecimal mainRate;//主材料成本比率
	private BigDecimal pcostRate;//人工成本率
	private BigDecimal ccostRate;//費用成本率
	private BigDecimal wasteUsd;//修繕單耗
	private BigDecimal perPrice;//平均單價
	private BigDecimal perSalar;//全廠人均薪資
	private BigDecimal avgPermoney;//人均產值
	private BigDecimal permoney;//人薪產值
	private BigDecimal wasteFact;//全廠總損耗
	private BigDecimal wasteNo;//無形損耗
	private BigDecimal sideRate;//邊料率
	private BigDecimal uhealRate;//不良率
	private BigDecimal wasteRate;//報廢率
	private BigDecimal factaddRate;//廠補率
	private BigDecimal waterTon;//水用量单耗
	private BigDecimal waterUsd;//用水金額單耗
	private BigDecimal lightDu;//电度数单耗
	private BigDecimal lightUsd;//用電金額單耗
	private BigDecimal gasTon;//蒸汽用量單耗
	private BigDecimal gasUsd;//用汽金額單耗
	private BigDecimal bheadRate;//回頭料%
	private BigDecimal bpreRate;//油壓退料%
	private BigDecimal bflowRate;//回流率%
	private BigDecimal drugWast;//藥品用量單耗
	private BigDecimal clrWast;//色料用量單耗
	private BigDecimal leaveUsd;//離型劑金額單耗
	private BigDecimal zjRate;//直間比
	private BigDecimal zgleaveRate;//直工離職率
	private BigDecimal factleaveRate;//全廠離職率
	private Double hurtNum;//工傷件數

	// Constructors

	/** default constructor */
	public VKpifact() {
	}

	/** minimal constructor */
	public VKpifact(VKpifactId id) {
		this.id = id;
	}

	/** full constructor */
	public VKpifact(VKpifactId id, BigDecimal thisYield, BigDecimal avgCircle,
			BigDecimal avgCirclehour, BigDecimal mutiRate,
			BigDecimal productRate, BigDecimal avgZgpro, BigDecimal avgPerpro,
			BigDecimal avgFactpro, Double storeNum, Double outRequest,
			BigDecimal outrequestRate, Double slIncome, BigDecimal mainRate,
			BigDecimal pcostRate, BigDecimal ccostRate, BigDecimal wasteUsd,
			BigDecimal perPrice, BigDecimal perSalar, BigDecimal avgPermoney,
			BigDecimal permoney, BigDecimal wasteFact, BigDecimal wasteNo,
			BigDecimal sideRate, BigDecimal uhealRate, BigDecimal wasteRate,
			BigDecimal factaddRate, BigDecimal waterTon, BigDecimal waterUsd,
			BigDecimal lightDu, BigDecimal lightUsd, BigDecimal gasTon,
			BigDecimal gasUsd, BigDecimal bheadRate, BigDecimal bpreRate,
			BigDecimal bflowRate, BigDecimal drugWast, BigDecimal clrWast,
			BigDecimal leaveUsd, BigDecimal zjRate, BigDecimal zgleaveRate,
			BigDecimal factleaveRate, Double hurtNum) {
		this.id = id;
		this.thisYield = thisYield;
		this.avgCircle = avgCircle;
		this.avgCirclehour = avgCirclehour;
		this.mutiRate = mutiRate;
		this.productRate = productRate;
		this.avgZgpro = avgZgpro;
		this.avgPerpro = avgPerpro;
		this.avgFactpro = avgFactpro;
		this.storeNum = storeNum;
		this.outRequest = outRequest;
		this.outrequestRate = outrequestRate;
		this.slIncome = slIncome;
		this.mainRate = mainRate;
		this.pcostRate = pcostRate;
		this.ccostRate = ccostRate;
		this.wasteUsd = wasteUsd;
		this.perPrice = perPrice;
		this.perSalar = perSalar;
		this.avgPermoney = avgPermoney;
		this.permoney = permoney;
		this.wasteFact = wasteFact;
		this.wasteNo = wasteNo;
		this.sideRate = sideRate;
		this.uhealRate = uhealRate;
		this.wasteRate = wasteRate;
		this.factaddRate = factaddRate;
		this.waterTon = waterTon;
		this.waterUsd = waterUsd;
		this.lightDu = lightDu;
		this.lightUsd = lightUsd;
		this.gasTon = gasTon;
		this.gasUsd = gasUsd;
		this.bheadRate = bheadRate;
		this.bpreRate = bpreRate;
		this.bflowRate = bflowRate;
		this.drugWast = drugWast;
		this.clrWast = clrWast;
		this.leaveUsd = leaveUsd;
		this.zjRate = zjRate;
		this.zgleaveRate = zgleaveRate;
		this.factleaveRate = factleaveRate;
		this.hurtNum = hurtNum;
	}

	// Property accessors

	public VKpifactId getId() {
		return this.id;
	}

	public void setId(VKpifactId id) {
		this.id = id;
	}

	public BigDecimal getThisYield() {
		return this.thisYield;
	}

	public void setThisYield(BigDecimal thisYield) {
		this.thisYield = thisYield;
	}

	public BigDecimal getAvgCircle() {
		return this.avgCircle;
	}

	public void setAvgCircle(BigDecimal avgCircle) {
		this.avgCircle = avgCircle;
	}

	public BigDecimal getAvgCirclehour() {
		return this.avgCirclehour;
	}

	public void setAvgCirclehour(BigDecimal avgCirclehour) {
		this.avgCirclehour = avgCirclehour;
	}

	public BigDecimal getMutiRate() {
		return this.mutiRate;
	}

	public void setMutiRate(BigDecimal mutiRate) {
		this.mutiRate = mutiRate;
	}

	public BigDecimal getProductRate() {
		return this.productRate;
	}

	public void setProductRate(BigDecimal productRate) {
		this.productRate = productRate;
	}

	public BigDecimal getAvgZgpro() {
		return this.avgZgpro;
	}

	public void setAvgZgpro(BigDecimal avgZgpro) {
		this.avgZgpro = avgZgpro;
	}

	public BigDecimal getAvgPerpro() {
		return this.avgPerpro;
	}

	public void setAvgPerpro(BigDecimal avgPerpro) {
		this.avgPerpro = avgPerpro;
	}

	public BigDecimal getAvgFactpro() {
		return this.avgFactpro;
	}

	public void setAvgFactpro(BigDecimal avgFactpro) {
		this.avgFactpro = avgFactpro;
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

	public BigDecimal getOutrequestRate() {
		return this.outrequestRate;
	}

	public void setOutrequestRate(BigDecimal outrequestRate) {
		this.outrequestRate = outrequestRate;
	}

	public Double getSlIncome() {
		return this.slIncome;
	}

	public void setSlIncome(Double slIncome) {
		this.slIncome = slIncome;
	}

	public BigDecimal getMainRate() {
		return this.mainRate;
	}

	public void setMainRate(BigDecimal mainRate) {
		this.mainRate = mainRate;
	}

	public BigDecimal getPcostRate() {
		return this.pcostRate;
	}

	public void setPcostRate(BigDecimal pcostRate) {
		this.pcostRate = pcostRate;
	}

	public BigDecimal getCcostRate() {
		return this.ccostRate;
	}

	public void setCcostRate(BigDecimal ccostRate) {
		this.ccostRate = ccostRate;
	}

	public BigDecimal getWasteUsd() {
		return this.wasteUsd;
	}

	public void setWasteUsd(BigDecimal wasteUsd) {
		this.wasteUsd = wasteUsd;
	}

	public BigDecimal getPerPrice() {
		return this.perPrice;
	}

	public void setPerPrice(BigDecimal perPrice) {
		this.perPrice = perPrice;
	}

	public BigDecimal getPerSalar() {
		return this.perSalar;
	}

	public void setPerSalar(BigDecimal perSalar) {
		this.perSalar = perSalar;
	}

	public BigDecimal getAvgPermoney() {
		return this.avgPermoney;
	}

	public void setAvgPermoney(BigDecimal avgPermoney) {
		this.avgPermoney = avgPermoney;
	}

	public BigDecimal getPermoney() {
		return this.permoney;
	}

	public void setPermoney(BigDecimal permoney) {
		this.permoney = permoney;
	}

	public BigDecimal getWasteFact() {
		return this.wasteFact;
	}

	public void setWasteFact(BigDecimal wasteFact) {
		this.wasteFact = wasteFact;
	}

	public BigDecimal getWasteNo() {
		return this.wasteNo;
	}

	public void setWasteNo(BigDecimal wasteNo) {
		this.wasteNo = wasteNo;
	}

	public BigDecimal getSideRate() {
		return this.sideRate;
	}

	public void setSideRate(BigDecimal sideRate) {
		this.sideRate = sideRate;
	}

	public BigDecimal getUhealRate() {
		return this.uhealRate;
	}

	public void setUhealRate(BigDecimal uhealRate) {
		this.uhealRate = uhealRate;
	}

	public BigDecimal getWasteRate() {
		return this.wasteRate;
	}

	public void setWasteRate(BigDecimal wasteRate) {
		this.wasteRate = wasteRate;
	}

	public BigDecimal getFactaddRate() {
		return this.factaddRate;
	}

	public void setFactaddRate(BigDecimal factaddRate) {
		this.factaddRate = factaddRate;
	}

	public BigDecimal getWaterTon() {
		return this.waterTon;
	}

	public void setWaterTon(BigDecimal waterTon) {
		this.waterTon = waterTon;
	}

	public BigDecimal getWaterUsd() {
		return this.waterUsd;
	}

	public void setWaterUsd(BigDecimal waterUsd) {
		this.waterUsd = waterUsd;
	}

	public BigDecimal getLightDu() {
		return this.lightDu;
	}

	public void setLightDu(BigDecimal lightDu) {
		this.lightDu = lightDu;
	}

	public BigDecimal getLightUsd() {
		return this.lightUsd;
	}

	public void setLightUsd(BigDecimal lightUsd) {
		this.lightUsd = lightUsd;
	}

	public BigDecimal getGasTon() {
		return this.gasTon;
	}

	public void setGasTon(BigDecimal gasTon) {
		this.gasTon = gasTon;
	}

	public BigDecimal getGasUsd() {
		return this.gasUsd;
	}

	public void setGasUsd(BigDecimal gasUsd) {
		this.gasUsd = gasUsd;
	}

	public BigDecimal getBheadRate() {
		return this.bheadRate;
	}

	public void setBheadRate(BigDecimal bheadRate) {
		this.bheadRate = bheadRate;
	}

	public BigDecimal getBpreRate() {
		return this.bpreRate;
	}

	public void setBpreRate(BigDecimal bpreRate) {
		this.bpreRate = bpreRate;
	}

	public BigDecimal getBflowRate() {
		return this.bflowRate;
	}

	public void setBflowRate(BigDecimal bflowRate) {
		this.bflowRate = bflowRate;
	}

	public BigDecimal getDrugWast() {
		return this.drugWast;
	}

	public void setDrugWast(BigDecimal drugWast) {
		this.drugWast = drugWast;
	}

	public BigDecimal getClrWast() {
		return this.clrWast;
	}

	public void setClrWast(BigDecimal clrWast) {
		this.clrWast = clrWast;
	}

	public BigDecimal getLeaveUsd() {
		return this.leaveUsd;
	}

	public void setLeaveUsd(BigDecimal leaveUsd) {
		this.leaveUsd = leaveUsd;
	}

	public BigDecimal getZjRate() {
		return this.zjRate;
	}

	public void setZjRate(BigDecimal zjRate) {
		this.zjRate = zjRate;
	}

	public BigDecimal getZgleaveRate() {
		return this.zgleaveRate;
	}

	public void setZgleaveRate(BigDecimal zgleaveRate) {
		this.zgleaveRate = zgleaveRate;
	}

	public BigDecimal getFactleaveRate() {
		return this.factleaveRate;
	}

	public void setFactleaveRate(BigDecimal factleaveRate) {
		this.factleaveRate = factleaveRate;
	}

	public Double getHurtNum() {
		return this.hurtNum;
	}

	public void setHurtNum(Double hurtNum) {
		this.hurtNum = hurtNum;
	}

}