package dao;

import entity.VWebstore;

public interface IVWebStoreDao {
	public VWebstore findById(String factNo,String factCode,String yymm);

}
