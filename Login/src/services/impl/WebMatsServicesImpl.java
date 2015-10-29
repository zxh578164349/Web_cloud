package services.impl;

import java.util.List;
import entity.*;
import dao.*;
import services.*;
import util.PageBean;

public class WebMatsServicesImpl implements IWebMatsServices {
	private IWebMatsDao matDao;

	public void setMatDao(IWebMatsDao matDao) {
		this.matDao = matDao;
	}

	public void addMats(WebMats mats) {
		// TODO Auto-generated method stub
		matDao.addMats(mats);

	}

	public List<WebMats> selMats(String factNo, String yymm, int page, int rows) {
		return matDao.selectMats(factNo, yymm, page, rows);
	}

	public int totlePage(String factNo, String yymm) {
		return matDao.totlePage(factNo, yymm);
	}

	public WebMats findById(WebMatsId id) {
		// TODO Auto-generated method stub
		return matDao.findById(id);
	}

	public List<WebMats> selByYymm(String yymm, String factNo) {
		return matDao.selByYymm(yymm, factNo);
	}

	public void delete(WebMatsId id) {
		// TODO Auto-generated method stub
		matDao.delete(id);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		return matDao.findPageBean(pageSize, page, factNo, yymm);
	}

}
