package dao;

import entity.VWebperson;

public interface IVWebpersonDao {
	public VWebperson findById(String factNo,String factCode,String yymm);

}
