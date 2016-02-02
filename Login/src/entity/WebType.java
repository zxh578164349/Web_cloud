package entity;

/**
 * WebType entity. @author MyEclipse Persistence Tools
 */

public class WebType implements java.io.Serializable {

	// Fields

	private WebTypeId id;
	private String typeName;
	private String webtypeMk;
	private String trMk;//0或null:非出差函文     1:出差函文   20160202

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

	public String getTrMk() {
		return trMk;
	}

	public void setTrMk(String trMk) {
		this.trMk = trMk;
	}
	
	
	

}