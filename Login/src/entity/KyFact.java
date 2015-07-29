package entity;

/**
 * KyFact entity. @author MyEclipse Persistence Tools
 */

public class KyFact implements java.io.Serializable {

	// Fields

	private String factNo;
	private String factCname;
	private String factEname;
	private String factCode;
	private String address;
	private String leader;
	private String telNo;
	private String faxNo;
	private String ownGroup;
	private String factSname;
	private String billTitle;
	private String compNo;
	private String nbid;
	private String areaNo;

	// Constructors

	/** default constructor */
	public KyFact() {
	}

	/** minimal constructor */
	public KyFact(String factNo) {
		this.factNo = factNo;
	}

	/** full constructor */
	public KyFact(String factNo, String factCname, String factEname,
			String factCode, String address, String leader, String telNo,
			String faxNo, String ownGroup, String factSname, String billTitle,
			String compNo, String nbid, String areaNo) {
		this.factNo = factNo;
		this.factCname = factCname;
		this.factEname = factEname;
		this.factCode = factCode;
		this.address = address;
		this.leader = leader;
		this.telNo = telNo;
		this.faxNo = faxNo;
		this.ownGroup = ownGroup;
		this.factSname = factSname;
		this.billTitle = billTitle;
		this.compNo = compNo;
		this.nbid = nbid;
		this.areaNo = areaNo;
	}

	// Property accessors

	public String getFactNo() {
		return this.factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public String getFactCname() {
		return this.factCname;
	}

	public void setFactCname(String factCname) {
		this.factCname = factCname;
	}

	public String getFactEname() {
		return this.factEname;
	}

	public void setFactEname(String factEname) {
		this.factEname = factEname;
	}

	public String getFactCode() {
		return this.factCode;
	}

	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLeader() {
		return this.leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public String getTelNo() {
		return this.telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getFaxNo() {
		return this.faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public String getOwnGroup() {
		return this.ownGroup;
	}

	public void setOwnGroup(String ownGroup) {
		this.ownGroup = ownGroup;
	}

	public String getFactSname() {
		return this.factSname;
	}

	public void setFactSname(String factSname) {
		this.factSname = factSname;
	}

	public String getBillTitle() {
		return this.billTitle;
	}

	public void setBillTitle(String billTitle) {
		this.billTitle = billTitle;
	}

	public String getCompNo() {
		return this.compNo;
	}

	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}

	public String getNbid() {
		return this.nbid;
	}

	public void setNbid(String nbid) {
		this.nbid = nbid;
	}

	public String getAreaNo() {
		return this.areaNo;
	}

	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

}