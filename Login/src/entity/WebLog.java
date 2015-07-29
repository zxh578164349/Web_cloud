package entity;

import java.util.Date;

/**
 * WebLog entity. @author MyEclipse Persistence Tools
 */

public class WebLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private String ip;
	private String username;
	private Date logtime;
	private String factno;

	// Constructors

	/** default constructor */
	public WebLog() {
	}

	/** full constructor */
	public WebLog(Integer id, String ip, String username, Date logtime,String factno) {
		this.id = id;
		this.ip = ip;
		this.username = username;
		this.logtime = logtime;
		this.factno=factno;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getLogtime() {
		return this.logtime;
	}

	public void setLogtime(Date logtime) {
		this.logtime = logtime;
	}

	public String getFactno() {
		return factno;
	}

	public void setFactno(String factno) {
		this.factno = factno;
	}
	

}