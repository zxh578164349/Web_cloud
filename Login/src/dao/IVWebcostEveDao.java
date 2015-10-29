package dao;

import entity.VWebcostEve;

public interface IVWebcostEveDao {
	public VWebcostEve findById(String factNo,String factCode,String yymm);

}
