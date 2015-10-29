package dao.impl;

import org.hibernate.Query;

import dao.Basedao;
import dao.IVSumstoreDao;
import entity.VSumstore;

public class VSumstoreDaoImpl extends Basedao implements IVSumstoreDao{

	public VSumstore findById(String factNo, String yymm) {
		// TODO Auto-generated method stub
		String hql="from VSumstore where id.factNo=? and id.yymm=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, yymm);
		VSumstore store=(VSumstore)query.uniqueResult();
		return store;
	}

}
