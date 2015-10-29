package dao;

import java.util.List;

import util.PageBean;
import entity.SumWebYieldData;

public interface ISumWebYieldDataDao {
	public void add(SumWebYieldData sum_ydate);
	public PageBean findPageBean(int pageSize,int page,String factNo,String begin_yymm,String end_yymm);
	public SumWebYieldData findById(String factNo,String factCode,String yymm);
	public List<SumWebYieldData>findByFactNo(String factNo,String yymm);
	public void delete(SumWebYieldData ydata);
	
	public List<SumWebYieldData> findByAny(String factNo,String beginDate,String endDate);
	
	public List<SumWebYieldData> findAll();
	public List<SumWebYieldData>findByFactNoAndYymm(String factNo,String begin_yymm,String end_yymm);
	public String findUsername(String factNo,String factCode,String yymm);

}
