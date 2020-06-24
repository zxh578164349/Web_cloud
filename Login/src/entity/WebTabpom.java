package entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * WebTabpom entity. @author MyEclipse Persistence Tools
 */

public class WebTabpom implements java.io.Serializable{

	// Fields

	private String pomNo;//物性編號
	private WebErpBrankProcess webBrank;//品牌
	private WebFormula formulaId;//配方索引（配方系統關聯）
	private Double hardness;//硬度
	private Double forces;//拉力
	private Double extend;//延伸
	private Double tearingC;//C型撕裂
	private Double tearingK;//褲型撕裂
	private Double proportion;//比重
	private Double wresistingAkron;//AKRON耐磨
	private Double wresistingDin;//DIN耐磨
	private Double ratioA;//止滑係數
	private Double ratioB;//耐油係數
	private Double modulus300;//300% Modulus
	private Double ableBend;//抗彎曲
	private Double ableYellow;//抗黃變
	private Double defyPress;//抗高壓
	private Double defyEle;//抗靜電
	private Double ageing;//老化水解
	private Double contract;//收縮
	private Double elasticity;//彈性
	private Double compression;//壓縮
	private Double division;//分裂
	private Double spitCream;//吐霜
	private String authentications;//認證
	private String instruction;//特性說明
	private String fileMk;//附檔
	private String username;//創建人
	private String tabpomDate;//創建日期
	private String modifyName;//修改人
	private String modifyDate;//修改日期
	private Double hardness2;//±值
	private String hardnessDescription;//測試方式說明
	private String forcesDescription;
	private String extendsDescription;
	private String tearingCDescription;
	private String tearingKDescription;
	private Double proportion2;//±值
	private String proportionDescription;
	private String wresistingAkronDes;
	private String wresistingDinDes;
	private String ratioADes;
	private String ratioBDes;
	private String ableBendDes;
	private String ableYellowDes;
	private String defyPressDes;
	private String defyEleDes;
	private String ageingDes;
	private String contractDes;
	private String elasticityDes;
	private String compressionDes;
	private String divisionDes;
	private String modulus300Des;	
	private String spitCreamDes;
	private List<WebTabpomfile> webTabpomfiles=new ArrayList<WebTabpomfile>();

	// Constructors

	/** default constructor */
	public WebTabpom(){
	}

	/** minimal constructor */
	public WebTabpom(String pomNo){
		this.pomNo=pomNo;
	}
	public WebTabpom(WebErpBrankProcess webBrank){
		this.webBrank=webBrank;
	}

	/** full constructor */
	public WebTabpom(String pomNo,WebErpBrankProcess webBrank,WebFormula formulaId,Double hardness,Double forces,Double extend,Double tearingC,Double tearingK,
			Double proportion,Double wresistingAkron,Double wresistingDin,Double ratioA,Double ratioB,Double ableBend,Double ableYellow,Double defyPress,
			Double defyEle,Double ageing,Double contract,Double elasticity,Double compression,Double division,String authentications,String instruction,
			String fileMk,String username,String tabpomDate,Double hardness2,String hardnessDescription,String forcesDescription,String extendsDescription,
			String tearingCDescription,String tearingKDescription,Double proportion2,String proportionDescription,String wresistingAkronDes,
			String wresistingDinDes,String ratioADes,String ratioBDes,String ableBendDes,String ableYellowDes,String defyPressDes,String defyEleDes,
			String ageingDes,String contractDes,String elasticityDes,String compressionDes,String divisionDes,Double modulus300,String modulus300Des,
			Double spitCream,String spitCreamDes,List<WebTabpomfile> webTabpomfiles){
		this.pomNo=pomNo;
		this.webBrank=webBrank;
		this.formulaId=formulaId;
		this.hardness=hardness;
		this.forces=forces;
		this.extend=extend;
		this.tearingC=tearingC;
		this.tearingK=tearingK;
		this.proportion=proportion;
		this.wresistingAkron=wresistingAkron;
		this.wresistingDin=wresistingDin;
		this.ratioA=ratioA;
		this.ratioB=ratioB;
		this.ableBend=ableBend;
		this.ableYellow=ableYellow;
		this.defyPress=defyPress;
		this.defyEle=defyEle;
		this.ageing=ageing;
		this.contract=contract;
		this.elasticity=elasticity;
		this.compression=compression;
		this.division=division;
		this.authentications=authentications;
		this.instruction=instruction;
		this.fileMk=fileMk;
		this.username=username;
		this.tabpomDate=tabpomDate;
		this.hardness2=hardness2;
		this.hardnessDescription=hardnessDescription;
		this.forcesDescription=forcesDescription;
		this.extendsDescription=extendsDescription;
		this.tearingCDescription=tearingCDescription;
		this.tearingKDescription=tearingKDescription;
		this.proportion2=proportion2;
		this.proportionDescription=proportionDescription;
		this.wresistingAkronDes=wresistingAkronDes;
		this.wresistingDinDes=wresistingDinDes;
		this.ratioADes=ratioADes;
		this.ratioBDes=ratioBDes;
		this.ableBendDes=ableBendDes;
		this.ableYellowDes=ableYellowDes;
		this.defyPressDes=defyPressDes;
		this.defyEleDes=defyEleDes;
		this.ageingDes=ageingDes;
		this.contractDes=contractDes;
		this.elasticityDes=elasticityDes;
		this.compressionDes=compressionDes;
		this.divisionDes=divisionDes;
		this.modulus300=modulus300;
		this.modulus300Des=modulus300Des;
		this.spitCream=spitCream;
		this.spitCreamDes=spitCreamDes;
		this.webTabpomfiles=webTabpomfiles;
	}

