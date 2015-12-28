package entity;

/**
 * WebFactorder entity. @author MyEclipse Persistence Tools
 */

public class WebFactorder implements java.io.Serializable {

	// Fields

	private Long orderId;
	private String factNo;
	private String factArea;
	private String brank;
	private String customer;
	private String modelNo;
	private Double orderData;

	// Constructors

	/** default constructor */
	public WebFactorder() {
	}

	/** minimal constructor */
	public WebFactorder(Long orderId) {
		this.orderId = orderId;
	}

	/** full constructor */
	public WebFactorder(Long orderId, String factNo, String factArea,
			String brank, String customer, String modelNo, Double orderData) {
		this.orderId = orderId;
		this.factNo = factNo;
		this.factArea = factArea;
		this.brank = brank;
		this.customer = customer;
		this.modelNo = modelNo;
		this.orderData = orderData;
	}

	// Property accessors

	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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

}