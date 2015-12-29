package services.impl;

import java.util.List;

import dao.IWebFactorderDao;
import services.IWebFactorderServices;
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
	
}
