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


	public PageBean findPageBean(int pageSize, int page, List<String> factNos,
			String brank, String customer, String model, String component) {
		// TODO Auto-generated method stub
		return factorderdao.findPageBean(pageSize, page, factNos, brank, customer, model, component);
	}


	public List<String> findComponent() {
		// TODO Auto-generated method stub
		return factorderdao.findComponent();
	}


	public List<String> findBrank() {
		// TODO Auto-generated method stub
		return factorderdao.findBrank();
	}


	public List<String> findCustomer() {
		// TODO Auto-generated method stub
		return factorderdao.findCustomer();
	}


	public List<String> findModel() {
		// TODO Auto-generated method stub
		return factorderdao.findModel();
	}
	
}
