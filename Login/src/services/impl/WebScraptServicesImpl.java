package services.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dao.IWebScraptDao;

import entity.Webscrapt;
import entity.WebscraptId;
import services.IWebScraptServices;
import util.PageBean;

public class WebScraptServicesImpl implements IWebScraptServices {

	private IWebScraptDao scraptDao;

	public IWebScraptDao getScraptDao() {
		return scraptDao;
	}

	public void setScraptDao(IWebScraptDao scraptDao) {
		this.scraptDao = scraptDao;
	}

	public void addWebScraptDao(Webscrapt scrapt) {
		scraptDao.addWebScraptDao(scrapt);
	}

	public List<Webscrapt> selectScrapt(String factNo, String yymm, int page,
			int rows) {
		return scraptDao.selectScrapt(factNo, yymm, page, rows);
	}

	public int totlePage(String factNo, String yymm) {
		return scraptDao.totlePage(factNo, yymm);
	}

	public Webscrapt findById(WebscraptId id) {
		return scraptDao.findById(id);
	}

	public List<Webscrapt> selByYymm(String yymm, String factNo) {
		return scraptDao.selByYymm(yymm, factNo);
	}

	public void delete(WebscraptId id) {
		scraptDao.delete(id);
	}

	public Webscrapt selBycan(String factNo, Date date, String factCode) {
		// TODO Auto-generated method stub
		return scraptDao.selBycan(factNo, date, factCode);
	}

	public boolean check(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		Boolean flag=false;
		DateFormat format=new SimpleDateFormat("yyyyMM");
		Date date=null;
		try {
			date=format.parse(yymm);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Webscrapt scrapt=this.selBycan(factNo, date, factCode);
		if(scrapt!=null){
			flag=true;
		}
		return flag;
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		return scraptDao.findPageBean(pageSize, page, factNo, yymm);
	}

	public List<Webscrapt> findByAny(String factNo, String beginDate,
			String endDate) {
		// TODO Auto-generated method stub
		return scraptDao.findByAny(factNo, beginDate, endDate);
	}

	public Webscrapt findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		return scraptDao.findById(factNo, factCode, yymm);
	}

}
