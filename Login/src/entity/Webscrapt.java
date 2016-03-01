package entity;

/**
 * Webscrapt entity. @author MyEclipse Persistence Tools
 */

public class Webscrapt implements java.io.Serializable {

	// Fields

	private WebscraptId id;
	private Double sideweit;
	private Double badcount;
	private Double badweit;
	private Double otherbadweight;
	private String username;
	private String usernameUd;

	// Constructors

	/** default constructor */
	public Webscrapt() {
	}

	/** minimal constructor */
	public Webscrapt(WebscraptId id) {
		this.id = id;
	}

	/** full constructor */
	public Webscrapt(WebscraptId id, Double sideweit, Double badcount,
			Double badweit, Double otherbadweight, String username) {
		this.id = id;
		this.sideweit = sideweit;
		this.badcount = badcount;
		this.badweit = badweit;
		this.otherbadweight = otherbadweight;
		this.username = username;
	}

	// Property accessors

	public WebscraptId getId() {
		return this.id;
	}

	public void setId(WebscraptId id) {
		this.id = id;
	}

	public Double getSideweit() {
		return this.sideweit;
	}

	public void setSideweit(Double sideweit) {
		this.sideweit = sideweit;
	}

	public Double getBadcount() {
		return this.badcount;
	}

	public void setBadcount(Double badcount) {
		this.badcount = badcount;
	}

	public Double getBadweit() {
		return this.badweit;
	}

	public void setBadweit(Double badweit) {
		this.badweit = badweit;
	}

	public Double getOtherbadweight() {
		return this.otherbadweight;
	}

	public void setOtherbadweight(Double otherbadweight) {
		this.otherbadweight = otherbadweight;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsernameUd() {
		return usernameUd;
	}

	public void setUsernameUd(String usernameUd) {
		this.usernameUd = usernameUd;
	}
	

}