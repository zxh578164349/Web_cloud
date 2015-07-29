package dao;

import java.util.List;

import entity.VKpifact;

public interface IVKpifactDao {
	public List<VKpifact> findAll(String factNo,String yymm);
	public VKpifact findById(String factNo,String factCode,String yymm);

}
