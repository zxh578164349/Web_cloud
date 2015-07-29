package services.impl;

import dao.IVWebcostDao;
import entity.VWebcost;
import services.IVWebcostServices;

public class VWebcostServicesImpl implements IVWebcostServices{
	private IVWebcostDao vwebcostDao;
	

	public void setVwebcostDao(IVWebcostDao vwebcostDao) {
		this.vwebcostDao = vwebcostDao;
	}


	public VWebcost findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		return vwebcostDao.findById(factNo, factCode, yymm);
	}

}
