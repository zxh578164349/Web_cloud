package services.impl;

import java.util.List;
import dao.IWebBackpfDao;
import entity.WebBackpf;
import entity.WebBackpfId;
import services.IWebBackpfServices;
import util.PageBean;

public class WebBackpfServicesImpl implements IWebBackpfServices {
	private IWebBackpfDao backpfDao;

	public void setBackpfDao(IWebBackpfDao backpfDao) {
		this.backpfDao = backpfDao;
	}

	/**
	 * ÃÌº”ªÿÓ^¡œ
	 */
	public void addBackpf(WebBackpf backpf) {
		// TODO Auto-generated method stub
		backpfDao.addBackpf(backpf);
	}

	public List<WebBackpf> selBackpf(String factNo, String yymm, int page,
			int rows) {
		return backpfDao.selectBackpf(factNo, yymm, page, rows);
	}

	public int totlePage(String factNo, String yymm) {
		return backpfDao.totlePage(factNo, yymm);
	}

	public WebBackpf findBackById(WebBackpfId id) {
		// TODO Auto-generated method stub
		return backpfDao.findBackById(id);
	}

	public List<WebBackpf> selByYymm(String yymm, String factNo) {
		return backpfDao.selByYymm(yymm, factNo);
	}

	public void delete(WebBackpfId id) {
		// TODO Auto-generated method stub
		backpfDao.delete(id);

	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		return backpfDao.findPageBean(pageSize, page, factNo, yymm);
	}

}
