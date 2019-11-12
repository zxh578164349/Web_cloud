package services.impl;

import java.util.List;

import dao.IWebObjsBDao;

import entity.KyzExpectmatmLog;
import entity.VWebobjA;
import entity.VWebobjA2;
import entity.VWebobjA3;
import entity.VWebobjBYdate;
import entity.VWebydatabyfcode2;
import entity.WebObjsB;
import services.IWebObjsBServices;
import util.PageBean;

public class WebObjsBServicesImpl implements IWebObjsBServices{
	private IWebObjsBDao webobjbdao;
	

	public void setWebobjbdao(IWebObjsBDao webobjbdao) {
		this.webobjbdao = webobjbdao;
	}


	public List<VWebobjBYdate> findByYymm(String yymm) {
		// TODO Auto-generated method stub
		return webobjbdao.findByYymm(yymm);
	}


	public List<VWebydatabyfcode2> findByYymm2(String yymm) {
		// TODO Auto-generated method stub
		return webobjbdao.findByYymm2(yymm);
	}


	public void addMore(List<WebObjsB> list) {
		// TODO Auto-generated method stub
		webobjbdao.addMore(list);
	}


	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		return webobjbdao.findPageBean(pageSize, page, factNo, yymm);
	}


	public WebObjsB findById(String factNo, String factcode, String yymm) {
		// TODO Auto-generated method stub
		return webobjbdao.findById(factNo, factcode, yymm);
	}


	public void delete(String factNo, String factCode, String yymm,
			KyzExpectmatmLog log) {
		// TODO Auto-generated method stub
		webobjbdao.delete(factNo, factCode, yymm, log);
	}


	public List<WebObjsB> findByYymm(String factno, String yymm) {
		// TODO Auto-generated method stub
		return webobjbdao.findByYymm(factno, yymm);
	}


	public List<VWebobjA> findByVwebobja(String yymm) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<VWebobjA2> findByVwebobja2(String yymm) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<VWebobjA3> findByVwebobja3(String yymmdd) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<WebObjsB> findObjByDay(String yymmdd) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<WebObjsB> findObjByMonth(String yymm) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<String[]> findNoInput(String yymmdd) {
		// TODO Auto-generated method stub
		return null;
	}

}
