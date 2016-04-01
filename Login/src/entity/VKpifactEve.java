package entity;

import java.math.BigDecimal;

/**
 * VKpifactEve entity. @author MyEclipse Persistence Tools
 */

public class VKpifactEve implements java.io.Serializable {

	// Fields

	private VKpifactEveId id;
	private BigDecimal sumEverydemo=new BigDecimal(0.0);
	private BigDecimal sumStandarddemo=new BigDecimal(0.0);
	private BigDecimal sumActualdemo=new BigDecimal(0.0);
	private BigDecimal sumActualpairs=new BigDecimal(0.0);
	private BigDecimal sumFacpairs=new BigDecimal(0.0);;
	private Double personzg=0.0;
	private Double personjg=0.0;
	private Double timezg=0.0;
	private Double timejg=0.0;
	private Double addtimezg=0.0;
	private Double addtimejg=0.0;
	private Double leavenumzg=0.0;
	private Double leavenumjg=0.0;
	private Double hurtnum=0.0;
	private Double invcount=0.0;
	private Double sellcount=0.0;
	private Double costcount=0.0;
	private Double wagezgUsd=0.0;
	private Double wagejgUsd=0.0;
	private Double cashcount=0.0;
	private Double sideweit=0.0;
	private Double badweit=0.0;
	private Double otherbadweight=0.0;
	private Double waterton=0.0;
	private Double electricdu=0.0;
	private Double gaston=0.0;
	private Double storenum=0.0;
	private Double outnum=0.0;
	private Double minusnum=0.0;
	private Double actlost=0.0;
	private Double avgbuttomweight2=0.0;
	private BigDecimal otherweight=new BigDecimal(0.0);
	private Double productednum=0.0;
	private Double noglueweight=0.0;
	private Double repairmoney=0.0;
	private Double workhours=0.0;
	private Double instorenum=0.0;
	private Double hole=0.0;
	private BigDecimal sumWorkday=new BigDecimal(0.0);
	private Double waterusd=0.0;
	private Double electricusd=0.0;
	private Double gasusd=0.0;
	private Double thickused=0.0;
	private Double backfeed=0.0;
	private Double oilback=0.0;
	private Double drugsused=0.0;
	private Double colorused=0.0;
	private Double leavemoney=0.0;
	private Double paypairs=0.0;
	private Double badcount=0.0;

	// Constructors

	/** default constructor */
	public VKpifactEve() {
	}

	/** minimal constructor */
	public VKpifactEve(VKpifactEveId id) {
		this.id = id;
	}

	/** full constructor */
	public VKpifactEve(VKpifactEveId id, BigDecimal sumEverydemo,
			BigDecimal sumStandarddemo, BigDecimal sumActualdemo,
			BigDecimal sumActualpairs, BigDecimal sumFacpairs, Double personzg,
			Double personjg, Double timezg, Double timejg, Double addtimezg,
			Double addtimejg, Double leavenumzg, Double leavenumjg,
			Double hurtnum, Double invcount, Double sellcount,
			Double costcount, Double wagezgUsd, Double wagejgUsd,
			Double cashcount, Double sideweit, Double badweit,
			Double otherbadweight, Double waterton, Double electricdu,
			Double gaston, Double storenum, Double outnum, Double minusnum,
			Double actlost, Double avgbuttomweight2, BigDecimal otherweight,
			Double productednum, Double noglueweight, Double repairmoney,
			Double workhours, Double instorenum, Double hole,
			BigDecimal sumWorkday, Double waterusd, Double electricusd,
			Double gasusd, Double thickused, Double backfeed, Double oilback,
			Double drugsused, Double colorused, Double leavemoney,
			Double paypairs, Double badcount) {
		this.id = id;
		this.sumEverydemo = sumEverydemo;
		this.sumStandarddemo = sumStandarddemo;
		this.sumActualdemo = sumActualdemo;
		this.sumActualpairs = sumActualpairs;
		this.sumFacpairs = sumFacpairs;
		this.personzg = personzg;
		this.personjg = personjg;
		this.timezg = timezg;
		this.timejg = timejg;
		this.addtimezg = addtimezg;
		this.addtimejg = addtimejg;
		this.leavenumzg = leavenumzg;
		this.leavenumjg = leavenumjg;
		this.hurtnum = hurtnum;
		this.invcount = invcount;
		this.sellcount = sellcount;
		this.costcount = costcount;
		this.wagezgUsd = wagezgUsd;
		this.wagejgUsd = wagejgUsd;
		this.cashcount = cashcount;
		this.sideweit = sideweit;
		this.badweit = badweit;
		this.otherbadweight = otherbadweight;
		this.waterton = waterton;
		this.electricdu = electricdu;
		this.gaston = gaston;
		this.storenum = storenum;
		this.outnum = outnum;
		this.minusnum = minusnum;
		this.actlost = actlost;
		this.avgbuttomweight2 = avgbuttomweight2;
		this.otherweight = otherweight;
		this.productednum = productednum;
		this.noglueweight = noglueweight;
		this.repairmoney = repairmoney;
		this.workhours = workhours;
		this.instorenum = instorenum;
		this.hole = hole;
		this.sumWorkday = sumWorkday;
		this.waterusd = waterusd;
		this.electricusd = electricusd;
		this.gasusd = gasusd;
		this.thickused = thickused;
		this.backfeed = backfeed;
		this.oilback = oilback;
		this.drugsused = drugsused;
		this.colorused = colorused;
		this.leavemoney = leavemoney;
		this.paypairs = paypairs;
		this.badcount = badcount;
	}

	// Property accessors

	public VKpifactEveId getId() {
		return this.id;
	}

