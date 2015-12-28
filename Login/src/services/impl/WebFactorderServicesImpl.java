package services.impl;

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
	
}
