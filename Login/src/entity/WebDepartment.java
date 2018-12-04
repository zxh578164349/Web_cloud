package entity;

/**
 * WebDepartment entity. @author MyEclipse Persistence Tools
 */

public class WebDepartment implements java.io.Serializable{

	// Fields

	private Integer depId;
	private String factNo;
	private String depName;

	// Constructors

	/** default constructor */
	public WebDepartment(){
	}

	/** minimal constructor */
	public WebDepartment(Integer depId){
		this.depId=depId;
	}

	/** full constructor */
	public WebDepartment(Integer depId,String factNo,String depName){
		this.depId=depId;
		this.factNo=factNo;
		this.depName=depName;
	}

	// Property accessors

	

	public String getFactNo(){
		return this.factNo;
	}

	public Integer getDepId(){
		return depId;
	}

	public void setDepId(Integer depId){
		this.depId=depId;
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