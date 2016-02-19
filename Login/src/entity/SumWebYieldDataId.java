package entity;

/**
 * SumWebYieldDataId entity. @author MyEclipse Persistence Tools
 */

public class SumWebYieldDataId implements java.io.Serializable {

	// Fields

	private VWebFact factNo;
	private String factCode;
	private String yymm;

	// Constructors

	/** default constructor */
	public SumWebYieldDataId() {
	}

	/** full constructor */
	public SumWebYieldDataId(VWebFact factNo, String factCode, String yymm) {
		this.factNo = factNo;
		this.factCode = factCode;
		this.yymm = yymm;
	}

	// Property accessors

	

	public String getFactCode() {
		return this.factCode;
	}

	public VWebFact getFactNo() {
		return factNo;
	}

	public void setFactNo(VWebFact factNo) {
		this.factNo = factNo;
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
		if (!(other instanceof SumWebYieldDataId))
			return false;
		SumWebYieldDataId castOther = (SumWebYieldDataId) other;

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