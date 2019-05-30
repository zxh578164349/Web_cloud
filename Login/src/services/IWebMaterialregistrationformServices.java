package services;

import java.util.List;

import util.PageBean;
import entity.WebMaterialregistrationform;
import entity.WebMaterialregistrationitems;

public interface IWebMaterialregistrationformServices {
	public PageBean findPageBean(int page,int pageSize,String mtype,String dateaA,String dateaB,String materielname);
	public List<WebMaterialregistrationitems> findObjsWithNopage(String mtype,String dateaA,String dateaB,String materielname);
	public void addMore(List<WebMaterialregistrationform>list);
	public List<String> findmtype();

}
