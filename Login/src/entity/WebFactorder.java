package entity;

/**
 * WebFactorder entity. @author MyEclipse Persistence Tools
 */

public class WebFactorder implements java.io.Serializable {

	// Fields

	private Long orderId;
	private String factSname;
	private String component;
	private String brank;
	private String customer;
	private String modelNo;
	private Double orderData;
	private String yymm;
	private String factNo;
	private String factArea;

	// Constructors

	/** default constructor */
	public WebFactorder() {
	}

	/** minimal constructor */
	public WebFactorder(Long orderId) {
		this.orderId = orderId;
	}
	public WebFactorder( String factSname, String component,
			String brank, String customer, String modelNo, 
			String yymm) {
		this.factSname = factSname;
		this.component = component;
		this.brank = brank;
		this.customer = customer;
		this.modelNo = modelNo;
		this.yymm = yymm;
	}
	

	/** full constructor */
	public WebFactorder(Long orderId, String factSname, String component,
			String brank, String customer, String modelNo, Double orderData,
			String yymm, String factNo, String factArea) {
		this.orderId = orderId;
		this.factSname = factSname;
		this.component = component;
		this.brank = brank;
		this.customer = customer;
		this.modelNo = modelNo;
		this.orderData = orderData;
		this.yymm = yymm;
		this.factNo = factNo;
		this.factArea = factArea;
	}

	// Property accessors

	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getFactSname() {
		return this.factSname;
	}

	public void setFactSname(String factSname) {
		this.factSname = factSname;
	}

	public String getComponent() {
		return this.component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getBrank() {
		return this.brank;
	}

	public void setBrank(String brank) {
		this.brank = brank;
	}

	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getModelNo() {
		return this.modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public Double getOrderData() {
		return this.orderData;
	}

	public void setOrderData(Double orderData) {
		this.orderData = orderData;
	}

	public String getYymm() {
		return this.yymm;
	}

	public void setYymm(String yymm) {
		this.yymm = yymm;
	}

	public String getFactNo() {
		return this.factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public String getFactArea() {
		return this.factArea;
	}

	public void setFactArea(String factArea) {
		this.factArea = factArea;
	}

}