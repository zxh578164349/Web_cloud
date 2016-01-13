package dao;

import java.util.List;

import entity.WebTabpomfile;

public interface IWebTabpomfileDao {
	public WebTabpomfile findById(String pomNo,String fileName);
	public void delete(String pomNo,String fileName);
	public List<WebTabpomfile>findByPomNo(String pomNo);

}
