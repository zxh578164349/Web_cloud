package services;

import java.util.List;
import java.util.Map;

import util.PageBean;
import entity.KyzExpectmatmLog;
import entity.WebPhonebook;

public interface IWebPhonebookServices {
	public void add(WebPhonebook phone);
	public PageBean findPageBean(int pageSize,int page,String factNo,String department,String post,String userName);
	public WebPhonebook findById(String factNo,String department,String post,String userName,String phoneA,String phoneB,String phoneC,String email );
	public void delete(String factNo,String department,String post,String userName,String phoneA,String phoneB,String phoneC,String email,KyzExpectmatmLog delLog);
	public void delete(WebPhonebook phone);
	public void addLarge(Map<String,Object>map,String username);
	public List<WebPhonebook> findToPrint(String factNo,String department,String post,String userName);
	public List<String>findDepartments(String factNo);
	public List<String>findPosts(String factNo);

}
