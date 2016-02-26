package services.impl;

import java.util.List;

import dao.IKyzExpectmatmFileDao;
import entity.KyzExpectmatmFile;
import entity.KyzExpectmatmLog;
import services.IKyzExpectmatmFileServices;

public class KyzExpectmatmFileServicesImpl implements IKyzExpectmatmFileServices{
	private IKyzExpectmatmFileDao kyzexpfileDao;
	

	public void setKyzexpfileDao(IKyzExpectmatmFileDao kyzexpfileDao) {
		this.kyzexpfileDao = kyzexpfileDao;
	}


	public void add(KyzExpectmatmFile file) {
		// TODO Auto-generated method stub
		kyzexpfileDao.add(file);
	}


	public List<KyzExpectmatmFile> findByBillNo(String billNo) {
		// TODO Auto-generated method stub
		return kyzexpfileDao.findByBillNo(billNo);
	}


	public void delete(KyzExpectmatmFile file,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		kyzexpfileDao.delete(file,delLog);
	}


	public KyzExpectmatmFile findById(Integer id) {
		// TODO Auto-generated method stub
		return kyzexpfileDao.findById(id);
	}


	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		boolean flag=false;
		try{
			kyzexpfileDao.delete(id);
			flag=true;
		}catch(RuntimeException e){
			flag=false;
		}
		return flag;
	}


	/**
	 * 日期:2016/2/25
	 * 描述:
	 */
	
	
	public List<String> findBillNo(String factNo, String visaTypeM) {
		// TODO Auto-generated method stub
		return kyzexpfileDao.findBillNo(factNo, visaTypeM);
	}

}
