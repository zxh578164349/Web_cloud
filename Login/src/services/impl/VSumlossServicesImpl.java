package services.impl;

import dao.IVSumlossDao;
import entity.VSumloss;
import services.IVSumlossServices;

public class VSumlossServicesImpl implements IVSumlossServices{
	private IVSumlossDao sumlossdao;
	

	public void setSumlossdao(IVSumlossDao sumlossdao) {
		this.sumlossdao = sumlossdao;
	}


	public VSumloss findById(String factNo, String yymm) {
		// TODO Auto-generated method stub
		return sumlossdao.findById(factNo, yymm);
	}

}
