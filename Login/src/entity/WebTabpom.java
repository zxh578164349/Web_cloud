package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * WebTabpom entity. @author MyEclipse Persistence Tools
 */

public class WebTabpom implements java.io.Serializable {

	// Fields

	private String pomNo;//物性編號
	private String pomName;//物料名稱
	private String spematerial;//指定料
	private String component;//部件
	private Double hardness;//硬度
	private Double forces;//拉力↑
	private Double extends_;//延伸↑
	private Double tearingC;//C型撕裂↑
	private Double tearingK;//褲型撕裂↑
	private Double proportion;//比重(1.13±0.25)
	private Double wresistingAkron;//AKRON耐磨↓
	private Double wresistingDin;//DIN耐磨↓
	private Double ratioA;//止滑係數↑
	private Double ratioB;//耐油係數↓
	private Double ableBend;//抗彎曲
	private String ableYellow;//抗黃變(String)
	private String defyPress;//抗高壓(String)
	private Double defyEle;//抗靜電
	private String ageing;//老化水解(String)
	private String contract;//收縮(String)
	private Double elasticity;//彈性↑
	private String compression;//壓縮↓(String)
	private Double division;//分裂↑
	private String authentications;//認證
	private String instruction;//特性說明
	private String fileMk;//附檔
	private String userName;//創建人
	private String tabpomDate;//創建日期
	private WebBrank webBrank;//品牌
	private Double proportionA;//比重誤差（與比重連起來用）
	//private VWebFact webfact;
	private List<VWebFact>webfacts=new ArrayList<VWebFact>();
	private List<WebTabpomfile>webtabfiles=new ArrayList<WebTabpomfile>();//記錄附檔文件

	// Constructors

	/** default constructor */
	public WebTabpom() {
	}

	/** minimal constructor */
	public WebTabpom(String pomNo) {
		this.pomNo = pomNo;
	}

	/** full constructor */
	public WebTabpom(String pomNo, WebBrank webBrank, String pomName,
			String spematerial, String component, /*VWebFact webfact,*/
			Double hardness, Double forces, Double extends_, Double tearingC,
			Double tearingK, Double proportion, Double wresistingAkron,
			Double wresistingDin, Double ratioA, Double ratioB,
			Double ableBend, String ableYellow, String defyPress,
			Double defyEle, String ageing, String contract, Double elasticity,
			String compression, Double division, String authentications,Double proportionA,
			String instruction, String fileMk,List<VWebFact> webfacts) {
		this.pomNo = pomNo;
		this.webBrank = webBrank;
		this.pomName = pomName;
		this.spematerial = spematerial;
		this.component = component;
		//this.webfact = webfact;
		this.webfacts=webfacts;
		this.hardness = hardness;
		this.forces = forces;
		this.extends_ = extends_;
		this.tearingC = tearingC;
		this.tearingK = tearingK;
		this.proportion = proportion;
		this.wresistingAkron = wresistingAkron;
		this.wresistingDin = wresistingDin;
		this.ratioA = ratioA;
		this.ratioB = ratioB;
		this.ableBend = ableBend;
		this.ableYellow = ableYellow;
		this.defyPress = defyPress;
		this.defyEle = defyEle;
		this.ageing = ageing;
		this.contract = contract;
		this.elasticity = elasticity;
		this.compression = compression;
		this.division = division;
		this.authentications = authentications;
		this.instruction = instruction;
		this.fileMk = fileMk;
	}

	// Property accessors

	public String getPomNo() {
		return this.pomNo;
	}

	public void setPomNo(String pomNo) {
		this.pomNo = pomNo;
	}

	public WebBrank getWebBrank() {
		return this.webBrank;
	}

	public void setWebBrank(WebBrank webBrank) {
		this.webBrank = webBrank;
	}

	public String getPomName() {
		return this.pomName;
	}

	public void setPomName(String pomName) {
		this.pomName = pomName;
	}

	public String getSpematerial() {
		return this.spematerial;
	}

