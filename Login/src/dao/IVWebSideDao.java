package dao;

import entity.VWebside;

public interface IVWebSideDao {
	public VWebside findById(String factNo,String factCode,String yymm);

}
