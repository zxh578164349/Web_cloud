package entity;

/**
 * KyVisabills entity. @author MyEclipse Persistence Tools
 */

public class KyVisabills implements java.io.Serializable {

	// Fields

	private KyVisabillsId id;
	private String visaSigner;
	private String visaRank;
	private String visaSignet;
	private String visaMk;
	private String visaAgent;
	private String visaAsignet;
	private String dateVisa;
	private String memo;
	private String flowMk;

	// Constructors

	/** default constructor */
	public KyVisabills() {
	}

	/** minimal constructor */
	public KyVisabills(KyVisabillsId id) {
		this.id = id;
	}

	/** full constructor */
	public KyVisabills(KyVisabillsId id, String visaSigner, String visaRank,
			String visaSignet, String visaMk, String visaAgent,
			String visaAsignet, String dateVisa, String memo,String flowMk) {
		this.id = id;
		this.visaSigner = visaSigner;
		this.visaRank = visaRank;
		this.visaSignet = visaSignet;
		this.visaMk = visaMk;
		this.visaAgent = visaAgent;
		this.visaAsignet = visaAsignet;
		this.dateVisa = dateVisa;
		this.memo = memo;
		this.flowMk=flowMk;
	}

	// Property accessors

	public KyVisabillsId getId() {
		return this.id;
	}

	public void setId(KyVisabillsId id) {
		this.id = id;
	}

	public String getVisaSigner() {
		return this.visaSigner;
	}

	public void setVisaSigner(String visaSigner) {
		this.visaSigner = visaSigner;
	}

	public String getVisaRank() {
		return this.visaRank;
	}

	public void setVisaRank(String visaRank) {
		this.visaRank = visaRank;
	}

	public String getVisaSignet() {
		return this.visaSignet;
	}

	public void setVisaSignet(String visaSignet) {
		this.visaSignet = visaSignet;
	}

	public String getVisaMk() {
		return this.visaMk;
	}

	public void setVisaMk(String visaMk) {
		this.visaMk = visaMk;
	}

	public String getVisaAgent() {
		return this.visaAgent;
	}

	public void setVisaAgent(String visaAgent) {
		this.visaAgent = visaAgent;
	}

	public String getVisaAsignet() {
		return this.visaAsignet;
	}

	public void setVisaAsignet(String visaAsignet) {
		this.visaAsignet = visaAsignet;
	}

	public String getDateVisa() {
		return this.dateVisa;
	}

	public void setDateVisa(String dateVisa) {
		this.dateVisa = dateVisa;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getFlowMk() {
		return flowMk;
	}

	public void setFlowMk(String flowMk) {
		this.flowMk = flowMk;
	}
	

}