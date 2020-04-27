package entity;

import java.sql.Timestamp;
import java.util.Date;

import com.opensymphony.xwork2.ActionContext;

/**
 * KyzExpectmatmLog entity. @author MyEclipse Persistence Tools
 */

/**
 * 
* 項目名稱：WebLogin   
* 類名稱：KyzExpectmatmLog   
* 類描述：數據刪記錄
* 創建人：KY2
 */
public class KyzExpectmatmLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private String billNo;
	private String username;
	private Date deldate=new Date();
	private String factNo;
	private String factCode;
	private String obj;
	private String content;
	private String yymm;

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
		if(username==null||username.equals("")){
			WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
			username=user.getUsername();
		}
		this.username = username;
	}

	public Date getDeldate() {
		return deldate;
	}

	public void setDeldate(Date deldate) {
		this.deldate = deldate;
	}

	public String getFactNo() {
		return factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public String getFactCode() {
		return factCode;
	}

	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}

	public String getObj() {
		return obj;
	}

	public void setObj(String obj) {
		this.obj = obj;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getYymm() {
		return yymm;
	}

	public void setYymm(String yymm) {
		this.yymm = yymm;
	}
	
	



}