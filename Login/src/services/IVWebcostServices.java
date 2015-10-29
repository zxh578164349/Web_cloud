package services;

import entity.VWebcost;

public interface IVWebcostServices {
	public VWebcost findById(String factNo,String factCode,String yymm);

}
