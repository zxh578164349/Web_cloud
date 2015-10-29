package dao;

import entity.VWebmachineEve;

public interface IVWebmachineEveDao {
	public VWebmachineEve findById(String factNo,String factCode,String yymm);

}
