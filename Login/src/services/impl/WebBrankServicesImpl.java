package services.impl;

import java.util.List;

import dao.IWebBrankDao;

import entity.WebBrank;
import services.IWebBrankServices;

public class WebBrankServicesImpl implements IWebBrankServices{
	private IWebBrankDao webbrankdao;
	

	public void setWebbrankdao(IWebBrankDao webbrankdao) {
		this.webbrankdao = webbrankdao;
	}


	public List<WebBrank> findAll() {
		// TODO Auto-generated method stub
		return webbrankdao.findAll();
	}

}
