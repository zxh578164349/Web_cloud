package dao;

import entity.VWebsideEve;

public interface IVWebsideEveDao {
	public VWebsideEve findById(String factNo,String factCode,String yymm);

}
