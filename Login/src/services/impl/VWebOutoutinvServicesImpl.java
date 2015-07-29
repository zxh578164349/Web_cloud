package services.impl;

import dao.IVWebOutoutinvDao;
import entity.VWeboutputinv;
import services.IVWebOutoutinvServices;

public class VWebOutoutinvServicesImpl implements IVWebOutoutinvServices{
	private IVWebOutoutinvDao vinvdao;
	

	public void setVinvdao(IVWebOutoutinvDao vinvdao) {
		this.vinvdao = vinvdao;
	}


	public VWeboutputinv findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		return vinvdao.findById(factNo, factCode, yymm);
	}

}
