package dao;

import java.util.List;

import util.PageBean;
import entity.WebTabpom;

public interface IWebTabpomDao {
	public void add(WebTabpom tabpom);
	public WebTabpom findById(String pomNo);
	public void delete(WebTabpom tabpom);
	public void delete(String pomNo);
	public PageBean findPageBean(int pageSize,int page,String pomName,String brank,String yymm,String yymm2);
	
	public String findPomNoById(String pomNo);
	public List<String> findPomNos(String component,String tabpomDate);
	public List<WebTabpom> findByAny(String pomName,String brank,String yymm,String yymm2);
 
}
