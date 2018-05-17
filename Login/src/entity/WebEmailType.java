package entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * WebEmailType entity. @author MyEclipse Persistence Tools
 */

public class WebEmailType implements java.io.Serializable {

	// Fields

	private Integer eid;
	private String ecode;
	private String ename;
	private String availableMk;//是否可見       Y可見        N不可見

	// Constructors

	/** default constructor */
	public WebEmailType() {
	}

	/** minimal constructor */
	public WebEmailType(Integer eid) {
		this.eid = eid;
	}

	/** full constructor */
	public WebEmailType(Integer eid, String ecode, String ename) {
		this.eid = eid;
		this.ecode = ecode;
		this.ename = ename;
	}

	// Property accessors

	public Integer getEid() {
		return this.eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getEcode() {
		return this.ecode;
	}

	public void setEcode(String ecode) {
		this.ecode = ecode;
	}

	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}	

	public String getAvailableMk() {
		return availableMk;
	}

	public void setAvailableMk(String availableMk) {
		this.availableMk = availableMk;
	}
	
	

	

}