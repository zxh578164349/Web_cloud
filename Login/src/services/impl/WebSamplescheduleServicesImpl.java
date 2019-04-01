package services.impl;

import java.util.List;

import dao.IWebSamplescheduleDao;

import entity.WebSampleschedule;
import services.IWebSamplescheduleServices;
import util.PageBean;

public class WebSamplescheduleServicesImpl implements IWebSamplescheduleServices{
	private IWebSamplescheduleDao websampdao;
	
	

	public void setWebsampdao(IWebSamplescheduleDao websampdao) {
		this.websampdao = websampdao;
	}

	public PageBean findPageBean(int page, int pageSize, String stype,
			String dateA,  String dateB,String samplelevel, String brand,
			String customer) {
		// TODO Auto-generated method stub
		return websampdao.findPageBean(page, pageSize, stype, dateA,  dateB,samplelevel, brand, customer);
	}

	public void addMore(List<WebSampleschedule> list) {
		// TODO Auto-generated method stub
		websampdao.addMore(list);
	}

}
