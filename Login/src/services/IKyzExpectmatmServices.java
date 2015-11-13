package services;

import java.util.List;

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
			
}
