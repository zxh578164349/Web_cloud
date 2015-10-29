package services.impl;

import java.util.List;

import services.IWebSubsortServices;
import dao.IWebSubSortDao;
import entity.WebSubsort;

public class WebSubsortServicesImpl implements IWebSubsortServices {
	private IWebSubSortDao subDao;

	public void setSubDao(IWebSubSortDao subDao) {
		this.subDao = subDao;
	}

	public List<WebSubsort> findById(Integer id) {
		// TODO Auto-generated method stub
		return subDao.findById(id);
	}

	public String findById2(Integer id) {
		// TODO Auto-generated method stub
		return subDao.findById2(id);
	}

}
