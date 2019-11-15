package entity;

/**
 * VWebobjBObj5 entity. @author MyEclipse Persistence Tools
 */

public class VWebobjBObj5 implements java.io.Serializable {

	// Fields

	private VWebobjBObj5Id id;
	private Long obja5;
	private Long obja6;
	private Long objaE;
	private Long obja7;
	private Long obja8;
	private Long obja9;

	// Constructors

	/** default constructor */
	public VWebobjBObj5() {
	}

	/** minimal constructor */
	public VWebobjBObj5(VWebobjBObj5Id id) {
		this.id = id;
	}

	/** full constructor */
	public VWebobjBObj5(VWebobjBObj5Id id, Long obja5, Long obja6,
			Long objaE, Long obja7, Long obja8,
			Long obja9) {
		this.id = id;
		this.obja5 = obja5;
		this.obja6 = obja6;
		this.objaE = objaE;
		this.obja7 = obja7;
		this.obja8 = obja8;
		this.obja9 = obja9;
	}

	// Property accessors

	public VWebobjBObj5Id getId() {
		return this.id;
	}

	public void setId(VWebobjBObj5Id id) {
		this.id = id;
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

	public Long getObjaE() {
		return this.objaE;
	}

	public void setObjaE(Long objaE) {
		this.objaE = objaE;
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

}