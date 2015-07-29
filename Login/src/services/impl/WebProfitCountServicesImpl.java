package services.impl;

import java.util.List;
import entity.*;
import dao.*;
import services.*;
import util.PageBean;

public class WebProfitCountServicesImpl implements IWebProfitCountServices {
	private IWebProfitCountDao profitcountDao;

	public void setProfitcountDao(IWebProfitCountDao profitcountDao) {
		this.profitcountDao = profitcountDao;
	}

	public void addProfitCount(WebProfitcount count) {
		// TODO Auto-generated method stub
		profitcountDao.addProfitCount(count);

	}

	public List<WebProfitcount> selProfitcount(String factNo, String yymm,
			int page, int rows) {
		return profitcountDao.selectProfitcount(factNo, yymm, page, rows);
	}

	public int totlePage(String factNo, String yymm) {
		return profitcountDao.totlePage(factNo, yymm);
	}

	public WebProfitcount findById(WebProfitcountId id) {
		return profitcountDao.findById(id);
	}

	public List<WebProfitcount> selByYymm(String yymm, String factNo) {
		return profitcountDao.selByYymm(yymm, factNo);
	}

	public void delete(WebProfitcountId id) {
		// TODO Auto-generated method stub
		profitcountDao.delete(id);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		return profitcountDao.findPageBean(pageSize, page, factNo, yymm);
	}

}
