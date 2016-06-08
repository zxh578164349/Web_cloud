package services;

import java.util.List;

import org.hibernate.Transaction;

import util.PageBean;

import entity.KyzExpectmatmLog;
import entity.WebFact;
import entity.WebFactorder;

public interface IWebFactorderServices {
	public void add(WebFactorder order);
	public WebFactorder findByOrderId(String factNo,String factArea,String yymm,String modelNo,String customer,String brank,String component);
	public void delete(String factNo,String factArea,String yymm,String modelNo,String customer,String brank,String component,KyzExpectmatmLog delLog);
	public void addLarge2(List<List<String>>list,String username);//经过事务声明配置，大批量添加20160117
	public void large2(List<List<String>>list,String username);//不经过事务声明配置，大批量添加20160117
	public PageBean findPageBean(int pageSize,int page,List<String>factAreas,List<String>brank,List<String>customer,List<String>model,List<String>component,String factNo,List<String>factNos,String yymm,String yymm2);
	public List<String> findComponent(List<String> factNos,List<String> factAreas);//目前所有的部件
	public List<String> findBrank(List<String> factNos,List<String> factAreas);//目前所有的品牌
	public List<String> findCustomer(List<String> factNos,List<String> factAreas);//目前所有的客户
	public List<String> findModel(List<String> factNos,List<String> factAreas);//目前所有的model
	//public List<String> findFactSname(List<String> FactSnames);//目前所有的廠名
	public List<String> findFactArea(List<String> factNos);//所有的廠別狀態
	
	
	/**
	 * 分組統計
	 * @param factSnames
	 * @param brank
	 * @param customer
	 * @param model
	 * @param component
	 * @param yymm	 
	 * @return
	 */
	public List<Object[]>findByGroup(List<String>factNos,List<String>factAreas,List<String>brank,List<String>customer,List<String>model,List<String>component,String factNo,String yymm,String yymm2);
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
	public List<Object[]>findByGroup2(List<String>factNos,List<String>factAreas,List<String>brank,List<String>customer,List<String>model,List<String>component,String factNo,String yymm,String yymm2);

	/**
	 * 批量刪除
	 * @Title: deleteMore
	 * @Description: TODO
	 * @param @param factAreas
	 * @param @param brank
	 * @param @param customer
	 * @param @param model
	 * @param @param component
	 * @param @param factNo
	 * @param @param factNos
	 * @param @param yymm
	 * @param @param yymm2
	 * @return void
	 * @throws
	 * @author web
	 * @date 2016/4/1
	 */
	public void deleteMore(List<String>factAreas,List<String>brank,List<String>customer,List<String>model,List<String>component,String factNo,List<String>factNos,String yymm,String yymm2);
	
	/**
	 * 当月没有導入訂單的廠別
	 * @Title: findNoinput
	 * @Description: TODO
	 * @param @param yymm
	 * @param @return
	 * @return List<WebFact>
	 * @throws
	 * @author web
	 * @date 2016/6/8
	 */
	public List<WebFact>findNoinput(String yymm);
}
