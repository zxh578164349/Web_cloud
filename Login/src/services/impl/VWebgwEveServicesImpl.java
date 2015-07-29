package services.impl;

import dao.IVWebgwEveDao;
import entity.VWebgwEve;
import services.IVWebgwEveServices;

public class VWebgwEveServicesImpl implements IVWebgwEveServices{
	private IVWebgwEveDao gweveDao;
	

	public void setGweveDao(IVWebgwEveDao gweveDao) {
		this.gweveDao = gweveDao;
	}


	public VWebgwEve findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		return gweveDao.findById(factNo, factCode, yymm);
	}

}
