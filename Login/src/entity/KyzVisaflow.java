package entity;

/**
 * KyzVisaflow entity. @author MyEclipse Persistence Tools
 */

public class KyzVisaflow implements java.io.Serializable {

	// Fields

	private KyzVisaflowId id;
	private String visaSigner;
	private String visaRank;
	private String flowMk;
	private String colTemp;
	private String trMk;//0或null:非出差流程    1:出差流程   20160202

	// Constructors

	/** default constructor */
	public KyzVisaflow() {
	}

	/** minimal constructor */
	public KyzVisaflow(KyzVisaflowId id) {
		this.id = id;
	}

	/** full constructor */
	public KyzVisaflow(KyzVisaflowId id, String visaSigner, String visaRank,String flowMk,String colTemp) {
		this.id = id;
		this.visaSigner = visaSigner;
		this.visaRank = visaRank;
		this.flowMk=flowMk;
		this.colTemp=colTemp;
	}

	// Property accessors

	public KyzVisaflowId getId() {
		return this.id;
	}

	public void setId(KyzVisaflowId id) {
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

	public String getFlowMk() {
		return flowMk;
	}

	public void setFlowMk(String flowMk) {
		this.flowMk = flowMk;
	}

	public String getColTemp() {
		return colTemp;
	}

	public void setColTemp(String colTemp) {
		this.colTemp = colTemp;
	}

	public String getTrMk() {
		return trMk;
	}

	public void setTrMk(String trMk) {
		this.trMk = trMk;
	}
	
	

}