package services.impl;

import java.util.List;

import org.hibernate.Transaction;

import dao.IWebFactorderDao;
import services.IWebFactorderServices;
import util.PageBean;
import entity.KyzExpectmatmLog;
import entity.WebFact;
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
	public WebFactorder findByOrderId(String factNo, String factArea,
			String yymm, String modelNo, String customer, String brank,
			String component) {
		// TODO Auto-generated method stub
		return factorderdao.findByOrderId(factNo, factArea, yymm, modelNo, customer, brank, component);
	}


	public void delete(String factNo, String factArea, String yymm,
			String modelNo, String customer, String brank, String component,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		factorderdao.delete(factNo, factArea, yymm, modelNo, customer, brank, component,delLog);
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


	/**
	 * 日期:2016/4/1
	 * 描述:
	 */
	
	
	public void deleteMore(List<String> factAreas, List<String> brank,
			List<String> customer, List<String> model, List<String> component,
			String factNo, List<String> factNos, String yymm, String yymm2) {
		// TODO Auto-generated method stub
		factorderdao.deleteMore(factAreas, brank, customer, model, component, factNo, factNos, yymm, yymm2);
	}


	/**
	 * 日期:2016/6/8
	 * 描述:
	 */
	
	
	public List<WebFact> findNoinput(String yymm) {
		// TODO Auto-generated method stub
		return factorderdao.findNoinput(yymm);
	}


	


	

	


	
	
}
