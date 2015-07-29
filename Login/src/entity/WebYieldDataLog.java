package entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * WebYieldDataLog entity. @author MyEclipse Persistence Tools
 */

public class WebYieldDataLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private String factNo;
	private String factCode;
	private Date yymmdd;
	private String username;
	private String ip;
	private Date logTime;
	private String isdel;

	// Constructors

	/** default constructor */
	public WebYieldDataLog() {
	}

	/** minimal constructor */
	public WebYieldDataLog(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public WebYieldDataLog(Integer id, String factNo, String factCode,
			Date yymmdd, String username, String ip, Date logTime,
			String isdel) {
		this.id = id;
		this.factNo = factNo;
		this.factCode = factCode;
		this.yymmdd = yymmdd;
		this.username = username;
		this.ip = ip;
		this.logTime = logTime;
		this.isdel = isdel;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFactNo() {
		return this.factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public String getFactCode() {
		return this.factCode;
	}

	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}

	

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}



	public Date getYymmdd() {
		return yymmdd;
	}

	public void setYymmdd(Date yymmdd) {
		this.yymmdd = yymmdd;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public String getIsdel() {
		return this.isdel;
	}

	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}

}