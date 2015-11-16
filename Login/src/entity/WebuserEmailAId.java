package entity;

/**
 * WebuserEmailAId entity. @author MyEclipse Persistence Tools
 */

public class WebuserEmailAId implements java.io.Serializable {

	// Fields

	private String factNo;
	private String email;
	private String emailpassword;
	private String visaSort;

	// Constructors

	/** default constructor */
	public WebuserEmailAId() {
	}

	/** full constructor */
	public WebuserEmailAId(String factNo, String email, String emailpassword,
			String visaSort) {
		this.factNo = factNo;
		this.email = email;
		this.emailpassword = emailpassword;
		this.visaSort = visaSort;
	}

	// Property accessors

	public String getFactNo() {
		return this.factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailpassword() {
		return this.emailpassword;
	}

	public void setEmailpassword(String emailpassword) {
		this.emailpassword = emailpassword;
	}

	public String getVisaSort() {
		return this.visaSort;
	}

	public void setVisaSort(String visaSort) {
		this.visaSort = visaSort;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WebuserEmailAId))
			return false;
		WebuserEmailAId castOther = (WebuserEmailAId) other;

		return ((this.getFactNo() == castOther.getFactNo()) || (this
				.getFactNo() != null && castOther.getFactNo() != null && this
				.getFactNo().equals(castOther.getFactNo())))
				&& ((this.getEmail() == castOther.getEmail()) || (this
						.getEmail() != null && castOther.getEmail() != null && this
						.getEmail().equals(castOther.getEmail())))
				&& ((this.getEmailpassword() == castOther.getEmailpassword()) || (this
						.getEmailpassword() != null
						&& castOther.getEmailpassword() != null && this
						.getEmailpassword()
						.equals(castOther.getEmailpassword())))
				&& ((this.getVisaSort() == castOther.getVisaSort()) || (this
						.getVisaSort() != null
						&& castOther.getVisaSort() != null && this
						.getVisaSort().equals(castOther.getVisaSort())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getFactNo() == null ? 0 : this.getFactNo().hashCode());
		result = 37 * result
				+ (getEmail() == null ? 0 : this.getEmail().hashCode());
		result = 37
				* result
				+ (getEmailpassword() == null ? 0 : this.getEmailpassword()
						.hashCode());
		result = 37 * result
				+ (getVisaSort() == null ? 0 : this.getVisaSort().hashCode());
		return result;
	}

}