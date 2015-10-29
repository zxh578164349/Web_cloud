package services;

import entity.WebLog;

public interface IWebLogService {
	// 保存用戶登陸信息
   //public boolean saveWebLog(WebLog webLog);
	public void saveWebLog(WebLog webLog);
	public WebLog findById(int id);
}
