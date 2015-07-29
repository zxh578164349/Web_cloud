package services.impl;

import dao.IVWebGwDao;
import entity.VWebgw;
import services.IVWebGwServices;

public class VWebGwServicesImpl implements IVWebGwServices{
	private IVWebGwDao vgwdao;
	

	public void setVgwdao(IVWebGwDao vgwdao) {
		this.vgwdao = vgwdao;
	}


	public VWebgw findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		return vgwdao.findById(factNo, factCode, yymm);
	}

}
