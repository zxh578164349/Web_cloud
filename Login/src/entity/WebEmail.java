package entity;

/**
 * WebEmail entity. @author MyEclipse Persistence Tools
 */

public class WebEmail implements java.io.Serializable {

	// Fields

	private Integer id;
	private String email;
	private String pwd;
	private String name;
	private String sengif;

	// Constructors

	/** default constructor */
	public WebEmail() {
	}

	/** minimal constructor */
	public WebEmail(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public WebEmail(Integer id, String email, String pwd, String name,
			String sengif) {
		this.id = id;
		this.email = email;
		this.pwd = pwd;
		this.name = name;
		this.sengif = sengif;
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

	public String getSengif() {
		return this.sengif;
	}

	public void setSengif(String sengif) {
		this.sengif = sengif;
	}

}