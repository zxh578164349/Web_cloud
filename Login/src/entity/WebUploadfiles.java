package entity;

/**
 * WebUploadfiles entity. @author MyEclipse Persistence Tools
 */

public class WebUploadfiles implements java.io.Serializable {

	// Fields

	private Integer id;
	private String filename;
	private String filetype;
	private String uploaddate;
	private String fileuser;
	private String filefactno;

	// Constructors

	/** default constructor */
	public WebUploadfiles() {
	}

	/** minimal constructor */
	public WebUploadfiles(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public WebUploadfiles(Integer id, String filename, String filetype,
			String uploaddate, String fileuser, String filefactno) {
		this.id = id;
		this.filename = filename;
		this.filetype = filetype;
		this.uploaddate = uploaddate;
		this.fileuser = fileuser;
		this.filefactno = filefactno;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFiletype() {
		return this.filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getUploaddate() {
		return this.uploaddate;
	}

	public void setUploaddate(String uploaddate) {
		this.uploaddate = uploaddate;
	}

	public String getFileuser() {
		return this.fileuser;
	}

	public void setFileuser(String fileuser) {
		this.fileuser = fileuser;
	}

	public String getFilefactno() {
		return this.filefactno;
	}

	public void setFilefactno(String filefactno) {
		this.filefactno = filefactno;
	}

}