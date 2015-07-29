package services.impl;

import dao.IWebFixedDao;
import dao.IWebFixedLogDao;
import entity.WebFixedLog;
import services.IWebFixedLogServices;

public class WebFixedLogServicesImpl implements IWebFixedLogServices {
	private IWebFixedLogDao fixlogDao;

	public void setFixlogDao(IWebFixedLogDao fixlogDao) {
		this.fixlogDao = fixlogDao;
	}

	public void add(WebFixedLog log) {
		// TODO Auto-generated method stub
		fixlogDao.add(log);
	}

}
