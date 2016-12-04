package services;

import java.util.List;

import util.PageBean;
import entity.KyzExpectmatmLog;
import entity.WebTabpom;

public interface IWebTabpomServices {
	public void add(WebTabpom tabpom);
	public WebTabpom findById(String pomNo);
	public void delete(WebTabpom tabpom,KyzExpectmatmLog delLog);
	public void delete(String pomNo,KyzExpectmatmLog delLog);
	public PageBean findPageBean(int pageSize,int page,String pomNo,String brank,String yymm,String yymm2);
	
	public String findPomNoById(String pomNo);
	public String makePomNo(String brank,String tabpomDate);
	public List<WebTabpom> findByAny(String pomName,String brank,String yymm,String yymm2);
	public List<String>findPomNos(String brank,String tabpomDate);

}
