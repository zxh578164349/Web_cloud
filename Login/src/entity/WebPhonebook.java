package entity;

/**
 * WebPhonebook entity. @author MyEclipse Persistence Tools
 */

public class WebPhonebook implements java.io.Serializable {

	// Fields

	private Long pbId;
	private String username;
	private String phoneA;
	private String phoneB;
	private String phoneC;
	private String department;
	private String post;
	private String email;
	private VWebFact fact;

	// Constructors

	/** default constructor */
	public WebPhonebook() {
	}

	/** minimal constructor */
	public WebPhonebook(Long pbId) {
		this.pbId = pbId;
	}

	/** full constructor */
	public WebPhonebook(Long pbId, String username,
			String phoneA, String phoneB, String phoneC) {
		this.pbId = pbId;
		this.username = username;
		this.phoneA = phoneA;
		this.phoneB = phoneB;
		this.phoneC = phoneC;
	}

	// Property accessors

	public Long getPbId() {
		return this.pbId;
	}

	public void setPbId(Long pbId) {
		this.pbId = pbId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getPhoneA() {
		return this.phoneA;
	}

	public void setPhoneA(String phoneA) {
		this.phoneA = phoneA;
	}

	public String getPhoneB() {
		return this.phoneB;
	}

	public void setPhoneB(String phoneB) {
		this.phoneB = phoneB;
	}

	public String getPhoneC() {
		return this.phoneC;
	}

	public void setPhoneC(String phoneC) {
		this.phoneC = phoneC;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public VWebFact getFact() {
		return fact;
	}

	public void setFact(VWebFact fact) {
		this.fact = fact;
	}

	
	
	


	

}