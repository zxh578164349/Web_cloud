package entity;

/**
 * WebCc entity. @author MyEclipse Persistence Tools
 */

public class WebCc implements java.io.Serializable {

	// Fields

	private Integer id;
	private String email;
	private String pwd;
	private String name;
	private String sendif;

	// Constructors

	/** default constructor */
	public WebCc() {
	}

	/** minimal constructor */
	public WebCc(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public WebCc(Integer id, String email, String pwd, String name,
			String sendif) {
		this.id = id;
		this.email = email;
		this.pwd = pwd;
		this.name = name;
		this.sendif = sendif;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSendif() {
		return this.sendif;
	}

	public void setSendif(String sendif) {
		this.sendif = sendif;
	}

}