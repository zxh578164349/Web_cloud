package services.impl;

import java.util.List;

import dao.IWebEmailDao;

import entity.WebCc;
import entity.WebEmail;
import services.IWebEmailService;

public class WebEmailServiceImpl implements IWebEmailService {

	private IWebEmailDao emailDao;

	public void setEmailDao(IWebEmailDao emailDao) {
		this.emailDao = emailDao;
	}

	public List<WebEmail> getEmail(String sendif) {
		return emailDao.getEmail(sendif);
	}

	public List<WebCc> getCC(String sendif) {
		return emailDao.getCC(sendif);
	}

}
