package entity;

import java.util.List;

/**
 * WebSubmenu2 entity. @author MyEclipse Persistence Tools
 */

public class WebSubmenu2 implements java.io.Serializable {

	// Fields

	private Integer submenuid;
	private WebMenu menu;
	private String submenuname;
	private String address;
	private String subtype;
	private List<WebSubmenu>list;

	// Constructors

	/** default constructor */
	public WebSubmenu2() {
	}
	public WebSubmenu2(Integer submenuid){
		this.submenuid=submenuid;
	}

	/** minimal constructor */
	public WebSubmenu2(Integer submenuid, WebMenu menu) {
		this.submenuid = submenuid;
		this.menu = menu;
	}

	/** full constructor */
	public WebSubmenu2(Integer submenuid, WebMenu menu, String submenuname,
			String address, String subtype) {
		this.submenuid = submenuid;
		this.menu = menu;
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

	

	public WebMenu getMenu() {
		return menu;
	}

	public void setMenu(WebMenu menu) {
		this.menu = menu;
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
	public List<WebSubmenu> getList() {
		return list;
	}
	public void setList(List<WebSubmenu> list) {
		this.list = list;
	}
	

}