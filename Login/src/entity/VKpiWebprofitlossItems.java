package entity;

/**
 * VKpiWebprofitlossItems entity. @author MyEclipse Persistence Tools
 */

public class VKpiWebprofitlossItems implements java.io.Serializable{

	// Fields

	private Integer eid;
	private String itemName;

	// Constructors

	/** default constructor */
	public VKpiWebprofitlossItems(){
	}

	/** minimal constructor */
	public VKpiWebprofitlossItems(Integer eid){
		this.eid=eid;
	}

	/** full constructor */
	public VKpiWebprofitlossItems(Integer eid,String itemName){
		this.eid=eid;
		this.itemName=itemName;
	}

	// Property accessors

	public Integer getEid(){
		return this.eid;
	}

	public void setEid(Integer eid){
		this.eid=eid;
	}

	public String getItemName(){
		return this.itemName;
	}

	public void setItemName(String itemName){
		this.itemName=itemName;
	}

}