package entity;

/**
 * VWebydatabyfcode2Id entity. @author MyEclipse Persistence Tools
 */

public class VWebydatabyfcode2Id implements java.io.Serializable {

	// Fields

	private String factCode;
	private String yymmdd;

	// Constructors

	/** default constructor */
	public VWebydatabyfcode2Id() {
	}

	/** full constructor */
	public VWebydatabyfcode2Id(String factCode, String yymmdd) {
		this.factCode = factCode;
		this.yymmdd = yymmdd;
	}

	// Property accessors

	public String getFactCode() {
		return this.factCode;
	}

	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}

	public String getYymmdd() {
		return this.yymmdd;
	}

	public void setYymmdd(String yymmdd) {
		this.yymmdd = yymmdd;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VWebydatabyfcode2Id))
			return false;
		VWebydatabyfcode2Id castOther = (VWebydatabyfcode2Id) other;

		return ((this.getFactCode() == castOther.getFactCode()) || (this
				.getFactCode() != null && castOther.getFactCode() != null && this
				.getFactCode().equals(castOther.getFactCode())))
				&& ((this.getYymmdd() == castOther.getYymmdd()) || (this
						.getYymmdd() != null && castOther.getYymmdd() != null && this
						.getYymmdd().equals(castOther.getYymmdd())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getFactCode() == null ? 0 : this.getFactCode().hashCode());
		result = 37 * result
				+ (getYymmdd() == null ? 0 : this.getYymmdd().hashCode());
		return result;
	}

}