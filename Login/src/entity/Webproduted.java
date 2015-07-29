package entity;

/**
 * Webproduted entity. @author MyEclipse Persistence Tools
 */

public class Webproduted implements java.io.Serializable {

	// Fields

	private WebprodutedId id;
	private Double storenum;
	private Double noliststorenum;
	private Double makestorenum;
	private Double outnum;
	private Double innum;
	private Double instorenum;
	private Double minusnum;
	private String username;

	// Constructors

	/** default constructor */
	public Webproduted() {
	}

	/** minimal constructor */
	public Webproduted(WebprodutedId id) {
		this.id = id;
	}

	/** full constructor */
	public Webproduted(WebprodutedId id, Double storenum,
			Double noliststorenum, Double makestorenum, Double outnum,
			Double innum, Double instorenum, Double minusnum, String username) {
		this.id = id;
		this.storenum = storenum;
		this.noliststorenum = noliststorenum;
		this.makestorenum = makestorenum;
		this.outnum = outnum;
		this.innum = innum;
		this.instorenum = instorenum;
		this.minusnum = minusnum;
		this.username = username;
	}

	// Property accessors

	public WebprodutedId getId() {
		return this.id;
	}

	public void setId(WebprodutedId id) {
		this.id = id;
	}

	public Double getStorenum() {
		return this.storenum;
	}

	public void setStorenum(Double storenum) {
		this.storenum = storenum;
	}

	public Double getNoliststorenum() {
		return this.noliststorenum;
	}

	public void setNoliststorenum(Double noliststorenum) {
		this.noliststorenum = noliststorenum;
	}

	public Double getMakestorenum() {
		return this.makestorenum;
	}

	public void setMakestorenum(Double makestorenum) {
		this.makestorenum = makestorenum;
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

	public Double getInstorenum() {
		return this.instorenum;
	}

	public void setInstorenum(Double instorenum) {
		this.instorenum = instorenum;
	}

	public Double getMinusnum() {
		return this.minusnum;
	}

	public void setMinusnum(Double minusnum) {
		this.minusnum = minusnum;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}