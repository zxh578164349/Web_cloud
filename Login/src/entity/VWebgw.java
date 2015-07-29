package entity;

import java.math.BigDecimal;

/**
 * VWebgw entity. @author MyEclipse Persistence Tools
 */

public class VWebgw implements java.io.Serializable {

	// Fields

	private VWebgwId id;
	private Double VWebgwE001;
	private BigDecimal VWebgwE002;
	private BigDecimal VWebgwE003;
	private BigDecimal VWebgwE004;
	private BigDecimal VWebgwE005;
	private BigDecimal VWebgwE006;

	// Constructors

	/** default constructor */
	public VWebgw() {
	}

	/** minimal constructor */
	public VWebgw(VWebgwId id) {
		this.id = id;
	}

	/** full constructor */
	public VWebgw(VWebgwId id, Double VWebgwE001, BigDecimal VWebgwE002,
			BigDecimal VWebgwE003, BigDecimal VWebgwE004,
			BigDecimal VWebgwE005, BigDecimal VWebgwE006) {
		this.id = id;
		this.VWebgwE001 = VWebgwE001;
		this.VWebgwE002 = VWebgwE002;
		this.VWebgwE003 = VWebgwE003;
		this.VWebgwE004 = VWebgwE004;
		this.VWebgwE005 = VWebgwE005;
		this.VWebgwE006 = VWebgwE006;
	}

	// Property accessors

	public VWebgwId getId() {
		return this.id;
	}

	public void setId(VWebgwId id) {
		this.id = id;
	}

	public Double getVWebgwE001() {
		return this.VWebgwE001;
	}

	public void setVWebgwE001(Double VWebgwE001) {
		this.VWebgwE001 = VWebgwE001;
	}

	public BigDecimal getVWebgwE002() {
		return this.VWebgwE002;
	}

	public void setVWebgwE002(BigDecimal VWebgwE002) {
		this.VWebgwE002 = VWebgwE002;
	}

	public BigDecimal getVWebgwE003() {
		return this.VWebgwE003;
	}

	public void setVWebgwE003(BigDecimal VWebgwE003) {
		this.VWebgwE003 = VWebgwE003;
	}

	public BigDecimal getVWebgwE004() {
		return this.VWebgwE004;
	}

	public void setVWebgwE004(BigDecimal VWebgwE004) {
		this.VWebgwE004 = VWebgwE004;
	}

	public BigDecimal getVWebgwE005() {
		return this.VWebgwE005;
	}

	public void setVWebgwE005(BigDecimal VWebgwE005) {
		this.VWebgwE005 = VWebgwE005;
	}

	public BigDecimal getVWebgwE006() {
		return this.VWebgwE006;
	}

	public void setVWebgwE006(BigDecimal VWebgwE006) {
		this.VWebgwE006 = VWebgwE006;
	}

}