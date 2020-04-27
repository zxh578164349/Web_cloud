package entity;

/**
 * WebuserEmailA entity. @author MyEclipse Persistence Tools
 */

/**
 * 
* 項目名稱：WebLogin   
* 類名稱：WebuserEmailA   
* 類描述：函文郵件同步人管理(分類別)
* 創建人：KY2
 */
public class WebuserEmailA implements java.io.Serializable{

	// Fields

	private WebuserEmailAId id;
	private String name;
	private String namePwd;
	private String colTemp;

	// Constructors

	/** default constructor */
	public WebuserEmailA(){
	}

	/** minimal constructor */
	public WebuserEmailA(WebuserEmailAId id){
		this.id=id;
	}

	/** full constructor */
	public WebuserEmailA(WebuserEmailAId id,String name,String namePwd,String colTemp){
		this.id=id;
		this.name=name;
		this.namePwd=namePwd;
		this.colTemp=colTemp;
	}

	// Property accessors

	public WebuserEmailAId getId(){
		return this.id;
	}

	public void setId(WebuserEmailAId id){
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

	public String getColTemp(){
		return this.colTemp;
	}

	public void setColTemp(String colTemp){
		this.colTemp=colTemp;
	}

}