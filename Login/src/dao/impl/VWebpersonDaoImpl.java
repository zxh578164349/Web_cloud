package dao.impl;

import org.hibernate.Query;

import dao.Basedao;
import dao.IVWebpersonDao;
import entity.VWebperson;

public class VWebpersonDaoImpl extends Basedao implements IVWebpersonDao{

	public VWebperson findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		String hql="from VWebperson where id.factNo=? and id.factCode=? and id.yymm=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, yymm);
		VWebperson person=(VWebperson)query.uniqueResult();
		return person;
	}

}
