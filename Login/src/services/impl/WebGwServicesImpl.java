package services.impl;

import java.util.List;
import dao.IWebGwDao;
import entity.*;
import services.IWebGwServices;
import util.PageBean;

public class WebGwServicesImpl implements IWebGwServices {
	private IWebGwDao gwDao;

	public void setGwDao(IWebGwDao gwDao) {
		this.gwDao = gwDao;
	}

	public void addWebGw(WebGw gw) {
		// TODO Auto-generated method stub
		gwDao.addGw(gw);
	}

	public List<WebGw> selGw(String factNo, String yymm, int page, int rows) {
		return gwDao.selectGw(factNo, yymm, page, rows);
	}

	public int totlePage(String factNo, String yymm) {
		return gwDao.totlePage(factNo, yymm);
	}

	public WebGw findById(WebGwId id) {
		// TODO Auto-generated method stub
		return gwDao.findById(id);
	}

	public List<WebGw> selByYymm(String yymm, String factNo) {
		return gwDao.selByYymm(yymm, factNo);
	}

	public void delete(WebGwId id) {
		// TODO Auto-generated method stub
		gwDao.delete(id);

	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		return gwDao.findPageBean(pageSize, page, factNo, yymm);
	}

}
