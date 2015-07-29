package dao;

import entity.WebLog;

public interface IWebLogDao {
	// 保存登陸信息
	public void saveWebLog(WebLog webLog);
	public WebLog findById(int id);
}
