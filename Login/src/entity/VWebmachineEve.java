package entity;

import java.math.BigDecimal;

/**
 * VWebmachineEve entity. @author MyEclipse Persistence Tools
 */

public class VWebmachineEve implements java.io.Serializable {

	// Fields

	private VWebmachineEveId id;
	private BigDecimal sumWorkday;
	private BigDecimal sumEverydemo;
	private BigDecimal sumStandarddemo;
	private BigDecimal sumActualdemo;
	private BigDecimal sumActualpairs;
	private Double avgbuttomweight;
	private Double avgbuttomweight2;
	private Double hole;

	// Constructors

	/** default constructor */
	public VWebmachineEve() {
	}

	/** minimal constructor */
	public VWebmachineEve(VWebmachineEveId id, Double hole) {
		this.id = id;
		this.hole = hole;
	}

	/** full constructor */
	public VWebmachineEve(VWebmachineEveId id, BigDecimal sumWorkday,
			BigDecimal sumEverydemo, BigDecimal sumStandarddemo,
			BigDecimal sumActualdemo, BigDecimal sumActualpairs,
			Double avgbuttomweight, Double avgbuttomweight2, Double hole) {
		this.id = id;
		this.sumWorkday = sumWorkday;
		this.sumEverydemo = sumEverydemo;
		this.sumStandarddemo = sumStandarddemo;
		this.sumActualdemo = sumActualdemo;
		this.sumActualpairs = sumActualpairs;
		this.avgbuttomweight = avgbuttomweight;
		this.avgbuttomweight2 = avgbuttomweight2;
		this.hole = hole;
	}

	// Property accessors

	public VWebmachineEveId getId() {
		return this.id;
	}

	public void setId(VWebmachineEveId id) {
		this.id = id;
	}

	public BigDecimal getSumWorkday() {
		return this.sumWorkday;
	}

	public void setSumWorkday(BigDecimal sumWorkday) {
		this.sumWorkday = sumWorkday;
	}

	public BigDecimal getSumEverydemo() {
		return this.sumEverydemo;
	}

	public void setSumEverydemo(BigDecimal sumEverydemo) {
		this.sumEverydemo = sumEverydemo;
	}

	public BigDecimal getSumStandarddemo() {
		return this.sumStandarddemo;
	}

	public void setSumStandarddemo(BigDecimal sumStandarddemo) {
		this.sumStandarddemo = sumStandarddemo;
	}

	public BigDecimal getSumActualdemo() {
		return this.sumActualdemo;
	}

	public void setSumActualdemo(BigDecimal sumActualdemo) {
		this.sumActualdemo = sumActualdemo;
	}

	public BigDecimal getSumActualpairs() {
		return this.sumActualpairs;
	}

	public void setSumActualpairs(BigDecimal sumActualpairs) {
		this.sumActualpairs = sumActualpairs;
	}

	public Double getAvgbuttomweight() {
		return this.avgbuttomweight;
	}

	public void setAvgbuttomweight(Double avgbuttomweight) {
		this.avgbuttomweight = avgbuttomweight;
	}

	public Double getAvgbuttomweight2() {
		return this.avgbuttomweight2;
	}

	public void setAvgbuttomweight2(Double avgbuttomweight2) {
		this.avgbuttomweight2 = avgbuttomweight2;
	}

	public Double getHole() {
		return this.hole;
	}

	public void setHole(Double hole) {
		this.hole = hole;
	}

}