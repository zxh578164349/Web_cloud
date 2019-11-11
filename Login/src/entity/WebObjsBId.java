package entity;

/**
 * WebObjsBId entity. @author MyEclipse Persistence Tools
 */

public class WebObjsBId implements java.io.Serializable {

	// Fields

	private WebFact webFact;
	private String yymmdd;

	// Constructors

	/** default constructor */
	public WebObjsBId() {
	}

	/** full constructor */
	public WebObjsBId(WebFact webFact, String yymmdd) {
		this.webFact = webFact;
		this.yymmdd = yymmdd;
	}

	// Property accessors


	public String getYymmdd() {
		return this.yymmdd;
	}

	public WebFact getWebFact() {
		return webFact;
	}

	public void setWebFact(WebFact webFact) {
		this.webFact = webFact;
	}

	public void setYymmdd(String yymmdd) {
		this.yymmdd = yymmdd;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WebObjsBId))
			return false;
		WebObjsBId castOther = (WebObjsBId) other;

		return ((this.getWebFact() == castOther.getWebFact()) || (this
				.getWebFact() != null && castOther.getWebFact() != null && this
				.getWebFact().equals(castOther.getWebFact())))			
				&& ((this.getYymmdd() == castOther.getYymmdd()) || (this
						.getYymmdd() != null && castOther.getYymmdd() != null && this
						.getYymmdd().equals(castOther.getYymmdd())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getWebFact() == null ? 0 : this.getWebFact().hashCode());		
		result = 37 * result
				+ (getYymmdd() == null ? 0 : this.getYymmdd().hashCode());
		return result;
	}

}