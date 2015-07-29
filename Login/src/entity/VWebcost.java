package entity;

import java.math.BigDecimal;

/**
 * VWebcost entity. @author MyEclipse Persistence Tools
 */

public class VWebcost implements java.io.Serializable {

	// Fields

	private VWebcostId id;
	private Double VWebcostJ001;
	private BigDecimal VWebcostJ002;
	private Double VWebcostJ003;
	private BigDecimal VWebcostJ004;
	private Double VWebcostJ005;
	private BigDecimal VWebcostJ006;
	private Double VWebcostJ007;
	private Double VWebcostJ008;
	private Double VWebcostJ009;
	private Double VWebcostJ010;
	private Double VWebcostJ011;

	// Constructors

	/** default constructor */
	public VWebcost() {
	}

	/** minimal constructor */
	public VWebcost(VWebcostId id) {
		this.id = id;
	}

	/** full constructor */
	public VWebcost(VWebcostId id, Double VWebcostJ001,
			BigDecimal VWebcostJ002, Double VWebcostJ003,
			BigDecimal VWebcostJ004, Double VWebcostJ005,
			BigDecimal VWebcostJ006, Double VWebcostJ007, Double VWebcostJ008,
			Double VWebcostJ009, Double VWebcostJ010, Double VWebcostJ011) {
		this.id = id;
		this.VWebcostJ001 = VWebcostJ001;
		this.VWebcostJ002 = VWebcostJ002;
		this.VWebcostJ003 = VWebcostJ003;
		this.VWebcostJ004 = VWebcostJ004;
		this.VWebcostJ005 = VWebcostJ005;
		this.VWebcostJ006 = VWebcostJ006;
		this.VWebcostJ007 = VWebcostJ007;
		this.VWebcostJ008 = VWebcostJ008;
		this.VWebcostJ009 = VWebcostJ009;
		this.VWebcostJ010 = VWebcostJ010;
		this.VWebcostJ011 = VWebcostJ011;
	}

	// Property accessors

	public VWebcostId getId() {
		return this.id;
	}

	public void setId(VWebcostId id) {
		this.id = id;
	}

	public Double getVWebcostJ001() {
		return this.VWebcostJ001;
	}

	public void setVWebcostJ001(Double VWebcostJ001) {
		this.VWebcostJ001 = VWebcostJ001;
	}

	public BigDecimal getVWebcostJ002() {
		return this.VWebcostJ002;
	}

	public void setVWebcostJ002(BigDecimal VWebcostJ002) {
		this.VWebcostJ002 = VWebcostJ002;
	}

	public Double getVWebcostJ003() {
		return this.VWebcostJ003;
	}

	public void setVWebcostJ003(Double VWebcostJ003) {
		this.VWebcostJ003 = VWebcostJ003;
	}

	public BigDecimal getVWebcostJ004() {
		return this.VWebcostJ004;
	}

	public void setVWebcostJ004(BigDecimal VWebcostJ004) {
		this.VWebcostJ004 = VWebcostJ004;
	}

	public Double getVWebcostJ005() {
		return this.VWebcostJ005;
	}

	public void setVWebcostJ005(Double VWebcostJ005) {
		this.VWebcostJ005 = VWebcostJ005;
	}

	public BigDecimal getVWebcostJ006() {
		return this.VWebcostJ006;
	}

	public void setVWebcostJ006(BigDecimal VWebcostJ006) {
		this.VWebcostJ006 = VWebcostJ006;
	}

	public Double getVWebcostJ007() {
		return this.VWebcostJ007;
	}

	public void setVWebcostJ007(Double VWebcostJ007) {
		this.VWebcostJ007 = VWebcostJ007;
	}

	public Double getVWebcostJ008() {
		return this.VWebcostJ008;
	}

	public void setVWebcostJ008(Double VWebcostJ008) {
		this.VWebcostJ008 = VWebcostJ008;
	}

	public Double getVWebcostJ009() {
		return this.VWebcostJ009;
	}

	public void setVWebcostJ009(Double VWebcostJ009) {
		this.VWebcostJ009 = VWebcostJ009;
	}

	public Double getVWebcostJ010() {
		return this.VWebcostJ010;
	}

	public void setVWebcostJ010(Double VWebcostJ010) {
		this.VWebcostJ010 = VWebcostJ010;
	}

	public Double getVWebcostJ011() {
		return this.VWebcostJ011;
	}

	public void setVWebcostJ011(Double VWebcostJ011) {
		this.VWebcostJ011 = VWebcostJ011;
	}

}