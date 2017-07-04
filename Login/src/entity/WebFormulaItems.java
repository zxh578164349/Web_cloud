package entity;

/**
 * WebFormulaItems entity. @author MyEclipse Persistence Tools
 */

public class WebFormulaItems implements java.io.Serializable{

	// Fields

	private Integer itemId;
	private WebFormula webFormula;
	private Integer sectionNo;//階段號
	private VWebErpProductinFormation typeNo;
	private String namece1;
	private String namece2;
	private Double phrVal;
	private Double weightVal;
	private String remark;
	private String modifyName;
	private String modifyDate;
	private String createName;
	private String createDate;
	private WebErpProductinFormation  fk_weberp_pf;//關聯配方原料表  （WebErpProductinFormation）
	
	

	// Constructors

	/** default constructor */
	public WebFormulaItems(){
	}

	/** minimal constructor */
	public WebFormulaItems(Integer itemId){
		this.itemId=itemId;
	}

	/** full constructor */
	public WebFormulaItems(Integer itemId,WebFormula webFormula,Integer sectionNo,VWebErpProductinFormation typeNo,String namece1,String namece2,Double phrVal,Double weightVal,
			String remark,String modifyName,String modifyDate,String createName,String createDate){
		this.itemId=itemId;
		this.webFormula=webFormula;
		this.sectionNo=sectionNo;
		this.typeNo=typeNo;
		this.namece1=namece1;
		this.namece2=namece2;
		this.phrVal=phrVal;
		this.weightVal=weightVal;
		this.remark=remark;
		this.modifyName=modifyName;
		this.modifyDate=modifyDate;
		this.createName=createName;
		this.createDate=createDate;
	}

	// Property accessors

	public Integer getItemId(){
		return this.itemId;
	}

	public void setItemId(Integer itemId){
		this.itemId=itemId;
	}

	public WebFormula getWebFormula(){
		return this.webFormula;
	}

	public void setWebFormula(WebFormula webFormula){
		this.webFormula=webFormula;
	}

	public Integer getSectionNo(){
		return this.sectionNo;
	}

	public void setSectionNo(Integer sectionNo){
		this.sectionNo=sectionNo;
	}

	

	

	public VWebErpProductinFormation getTypeNo(){
		return typeNo;
	}

	public void setTypeNo(VWebErpProductinFormation typeNo){
		this.typeNo=typeNo;
	}

	public String getNamece1(){
		return this.namece1;
	}

	public void setNamece1(String namece1){
		this.namece1=namece1;
	}

	public String getNamece2(){
		return this.namece2;
	}

	public void setNamece2(String namece2){
		this.namece2=namece2;
	}

	public Double getPhrVal(){
		return this.phrVal;
	}

	public void setPhrVal(Double phrVal){
		this.phrVal=phrVal;
	}

	public Double getWeightVal(){
		return this.weightVal;
	}

	public void setWeightVal(Double weightVal){
		this.weightVal=weightVal;
	}

	public String getRemark(){
		return this.remark;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}

	public String getModifyName(){
		return this.modifyName;
	}

	public void setModifyName(String modifyName){
		this.modifyName=modifyName;
	}

	public String getModifyDate(){
		return this.modifyDate;
	}

	public void setModifyDate(String modifyDate){
		this.modifyDate=modifyDate;
	}

	public String getCreateName(){
		return this.createName;
	}

	public void setCreateName(String createName){
		this.createName=createName;
	}

	public String getCreateDate(){
		return this.createDate;
	}

	public void setCreateDate(String createDate){
		this.createDate=createDate;
	}

	public WebErpProductinFormation getFk_weberp_pf(){
		return fk_weberp_pf;
	}

	public void setFk_weberp_pf(WebErpProductinFormation fk_weberp_pf){
		this.fk_weberp_pf=fk_weberp_pf;
	}

	
	
	

}