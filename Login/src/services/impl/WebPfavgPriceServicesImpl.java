package services.impl;

import java.util.List;
import entity.*;
import dao.*;
import services.*;
import util.PageBean;

public class WebPfavgPriceServicesImpl implements IWebPfavgPriceServices {
	private IWebPfavgPriceDao pfaDao;

	public void setPfaDao(IWebPfavgPriceDao pfaDao) {
		this.pfaDao = pfaDao;
	}

	public void addprice(WebPfavgprice price) {
		// TODO Auto-generated method stub
		pfaDao.addAvgPrice(price);

	}

	public List<WebPfavgprice> selPfavgprice(String factNo, String yymm,
			int page, int rows) {
		return pfaDao.selectPfavgprice(factNo, yymm, page, rows);
	}

	public int totlePage(String factNo, String yymm) {
		return pfaDao.totlePage(factNo, yymm);
	}

	public WebPfavgprice findById(WebPfavgpriceId id) {
		return pfaDao.findById(id);
	}

	public List<WebPfavgprice> selByYymm(String yymm, String factNo) {
		return pfaDao.selByYymm(yymm, factNo);
	}

	public void delete(WebPfavgpriceId id) {
		// TODO Auto-generated method stub
		pfaDao.delete(id);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		return pfaDao.findPageBean(pageSize, page, factNo, yymm);
	}

}
