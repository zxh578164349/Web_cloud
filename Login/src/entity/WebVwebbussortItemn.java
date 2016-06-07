package entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * WebVwebbussortItemn entity. @author MyEclipse Persistence Tools
 */

public class WebVwebbussortItemn implements java.io.Serializable {

	// Fields

	private Integer mid;
	private String itemname;
	private List<WebVwebussortSubitem> webVwebussortSubitems = new ArrayList<WebVwebussortSubitem>();

	// Constructors

	/** default constructor */
	public WebVwebbussortItemn() {
	}

	/** minimal constructor */
	public WebVwebbussortItemn(Integer mid) {
		this.mid = mid;
	}

	/** full constructor */
	public WebVwebbussortItemn(Integer mid, String itemname,
			List<WebVwebussortSubitem> webVwebussortSubitems) {
		this.mid = mid;
		this.itemname = itemname;
		this.webVwebussortSubitems = webVwebussortSubitems;
	}

	// Property accessors

	public Integer getMid() {
		return this.mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getItemname() {
		return this.itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public List<WebVwebussortSubitem> getWebVwebussortSubitems() {
		return webVwebussortSubitems;
	}

	public void setWebVwebussortSubitems(
			List<WebVwebussortSubitem> webVwebussortSubitems) {
		this.webVwebussortSubitems = webVwebussortSubitems;
	}

	

}