package entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * KyzExpectmatmLog entity. @author MyEclipse Persistence Tools
 */

public class KyzExpectmatmLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private String billNo;
	private String username;
	private Date deldate;

	// Constructors

	/** default constructor */
	public KyzExpectmatmLog() {
	}

	/** minimal constructor */
	public KyzExpectmatmLog(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public KyzExpectmatmLog(Integer id, String billNo, String username,
			Date deldate) {
		this.id = id;
		this.billNo = billNo;
		this.username = username;
		this.deldate = deldate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBillNo() {
		return this.billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDeldate() {
		return deldate;
	}

	public void setDeldate(Date deldate) {
		this.deldate = deldate;
	}



}