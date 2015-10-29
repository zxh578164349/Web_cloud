package dao.impl;

import dao.Basedao;
import dao.IWebLogDao;
import entity.WebLog;

public class WebLogDaoImpl extends Basedao implements IWebLogDao {

	public void saveWebLog(WebLog webLog) {
		//getSession().save(webLog);
		super.merge(webLog);
	}

	public WebLog findById(int id) {
		// TODO Auto-generated method stub
		WebLog log=(WebLog)super.findById(id, WebLog.class);
		return log;
	}

}
