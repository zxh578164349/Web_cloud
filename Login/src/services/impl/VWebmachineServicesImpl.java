package services.impl;

import services.IVWebmachineServices;
import dao.IVWebmachineDao;
import entity.VWebmachine;

public class VWebmachineServicesImpl implements IVWebmachineServices{
	private IVWebmachineDao vwebmachinedao;
	

	public void setVwebmachinedao(IVWebmachineDao vwebmachinedao) {
		this.vwebmachinedao = vwebmachinedao;
	}


	public VWebmachine findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		return vwebmachinedao.findById(factNo, factCode, yymm);
	}

}
