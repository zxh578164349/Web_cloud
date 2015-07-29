package services.impl;

import dao.IVWebmachineEveDao;
import entity.VWebmachineEve;
import services.IVWebmachineEveServices;

public class VWebmachineEveServicesImpl implements IVWebmachineEveServices{
	private IVWebmachineEveDao machineeveDao;
	

	public void setMachineeveDao(IVWebmachineEveDao machineeveDao) {
		this.machineeveDao = machineeveDao;
	}


	public VWebmachineEve findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		return machineeveDao.findById(factNo, factCode, yymm);
	}

}
