package services;

import entity.WebLog;

public interface IWebLogService {
	// �O�s�Τ�n���H��
   //public boolean saveWebLog(WebLog webLog);
	public void saveWebLog(WebLog webLog);
	public WebLog findById(int id);
}
