package dao;

import java.util.List;

import util.PageBean;
import entity.*;

public interface IWebEstProductDao {
	public void add(Webestproduct product);

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm);

	public List<Webestproduct> findByFactNo(String factNo);

	public Webestproduct findById(WebestproductId id);

	public void delete(WebestproductId id);

	public List<Webestproduct> selByFactNoAndYymm(String factNo, String yymm,
			String type);
	
	public Webestproduct findById(String factNo,String factCode,String yymm,String type);
	public Object[] findSum(String factCode,String yymm,String type);
	
	public List<Webestproduct>findByFactcode(String factNo,String factCode,String yymm);
	public List<Webestproduct>findNullYpre(String factNo,String factCode,String yymm);
	public List<Webestproduct>findByAny(String factNo,String beginDate,String endDate);
	
	//檢查當月沒有輸入數據的廠別
	public List<String[]> getFactPrint_show(String date);
	//用於webcashout請款資料報表(上部)
	public Object[] reportWebCashout(String factNo,String factCode,String yymm,String type);
	
	public List<String> findtypeById(String factNo,String factCode,String yymm);


}
