package dao.impl;

import org.hibernate.Query;

import dao.Basedao;
import dao.IVKpifactEveDao;
import entity.VKpifactEve;

public class VKpifactEveDaoImpl extends Basedao implements IVKpifactEveDao{

	public VKpifactEve findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		String hql="from VKpifactEve where id.factNo=? and id.factCode=? and id.yymm=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, yymm);
		VKpifactEve eve=(VKpifactEve)query.uniqueResult();
		return eve;
	}

}
