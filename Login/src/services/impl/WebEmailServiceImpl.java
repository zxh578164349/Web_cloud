package services.impl;

import java.util.List;

import dao.IWebEmailDao;

import entity.WebCc;
import entity.WebEmail;
import entity.WebEmailAll;
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

	/**
	 * 日期:2016/6/24
	 * 描述:
	 */
	
	
	public List<WebEmailAll> findEmail(String emailType){
		// TODO Auto-generated method stub
		return emailDao.findEmail(emailType);
	}

	/**
	 * 日期:2016/6/24
	 * 描述:
	 */
	
	
	public List<WebEmailAll> findCC(String emailType){
		// TODO Auto-generated method stub
		return emailDao.findCC(emailType);
	}

}
