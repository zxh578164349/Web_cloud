package dao;

import java.util.List;

import util.PageBean;

import entity.WebFactorder;

public interface IWebFactorderDao {
	public void add(WebFactorder order);
	public void addLarge(List<String>list);//大批量添加
	public PageBean findPageBean(int pageSize,int page,List<String>factNos,String brank,String customer,String model,String component);
	public List<String> findComponent();//目前所有的部件
	public List<String> findBrank();//目前所有的品牌
	public List<String> findCustomer();//目前所有的客户
	public List<String> findModel();//目前所有的model
	
	

}
