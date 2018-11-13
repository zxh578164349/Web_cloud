package services;

import java.util.List;

import util.PageBean;
import entity.KyzExpectmatmLog;
import entity.SumWebYieldData;
import entity.SumWebYieldDataView;

public interface ISumWebYieldDataServices {
	public void add(SumWebYieldData sum_ydate);
	public PageBean findPageBean(int pageSize,int page,String factNo,String begin_yymm,String end_yymm);
	public SumWebYieldDataView findById(String factNo,String factCode,String yymm);
	public SumWebYieldData findById2(String factNo,String factCode,String yymm);
	public int findByFactNo(String factNo,String yymm);
	public List<SumWebYieldData> findByFactNo2(String factNo,String yymm);
	public void delete(SumWebYieldData ydata);
	public List<SumWebYieldDataView> findByAny(String factNo,String beginDate,String endDate);
	public List<SumWebYieldData>findAll();
	
	/**
	 * 
	 * @Title: findByFactNoAndYymm
	 * @Description: 報表的數據
	 * @param @param factNo
	 * @param @param begin_yymm
	 * @param @param end_yymm
	 * @param @return
	 * @return List<SumWebYieldData>
	 * @throws
	 * @author web
	 * @date 2016/2/19
	 */
	public List<SumWebYieldDataView>findByFactNoAndYymm(String factNo,String begin_yymm,String end_yymm);
	public String findUsername(String factNo,String factCode,String yymm);
	public List<SumWebYieldDataView> findObjs(String yymm,String yymm2);

}
