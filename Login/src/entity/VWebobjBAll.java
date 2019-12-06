package entity;



/**
 * VWebobjBAll entity. @author MyEclipse Persistence Tools
 */

public class VWebobjBAll implements java.io.Serializable {

	// Fields

	private VWebobjBAllId id;
	private Double totalhole;//最大孔位數
	private Double machinepower;//最大生產數(模/月)
	private Double hole;//有效孔位數  
	private Double sample;//工程樣品孔位
	private Double accessories;//補料孔位
	private Double other;//其它孔位
	private Double sumRealcashoutpairs;//實際請款雙數(雙/月)
	private Double sumRealcashoutmoney;//實際請款金額(USD)
	private Double sumActualyield;//實際生產模數(模/月)
	private Double formulaA;//實際生產雙數(雙/月)
	private Double sumZpobja;//正批生產雙數(雙)/月
	private Double sumHostpairs;//客補生產雙數(雙)/月
	private Double sumFactpairs;//廠補生產雙數(雙)/月
	private Double sumSamplepairs;//樣品生產雙數(雙)/月
	private Double avgOnmodulus;//月平均_上模數
	private Double avgPersonnum;//月平均_人數(拉模手)
	private Double avgStandardoutput;//月平均_標準產量(模/日)
	private Double avgActualyield;//月平均_實際產量(模/日)
	private Double formulaB;//月平均_實際產量(雙)
	private Double formulaC;//月達成率(%)
	private Double formulaD;//月實際回轉數
	private Double avgObjA2;//月平均_慢單狀況(張)
	private Double avgObjA3;//月平均_慢單狀況(雙)
	private Double objA4;//當月月底之訂單欠數
	private Long sumObjA7;//當月總招工數
	private Long sumObjA8;//當月總離職數
	private Long CObjA6;//直工人數
	private Long CObjA7;//間工人數
	private Long CObjA8;//全廠人數
	private Double CObjA2;//機台利用率
	private Double CObjA10;//直工人均產能
	private Double CObjA11;//全廠人均產能
	private Double CObjA14;//加班費
	private Double CObjA15;//成本率
	private Double CObjA16;//回頭率
	private Double CObjA17;//總損耗
	private Double CObjA18;//平均邊料重
	private Double CObjA19;//邊料率
	private Double CObjA21;//不良率
	private Double CObjA22;//退貨率
	private Double CObjA24;//用水單耗
	private Double CObjA25;//用電單耗
	private Double CObjA26;//蒸汽單耗（USD/模）
	private Double CObjA27;//蒸汽單耗  （KG/模）
	private Double CObjA28;//色料藥品單耗  （G/雙）
	private Double CObjA29;//色料藥品單耗  （USD/雙）

	// Constructors

	/** default constructor */
	public VWebobjBAll() {
	}

	/** minimal constructor */
	public VWebobjBAll(VWebobjBAllId id) {
		this.id = id;
	}

	/** full constructor */
	public VWebobjBAll(VWebobjBAllId id, Double totalhole, Double machinepower,
			Double hole, Double sample, Double accessories, Double other,
			Double sumRealcashoutpairs, Double sumRealcashoutmoney,
			Double sumActualyield, Double formulaA,
			Double sumZpobja, Double sumHostpairs,
			Double sumFactpairs, Double sumSamplepairs,
			Double avgOnmodulus, Double avgPersonnum,
			Double avgStandardoutput, Double avgActualyield,
			Double formulaB, Double formulaC, Double formulaD,
			Double avgObjA2, Double avgObjA3, Double objA4,
			Long sumObjA7, Long sumObjA8, Long CObjA6,
			Long CObjA7, Long CObjA8, Double CObjA2, Double CObjA10,
			Double CObjA11, Double CObjA14, Double CObjA15, Double CObjA16,
			Double CObjA17, Double CObjA18, Double CObjA19, Double CObjA21,
			Double CObjA22, Double CObjA24, Double CObjA25, Double CObjA26,
			Double CObjA27, Double CObjA28, Double CObjA29) {
		this.id = id;
		this.totalhole = totalhole;
		this.machinepower = machinepower;
		this.hole = hole;
		this.sample = sample;
		this.accessories = accessories;
		this.other = other;
		this.sumRealcashoutpairs = sumRealcashoutpairs;
		this.sumRealcashoutmoney = sumRealcashoutmoney;
		this.sumActualyield = sumActualyield;
		this.formulaA = formulaA;
		this.sumZpobja = sumZpobja;
		this.sumHostpairs = sumHostpairs;
		this.sumFactpairs = sumFactpairs;
		this.sumSamplepairs = sumSamplepairs;
		this.avgOnmodulus = avgOnmodulus;
		this.avgPersonnum = avgPersonnum;
		this.avgStandardoutput = avgStandardoutput;
		this.avgActualyield = avgActualyield;
		this.formulaB = formulaB;
		this.formulaC = formulaC;
		this.formulaD = formulaD;
		this.avgObjA2 = avgObjA2;
		this.avgObjA3 = avgObjA3;
		this.objA4 = objA4;
		this.sumObjA7 = sumObjA7;
		this.sumObjA8 = sumObjA8;
		this.CObjA6 = CObjA6;
		this.CObjA7 = CObjA7;
		this.CObjA8 = CObjA8;
		this.CObjA2 = CObjA2;
		this.CObjA10 = CObjA10;
		this.CObjA11 = CObjA11;
		this.CObjA14 = CObjA14;
		this.CObjA15 = CObjA15;
		this.CObjA16 = CObjA16;
		this.CObjA17 = CObjA17;
		this.CObjA18 = CObjA18;
		this.CObjA19 = CObjA19;
		this.CObjA21 = CObjA21;
		this.CObjA22 = CObjA22;
		this.CObjA24 = CObjA24;
		this.CObjA25 = CObjA25;
		this.CObjA26 = CObjA26;
		this.CObjA27 = CObjA27;
		this.CObjA28 = CObjA28;
		this.CObjA29 = CObjA29;
	}

