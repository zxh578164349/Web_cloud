package services;

import java.util.List;
import java.util.Map;

import util.PageBean;
import entity.KyVisabillm;
import entity.KyzExpectmatmLog;
import entity.WebNewproduct;

public interface IWebNewproductServices {
	public void add(WebNewproduct obj);
	public PageBean findPageBean(int page,int pageSize,String factNo,String billNo,String createDateA,String createDateB,String PName);
	public void delete(String billNo,KyzExpectmatmLog log);
	public WebNewproduct findByBillNo(String billNo);
	public String findByfactNoACreatedate(String factNo,String createDate);
	public Map<String, Object> print(String factNo, String billNo, String sort,KyVisabillm vbm);

}
