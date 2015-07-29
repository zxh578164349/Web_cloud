package entity;

/**
 * Webpersonnum entity. @author MyEclipse Persistence Tools
 */

public class Webpersonnum implements java.io.Serializable {

	// Fields

	private WebpersonnumId id;
	private Double standardnumzg;
	private Double realnumzg;
	private Double standardnumjg;
	private Double realnumjg;
	private Double outstandardnum;
	private Double outrealnum;
	private String username;

	// Constructors

	/** default constructor */
	public Webpersonnum() {
	}

	/** minimal constructor */
	public Webpersonnum(WebpersonnumId id) {
		this.id = id;
	}

	/** full constructor */
	public Webpersonnum(WebpersonnumId id, Double standardnumzg,
			Double realnumzg, Double standardnumjg, Double realnumjg,
			Double outstandardnum, Double outrealnum, String username) {
		this.id = id;
		this.standardnumzg = standardnumzg;
		this.realnumzg = realnumzg;
		this.standardnumjg = standardnumjg;
		this.realnumjg = realnumjg;
		this.outstandardnum = outstandardnum;
		this.outrealnum = outrealnum;
		this.username = username;
	}

	// Property accessors

	public WebpersonnumId getId() {
		return this.id;
	}

	public void setId(WebpersonnumId id) {
		this.id = id;
	}

	public Double getStandardnumzg() {
		return this.standardnumzg;
	}

	public void setStandardnumzg(Double standardnumzg) {
		this.standardnumzg = standardnumzg;
	}

	public Double getRealnumzg() {
		return this.realnumzg;
	}

	public void setRealnumzg(Double realnumzg) {
		this.realnumzg = realnumzg;
	}

	public Double getStandardnumjg() {
		return this.standardnumjg;
	}

	public void setStandardnumjg(Double standardnumjg) {
		this.standardnumjg = standardnumjg;
	}

	public Double getRealnumjg() {
		return this.realnumjg;
	}

	public void setRealnumjg(Double realnumjg) {
		this.realnumjg = realnumjg;
	}

	public Double getOutstandardnum() {
		return this.outstandardnum;
	}

	public void setOutstandardnum(Double outstandardnum) {
		this.outstandardnum = outstandardnum;
	}

	public Double getOutrealnum() {
		return this.outrealnum;
	}

	public void setOutrealnum(Double outrealnum) {
		this.outrealnum = outrealnum;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}