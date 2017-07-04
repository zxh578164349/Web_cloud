package dao;

import java.util.List;

import util.PageBean;
import entity.KyzExpectmatmLog;
import entity.WebTabpom;

public interface IWebTabpomDao {
	public void add(WebTabpom tabpom);
	public WebTabpom findById(String pomNo);
	public void delete(WebTabpom tabpom,KyzExpectmatmLog delLog);
	public void delete(String pomNo,KyzExpectmatmLog delLog);
	public PageBean findPageBean(int pageSize,int page,String pomNo,String brank,String yymm,String yymm2,String factNo);
	
	public String findPomNoById(String pomNo);
	public List<String> findPomNos(String brank,String tabpomDate);
	public List<WebTabpom> findByAny(String pomName,String brank,String yymm,String yymm2);
 
}
