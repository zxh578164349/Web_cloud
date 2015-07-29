package entity;

/**
 * KyzAcct entity. @author MyEclipse Persistence Tools
 */

public class KyzAcct implements java.io.Serializable {

	// Fields

	private String acctNo;
	private String acctName;
	private String bacctNo;
	private String bacctName;

	// Constructors

	/** default constructor */
	public KyzAcct() {
	}

	/** minimal constructor */
	public KyzAcct(String acctNo) {
		this.acctNo = acctNo;
	}

	/** full constructor */
	public KyzAcct(String acctNo, String acctName, String bacctNo,
			String bacctName) {
		this.acctNo = acctNo;
		this.acctName = acctName;
		this.bacctNo = bacctNo;
		this.bacctName = bacctName;
	}

	// Property accessors

	public String getAcctNo() {
		return this.acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getAcctName() {
		return this.acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public String getBacctNo() {
		return this.bacctNo;
	}

	public void setBacctNo(String bacctNo) {
		this.bacctNo = bacctNo;
	}

	public String getBacctName() {
		return this.bacctName;
	}

	public void setBacctName(String bacctName) {
		this.bacctName = bacctName;
	}

}