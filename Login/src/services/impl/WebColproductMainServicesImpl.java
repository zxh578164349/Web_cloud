package services.impl;

import dao.IWebColproductMainDao;
import entity.KyzExpectmatmLog;
import entity.WebColproductItems;
import entity.WebColproductMain;
import services.IWebColproductMainServices;
import util.PageBean;

public class WebColproductMainServicesImpl implements IWebColproductMainServices{
	private IWebColproductMainDao webcolprodao;
		
	public void setWebcolprodao(IWebColproductMainDao webcolprodao) {
		this.webcolprodao = webcolprodao;
	}

	public void add(WebColproductMain obj) {
		// TODO Auto-generated method stub
		webcolprodao.add(obj);
	}

	public WebColproductMain findByBillNo(String billNo) {
		// TODO Auto-generated method stub
		WebColproductMain obj=webcolprodao.findByBillNo(billNo);
		return obj;
	}

	public void delete(String billNo, KyzExpectmatmLog log) {
		// TODO Auto-generated method stub
		webcolprodao.delete(billNo, log);
	}

	public PageBean findPageBean(int page, int pageSize, String factNo,
			String billNo, String dateA, String dateB) {
		// TODO Auto-generated method stub
		return webcolprodao.findPageBean(page, pageSize, factNo, billNo, dateA, dateB);
	}

	public WebColproductItems findById(int iid) {
		// TODO Auto-generated method stub
		return webcolprodao.findById(iid);
	}

	public void delete_item(int iid, KyzExpectmatmLog log) {
		// TODO Auto-generated method stub		
		webcolprodao.delete_item(iid, log);
	}

	public String findByfactNoACreatedate(String factNo, String createDate) {
		// TODO Auto-generated method stub
		return webcolprodao.findByfactNoACreatedate(factNo, createDate);
	}

}
