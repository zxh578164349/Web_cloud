package entity;

/**
 * WebDistributionsub entity. @author MyEclipse Persistence Tools
 */

public class WebDistributionsub implements java.io.Serializable {

	// Fields

	private Integer usersubmenu;
	private Integer usersubmenuid;

	// Constructors

	/** default constructor */
	public WebDistributionsub() {
	}

	/** full constructor */
	public WebDistributionsub(Integer usersubmenu, Integer usersubmenuid) {
		this.usersubmenu = usersubmenu;
		this.usersubmenuid = usersubmenuid;
	}

	// Property accessors

	public Integer getUsersubmenu() {
		return this.usersubmenu;
	}

	public void setUsersubmenu(Integer usersubmenu) {
		this.usersubmenu = usersubmenu;
	}

	public Integer getUsersubmenuid() {
		return this.usersubmenuid;
	}

	public void setUsersubmenuid(Integer usersubmenuid) {
		this.usersubmenuid = usersubmenuid;
	}

}