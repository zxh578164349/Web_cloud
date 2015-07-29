package services.impl;

import dao.IVWebcostEveDao;
import entity.VWebcostEve;
import services.IVWebcostEveServices;

public class VWebcostEveServicesImpl implements IVWebcostEveServices{
	private IVWebcostEveDao vwebcosteveDao;
	

	public void setVwebcosteveDao(IVWebcostEveDao vwebcosteveDao) {
		this.vwebcosteveDao = vwebcosteveDao;
	}


	public VWebcostEve findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		return vwebcosteveDao.findById(factNo, factCode, yymm);
	}

}
