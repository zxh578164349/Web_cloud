package services.impl;

import java.util.List;

import dao.IWebObjsADao;

import entity.KyzExpectmatmLog;
import entity.VWebobjA;
import entity.VWebobjA2;
import entity.VWebobjA3;
import entity.WebObjsA;
import services.IWebObjsAServices;
import util.PageBean;

public class WebObjsAServicesImpl implements IWebObjsAServices{
	private IWebObjsADao webobjadao;
	

	

	public void setWebobjadao(IWebObjsADao webobjadao) {
		this.webobjadao = webobjadao;
	}

	public void addMore(List<WebObjsA> list) {
		// TODO Auto-generated method stub
		webobjadao.addMore(list);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		return webobjadao.findPageBean(pageSize, page, factNo, yymm);
	}	

	public WebObjsA findById(String factNo, String factcode, String yymm) {
		// TODO Auto-generated method stub
		return webobjadao.findById(factNo, factcode, yymm);
	}

	public void delete(String factNo, String factCode, String yymm,
			KyzExpectmatmLog log) {
		// TODO Auto-generated method stub
		webobjadao.delete(factNo, factCode, yymm, log);
	}

	public List<WebObjsA> findByYymm(String factno, String yymm) {
		// TODO Auto-generated method stub
		return webobjadao.findByYymm(factno, yymm);
	}

	public List<VWebobjA> findByVwebobja(String yymm) {
		// TODO Auto-generated method stub
		return webobjadao.findByVwebobja(yymm);
	}

	public List<VWebobjA2> findByVwebobja2(String yymm) {
		// TODO Auto-generated method stub
		return webobjadao.findByVwebobja2(yymm);
	}

	public List<WebObjsA> findObjByDay(String yymmdd) {
		// TODO Auto-generated method stub
		return webobjadao.findObjByDay(yymmdd);
	}

	public List<VWebobjA3> findByVwebobja3(String yymmdd) {
		// TODO Auto-generated method stub
		return webobjadao.findByVwebobja3(yymmdd);
	}

}
