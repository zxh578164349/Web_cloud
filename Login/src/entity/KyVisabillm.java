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
	private String purmanNo;//函文承辦人
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
	private String delMk;//刪除標記    0或空 未刪除     1刪除
	private String userCreate;//函文創新者
	private List<KyVisabills> kyVisabillses = new ArrayList<KyVisabills>();
	private WebBussinessletter webbussletter;
	private KyzExpectmatm kyzexp;
	private KyzContactletter kyzletter;
	private Webremittancelist webrel;
	private WebFormula formula;//工廠配方
	private String oneMk;//發送一次標識    0或null :正常發送    1:隻發送1次
	private VWebFact factNo2;//關聯工廠名稱(不需要映射列名)
	private String general;//函文概要(不需要映射列名)
	private String visaSortM;//函文大類
	private WebType webtype;//函文大類外鍵
	private WebUser userId;
	private String useremail;//創建人email

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

	public WebBussinessletter getWebbussletter() {
		return webbussletter;
	}

	public void setWebbussletter(WebBussinessletter webbussletter) {
		this.webbussletter = webbussletter;
	}

	public String getDelMk() {
		return delMk;
	}

	public void setDelMk(String delMk) {
		this.delMk = delMk;
	}

	public KyzExpectmatm getKyzexp() {
		return kyzexp;
	}

	public void setKyzexp(KyzExpectmatm kyzexp) {
		this.kyzexp = kyzexp;
	}

	public KyzContactletter getKyzletter() {
		return kyzletter;
	}

	public void setKyzletter(KyzContactletter kyzletter) {
		this.kyzletter = kyzletter;
	}

	public Webremittancelist getWebrel() {
		return webrel;
	}

	public void setWebrel(Webremittancelist webrel) {
		this.webrel = webrel;
	}

	public WebFormula getFormula(){
		return formula;
	}

	public void setFormula(WebFormula formula){
		this.formula=formula;
	}

	public String getUserCreate(){
		return userCreate;
	}

	public void setUserCreate(String userCreate){
		this.userCreate=userCreate;
	}

	public String getOneMk(){
		return oneMk;
	}

	public void setOneMk(String oneMk){
		this.oneMk=oneMk;
	}

	public String getGeneral(){
		return general;
	}

	public void setGeneral(String general){
		this.general=general;
	}

	public VWebFact getFactNo2(){
		return factNo2;
	}

	public void setFactNo2(VWebFact factNo2){
		this.factNo2=factNo2;
	}

	public String getVisaSortM(){
		return visaSortM;
	}

	public void setVisaSortM(String visaSortM){
		this.visaSortM=visaSortM;
	}

	public WebType getWebtype(){
		return webtype;
	}

	public void setWebtype(WebType webtype){
		this.webtype=webtype;
	}

	public WebUser getUserId(){
		return userId;
	}

	public void setUserId(WebUser userId){
		this.userId=userId;
	}

	public String getUseremail(){
		return useremail;
	}

	public void setUseremail(String useremail){
		this.useremail=useremail;
	}

	
	
	

	
	
	
	
	
	





}