package entity;

/**
 * WebBrProductitemId entity. @author MyEclipse Persistence Tools
 */

public class WebBrProductitemId implements java.io.Serializable{

	// Fields
	private String yymmdd;
	private WebBrProduct webBrProduct;

	// Constructors

	/** default constructor */
	public WebBrProductitemId(){
	}

	/** full constructor */
	public WebBrProductitemId(String yymmdd,WebBrProduct webBrProduct){
		this.yymmdd=yymmdd;
		this.webBrProduct=webBrProduct;
	}

	// Property accessors

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

		return ((this.getYymmdd() == castOther.getYymmdd()) || (this.getYymmdd() != null && castOther.getYymmdd() != null && this.getYymmdd().equals(
						castOther.getYymmdd())))
				&& ((this.getWebBrProduct() == castOther.getWebBrProduct()) || (this.getWebBrProduct() != null && castOther.getWebBrProduct() != null && this
						.getWebBrProduct().equals(castOther.getWebBrProduct())));
	}

	public int hashCode(){
		int result=17;

		result=37 * result + (getYymmdd() == null ? 0 : this.getYymmdd().hashCode());
		result=37 * result + (getWebBrProduct() == null ? 0 : this.getWebBrProduct().hashCode());
		return result;
	}

}