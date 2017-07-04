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
	private String enableMk;
	private List<WebJurisdiction> webJurisdictions = new ArrayList<WebJurisdiction>();
	private List<WebSubmenu2>submenus=new ArrayList<WebSubmenu2>();
	private String typeMk;//菜單類型     0:使用者可見    1:訪客可見

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

	public List<WebSubmenu2> getSubmenus() {
		return submenus;
	}

	public void setSubmenus(List<WebSubmenu2> submenus) {
		this.submenus = submenus;
	}

	public String getEnableMk() {
		return enableMk;
	}

	public void setEnableMk(String enableMk) {
		this.enableMk = enableMk;
	}

	public String getTypeMk(){
		return typeMk;
	}

	public void setTypeMk(String typeMk){
		this.typeMk=typeMk;
	}
	
	

}