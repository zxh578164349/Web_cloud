package services.impl;

import dao.IVSumstoreDao;
import entity.VSumstore;
import services.IVSumstoreServices;

public class VSumstoreServicesImpl implements IVSumstoreServices{
	private IVSumstoreDao sumstoredao;
	

	public void setSumstoredao(IVSumstoreDao sumstoredao) {
		this.sumstoredao = sumstoredao;
	}


	public VSumstore findById(String factNo, String yymm) {
		// TODO Auto-generated method stub
		return sumstoredao.findById(factNo, yymm);
	}

}
