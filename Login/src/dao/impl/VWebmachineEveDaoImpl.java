package dao.impl;

import org.hibernate.Query;

import dao.Basedao;
import dao.IVWebmachineEveDao;
import entity.VWebmachineEve;

public class VWebmachineEveDaoImpl extends Basedao implements IVWebmachineEveDao{

	public VWebmachineEve findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		String hql="from VWebmachineEve where id.factNo=? and id.factCode=? and id.yymm=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, yymm);
		VWebmachineEve eve=(VWebmachineEve)query.uniqueResult();
		return eve;
	}

}
