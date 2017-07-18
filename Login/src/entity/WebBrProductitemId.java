package entity;

/**
 * WebBrProductitemId entity. @author MyEclipse Persistence Tools
 */

public class WebBrProductitemId implements java.io.Serializable{

	// Fields

	private String factNo;
	private String yymmdd;
	private WebBrProduct webBrProduct;

	// Constructors

	/** default constructor */
	public WebBrProductitemId(){
	}

	/** full constructor */
	public WebBrProductitemId(String factNo,String yymmdd,WebBrProduct webBrProduct){
		this.factNo=factNo;
		this.yymmdd=yymmdd;
		this.webBrProduct=webBrProduct;
	}

	// Property accessors

	public String getFactNo(){
		return this.factNo;
	}

	public void setFactNo(String factNo){
		this.factNo=factNo;
	}

	public String getYymmdd(){
		return this.yymmdd;
	}

	public void setYymmdd(String yymmdd){
		this.yymmdd=yymmdd;
	}

	public WebBrProduct getWebBrProduct(){
		return this.webBrProduct;
	}

	public void setWebBrProduct(WebBrProduct webBrProduct){
		this.webBrProduct=webBrProduct;
	}

	public boolean equals(Object other){
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WebBrProductitemId))
			return false;
		WebBrProductitemId castOther=(WebBrProductitemId)other;

		return ((this.getFactNo() == castOther.getFactNo()) || (this.getFactNo() != null && castOther.getFactNo() != null && this.getFactNo().equals(
				castOther.getFactNo())))
				&& ((this.getYymmdd() == castOther.getYymmdd()) || (this.getYymmdd() != null && castOther.getYymmdd() != null && this.getYymmdd().equals(
						castOther.getYymmdd())))
				&& ((this.getWebBrProduct() == castOther.getWebBrProduct()) || (this.getWebBrProduct() != null && castOther.getWebBrProduct() != null && this
						.getWebBrProduct().equals(castOther.getWebBrProduct())));
	}

	public int hashCode(){
		int result=17;

		result=37 * result + (getFactNo() == null ? 0 : this.getFactNo().hashCode());
		result=37 * result + (getYymmdd() == null ? 0 : this.getYymmdd().hashCode());
		result=37 * result + (getWebBrProduct() == null ? 0 : this.getWebBrProduct().hashCode());
		return result;
	}

}