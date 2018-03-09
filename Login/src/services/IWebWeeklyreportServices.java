package services;

import util.PageBean;
import entity.WebWeeklyreport;

public interface IWebWeeklyreportServices {
	public void add(WebWeeklyreport obj);
	public PageBean findPageBean(int page,int pageSize,int uid,String createDate);

}
