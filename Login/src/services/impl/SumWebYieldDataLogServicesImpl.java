package services.impl;

import dao.ISumWebYieldDataLogDao;
import entity.SumWebYieldDataLog;
import services.ISumWebYieldDataLogServices;

public class SumWebYieldDataLogServicesImpl implements ISumWebYieldDataLogServices{
	private ISumWebYieldDataLogDao sumydatelogDao;
	

	public void setSumydatelogDao(ISumWebYieldDataLogDao sumydatelogDao) {
		this.sumydatelogDao = sumydatelogDao;
	}


	public void add(SumWebYieldDataLog log) {
		// TODO Auto-generated method stub
		sumydatelogDao.add(log);
	}

}
