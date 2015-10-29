package services.impl;

import java.util.List;

import dao.IKpifactDao;
import entity.Kpifact;
import services.IKpifactServices;
import util.PageBean;

public class KpifactServicesImpl implements IKpifactServices{
	private IKpifactDao kpidao;
	

	public void setKpidao(IKpifactDao kpidao) {
		this.kpidao = kpidao;
	}

	public void add(Kpifact kpi) {
		// TODO Auto-generated method stub
		kpidao.add(kpi);
	}

	public Kpifact findById(String factNo, String factCode, String yyyy) {
		// TODO Auto-generated method stub
		Kpifact kpi=kpidao.findById(factNo, factCode, yyyy);
		return kpi;
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yyyy) {
		// TODO Auto-generated method stub
		return kpidao.findPageBean(pageSize, page, factNo, yyyy);
	}

	public void delete(String factNo, String factCode, String yyyy) {
		// TODO Auto-generated method stub
		kpidao.delete(factNo, factCode, yyyy);
	}

	public List<String> findDateByFactNo(String factNo,String yyyy) {
		// TODO Auto-generated method stub
		return kpidao.findDateByFactNo(factNo,yyyy);
	}

}
