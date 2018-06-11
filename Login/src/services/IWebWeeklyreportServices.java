package services;

import java.util.List;

import util.PageBean;
import entity.KyzExpectmatmLog;
import entity.WebWeeklyreport;

public interface IWebWeeklyreportServices {
	public void add(WebWeeklyreport obj);
	public PageBean findPageBean(int page,int pageSize,int uid,String sdate,int bid);
	public WebWeeklyreport findByUidASdateABid(int uid, String sdate,int bid);
	public WebWeeklyreport findById(int rid);
	public void delete(int rid,KyzExpectmatmLog log);
	public List<WebWeeklyreport> findOneATwo(int uid,int bid,String sdate,String sdate_last);
	public List<WebWeeklyreport> findByEdate(String sdate); 
	public List<WebWeeklyreport> findByEdate(String sdate,String sdate2); 
	

}
