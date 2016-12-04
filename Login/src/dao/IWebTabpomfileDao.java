package dao;

import java.util.List;

import entity.KyzExpectmatmLog;
import entity.WebTabpomfile;

public interface IWebTabpomfileDao {
	public WebTabpomfile findById(String pomNo,String fileName);
	public void delete(String pomNo,String fileName);
	public List<Object[]>findByPomNo(String pomNo);
	public void add(WebTabpomfile file);

}
