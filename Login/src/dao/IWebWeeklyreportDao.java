package dao;

import util.PageBean;
import entity.WebWeeklyreport;

public interface IWebWeeklyreportDao {
	public void add(WebWeeklyreport obj);
	public PageBean findPageBean(int page,int pageSize,int uid,String createDate);
	public WebWeeklyreport findById(int rid);
	

}
