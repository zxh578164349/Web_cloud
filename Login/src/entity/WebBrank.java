package entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * WebBrank entity. @author MyEclipse Persistence Tools
 */

public class WebBrank implements java.io.Serializable {

	// Fields

	private String BNo;
	private String BName;
	private List<WebTabpom> webTabpoms = new ArrayList<WebTabpom>();

	// Constructors

	/** default constructor */
	public WebBrank() {
	}

	/** minimal constructor */
	public WebBrank(String BNo) {
		this.BNo = BNo;
	}

	/** full constructor */
	public WebBrank(String BNo, String BName, List<WebTabpom> webTabpoms) {
		this.BNo = BNo;
		this.BName = BName;
		this.webTabpoms = webTabpoms;
	}

	// Property accessors

	public String getBNo() {
		return this.BNo;
	}

	public void setBNo(String BNo) {
		this.BNo = BNo;
	}

	public String getBName() {
		return this.BName;
	}

	public void setBName(String BName) {
		this.BName = BName;
	}

	public List<WebTabpom> getWebTabpoms() {
		return webTabpoms;
	}

	public void setWebTabpoms(List<WebTabpom> webTabpoms) {
		this.webTabpoms = webTabpoms;
	}

    

}