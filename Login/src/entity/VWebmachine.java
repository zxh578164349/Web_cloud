package entity;

import java.math.BigDecimal;

/**
 * VWebmachine entity. @author MyEclipse Persistence Tools
 */

public class VWebmachine implements java.io.Serializable {

	// Fields

	private VWebmachineId id;
	private Double VWebmcA001;
	private BigDecimal VWebmcA002;
	private BigDecimal VWebmcA003;
	private BigDecimal VWebmcA004;
	private BigDecimal VWebmcA005;
	private Double VWebmcA006;
	private Double VWebmcA007;
	private BigDecimal VWebmcA008;
	private BigDecimal VWebmcA009;
	private BigDecimal VWebmcA010;
	private BigDecimal VWebmcA011;

	// Constructors

	/** default constructor */
	public VWebmachine() {
	}

	/** minimal constructor */
	public VWebmachine(VWebmachineId id, Double VWebmcA001) {
		this.id = id;
		this.VWebmcA001 = VWebmcA001;
	}

	/** full constructor */
	public VWebmachine(VWebmachineId id, Double VWebmcA001,
			BigDecimal VWebmcA002, BigDecimal VWebmcA003,
			BigDecimal VWebmcA004, BigDecimal VWebmcA005, Double VWebmcA006,
			Double VWebmcA007, BigDecimal VWebmcA008, BigDecimal VWebmcA009,
			BigDecimal VWebmcA010, BigDecimal VWebmcA011) {
		this.id = id;
		this.VWebmcA001 = VWebmcA001;
		this.VWebmcA002 = VWebmcA002;
		this.VWebmcA003 = VWebmcA003;
		this.VWebmcA004 = VWebmcA004;
		this.VWebmcA005 = VWebmcA005;
		this.VWebmcA006 = VWebmcA006;
		this.VWebmcA007 = VWebmcA007;
		this.VWebmcA008 = VWebmcA008;
		this.VWebmcA009 = VWebmcA009;
		this.VWebmcA010 = VWebmcA010;
		this.VWebmcA011 = VWebmcA011;
	}

	// Property accessors

	public VWebmachineId getId() {
		return this.id;
	}

	public void setId(VWebmachineId id) {
		this.id = id;
	}

	public Double getVWebmcA001() {
		return this.VWebmcA001;
	}

	public void setVWebmcA001(Double VWebmcA001) {
		this.VWebmcA001 = VWebmcA001;
	}

	public BigDecimal getVWebmcA002() {
		return this.VWebmcA002;
	}

	public void setVWebmcA002(BigDecimal VWebmcA002) {
		this.VWebmcA002 = VWebmcA002;
	}

	public BigDecimal getVWebmcA003() {
		return this.VWebmcA003;
	}

	public void setVWebmcA003(BigDecimal VWebmcA003) {
		this.VWebmcA003 = VWebmcA003;
	}

	public BigDecimal getVWebmcA004() {
		return this.VWebmcA004;
	}

	public void setVWebmcA004(BigDecimal VWebmcA004) {
		this.VWebmcA004 = VWebmcA004;
	}

	public BigDecimal getVWebmcA005() {
		return this.VWebmcA005;
	}

	public void setVWebmcA005(BigDecimal VWebmcA005) {
		this.VWebmcA005 = VWebmcA005;
	}

	public Double getVWebmcA006() {
		return this.VWebmcA006;
	}

	public void setVWebmcA006(Double VWebmcA006) {
		this.VWebmcA006 = VWebmcA006;
	}

	public Double getVWebmcA007() {
		return this.VWebmcA007;
	}

	public void setVWebmcA007(Double VWebmcA007) {
		this.VWebmcA007 = VWebmcA007;
	}

	public BigDecimal getVWebmcA008() {
		return this.VWebmcA008;
	}

	public void setVWebmcA008(BigDecimal VWebmcA008) {
		this.VWebmcA008 = VWebmcA008;
	}

	public BigDecimal getVWebmcA009() {
		return this.VWebmcA009;
	}

	public void setVWebmcA009(BigDecimal VWebmcA009) {
		this.VWebmcA009 = VWebmcA009;
	}

	public BigDecimal getVWebmcA010() {
		return this.VWebmcA010;
	}

	public void setVWebmcA010(BigDecimal VWebmcA010) {
		this.VWebmcA010 = VWebmcA010;
	}

	public BigDecimal getVWebmcA011() {
		return this.VWebmcA011;
	}

	public void setVWebmcA011(BigDecimal VWebmcA011) {
		this.VWebmcA011 = VWebmcA011;
	}

}