	public void setId(VKpifactEveId id) {
		this.id = id;
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

	public BigDecimal getSumFacpairs() {
		return this.sumFacpairs;
	}

	public void setSumFacpairs(BigDecimal sumFacpairs) {
		this.sumFacpairs = sumFacpairs;
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

	public Double getHurtnum() {
		return this.hurtnum;
	}

	public void setHurtnum(Double hurtnum) {
		this.hurtnum = hurtnum;
	}

	public Double getInvcount() {
		return this.invcount;
	}

	public void setInvcount(Double invcount) {
		this.invcount = invcount;
	}

	public Double getSellcount() {
		return this.sellcount;
	}

	public void setSellcount(Double sellcount) {
		this.sellcount = sellcount;
	}

	public Double getCostcount() {
		return this.costcount;
	}

	public void setCostcount(Double costcount) {
		this.costcount = costcount;
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

	public Double getCashcount() {
		return this.cashcount;
	}

	public void setCashcount(Double cashcount) {
		this.cashcount = cashcount;
	}

	public Double getSideweit() {
		return this.sideweit;
	}

	public void setSideweit(Double sideweit) {
		this.sideweit = sideweit;
	}

	public Double getBadweit() {
		return this.badweit;
	}

	public void setBadweit(Double badweit) {
		this.badweit = badweit;
	}

	public Double getOtherbadweight() {
		return this.otherbadweight;
	}

	public void setOtherbadweight(Double otherbadweight) {
		this.otherbadweight = otherbadweight;
	}

	public Double getWaterton() {
		return this.waterton;
	}

	public void setWaterton(Double waterton) {
		this.waterton = waterton;
	}

	public Double getElectricdu() {
		return this.electricdu;
	}

	public void setElectricdu(Double electricdu) {
		this.electricdu = electricdu;
	}

	public Double getGaston() {
		return this.gaston;
	}

	public void setGaston(Double gaston) {
		this.gaston = gaston;
	}

	public Double getStorenum() {
		return this.storenum;
	}

	public void setStorenum(Double storenum) {
		this.storenum = storenum;
	}

	public Double getOutnum() {
		return this.outnum;
	}

	public void setOutnum(Double outnum) {
		this.outnum = outnum;
	}

	public Double getMinusnum() {
		return this.minusnum;
	}

	public void setMinusnum(Double minusnum) {
		this.minusnum = minusnum;
	}

	public Double getActlost() {
		return this.actlost;
	}

	public void setActlost(Double actlost) {
		this.actlost = actlost;
	}

	public Double getAvgbuttomweight2() {
		return this.avgbuttomweight2;
	}

	public void setAvgbuttomweight2(Double avgbuttomweight2) {
		this.avgbuttomweight2 = avgbuttomweight2;
	}

	public BigDecimal getOtherweight() {
		return this.otherweight;
	}

	public void setOtherweight(BigDecimal otherweight) {
		this.otherweight = otherweight;
	}

	public Double getProductednum() {
		return this.productednum;
	}

	public void setProductednum(Double productednum) {
		this.productednum = productednum;
	}

	public Double getNoglueweight() {
		return this.noglueweight;
	}

	public void setNoglueweight(Double noglueweight) {
		this.noglueweight = noglueweight;
	}

	public Double getRepairmoney() {
		return this.repairmoney;
	}

	public void setRepairmoney(Double repairmoney) {
		this.repairmoney = repairmoney;
	}

	public Double getWorkhours() {
		return this.workhours;
	}

	public void setWorkhours(Double workhours) {
		this.workhours = workhours;
	}

	public Double getInstorenum() {
		return this.instorenum;
	}

	public void setInstorenum(Double instorenum) {
		this.instorenum = instorenum;
	}

	public Double getHole() {
		return this.hole;
	}

	public void setHole(Double hole) {
		this.hole = hole;
	}

	public BigDecimal getSumWorkday() {
		return this.sumWorkday;
	}

	public void setSumWorkday(BigDecimal sumWorkday) {
		this.sumWorkday = sumWorkday;
	}

	public Double getWaterusd() {
		return this.waterusd;
	}

	public void setWaterusd(Double waterusd) {
		this.waterusd = waterusd;
	}

	public Double getElectricusd() {
		return this.electricusd;
	}

	public void setElectricusd(Double electricusd) {
		this.electricusd = electricusd;
	}

	public Double getGasusd() {
		return this.gasusd;
	}

	public void setGasusd(Double gasusd) {
		this.gasusd = gasusd;
	}

	public Double getThickused() {
		return this.thickused;
	}

	public void setThickused(Double thickused) {
		this.thickused = thickused;
	}

	public Double getBackfeed() {
		return this.backfeed;
	}

	public void setBackfeed(Double backfeed) {
		this.backfeed = backfeed;
	}

	public Double getOilback() {
		return this.oilback;
	}

	public void setOilback(Double oilback) {
		this.oilback = oilback;
	}

	public Double getDrugsused() {
		return this.drugsused;
	}

	public void setDrugsused(Double drugsused) {
		this.drugsused = drugsused;
	}

	public Double getColorused() {
		return this.colorused;
	}

	public void setColorused(Double colorused) {
		this.colorused = colorused;
	}

	public Double getLeavemoney() {
		return this.leavemoney;
	}

	public void setLeavemoney(Double leavemoney) {
		this.leavemoney = leavemoney;
	}

	public Double getPaypairs() {
		return this.paypairs;
	}

	public void setPaypairs(Double paypairs) {
		this.paypairs = paypairs;
	}

	public Double getBadcount() {
		return this.badcount;
	}

	public void setBadcount(Double badcount) {
		this.badcount = badcount;
	}

}