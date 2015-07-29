package entity;

import java.math.BigDecimal;

/**
 * VSumstore entity. @author MyEclipse Persistence Tools
 */

public class VSumstore implements java.io.Serializable {

	// Fields

	private VSumstoreId id;
	private BigDecimal storeB001;
	private BigDecimal storeB002;
	private BigDecimal storeB003;
	private BigDecimal storeB005;
	private BigDecimal storeB006;
	private BigDecimal storeB007;
	private BigDecimal storeB008;

	// Constructors

	/** default constructor */
	public VSumstore() {
	}

	/** minimal constructor */
	public VSumstore(VSumstoreId id) {
		this.id = id;
	}

	/** full constructor */
	public VSumstore(VSumstoreId id, BigDecimal storeB001,
			BigDecimal storeB002, BigDecimal storeB003, BigDecimal storeB005,
			BigDecimal storeB006, BigDecimal storeB007, BigDecimal storeB008) {
		this.id = id;
		this.storeB001 = storeB001;
		this.storeB002 = storeB002;
		this.storeB003 = storeB003;
		this.storeB005 = storeB005;
		this.storeB006 = storeB006;
		this.storeB007 = storeB007;
		this.storeB008 = storeB008;
	}

	// Property accessors

	public VSumstoreId getId() {
		return this.id;
	}

	public void setId(VSumstoreId id) {
		this.id = id;
	}

	public BigDecimal getStoreB001() {
		return this.storeB001;
	}

	public void setStoreB001(BigDecimal storeB001) {
		this.storeB001 = storeB001;
	}

	public BigDecimal getStoreB002() {
		return this.storeB002;
	}

	public void setStoreB002(BigDecimal storeB002) {
		this.storeB002 = storeB002;
	}

	public BigDecimal getStoreB003() {
		return this.storeB003;
	}

	public void setStoreB003(BigDecimal storeB003) {
		this.storeB003 = storeB003;
	}

	public BigDecimal getStoreB005() {
		return this.storeB005;
	}

	public void setStoreB005(BigDecimal storeB005) {
		this.storeB005 = storeB005;
	}

	public BigDecimal getStoreB006() {
		return this.storeB006;
	}

	public void setStoreB006(BigDecimal storeB006) {
		this.storeB006 = storeB006;
	}

	public BigDecimal getStoreB007() {
		return this.storeB007;
	}

	public void setStoreB007(BigDecimal storeB007) {
		this.storeB007 = storeB007;
	}

	public BigDecimal getStoreB008() {
		return this.storeB008;
	}

	public void setStoreB008(BigDecimal storeB008) {
		this.storeB008 = storeB008;
	}

}