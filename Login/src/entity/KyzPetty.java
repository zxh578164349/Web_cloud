package entity;

/**
 * KyzPetty entity. @author MyEclipse Persistence Tools
 */

public class KyzPetty implements java.io.Serializable {

	// Fields

	private KyzPettyId id;
	private String currencyNo;
	private String datePaybill;
	private String itemNo;
	private String dateOver;
	private String deptNo;
	private String secNo;
	private String expenseMk;
	private String dbcdMk;
	private String acctNo;	 	
	private String noteMk;
	private String factExpense;
	private String deptExpense;
	private String vouMk;
	private String taxMk;
	private String dateVou;
	private String taxmMk;
	private String vouNo;
	private String venderCode;
	private Double cashExpense;
	private Double taxExpense;
	private String manHandle;
	private String memoMk1;
	private String memoMk2;
	private String memoMk3;
	private String memoMk4;
	private String userNm;
	private String timeCreate;
	private String itemYm;
	private String noteIn;
	private String expenseType;
	private String timeZd;

	// Constructors

	/** default constructor */
	public KyzPetty() {
	}

	/** minimal constructor */
	public KyzPetty(KyzPettyId id, String currencyNo, String datePaybill,
			String itemNo) {
		this.id = id;
		this.currencyNo = currencyNo;
		this.datePaybill = datePaybill;
		this.itemNo = itemNo;
	}

	/** full constructor */
	public KyzPetty(KyzPettyId id, String currencyNo, String datePaybill,
			String itemNo, String dateOver, String deptNo, String secNo,
			String expenseMk, String dbcdMk, String acctNo, String noteMk,
			String factExpense, String deptExpense, String vouMk, String taxMk,
			String dateVou, String taxmMk, String vouNo, String venderCode,
			Double cashExpense, Double taxExpense, String manHandle,
			String memoMk1, String memoMk2, String memoMk3, String memoMk4,
			String userNm, String timeCreate, String itemYm, String noteIn,
			String expenseType, String timeZd) {
		this.id = id;
		this.currencyNo = currencyNo;
		this.datePaybill = datePaybill;
		this.itemNo = itemNo;
		this.dateOver = dateOver;
		this.deptNo = deptNo;
		this.secNo = secNo;
		this.expenseMk = expenseMk;
		this.dbcdMk = dbcdMk;
		this.acctNo = acctNo;
		this.noteMk = noteMk;
		this.factExpense = factExpense;
		this.deptExpense = deptExpense;
		this.vouMk = vouMk;
		this.taxMk = taxMk;
		this.dateVou = dateVou;
		this.taxmMk = taxmMk;
		this.vouNo = vouNo;
		this.venderCode = venderCode;
		this.cashExpense = cashExpense;
		this.taxExpense = taxExpense;
		this.manHandle = manHandle;
		this.memoMk1 = memoMk1;
		this.memoMk2 = memoMk2;
		this.memoMk3 = memoMk3;
		this.memoMk4 = memoMk4;
		this.userNm = userNm;
		this.timeCreate = timeCreate;
		this.itemYm = itemYm;
		this.noteIn = noteIn;
		this.expenseType = expenseType;
		this.timeZd = timeZd;
	}

	// Property accessors

	public KyzPettyId getId() {
		return this.id;
	}

	public void setId(KyzPettyId id) {
		this.id = id;
	}

	public String getCurrencyNo() {
		return this.currencyNo;
	}

	public void setCurrencyNo(String currencyNo) {
		this.currencyNo = currencyNo;
	}

	public String getDatePaybill() {
		return this.datePaybill;
	}

	public void setDatePaybill(String datePaybill) {
		this.datePaybill = datePaybill;
	}

	public String getItemNo() {
		return this.itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getDateOver() {
		return this.dateOver;
	}

	public void setDateOver(String dateOver) {
		this.dateOver = dateOver;
	}

	public String getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getSecNo() {
		return this.secNo;
	}

	public void setSecNo(String secNo) {
		this.secNo = secNo;
	}

	public String getExpenseMk() {
		return this.expenseMk;
	}

	public void setExpenseMk(String expenseMk) {
		this.expenseMk = expenseMk;
	}

	public String getDbcdMk() {
		return this.dbcdMk;
	}

	public void setDbcdMk(String dbcdMk) {
		this.dbcdMk = dbcdMk;
	}

	public String getAcctNo() {
		return this.acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getNoteMk() {
		return this.noteMk;
	}

	public void setNoteMk(String noteMk) {
		this.noteMk = noteMk;
	}

	public String getFactExpense() {
		return this.factExpense;
	}

	public void setFactExpense(String factExpense) {
		this.factExpense = factExpense;
	}

	public String getDeptExpense() {
		return this.deptExpense;
	}

	public void setDeptExpense(String deptExpense) {
		this.deptExpense = deptExpense;
	}

	public String getVouMk() {
		return this.vouMk;
	}

	public void setVouMk(String vouMk) {
		this.vouMk = vouMk;
	}

	public String getTaxMk() {
		return this.taxMk;
	}

	public void setTaxMk(String taxMk) {
		this.taxMk = taxMk;
	}

	public String getDateVou() {
		return this.dateVou;
	}

	public void setDateVou(String dateVou) {
		this.dateVou = dateVou;
	}

	public String getTaxmMk() {
		return this.taxmMk;
	}

	public void setTaxmMk(String taxmMk) {
		this.taxmMk = taxmMk;
	}

	public String getVouNo() {
		return this.vouNo;
	}

	public void setVouNo(String vouNo) {
		this.vouNo = vouNo;
	}

	public String getVenderCode() {
		return this.venderCode;
	}

	public void setVenderCode(String venderCode) {
		this.venderCode = venderCode;
	}

	public Double getCashExpense() {
		return this.cashExpense;
	}

	public void setCashExpense(Double cashExpense) {
		this.cashExpense = cashExpense;
	}

	public Double getTaxExpense() {
		return this.taxExpense;
	}

	public void setTaxExpense(Double taxExpense) {
		this.taxExpense = taxExpense;
	}

	public String getManHandle() {
		return this.manHandle;
	}

	public void setManHandle(String manHandle) {
		this.manHandle = manHandle;
	}

	public String getMemoMk1() {
		return this.memoMk1;
	}

	public void setMemoMk1(String memoMk1) {
		this.memoMk1 = memoMk1;
	}

	public String getMemoMk2() {
		return this.memoMk2;
	}

	public void setMemoMk2(String memoMk2) {
		this.memoMk2 = memoMk2;
	}

	public String getMemoMk3() {
		return this.memoMk3;
	}

	public void setMemoMk3(String memoMk3) {
		this.memoMk3 = memoMk3;
	}

	public String getMemoMk4() {
		return this.memoMk4;
	}

	public void setMemoMk4(String memoMk4) {
		this.memoMk4 = memoMk4;
	}

	public String getUserNm() {
		return this.userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getTimeCreate() {
		return this.timeCreate;
	}

	public void setTimeCreate(String timeCreate) {
		this.timeCreate = timeCreate;
	}

	public String getItemYm() {
		return this.itemYm;
	}

	public void setItemYm(String itemYm) {
		this.itemYm = itemYm;
	}

	public String getNoteIn() {
		return this.noteIn;
	}

	public void setNoteIn(String noteIn) {
		this.noteIn = noteIn;
	}

	public String getExpenseType() {
		return this.expenseType;
	}

	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}

	public String getTimeZd() {
		return this.timeZd;
	}

	public void setTimeZd(String timeZd) {
		this.timeZd = timeZd;
	}

}