package entity;

import java.math.BigDecimal;

/**
 * VWebcostEve entity. @author MyEclipse Persistence Tools
 */

public class VWebcostEve implements java.io.Serializable {

	// Fields

	private VWebcostEveId id;
	private BigDecimal sumActualdemo;
	private Double gluestoremoney;
	private Double drugsused;
	private Double leavemoney;
	private Double whitenum;
	private Double blacknum;
	private Double gluenum;
	private Double greynum;
	private Double othernum;

	// Constructors

	/** default constructor */
	public VWebcostEve() {
	}

	/** minimal constructor */
	public VWebcostEve(VWebcostEveId id) {
		this.id = id;
	}

	/** full constructor */
	public VWebcostEve(VWebcostEveId id, BigDecimal sumActualdemo,
			Double gluestoremoney, Double drugsused, Double leavemoney,
			Double whitenum, Double blacknum, Double gluenum, Double greynum,
			Double othernum) {
		this.id = id;
		this.sumActualdemo = sumActualdemo;
		this.gluestoremoney = gluestoremoney;
		this.drugsused = drugsused;
		this.leavemoney = leavemoney;
		this.whitenum = whitenum;
		this.blacknum = blacknum;
		this.gluenum = gluenum;
		this.greynum = greynum;
		this.othernum = othernum;
	}

	// Property accessors

	public VWebcostEveId getId() {
		return this.id;
	}

	public void setId(VWebcostEveId id) {
		this.id = id;
	}

	public BigDecimal getSumActualdemo() {
		return this.sumActualdemo;
	}

	public void setSumActualdemo(BigDecimal sumActualdemo) {
		this.sumActualdemo = sumActualdemo;
	}

	public Double getGluestoremoney() {
		return this.gluestoremoney;
	}

	public void setGluestoremoney(Double gluestoremoney) {
		this.gluestoremoney = gluestoremoney;
	}

	public Double getDrugsused() {
		return this.drugsused;
	}

	public void setDrugsused(Double drugsused) {
		this.drugsused = drugsused;
	}

	public Double getLeavemoney() {
		return this.leavemoney;
	}

	public void setLeavemoney(Double leavemoney) {
		this.leavemoney = leavemoney;
	}

	public Double getWhitenum() {
		return this.whitenum;
	}

	public void setWhitenum(Double whitenum) {
		this.whitenum = whitenum;
	}

	public Double getBlacknum() {
		return this.blacknum;
	}

	public void setBlacknum(Double blacknum) {
		this.blacknum = blacknum;
	}

	public Double getGluenum() {
		return this.gluenum;
	}

	public void setGluenum(Double gluenum) {
		this.gluenum = gluenum;
	}

	public Double getGreynum() {
		return this.greynum;
	}

	public void setGreynum(Double greynum) {
		this.greynum = greynum;
	}

	public Double getOthernum() {
		return this.othernum;
	}

	public void setOthernum(Double othernum) {
		this.othernum = othernum;
	}

}