package services.impl;

import java.util.List;

import dao.IWebMajorSortDao;

import entity.WebMajorsort;
import services.IWebMajorSortServices;

public class WebMajorSortServicesImpl implements IWebMajorSortServices {
	private IWebMajorSortDao majorDao;

	public void setMajorDao(IWebMajorSortDao majorDao) {
		this.majorDao = majorDao;
	}

	public List<WebMajorsort> findAll() {
		// TODO Auto-generated method stub
		return majorDao.findAll();
	}

	public String findById(Integer id) {
		// TODO Auto-generated method stub
		return majorDao.findById(id);
	}

}
