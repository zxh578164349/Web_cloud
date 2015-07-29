package services.impl;

import dao.IVSumWebmix1Dao;
import entity.VSumWebmix1;
import services.IVSumWebmix1Services;

public class VSumWebmix1ServicesImpl implements IVSumWebmix1Services{
	private IVSumWebmix1Dao vmix1dao;
	

	public void setVmix1dao(IVSumWebmix1Dao vmix1dao) {
		this.vmix1dao = vmix1dao;
	}


	public VSumWebmix1 findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		return vmix1dao.findById(factNo, factCode, yymm);
	}

}
