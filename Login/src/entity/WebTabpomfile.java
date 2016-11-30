package entity;

/**
 * WebTabpomfile entity. @author MyEclipse Persistence Tools
 */

public class WebTabpomfile implements java.io.Serializable{

	// Fields

	private WebTabpomfileId id;
	private Long pfileId;
	private String factNo;
	private String visaTypeM;
	private String username;
	private String createdate;

	// Constructors

	/** default constructor */
	public WebTabpomfile(){
	}

	/** minimal constructor */
	public WebTabpomfile(WebTabpomfileId id){
		this.id=id;
	}

	/** full constructor */
	public WebTabpomfile(WebTabpomfileId id,Long pfileId,String factNo,String visaTypeM,String username,String createdate){
		this.id=id;
		this.pfileId=pfileId;
		this.factNo=factNo;
		this.visaTypeM=visaTypeM;
		this.username=username;
		this.createdate=createdate;
	}

	// Property accessors

	public WebTabpomfileId getId(){
		return this.id;
	}

	public void setId(WebTabpomfileId id){
		this.id=id;
	}

	public Long getPfileId(){
		return this.pfileId;
	}

	public void setPfileId(Long pfileId){
		this.pfileId=pfileId;
	}

	public String getFactNo(){
		return this.factNo;
	}

	public void setFactNo(String factNo){
		this.factNo=factNo;
	}

	public String getVisaTypeM(){
		return this.visaTypeM;
	}

	public void setVisaTypeM(String visaTypeM){
		this.visaTypeM=visaTypeM;
	}

	public String getUsername(){
		return this.username;
	}

	public void setUsername(String username){
		this.username=username;
	}

	public String getCreatedate(){
		return this.createdate;
	}

	public void setCreatedate(String createdate){
		this.createdate=createdate;
	}

}