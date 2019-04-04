package services;

import java.util.List;

import util.PageBean;
import entity.WebMaterialregistrationform;

public interface IWebMaterialregistrationformServices {
	public PageBean findPageBean(int page,int pageSize,String mtype,String dateaA,String dateaB,String materielname);
	public void addMore(List<WebMaterialregistrationform>list);
	public List<String> findmtype();

}
