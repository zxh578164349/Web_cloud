package entity;

/**
 * Webbackfeed entity. @author MyEclipse Persistence Tools
 */

public class Webbackfeed implements java.io.Serializable {

	// Fields

	private WebbackfeedId id;
	private Double thickused;
	private Double backfeed;
	private Double oilback;
	private String username;

	// Constructors

	/** default constructor */
	public Webbackfeed() {
	}

	/** minimal constructor */
	public Webbackfeed(WebbackfeedId id) {
		this.id = id;
	}

	/** full constructor */
	public Webbackfeed(WebbackfeedId id, Double thickused, Double backfeed,
			Double oilback, String username) {
		this.id = id;
		this.thickused = thickused;
		this.backfeed = backfeed;
		this.oilback = oilback;
		this.username = username;
	}

	// Property accessors

	public WebbackfeedId getId() {
		return this.id;
	}

	public void setId(WebbackfeedId id) {
		this.id = id;
	}

	public Double getThickused() {
		return this.thickused;
	}

	public void setThickused(Double thickused) {
		this.thickused = thickused;
	}

	public Double getBackfeed() {
		return this.backfeed;
	}

	public void setBackfeed(Double backfeed) {
		this.backfeed = backfeed;
	}

	public Double getOilback() {
		return this.oilback;
	}

	public void setOilback(Double oilback) {
		this.oilback = oilback;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}