package services.impl;

import dao.IVSumwloDao;
import entity.VSumwlo;
import services.IVSumwloServices;

public class VSumwloServicesImpl implements IVSumwloServices{
	private IVSumwloDao sumwlodao;
	
	
	public void setSumwlodao(IVSumwloDao sumwlodao) {
		this.sumwlodao = sumwlodao;
	}

	public VSumwlo findById(String factNo, String yymm) {
		// TODO Auto-generated method stub
		return sumwlodao.findById(factNo, yymm);
	}

}
