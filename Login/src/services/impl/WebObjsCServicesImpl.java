package services.impl;

import java.util.List;

import dao.IWebObjsCDao;

import entity.KyzExpectmatmLog;
import entity.WebObjsC;
import services.IWebObjsCServices;
import util.PageBean;

public class WebObjsCServicesImpl implements IWebObjsCServices{
	private IWebObjsCDao webobjcdao;
	
	public void setWebobjcdao(IWebObjsCDao webobjcdao) {
		this.webobjcdao = webobjcdao;
	}
	
	public void add(WebObjsC obj){
		webobjcdao.add(obj);
	}

	public void addMore(List<WebObjsC> list) {
		// TODO Auto-generated method stub
		webobjcdao.addMore(list);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm,String yymmdd) {
		// TODO Auto-generated method stub
		return webobjcdao.findPageBean(pageSize, page, factNo, yymm,yymmdd);
	}

	public WebObjsC findById(String factNo, String factcode, String yymmdd) {
		// TODO Auto-generated method stub
		return webobjcdao.findById(factNo, factcode, yymmdd);
	}

	public void delete(String factNo, String factCode, String yymmdd,
			KyzExpectmatmLog log) {
		// TODO Auto-generated method stub
		webobjcdao.delete(factNo, factCode, yymmdd, log);
	}

	public List<String[]> findNoInput(String yymmdd) {
		// TODO Auto-generated method stub
		return webobjcdao.findNoInput(yymmdd);
	}

	public List<WebObjsC> findObjByDay(String yymmdd) {
		// TODO Auto-generated method stub
		return webobjcdao.findObjByDay(yymmdd);
	}

}
