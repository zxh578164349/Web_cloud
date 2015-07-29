package dao.impl;

import org.hibernate.Query;

import dao.Basedao;
import dao.IVWebgwEveDao;
import entity.VWebgwEve;

public class VWebgwEveDaoImpl extends Basedao implements IVWebgwEveDao{

	public VWebgwEve findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		String hql="from VWebgwEve where id.factNo=? and id.factCode=? and id.yymm=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, yymm);
		VWebgwEve eve=(VWebgwEve)query.uniqueResult();
		return eve;
	}

}
