package entity;

/**
 * Hong22Id entity. @author MyEclipse Persistence Tools
 */

public class Hong22Id implements java.io.Serializable{

	// Fields

	private Hong11 hong11;
	private String yymmdd;

	// Constructors

	/** default constructor */
	public Hong22Id(){
	}

	/** full constructor */
	public Hong22Id(Hong11 hong11,String yymmdd){
		this.hong11=hong11;
		this.yymmdd=yymmdd;
	}

	// Property accessors

	public Hong11 getHong11(){
		return this.hong11;
	}

	public void setHong11(Hong11 hong11){
		this.hong11=hong11;
	}

	public String getYymmdd(){
		return this.yymmdd;
	}

	public void setYymmdd(String yymmdd){
		this.yymmdd=yymmdd;
	}

	public boolean equals(Object other){
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Hong22Id))
			return false;
		Hong22Id castOther=(Hong22Id)other;

		return ((this.getHong11() == castOther.getHong11()) || (this.getHong11() != null && castOther.getHong11() != null && this.getHong11().equals(
				castOther.getHong11())))
				&& ((this.getYymmdd() == castOther.getYymmdd()) || (this.getYymmdd() != null && castOther.getYymmdd() != null && this.getYymmdd().equals(
						castOther.getYymmdd())));
	}

	public int hashCode(){
		int result=17;

		result=37 * result + (getHong11() == null ? 0 : this.getHong11().hashCode());
		result=37 * result + (getYymmdd() == null ? 0 : this.getYymmdd().hashCode());
		return result;
	}

}