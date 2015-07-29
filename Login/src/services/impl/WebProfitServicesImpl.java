package services.impl;

import java.util.List;
import entity.*;
import dao.*;
import services.*;
import util.PageBean;

public class WebProfitServicesImpl implements IWebProfitServices {
	private IWebProfitDao profitDao;

	public void setProfitDao(IWebProfitDao profitDao) {
		this.profitDao = profitDao;
	}

	public void addProfit(WebProfit profit) {
		// TODO Auto-generated method stub
		profitDao.addProfit(profit);
	}

	public List<WebProfit> selProfit(String factNo, String yymm, int page,
			int rows) {
		return profitDao.selectProfit(factNo, yymm, page, rows);
	}

	public int totlePage(String factNo, String yymm) {
		return profitDao.totlePage(factNo, yymm);
	}

	public WebProfit findById(WebProfitId id) {
		// TODO Auto-generated method stub
		return profitDao.findById(id);
	}

	public List<WebProfit> selByYymm(String yymm, String factNo) {
		return profitDao.selByYymm(yymm, factNo);
	}

	public void delete(WebProfitId id) {
		// TODO Auto-generated method stub
		profitDao.delete(id);

	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		return profitDao.findPageBean(pageSize, page, factNo, yymm);
	}

}
