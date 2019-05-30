package entity;

import java.math.BigDecimal;

/**
 * VWeballobjasumwebyield2019 entity. @author MyEclipse Persistence Tools
 */

public class VWeballobjasumwebyield2019 implements java.io.Serializable {

	// Fields

	private VWeballobjasumwebyield2019Id id;
	private Double objA103;
	private Double objA105;
	private Double objA169;
	private Double objA171;
	private Double objA172;
	private Double objA173;
	private Double objA174;
	private Double objA175;
	private Double objA203;
	private Double objA204;
	private Double objA205;
	private Double objA206;
	private Double objA207;
	private Double objA208;
	private Double objA209;
	private Double objA210;
	private Double objA211;
	private Double objA212;
	private Double objA213;
	private Double objA214;
	private Double objA215;
	private Double sumWorkdays;
	private BigDecimal sumHostpairs;
	private BigDecimal sumFactpairs;
	private BigDecimal sumSamplepairs; 
	private WebFact factNo;

	// Constructors

	/** default constructor */
	public VWeballobjasumwebyield2019() {
	}

	/** minimal constructor */
	public VWeballobjasumwebyield2019(VWeballobjasumwebyield2019Id id) {
		this.id = id;
	}
	
	public VWeballobjasumwebyield2019(VWeballobjasumwebyield2019Id id,WebFact factNo) {
		this.id = id;
		this.factNo=factNo;
	}

	/** full constructor */
	public VWeballobjasumwebyield2019(VWeballobjasumwebyield2019Id id,
			Double objA103, Double objA105, Double objA169, Double objA171,
			Double objA172, Double objA173, Double objA174, Double objA175,
			Double objA203, Double objA204, Double objA205, Double objA206,
			Double objA207, Double objA208, Double objA209, Double objA210,
			Double objA211, Double objA212, Double objA213, Double objA214,
			Double objA215, BigDecimal sumHostpairs, BigDecimal sumFactpairs,
			BigDecimal sumSamplepairs) {
		this.id = id;
		this.objA103 = objA103;
		this.objA105 = objA105;
		this.objA169 = objA169;
		this.objA171 = objA171;
		this.objA172 = objA172;
		this.objA173 = objA173;
		this.objA174 = objA174;
		this.objA175 = objA175;
		this.objA203 = objA203;
		this.objA204 = objA204;
		this.objA205 = objA205;
		this.objA206 = objA206;
		this.objA207 = objA207;
		this.objA208 = objA208;
		this.objA209 = objA209;
		this.objA210 = objA210;
		this.objA211 = objA211;
		this.objA212 = objA212;
		this.objA213 = objA213;
		this.objA214 = objA214;
		this.objA215 = objA215;
		this.sumHostpairs = sumHostpairs;
		this.sumFactpairs = sumFactpairs;
		this.sumSamplepairs = sumSamplepairs;
	}

	// Property accessors

	public VWeballobjasumwebyield2019Id getId() {
		return this.id;
	}

	public void setId(VWeballobjasumwebyield2019Id id) {
		this.id = id;
	}

	public Double getObjA103() {
		return this.objA103;
	}

	public void setObjA103(Double objA103) {
		this.objA103 = objA103;
	}

	public Double getObjA105() {
		return this.objA105;
	}

	public void setObjA105(Double objA105) {
		this.objA105 = objA105;
	}

	public Double getObjA169() {
		return this.objA169;
	}

	public void setObjA169(Double objA169) {
		this.objA169 = objA169;
	}

	public Double getObjA171() {
		return this.objA171;
	}

	public void setObjA171(Double objA171) {
		this.objA171 = objA171;
	}

	public Double getObjA172() {
		return this.objA172;
	}

	public void setObjA172(Double objA172) {
		this.objA172 = objA172;
	}

	public Double getObjA173() {
		return this.objA173;
	}

	public void setObjA173(Double objA173) {
		this.objA173 = objA173;
	}

	public Double getObjA174() {
		return this.objA174;
	}

	public void setObjA174(Double objA174) {
		this.objA174 = objA174;
	}

	public Double getObjA175() {
		return this.objA175;
	}

	public void setObjA175(Double objA175) {
		this.objA175 = objA175;
	}

	public Double getObjA203() {
		return this.objA203;
	}

	public void setObjA203(Double objA203) {
		this.objA203 = objA203;
	}

	public Double getObjA204() {
		return this.objA204;
	}

	public void setObjA204(Double objA204) {
		this.objA204 = objA204;
	}

	public Double getObjA205() {
		return this.objA205;
	}

	public void setObjA205(Double objA205) {
		this.objA205 = objA205;
	}

	public Double getObjA206() {
		return this.objA206;
	}

	public void setObjA206(Double objA206) {
		this.objA206 = objA206;
	}

	public Double getObjA207() {
		return this.objA207;
	}

	public void setObjA207(Double objA207) {
		this.objA207 = objA207;
	}

	public Double getObjA208() {
		return this.objA208;
	}

	public void setObjA208(Double objA208) {
		this.objA208 = objA208;
	}

	public Double getObjA209() {
		return this.objA209;
	}

	public void setObjA209(Double objA209) {
		this.objA209 = objA209;
	}

	public Double getObjA210() {
		return this.objA210;
	}

	public void setObjA210(Double objA210) {
		this.objA210 = objA210;
	}

	public Double getObjA211() {
		return this.objA211;
	}

	public void setObjA211(Double objA211) {
		this.objA211 = objA211;
	}

	public Double getObjA212() {
		return this.objA212;
	}

	public void setObjA212(Double objA212) {
		this.objA212 = objA212;
	}

	public Double getObjA213() {
		return this.objA213;
	}

	public void setObjA213(Double objA213) {
		this.objA213 = objA213;
	}

	public Double getObjA214() {
		return this.objA214;
	}

	public void setObjA214(Double objA214) {
		this.objA214 = objA214;
	}

	public Double getObjA215() {
		return this.objA215;
	}

	public void setObjA215(Double objA215) {
		this.objA215 = objA215;
	}

	public BigDecimal getSumHostpairs() {
		return this.sumHostpairs;
	}

	public void setSumHostpairs(BigDecimal sumHostpairs) {
		this.sumHostpairs = sumHostpairs;
	}

	public BigDecimal getSumFactpairs() {
		return this.sumFactpairs;
	}

	public void setSumFactpairs(BigDecimal sumFactpairs) {
		this.sumFactpairs = sumFactpairs;
	}

	public BigDecimal getSumSamplepairs() {
		return this.sumSamplepairs;
	}

	public void setSumSamplepairs(BigDecimal sumSamplepairs) {
		this.sumSamplepairs = sumSamplepairs;
	}

	public Double getSumWorkdays() {
		return sumWorkdays;
	}

	public void setSumWorkdays(Double sumWorkdays) {
		this.sumWorkdays = sumWorkdays;
	}

	public WebFact getFactNo() {
		return factNo;
	}

	public void setFactNo(WebFact factNo) {
		this.factNo = factNo;
	}

	
	
	
	
	

}