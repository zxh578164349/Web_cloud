package dao;

import java.util.List;

import util.PageBean;
import entity.KyzExpectmatmLog;
import entity.WebBussinessletter;
import entity.WebUser;

public interface IWebBussinessletterDao {
	public void add(WebBussinessletter buss);
	public PageBean findPageBean(int pageSize,int page,String billNo,String factNo,WebUser user);
	public String findMaxBillNo(String factNo,String createDate);
	public WebBussinessletter findById(String billNo);
	public void delete(WebBussinessletter letter,KyzExpectmatmLog delLog);
	public void delete(String billNo,KyzExpectmatmLog delLog);
	
	public List<WebBussinessletter>findBefor2Month();//兩箇月之前沒有添加刪除標記的函文20160216
	public void addLarge(List<WebBussinessletter>list);//大批量添加 20160216
	public String findBillNo(String billNo);

}
