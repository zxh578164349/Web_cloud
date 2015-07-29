package services.impl;

import dao.IVSuminvDao;
import entity.VSuminv;
import services.IVSuminvServices;

public class VSuminvServicesImpl implements IVSuminvServices{
	private IVSuminvDao suminvdao;
	

	public void setSuminvdao(IVSuminvDao suminvdao) {
		this.suminvdao = suminvdao;
	}


	public VSuminv findById(String factNo, String yymm) {
		// TODO Auto-generated method stub
		return suminvdao.findById(factNo, yymm);
	}

}
