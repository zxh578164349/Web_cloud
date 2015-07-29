package entity;

import java.math.BigDecimal;

/**
 * VSumloss entity. @author MyEclipse Persistence Tools
 */

public class VSumloss implements java.io.Serializable {

	// Fields

	private VSumlossId id;
	private BigDecimal lossA001;
	private BigDecimal lossA002;
	private BigDecimal lossA003;
	private BigDecimal lossA004;
	private BigDecimal lossA005;
	private BigDecimal lossA006;

	// Constructors

	/** default constructor */
	public VSumloss() {
	}

	/** minimal constructor */
	public VSumloss(VSumlossId id) {
		this.id = id;
	}

	/** full constructor */
	public VSumloss(VSumlossId id, BigDecimal lossA001, BigDecimal lossA002,
			BigDecimal lossA003, BigDecimal lossA004, BigDecimal lossA005,
			BigDecimal lossA006) {
		this.id = id;
		this.lossA001 = lossA001;
		this.lossA002 = lossA002;
		this.lossA003 = lossA003;
		this.lossA004 = lossA004;
		this.lossA005 = lossA005;
		this.lossA006 = lossA006;
	}

	// Property accessors

	public VSumlossId getId() {
		return this.id;
	}

	public void setId(VSumlossId id) {
		this.id = id;
	}

	public BigDecimal getLossA001() {
		return this.lossA001;
	}

	public void setLossA001(BigDecimal lossA001) {
		this.lossA001 = lossA001;
	}

	public BigDecimal getLossA002() {
		return this.lossA002;
	}

	public void setLossA002(BigDecimal lossA002) {
		this.lossA002 = lossA002;
	}

	public BigDecimal getLossA003() {
		return this.lossA003;
	}

	public void setLossA003(BigDecimal lossA003) {
		this.lossA003 = lossA003;
	}

	public BigDecimal getLossA004() {
		return this.lossA004;
	}

	public void setLossA004(BigDecimal lossA004) {
		this.lossA004 = lossA004;
	}

	public BigDecimal getLossA005() {
		return this.lossA005;
	}

	public void setLossA005(BigDecimal lossA005) {
		this.lossA005 = lossA005;
	}

	public BigDecimal getLossA006() {
		return this.lossA006;
	}

	public void setLossA006(BigDecimal lossA006) {
		this.lossA006 = lossA006;
	}

}