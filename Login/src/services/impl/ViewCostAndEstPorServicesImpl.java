package services.impl;

import java.util.List;

import dao.IViewCostAndEstProDao;

import services.IViewCostAndEstProServices;

public class ViewCostAndEstPorServicesImpl implements
		IViewCostAndEstProServices {
	private IViewCostAndEstProDao viewCostDao;

	public void setViewCostDao(IViewCostAndEstProDao viewCostDao) {
		this.viewCostDao = viewCostDao;
	}

	public List findAll() {
		// TODO Auto-generated method stub
		return viewCostDao.findAll();
	}

}
