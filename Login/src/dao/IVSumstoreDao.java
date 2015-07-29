package dao;

import entity.VSumstore;

public interface IVSumstoreDao {
	public VSumstore findById(String factNo,String yymm);

}
