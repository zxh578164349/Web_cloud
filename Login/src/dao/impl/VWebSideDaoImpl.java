package dao.impl;

import org.hibernate.Query;

import dao.Basedao;
import dao.IVWebSideDao;
import entity.VWebside;

public class VWebSideDaoImpl extends Basedao implements IVWebSideDao{

	public VWebside findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		String hql="from VWebside where id.factNo=? and id.factCode=? and id.yymm=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, yymm);
		VWebside side=(VWebside)query.uniqueResult();
		return side;
	}

}
