package services.impl;

import java.util.List;
import entity.*;
import dao.*;
import services.*;
import util.PageBean;

public class WebSideServicesImpl implements IWebSideServices {
	private IWebSideDao sideDao;

	public void setSideDao(IWebSideDao sideDao) {
		this.sideDao = sideDao;
	}

	public void addside(WebSide side) {
		// TODO Auto-generated method stub
		sideDao.addSide(side);

	}

	public List<WebSide> selSide(String factNo, String yymm, int page, int rows) {
		return sideDao.selectSide(factNo, yymm, page, rows);
	}

	public int totlePage(String factNo, String yymm) {
		return sideDao.totlePage(factNo, yymm);
	}

	public WebSide findById(WebSideId id) {
		// TODO Auto-generated method stub
		return sideDao.findById(id);
	}

	public List<WebSide> selByYymm(String yymm, String factNo) {
		return sideDao.selByYymm(yymm, factNo);
	}

	public void delete(WebSideId id) {
		// TODO Auto-generated method stub
		sideDao.delete(id);

	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		return sideDao.findPageBean(pageSize, page, factNo, yymm);
	}

}
