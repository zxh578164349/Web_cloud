package services.impl;

import java.util.List;
import dao.IWebCpStroreDao;
import entity.WebCpstore;
import entity.WebCpstoreId;
import services.IWebCpStoreServices;
import util.PageBean;

public class WebCpStoreServicesImpl implements IWebCpStoreServices {
	private IWebCpStroreDao storeDao;

	public void setStoreDao(IWebCpStroreDao storeDao) {
		this.storeDao = storeDao;
	}

	public void addWebCpStore(WebCpstore store) {
		// TODO Auto-generated method stub
		storeDao.addWebCpStore(store);

	}

	public List<WebCpstore> selCpstore(String factNo, String yymm, int page,
			int rows) {
		return storeDao.selectCpStore(factNo, yymm, page, rows);
	}

	public int totlePage(String factNo, String yymm) {
		return storeDao.totlePage(factNo, yymm);
	}

	public WebCpstore findById(WebCpstoreId id) {
		// TODO Auto-generated method stub
		return storeDao.findById(id);
	}

	public List<WebCpstore> selByYymm(String yymm, String factNo) {
		return storeDao.selByYymm(yymm, factNo);
	}

	public void delete(WebCpstoreId id) {
		// TODO Auto-generated method stub
		storeDao.delete(id);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		return storeDao.findPageBean(pageSize, page, factNo, yymm);
	}

}
