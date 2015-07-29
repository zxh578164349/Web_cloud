package entity;

/**
 * KyzExpectmatsId entity. @author MyEclipse Persistence Tools
 */

public class KyzExpectmatsId implements java.io.Serializable {

	// Fields

	private KyzExpectmatm kyzExpectmatm;
	private String itemNo;

	// Constructors

	/** default constructor */
	public KyzExpectmatsId() {
	}

	/** full constructor */
	public KyzExpectmatsId(KyzExpectmatm kyzExpectmatm, String itemNo) {
		this.kyzExpectmatm = kyzExpectmatm;
		this.itemNo = itemNo;
	}

	// Property accessors

	public KyzExpectmatm getKyzExpectmatm() {
		return this.kyzExpectmatm;
	}

	public void setKyzExpectmatm(KyzExpectmatm kyzExpectmatm) {
		this.kyzExpectmatm = kyzExpectmatm;
	}

	public String getItemNo() {
		return this.itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof KyzExpectmatsId))
			return false;
		KyzExpectmatsId castOther = (KyzExpectmatsId) other;

		return ((this.getKyzExpectmatm() == castOther.getKyzExpectmatm()) || (this
				.getKyzExpectmatm() != null
				&& castOther.getKyzExpectmatm() != null && this
				.getKyzExpectmatm().equals(castOther.getKyzExpectmatm())))
				&& ((this.getItemNo() == castOther.getItemNo()) || (this
						.getItemNo() != null && castOther.getItemNo() != null && this
						.getItemNo().equals(castOther.getItemNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getKyzExpectmatm() == null ? 0 : this.getKyzExpectmatm()
						.hashCode());
		result = 37 * result
				+ (getItemNo() == null ? 0 : this.getItemNo().hashCode());
		return result;
	}

}