package entity;

import java.math.BigDecimal;

/**
 * VWebobjA entity. @author MyEclipse Persistence Tools
 */

public class VWebobjA implements java.io.Serializable {

	// Fields

	private VWebobjAId id;
	private BigDecimal objA1;
	private BigDecimal objA2;
	private BigDecimal objA3;
	private BigDecimal objA4;
	private BigDecimal objA5;
	private BigDecimal objA6;
	private BigDecimal objA7;
	private BigDecimal objA8;
	private BigDecimal objA9;
	private BigDecimal objA10;
	private BigDecimal objA11;

	// Constructors

	/** default constructor */
	public VWebobjA() {
	}

	/** minimal constructor */
	public VWebobjA(VWebobjAId id) {
		this.id = id;
	}

	/** full constructor */
	public VWebobjA(VWebobjAId id, BigDecimal objA1, BigDecimal objA2,
			BigDecimal objA3, BigDecimal objA4, BigDecimal objA5,
			BigDecimal objA6, BigDecimal objA7, BigDecimal objA8,
			BigDecimal objA9, BigDecimal objA10, BigDecimal objA11) {
		this.id = id;
		this.objA1 = objA1;
		this.objA2 = objA2;
		this.objA3 = objA3;
		this.objA4 = objA4;
		this.objA5 = objA5;
		this.objA6 = objA6;
		this.objA7 = objA7;
		this.objA8 = objA8;
		this.objA9 = objA9;
		this.objA10 = objA10;
		this.objA11 = objA11;
	}

	// Property accessors

	public VWebobjAId getId() {
		return this.id;
	}

	public void setId(VWebobjAId id) {
		this.id = id;
	}

	public BigDecimal getObjA1() {
		return this.objA1;
	}

	public void setObjA1(BigDecimal objA1) {
		this.objA1 = objA1;
	}

	public BigDecimal getObjA2() {
		return this.objA2;
	}

	public void setObjA2(BigDecimal objA2) {
		this.objA2 = objA2;
	}

	public BigDecimal getObjA3() {
		return this.objA3;
	}

	public void setObjA3(BigDecimal objA3) {
		this.objA3 = objA3;
	}

	public BigDecimal getObjA4() {
		return this.objA4;
	}

	public void setObjA4(BigDecimal objA4) {
		this.objA4 = objA4;
	}

	public BigDecimal getObjA5() {
		return this.objA5;
	}

	public void setObjA5(BigDecimal objA5) {
		this.objA5 = objA5;
	}

	public BigDecimal getObjA6() {
		return this.objA6;
	}

	public void setObjA6(BigDecimal objA6) {
		this.objA6 = objA6;
	}

	public BigDecimal getObjA7() {
		return this.objA7;
	}

	public void setObjA7(BigDecimal objA7) {
		this.objA7 = objA7;
	}

	public BigDecimal getObjA8() {
		return this.objA8;
	}

	public void setObjA8(BigDecimal objA8) {
		this.objA8 = objA8;
	}

	public BigDecimal getObjA9() {
		return this.objA9;
	}

	public void setObjA9(BigDecimal objA9) {
		this.objA9 = objA9;
	}

	public BigDecimal getObjA10() {
		return this.objA10;
	}

	public void setObjA10(BigDecimal objA10) {
		this.objA10 = objA10;
	}

	public BigDecimal getObjA11() {
		return this.objA11;
	}

	public void setObjA11(BigDecimal objA11) {
		this.objA11 = objA11;
	}

}