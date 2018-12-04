package services.impl;

import java.util.List;

import dao.IWebEmailDao;

import entity.KyzExpectmatmLog;
import entity.WebCc;
import entity.WebEmail;
import entity.WebEmailAll;
import entity.WebEmailType;
import services.IWebEmailService;
import util.PageBean;

public class WebEmailServiceImpl implements IWebEmailService {

	private IWebEmailDao emailDao;

	public void setEmailDao(IWebEmailDao emailDao) {
		this.emailDao = emailDao;
	}

	/*public List<WebEmail> getEmail(String sendif) {
		return emailDao.getEmail(sendif);
	}

	public List<WebCc> getCC(String sendif) {
		return emailDao.getCC(sendif);
	}*/

	/**
	 * 日期:2016/6/24
	 * 描述:
	 */
	
	
	public List<WebEmailAll> findEmail(int eid,String emailOrCc){
		// TODO Auto-generated method stub
		return emailDao.findEmail(eid,emailOrCc);
	}

	public List<WebEmailType> findAllEmailTypes() {
		// TODO Auto-generated method stub
		return emailDao.findAllEmailTypes();
	}

	public void add(WebEmailAll obj) {
		// TODO Auto-generated method stub
		emailDao.add(obj);
	}

	public PageBean findPageBean(int page, int pageSize, int eid, String email,
			String uname,String emailOrcc,String emailMk) {
		// TODO Auto-generated method stub
		return emailDao.findPageBean(page, pageSize, eid, email, uname, emailOrcc, emailMk);
	}

	public WebEmailAll findById(int eid) {
		// TODO Auto-generated method stub
		return emailDao.findById(eid);
	}

	public void delete(int eid, KyzExpectmatmLog log) {
		// TODO Auto-generated method stub
		emailDao.delete(eid, log);
	}

	public WebEmailAll findByEmailAndEtypeAndEmailOrCc(String email, int type,
			String emailOrCc) {
		// TODO Auto-generated method stub
		return emailDao.findByEmailAndEtypeAndEmailOrCc(email, type, emailOrCc);
	}

	/**
	 * 日期:2016/6/24
	 * 描述:
	 */
	
	
	/*public List<WebEmailAll> findCC(String emailType){
		// TODO Auto-generated method stub
		return emailDao.findCC(emailType);
	}*/

}
