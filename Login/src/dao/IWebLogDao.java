package dao;

import entity.WebLog;

public interface IWebLogDao {
	// �O�s�n���H��
	public void saveWebLog(WebLog webLog);
	public WebLog findById(int id);
}
