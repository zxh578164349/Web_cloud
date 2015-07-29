package entity;

import java.math.BigDecimal;

/**
 * SumWebYieldData entity. @author MyEclipse Persistence Tools
 */

public class SumWebYieldData implements java.io.Serializable {

	// Fields

	private SumWebYieldDataId id;
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
	private String startDate;
	private String endDate;
	private Double sumWorkhours;
	private String username;

	// Constructors

	/** default constructor */
	public SumWebYieldData() {
	}

	/** minimal constructor */
	public SumWebYieldData(SumWebYieldDataId id) {
		this.id = id;
	}

	/** full constructor */
	public SumWebYieldData(SumWebYieldDataId id, BigDecimal sumWorkdays,
			BigDecimal sumEverypeople, BigDecimal sumEverydemo,
			BigDecimal sumStandarddemo, BigDecimal sumActualdemo,
			BigDecimal sumActualpairs, BigDecimal sumHostpairs,
			BigDecimal sumFactpairs, BigDecimal sumSamplepairs,
			BigDecimal sumOutnum, BigDecimal sumBacknum, String startDate,
			String endDate,Double sumWorkhours,String username) {
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
		this.startDate = startDate;
		this.endDate = endDate;
		this.sumWorkhours=sumWorkhours;
		this.username=username;
	}

	// Property accessors

	public SumWebYieldDataId getId() {
		return this.id;
	}

	public void setId(SumWebYieldDataId id) {
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

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Double getSumWorkhours() {
		return sumWorkhours;
	}

	public void setSumWorkhours(Double sumWorkhours) {
		this.sumWorkhours = sumWorkhours;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

}