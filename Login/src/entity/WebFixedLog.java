package entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * WebFixedLog entity. @author MyEclipse Persistence Tools
 */

public class WebFixedLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private String factnoFrom;
	private String factcodeFrom;
	private String factnoTo;
	private String factcodeTo;
	private String fixedId;
	private String assetscoding;
	private String assetname;
	private String changedate;
	private Date logtime;

	// Constructors

	/** default constructor */
	public WebFixedLog() {
	}

	/** minimal constructor */
	public WebFixedLog(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public WebFixedLog(Integer id, String factnoFrom, String factcodeFrom,
			String factnoTo, String factcodeTo, String fixedId,
			String assetscoding, String assetname, String changedate,
			Date logtime) {
		this.id = id;
		this.factnoFrom = factnoFrom;
		this.factcodeFrom = factcodeFrom;
		this.factnoTo = factnoTo;
		this.factcodeTo = factcodeTo;
		this.fixedId = fixedId;
		this.assetscoding = assetscoding;
		this.assetname = assetname;
		this.changedate = changedate;
		this.logtime = logtime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFactnoFrom() {
		return this.factnoFrom;
	}

	public void setFactnoFrom(String factnoFrom) {
		this.factnoFrom = factnoFrom;
	}

	public String getFactcodeFrom() {
		return this.factcodeFrom;
	}

	public void setFactcodeFrom(String factcodeFrom) {
		this.factcodeFrom = factcodeFrom;
	}

	public String getFactnoTo() {
		return this.factnoTo;
	}

	public void setFactnoTo(String factnoTo) {
		this.factnoTo = factnoTo;
	}

	public String getFactcodeTo() {
		return this.factcodeTo;
	}

	public void setFactcodeTo(String factcodeTo) {
		this.factcodeTo = factcodeTo;
	}

	public String getFixedId() {
		return this.fixedId;
	}

	public void setFixedId(String fixedId) {
		this.fixedId = fixedId;
	}

	public String getAssetscoding() {
		return this.assetscoding;
	}

	public void setAssetscoding(String assetscoding) {
		this.assetscoding = assetscoding;
	}

	public String getAssetname() {
		return this.assetname;
	}

	public void setAssetname(String assetname) {
		this.assetname = assetname;
	}

	public String getChangedate() {
		return this.changedate;
	}

	public void setChangedate(String changedate) {
		this.changedate = changedate;
	}

	public Date getLogtime() {
		return logtime;
	}

	public void setLogtime(Date logtime) {
		this.logtime = logtime;
	}



}