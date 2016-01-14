package dao;

import java.util.List;

import util.PageBean;

import entity.WebFactorder;

public interface IWebFactorderDao {
	public void add(WebFactorder order);
	public void addLarge(List<String>list);//大批量添加
	public PageBean findPageBean(int pageSize,int page,List<String>factSnames,List<String>brank,List<String>customer,List<String>model,List<String>component);
	public List<String> findComponent(List<String> factSname);//目前所有的部件
	public List<String> findBrank(List<String> factSname);//目前所有的品牌
	public List<String> findCustomer(List<String> factSname);//目前所有的客户
	public List<String> findModel(List<String> factSname);//目前所有的model
	public List<String> findFactSname();//目前所有的廠名
	public List<WebFactorder>findWithNoPage(List<String>factSnames,List<String>brank,List<String>customer,List<String>model,List<String>component,String yymm);
	public int findMonthData(String factSname,String brank,String customer,String model,String component,String year);
	public List<Object[]>findWebFactorder(List<String>factNos,List<String>brank,List<String>customer,List<String>model,List<String>component,String yymm);
	public List<Double> findOrderdata(String factSname,String brank,String customer,String model,String component,String yymm);
	
	
	
	

}
