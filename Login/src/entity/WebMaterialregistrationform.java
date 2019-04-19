package entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * WebMaterialregistrationform entity. @author MyEclipse Persistence Tools
 */

public class WebMaterialregistrationform implements java.io.Serializable {

	// Fields

	private Integer mid;
	private WebUser webUserByUpdateuser;
	private WebUser webUserByCreateuser;
	private String mtype;
	private String sdateA;
	private String edateA;
	private String sdateB;
	private String edateB;
	private String sdateC;
	private String edateC;
	private String sdateD;
	private String edateD;
	private String createDate;
	private String updateDate;
	private String sdateE;
	private String edateE;
	private List webMaterialregistrationitemses = new ArrayList();
	private WebMonths yymm;

	// Constructors

	/** default constructor */
	public WebMaterialregistrationform() {
	}

	/** minimal constructor */
	public WebMaterialregistrationform(Integer mid) {
		this.mid = mid;
	}

	/** full constructor */
	public WebMaterialregistrationform(Integer mid,
			WebUser webUserByUpdateuser, WebUser webUserByCreateuser,
			String mtype, String sdateA, String edateA, String sdateB,
			String edateB, String sdateC, String edateC, String sdateD,
			String edateD, String createDate, String updateDate, String sdateE,
			String edateE, List webMaterialregistrationitemses) {
		this.mid = mid;
		this.webUserByUpdateuser = webUserByUpdateuser;
		this.webUserByCreateuser = webUserByCreateuser;
		this.mtype = mtype;
		this.sdateA = sdateA;
		this.edateA = edateA;
		this.sdateB = sdateB;
		this.edateB = edateB;
		this.sdateC = sdateC;
		this.edateC = edateC;
		this.sdateD = sdateD;
		this.edateD = edateD;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.sdateE = sdateE;
		this.edateE = edateE;
		this.webMaterialregistrationitemses = webMaterialregistrationitemses;
	}

	// Property accessors

	
	public Integer getMid() {
		return this.mid;
	}

	public WebMonths getYymm() {
		return yymm;
	}

	public void setYymm(WebMonths yymm) {
		this.yymm = yymm;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public WebUser getWebUserByUpdateuser() {
		return this.webUserByUpdateuser;
	}

	public void setWebUserByUpdateuser(WebUser webUserByUpdateuser) {
		this.webUserByUpdateuser = webUserByUpdateuser;
	}

	public WebUser getWebUserByCreateuser() {
		return this.webUserByCreateuser;
	}

	public void setWebUserByCreateuser(WebUser webUserByCreateuser) {
		this.webUserByCreateuser = webUserByCreateuser;
	}

	public String getMtype() {
		return mtype;
	}

	public void setMtype(String mtype) {
		this.mtype = mtype;
	}

	public List getWebMaterialregistrationitemses() {
		return webMaterialregistrationitemses;
	}

	public void setWebMaterialregistrationitemses(
			List webMaterialregistrationitemses) {
		this.webMaterialregistrationitemses = webMaterialregistrationitemses;
	}

	public String getSdateA() {
		return this.sdateA;
	}

	public void setSdateA(String sdateA) {
		this.sdateA = sdateA;
	}

	public String getEdateA() {
		return this.edateA;
	}

	public void setEdateA(String edateA) {
		this.edateA = edateA;
	}

	public String getSdateB() {
		return this.sdateB;
	}

	public void setSdateB(String sdateB) {
		this.sdateB = sdateB;
	}

	public String getEdateB() {
		return this.edateB;
	}

	public void setEdateB(String edateB) {
		this.edateB = edateB;
	}

	public String getSdateC() {
		return this.sdateC;
	}

	public void setSdateC(String sdateC) {
		this.sdateC = sdateC;
	}

	public String getEdateC() {
		return this.edateC;
	}

	public void setEdateC(String edateC) {
		this.edateC = edateC;
	}

	public String getSdateD() {
		return this.sdateD;
	}

	public void setSdateD(String sdateD) {
		this.sdateD = sdateD;
	}

	public String getEdateD() {
		return this.edateD;
	}

	public void setEdateD(String edateD) {
		this.edateD = edateD;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getSdateE() {
		return this.sdateE;
	}

	public void setSdateE(String sdateE) {
		this.sdateE = sdateE;
	}

	public String getEdateE() {
		return this.edateE;
	}

	public void setEdateE(String edateE) {
		this.edateE = edateE;
	}

	

}