package services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;

import util.PageBean;

import dao.IKyzExpectmatmDao;
import entity.*;

public interface IKyzExpectmatmServices {
	public void add(KyzExpectmatm kyz);

	public List<KyzExpectmatm> getList(String memoSmk);

	public String makeBillNo(String factNo, String timeCreate);
	public KyzExpectmatm findById(KyzExpectmatmId id);
	public PageBean findPageBean(int pageSize, int page, String factNo,String visaSort,String billNo,String userNm,String timeCreate,String timeCreate2);
	public void delete(KyzExpectmatmId id);
	public List<KyzExpectmatm> findByFactNo(String factno);
	public List<KyzExpectmatm> findById_Print(KyzExpectmatmId id);
	
	public KyzExpectmatm findById2(String billNo);
	public String findTitleByBillno(String billNo);
	
	public List<Object[]>findTitle(String factNo);
	public KyzExpectmatm findById(String factNo,String billNo);
	
	public List<KyzExpectmatm>findBefor2Month();//兩箇月之前沒有添加刪除標記的函文20160216
	public void addLarge(List<KyzExpectmatm>list);//大批量添加20160216
	
	/**
	 * 函文報表實現放在Services層
	 * @Title: print
	 * @Description: TODO
	 * @param @param id
	 * @param @param sort
	 * @param @param response
	 * @param @return
	 * @return Map<String,Object>
	 * @throws
	 * @author web
	 * @date 2016/2/22
	 */
	public Map<String,Object> print(String factNo,String billNo,String sort);
			
}
