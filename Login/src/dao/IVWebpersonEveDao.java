package dao;

import entity.VWebpersonEve;

public interface IVWebpersonEveDao {
	public VWebpersonEve findById(String factNo,String factCode,String yymm);

}
