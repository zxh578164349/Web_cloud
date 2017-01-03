package entity;

/**
 * VWebUserEmailId entity. @author MyEclipse Persistence Tools
 */

public class VWebUserEmailId implements java.io.Serializable{

	// Fields

	private String factNo;
	private String email;

	// Constructors

	/** default constructor */
	public VWebUserEmailId(){
	}

	/** full constructor */
	public VWebUserEmailId(String factNo,String email){
		this.factNo=factNo;
		this.email=email;
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

	public boolean equals(Object other){
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VWebUserEmailId))
			return false;
		VWebUserEmailId castOther=(VWebUserEmailId)other;

		return ((this.getFactNo() == castOther.getFactNo()) || (this.getFactNo() != null && castOther.getFactNo() != null && this.getFactNo().equals(
				castOther.getFactNo())))
				&& ((this.getEmail() == castOther.getEmail()) || (this.getEmail() != null && castOther.getEmail() != null && this.getEmail().equals(
						castOther.getEmail())));
	}

	public int hashCode(){
		int result=17;

		result=37 * result + (getFactNo() == null ? 0 : this.getFactNo().hashCode());
		result=37 * result + (getEmail() == null ? 0 : this.getEmail().hashCode());
		return result;
	}

}