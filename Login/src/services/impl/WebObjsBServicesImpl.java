package services.impl;

import java.util.List;

import dao.IWebObjsBDao;

import entity.VWebobjBYdate;
import entity.VWebydatabyfcode2;
import services.IWebObjsBServices;

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

}
