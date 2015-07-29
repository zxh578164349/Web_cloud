package dao.impl;

import org.hibernate.Query;

import dao.Basedao;
import dao.IVWebStoreDao;
import entity.VWebstore;

public class VWebStoreDaoImpl extends Basedao implements IVWebStoreDao{

	public VWebstore findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		String hql="from VWebstore where id.factNo=? and id.factCode=? and id.yymm=?";
		Query query=getSession().createQuery(hql);
		query.setString(0,factNo);
		query.setString(1, factCode);
		query.setString(2, yymm);
		VWebstore store=(VWebstore)query.uniqueResult();
		return store;
	}

}
