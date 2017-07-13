package entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * WebBrProductitem entity. @author MyEclipse Persistence Tools
 */

public class WebBrProductitem implements java.io.Serializable{

	// Fields

	private WebBrProductitemId id;
	private WebBrProduct webBrProduct;
	private Double inventory;
	private Double orderNotin;
	private Double actualUsed;
	private String createUser;
	private String editUser;
	private String createDate;
	private String editDate;
	private List webBrEstimatingitems;

	// Constructors

	/** default constructor */
	public WebBrProductitem(){
	}

	/** minimal constructor */
	public WebBrProductitem(WebBrProductitemId id){
		this.id=id;
	}

	/** full constructor */
	public WebBrProductitem(WebBrProductitemId id,WebBrProduct webBrProduct,Double inventory,Double orderNotin,Double actualUsed,String createUser,
			String editUser,String createDate,String editDate,List webBrEstimatingitems){
		this.id=id;
		this.webBrProduct=webBrProduct;
		this.inventory=inventory;
		this.orderNotin=orderNotin;
		this.actualUsed=actualUsed;
		this.createUser=createUser;
		this.editUser=editUser;
		this.createDate=createDate;
		this.editDate=editDate;
		this.webBrEstimatingitems=webBrEstimatingitems;
	}

	// Property accessors

	public WebBrProductitemId getId(){
		return this.id;
	}

	public void setId(WebBrProductitemId id){
		this.id=id;
	}

	public WebBrProduct getWebBrProduct(){
		return this.webBrProduct;
	}

	public void setWebBrProduct(WebBrProduct webBrProduct){
		this.webBrProduct=webBrProduct;
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

	public String getCreateUser(){
		return this.createUser;
	}

	public void setCreateUser(String createUser){
		this.createUser=createUser;
	}

	public String getEditUser(){
		return this.editUser;
	}

	public void setEditUser(String editUser){
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

	public List getWebBrEstimatingitems(){
		return webBrEstimatingitems;
	}

	public void setWebBrEstimatingitems(List webBrEstimatingitems){
		this.webBrEstimatingitems=webBrEstimatingitems;
	}

	

}