package entity;

import java.math.BigDecimal;

/**
 * VWeboutputinvEve entity. @author MyEclipse Persistence Tools
 */

public class VWeboutputinvEve implements java.io.Serializable {

	// Fields

	private VWeboutputinvEveId id;
	private BigDecimal sumActualpairs;
	private Double invcount;
	private Double paypairs;

	// Constructors

	/** default constructor */
	public VWeboutputinvEve() {
	}

	/** minimal constructor */
	public VWeboutputinvEve(VWeboutputinvEveId id) {
		this.id = id;
	}

	/** full constructor */
	public VWeboutputinvEve(VWeboutputinvEveId id, BigDecimal sumActualpairs,
			Double invcount, Double paypairs) {
		this.id = id;
		this.sumActualpairs = sumActualpairs;
		this.invcount = invcount;
		this.paypairs = paypairs;
	}

	// Property accessors

	public VWeboutputinvEveId getId() {
		return this.id;
	}

	public void setId(VWeboutputinvEveId id) {
		this.id = id;
	}

	public BigDecimal getSumActualpairs() {
		return this.sumActualpairs;
	}

	public void setSumActualpairs(BigDecimal sumActualpairs) {
		this.sumActualpairs = sumActualpairs;
	}

	public Double getInvcount() {
		return this.invcount;
	}

	public void setInvcount(Double invcount) {
		this.invcount = invcount;
	}

	public Double getPaypairs() {
		return this.paypairs;
	}

	public void setPaypairs(Double paypairs) {
		this.paypairs = paypairs;
	}

}