package entity;

/**
 * Webmixperson entity. @author MyEclipse Persistence Tools
 */

public class Webmixperson implements java.io.Serializable {

	// Fields

	private WebmixpersonId id;
	private Double personzg;
	private Double personjg;
	private Double timezg;
	private Double timejg;
	private Double leavenumbzg;
	private Double leavenumjg;
	private Double addtimezg;
	private Double addtimejg;
	private Double hurtnum;
	private String username;
	private String usernameUd;

	// Constructors

	/** default constructor */
	public Webmixperson() {
	}

	/** minimal constructor */
	public Webmixperson(WebmixpersonId id) {
		this.id = id;
	}

	/** full constructor */
	public Webmixperson(WebmixpersonId id, Double personzg, Double personjg,
			Double timezg, Double timejg, Double leavenumbzg,
			Double leavenumjg, Double addtimezg, Double addtimejg,
			Double hurtnum, String username) {
		this.id = id;
		this.personzg = personzg;
		this.personjg = personjg;
		this.timezg = timezg;
		this.timejg = timejg;
		this.leavenumbzg = leavenumbzg;
		this.leavenumjg = leavenumjg;
		this.addtimezg = addtimezg;
		this.addtimejg = addtimejg;
		this.hurtnum = hurtnum;
		this.username = username;
	}

	// Property accessors

	public WebmixpersonId getId() {
		return this.id;
	}

	public void setId(WebmixpersonId id) {
		this.id = id;
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

	public Double getLeavenumbzg() {
		return this.leavenumbzg;
	}

	public void setLeavenumbzg(Double leavenumbzg) {
		this.leavenumbzg = leavenumbzg;
	}

	public Double getLeavenumjg() {
		return this.leavenumjg;
	}

	public void setLeavenumjg(Double leavenumjg) {
		this.leavenumjg = leavenumjg;
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

	public Double getHurtnum() {
		return this.hurtnum;
	}

	public void setHurtnum(Double hurtnum) {
		this.hurtnum = hurtnum;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsernameUd() {
		return usernameUd;
	}

	public void setUsernameUd(String usernameUd) {
		this.usernameUd = usernameUd;
	}
	

}