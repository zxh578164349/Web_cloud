package services;

import java.util.List;

import util.PageBean;
import entity.WebTestmouldregistrationform;

public interface IWebTestmouldregistrationformServices {
	public PageBean findPageBean(int page,int pageSize,String dateA,String dateB,String customer,String brand);
	public void addMore(List<WebTestmouldregistrationform>list);

}
