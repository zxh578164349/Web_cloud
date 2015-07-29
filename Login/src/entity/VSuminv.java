package entity;

import java.math.BigDecimal;

/**
 * VSuminv entity. @author MyEclipse Persistence Tools
 */

public class VSuminv implements java.io.Serializable {

	// Fields

	private VSuminvId id;
	private BigDecimal invD001;
	private BigDecimal invD002;
	private BigDecimal invD003;
	private BigDecimal invD004;
	private BigDecimal invD005;
	private BigDecimal invD006;

	// Constructors

	/** default constructor */
	public VSuminv() {
	}

	/** minimal constructor */
	public VSuminv(VSuminvId id) {
		this.id = id;
	}

	/** full constructor */
	public VSuminv(VSuminvId id, BigDecimal invD001, BigDecimal invD002,
			BigDecimal invD003, BigDecimal invD004, BigDecimal invD005,
			BigDecimal invD006) {
		this.id = id;
		this.invD001 = invD001;
		this.invD002 = invD002;
		this.invD003 = invD003;
		this.invD004 = invD004;
		this.invD005 = invD005;
		this.invD006 = invD006;
	}

	// Property accessors

	public VSuminvId getId() {
		return this.id;
	}

	public void setId(VSuminvId id) {
		this.id = id;
	}

	public BigDecimal getInvD001() {
		return this.invD001;
	}

	public void setInvD001(BigDecimal invD001) {
		this.invD001 = invD001;
	}

	public BigDecimal getInvD002() {
		return this.invD002;
	}

	public void setInvD002(BigDecimal invD002) {
		this.invD002 = invD002;
	}

	public BigDecimal getInvD003() {
		return this.invD003;
	}

	public void setInvD003(BigDecimal invD003) {
		this.invD003 = invD003;
	}

	public BigDecimal getInvD004() {
		return this.invD004;
	}

	public void setInvD004(BigDecimal invD004) {
		this.invD004 = invD004;
	}

	public BigDecimal getInvD005() {
		return this.invD005;
	}

	public void setInvD005(BigDecimal invD005) {
		this.invD005 = invD005;
	}

	public BigDecimal getInvD006() {
		return this.invD006;
	}

	public void setInvD006(BigDecimal invD006) {
		this.invD006 = invD006;
	}

}