	// Property accessors

	public String getPomNo(){
		return this.pomNo;
	}

	public void setPomNo(String pomNo){
		this.pomNo=pomNo;
	}
	
	public WebErpBrankProcess getWebBrank(){
		return webBrank;
	}

	public void setWebBrank(WebErpBrankProcess webBrank){
		this.webBrank=webBrank;
	}

	

	public WebFormula getFormulaId(){
		return formulaId;
	}

	public void setFormulaId(WebFormula formulaId){
		this.formulaId=formulaId;
	}

	public Double getHardness(){
		return this.hardness;
	}

	public void setHardness(Double hardness){
		this.hardness=hardness;
	}

	public Double getForces(){
		return this.forces;
	}

	public void setForces(Double forces){
		this.forces=forces;
	}

	public Double getExtend(){
		return extend;
	}

	public void setExtend(Double extend){
		this.extend=extend;
	}

	public Double getTearingC(){
		return this.tearingC;
	}

	public void setTearingC(Double tearingC){
		this.tearingC=tearingC;
	}

	public Double getTearingK(){
		return this.tearingK;
	}

	public void setTearingK(Double tearingK){
		this.tearingK=tearingK;
	}

	public Double getProportion(){
		return this.proportion;
	}

	public void setProportion(Double proportion){
		this.proportion=proportion;
	}

	public Double getWresistingAkron(){
		return this.wresistingAkron;
	}

	public void setWresistingAkron(Double wresistingAkron){
		this.wresistingAkron=wresistingAkron;
	}

	public Double getWresistingDin(){
		return this.wresistingDin;
	}

	public void setWresistingDin(Double wresistingDin){
		this.wresistingDin=wresistingDin;
	}

	public Double getRatioA(){
		return this.ratioA;
	}

	public void setRatioA(Double ratioA){
		this.ratioA=ratioA;
	}

	public Double getRatioB(){
		return this.ratioB;
	}

	public void setRatioB(Double ratioB){
		this.ratioB=ratioB;
	}

	public Double getAbleBend(){
		return this.ableBend;
	}

	public void setAbleBend(Double ableBend){
		this.ableBend=ableBend;
	}

	public Double getAbleYellow(){
		return this.ableYellow;
	}

	public void setAbleYellow(Double ableYellow){
		this.ableYellow=ableYellow;
	}

	public Double getDefyPress(){
		return this.defyPress;
	}

	public void setDefyPress(Double defyPress){
		this.defyPress=defyPress;
	}

	public Double getDefyEle(){
		return this.defyEle;
	}

	public void setDefyEle(Double defyEle){
		this.defyEle=defyEle;
	}

	public Double getAgeing(){
		return this.ageing;
	}

	public void setAgeing(Double ageing){
		this.ageing=ageing;
	}

	public Double getContract(){
		return this.contract;
	}

	public void setContract(Double contract){
		this.contract=contract;
	}

	public Double getElasticity(){
		return this.elasticity;
	}

	public void setElasticity(Double elasticity){
		this.elasticity=elasticity;
	}

	public Double getCompression(){
		return this.compression;
	}

	public void setCompression(Double compression){
		this.compression=compression;
	}

	public Double getDivision(){
		return this.division;
	}

	public void setDivision(Double division){
		this.division=division;
	}

	public String getAuthentications(){
		return this.authentications;
	}

	public void setAuthentications(String authentications){
		this.authentications=authentications;
	}

	public String getInstruction(){
		return this.instruction;
	}

	public void setInstruction(String instruction){
		this.instruction=instruction;
	}

	public String getFileMk(){
		return this.fileMk;
	}

	public void setFileMk(String fileMk){
		this.fileMk=fileMk;
	}

	public String getUsername(){
		return this.username;
	}

	public void setUsername(String username){
		this.username=username;
	}

	public String getTabpomDate(){
		return this.tabpomDate;
	}

	public void setTabpomDate(String tabpomDate){
		this.tabpomDate=tabpomDate;
	}

	public Double getHardness2(){
		return this.hardness2;
	}

