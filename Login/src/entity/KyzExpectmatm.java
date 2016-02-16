package entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * KyzExpectmatm entity. @author MyEclipse Persistence Tools
 */

public class KyzExpectmatm implements java.io.Serializable {

	// Fields

	private KyzExpectmatmId id;
	private String ymExpect;
	private String secNo;
	private String stockNo;
	private String userNm;
	private Date timeCreate;
	private String lockMk;
	private String dateLock;
	private String billMk;
	private String billOld;
	private String telNo;
	private String urgentMk;
	private String dateEtd;
	private String memoMk;
	private String memoSmk;
	private String username;
	private String emerWhether;
	private String factCode;
	private String visaType;
	private String filesYn;
	private String firstPage;
	private String colTemp;
	private String kyzsMk;
	private String delMk;//刪除標記        0或空 未刪除     1刪除
	private List<KyzExpectmats> kyzExpectmatses = new ArrayList();

	// Constructors

	/** default constructor */
	public KyzExpectmatm() {
	}

	/** minimal constructor */
	public KyzExpectmatm(KyzExpectmatmId id) {
		this.id = id;
	}

	/** full constructor */
	public KyzExpectmatm(KyzExpectmatmId id, String ymExpect, String secNo,
			String stockNo, String userNm, Date timeCreate, String lockMk,
			String dateLock, String billMk, String billOld, String telNo,
			String urgentMk, String dateEtd, String memoMk, String memoSmk,
			String username, String emerWhether, String factCode,String visaType,String filesYn,String firstPage,
			String colTemp,String kyzsMk,String delMk,List<KyzExpectmats> kyzExpectmatses) {
		this.id = id;
		this.ymExpect = ymExpect;
		this.secNo = secNo;
		this.stockNo = stockNo;
		this.userNm = userNm;
		this.timeCreate = timeCreate;
		this.lockMk = lockMk;
		this.dateLock = dateLock;
		this.billMk = billMk;
		this.billOld = billOld;
		this.telNo = telNo;
		this.urgentMk = urgentMk;
		this.dateEtd = dateEtd;
		this.memoMk = memoMk;
		this.memoSmk = memoSmk;
		this.username = username;
		this.emerWhether = emerWhether;
		this.factCode = factCode;
		this.visaType=visaType;
		this.filesYn=filesYn;
		this.firstPage=firstPage;
		this.colTemp=colTemp;
		this.kyzsMk=kyzsMk;
		this.delMk=delMk;
		this.kyzExpectmatses = kyzExpectmatses;
	}

	// Property accessors
	

	
	public String getVisaType() {
		return visaType;
	}

	public String getKyzsMk() {
		return kyzsMk;
	}

	public void setKyzsMk(String kyzsMk) {
		this.kyzsMk = kyzsMk;
	}

	public String getFilesYn() {
		return filesYn;
	}

	public void setFilesYn(String filesYn) {
		this.filesYn = filesYn;
	}

	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}

	public KyzExpectmatmId getId() {
		return this.id;
	}

	public void setId(KyzExpectmatmId id) {
		this.id = id;
	}

	public String getYmExpect() {
		return this.ymExpect;
	}

	public void setYmExpect(String ymExpect) {
		this.ymExpect = ymExpect;
	}

	public String getSecNo() {
		return this.secNo==null?"":secNo;
	}

	public void setSecNo(String secNo) {
		this.secNo = secNo;
	}

	public String getStockNo() {
		return this.stockNo;
	}

	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}

	public String getUserNm() {
		return this.userNm==null?"":userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}



	public Date getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}

	public String getLockMk() {
		return this.lockMk;
	}

	public void setLockMk(String lockMk) {
		this.lockMk = lockMk;
	}

	public String getDateLock() {
		return this.dateLock;
	}

	public void setDateLock(String dateLock) {
		this.dateLock = dateLock;
	}

	public String getBillMk() {
		return this.billMk;
	}

	public void setBillMk(String billMk) {
		this.billMk = billMk;
	}

	public String getBillOld() {
		return this.billOld;
	}

	public void setBillOld(String billOld) {
		this.billOld = billOld;
	}

	public String getTelNo() {
		return this.telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getUrgentMk() {
		return this.urgentMk;
	}

	public void setUrgentMk(String urgentMk) {
		this.urgentMk = urgentMk;
	}

	public String getDateEtd() {
		return this.dateEtd;
	}

	public void setDateEtd(String dateEtd) {
		this.dateEtd = dateEtd;
	}

	public String getMemoMk() {
		return this.memoMk==null?"":memoMk;
	}

	public void setMemoMk(String memoMk) {
		this.memoMk = memoMk;
	}

	public String getMemoSmk() {
		return this.memoSmk==null?"":memoSmk;
	}

	public void setMemoSmk(String memoSmk) {
		this.memoSmk = memoSmk;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmerWhether() {
		return this.emerWhether;
	}

	public void setEmerWhether(String emerWhether) {
		this.emerWhether = emerWhether;
	}

	public String getFactCode() {
		return this.factCode;
	}

	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}

	public List<KyzExpectmats> getKyzExpectmatses() {
		return kyzExpectmatses;
	}

	public void setKyzExpectmatses(List<KyzExpectmats> kyzExpectmatses) {
		this.kyzExpectmatses = kyzExpectmatses;
	}

	public String getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(String firstPage) {
		this.firstPage = firstPage;
	}

	public String getColTemp() {
		return colTemp;
	}

	public void setColTemp(String colTemp) {
		this.colTemp = colTemp;
	}

	public String getDelMk() {
		return delMk;
	}

	public void setDelMk(String delMk) {
		this.delMk = delMk;
	}
	
	
	



}