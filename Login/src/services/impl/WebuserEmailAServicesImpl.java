package services.impl;

import java.util.List;

import services.IWebuserEmailAServices;
import util.PageBean;
import dao.IWebuserEmailADao;
import entity.WebuserEmailA;

public class WebuserEmailAServicesImpl implements IWebuserEmailAServices{
	private IWebuserEmailADao webuseremailaDao;
	

	public void setWebuseremailaDao(IWebuserEmailADao webuseremailaDao) {
		this.webuseremailaDao = webuseremailaDao;
	}

	public void add(WebuserEmailA email) {
		// TODO Auto-generated method stub
		webuseremailaDao.add(email);
	}

	public WebuserEmailA findById(String factNo, String email, String emailPwd,
			String visaSort) {
		// TODO Auto-generated method stub
		return webuseremailaDao.findById(factNo, email, emailPwd, visaSort);
	}

	public void delete(String factNo, String email, String emailPwd,
			String visaSort) {
		// TODO Auto-generated method stub
		webuseremailaDao.delete(factNo, email, emailPwd, visaSort);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String email, String visaSort) {
		// TODO Auto-generated method stub
		return webuseremailaDao.findPageBean(pageSize, page, factNo, email, visaSort);
	}

	public List<String> findByEmail(String factNo, String email, String visaSort) {
		// TODO Auto-generated method stub
		return webuseremailaDao.findByEmail(factNo, email, visaSort);
	}
	

}
