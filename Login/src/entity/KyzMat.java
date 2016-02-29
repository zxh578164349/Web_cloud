package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * KyzMat entity. @author MyEclipse Persistence Tools
 */

public class KyzMat implements java.io.Serializable {

	// Fields

	private String matNo;//物料編號
	private String matCname;//物料中文名稱
	private String matEname;//物料英文名稱
	private String matType;//資材分類
	private String matSize;//物料規格
	private String color;//物料顏色
	private String acctNo;//會計科目
	private String policy;//采購政策
	private Double purPrice;//采購單價
	private Double dateUse;//使用期限
	private Double saftQty;//安全庫存
	private String stockNo;//倉儲代號
	private String cunit;//中文單位
	private String eunit;//英文單位
	private String punit;//幣別
	private String smatNo;//商品代號
	private String smatName;//商品名稱
	private Double smatWeit;//商品單重
	private String builder;//資料建構人  (隱藏)
	private String dateB;//資料建構時間   (隱藏)
	private String updateMan;//異動人      (隱藏)
	private String dateUpdate;//異動時間    (隱藏)
	private String lunit;//領用單位
	private Double clRate;//領用換算率
	private String yyMk;//PCN  (隱藏)
	private String formalMk;//正式料號
	private String fromMk;//料號來源
	private String gwMk;//關務帳
	private String unitWeit;//重量單位
	private Double rateWaste;//海關損耗率
	//以下都隱藏(除了大，中，小分類)
	private String itemMat;
	private String smatUnit;
	private Double smatRate;
	private String gwTypemk;
	private String areaMk1;
	private String areaMk2;
	private String areaMk3;
	private String useMk;//使用註記
	private String dateStop;//禁用日期
	private String costMk;
	private String matNew;
	private String matMk;
	private String typeBno;//大分類
	private String typeMno;//中分類
	private String typeSno;//小分類
	private String ASize;
	private String BSize;
	private String HSize;
	private Double weit;//包重量
	private String bsmatNo;//商品代號
	private String bsmatName;//商品名稱
	private String matType2;//WL 物料,YL原料
	private String compNo;//公司別
	private String compNo1;//公司別
	private String gxMk;//632與634共用注記
	private String zbMk;//準備禁用
	private String factMk;//分廠別顯示物料
	private String dateBefroe;//前一次收貨日期
	private String dateMax;//最新的收貨日期
	private String cbzbMk;//成本轉檔注記
	private String matType3;
	private String factNo;//廠別
	private List<SubKyzmat>subKyzmats=new ArrayList<SubKyzmat>();

	// Constructors

	/** default constructor */
	public KyzMat() {
	}

	/** minimal constructor */
	public KyzMat(String matNo) {
		this.matNo = matNo;
	}

	/** full constructor */
	public KyzMat(String matNo, String matCname, String matEname,
			String matType, String matSize, String color, String acctNo,
			String policy, Double purPrice, Double dateUse, Double saftQty,
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
			String cbzbMk, String matType3,String factNo,List<SubKyzmat>subKyzmats) {
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
		this.factNo=factNo;
		this.subKyzmats=subKyzmats;
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

	

	public Double getDateUse() {
		return dateUse;
	}

	public void setDateUse(Double dateUse) {
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

	public String getFactNo() {
		return factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public List<SubKyzmat> getSubKyzmats() {
		return subKyzmats;
	}

	public void setSubKyzmats(List<SubKyzmat> subKyzmats) {
		this.subKyzmats = subKyzmats;
	}
	


	

}