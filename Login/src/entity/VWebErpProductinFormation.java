package entity;

/**
 * VWebErpProductinFormation entity. @author MyEclipse Persistence Tools
 */

public class VWebErpProductinFormation implements java.io.Serializable{

	// Fields

	private String itemcategory;
	private String itemcategoryname;

	// Constructors

	/** default constructor */
	public VWebErpProductinFormation(){
	}

	/** minimal constructor */
	public VWebErpProductinFormation(String itemcategory){
		this.itemcategory=itemcategory;
	}

	/** full constructor */
	public VWebErpProductinFormation(String itemcategory,String itemcategoryname){
		this.itemcategory=itemcategory;
		this.itemcategoryname=itemcategoryname;
	}

	// Property accessors

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

}