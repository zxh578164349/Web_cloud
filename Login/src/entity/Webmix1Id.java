package entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Webmix1Id entity. @author MyEclipse Persistence Tools
 */

public class Webmix1Id implements java.io.Serializable {

	// Fields

	private String factNo;
	private String factCode;
	private Date yymmdd;

	// Constructors

	/** default constructor */
	public Webmix1Id() {
	}

	/** full constructor */
	public Webmix1Id(String factNo, String factCode, Date yymmdd) {
		this.factNo = factNo;
		this.factCode = factCode;
		this.yymmdd = yymmdd;
	}

	// Property accessors

	public String getFactNo() {
		return this.factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public String getFactCode() {
		return this.factCode;
	}

	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}



	public Date getYymmdd() {
		return yymmdd;
	}

	public void setYymmdd(Date yymmdd) {
		this.yymmdd = yymmdd;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Webmix1Id))
			return false;
		Webmix1Id castOther = (Webmix1Id) other;

		return ((this.getFactNo() == castOther.getFactNo()) || (this
				.getFactNo() != null && castOther.getFactNo() != null && this
				.getFactNo().equals(castOther.getFactNo())))
				&& ((this.getFactCode() == castOther.getFactCode()) || (this
						.getFactCode() != null
						&& castOther.getFactCode() != null && this
						.getFactCode().equals(castOther.getFactCode())))
				&& ((this.getYymmdd() == castOther.getYymmdd()) || (this
						.getYymmdd() != null && castOther.getYymmdd() != null && this
						.getYymmdd().equals(castOther.getYymmdd())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getFactNo() == null ? 0 : this.getFactNo().hashCode());
		result = 37 * result
				+ (getFactCode() == null ? 0 : this.getFactCode().hashCode());
		result = 37 * result
				+ (getYymmdd() == null ? 0 : this.getYymmdd().hashCode());
		return result;
	}

}