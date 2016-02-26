package services;

import java.util.Date;
import java.util.List;

import util.PageBean;
import entity.KyzExpectmatmLog;
import entity.Webbackfeed;
import entity.WebbackfeedId;

public interface IWebBackFeedServices {
	public void add(Webbackfeed feed);

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm,String yymm2);

	public List<Webbackfeed> findByFactNo(String factNo);

	public Webbackfeed findById(WebbackfeedId id);

	public void delete(WebbackfeedId id,KyzExpectmatmLog delLog);
	public boolean check(String factNo,String factCode,String yymm);
	public Webbackfeed findById(String factNo,String factCode,Date yymm);
	public List<Webbackfeed> findByAny(String factNo,String beginDate,String endDate);

}
