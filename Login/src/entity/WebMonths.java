package entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * WebMonths entity. @author MyEclipse Persistence Tools
 */

public class WebMonths implements java.io.Serializable {

	// Fields

	private WebMonthsId id;
	private List webTestmouldregistrationforms = new ArrayList();
	private List webMaterialregistrationforms = new ArrayList();
	private List webSampleschedules = new ArrayList();

	// Constructors

	/** default constructor */
	public WebMonths() {
	}

	/** minimal constructor */
	public WebMonths(WebMonthsId id) {
		this.id = id;
	}

	/** full constructor */
	public WebMonths(WebMonthsId id, List webTestmouldregistrationforms,
			List webMaterialregistrationforms, List webSampleschedules) {
		this.id = id;
		this.webTestmouldregistrationforms = webTestmouldregistrationforms;
		this.webMaterialregistrationforms = webMaterialregistrationforms;
		this.webSampleschedules = webSampleschedules;
	}

	// Property accessors

	public WebMonthsId getId() {
		return this.id;
	}

	public void setId(WebMonthsId id) {
		this.id = id;
	}

	public List getWebTestmouldregistrationforms() {
		return webTestmouldregistrationforms;
	}

	public void setWebTestmouldregistrationforms(List webTestmouldregistrationforms) {
		this.webTestmouldregistrationforms = webTestmouldregistrationforms;
	}

	public List getWebMaterialregistrationforms() {
		return webMaterialregistrationforms;
	}

	public void setWebMaterialregistrationforms(List webMaterialregistrationforms) {
		this.webMaterialregistrationforms = webMaterialregistrationforms;
	}

	public List getWebSampleschedules() {
		return webSampleschedules;
	}

	public void setWebSampleschedules(List webSampleschedules) {
		this.webSampleschedules = webSampleschedules;
	}

	

}