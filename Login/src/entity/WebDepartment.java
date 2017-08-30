package entity;

/**
 * WebDepartment entity. @author MyEclipse Persistence Tools
 */

public class WebDepartment implements java.io.Serializable{

	// Fields

	private Long depId;
	private String factNo;
	private String depName;

	// Constructors

	/** default constructor */
	public WebDepartment(){
	}

	/** minimal constructor */
	public WebDepartment(Long depId){
		this.depId=depId;
	}

	/** full constructor */
	public WebDepartment(Long depId,String factNo,String depName){
		this.depId=depId;
		this.factNo=factNo;
		this.depName=depName;
	}

	// Property accessors

	public Long getDepId(){
		return this.depId;
	}

	public void setDepId(Long depId){
		this.depId=depId;
	}

	public String getFactNo(){
		return this.factNo;
	}

	public void setFactNo(String factNo){
		this.factNo=factNo;
	}

	public String getDepName(){
		return this.depName;
	}

	public void setDepName(String depName){
		this.depName=depName;
	}

}