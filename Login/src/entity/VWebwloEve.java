package entity;

import java.math.BigDecimal;

/**
 * VWebwloEve entity. @author MyEclipse Persistence Tools
 */

public class VWebwloEve implements java.io.Serializable {

	// Fields

	private VWebwloEveId id;
	private BigDecimal sumactualdemo;
	private Double waterton;
	private Double waterusd;
	private Double electricdu;
	private Double electricusd;
	private Double gaston;
	private Double gasusd;
	private Double oilton;
	private Double oilusd;

	// Constructors

	/** default constructor */
	public VWebwloEve() {
	}

	/** minimal constructor */
	public VWebwloEve(VWebwloEveId id) {
		this.id = id;
	}

	/** full constructor */
	public VWebwloEve(VWebwloEveId id, BigDecimal sumactualdemo,
			Double waterton, Double waterusd, Double electricdu,
			Double electricusd, Double gaston, Double gasusd, Double oilton,
			Double oilusd) {
		this.id = id;
		this.sumactualdemo = sumactualdemo;
		this.waterton = waterton;
		this.waterusd = waterusd;
		this.electricdu = electricdu;
		this.electricusd = electricusd;
		this.gaston = gaston;
		this.gasusd = gasusd;
		this.oilton = oilton;
		this.oilusd = oilusd;
	}

	// Property accessors

	public VWebwloEveId getId() {
		return this.id;
	}

	public void setId(VWebwloEveId id) {
		this.id = id;
	}

	public BigDecimal getSumactualdemo() {
		return this.sumactualdemo;
	}

	public void setSumactualdemo(BigDecimal sumactualdemo) {
		this.sumactualdemo = sumactualdemo;
	}

	public Double getWaterton() {
		return this.waterton;
	}

	public void setWaterton(Double waterton) {
		this.waterton = waterton;
	}

	public Double getWaterusd() {
		return this.waterusd;
	}

	public void setWaterusd(Double waterusd) {
		this.waterusd = waterusd;
	}

	public Double getElectricdu() {
		return this.electricdu;
	}

	public void setElectricdu(Double electricdu) {
		this.electricdu = electricdu;
	}

	public Double getElectricusd() {
		return this.electricusd;
	}

	public void setElectricusd(Double electricusd) {
		this.electricusd = electricusd;
	}

	public Double getGaston() {
		return this.gaston;
	}

	public void setGaston(Double gaston) {
		this.gaston = gaston;
	}

	public Double getGasusd() {
		return this.gasusd;
	}

	public void setGasusd(Double gasusd) {
		this.gasusd = gasusd;
	}

	public Double getOilton() {
		return this.oilton;
	}

	public void setOilton(Double oilton) {
		this.oilton = oilton;
	}

	public Double getOilusd() {
		return this.oilusd;
	}

	public void setOilusd(Double oilusd) {
		this.oilusd = oilusd;
	}

}