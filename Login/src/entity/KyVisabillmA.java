package entity;

/**
 * KyVisabillmA entity. @author MyEclipse Persistence Tools
 */

public class KyVisabillmA implements java.io.Serializable {

	// Fields

	private KyVisabillmAId id;
	private String purmanNo;
	private String signerNext;
	private String signerLast;
	private String lastMk;
	private String visaMk;
	private String revisaMk;
	private String itemNext;
	private String itemLast;
	private String memoMk;
	private String dateCreate;
	private String emailMk;
	private String colTemp;
	private String delMk;
	private String userCreate;
	private String oneMk;
	private String visaSortM;
	private String useremail;
	private Integer fkWebuser;

	// Constructors

	/** default constructor */
	public KyVisabillmA() {
	}

	/** minimal constructor */
	public KyVisabillmA(KyVisabillmAId id) {
		this.id = id;
	}

	/** full constructor */
	public KyVisabillmA(KyVisabillmAId id, String purmanNo, String signerNext,
			String signerLast, String lastMk, String visaMk, String revisaMk,
			String itemNext, String itemLast, String memoMk, String dateCreate,
			String emailMk, String colTemp, String delMk, String userCreate,
			String oneMk, String visaSortM, String useremail, Integer fkWebuser) {
		this.id = id;
		this.purmanNo = purmanNo;
		this.signerNext = signerNext;
		this.signerLast = signerLast;
		this.lastMk = lastMk;
		this.visaMk = visaMk;
		this.revisaMk = revisaMk;
		this.itemNext = itemNext;
		this.itemLast = itemLast;
		this.memoMk = memoMk;
		this.dateCreate = dateCreate;
		this.emailMk = emailMk;
		this.colTemp = colTemp;
		this.delMk = delMk;
		this.userCreate = userCreate;
		this.oneMk = oneMk;
		this.visaSortM = visaSortM;
		this.useremail = useremail;
		this.fkWebuser = fkWebuser;
	}

	// Property accessors

	public KyVisabillmAId getId() {
		return this.id;
	}

	public void setId(KyVisabillmAId id) {
		this.id = id;
	}

	public String getPurmanNo() {
		return this.purmanNo;
	}

	public void setPurmanNo(String purmanNo) {
		this.purmanNo = purmanNo;
	}

	public String getSignerNext() {
		return this.signerNext;
	}

	public void setSignerNext(String signerNext) {
		this.signerNext = signerNext;
	}

	public String getSignerLast() {
		return this.signerLast;
	}

	public void setSignerLast(String signerLast) {
		this.signerLast = signerLast;
	}

	public String getLastMk() {
		return this.lastMk;
	}

	public void setLastMk(String lastMk) {
		this.lastMk = lastMk;
	}

	public String getVisaMk() {
		return this.visaMk;
	}

	public void setVisaMk(String visaMk) {
		this.visaMk = visaMk;
	}

	public String getRevisaMk() {
		return this.revisaMk;
	}

	public void setRevisaMk(String revisaMk) {
		this.revisaMk = revisaMk;
	}

	public String getItemNext() {
		return this.itemNext;
	}

	public void setItemNext(String itemNext) {
		this.itemNext = itemNext;
	}

	public String getItemLast() {
		return this.itemLast;
	}

	public void setItemLast(String itemLast) {
		this.itemLast = itemLast;
	}

	public String getMemoMk() {
		return this.memoMk;
	}

	public void setMemoMk(String memoMk) {
		this.memoMk = memoMk;
	}

	public String getDateCreate() {
		return this.dateCreate;
	}

	public void setDateCreate(String dateCreate) {
		this.dateCreate = dateCreate;
	}

	public String getEmailMk() {
		return this.emailMk;
	}

	public void setEmailMk(String emailMk) {
		this.emailMk = emailMk;
	}

	public String getColTemp() {
		return this.colTemp;
	}

	public void setColTemp(String colTemp) {
		this.colTemp = colTemp;
	}

	public String getDelMk() {
		return this.delMk;
	}

	public void setDelMk(String delMk) {
		this.delMk = delMk;
	}

	public String getUserCreate() {
		return this.userCreate;
	}

	public void setUserCreate(String userCreate) {
		this.userCreate = userCreate;
	}

	public String getOneMk() {
		return this.oneMk;
	}

	public void setOneMk(String oneMk) {
		this.oneMk = oneMk;
	}

	public String getVisaSortM() {
		return this.visaSortM;
	}

	public void setVisaSortM(String visaSortM) {
		this.visaSortM = visaSortM;
	}

	public String getUseremail() {
		return this.useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public Integer getFkWebuser() {
		return this.fkWebuser;
	}

	public void setFkWebuser(Integer fkWebuser) {
		this.fkWebuser = fkWebuser;
	}

}