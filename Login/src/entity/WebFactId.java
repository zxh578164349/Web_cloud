package entity;

/**
 * WebFactId entity. @author MyEclipse Persistence Tools
 */

public class WebFactId implements java.io.Serializable {

	// Fields

	private String factNo;
	private String factArea;

	// Constructors

	/** default constructor */
	public WebFactId() {
	}

	/** full constructor */
	public WebFactId(String factNo, String factArea) {
		this.factNo = factNo;
		this.factArea = factArea;
	}

	// Property accessors

	public String getFactNo() {
		return this.factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public String getFactArea() {
		return this.factArea;
	}

	public void setFactArea(String factArea) {
		this.factArea = factArea;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WebFactId))
			return false;
		WebFactId castOther = (WebFactId) other;

		return ((this.getFactNo() == castOther.getFactNo()) || (this
				.getFactNo() != null && castOther.getFactNo() != null && this
				.getFactNo().equals(castOther.getFactNo())))
				&& ((this.getFactArea() == castOther.getFactArea()) || (this
						.getFactArea() != null
						&& castOther.getFactArea() != null && this
						.getFactArea().equals(castOther.getFactArea())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getFactNo() == null ? 0 : this.getFactNo().hashCode());
		result = 37 * result
				+ (getFactArea() == null ? 0 : this.getFactArea().hashCode());
		return result;
	}

}