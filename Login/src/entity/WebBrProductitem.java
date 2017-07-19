package entity;

/**
 * WebBrProductitem entity. @author MyEclipse Persistence Tools
 */

public class WebBrProductitem implements java.io.Serializable{

	// Fields

	private WebBrProductitemId id;
	private Double inventory;
	private Double orderNotin;
	private Double actualUsed;
	private WebUser createUser;
	private WebUser editUser;
	private String createDate;
	private String editDate;

	// Constructors

	/** default constructor */
	public WebBrProductitem(){
	}

	/** minimal constructor */
	public WebBrProductitem(WebBrProductitemId id){
		this.id=id;
	}

	/** full constructor */
	public WebBrProductitem(WebBrProductitemId id,Double inventory,Double orderNotin,Double actualUsed,WebUser createUser,WebUser editUser,String createDate,
			String editDate){
		this.id=id;
		this.inventory=inventory;
		this.orderNotin=orderNotin;
		this.actualUsed=actualUsed;
		this.createUser=createUser;
		this.editUser=editUser;
		this.createDate=createDate;
		this.editDate=editDate;
	}

	// Property accessors

	public WebBrProductitemId getId(){
		return this.id;
	}

	public void setId(WebBrProductitemId id){
		this.id=id;
	}

	public Double getInventory(){
		return this.inventory;
	}

	public void setInventory(Double inventory){
		this.inventory=inventory;
	}

	public Double getOrderNotin(){
		return this.orderNotin;
	}

	public void setOrderNotin(Double orderNotin){
		this.orderNotin=orderNotin;
	}

	public Double getActualUsed(){
		return this.actualUsed;
	}

	public void setActualUsed(Double actualUsed){
		this.actualUsed=actualUsed;
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

}