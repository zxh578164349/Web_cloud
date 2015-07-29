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
	private BigDecimal avgCirclehour;//時迴轉
	private BigDecimal factaddRate;//廠補率
	private BigDecimal productRate;//產能達成率
	private Double storeNum;//成倉庫存
	private Double outRequest;//已出未請
	private BigDecimal outrequestRate;//生產與請款差異率
	private BigDecimal avgFactpro;//全廠人均時產能
	private BigDecimal avgZgpro;//直工人均产能
	private BigDecimal avgPerpro;//全厂人均产能
	private BigDecimal avgPermoney;//人均時產值
	private BigDecimal permoney;//人薪產值
	private BigDecimal waterTon;//水用量单耗
	private BigDecimal lightDu;//电度数单耗
	private BigDecimal gasUsd;//汽/油金额单耗
	private BigDecimal wasteUsd;//費用單耗
	private BigDecimal mainRate;//主材料成本比率
	private BigDecimal sideRate;//邊料率
	private BigDecimal wasteRate;//報廢率
	private BigDecimal wasteFact;//全廠總損耗
	private BigDecimal wasteNo;//無形損耗
	private BigDecimal zjRate;//直間比
	private Double hurtNum;//工傷件數
	private BigDecimal zgleaveRate;//直工離職率
	private BigDecimal factleaveRate;//全廠離職率

	// Constructors

	/** default constructor */
	public VKpifact() {
	}

	/** minimal constructor */
	public VKpifact(VKpifactId id) {
		this.id = id;
	}

	/** full constructor */
	public VKpifact(VKpifactId id, BigDecimal thisYield, BigDecimal avgCircle,BigDecimal avgCirclehour,
			BigDecimal factaddRate, BigDecimal productRate, Double storeNum,
			Double outRequest, BigDecimal outrequestRate,
			BigDecimal avgFactpro, BigDecimal avgZgpro, BigDecimal avgPerpro,
			BigDecimal avgPermoney, BigDecimal permoney, BigDecimal waterTon,
			BigDecimal lightDu, BigDecimal gasUsd, BigDecimal wasteUsd,
			BigDecimal mainRate, BigDecimal sideRate, BigDecimal wasteRate,
			BigDecimal wasteFact, BigDecimal wasteNo, BigDecimal zjRate,
			Double hurtNum, BigDecimal zgleaveRate, BigDecimal factleaveRate) {
		this.id = id;
		this.thisYield = thisYield;
		this.avgCircle = avgCircle;
		this.avgCirclehour=avgCirclehour;
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

	public BigDecimal getFactaddRate() {
		return this.factaddRate;
	}

	public void setFactaddRate(BigDecimal factaddRate) {
		this.factaddRate = factaddRate;
	}

	public BigDecimal getProductRate() {
		return this.productRate;
	}

	public void setProductRate(BigDecimal productRate) {
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

	public BigDecimal getOutrequestRate() {
		return this.outrequestRate;
	}

	public void setOutrequestRate(BigDecimal outrequestRate) {
		this.outrequestRate = outrequestRate;
	}

	public BigDecimal getAvgFactpro() {
		return this.avgFactpro;
	}

	public void setAvgFactpro(BigDecimal avgFactpro) {
		this.avgFactpro = avgFactpro;
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

	public BigDecimal getWaterTon() {
		return this.waterTon;
	}

	public void setWaterTon(BigDecimal waterTon) {
		this.waterTon = waterTon;
	}

	public BigDecimal getLightDu() {
		return this.lightDu;
	}

	public void setLightDu(BigDecimal lightDu) {
		this.lightDu = lightDu;
	}

	public BigDecimal getGasUsd() {
		return this.gasUsd;
	}

	public void setGasUsd(BigDecimal gasUsd) {
		this.gasUsd = gasUsd;
	}

	public BigDecimal getWasteUsd() {
		return this.wasteUsd;
	}

	public void setWasteUsd(BigDecimal wasteUsd) {
		this.wasteUsd = wasteUsd;
	}

	public BigDecimal getMainRate() {
		return this.mainRate;
	}

	public void setMainRate(BigDecimal mainRate) {
		this.mainRate = mainRate;
	}

	public BigDecimal getSideRate() {
		return this.sideRate;
	}

	public void setSideRate(BigDecimal sideRate) {
		this.sideRate = sideRate;
	}

	public BigDecimal getWasteRate() {
		return this.wasteRate;
	}

	public void setWasteRate(BigDecimal wasteRate) {
		this.wasteRate = wasteRate;
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

	public BigDecimal getZjRate() {
		return this.zjRate;
	}

	public void setZjRate(BigDecimal zjRate) {
		this.zjRate = zjRate;
	}

	public Double getHurtNum() {
		return this.hurtNum;
	}

	public void setHurtNum(Double hurtNum) {
		this.hurtNum = hurtNum;
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

	public BigDecimal getAvgCirclehour() {
		return avgCirclehour;
	}

	public void setAvgCirclehour(BigDecimal avgCirclehour) {
		this.avgCirclehour = avgCirclehour;
	}


	

}