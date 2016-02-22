package dao;

import java.util.List;

import org.hibernate.SessionFactory;

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
	public List<KyzExpectmatm> findById_Print(String factNo,String billNo);
	
	public KyzExpectmatm findById2(String billNo);//�ѩ�渹�O�ߤ@��,�ҥH�i�H�Χ@��@���d��
	public String findTitleByBillno(String billNo);
	
	public List<Object[]>findTitle(String factNo);
	public KyzExpectmatm findById(String factNo,String billNo);
	
	public List<KyzExpectmatm>findBefor2Month();//兩箇月之前沒有添加刪除標記的函文20160216
	public void addLarge(List<KyzExpectmatm>list);//大批量添加20160216
}
