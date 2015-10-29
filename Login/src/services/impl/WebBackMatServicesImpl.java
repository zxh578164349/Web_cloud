package services.impl;

import java.util.List;
import dao.IWebBackMatDao;
import entity.WebBackmat;
import entity.WebBackmatId;
import services.IWebBackMatServices;
import util.PageBean;

public class WebBackMatServicesImpl implements IWebBackMatServices {
	private IWebBackMatDao backMatDao;

	public void setBackMatDao(IWebBackMatDao backMatDao) {
		this.backMatDao = backMatDao;
	}

	/**
	 * 氝樓隙彶煨妏蚚緙�
	 */
	public void addBackMat(WebBackmat backmat) {
		// TODO Auto-generated method stub
		backMatDao.addBackMat(backmat);

	}

	public List<WebBackmat> selBackmats(String factNo, String yymm, int page,
			int rows) {
		return backMatDao.selectBackMat(factNo, yymm, page, rows);

	}

	public int totlePage(String factNo, String yymm) {
		return backMatDao.totlePage(factNo, yymm);
	}

	public WebBackmat findBackById(WebBackmatId id) {
		// TODO Auto-generated method stub
		return backMatDao.findBackById(id);
	}

	public List<WebBackmat> selByYymm(String yymm, String factNo) {
		return backMatDao.selByYymm(yymm, factNo);
	}

	public void delete(WebBackmatId id) {
		// TODO Auto-generated method stub
		backMatDao.delete(id);

	}

	public boolean deleteBackMat(String billNo) {
		// TODO Auto-generated method stub
		return false;
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		return backMatDao.findPageBean(pageSize, page, factNo, yymm);
	}

}
