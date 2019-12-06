package entity;

/**
 * VWebobjBAllYearId entity. @author MyEclipse Persistence Tools
 */

public class VWebobjBAllYearId implements java.io.Serializable {

	// Fields

	private WebFact webFact;
	private String year;

	// Constructors

	/** default constructor */
	public VWebobjBAllYearId() {
	}

	

	/** full constructor */
	public VWebobjBAllYearId(WebFact webFact, String year) {
		this.webFact = webFact;
		this.year = year;
	}

	// Property accessors
	

	public String getYear() {
		return this.year;
	}

	public WebFact getWebFact() {
		return webFact;
	}



	public void setWebFact(WebFact webFact) {
		this.webFact = webFact;
	}



	public void setYear(String year) {
		this.year = year;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VWebobjBAllYearId))
			return false;
		VWebobjBAllYearId castOther = (VWebobjBAllYearId) other;

		return ((this.getWebFact() == castOther.getWebFact()) || (this
				.getWebFact() != null && castOther.getWebFact() != null && this
				.getWebFact().equals(castOther.getWebFact())))				
				&& ((this.getYear() == castOther.getYear()) || (this.getYear() != null
						&& castOther.getYear() != null && this.getYear()
						.equals(castOther.getYear())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getWebFact() == null ? 0 : this.getWebFact().hashCode());		
		result = 37 * result
				+ (getYear() == null ? 0 : this.getYear().hashCode());
		return result;
	}

}