package services.impl;

import dao.IKyzExpcetmatmLogDao;
import entity.KyzExpectmatmLog;
import services.IKyzExpectmatmLogServices;

public class KyzExpectmatmLogServicesImpl implements IKyzExpectmatmLogServices{
	private IKyzExpcetmatmLogDao kyzExpLogDao;
	
	public void setKyzExpLogDao(IKyzExpcetmatmLogDao kyzExpLogDao) {
		this.kyzExpLogDao = kyzExpLogDao;
	}

	public void add(KyzExpectmatmLog log) {
		// TODO Auto-generated method stub
		kyzExpLogDao.add(log);
	}

}
