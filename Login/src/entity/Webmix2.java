package entity;

/**
 * Webmix2 entity. @author MyEclipse Persistence Tools
 */

public class Webmix2 implements java.io.Serializable {

	// Fields
	private Webmix2Id id;
	private Double invcount;
	private Double hostinvcount;
	private Double sellcount;
	private Double costcount;
	private Double wagezgUsd;
	private Double wagejgUsd;
	private Double addmoneyzg;
	private Double addmoneyjg;
	private Double cashcount;
	private Double other;
	private String username;
	private Double payPairs;
	private String usernameUd;

	// Constructors

	/** default constructor */
	public Webmix2() {
	}

	/** minimal constructor */
	public Webmix2(Webmix2Id id) {
		this.id = id;
	}

	/** full constructor */
	public Webmix2(Webmix2Id id, Double invcount, Double hostinvcount,
			Double sellcount, Double costcount, Double wagezgUsd,
			Double wagejgUsd, Double addmoneyzg, Double addmoneyjg,
			Double cashcount, Double other, String username,Double payPairs) {
		this.id = id;
		this.invcount = invcount;
		this.hostinvcount = hostinvcount;
		this.sellcount = sellcount;
		this.costcount = costcount;
		this.wagezgUsd = wagezgUsd;
		this.wagejgUsd = wagejgUsd;
		this.addmoneyzg = addmoneyzg;
		this.addmoneyjg = addmoneyjg;
		this.cashcount = cashcount;
		this.other = other;
		this.username = username;
		this.payPairs=payPairs;
	}

	// Property accessors

	public Webmix2Id getId() {
		return this.id;
	}

	public void setId(Webmix2Id id) {
		this.id = id;
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

	public Double getCashcount() {
		return this.cashcount;
	}

	public void setCashcount(Double cashcount) {
		this.cashcount = cashcount;
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

	public Double getPayPairs() {
		return payPairs;
	}

	public void setPayPairs(Double payPairs) {
		this.payPairs = payPairs;
	}

	public String getUsernameUd() {
		return usernameUd;
	}

	public void setUsernameUd(String usernameUd) {
		this.usernameUd = usernameUd;
	}
	

}