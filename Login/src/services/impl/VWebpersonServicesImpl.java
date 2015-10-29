package services.impl;

import dao.IVWebpersonDao;
import entity.VWebperson;
import services.IVWebpersonServices;

public class VWebpersonServicesImpl implements IVWebpersonServices{
	private IVWebpersonDao vpersondao;
	

	public void setVpersondao(IVWebpersonDao vpersondao) {
		this.vpersondao = vpersondao;
	}


	public VWebperson findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		return vpersondao.findById(factNo, factCode, yymm);
	}

}
