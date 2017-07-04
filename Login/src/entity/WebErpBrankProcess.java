package entity;

import java.util.List;

/**
 * WebErpBrankProcess entity. @author MyEclipse Persistence Tools
 */

public class WebErpBrankProcess implements java.io.Serializable{

	// Fields

	private Integer id;
	private String syscode;
	private String sysno;
	private String name;
	private String tname;
	private String cname;
	private String description;
	private List<WebTabpom> webTabpoms;

	// Constructors

	/** default constructor */
	public WebErpBrankProcess(){
	}

	/** minimal constructor */
	public WebErpBrankProcess(Integer id){
		this.id=id;
	}

	/** full constructor */
	public WebErpBrankProcess(Integer id,String syscode,String sysno,String name,String tname,String cname,String description){
		this.id=id;
		this.syscode=syscode;
		this.sysno=sysno;
		this.name=name;
		this.tname=tname;
		this.cname=cname;
		this.description=description;
	}

	// Property accessors

	public Integer getId(){
		return this.id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getSyscode(){
		return this.syscode;
	}

	public void setSyscode(String syscode){
		this.syscode=syscode;
	}

	public String getSysno(){
		return this.sysno;
	}

	public void setSysno(String sysno){
		this.sysno=sysno;
	}

	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getTname(){
		return this.tname;
	}

	public void setTname(String tname){
		this.tname=tname;
	}

	public String getCname(){
		return this.cname;
	}

	public void setCname(String cname){
		this.cname=cname;
	}

	public String getDescription(){
		return this.description;
	}

	public void setDescription(String description){
		this.description=description;
	}

	public List<WebTabpom> getWebTabpoms(){
		return webTabpoms;
	}

	public void setWebTabpoms(List<WebTabpom> webTabpoms){
		this.webTabpoms=webTabpoms;
	}
	

}