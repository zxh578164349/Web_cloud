package entity;

/**
 * WebFormtype entity. @author MyEclipse Persistence Tools
 */

public class WebFormtype implements java.io.Serializable {

	// Fields

	private Integer fid;
	private WebType webType;
	private String fname;

	// Constructors

	/** default constructor */
	public WebFormtype() {
	}

	/** minimal constructor */
	public WebFormtype(Integer fid) {
		this.fid = fid;
	}

	/** full constructor */
	public WebFormtype(Integer fid, WebType webType, String fname) {
		this.fid = fid;
		this.webType = webType;
		this.fname = fname;
	}

	// Property accessors

	public Integer getFid() {
		return this.fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public WebType getWebType() {
		return this.webType;
	}

	public void setWebType(WebType webType) {
		this.webType = webType;
	}

	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

}