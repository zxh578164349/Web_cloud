package entity;

/**
 * WebEmailAll entity. @author MyEclipse Persistence Tools
 */

public class WebEmailAll implements java.io.Serializable{

	// Fields

	private Integer eid;
	private String factNo;
	private String factCode;
	private String factPart;
	private String email;
	private String username;
	private String toMail;//收件人
	private String toCc;//抄送人
	private String mkA;//工廠訂單Email
	private String mkB;//其它email

	// Constructors

	/** default constructor */
	public WebEmailAll(){
	}

	/** minimal constructor */
	public WebEmailAll(Integer eid){
		this.eid=eid;
	}

	/** full constructor */
	public WebEmailAll(Integer eid,String factNo,String factCode,String factPart,String email,String username,String toMail,String toCc,String mkA,String mkB){
		this.eid=eid;
		this.factNo=factNo;
		this.factCode=factCode;
		this.factPart=factPart;
		this.email=email;
		this.username=username;
		this.toMail=toMail;
		this.toCc=toCc;
		this.mkA=mkA;
		this.mkB=mkB;
	}

	// Property accessors

	public Integer getEid(){
		return this.eid;
	}

	public void setEid(Integer eid){
		this.eid=eid;
	}

	public String getFactNo(){
		return this.factNo;
	}

	public void setFactNo(String factNo){
		this.factNo=factNo;
	}

	public String getFactCode(){
		return this.factCode;
	}

	public void setFactCode(String factCode){
		this.factCode=factCode;
	}

	public String getFactPart(){
		return this.factPart;
	}

	public void setFactPart(String factPart){
		this.factPart=factPart;
	}

	public String getEmail(){
		return this.email;
	}

	public void setEmail(String email){
		this.email=email;
	}

	public String getUsername(){
		return this.username;
	}

	public void setUsername(String username){
		this.username=username;
	}

	public String getToMail(){
		return this.toMail;
	}

	public void setToMail(String toMail){
		this.toMail=toMail;
	}

	public String getToCc(){
		return this.toCc;
	}

	public void setToCc(String toCc){
		this.toCc=toCc;
	}

	public String getMkA(){
		return this.mkA;
	}

	public void setMkA(String mkA){
		this.mkA=mkA;
	}

	public String getMkB(){
		return this.mkB;
	}

	public void setMkB(String mkB){
		this.mkB=mkB;
	}

}