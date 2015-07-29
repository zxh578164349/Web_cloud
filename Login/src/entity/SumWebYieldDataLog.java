package entity;

/**
 * SumWebYieldDataLog entity. @author MyEclipse Persistence Tools
 */

public class SumWebYieldDataLog implements java.io.Serializable {

	// Fields

	private SumWebYieldDataLogId id;
	private String startDate;
	private String endDate;
	private String username;
	private String createdate;

	// Constructors

	/** default constructor */
	public SumWebYieldDataLog() {
	}

	/** minimal constructor */
	public SumWebYieldDataLog(SumWebYieldDataLogId id) {
		this.id = id;
	}

	/** full constructor */
	public SumWebYieldDataLog(SumWebYieldDataLogId id, String startDate,
			String endDate, String username, String createdate) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.username = username;
		this.createdate = createdate;
	}

	// Property accessors

	public SumWebYieldDataLogId getId() {
		return this.id;
	}

	public void setId(SumWebYieldDataLogId id) {
		this.id = id;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

}