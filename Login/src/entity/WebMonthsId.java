package entity;

/**
 * WebMonthsId entity. @author MyEclipse Persistence Tools
 */

public class WebMonthsId implements java.io.Serializable {

	// Fields

	private String yymm;
	private String objType;

	// Constructors

	/** default constructor */
	public WebMonthsId() {
	}

	/** full constructor */
	public WebMonthsId(String yymm, String objType) {
		this.yymm = yymm;
		this.objType = objType;
	}

	// Property accessors

	public String getYymm() {
		return this.yymm;
	}

	public void setYymm(String yymm) {
		this.yymm = yymm;
	}

	public String getObjType() {
		return this.objType;
	}

	public void setObjType(String objType) {
		this.objType = objType;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WebMonthsId))
			return false;
		WebMonthsId castOther = (WebMonthsId) other;

		return ((this.getYymm() == castOther.getYymm()) || (this.getYymm() != null
				&& castOther.getYymm() != null && this.getYymm().equals(
				castOther.getYymm())))
				&& ((this.getObjType() == castOther.getObjType()) || (this
						.getObjType() != null && castOther.getObjType() != null && this
						.getObjType().equals(castOther.getObjType())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getYymm() == null ? 0 : this.getYymm().hashCode());
		result = 37 * result
				+ (getObjType() == null ? 0 : this.getObjType().hashCode());
		return result;
	}

}