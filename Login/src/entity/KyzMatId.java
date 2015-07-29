package entity;

/**
 * KyzMatId entity. @author MyEclipse Persistence Tools
 */

public class KyzMatId implements java.io.Serializable {

	// Fields

	private String matNo;
	private String matCname;
	private String matEname;
	private String matType;
	private String matSize;
	private String color;
	private String acctNo;
	private String policy;
	private Double purPrice;
	private Byte dateUse;
	private Double saftQty;
	private String stockNo;
	private String cunit;
	private String eunit;
	private String punit;
	private String smatNo;
	private String smatName;
	private Double smatWeit;
	private String builder;
	private String dateB;
	private String updateMan;
	private String dateUpdate;
	private String lunit;
	private Double clRate;
	private String yyMk;
	private String formalMk;
	private String fromMk;
	private String gwMk;
	private String unitWeit;
	private Double rateWaste;
	private String itemMat;
	private String smatUnit;
	private Double smatRate;
	private String gwTypemk;
	private String areaMk1;
	private String areaMk2;
	private String areaMk3;
	private String useMk;
	private String dateStop;
	private String costMk;
	private String matNew;
	private String matMk;
	private String typeBno;
	private String typeMno;
	private String typeSno;
	private String ASize;
	private String BSize;
	private String HSize;
	private Double weit;
	private String bsmatNo;
	private String bsmatName;
	private String matType2;
	private String compNo;
	private String compNo1;
	private String gxMk;
	private String zbMk;
	private String factMk;
	private String dateBefroe;
	private String dateMax;
	private String cbzbMk;
	private String matType3;

	// Constructors

	/** default constructor */
	public KyzMatId() {
	}

	/** minimal constructor */
	public KyzMatId(String matNo) {
		this.matNo = matNo;
	}

	/** full constructor */
	public KyzMatId(String matNo, String matCname, String matEname,
			String matType, String matSize, String color, String acctNo,
			String policy, Double purPrice, Byte dateUse, Double saftQty,
			String stockNo, String cunit, String eunit, String punit,
			String smatNo, String smatName, Double smatWeit, String builder,
			String dateB, String updateMan, String dateUpdate, String lunit,
			Double clRate, String yyMk, String formalMk, String fromMk,
			String gwMk, String unitWeit, Double rateWaste, String itemMat,
			String smatUnit, Double smatRate, String gwTypemk, String areaMk1,
			String areaMk2, String areaMk3, String useMk, String dateStop,
			String costMk, String matNew, String matMk, String typeBno,
			String typeMno, String typeSno, String ASize, String BSize,
			String HSize, Double weit, String bsmatNo, String bsmatName,
			String matType2, String compNo, String compNo1, String gxMk,
			String zbMk, String factMk, String dateBefroe, String dateMax,
			String cbzbMk, String matType3) {
		this.matNo = matNo;
		this.matCname = matCname;
		this.matEname = matEname;
		this.matType = matType;
		this.matSize = matSize;
		this.color = color;
		this.acctNo = acctNo;
		this.policy = policy;
		this.purPrice = purPrice;
		this.dateUse = dateUse;
		this.saftQty = saftQty;
		this.stockNo = stockNo;
		this.cunit = cunit;
		this.eunit = eunit;
		this.punit = punit;
		this.smatNo = smatNo;
		this.smatName = smatName;
		this.smatWeit = smatWeit;
		this.builder = builder;
		this.dateB = dateB;
		this.updateMan = updateMan;
		this.dateUpdate = dateUpdate;
		this.lunit = lunit;
		this.clRate = clRate;
		this.yyMk = yyMk;
		this.formalMk = formalMk;
		this.fromMk = fromMk;
		this.gwMk = gwMk;
		this.unitWeit = unitWeit;
		this.rateWaste = rateWaste;
		this.itemMat = itemMat;
		this.smatUnit = smatUnit;
		this.smatRate = smatRate;
		this.gwTypemk = gwTypemk;
		this.areaMk1 = areaMk1;
		this.areaMk2 = areaMk2;
		this.areaMk3 = areaMk3;
		this.useMk = useMk;
		this.dateStop = dateStop;
		this.costMk = costMk;
		this.matNew = matNew;
		this.matMk = matMk;
		this.typeBno = typeBno;
		this.typeMno = typeMno;
		this.typeSno = typeSno;
		this.ASize = ASize;
		this.BSize = BSize;
		this.HSize = HSize;
		this.weit = weit;
		this.bsmatNo = bsmatNo;
		this.bsmatName = bsmatName;
		this.matType2 = matType2;
		this.compNo = compNo;
		this.compNo1 = compNo1;
		this.gxMk = gxMk;
		this.zbMk = zbMk;
		this.factMk = factMk;
		this.dateBefroe = dateBefroe;
		this.dateMax = dateMax;
		this.cbzbMk = cbzbMk;
		this.matType3 = matType3;
	}

