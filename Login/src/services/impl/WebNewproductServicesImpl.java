package services.impl;

import java.util.List;

import dao.IWebNewproductDao;
import entity.KyzExpectmatmLog;
import entity.WebNewproduct;
import services.IWebNewproductServices;
import util.PageBean;

public class WebNewproductServicesImpl implements IWebNewproductServices{
	private IWebNewproductDao webnewprodao;
	

	public void setWebnewprodao(IWebNewproductDao webnewprodao) {
		this.webnewprodao = webnewprodao;
	}

	public void add(WebNewproduct obj) {
		// TODO Auto-generated method stub		
		webnewprodao.add(obj);
	}

	public PageBean findPageBean(int page, int pageSize, String factNo,
			String billNo, String createDateA, String createDateB) {
		// TODO Auto-generated method stub
		return webnewprodao.findPageBean(page, pageSize, factNo, billNo, createDateA, createDateB);
	}
	
	public void delete(String billNo, KyzExpectmatmLog log) {
		// TODO Auto-generated method stub
		webnewprodao.delete(billNo, log);
	}

	public WebNewproduct findByBillNo(String billNo) {
		// TODO Auto-generated method stub
		return webnewprodao.findByBillNo(billNo);
	}

	public String findByfactNoACreatedate(String factNo, String createDate) {
		// TODO Auto-generated method stub
		return webnewprodao.findByfactNoACreatedate(factNo, createDate);
	}

}
