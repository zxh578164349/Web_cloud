package entity;

import java.math.BigDecimal;

/**
 * VWeballobjasumwebyield entity. @author MyEclipse Persistence Tools
 */

public class VWeballobjasumwebyield implements java.io.Serializable {

	// Fields

	private VWeballobjasumwebyieldId id;
	private Double objA101;
	private Double objA177;
	private Double objA178;
	private BigDecimal sumActualpairs;
	private WebFact fact;

	// Constructors

	/** default constructor */
	public VWeballobjasumwebyield() {
	}

	/** minimal constructor */
	public VWeballobjasumwebyield(VWeballobjasumwebyieldId id) {
		this.id = id;
	}
	
	public VWeballobjasumwebyield(WebFact fact,String yymm) {
		this.fact = fact;
		this.id.setYymm(yymm);
	}

	/** full constructor */
	public VWeballobjasumwebyield(VWeballobjasumwebyieldId id, Double objA101,
			Double objA177, Double objA178, BigDecimal sumActualpairs) {
		this.id = id;
		this.objA101 = objA101;
		this.objA177 = objA177;
		this.objA178 = objA178;
		this.sumActualpairs = sumActualpairs;
	}

	// Property accessors

	public VWeballobjasumwebyieldId getId() {
		return this.id;
	}

	public void setId(VWeballobjasumwebyieldId id) {
		this.id = id;
	}

	public Double getObjA101() {
		return this.objA101;
	}

	public void setObjA101(Double objA101) {
		this.objA101 = objA101;
	}

	public Double getObjA177() {
		return this.objA177;
	}

	public void setObjA177(Double objA177) {
		this.objA177 = objA177;
	}

	public Double getObjA178() {
		return this.objA178;
	}

	public void setObjA178(Double objA178) {
		this.objA178 = objA178;
	}

	public BigDecimal getSumActualpairs() {
		return this.sumActualpairs;
	}

	public void setSumActualpairs(BigDecimal sumActualpairs) {
		this.sumActualpairs = sumActualpairs;
	}

	public WebFact getFact() {
		return fact;
	}

	public void setFact(WebFact fact) {
		this.fact = fact;
	}
	

}