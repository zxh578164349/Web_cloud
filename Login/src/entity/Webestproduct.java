package entity;

/**
 * Webestproduct entity. @author MyEclipse Persistence Tools
 */

public class Webestproduct implements java.io.Serializable {

	// Fields

	private WebestproductId id;
	private Double hole;//有效孔位數(positiveNumber)
	private Double machinepower;//機台戰力(forceAnalysis)
	private Double estdays;
	private Double esteverymodel;//預計每日上模數(expectedProduction)
	private Double esteverypeople;
	private Double estmodel;
	private Double estnum;
	private Double estpay;//預計請款雙數(expectedPayment)
	private Double estmoney;
	private Double totalhole;//總機孔(hole)
	private Double sample;//工程樣品(sample)
	private Double accessories;//輔料(accessories)
	private Double other;//其它(other)
	private String username;

	// Constructors

	/** default constructor */
	public Webestproduct() {
	}
	/** minimal constructor */
	public Webestproduct(WebestproductId id){
		this.id=id;
	}

	/** full constructor */
	public Webestproduct(WebestproductId id, Double hole, Double machinepower,
			Double estdays, Double esteverymodel, Double esteverypeople,
			Double estmodel, Double estnum, Double estpay, Double estmoney,
			Double totalhole,Double sample,Double accessories,Double other,
			String username) {
		this.id = id;
		this.hole = hole;
		this.machinepower = machinepower;
		this.estdays = estdays;
		this.esteverymodel = esteverymodel;
		this.esteverypeople = esteverypeople;
		this.estmodel = estmodel;
		this.estnum = estnum;
		this.estpay = estpay;
		this.estmoney = estmoney;
		this.username = username;
		this.totalhole=totalhole;
		this.sample=sample;
		this.accessories=accessories;
		this.other=other;
	}

	// Property accessors

	public WebestproductId getId() {
		return this.id;
	}

	public void setId(WebestproductId id) {
		this.id = id;
	}

	public Double getHole() {
		return this.hole;
	}

	public void setHole(Double hole) {
		this.hole = hole;
	}

	public Double getMachinepower() {
		return this.machinepower;
	}

	public void setMachinepower(Double machinepower) {
		this.machinepower = machinepower;
	}

	public Double getEstdays() {
		return this.estdays;
	}

	public void setEstdays(Double estdays) {
		this.estdays = estdays;
	}

	public Double getEsteverymodel() {
		return this.esteverymodel;
	}

	public void setEsteverymodel(Double esteverymodel) {
		this.esteverymodel = esteverymodel;
	}

	public Double getEsteverypeople() {
		return this.esteverypeople;
	}

	public void setEsteverypeople(Double esteverypeople) {
		this.esteverypeople = esteverypeople;
	}

	public Double getEstmodel() {
		return this.estmodel;
	}

	public void setEstmodel(Double estmodel) {
		this.estmodel = estmodel;
	}

	public Double getEstnum() {
		return this.estnum;
	}

	public void setEstnum(Double estnum) {
		this.estnum = estnum;
	}

	public Double getEstpay() {
		return this.estpay;
	}

	public void setEstpay(Double estpay) {
		this.estpay = estpay;
	}

	public Double getEstmoney() {
		return this.estmoney;
	}

	public void setEstmoney(Double estmoney) {
		this.estmoney = estmoney;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Double getTotalhole() {
		return totalhole;
	}

	public void setTotalhole(Double totalhole) {
		this.totalhole = totalhole;
	}

	public Double getSample() {
		return sample;
	}

	public void setSample(Double sample) {
		this.sample = sample;
	}

	public Double getAccessories() {
		return accessories;
	}

	public void setAccessories(Double accessories) {
		this.accessories = accessories;
	}

	public Double getOther() {
		return other;
	}

	public void setOther(Double other) {
		this.other = other;
	}
	

}