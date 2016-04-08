package entity;

/**
 * WebydataNoinput entity. @author MyEclipse Persistence Tools
 */

public class WebydataNoinput implements java.io.Serializable {

	// Fields

	private WebydataNoinputId id;
	private String datecreate;

	// Constructors

	/** default constructor */
	public WebydataNoinput() {
	}

	/** minimal constructor */
	public WebydataNoinput(WebydataNoinputId id) {
		this.id = id;
	}

	/** full constructor */
	public WebydataNoinput(WebydataNoinputId id, String datecreate) {
		this.id = id;
		this.datecreate = datecreate;
	}

	// Property accessors

	public WebydataNoinputId getId() {
		return this.id;
	}

	public void setId(WebydataNoinputId id) {
		this.id = id;
	}

	public String getDatecreate() {
		return this.datecreate;
	}

	public void setDatecreate(String datecreate) {
		this.datecreate = datecreate;
	}

}