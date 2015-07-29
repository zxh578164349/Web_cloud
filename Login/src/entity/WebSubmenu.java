package entity;

/**
 * WebSubmenu entity. @author MyEclipse Persistence Tools
 */

public class WebSubmenu implements java.io.Serializable {

	// Fields

	private Integer submenuid;
	private WebJurisdiction webJurisdiction;
	private String submenuname;
	private String address;
	private String subtype;

	// Constructors

	/** default constructor */
	public WebSubmenu() {
	}

	/** minimal constructor */
	public WebSubmenu(Integer submenuid, WebJurisdiction webJurisdiction) {
		this.submenuid = submenuid;
		this.webJurisdiction = webJurisdiction;
	}

	/** full constructor */
	public WebSubmenu(Integer submenuid, WebJurisdiction webJurisdiction,
			String submenuname, String address, String subtype) {
		this.submenuid = submenuid;
		this.webJurisdiction = webJurisdiction;
		this.submenuname = submenuname;
		this.address = address;
		this.subtype = subtype;
	}

	// Property accessors

	public Integer getSubmenuid() {
		return this.submenuid;
	}

	public void setSubmenuid(Integer submenuid) {
		this.submenuid = submenuid;
	}

	public WebJurisdiction getWebJurisdiction() {
		return this.webJurisdiction;
	}

	public void setWebJurisdiction(WebJurisdiction webJurisdiction) {
		this.webJurisdiction = webJurisdiction;
	}

	public String getSubmenuname() {
		return this.submenuname;
	}

	public void setSubmenuname(String submenuname) {
		this.submenuname = submenuname;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSubtype() {
		return this.subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

}