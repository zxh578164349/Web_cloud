package services.impl;

import dao.IWebmonthsDao;
import entity.WebMonths;
import services.IWebmonthsServices;

public class WebmonthsServicesImpl implements IWebmonthsServices{
	private IWebmonthsDao webmonthsdao;
	

	public void setWebmonthsdao(IWebmonthsDao webmonthsdao) {
		this.webmonthsdao = webmonthsdao;
	}

	public void addWebmonths(WebMonths obj) {
		// TODO Auto-generated method stub
		webmonthsdao.addWebmonths(obj);
	}

	public WebMonths findWebmonths(String yymm, String objType) {
		// TODO Auto-generated method stub
		return webmonthsdao.findWebmonths(yymm, objType);
	}

}
