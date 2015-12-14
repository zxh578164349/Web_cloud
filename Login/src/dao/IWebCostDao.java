package dao;

import java.util.List;

import util.PageBean;
import entity.Webcost;
import entity.WebcostId;

public interface IWebCostDao {
	public void add(Webcost cost);

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm,String yymm2);

	public List<Webcost> findByFactNo(String factNo);

	public Webcost findById(WebcostId id);

	public void delete(WebcostId id);
	
	public List<Webcost>findByAny(String factNo,String beginDate,String endDate);
	public Webcost findById(String factNo,String factCode,String yymm);
	
	public List<Webcost> findByFactNoYm(String factNo,String yymm);

}
