package entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * KyVisabillm entity. @author MyEclipse Persistence Tools
 */

public class KyVisabillm implements java.io.Serializable {

	// Fields

	private KyVisabillmId id;
	private String purmanNo;
	private String signerNext;//下一位審核人(Email)
	private String signerLast;//最近審核人(Email)
	private String lastMk;//最近審核狀態(最近一個審核人決定  :Y:已審核;T:打回)
	private String visaMk;//審核狀態(最後一個人決定: N:未審核;Y:已審核;T:打回)
	private String revisaMk;//重審狀態
	private String itemNext;//下一個項次
	private String itemLast;//最近項次
	private String memoMk;
	private String dateCreate;
	private String emailMk;//是否已经发送知会email
	private String colTemp;
	private List<KyVisabills> kyVisabillses = new ArrayList<KyVisabills>();

	// Constructors

	/** default constructor */
	public KyVisabillm() {
	}

	/** minimal constructor */
	public KyVisabillm(KyVisabillmId id) {
		this.id = id;
	}

	/** full constructor */
	public KyVisabillm(KyVisabillmId id, String purmanNo, String signerNext,
			String signerLast, String lastMk, String visaMk, String revisaMk,
			String itemNext, String itemLast, String memoMk, String dateCreate,
			String emailMk,String colTemp,List<KyVisabills> kyVisabillses) {
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
		this.emailMk=emailMk;
		this.colTemp=colTemp;
		this.kyVisabillses = kyVisabillses;
	}

	// Property accessors

	public KyVisabillmId getId() {
		return this.id;
	}

	public void setId(KyVisabillmId id) {
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
	

	public String getColTemp() {
		return colTemp;
	}

	public void setColTemp(String colTemp) {
		this.colTemp = colTemp;
	}
	

	public String getEmailMk() {
		return emailMk;
	}

	public void setEmailMk(String emailMk) {
		this.emailMk = emailMk;
	}

	public List<KyVisabills> getKyVisabillses() {
		return kyVisabillses;
	}

	public void setKyVisabillses(List<KyVisabills> kyVisabillses) {
		this.kyVisabillses = kyVisabillses;
	}





}