package entity;

/**
 * VWebobjBObj4Id entity. @author MyEclipse Persistence Tools
 */

public class VWebobjBObj4Id implements java.io.Serializable {

	// Fields

	private WebFact webFact;
	private String yymm;

	// Constructors

	/** default constructor */
	public VWebobjBObj4Id() {
	}


	/** full constructor */
	public VWebobjBObj4Id(WebFact webFact, String yymm) {
		this.webFact = webFact;
		this.yymm = yymm;
	}

	// Property accessors
	

	public String getYymm() {
		return this.yymm;
	}
	
	public WebFact getWebFact() {
		return webFact;
	}

	public void setWebFact(WebFact webFact) {
		this.webFact = webFact;
	}

	public void setYymm(String yymm) {
		this.yymm = yymm;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VWebobjBObj4Id))
			return false;
		VWebobjBObj4Id castOther = (VWebobjBObj4Id) other;

		return ((this.getWebFact() == castOther.getWebFact()) || (this
				.getWebFact() != null && castOther.getWebFact() != null && this
				.getWebFact().equals(castOther.getWebFact())))				
				&& ((this.getYymm() == castOther.getYymm()) || (this.getYymm() != null
						&& castOther.getYymm() != null && this.getYymm()
						.equals(castOther.getYymm())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getWebFact() == null ? 0 : this.getWebFact().hashCode());		
		result = 37 * result
				+ (getYymm() == null ? 0 : this.getYymm().hashCode());
		return result;
	}

}