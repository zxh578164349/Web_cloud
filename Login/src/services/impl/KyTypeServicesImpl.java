package services.impl;

import java.util.ArrayList;
import java.util.List;

import dao.IKyTypeDao;

import entity.KyType;
import services.IKyTypeServices;

public class KyTypeServicesImpl implements IKyTypeServices{
	private IKyTypeDao kytypeDao;

	public void setKytypeDao(IKyTypeDao kytypeDao) {
		this.kytypeDao = kytypeDao;
	}

	public List<KyType> findByTypeNo(String typeNo) {
		// TODO Auto-generated method stub
		List<KyType>oldlist=kytypeDao.findByTypeNo(typeNo);
		List<KyType>newlist=new ArrayList();
		for(int i=0;i<oldlist.size();i++){
			KyType type=new KyType();
			type.setTypeName(oldlist.get(i).getId().getTypeSno());//因為dwr的問題,以typename代替id.typeSno
			type.setTypeSname(oldlist.get(i).getTypeSname());
			newlist.add(type);
		}
		return newlist;
	}
	
	public List<KyType> findByTypeNo_action(String typeNo){
		return kytypeDao.findByTypeNo2(typeNo);
	}

	public String getTypeSname(String typNo, String typeSno) {
		// TODO Auto-generated method stub
		return kytypeDao.getTypeSname(typNo, typeSno);
	}


}
