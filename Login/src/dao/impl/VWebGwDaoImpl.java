package dao.impl;

import org.hibernate.Query;

import dao.Basedao;
import dao.IVWebGwDao;
import entity.VWebgw;

public class VWebGwDaoImpl extends Basedao implements IVWebGwDao{

	public VWebgw findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		String hql="from VWebgw where id.factNo=? and id.factCode=? and id.yymm=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, yymm);
		VWebgw gw=(VWebgw)query.uniqueResult();
		return gw;
	}

}
