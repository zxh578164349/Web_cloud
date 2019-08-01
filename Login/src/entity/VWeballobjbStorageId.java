package entity;

/**
 * VWeballobjbStorageId entity. @author MyEclipse Persistence Tools
 */

public class VWeballobjbStorageId implements java.io.Serializable {

	// Fields

	private String factNo;
	private String factCode;
	private String yymm;

	// Constructors

	/** default constructor */
	public VWeballobjbStorageId() {
	}

	/** full constructor */
	public VWeballobjbStorageId(String factNo, String factCode, String yymm) {
		this.factNo = factNo;
		this.factCode = factCode;
		this.yymm = yymm;
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

	public String getYymm() {
		return this.yymm;
	}

	public void setYymm(String yymm) {
		this.yymm = yymm;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VWeballobjbStorageId))
			return false;
		VWeballobjbStorageId castOther = (VWeballobjbStorageId) other;

		return ((this.getFactNo() == castOther.getFactNo()) || (this
				.getFactNo() != null && castOther.getFactNo() != null && this
				.getFactNo().equals(castOther.getFactNo())))
				&& ((this.getFactCode() == castOther.getFactCode()) || (this
						.getFactCode() != null
						&& castOther.getFactCode() != null && this
						.getFactCode().equals(castOther.getFactCode())))
				&& ((this.getYymm() == castOther.getYymm()) || (this.getYymm() != null
						&& castOther.getYymm() != null && this.getYymm()
						.equals(castOther.getYymm())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getFactNo() == null ? 0 : this.getFactNo().hashCode());
		result = 37 * result
				+ (getFactCode() == null ? 0 : this.getFactCode().hashCode());
		result = 37 * result
				+ (getYymm() == null ? 0 : this.getYymm().hashCode());
		return result;
	}

}