package entity;

import java.math.BigDecimal;

/**
 * WebYielePrediction entity. @author MyEclipse Persistence Tools
 */

public class WebYielePrediction implements java.io.Serializable {

	// Fields

	private WebYielePredictionId id;
	private Double forceAnalysis;
	private Double expectedProduction;
	private Double expectedPayment;
	private Double hole;
	private Double positiveNumber;
	private Double sample;
	private Double accessories;
	private Double other;
	private String username;

	// Constructors

	/** default constructor */
	public WebYielePrediction() {
	}

	/** minimal constructor */
	public WebYielePrediction(WebYielePredictionId id) {
		this.id = id;
	}

	/** full constructor */
	public WebYielePrediction(WebYielePredictionId id, Double forceAnalysis,
			Double expectedProduction, Double expectedPayment, Double hole,
			Double positiveNumber, Double sample, Double accessories,
			Double other, String username) {
		this.id = id;
		this.forceAnalysis = forceAnalysis;
		this.expectedProduction = expectedProduction;
		this.expectedPayment = expectedPayment;
		this.hole = hole;
		this.positiveNumber = positiveNumber;
		this.sample = sample;
		this.accessories = accessories;
		this.other = other;
		this.username = username;
	}

	// Property accessors

	public WebYielePredictionId getId() {
		return this.id;
	}

	public void setId(WebYielePredictionId id) {
		this.id = id;
	}

	public Double getForceAnalysis() {
		return this.forceAnalysis;
	}

	public void setForceAnalysis(Double forceAnalysis) {
		this.forceAnalysis = forceAnalysis;
	}

	public Double getExpectedProduction() {
		return this.expectedProduction;
	}

	public void setExpectedProduction(Double expectedProduction) {
		this.expectedProduction = expectedProduction;
	}

	public Double getExpectedPayment() {
		return this.expectedPayment;
	}

	public void setExpectedPayment(Double expectedPayment) {
		this.expectedPayment = expectedPayment;
	}

	

	

	public Double getHole() {
		return hole;
	}

	public void setHole(Double hole) {
		this.hole = hole;
	}

	public Double getPositiveNumber() {
		return this.positiveNumber;
	}

	public void setPositiveNumber(Double positiveNumber) {
		this.positiveNumber = positiveNumber;
	}

	public Double getSample() {
		return this.sample;
	}

	public void setSample(Double sample) {
		this.sample = sample;
	}

	public Double getAccessories() {
		return this.accessories;
	}

	public void setAccessories(Double accessories) {
		this.accessories = accessories;
	}

	public Double getOther() {
		return this.other;
	}

	public void setOther(Double other) {
		this.other = other;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}