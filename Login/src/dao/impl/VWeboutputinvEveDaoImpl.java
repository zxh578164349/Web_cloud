package dao.impl;

import org.hibernate.Query;

import dao.Basedao;
import dao.IVWeboutputinvEveDao;
import entity.VWeboutputinvEve;

public class VWeboutputinvEveDaoImpl extends Basedao implements IVWeboutputinvEveDao{

	public VWeboutputinvEve findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		String hql="from VWeboutputinvEve where id.factNo=? and id.factCode=? and id.yymm=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, yymm);
		VWeboutputinvEve eve=(VWeboutputinvEve)query.uniqueResult();
		return eve;
	}

}
