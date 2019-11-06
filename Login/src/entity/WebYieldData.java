package entity;

/**
 * WebYieldData entity. @author MyEclipse Persistence Tools
 */

public class WebYieldData implements java.io.Serializable {

	// Fields

	private WebYieldDataId id;
	private Double onModulus;//上模數(模)
	private Double personnum;//人數(人)
	private Double standardOutput;//標準產量(模)
	private Double actualYield;//實際產量(模)
	private Double achievingRate;//達成率(%)
	private String workorholiday;//工作日/假日
	private Double daycount;//天數(天)
	private Double actualpairs;//實際生產雙數(雙)
	private Double hostpairs;//客補生產雙數(雙)
	private Double factpairs;//廠補生產雙數(雙)
	private Double samplepairs;//樣品生產雙數(雙)
	private Double outnum;//出貨數(雙)
	private Double backnum;//退貨數(雙)
	private Double workhours;//上模總工時(小時)
	private String username;
	private String usernameUd;
	private String timeoutRecorde;//超時輸入數據記錄   1:超時
	private String dateCreate;//創建時間（精確到分,年分隻保留后2位 例：1603310200）

	// Constructors

	/** default constructor */
	public WebYieldData() {
	}

	/** minimal constructor */
	public WebYieldData(WebYieldDataId id) {
		this.id = id;
	}

	/** full constructor */
	public WebYieldData(WebYieldDataId id, Double onModulus, Double personnum,
			Double standardOutput, Double actualYield, Double achievingRate,
			String username, String workorholiday, Double daycount,Double actualpairs,
			Double hostpairs,Double factpairs,Double samplepairs,Double outnum,Double backnum,
			Double workhours) {
		this.id = id;
		this.onModulus = onModulus;
		this.personnum = personnum;
		this.standardOutput = standardOutput;
		this.actualYield = actualYield;
		this.achievingRate = achievingRate;
		this.username = username;
		this.workorholiday = workorholiday;
		this.daycount = daycount;
		this.actualpairs=actualpairs;
		this.hostpairs=hostpairs;
		this.factpairs=factpairs;
		this.samplepairs=samplepairs;
		this.outnum=outnum;
		this.backnum=backnum;
		this.workhours=workhours;
	}
	
	public WebYieldData(Double onModulus,Double personnum,Double standardOutput, Double actualYield,Double daycount){
		this.onModulus = onModulus;
		this.personnum = personnum;
		this.standardOutput = standardOutput;
		this.actualYield = actualYield;
		this.daycount=daycount;
	}

	// Property accessors

	public WebYieldDataId getId() {
		return this.id;
	}

	public void setId(WebYieldDataId id) {
		this.id = id;
	}

	public Double getOnModulus() {
		return this.onModulus;
	}

	public void setOnModulus(Double onModulus) {
		this.onModulus = onModulus;
	}

	public Double getPersonnum() {
		return this.personnum;
	}

	public void setPersonnum(Double personnum) {
		this.personnum = personnum;
	}

	public Double getStandardOutput() {
		return this.standardOutput;
	}

	public void setStandardOutput(Double standardOutput) {
		this.standardOutput = standardOutput;
	}

	public Double getActualYield() {
		return this.actualYield;
	}

	public void setActualYield(Double actualYield) {
		this.actualYield = actualYield;
	}

	public Double getAchievingRate() {
		return this.achievingRate;
	}

	public void setAchievingRate(Double achievingRate) {
		this.achievingRate = achievingRate;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getWorkorholiday() {
		return this.workorholiday;
	}

	public void setWorkorholiday(String workorholiday) {
		this.workorholiday = workorholiday;
	}

	public Double getDaycount() {
		return this.daycount;
	}

	public void setDaycount(Double daycount) {
		this.daycount = daycount;
	}

	public Double getActualpairs() {
		return actualpairs;
	}

	public void setActualpairs(Double actualpairs) {
		this.actualpairs = actualpairs;
	}

	public Double getHostpairs() {
		return hostpairs;
	}

	public void setHostpairs(Double hostpairs) {
		this.hostpairs = hostpairs;
	}

	public Double getFactpairs() {
		return factpairs;
	}

	public void setFactpairs(Double factpairs) {
		this.factpairs = factpairs;
	}

	public Double getSamplepairs() {
		return samplepairs;
	}

	public void setSamplepairs(Double samplepairs) {
		this.samplepairs = samplepairs;
	}

	public Double getOutnum() {
		return outnum;
	}

	public void setOutnum(Double outnum) {
		this.outnum = outnum;
	}

	public Double getBacknum() {
		return backnum;
	}

	public void setBacknum(Double backnum) {
		this.backnum = backnum;
	}

	public Double getWorkhours() {
		return workhours;
	}

	public void setWorkhours(Double workhours) {
		this.workhours = workhours;
	}

	public String getUsernameUd() {
		return usernameUd;
	}

	public void setUsernameUd(String usernameUd) {
		this.usernameUd = usernameUd;
	}

	public String getTimeoutRecorde() {
		return timeoutRecorde;
	}

	public void setTimeoutRecorde(String timeoutRecorde) {
		this.timeoutRecorde = timeoutRecorde;
	}

	public String getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(String dateCreate) {
		this.dateCreate = dateCreate;
	}
	
	
	

}