package entity;

/**
 * WebErpProductinFormation entity. @author MyEclipse Persistence Tools
 */

public class WebErpProductinFormation implements java.io.Serializable{

	// Fields

	private Integer itemid;
	private String itemcode;
	private String itemcategory;
	private String itemcategoryname;
	private String namec1;
	private String namec2;
	private String namee1;
	private String namee2;
	private String desc1;
	private String desc2;
	private String desc3;
	private String desc4;
	private String desc5;
	private String desc6;
	private String createdate;
	private Integer createby;
	private String modifieddate;
	private Integer modifiedby;
	private Byte state;
	private Byte status;

	// Constructors

	/** default constructor */
	public WebErpProductinFormation(){
	}

	/** minimal constructor */
	public WebErpProductinFormation(Integer itemid){
		this.itemid=itemid;
	}

	/** full constructor */
	public WebErpProductinFormation(Integer itemid,String itemcode,String itemcategory,String itemcategoryname,String namec1,String namec2,String namee1,
			String namee2,String desc1,String desc2,String desc3,String desc4,String desc5,String desc6,String createdate,Integer createby,String modifieddate,
			Integer modifiedby,Byte state,Byte status){
		this.itemid=itemid;
		this.itemcode=itemcode;
		this.itemcategory=itemcategory;
		this.itemcategoryname=itemcategoryname;
		this.namec1=namec1;
		this.namec2=namec2;
		this.namee1=namee1;
		this.namee2=namee2;
		this.desc1=desc1;
		this.desc2=desc2;
		this.desc3=desc3;
		this.desc4=desc4;
		this.desc5=desc5;
		this.desc6=desc6;
		this.createdate=createdate;
		this.createby=createby;
		this.modifieddate=modifieddate;
		this.modifiedby=modifiedby;
		this.state=state;
		this.status=status;
	}

	// Property accessors

	public Integer getItemid(){
		return this.itemid;
	}

	public void setItemid(Integer itemid){
		this.itemid=itemid;
	}

	public String getItemcode(){
		return this.itemcode;
	}

	public void setItemcode(String itemcode){
		this.itemcode=itemcode;
	}

	public String getItemcategory(){
		return this.itemcategory;
	}

	public void setItemcategory(String itemcategory){
		this.itemcategory=itemcategory;
	}

	public String getItemcategoryname(){
		return this.itemcategoryname;
	}

	public void setItemcategoryname(String itemcategoryname){
		this.itemcategoryname=itemcategoryname;
	}

	public String getNamec1(){
		return this.namec1;
	}

	public void setNamec1(String namec1){
		this.namec1=namec1;
	}

	public String getNamec2(){
		return this.namec2;
	}

	public void setNamec2(String namec2){
		this.namec2=namec2;
	}

	public String getNamee1(){
		return this.namee1;
	}

	public void setNamee1(String namee1){
		this.namee1=namee1;
	}

	public String getNamee2(){
		return this.namee2;
	}

	public void setNamee2(String namee2){
		this.namee2=namee2;
	}

	public String getDesc1(){
		return this.desc1;
	}

	public void setDesc1(String desc1){
		this.desc1=desc1;
	}

	public String getDesc2(){
		return this.desc2;
	}

	public void setDesc2(String desc2){
		this.desc2=desc2;
	}

	public String getDesc3(){
		return this.desc3;
	}

	public void setDesc3(String desc3){
		this.desc3=desc3;
	}

	public String getDesc4(){
		return this.desc4;
	}

	public void setDesc4(String desc4){
		this.desc4=desc4;
	}

	public String getDesc5(){
		return this.desc5;
	}

	public void setDesc5(String desc5){
		this.desc5=desc5;
	}

	public String getDesc6(){
		return this.desc6;
	}

	public void setDesc6(String desc6){
		this.desc6=desc6;
	}

	public String getCreatedate(){
		return this.createdate;
	}

	public void setCreatedate(String createdate){
		this.createdate=createdate;
	}

	public Integer getCreateby(){
		return this.createby;
	}

	public void setCreateby(Integer createby){
		this.createby=createby;
	}

	public String getModifieddate(){
		return this.modifieddate;
	}

	public void setModifieddate(String modifieddate){
		this.modifieddate=modifieddate;
	}

	public Integer getModifiedby(){
		return this.modifiedby;
	}

	public void setModifiedby(Integer modifiedby){
		this.modifiedby=modifiedby;
	}

	public Byte getState(){
		return this.state;
	}

	public void setState(Byte state){
		this.state=state;
	}

	public Byte getStatus(){
		return this.status;
	}

	public void setStatus(Byte status){
		this.status=status;
	}

}