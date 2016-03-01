package entity;

/**
 * Webwlo entity. @author MyEclipse Persistence Tools
 */

public class Webwlo implements java.io.Serializable {

	// Fields

	private WebwloId id;
	private Double waterton;
	private Double waterusd;
	private Double electricdu;
	private Double electricusd;
	private Double gaston;
	private Double gasusd;
	private Double oilton;
	private Double oilusd;
	private String username;
	private Double repiarMoney;
	private String usernameUd;

	// Constructors

	/** default constructor */
	public Webwlo() {
	}

	/** minimal constructor */
	public Webwlo(WebwloId id) {
		this.id = id;
	}

	/** full constructor */
	public Webwlo(WebwloId id, Double waterton, Double waterusd,
			Double electricdu, Double electricusd, Double gaston,
			Double gasusd, Double oilton, Double oilusd, String username,Double repiarMoney) {
		this.id = id;
		this.waterton = waterton;
		this.waterusd = waterusd;
		this.electricdu = electricdu;
		this.electricusd = electricusd;
		this.gaston = gaston;
		this.gasusd = gasusd;
		this.oilton = oilton;
		this.oilusd = oilusd;
		this.username = username;
		this.repiarMoney=repiarMoney;
	}

	// Property accessors

	public WebwloId getId() {
		return this.id;
	}

	public void setId(WebwloId id) {
		this.id = id;
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

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Double getRepiarMoney() {
		return repiarMoney;
	}

	public void setRepiarMoney(Double repiarMoney) {
		this.repiarMoney = repiarMoney;
	}

	public String getUsernameUd() {
		return usernameUd;
	}

	public void setUsernameUd(String usernameUd) {
		this.usernameUd = usernameUd;
	}
	

}