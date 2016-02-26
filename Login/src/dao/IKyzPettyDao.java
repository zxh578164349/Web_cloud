package dao;

import java.util.List;

import entity.KyzExpectmatmLog;
import entity.KyzPetty;
import util.PageBean;

public interface IKyzPettyDao {
	public PageBean findPageBean(int pageSize,int page,String factNo,String billNo,String createTime);
	public void add(KyzPetty kyzpetty);
	public List<KyzPetty> findByFactNoAndPayDate(String factNo,String payDate);
	public KyzPetty findById(String factNo,String billNo);
	public void delete(String factNo,String billNo,KyzExpectmatmLog delLog);
	
	public List<KyzPetty> findByAnyThing(String factNo,String dateTime,String dateTime2,String expenseMk,String taxmMk);		
}
