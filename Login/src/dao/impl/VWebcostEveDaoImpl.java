package dao.impl;

import org.hibernate.Query;

import dao.Basedao;
import dao.IVWebcostEveDao;
import entity.VWebcostEve;

public class VWebcostEveDaoImpl extends Basedao implements IVWebcostEveDao{

	public VWebcostEve findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		String hql="from VWebcostEve where id.factNo=? and id.factCode=? and id.yymm=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, yymm);
		VWebcostEve obj=(VWebcostEve)query.uniqueResult();
		return obj;
	}

}
