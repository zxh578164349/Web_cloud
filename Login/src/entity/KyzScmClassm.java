package entity;

/**
 * KyzScmClassm entity. @author MyEclipse Persistence Tools
 */

public class KyzScmClassm implements java.io.Serializable {

	// Fields

	private KyzScmClassmId id;
	private String scmBclassNm;
	private String scmMclassNm;
	private String scmSclassNm;
	private String stopMk;
	private String addTime;
	private String updTime;
	private String memo;
	private String scmBclassNmEng;
	private String scmMclassNmEng;
	private String scmSclassNmEng;

	// Constructors

	/** default constructor */
	public KyzScmClassm() {
	}

	/** minimal constructor */
	public KyzScmClassm(KyzScmClassmId id) {
		this.id = id;
	}

	/** full constructor */
	public KyzScmClassm(KyzScmClassmId id, String scmBclassNm,
			String scmMclassNm, String scmSclassNm, String stopMk,
			String addTime, String updTime, String memo, String scmBclassNmEng,
			String scmMclassNmEng, String scmSclassNmEng) {
		this.id = id;
		this.scmBclassNm = scmBclassNm;
		this.scmMclassNm = scmMclassNm;
		this.scmSclassNm = scmSclassNm;
		this.stopMk = stopMk;
		this.addTime = addTime;
		this.updTime = updTime;
		this.memo = memo;
		this.scmBclassNmEng = scmBclassNmEng;
		this.scmMclassNmEng = scmMclassNmEng;
		this.scmSclassNmEng = scmSclassNmEng;
	}

	// Property accessors

	public KyzScmClassmId getId() {
		return this.id;
	}

	public void setId(KyzScmClassmId id) {
		this.id = id;
	}

	public String getScmBclassNm() {
		return this.scmBclassNm;
	}

	public void setScmBclassNm(String scmBclassNm) {
		this.scmBclassNm = scmBclassNm;
	}

	public String getScmMclassNm() {
		return this.scmMclassNm;
	}

	public void setScmMclassNm(String scmMclassNm) {
		this.scmMclassNm = scmMclassNm;
	}

	public String getScmSclassNm() {
		return this.scmSclassNm;
	}

	public void setScmSclassNm(String scmSclassNm) {
		this.scmSclassNm = scmSclassNm;
	}

	public String getStopMk() {
		return this.stopMk;
	}

	public void setStopMk(String stopMk) {
		this.stopMk = stopMk;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getUpdTime() {
		return this.updTime;
	}

	public void setUpdTime(String updTime) {
		this.updTime = updTime;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getScmBclassNmEng() {
		return this.scmBclassNmEng;
	}

	public void setScmBclassNmEng(String scmBclassNmEng) {
		this.scmBclassNmEng = scmBclassNmEng;
	}

	public String getScmMclassNmEng() {
		return this.scmMclassNmEng;
	}

	public void setScmMclassNmEng(String scmMclassNmEng) {
		this.scmMclassNmEng = scmMclassNmEng;
	}

	public String getScmSclassNmEng() {
		return this.scmSclassNmEng;
	}

	public void setScmSclassNmEng(String scmSclassNmEng) {
		this.scmSclassNmEng = scmSclassNmEng;
	}

}