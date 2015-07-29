package dao.impl;

import org.hibernate.Query;

import dao.Basedao;
import dao.IVWebpersonEveDao;
import entity.VWebpersonEve;

public class VWebpersonEveDaoImpl extends Basedao implements IVWebpersonEveDao{

	public VWebpersonEve findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		String hql="from VWebpersonEve where id.factNo=? and id.factCode=? and id.yymm=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, yymm);
		VWebpersonEve eve=(VWebpersonEve)query.uniqueResult();
		return eve;
	}

}
