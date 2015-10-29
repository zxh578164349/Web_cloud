package dao;

import entity.VKpifactEve;

public interface IVKpifactEveDao {
	public VKpifactEve findById(String factNo,String factCode,String yymm);

}
