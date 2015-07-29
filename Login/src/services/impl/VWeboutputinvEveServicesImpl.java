package services.impl;

import dao.IVWeboutputinvEveDao;
import entity.VWeboutputinvEve;
import services.IVWeboutputinvEveServices;

public class VWeboutputinvEveServicesImpl implements IVWeboutputinvEveServices{
	private IVWeboutputinvEveDao inveveDao;
	

	public void setInveveDao(IVWeboutputinvEveDao inveveDao) {
		this.inveveDao = inveveDao;
	}


	public VWeboutputinvEve findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		return inveveDao.findById(factNo, factCode, yymm);
	}

}
