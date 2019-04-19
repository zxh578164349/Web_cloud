package dao;

import java.util.List;

import util.PageBean;
import entity.WebSampleschedule;

public interface IWebSamplescheduleDao {
	public PageBean findPageBean(int page,int pageSize,String stype,String dateA,String dateB,String samplelevel,String brand,String customer);
	public void addMore(List<WebSampleschedule>list);
	public List<String> findBrand();
	public List<String> findCustomer();

}