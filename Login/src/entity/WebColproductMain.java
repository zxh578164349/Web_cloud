package entity;

import java.util.ArrayList;
import java.util.List;


/**
 * WebColproductMain entity. @author MyEclipse Persistence Tools
 */

public class WebColproductMain implements java.io.Serializable {

	// Fields

	private Integer mid;
	private WebUser webUserByUpdateUserFid;
	private WebUser webUserByCreateUserFid;
	private String billNo;
	private String factNo;
	private String createDate;
	private String updateDate;
	private String visaType;
	private String visaTypeM;
	private List<WebColproductItems> webColproductItemses = new ArrayList<WebColproductItems>();

	// Constructors

	/** default constructor */
	public WebColproductMain() {
	}

	/** minimal constructor */
	public WebColproductMain(Integer mid) {
		this.mid = mid;
	}

	/** full constructor */
	public WebColproductMain(Integer mid, WebUser webUserByUpdateUserFid,
			WebUser webUserByCreateUserFid, String billNo, String factNo,
			String createDate, String updateDate, String visaType,
			String visaTypeM, List<WebColproductItems> webColproductItemses) {
		this.mid = mid;
		this.webUserByUpdateUserFid = webUserByUpdateUserFid;
		this.webUserByCreateUserFid = webUserByCreateUserFid;
		this.billNo = billNo;
		this.factNo = factNo;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.visaType = visaType;
		this.visaTypeM = visaTypeM;
		this.webColproductItemses = webColproductItemses;
	}

	// Property accessors

	public Integer getMid() {
		return this.mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

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

	public List<WebColproductItems> getWebColproductItemses() {
		return webColproductItemses;
	}

	public void setWebColproductItemses(
			List<WebColproductItems> webColproductItemses) {
		this.webColproductItemses = webColproductItemses;
	}

	

}