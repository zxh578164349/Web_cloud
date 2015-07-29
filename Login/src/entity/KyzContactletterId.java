package entity;

/**
 * KyzContactletterId entity. @author MyEclipse Persistence Tools
 */

public class KyzContactletterId implements java.io.Serializable {

	// Fields

	private String factNo;
	private String billNo;

	// Constructors

	/** default constructor */
	public KyzContactletterId() {
	}

	/** full constructor */
	public KyzContactletterId(String factNo, String billNo) {
		this.factNo = factNo;
		this.billNo = billNo;
	}

	// Property accessors

	public String getFactNo() {
		return this.factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public String getBillNo() {
		return this.billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof KyzContactletterId))
			return false;
		KyzContactletterId castOther = (KyzContactletterId) other;

		return ((this.getFactNo() == castOther.getFactNo()) || (this
				.getFactNo() != null && castOther.getFactNo() != null && this
				.getFactNo().equals(castOther.getFactNo())))
				&& ((this.getBillNo() == castOther.getBillNo()) || (this
						.getBillNo() != null && castOther.getBillNo() != null && this
						.getBillNo().equals(castOther.getBillNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getFactNo() == null ? 0 : this.getFactNo().hashCode());
		result = 37 * result
				+ (getBillNo() == null ? 0 : this.getBillNo().hashCode());
		return result;
	}

}