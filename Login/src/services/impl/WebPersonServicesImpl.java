package services.impl;

import java.util.List;
import entity.*;
import dao.*;
import services.*;
import util.PageBean;

public class WebPersonServicesImpl implements IWebPersonServices {
	private IWebPersonDao personDao;

	public void setPersonDao(IWebPersonDao personDao) {
		this.personDao = personDao;
	}

	public void addPerson(WebPerson person) {
		// TODO Auto-generated method stub
		personDao.addPerson(person);

	}

	public List<WebPerson> selPerson(String factNo, String yymm, int page,
			int rows) {
		return personDao.selectPerson(factNo, yymm, page, rows);
	}

	public int totlePage(String factNo, String yymm) {
		return personDao.totlePage(factNo, yymm);
	}

	public WebPerson findById(WebPersonId id) {
		// TODO Auto-generated method stub
		return personDao.findById(id);
	}

	public List<WebPerson> selByYymm(String yymm, String factNo) {
		return personDao.selByYymm(yymm, factNo);
	}

	public void delete(WebPersonId id) {
		// TODO Auto-generated method stub
		personDao.delete(id);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		return personDao.findPageBean(pageSize, page, factNo, yymm);
	}

}
