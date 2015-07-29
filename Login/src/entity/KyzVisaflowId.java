package entity;

/**
 * KyzVisaflowId entity. @author MyEclipse Persistence Tools
 */

public class KyzVisaflowId implements java.io.Serializable {

	// Fields

	private String factNo;
	private String visaSort;
	private String purmanNo;
	private String itemNo;

	// Constructors

	/** default constructor */
	public KyzVisaflowId() {
	}

	/** full constructor */
	public KyzVisaflowId(String factNo, String visaSort, String purmanNo,
			String itemNo) {
		this.factNo = factNo;
		this.visaSort = visaSort;
		this.purmanNo = purmanNo;
		this.itemNo = itemNo;
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

	public String getPurmanNo() {
		return this.purmanNo;
	}

	public void setPurmanNo(String purmanNo) {
		this.purmanNo = purmanNo;
	}

	public String getItemNo() {
		return this.itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof KyzVisaflowId))
			return false;
		KyzVisaflowId castOther = (KyzVisaflowId) other;

		return ((this.getFactNo() == castOther.getFactNo()) || (this
				.getFactNo() != null && castOther.getFactNo() != null && this
				.getFactNo().equals(castOther.getFactNo())))
				&& ((this.getVisaSort() == castOther.getVisaSort()) || (this
						.getVisaSort() != null
						&& castOther.getVisaSort() != null && this
						.getVisaSort().equals(castOther.getVisaSort())))
				&& ((this.getPurmanNo() == castOther.getPurmanNo()) || (this
						.getPurmanNo() != null
						&& castOther.getPurmanNo() != null && this
						.getPurmanNo().equals(castOther.getPurmanNo())))
				&& ((this.getItemNo() == castOther.getItemNo()) || (this
						.getItemNo() != null && castOther.getItemNo() != null && this
						.getItemNo().equals(castOther.getItemNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getFactNo() == null ? 0 : this.getFactNo().hashCode());
		result = 37 * result
				+ (getVisaSort() == null ? 0 : this.getVisaSort().hashCode());
		result = 37 * result
				+ (getPurmanNo() == null ? 0 : this.getPurmanNo().hashCode());
		result = 37 * result
				+ (getItemNo() == null ? 0 : this.getItemNo().hashCode());
		return result;
	}

}