	// Property accessors

	public String getMatNo() {
		return this.matNo;
	}

	public void setMatNo(String matNo) {
		this.matNo = matNo;
	}

	public String getMatCname() {
		return this.matCname;
	}

	public void setMatCname(String matCname) {
		this.matCname = matCname;
	}

	public String getMatEname() {
		return this.matEname;
	}

	public void setMatEname(String matEname) {
		this.matEname = matEname;
	}

	public String getMatType() {
		return this.matType;
	}

	public void setMatType(String matType) {
		this.matType = matType;
	}

	public String getMatSize() {
		return this.matSize;
	}

	public void setMatSize(String matSize) {
		this.matSize = matSize;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getAcctNo() {
		return this.acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getPolicy() {
		return this.policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

	public Double getPurPrice() {
		return this.purPrice;
	}

	public void setPurPrice(Double purPrice) {
		this.purPrice = purPrice;
	}

	public Byte getDateUse() {
		return this.dateUse;
	}

	public void setDateUse(Byte dateUse) {
		this.dateUse = dateUse;
	}

	public Double getSaftQty() {
		return this.saftQty;
	}

	public void setSaftQty(Double saftQty) {
		this.saftQty = saftQty;
	}

	public String getStockNo() {
		return this.stockNo;
	}

	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}

	public String getCunit() {
		return this.cunit;
	}

	public void setCunit(String cunit) {
		this.cunit = cunit;
	}

	public String getEunit() {
		return this.eunit;
	}

	public void setEunit(String eunit) {
		this.eunit = eunit;
	}

	public String getPunit() {
		return this.punit;
	}

	public void setPunit(String punit) {
		this.punit = punit;
	}

	public String getSmatNo() {
		return this.smatNo;
	}

	public void setSmatNo(String smatNo) {
		this.smatNo = smatNo;
	}

	public String getSmatName() {
		return this.smatName;
	}

	public void setSmatName(String smatName) {
		this.smatName = smatName;
	}

	public Double getSmatWeit() {
		return this.smatWeit;
	}

	public void setSmatWeit(Double smatWeit) {
		this.smatWeit = smatWeit;
	}

	public String getBuilder() {
		return this.builder;
	}

	public void setBuilder(String builder) {
		this.builder = builder;
	}

	public String getDateB() {
		return this.dateB;
	}

	public void setDateB(String dateB) {
		this.dateB = dateB;
	}

	public String getUpdateMan() {
		return this.updateMan;
	}

	public void setUpdateMan(String updateMan) {
		this.updateMan = updateMan;
	}

	public String getDateUpdate() {
		return this.dateUpdate;
	}

	public void setDateUpdate(String dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public String getLunit() {
		return this.lunit;
	}

	public void setLunit(String lunit) {
		this.lunit = lunit;
	}

	public Double getClRate() {
		return this.clRate;
	}

	public void setClRate(Double clRate) {
		this.clRate = clRate;
	}

	public String getYyMk() {
		return this.yyMk;
	}

	public void setYyMk(String yyMk) {
		this.yyMk = yyMk;
	}

	public String getFormalMk() {
		return this.formalMk;
	}

	public void setFormalMk(String formalMk) {
		this.formalMk = formalMk;
	}

	public String getFromMk() {
		return this.fromMk;
	}

	public void setFromMk(String fromMk) {
		this.fromMk = fromMk;
	}

	public String getGwMk() {
		return this.gwMk;
	}

	public void setGwMk(String gwMk) {
		this.gwMk = gwMk;
	}

	public String getUnitWeit() {
		return this.unitWeit;
	}

	public void setUnitWeit(String unitWeit) {
		this.unitWeit = unitWeit;
	}

	public Double getRateWaste() {
		return this.rateWaste;
	}

	public void setRateWaste(Double rateWaste) {
		this.rateWaste = rateWaste;
	}

	public String getItemMat() {
		return this.itemMat;
	}

	public void setItemMat(String itemMat) {
		this.itemMat = itemMat;
	}

	public String getSmatUnit() {
		return this.smatUnit;
	}

	public void setSmatUnit(String smatUnit) {
		this.smatUnit = smatUnit;
	}

	public Double getSmatRate() {
		return this.smatRate;
	}

	public void setSmatRate(Double smatRate) {
		this.smatRate = smatRate;
	}

	public String getGwTypemk() {
		return this.gwTypemk;
	}

	public void setGwTypemk(String gwTypemk) {
		this.gwTypemk = gwTypemk;
	}

	public String getAreaMk1() {
		return this.areaMk1;
	}

	public void setAreaMk1(String areaMk1) {
		this.areaMk1 = areaMk1;
	}

	public String getAreaMk2() {
		return this.areaMk2;
	}

	public void setAreaMk2(String areaMk2) {
		this.areaMk2 = areaMk2;
	}

	public String getAreaMk3() {
		return this.areaMk3;
	}

	public void setAreaMk3(String areaMk3) {
		this.areaMk3 = areaMk3;
	}

	public String getUseMk() {
		return this.useMk;
	}

	public void setUseMk(String useMk) {
		this.useMk = useMk;
	}

	public String getDateStop() {
		return this.dateStop;
	}

	public void setDateStop(String dateStop) {
		this.dateStop = dateStop;
	}

	public String getCostMk() {
		return this.costMk;
	}

	public void setCostMk(String costMk) {
		this.costMk = costMk;
	}

	public String getMatNew() {
		return this.matNew;
	}

	public void setMatNew(String matNew) {
		this.matNew = matNew;
	}

	public String getMatMk() {
		return this.matMk;
	}

	public void setMatMk(String matMk) {
		this.matMk = matMk;
	}

	public String getTypeBno() {
		return this.typeBno;
	}

	public void setTypeBno(String typeBno) {
		this.typeBno = typeBno;
	}

	public String getTypeMno() {
		return this.typeMno;
	}

	public void setTypeMno(String typeMno) {
		this.typeMno = typeMno;
	}

	public String getTypeSno() {
		return this.typeSno;
	}

	public void setTypeSno(String typeSno) {
		this.typeSno = typeSno;
	}

	public String getASize() {
		return this.ASize;
	}

	public void setASize(String ASize) {
		this.ASize = ASize;
	}

	public String getBSize() {
		return this.BSize;
	}

	public void setBSize(String BSize) {
		this.BSize = BSize;
	}

	public String getHSize() {
		return this.HSize;
	}

	public void setHSize(String HSize) {
		this.HSize = HSize;
	}

	public Double getWeit() {
		return this.weit;
	}

	public void setWeit(Double weit) {
		this.weit = weit;
	}

	public String getBsmatNo() {
		return this.bsmatNo;
	}

	public void setBsmatNo(String bsmatNo) {
		this.bsmatNo = bsmatNo;
	}

	public String getBsmatName() {
		return this.bsmatName;
	}

	public void setBsmatName(String bsmatName) {
		this.bsmatName = bsmatName;
	}

	public String getMatType2() {
		return this.matType2;
	}

	public void setMatType2(String matType2) {
		this.matType2 = matType2;
	}

	public String getCompNo() {
		return this.compNo;
	}

	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}

	public String getCompNo1() {
		return this.compNo1;
	}

	public void setCompNo1(String compNo1) {
		this.compNo1 = compNo1;
	}

	public String getGxMk() {
		return this.gxMk;
	}

	public void setGxMk(String gxMk) {
		this.gxMk = gxMk;
	}

	public String getZbMk() {
		return this.zbMk;
	}

	public void setZbMk(String zbMk) {
		this.zbMk = zbMk;
	}

	public String getFactMk() {
		return this.factMk;
	}

	public void setFactMk(String factMk) {
		this.factMk = factMk;
	}

	public String getDateBefroe() {
		return this.dateBefroe;
	}

	public void setDateBefroe(String dateBefroe) {
		this.dateBefroe = dateBefroe;
	}

	public String getDateMax() {
		return this.dateMax;
	}

	public void setDateMax(String dateMax) {
		this.dateMax = dateMax;
	}

	public String getCbzbMk() {
		return this.cbzbMk;
	}

	public void setCbzbMk(String cbzbMk) {
		this.cbzbMk = cbzbMk;
	}

	public String getMatType3() {
		return this.matType3;
	}

	public void setMatType3(String matType3) {
		this.matType3 = matType3;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof KyzMatId))
			return false;
		KyzMatId castOther = (KyzMatId) other;

		return ((this.getMatNo() == castOther.getMatNo()) || (this.getMatNo() != null
				&& castOther.getMatNo() != null && this.getMatNo().equals(
				castOther.getMatNo())))
				&& ((this.getMatCname() == castOther.getMatCname()) || (this
						.getMatCname() != null
						&& castOther.getMatCname() != null && this
						.getMatCname().equals(castOther.getMatCname())))
				&& ((this.getMatEname() == castOther.getMatEname()) || (this
						.getMatEname() != null
						&& castOther.getMatEname() != null && this
						.getMatEname().equals(castOther.getMatEname())))
				&& ((this.getMatType() == castOther.getMatType()) || (this
						.getMatType() != null && castOther.getMatType() != null && this
						.getMatType().equals(castOther.getMatType())))
				&& ((this.getMatSize() == castOther.getMatSize()) || (this
						.getMatSize() != null && castOther.getMatSize() != null && this
						.getMatSize().equals(castOther.getMatSize())))
				&& ((this.getColor() == castOther.getColor()) || (this
						.getColor() != null && castOther.getColor() != null && this
						.getColor().equals(castOther.getColor())))
				&& ((this.getAcctNo() == castOther.getAcctNo()) || (this
						.getAcctNo() != null && castOther.getAcctNo() != null && this
						.getAcctNo().equals(castOther.getAcctNo())))
				&& ((this.getPolicy() == castOther.getPolicy()) || (this
						.getPolicy() != null && castOther.getPolicy() != null && this
						.getPolicy().equals(castOther.getPolicy())))
				&& ((this.getPurPrice() == castOther.getPurPrice()) || (this
						.getPurPrice() != null
						&& castOther.getPurPrice() != null && this
						.getPurPrice().equals(castOther.getPurPrice())))
				&& ((this.getDateUse() == castOther.getDateUse()) || (this
						.getDateUse() != null && castOther.getDateUse() != null && this
						.getDateUse().equals(castOther.getDateUse())))
				&& ((this.getSaftQty() == castOther.getSaftQty()) || (this
						.getSaftQty() != null && castOther.getSaftQty() != null && this
						.getSaftQty().equals(castOther.getSaftQty())))
				&& ((this.getStockNo() == castOther.getStockNo()) || (this
						.getStockNo() != null && castOther.getStockNo() != null && this
						.getStockNo().equals(castOther.getStockNo())))
				&& ((this.getCunit() == castOther.getCunit()) || (this
						.getCunit() != null && castOther.getCunit() != null && this
						.getCunit().equals(castOther.getCunit())))
				&& ((this.getEunit() == castOther.getEunit()) || (this
						.getEunit() != null && castOther.getEunit() != null && this
						.getEunit().equals(castOther.getEunit())))
				&& ((this.getPunit() == castOther.getPunit()) || (this
						.getPunit() != null && castOther.getPunit() != null && this
						.getPunit().equals(castOther.getPunit())))
				&& ((this.getSmatNo() == castOther.getSmatNo()) || (this
						.getSmatNo() != null && castOther.getSmatNo() != null && this
						.getSmatNo().equals(castOther.getSmatNo())))
				&& ((this.getSmatName() == castOther.getSmatName()) || (this
						.getSmatName() != null
						&& castOther.getSmatName() != null && this
						.getSmatName().equals(castOther.getSmatName())))
				&& ((this.getSmatWeit() == castOther.getSmatWeit()) || (this
						.getSmatWeit() != null
						&& castOther.getSmatWeit() != null && this
						.getSmatWeit().equals(castOther.getSmatWeit())))
				&& ((this.getBuilder() == castOther.getBuilder()) || (this
						.getBuilder() != null && castOther.getBuilder() != null && this
						.getBuilder().equals(castOther.getBuilder())))
				&& ((this.getDateB() == castOther.getDateB()) || (this
						.getDateB() != null && castOther.getDateB() != null && this
						.getDateB().equals(castOther.getDateB())))
				&& ((this.getUpdateMan() == castOther.getUpdateMan()) || (this
						.getUpdateMan() != null
						&& castOther.getUpdateMan() != null && this
						.getUpdateMan().equals(castOther.getUpdateMan())))
				&& ((this.getDateUpdate() == castOther.getDateUpdate()) || (this
						.getDateUpdate() != null
						&& castOther.getDateUpdate() != null && this
						.getDateUpdate().equals(castOther.getDateUpdate())))
				&& ((this.getLunit() == castOther.getLunit()) || (this
						.getLunit() != null && castOther.getLunit() != null && this
						.getLunit().equals(castOther.getLunit())))
				&& ((this.getClRate() == castOther.getClRate()) || (this
						.getClRate() != null && castOther.getClRate() != null && this
						.getClRate().equals(castOther.getClRate())))
				&& ((this.getYyMk() == castOther.getYyMk()) || (this.getYyMk() != null
						&& castOther.getYyMk() != null && this.getYyMk()
						.equals(castOther.getYyMk())))
				&& ((this.getFormalMk() == castOther.getFormalMk()) || (this
						.getFormalMk() != null
						&& castOther.getFormalMk() != null && this
						.getFormalMk().equals(castOther.getFormalMk())))
				&& ((this.getFromMk() == castOther.getFromMk()) || (this
						.getFromMk() != null && castOther.getFromMk() != null && this
						.getFromMk().equals(castOther.getFromMk())))
				&& ((this.getGwMk() == castOther.getGwMk()) || (this.getGwMk() != null
						&& castOther.getGwMk() != null && this.getGwMk()
						.equals(castOther.getGwMk())))
				&& ((this.getUnitWeit() == castOther.getUnitWeit()) || (this
						.getUnitWeit() != null
						&& castOther.getUnitWeit() != null && this
						.getUnitWeit().equals(castOther.getUnitWeit())))
				&& ((this.getRateWaste() == castOther.getRateWaste()) || (this
						.getRateWaste() != null
						&& castOther.getRateWaste() != null && this
						.getRateWaste().equals(castOther.getRateWaste())))
				&& ((this.getItemMat() == castOther.getItemMat()) || (this
						.getItemMat() != null && castOther.getItemMat() != null && this
						.getItemMat().equals(castOther.getItemMat())))
				&& ((this.getSmatUnit() == castOther.getSmatUnit()) || (this
						.getSmatUnit() != null
						&& castOther.getSmatUnit() != null && this
						.getSmatUnit().equals(castOther.getSmatUnit())))
				&& ((this.getSmatRate() == castOther.getSmatRate()) || (this
						.getSmatRate() != null
						&& castOther.getSmatRate() != null && this
						.getSmatRate().equals(castOther.getSmatRate())))
				&& ((this.getGwTypemk() == castOther.getGwTypemk()) || (this
						.getGwTypemk() != null
						&& castOther.getGwTypemk() != null && this
						.getGwTypemk().equals(castOther.getGwTypemk())))
				&& ((this.getAreaMk1() == castOther.getAreaMk1()) || (this
						.getAreaMk1() != null && castOther.getAreaMk1() != null && this
						.getAreaMk1().equals(castOther.getAreaMk1())))
				&& ((this.getAreaMk2() == castOther.getAreaMk2()) || (this
						.getAreaMk2() != null && castOther.getAreaMk2() != null && this
						.getAreaMk2().equals(castOther.getAreaMk2())))
				&& ((this.getAreaMk3() == castOther.getAreaMk3()) || (this
						.getAreaMk3() != null && castOther.getAreaMk3() != null && this
						.getAreaMk3().equals(castOther.getAreaMk3())))
				&& ((this.getUseMk() == castOther.getUseMk()) || (this
						.getUseMk() != null && castOther.getUseMk() != null && this
						.getUseMk().equals(castOther.getUseMk())))
				&& ((this.getDateStop() == castOther.getDateStop()) || (this
						.getDateStop() != null
						&& castOther.getDateStop() != null && this
						.getDateStop().equals(castOther.getDateStop())))
				&& ((this.getCostMk() == castOther.getCostMk()) || (this
						.getCostMk() != null && castOther.getCostMk() != null && this
						.getCostMk().equals(castOther.getCostMk())))
				&& ((this.getMatNew() == castOther.getMatNew()) || (this
						.getMatNew() != null && castOther.getMatNew() != null && this
						.getMatNew().equals(castOther.getMatNew())))
				&& ((this.getMatMk() == castOther.getMatMk()) || (this
						.getMatMk() != null && castOther.getMatMk() != null && this
						.getMatMk().equals(castOther.getMatMk())))
				&& ((this.getTypeBno() == castOther.getTypeBno()) || (this
						.getTypeBno() != null && castOther.getTypeBno() != null && this
						.getTypeBno().equals(castOther.getTypeBno())))
				&& ((this.getTypeMno() == castOther.getTypeMno()) || (this
						.getTypeMno() != null && castOther.getTypeMno() != null && this
						.getTypeMno().equals(castOther.getTypeMno())))
				&& ((this.getTypeSno() == castOther.getTypeSno()) || (this
						.getTypeSno() != null && castOther.getTypeSno() != null && this
						.getTypeSno().equals(castOther.getTypeSno())))
				&& ((this.getASize() == castOther.getASize()) || (this
						.getASize() != null && castOther.getASize() != null && this
						.getASize().equals(castOther.getASize())))
				&& ((this.getBSize() == castOther.getBSize()) || (this
						.getBSize() != null && castOther.getBSize() != null && this
						.getBSize().equals(castOther.getBSize())))
				&& ((this.getHSize() == castOther.getHSize()) || (this
						.getHSize() != null && castOther.getHSize() != null && this
						.getHSize().equals(castOther.getHSize())))
				&& ((this.getWeit() == castOther.getWeit()) || (this.getWeit() != null
						&& castOther.getWeit() != null && this.getWeit()
						.equals(castOther.getWeit())))
				&& ((this.getBsmatNo() == castOther.getBsmatNo()) || (this
						.getBsmatNo() != null && castOther.getBsmatNo() != null && this
						.getBsmatNo().equals(castOther.getBsmatNo())))
				&& ((this.getBsmatName() == castOther.getBsmatName()) || (this
						.getBsmatName() != null
						&& castOther.getBsmatName() != null && this
						.getBsmatName().equals(castOther.getBsmatName())))
				&& ((this.getMatType2() == castOther.getMatType2()) || (this
						.getMatType2() != null
						&& castOther.getMatType2() != null && this
						.getMatType2().equals(castOther.getMatType2())))
				&& ((this.getCompNo() == castOther.getCompNo()) || (this
						.getCompNo() != null && castOther.getCompNo() != null && this
						.getCompNo().equals(castOther.getCompNo())))
				&& ((this.getCompNo1() == castOther.getCompNo1()) || (this
						.getCompNo1() != null && castOther.getCompNo1() != null && this
						.getCompNo1().equals(castOther.getCompNo1())))
				&& ((this.getGxMk() == castOther.getGxMk()) || (this.getGxMk() != null
						&& castOther.getGxMk() != null && this.getGxMk()
						.equals(castOther.getGxMk())))
				&& ((this.getZbMk() == castOther.getZbMk()) || (this.getZbMk() != null
						&& castOther.getZbMk() != null && this.getZbMk()
						.equals(castOther.getZbMk())))
				&& ((this.getFactMk() == castOther.getFactMk()) || (this
						.getFactMk() != null && castOther.getFactMk() != null && this
						.getFactMk().equals(castOther.getFactMk())))
				&& ((this.getDateBefroe() == castOther.getDateBefroe()) || (this
						.getDateBefroe() != null
						&& castOther.getDateBefroe() != null && this
						.getDateBefroe().equals(castOther.getDateBefroe())))
				&& ((this.getDateMax() == castOther.getDateMax()) || (this
						.getDateMax() != null && castOther.getDateMax() != null && this
						.getDateMax().equals(castOther.getDateMax())))
				&& ((this.getCbzbMk() == castOther.getCbzbMk()) || (this
						.getCbzbMk() != null && castOther.getCbzbMk() != null && this
						.getCbzbMk().equals(castOther.getCbzbMk())))
				&& ((this.getMatType3() == castOther.getMatType3()) || (this
						.getMatType3() != null
						&& castOther.getMatType3() != null && this
						.getMatType3().equals(castOther.getMatType3())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getMatNo() == null ? 0 : this.getMatNo().hashCode());
		result = 37 * result
				+ (getMatCname() == null ? 0 : this.getMatCname().hashCode());
		result = 37 * result
				+ (getMatEname() == null ? 0 : this.getMatEname().hashCode());
		result = 37 * result
				+ (getMatType() == null ? 0 : this.getMatType().hashCode());
		result = 37 * result
				+ (getMatSize() == null ? 0 : this.getMatSize().hashCode());
		result = 37 * result
				+ (getColor() == null ? 0 : this.getColor().hashCode());
		result = 37 * result
				+ (getAcctNo() == null ? 0 : this.getAcctNo().hashCode());
		result = 37 * result
				+ (getPolicy() == null ? 0 : this.getPolicy().hashCode());
		result = 37 * result
				+ (getPurPrice() == null ? 0 : this.getPurPrice().hashCode());
		result = 37 * result
				+ (getDateUse() == null ? 0 : this.getDateUse().hashCode());
		result = 37 * result
				+ (getSaftQty() == null ? 0 : this.getSaftQty().hashCode());
		result = 37 * result
				+ (getStockNo() == null ? 0 : this.getStockNo().hashCode());
		result = 37 * result
				+ (getCunit() == null ? 0 : this.getCunit().hashCode());
		result = 37 * result
				+ (getEunit() == null ? 0 : this.getEunit().hashCode());
		result = 37 * result
				+ (getPunit() == null ? 0 : this.getPunit().hashCode());
		result = 37 * result
				+ (getSmatNo() == null ? 0 : this.getSmatNo().hashCode());
		result = 37 * result
				+ (getSmatName() == null ? 0 : this.getSmatName().hashCode());
		result = 37 * result
				+ (getSmatWeit() == null ? 0 : this.getSmatWeit().hashCode());
		result = 37 * result
				+ (getBuilder() == null ? 0 : this.getBuilder().hashCode());
		result = 37 * result
				+ (getDateB() == null ? 0 : this.getDateB().hashCode());
		result = 37 * result
				+ (getUpdateMan() == null ? 0 : this.getUpdateMan().hashCode());
		result = 37
				* result
				+ (getDateUpdate() == null ? 0 : this.getDateUpdate()
						.hashCode());
		result = 37 * result
				+ (getLunit() == null ? 0 : this.getLunit().hashCode());
		result = 37 * result
				+ (getClRate() == null ? 0 : this.getClRate().hashCode());
		result = 37 * result
				+ (getYyMk() == null ? 0 : this.getYyMk().hashCode());
		result = 37 * result
				+ (getFormalMk() == null ? 0 : this.getFormalMk().hashCode());
		result = 37 * result
				+ (getFromMk() == null ? 0 : this.getFromMk().hashCode());
		result = 37 * result
				+ (getGwMk() == null ? 0 : this.getGwMk().hashCode());
		result = 37 * result
				+ (getUnitWeit() == null ? 0 : this.getUnitWeit().hashCode());
		result = 37 * result
				+ (getRateWaste() == null ? 0 : this.getRateWaste().hashCode());
		result = 37 * result
				+ (getItemMat() == null ? 0 : this.getItemMat().hashCode());
		result = 37 * result
				+ (getSmatUnit() == null ? 0 : this.getSmatUnit().hashCode());
		result = 37 * result
				+ (getSmatRate() == null ? 0 : this.getSmatRate().hashCode());
		result = 37 * result
				+ (getGwTypemk() == null ? 0 : this.getGwTypemk().hashCode());
		result = 37 * result
				+ (getAreaMk1() == null ? 0 : this.getAreaMk1().hashCode());
		result = 37 * result
				+ (getAreaMk2() == null ? 0 : this.getAreaMk2().hashCode());
		result = 37 * result
				+ (getAreaMk3() == null ? 0 : this.getAreaMk3().hashCode());
		result = 37 * result
				+ (getUseMk() == null ? 0 : this.getUseMk().hashCode());
		result = 37 * result
				+ (getDateStop() == null ? 0 : this.getDateStop().hashCode());
		result = 37 * result
				+ (getCostMk() == null ? 0 : this.getCostMk().hashCode());
		result = 37 * result
				+ (getMatNew() == null ? 0 : this.getMatNew().hashCode());
		result = 37 * result
				+ (getMatMk() == null ? 0 : this.getMatMk().hashCode());
		result = 37 * result
				+ (getTypeBno() == null ? 0 : this.getTypeBno().hashCode());
		result = 37 * result
				+ (getTypeMno() == null ? 0 : this.getTypeMno().hashCode());
		result = 37 * result
				+ (getTypeSno() == null ? 0 : this.getTypeSno().hashCode());
		result = 37 * result
				+ (getASize() == null ? 0 : this.getASize().hashCode());
		result = 37 * result
				+ (getBSize() == null ? 0 : this.getBSize().hashCode());
		result = 37 * result
				+ (getHSize() == null ? 0 : this.getHSize().hashCode());
		result = 37 * result
				+ (getWeit() == null ? 0 : this.getWeit().hashCode());
		result = 37 * result
				+ (getBsmatNo() == null ? 0 : this.getBsmatNo().hashCode());
		result = 37 * result
				+ (getBsmatName() == null ? 0 : this.getBsmatName().hashCode());
		result = 37 * result
				+ (getMatType2() == null ? 0 : this.getMatType2().hashCode());
		result = 37 * result
				+ (getCompNo() == null ? 0 : this.getCompNo().hashCode());
		result = 37 * result
				+ (getCompNo1() == null ? 0 : this.getCompNo1().hashCode());
		result = 37 * result
				+ (getGxMk() == null ? 0 : this.getGxMk().hashCode());
		result = 37 * result
				+ (getZbMk() == null ? 0 : this.getZbMk().hashCode());
		result = 37 * result
				+ (getFactMk() == null ? 0 : this.getFactMk().hashCode());
		result = 37
				* result
				+ (getDateBefroe() == null ? 0 : this.getDateBefroe()
						.hashCode());
		result = 37 * result
				+ (getDateMax() == null ? 0 : this.getDateMax().hashCode());
		result = 37 * result
				+ (getCbzbMk() == null ? 0 : this.getCbzbMk().hashCode());
		result = 37 * result
				+ (getMatType3() == null ? 0 : this.getMatType3().hashCode());
		return result;
	}

}