package entity;

/**
 * VWebprofitlossFactEveId entity. @author MyEclipse Persistence Tools
 */

public class VWebprofitlossFactEveId implements java.io.Serializable {

	// Fields

	private String factNo;
	private String yymm;

	// Constructors

	/** default constructor */
	public VWebprofitlossFactEveId() {
	}

	/** full constructor */
	public VWebprofitlossFactEveId(String factNo, String yymm) {
		this.factNo = factNo;
		this.yymm = yymm;
	}

	// Property accessors

	public String getFactNo() {
		return this.factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
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
		if (!(other instanceof VWebprofitlossFactEveId))
			return false;
		VWebprofitlossFactEveId castOther = (VWebprofitlossFactEveId) other;

		return ((this.getFactNo() == castOther.getFactNo()) || (this
				.getFactNo() != null && castOther.getFactNo() != null && this
				.getFactNo().equals(castOther.getFactNo())))
				&& ((this.getYymm() == castOther.getYymm()) || (this.getYymm() != null
						&& castOther.getYymm() != null && this.getYymm()
						.equals(castOther.getYymm())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getFactNo() == null ? 0 : this.getFactNo().hashCode());
		result = 37 * result
				+ (getYymm() == null ? 0 : this.getYymm().hashCode());
		return result;
	}

}