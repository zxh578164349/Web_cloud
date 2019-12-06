package entity;

/**
 * VWebobjBAllFactnoYearId entity. @author MyEclipse Persistence Tools
 */

public class VWebobjBAllFactnoYearId implements java.io.Serializable {

	// Fields

	private VWebFact fact;
	private String year;

	// Constructors

	/** default constructor */
	public VWebobjBAllFactnoYearId() {
	}


	/** full constructor */
	public VWebobjBAllFactnoYearId(VWebFact fact, String year) {
		this.fact = fact;
		this.year = year;
	}

	// Property accessors

	

	public String getYear() {
		return this.year;
	}

	public VWebFact getFact() {
		return fact;
	}


	public void setFact(VWebFact fact) {
		this.fact = fact;
	}


	public void setYear(String year) {
		this.year = year;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VWebobjBAllFactnoYearId))
			return false;
		VWebobjBAllFactnoYearId castOther = (VWebobjBAllFactnoYearId) other;

		return ((this.getFact() == castOther.getFact()) || (this
				.getFact() != null && castOther.getFact() != null && this
				.getFact().equals(castOther.getFact())))
				&& ((this.getYear() == castOther.getYear()) || (this.getYear() != null
						&& castOther.getYear() != null && this.getYear()
						.equals(castOther.getYear())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getFact() == null ? 0 : this.getFact().hashCode());
		result = 37 * result
				+ (getYear() == null ? 0 : this.getYear().hashCode());
		return result;
	}

}