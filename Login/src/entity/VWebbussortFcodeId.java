package entity;

/**
 * VWebbussortFcodeId entity. @author MyEclipse Persistence Tools
 */

public class VWebbussortFcodeId implements java.io.Serializable {

	// Fields
	
	private WebFact fact;
	private String yymm;

	// Constructors

	/** default constructor */
	public VWebbussortFcodeId() {
	}

	/** full constructor */
	public VWebbussortFcodeId(WebFact fact, String yymm) {
		this.fact = fact;
		this.yymm = yymm;
	}

	// Property accessors
	
	public String getYymm() {
		return this.yymm;
	}

	public WebFact getFact() {
		return fact;
	}

	public void setFact(WebFact fact) {
		this.fact = fact;
	}

	public void setYymm(String yymm) {
		this.yymm = yymm;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VWebbussortFcodeId))
			return false;
		VWebbussortFcodeId castOther = (VWebbussortFcodeId) other;

		return ((this.getFact() == castOther.getFact()) || (this
				.getFact() != null && castOther.getFact() != null && this
				.getFact().equals(castOther.getFact())))				
				&& ((this.getYymm() == castOther.getYymm()) || (this.getYymm() != null
						&& castOther.getYymm() != null && this.getYymm()
						.equals(castOther.getYymm())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getFact() == null ? 0 : this.getFact().hashCode());
		result = 37 * result
				+ (getYymm() == null ? 0 : this.getYymm().hashCode());
		return result;
	}

}