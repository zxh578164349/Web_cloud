package entity;

/**
 * WebuserEmailA entity. @author MyEclipse Persistence Tools
 */

public class WebuserEmailA implements java.io.Serializable {

	// Fields

	private WebuserEmailAId id;
	private String name;
	private String namePwd;
	private String colTemp;
	private String typeMk;//簽核人或 知會人            0  簽核人         1  知會人

	// Constructors

	/** default constructor */
	public WebuserEmailA() {
	}

	/** minimal constructor */
	public WebuserEmailA(WebuserEmailAId id) {
		this.id = id;
	}

	/** full constructor */
	public WebuserEmailA(WebuserEmailAId id, String name, String namePwd,String colTemp) {
		this.id = id;
		this.name = name;
		this.namePwd = namePwd;
		this.colTemp=colTemp;
	}

	// Property accessors

	public WebuserEmailAId getId() {
		return this.id;
	}

	public void setId(WebuserEmailAId id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNamePwd() {
		return this.namePwd;
	}

	public void setNamePwd(String namePwd) {
		this.namePwd = namePwd;
	}

	public String getColTemp() {
		return colTemp;
	}

	public void setColTemp(String colTemp) {
		this.colTemp = colTemp;
	}

	public String getTypeMk(){
		return typeMk;
	}

	public void setTypeMk(String typeMk){
		this.typeMk=typeMk;
	}
	
	

}