package entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * WebFormula entity. @author MyEclipse Persistence Tools
 */

public class WebFormula implements java.io.Serializable{

	// Fields

	private String formulaIndex;//配方索引
	private VWebFact factNo;
	private String formulaNo;//配方編號
	private String formulaName;//配方名稱
	private WebErpBrankProcess factCode;//製程類別
	private Double magnification;//倍率
	private String semifinishedProductHardness;//帶皮半成品硬度
	private String productHardness;//成品硬度
	private String brandBody;//品牌形體
	private String color;//顏色
	private String issuedDate;//發行日期
	private String assignBrand;//品牌指定
	private String remark;//備註
	private String createName;
	private String createDate;
	private String modifyName;
	private String modifyDate;
	private List<WebFormulaItems> webFormulaItemses=new ArrayList<WebFormulaItems>();
	private JRBeanCollectionDataSource webFormulaItemses2;
	private WebTabpom pom;
	private KyVisabillm vbm;
	private String visaSort;
	private Integer userId;
	private String useremail;

	// Constructors

	/** default constructor */
	public WebFormula(){
	}

	/** minimal constructor */
	public WebFormula(String formulaIndex){
		this.formulaIndex=formulaIndex;
	}

	/** full constructor */
	public WebFormula(String formulaIndex,VWebFact factNo,String formulaNo,String formulaName,WebErpBrankProcess factCode,Double magnification,
			String semifinishedProductHardness,String productHardness,String brandBody,String color,String issuedDate,String assignBrand,String remark,
			String createName,String createDate,String modifyName,String modifyDate,List<WebFormulaItems> webFormulaItemses){
		this.formulaIndex=formulaIndex;
		this.factNo=factNo;
		this.formulaNo=formulaNo;
		this.formulaName=formulaName;
		this.factCode=factCode;
		this.magnification=magnification;
		this.semifinishedProductHardness=semifinishedProductHardness;
		this.productHardness=productHardness;
		this.brandBody=brandBody;
		this.color=color;
		this.issuedDate=issuedDate;
		this.assignBrand=assignBrand;
		this.remark=remark;
		this.createName=createName;
		this.createDate=createDate;
		this.modifyName=modifyName;
		this.modifyDate=modifyDate;
		this.webFormulaItemses=webFormulaItemses;
	}
	
	public WebFormula(VWebFact factNo,WebErpBrankProcess factCode,WebTabpom pom){
		this.factNo=factNo;
		this.factCode=factCode;
		this.pom=pom;
	}

	// Property accessors

	public String getFormulaIndex(){
		return this.formulaIndex;
	}

	public void setFormulaIndex(String formulaIndex){
		this.formulaIndex=formulaIndex;
	}

	public String getFormulaNo(){
		return this.formulaNo;
	}

	public void setFormulaNo(String formulaNo){
		this.formulaNo=formulaNo;
	}

	public String getFormulaName(){
		return this.formulaName;
	}

	public void setFormulaName(String formulaName){
		this.formulaName=formulaName;
	}

	public WebErpBrankProcess getFactCode(){
		return factCode;
	}

	public void setFactCode(WebErpBrankProcess factCode){
		this.factCode=factCode;
	}

	public Double getMagnification(){
		return this.magnification;
	}

	public void setMagnification(Double magnification){
		this.magnification=magnification;
	}

	public String getSemifinishedProductHardness(){
		return this.semifinishedProductHardness;
	}

	public void setSemifinishedProductHardness(String semifinishedProductHardness){
		this.semifinishedProductHardness=semifinishedProductHardness;
	}

	public String getProductHardness(){
		return this.productHardness;
	}

	public void setProductHardness(String productHardness){
		this.productHardness=productHardness;
	}

	public String getBrandBody(){
		return this.brandBody;
	}

	public void setBrandBody(String brandBody){
		this.brandBody=brandBody;
	}

	public String getColor(){
		return this.color;
	}

	public void setColor(String color){
		this.color=color;
	}

	public String getIssuedDate(){
		return this.issuedDate;
	}

	public void setIssuedDate(String issuedDate){
		this.issuedDate=issuedDate;
	}

	public String getAssignBrand(){
		return this.assignBrand;
	}

	public void setAssignBrand(String assignBrand){
		this.assignBrand=assignBrand;
	}

	public String getRemark(){
		return this.remark;
	}

	public void setRemark(String remark){
		this.remark=remark;
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

	public VWebFact getFactNo(){
		return factNo;
	}

	public void setFactNo(VWebFact factNo){
		this.factNo=factNo;
	}

	public List<WebFormulaItems> getWebFormulaItemses(){
		return webFormulaItemses;
	}

	public void setWebFormulaItemses(List<WebFormulaItems> webFormulaItemses){
		this.webFormulaItemses=webFormulaItemses;
	}

	public WebTabpom getPom(){
		return pom;
	}

	public void setPom(WebTabpom pom){
		this.pom=pom;
	}

	public KyVisabillm getVbm(){
		return vbm;
	}

	public void setVbm(KyVisabillm vbm){
		this.vbm=vbm;
	}

	public JRBeanCollectionDataSource getWebFormulaItemses2() {
		return webFormulaItemses2;
	}

	public void setWebFormulaItemses2(JRBeanCollectionDataSource webFormulaItemses2) {
		this.webFormulaItemses2 = webFormulaItemses2;
	}

	public String getVisaSort(){
		return visaSort;
	}

	public void setVisaSort(String visaSort){
		this.visaSort=visaSort;
	}

	public Integer getUserId(){
		return userId;
	}

	public void setUserId(Integer userId){
		this.userId=userId;
	}

	public String getUseremail(){
		return useremail;
	}

	public void setUseremail(String useremail){
		this.useremail=useremail;
	}
	
	
	
	


}