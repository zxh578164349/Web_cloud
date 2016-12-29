package entity;

/**
 * WebOperationToUser entity. @author MyEclipse Persistence Tools
 */

public class WebOperationToUser implements java.io.Serializable{

	// Fields

	private Integer id;
	private WebUser webUser;
	private WebUserOperation webUserOperation;

	// Constructors

	/** default constructor */
	public WebOperationToUser(){
	}

	/** minimal constructor */
	public WebOperationToUser(Integer id){
		this.id=id;
	}

	/** full constructor */
	public WebOperationToUser(Integer id,WebUser webUser,WebUserOperation webUserOperation){
		this.id=id;
		this.webUser=webUser;
		this.webUserOperation=webUserOperation;
	}

	// Property accessors

	public Integer getId(){
		return this.id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public WebUser getWebUser(){
		return this.webUser;
	}

	public void setWebUser(WebUser webUser){
		this.webUser=webUser;
	}

	public WebUserOperation getWebUserOperation(){
		return this.webUserOperation;
	}

	public void setWebUserOperation(WebUserOperation webUserOperation){
		this.webUserOperation=webUserOperation;
	}

}