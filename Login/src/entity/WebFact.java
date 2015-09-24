package entity;

/**
 * WebFact entity. @author MyEclipse Persistence Tools
 */

public class WebFact implements java.io.Serializable {

	// Fields

	private WebFactId id;
	private String factSname;
	private String orderNo;
	private String factCode;
	private String fcodeIndex;
	private String factShow;
	private String factShowA;
	private String factDisable;
	private String openDate;
	private String closeDate;

	// Constructors

	/** default constructor */
	public WebFact() {
	}

	/** minimal constructor */
	public WebFact(WebFactId id) {
		this.id = id;
	}

	/** full constructor */
	public WebFact(WebFactId id, String factSname, String orderNo,
			String factCode,String fcodeIndex,String factShow,String factShowA,String factDisable,
			String openDate,String closeDate) {
		this.id = id;
		this.factSname = factSname;
		this.orderNo = orderNo;
		this.factCode = factCode;
		this.fcodeIndex=fcodeIndex;
		this.factShow=factShow;
		this.factShowA=factShowA;
		this.factDisable=factDisable;
		this.openDate=openDate;
		this.closeDate=closeDate;
	}

	// Property accessors

	public WebFactId getId() {
		return this.id;
	}

	public void setId(WebFactId id) {
		this.id = id;
	}

	public String getFactSname() {
		return this.factSname;
	}

	public void setFactSname(String factSname) {
		this.factSname = factSname;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getFactCode() {
		return this.factCode;
	}

	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}

	public String getFcodeIndex() {
		return fcodeIndex;
	}

	public void setFcodeIndex(String fcodeIndex) {
		this.fcodeIndex = fcodeIndex;
	}

	public String getFactShow() {
		return factShow;
	}

	public void setFactShow(String factShow) {
		this.factShow = factShow;
	}

	public String getFactShowA() {
		return factShowA;
	}

	public void setFactShowA(String factShowA) {
		this.factShowA = factShowA;
	}

	public String getFactDisable() {
		return factDisable;
	}

	public void setFactDisable(String factDisable) {
		this.factDisable = factDisable;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}
	
	
	

}