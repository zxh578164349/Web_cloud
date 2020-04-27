package entity;

/**
 * WebuserEmail entity. @author MyEclipse Persistence Tools
 */

/**
 * 
* 項目名稱：WebLogin   
* 類名稱：WebuserEmail   
* 類描述：函文郵件同步人管理
* 創建人：KY2
 */
public class WebuserEmail implements java.io.Serializable{

	// Fields

	private WebuserEmailId id;
	private String name;
	private String namePwd;

	// Constructors

	/** default constructor */
	public WebuserEmail(){
	}

	/** minimal constructor */
	public WebuserEmail(WebuserEmailId id){
		this.id=id;
	}

	/** full constructor */
	public WebuserEmail(WebuserEmailId id,String name,String namePwd){
		this.id=id;
		this.name=name;
		this.namePwd=namePwd;
	}

	// Property accessors

	public WebuserEmailId getId(){
		return this.id;
	}

	public void setId(WebuserEmailId id){
		this.id=id;
	}

	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getNamePwd(){
		return this.namePwd;
	}

	public void setNamePwd(String namePwd){
		this.namePwd=namePwd;
	}

}