package entity;

/**
 * WebBrEstimatingitem entity. @author MyEclipse Persistence Tools
 */

public class WebBrEstimatingitem implements java.io.Serializable{

	// Fields

	private WebBrEstimatingitemId id;
	private Double actualPairs;
	private Double estimatingPairs1;
	private Double estimatingPairs2;
	private Double estimatingPairs3;
	private WebUser createUser;
	private WebUser editUser;
	private String createDate;
	private String editDate;
	private VWebFact factNo2;

	// Constructors

	/** default constructor */
	public WebBrEstimatingitem(){
	}

	/** minimal constructor */
	public WebBrEstimatingitem(WebBrEstimatingitemId id){
		this.id=id;
	}
	
	public WebBrEstimatingitem(WebBrEstimatingitemId id,VWebFact factNo2){
		this.id=id;
		this.factNo2=factNo2;
	}

	/** full constructor */
	public WebBrEstimatingitem(WebBrEstimatingitemId id,Double actualPairs,Double estimatingPairs1,Double estimatingPairs2,Double estimatingPairs3,
			String createDate,String editDate){
		this.id=id;
		this.actualPairs=actualPairs;
		this.estimatingPairs1=estimatingPairs1;
		this.estimatingPairs2=estimatingPairs2;
		this.estimatingPairs3=estimatingPairs3;		
		this.createDate=createDate;
		this.editDate=editDate;
	}

	// Property accessors

	public WebBrEstimatingitemId getId(){
		return this.id;
	}

	public void setId(WebBrEstimatingitemId id){
		this.id=id;
	}

	public Double getActualPairs(){
		return this.actualPairs;
	}

	public void setActualPairs(Double actualPairs){
		this.actualPairs=actualPairs;
	}

	public Double getEstimatingPairs1(){
		return this.estimatingPairs1;
	}

	public void setEstimatingPairs1(Double estimatingPairs1){
		this.estimatingPairs1=estimatingPairs1;
	}

	public Double getEstimatingPairs2(){
		return this.estimatingPairs2;
	}

	public void setEstimatingPairs2(Double estimatingPairs2){
		this.estimatingPairs2=estimatingPairs2;
	}

	public Double getEstimatingPairs3(){
		return this.estimatingPairs3;
	}

	public void setEstimatingPairs3(Double estimatingPairs3){
		this.estimatingPairs3=estimatingPairs3;
	}

	

	public WebUser getCreateUser(){
		return createUser;
	}

	public void setCreateUser(WebUser createUser){
		this.createUser=createUser;
	}

	public WebUser getEditUser(){
		return editUser;
	}

	public void setEditUser(WebUser editUser){
		this.editUser=editUser;
	}

	public String getCreateDate(){
		return this.createDate;
	}

	public void setCreateDate(String createDate){
		this.createDate=createDate;
	}

	public String getEditDate(){
		return this.editDate;
	}

	public void setEditDate(String editDate){
		this.editDate=editDate;
	}

	public VWebFact getFactNo2(){
		return factNo2;
	}

	public void setFactNo2(VWebFact factNo2){
		this.factNo2=factNo2;
	}
	
	

}