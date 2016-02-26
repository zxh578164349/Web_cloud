package services;

import java.util.Date;
import java.util.List;

import util.PageBean;

import entity.KyzExpectmatmLog;
import entity.Webmix2;
import entity.Webscrapt;
import entity.WebscraptId;

public interface IWebScraptServices {
	// �s�W
	public void addWebScraptDao(Webscrapt scrapt);

	// �ھڱ��d�߼ƾ�
	public List<Webscrapt> selectScrapt(String factNo, String yymm, int page,
			int rows);

	// �d�ߥ����ƾڪ��`����
	public int totlePage(String factNo, String yymm);

	public Webscrapt findById(WebscraptId id);

	public List<Webscrapt> selByYymm(String yymm, String factNo);

	public void delete(WebscraptId id,KyzExpectmatmLog delLog);

	public Webscrapt selBycan(String factNo, Date date, String factCode);
	public boolean check(String factNo,String factCode,String yymm);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm,String yymm2);
	public List<Webscrapt> findByAny(String factNo,String beginDate,String endDate);
	public Webscrapt findById(String factNo,String factCode,String yymm);
}
