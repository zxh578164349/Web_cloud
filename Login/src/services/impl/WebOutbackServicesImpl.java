package services.impl;

import java.util.List;
import entity.*;
import dao.*;
import services.*;
import util.PageBean;

public class WebOutbackServicesImpl implements IWebOutbackServices {
	private IWebOutbackDao outbackDao;

	public void setOutbackDao(IWebOutbackDao outbackDao) {
		this.outbackDao = outbackDao;
	}

	public void addOutback(WebOutback back) {
		// TODO Auto-generated method stub
		outbackDao.addOutback(back);

	}

	public List<WebOutback> selOutback(String factNo, String yymm, int page,
			int rows) {
		return outbackDao.selectOutback(factNo, yymm, page, rows);
	}

	public int totlePage(String factNo, String yymm) {
		return outbackDao.totlePage(factNo, yymm);
	}

	public WebOutback findById(WebOutbackId id) {
		// TODO Auto-generated method stub
		return outbackDao.findById(id);
	}

	public List<WebOutback> selByYymm(String yymm, String factNo) {
		return outbackDao.selByYymm(yymm, factNo);
	}

	public void delete(WebOutbackId id) {
		// TODO Auto-generated method stub
		outbackDao.delete(id);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		return outbackDao.findPageBean(pageSize, page, factNo, yymm);
	}

}
