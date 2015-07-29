package entity;

/**
 * KyzExpectmats entity. @author MyEclipse Persistence Tools
 */

public class KyzExpectmats implements java.io.Serializable {

	// Fields

	private KyzExpectmatsId id;
	private String matNo;
	private Double qtyExpect;
	private String memoMk;
	private Double qtyOk;
	private Double personNo;
	private String useNm;
	private String changeNo;
	private String lastTime;
	private String qtyPair;
	private String itemNm;
	private String moneyType;
	private String factCode;

	// Constructors

	/** default constructor */
	public KyzExpectmats() {
	}

	/** minimal constructor */
	public KyzExpectmats(KyzExpectmatsId id) {
		this.id = id;
	}

	/** full constructor */
	public KyzExpectmats(KyzExpectmatsId id, String matNo, Double qtyExpect,
			String memoMk, Double qtyOk, Double personNo, String useNm,
			String changeNo, String lastTime, String qtyPair, String itemNm,
			String moneyType, String factCode) {
		this.id = id;
		this.matNo = matNo;
		this.qtyExpect = qtyExpect;
		this.memoMk = memoMk;
		this.qtyOk = qtyOk;
		this.personNo = personNo;
		this.useNm = useNm;
		this.changeNo = changeNo;
		this.lastTime = lastTime;
		this.qtyPair = qtyPair;
		this.itemNm = itemNm;
		this.moneyType = moneyType;
		this.factCode = factCode;
	}

	// Property accessors

	public KyzExpectmatsId getId() {
		return this.id;
	}

	public void setId(KyzExpectmatsId id) {
		this.id = id;
	}

	public String getMatNo() {
		return this.matNo;
	}

	public void setMatNo(String matNo) {
		this.matNo = matNo;
	}

	public Double getQtyExpect() {
		return this.qtyExpect;
	}

	public void setQtyExpect(Double qtyExpect) {
		this.qtyExpect = qtyExpect;
	}

	public String getMemoMk() {
		return this.memoMk;
	}

	public void setMemoMk(String memoMk) {
		this.memoMk = memoMk;
	}

	public Double getQtyOk() {
		return this.qtyOk;
	}

	public void setQtyOk(Double qtyOk) {
		this.qtyOk = qtyOk;
	}

	public Double getPersonNo() {
		return this.personNo;
	}

	public void setPersonNo(Double personNo) {
		this.personNo = personNo;
	}

	public String getUseNm() {
		return this.useNm;
	}

	public void setUseNm(String useNm) {
		this.useNm = useNm;
	}

	public String getChangeNo() {
		return this.changeNo;
	}

	public void setChangeNo(String changeNo) {
		this.changeNo = changeNo;
	}

	public String getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public String getQtyPair() {
		return this.qtyPair;
	}

	public void setQtyPair(String qtyPair) {
		this.qtyPair = qtyPair;
	}

	public String getItemNm() {
		return this.itemNm;
	}

	public void setItemNm(String itemNm) {
		this.itemNm = itemNm;
	}

	public String getMoneyType() {
		return this.moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}

	public String getFactCode() {
		return this.factCode;
	}

	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}

}