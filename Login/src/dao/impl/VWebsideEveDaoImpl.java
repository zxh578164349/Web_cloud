package dao.impl;

import org.hibernate.Query;

import dao.Basedao;
import dao.IVWebsideEveDao;
import entity.VWebsideEve;

public class VWebsideEveDaoImpl extends Basedao implements IVWebsideEveDao{

	public VWebsideEve findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		String hql="from VWebsideEve where id.factNo=? and id.factCode=? and id.yymm=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, yymm);
		VWebsideEve eve=(VWebsideEve)query.uniqueResult();
		return eve;
	}

}
