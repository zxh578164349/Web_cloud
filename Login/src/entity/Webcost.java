package entity;

/**
 * Webcost entity. @author MyEclipse Persistence Tools
 */

public class Webcost implements java.io.Serializable {

	// Fields

	private WebcostId id;
	private Double actlost;
	private Double avgbuttomweight;
	private Double avgbuttomweight2;
	private Double avgprice;
	private Double totalstore;
	private Double totalstoremoney;
	private Double gluestore;
	private Double gluestoremoney;
	private Double colorused;
	private Double drugsused;
	private Double leavemoney;
	private Double whitenum;
	private Double blacknum;
	private Double gluenum;
	private Double greynum;
	private Double othernum;
	private Double otherweight;
	private String username;
	private Double noGlueWeight;
	private Double productedNum;
	private String usernameUd;

	// Constructors

	/** default constructor */
	public Webcost() {
	}

	/** minimal constructor */
	public Webcost(WebcostId id) {
		this.id = id;
	}

	/** full constructor */
	public Webcost(WebcostId id, Double actlost, Double avgbuttomweight,
			Double avgbuttomweight2, Double avgprice, Double totalstore,
			Double totalstoremoney, Double gluestore, Double gluestoremoney,
			Double colorused, Double drugsused, Double leavemoney,
			Double whitenum, Double blacknum, Double gluenum, Double greynum,
			Double othernum,Double otherweight, String username,
			Double noGlueWeight,Double productedNum) {
		this.id = id;
		this.actlost = actlost;
		this.avgbuttomweight = avgbuttomweight;
		this.avgbuttomweight2 = avgbuttomweight2;
		this.avgprice = avgprice;
		this.totalstore = totalstore;
		this.totalstoremoney = totalstoremoney;
		this.gluestore = gluestore;
		this.gluestoremoney = gluestoremoney;
		this.colorused = colorused;
		this.drugsused = drugsused;
		this.leavemoney = leavemoney;
		this.whitenum = whitenum;
		this.blacknum = blacknum;
		this.gluenum = gluenum;
		this.greynum = greynum;
		this.othernum = othernum;
		this.otherweight=otherweight;
		this.username = username;
		this.noGlueWeight=noGlueWeight;
		this.productedNum=productedNum;
	}

	// Property accessors

	public WebcostId getId() {
		return this.id;
	}

	public void setId(WebcostId id) {
		this.id = id;
	}

	public Double getActlost() {
		return this.actlost;
	}

	public void setActlost(Double actlost) {
		this.actlost = actlost;
	}

	public Double getAvgbuttomweight() {
		return this.avgbuttomweight;
	}

	public void setAvgbuttomweight(Double avgbuttomweight) {
		this.avgbuttomweight = avgbuttomweight;
	}

	public Double getAvgbuttomweight2() {
		return this.avgbuttomweight2;
	}

	public void setAvgbuttomweight2(Double avgbuttomweight2) {
		this.avgbuttomweight2 = avgbuttomweight2;
	}

	public Double getAvgprice() {
		return this.avgprice;
	}

	public void setAvgprice(Double avgprice) {
		this.avgprice = avgprice;
	}

	public Double getTotalstore() {
		return this.totalstore;
	}

	public void setTotalstore(Double totalstore) {
		this.totalstore = totalstore;
	}

	public Double getTotalstoremoney() {
		return this.totalstoremoney;
	}

	public void setTotalstoremoney(Double totalstoremoney) {
		this.totalstoremoney = totalstoremoney;
	}

	public Double getGluestore() {
		return this.gluestore;
	}

	public void setGluestore(Double gluestore) {
		this.gluestore = gluestore;
	}

	public Double getGluestoremoney() {
		return this.gluestoremoney;
	}

	public void setGluestoremoney(Double gluestoremoney) {
		this.gluestoremoney = gluestoremoney;
	}

	public Double getColorused() {
		return this.colorused;
	}

	public void setColorused(Double colorused) {
		this.colorused = colorused;
	}

	public Double getDrugsused() {
		return this.drugsused;
	}

	public void setDrugsused(Double drugsused) {
		this.drugsused = drugsused;
	}

	public Double getLeavemoney() {
		return this.leavemoney;
	}

	public void setLeavemoney(Double leavemoney) {
		this.leavemoney = leavemoney;
	}

	public Double getWhitenum() {
		return this.whitenum;
	}

	public void setWhitenum(Double whitenum) {
		this.whitenum = whitenum;
	}

	public Double getBlacknum() {
		return this.blacknum;
	}

	public void setBlacknum(Double blacknum) {
		this.blacknum = blacknum;
	}

	public Double getGluenum() {
		return this.gluenum;
	}

	public void setGluenum(Double gluenum) {
		this.gluenum = gluenum;
	}

	public Double getGreynum() {
		return this.greynum;
	}

	public void setGreynum(Double greynum) {
		this.greynum = greynum;
	}

	public Double getOthernum() {
		return this.othernum;
	}

	public void setOthernum(Double othernum) {
		this.othernum = othernum;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Double getOtherweight() {
		return otherweight;
	}

	public void setOtherweight(Double otherweight) {
		this.otherweight = otherweight;
	}

	public Double getNoGlueWeight() {
		return noGlueWeight;
	}

	public void setNoGlueWeight(Double noGlueWeight) {
		this.noGlueWeight = noGlueWeight;
	}

	public Double getProductedNum() {
		return productedNum;
	}

	public void setProductedNum(Double productedNum) {
		this.productedNum = productedNum;
	}

	public String getUsernameUd() {
		return usernameUd;
	}

	public void setUsernameUd(String usernameUd) {
		this.usernameUd = usernameUd;
	}
	

	
	

}