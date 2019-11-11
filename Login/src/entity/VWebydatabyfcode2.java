package entity;

import java.math.BigDecimal;

/**
 * VWebydatabyfcode2 entity. @author MyEclipse Persistence Tools
 */

public class VWebydatabyfcode2 implements java.io.Serializable {

	// Fields

	private VWebydatabyfcode2Id id;
	private BigDecimal standardOutput;
	private BigDecimal actualYield;

	// Constructors

	/** default constructor */
	public VWebydatabyfcode2() {
	}

	/** minimal constructor */
	public VWebydatabyfcode2(VWebydatabyfcode2Id id) {
		this.id = id;
	}

	/** full constructor */
	public VWebydatabyfcode2(VWebydatabyfcode2Id id, BigDecimal standardOutput,
			BigDecimal actualYield) {
		this.id = id;
		this.standardOutput = standardOutput;
		this.actualYield = actualYield;
	}

	// Property accessors

	public VWebydatabyfcode2Id getId() {
		return this.id;
	}

	public void setId(VWebydatabyfcode2Id id) {
		this.id = id;
	}

	public BigDecimal getStandardOutput() {
		return this.standardOutput;
	}

	public void setStandardOutput(BigDecimal standardOutput) {
		this.standardOutput = standardOutput;
	}

	public BigDecimal getActualYield() {
		return this.actualYield;
	}

	public void setActualYield(BigDecimal actualYield) {
		this.actualYield = actualYield;
	}

}