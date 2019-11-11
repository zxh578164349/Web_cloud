package entity;

import java.math.BigDecimal;

/**
 * VWebobjBYdate entity. @author MyEclipse Persistence Tools
 */

public class VWebobjBYdate implements java.io.Serializable {

	// Fields

	private VWebobjBYdateId id;
	private Double onModulus;
	private Double personnum;
	private Double standardOutput;
	private Double actualYield;
	private Double zpObja;
	private Double hostpairs;
	private Double factpairs;
	private Double samplepairs;
	private Double outnum;
	private Double backnum;
	private Double workhours;
	private Double daycount;
	private String workorholiday;
	private Double actualpairs;
	private Double achievingRate;

	// Constructors

	/** default constructor */
	public VWebobjBYdate() {
	}

	/** minimal constructor */
	public VWebobjBYdate(VWebobjBYdateId id) {
		this.id = id;
	}

	/** full constructor */
	public VWebobjBYdate(VWebobjBYdateId id, Double onModulus,
			Double personnum, Double standardOutput, Double actualYield,
			Double zpObja, Double hostpairs, Double factpairs,
			Double samplepairs, Double outnum, Double backnum,
			Double workhours, Double daycount, String workorholiday,
			Double actualpairs, Double achievingRate) {
		this.id = id;
		this.onModulus = onModulus;
		this.personnum = personnum;
		this.standardOutput = standardOutput;
		this.actualYield = actualYield;
		this.zpObja = zpObja;
		this.hostpairs = hostpairs;
		this.factpairs = factpairs;
		this.samplepairs = samplepairs;
		this.outnum = outnum;
		this.backnum = backnum;
		this.workhours = workhours;
		this.daycount = daycount;
		this.workorholiday = workorholiday;
		this.actualpairs = actualpairs;
		this.achievingRate = achievingRate;
	}

	// Property accessors
	
	public VWebobjBYdate(Double onModulus,Double personnum,Double standardOutput, Double actualYield,Double daycount){
		this.onModulus = onModulus;
		this.personnum = personnum;
		this.standardOutput = standardOutput;
		this.actualYield = actualYield;
		this.daycount=daycount;
	}

	public VWebobjBYdateId getId() {
		return this.id;
	}

	public void setId(VWebobjBYdateId id) {
		this.id = id;
	}

	public Double getOnModulus() {
		return this.onModulus;
	}

	public void setOnModulus(Double onModulus) {
		this.onModulus = onModulus;
	}

	public Double getPersonnum() {
		return this.personnum;
	}

	public void setPersonnum(Double personnum) {
		this.personnum = personnum;
	}

	public Double getStandardOutput() {
		return this.standardOutput;
	}

	public void setStandardOutput(Double standardOutput) {
		this.standardOutput = standardOutput;
	}

	public Double getActualYield() {
		return this.actualYield;
	}

	public void setActualYield(Double actualYield) {
		this.actualYield = actualYield;
	}

	public Double getZpObja() {
		return this.zpObja;
	}

	public void setZpObja(Double zpObja) {
		this.zpObja = zpObja;
	}

	public Double getHostpairs() {
		return this.hostpairs;
	}

	public void setHostpairs(Double hostpairs) {
		this.hostpairs = hostpairs;
	}

	public Double getFactpairs() {
		return this.factpairs;
	}

	public void setFactpairs(Double factpairs) {
		this.factpairs = factpairs;
	}

	public Double getSamplepairs() {
		return this.samplepairs;
	}

	public void setSamplepairs(Double samplepairs) {
		this.samplepairs = samplepairs;
	}

	public Double getOutnum() {
		return this.outnum;
	}

	public void setOutnum(Double outnum) {
		this.outnum = outnum;
	}

	public Double getBacknum() {
		return this.backnum;
	}

	public void setBacknum(Double backnum) {
		this.backnum = backnum;
	}

	public Double getWorkhours() {
		return this.workhours;
	}

	public void setWorkhours(Double workhours) {
		this.workhours = workhours;
	}

	public Double getDaycount() {
		return this.daycount;
	}

	public void setDaycount(Double daycount) {
		this.daycount = daycount;
	}

	public String getWorkorholiday() {
		return this.workorholiday;
	}

	public void setWorkorholiday(String workorholiday) {
		this.workorholiday = workorholiday;
	}

	public Double getActualpairs() {
		return actualpairs;
	}

	public void setActualpairs(Double actualpairs) {
		this.actualpairs = actualpairs;
	}

	public Double getAchievingRate() {
		return achievingRate;
	}

	public void setAchievingRate(Double achievingRate) {
		this.achievingRate = achievingRate;
	}

	

}