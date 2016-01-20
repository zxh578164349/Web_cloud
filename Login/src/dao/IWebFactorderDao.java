package dao;

import java.util.List;

import org.hibernate.Transaction;

import util.PageBean;

import entity.WebFactorder;

public interface IWebFactorderDao {
	public void add(WebFactorder order);
	public WebFactorder findByOrderId(long orderid);
	public void delete(long orderid);
	public void addLarge(List<String>list);//大批量添加
	public void addLarge2(List<List<String>>list);//大批量導入數據20160117(修改版)
	public PageBean findPageBean(int pageSize,int page,List<String>factSnames,List<String>brank,List<String>customer,List<String>model,List<String>component,String year,String factNo,List<String>factNos);
	public List<String> findComponent(List<String> FactSnames);//目前所有的部件
	public List<String> findBrank(List<String> FactSnames);//目前所有的品牌
	public List<String> findCustomer(List<String> FactSnames);//目前所有的客户
	public List<String> findModel(List<String> FactSnames);//目前所有的model
	public List<String> findFactSname(List<String> FactSnames);//目前所有的廠名
	
	public List<WebFactorder>findWithNoPage(List<String>factSnames,List<String>brank,List<String>customer,List<String>model,List<String>component,String yymm);
	public int findMonthData(String factSname,String brank,String customer,String model,String component,String year);
	public List<Object[]>findWebFactorder(List<String>factNos,List<String>brank,List<String>customer,List<String>model,List<String>component,String yymm);
	public List<Double> findOrderdata(String factSname,String brank,String customer,String model,String component,String yymm);
	
	/**
	 * 不定向分组统计
	 * @param factSnames
	 * @param brank
	 * @param customer
	 * @param model
	 * @param component
	 * @param year
	 * @return
	 */
	public List<Object[]>findByGroup(List<String>factSnames,List<String>brank,List<String>customer,List<String>model,List<String>component,String factNo,String yymm,String yymm2);
	
	/**
	 * 在不定向分组统计的基础上，找出一年中不重复的（厂名+品牌+客户+模具+部件）
	 * @param factSnames
	 * @param brank
	 * @param customer
	 * @param model
	 * @param component
	 * @param year
	 * @return
	 */
	public List<Object[]>findByGroup2(List<String>factSnames,List<String>brank,List<String>customer,List<String>model,List<String>component,String factNo,String yymm,String yymm2);
	
	
	
		
}
