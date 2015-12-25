package services;

import java.util.List;

import util.PageBean;
import entity.WebTabpom;

public interface IWebTabpomServices {
	public void add(WebTabpom tabpom);
	public WebTabpom findById(String pomNo);
	public void delete(WebTabpom tabpom);
	public void delete(String pomNo);
	public PageBean findPageBean(int pageSize,int page,String pomName,String brank,String yymm,String yymm2);
	
	public String findPomNoById(String pomNo);
	public String makePomNo(String component,String tabpomDate);
	public List<WebTabpom> findByAny(String pomName,String brank,String yymm,String yymm2);

}
