package services.impl;

import java.util.List;
import dao.IWebJurisdictionDao;
import entity.WebJurisdiction;
import services.IWebJurisdictionService;

public class WebJurisdictionServiceImpl implements IWebJurisdictionService {

	private IWebJurisdictionDao jurisdictionDao;

	public IWebJurisdictionDao getJurisdictionDsao() {
		return jurisdictionDao;
	}

	public void setJurisdictionDao(IWebJurisdictionDao jurisdictionDao) {
		this.jurisdictionDao = jurisdictionDao;
	}

	public boolean addJurisdiction(WebJurisdiction jurisdiction) {
		try {
			jurisdictionDao.addJurisdiction(jurisdiction);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public int delByUserId(int userId) {
		return jurisdictionDao.delByUserId(userId);
	}

	public List<WebJurisdiction> selSub(int userid) {
		return jurisdictionDao.selbyid(userid);
	}

	public boolean delJur(WebJurisdiction jurisdiction) {
		try {
			jurisdictionDao.delJur(jurisdiction);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean add(WebJurisdiction jurisdiction) {
		try {
			jurisdictionDao.add(jurisdiction);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public WebJurisdiction selBymenuName(String name, int id) {
		return jurisdictionDao.selBymenuName(name, id);
	}

	public WebJurisdiction findById(int id) {
		// TODO Auto-generated method stub
		return jurisdictionDao.findById(id);
	}

}
