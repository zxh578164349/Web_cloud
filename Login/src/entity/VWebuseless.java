package entity;

import java.math.BigDecimal;

/**
 * VWebuseless entity. @author MyEclipse Persistence Tools
 */

public class VWebuseless implements java.io.Serializable {

	// Fields

	private VWebuselessId id;
	private BigDecimal webA1=new BigDecimal(0.0);
	private BigDecimal webA2=new BigDecimal(0.0);
	private BigDecimal webA3=new BigDecimal(0.0);
	private BigDecimal webA4=new BigDecimal(0.0);
	private Double webA5=0.0;
	private BigDecimal webA6=new BigDecimal(0.0);
	private Double webA7=0.0;
	private Double webA8=0.0;
	private BigDecimal webA9=new BigDecimal(0.0);
	private BigDecimal webA10=new BigDecimal(0.0);

	// Constructors

	/** default constructor */
	public VWebuseless() {
	}

	/** minimal constructor */
	public VWebuseless(VWebuselessId id) {
		this.id = id;
	}

	/** full constructor */
	public VWebuseless(VWebuselessId id, BigDecimal webA1, BigDecimal webA2,
			BigDecimal webA3, BigDecimal webA4, Double webA5, BigDecimal webA6,
			Double webA7, Double webA8, BigDecimal webA9, BigDecimal webA10) {
		this.id = id;
		this.webA1 = webA1;
		this.webA2 = webA2;
		this.webA3 = webA3;
		this.webA4 = webA4;
		this.webA5 = webA5;
		this.webA6 = webA6;
		this.webA7 = webA7;
		this.webA8 = webA8;
		this.webA9 = webA9;
		this.webA10 = webA10;
	}

	// Property accessors

	public VWebuselessId getId() {
		return this.id;
	}

	public void setId(VWebuselessId id) {
		this.id = id;
	}

	public BigDecimal getWebA1() {
		return this.webA1;
	}

	public void setWebA1(BigDecimal webA1) {
		this.webA1 = webA1;
	}

	public BigDecimal getWebA2() {
		return this.webA2;
	}

	public void setWebA2(BigDecimal webA2) {
		this.webA2 = webA2;
	}

	public BigDecimal getWebA3() {
		return this.webA3;
	}

	public void setWebA3(BigDecimal webA3) {
		this.webA3 = webA3;
	}

	public BigDecimal getWebA4() {
		return this.webA4;
	}

	public void setWebA4(BigDecimal webA4) {
		this.webA4 = webA4;
	}

	public Double getWebA5() {
		return this.webA5;
	}

	public void setWebA5(Double webA5) {
		this.webA5 = webA5;
	}

	public BigDecimal getWebA6() {
		return this.webA6;
	}

	public void setWebA6(BigDecimal webA6) {
		this.webA6 = webA6;
	}

	public Double getWebA7() {
		return this.webA7;
	}

	public void setWebA7(Double webA7) {
		this.webA7 = webA7;
	}

	public Double getWebA8() {
		return this.webA8;
	}

	public void setWebA8(Double webA8) {
		this.webA8 = webA8;
	}

	public BigDecimal getWebA9() {
		return this.webA9;
	}

	public void setWebA9(BigDecimal webA9) {
		this.webA9 = webA9;
	}

	public BigDecimal getWebA10() {
		return this.webA10;
	}

	public void setWebA10(BigDecimal webA10) {
		this.webA10 = webA10;
	}

}