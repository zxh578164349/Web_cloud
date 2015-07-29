package dao.impl;

import org.hibernate.Query;

import dao.Basedao;
import dao.IVWebstoreEveDao;
import entity.VWebstoreEve;

public class VWebstoreEveDaoImpl extends Basedao implements IVWebstoreEveDao{

	public VWebstoreEve findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		String hql="from VWebstoreEve where id.factNo=? and id.factCode=? and id.yymm=?";
		Query query=getSession().createQuery(hql);
		query.setString(0,factNo);
		query.setString(1, factCode);
		query.setString(2, yymm);
		VWebstoreEve eve=(VWebstoreEve)query.uniqueResult();
		return eve;
	}

}
