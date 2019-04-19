package services.impl;

import java.util.List;

import dao.IWebFormtypeDao;

import entity.WebFormtype;
import services.IWebFormtypeServices;

public class WebFormtypeServicesImpl implements IWebFormtypeServices{
	private IWebFormtypeDao webformtypedao;
	
	public void setWebformtypedao(IWebFormtypeDao webformtypedao) {
		this.webformtypedao = webformtypedao;
	}

	public List<Object[]> findWebformByFactnoTypeno(String factNo,
			String typeNo) {
		// TODO Auto-generated method stub
		return webformtypedao.findWebformByFactnoTypeno(factNo, typeNo);
	}

}
