package entity;

/**
 * WebWeeklyreport entity. @author MyEclipse Persistence Tools
 */

/**
 * 
* 項目名稱：WebLogin   
* 類名稱：WebWeeklyreport   
* 類描述：業務每週報表
* 創建人：KY2
 */
public class WebWeeklyreport implements java.io.Serializable {

	// Fields

	private Integer RId;
	private WebUser webUser;
	private WebErpBrankProcess webErpBrankProcess;
	private VWebFact VWebFact;
	private String SDate;
	private String EDate;
	private String RTitle;
	private String RContent;
	private Integer weeks;
	private String createDate;
	private String updateDate;
	private String RContentLast;//上周事項

	// Constructors

	/** default constructor */
	public WebWeeklyreport() {
	}

	/** minimal constructor */
	public WebWeeklyreport(Integer RId) {
		this.RId = RId;
	}

	/** full constructor */
	public WebWeeklyreport(Integer RId, WebUser webUser,
			WebErpBrankProcess webErpBrankProcess, VWebFact VWebFact,
			String SDate, String EDate, String RTitle, String RContent,
			Integer weeks, String createDate, String updateDate) {
		this.RId = RId;
		this.webUser = webUser;
		this.webErpBrankProcess = webErpBrankProcess;
		this.VWebFact = VWebFact;
		this.SDate = SDate;
		this.EDate = EDate;
		this.RTitle = RTitle;
		this.RContent = RContent;
		this.weeks = weeks;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	// Property accessors

	public Integer getRId() {
		return this.RId;
	}

	public void setRId(Integer RId) {
		this.RId = RId;
	}

	public WebUser getWebUser() {
		return this.webUser;
	}

	public void setWebUser(WebUser webUser) {
		this.webUser = webUser;
	}

	public WebErpBrankProcess getWebErpBrankProcess() {
		return this.webErpBrankProcess;
	}

	public void setWebErpBrankProcess(WebErpBrankProcess webErpBrankProcess) {
		this.webErpBrankProcess = webErpBrankProcess;
	}

	public VWebFact getVWebFact() {
		return this.VWebFact;
	}

	public void setVWebFact(VWebFact VWebFact) {
		this.VWebFact = VWebFact;
	}

	public String getSDate() {
		return this.SDate;
	}

	public void setSDate(String SDate) {
		this.SDate = SDate;
	}

	public String getEDate() {
		return this.EDate;
	}

	public void setEDate(String EDate) {
		this.EDate = EDate;
	}

	public String getRTitle() {
		return this.RTitle;
	}

	public void setRTitle(String RTitle) {
		this.RTitle = RTitle;
	}

	public String getRContent() {
		return this.RContent;
	}

	public void setRContent(String RContent) {
		this.RContent = RContent;
	}

	public Integer getWeeks() {
		return this.weeks;
	}

	public void setWeeks(Integer weeks) {
		this.weeks = weeks;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getRContentLast() {
		return RContentLast;
	}

	public void setRContentLast(String rContentLast) {
		RContentLast = rContentLast;
	}
	
	

}