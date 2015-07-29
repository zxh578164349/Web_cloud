package services.impl;

import dao.IVWebSideDao;
import entity.VWebside;
import services.IVWebSideServices;

public class VWebSideServicesImpl implements IVWebSideServices{
	private IVWebSideDao vsidedao;
	

	public void setVsidedao(IVWebSideDao vsidedao) {
		this.vsidedao = vsidedao;
	}


	public VWebside findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		return vsidedao.findById(factNo, factCode, yymm);
	}

}
