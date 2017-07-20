package entity;

/**
 * Hong11Id entity. @author MyEclipse Persistence Tools
 */

public class Hong11Id implements java.io.Serializable{

	// Fields

	private String factNo;
	private Long wid;

	// Constructors

	/** default constructor */
	public Hong11Id(){
	}

	/** full constructor */
	public Hong11Id(String factNo,Long wid){
		this.factNo=factNo;
		this.wid=wid;
	}

	// Property accessors

	public String getFactNo(){
		return this.factNo;
	}

	public void setFactNo(String factNo){
		this.factNo=factNo;
	}

	public Long getWid(){
		return this.wid;
	}

	public void setWid(Long wid){
		this.wid=wid;
	}

	public boolean equals(Object other){
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Hong11Id))
			return false;
		Hong11Id castOther=(Hong11Id)other;

		return ((this.getFactNo() == castOther.getFactNo()) || (this.getFactNo() != null && castOther.getFactNo() != null && this.getFactNo().equals(
				castOther.getFactNo())))
				&& ((this.getWid() == castOther.getWid()) || (this.getWid() != null && castOther.getWid() != null && this.getWid().equals(castOther.getWid())));
	}

	public int hashCode(){
		int result=17;

		result=37 * result + (getFactNo() == null ? 0 : this.getFactNo().hashCode());
		result=37 * result + (getWid() == null ? 0 : this.getWid().hashCode());
		return result;
	}

}