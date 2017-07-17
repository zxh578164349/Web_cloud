package entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * WebBrProduct entity. @author MyEclipse Persistence Tools
 */

public class WebBrProduct implements java.io.Serializable{

	// Fields

	private Integer wid;
	private VWebFact factNo;
	private String itemcategory;
	private String itemcategoryname;
	private String namec1;
	private String namec2;
	private WebUser createUser;
	private WebUser editUser;
	private String createDate;
	private String editDate;
	private List webBrProductitems;
	private WebErpProductinFormation weberppr;

	// Constructors

	/** default constructor */
	public WebBrProduct(){
	}

	/** minimal constructor */
	public WebBrProduct(Integer wid){
		this.wid=wid;
	}

	/** full constructor */
	public WebBrProduct(Integer wid,VWebFact factNo,String itemcategory,String itemcategoryname,String namec1,String namec2,
			String createDate,String editDate,List webBrProductitems){
		this.wid=wid;
		this.factNo=factNo;
		this.itemcategory=itemcategory;
		this.itemcategoryname=itemcategoryname;
		this.namec1=namec1;
		this.namec2=namec2;		
		this.createDate=createDate;
		this.editDate=editDate;
		this.webBrProductitems=webBrProductitems;
	}

	// Property accessors

	public Integer getWid(){
		return this.wid;
	}

	public void setWid(Integer wid){
		this.wid=wid;
	}

	

	public VWebFact getFactNo(){
		return factNo;
	}

	public void setFactNo(VWebFact factNo){
		this.factNo=factNo;
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

	public List getWebBrProductitems(){
		return webBrProductitems;
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

	public void setWebBrProductitems(List webBrProductitems){
		this.webBrProductitems=webBrProductitems;
	}

	public WebErpProductinFormation getWeberppr(){
		return weberppr;
	}

	public void setWeberppr(WebErpProductinFormation weberppr){
		this.weberppr=weberppr;
	}

	
	
	

	

}