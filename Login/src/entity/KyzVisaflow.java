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
	private String visaSortM;//類別大類
	private String delMk;//刪除標記        0或空 未刪除     1刪除

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

	public String getVisaSortM() {
		return visaSortM;
	}

	public void setVisaSortM(String visaSortM) {
		this.visaSortM = visaSortM;
	}

	public String getDelMk() {
		return delMk;
	}

	public void setDelMk(String delMk) {
		this.delMk = delMk;
	}

	
	


	
	

}