package entity;

import java.util.Date;

/**
 * WebPersonId entity. @author MyEclipse Persistence Tools
 */

public class WebPersonId implements java.io.Serializable {

	// Fields

	private String factNo;
	private String billNo;
	private Date yymm;

	// Constructors

	/** default constructor */
	public WebPersonId() {
	}

	/** full constructor */
	public WebPersonId(String factNo, String billNo, Date yymm) {
		this.factNo = factNo;
		this.billNo = billNo;
		this.yymm = yymm;
	}

	// Property accessors

	public String getFactNo() {
		return this.factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public String getBillNo() {
		return this.billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public Date getYymm() {
		return this.yymm;
	}

	public void setYymm(Date yymm) {
		this.yymm = yymm;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WebPersonId))
			return false;
		WebPersonId castOther = (WebPersonId) other;

		return ((this.getFactNo() == castOther.getFactNo()) || (this
				.getFactNo() != null && castOther.getFactNo() != null && this
				.getFactNo().equals(castOther.getFactNo())))
				&& ((this.getBillNo() == castOther.getBillNo()) || (this
						.getBillNo() != null && castOther.getBillNo() != null && this
						.getBillNo().equals(castOther.getBillNo())))
				&& ((this.getYymm() == castOther.getYymm()) || (this.getYymm() != null
						&& castOther.getYymm() != null && this.getYymm()
						.equals(castOther.getYymm())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getFactNo() == null ? 0 : this.getFactNo().hashCode());
		result = 37 * result
				+ (getBillNo() == null ? 0 : this.getBillNo().hashCode());
		result = 37 * result
				+ (getYymm() == null ? 0 : this.getYymm().hashCode());
		return result;
	}

}