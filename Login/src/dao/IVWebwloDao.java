package dao;

import entity.VWebwlo;

public interface IVWebwloDao {
	public VWebwlo findById(String factNo,String factCode,String yymm);

}
