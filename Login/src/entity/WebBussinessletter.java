package entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * WebBussinessletter entity. @author MyEclipse Persistence Tools
 */

public class WebBussinessletter implements java.io.Serializable {

	// Fields

	private String blNo;
	private String unit;
	private String username;
	private String position;
	private String GAgent;
	private String address;
	private Date dateFrom;
	private Date dateEnd;
	private Date timeFrom;
	private Date timeEnd;
	private String createDate;
	private String factNo;
	private String planList;
	private String visaSort;
	private Integer sumDate;

	// Constructors

	/** default constructor */
	public WebBussinessletter() {
	}

	/** minimal constructor */
	public WebBussinessletter(String blNo) {
		this.blNo = blNo;
	}

	/** full constructor */
	public WebBussinessletter(String blNo, String unit, String username,
			String position, String GAgent, String address, Date dateFrom,
			Date dateEnd, Date timeFrom, Date timeEnd,
			String createDate, String factNo, String planList) {
		this.blNo = blNo;
		this.unit = unit;
		this.username = username;
		this.position = position;
		this.GAgent = GAgent;
		this.address = address;
		this.dateFrom = dateFrom;
		this.dateEnd = dateEnd;
		this.timeFrom = timeFrom;
		this.timeEnd = timeEnd;
		this.createDate = createDate;
		this.factNo = factNo;
		this.planList = planList;
	}

	// Property accessors

	public String getBlNo() {
		return this.blNo;
	}

	public void setBlNo(String blNo) {
		this.blNo = blNo;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getGAgent() {
		return this.GAgent;
	}

	public void setGAgent(String GAgent) {
		this.GAgent = GAgent;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}



	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Date getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(Date timeFrom) {
		this.timeFrom = timeFrom;
	}

	public Date getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getFactNo() {
		return this.factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public String getPlanList() {
		return this.planList;
	}

	public void setPlanList(String planList) {
		this.planList = planList;
	}

	public String getVisaSort() {
		return visaSort;
	}

	public void setVisaSort(String visaSort) {
		this.visaSort = visaSort;
	}

	public Integer getSumDate() {
		return sumDate;
	}

	public void setSumDate(Integer sumDate) {
		this.sumDate = sumDate;
	}
	
	

}