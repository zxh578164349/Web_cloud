package entity;

/**
 * WebFactorderId entity. @author MyEclipse Persistence Tools
 */

public class WebFactorderId implements java.io.Serializable {

	// Fields

	private String factNo;
	private String factArea;
	private String yymm;
	private String modelNo;
	private String customer;
	private String brank;
	private String component;

	// Constructors

	/** default constructor */
	public WebFactorderId() {
	}

	/** full constructor */
	public WebFactorderId(String factNo, String factArea, String yymm,
			String modelNo, String customer, String brank, String component) {
		this.factNo = factNo;
		this.factArea = factArea;
		this.yymm = yymm;
		this.modelNo = modelNo;
		this.customer = customer;
		this.brank = brank;
		this.component = component;
	}

	// Property accessors

	public String getFactNo() {
		return this.factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public String getFactArea() {
		return this.factArea;
	}

	public void setFactArea(String factArea) {
		this.factArea = factArea;
	}

	public String getYymm() {
		return this.yymm;
	}

	public void setYymm(String yymm) {
		this.yymm = yymm;
	}

	public String getModelNo() {
		return this.modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getBrank() {
		return this.brank;
	}

	public void setBrank(String brank) {
		this.brank = brank;
	}

	public String getComponent() {
		return this.component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WebFactorderId))
			return false;
		WebFactorderId castOther = (WebFactorderId) other;

		return ((this.getFactNo() == castOther.getFactNo()) || (this
				.getFactNo() != null && castOther.getFactNo() != null && this
				.getFactNo().equals(castOther.getFactNo())))
				&& ((this.getFactArea() == castOther.getFactArea()) || (this
						.getFactArea() != null
						&& castOther.getFactArea() != null && this
						.getFactArea().equals(castOther.getFactArea())))
				&& ((this.getYymm() == castOther.getYymm()) || (this.getYymm() != null
						&& castOther.getYymm() != null && this.getYymm()
						.equals(castOther.getYymm())))
				&& ((this.getModelNo() == castOther.getModelNo()) || (this
						.getModelNo() != null && castOther.getModelNo() != null && this
						.getModelNo().equals(castOther.getModelNo())))
				&& ((this.getCustomer() == castOther.getCustomer()) || (this
						.getCustomer() != null
						&& castOther.getCustomer() != null && this
						.getCustomer().equals(castOther.getCustomer())))
				&& ((this.getBrank() == castOther.getBrank()) || (this
						.getBrank() != null && castOther.getBrank() != null && this
						.getBrank().equals(castOther.getBrank())))
				&& ((this.getComponent() == castOther.getComponent()) || (this
						.getComponent() != null
						&& castOther.getComponent() != null && this
						.getComponent().equals(castOther.getComponent())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getFactNo() == null ? 0 : this.getFactNo().hashCode());
		result = 37 * result
				+ (getFactArea() == null ? 0 : this.getFactArea().hashCode());
		result = 37 * result
				+ (getYymm() == null ? 0 : this.getYymm().hashCode());
		result = 37 * result
				+ (getModelNo() == null ? 0 : this.getModelNo().hashCode());
		result = 37 * result
				+ (getCustomer() == null ? 0 : this.getCustomer().hashCode());
		result = 37 * result
				+ (getBrank() == null ? 0 : this.getBrank().hashCode());
		result = 37 * result
				+ (getComponent() == null ? 0 : this.getComponent().hashCode());
		return result;
	}

}