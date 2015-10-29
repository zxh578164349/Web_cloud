package dao;

import entity.VWebgw;

public interface IVWebGwDao {
	public VWebgw findById(String factNo,String factCode,String yymm);

}
