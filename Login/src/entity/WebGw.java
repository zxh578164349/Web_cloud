package entity;

/**
 * WebGw entity. @author MyEclipse Persistence Tools
 */

public class WebGw implements java.io.Serializable {

	// Fields

	private WebGwId id;
	private String factCode;
	private Double lossWeit;
	private Double lossExp;
	private Double wlossWeit;
	private Double wlossExp;

	// Constructors

	/** default constructor */
	public WebGw() {
	}

	/** minimal constructor */
	public WebGw(WebGwId id) {
		this.id = id;
	}

	/** full constructor */
	public WebGw(WebGwId id, String factCode, Double lossWeit, Double lossExp,
			Double wlossWeit, Double wlossExp) {
		this.id = id;
		this.factCode = factCode;
		this.lossWeit = lossWeit;
		this.lossExp = lossExp;
		this.wlossWeit = wlossWeit;
		this.wlossExp = wlossExp;
	}

	// Property accessors

	public WebGwId getId() {
		return this.id;
	}

	public void setId(WebGwId id) {
		this.id = id;
	}

	public String getFactCode() {
		return this.factCode;
	}

	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}

	public Double getLossWeit() {
		return this.lossWeit;
	}

	public void setLossWeit(Double lossWeit) {
		this.lossWeit = lossWeit;
	}

	public Double getLossExp() {
		return this.lossExp;
	}

	public void setLossExp(Double lossExp) {
		this.lossExp = lossExp;
	}

	public Double getWlossWeit() {
		return this.wlossWeit;
	}

	public void setWlossWeit(Double wlossWeit) {
		this.wlossWeit = wlossWeit;
	}

	public Double getWlossExp() {
		return this.wlossExp;
	}

	public void setWlossExp(Double wlossExp) {
		this.wlossExp = wlossExp;
	}

}