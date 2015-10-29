package dao.impl;

import org.hibernate.Query;

import dao.Basedao;
import dao.IVWebOutoutinvDao;
import entity.VWeboutputinv;

public class VWebOutoutinvDaoImpl extends Basedao implements IVWebOutoutinvDao{

	public VWeboutputinv findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		String hql="from VWeboutputinv where id.factNo=? and id.factCode=? and id.yymm=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, yymm);
		VWeboutputinv inv=(VWeboutputinv)query.uniqueResult();
		return inv;
	}

}
