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


	public void addLarge(List<String> list) {
		// TODO Auto-generated method stub
			factorderdao.addLarge(list);		
	}


	public PageBean findPageBean(int pageSize,int page,List<String>factSnames,List<String>brank,List<String>customer,List<String>model,List<String>component,String year,String factNo){
		// TODO Auto-generated method stub
		return factorderdao.findPageBean(pageSize, page, factSnames, brank, customer, model, component,year,factNo);
	}
	public List<String> findComponent(List<String> factNos) {
		// TODO Auto-generated method stub
		return factorderdao.findComponent(factNos);
	}


	public List<String> findBrank(List<String> factNos) {
		// TODO Auto-generated method stub
		return factorderdao.findBrank(factNos);
	}


	public List<String> findCustomer(List<String> factNos) {
		// TODO Auto-generated method stub
		return factorderdao.findCustomer(factNos);
	}


	public List<String> findModel(List<String> factNos) {
		// TODO Auto-generated method stub
		return factorderdao.findModel(factNos);
	}
	
	public List<String> findFactSname(List<String> factNos) {
		// TODO Auto-generated method stub
		return factorderdao.findFactSname(factNos);
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


	public List<Object[]> findByGroup(List<String> factSnames,
			List<String> brank, List<String> customer, List<String> model,
			List<String> component, String year,String factNo) {
		// TODO Auto-generated method stub
		return factorderdao.findByGroup(factSnames, brank, customer, model, component, year,factNo);
	}


	public List<Object[]> findByGroup2(List<String> factSnames,
			List<String> brank, List<String> customer, List<String> model,
			List<String> component, String year,String factNo) {
		// TODO Auto-generated method stub
		return factorderdao.findByGroup2(factSnames, brank, customer, model, component, year,factNo);
	}


	public void addLarge2(List<List<String>> list) {
		// TODO Auto-generated method stub
		factorderdao.addLarge2(list);
	}


	
	
}
