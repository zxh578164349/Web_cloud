package entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Hong11 entity. @author MyEclipse Persistence Tools
 */

public class Hong11 implements java.io.Serializable{

	// Fields

	private Hong11Id id;
	private String fffff;
	private Set hong22s=new HashSet(0);

	// Constructors

	/** default constructor */
	public Hong11(){
	}

	/** minimal constructor */
	public Hong11(Hong11Id id){
		this.id=id;
	}

	/** full constructor */
	public Hong11(Hong11Id id,String fffff,Set hong22s){
		this.id=id;
		this.fffff=fffff;
		this.hong22s=hong22s;
	}

	// Property accessors

	public Hong11Id getId(){
		return this.id;
	}

	public void setId(Hong11Id id){
		this.id=id;
	}

	public String getFffff(){
		return this.fffff;
	}

	public void setFffff(String fffff){
		this.fffff=fffff;
	}

	public Set getHong22s(){
		return this.hong22s;
	}

	public void setHong22s(Set hong22s){
		this.hong22s=hong22s;
	}

}