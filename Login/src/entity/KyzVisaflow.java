package entity;

/**
 * KyzVisaflow entity. @author MyEclipse Persistence Tools
 */

/**
 * 
* 項目名稱：WebLogin   
* 類名稱：KyzVisaflow   
* 類描述：簽核流程
* 創建人：KY2
 */
public class KyzVisaflow implements java.io.Serializable {

	// Fields

	private KyzVisaflowId id;
	private String visaSigner;
	private String visaRank;
	private String flowMk;
	private String colTemp;
	private String visaSortM;//類別大類
	private String delMk;//刪除標記        0或空 未刪除     1刪除
	private String typeMk; //區分 出差類(TR)    配方類(PF)     其它類(0)
	private String trMk;//是否分部門     Y:是   N:否
	private WebDepartment depId;//用於同一個帳號可以在多個部門妹建立同一類別的流程    (PS分部門的，並且申請人才有的；default表示默認值)
	private String visible;//是否可見      Y可見     N不可見
	private WebType webtype;//函文大類外鍵
	private WebFormtype webformtype;//函文小類外鍵

	// Constructors

	/** default constructor */
	public KyzVisaflow() {
	}

	/** minimal constructor */
	public KyzVisaflow(KyzVisaflowId id) {
		this.id = id;
	}

	/** full constructor */
	public KyzVisaflow(KyzVisaflowId id, String visaSigner, String visaRank,String flowMk,String colTemp) {
		this.id = id;
		this.visaSigner = visaSigner;
		this.visaRank = visaRank;
		this.flowMk=flowMk;
		this.colTemp=colTemp;
	}

	// Property accessors

	public KyzVisaflowId getId() {
		return this.id;
	}

	public void setId(KyzVisaflowId id) {
		this.id = id;
	}

	public String getVisaSigner() {
		return this.visaSigner;
	}

	public void setVisaSigner(String visaSigner) {
		this.visaSigner = visaSigner;
	}

	public String getVisaRank() {
		return this.visaRank;
	}

	public void setVisaRank(String visaRank) {
		this.visaRank = visaRank;
	}

	public String getFlowMk() {
		return flowMk;
	}

	public void setFlowMk(String flowMk) {
		this.flowMk = flowMk;
	}

	public String getColTemp() {
		return colTemp;
	}

	public void setColTemp(String colTemp) {
		this.colTemp = colTemp;
	}

	public String getVisaSortM() {
		return visaSortM;
	}

	public void setVisaSortM(String visaSortM) {
		this.visaSortM = visaSortM;
	}

	public String getDelMk() {
		return delMk;
	}

	public void setDelMk(String delMk) {
		this.delMk = delMk;
	}

	public String getTypeMk(){
		return typeMk;
	}

	public void setTypeMk(String typeMk){
		this.typeMk=typeMk;
	}

	public String getTrMk(){
		return trMk;
	}

	public void setTrMk(String trMk){
		this.trMk=trMk;
	}

	public WebDepartment getDepId(){
		return depId;
	}

	public void setDepId(WebDepartment depId){
		this.depId=depId;
	}

	public String getVisible(){
		return visible;
	}

	public void setVisible(String visible){
		this.visible=visible;
	}

	public WebType getWebtype() {
		return webtype;
	}

	public void setWebtype(WebType webtype) {
		this.webtype = webtype;
	}

	public WebFormtype getWebformtype() {
		return webformtype;
	}

	public void setWebformtype(WebFormtype webformtype) {
		this.webformtype = webformtype;
	}

	
	

	
	
	

	
	


	
	

}