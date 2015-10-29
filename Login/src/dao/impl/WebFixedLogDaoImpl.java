package dao.impl;

import dao.Basedao;
import dao.IWebFixedLogDao;
import entity.WebFixedLog;

public class WebFixedLogDaoImpl extends Basedao implements IWebFixedLogDao {

	public void add(WebFixedLog log) {
		// TODO Auto-generated method stub
		super.merge(log);
	}

}
