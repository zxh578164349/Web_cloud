package dao;

import java.util.List;

import util.PageBean;
import entity.WebMaterialregistrationform;

public interface IWebMaterialregistrationformDao {
	public PageBean findPageBean(int page,int pageSize,String mtype,String sdate,String edate,String materielname);
	public void addMore(List<WebMaterialregistrationform>list);
	public List<String> findmtype();
	

}
