package services.impl;

import dao.IWebcashoutDao;
import entity.Webcashout;
import services.IWebCashoutServices;
import util.PageBean;

public class WebCashoutServicesImpl implements IWebCashoutServices{
	private IWebcashoutDao cashoutDao;

	public void setCashoutDao(IWebcashoutDao cashoutDao) {
		this.cashoutDao = cashoutDao;
	}

	public void add(Webcashout cashout) {
		// TODO Auto-generated method stub
		cashoutDao.add(cashout);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String factCode, String date) {
		// TODO Auto-generated method stub
		return cashoutDao.findPageBean(pageSize, page, factNo, factCode, date);
	}

	public Webcashout findById(String factNo, String factCode, String date) {
		// TODO Auto-generated method stub
		return cashoutDao.findById(factNo, factCode, date);
	}

	public void delete(String factNo, String factCode, String date) {
		// TODO Auto-generated method stub
		cashoutDao.delete(factNo, factCode, date);
	}
	

}
