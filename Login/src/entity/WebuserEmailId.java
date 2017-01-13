package entity;

/**
 * WebuserEmailId entity. @author MyEclipse Persistence Tools
 */

public class WebuserEmailId implements java.io.Serializable{

	// Fields

	private String factNo;
	private String email;
	private String emailpassword;
	private String typeMk;

	// Constructors

	/** default constructor */
	public WebuserEmailId(){
	}

	/** full constructor */
	public WebuserEmailId(String factNo,String email,String emailpassword,String typeMk){
		this.factNo=factNo;
		this.email=email;
		this.emailpassword=emailpassword;
		this.typeMk=typeMk;
	}

	// Property accessors

	public String getFactNo(){
		return this.factNo;
	}

	public void setFactNo(String factNo){
		this.factNo=factNo;
	}

	public String getEmail(){
		return this.email;
	}

	public void setEmail(String email){
		this.email=email;
	}

	public String getEmailpassword(){
		return this.emailpassword;
	}

	public void setEmailpassword(String emailpassword){
		this.emailpassword=emailpassword;
	}

	public String getTypeMk(){
		return this.typeMk;
	}

	public void setTypeMk(String typeMk){
		this.typeMk=typeMk;
	}

	public boolean equals(Object other){
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WebuserEmailId))
			return false;
		WebuserEmailId castOther=(WebuserEmailId)other;

		return ((this.getFactNo() == castOther.getFactNo()) || (this.getFactNo() != null && castOther.getFactNo() != null && this.getFactNo().equals(
				castOther.getFactNo())))
				&& ((this.getEmail() == castOther.getEmail()) || (this.getEmail() != null && castOther.getEmail() != null && this.getEmail().equals(
						castOther.getEmail())))
				&& ((this.getEmailpassword() == castOther.getEmailpassword()) || (this.getEmailpassword() != null && castOther.getEmailpassword() != null && this
						.getEmailpassword().equals(castOther.getEmailpassword())))
				&& ((this.getTypeMk() == castOther.getTypeMk()) || (this.getTypeMk() != null && castOther.getTypeMk() != null && this.getTypeMk().equals(
						castOther.getTypeMk())));
	}

	public int hashCode(){
		int result=17;

		result=37 * result + (getFactNo() == null ? 0 : this.getFactNo().hashCode());
		result=37 * result + (getEmail() == null ? 0 : this.getEmail().hashCode());
		result=37 * result + (getEmailpassword() == null ? 0 : this.getEmailpassword().hashCode());
		result=37 * result + (getTypeMk() == null ? 0 : this.getTypeMk().hashCode());
		return result;
	}

}