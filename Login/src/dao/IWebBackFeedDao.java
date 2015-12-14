package dao;

import java.util.Date;
import java.util.List;

import util.PageBean;
import entity.*;

public interface IWebBackFeedDao {
	public void add(Webbackfeed feed);

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm,String yymm2);

	public List<Webbackfeed> findByFactNo(String factNo);

	public Webbackfeed findById(WebbackfeedId id);

	public void delete(WebbackfeedId id);
	public Webbackfeed findById(String factNo,String factCode,Date yymm);
	public List<Webbackfeed> findByAny(String factNo,String beginDate,String endDate);
	public Webbackfeed findById(String factNo,String factCode,String yymm);
	

}
