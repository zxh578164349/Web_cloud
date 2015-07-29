package dao.impl;

import org.hibernate.Query;

import dao.Basedao;
import dao.IVSumlossDao;
import entity.VSumloss;

public class VSumlossDaoImpl extends Basedao implements IVSumlossDao{

	public VSumloss findById(String factNo, String yymm) {
		// TODO Auto-generated method stub
		String hql="from VSumloss where id.factNo=? and id.yymm=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, yymm);
		VSumloss loss=(VSumloss)query.uniqueResult();
		return loss;
	}

}
