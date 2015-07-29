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
			String factCode,String fcodeIndex,String factShow,String factShowA) {
		this.id = id;
		this.factSname = factSname;
		this.orderNo = orderNo;
		this.factCode = factCode;
		this.fcodeIndex=fcodeIndex;
		this.factShow=factShow;
		this.factShowA=factShowA;
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
	
	
	

}