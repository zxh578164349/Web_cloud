package services.impl;

import dao.IVWebstoreEveDao;
import entity.VWebstoreEve;
import services.IVWebstoreEveServices;

public class VWebstoreEveServicesImpl implements IVWebstoreEveServices{
	private IVWebstoreEveDao storeeveDao;
	

	public void setStoreeveDao(IVWebstoreEveDao storeeveDao) {
		this.storeeveDao = storeeveDao;
	}


	public VWebstoreEve findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		return storeeveDao.findById(factNo, factCode, yymm);
	}

}
