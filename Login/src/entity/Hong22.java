package entity;

/**
 * Hong22 entity. @author MyEclipse Persistence Tools
 */

public class Hong22 implements java.io.Serializable{

	// Fields

	private Hong22Id id;
	private String fffff;

	// Constructors

	/** default constructor */
	public Hong22(){
	}

	/** minimal constructor */
	public Hong22(Hong22Id id){
		this.id=id;
	}

	/** full constructor */
	public Hong22(Hong22Id id,String fffff){
		this.id=id;
		this.fffff=fffff;
	}

	// Property accessors

	public Hong22Id getId(){
		return this.id;
	}

	public void setId(Hong22Id id){
		this.id=id;
	}

	public String getFffff(){
		return this.fffff;
	}

	public void setFffff(String fffff){
		this.fffff=fffff;
	}

}