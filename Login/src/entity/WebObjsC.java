package entity;

/**
 * WebObjsC entity. @author MyEclipse Persistence Tools
 */

public class WebObjsC implements java.io.Serializable {

	// Fields

	private WebObjsCId id;
	private Double objA1;//生產欠數
	private Double objA2;//孔位數
	private Double objA3;//上模數
	private Double objA4;//日產能
	private Double objA5;//回轉數
	private Long objA6;//出勤人員數
	private Long objA7;//離職、資遺人數
	private Double objA8;//每日發生費用
	private String username;
	private String usernameUd;
	private String createdate;
	private String updatedate;

	// Constructors

	/** default constructor */
	public WebObjsC() {
	}

	/** minimal constructor */
	public WebObjsC(WebObjsCId id) {
		this.id = id;
	}

	/** full constructor */
	public WebObjsC(WebObjsCId id, Double objA1, Double objA2, Double objA3,
			Double objA4, Double objA5, Long objA6, Long objA7, Double objA8,
			String username, String usernameUd, String createdate,
			String updatedate) {
		this.id = id;
		this.objA1 = objA1;
		this.objA2 = objA2;
		this.objA3 = objA3;
		this.objA4 = objA4;
		this.objA5 = objA5;
		this.objA6 = objA6;
		this.objA7 = objA7;
		this.objA8 = objA8;
		this.username = username;
		this.usernameUd = usernameUd;
		this.createdate = createdate;
		this.updatedate = updatedate;
	}

	// Property accessors

	public WebObjsCId getId() {
		return this.id;
	}

	public void setId(WebObjsCId id) {
		this.id = id;
	}

	public Double getObjA1() {
		return this.objA1;
	}

	public void setObjA1(Double objA1) {
		this.objA1 = objA1;
	}

	public Double getObjA2() {
		return this.objA2;
	}

	public void setObjA2(Double objA2) {
		this.objA2 = objA2;
	}

	public Double getObjA3() {
		return this.objA3;
	}

	public void setObjA3(Double objA3) {
		this.objA3 = objA3;
	}

	public Double getObjA4() {
		return this.objA4;
	}

	public void setObjA4(Double objA4) {
		this.objA4 = objA4;
	}

	public Double getObjA5() {
		return this.objA5;
	}

	public void setObjA5(Double objA5) {
		this.objA5 = objA5;
	}

	public Long getObjA6() {
		return this.objA6;
	}

	public void setObjA6(Long objA6) {
		this.objA6 = objA6;
	}

	public Long getObjA7() {
		return this.objA7;
	}

	public void setObjA7(Long objA7) {
		this.objA7 = objA7;
	}

	public Double getObjA8() {
		return this.objA8;
	}

	public void setObjA8(Double objA8) {
		this.objA8 = objA8;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsernameUd() {
		return this.usernameUd;
	}

	public void setUsernameUd(String usernameUd) {
		this.usernameUd = usernameUd;
	}

	public String getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getUpdatedate() {
		return this.updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

}