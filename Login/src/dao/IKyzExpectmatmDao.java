package dao;

import java.util.List;

import util.PageBean;

import entity.KyzExpectmatm;
import entity.KyzExpectmatmId;
import entity.KyzExpectmats;

public interface IKyzExpectmatmDao {
	public void add(KyzExpectmatm kyz);

	public List<String> findBillNo(String factNo, String timeCreate);

	public List<KyzExpectmatm> getList(String memoSmk);
	public KyzExpectmatm findById(KyzExpectmatmId id);
	public PageBean findFixWithPage(int pageSize, int page, String visaSort,String yymm,String billNo,String userNm,String timeCreate,String timeCreate2);
	public void delete(KyzExpectmatmId id);
	public List<KyzExpectmatm> findByFactNo(String factno);
	public List<KyzExpectmatm> findById_Print(KyzExpectmatmId id);
	
	public KyzExpectmatm findById2(String billNo);//�ѩ�渹�O�ߤ@��,�ҥH�i�H�Χ@��@����d��
	public String findTitleByBillno(String billNo);
}
