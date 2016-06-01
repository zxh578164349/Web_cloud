package entity;

/**
 * WebVwebussortSubitem entity. @author MyEclipse Persistence Tools
 */

public class WebVwebussortSubitem implements java.io.Serializable {

	// Fields

	private Integer sid;
	private WebVwebbussortItemn webVwebbussortItemn;
	private String itemname;

	// Constructors

	/** default constructor */
	public WebVwebussortSubitem() {
	}

	/** minimal constructor */
	public WebVwebussortSubitem(Integer sid) {
		this.sid = sid;
	}

	/** full constructor */
	public WebVwebussortSubitem(Integer sid,
			WebVwebbussortItemn webVwebbussortItemn, String itemname) {
		this.sid = sid;
		this.webVwebbussortItemn = webVwebbussortItemn;
		this.itemname = itemname;
	}

	// Property accessors

	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public WebVwebbussortItemn getWebVwebbussortItemn() {
		return this.webVwebbussortItemn;
	}

	public void setWebVwebbussortItemn(WebVwebbussortItemn webVwebbussortItemn) {
		this.webVwebbussortItemn = webVwebbussortItemn;
	}

	public String getItemname() {
		return this.itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

}