package dao.impl;

import org.hibernate.Query;

import dao.Basedao;
import dao.IVSuminvDao;
import entity.VSuminv;

public class VSuminvDaoImpl extends Basedao implements IVSuminvDao{

	public VSuminv findById(String factNo, String yymm) {
		// TODO Auto-generated method stub
		String hql="from VSuminv where id.factNo=? and yymm=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, yymm);
		VSuminv inv=(VSuminv)query.uniqueResult();
		return inv;
	}

}
