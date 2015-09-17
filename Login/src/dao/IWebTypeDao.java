package dao;

import java.util.List;

import util.PageBean;

import entity.WebType;

public interface IWebTypeDao {
	public void add(WebType type);
	public PageBean findPageBean(int page,int pageSize,String factNo);
	public WebType findById(String factNo,String typeNo);
	public void delete(String factNo,String typeNo);
	public List<WebType>findByFactNo(String factNo);
	
	public String findTypeNameById(String factNo,String typeNo);
}
