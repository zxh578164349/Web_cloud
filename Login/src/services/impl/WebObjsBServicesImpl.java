package services.impl;

import java.util.List;

import dao.IWebObjsBDao;

import entity.KyzExpectmatmLog;
import entity.VWebobjA;
import entity.VWebobjA2;
import entity.VWebobjA3;
import entity.VWebobjBObj;
import entity.VWebobjBObj3;
import entity.VWebobjBObj4;
import entity.VWebobjBObj5;
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

	public List<VWebobjBObj> findByYymm2(String factno, String yymm) {
		// TODO Auto-generated method stub
		return webobjbdao.findByYymm2(factno, yymm);
	}
			
	public List<VWebobjBObj3> findByVwebobjb3(String yymmdd) {
		// TODO Auto-generated method stub
		return webobjbdao.findByVwebobjb3(yymmdd);
	}


	public List<VWebobjBObj> findObjByDay(String yymmdd) {
		// TODO Auto-generated method stub
		return webobjbdao.findObjByDay(yymmdd);
	}


	public List<VWebobjBObj> findObjByMonth(String yymm) {
		// TODO Auto-generated method stub
		return webobjbdao.findObjByMonth(yymm);
	}


	public List<String[]> findNoInput(String yymmdd) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<VWebobjBObj4> findVWebobjBObj4(String yymm) {
		// TODO Auto-generated method stub
		return webobjbdao.findVWebobjBObj4(yymm);
	}


	public List<VWebobjBObj5> findVWebobjBObj5(String yymm) {
		// TODO Auto-generated method stub
		return webobjbdao.findVWebobjBObj5(yymm);
	}


	public void add(WebObjsB obj) {
		// TODO Auto-generated method stub
		webobjbdao.add(obj);
	}


	

}
