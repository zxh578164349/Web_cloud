package entity;

/**
 * WebPhonebook entity. @author MyEclipse Persistence Tools
 */

public class WebPhonebook implements java.io.Serializable {

	// Fields

	private WebPhonebookId id;
	private Long pbId;
	private String qq;
	private String weixin;
	private String link;
	private String creater;

	// Constructors

	/** default constructor */
	public WebPhonebook() {
	}

	/** minimal constructor */
	public WebPhonebook(WebPhonebookId id, Long pbId) {
		this.id = id;
		this.pbId = pbId;
	}

	/** full constructor */
	public WebPhonebook(WebPhonebookId id, Long pbId, String qq, String weixin,
			String link, String creater) {
		this.id = id;
		this.pbId = pbId;
		this.qq = qq;
		this.weixin = weixin;
		this.link = link;
		this.creater = creater;
	}

	// Property accessors

	public WebPhonebookId getId() {
		return this.id;
	}

	public void setId(WebPhonebookId id) {
		this.id = id;
	}

	public Long getPbId() {
		return this.pbId;
	}

	public void setPbId(Long pbId) {
		this.pbId = pbId;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeixin() {
		return this.weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getCreater() {
		return this.creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

}