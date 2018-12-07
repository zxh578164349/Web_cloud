package dao;

import util.PageBean;
import entity.KyzExpectmatmLog;
import entity.WebColproductItems;
import entity.WebColproductMain;

public interface IWebColproductMainDao {
	public void add(WebColproductMain obj);
	public WebColproductMain findByBillNo(String billNo);
	public void delete(String billNo,KyzExpectmatmLog log);
	public PageBean findPageBean(int page,int pageSize,String factNo,String billNo,String dateA,String dateB);
	public PageBean findPageBeanMain(int page,int pageSize,String factNo,String billNo,String dateA,String dateB,String title);	
	public String findByfactNoACreatedate(String factNo,String createDate);
	
	

}
