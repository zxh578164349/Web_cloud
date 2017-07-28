package entity_temp;

/**
 * WebBrEstimatingitem entity. @author MyEclipse Persistence Tools
 */

public class WebBrEstimatingitemTemp implements java.io.Serializable{

	// Fields
	private String factCode;
	private String yymmdd;
	private Double actualPairs;
	private Double estimatingPairs1;
	private Double estimatingPairs2;
	private Double estimatingPairs3;

	// Constructors

	/** default constructor */
	public WebBrEstimatingitemTemp(){
	}

	/** minimal constructor */
	public WebBrEstimatingitemTemp(String factCode,String yymmdd){
		this.factCode=factCode;
		this.yymmdd=yymmdd;
	}
	
	public WebBrEstimatingitemTemp(String factCode,String yymmdd,Double actualPairs,Double estimatingPairs1,
			Double estimatingPairs2,Double estimatingPairs3){
		this.factCode=factCode;
		this.yymmdd=yymmdd;
		this.actualPairs=actualPairs;
		this.estimatingPairs1=estimatingPairs1;
		this.estimatingPairs2=estimatingPairs2;
		this.estimatingPairs3=estimatingPairs3;
		
	}

	

	// Property accessors

	

	public Double getActualPairs(){
		return this.actualPairs;
	}

	public String getFactCode(){
		return factCode;
	}

	public void setFactCode(String factCode){
		this.factCode=factCode;
	}

	public String getYymmdd(){
		return yymmdd;
	}

	public void setYymmdd(String yymmdd){
		this.yymmdd=yymmdd;
	}

	public void setActualPairs(Double actualPairs){
		this.actualPairs=actualPairs;
	}

	public Double getEstimatingPairs1(){
		return this.estimatingPairs1;
	}

	public void setEstimatingPairs1(Double estimatingPairs1){
		this.estimatingPairs1=estimatingPairs1;
	}

	public Double getEstimatingPairs2(){
		return this.estimatingPairs2;
	}

	public void setEstimatingPairs2(Double estimatingPairs2){
		this.estimatingPairs2=estimatingPairs2;
	}

	public Double getEstimatingPairs3(){
		return this.estimatingPairs3;
	}

	public void setEstimatingPairs3(Double estimatingPairs3){
		this.estimatingPairs3=estimatingPairs3;
	}

	

	

}