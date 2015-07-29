package entity;

/**
 * KyzSecId entity. @author MyEclipse Persistence Tools
 */

public class KyzSecId implements java.io.Serializable {

	// Fields

	private String factNo;
	private String secNo;

	// Constructors

	/** default constructor */
	public KyzSecId() {
	}

	/** full constructor */
	public KyzSecId(String factNo, String secNo) {
		this.factNo = factNo;
		this.secNo = secNo;
	}

	// Property accessors

	public String getFactNo() {
		return this.factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public String getSecNo() {
		return this.secNo;
	}

	public void setSecNo(String secNo) {
		this.secNo = secNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof KyzSecId))
			return false;
		KyzSecId castOther = (KyzSecId) other;

		return ((this.getFactNo() == castOther.getFactNo()) || (this
				.getFactNo() != null && castOther.getFactNo() != null && this
				.getFactNo().equals(castOther.getFactNo())))
				&& ((this.getSecNo() == castOther.getSecNo()) || (this
						.getSecNo() != null && castOther.getSecNo() != null && this
						.getSecNo().equals(castOther.getSecNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getFactNo() == null ? 0 : this.getFactNo().hashCode());
		result = 37 * result
				+ (getSecNo() == null ? 0 : this.getSecNo().hashCode());
		return result;
	}

}