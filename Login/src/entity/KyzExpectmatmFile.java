package entity;

/**
 * KyzExpectmatmFile entity. @author MyEclipse Persistence Tools
 */

public class KyzExpectmatmFile implements java.io.Serializable {

	// Fields

	private Integer id;
	private String billno;
	private String filename;
	private String username;

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
	

}