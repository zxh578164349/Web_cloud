package entity;

/**
 * WebFixed entity. @author MyEclipse Persistence Tools
 */

public class WebFixed implements java.io.Serializable {

	// Fields

	private String fixedassetsId;
	private String factNo;
	private String fixedId;
	private String assetscoding;
	private String assetname;
	private String majorId;
	private String majorName;
	private String subId;
	private String subName;
	private String model;
	private String brand;
	private String manufacturer;
	private String unitsId;
	private String unitsName;
	private String usesituationId;
	private String divisionId;
	private String divisionName;
	private String departmentId;
	private String departmentName;
	private String useraccount;
	private String contactor;
	private String addwaysId;
	private String addwaysName;
	private Double originalvalue;
	private String exfactordate;
	private String purchasedate;
	private String recordedate;
	private String methodId;
	private String methodName;
	private Double qty1;
	private Double qty2;
	private Double qty3;
	private Double qty4;
	private Double qty5;
	private Double qty6;
	private Double qty7;
	private String areaName;
	private String fromTo;
	private String memery1;
	private String memery2;
	private Double qty8;
	private Double qty9;
	private Double qty10;
	private Double qty11;
	private String memery3;
	private String memery4;
	private String memery5;
	private String updateTime;
	private String factcode;
	private String addTime;
	private String delMk;
	private String lostMk;
	private Double priceIn;
	private String currency;//����
	private String keeper;//�s���m/�O�ޤH
	private String checkdate;//�禬���
	private String username;
	private String usernameUd;

	// Constructors

	/** default constructor */
	public WebFixed() {
	}

	/** minimal constructor */
	public WebFixed(String fixedassetsId) {
		this.fixedassetsId = fixedassetsId;
	}

	/** full constructor */
	public WebFixed(String fixedassetsId, String factNo, String fixedId,
			String assetscoding, String assetname, String majorId,
			String majorName, String subId, String subName, String model,
			String brand, String manufacturer, String unitsId,
			String unitsName, String usesituationId, String divisionId,
			String divisionName, String departmentId, String departmentName,
			String useraccount, String contactor, String addwaysId,
			String addwaysName, Double originalvalue, String exfactordate,
			String purchasedate, String recordedate, String methodId,
			String methodName, Double qty1, Double qty2, Double qty3,
			Double qty4, Double qty5, Double qty6, Double qty7,
			String areaName, String fromTo, String memery1, String memery2,
			Double qty8, Double qty9, Double qty10, Double qty11,
			String memery3, String memery4, String memery5, String updateTime,
			String factcode, String addTime, String delMk, String lostMk,
			Double priceIn,String currency,String keeper,String checkdate) {
		this.fixedassetsId = fixedassetsId;
		this.factNo = factNo;
		this.fixedId = fixedId;
		this.assetscoding = assetscoding;
		this.assetname = assetname;
		this.majorId = majorId;
		this.majorName = majorName;
		this.subId = subId;
		this.subName = subName;
		this.model = model;
		this.brand = brand;
		this.manufacturer = manufacturer;
		this.unitsId = unitsId;
		this.unitsName = unitsName;
		this.usesituationId = usesituationId;
		this.divisionId = divisionId;
		this.divisionName = divisionName;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.useraccount = useraccount;
		this.contactor = contactor;
		this.addwaysId = addwaysId;
		this.addwaysName = addwaysName;
		this.originalvalue = originalvalue;
		this.exfactordate = exfactordate;
		this.purchasedate = purchasedate;
		this.recordedate = recordedate;
		this.methodId = methodId;
		this.methodName = methodName;
		this.qty1 = qty1;
		this.qty2 = qty2;
		this.qty3 = qty3;
		this.qty4 = qty4;
		this.qty5 = qty5;
		this.qty6 = qty6;
		this.qty7 = qty7;
		this.areaName = areaName;
		this.fromTo = fromTo;
		this.memery1 = memery1;
		this.memery2 = memery2;
		this.qty8 = qty8;
		this.qty9 = qty9;
		this.qty10 = qty10;
		this.qty11 = qty11;
		this.memery3 = memery3;
		this.memery4 = memery4;
		this.memery5 = memery5;
		this.updateTime = updateTime;
		this.factcode = factcode;
		this.addTime = addTime;
		this.delMk = delMk;
		this.lostMk = lostMk;
		this.priceIn = priceIn;
		this.currency=currency;
		this.keeper=keeper;
		this.checkdate=checkdate;
	}

	// Property accessors

	public String getFixedassetsId() {
		return this.fixedassetsId;
	}

	public void setFixedassetsId(String fixedassetsId) {
		this.fixedassetsId = fixedassetsId;
	}

	public String getFactNo() {
		return this.factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public String getFixedId() {
		return this.fixedId;
	}

	public void setFixedId(String fixedId) {
		this.fixedId = fixedId;
	}

	public String getAssetscoding() {
		return this.assetscoding;
	}

	public void setAssetscoding(String assetscoding) {
		this.assetscoding = assetscoding;
	}

	public String getAssetname() {
		return this.assetname;
	}

	public void setAssetname(String assetname) {
		this.assetname = assetname;
	}

	public String getMajorId() {
		return this.majorId;
	}

	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}

