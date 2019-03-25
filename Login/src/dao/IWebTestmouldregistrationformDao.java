package dao;

import java.util.List;

import entity.WebTestmouldregistrationform;
import util.PageBean;

public interface IWebTestmouldregistrationformDao {
	public PageBean findPageBean(int page,int pageSize,String dateA,String dateB,String customer,String brand);
	public void addMore(List<WebTestmouldregistrationform>list);
	
}
