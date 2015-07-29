package dao.impl;

import org.hibernate.Query;

import dao.Basedao;
import dao.IVSumwloDao;
import entity.VSumwlo;

public class VSumwloDaoImpl extends Basedao implements IVSumwloDao{

	public VSumwlo findById(String factNo, String yymm) {
		// TODO Auto-generated method stub
		String hql="from VSumwlo where id.factNo=? and id.yymm=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, yymm);
		VSumwlo wlo=(VSumwlo)query.uniqueResult();
		return wlo;
	}

}