	public String getMajorName() {
		return this.majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getSubId() {
		return this.subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public String getSubName() {
		return this.subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getUnitsId() {
		return this.unitsId;
	}

	public void setUnitsId(String unitsId) {
		this.unitsId = unitsId;
	}

	public String getUnitsName() {
		return this.unitsName;
	}

	public void setUnitsName(String unitsName) {
		this.unitsName = unitsName;
	}

	public String getUsesituationId() {
		return this.usesituationId;
	}

	public void setUsesituationId(String usesituationId) {
		this.usesituationId = usesituationId;
	}

	public String getDivisionId() {
		return this.divisionId;
	}

	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}

	public String getDivisionName() {
		return this.divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getUseraccount() {
		return this.useraccount;
	}

	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}

	public String getContactor() {
		return this.contactor;
	}

	public void setContactor(String contactor) {
		this.contactor = contactor;
	}

	public String getAddwaysId() {
		return this.addwaysId;
	}

	public void setAddwaysId(String addwaysId) {
		this.addwaysId = addwaysId;
	}

	public String getAddwaysName() {
		return this.addwaysName;
	}

	public void setAddwaysName(String addwaysName) {
		this.addwaysName = addwaysName;
	}

	public Double getOriginalvalue() {
		return this.originalvalue;
	}

	public void setOriginalvalue(Double originalvalue) {
		this.originalvalue = originalvalue;
	}

	public String getExfactordate() {
		return this.exfactordate;
	}

	public void setExfactordate(String exfactordate) {
		this.exfactordate = exfactordate;
	}

	public String getPurchasedate() {
		return this.purchasedate;
	}

	public void setPurchasedate(String purchasedate) {
		this.purchasedate = purchasedate;
	}

	public String getRecordedate() {
		return this.recordedate;
	}

	public void setRecordedate(String recordedate) {
		this.recordedate = recordedate;
	}

	public String getMethodId() {
		return this.methodId;
	}

	public void setMethodId(String methodId) {
		this.methodId = methodId;
	}

	public String getMethodName() {
		return this.methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Double getQty1() {
		return this.qty1;
	}

	public void setQty1(Double qty1) {
		this.qty1 = qty1;
	}

	public Double getQty2() {
		return this.qty2;
	}

	public void setQty2(Double qty2) {
		this.qty2 = qty2;
	}

	public Double getQty3() {
		return this.qty3;
	}

	public void setQty3(Double qty3) {
		this.qty3 = qty3;
	}

	public Double getQty4() {
		return this.qty4;
	}

	public void setQty4(Double qty4) {
		this.qty4 = qty4;
	}

	public Double getQty5() {
		return this.qty5;
	}

	public void setQty5(Double qty5) {
		this.qty5 = qty5;
	}

	public Double getQty6() {
		return this.qty6;
	}

	public void setQty6(Double qty6) {
		this.qty6 = qty6;
	}

	public Double getQty7() {
		return this.qty7;
	}

	public void setQty7(Double qty7) {
		this.qty7 = qty7;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getFromTo() {
		return this.fromTo;
	}

	public void setFromTo(String fromTo) {
		this.fromTo = fromTo;
	}

	public String getMemery1() {
		return this.memery1;
	}

	public void setMemery1(String memery1) {
		this.memery1 = memery1;
	}

	public String getMemery2() {
		return this.memery2;
	}

	public void setMemery2(String memery2) {
		this.memery2 = memery2;
	}

	public Double getQty8() {
		return this.qty8;
	}

	public void setQty8(Double qty8) {
		this.qty8 = qty8;
	}

	public Double getQty9() {
		return this.qty9;
	}

	public void setQty9(Double qty9) {
		this.qty9 = qty9;
	}

	public Double getQty10() {
		return this.qty10;
	}

	public void setQty10(Double qty10) {
		this.qty10 = qty10;
	}

	public Double getQty11() {
		return this.qty11;
	}

	public void setQty11(Double qty11) {
		this.qty11 = qty11;
	}

	public String getMemery3() {
		return this.memery3;
	}

	public void setMemery3(String memery3) {
		this.memery3 = memery3;
	}

	public String getMemery4() {
		return this.memery4;
	}

	public void setMemery4(String memery4) {
		this.memery4 = memery4;
	}

	public String getMemery5() {
		return this.memery5;
	}

	public void setMemery5(String memery5) {
		this.memery5 = memery5;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getFactcode() {
		return this.factcode;
	}

	public void setFactcode(String factcode) {
		this.factcode = factcode;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getDelMk() {
		return this.delMk;
	}

	public void setDelMk(String delMk) {
		this.delMk = delMk;
	}

	public String getLostMk() {
		return this.lostMk;
	}

	public void setLostMk(String lostMk) {
		this.lostMk = lostMk;
	}

	public Double getPriceIn() {
		return this.priceIn;
	}

	public void setPriceIn(Double priceIn) {
		this.priceIn = priceIn;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getKeeper() {
		return keeper;
	}

	public void setKeeper(String keeper) {
		this.keeper = keeper;
	}



	public String getCheckdate() {
		return checkdate;
	}

	public void setCheckdate(String checkdate) {
		this.checkdate = checkdate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsernameUd() {
		return usernameUd;
	}

	public void setUsernameUd(String usernameUd) {
		this.usernameUd = usernameUd;
	}
	
	

}