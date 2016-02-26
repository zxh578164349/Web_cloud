package services;

import java.util.List;

import util.PageBean;
import entity.KyzExpectmatmLog;
import entity.Webcost;
import entity.WebcostId;

public interface IWebCostServices {
	public void add(Webcost cost);

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm,String yymm2);

	public List<Webcost> findByFactNo(String factNo);

	public Webcost findById(WebcostId id);

	public void delete(WebcostId id,KyzExpectmatmLog delLog);
	public boolean check(String factNo,String factCode,String yymm);
	
	public List<Webcost> findByAny(String factNo, String beginDate,String endDate);
	public Webcost findById(String factNo,String factCode,String yymm);
	public List<Webcost> findByFactNoYm(String factNo,String yymm);

}
