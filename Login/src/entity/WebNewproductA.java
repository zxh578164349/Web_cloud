package entity;

/**
 * WebNewproductA entity. @author MyEclipse Persistence Tools
 */

public class WebNewproductA implements java.io.Serializable {

	// Fields

	private Integer mid;
	private String billNo;
	private String factNo;
	private String createDate;
	private String updateDate;
	private Integer createUserFid;
	private Integer updateUserFid;
	private String receiveDate;
	private String PName;
	private String PExp;
	private String PResultGuest;
	private String PResult;
	private String filesYn;
	private String visaType;
	private String visaTypeM;

	// Constructors

	/** default constructor */
	public WebNewproductA() {
	}

	/** minimal constructor */
	public WebNewproductA(Integer mid) {
		this.mid = mid;
	}

	/** full constructor */
	public WebNewproductA(Integer mid, String billNo, String factNo,
			String createDate, String updateDate, Integer createUserFid,
			Integer updateUserFid, String receiveDate, String PName,
			String PExp, String PResultGuest, String PResult, String filesYn,
			String visaType, String visaTypeM) {
		this.mid = mid;
		this.billNo = billNo;
		this.factNo = factNo;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.createUserFid = createUserFid;
		this.updateUserFid = updateUserFid;
		this.receiveDate = receiveDate;
		this.PName = PName;
		this.PExp = PExp;
		this.PResultGuest = PResultGuest;
		this.PResult = PResult;
		this.filesYn = filesYn;
		this.visaType = visaType;
		this.visaTypeM = visaTypeM;
	}

	// Property accessors

	public Integer getMid() {
		return this.mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getBillNo() {
		return this.billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getFactNo() {
		return this.factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getCreateUserFid() {
		return this.createUserFid;
	}

	public void setCreateUserFid(Integer createUserFid) {
		this.createUserFid = createUserFid;
	}

	public Integer getUpdateUserFid() {
		return this.updateUserFid;
	}

	public void setUpdateUserFid(Integer updateUserFid) {
		this.updateUserFid = updateUserFid;
	}

	public String getReceiveDate() {
		return this.receiveDate;
	}

	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getPName() {
		return this.PName;
	}

	public void setPName(String PName) {
		this.PName = PName;
	}

	public String getPExp() {
		return this.PExp;
	}

	public void setPExp(String PExp) {
		this.PExp = PExp;
	}

	public String getPResultGuest() {
		return this.PResultGuest;
	}

	public void setPResultGuest(String PResultGuest) {
		this.PResultGuest = PResultGuest;
	}

	public String getPResult() {
		return this.PResult;
	}

	public void setPResult(String PResult) {
		this.PResult = PResult;
	}

	public String getFilesYn() {
		return this.filesYn;
	}

	public void setFilesYn(String filesYn) {
		this.filesYn = filesYn;
	}

	public String getVisaType() {
		return this.visaType;
	}

	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}

	public String getVisaTypeM() {
		return this.visaTypeM;
	}

	public void setVisaTypeM(String visaTypeM) {
		this.visaTypeM = visaTypeM;
	}

}