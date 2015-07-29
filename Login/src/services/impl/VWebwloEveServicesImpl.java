package services.impl;

import dao.IVWebwloEveDao;
import entity.VWebwloEve;
import services.IVWebwloEveServices;

public class VWebwloEveServicesImpl implements IVWebwloEveServices{
	private IVWebwloEveDao wloeveDao;
	

	public void setWloeveDao(IVWebwloEveDao wloeveDao) {
		this.wloeveDao = wloeveDao;
	}


	public VWebwloEve findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		return wloeveDao.findById(factNo, factCode, yymm);
	}

}
