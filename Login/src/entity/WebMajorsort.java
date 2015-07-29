package entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * WebMajorsort entity. @author MyEclipse Persistence Tools
 */

public class WebMajorsort implements java.io.Serializable {

	// Fields

	private Integer majorid;
	private String majorsortid;
	private String majorname;
	private Timestamp addtime;
	private Set webSubsorts = new HashSet(0);

	// Constructors

	/** default constructor */
	public WebMajorsort() {
	}

	/** minimal constructor */
	public WebMajorsort(Integer majorid) {
		this.majorid = majorid;
	}

	/** full constructor */
	public WebMajorsort(Integer majorid, String majorsortid, String majorname,
			Timestamp addtime, Set webSubsorts) {
		this.majorid = majorid;
		this.majorsortid = majorsortid;
		this.majorname = majorname;
		this.addtime = addtime;
		this.webSubsorts = webSubsorts;
	}

	// Property accessors

	public Integer getMajorid() {
		return this.majorid;
	}

	public void setMajorid(Integer majorid) {
		this.majorid = majorid;
	}

	public String getMajorsortid() {
		return this.majorsortid;
	}

	public void setMajorsortid(String majorsortid) {
		this.majorsortid = majorsortid;
	}

	public String getMajorname() {
		return this.majorname;
	}

	public void setMajorname(String majorname) {
		this.majorname = majorname;
	}

	public Timestamp getAddtime() {
		return this.addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}

	public Set getWebSubsorts() {
		return this.webSubsorts;
	}

	public void setWebSubsorts(Set webSubsorts) {
		this.webSubsorts = webSubsorts;
	}

}