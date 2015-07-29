package dao;

import entity.VWebmachine;

public interface IVWebmachineDao {
	public VWebmachine findById(String factNo,String factCode,String yymm);

}
