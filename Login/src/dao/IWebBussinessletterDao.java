package dao;

import java.util.List;

import util.PageBean;
import entity.WebBussinessletter;

public interface IWebBussinessletterDao {
	public void add(WebBussinessletter buss);
	public PageBean findPageBean(int pageSize,int page,String billNo,String factNo);
	public String findMaxBillNo(String factNo,String createDate);
	public WebBussinessletter findById(String billNo);
	public void delete(WebBussinessletter letter);
	public void delete(String billNo);

}
