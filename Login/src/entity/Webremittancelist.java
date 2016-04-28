package entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Webremittancelist entity. @author MyEclipse Persistence Tools
 */

public class Webremittancelist implements java.io.Serializable {

	// Fields

	private String billNo;
	private String factNo;
	private String factCode;
	private String yymm;
	private String fromAccount;
	private String fromBank;
	private String createdate;
	private String username;//申請人名字
	private String udUsername;
	private String udDate;	
	private List<Webremittancelists> webremittancelistses = new ArrayList<Webremittancelists>();
	private KyVisabillm vbm;
	private String visaType;//小分類
	private WebType webtype;//大分類
	private String username2;//申請人賬號

	// Constructors

	/** default constructor */
	public Webremittancelist() {
	}

	/** minimal constructor */
	public Webremittancelist(String billNo) {
		this.billNo = billNo;
	}

	/** full constructor */
	public Webremittancelist(String billNo, String factNo, String factCode,
			String yymm, String fromAccount, String fromBank,
			String createdate, String username, String udUsername,
			String udDate, List<Webremittancelists> webremittancelistses) {
		this.billNo = billNo;
		this.factNo = factNo;
		this.factCode = factCode;
		this.yymm = yymm;
		this.fromAccount = fromAccount;
		this.fromBank = fromBank;
		this.createdate = createdate;
		this.username = username;
		this.udUsername = udUsername;
		this.udDate = udDate;
		this.webremittancelistses = webremittancelistses;
	}

	// Property accessors

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

	public String getFactCode() {
		return this.factCode;
	}

	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}

	public String getYymm() {
		return this.yymm;
	}

	public void setYymm(String yymm) {
		this.yymm = yymm;
	}

	public String getFromAccount() {
		return this.fromAccount;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public String getFromBank() {
		return this.fromBank;
	}

	public void setFromBank(String fromBank) {
		this.fromBank = fromBank;
	}

	public String getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUdUsername() {
		return this.udUsername;
	}

	public void setUdUsername(String udUsername) {
		this.udUsername = udUsername;
	}

	public String getUdDate() {
		return this.udDate;
	}

	public void setUdDate(String udDate) {
		this.udDate = udDate;
	}

	public List<Webremittancelists> getWebremittancelistses() {
		return webremittancelistses;
	}

	public void setWebremittancelistses(
			List<Webremittancelists> webremittancelistses) {
		this.webremittancelistses = webremittancelistses;
	}

	public String getVisaType() {
		return visaType;
	}

	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}
	
	public KyVisabillm getVbm() {
		return vbm;
	}

	public void setVbm(KyVisabillm vbm) {
		this.vbm = vbm;
	}

	public WebType getWebtype() {
		return webtype;
	}

	public void setWebtype(WebType webtype) {
		this.webtype = webtype;
	}

	public String getUsername2() {
		return username2;
	}

	public void setUsername2(String username2) {
		this.username2 = username2;
	}
	
	

	

}