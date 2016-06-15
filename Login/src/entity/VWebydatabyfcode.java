package entity;

import java.math.BigDecimal;

/**
 * VWebydatabyfcode entity. @author MyEclipse Persistence Tools
 */

public class VWebydatabyfcode implements java.io.Serializable {

	// Fields

	private VWebydatabyfcodeId id;
	private BigDecimal standardOutput;
	private BigDecimal actualYield;

	// Constructors

	/** default constructor */
	public VWebydatabyfcode() {
	}

	/** minimal constructor */
	public VWebydatabyfcode(VWebydatabyfcodeId id) {
		this.id = id;
	}

	/** full constructor */
	public VWebydatabyfcode(VWebydatabyfcodeId id, BigDecimal standardOutput,
			BigDecimal actualYield) {
		this.id = id;
		this.standardOutput = standardOutput;
		this.actualYield = actualYield;
	}

	// Property accessors

	public VWebydatabyfcodeId getId() {
		return this.id;
	}

	public void setId(VWebydatabyfcodeId id) {
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