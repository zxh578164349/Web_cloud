package services.impl;

import java.util.List;

import dao.IWebuserEmailDao;
import entity.WebuserEmail;
import services.IWebuserEmailServices;
import util.PageBean;

public class WebuserEmailServicesImpl implements IWebuserEmailServices{
	private IWebuserEmailDao webuseremailDao;
	

	public void setWebuseremailDao(IWebuserEmailDao webuseremailDao) {
		this.webuseremailDao = webuseremailDao;
	}



	public void add(WebuserEmail email) {
		// TODO Auto-generated method stub
		webuseremailDao.add(email);
	}

	public WebuserEmail findById(String factNo, String email, String emailpwd) {
		// TODO Auto-generated method stub
		return webuseremailDao.findById(factNo, email, emailpwd);
	}

	public void delete(String factNo,String email,String emailpwd) {
		// TODO Auto-generated method stub
		webuseremailDao.delete(factNo,email,emailpwd);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String email) {
		// TODO Auto-generated method stub
		return webuseremailDao.findPageBean(pageSize, page, factNo, email);
	}

	public List<WebuserEmail> findByFactNoAEmailPwd(String factNo, String email) {
		// TODO Auto-generated method stub
		return webuseremailDao.findByFactNoAEmailPwd(factNo, email);
	}



	public List<String> findByFactNoAEmailPwd2(String factNo, String email) {
		// TODO Auto-generated method stub
		return webuseremailDao.findByFactNoAEmailPwd2(factNo, email);
	}

}
