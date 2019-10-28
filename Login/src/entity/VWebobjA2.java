package entity;

/**
 * VWebobjA2 entity. @author MyEclipse Persistence Tools
 */

public class VWebobjA2 implements java.io.Serializable {

	// Fields

	private VWebobjA2Id id;
	private Integer objA12;
	private Integer objA13;
	private Integer objA14;
	private Integer objA15;
	private Integer objA16;

	// Constructors

	/** default constructor */
	public VWebobjA2() {
	}

	/** minimal constructor */
	public VWebobjA2(VWebobjA2Id id) {
		this.id = id;
	}

	/** full constructor */
	public VWebobjA2(VWebobjA2Id id, Integer objA12, Integer objA13,
			Integer objA14, Integer objA15, Integer objA16) {
		this.id = id;
		this.objA12 = objA12;
		this.objA13 = objA13;
		this.objA14 = objA14;
		this.objA15 = objA15;
		this.objA16 = objA16;
	}

	// Property accessors

	public VWebobjA2Id getId() {
		return this.id;
	}

	public void setId(VWebobjA2Id id) {
		this.id = id;
	}

	public Integer getObjA12() {
		return this.objA12;
	}

	public void setObjA12(Integer objA12) {
		this.objA12 = objA12;
	}

	public Integer getObjA13() {
		return this.objA13;
	}

	public void setObjA13(Integer objA13) {
		this.objA13 = objA13;
	}

	public Integer getObjA14() {
		return this.objA14;
	}

	public void setObjA14(Integer objA14) {
		this.objA14 = objA14;
	}

	public Integer getObjA15() {
		return this.objA15;
	}

	public void setObjA15(Integer objA15) {
		this.objA15 = objA15;
	}

	public Integer getObjA16() {
		return this.objA16;
	}

	public void setObjA16(Integer objA16) {
		this.objA16 = objA16;
	}

}