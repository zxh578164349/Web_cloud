package entity;

import java.util.List;

/**
 * WebType entity. @author MyEclipse Persistence Tools
 */

public class WebType implements java.io.Serializable {

	// Fields
	//private static final long serialVersionUID =-8880440583205006515L;
	private WebTypeId id;
	private String typeName;
	private String webtypeMk;
	private String delMk;//刪除標記        0或空 未刪除     1刪除
	private String typeMk; //區分 出差類(TR)    配方類(PF)     其它類(0)
	private List<KyzExpectmatm>list_kyzexp;
	private List<KyzContactletter>list_letter;
	private List<WebBussinessletter>list_buss;
	private List<KyzVisaflow>list_visaflow;
	private List<Webremittancelist>list_webrel;
	private List<WebFormula>list_formula;
	private String trMk;//是否分部門     Y:是   N:否

	// Constructors

	public String getDelMk() {
		return delMk;
	}

	public void setDelMk(String delMk) {
		this.delMk = delMk;
	}

	/** default constructor */
	public WebType() {
	}

	/** minimal constructor */
	public WebType(WebTypeId id) {
		this.id = id;
	}

	/** full constructor */
	public WebType(WebTypeId id, String typeName,String webtypeMk) {
		this.id = id;
		this.typeName = typeName;
		this.webtypeMk=webtypeMk;
	}

	// Property accessors

	public WebTypeId getId() {
		return this.id;
	}

	public void setId(WebTypeId id) {
		this.id = id;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getWebtypeMk() {
		return webtypeMk;
	}

	public void setWebtypeMk(String webtypeMk) {
		this.webtypeMk = webtypeMk;
	}

	public List<KyzExpectmatm> getList_kyzexp() {
		return list_kyzexp;
	}

	public void setList_kyzexp(List<KyzExpectmatm> list_kyzexp) {		
		this.list_kyzexp = list_kyzexp;
	}

	public List<KyzContactletter> getList_letter() {
		return list_letter;
	}

	public void setList_letter(List<KyzContactletter> list_letter) {
		this.list_letter = list_letter;
	}

	public List<WebBussinessletter> getList_buss() {
		return list_buss;
	}

	public void setList_buss(List<WebBussinessletter> list_buss) {
		this.list_buss = list_buss;
	}

	public List<KyzVisaflow> getList_visaflow() {
		return list_visaflow;
	}

	public void setList_visaflow(List<KyzVisaflow> list_visaflow) {
		this.list_visaflow = list_visaflow;
	}

	public List<Webremittancelist> getList_webrel() {
		return list_webrel;
	}

	public void setList_webrel(List<Webremittancelist> list_webrel) {
		this.list_webrel = list_webrel;
	}

	public String getTypeMk(){
		return typeMk;
	}

	public void setTypeMk(String typeMk){
		this.typeMk=typeMk;
	}

	public List<WebFormula> getList_formula(){
		return list_formula;
	}

	public void setList_formula(List<WebFormula> list_formula){
		this.list_formula=list_formula;
	}

	public String getTrMk(){
		return trMk;
	}

	public void setTrMk(String trMk){
		this.trMk=trMk;
	}
	
	

	
	
	

	
	
	

	
	
	
	

}