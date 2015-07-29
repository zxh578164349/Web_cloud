package entity;

import java.math.BigDecimal;

/**
 * VWeboutputinv entity. @author MyEclipse Persistence Tools
 */

public class VWeboutputinv implements java.io.Serializable {

	// Fields

	private VWeboutputinvId id;
	private BigDecimal VWebinvB001;
	private Double VWebinvB002;
	private Double VWebinvB003;
	private BigDecimal VWebinvB004;

	// Constructors

	/** default constructor */
	public VWeboutputinv() {
	}

	/** minimal constructor */
	public VWeboutputinv(VWeboutputinvId id) {
		this.id = id;
	}

	/** full constructor */
	public VWeboutputinv(VWeboutputinvId id, BigDecimal VWebinvB001,
			Double VWebinvB002, Double VWebinvB003, BigDecimal VWebinvB004) {
		this.id = id;
		this.VWebinvB001 = VWebinvB001;
		this.VWebinvB002 = VWebinvB002;
		this.VWebinvB003 = VWebinvB003;
		this.VWebinvB004 = VWebinvB004;
	}

	// Property accessors

	public VWeboutputinvId getId() {
		return this.id;
	}

	public void setId(VWeboutputinvId id) {
		this.id = id;
	}

	public BigDecimal getVWebinvB001() {
		return this.VWebinvB001;
	}

	public void setVWebinvB001(BigDecimal VWebinvB001) {
		this.VWebinvB001 = VWebinvB001;
	}

	public Double getVWebinvB002() {
		return this.VWebinvB002;
	}

	public void setVWebinvB002(Double VWebinvB002) {
		this.VWebinvB002 = VWebinvB002;
	}

	public Double getVWebinvB003() {
		return this.VWebinvB003;
	}

	public void setVWebinvB003(Double VWebinvB003) {
		this.VWebinvB003 = VWebinvB003;
	}

	public BigDecimal getVWebinvB004() {
		return this.VWebinvB004;
	}

	public void setVWebinvB004(BigDecimal VWebinvB004) {
		this.VWebinvB004 = VWebinvB004;
	}

}