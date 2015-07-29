package entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * WebMenu entity. @author MyEclipse Persistence Tools
 */

public class WebMenu implements java.io.Serializable {

	// Fields

	private Integer menuid;
	private String menuname;
	private List<WebJurisdiction> webJurisdictions = new ArrayList<WebJurisdiction>();

	// Constructors

	/** default constructor */
	public WebMenu() {
	}

	/** minimal constructor */
	public WebMenu(Integer menuid, String menuname) {
		this.menuid = menuid;
		this.menuname = menuname;
	}

	/** full constructor */
	public WebMenu(Integer menuid, String menuname, List<WebJurisdiction> webJurisdictions) {
		this.menuid = menuid;
		this.menuname = menuname;
		this.webJurisdictions = webJurisdictions;
	}

	// Property accessors

	public Integer getMenuid() {
		return this.menuid;
	}

	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}

	public String getMenuname() {
		return this.menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public List<WebJurisdiction> getWebJurisdictions() {
		return this.webJurisdictions;
	}

	public void setWebJurisdictions(List<WebJurisdiction> webJurisdictions) {
		this.webJurisdictions = webJurisdictions;
	}

}