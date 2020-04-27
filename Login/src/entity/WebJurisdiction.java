package entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * WebJurisdiction entity. @author MyEclipse Persistence Tools
 */

/**
 * 
* 項目名稱：WebLogin   
* 類名稱：WebJurisdiction   
* 類描述：權限數據表
* 創建人：KY2
 */
public class WebJurisdiction implements java.io.Serializable {

	// Fields

	private Integer jurisdictionid;
	private WebMenu webMenu;
	private WebUser webUser;
	private List<WebSubmenu> webSubmenus = new ArrayList<WebSubmenu>();

	// Constructors

	/** default constructor */
	public WebJurisdiction() {
	}

	/** minimal constructor */
	public WebJurisdiction(Integer jurisdictionid, WebMenu webMenu,
			WebUser webUser) {
		this.jurisdictionid = jurisdictionid;
		this.webMenu = webMenu;
		this.webUser = webUser;
	}

	/** full constructor */
	public WebJurisdiction(Integer jurisdictionid, WebMenu webMenu,
			WebUser webUser, List<WebSubmenu> webSubmenus) {
		this.jurisdictionid = jurisdictionid;
		this.webMenu = webMenu;
		this.webUser = webUser;
		this.webSubmenus = webSubmenus;
	}

	// Property accessors

	public Integer getJurisdictionid() {
		return this.jurisdictionid;
	}

	public void setJurisdictionid(Integer jurisdictionid) {
		this.jurisdictionid = jurisdictionid;
	}

	public WebMenu getWebMenu() {
		return this.webMenu;
	}

	public void setWebMenu(WebMenu webMenu) {
		this.webMenu = webMenu;
	}

	public WebUser getWebUser() {
		return this.webUser;
	}

	public void setWebUser(WebUser webUser) {
		this.webUser = webUser;
	}

	public List<WebSubmenu> getWebSubmenus() {
		return this.webSubmenus;
	}

	public void setWebSubmenus(List<WebSubmenu> webSubmenus) {
		this.webSubmenus = webSubmenus;
	}

}