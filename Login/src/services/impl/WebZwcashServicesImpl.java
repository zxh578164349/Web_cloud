package services.impl;

import java.util.List;
import entity.*;
import dao.*;
import services.*;
import util.PageBean;

public class WebZwcashServicesImpl implements IWebZwcashServices {
	private IWebZwcashDao zwcashDao;

	public void setZwcashDao(IWebZwcashDao zwcashDao) {
		this.zwcashDao = zwcashDao;
	}

	/**
	 * ²K¥[¤ô¹qªo
	 */
	public void addzwcash(WebZwcash cash) {
		// TODO Auto-generated method stub
		zwcashDao.addZwcash(cash);
	}

	public List<WebZwcash> selZwcash(String factNo, String yymm, int page,
			int rows) {
		return zwcashDao.selectZwcash(factNo, yymm, page, rows);
	}

	public int totlePage(String factNo, String yymm) {
		return zwcashDao.totlePage(factNo, yymm);
	}

	public WebZwcash findById(WebZwcashId id) {
		return zwcashDao.findById(id);
	}

	public List<WebZwcash> selByYymm(String yymm, String factNo) {
		return zwcashDao.selByYymm(yymm, factNo);
	}

	public void delete(WebZwcashId id) {
		// TODO Auto-generated method stub
		zwcashDao.delete(id);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		return zwcashDao.findPageBean(pageSize, page, factNo, yymm);
	}

}
