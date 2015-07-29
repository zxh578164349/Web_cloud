package entity;

/**
 * SubKyzmatId entity. @author MyEclipse Persistence Tools
 */

public class SubKyzmatId implements java.io.Serializable {

	// Fields

	private String username;
	private String factNo;
	private KyzMat kyzMat;

	// Constructors

	/** default constructor */
	public SubKyzmatId() {
	}

	/** full constructor */
	public SubKyzmatId(String username, String factNo, KyzMat kyzMat) {
		this.username = username;
		this.factNo = factNo;
		this.kyzMat = kyzMat;
	}

	// Property accessors

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFactNo() {
		return this.factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public KyzMat getKyzMat() {
		return this.kyzMat;
	}

	public void setKyzMat(KyzMat kyzMat) {
		this.kyzMat = kyzMat;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SubKyzmatId))
			return false;
		SubKyzmatId castOther = (SubKyzmatId) other;

		return ((this.getUsername() == castOther.getUsername()) || (this
				.getUsername() != null && castOther.getUsername() != null && this
				.getUsername().equals(castOther.getUsername())))
				&& ((this.getFactNo() == castOther.getFactNo()) || (this
						.getFactNo() != null && castOther.getFactNo() != null && this
						.getFactNo().equals(castOther.getFactNo())))
				&& ((this.getKyzMat() == castOther.getKyzMat()) || (this
						.getKyzMat() != null && castOther.getKyzMat() != null && this
						.getKyzMat().equals(castOther.getKyzMat())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUsername() == null ? 0 : this.getUsername().hashCode());
		result = 37 * result
				+ (getFactNo() == null ? 0 : this.getFactNo().hashCode());
		result = 37 * result
				+ (getKyzMat() == null ? 0 : this.getKyzMat().hashCode());
		return result;
	}

}