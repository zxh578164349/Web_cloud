package entity;

/**
 * KyzExpectmatmFile entity. @author MyEclipse Persistence Tools
 */

/**
 * 
* 項目名稱：WebLogin   
* 類名稱：KyzExpectmatmFile   
* 類描述：函文附檔文件
* 創建人：KY2
 */
public class KyzExpectmatmFile implements java.io.Serializable {

	// Fields

	private Integer id;
	private String billno;
	private String filename;
	private String username;
	private String factNo;
	private String visaTypeM;
	private String fileurl;

	// Constructors

	/** default constructor */
	public KyzExpectmatmFile() {
	}

	/** minimal constructor */
	public KyzExpectmatmFile(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public KyzExpectmatmFile(Integer id, String billno, String filename,String username) {
		this.id = id;
		this.billno = billno;
		this.filename = filename;
		this.username=username;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBillno() {
		return this.billno;
	}

	public void setBillno(String billno) {
		this.billno = billno;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFactNo() {
		return factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public String getVisaTypeM() {
		return visaTypeM;
	}

	public void setVisaTypeM(String visaTypeM) {
		this.visaTypeM = visaTypeM;
	}

	public String getFileurl(){
		return fileurl;
	}

	public void setFileurl(String fileurl){
		this.fileurl=fileurl;
	}

	
	
	

}