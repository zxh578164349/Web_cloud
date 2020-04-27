package entity;

/**
 * Webcashout entity. @author MyEclipse Persistence Tools
 */

/**
 * 
* 項目名稱：WebLogin   
* 類名稱：Webcashout   
* 類描述：請款資料
* 創建人：KY2
 */
public class Webcashout implements java.io.Serializable {

	// Fields

	private WebcashoutId id;
	private Double realpairs;
	private Double convertmoney;
	private Double realcashoutpairs;
	private Double realcashoutmoney;
	private String username;
	private String usernameUd;

	// Constructors

	/** default constructor */
	public Webcashout() {
	}

	/** minimal constructor */
	public Webcashout(WebcashoutId id) {
		this.id = id;
	}

	/** full constructor */
	public Webcashout(WebcashoutId id, Double realpairs, Double convertmoney,
			Double realcashoutpairs, Double realcashoutmoney,String username) {
		this.id = id;
		this.realpairs = realpairs;
		this.convertmoney = convertmoney;
		this.realcashoutpairs = realcashoutpairs;
		this.realcashoutmoney = realcashoutmoney;
		this.username=username;
	}

	// Property accessors

	public WebcashoutId getId() {
		return this.id;
	}

	public void setId(WebcashoutId id) {
		this.id = id;
	}

	public Double getRealpairs() {
		return this.realpairs;
	}

	public void setRealpairs(Double realpairs) {
		this.realpairs = realpairs;
	}

	public Double getConvertmoney() {
		return this.convertmoney;
	}

	public void setConvertmoney(Double convertmoney) {
		this.convertmoney = convertmoney;
	}

	public Double getRealcashoutpairs() {
		return this.realcashoutpairs;
	}

	public void setRealcashoutpairs(Double realcashoutpairs) {
		this.realcashoutpairs = realcashoutpairs;
	}

	public Double getRealcashoutmoney() {
		return this.realcashoutmoney;
	}

	public void setRealcashoutmoney(Double realcashoutmoney) {
		this.realcashoutmoney = realcashoutmoney;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsernameUd() {
		return usernameUd;
	}

	public void setUsernameUd(String usernameUd) {
		this.usernameUd = usernameUd;
	}
	

}