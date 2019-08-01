package entity;

/**
 * VWeballobjbStorage entity. @author MyEclipse Persistence Tools
 */

public class VWeballobjbStorage implements java.io.Serializable {

	// Fields

	private VWeballobjbStorageId id;
	private Double objA42;
	private Double objA43;
	private VWebFact factNo2;//關聯工廠名稱(不需要映射列名)

	// Constructors

	/** default constructor */
	public VWeballobjbStorage() {
	}

	/** minimal constructor */
	public VWeballobjbStorage(VWeballobjbStorageId id) {
		this.id = id;
	}

	/** full constructor */
	public VWeballobjbStorage(VWeballobjbStorageId id, Double objA42,
			Double objA43) {
		this.id = id;
		this.objA42 = objA42;
		this.objA43 = objA43;
	}
	
	public VWeballobjbStorage(VWeballobjbStorageId id,VWebFact factNo2) {
		this.id = id;
		this.factNo2=factNo2;
	}
	

	// Property accessors

	public VWeballobjbStorageId getId() {
		return this.id;
	}

	public void setId(VWeballobjbStorageId id) {
		this.id = id;
	}

	public Double getObjA42() {
		return this.objA42;
	}

	public void setObjA42(Double objA42) {
		this.objA42 = objA42;
	}

	public Double getObjA43() {
		return this.objA43;
	}

	public void setObjA43(Double objA43) {
		this.objA43 = objA43;
	}

	public VWebFact getFactNo2() {
		return factNo2;
	}

	public void setFactNo2(VWebFact factNo2) {
		this.factNo2 = factNo2;
	}
	

}