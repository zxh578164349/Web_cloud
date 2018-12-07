package entity;

import java.util.List;

/**
 * WebNewproduct entity. @author MyEclipse Persistence Tools
 */

public class WebNewproduct implements java.io.Serializable {

	// Fields
	
	private WebUser webUserByUpdateUserFid;
	private WebUser webUserByCreateUserFid;
	private String billNo;
	private VWebFact factNo;
	private String createDate;
	private String updateDate;
	private String receiveDate;
	private String PName;
	private String PExp;
	private String PResultGuest;
	private String PResult;
	private String filesYn;
	private String visaType;
	private String visaTypeM;
	private KyVisabillm vbm;
	private List<KyzExpectmatmFile>list_file;
	private String title;

	// Constructors

	public KyVisabillm getVbm() {
		return vbm;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setVbm(KyVisabillm vbm) {
		this.vbm = vbm;
	}

	public List<KyzExpectmatmFile> getList_file() {
		return list_file;
	}

	public void setList_file(List<KyzExpectmatmFile> list_file) {
		this.list_file = list_file;
	}

	/** default constructor */
	public WebNewproduct() {
	}

	/** minimal constructor */
	public WebNewproduct(String billNo) {
		this.billNo = billNo;
	}

	/** full constructor */
	public WebNewproduct(WebUser webUserByUpdateUserFid,
			WebUser webUserByCreateUserFid, String billNo, VWebFact factNo,
			String createDate, String updateDate, String receiveDate,
			String PName, String PExp, String PResultGuest, String PResult,
			String filesYn, String visaType, String visaTypeM) {
		this.webUserByUpdateUserFid = webUserByUpdateUserFid;
		this.webUserByCreateUserFid = webUserByCreateUserFid;
		this.billNo = billNo;
		this.factNo = factNo;
		this.createDate = createDate;
		this.updateDate = updateDate;
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

	public WebUser getWebUserByUpdateUserFid() {
		return this.webUserByUpdateUserFid;
	}

	public void setWebUserByUpdateUserFid(WebUser webUserByUpdateUserFid) {
		this.webUserByUpdateUserFid = webUserByUpdateUserFid;
	}

	public WebUser getWebUserByCreateUserFid() {
		return this.webUserByCreateUserFid;
	}

	public void setWebUserByCreateUserFid(WebUser webUserByCreateUserFid) {
		this.webUserByCreateUserFid = webUserByCreateUserFid;
	}

	public String getBillNo() {
		return this.billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	

	public VWebFact getFactNo() {
		return factNo;
	}

	public void setFactNo(VWebFact factNo) {
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