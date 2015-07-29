package entity;

/**
 * Webmix1 entity. @author MyEclipse Persistence Tools
 */

public class Webmix1 implements java.io.Serializable {

	// Fields

	private Webmix1Id id;
	private Double workday;
	private Double everypeople;
	private Double everydemo;
	private Double standarddemo;
	private Double actualdemo;
	private Double actualpairs;
	private Double hostpairs;
	private Double factpairs;
	private Double samplepairs;
	private Double outnum;
	private Double backnum;
	private String username;

	// Constructors

	/** default constructor */
	public Webmix1() {
	}

	/** minimal constructor */
	public Webmix1(Webmix1Id id) {
		this.id = id;
	}

	/** full constructor */
	public Webmix1(Webmix1Id id, Double workday, Double everypeople,
			Double everydemo, Double standarddemo, Double actualdemo,
			Double actualpairs, Double hostpairs, Double factpairs,
			Double samplepairs, Double outnum, Double backnum, String username) {
		this.id = id;
		this.workday = workday;
		this.everypeople = everypeople;
		this.everydemo = everydemo;
		this.standarddemo = standarddemo;
		this.actualdemo = actualdemo;
		this.actualpairs = actualpairs;
		this.hostpairs = hostpairs;
		this.factpairs = factpairs;
		this.samplepairs = samplepairs;
		this.outnum = outnum;
		this.backnum = backnum;
		this.username = username;
	}

	// Property accessors

	public Webmix1Id getId() {
		return this.id;
	}

	public void setId(Webmix1Id id) {
		this.id = id;
	}

	public Double getWorkday() {
		return this.workday;
	}

	public void setWorkday(Double workday) {
		this.workday = workday;
	}

	public Double getEverypeople() {
		return this.everypeople;
	}

	public void setEverypeople(Double everypeople) {
		this.everypeople = everypeople;
	}

	public Double getEverydemo() {
		return this.everydemo;
	}

	public void setEverydemo(Double everydemo) {
		this.everydemo = everydemo;
	}

	public Double getStandarddemo() {
		return this.standarddemo;
	}

	public void setStandarddemo(Double standarddemo) {
		this.standarddemo = standarddemo;
	}

	public Double getActualdemo() {
		return this.actualdemo;
	}

	public void setActualdemo(Double actualdemo) {
		this.actualdemo = actualdemo;
	}

	public Double getActualpairs() {
		return this.actualpairs;
	}

	public void setActualpairs(Double actualpairs) {
		this.actualpairs = actualpairs;
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

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}