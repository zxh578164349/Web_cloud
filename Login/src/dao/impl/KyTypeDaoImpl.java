package dao.impl;

import java.util.List;

import dao.Basedao;
import dao.IKyTypeDao;
import entity.KyType;

public class KyTypeDaoImpl extends Basedao implements IKyTypeDao{

	public List<KyType> findByTypeNo(String typeNo) {
		// TODO Auto-generated method stub
		String hql="from KyType where id.typeNo=? and id.typeSno like 'C%' order by id.typeSno";
		String[]objs={typeNo};
		return super.findAll(hql, objs);
	}

	public List<KyType> findByTypeNo2(String typeNo) {
		// TODO Auto-generated method stub
		String hql="from KyType where id.typeNo=? order by id.typeSno";
		String[]objs={typeNo};
		return super.findAll(hql, objs);
	}


}
