package entity;

/**
 * WebTabpomfile entity. @author MyEclipse Persistence Tools
 */

public class WebTabpomfile implements java.io.Serializable{

	// Fields

	private Long pfileId;
	private WebTabpom webTabpom;
	private String filename;
	private String factNo;
	private String visaTypeM;
	private String username;
	private String createdate;

	// Constructors

	/** default constructor */
	public WebTabpomfile(){
	}

	/** minimal constructor */
	public WebTabpomfile(Long pfileId){
		this.pfileId=pfileId;
	}

	/** full constructor */
	public WebTabpomfile(Long pfileId,WebTabpom webTabpom,String filename,String factNo,String visaTypeM,String username,String createdate){
		this.pfileId=pfileId;
		this.webTabpom=webTabpom;
		this.filename=filename;
		this.factNo=factNo;
		this.visaTypeM=visaTypeM;
		this.username=username;
		this.createdate=createdate;
	}

	// Property accessors

	public Long getPfileId(){
		return this.pfileId;
	}

	public void setPfileId(Long pfileId){
		this.pfileId=pfileId;
	}

	public WebTabpom getWebTabpom(){
		return this.webTabpom;
	}

	public void setWebTabpom(WebTabpom webTabpom){
		this.webTabpom=webTabpom;
	}

	public String getFilename(){
		return this.filename;
	}

	public void setFilename(String filename){
		this.filename=filename;
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