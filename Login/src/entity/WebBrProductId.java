package entity;

/**
 * WebBrProductId entity. @author MyEclipse Persistence Tools
 */

public class WebBrProductId implements java.io.Serializable{

	// Fields

	private String factNo;
	private WebErpProductinFormation webErpProductinFormation;

	// Constructors

	/** default constructor */
	public WebBrProductId(){
	}

	/** full constructor */
	public WebBrProductId(String factNo,WebErpProductinFormation webErpProductinFormation){
		this.factNo=factNo;
		this.webErpProductinFormation=webErpProductinFormation;
	}

	// Property accessors

	public String getFactNo(){
		return this.factNo;
	}

	public void setFactNo(String factNo){
		this.factNo=factNo;
	}

	public WebErpProductinFormation getWebErpProductinFormation(){
		return this.webErpProductinFormation;
	}

	public void setWebErpProductinFormation(WebErpProductinFormation webErpProductinFormation){
		this.webErpProductinFormation=webErpProductinFormation;
	}

	public boolean equals(Object other){
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WebBrProductId))
			return false;
		WebBrProductId castOther=(WebBrProductId)other;

		return ((this.getFactNo() == castOther.getFactNo()) || (this.getFactNo() != null && castOther.getFactNo() != null && this.getFactNo().equals(
				castOther.getFactNo())))
				&& ((this.getWebErpProductinFormation() == castOther.getWebErpProductinFormation()) || (this.getWebErpProductinFormation() != null
						&& castOther.getWebErpProductinFormation() != null && this.getWebErpProductinFormation()
						.equals(castOther.getWebErpProductinFormation())));
	}

	public int hashCode(){
		int result=17;

		result=37 * result + (getFactNo() == null ? 0 : this.getFactNo().hashCode());
		result=37 * result + (getWebErpProductinFormation() == null ? 0 : this.getWebErpProductinFormation().hashCode());
		return result;
	}

}