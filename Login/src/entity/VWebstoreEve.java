package entity;

import java.math.BigDecimal;

/**
 * VWebstoreEve entity. @author MyEclipse Persistence Tools
 */

public class VWebstoreEve implements java.io.Serializable {

	// Fields

	private VWebstoreEveId id;
	private BigDecimal sumhostpairs;
	private BigDecimal sumsamplepairs;
	private Double invcount;
	private Double hostinvcount;
	private Double badcount;
	private BigDecimal lagStorenum;
	private BigDecimal lagNoliststrorenum;
	private BigDecimal lagMakestrorenum;
	private BigDecimal lagOutnum;
	private BigDecimal lagInnum;
	private BigDecimal lagInstorenum;
	private Double storenum;
	private Double noliststrorenum;
	private Double makestrorenum;
	private Double outnum;
	private Double innum;
	private Double minusnum;

	// Constructors

	/** default constructor */
	public VWebstoreEve() {
	}

	/** minimal constructor */
	public VWebstoreEve(VWebstoreEveId id) {
		this.id = id;
	}

	/** full constructor */
	public VWebstoreEve(VWebstoreEveId id, BigDecimal sumhostpairs,
			BigDecimal sumsamplepairs, Double invcount, Double hostinvcount,
			Double badcount, BigDecimal lagStorenum,
			BigDecimal lagNoliststrorenum, BigDecimal lagMakestrorenum,
			BigDecimal lagOutnum, BigDecimal lagInnum,
			BigDecimal lagInstorenum, Double storenum, Double noliststrorenum,
			Double makestrorenum, Double outnum, Double innum, Double minusnum) {
		this.id = id;
		this.sumhostpairs = sumhostpairs;
		this.sumsamplepairs = sumsamplepairs;
		this.invcount = invcount;
		this.hostinvcount = hostinvcount;
		this.badcount = badcount;
		this.lagStorenum = lagStorenum;
		this.lagNoliststrorenum = lagNoliststrorenum;
		this.lagMakestrorenum = lagMakestrorenum;
		this.lagOutnum = lagOutnum;
		this.lagInnum = lagInnum;
		this.lagInstorenum = lagInstorenum;
		this.storenum = storenum;
		this.noliststrorenum = noliststrorenum;
		this.makestrorenum = makestrorenum;
		this.outnum = outnum;
		this.innum = innum;
		this.minusnum = minusnum;
	}

	// Property accessors

	public VWebstoreEveId getId() {
		return this.id;
	}

	public void setId(VWebstoreEveId id) {
		this.id = id;
	}

	public BigDecimal getSumhostpairs() {
		return this.sumhostpairs;
	}

	public void setSumhostpairs(BigDecimal sumhostpairs) {
		this.sumhostpairs = sumhostpairs;
	}

	public BigDecimal getSumsamplepairs() {
		return this.sumsamplepairs;
	}

	public void setSumsamplepairs(BigDecimal sumsamplepairs) {
		this.sumsamplepairs = sumsamplepairs;
	}

	public Double getInvcount() {
		return this.invcount;
	}

	public void setInvcount(Double invcount) {
		this.invcount = invcount;
	}

	public Double getHostinvcount() {
		return this.hostinvcount;
	}

	public void setHostinvcount(Double hostinvcount) {
		this.hostinvcount = hostinvcount;
	}

	public Double getBadcount() {
		return this.badcount;
	}

	public void setBadcount(Double badcount) {
		this.badcount = badcount;
	}

	public BigDecimal getLagStorenum() {
		return this.lagStorenum;
	}

	public void setLagStorenum(BigDecimal lagStorenum) {
		this.lagStorenum = lagStorenum;
	}

	public BigDecimal getLagNoliststrorenum() {
		return this.lagNoliststrorenum;
	}

	public void setLagNoliststrorenum(BigDecimal lagNoliststrorenum) {
		this.lagNoliststrorenum = lagNoliststrorenum;
	}

	public BigDecimal getLagMakestrorenum() {
		return this.lagMakestrorenum;
	}

	public void setLagMakestrorenum(BigDecimal lagMakestrorenum) {
		this.lagMakestrorenum = lagMakestrorenum;
	}

	public BigDecimal getLagOutnum() {
		return this.lagOutnum;
	}

	public void setLagOutnum(BigDecimal lagOutnum) {
		this.lagOutnum = lagOutnum;
	}

	public BigDecimal getLagInnum() {
		return this.lagInnum;
	}

	public void setLagInnum(BigDecimal lagInnum) {
		this.lagInnum = lagInnum;
	}

	public BigDecimal getLagInstorenum() {
		return this.lagInstorenum;
	}

	public void setLagInstorenum(BigDecimal lagInstorenum) {
		this.lagInstorenum = lagInstorenum;
	}

	public Double getStorenum() {
		return this.storenum;
	}

	public void setStorenum(Double storenum) {
		this.storenum = storenum;
	}

	public Double getNoliststrorenum() {
		return this.noliststrorenum;
	}

	public void setNoliststrorenum(Double noliststrorenum) {
		this.noliststrorenum = noliststrorenum;
	}

	public Double getMakestrorenum() {
		return this.makestrorenum;
	}

	public void setMakestrorenum(Double makestrorenum) {
		this.makestrorenum = makestrorenum;
	}

	public Double getOutnum() {
		return this.outnum;
	}

	public void setOutnum(Double outnum) {
		this.outnum = outnum;
	}

	public Double getInnum() {
		return this.innum;
	}

	public void setInnum(Double innum) {
		this.innum = innum;
	}

	public Double getMinusnum() {
		return this.minusnum;
	}

	public void setMinusnum(Double minusnum) {
		this.minusnum = minusnum;
	}

}