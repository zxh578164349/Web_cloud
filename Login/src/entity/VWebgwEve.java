package entity;

import java.math.BigDecimal;

/**
 * VWebgwEve entity. @author MyEclipse Persistence Tools
 */

public class VWebgwEve implements java.io.Serializable {

	// Fields

	private VWebgwEveId id;
	private BigDecimal sumActualpairs;
	private Double sideweit;
	private Double badweit;
	private Double otherbadweight;
	private Double actlost;
	private Double avgbuttomweight2;
	private Double otherweight;

	// Constructors

	/** default constructor */
	public VWebgwEve() {
	}

	/** minimal constructor */
	public VWebgwEve(VWebgwEveId id) {
		this.id = id;
	}

	/** full constructor */
	public VWebgwEve(VWebgwEveId id, BigDecimal sumActualpairs,
			Double sideweit, Double badweit, Double otherbadweight,
			Double actlost, Double avgbuttomweight2,Double otherweight) {
		this.id = id;
		this.sumActualpairs = sumActualpairs;
		this.sideweit = sideweit;
		this.badweit = badweit;
		this.otherbadweight = otherbadweight;
		this.actlost = actlost;
		this.avgbuttomweight2 = avgbuttomweight2;
		this.otherweight=otherweight;
	}

	// Property accessors

	public VWebgwEveId getId() {
		return this.id;
	}

	public void setId(VWebgwEveId id) {
		this.id = id;
	}

	public BigDecimal getSumActualpairs() {
		return this.sumActualpairs;
	}

	public void setSumActualpairs(BigDecimal sumActualpairs) {
		this.sumActualpairs = sumActualpairs;
	}

	public Double getSideweit() {
		return this.sideweit;
	}

	public void setSideweit(Double sideweit) {
		this.sideweit = sideweit;
	}

	public Double getBadweit() {
		return this.badweit;
	}

	public void setBadweit(Double badweit) {
		this.badweit = badweit;
	}

	public Double getOtherbadweight() {
		return this.otherbadweight;
	}

	public void setOtherbadweight(Double otherbadweight) {
		this.otherbadweight = otherbadweight;
	}

	public Double getActlost() {
		return this.actlost;
	}

	public void setActlost(Double actlost) {
		this.actlost = actlost;
	}

	public Double getAvgbuttomweight2() {
		return this.avgbuttomweight2;
	}

	public void setAvgbuttomweight2(Double avgbuttomweight2) {
		this.avgbuttomweight2 = avgbuttomweight2;
	}

	public Double getOtherweight() {
		return otherweight;
	}

	public void setOtherweight(Double otherweight) {
		this.otherweight = otherweight;
	}
	

}