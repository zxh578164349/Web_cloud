package entity;



/**
 * ViewCostandestpro entity. @author MyEclipse Persistence Tools
 */

public class ViewCostandestpro  implements java.io.Serializable {


    // Fields    

     private ViewCostandestproId id;
     private Double actlost;
     private Double hole;


    // Constructors

    /** default constructor */
    public ViewCostandestpro() {
    }
    
    public Double getActlost() {
		return actlost;
	}

	public void setActlost(Double actlost) {
		this.actlost = actlost;
	}

	public Double getHole() {
		return hole;
	}

	public void setHole(Double hole) {
		this.hole = hole;
	}

	/** minimal constructor */
	public ViewCostandestpro(ViewCostandestproId id) {
		this.id = id;
	}

    
    /** full constructor */
    public ViewCostandestpro(ViewCostandestproId id,Double actlost,Double hole) {
        this.id = id;
        this.actlost=actlost;
        this.hole=hole;
    }

   
    // Property accessors

    public ViewCostandestproId getId() {
        return this.id;
    }
    
    public void setId(ViewCostandestproId id) {
        this.id = id;
    }
   








}