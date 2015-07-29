package entity;

/**
 * KyType entity. @author MyEclipse Persistence Tools
 */

public class KyType implements java.io.Serializable {

	// Fields

	private KyTypeId id;
	private String typeName;
	private String typeSname;
	private String ioMk;
	private String orderbyid;

	// Constructors

	/** default constructor */
	public KyType() {
	}

	/** minimal constructor */
	public KyType(KyTypeId id) {
		this.id = id;
	}

	/** full constructor */
	public KyType(KyTypeId id, String typeName, String typeSname, String ioMk,
			String orderbyid) {
		this.id = id;
		this.typeName = typeName;
		this.typeSname = typeSname;
		this.ioMk = ioMk;
		this.orderbyid = orderbyid;
	}

	// Property accessors

	public KyTypeId getId() {
		return this.id;
	}

	public void setId(KyTypeId id) {
		this.id = id;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeSname() {
		return this.typeSname;
	}

	public void setTypeSname(String typeSname) {
		this.typeSname = typeSname;
	}

	public String getIoMk() {
		return this.ioMk;
	}

	public void setIoMk(String ioMk) {
		this.ioMk = ioMk;
	}

	public String getOrderbyid() {
		return this.orderbyid;
	}

	public void setOrderbyid(String orderbyid) {
		this.orderbyid = orderbyid;
	}

}