package entity;


/**
 * VWebobjBAllFactnoYear entity. @author MyEclipse Persistence Tools
 */

public class VWebobjBAllFactnoYear implements java.io.Serializable {

	// Fields

	private VWebobjBAllFactnoYearId id;
	private Long sumObjA7;
	private Long sumObjA8;
	private Long CObjA6;
	private Long CObjA7;
	private Long CObjA8;
	private Long months;

	// Constructors

	/** default constructor */
	public VWebobjBAllFactnoYear() {
	}

	/** minimal constructor */
	public VWebobjBAllFactnoYear(VWebobjBAllFactnoYearId id) {
		this.id = id;
	}

	/** full constructor */
	public VWebobjBAllFactnoYear(VWebobjBAllFactnoYearId id,
			Long sumObjA7, Long sumObjA8, Long CObjA6,
			Long CObjA7, Long CObjA8, Long months) {
		this.id = id;
		this.sumObjA7 = sumObjA7;
		this.sumObjA8 = sumObjA8;
		this.CObjA6 = CObjA6;
		this.CObjA7 = CObjA7;
		this.CObjA8 = CObjA8;
		this.months = months;
	}

	// Property accessors

	public VWebobjBAllFactnoYearId getId() {
		return this.id;
	}

	public void setId(VWebobjBAllFactnoYearId id) {
		this.id = id;
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

	public Long getMonths() {
		return this.months;
	}

	public void setMonths(Long months) {
		this.months = months;
	}

}