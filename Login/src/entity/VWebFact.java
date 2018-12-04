package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * VWebFact entity. @author MyEclipse Persistence Tools
 */

public class VWebFact implements java.io.Serializable {

	// Fields

	private String factNo;
	private String factSname;
	private String orderNo;
	private String erpFactcode; 
	private List<WebPhonebook>phones;

	// Constructors

	/** default constructor */
	public VWebFact() {
	}

	/** minimal constructor */
	public VWebFact(String factNo) {
		this.factNo = factNo;
	}
	
	public VWebFact(String factNo,String factSname) {
		this.factNo = factNo;
		this.factSname=factSname;
	}

	/** full constructor */
	public VWebFact(String factNo, String factSname, String orderNo) {
		this.factNo = factNo;
		this.factSname = factSname;
		this.orderNo = orderNo;
	}

	// Property accessors

	public String getFactNo() {
		return this.factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public String getFactSname() {
		return this.factSname;
	}

	public void setFactSname(String factSname) {
		this.factSname = factSname;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public List<WebPhonebook> getPhones() {
		return phones;
	}

	public void setPhones(List<WebPhonebook> phones) {
		this.phones = phones;
	}

	public String getErpFactcode(){
		return erpFactcode;
	}

	public void setErpFactcode(String erpFactcode){
		this.erpFactcode=erpFactcode;
	}
	
	
	


	

}