package entity;

import java.math.BigDecimal;

/**
 * VWebsideEve entity. @author MyEclipse Persistence Tools
 */

public class VWebsideEve implements java.io.Serializable {

	// Fields

	private VWebsideEveId id;
	private BigDecimal sumActualpairs;
	private Double sideweit;
	private Double badcount;
	private Double badweit;
	private Double otherbadweight;
	private Double avgbuttomweight;
	private Double avgbuttomweight2;
	private Double avgprice;
	private Double otherweight;

	// Constructors

	/** default constructor */
	public VWebsideEve() {
	}

	/** minimal constructor */
	public VWebsideEve(VWebsideEveId id) {
		this.id = id;
	}

	/** full constructor */
	public VWebsideEve(VWebsideEveId id, BigDecimal sumActualpairs,
			Double sideweit, Double badcount, Double badweit,
			Double otherbadweight, Double avgbuttomweight,
			Double avgbuttomweight2, Double avgprice,Double otherweight) {
		this.id = id;
		this.sumActualpairs = sumActualpairs;
		this.sideweit = sideweit;
		this.badcount = badcount;
		this.badweit = badweit;
		this.otherbadweight = otherbadweight;
		this.avgbuttomweight = avgbuttomweight;
		this.avgbuttomweight2 = avgbuttomweight2;
		this.avgprice = avgprice;
		this.otherweight=otherweight;
	}

	// Property accessors

	public VWebsideEveId getId() {
		return this.id;
	}

	public void setId(VWebsideEveId id) {
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

	public Double getBadcount() {
		return this.badcount;
	}

	public void setBadcount(Double badcount) {
		this.badcount = badcount;
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

	public Double getAvgbuttomweight() {
		return this.avgbuttomweight;
	}

	public void setAvgbuttomweight(Double avgbuttomweight) {
		this.avgbuttomweight = avgbuttomweight;
	}

	public Double getAvgbuttomweight2() {
		return this.avgbuttomweight2;
	}

	public void setAvgbuttomweight2(Double avgbuttomweight2) {
		this.avgbuttomweight2 = avgbuttomweight2;
	}

	public Double getAvgprice() {
		return this.avgprice;
	}

	public void setAvgprice(Double avgprice) {
		this.avgprice = avgprice;
	}

	public Double getOtherweight() {
		return otherweight;
	}

	public void setOtherweight(Double otherweight) {
		this.otherweight = otherweight;
	}
	

}