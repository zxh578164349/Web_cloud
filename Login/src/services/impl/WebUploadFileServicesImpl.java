package services.impl;

import java.util.List;

import dao.IWebUploadFileDao;
import entity.KyzExpectmatmLog;
import entity.WebUploadfiles;
import services.IWebUploadFileServices;

public class WebUploadFileServicesImpl implements IWebUploadFileServices{
	private IWebUploadFileDao webuploadfileDao;
	
	public void setWebuploadfileDao(IWebUploadFileDao webuploadfileDao) {
		this.webuploadfileDao = webuploadfileDao;
	}

	public void add(WebUploadfiles file) {
		// TODO Auto-generated method stub
		webuploadfileDao.add(file);
	}

	public List<WebUploadfiles> findByName(String userName, String factNo) {
		// TODO Auto-generated method stub
		return webuploadfileDao.findByName(userName, factNo);
	}

	public boolean delete(int id,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		return webuploadfileDao.delete(id,delLog);
	}

	public WebUploadfiles findById(int id) {
		// TODO Auto-generated method stub
		return webuploadfileDao.findById(id);
	}

}
