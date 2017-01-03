package entity;

/**
 * VWebUserEmail entity. @author MyEclipse Persistence Tools
 */

public class VWebUserEmail implements java.io.Serializable{

	// Fields

	private VWebUserEmailId id;
	private String username;

	// Constructors

	/** default constructor */
	public VWebUserEmail(){
	}

	/** full constructor */
	public VWebUserEmail(VWebUserEmailId id,String username){
		this.id=id;
		this.username=username;
	}

	// Property accessors

	public VWebUserEmailId getId(){
		return this.id;
	}

	public void setId(VWebUserEmailId id){
		this.id=id;
	}

	public String getUsername(){
		return this.username;
	}

	public void setUsername(String username){
		this.username=username;
	}

}