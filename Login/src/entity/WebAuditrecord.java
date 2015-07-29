package entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * WebAuditrecord entity. @author MyEclipse Persistence Tools
 */

public class WebAuditrecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String content;
	private String applyperson;
	private String applyfact;
	private String suggestionperson;
	private Date hopefinishdate;
	private Integer auditresultid;
	private List<WebAuditrecord> webAuditresults = new ArrayList<WebAuditrecord>();

	// Constructors

	/** default constructor */
	public WebAuditrecord() {
	}

	/** minimal constructor */
	public WebAuditrecord(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public WebAuditrecord(Integer id, String title, String content,
			String applyperson, String applyfact, String suggestionperson,
			Timestamp hopefinishdate, Integer auditresultid, List<WebAuditrecord> webAuditresults) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.applyperson = applyperson;
		this.applyfact = applyfact;
		this.suggestionperson = suggestionperson;
		this.hopefinishdate = hopefinishdate;
		this.auditresultid = auditresultid;
		this.webAuditresults = webAuditresults;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getApplyperson() {
		return this.applyperson;
	}

	public void setApplyperson(String applyperson) {
		this.applyperson = applyperson;
	}

	public String getApplyfact() {
		return this.applyfact;
	}

	public void setApplyfact(String applyfact) {
		this.applyfact = applyfact;
	}

	public String getSuggestionperson() {
		return this.suggestionperson;
	}

	public void setSuggestionperson(String suggestionperson) {
		this.suggestionperson = suggestionperson;
	}

	public Date getHopefinishdate() {
		return this.hopefinishdate;
	}

	public void setHopefinishdate(Date hopefinishdate) {
		this.hopefinishdate = hopefinishdate;
	}

	public Integer getAuditresultid() {
		return this.auditresultid;
	}

	public void setAuditresultid(Integer auditresultid) {
		this.auditresultid = auditresultid;
	}

	public List<WebAuditrecord> getWebAuditresults() {
		return this.webAuditresults;
	}

	public void setWebAuditresults(List<WebAuditrecord> webAuditresults) {
		this.webAuditresults = webAuditresults;
	}

}