package entity;

/**
 * WebMaterialregistrationitems entity. @author MyEclipse Persistence Tools
 */

public class WebMaterialregistrationitems implements java.io.Serializable {

	// Fields

	private Integer miid;
	private WebUser webUserByUpdateuser;
	private WebMaterialregistrationform webMaterialregistrationform;
	private WebUser webUserByCreateuser;
	private String materielname;
	private String characteristic;
	private String unit;
	private Double stocka;
	private Double inumsa;
	private Double onumsa;
	private Double inumsb;
	private Double onumsb;
	private Double inumsc;
	private Double onumsc;
	private Double inumsd;
	private Double onumsd;
	private Double inumse;
	private Double onumse;
	private Double stockb;
	private String remark;
	private String createDate;
	private String updateDate;

	// Constructors

	/** default constructor */
	public WebMaterialregistrationitems() {
	}

	/** minimal constructor */
	public WebMaterialregistrationitems(Integer miid) {
		this.miid = miid;
	}

	/** full constructor */
	public WebMaterialregistrationitems(Integer miid,
			WebUser webUserByUpdateuser,
			WebMaterialregistrationform webMaterialregistrationform,
			WebUser webUserByCreateuser, String materielname,
			String characteristic, String unit, Double stocka, Double inumsa,
			Double onumsa, Double inumsb, Double onumsb, Double inumsc,
			Double onumsc, Double inumsd, Double onumsd, Double stockb,
			String remark, String createDate, String updateDate) {
		this.miid = miid;
		this.webUserByUpdateuser = webUserByUpdateuser;
		this.webMaterialregistrationform = webMaterialregistrationform;
		this.webUserByCreateuser = webUserByCreateuser;
		this.materielname = materielname;
		this.characteristic = characteristic;
		this.unit = unit;
		this.stocka = stocka;
		this.inumsa = inumsa;
		this.onumsa = onumsa;
		this.inumsb = inumsb;
		this.onumsb = onumsb;
		this.inumsc = inumsc;
		this.onumsc = onumsc;
		this.inumsd = inumsd;
		this.onumsd = onumsd;
		this.stockb = stockb;
		this.remark = remark;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	// Property accessors

	public Integer getMiid() {
		return this.miid;
	}

	public void setMiid(Integer miid) {
		this.miid = miid;
	}

	public WebUser getWebUserByUpdateuser() {
		return this.webUserByUpdateuser;
	}

	public void setWebUserByUpdateuser(WebUser webUserByUpdateuser) {
		this.webUserByUpdateuser = webUserByUpdateuser;
	}

	public WebMaterialregistrationform getWebMaterialregistrationform() {
		return this.webMaterialregistrationform;
	}

	public void setWebMaterialregistrationform(
			WebMaterialregistrationform webMaterialregistrationform) {
		this.webMaterialregistrationform = webMaterialregistrationform;
	}

	public WebUser getWebUserByCreateuser() {
		return this.webUserByCreateuser;
	}

	public void setWebUserByCreateuser(WebUser webUserByCreateuser) {
		this.webUserByCreateuser = webUserByCreateuser;
	}

	public String getMaterielname() {
		return this.materielname;
	}

	public void setMaterielname(String materielname) {
		this.materielname = materielname;
	}

	public String getCharacteristic() {
		return this.characteristic;
	}

	public void setCharacteristic(String characteristic) {
		this.characteristic = characteristic;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getStocka() {
		return this.stocka;
	}

	public void setStocka(Double stocka) {
		this.stocka = stocka;
	}

	public Double getInumsa() {
		return this.inumsa;
	}

	public void setInumsa(Double inumsa) {
		this.inumsa = inumsa;
	}

	public Double getOnumsa() {
		return this.onumsa;
	}

	public void setOnumsa(Double onumsa) {
		this.onumsa = onumsa;
	}

	public Double getInumsb() {
		return this.inumsb;
	}

	public void setInumsb(Double inumsb) {
		this.inumsb = inumsb;
	}

	public Double getOnumsb() {
		return this.onumsb;
	}

	public void setOnumsb(Double onumsb) {
		this.onumsb = onumsb;
	}

	public Double getInumsc() {
		return this.inumsc;
	}

	public void setInumsc(Double inumsc) {
		this.inumsc = inumsc;
	}

	public Double getOnumsc() {
		return this.onumsc;
	}

	public void setOnumsc(Double onumsc) {
		this.onumsc = onumsc;
	}

	public Double getInumsd() {
		return this.inumsd;
	}

	public void setInumsd(Double inumsd) {
		this.inumsd = inumsd;
	}

	public Double getOnumsd() {
		return this.onumsd;
	}

	public void setOnumsd(Double onumsd) {
		this.onumsd = onumsd;
	}

	public Double getStockb() {
		return this.stockb;
	}

	public void setStockb(Double stockb) {
		this.stockb = stockb;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Double getInumse() {
		return inumse;
	}

	public void setInumse(Double inumse) {
		this.inumse = inumse;
	}

	public Double getOnumse() {
		return onumse;
	}

	public void setOnumse(Double onumse) {
		this.onumse = onumse;
	}
	
	

}