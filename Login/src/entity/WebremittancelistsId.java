package entity;

/**
 * WebremittancelistsId entity. @author MyEclipse Persistence Tools
 */

public class WebremittancelistsId implements java.io.Serializable {

	// Fields

	private String itemNo;
	private Webremittancelist webremittancelist;

	// Constructors

	/** default constructor */
	public WebremittancelistsId() {
	}

	/** full constructor */
	public WebremittancelistsId(String itemNo,
			Webremittancelist webremittancelist) {
		this.itemNo = itemNo;
		this.webremittancelist = webremittancelist;
	}

	// Property accessors

	public String getItemNo() {
		return this.itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public Webremittancelist getWebremittancelist() {
		return this.webremittancelist;
	}

	public void setWebremittancelist(Webremittancelist webremittancelist) {
		this.webremittancelist = webremittancelist;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WebremittancelistsId))
			return false;
		WebremittancelistsId castOther = (WebremittancelistsId) other;

		return ((this.getItemNo() == castOther.getItemNo()) || (this
				.getItemNo() != null && castOther.getItemNo() != null && this
				.getItemNo().equals(castOther.getItemNo())))
				&& ((this.getWebremittancelist() == castOther
						.getWebremittancelist()) || (this
						.getWebremittancelist() != null
						&& castOther.getWebremittancelist() != null && this
						.getWebremittancelist().equals(
								castOther.getWebremittancelist())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getItemNo() == null ? 0 : this.getItemNo().hashCode());
		result = 37
				* result
				+ (getWebremittancelist() == null ? 0 : this
						.getWebremittancelist().hashCode());
		return result;
	}

}