package services.impl;

import java.util.List;

import dao.IVKpifactDao;

import entity.VKpifact;
import services.IVKpifactServices;

public class VKpifactServicesImpl implements IVKpifactServices{
	private IVKpifactDao vkpidao;
	

	public void setVkpidao(IVKpifactDao vkpidao) {
		this.vkpidao = vkpidao;
	}


	public List<VKpifact> findAll(String factNo,String yymm) {
		// TODO Auto-generated method stub		
		return vkpidao.findAll(factNo,yymm);
	}


	public VKpifact findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		return vkpidao.findById(factNo, factCode, yymm);
	}

}
