package dao;

import java.util.List;

import util.PageBean;
import entity.KyzExpectmatmLog;
import entity.WebNewproduct;

public interface IWebNewproductDao {
	public void add(WebNewproduct obj);
	public PageBean findPageBean(int page,int pageSize,String factNo,String billNo,String createDateA,String createDateB);
	public void delete(String billNo,KyzExpectmatmLog log);
	public WebNewproduct findByBillNo(String billNo);
	public String findByfactNoACreatedate(String factNo,String createDate);

}
