package entity;

/**
 * Hong33Id entity. @author MyEclipse Persistence Tools
 */

public class Hong33Id implements java.io.Serializable{

	// Fields

	private String factno;
	private String visasort;
	private String email;
	private WebDepartment webDepartment;

	// Constructors

	/** default constructor */
	public Hong33Id(){
	}

	/** full constructor */
	public Hong33Id(String factno,String visasort,String email,WebDepartment webDepartment){
		this.factno=factno;
		this.visasort=visasort;
		this.email=email;
		this.webDepartment=webDepartment;
	}

	// Property accessors

	public String getFactno(){
		return this.factno;
	}

	public void setFactno(String factno){
		this.factno=factno;
	}

	public String getVisasort(){
		return this.visasort;
	}

	public void setVisasort(String visasort){
		this.visasort=visasort;
	}

	public String getEmail(){
		return this.email;
	}

	public void setEmail(String email){
		this.email=email;
	}

	public WebDepartment getWebDepartment(){
		return this.webDepartment;
	}

	public void setWebDepartment(WebDepartment webDepartment){
		this.webDepartment=webDepartment;
	}

	public boolean equals(Object other){
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Hong33Id))
			return false;
		Hong33Id castOther=(Hong33Id)other;

		return ((this.getFactno() == castOther.getFactno()) || (this.getFactno() != null && castOther.getFactno() != null && this.getFactno().equals(
				castOther.getFactno())))
				&& ((this.getVisasort() == castOther.getVisasort()) || (this.getVisasort() != null && castOther.getVisasort() != null && this.getVisasort()
						.equals(castOther.getVisasort())))
				&& ((this.getEmail() == castOther.getEmail()) || (this.getEmail() != null && castOther.getEmail() != null && this.getEmail().equals(
						castOther.getEmail())))
				&& ((this.getWebDepartment() == castOther.getWebDepartment()) || (this.getWebDepartment() != null && castOther.getWebDepartment() != null && this
						.getWebDepartment().equals(castOther.getWebDepartment())));
	}

	public int hashCode(){
		int result=17;

		result=37 * result + (getFactno() == null ? 0 : this.getFactno().hashCode());
		result=37 * result + (getVisasort() == null ? 0 : this.getVisasort().hashCode());
		result=37 * result + (getEmail() == null ? 0 : this.getEmail().hashCode());
		result=37 * result + (getWebDepartment() == null ? 0 : this.getWebDepartment().hashCode());
		return result;
	}

}