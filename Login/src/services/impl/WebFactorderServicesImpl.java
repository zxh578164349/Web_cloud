package services.impl;

import java.util.List;

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


	public PageBean findPageBean(int pageSize,int page,List<String>factSnames,List<String>brank,List<String>customer,List<String>model,List<String>component){
		// TODO Auto-generated method stub
		return factorderdao.findPageBean(pageSize, page, factSnames, brank, customer, model, component);
	}
	public List<String> findComponent(List<String> factSname) {
		// TODO Auto-generated method stub
		return factorderdao.findComponent(factSname);
	}


	public List<String> findBrank(List<String> factSname) {
		// TODO Auto-generated method stub
		return factorderdao.findBrank(factSname);
	}


	public List<String> findCustomer(List<String> factSname) {
		// TODO Auto-generated method stub
		return factorderdao.findCustomer(factSname);
	}


	public List<String> findModel(List<String> factSname) {
		// TODO Auto-generated method stub
		return factorderdao.findModel(factSname);
	}
	
	public List<String> findFactSname() {
		// TODO Auto-generated method stub
		return factorderdao.findFactSname();
	}


	public List<WebFactorder> findWithNoPage(List<String> factNos,
			List<String> brank, List<String> customer, List<String> model,
			List<String> component,String yymm) {
		// TODO Auto-generated method stub
		return factorderdao.findWithNoPage(factNos, brank, customer, model, component,yymm);
	}


	public int findMonthData(String factNo, String brank, String customer,
			String model, String component, String year) {
		// TODO Auto-generated method stub
		return factorderdao.findMonthData(factNo, brank, customer, model, component, year);
	}


	public List<Object[]> findWebFactorder(List<String> factNos,
			List<String> brank, List<String> customer, List<String> model,
			List<String> component, String yymm) {
		// TODO Auto-generated method stub
		return factorderdao.findWebFactorder(factNos, brank, customer, model, component, yymm);
	}


	public List<Double> findOrderdata(String factSname, String brank,
			String customer, String model, String component, String yymm) {
		// TODO Auto-generated method stub
		return factorderdao.findOrderdata(factSname, brank, customer, model, component, yymm);
	}


	
	
}
