package entity;

/**
 * VWebydataawebcashout entity. @author MyEclipse Persistence Tools
 */

public class VWebydataawebcashout implements java.io.Serializable {

	// Fields

	private VWebydataawebcashoutId id;
	private Double onModulus;//生產檢核
	private Double realpairs;//交單雙數
	private Double convertmoney;//折算金額
	private Double realcashoutpairs;//請款雙數
	private Double realcashoutmoney;//請款金額
	private String workorholiday;

	// Constructors

	/** default constructor */
	public VWebydataawebcashout() {
	}

	/** minimal constructor */
	public VWebydataawebcashout(VWebydataawebcashoutId id) {
		this.id = id;
	}

	/** full constructor */
	public VWebydataawebcashout(VWebydataawebcashoutId id, Double onModulus,
			Double realpairs, Double convertmoney, Double realcashoutpairs,
			Double realcashoutmoney, String workorholiday) {
		this.id = id;
		this.onModulus = onModulus;
		this.realpairs = realpairs;
		this.convertmoney = convertmoney;
		this.realcashoutpairs = realcashoutpairs;
		this.realcashoutmoney = realcashoutmoney;
		this.workorholiday = workorholiday;
	}

	// Property accessors

	public VWebydataawebcashoutId getId() {
		return this.id;
	}

	public void setId(VWebydataawebcashoutId id) {
		this.id = id;
	}

	public Double getOnModulus() {
		return this.onModulus;
	}

	public void setOnModulus(Double onModulus) {
		this.onModulus = onModulus;
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

	public String getWorkorholiday() {
		return this.workorholiday;
	}

	public void setWorkorholiday(String workorholiday) {
		this.workorholiday = workorholiday;
	}

}