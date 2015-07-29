package dao;

import entity.VWebcost;

public interface IVWebcostDao {
	public VWebcost findById(String factNo,String factCode,String yymm);

}
