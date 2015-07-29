package entity;

/**
 * WebZwcash entity. @author MyEclipse Persistence Tools
 */

public class WebZwcash implements java.io.Serializable {

	// Fields

	private WebZwcashId id;
	private String factCode;
	private Double realDemo;
	private Double lightDu;
	private Double lightDemo;
	private Double lightUsd;
	private Double lightExp;
	private Double gasT;
	private Double gasDemo;
	private Double gasUsd;
	private Double gasExp;
	private Double oilLi;
	private Double oilDemo;
	private Double oilUsd;
	private Double oilExp;
	private String dateB;
	private String dateE;
	private String lockMk;
	private String userNo;
	private String dateTime;
	private Double waterDu;
	private Double waterDemo;
	private Double waterUsd;
	private Double waterExp;

	// Constructors

	/** default constructor */
	public WebZwcash() {
	}

	/** minimal constructor */
	public WebZwcash(WebZwcashId id) {
		this.id = id;
	}

	/** full constructor */
	public WebZwcash(WebZwcashId id, String factCode, Double realDemo,
			Double lightDu, Double lightDemo, Double lightUsd, Double lightExp,
			Double gasT, Double gasDemo, Double gasUsd, Double gasExp,
			Double oilLi, Double oilDemo, Double oilUsd, Double oilExp,
			String dateB, String dateE, String lockMk, String userNo,
			String dateTime, Double waterDu, Double waterDemo, Double waterUsd,
			Double waterExp) {
		this.id = id;
		this.factCode = factCode;
		this.realDemo = realDemo;
		this.lightDu = lightDu;
		this.lightDemo = lightDemo;
		this.lightUsd = lightUsd;
		this.lightExp = lightExp;
		this.gasT = gasT;
		this.gasDemo = gasDemo;
		this.gasUsd = gasUsd;
		this.gasExp = gasExp;
		this.oilLi = oilLi;
		this.oilDemo = oilDemo;
		this.oilUsd = oilUsd;
		this.oilExp = oilExp;
		this.dateB = dateB;
		this.dateE = dateE;
		this.lockMk = lockMk;
		this.userNo = userNo;
		this.dateTime = dateTime;
		this.waterDu = waterDu;
		this.waterDemo = waterDemo;
		this.waterUsd = waterUsd;
		this.waterExp = waterExp;
	}

	// Property accessors

	public WebZwcashId getId() {
		return this.id;
	}

	public void setId(WebZwcashId id) {
		this.id = id;
	}

	public String getFactCode() {
		return this.factCode;
	}

	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}

	public Double getRealDemo() {
		return this.realDemo;
	}

	public void setRealDemo(Double realDemo) {
		this.realDemo = realDemo;
	}

	public Double getLightDu() {
		return this.lightDu;
	}

	public void setLightDu(Double lightDu) {
		this.lightDu = lightDu;
	}

	public Double getLightDemo() {
		return this.lightDemo;
	}

	public void setLightDemo(Double lightDemo) {
		this.lightDemo = lightDemo;
	}

	public Double getLightUsd() {
		return this.lightUsd;
	}

	public void setLightUsd(Double lightUsd) {
		this.lightUsd = lightUsd;
	}

	public Double getLightExp() {
		return this.lightExp;
	}

	public void setLightExp(Double lightExp) {
		this.lightExp = lightExp;
	}

	public Double getGasT() {
		return this.gasT;
	}

	public void setGasT(Double gasT) {
		this.gasT = gasT;
	}

	public Double getGasDemo() {
		return this.gasDemo;
	}

	public void setGasDemo(Double gasDemo) {
		this.gasDemo = gasDemo;
	}

	public Double getGasUsd() {
		return this.gasUsd;
	}

	public void setGasUsd(Double gasUsd) {
		this.gasUsd = gasUsd;
	}

	public Double getGasExp() {
		return this.gasExp;
	}

	public void setGasExp(Double gasExp) {
		this.gasExp = gasExp;
	}

	public Double getOilLi() {
		return this.oilLi;
	}

	public void setOilLi(Double oilLi) {
		this.oilLi = oilLi;
	}

	public Double getOilDemo() {
		return this.oilDemo;
	}

	public void setOilDemo(Double oilDemo) {
		this.oilDemo = oilDemo;
	}

	public Double getOilUsd() {
		return this.oilUsd;
	}

	public void setOilUsd(Double oilUsd) {
		this.oilUsd = oilUsd;
	}

	public Double getOilExp() {
		return this.oilExp;
	}

	public void setOilExp(Double oilExp) {
		this.oilExp = oilExp;
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

	public Double getWaterDu() {
		return this.waterDu;
	}

	public void setWaterDu(Double waterDu) {
		this.waterDu = waterDu;
	}

	public Double getWaterDemo() {
		return this.waterDemo;
	}

	public void setWaterDemo(Double waterDemo) {
		this.waterDemo = waterDemo;
	}

	public Double getWaterUsd() {
		return this.waterUsd;
	}

	public void setWaterUsd(Double waterUsd) {
		this.waterUsd = waterUsd;
	}

	public Double getWaterExp() {
		return this.waterExp;
	}

	public void setWaterExp(Double waterExp) {
		this.waterExp = waterExp;
	}

}