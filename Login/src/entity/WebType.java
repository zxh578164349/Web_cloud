package entity;

import java.util.List;

/**
 * WebType entity. @author MyEclipse Persistence Tools
 */

public class WebType implements java.io.Serializable {

	// Fields

	private WebTypeId id;
	private String typeName;
	private String webtypeMk;
	private List<KyzExpectmatm>list_kyzexp;
	private List<KyzContactletter>list_letter;
	private List<WebBussinessletter>list_buss;
	private List<KyzVisaflow>list_visaflow;

	// Constructors

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

	
	
	

	
	
	
	

}