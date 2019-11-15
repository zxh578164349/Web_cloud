package entity;


/**
 * VWebobjBObj entity. @author MyEclipse Persistence Tools
 */

public class VWebobjBObj implements java.io.Serializable {

	// Fields

	private VWebobjBObjId id;
	private String workorholiday;
	private Double totalhole;
	private Double machinepower;
	private Double hole;
	private Double sample;
	private Double accessories;
	private Double other;
	private Double estmodel;
	private Double estnum;
	private Double estpay;
	private Double onmodulus;
	private Double personnum;
	private Double standardoutput;
	private Double actualyield;
	private Double objaA;
	private Double objaB;
	private Double objaC;
	private Double obja1;
	private Double objaD;
	private Double obja2;
	private Double obja3;
	private Double obja4;
	private Long obja5;
	private Long obja6;
	private Long objaE;
	private Long obja7;
	private Long obja8;
	private Long obja9;
	private String obja10;
	private String obja11;
	private String obja12;
	private String obja13;
	private String obja14;
	private Double daycount;//上班天數

	// Constructors

	/** default constructor */
	public VWebobjBObj() {
	}

	/** minimal constructor */
	public VWebobjBObj(VWebobjBObjId id) {
		this.id = id;
	}

	/** full constructor */
	public VWebobjBObj(VWebobjBObjId id, String workorholiday,
			Double totalhole, Double machinepower, Double hole, Double sample,
			Double accessories, Double other, Double estmodel, Double estnum,
			Double estpay, Double onmodulus, Double personnum,
			Double standardoutput, Double actualyield, Double objaA,
			Double objaB, Double objaC, Double obja1, Double objaD,
			Double obja2, Double obja3, Double obja4, Long obja5, Long obja6,
			Long objaE, Long obja7, Long obja8, Long obja9,
			String obja10, String obja11, String obja12, String obja13,
			String obja14) {
		this.id = id;
		this.workorholiday = workorholiday;
		this.totalhole = totalhole;
		this.machinepower = machinepower;
		this.hole = hole;
		this.sample = sample;
		this.accessories = accessories;
		this.other = other;
		this.estmodel = estmodel;
		this.estnum = estnum;
		this.estpay = estpay;
		this.onmodulus = onmodulus;
		this.personnum = personnum;
		this.standardoutput = standardoutput;
		this.actualyield = actualyield;
		this.objaA = objaA;
		this.objaB = objaB;
		this.objaC = objaC;
		this.obja1 = obja1;
		this.objaD = objaD;
		this.obja2 = obja2;
		this.obja3 = obja3;
		this.obja4 = obja4;
		this.obja5 = obja5;
		this.obja6 = obja6;
		this.objaE = objaE;
		this.obja7 = obja7;
		this.obja8 = obja8;
		this.obja9 = obja9;
		this.obja10 = obja10;
		this.obja11 = obja11;
		this.obja12 = obja12;
		this.obja13 = obja13;
		this.obja14 = obja14;
	}

	// Property accessors

	public VWebobjBObjId getId() {
		return this.id;
	}

	public void setId(VWebobjBObjId id) {
		this.id = id;
	}

	public String getWorkorholiday() {
		return this.workorholiday;
	}

	public void setWorkorholiday(String workorholiday) {
		this.workorholiday = workorholiday;
	}

	

	public Double getPersonnum() {
		return this.personnum;
	}

	public void setPersonnum(Double personnum) {
		this.personnum = personnum;
	}

	public Double getStandardoutput() {
		return this.standardoutput;
	}

	public void setStandardoutput(Double standardoutput) {
		this.standardoutput = standardoutput;
	}

	public Double getActualyield() {
		return this.actualyield;
	}

	public void setActualyield(Double actualyield) {
		this.actualyield = actualyield;
	}

	

	public Double getObja1() {
		return this.obja1;
	}

	public void setObja1(Double obja1) {
		this.obja1 = obja1;
	}

	

	public Double getObja2() {
		return this.obja2;
	}

	public void setObja2(Double obja2) {
		this.obja2 = obja2;
	}

	public Double getObja3() {
		return this.obja3;
	}

	public void setObja3(Double obja3) {
		this.obja3 = obja3;
	}

	public Double getObja4() {
		return this.obja4;
	}

	public void setObja4(Double obja4) {
		this.obja4 = obja4;
	}

	public Long getObja5() {
		return this.obja5;
	}

	public void setObja5(Long obja5) {
		this.obja5 = obja5;
	}

	public Long getObja6() {
		return this.obja6;
	}

	public void setObja6(Long obja6) {
		this.obja6 = obja6;
	}


	public Long getObja7() {
		return this.obja7;
	}

	public void setObja7(Long obja7) {
		this.obja7 = obja7;
	}

	public Long getObja8() {
		return this.obja8;
	}

	public void setObja8(Long obja8) {
		this.obja8 = obja8;
	}

	public Long getObja9() {
		return this.obja9;
	}

	public void setObja9(Long obja9) {
		this.obja9 = obja9;
	}

	public String getObja10() {
		return this.obja10;
	}

	public void setObja10(String obja10) {
		this.obja10 = obja10;
	}

	public String getObja11() {
		return this.obja11;
	}

	public void setObja11(String obja11) {
		this.obja11 = obja11;
	}

	public String getObja12() {
		return this.obja12;
	}

	public void setObja12(String obja12) {
		this.obja12 = obja12;
	}

	public String getObja13() {
		return this.obja13;
	}

	public void setObja13(String obja13) {
		this.obja13 = obja13;
	}

	public String getObja14() {
		return this.obja14;
	}

	public void setObja14(String obja14) {
		this.obja14 = obja14;
	}

	public Double getTotalhole() {
		return totalhole;
	}

	public void setTotalhole(Double totalhole) {
		this.totalhole = totalhole;
	}

	public Double getMachinepower() {
		return machinepower;
	}

	public void setMachinepower(Double machinepower) {
		this.machinepower = machinepower;
	}

	public Double getHole() {
		return hole;
	}

	public void setHole(Double hole) {
		this.hole = hole;
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

	public Double getEstmodel() {
		return estmodel;
	}

	public void setEstmodel(Double estmodel) {
		this.estmodel = estmodel;
	}

	public Double getEstnum() {
		return estnum;
	}

	public void setEstnum(Double estnum) {
		this.estnum = estnum;
	}

	public Double getEstpay() {
		return estpay;
	}

	public void setEstpay(Double estpay) {
		this.estpay = estpay;
	}

	public Double getOnmodulus() {
		return onmodulus;
	}

	public void setOnmodulus(Double onmodulus) {
		this.onmodulus = onmodulus;
	}

	public Double getObjaA() {
		return objaA;
	}

	public void setObjaA(Double objaA) {
		this.objaA = objaA;
	}

	public Double getObjaB() {
		return objaB;
	}

	public void setObjaB(Double objaB) {
		this.objaB = objaB;
	}

	public Double getObjaC() {
		return objaC;
	}

	public void setObjaC(Double objaC) {
		this.objaC = objaC;
	}

	public Double getObjaD() {
		return objaD;
	}

	public void setObjaD(Double objaD) {
		this.objaD = objaD;
	}

	public Long getObjaE() {
		return objaE;
	}

	public void setObjaE(Long objaE) {
		this.objaE = objaE;
	}

	public Double getDaycount() {
		return daycount;
	}

	public void setDaycount(Double daycount) {
		this.daycount = daycount;
	}
	
	
	
	

}