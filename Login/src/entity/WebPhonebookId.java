package entity;

/**
 * WebPhonebookId entity. @author MyEclipse Persistence Tools
 */

public class WebPhonebookId implements java.io.Serializable {

	// Fields

	private String username;
	//private String factno;
	private VWebFact fact;
	private String phoneA;
	private String phoneB;
	private String phoneC;
	private String department;
	private String post;
	private String email;

	// Constructors

	/** default constructor */
	public WebPhonebookId() {
	}

	/** full constructor */
	public WebPhonebookId(String username, VWebFact fact, String phoneA,
			String phoneB, String phoneC, String department, String post,
			String email) {
		this.username = username;
		this.fact = fact;
		this.phoneA = phoneA;
		this.phoneB = phoneB;
		this.phoneC = phoneC;
		this.department = department;
		this.post = post;
		this.email = email;
	}

	// Property accessors

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	public VWebFact getFact() {
		return fact;
	}

	public void setFact(VWebFact fact) {
		this.fact = fact;
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
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPost() {
		return this.post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WebPhonebookId))
			return false;
		WebPhonebookId castOther = (WebPhonebookId) other;

		return ((this.getUsername() == castOther.getUsername()) || (this
				.getUsername() != null && castOther.getUsername() != null && this
				.getUsername().equals(castOther.getUsername())))
				&& ((this.getFact() == castOther.getFact()) || (this
						.getFact() != null && castOther.getFact() != null && this
						.getFact().equals(castOther.getFact())))
				&& ((this.getPhoneA() == castOther.getPhoneA()) || (this
						.getPhoneA() != null && castOther.getPhoneA() != null && this
						.getPhoneA().equals(castOther.getPhoneA())))
				&& ((this.getPhoneB() == castOther.getPhoneB()) || (this
						.getPhoneB() != null && castOther.getPhoneB() != null && this
						.getPhoneB().equals(castOther.getPhoneB())))
				&& ((this.getPhoneC() == castOther.getPhoneC()) || (this
						.getPhoneC() != null && castOther.getPhoneC() != null && this
						.getPhoneC().equals(castOther.getPhoneC())))
				&& ((this.getDepartment() == castOther.getDepartment()) || (this
						.getDepartment() != null
						&& castOther.getDepartment() != null && this
						.getDepartment().equals(castOther.getDepartment())))
				&& ((this.getPost() == castOther.getPost()) || (this.getPost() != null
						&& castOther.getPost() != null && this.getPost()
						.equals(castOther.getPost())))
				&& ((this.getEmail() == castOther.getEmail()) || (this
						.getEmail() != null && castOther.getEmail() != null && this
						.getEmail().equals(castOther.getEmail())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUsername() == null ? 0 : this.getUsername().hashCode());
		result = 37 * result
				+ (getFact() == null ? 0 : this.getFact().hashCode());
		result = 37 * result
				+ (getPhoneA() == null ? 0 : this.getPhoneA().hashCode());
		result = 37 * result
				+ (getPhoneB() == null ? 0 : this.getPhoneB().hashCode());
		result = 37 * result
				+ (getPhoneC() == null ? 0 : this.getPhoneC().hashCode());
		result = 37
				* result
				+ (getDepartment() == null ? 0 : this.getDepartment()
						.hashCode());
		result = 37 * result
				+ (getPost() == null ? 0 : this.getPost().hashCode());
		result = 37 * result
				+ (getEmail() == null ? 0 : this.getEmail().hashCode());
		return result;
	}

}