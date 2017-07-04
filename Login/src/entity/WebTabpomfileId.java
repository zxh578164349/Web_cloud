package entity;

/**
 * WebTabpomfileId entity. @author MyEclipse Persistence Tools
 */

public class WebTabpomfileId implements java.io.Serializable{

	// Fields

	private WebTabpom webTabpom;
	private String filename;

	// Constructors

	/** default constructor */
	public WebTabpomfileId(){
	}

	/** full constructor */
	public WebTabpomfileId(WebTabpom webTabpom,String filename){
		this.webTabpom=webTabpom;
		this.filename=filename;
	}

	// Property accessors

	public WebTabpom getWebTabpom(){
		return this.webTabpom;
	}

	public void setWebTabpom(WebTabpom webTabpom){
		this.webTabpom=webTabpom;
	}

	public String getFilename(){
		return this.filename;
	}

	public void setFilename(String filename){
		this.filename=filename;
	}

	public boolean equals(Object other){
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WebTabpomfileId))
			return false;
		WebTabpomfileId castOther=(WebTabpomfileId)other;

		return ((this.getWebTabpom() == castOther.getWebTabpom()) || (this.getWebTabpom() != null && castOther.getWebTabpom() != null && this.getWebTabpom()
				.equals(castOther.getWebTabpom())))
				&& ((this.getFilename() == castOther.getFilename()) || (this.getFilename() != null && castOther.getFilename() != null && this.getFilename()
						.equals(castOther.getFilename())));
	}

	public int hashCode(){
		int result=17;

		result=37 * result + (getWebTabpom() == null ? 0 : this.getWebTabpom().hashCode());
		result=37 * result + (getFilename() == null ? 0 : this.getFilename().hashCode());
		return result;
	}

}