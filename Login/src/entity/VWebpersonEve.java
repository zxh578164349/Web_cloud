package entity;

import java.math.BigDecimal;

/**
 * VWebpersonEve entity. @author MyEclipse Persistence Tools
 */

public class VWebpersonEve implements java.io.Serializable {

	// Fields

	private VWebpersonEveId id;
	private Double personzg;
	private Double personjg;
	private Double timezg;
	private Double timejg;
	private Double addtimezg;
	private Double addtimejg;
	private Double leavenumzg;
	private Double leavenumjg;
	private Double wagezgUsd;
	private Double wagejgUsd;
	private Double addmoneyzg;
	private Double addmoneyjg;
	private BigDecimal sumStandarddemo;
	private BigDecimal sumActualdemo;
	private BigDecimal sumActualpairs;

	// Constructors

	/** default constructor */
	public VWebpersonEve() {
	}

	/** minimal constructor */
	public VWebpersonEve(VWebpersonEveId id) {
		this.id = id;
	}

	/** full constructor */
	public VWebpersonEve(VWebpersonEveId id, Double personzg, Double personjg,
			Double timezg, Double timejg, Double addtimezg, Double addtimejg,
			Double leavenumzg, Double leavenumjg, Double wagezgUsd,
			Double wagejgUsd, Double addmoneyzg, Double addmoneyjg,
			BigDecimal sumStandarddemo, BigDecimal sumActualdemo,
			BigDecimal sumActualpairs) {
		this.id = id;
		this.personzg = personzg;
		this.personjg = personjg;
		this.timezg = timezg;
		this.timejg = timejg;
		this.addtimezg = addtimezg;
		this.addtimejg = addtimejg;
		this.leavenumzg = leavenumzg;
		this.leavenumjg = leavenumjg;
		this.wagezgUsd = wagezgUsd;
		this.wagejgUsd = wagejgUsd;
		this.addmoneyzg = addmoneyzg;
		this.addmoneyjg = addmoneyjg;
		this.sumStandarddemo = sumStandarddemo;
		this.sumActualdemo = sumActualdemo;
		this.sumActualpairs = sumActualpairs;
	}

	// Property accessors

	public VWebpersonEveId getId() {
		return this.id;
	}

	public void setId(VWebpersonEveId id) {
		this.id = id;
	}

	public Double getPersonzg() {
		return this.personzg;
	}

	public void setPersonzg(Double personzg) {
		this.personzg = personzg;
	}

	public Double getPersonjg() {
		return this.personjg;
	}

	public void setPersonjg(Double personjg) {
		this.personjg = personjg;
	}

	public Double getTimezg() {
		return this.timezg;
	}

	public void setTimezg(Double timezg) {
		this.timezg = timezg;
	}

	public Double getTimejg() {
		return this.timejg;
	}

	public void setTimejg(Double timejg) {
		this.timejg = timejg;
	}

	public Double getAddtimezg() {
		return this.addtimezg;
	}

	public void setAddtimezg(Double addtimezg) {
		this.addtimezg = addtimezg;
	}

	public Double getAddtimejg() {
		return this.addtimejg;
	}

	public void setAddtimejg(Double addtimejg) {
		this.addtimejg = addtimejg;
	}

	public Double getLeavenumzg() {
		return this.leavenumzg;
	}

	public void setLeavenumzg(Double leavenumzg) {
		this.leavenumzg = leavenumzg;
	}

	public Double getLeavenumjg() {
		return this.leavenumjg;
	}

	public void setLeavenumjg(Double leavenumjg) {
		this.leavenumjg = leavenumjg;
	}

	public Double getWagezgUsd() {
		return this.wagezgUsd;
	}

	public void setWagezgUsd(Double wagezgUsd) {
		this.wagezgUsd = wagezgUsd;
	}

	public Double getWagejgUsd() {
		return this.wagejgUsd;
	}

	public void setWagejgUsd(Double wagejgUsd) {
		this.wagejgUsd = wagejgUsd;
	}

	public Double getAddmoneyzg() {
		return this.addmoneyzg;
	}

	public void setAddmoneyzg(Double addmoneyzg) {
		this.addmoneyzg = addmoneyzg;
	}

	public Double getAddmoneyjg() {
		return this.addmoneyjg;
	}

	public void setAddmoneyjg(Double addmoneyjg) {
		this.addmoneyjg = addmoneyjg;
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

}