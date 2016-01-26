package dao;

import java.util.Map;

import util.PageBean;
import entity.WebPhonebook;

public interface IWebPhonebookDao {
	public void add(WebPhonebook phone);
	public PageBean findPageBean(int pageSize,int page,String factNo,String department,String post,String userName);
	public WebPhonebook findById(long pbId);
	public void delete(long pbId);
	public void addLarge(Map<String,Object>map,String username);

}
