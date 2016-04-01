package dao;

import java.util.Date;
import java.util.List;
import util.PageBean;
import entity.*;

public interface IWebYieldDataDao {
	public List<WebYieldData> findDataByFactcode(String factno,
			String factcode, String yymm);

	public void addYdata(WebYieldData data);

	public List<WebYieldData> selectYDate(String factNo, String yymm, int page,
			int rows);

	// �d�ߥ����ƾڪ��`����
	public int totlePage(String factNo, String yymm);

	public WebYieldData findById(WebYieldDataId id);

	public PageBean findYdataWithPage(int pageSize, int page, String factNo,
			String yymm,String yymm2);

	public void delete(WebYieldDataId id);

	public List<WebYieldData> findAByFactNo(String factNo);

	public List<WebYieldData> getAllWithNoPage(String factNo, String yymm);

	public PageBean findAllYDataForMonth(int pageSize, int page, String factNo,
			String yymm);

	public List<WebYieldData> findNullYdata(String factno, String factcode,
			String yymm);

	public List findNullYdata(String yymm);

	public Object[] totalWithFactCode(String factCode, String yymmdd);

	public Object[] testireport(String factno, String factcode, String yymm);

	public void addYdate_log(WebYieldDataLog log);

	public List<String[]> getFactPrint(String date);

	public List<WebYieldData> findDataByFactcode2(String factno,
			String factcode, String firstyymmdd, String lastyymmdd);
	public Object[] getSumWebYieldDate(String factNo,String factCode,String startDate,String endDate); 
	
	public long findYdateSdateToEnddate(String factNo,String factCode,String startDate,String endDate);
	
	public List<WebYieldData>findYdate(String factNo,String startDate,String endDate);
	
	public List<String[]> getFactPrint_show(String date);
	
	public double findNums(String factNo,String factCode);
	
	/**
	 * 月份所有廠的數據
	 * @Title: findByYymm
	 * @Description: TODO
	 * @param @param yymm
	 * @param @return
	 * @return List<WebYieldData>
	 * @throws
	 * @author web
	 * @date 2016/4/1
	 */
	public List<WebYieldData>findByYymm(String yymm);
			

}
