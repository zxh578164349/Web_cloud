package services.impl;

import java.util.List;

import dao.IVWebydataawebcashoutDao;

import entity.VWebydataawebcashout;
import services.IVWebydataawebcashoutServices;

public class VWebydataawebcashoutServicesImpl implements IVWebydataawebcashoutServices{
	private IVWebydataawebcashoutDao vcashoutDao;
	

	public void setVcashoutDao(IVWebydataawebcashoutDao vcashoutDao) {
		this.vcashoutDao = vcashoutDao;
	}

	public List<VWebydataawebcashout> findById(String factNo, String factCode,
			String yymm) {
		// TODO Auto-generated method stub
		return vcashoutDao.findById(factNo, factCode, yymm);
	}

	public VWebydataawebcashout findByIdOne(String factNo, String factCode,
			String yymmdd) {
		// TODO Auto-generated method stub
		return vcashoutDao.findByIdOne(factNo, factCode, yymmdd);
	}

}
