package entity;

/**
 * WebDistribution entity. @author MyEclipse Persistence Tools
 */

public class WebDistribution implements java.io.Serializable {

	// Fields

	private Integer usersub;
	private Integer usersubid;

	// Constructors

	/** default constructor */
	public WebDistribution() {
	}

	/** full constructor */
	public WebDistribution(Integer usersub, Integer usersubid) {
		this.usersub = usersub;
		this.usersubid = usersubid;
	}

	// Property accessors

	public Integer getUsersub() {
		return this.usersub;
	}

	public void setUsersub(Integer usersub) {
		this.usersub = usersub;
	}

	public Integer getUsersubid() {
		return this.usersubid;
	}

	public void setUsersubid(Integer usersubid) {
		this.usersubid = usersubid;
	}

}