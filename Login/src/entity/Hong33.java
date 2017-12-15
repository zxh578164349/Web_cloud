package entity;

/**
 * Hong33 entity. @author MyEclipse Persistence Tools
 */

public class Hong33 implements java.io.Serializable{

	// Fields

	private Hong33Id id;
	private String name1;

	// Constructors

	/** default constructor */
	public Hong33(){
	}

	/** minimal constructor */
	public Hong33(Hong33Id id){
		this.id=id;
	}

	/** full constructor */
	public Hong33(Hong33Id id,String name1){
		this.id=id;
		this.name1=name1;
	}

	// Property accessors

	public Hong33Id getId(){
		return this.id;
	}

	public void setId(Hong33Id id){
		this.id=id;
	}

	public String getName1(){
		return this.name1;
	}

	public void setName1(String name1){
		this.name1=name1;
	}

}