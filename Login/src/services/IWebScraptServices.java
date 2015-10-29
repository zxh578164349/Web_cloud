package services;

import java.util.Date;
import java.util.List;

import util.PageBean;

import entity.Webmix2;
import entity.Webscrapt;
import entity.WebscraptId;

public interface IWebScraptServices {
	// 穝糤
	public void addWebScraptDao(Webscrapt scrapt);

	// 沮兵ン琩高计沮
	public List<Webscrapt> selectScrapt(String factNo, String yymm, int page,
			int rows);

	// 琩高场计沮羆计
	public int totlePage(String factNo, String yymm);

	public Webscrapt findById(WebscraptId id);

	public List<Webscrapt> selByYymm(String yymm, String factNo);

	public void delete(WebscraptId id);

	public Webscrapt selBycan(String factNo, Date date, String factCode);
	public boolean check(String factNo,String factCode,String yymm);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);
	public List<Webscrapt> findByAny(String factNo,String beginDate,String endDate);
	public Webscrapt findById(String factNo,String factCode,String yymm);
}
