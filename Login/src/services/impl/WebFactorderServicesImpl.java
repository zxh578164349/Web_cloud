package services.impl;

import java.util.List;

import org.hibernate.Transaction;

import dao.IWebFactorderDao;
import services.IWebFactorderServices;
import util.PageBean;
import entity.WebFactorder;

public class WebFactorderServicesImpl implements IWebFactorderServices{
	private IWebFactorderDao factorderdao;
	

	public void setFactorderdao(IWebFactorderDao factorderdao) {
		this.factorderdao = factorderdao;
	}


	public void add(WebFactorder order) {
		// TODO Auto-generated method stub
		factorderdao.add(order);
	}
	public WebFactorder findByOrderId(long orderid) {
		// TODO Auto-generated method stub
		return factorderdao.findByOrderId(orderid);
	}


	public void delete(long orderid) {
		// TODO Auto-generated method stub
		factorderdao.delete(orderid);
	}


	public void addLarge(List<String> list) {
		// TODO Auto-generated method stub
			factorderdao.addLarge(list);		
	}


	public PageBean findPageBean(int pageSize,int page,List<String>factAreas,List<String>brank,List<String>customer,List<String>model,List<String>component,String factNo,List<String>factNos,String yymm,String yymm2){
		// TODO Auto-generated method stub
		return factorderdao.findPageBean(pageSize, page, factAreas, brank, customer, model, component,factNo,factNos,yymm,yymm2);
	}
	public List<String> findComponent(List<String> factNos,List<String> factAreas) {
		// TODO Auto-generated method stub
		return factorderdao.findComponent(factNos,factAreas);
	}


	public List<String> findBrank(List<String> factNos,List<String> factAreas) {
		// TODO Auto-generated method stub
		return factorderdao.findBrank(factNos,factAreas);
	}


	public List<String> findCustomer(List<String> factNos,List<String> factAreas) {
		// TODO Auto-generated method stub
		return factorderdao.findCustomer(factNos,factAreas);
	}


	public List<String> findModel(List<String> factNos,List<String> factAreas) {
		// TODO Auto-generated method stub
		return factorderdao.findModel(factNos,factAreas);
	}
	
	/*public List<String> findFactSname(List<String> factNos) {
		// TODO Auto-generated method stub
		return factorderdao.findFactSname(factNos);
	}*/
	
	public List<String> findFactArea(List<String> factNos) {
		// TODO Auto-generated method stub
		return factorderdao.findFactArea(factNos);
	}



	public List<WebFactorder> findWithNoPage(List<String> factNos,
			List<String> brank, List<String> customer, List<String> model,
			List<String> component,String year) {
		// TODO Auto-generated method stub
		return factorderdao.findWithNoPage(factNos, brank, customer, model, component,year);
	}


	public int findMonthData(String factNo, String brank, String customer,
			String model, String component, String year) {
		// TODO Auto-generated method stub
		return factorderdao.findMonthData(factNo, brank, customer, model, component, year);
	}


	public List<Object[]> findWebFactorder(List<String> factNos,
			List<String> brank, List<String> customer, List<String> model,
			List<String> component, String year) {
		// TODO Auto-generated method stub
		return factorderdao.findWebFactorder(factNos, brank, customer, model, component, year);
	}


	public List<Double> findOrderdata(String factSname, String brank,
			String customer, String model, String component, String yymm) {
		// TODO Auto-generated method stub
		return factorderdao.findOrderdata(factSname, brank, customer, model, component, yymm);
	}


	public List<Object[]> findByGroup(List<String>factNos,List<String>factAreas,
			List<String> brank, List<String> customer, List<String> model,
			List<String> component,String factNo,String yymm,String yymm2) {
		// TODO Auto-generated method stub
		return factorderdao.findByGroup(factNos,factAreas, brank, customer, model, component,factNo, yymm, yymm2);
	}


	public List<Object[]> findByGroup2(List<String>factNos,List<String>factAreas,
			List<String> brank, List<String> customer, List<String> model,
			List<String> component,String factNo,String yymm,String yymm2) {
		// TODO Auto-generated method stub
		return factorderdao.findByGroup2(factNos,factAreas, brank, customer, model, component,factNo, yymm,yymm2);
	}


	/**
	 * 经过事务声明配置（方法要以add开头）
	 */
	public void addLarge2(List<List<String>> list,String username) {
		// TODO Auto-generated method stub
		factorderdao.addLarge2(list,username);
	}
	/**
	 * 不经过事务声明配置（方法不能以add开头）
	 */
	public void large2(List<List<String>> list,String username) {
		// TODO Auto-generated method stub
		factorderdao.addLarge3(list,username);
	}


	

	


	
	
}
