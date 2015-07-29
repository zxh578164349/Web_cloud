package entity;

import java.math.BigDecimal;

/**
 * VSumWebmix1 entity. @author MyEclipse Persistence Tools
 */

public class VSumWebmix1 implements java.io.Serializable {

	// Fields

	private VSumWebmix1Id id;
	private BigDecimal sumWorkdays;
	private BigDecimal sumEverypeople;
	private BigDecimal sumEverydemo;
	private BigDecimal sumStandarddemo;
	private BigDecimal sumActualdemo;
	private BigDecimal sumActualpairs;
	private BigDecimal sumHostpairs;
	private BigDecimal sumFactpairs;
	private BigDecimal sumSamplepairs;
	private BigDecimal sumOutnum;
	private BigDecimal sumBacknum;
	private BigDecimal sumWorkhours;

	// Constructors

	/** default constructor */
	public VSumWebmix1() {
	}

	/** minimal constructor */
	public VSumWebmix1(VSumWebmix1Id id) {
		this.id = id;
	}

	/** full constructor */
	public VSumWebmix1(VSumWebmix1Id id, BigDecimal sumWorkdays,
			BigDecimal sumEverypeople, BigDecimal sumEverydemo,
			BigDecimal sumStandarddemo, BigDecimal sumActualdemo,
			BigDecimal sumActualpairs, BigDecimal sumHostpairs,
			BigDecimal sumFactpairs, BigDecimal sumSamplepairs,
			BigDecimal sumOutnum, BigDecimal sumBacknum,BigDecimal sumWorkhours) {
		this.id = id;
		this.sumWorkdays = sumWorkdays;
		this.sumEverypeople = sumEverypeople;
		this.sumEverydemo = sumEverydemo;
		this.sumStandarddemo = sumStandarddemo;
		this.sumActualdemo = sumActualdemo;
		this.sumActualpairs = sumActualpairs;
		this.sumHostpairs = sumHostpairs;
		this.sumFactpairs = sumFactpairs;
		this.sumSamplepairs = sumSamplepairs;
		this.sumOutnum = sumOutnum;
		this.sumBacknum = sumBacknum;
		this.sumWorkhours=sumWorkhours;
	}

	// Property accessors

	public VSumWebmix1Id getId() {
		return this.id;
	}

	public void setId(VSumWebmix1Id id) {
		this.id = id;
	}

	public BigDecimal getSumWorkdays() {
		return this.sumWorkdays;
	}

	public void setSumWorkdays(BigDecimal sumWorkdays) {
		this.sumWorkdays = sumWorkdays;
	}

	public BigDecimal getSumEverypeople() {
		return this.sumEverypeople;
	}

	public void setSumEverypeople(BigDecimal sumEverypeople) {
		this.sumEverypeople = sumEverypeople;
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

	public BigDecimal getSumHostpairs() {
		return this.sumHostpairs;
	}

	public void setSumHostpairs(BigDecimal sumHostpairs) {
		this.sumHostpairs = sumHostpairs;
	}

	public BigDecimal getSumFactpairs() {
		return this.sumFactpairs;
	}

	public void setSumFactpairs(BigDecimal sumFactpairs) {
		this.sumFactpairs = sumFactpairs;
	}

	public BigDecimal getSumSamplepairs() {
		return this.sumSamplepairs;
	}

	public void setSumSamplepairs(BigDecimal sumSamplepairs) {
		this.sumSamplepairs = sumSamplepairs;
	}

	public BigDecimal getSumOutnum() {
		return this.sumOutnum;
	}

	public void setSumOutnum(BigDecimal sumOutnum) {
		this.sumOutnum = sumOutnum;
	}

	public BigDecimal getSumBacknum() {
		return this.sumBacknum;
	}

	public void setSumBacknum(BigDecimal sumBacknum) {
		this.sumBacknum = sumBacknum;
	}

	public BigDecimal getSumWorkhours() {
		return sumWorkhours;
	}

	public void setSumWorkhours(BigDecimal sumWorkhours) {
		this.sumWorkhours = sumWorkhours;
	}
	

}