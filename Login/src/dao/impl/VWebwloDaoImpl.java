package dao.impl;

import org.hibernate.Query;

import dao.Basedao;
import dao.IVWebwloDao;
import entity.VWebwlo;

public class VWebwloDaoImpl extends Basedao implements IVWebwloDao{

	public VWebwlo findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		String hql="from VWebwlo where id.factNo=? and id.factCode=? and id.yymm=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, yymm);
		VWebwlo wlo=(VWebwlo)query.uniqueResult();
		return wlo;
	}

}
