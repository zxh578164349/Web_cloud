package entity;

/**
 * WebJurs entity. @author MyEclipse Persistence Tools
 */

public class WebJurs implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer submenuid;
	private String name;

	// Constructors

	/** default constructor */
	public WebJurs() {
	}

	/** minimal constructor */
	public WebJurs(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public WebJurs(Integer id, Integer submenuid, String name) {
		this.id = id;
		this.submenuid = submenuid;
		this.name = name;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSubmenuid() {
		return this.submenuid;
	}

	public void setSubmenuid(Integer submenuid) {
		this.submenuid = submenuid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}