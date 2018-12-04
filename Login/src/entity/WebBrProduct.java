package entity;

import java.util.List;

/**
 * WebBrProduct entity. @author MyEclipse Persistence Tools
 */

public class WebBrProduct implements java.io.Serializable{

	// Fields

	private WebBrProductId id;
	private Integer wid;
	private String itemcategory;
	private String itemcategoryname;
	private String namec1;
	private String namec2;
	private Integer createUser;
	private Integer editUser;
	private String createDate;
	private String editDate;
	private List webBrProductitems;
	
	private VWebFact factNo2;
	private WebUser createUser2;
	private WebUser editUser2;

	// Constructors

	/** default constructor */
	public WebBrProduct(){
	}

	/** minimal constructor */
	public WebBrProduct(WebBrProductId id){
		this.id=id;
	}

	/** full constructor */
	public WebBrProduct(WebBrProductId id,Integer wid,String itemcategory,String itemcategoryname,String namec1,String namec2,Integer createUser,
			Integer editUser,String createDate,String editDate){
		this.id=id;
		this.wid=wid;
		this.itemcategory=itemcategory;
		this.itemcategoryname=itemcategoryname;
		this.namec1=namec1;
		this.namec2=namec2;
		this.createUser=createUser;
		this.editUser=editUser;
		this.createDate=createDate;
		this.editDate=editDate;
	}

	// Property accessors

	public WebBrProductId getId(){
		return this.id;
	}

	public void setId(WebBrProductId id){
		this.id=id;
	}

	public Integer getWid(){
		return this.wid;
	}

	public void setWid(Integer wid){
		this.wid=wid;
	}

	public String getItemcategory(){
		return this.itemcategory;
	}

	public void setItemcategory(String itemcategory){
		this.itemcategory=itemcategory;
	}

	public String getItemcategoryname(){
		return this.itemcategoryname;
	}

	public void setItemcategoryname(String itemcategoryname){
		this.itemcategoryname=itemcategoryname;
	}

	public String getNamec1(){
		return this.namec1;
	}

	public void setNamec1(String namec1){
		this.namec1=namec1;
	}

	public String getNamec2(){
		return this.namec2;
	}

	public void setNamec2(String namec2){
		this.namec2=namec2;
	}

	public Integer getCreateUser(){
		return this.createUser;
	}

	public void setCreateUser(Integer createUser){
		this.createUser=createUser;
	}

	public Integer getEditUser(){
		return this.editUser;
	}

	public void setEditUser(Integer editUser){
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

	public WebUser getCreateUser2(){
		return createUser2;
	}

	public void setCreateUser2(WebUser createUser2){
		this.createUser2=createUser2;
	}

	public WebUser getEditUser2(){
		return editUser2;
	}

	public void setEditUser2(WebUser editUser2){
		this.editUser2=editUser2;
	}

	public List getWebBrProductitems(){
		return webBrProductitems;
	}

	public void setWebBrProductitems(List webBrProductitems){
		this.webBrProductitems=webBrProductitems;
	}
	
	

}