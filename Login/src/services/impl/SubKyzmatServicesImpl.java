package services.impl;

import java.util.List;

import dao.ISubKyzmatDao;
import entity.SubKyzmat;
import services.ISubKyzmatServices;
import util.PageBean;

public class SubKyzmatServicesImpl implements ISubKyzmatServices{
	private ISubKyzmatDao subkyzmatDao;
	

	public void setSubkyzmatDao(ISubKyzmatDao subkyzmatDao) {
		this.subkyzmatDao = subkyzmatDao;
	}


	public void add(SubKyzmat sub) {
		// TODO Auto-generated method stub
		subkyzmatDao.add(sub);
	}


	public List<String> findByMatNo(String matNo) {
		// TODO Auto-generated method stub
		return subkyzmatDao.findByMatNo(matNo);
	}


	public PageBean findPageBean(int pageSize, int page, String fromDate,
			String endDate, String matCname, String bNo, String mNo, String sNo,
			String factNo,String matNo) {
		// TODO Auto-generated method stub
		return subkyzmatDao.findPageBean(pageSize, page, fromDate, endDate, matCname, bNo, mNo, sNo, factNo,matNo);
	}


	public List<SubKyzmat> findWithNoPage(String fromDate, String endDate,
			String matCname, String bNo, String mNo, String sNo, String factNo,String matNo) {
		// TODO Auto-generated method stub
		return subkyzmatDao.findWithNoPage(fromDate, endDate, matCname, bNo, mNo, sNo, factNo,matNo);
	}


	public SubKyzmat findById(String userName, String factNo, String matNo) {
		// TODO Auto-generated method stub
		return subkyzmatDao.findById(userName, factNo, matNo);
	}


	public void delete(String userName, String factNo, String matNo) {
		// TODO Auto-generated method stub
		subkyzmatDao.delete(userName, factNo, matNo);
	}

}
