package services;

import java.util.Map;

import util.PageBean;
import entity.KyVisabillm;
import entity.KyzExpectmatmLog;
import entity.WebColproductItems;
import entity.WebColproductMain;

public interface IWebColproductMainServices {
	public void add(WebColproductMain obj);
	public WebColproductMain findByBillNo(String billNo);
	public void delete(String billNo,KyzExpectmatmLog log);
	public PageBean findPageBean(int page,int pageSize,String factNo,String billNo,String dateA,String dateB);
	public PageBean findPageBeanMain(int page,int pageSize,String factNo,String billNo,String dateA,String dateB,String title);
	public String findByfactNoACreatedate(String factNo,String createDate);		
	public Map<String,Object> print(String factNo,String billNo,String sort,KyVisabillm vbm);

}
