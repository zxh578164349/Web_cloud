package dao;

import entity.VWebgwEve;

public interface IVWebgwEveDao {
	public VWebgwEve findById(String factNo,String factCode,String yymm);

}
