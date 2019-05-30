package services.impl;

import java.util.List;

import dao.IWebMaterialregistrationformDao;

import entity.WebMaterialregistrationform;
import entity.WebMaterialregistrationitems;
import services.IWebMaterialregistrationformServices;
import util.PageBean;

public class WebMaterialregistrationformServicesImpl implements IWebMaterialregistrationformServices{
	
	private IWebMaterialregistrationformDao webmateriadao;
	
	

	public void setWebmateriadao(IWebMaterialregistrationformDao webmateriadao) {
		this.webmateriadao = webmateriadao;
	}

	public PageBean findPageBean(int page, int pageSize, String mtype,
			String dateaA, String dateaB,String materielname) {
		// TODO Auto-generated method stub
		return webmateriadao.findPageBean(page, pageSize, mtype, dateaA, dateaB,materielname);
	}

	public void addMore(List<WebMaterialregistrationform> list) {
		// TODO Auto-generated method stub
		webmateriadao.addMore(list);
	}

	public List<String> findmtype() {
		// TODO Auto-generated method stub
		return webmateriadao.findmtype();
	}

	public List<WebMaterialregistrationitems> findObjsWithNopage(String mtype,
			String dateaA, String dateaB, String materielname) {
		// TODO Auto-generated method stub
		return webmateriadao.findObjsWithNopage(mtype, dateaA, dateaB, materielname);
	}

}
