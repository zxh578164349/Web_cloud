package dao;

import java.util.List;

import util.PageBean;

import entity.WebFactorder;

public interface IWebFactorderDao {
	public void add(WebFactorder order);
	public void addLarge(List<String>list);//大批量添加
	public PageBean findPageBean(int pageSize,int page,List<String>factNos,String brank,String customer,String model,String component);

}
