package dao;

import java.util.List;

import util.PageBean;
import entity.*;

public interface IWebPersonNumDao {
	public void add(Webpersonnum person);

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm,String beginDay,String endDay);

	public List<Webpersonnum> findByFactNo(String factNo);

	public Webpersonnum findById(WebpersonnumId id);

	public void delete(WebpersonnumId id,KyzExpectmatmLog delLog);
	
	
	public List<Webpersonnum> findByYymmdd(String yymmdd);	
	public List<Object[]> getTotalByYymmdd(String yymmdd);
	public Webpersonnum findById2(String factNo,String factCode,String yymmdd);
	public List<Object[]> findByYnmmddAndFactcode(String yymmdd);
	
	/**
	 * 搜索打印
	 * @Title: print_search
	 * @Description: TODO
	 * @param @param factNo
	 * @param @param yymm1
	 * @param @param yymm2
	 * @param @return
	 * @return List<Webpersonnum>
	 * @throws
	 * @author web
	 * @date 2016/3/1
	 */
	public List<Webpersonnum>print_search(String factNo,String yymm1,String yymm2);

}
