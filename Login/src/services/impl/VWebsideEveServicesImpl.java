package services.impl;

import dao.IVWebsideEveDao;
import entity.VWebsideEve;
import services.IVWebsideEveServices;

public class VWebsideEveServicesImpl implements IVWebsideEveServices{
	private IVWebsideEveDao sideeveDao;
	

	public void setSideeveDao(IVWebsideEveDao sideeveDao) {
		this.sideeveDao = sideeveDao;
	}


	public VWebsideEve findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		return sideeveDao.findById(factNo, factCode, yymm);
	}

}
