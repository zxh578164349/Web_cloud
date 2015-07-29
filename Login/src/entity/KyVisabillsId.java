package entity;

/**
 * KyVisabillsId entity. @author MyEclipse Persistence Tools
 */

public class KyVisabillsId implements java.io.Serializable {

	// Fields

	private KyVisabillm kyVisabillm;
	private String itemNo;

	// Constructors

	/** default constructor */
	public KyVisabillsId() {
	}

	/** full constructor */
	public KyVisabillsId(KyVisabillm kyVisabillm, String itemNo) {
		this.kyVisabillm = kyVisabillm;
		this.itemNo = itemNo;
	}

	// Property accessors

	public KyVisabillm getKyVisabillm() {
		return this.kyVisabillm;
	}

	public void setKyVisabillm(KyVisabillm kyVisabillm) {
		this.kyVisabillm = kyVisabillm;
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
		if (!(other instanceof KyVisabillsId))
			return false;
		KyVisabillsId castOther = (KyVisabillsId) other;

		return ((this.getKyVisabillm() == castOther.getKyVisabillm()) || (this
				.getKyVisabillm() != null && castOther.getKyVisabillm() != null && this
				.getKyVisabillm().equals(castOther.getKyVisabillm())))
				&& ((this.getItemNo() == castOther.getItemNo()) || (this
						.getItemNo() != null && castOther.getItemNo() != null && this
						.getItemNo().equals(castOther.getItemNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getKyVisabillm() == null ? 0 : this.getKyVisabillm()
						.hashCode());
		result = 37 * result
				+ (getItemNo() == null ? 0 : this.getItemNo().hashCode());
		return result;
	}

}