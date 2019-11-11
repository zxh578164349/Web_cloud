package entity;

/**
 * WebObjsB entity. @author MyEclipse Persistence Tools
 */

public class WebObjsB implements java.io.Serializable {

	// Fields

	private WebObjsBId id;
	private Double onModulus;//上模數
	private Double personnum;//人數(拉模手)
	private Double standardOutput;//標準產量(模/日)
	private Double actualYield;//實際產量(模/日)
	private Double zpObja;//正批
	private Double hostpairs;//客補
	private Double factpairs;//廠補
	private Double samplepairs;//樣品
	private Double outnum;//出貨數
	private Double backnum;//退貨數
	private Double workhours;//上模總工時
	private Double daycount;//天數
	private Double objA1;//成型不良雙數
	private Double objA2;//慢單狀況(張)
	private Double objA3;//慢單狀況(雙)
	private Double objA4;//訂單欠數
	private Long objA5;//直工人數
	private Long objA6;//間工人數
	private Long objA7;//招工數
	private Long objA8;//離職數
	private Long objA9;//請假數
	private String objA10;//品質問題與客訴
	private String objA11;//扣款訊息
	private String objA12;//機台狀況/異常
	private String objA13;//客人來訪訊息
	private String objA14;//其他提報事項
	private String workorholiday;//假日/工作日
	private String username;
	private String usernameUd;
	private String datecreate;
	private String datecreateUd;

	// Constructors

	/** default constructor */
	public WebObjsB() {
	}

	/** minimal constructor */
	public WebObjsB(WebObjsBId id) {
		this.id = id;
	}

	/** full constructor */
	public WebObjsB(WebObjsBId id, Double onModulus, Double personnum,
			Double standardOutput, Double actualYield, Double zpObja,
			Double hostpairs, Double factpairs, Double samplepairs,
			Double outnum, Double backnum, Double workhours, Double daycount,
			Double objA1, Double objA2, Double objA3, Double objA4, Long objA5,
			Long objA6, Long objA7, Long objA8, Long objA9, String objA10,
			String objA11, String objA12, String objA13, String objA14,
			String workorholiday, String username, String usernameUd,
			String datecreate, String datecreateUd) {
		this.id = id;
		this.onModulus = onModulus;
		this.personnum = personnum;
		this.standardOutput = standardOutput;
		this.actualYield = actualYield;
		this.zpObja = zpObja;
		this.hostpairs = hostpairs;
		this.factpairs = factpairs;
		this.samplepairs = samplepairs;
		this.outnum = outnum;
		this.backnum = backnum;
		this.workhours = workhours;
		this.daycount = daycount;
		this.objA1 = objA1;
		this.objA2 = objA2;
		this.objA3 = objA3;
		this.objA4 = objA4;
		this.objA5 = objA5;
		this.objA6 = objA6;
		this.objA7 = objA7;
		this.objA8 = objA8;
		this.objA9 = objA9;
		this.objA10 = objA10;
		this.objA11 = objA11;
		this.objA12 = objA12;
		this.objA13 = objA13;
		this.objA14 = objA14;
		this.workorholiday = workorholiday;
		this.username = username;
		this.usernameUd = usernameUd;
		this.datecreate = datecreate;
		this.datecreateUd = datecreateUd;
	}

	// Property accessors

	public WebObjsB(Double onModulus,Double personnum,Double standardOutput, Double actualYield,Double daycount){
		this.onModulus = onModulus;
		this.personnum = personnum;
		this.standardOutput = standardOutput;
		this.actualYield = actualYield;
		this.daycount=daycount;
	}
	
	public WebObjsBId getId() {
		return this.id;
	}

	public void setId(WebObjsBId id) {
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

	public Double getZpObja() {
		return this.zpObja;
	}

	public void setZpObja(Double zpObja) {
		this.zpObja = zpObja;
	}

	public Double getHostpairs() {
		return this.hostpairs;
	}

	public void setHostpairs(Double hostpairs) {
		this.hostpairs = hostpairs;
	}

	public Double getFactpairs() {
		return this.factpairs;
	}

	public void setFactpairs(Double factpairs) {
		this.factpairs = factpairs;
	}

	public Double getSamplepairs() {
		return this.samplepairs;
	}

	public void setSamplepairs(Double samplepairs) {
		this.samplepairs = samplepairs;
	}

	public Double getOutnum() {
		return this.outnum;
	}

	public void setOutnum(Double outnum) {
		this.outnum = outnum;
	}

	public Double getBacknum() {
		return this.backnum;
	}

	public void setBacknum(Double backnum) {
		this.backnum = backnum;
	}

	public Double getWorkhours() {
		return this.workhours;
	}

	public void setWorkhours(Double workhours) {
		this.workhours = workhours;
	}

	public Double getDaycount() {
		return this.daycount;
	}

	public void setDaycount(Double daycount) {
		this.daycount = daycount;
	}

	public Double getObjA1() {
		return this.objA1;
	}

	public void setObjA1(Double objA1) {
		this.objA1 = objA1;
	}

	public Double getObjA2() {
		return this.objA2;
	}

	public void setObjA2(Double objA2) {
		this.objA2 = objA2;
	}

	public Double getObjA3() {
		return this.objA3;
	}

	public void setObjA3(Double objA3) {
		this.objA3 = objA3;
	}

	public Double getObjA4() {
		return this.objA4;
	}

	public void setObjA4(Double objA4) {
		this.objA4 = objA4;
	}

	public Long getObjA5() {
		return this.objA5;
	}

	public void setObjA5(Long objA5) {
		this.objA5 = objA5;
	}

	public Long getObjA6() {
		return this.objA6;
	}

	public void setObjA6(Long objA6) {
		this.objA6 = objA6;
	}

	public Long getObjA7() {
		return this.objA7;
	}

	public void setObjA7(Long objA7) {
		this.objA7 = objA7;
	}

	public Long getObjA8() {
		return this.objA8;
	}

	public void setObjA8(Long objA8) {
		this.objA8 = objA8;
	}

	public Long getObjA9() {
		return this.objA9;
	}

	public void setObjA9(Long objA9) {
		this.objA9 = objA9;
	}

	public String getObjA10() {
		return this.objA10;
	}

	public void setObjA10(String objA10) {
		this.objA10 = objA10;
	}

	public String getObjA11() {
		return this.objA11;
	}

	public void setObjA11(String objA11) {
		this.objA11 = objA11;
	}

	public String getObjA12() {
		return this.objA12;
	}

	public void setObjA12(String objA12) {
		this.objA12 = objA12;
	}

	public String getObjA13() {
		return this.objA13;
	}

	public void setObjA13(String objA13) {
		this.objA13 = objA13;
	}

	public String getObjA14() {
		return this.objA14;
	}

	public void setObjA14(String objA14) {
		this.objA14 = objA14;
	}

	public String getWorkorholiday() {
		return this.workorholiday;
	}

	public void setWorkorholiday(String workorholiday) {
		this.workorholiday = workorholiday;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsernameUd() {
		return this.usernameUd;
	}

	public void setUsernameUd(String usernameUd) {
		this.usernameUd = usernameUd;
	}

	public String getDatecreate() {
		return this.datecreate;
	}

	public void setDatecreate(String datecreate) {
		this.datecreate = datecreate;
	}

	public String getDatecreateUd() {
		return this.datecreateUd;
	}

	public void setDatecreateUd(String datecreateUd) {
		this.datecreateUd = datecreateUd;
	}

}