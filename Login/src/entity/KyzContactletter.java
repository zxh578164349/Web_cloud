package entity;

import java.util.List;

/**
 * KyzContactletter entity. @author MyEclipse Persistence Tools
 */

/**
 * 
* 項目名稱：WebLogin   
* 類名稱：KyzContactletter   
* 類描述：內部聯絡函
* 創建人：KY2
 */
public class KyzContactletter implements java.io.Serializable {

	// Fields

	private KyzContactletterId id;
	private String secNo;
	private String chargeNo;
	private String ymExpect;
	private String userNm;
	private String toUser;
	private String chargeList;
	private String title;
	private String memoMk;
	private String visaType;
	private String filesYn;
	private String firstPage;
	private String factCode;
	private String colTemp;
	private String delMk;//刪除標記       0或空 未刪除     1刪除
	private String visaTypeM;//類別大類
	private KyVisabillm vbm;
	private List<KyzExpectmatmFile>list_file;
	private String userAccount;//申請人賬號
	private String emerMk;//是否緊急      0緊急       1不緊急 
	private List<KyzExpectmatmLog>list_logs;
	private Integer userId;
	private String useremail;
	private VWebFact factNo2;//關聯工廠名稱(不需要映射列名)

	// Constructors

	public String getUseremail(){
		return useremail;
	}

	public void setUseremail(String useremail){
		this.useremail=useremail;
	}

	public Integer getUserId(){
		return userId;
	}

	public void setUserId(Integer userId){
		this.userId=userId;
	}

	/** default constructor */
	public KyzContactletter() {
	}

	/** minimal constructor */
	public KyzContactletter(KyzContactletterId id) {
		this.id = id;
	}

	/** full constructor */
	public KyzContactletter(KyzContactletterId id, String secNo,
			String chargeNo, String ymExpect, String userNm, String toUser,
			String chargeList, String title, String memoMk, String visaType,
			String filesYn, String firstPage,String factCode,String colTemp
			,String delMk) {
		this.id = id;
		this.secNo = secNo;
		this.chargeNo = chargeNo;
		this.ymExpect = ymExpect;
		this.userNm = userNm;
		this.toUser = toUser;
		this.chargeList = chargeList;
		this.title = title;
		this.memoMk = memoMk;
		this.visaType = visaType;
		this.filesYn = filesYn;
		this.firstPage = firstPage;
		this.factCode=factCode;
		this.colTemp=colTemp;
		this.delMk=delMk;
	}

	// Property accessors

	public KyzContactletterId getId() {
		return this.id;
	}

	public void setId(KyzContactletterId id) {
		this.id = id;
	}

	public String getSecNo() {
		return this.secNo;
	}

	public void setSecNo(String secNo) {
		this.secNo = secNo;
	}

	public String getChargeNo() {
		return this.chargeNo;
	}

	public void setChargeNo(String chargeNo) {
		this.chargeNo = chargeNo;
	}

	public String getYmExpect() {
		return this.ymExpect;
	}

	public void setYmExpect(String ymExpect) {
		this.ymExpect = ymExpect;
	}

	public String getUserNm() {
		return this.userNm==null?"":userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getToUser() {
		return this.toUser==null?"":toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getChargeList() {
		return this.chargeList==null?"":chargeList;
	}

	public void setChargeList(String chargeList) {
		this.chargeList = chargeList;
	}

	public String getTitle() {
		return this.title==null?"":title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMemoMk() {
		return this.memoMk==null?"":memoMk;
	}

	public void setMemoMk(String memoMk) {
		this.memoMk = memoMk;
	}

	public String getVisaType() {
		return this.visaType;
	}

	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}

	public String getFilesYn() {
		return this.filesYn;
	}

	public void setFilesYn(String filesYn) {
		this.filesYn = filesYn;
	}

	public String getFirstPage() {
		return this.firstPage;
	}

	public void setFirstPage(String firstPage) {
		this.firstPage = firstPage;
	}

	public String getFactCode() {
		return factCode;
	}

	public void setFactCode(String factCode) {
		this.factCode = factCode;
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

	public String getVisaTypeM() {
		return visaTypeM;
	}

	public void setVisaTypeM(String visaTypeM) {
		this.visaTypeM = visaTypeM;
	}

	public KyVisabillm getVbm() {
		return vbm;
	}

	public void setVbm(KyVisabillm vbm) {
		this.vbm = vbm;
	}

	public List<KyzExpectmatmFile> getList_file() {
		return list_file;
	}

	public void setList_file(List<KyzExpectmatmFile> list_file) {
		this.list_file = list_file;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getEmerMk(){
		return emerMk;
	}

	public void setEmerMk(String emerMk){
		this.emerMk=emerMk;
	}
	
	public List<KyzExpectmatmLog> getList_logs(){
		return list_logs;
	}

	public void setList_logs(List<KyzExpectmatmLog> list_logs){
		this.list_logs=list_logs;
	}

	public VWebFact getFactNo2() {
		return factNo2;
	}

	public void setFactNo2(VWebFact factNo2) {
		this.factNo2 = factNo2;
	}
	
	
	
	

}