package entity;

import java.util.ArrayList;
import java.util.List;


/**
 * WebColproductMain entity. @author MyEclipse Persistence Tools
 */

public class WebColproductMain implements java.io.Serializable {

	// Fields
	
	private String billNo;
	private WebUser webUserByUpdateUserFid;
	private WebUser webUserByCreateUserFid;	
	private VWebFact factNo;
	private String createDate;
	private String updateDate;
	private String visaType;
	private String visaTypeM;
	private List<WebColproductItems> webColproductItemses = new ArrayList<WebColproductItems>();
	private KyVisabillm vbm;
	private String title;
	private String colDateMain;//主表日期
	private String orderManMain;//主表下單人

	// Constructors

	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	/** default constructor */
	public WebColproductMain() {
	}

	/** minimal constructor */
	public WebColproductMain(String billNo) {
		this.billNo = billNo;
	}

	/** full constructor */
	public WebColproductMain( WebUser webUserByUpdateUserFid,
			WebUser webUserByCreateUserFid, String billNo, VWebFact factNo,
			String createDate, String updateDate, String visaType,
			String visaTypeM, List<WebColproductItems> webColproductItemses) {
		this.billNo = billNo;
		this.webUserByUpdateUserFid = webUserByUpdateUserFid;
		this.webUserByCreateUserFid = webUserByCreateUserFid;
		this.factNo = factNo;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.visaType = visaType;
		this.visaTypeM = visaTypeM;
		this.webColproductItemses = webColproductItemses;
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

	public KyVisabillm getVbm() {
		return vbm;
	}

	public void setVbm(KyVisabillm vbm) {
		this.vbm = vbm;
	}

	public String getColDateMain() {
		return colDateMain;
	}

	public void setColDateMain(String colDateMain) {
		this.colDateMain = colDateMain;
	}

	public String getOrderManMain() {
		return orderManMain;
	}

	public void setOrderManMain(String orderManMain) {
		this.orderManMain = orderManMain;
	}
	
	
	
	
	

	

}