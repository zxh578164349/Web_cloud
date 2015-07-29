package services.impl;

import java.util.List;
import entity.*;
import dao.*;
import services.*;
import util.PageBean;

public class WebOutputInvServicesImpl implements IWebOutputInvServices {
	private IWebOutputInvDao outputInvDao;

	public void setOutputInvDao(IWebOutputInvDao outputInvDao) {
		this.outputInvDao = outputInvDao;
	}

	public void addoutPut(WebOutputinv output) {
		// TODO Auto-generated method stub
		outputInvDao.addOutput(output);
	}

	public List<WebOutputinv> selOutputinv(String factNo, String yymm,
			int page, int rows) {
		return outputInvDao.selectOutputInv(factNo, yymm, page, rows);
	}

	public int totlePage(String factNo, String yymm) {
		return outputInvDao.totlePage(factNo, yymm);
	}

	public WebOutputinv findById(WebOutputinvId id) {
		// TODO Auto-generated method stub
		return outputInvDao.findById(id);
	}

	public List<WebOutputinv> selByYymm(String yymm, String factNo) {
		return outputInvDao.selByYymm(yymm, factNo);
	}

	public void delete(WebOutputinvId id) {
		// TODO Auto-generated method stub
		outputInvDao.delete(id);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		return outputInvDao.findPageBean(pageSize, page, factNo, yymm);
	}

}
