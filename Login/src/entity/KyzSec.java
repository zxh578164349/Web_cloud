package entity;

/**
 * KyzSec entity. @author MyEclipse Persistence Tools
 */

public class KyzSec implements java.io.Serializable {

	// Fields

	private KyzSecId id;
	private String secNm;
	private String areaMk;
	private String deptNo;
	private String useMk;

	// Constructors

	/** default constructor */
	public KyzSec() {
	}

	/** minimal constructor */
	public KyzSec(KyzSecId id) {
		this.id = id;
	}

	/** full constructor */
	public KyzSec(KyzSecId id, String secNm, String areaMk, String deptNo,
			String useMk) {
		this.id = id;
		this.secNm = secNm;
		this.areaMk = areaMk;
		this.deptNo = deptNo;
		this.useMk = useMk;
	}

	// Property accessors

	public KyzSecId getId() {
		return this.id;
	}

	public void setId(KyzSecId id) {
		this.id = id;
	}

	public String getSecNm() {
		return this.secNm;
	}

	public void setSecNm(String secNm) {
		this.secNm = secNm;
	}

	public String getAreaMk() {
		return this.areaMk;
	}

	public void setAreaMk(String areaMk) {
		this.areaMk = areaMk;
	}

	public String getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getUseMk() {
		return this.useMk;
	}

	public void setUseMk(String useMk) {
		this.useMk = useMk;
	}

}