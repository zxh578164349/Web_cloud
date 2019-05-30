package services;

import java.util.List;

import util.PageBean;
import entity.WebMonths;
import entity.WebTestmouldregistrationform;

public interface IWebTestmouldregistrationformServices {
	public PageBean findPageBean(int page,int pageSize,String dateA,String dateB,String customer,String brand);
	public void addMore(List<WebTestmouldregistrationform>list);
	public List<String> findBrand();
	public List<String> findCustomer();	
	public List<WebTestmouldregistrationform> findObjsWithNopage(String dateA,String dateB,String customer,String brand);

}
