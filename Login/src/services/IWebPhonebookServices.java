package services;

import util.PageBean;
import entity.WebPhonebook;

public interface IWebPhonebookServices {
	public void add(WebPhonebook phone);
	public PageBean findPageBean(int pageSize,int page,String factNo,String department,String post,String userName);
	public WebPhonebook findById(long pbId);
	public void delete(long pbId);

}