	// Property accessors

	public VWebobjBAllId getId() {
		return this.id;
	}

	public void setId(VWebobjBAllId id) {
		this.id = id;
	}

	public Double getTotalhole() {
		return this.totalhole;
	}

	public void setTotalhole(Double totalhole) {
		this.totalhole = totalhole;
	}

	public Double getMachinepower() {
		return this.machinepower;
	}

	public void setMachinepower(Double machinepower) {
		this.machinepower = machinepower;
	}

	public Double getHole() {
		return this.hole;
	}

	public void setHole(Double hole) {
		this.hole = hole;
	}

	public Double getSample() {
		return this.sample;
	}

	public void setSample(Double sample) {
		this.sample = sample;
	}

	public Double getAccessories() {
		return this.accessories;
	}

	public void setAccessories(Double accessories) {
		this.accessories = accessories;
	}

	public Double getOther() {
		return this.other;
	}

	public void setOther(Double other) {
		this.other = other;
	}

	public Double getSumRealcashoutpairs() {
		return this.sumRealcashoutpairs;
	}

	public void setSumRealcashoutpairs(Double sumRealcashoutpairs) {
		this.sumRealcashoutpairs = sumRealcashoutpairs;
	}

	public Double getSumRealcashoutmoney() {
		return this.sumRealcashoutmoney;
	}

	public void setSumRealcashoutmoney(Double sumRealcashoutmoney) {
		this.sumRealcashoutmoney = sumRealcashoutmoney;
	}

	public Double getSumActualyield() {
		return this.sumActualyield;
	}

	public void setSumActualyield(Double sumActualyield) {
		this.sumActualyield = sumActualyield;
	}

	public Double getFormulaA() {
		return this.formulaA;
	}

	public void setFormulaA(Double formulaA) {
		this.formulaA = formulaA;
	}

	public Double getSumZpobja() {
		return this.sumZpobja;
	}

	public void setSumZpobja(Double sumZpobja) {
		this.sumZpobja = sumZpobja;
	}

	public Double getSumHostpairs() {
		return this.sumHostpairs;
	}

	public void setSumHostpairs(Double sumHostpairs) {
		this.sumHostpairs = sumHostpairs;
	}

	public Double getSumFactpairs() {
		return this.sumFactpairs;
	}

	public void setSumFactpairs(Double sumFactpairs) {
		this.sumFactpairs = sumFactpairs;
	}

	public Double getSumSamplepairs() {
		return this.sumSamplepairs;
	}

	public void setSumSamplepairs(Double sumSamplepairs) {
		this.sumSamplepairs = sumSamplepairs;
	}

	public Double getAvgOnmodulus() {
		return this.avgOnmodulus;
	}

	public void setAvgOnmodulus(Double avgOnmodulus) {
		this.avgOnmodulus = avgOnmodulus;
	}

	public Double getAvgPersonnum() {
		return this.avgPersonnum;
	}

	public void setAvgPersonnum(Double avgPersonnum) {
		this.avgPersonnum = avgPersonnum;
	}

	public Double getAvgStandardoutput() {
		return this.avgStandardoutput;
	}

	public void setAvgStandardoutput(Double avgStandardoutput) {
		this.avgStandardoutput = avgStandardoutput;
	}

	public Double getAvgActualyield() {
		return this.avgActualyield;
	}

	public void setAvgActualyield(Double avgActualyield) {
		this.avgActualyield = avgActualyield;
	}

