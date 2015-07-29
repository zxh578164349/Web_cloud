package services.impl;

import java.util.List;
import entity.*;
import dao.*;
import services.*;
import util.PageBean;

public class WebMatstoreServicesImpl implements IWebMatstoreServices {
	private IWebMatstoreDao marstoreDao;

	public void setMarstoreDao(IWebMatstoreDao marstoreDao) {
		this.marstoreDao = marstoreDao;
	}

	public void addMatstore(WebMatstore store) {
		// TODO Auto-generated method stub
		marstoreDao.addMatstore(store);

	}

	public List<WebMatstore> selMatstore(String factNo, String yymm, int page,
			int rows) {
		return marstoreDao.selectMatstore(factNo, yymm, page, rows);
	}

	public int totlePage(String factNo, String yymm) {
		return marstoreDao.totlePage(factNo, yymm);
	}

	public WebMatstore findById(WebMatstoreId id) {
		// TODO Auto-generated method stub
		return marstoreDao.findById(id);
	}

	public List<WebMatstore> selByYymm(String yymm, String factNo) {
		return marstoreDao.selByYymm(yymm, factNo);
	}

	public void delete(WebMatstoreId id) {
		// TODO Auto-generated method stub
		marstoreDao.delete(id);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		return marstoreDao.findPageBean(pageSize, page, factNo, yymm);
	}

}
