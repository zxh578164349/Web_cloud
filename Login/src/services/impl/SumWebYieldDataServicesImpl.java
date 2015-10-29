package services.impl;

import java.util.List;

import dao.ISumWebYieldDataDao;
import entity.SumWebYieldData;
import services.ISumWebYieldDataServices;
import util.PageBean;

public class SumWebYieldDataServicesImpl implements ISumWebYieldDataServices{
	private ISumWebYieldDataDao sumydateDao;
	

	public void setSumydateDao(ISumWebYieldDataDao sumydateDao) {
		this.sumydateDao = sumydateDao;
	}

	public void add(SumWebYieldData sum_ydate) {
		// TODO Auto-generated method stub
		sumydateDao.add(sum_ydate);		
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String begin_yymm,String end_yymm) {
		// TODO Auto-generated method stub
		return sumydateDao.findPageBean(pageSize, page, factNo, begin_yymm,end_yymm);
	}

	public SumWebYieldData findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		return sumydateDao.findById(factNo, factCode, yymm);
	}

	public int findByFactNo(String factNo, String yymm) {
		// TODO Auto-generated method stub
		List<SumWebYieldData>list=sumydateDao.findByFactNo(factNo, yymm);
		int num=list.size();
		return num;
	}

	public void delete(SumWebYieldData ydata) {
		// TODO Auto-generated method stub
		sumydateDao.delete(ydata);
	}

	public List<SumWebYieldData> findByFactNo2(String factNo, String yymm) {
		// TODO Auto-generated method stub
		return sumydateDao.findByFactNo(factNo, yymm);
	}

	public List<SumWebYieldData> findByAny(String factNo, String beginDate,
			String endDate) {
		// TODO Auto-generated method stub
		return sumydateDao.findByAny(factNo, beginDate, endDate);
	}

	public List<SumWebYieldData> findAll() {
		// TODO Auto-generated method stub
		return sumydateDao.findAll();
	}

	public List<SumWebYieldData> findByFactNoAndYymm(String factNo,
			String begin_yymm, String end_yymm) {
		// TODO Auto-generated method stub
		return sumydateDao.findByFactNoAndYymm(factNo, begin_yymm, end_yymm);
	}

	public String findUsername(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		return sumydateDao.findUsername(factNo, factCode, yymm);
	}

}
