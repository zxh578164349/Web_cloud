package services.impl;

import services.IVKpifactEveServices;
import dao.IVKpifactEveDao;
import entity.VKpifactEve;

public class VKpifactEveServicesImpl implements IVKpifactEveServices{
	private IVKpifactEveDao vkpievedao;
	

	public void setVkpievedao(IVKpifactEveDao vkpievedao) {
		this.vkpievedao = vkpievedao;
	}


	public VKpifactEve findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		return vkpievedao.findById(factNo, factCode, yymm);
	}

}
