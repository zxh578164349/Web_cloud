package services.impl;

import services.IWebLogService;
import dao.IWebLogDao;
import entity.WebLog;

public class WebLogServiceImpl implements IWebLogService {

	private IWebLogDao webLogDao;

	public IWebLogDao getWebLogDao() {
		return webLogDao;
	}

	public void setWebLogDao(IWebLogDao webLogDao) {
		this.webLogDao = webLogDao;
	}

	public void saveWebLog(WebLog webLog) {
		// TODO Auto-generated method stub
		webLogDao.saveWebLog(webLog);
		
	}
	/*public boolean saveWebLog(WebLog webLog) {
		try {
			webLogDao.saveWebLog(webLog);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}*/

	public WebLog findById(int id) {
		// TODO Auto-generated method stub
		return webLogDao.findById(id);
	}

}
