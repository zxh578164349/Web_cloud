package entity;

/**
 * SubKyzmat entity. @author MyEclipse Persistence Tools
 */

public class SubKyzmat implements java.io.Serializable {

	// Fields

	private SubKyzmatId id;
	private String matCname;

	// Constructors

	/** default constructor */
	public SubKyzmat() {
	}

	/** minimal constructor */
	public SubKyzmat(SubKyzmatId id) {
		this.id = id;
	}

	/** full constructor */
	public SubKyzmat(SubKyzmatId id, String matCname) {
		this.id = id;
		this.matCname = matCname;
	}

	// Property accessors

	public SubKyzmatId getId() {
		return this.id;
	}

	public void setId(SubKyzmatId id) {
		this.id = id;
	}

	public String getMatCname() {
		return this.matCname;
	}

	public void setMatCname(String matCname) {
		this.matCname = matCname;
	}

}