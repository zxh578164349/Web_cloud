package entity;

/**
 * WebydataNoinputId entity. @author MyEclipse Persistence Tools
 */

public class WebydataNoinputId implements java.io.Serializable {

	// Fields

	private WebFact fact;
	private String yymmdd;

	// Constructors

	/** default constructor */
	public WebydataNoinputId() {
	}

	/** full constructor */
	public WebydataNoinputId(WebFact fact ,  String yymmdd) {
		this.fact = fact;
		this.yymmdd = yymmdd;
	}

	// Property accessors


	public String getYymmdd() {
		return this.yymmdd;
	}

	public void setYymmdd(String yymmdd) {
		this.yymmdd = yymmdd;
	}
	

	public WebFact getFact() {
		return fact;
	}

	public void setFact(WebFact fact) {
		this.fact = fact;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WebydataNoinputId))
			return false;
		WebydataNoinputId castOther = (WebydataNoinputId) other;

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