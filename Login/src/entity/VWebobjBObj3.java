package entity;

/**
 * VWebobjBObj3 entity. @author MyEclipse Persistence Tools
 */

public class VWebobjBObj3 implements java.io.Serializable {

	// Fields

	private VWebobjBObj3Id id;
	private Long objA5;
	private Long objA6;
	private Long objAe;
	private Long objA7;
	private Long objA8;
	private Long objA9;
	private String workorholiday;

	// Constructors

	/** default constructor */
	public VWebobjBObj3() {
	}

	/** minimal constructor */
	public VWebobjBObj3(VWebobjBObj3Id id) {
		this.id = id;
	}

	/** full constructor */
	public VWebobjBObj3(VWebobjBObj3Id id, Long objA5, Long objA6,
			Long objAe, Long objA7, Long objA8,
			Long objA9) {
		this.id = id;
		this.objA5 = objA5;
		this.objA6 = objA6;
		this.objAe = objAe;
		this.objA7 = objA7;
		this.objA8 = objA8;
		this.objA9 = objA9;
	}

	// Property accessors

	public VWebobjBObj3Id getId() {
		return this.id;
	}

	public void setId(VWebobjBObj3Id id) {
		this.id = id;
	}

	public Long getObjA5() {
		return this.objA5;
	}

	public void setObjA5(Long objA5) {
		this.objA5 = objA5;
	}

	public Long getObjA6() {
		return this.objA6;
	}

	public void setObjA6(Long objA6) {
		this.objA6 = objA6;
	}

	public Long getObjAe() {
		return this.objAe;
	}

	public void setObjAe(Long objAe) {
		this.objAe = objAe;
	}

	public Long getObjA7() {
		return this.objA7;
	}

	public void setObjA7(Long objA7) {
		this.objA7 = objA7;
	}

	public Long getObjA8() {
		return this.objA8;
	}

	public void setObjA8(Long objA8) {
		this.objA8 = objA8;
	}

	public Long getObjA9() {
		return this.objA9;
	}

	public void setObjA9(Long objA9) {
		this.objA9 = objA9;
	}

	public String getWorkorholiday() {
		return workorholiday;
	}

	public void setWorkorholiday(String workorholiday) {
		this.workorholiday = workorholiday;
	}
	

}