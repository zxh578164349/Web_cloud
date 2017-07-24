package entity;

import java.math.BigDecimal;

/**
 * VWebBrProandest entity. @author MyEclipse Persistence Tools
 */

public class VWebBrProandest implements java.io.Serializable{

	// Fields

	private VWebBrProandestId id;
	private BigDecimal inventory;
	private BigDecimal ordernotin;
	private BigDecimal actualused;
	private Double actualpairs;
	private Double estimatingpairs1;
	private Double estimatingpairs2;
	private Double estimatingpairs3;

	// Constructors

	/** default constructor */
	public VWebBrProandest(){
	}

	/** minimal constructor */
	public VWebBrProandest(VWebBrProandestId id){
		this.id=id;
	}

	/** full constructor */
	public VWebBrProandest(VWebBrProandestId id,BigDecimal inventory,BigDecimal ordernotin,BigDecimal actualused,Double actualpairs,Double estimatingpairs1,
			Double estimatingpairs2,Double estimatingpairs3){
		this.id=id;
		this.inventory=inventory;
		this.ordernotin=ordernotin;
		this.actualused=actualused;
		this.actualpairs=actualpairs;
		this.estimatingpairs1=estimatingpairs1;
		this.estimatingpairs2=estimatingpairs2;
		this.estimatingpairs3=estimatingpairs3;
	}

	// Property accessors

	public VWebBrProandestId getId(){
		return this.id;
	}

	public void setId(VWebBrProandestId id){
		this.id=id;
	}

	public BigDecimal getInventory(){
		return this.inventory;
	}

	public void setInventory(BigDecimal inventory){
		this.inventory=inventory;
	}

	public BigDecimal getOrdernotin(){
		return this.ordernotin;
	}

	public void setOrdernotin(BigDecimal ordernotin){
		this.ordernotin=ordernotin;
	}

	public BigDecimal getActualused(){
		return this.actualused;
	}

	public void setActualused(BigDecimal actualused){
		this.actualused=actualused;
	}

	public Double getActualpairs(){
		return this.actualpairs;
	}

	public void setActualpairs(Double actualpairs){
		this.actualpairs=actualpairs;
	}

	public Double getEstimatingpairs1(){
		return this.estimatingpairs1;
	}

	public void setEstimatingpairs1(Double estimatingpairs1){
		this.estimatingpairs1=estimatingpairs1;
	}

	public Double getEstimatingpairs2(){
		return this.estimatingpairs2;
	}

	public void setEstimatingpairs2(Double estimatingpairs2){
		this.estimatingpairs2=estimatingpairs2;
	}

	public Double getEstimatingpairs3(){
		return this.estimatingpairs3;
	}

	public void setEstimatingpairs3(Double estimatingpairs3){
		this.estimatingpairs3=estimatingpairs3;
	}

}