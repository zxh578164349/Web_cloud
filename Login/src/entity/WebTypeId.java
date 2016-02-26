package entity;

/**
 * WebTypeId entity. @author MyEclipse Persistence Tools
 */

public class WebTypeId implements java.io.Serializable {

	// Fields

	private String factNo;
	private String typeNo;
	

	// Constructors

	/** default constructor */
	public WebTypeId() {
	}

	/** full constructor */
	public WebTypeId(String factNo, String typeNo) {
		this.factNo = factNo;
		this.typeNo = typeNo;
	}

	// Property accessors

	public String getFactNo() {
		return this.factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public String getTypeNo() {
		return this.typeNo;
	}

	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}
	

	

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WebTypeId))
			return false;
		WebTypeId castOther = (WebTypeId) other;

		return ((this.getFactNo() == castOther.getFactNo()) || (this
				.getFactNo() != null && castOther.getFactNo() != null && this
				.getFactNo().equals(castOther.getFactNo())))
				&& ((this.getTypeNo() == castOther.getTypeNo()) || (this
						.getTypeNo() != null && castOther.getTypeNo() != null && this
						.getTypeNo().equals(castOther.getTypeNo())));
				
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getFactNo() == null ? 0 : this.getFactNo().hashCode());
		result = 37 * result
				+ (getTypeNo() == null ? 0 : this.getTypeNo().hashCode());		
		return result;
	}

}