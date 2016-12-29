package entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * WebUserOperation entity. @author MyEclipse Persistence Tools
 */

public class WebUserOperation implements java.io.Serializable{

	// Fields

	private Integer id;
	private String operationEname;
	private String operationCname;
	private List<WebOperationToUser>webOperationToUsers=new ArrayList<WebOperationToUser>();

	// Constructors

	/** default constructor */
	public WebUserOperation(){
	}

	/** minimal constructor */
	public WebUserOperation(Integer id){
		this.id=id;
	}

	/** full constructor */
	public WebUserOperation(Integer id,String operationEname,String operationCname,List<WebOperationToUser> webOperationToUsers){
		this.id=id;
		this.operationEname=operationEname;
		this.operationCname=operationCname;
		this.webOperationToUsers=webOperationToUsers;
	}

	// Property accessors

	public Integer getId(){
		return this.id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getOperationEname(){
		return this.operationEname;
	}

	public void setOperationEname(String operationEname){
		this.operationEname=operationEname;
	}

	public String getOperationCname(){
		return this.operationCname;
	}

	public void setOperationCname(String operationCname){
		this.operationCname=operationCname;
	}

	public List<WebOperationToUser> getWebOperationToUsers(){
		return webOperationToUsers;
	}

	public void setWebOperationToUsers(List<WebOperationToUser> webOperationToUsers){
		this.webOperationToUsers=webOperationToUsers;
	}

	

}