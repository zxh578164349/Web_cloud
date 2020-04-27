package entity;

/**
 * WebFactorder entity. @author MyEclipse Persistence Tools
 */

/**
 * 
* 項目名稱：WebLogin   
* 類名稱：WebFactorder   
* 類描述：工廠訂單
* 創建人：KY2
 */
public class WebFactorder implements java.io.Serializable {

	// Fields

	private WebFactorderId id;
	private Long orderId;
	private String factSname;
	private Double orderData;
	private String colTemp;

	// Constructors

	/** default constructor */
	public WebFactorder() {
	}

	/** minimal constructor */
	public WebFactorder(WebFactorderId id, Long orderId) {
		this.id = id;
		this.orderId = orderId;
	}

	/** full constructor */
	public WebFactorder(WebFactorderId id, Long orderId, String factSname,
			Double orderData, String colTemp) {
		this.id = id;
		this.orderId = orderId;
		this.factSname = factSname;
		this.orderData = orderData;
		this.colTemp = colTemp;
	}

	// Property accessors

	public WebFactorderId getId() {
		return this.id;
	}

	public void setId(WebFactorderId id) {
		this.id = id;
	}

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

	public Double getOrderData() {
		return this.orderData;
	}

	public void setOrderData(Double orderData) {
		this.orderData = orderData;
	}

	public String getColTemp() {
		return this.colTemp;
	}

	public void setColTemp(String colTemp) {
		this.colTemp = colTemp;
	}

}