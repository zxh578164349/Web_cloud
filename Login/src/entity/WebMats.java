package entity;

/**
 * WebMats entity. @author MyEclipse Persistence Tools
 */

public class WebMats implements java.io.Serializable {

	// Fields

	private WebMatsId id;
	private String factCode;
	private Double matC;
	private Double matS;
	private String dateB;
	private String dateE;
	private String lockMk;
	private String userNo;
	private String dateTime;
	private Double countDanhao;
	private Double moneyDanhao;

	// Constructors

	/** default constructor */
	public WebMats() {
	}

	/** minimal constructor */
	public WebMats(WebMatsId id) {
		this.id = id;
	}

	/** full constructor */
	public WebMats(WebMatsId id, String factCode, Double matC, Double matS,
			String dateB, String dateE, String lockMk, String userNo,
			String dateTime, Double countDanhao, Double moneyDanhao) {
		this.id = id;
		this.factCode = factCode;
		this.matC = matC;
		this.matS = matS;
		this.dateB = dateB;
		this.dateE = dateE;
		this.lockMk = lockMk;
		this.userNo = userNo;
		this.dateTime = dateTime;
		this.countDanhao = countDanhao;
		this.moneyDanhao = moneyDanhao;
	}

	// Property accessors

	public WebMatsId getId() {
		return this.id;
	}

	public void setId(WebMatsId id) {
		this.id = id;
	}

	public String getFactCode() {
		return this.factCode;
	}

	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}

	public Double getMatC() {
		return this.matC;
	}

	public void setMatC(Double matC) {
		this.matC = matC;
	}

	public Double getMatS() {
		return this.matS;
	}

	public void setMatS(Double matS) {
		this.matS = matS;
	}

	public String getDateB() {
		return this.dateB;
	}

	public void setDateB(String dateB) {
		this.dateB = dateB;
	}

	public String getDateE() {
		return this.dateE;
	}

	public void setDateE(String dateE) {
		this.dateE = dateE;
	}

	public String getLockMk() {
		return this.lockMk;
	}

	public void setLockMk(String lockMk) {
		this.lockMk = lockMk;
	}

	public String getUserNo() {
		return this.userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public Double getCountDanhao() {
		return this.countDanhao;
	}

	public void setCountDanhao(Double countDanhao) {
		this.countDanhao = countDanhao;
	}

	public Double getMoneyDanhao() {
		return this.moneyDanhao;
	}

	public void setMoneyDanhao(Double moneyDanhao) {
		this.moneyDanhao = moneyDanhao;
	}

}