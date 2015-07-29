package services.impl;

import dao.IKyzExpectmatmsDao;
import entity.KyzExpectmats;
import entity.KyzExpectmatsId;
import services.IKyzExpectmatmsServices;

public class KyzExpcetmatmsServicesImpl implements IKyzExpectmatmsServices{
	private IKyzExpectmatmsDao kyzsDao;

	public final void setKyzsDao(IKyzExpectmatmsDao kyzsDao) {
		this.kyzsDao = kyzsDao;
	}

	public KyzExpectmats findById(String factNo,String billNo, String itemNo) {
		// TODO Auto-generated method stub
		return kyzsDao.findById(factNo,billNo,itemNo);
	}

	public void delete(String factNo,String billNo, String itemNo) {
		// TODO Auto-generated method stub
		kyzsDao.delete(factNo,billNo,itemNo);
	}

}
