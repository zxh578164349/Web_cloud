package entity;

/**
 * KyzScmClassmId entity. @author MyEclipse Persistence Tools
 */

public class KyzScmClassmId implements java.io.Serializable {

	// Fields

	private String scmBclassNo;
	private String scmMclassNo;
	private String scmSclassNo;

	// Constructors

	/** default constructor */
	public KyzScmClassmId() {
	}

	/** full constructor */
	public KyzScmClassmId(String scmBclassNo, String scmMclassNo,
			String scmSclassNo) {
		this.scmBclassNo = scmBclassNo;
		this.scmMclassNo = scmMclassNo;
		this.scmSclassNo = scmSclassNo;
	}

	// Property accessors

	public String getScmBclassNo() {
		return this.scmBclassNo;
	}

	public void setScmBclassNo(String scmBclassNo) {
		this.scmBclassNo = scmBclassNo;
	}

	public String getScmMclassNo() {
		return this.scmMclassNo;
	}

	public void setScmMclassNo(String scmMclassNo) {
		this.scmMclassNo = scmMclassNo;
	}

	public String getScmSclassNo() {
		return this.scmSclassNo;
	}

	public void setScmSclassNo(String scmSclassNo) {
		this.scmSclassNo = scmSclassNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof KyzScmClassmId))
			return false;
		KyzScmClassmId castOther = (KyzScmClassmId) other;

		return ((this.getScmBclassNo() == castOther.getScmBclassNo()) || (this
				.getScmBclassNo() != null && castOther.getScmBclassNo() != null && this
				.getScmBclassNo().equals(castOther.getScmBclassNo())))
				&& ((this.getScmMclassNo() == castOther.getScmMclassNo()) || (this
						.getScmMclassNo() != null
						&& castOther.getScmMclassNo() != null && this
						.getScmMclassNo().equals(castOther.getScmMclassNo())))
				&& ((this.getScmSclassNo() == castOther.getScmSclassNo()) || (this
						.getScmSclassNo() != null
						&& castOther.getScmSclassNo() != null && this
						.getScmSclassNo().equals(castOther.getScmSclassNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getScmBclassNo() == null ? 0 : this.getScmBclassNo()
						.hashCode());
		result = 37
				* result
				+ (getScmMclassNo() == null ? 0 : this.getScmMclassNo()
						.hashCode());
		result = 37
				* result
				+ (getScmSclassNo() == null ? 0 : this.getScmSclassNo()
						.hashCode());
		return result;
	}

}