package entity;

import java.sql.Timestamp;

/**
 * WebSubsort entity. @author MyEclipse Persistence Tools
 */

public class WebSubsort implements java.io.Serializable {

	// Fields

	private Integer subid;
	private WebMajorsort webMajorsort;
	private String subsortid;
	private String subname;
	private Integer unitsid;
	private String units;
	private Integer usefullife;
	private Double depreciationrate;
	private Timestamp addtime;

	// Constructors

	/** default constructor */
	public WebSubsort() {
	}

	/** minimal constructor */
	public WebSubsort(Integer subid) {
		this.subid = subid;
	}

	/** full constructor */
	public WebSubsort(Integer subid, WebMajorsort webMajorsort,
			String subsortid, String subname, Integer unitsid, String units,
			Integer usefullife, Double depreciationrate, Timestamp addtime) {
		this.subid = subid;
		this.webMajorsort = webMajorsort;
		this.subsortid = subsortid;
		this.subname = subname;
		this.unitsid = unitsid;
		this.units = units;
		this.usefullife = usefullife;
		this.depreciationrate = depreciationrate;
		this.addtime = addtime;
	}

	// Property accessors

	public Integer getSubid() {
		return this.subid;
	}

	public void setSubid(Integer subid) {
		this.subid = subid;
	}

	public WebMajorsort getWebMajorsort() {
		return this.webMajorsort;
	}

	public void setWebMajorsort(WebMajorsort webMajorsort) {
		this.webMajorsort = webMajorsort;
	}

	public String getSubsortid() {
		return this.subsortid;
	}

	public void setSubsortid(String subsortid) {
		this.subsortid = subsortid;
	}

	public String getSubname() {
		return this.subname;
	}

	public void setSubname(String subname) {
		this.subname = subname;
	}

	public Integer getUnitsid() {
		return this.unitsid;
	}

	public void setUnitsid(Integer unitsid) {
		this.unitsid = unitsid;
	}

	public String getUnits() {
		return this.units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public Integer getUsefullife() {
		return this.usefullife;
	}

	public void setUsefullife(Integer usefullife) {
		this.usefullife = usefullife;
	}

	public Double getDepreciationrate() {
		return this.depreciationrate;
	}

	public void setDepreciationrate(Double depreciationrate) {
		this.depreciationrate = depreciationrate;
	}

	public Timestamp getAddtime() {
		return this.addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}

}