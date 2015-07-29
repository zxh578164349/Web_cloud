package entity;

/**
 * KyVisabillmId entity. @author MyEclipse Persistence Tools
 */

public class KyVisabillmId implements java.io.Serializable {

	// Fields

	private String factNo;
	private String visaSort;
	private String billNo;

	// Constructors

	/** default constructor */
	public KyVisabillmId() {
	}

	/** full constructor */
	public KyVisabillmId(String factNo, String visaSort, String billNo) {
		this.factNo = factNo;
		this.visaSort = visaSort;
		this.billNo = billNo;
	}

	// Property accessors

	public String getFactNo() {
		return this.factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public String getVisaSort() {
		return this.visaSort;
	}

	public void setVisaSort(String visaSort) {
		this.visaSort = visaSort;
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
		if (!(other instanceof KyVisabillmId))
			return false;
		KyVisabillmId castOther = (KyVisabillmId) other;

		return ((this.getFactNo() == castOther.getFactNo()) || (this
				.getFactNo() != null && castOther.getFactNo() != null && this
				.getFactNo().equals(castOther.getFactNo())))
				&& ((this.getVisaSort() == castOther.getVisaSort()) || (this
						.getVisaSort() != null
						&& castOther.getVisaSort() != null && this
						.getVisaSort().equals(castOther.getVisaSort())))
				&& ((this.getBillNo() == castOther.getBillNo()) || (this
						.getBillNo() != null && castOther.getBillNo() != null && this
						.getBillNo().equals(castOther.getBillNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getFactNo() == null ? 0 : this.getFactNo().hashCode());
		result = 37 * result
				+ (getVisaSort() == null ? 0 : this.getVisaSort().hashCode());
		result = 37 * result
				+ (getBillNo() == null ? 0 : this.getBillNo().hashCode());
		return result;
	}

}