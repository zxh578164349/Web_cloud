package entity;

/**
 * WebTabpomfile entity. @author MyEclipse Persistence Tools
 */

public class WebTabpomfile implements java.io.Serializable {

	// Fields

	private WebTabpomfileId id;

	// Constructors

	/** default constructor */
	public WebTabpomfile() {
	}

	/** full constructor */
	public WebTabpomfile(WebTabpomfileId id) {
		this.id = id;
	}

	// Property accessors

	public WebTabpomfileId getId() {
		return this.id;
	}

	public void setId(WebTabpomfileId id) {
		this.id = id;
	}

}