	public void setHardness2(Double hardness2){
		this.hardness2=hardness2;
	}

	public String getHardnessDescription(){
		return this.hardnessDescription;
	}

	public void setHardnessDescription(String hardnessDescription){
		this.hardnessDescription=hardnessDescription;
	}

	public String getForcesDescription(){
		return this.forcesDescription;
	}

	public void setForcesDescription(String forcesDescription){
		this.forcesDescription=forcesDescription;
	}

	public String getExtendsDescription(){
		return this.extendsDescription;
	}

	public void setExtendsDescription(String extendsDescription){
		this.extendsDescription=extendsDescription;
	}

	public String getTearingCDescription(){
		return this.tearingCDescription;
	}

	public void setTearingCDescription(String tearingCDescription){
		this.tearingCDescription=tearingCDescription;
	}

	public String getTearingKDescription(){
		return this.tearingKDescription;
	}

	public void setTearingKDescription(String tearingKDescription){
		this.tearingKDescription=tearingKDescription;
	}

	public Double getProportion2(){
		return this.proportion2;
	}

	public void setProportion2(Double proportion2){
		this.proportion2=proportion2;
	}

	public String getProportionDescription(){
		return this.proportionDescription;
	}

	public void setProportionDescription(String proportionDescription){
		this.proportionDescription=proportionDescription;
	}

	public String getWresistingAkronDes(){
		return this.wresistingAkronDes;
	}

	public void setWresistingAkronDes(String wresistingAkronDes){
		this.wresistingAkronDes=wresistingAkronDes;
	}

	public String getWresistingDinDes(){
		return this.wresistingDinDes;
	}

	public void setWresistingDinDes(String wresistingDinDes){
		this.wresistingDinDes=wresistingDinDes;
	}

	public String getRatioADes(){
		return this.ratioADes;
	}

	public void setRatioADes(String ratioADes){
		this.ratioADes=ratioADes;
	}

	public String getRatioBDes(){
		return this.ratioBDes;
	}

	public void setRatioBDes(String ratioBDes){
		this.ratioBDes=ratioBDes;
	}

	public String getAbleBendDes(){
		return this.ableBendDes;
	}

	public void setAbleBendDes(String ableBendDes){
		this.ableBendDes=ableBendDes;
	}

	public String getAbleYellowDes(){
		return this.ableYellowDes;
	}

	public void setAbleYellowDes(String ableYellowDes){
		this.ableYellowDes=ableYellowDes;
	}

	public String getDefyPressDes(){
		return this.defyPressDes;
	}

	public void setDefyPressDes(String defyPressDes){
		this.defyPressDes=defyPressDes;
	}

	public String getDefyEleDes(){
		return this.defyEleDes;
	}

	public void setDefyEleDes(String defyEleDes){
		this.defyEleDes=defyEleDes;
	}

	public String getAgeingDes(){
		return this.ageingDes;
	}

	public void setAgeingDes(String ageingDes){
		this.ageingDes=ageingDes;
	}

	public String getContractDes(){
		return this.contractDes;
	}

	public void setContractDes(String contractDes){
		this.contractDes=contractDes;
	}

	public String getElasticityDes(){
		return this.elasticityDes;
	}

	public void setElasticityDes(String elasticityDes){
		this.elasticityDes=elasticityDes;
	}

	public String getCompressionDes(){
		return this.compressionDes;
	}

	public void setCompressionDes(String compressionDes){
		this.compressionDes=compressionDes;
	}

	public String getDivisionDes(){
		return this.divisionDes;
	}

	public void setDivisionDes(String divisionDes){
		this.divisionDes=divisionDes;
	}

	public Double getModulus300(){
		return this.modulus300;
	}

	public void setModulus300(Double modulus300){
		this.modulus300=modulus300;
	}

	public String getModulus300Des(){
		return this.modulus300Des;
	}

	public void setModulus300Des(String modulus300Des){
		this.modulus300Des=modulus300Des;
	}

	public Double getSpitCream(){
		return this.spitCream;
	}

	public void setSpitCream(Double spitCream){
		this.spitCream=spitCream;
	}

	public String getSpitCreamDes(){
		return spitCreamDes;
	}

	public void setSpitCreamDes(String spitCreamDes){
		this.spitCreamDes=spitCreamDes;
	}

	public List<WebTabpomfile> getWebTabpomfiles(){
		return webTabpomfiles;
	}

	public void setWebTabpomfiles(List<WebTabpomfile> webTabpomfiles){
		this.webTabpomfiles=webTabpomfiles;
	}

	public String getModifyName(){
		return modifyName;
	}

	public void setModifyName(String modifyName){
		this.modifyName=modifyName;
	}

	public String getModifyDate(){
		return modifyDate;
	}

	public void setModifyDate(String modifyDate){
		this.modifyDate=modifyDate;
	}

	

}