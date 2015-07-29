package dao.impl;

import org.hibernate.Query;

import dao.Basedao;
import dao.IVWebwloEveDao;
import entity.VWebwloEve;

public class VWebwloEveDaoImpl extends Basedao implements IVWebwloEveDao{

	public VWebwloEve findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		String hql="from VWebwloEve where id.factNo=? and id.factCode=? and id.yymm=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, yymm);
		VWebwloEve eve=(VWebwloEve)query.uniqueResult();
		return eve;
	}

}
