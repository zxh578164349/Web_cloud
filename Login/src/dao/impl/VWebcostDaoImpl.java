package dao.impl;

import org.hibernate.Query;

import dao.Basedao;
import dao.IVWebcostDao;
import entity.VWebcost;

public class VWebcostDaoImpl extends Basedao implements IVWebcostDao{

	public VWebcost findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		String hql="from VWebcost where id.factNo=? and id.factCode=? and id.yymm=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, yymm);
		VWebcost obj=(VWebcost)query.uniqueResult();
		return obj;
	}

}
