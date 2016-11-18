package entity;

/**
 * VWebErpProductinFormation entity. @author MyEclipse Persistence Tools
 */

public class VWebErpProductinFormation implements java.io.Serializable{

	// Fields

	private String selfchar1;
	private String selfchar1name;

	// Constructors

	/** default constructor */
	public VWebErpProductinFormation(){
	}

	/** minimal constructor */
	public VWebErpProductinFormation(String selfchar1){
		this.selfchar1=selfchar1;
	}

	/** full constructor */
	public VWebErpProductinFormation(String selfchar1,String selfchar1name){
		this.selfchar1=selfchar1;
		this.selfchar1name=selfchar1name;
	}

	public String getSelfchar1(){
		return selfchar1;
	}

	public void setSelfchar1(String selfchar1){
		this.selfchar1=selfchar1;
	}

	public String getSelfchar1name(){
		return selfchar1name;
	}

	public void setSelfchar1name(String selfchar1name){
		this.selfchar1name=selfchar1name;
	}

	// Property accessors

	

}