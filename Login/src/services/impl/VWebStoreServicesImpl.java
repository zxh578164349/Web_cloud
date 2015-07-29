package services.impl;

import dao.IVWebStoreDao;
import entity.VWebstore;
import services.IVWebStoreServices;

public class VWebStoreServicesImpl implements IVWebStoreServices{
	private IVWebStoreDao vstoredao;
	

	public void setVstoredao(IVWebStoreDao vstoredao) {
		this.vstoredao = vstoredao;
	}


	public VWebstore findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		return vstoredao.findById(factNo, factCode, yymm);
	}

}
