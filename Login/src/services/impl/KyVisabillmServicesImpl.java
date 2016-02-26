package services.impl;

import java.util.List;

import dao.IKyVisaBillmDao;
import entity.KyVisabillm;
import entity.KyzExpectmatmLog;
import services.IKyVisabillmServices;
import util.PageBean;

public class KyVisabillmServicesImpl implements IKyVisabillmServices{
	private IKyVisaBillmDao visabillmDao;

	public void setVisabillmDao(IKyVisaBillmDao visabillmDao) {
		this.visabillmDao = visabillmDao;
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String billNo,String visaMk) {
		// TODO Auto-generated method stub
		return visabillmDao.findPageBean(pageSize, page, factNo, billNo,visaMk);
	}

	public void add(KyVisabillm vbm) {
		// TODO Auto-generated method stub
		visabillmDao.add(vbm);
	}

	public KyVisabillm findById(String factNo, String visaSort, String billNo) {
		// TODO Auto-generated method stub
		return visabillmDao.findById(factNo, visaSort, billNo);
	}

	public void delete(String factNo, String visaSort, String billNo,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		visabillmDao.delete(factNo, visaSort, billNo,delLog);
		
	}

	public List<KyVisabillm> findByVisaMk(String visaMk) {
		// TODO Auto-generated method stub
		return visabillmDao.findByVisaMk(visaMk);
	}

	public List<KyVisabillm> findAllVbm() {
		// TODO Auto-generated method stub
		return visabillmDao.findAllVbm();
	}

	public List<KyVisabillm> findByVisaMk2(String visaMk) {
		// TODO Auto-generated method stub
		return visabillmDao.findByVisaMk2(visaMk);
	}

	public KyVisabillm findByBillNo(String billNo) {
		// TODO Auto-generated method stub
		return visabillmDao.findByBillNo(billNo);
	}

	public List<KyVisabillm> findBefor2Month() {
		// TODO Auto-generated method stub
		return visabillmDao.findBefor2Month();
	}

	public void addLarge(List<KyVisabillm> list) {
		// TODO Auto-generated method stub
		visabillmDao.addLarge(list);
	}

}
