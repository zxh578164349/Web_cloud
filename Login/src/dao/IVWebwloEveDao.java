package dao;

import entity.VWebwloEve;

public interface IVWebwloEveDao {
	public VWebwloEve findById(String factNo,String factCode,String yymm);

}
