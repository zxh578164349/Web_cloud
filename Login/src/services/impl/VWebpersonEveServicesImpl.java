package services.impl;

import dao.IVWebpersonEveDao;
import entity.VWebpersonEve;
import services.IVWebpersonEveServices;

public class VWebpersonEveServicesImpl implements IVWebpersonEveServices{
	private IVWebpersonEveDao personeveDao;
	

	public void setPersoneveDao(IVWebpersonEveDao personeveDao) {
		this.personeveDao = personeveDao;
	}


	public VWebpersonEve findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		return personeveDao.findById(factNo, factCode, yymm);
	}

}
