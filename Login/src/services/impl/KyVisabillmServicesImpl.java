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


	public void add(KyVisabillm vbm) {
		// TODO Auto-generated method stub
		visabillmDao.add(vbm);
	}

	public KyVisabillm findById(String factNo, String visaSort, String billNo) {
		// TODO Auto-generated method stub
		return visabillmDao.findById(factNo, visaSort, billNo);
	}
	
	public KyVisabillm findById2(String factNo, String visaSort, String billNo) {
		// TODO Auto-generated method stub
		return visabillmDao.findById2(factNo, visaSort, billNo);
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
	public List<KyVisabillm> findByBillNo2(String billNo) {
		// TODO Auto-generated method stub
		return visabillmDao.findByBillNo2(billNo);
	}

	public List<KyVisabillm> findBefor2Month() {
		// TODO Auto-generated method stub
		return visabillmDao.findBefor2Month();
	}
	
	public List<KyVisabillm> findBefor2Month2() {
		// TODO Auto-generated method stub
		return visabillmDao.findBefor2Month2();
	}

	public void addLarge(List<KyVisabillm> list) {
		// TODO Auto-generated method stub
		visabillmDao.addLarge(list);
	}


	/**
	 * 日期:2016/11/18
	 * 描述:
	 */
	
	
	public boolean add2(KyVisabillm vbm){
		// TODO Auto-generated method stub
		boolean flag=true;
		try{
			visabillmDao.add(vbm);
		}catch(Exception e){
			flag=false;
			System.out.println(e);
		}
		return flag;
	}

}
