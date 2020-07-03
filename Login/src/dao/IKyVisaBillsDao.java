package dao;

import java.util.List;

import util.PageBean;

import entity.KyVisabills;
import entity.KyVisabillsId;
import entity.KyzExpectmatmLog;
import entity.WebUser;

public interface IKyVisaBillsDao {
	public List<KyVisabills> findVisaBills(String visaSort,String billNo);
	public void add(KyVisabills vbs);
	public KyVisabills findById(String factNo,String visaSort,String billNo,String itemNo);
	public void delete(String factNo,String visaSort,String billNo,String itemNo);
	/**
	 * 函文審核狀況
	 * @Title: findPageBean
	 * @Description: TODO
	 * @param @param pageSize
	 * @param @param page
	 * @param @param visaMk
	 * @param @param factNo
	 * @param @param billNo
	 * @param @param visaSort
	 * @param @param createDate
	 * @param @param createDate2
	 * @param @param user
	 * @param @return
	 * @return PageBean
	 * @throws
	 * @author web
	 * @date 2016/5/20
	 */
	public PageBean findPageBean(int pageSize,int page,String visaMk,String factNo,String billNo,String visaSort,String createDate,String createDate2,WebUser user,String title,String bigType);
	public List<KyVisabills> findByFNN(String factNo,String userName);
	/**
	 * 函文審核
	 * @Title: findPageBean_tw
	 * @Description: TODO
	 * @param @param pageSize
	 * @param @param page
	 * @param @param visaMk
	 * @param @param factNo
	 * @param @param billNo
	 * @param @param visaSort
	 * @param @param createDate
	 * @param @param createDate2
	 * @param @param user
	 * @param @return
	 * @return PageBean
	 * @throws
	 * @author web
	 * @date 2016/5/20
	 */
	public PageBean findPageBean_tw(int pageSize,int page,String visaMk,String factNo,String billNo,String visaSort,String createDate,String createDate2, WebUser user,String title,String bigType,int initMain);
	public int findKyVisaBills_Int(String factNo,String email);
	
	public int findBillsWithNo(String visaSort,String billNo);
	public List<KyVisabills> findBillsWithNo2(String visaSort,String billNo);//visaMk=N  flowMk=Y
	public void delete(KyVisabills bils);
	
	public List<KyVisabills> findtoprint(String visaMk, String factNo, String billNo, String visaSort,
			String createDate, String createDate2,WebUser user,String title,String bigType);
	
	public String findById_01(String billNo);

}
