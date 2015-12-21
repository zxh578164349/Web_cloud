package services.impl;

import dao.IWebTabpomDao;
import entity.WebTabpom;
import services.IWebTabpomServices;
import util.PageBean;

public class WebTabpomServicesImpl implements IWebTabpomServices{
	private IWebTabpomDao tabpomDao;
	

	public void setTabpomDao(IWebTabpomDao tabpomDao) {
		this.tabpomDao = tabpomDao;
	}

	public void add(WebTabpom tabpom) {
		// TODO Auto-generated method stub
		tabpomDao.add(tabpom);
	}

	public WebTabpom findById(String pomNo) {
		// TODO Auto-generated method stub
		return tabpomDao.findById(pomNo);
	}

	public void delete(WebTabpom tabpom) {
		// TODO Auto-generated method stub
		tabpomDao.delete(tabpom);
	}

	public void delete(String pomNo) {
		// TODO Auto-generated method stub
		tabpomDao.delete(pomNo);
	}

	public PageBean findPageBean(int pageSize, int page, String pomName,
			String brank) {
		// TODO Auto-generated method stub
		return tabpomDao.findPageBean(pageSize, page, pomName, brank);
	}

	public String findPomNoById(String pomNo) {
		// TODO Auto-generated method stub
		return tabpomDao.findPomNoById(pomNo);
	}

}
