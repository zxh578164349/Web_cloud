package services.impl;

import java.util.List;

import dao.IWebTestmouldregistrationformDao;

import entity.WebMonths;
import entity.WebTestmouldregistrationform;
import services.IWebTestmouldregistrationformServices;
import util.PageBean;

public class WebTestmouldregistrationformServicesImpl implements IWebTestmouldregistrationformServices{
	private IWebTestmouldregistrationformDao webtestregisformdao;
		
	public void setWebtestregisformdao(
			IWebTestmouldregistrationformDao webtestregisformdao) {
		this.webtestregisformdao = webtestregisformdao;
	}

	public PageBean findPageBean(int page, int pageSize, String dateA,
			String dateB, String customer, String brand) {
		// TODO Auto-generated method stub
		return webtestregisformdao.findPageBean(page, pageSize, dateA, dateB, customer, brand);
	}

	public void addMore(List<WebTestmouldregistrationform> list) {
		// TODO Auto-generated method stub
		webtestregisformdao.addMore(list);
	}

	public List<String> findBrand() {
		// TODO Auto-generated method stub		
		return webtestregisformdao.findBrand();
	}

	public List<String> findCustomer() {
		// TODO Auto-generated method stub
		return webtestregisformdao.findCustomer();
	}
	

}
