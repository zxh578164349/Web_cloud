package entity;

/**
 * KyTypeId entity. @author MyEclipse Persistence Tools
 */

public class KyTypeId implements java.io.Serializable {

	// Fields

	private String typeNo;
	private String typeSno;

	// Constructors

	/** default constructor */
	public KyTypeId() {
	}

	/** full constructor */
	public KyTypeId(String typeNo, String typeSno) {
		this.typeNo = typeNo;
		this.typeSno = typeSno;
	}

	// Property accessors

	public String getTypeNo() {
		return this.typeNo;
	}

	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}

	public String getTypeSno() {
		return this.typeSno;
	}

	public void setTypeSno(String typeSno) {
		this.typeSno = typeSno;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof KyTypeId))
			return false;
		KyTypeId castOther = (KyTypeId) other;

		return ((this.getTypeNo() == castOther.getTypeNo()) || (this
				.getTypeNo() != null && castOther.getTypeNo() != null && this
				.getTypeNo().equals(castOther.getTypeNo())))
				&& ((this.getTypeSno() == castOther.getTypeSno()) || (this
						.getTypeSno() != null && castOther.getTypeSno() != null && this
						.getTypeSno().equals(castOther.getTypeSno())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getTypeNo() == null ? 0 : this.getTypeNo().hashCode());
		result = 37 * result
				+ (getTypeSno() == null ? 0 : this.getTypeSno().hashCode());
		return result;
	}

}