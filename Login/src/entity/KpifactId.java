package entity;

/**
 * KpifactId entity. @author MyEclipse Persistence Tools
 */

public class KpifactId implements java.io.Serializable {

	// Fields

	private String factNo;
	private String factCode;
	private String yyyy;

	// Constructors

	/** default constructor */
	public KpifactId() {
	}

	/** full constructor */
	public KpifactId(String factNo, String factCode, String yyyy) {
		this.factNo = factNo;
		this.factCode = factCode;
		this.yyyy = yyyy;
	}

	// Property accessors

	public String getFactNo() {
		return this.factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public String getFactCode() {
		return this.factCode;
	}

	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}

	public String getYyyy() {
		return this.yyyy;
	}

	public void setYyyy(String yyyy) {
		this.yyyy = yyyy;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof KpifactId))
			return false;
		KpifactId castOther = (KpifactId) other;

		return ((this.getFactNo() == castOther.getFactNo()) || (this
				.getFactNo() != null && castOther.getFactNo() != null && this
				.getFactNo().equals(castOther.getFactNo())))
				&& ((this.getFactCode() == castOther.getFactCode()) || (this
						.getFactCode() != null
						&& castOther.getFactCode() != null && this
						.getFactCode().equals(castOther.getFactCode())))
				&& ((this.getYyyy() == castOther.getYyyy()) || (this.getYyyy() != null
						&& castOther.getYyyy() != null && this.getYyyy()
						.equals(castOther.getYyyy())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getFactNo() == null ? 0 : this.getFactNo().hashCode());
		result = 37 * result
				+ (getFactCode() == null ? 0 : this.getFactCode().hashCode());
		result = 37 * result
				+ (getYyyy() == null ? 0 : this.getYyyy().hashCode());
		return result;
	}

}