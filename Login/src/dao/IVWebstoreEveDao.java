package dao;

import entity.VWebstoreEve;

public interface IVWebstoreEveDao {
	public VWebstoreEve findById(String factNo,String factCode,String yymm);

}
