package entity;

/**
 * VWebobjA3Id entity. @author MyEclipse Persistence Tools
 */

public class VWebobjA3Id implements java.io.Serializable {

	// Fields

	private VWebFact fact;
	private String yymmdd;

	// Constructors

	/** default constructor */
	public VWebobjA3Id() {
	}

	/** full constructor */
	public VWebobjA3Id(VWebFact fact, String yymmdd) {
		this.fact = fact;
		this.yymmdd = yymmdd;
	}

	// Property accessors

	

	public String getYymmdd() {
		return this.yymmdd;
	}

	public VWebFact getFact() {
		return fact;
	}

	public void setFact(VWebFact fact) {
		this.fact = fact;
	}

	public void setYymmdd(String yymmdd) {
		this.yymmdd = yymmdd;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VWebobjA3Id))
			return false;
		VWebobjA3Id castOther = (VWebobjA3Id) other;

		return ((this.getFact() == castOther.getFact()) || (this
				.getFact() != null && castOther.getFact() != null && this
				.getFact().equals(castOther.getFact())))
				&& ((this.getYymmdd() == castOther.getYymmdd()) || (this
						.getYymmdd() != null && castOther.getYymmdd() != null && this
						.getYymmdd().equals(castOther.getYymmdd())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getFact() == null ? 0 : this.getFact().hashCode());
		result = 37 * result
				+ (getYymmdd() == null ? 0 : this.getYymmdd().hashCode());
		return result;
	}

}