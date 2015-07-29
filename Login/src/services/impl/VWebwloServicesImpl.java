package services.impl;

import dao.IVWebwloDao;
import entity.VWebwlo;
import services.IVWebwloServices;

public class VWebwloServicesImpl implements IVWebwloServices{
	private IVWebwloDao vwlodao;
	

	public void setVwlodao(IVWebwloDao vwlodao) {
		this.vwlodao = vwlodao;
	}


	public VWebwlo findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		return vwlodao.findById(factNo, factCode, yymm);
	}

}
