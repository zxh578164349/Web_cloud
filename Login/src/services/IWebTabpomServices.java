package services;

import util.PageBean;
import entity.WebTabpom;

public interface IWebTabpomServices {
	public void add(WebTabpom tabpom);
	public WebTabpom findById(String pomNo);
	public void delete(WebTabpom tabpom);
	public void delete(String pomNo);
	public PageBean findPageBean(int pageSize,int page,String pomName,String brank);
	
	public String findPomNoById(String pomNo);
	public String makePomNo(String component,String tabpomDate);

}
