package entity;

/**
 * KyzContactletter entity. @author MyEclipse Persistence Tools
 */

public class KyzContactletter implements java.io.Serializable {

	// Fields

	private KyzContactletterId id;
	private String secNo;//�ӿ���
	private String chargeNo;//�ӿ�D��
	private String ymExpect;//�إߤ��
	private String userNm;//�ӽФH
	private String toUser;//�����
	private String chargeList;//CC�]�e�^
	private String title;//���D
	private String memoMk;//���e
	private String visaType;//����
	private String filesYn;//�O�_������
	private String firstPage;//�O�_��ܲĤ@��
	private String factCode;//�t�O���A
	private String colTemp;

	// Constructors

	/** default constructor */
	public KyzContactletter() {
	}

	/** minimal constructor */
	public KyzContactletter(KyzContactletterId id) {
		this.id = id;
	}

	/** full constructor */
	public KyzContactletter(KyzContactletterId id, String secNo,
			String chargeNo, String ymExpect, String userNm, String toUser,
			String chargeList, String title, String memoMk, String visaType,
			String filesYn, String firstPage,String factCode,String colTemp) {
		this.id = id;
		this.secNo = secNo;
		this.chargeNo = chargeNo;
		this.ymExpect = ymExpect;
		this.userNm = userNm;
		this.toUser = toUser;
		this.chargeList = chargeList;
		this.title = title;
		this.memoMk = memoMk;
		this.visaType = visaType;
		this.filesYn = filesYn;
		this.firstPage = firstPage;
		this.factCode=factCode;
		this.colTemp=colTemp;
	}

	// Property accessors

	public KyzContactletterId getId() {
		return this.id;
	}

	public void setId(KyzContactletterId id) {
		this.id = id;
	}

	public String getSecNo() {
		return this.secNo;
	}

	public void setSecNo(String secNo) {
		this.secNo = secNo;
	}

	public String getChargeNo() {
		return this.chargeNo;
	}

	public void setChargeNo(String chargeNo) {
		this.chargeNo = chargeNo;
	}

	public String getYmExpect() {
		return this.ymExpect;
	}

	public void setYmExpect(String ymExpect) {
		this.ymExpect = ymExpect;
	}

	public String getUserNm() {
		return this.userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getToUser() {
		return this.toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getChargeList() {
		return this.chargeList;
	}

	public void setChargeList(String chargeList) {
		this.chargeList = chargeList;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMemoMk() {
		return this.memoMk;
	}

	public void setMemoMk(String memoMk) {
		this.memoMk = memoMk;
	}

	public String getVisaType() {
		return this.visaType;
	}

	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}

	public String getFilesYn() {
		return this.filesYn;
	}

	public void setFilesYn(String filesYn) {
		this.filesYn = filesYn;
	}

	public String getFirstPage() {
		return this.firstPage;
	}

	public void setFirstPage(String firstPage) {
		this.firstPage = firstPage;
	}

	public String getFactCode() {
		return factCode;
	}

	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}

	public String getColTemp() {
		return colTemp;
	}

	public void setColTemp(String colTemp) {
		this.colTemp = colTemp;
	}
	
	

}