	public Double getFormulaB() {
		return this.formulaB;
	}

	public void setFormulaB(Double formulaB) {
		this.formulaB = formulaB;
	}

	public Double getFormulaC() {
		return this.formulaC;
	}

	public void setFormulaC(Double formulaC) {
		this.formulaC = formulaC;
	}

	public Double getFormulaD() {
		return this.formulaD;
	}

	public void setFormulaD(Double formulaD) {
		this.formulaD = formulaD;
	}

	public Double getAvgObjA2() {
		return this.avgObjA2;
	}

	public void setAvgObjA2(Double avgObjA2) {
		this.avgObjA2 = avgObjA2;
	}

	public Double getAvgObjA3() {
		return this.avgObjA3;
	}

	public void setAvgObjA3(Double avgObjA3) {
		this.avgObjA3 = avgObjA3;
	}

	public Double getObjA4() {
		return this.objA4;
	}

	public void setObjA4(Double objA4) {
		this.objA4 = objA4;
	}

	public Long getSumObjA7() {
		return this.sumObjA7;
	}

	public void setSumObjA7(Long sumObjA7) {
		this.sumObjA7 = sumObjA7;
	}

	public Long getSumObjA8() {
		return this.sumObjA8;
	}

	public void setSumObjA8(Long sumObjA8) {
		this.sumObjA8 = sumObjA8;
	}

	public Long getCObjA6() {
		return this.CObjA6;
	}

	public void setCObjA6(Long CObjA6) {
		this.CObjA6 = CObjA6;
	}

	public Long getCObjA7() {
		return this.CObjA7;
	}

	public void setCObjA7(Long CObjA7) {
		this.CObjA7 = CObjA7;
	}

	public Long getCObjA8() {
		return this.CObjA8;
	}

	public void setCObjA8(Long CObjA8) {
		this.CObjA8 = CObjA8;
	}

	public Double getCObjA2() {
		return this.CObjA2;
	}

	public void setCObjA2(Double CObjA2) {
		this.CObjA2 = CObjA2;
	}

	public Double getCObjA10() {
		return this.CObjA10;
	}

	public void setCObjA10(Double CObjA10) {
		this.CObjA10 = CObjA10;
	}

	public Double getCObjA11() {
		return this.CObjA11;
	}

	public void setCObjA11(Double CObjA11) {
		this.CObjA11 = CObjA11;
	}

	public Double getCObjA14() {
		return this.CObjA14;
	}

	public void setCObjA14(Double CObjA14) {
		this.CObjA14 = CObjA14;
	}

	public Double getCObjA15() {
		return this.CObjA15;
	}

	public void setCObjA15(Double CObjA15) {
		this.CObjA15 = CObjA15;
	}

	public Double getCObjA16() {
		return this.CObjA16;
	}

	public void setCObjA16(Double CObjA16) {
		this.CObjA16 = CObjA16;
	}

	public Double getCObjA17() {
		return this.CObjA17;
	}

	public void setCObjA17(Double CObjA17) {
		this.CObjA17 = CObjA17;
	}

	public Double getCObjA18() {
		return this.CObjA18;
	}

	public void setCObjA18(Double CObjA18) {
		this.CObjA18 = CObjA18;
	}

	public Double getCObjA19() {
		return this.CObjA19;
	}

	public void setCObjA19(Double CObjA19) {
		this.CObjA19 = CObjA19;
	}

	public Double getCObjA21() {
		return this.CObjA21;
	}

	public void setCObjA21(Double CObjA21) {
		this.CObjA21 = CObjA21;
	}

	public Double getCObjA22() {
		return this.CObjA22;
	}

	public void setCObjA22(Double CObjA22) {
		this.CObjA22 = CObjA22;
	}

	public Double getCObjA24() {
		return this.CObjA24;
	}

	public void setCObjA24(Double CObjA24) {
		this.CObjA24 = CObjA24;
	}

	public Double getCObjA25() {
		return this.CObjA25;
	}

	public void setCObjA25(Double CObjA25) {
		this.CObjA25 = CObjA25;
	}

	public Double getCObjA26() {
		return this.CObjA26;
	}

	public void setCObjA26(Double CObjA26) {
		this.CObjA26 = CObjA26;
	}

	public Double getCObjA27() {
		return this.CObjA27;
	}

	public void setCObjA27(Double CObjA27) {
		this.CObjA27 = CObjA27;
	}

	public Double getCObjA28() {
		return this.CObjA28;
	}

	public void setCObjA28(Double CObjA28) {
		this.CObjA28 = CObjA28;
	}

	public Double getCObjA29() {
		return this.CObjA29;
	}

	public void setCObjA29(Double CObjA29) {
		this.CObjA29 = CObjA29;
	}

}