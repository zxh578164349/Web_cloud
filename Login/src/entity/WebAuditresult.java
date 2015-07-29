package entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * WebAuditresult entity. @author MyEclipse Persistence Tools
 */

public class WebAuditresult implements java.io.Serializable {

	// Fields

	private Integer auditresultid;
	private WebAuditrecord webAuditrecord;
	private String auditperson;
	private Integer whether;
	private Date auditdate;
	private Integer whetheraudit;

	// Constructors

	/** default constructor */
	public WebAuditresult() {
	}

	/** minimal constructor */
	public WebAuditresult(Integer auditresultid, WebAuditrecord webAuditrecord) {
		this.auditresultid = auditresultid;
		this.webAuditrecord = webAuditrecord;
	}

	/** full constructor */
	public WebAuditresult(Integer auditresultid, WebAuditrecord webAuditrecord,
			String auditperson, Integer whether, Timestamp auditdate,
			Integer whetheraudit) {
		this.auditresultid = auditresultid;
		this.webAuditrecord = webAuditrecord;
		this.auditperson = auditperson;
		this.whether = whether;
		this.auditdate = auditdate;
		this.whetheraudit = whetheraudit;
	}

	// Property accessors

	public Integer getAuditresultid() {
		return this.auditresultid;
	}

	public void setAuditresultid(Integer auditresultid) {
		this.auditresultid = auditresultid;
	}

	public WebAuditrecord getWebAuditrecord() {
		return this.webAuditrecord;
	}

	public void setWebAuditrecord(WebAuditrecord webAuditrecord) {
		this.webAuditrecord = webAuditrecord;
	}

	public String getAuditperson() {
		return this.auditperson;
	}

	public void setAuditperson(String auditperson) {
		this.auditperson = auditperson;
	}

	public Integer getWhether() {
		return this.whether;
	}

	public void setWhether(Integer whether) {
		this.whether = whether;
	}

	public Date getAuditdate() {
		return this.auditdate;
	}

	public void setAuditdate(Date auditdate) {
		this.auditdate = auditdate;
	}

	public Integer getWhetheraudit() {
		return this.whetheraudit;
	}

	public void setWhetheraudit(Integer whetheraudit) {
		this.whetheraudit = whetheraudit;
	}

}