	public void setSpematerial(String spematerial) {
		this.spematerial = spematerial;
	}

	public String getComponent() {
		return this.component;
	}

	public void setComponent(String component) {
		this.component = component;
	}



	public Double getHardness() {
		return this.hardness;
	}

	public void setHardness(Double hardness) {
		this.hardness = hardness;
	}

	public Double getForces() {
		return this.forces;
	}

	public void setForces(Double forces) {
		this.forces = forces;
	}

	public Double getExtends_() {
		return this.extends_;
	}

	public void setExtends_(Double extends_) {
		this.extends_ = extends_;
	}

	public Double getTearingC() {
		return this.tearingC;
	}

	public void setTearingC(Double tearingC) {
		this.tearingC = tearingC;
	}

	public Double getTearingK() {
		return this.tearingK;
	}

	public void setTearingK(Double tearingK) {
		this.tearingK = tearingK;
	}

	public Double getProportion() {
		return this.proportion;
	}

	public void setProportion(Double proportion) {
		this.proportion = proportion;
	}

	public Double getWresistingAkron() {
		return this.wresistingAkron;
	}

	public void setWresistingAkron(Double wresistingAkron) {
		this.wresistingAkron = wresistingAkron;
	}

	public Double getWresistingDin() {
		return this.wresistingDin;
	}

	public void setWresistingDin(Double wresistingDin) {
		this.wresistingDin = wresistingDin;
	}

	public Double getRatioA() {
		return this.ratioA;
	}

	public void setRatioA(Double ratioA) {
		this.ratioA = ratioA;
	}

	public Double getRatioB() {
		return this.ratioB;
	}

	public void setRatioB(Double ratioB) {
		this.ratioB = ratioB;
	}

	public Double getAbleBend() {
		return this.ableBend;
	}

	public void setAbleBend(Double ableBend) {
		this.ableBend = ableBend;
	}

	

	

	public Double getDefyEle() {
		return this.defyEle;
	}

	public void setDefyEle(Double defyEle) {
		this.defyEle = defyEle;
	}

	

	

	public Double getElasticity() {
		return this.elasticity;
	}

	public void setElasticity(Double elasticity) {
		this.elasticity = elasticity;
	}

	

	public Double getDivision() {
		return this.division;
	}

	public void setDivision(Double division) {
		this.division = division;
	}

	public String getAuthentications() {
		return this.authentications;
	}

	public void setAuthentications(String authentications) {
		this.authentications = authentications;
	}

	public String getInstruction() {
		return this.instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public String getFileMk() {
		return this.fileMk;
	}

	public void setFileMk(String fileMk) {
		this.fileMk = fileMk;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTabpomDate() {
		return tabpomDate;
	}

	public void setTabpomDate(String tabpomDate) {
		this.tabpomDate = tabpomDate;
	}

	public List<VWebFact> getWebfacts() {
		return webfacts;
	}

	public void setWebfacts(List<VWebFact> webfacts) {
		this.webfacts = webfacts;
	}

	public List<WebTabpomfile> getWebtabfiles() {
		return webtabfiles;
	}

	public void setWebtabfiles(List<WebTabpomfile> webtabfiles) {
		this.webtabfiles = webtabfiles;
	}

	public String getAbleYellow() {
		return ableYellow;
	}

	public void setAbleYellow(String ableYellow) {
		this.ableYellow = ableYellow;
	}

	public String getDefyPress() {
		return defyPress;
	}

	public void setDefyPress(String defyPress) {
		this.defyPress = defyPress;
	}

	public String getAgeing() {
		return ageing;
	}

	public void setAgeing(String ageing) {
		this.ageing = ageing;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getCompression() {
		return compression;
	}

	public void setCompression(String compression) {
		this.compression = compression;
	}

	public Double getProportionA() {
		return proportionA;
	}

	public void setProportionA(Double proportionA) {
		this.proportionA = proportionA;
	}
	

	
	
	
	
	
	
	
	

}