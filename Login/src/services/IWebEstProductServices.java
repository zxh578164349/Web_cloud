package services;

import java.util.List;

import util.PageBean;
import entity.KyzExpectmatmLog;
import entity.Webestproduct;
import entity.WebestproductId;

public interface IWebEstProductServices {
	public void add(Webestproduct product);

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm,String yymm2);

	public List<Webestproduct> findByFactNo(String factNo);

	public Webestproduct findById(WebestproductId id);

	public void delete(WebestproductId id,KyzExpectmatmLog delLog);

	public List<Webestproduct> selByFactNoAndYymm(String factNo, String yymm,
			String type);
	public boolean check(String factNo,String factCode,String yymm,String type);
	public Webestproduct findById(String factNo, String factCode, String yymm,
			String type);
	public Object[] findSum(String factCode, String yymm,String type);
	
	public List<Webestproduct>findByFactcode(String factNo,String factCode,String yymm);
	public List<Webestproduct>findNullYpre(String factNo,String factCode,String yymm);
	public List<Webestproduct>findByAny(String factNo,String beginDate,String endDate);
	
	//�ˬd���S����J�ƾڪ��t�O
	public List<String[]> getFactPrint_show(String date);
	public Object[] reportWebCashout(String factNo,String factCode,String yymm,String type);
	public List<String> findtypeById(String factNo,String factCode,String yymm);
	
	/**
	 * 所有工廠月份數據
	 * @Title: findByYymm
	 * @Description: TODO
	 * @param @param yymm
	 * @param @return
	 * @return List<Webestproduct>
	 * @throws
	 * @author web
	 * @date 2016/4/1
	 */
	public List<Webestproduct>findByYymm(String yymm);
	public List<Webestproduct>findByYymm(String yymm,String yymm2);
	public List<Webestproduct>findByYymm_all(String yymm,String yymm2);
	public void addMore(List<Webestproduct> list);

}
