package dao.impl;

import org.hibernate.Query;

import dao.Basedao;
import dao.IVSumWebmix1Dao;
import entity.VSumWebmix1;

public class VSumWebmix1DaoImpl extends Basedao implements IVSumWebmix1Dao{

	public VSumWebmix1 findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		String hql="from VSumWebmix1 where id.factNo=? and id.factCode=? and id.yymm=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, yymm);
		VSumWebmix1 mix1=(VSumWebmix1)query.uniqueResult();
		return mix1;
	}

}
