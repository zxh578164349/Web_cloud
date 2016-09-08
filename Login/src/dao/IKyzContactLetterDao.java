package dao;

import java.util.List;

import util.PageBean;
import entity.KyzContactletter;
import entity.KyzExpectmatmLog;
import entity.WebUser;

public interface IKyzContactLetterDao {
	public void add(KyzContactletter letter);
	public PageBean findPageBean(int pageSize, int page, String factNo,String visaSort,String billNo,WebUser user,String timeCreate,String timeCreate2);		
	public List<String> findBillNo(String factNo, String timeCreate);
	public KyzContactletter findById(String factNo,String billNo);
	public void delete(String factNo,String billNo,KyzExpectmatmLog delLog);
	public String findTitleByBillno(String billNo);
	public List<Object[]>findTitle(String factNo);
	public List<Object[]>findTitle(List<String>billnos);
	
	/**
	 * 
	 * @Title: findBefor2Month
	 * @Description: 兩箇月之前沒有添加刪除標記的函文20160216
	 * @param @return
	 * @return List<KyzContactletter>
	 * @throws
	 * @author web
	 * @date 2016/2/17
	 */
	public List<KyzContactletter>findBefor2Month();
	
	/**
	 * 
	 * @Title: addLarge
	 * @Description: 大批量添加 20160216
	 * @param @param list
	 * @return void
	 * @throws
	 * @author web
	 * @date 2016/2/17
	 */
	public void addLarge(List<KyzContactletter>list);

}
